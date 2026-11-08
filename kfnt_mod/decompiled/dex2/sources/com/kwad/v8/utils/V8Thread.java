package com.kwad.v8.utils;

import com.kwad.v8.V8;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class V8Thread extends Thread {
    private V8 runtime;
    private final V8Runnable target;

    public V8Thread(V8Runnable v8Runnable) {
        this.target = v8Runnable;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        this.runtime = V8.createV8Runtime();
        try {
            this.target.run(this.runtime);
            synchronized (this) {
                if (this.runtime.getLocker().hasLock()) {
                    this.runtime.close();
                    this.runtime = null;
                }
            }
        } catch (Throwable th) {
            synchronized (this) {
                if (this.runtime.getLocker().hasLock()) {
                    this.runtime.close();
                    this.runtime = null;
                }
                throw th;
            }
        }
    }
}
