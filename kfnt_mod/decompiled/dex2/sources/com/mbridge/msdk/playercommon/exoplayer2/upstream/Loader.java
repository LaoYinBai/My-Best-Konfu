package com.mbridge.msdk.playercommon.exoplayer2.upstream;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import com.mbridge.msdk.playercommon.exoplayer2.util.Assertions;
import com.mbridge.msdk.playercommon.exoplayer2.util.TraceUtil;
import com.mbridge.msdk.playercommon.exoplayer2.util.Util;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class Loader implements LoaderErrorThrower {
    public static final int DONT_RETRY = 2;
    public static final int DONT_RETRY_FATAL = 3;
    public static final int RETRY = 0;
    public static final int RETRY_RESET_ERROR_COUNT = 1;
    private LoadTask<? extends Loadable> currentTask;
    private final ExecutorService downloadExecutorService;
    private IOException fatalError;

    public interface Callback<T extends Loadable> {
        void onLoadCanceled(T t, long j, long j2, boolean z);

        void onLoadCompleted(T t, long j, long j2);

        int onLoadError(T t, long j, long j2, IOException iOException);
    }

    public interface Loadable {
        void cancelLoad();

        void load() throws InterruptedException, IOException;
    }

    public interface ReleaseCallback {
        void onLoaderReleased();
    }

    public @interface RetryAction {
    }

    public static final class UnexpectedLoaderException extends IOException {
        public UnexpectedLoaderException(Throwable th) {
            super("Unexpected " + th.getClass().getSimpleName() + ": " + th.getMessage(), th);
        }
    }

    public Loader(String str) {
        this.downloadExecutorService = Util.newSingleThreadExecutor(str);
    }

    public final <T extends Loadable> long startLoading(T t, Callback<T> callback, int i) {
        Looper looperMyLooper = Looper.myLooper();
        Assertions.checkState(looperMyLooper != null);
        this.fatalError = null;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        new LoadTask(looperMyLooper, t, callback, i, jElapsedRealtime).start(0L);
        return jElapsedRealtime;
    }

    public final boolean isLoading() {
        return this.currentTask != null;
    }

    public final void cancelLoading() {
        this.currentTask.cancel(false);
    }

    public final void release() {
        release(null);
    }

    public final void release(ReleaseCallback releaseCallback) {
        if (this.currentTask != null) {
            this.currentTask.cancel(true);
        }
        if (releaseCallback != null) {
            this.downloadExecutorService.execute(new ReleaseTask(releaseCallback));
        }
        this.downloadExecutorService.shutdown();
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.upstream.LoaderErrorThrower
    public final void maybeThrowError() throws IOException {
        maybeThrowError(Integer.MIN_VALUE);
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.upstream.LoaderErrorThrower
    public final void maybeThrowError(int i) throws IOException {
        if (this.fatalError != null) {
            throw this.fatalError;
        }
        if (this.currentTask != null) {
            LoadTask<? extends Loadable> loadTask = this.currentTask;
            if (i == Integer.MIN_VALUE) {
                i = this.currentTask.defaultMinRetryCount;
            }
            loadTask.maybeThrowError(i);
        }
    }

    private final class LoadTask<T extends Loadable> extends Handler implements Runnable {
        private static final int MSG_CANCEL = 1;
        private static final int MSG_END_OF_SOURCE = 2;
        private static final int MSG_FATAL_ERROR = 4;
        private static final int MSG_IO_EXCEPTION = 3;
        private static final int MSG_START = 0;
        private static final String TAG = "LoadTask";
        private Callback<T> callback;
        private volatile boolean canceled;
        private IOException currentError;
        public final int defaultMinRetryCount;
        private int errorCount;
        private volatile Thread executorThread;
        private final T loadable;
        private volatile boolean released;
        private final long startTimeMs;

        public LoadTask(Looper looper, T t, Callback<T> callback, int i, long j) {
            super(looper);
            this.loadable = t;
            this.callback = callback;
            this.defaultMinRetryCount = i;
            this.startTimeMs = j;
        }

        public final void maybeThrowError(int i) throws IOException {
            if (this.currentError != null && this.errorCount > i) {
                throw this.currentError;
            }
        }

        public final void start(long j) {
            Assertions.checkState(Loader.this.currentTask == null);
            Loader.this.currentTask = this;
            if (j > 0) {
                sendEmptyMessageDelayed(0, j);
            } else {
                execute();
            }
        }

        public final void cancel(boolean z) {
            this.released = z;
            this.currentError = null;
            if (hasMessages(0)) {
                removeMessages(0);
                if (!z) {
                    sendEmptyMessage(1);
                }
            } else {
                this.canceled = true;
                this.loadable.cancelLoad();
                if (this.executorThread != null) {
                    this.executorThread.interrupt();
                }
            }
            if (z) {
                finish();
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                this.callback.onLoadCanceled(this.loadable, jElapsedRealtime, jElapsedRealtime - this.startTimeMs, true);
                this.callback = null;
            }
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                this.executorThread = Thread.currentThread();
                if (!this.canceled) {
                    TraceUtil.beginSection("load:" + this.loadable.getClass().getSimpleName());
                    try {
                        this.loadable.load();
                        TraceUtil.endSection();
                    } catch (Throwable th) {
                        TraceUtil.endSection();
                        throw th;
                    }
                }
                if (this.released) {
                    return;
                }
                sendEmptyMessage(2);
            } catch (IOException e) {
                if (this.released) {
                    return;
                }
                obtainMessage(3, e).sendToTarget();
            } catch (Error e2) {
                Log.e(TAG, "Unexpected error loading stream", e2);
                if (!this.released) {
                    obtainMessage(4, e2).sendToTarget();
                }
                throw e2;
            } catch (InterruptedException unused) {
                Assertions.checkState(this.canceled);
                if (this.released) {
                    return;
                }
                sendEmptyMessage(2);
            } catch (Exception e3) {
                Log.e(TAG, "Unexpected exception loading stream", e3);
                if (this.released) {
                    return;
                }
                obtainMessage(3, new UnexpectedLoaderException(e3)).sendToTarget();
            } catch (OutOfMemoryError e4) {
                Log.e(TAG, "OutOfMemory error loading stream", e4);
                if (this.released) {
                    return;
                }
                obtainMessage(3, new UnexpectedLoaderException(e4)).sendToTarget();
            }
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (this.released) {
                return;
            }
            if (message.what == 0) {
                execute();
                return;
            }
            if (message.what == 4) {
                throw ((Error) message.obj);
            }
            finish();
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            long j = jElapsedRealtime - this.startTimeMs;
            if (this.canceled) {
                this.callback.onLoadCanceled(this.loadable, jElapsedRealtime, j, false);
                return;
            }
            switch (message.what) {
                case 1:
                    this.callback.onLoadCanceled(this.loadable, jElapsedRealtime, j, false);
                    return;
                case 2:
                    try {
                        this.callback.onLoadCompleted(this.loadable, jElapsedRealtime, j);
                        return;
                    } catch (RuntimeException e) {
                        Log.e(TAG, "Unexpected exception handling load completed", e);
                        Loader.this.fatalError = new UnexpectedLoaderException(e);
                        return;
                    }
                case 3:
                    this.currentError = (IOException) message.obj;
                    int iOnLoadError = this.callback.onLoadError(this.loadable, jElapsedRealtime, j, this.currentError);
                    if (iOnLoadError == 3) {
                        Loader.this.fatalError = this.currentError;
                        return;
                    } else {
                        if (iOnLoadError != 2) {
                            this.errorCount = iOnLoadError != 1 ? 1 + this.errorCount : 1;
                            start(getRetryDelayMillis());
                            return;
                        }
                        return;
                    }
                default:
                    return;
            }
        }

        private void execute() {
            this.currentError = null;
            Loader.this.downloadExecutorService.execute(Loader.this.currentTask);
        }

        private void finish() {
            Loader.this.currentTask = null;
        }

        private long getRetryDelayMillis() {
            return Math.min((this.errorCount - 1) * 1000, 5000);
        }
    }

    private static final class ReleaseTask implements Runnable {
        private final ReleaseCallback callback;

        public ReleaseTask(ReleaseCallback releaseCallback) {
            this.callback = releaseCallback;
        }

        @Override // java.lang.Runnable
        public final void run() {
            this.callback.onLoaderReleased();
        }
    }
}
