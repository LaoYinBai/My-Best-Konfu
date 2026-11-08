package com.unity.purchasing.common;

import android.os.Handler;
import android.os.Looper;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DelayedHandler implements IDelayedExecutor {
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override // com.unity.purchasing.common.IDelayedExecutor
    public void ExecuteDelayed(Runnable runnable, long j) {
        this.handler.postDelayed(runnable, j);
    }
}
