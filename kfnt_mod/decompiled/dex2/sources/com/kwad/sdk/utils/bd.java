package com.kwad.sdk.utils;

import java.lang.ref.WeakReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class bd implements Runnable {
    private WeakReference<Runnable> a;

    public bd(Runnable runnable) {
        this.a = new WeakReference<>(runnable);
    }

    @Override // java.lang.Runnable
    public void run() {
        Runnable runnable = this.a.get();
        if (runnable != null) {
            runnable.run();
        }
    }
}
