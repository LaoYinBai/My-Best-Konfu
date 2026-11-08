package com.kwad.sdk.core.imageloader.core;

import android.graphics.Bitmap;
import android.os.Handler;
import com.kwad.sdk.core.imageloader.core.DisplayImageOptions;
import com.kwad.sdk.core.imageloader.core.assist.FailReason;
import com.kwad.sdk.core.imageloader.core.assist.ImageScaleType;
import com.kwad.sdk.core.imageloader.core.assist.ImageSize;
import com.kwad.sdk.core.imageloader.core.assist.LoadedFrom;
import com.kwad.sdk.core.imageloader.core.assist.ViewScaleType;
import com.kwad.sdk.core.imageloader.core.decode.DecodedResult;
import com.kwad.sdk.core.imageloader.core.decode.ImageDecoder;
import com.kwad.sdk.core.imageloader.core.decode.ImageDecodingInfo;
import com.kwad.sdk.core.imageloader.core.download.ImageDownloader;
import com.kwad.sdk.core.imageloader.core.imageaware.ImageAware;
import com.kwad.sdk.core.imageloader.core.listener.ImageLoadingListener;
import com.kwad.sdk.core.imageloader.core.listener.ImageLoadingProgressListener;
import com.kwad.sdk.core.imageloader.utils.IoUtils;
import com.kwad.sdk.core.imageloader.utils.L;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class LoadAndDisplayImageTask implements IoUtils.CopyListener, Runnable {
    private static final String ERROR_NO_IMAGE_STREAM = "No stream for image [%s]";
    private static final String ERROR_POST_PROCESSOR_NULL = "Post-processor returned null [%s]";
    private static final String ERROR_PRE_PROCESSOR_NULL = "Pre-processor returned null [%s]";
    private static final String ERROR_PROCESSOR_FOR_DISK_CACHE_NULL = "Bitmap processor for disk cache returned null [%s]";
    private static final String LOG_CACHE_IMAGE_IN_MEMORY = "Cache image in memory [%s]";
    private static final String LOG_CACHE_IMAGE_ON_DISK = "Cache image on disk [%s]";
    private static final String LOG_DELAY_BEFORE_LOADING = "Delay %d ms before loading...  [%s]";
    private static final String LOG_GET_IMAGE_FROM_MEMORY_CACHE_AFTER_WAITING = "...Get cached bitmap from memory after waiting. [%s]";
    private static final String LOG_LOAD_IMAGE_FROM_DISK_CACHE = "Load image from disk cache [%s]";
    private static final String LOG_LOAD_IMAGE_FROM_NETWORK = "Load image from network [%s]";
    private static final String LOG_POSTPROCESS_IMAGE = "PostProcess image before displaying [%s]";
    private static final String LOG_PREPROCESS_IMAGE = "PreProcess image before caching in memory [%s]";
    private static final String LOG_PROCESS_IMAGE_BEFORE_CACHE_ON_DISK = "Process image before cache on disk [%s]";
    private static final String LOG_RESIZE_CACHED_IMAGE_FILE = "Resize image in disk cache [%s]";
    private static final String LOG_RESUME_AFTER_PAUSE = ".. Resume loading [%s]";
    private static final String LOG_START_DISPLAY_IMAGE_TASK = "Start display image task [%s]";
    private static final String LOG_TASK_CANCELLED_IMAGEAWARE_COLLECTED = "ImageAware was collected by GC. Task is cancelled. [%s]";
    private static final String LOG_TASK_CANCELLED_IMAGEAWARE_REUSED = "ImageAware is reused for another image. Task is cancelled. [%s]";
    private static final String LOG_TASK_INTERRUPTED = "Task was interrupted [%s]";
    private static final String LOG_WAITING_FOR_IMAGE_LOADED = "Image already is loading. Waiting... [%s]";
    private static final String LOG_WAITING_FOR_RESUME = "ImageLoader is paused. Waiting...  [%s]";
    private final ImageLoaderConfiguration configuration;
    private final ImageDecoder decoder;
    private final ImageDownloader downloader;
    private final ImageLoaderEngine engine;
    private final Handler handler;
    final ImageAware imageAware;
    private final ImageLoadingInfo imageLoadingInfo;
    final ImageLoadingListener listener;
    private LoadedFrom loadedFrom = LoadedFrom.NETWORK;
    private final String memoryCacheKey;
    private final ImageDownloader networkDeniedDownloader;
    final DisplayImageOptions options;
    final ImageLoadingProgressListener progressListener;
    private final ImageDownloader slowNetworkDownloader;
    private final boolean syncLoading;
    private final ImageSize targetSize;
    final String uri;

    private static class FireCancelEventRunnable implements Runnable {
        private WeakReference<LoadAndDisplayImageTask> weakReference;

        FireCancelEventRunnable(LoadAndDisplayImageTask loadAndDisplayImageTask) {
            this.weakReference = new WeakReference<>(loadAndDisplayImageTask);
        }

        @Override // java.lang.Runnable
        public void run() {
            LoadAndDisplayImageTask loadAndDisplayImageTask = this.weakReference.get();
            if (loadAndDisplayImageTask != null) {
                loadAndDisplayImageTask.listener.onLoadingCancelled(loadAndDisplayImageTask.uri, loadAndDisplayImageTask.imageAware.getWrappedView());
            }
        }
    }

    private static class FireFailEventRunnable implements Runnable {
        final Throwable failCause;
        final FailReason.FailType failType;
        private WeakReference<LoadAndDisplayImageTask> weakReference;

        FireFailEventRunnable(LoadAndDisplayImageTask loadAndDisplayImageTask, FailReason.FailType failType, Throwable th) {
            this.weakReference = new WeakReference<>(loadAndDisplayImageTask);
            this.failCause = th;
            this.failType = failType;
        }

        @Override // java.lang.Runnable
        public void run() {
            LoadAndDisplayImageTask loadAndDisplayImageTask = this.weakReference.get();
            if (loadAndDisplayImageTask != null) {
                if (loadAndDisplayImageTask.options.shouldShowImageOnFail()) {
                    loadAndDisplayImageTask.imageAware.setImageDrawable(loadAndDisplayImageTask.options.getImageOnFail(loadAndDisplayImageTask.configuration.resources));
                }
                loadAndDisplayImageTask.listener.onLoadingFailed(loadAndDisplayImageTask.uri, loadAndDisplayImageTask.imageAware.getWrappedView(), new FailReason(this.failType, this.failCause));
            }
        }
    }

    class TaskCancelledException extends Exception {
        private static final long serialVersionUID = -504619855289909996L;

        TaskCancelledException() {
        }
    }

    public LoadAndDisplayImageTask(ImageLoaderEngine imageLoaderEngine, ImageLoadingInfo imageLoadingInfo, Handler handler) {
        this.engine = imageLoaderEngine;
        this.imageLoadingInfo = imageLoadingInfo;
        this.handler = handler;
        this.configuration = imageLoaderEngine.configuration;
        this.downloader = this.configuration.downloader;
        this.networkDeniedDownloader = this.configuration.networkDeniedDownloader;
        this.slowNetworkDownloader = this.configuration.slowNetworkDownloader;
        this.decoder = this.configuration.decoder;
        this.uri = imageLoadingInfo.uri;
        this.memoryCacheKey = imageLoadingInfo.memoryCacheKey;
        this.imageAware = imageLoadingInfo.imageAware;
        this.targetSize = imageLoadingInfo.targetSize;
        this.options = imageLoadingInfo.options;
        this.listener = imageLoadingInfo.listener;
        this.progressListener = imageLoadingInfo.progressListener;
        this.syncLoading = this.options.isSyncLoading();
    }

    private void checkTaskInterrupted() throws TaskCancelledException {
        if (isTaskInterrupted()) {
            throw new TaskCancelledException();
        }
    }

    private void checkTaskNotActual() throws TaskCancelledException {
        checkViewCollected();
        checkViewReused();
    }

    private void checkViewCollected() throws TaskCancelledException {
        if (isViewCollected()) {
            throw new TaskCancelledException();
        }
    }

    private void checkViewReused() throws TaskCancelledException {
        if (isViewReused()) {
            throw new TaskCancelledException();
        }
    }

    private DecodedResult decodeImage(String str) {
        return this.decoder.decode(new ImageDecodingInfo(this.memoryCacheKey, str, this.uri, this.targetSize, this.imageAware.getScaleType(), getDownloader(), this.options, this.listener));
    }

    private boolean delayIfNeed() throws InterruptedException {
        if (!this.options.shouldDelayBeforeLoading()) {
            return false;
        }
        L.d(LOG_DELAY_BEFORE_LOADING, Integer.valueOf(this.options.getDelayBeforeLoading()), this.memoryCacheKey);
        try {
            Thread.sleep(this.options.getDelayBeforeLoading());
            return isTaskNotActual();
        } catch (InterruptedException unused) {
            L.e(LOG_TASK_INTERRUPTED, this.memoryCacheKey);
            return true;
        }
    }

    private boolean downloadImage() throws IOException {
        InputStream stream = getDownloader().getStream(this.uri, this.options.getExtraForDownloader());
        if (stream == null) {
            L.e(ERROR_NO_IMAGE_STREAM, this.memoryCacheKey);
            return false;
        }
        try {
            return this.configuration.diskCache.save(this.uri, stream, this);
        } finally {
            IoUtils.closeSilently(stream);
        }
    }

    private void fireCancelEvent() {
        if (this.syncLoading || isTaskInterrupted()) {
            return;
        }
        runTask(new FireCancelEventRunnable(this), false, this.handler, this.engine);
    }

    private void fireFailEvent(FailReason.FailType failType, Throwable th) {
        if (this.syncLoading || isTaskInterrupted() || isTaskNotActual()) {
            return;
        }
        runTask(new FireFailEventRunnable(this, failType, th), false, this.handler, this.engine);
    }

    private boolean fireProgressEvent(final int i, final int i2) {
        if (isTaskInterrupted() || isTaskNotActual()) {
            return false;
        }
        if (this.progressListener == null) {
            return true;
        }
        runTask(new Runnable() { // from class: com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.1
            @Override // java.lang.Runnable
            public void run() {
                LoadAndDisplayImageTask.this.progressListener.onProgressUpdate(LoadAndDisplayImageTask.this.uri, LoadAndDisplayImageTask.this.imageAware.getWrappedView(), i, i2);
            }
        }, false, this.handler, this.engine);
        return true;
    }

    private ImageDownloader getDownloader() {
        return this.engine.isNetworkDenied() ? this.networkDeniedDownloader : this.engine.isSlowNetwork() ? this.slowNetworkDownloader : this.downloader;
    }

    private boolean isTaskInterrupted() {
        if (!Thread.interrupted()) {
            return false;
        }
        L.d(LOG_TASK_INTERRUPTED, this.memoryCacheKey);
        return true;
    }

    private boolean isTaskNotActual() {
        return isViewCollected() || isViewReused();
    }

    private boolean isViewCollected() {
        if (!this.imageAware.isCollected()) {
            return false;
        }
        L.d(LOG_TASK_CANCELLED_IMAGEAWARE_COLLECTED, this.memoryCacheKey);
        return true;
    }

    private boolean isViewReused() {
        if (!(!this.memoryCacheKey.equals(this.engine.getLoadingUriForView(this.imageAware)))) {
            return false;
        }
        L.d(LOG_TASK_CANCELLED_IMAGEAWARE_REUSED, this.memoryCacheKey);
        return true;
    }

    private boolean resizeAndSaveImage(int i, int i2) {
        File file = this.configuration.diskCache.get(this.uri);
        if (file == null || !file.exists()) {
            return false;
        }
        DecodedResult decodedResultDecode = this.decoder.decode(new ImageDecodingInfo(this.memoryCacheKey, ImageDownloader.Scheme.FILE.wrap(file.getAbsolutePath()), this.uri, new ImageSize(i, i2), ViewScaleType.FIT_INSIDE, getDownloader(), new DisplayImageOptions.Builder().cloneFrom(this.options).imageScaleType(ImageScaleType.IN_SAMPLE_INT).build(), this.listener));
        Bitmap bitmapProcess = decodedResultDecode != null ? decodedResultDecode.mBitmap : null;
        if (bitmapProcess != null && this.configuration.processorForDiskCache != null) {
            L.d(LOG_PROCESS_IMAGE_BEFORE_CACHE_ON_DISK, this.memoryCacheKey);
            bitmapProcess = this.configuration.processorForDiskCache.process(bitmapProcess);
            if (bitmapProcess == null) {
                L.e(ERROR_PROCESSOR_FOR_DISK_CACHE_NULL, this.memoryCacheKey);
            }
        }
        if (bitmapProcess == null) {
            return false;
        }
        boolean zSave = this.configuration.diskCache.save(this.uri, bitmapProcess);
        bitmapProcess.recycle();
        return zSave;
    }

    static void runTask(Runnable runnable, boolean z, Handler handler, ImageLoaderEngine imageLoaderEngine) {
        if (z) {
            runnable.run();
        } else if (handler == null) {
            imageLoaderEngine.fireCallback(runnable);
        } else {
            handler.post(runnable);
        }
    }

    private boolean tryCacheImageOnDisk() {
        L.d(LOG_CACHE_IMAGE_ON_DISK, this.memoryCacheKey);
        try {
            boolean zDownloadImage = downloadImage();
            if (!zDownloadImage) {
                return zDownloadImage;
            }
            int i = this.configuration.maxImageWidthForDiskCache;
            int i2 = this.configuration.maxImageHeightForDiskCache;
            if (i <= 0 && i2 <= 0) {
                return zDownloadImage;
            }
            L.d(LOG_RESIZE_CACHED_IMAGE_FILE, this.memoryCacheKey);
            resizeAndSaveImage(i, i2);
            return zDownloadImage;
        } catch (IOException e) {
            L.e(e);
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0096 A[Catch: Throwable -> 0x004a, OutOfMemoryError -> 0x004c, IOException -> 0x004e, TaskCancelledException -> 0x00ba, IllegalStateException -> 0x00bd, TRY_LEAVE, TryCatch #1 {TaskCancelledException -> 0x00ba, blocks: (B:3:0x0001, B:5:0x000f, B:7:0x0015, B:9:0x001f, B:13:0x0043, B:31:0x0090, B:33:0x0096, B:22:0x0051, B:24:0x006a, B:26:0x0070, B:28:0x007c, B:29:0x0086), top: B:51:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.kwad.sdk.core.imageloader.core.decode.DecodedResult tryLoadBitmap() throws com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException {
        /*
            r9 = this;
            r0 = 0
            com.kwad.sdk.core.imageloader.core.ImageLoaderConfiguration r1 = r9.configuration     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> La6 java.io.IOException -> Lb0 com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbc
            com.kwad.sdk.core.imageloader.cache.disc.DiskCache r1 = r1.diskCache     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> La6 java.io.IOException -> Lb0 com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbc
            java.lang.String r2 = r9.uri     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> La6 java.io.IOException -> Lb0 com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbc
            java.io.File r1 = r1.get(r2)     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> La6 java.io.IOException -> Lb0 com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbc
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L40
            boolean r4 = r1.exists()     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> La6 java.io.IOException -> Lb0 com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbc
            if (r4 == 0) goto L40
            long r4 = r1.length()     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> La6 java.io.IOException -> Lb0 com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbc
            r6 = 0
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 <= 0) goto L40
            java.lang.String r4 = "Load image from disk cache [%s]"
            java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> La6 java.io.IOException -> Lb0 com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbc
            java.lang.String r6 = r9.memoryCacheKey     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> La6 java.io.IOException -> Lb0 com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbc
            r5[r2] = r6     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> La6 java.io.IOException -> Lb0 com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbc
            com.kwad.sdk.core.imageloader.utils.L.d(r4, r5)     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> La6 java.io.IOException -> Lb0 com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbc
            com.kwad.sdk.core.imageloader.core.assist.LoadedFrom r4 = com.kwad.sdk.core.imageloader.core.assist.LoadedFrom.DISC_CACHE     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> La6 java.io.IOException -> Lb0 com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbc
            r9.loadedFrom = r4     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> La6 java.io.IOException -> Lb0 com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbc
            r9.checkTaskNotActual()     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> La6 java.io.IOException -> Lb0 com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbc
            com.kwad.sdk.core.imageloader.core.download.ImageDownloader$Scheme r4 = com.kwad.sdk.core.imageloader.core.download.ImageDownloader.Scheme.FILE     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> La6 java.io.IOException -> Lb0 com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbc
            java.lang.String r1 = r1.getAbsolutePath()     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> La6 java.io.IOException -> Lb0 com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbc
            java.lang.String r1 = r4.wrap(r1)     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> La6 java.io.IOException -> Lb0 com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbc
            com.kwad.sdk.core.imageloader.core.decode.DecodedResult r1 = r9.decodeImage(r1)     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> La6 java.io.IOException -> Lb0 com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbc
            goto L41
        L40:
            r1 = r0
        L41:
            if (r1 == 0) goto L51
            boolean r4 = r1.isDecoded()     // Catch: java.lang.Throwable -> L4a java.lang.OutOfMemoryError -> L4c java.io.IOException -> L4e com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbd
            if (r4 != 0) goto L8e
            goto L51
        L4a:
            r0 = move-exception
            goto La0
        L4c:
            r0 = move-exception
            goto Laa
        L4e:
            r0 = move-exception
            goto Lb4
        L51:
            java.lang.String r4 = "Load image from network [%s]"
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L4a java.lang.OutOfMemoryError -> L4c java.io.IOException -> L4e com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbd
            java.lang.String r5 = r9.memoryCacheKey     // Catch: java.lang.Throwable -> L4a java.lang.OutOfMemoryError -> L4c java.io.IOException -> L4e com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbd
            r3[r2] = r5     // Catch: java.lang.Throwable -> L4a java.lang.OutOfMemoryError -> L4c java.io.IOException -> L4e com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbd
            com.kwad.sdk.core.imageloader.utils.L.d(r4, r3)     // Catch: java.lang.Throwable -> L4a java.lang.OutOfMemoryError -> L4c java.io.IOException -> L4e com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbd
            com.kwad.sdk.core.imageloader.core.assist.LoadedFrom r2 = com.kwad.sdk.core.imageloader.core.assist.LoadedFrom.NETWORK     // Catch: java.lang.Throwable -> L4a java.lang.OutOfMemoryError -> L4c java.io.IOException -> L4e com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbd
            r9.loadedFrom = r2     // Catch: java.lang.Throwable -> L4a java.lang.OutOfMemoryError -> L4c java.io.IOException -> L4e com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbd
            java.lang.String r2 = r9.uri     // Catch: java.lang.Throwable -> L4a java.lang.OutOfMemoryError -> L4c java.io.IOException -> L4e com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbd
            com.kwad.sdk.core.imageloader.core.DisplayImageOptions r3 = r9.options     // Catch: java.lang.Throwable -> L4a java.lang.OutOfMemoryError -> L4c java.io.IOException -> L4e com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbd
            boolean r3 = r3.isCacheOnDisk()     // Catch: java.lang.Throwable -> L4a java.lang.OutOfMemoryError -> L4c java.io.IOException -> L4e com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbd
            if (r3 == 0) goto L86
            boolean r3 = r9.tryCacheImageOnDisk()     // Catch: java.lang.Throwable -> L4a java.lang.OutOfMemoryError -> L4c java.io.IOException -> L4e com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbd
            if (r3 == 0) goto L86
            com.kwad.sdk.core.imageloader.core.ImageLoaderConfiguration r3 = r9.configuration     // Catch: java.lang.Throwable -> L4a java.lang.OutOfMemoryError -> L4c java.io.IOException -> L4e com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbd
            com.kwad.sdk.core.imageloader.cache.disc.DiskCache r3 = r3.diskCache     // Catch: java.lang.Throwable -> L4a java.lang.OutOfMemoryError -> L4c java.io.IOException -> L4e com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbd
            java.lang.String r4 = r9.uri     // Catch: java.lang.Throwable -> L4a java.lang.OutOfMemoryError -> L4c java.io.IOException -> L4e com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbd
            java.io.File r3 = r3.get(r4)     // Catch: java.lang.Throwable -> L4a java.lang.OutOfMemoryError -> L4c java.io.IOException -> L4e com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbd
            if (r3 == 0) goto L86
            com.kwad.sdk.core.imageloader.core.download.ImageDownloader$Scheme r2 = com.kwad.sdk.core.imageloader.core.download.ImageDownloader.Scheme.FILE     // Catch: java.lang.Throwable -> L4a java.lang.OutOfMemoryError -> L4c java.io.IOException -> L4e com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbd
            java.lang.String r3 = r3.getAbsolutePath()     // Catch: java.lang.Throwable -> L4a java.lang.OutOfMemoryError -> L4c java.io.IOException -> L4e com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbd
            java.lang.String r2 = r2.wrap(r3)     // Catch: java.lang.Throwable -> L4a java.lang.OutOfMemoryError -> L4c java.io.IOException -> L4e com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbd
        L86:
            r9.checkTaskNotActual()     // Catch: java.lang.Throwable -> L4a java.lang.OutOfMemoryError -> L4c java.io.IOException -> L4e com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbd
            com.kwad.sdk.core.imageloader.core.decode.DecodedResult r2 = r9.decodeImage(r2)     // Catch: java.lang.Throwable -> L4a java.lang.OutOfMemoryError -> L4c java.io.IOException -> L4e com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbd
            r1 = r2
        L8e:
            if (r1 == 0) goto L96
            boolean r2 = r1.isDecoded()     // Catch: java.lang.Throwable -> L4a java.lang.OutOfMemoryError -> L4c java.io.IOException -> L4e com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbd
            if (r2 != 0) goto Lc2
        L96:
            com.kwad.sdk.core.imageloader.core.assist.FailReason$FailType r2 = com.kwad.sdk.core.imageloader.core.assist.FailReason.FailType.DECODING_ERROR     // Catch: java.lang.Throwable -> L4a java.lang.OutOfMemoryError -> L4c java.io.IOException -> L4e com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbd
            r9.fireFailEvent(r2, r0)     // Catch: java.lang.Throwable -> L4a java.lang.OutOfMemoryError -> L4c java.io.IOException -> L4e com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException -> Lba java.lang.IllegalStateException -> Lbd
            goto Lc2
        L9c:
            r1 = move-exception
            r8 = r1
            r1 = r0
            r0 = r8
        La0:
            com.kwad.sdk.core.imageloader.utils.L.e(r0)
            com.kwad.sdk.core.imageloader.core.assist.FailReason$FailType r2 = com.kwad.sdk.core.imageloader.core.assist.FailReason.FailType.UNKNOWN
            goto Lbf
        La6:
            r1 = move-exception
            r8 = r1
            r1 = r0
            r0 = r8
        Laa:
            com.kwad.sdk.core.imageloader.utils.L.e(r0)
            com.kwad.sdk.core.imageloader.core.assist.FailReason$FailType r2 = com.kwad.sdk.core.imageloader.core.assist.FailReason.FailType.OUT_OF_MEMORY
            goto Lbf
        Lb0:
            r1 = move-exception
            r8 = r1
            r1 = r0
            r0 = r8
        Lb4:
            com.kwad.sdk.core.imageloader.utils.L.e(r0)
            com.kwad.sdk.core.imageloader.core.assist.FailReason$FailType r2 = com.kwad.sdk.core.imageloader.core.assist.FailReason.FailType.IO_ERROR
            goto Lbf
        Lba:
            r0 = move-exception
            throw r0
        Lbc:
            r1 = r0
        Lbd:
            com.kwad.sdk.core.imageloader.core.assist.FailReason$FailType r2 = com.kwad.sdk.core.imageloader.core.assist.FailReason.FailType.NETWORK_DENIED
        Lbf:
            r9.fireFailEvent(r2, r0)
        Lc2:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.core.imageloader.core.LoadAndDisplayImageTask.tryLoadBitmap():com.kwad.sdk.core.imageloader.core.decode.DecodedResult");
    }

    private boolean waitIfPaused() {
        AtomicBoolean pause = this.engine.getPause();
        if (pause.get()) {
            synchronized (this.engine.getPauseLock()) {
                if (pause.get()) {
                    L.d(LOG_WAITING_FOR_RESUME, this.memoryCacheKey);
                    try {
                        this.engine.getPauseLock().wait();
                        L.d(LOG_RESUME_AFTER_PAUSE, this.memoryCacheKey);
                    } catch (InterruptedException unused) {
                        L.e(LOG_TASK_INTERRUPTED, this.memoryCacheKey);
                        return true;
                    }
                }
            }
        }
        return isTaskNotActual();
    }

    String getLoadingUri() {
        return this.uri;
    }

    @Override // com.kwad.sdk.core.imageloader.utils.IoUtils.CopyListener
    public boolean onBytesCopied(int i, int i2) {
        return this.syncLoading || fireProgressEvent(i, i2);
    }

    @Override // java.lang.Runnable
    public void run() {
        if (waitIfPaused() || delayIfNeed()) {
            return;
        }
        ReentrantLock reentrantLock = this.imageLoadingInfo.loadFromUriLock;
        L.d(LOG_START_DISPLAY_IMAGE_TASK, this.memoryCacheKey);
        if (reentrantLock.isLocked()) {
            L.d(LOG_WAITING_FOR_IMAGE_LOADED, this.memoryCacheKey);
        }
        reentrantLock.lock();
        try {
            checkTaskNotActual();
            DecodedResult decodedResultTryLoadBitmap = this.configuration.memoryCache.get(this.memoryCacheKey);
            if (decodedResultTryLoadBitmap == null || !decodedResultTryLoadBitmap.isDecoded()) {
                decodedResultTryLoadBitmap = tryLoadBitmap();
                if (decodedResultTryLoadBitmap != null && decodedResultTryLoadBitmap.isDecoded()) {
                    checkTaskNotActual();
                    checkTaskInterrupted();
                    if (this.options.shouldPreProcess()) {
                        L.d(LOG_PREPROCESS_IMAGE, this.memoryCacheKey);
                        if (decodedResultTryLoadBitmap.mBitmap != null) {
                            decodedResultTryLoadBitmap.mBitmap = this.options.getPreProcessor().process(decodedResultTryLoadBitmap.mBitmap);
                        }
                        if (decodedResultTryLoadBitmap.mBitmap == null) {
                            L.e(ERROR_PRE_PROCESSOR_NULL, this.memoryCacheKey);
                        }
                    }
                    if (decodedResultTryLoadBitmap != null && this.options.isCacheInMemory()) {
                        L.d(LOG_CACHE_IMAGE_IN_MEMORY, this.memoryCacheKey);
                        this.configuration.memoryCache.put(this.memoryCacheKey, decodedResultTryLoadBitmap);
                    }
                }
                return;
            }
            this.loadedFrom = LoadedFrom.MEMORY_CACHE;
            L.d(LOG_GET_IMAGE_FROM_MEMORY_CACHE_AFTER_WAITING, this.memoryCacheKey);
            if (decodedResultTryLoadBitmap != null && this.options.shouldPostProcess()) {
                L.d(LOG_POSTPROCESS_IMAGE, this.memoryCacheKey);
                decodedResultTryLoadBitmap.mBitmap = this.options.getPostProcessor().process(decodedResultTryLoadBitmap.mBitmap);
                if (decodedResultTryLoadBitmap.mBitmap == null) {
                    L.e(ERROR_POST_PROCESSOR_NULL, this.memoryCacheKey);
                }
            }
            checkTaskNotActual();
            checkTaskInterrupted();
            reentrantLock.unlock();
            runTask(new DisplayBitmapTask(decodedResultTryLoadBitmap, this.imageLoadingInfo, this.engine, this.loadedFrom), this.syncLoading, this.handler, this.engine);
        } catch (TaskCancelledException unused) {
            fireCancelEvent();
        } finally {
            reentrantLock.unlock();
        }
    }
}
