package com.mbridge.msdk.playercommon.exoplayer2.video;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Surface;
import com.mbridge.msdk.playercommon.exoplayer2.util.Assertions;
import com.mbridge.msdk.playercommon.exoplayer2.util.EGLSurfaceTexture;
import com.mbridge.msdk.playercommon.exoplayer2.util.Util;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class DummySurface extends Surface {
    private static final String EXTENSION_PROTECTED_CONTENT = "EGL_EXT_protected_content";
    private static final String EXTENSION_SURFACELESS_CONTEXT = "EGL_KHR_surfaceless_context";
    private static final String TAG = "DummySurface";
    private static int secureMode;
    private static boolean secureModeInitialized;
    public final boolean secure;
    private final DummySurfaceThread thread;
    private boolean threadReleased;

    public static synchronized boolean isSecureSupported(Context context) {
        if (!secureModeInitialized) {
            secureMode = Util.SDK_INT < 24 ? 0 : getSecureModeV24(context);
            secureModeInitialized = true;
        }
        return secureMode != 0;
    }

    public static DummySurface newInstanceV17(Context context, boolean z) {
        assertApiLevel17OrHigher();
        Assertions.checkState(!z || isSecureSupported(context));
        return new DummySurfaceThread().init(z ? secureMode : 0);
    }

    private DummySurface(DummySurfaceThread dummySurfaceThread, SurfaceTexture surfaceTexture, boolean z) {
        super(surfaceTexture);
        this.thread = dummySurfaceThread;
        this.secure = z;
    }

    @Override // android.view.Surface
    public final void release() {
        super.release();
        synchronized (this.thread) {
            if (!this.threadReleased) {
                this.thread.release();
                this.threadReleased = true;
            }
        }
    }

    private static void assertApiLevel17OrHigher() {
        if (Util.SDK_INT < 17) {
            throw new UnsupportedOperationException("Unsupported prior to API level 17");
        }
    }

    private static int getSecureModeV24(Context context) {
        String strEglQueryString;
        if (Util.SDK_INT < 26 && ("samsung".equals(Util.MANUFACTURER) || "XT1650".equals(Util.MODEL))) {
            return 0;
        }
        if ((Util.SDK_INT >= 26 || context.getPackageManager().hasSystemFeature("android.hardware.vr.high_performance")) && (strEglQueryString = EGL14.eglQueryString(EGL14.eglGetDisplay(0), 12373)) != null && strEglQueryString.contains(EXTENSION_PROTECTED_CONTENT)) {
            return strEglQueryString.contains(EXTENSION_SURFACELESS_CONTEXT) ? 1 : 2;
        }
        return 0;
    }

    private static class DummySurfaceThread extends HandlerThread implements Handler.Callback {
        private static final int MSG_INIT = 1;
        private static final int MSG_RELEASE = 2;
        private EGLSurfaceTexture eglSurfaceTexture;
        private Handler handler;
        private Error initError;
        private RuntimeException initException;
        private DummySurface surface;

        public DummySurfaceThread() {
            super("dummySurface");
        }

        public DummySurface init(int i) {
            boolean z;
            start();
            this.handler = new Handler(getLooper(), this);
            this.eglSurfaceTexture = new EGLSurfaceTexture(this.handler);
            synchronized (this) {
                z = false;
                this.handler.obtainMessage(1, i, 0).sendToTarget();
                while (this.surface == null && this.initException == null && this.initError == null) {
                    try {
                        wait();
                    } catch (InterruptedException unused) {
                        z = true;
                    }
                }
            }
            if (z) {
                Thread.currentThread().interrupt();
            }
            if (this.initException != null) {
                throw this.initException;
            }
            if (this.initError != null) {
                throw this.initError;
            }
            return (DummySurface) Assertions.checkNotNull(this.surface);
        }

        public void release() {
            Assertions.checkNotNull(this.handler);
            this.handler.sendEmptyMessage(2);
        }

        /* JADX WARN: Removed duplicated region for block: B:56:0x0055 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // android.os.Handler.Callback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean handleMessage(android.os.Message r4) {
            /*
                r3 = this;
                int r0 = r4.what
                r1 = 1
                switch(r0) {
                    case 1: goto L1e;
                    case 2: goto L7;
                    default: goto L6;
                }
            L6:
                return r1
            L7:
                r3.releaseInternal()     // Catch: java.lang.Throwable -> Le java.lang.Throwable -> L10
            La:
                r3.quit()
                goto L19
            Le:
                r4 = move-exception
                goto L1a
            L10:
                r4 = move-exception
                java.lang.String r0 = "DummySurface"
                java.lang.String r2 = "Failed to release dummy surface"
                android.util.Log.e(r0, r2, r4)     // Catch: java.lang.Throwable -> Le
                goto La
            L19:
                return r1
            L1a:
                r3.quit()
                throw r4
            L1e:
                int r4 = r4.arg1     // Catch: java.lang.Throwable -> L2c java.lang.Error -> L2e java.lang.RuntimeException -> L41
                r3.initInternal(r4)     // Catch: java.lang.Throwable -> L2c java.lang.Error -> L2e java.lang.RuntimeException -> L41
                monitor-enter(r3)
                r3.notify()     // Catch: java.lang.Throwable -> L29
                monitor-exit(r3)     // Catch: java.lang.Throwable -> L29
                goto L50
            L29:
                r4 = move-exception
                monitor-exit(r3)     // Catch: java.lang.Throwable -> L29
                throw r4
            L2c:
                r4 = move-exception
                goto L54
            L2e:
                r4 = move-exception
                java.lang.String r0 = "DummySurface"
                java.lang.String r2 = "Failed to initialize dummy surface"
                android.util.Log.e(r0, r2, r4)     // Catch: java.lang.Throwable -> L2c
                r3.initError = r4     // Catch: java.lang.Throwable -> L2c
                monitor-enter(r3)
                r3.notify()     // Catch: java.lang.Throwable -> L3e
                monitor-exit(r3)     // Catch: java.lang.Throwable -> L3e
                goto L50
            L3e:
                r4 = move-exception
                monitor-exit(r3)     // Catch: java.lang.Throwable -> L3e
                throw r4
            L41:
                r4 = move-exception
                java.lang.String r0 = "DummySurface"
                java.lang.String r2 = "Failed to initialize dummy surface"
                android.util.Log.e(r0, r2, r4)     // Catch: java.lang.Throwable -> L2c
                r3.initException = r4     // Catch: java.lang.Throwable -> L2c
                monitor-enter(r3)
                r3.notify()     // Catch: java.lang.Throwable -> L51
                monitor-exit(r3)     // Catch: java.lang.Throwable -> L51
            L50:
                return r1
            L51:
                r4 = move-exception
                monitor-exit(r3)     // Catch: java.lang.Throwable -> L51
                throw r4
            L54:
                monitor-enter(r3)
                r3.notify()     // Catch: java.lang.Throwable -> L5a
                monitor-exit(r3)     // Catch: java.lang.Throwable -> L5a
                throw r4
            L5a:
                r4 = move-exception
                monitor-exit(r3)     // Catch: java.lang.Throwable -> L5a
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.playercommon.exoplayer2.video.DummySurface.DummySurfaceThread.handleMessage(android.os.Message):boolean");
        }

        private void initInternal(int i) {
            Assertions.checkNotNull(this.eglSurfaceTexture);
            this.eglSurfaceTexture.init(i);
            this.surface = new DummySurface(this, this.eglSurfaceTexture.getSurfaceTexture(), i != 0);
        }

        private void releaseInternal() {
            Assertions.checkNotNull(this.eglSurfaceTexture);
            this.eglSurfaceTexture.release();
        }
    }
}
