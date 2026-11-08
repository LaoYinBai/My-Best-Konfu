package com.meizu.cloud.pushsdk.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import com.meizu.cloud.pushinternal.DebugLogger;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class IntentReceiver extends BroadcastReceiver {
    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(0);
    private static Handler sHandler;
    private static HandlerThread sHandlerThread;

    protected abstract void onHandleIntent(Context context, Intent intent);

    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, final Intent intent) {
        DebugLogger.init(context);
        if (ATOMIC_INTEGER.getAndIncrement() == 0) {
            sHandlerThread = new HandlerThread("IntentReceiver", 10);
            sHandlerThread.start();
            sHandler = new Handler(sHandlerThread.getLooper());
        }
        sHandler.post(new Runnable() { // from class: com.meizu.cloud.pushsdk.base.IntentReceiver.1
            @Override // java.lang.Runnable
            public void run() {
                IntentReceiver.this.onHandleIntent(context, intent);
                if (IntentReceiver.ATOMIC_INTEGER.decrementAndGet() == 0) {
                    IntentReceiver.sHandlerThread.quit();
                }
            }
        });
    }
}
