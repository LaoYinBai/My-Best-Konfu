package com.ss.union.game.sdk.core.base.event;

import android.os.Looper;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TeaSafeThread extends TeaThread {
    private static final String a = "TeaSafeThread";
    private static volatile TeaSafeThread b;

    public interface ExceptionHandler {
        void handlerException(Thread thread, Throwable th);
    }

    private TeaSafeThread() {
        super(a);
    }

    private TeaSafeThread(String str) {
        super(str);
    }

    public static TeaSafeThread getInst() {
        if (b == null) {
            synchronized (TeaSafeThread.class) {
                if (b == null) {
                    b = new TeaSafeThread();
                    b.start();
                }
            }
        }
        return b;
    }

    public void install(ExceptionHandler exceptionHandler) {
        b.a(this, exceptionHandler);
    }

    @Override // com.ss.union.game.sdk.core.base.event.TeaThread, android.os.HandlerThread
    protected void onLooperPrepared() {
        super.onLooperPrepared();
    }

    @Override // android.os.HandlerThread
    public boolean quit() {
        b.a(this);
        return super.quit();
    }

    @Override // android.os.HandlerThread
    public boolean quitSafely() {
        b.a(this);
        return super.quitSafely();
    }

    private static final class b {
        private static ThreadLocal<Boolean> a = new ThreadLocal<>();

        private b() {
        }

        public static synchronized void a(TeaThread teaThread, final ExceptionHandler exceptionHandler) {
            teaThread.ensureTeaThreadLite(new Runnable() { // from class: com.ss.union.game.sdk.core.base.event.TeaSafeThread.b.1
                @Override // java.lang.Runnable
                public void run() {
                    b.a.set(true);
                    while (true) {
                        try {
                            Looper.loop();
                        } catch (Throwable th) {
                            if (th instanceof a) {
                                return;
                            }
                            if (exceptionHandler != null) {
                                exceptionHandler.handlerException(Thread.currentThread(), th);
                            }
                        }
                    }
                }
            });
        }

        public static synchronized void a(TeaThread teaThread) {
            teaThread.ensureTeaThreadLite(new Runnable() { // from class: com.ss.union.game.sdk.core.base.event.TeaSafeThread.b.2
                @Override // java.lang.Runnable
                public void run() {
                    Boolean bool = (Boolean) b.a.get();
                    if (bool == null || !bool.booleanValue()) {
                        return;
                    }
                    b.a.set(false);
                    throw new a("Quit TeaSafeLooper.....");
                }
            });
        }
    }

    private static class a extends RuntimeException {
        a(String str) {
            super(str);
        }
    }
}
