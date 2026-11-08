package com.ss.union.game.sdk.core.luban;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Luban implements Handler.Callback {
    private static final String DEFAULT_DISK_CACHE_DIR = "luban_disk_cache";
    private static final int MSG_COMPRESS_ERROR = 2;
    private static final int MSG_COMPRESS_START = 1;
    private static final int MSG_COMPRESS_SUCCESS = 0;
    private static final String TAG = "Luban";
    private boolean focusAlpha;
    private OnCompressListener mCompressListener;
    private CompressionPredicate mCompressionPredicate;
    private Handler mHandler;
    private int mLeastCompressSize;
    private OnRenameListener mRenameListener;
    private List<InputStreamProvider> mStreamProviders;
    private String mTargetDir;

    private Luban(Builder builder) {
        this.mTargetDir = builder.mTargetDir;
        this.mRenameListener = builder.mRenameListener;
        this.mStreamProviders = builder.mStreamProviders;
        this.mCompressListener = builder.mCompressListener;
        this.mLeastCompressSize = builder.mLeastCompressSize;
        this.mCompressionPredicate = builder.mCompressionPredicate;
        this.mHandler = new Handler(Looper.getMainLooper(), this);
    }

    public static Builder with(Context context) {
        return new Builder(context);
    }

    private File getImageCacheFile(Context context, String str) {
        if (TextUtils.isEmpty(this.mTargetDir)) {
            this.mTargetDir = getImageCacheDir(context).getAbsolutePath();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.mTargetDir);
        sb.append("/");
        sb.append(System.currentTimeMillis());
        sb.append((int) (Math.random() * 1000.0d));
        if (TextUtils.isEmpty(str)) {
            str = ".jpg";
        }
        sb.append(str);
        return new File(sb.toString());
    }

    private File getImageCustomFile(Context context, String str) {
        if (TextUtils.isEmpty(this.mTargetDir)) {
            this.mTargetDir = getImageCacheDir(context).getAbsolutePath();
        }
        return new File(this.mTargetDir + "/" + str);
    }

    private File getImageCacheDir(Context context) {
        return getImageCacheDir(context, DEFAULT_DISK_CACHE_DIR);
    }

    private static File getImageCacheDir(Context context, String str) {
        File externalCacheDir = context.getExternalCacheDir();
        if (externalCacheDir != null) {
            File file = new File(externalCacheDir, str);
            if (file.mkdirs() || (file.exists() && file.isDirectory())) {
                return file;
            }
            return null;
        }
        if (Log.isLoggable(TAG, 6)) {
            Log.e(TAG, "default disk cache dir is null");
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launch(final Context context) {
        if (this.mStreamProviders == null || (this.mStreamProviders.size() == 0 && this.mCompressListener != null)) {
            this.mCompressListener.onError(new NullPointerException("image file cannot be null"));
        }
        Iterator<InputStreamProvider> it = this.mStreamProviders.iterator();
        while (it.hasNext()) {
            final InputStreamProvider next = it.next();
            AsyncTask.SERIAL_EXECUTOR.execute(new Runnable() { // from class: com.ss.union.game.sdk.core.luban.Luban.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Luban.this.mHandler.sendMessage(Luban.this.mHandler.obtainMessage(1));
                        Luban.this.mHandler.sendMessage(Luban.this.mHandler.obtainMessage(0, Luban.this.compress(context, next)));
                    } catch (IOException e) {
                        Luban.this.mHandler.sendMessage(Luban.this.mHandler.obtainMessage(2, e));
                    }
                }
            });
            it.remove();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public File get(InputStreamProvider inputStreamProvider, Context context) throws IOException {
        try {
            return new b(inputStreamProvider, getImageCacheFile(context, a.SINGLE.a(inputStreamProvider)), this.focusAlpha).a();
        } finally {
            inputStreamProvider.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<File> get(Context context) throws IOException {
        ArrayList arrayList = new ArrayList();
        Iterator<InputStreamProvider> it = this.mStreamProviders.iterator();
        while (it.hasNext()) {
            arrayList.add(compress(context, it.next()));
            it.remove();
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public File compress(Context context, InputStreamProvider inputStreamProvider) throws IOException {
        try {
            return compressReal(context, inputStreamProvider);
        } finally {
            inputStreamProvider.close();
        }
    }

    private File compressReal(Context context, InputStreamProvider inputStreamProvider) throws IOException {
        File imageCacheFile = getImageCacheFile(context, a.SINGLE.a(inputStreamProvider));
        if (this.mRenameListener != null) {
            imageCacheFile = getImageCustomFile(context, this.mRenameListener.rename(inputStreamProvider.getPath()));
        }
        if (this.mCompressionPredicate != null) {
            if (this.mCompressionPredicate.apply(inputStreamProvider.getPath()) && a.SINGLE.a(this.mLeastCompressSize, inputStreamProvider.getPath())) {
                return new b(inputStreamProvider, imageCacheFile, this.focusAlpha).a();
            }
            return new File(inputStreamProvider.getPath());
        }
        if (a.SINGLE.a(this.mLeastCompressSize, inputStreamProvider.getPath())) {
            return new b(inputStreamProvider, imageCacheFile, this.focusAlpha).a();
        }
        return new File(inputStreamProvider.getPath());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (this.mCompressListener == null) {
            return false;
        }
        switch (message.what) {
            case 0:
                this.mCompressListener.onSuccess((File) message.obj);
                return false;
            case 1:
                this.mCompressListener.onStart();
                return false;
            case 2:
                this.mCompressListener.onError((Throwable) message.obj);
                return false;
            default:
                return false;
        }
    }

    public static class Builder {
        private Context context;
        private boolean focusAlpha;
        private OnCompressListener mCompressListener;
        private CompressionPredicate mCompressionPredicate;
        private OnRenameListener mRenameListener;
        private String mTargetDir;
        private int mLeastCompressSize = 100;
        private List<InputStreamProvider> mStreamProviders = new ArrayList();

        public Builder putGear(int i) {
            return this;
        }

        Builder(Context context) {
            this.context = context;
        }

        private Luban build() {
            return new Luban(this);
        }

        public Builder load(InputStreamProvider inputStreamProvider) {
            this.mStreamProviders.add(inputStreamProvider);
            return this;
        }

        public Builder load(final File file) {
            this.mStreamProviders.add(new InputStreamAdapter() { // from class: com.ss.union.game.sdk.core.luban.Luban.Builder.1
                @Override // com.ss.union.game.sdk.core.luban.InputStreamAdapter
                public InputStream openInternal() throws IOException {
                    return new FileInputStream(file);
                }

                @Override // com.ss.union.game.sdk.core.luban.InputStreamProvider
                public String getPath() {
                    return file.getAbsolutePath();
                }
            });
            return this;
        }

        public Builder load(final String str) {
            this.mStreamProviders.add(new InputStreamAdapter() { // from class: com.ss.union.game.sdk.core.luban.Luban.Builder.2
                @Override // com.ss.union.game.sdk.core.luban.InputStreamAdapter
                public InputStream openInternal() throws IOException {
                    return new FileInputStream(str);
                }

                @Override // com.ss.union.game.sdk.core.luban.InputStreamProvider
                public String getPath() {
                    return str;
                }
            });
            return this;
        }

        public <T> Builder load(List<T> list) {
            for (T t : list) {
                if (t instanceof String) {
                    load((String) t);
                } else if (t instanceof File) {
                    load((File) t);
                } else if (t instanceof Uri) {
                    load((Uri) t);
                } else {
                    throw new IllegalArgumentException("Incoming data type exception, it must be String, File, Uri or Bitmap");
                }
            }
            return this;
        }

        public Builder load(final Uri uri) {
            this.mStreamProviders.add(new InputStreamAdapter() { // from class: com.ss.union.game.sdk.core.luban.Luban.Builder.3
                @Override // com.ss.union.game.sdk.core.luban.InputStreamAdapter
                public InputStream openInternal() throws IOException {
                    return Builder.this.context.getContentResolver().openInputStream(uri);
                }

                @Override // com.ss.union.game.sdk.core.luban.InputStreamProvider
                public String getPath() {
                    return uri.getPath();
                }
            });
            return this;
        }

        public Builder setRenameListener(OnRenameListener onRenameListener) {
            this.mRenameListener = onRenameListener;
            return this;
        }

        public Builder setCompressListener(OnCompressListener onCompressListener) {
            this.mCompressListener = onCompressListener;
            return this;
        }

        public Builder setTargetDir(String str) {
            this.mTargetDir = str;
            return this;
        }

        public Builder setFocusAlpha(boolean z) {
            this.focusAlpha = z;
            return this;
        }

        public Builder ignoreBy(int i) {
            this.mLeastCompressSize = i;
            return this;
        }

        public Builder filter(CompressionPredicate compressionPredicate) {
            this.mCompressionPredicate = compressionPredicate;
            return this;
        }

        public void launch() {
            build().launch(this.context);
        }

        public File get(final String str) throws IOException {
            return build().get(new InputStreamAdapter() { // from class: com.ss.union.game.sdk.core.luban.Luban.Builder.4
                @Override // com.ss.union.game.sdk.core.luban.InputStreamAdapter
                public InputStream openInternal() throws IOException {
                    return new FileInputStream(str);
                }

                @Override // com.ss.union.game.sdk.core.luban.InputStreamProvider
                public String getPath() {
                    return str;
                }
            }, this.context);
        }

        public List<File> get() throws IOException {
            return build().get(this.context);
        }
    }
}
