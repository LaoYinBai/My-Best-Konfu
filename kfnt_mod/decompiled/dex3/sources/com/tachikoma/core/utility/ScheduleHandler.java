package com.tachikoma.core.utility;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ScheduleHandler extends Handler {
    private final long mInterval;
    private final Runnable mRunnable;
    private boolean mStopped;

    public ScheduleHandler(long j, Runnable runnable) {
        this.mStopped = true;
        this.mInterval = j;
        this.mRunnable = runnable;
    }

    public ScheduleHandler(Looper looper, long j, Runnable runnable) {
        super(looper);
        this.mStopped = true;
        this.mInterval = j;
        this.mRunnable = runnable;
    }

    public void delayStart() {
        delayStart(this.mInterval);
    }

    public void delayStart(long j) {
        if (this.mStopped) {
            this.mStopped = false;
            sendEmptyMessageDelayed(0, j);
        }
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (this.mStopped) {
            return;
        }
        this.mRunnable.run();
        sendEmptyMessageDelayed(0, this.mInterval);
    }

    public boolean isRunning() {
        return !this.mStopped;
    }

    public void start() {
        if (this.mStopped) {
            this.mStopped = false;
            sendEmptyMessage(0);
        }
    }

    public void stop() {
        this.mStopped = true;
        removeMessages(0);
    }
}
