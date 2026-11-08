package com.ss.union.game.sdk.common.util;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CountDownUtils {

    public interface OnCountDownTimerListener {
        void onFinish();

        void onTick(long j);
    }

    public static ITimer createTimer(long j, long j2, TimeUnit timeUnit, OnCountDownTimerListener onCountDownTimerListener) {
        return new a(j, j2, timeUnit, onCountDownTimerListener);
    }

    private static class a extends ITimer {
        private Handler a;
        private long b;
        private long c;
        private long d;
        private long e;
        private Runnable f;

        public a(long j, long j2, TimeUnit timeUnit, OnCountDownTimerListener onCountDownTimerListener) {
            super(onCountDownTimerListener, timeUnit);
            this.f = new Runnable() { // from class: com.ss.union.game.sdk.common.util.CountDownUtils.a.1
                @Override // java.lang.Runnable
                public void run() {
                    a.this.c -= a.this.d;
                    if (a.this.c <= 0) {
                        a.this.c = 0L;
                    }
                    a.this.publicTick(a.this.c);
                    if (a.this.c != 0) {
                        a.this.a();
                    } else {
                        a.this.publicFinish();
                    }
                }
            };
            this.a = new Handler(Looper.getMainLooper());
            this.b = j;
            this.c = this.b;
            this.d = j2;
            this.e = timeToMillis(j2);
        }

        @Override // com.ss.union.game.sdk.common.util.CountDownUtils.ITimer
        public synchronized void start() {
            publicTick(this.c);
            a();
        }

        @Override // com.ss.union.game.sdk.common.util.CountDownUtils.ITimer
        public synchronized void pause() {
            b();
        }

        @Override // com.ss.union.game.sdk.common.util.CountDownUtils.ITimer
        public synchronized void resume() {
            start();
        }

        @Override // com.ss.union.game.sdk.common.util.CountDownUtils.ITimer
        public synchronized void cancel() {
            b();
            this.c = this.b;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            b();
            this.a.postDelayed(this.f, this.e);
        }

        private void b() {
            this.a.removeCallbacksAndMessages(null);
        }
    }

    public static abstract class ITimer {
        private OnCountDownTimerListener a;
        private TimeUnit b;

        public abstract void cancel();

        public abstract void pause();

        public abstract void resume();

        public abstract void start();

        public ITimer(OnCountDownTimerListener onCountDownTimerListener, TimeUnit timeUnit) {
            this.a = onCountDownTimerListener;
            this.b = timeUnit;
        }

        protected long timeToMillis(long j) {
            return this.b.toMillis(j);
        }

        protected void publicTick(long j) {
            if (this.a != null) {
                this.a.onTick(j);
            }
        }

        protected void publicFinish() {
            if (this.a != null) {
                this.a.onFinish();
            }
        }
    }
}
