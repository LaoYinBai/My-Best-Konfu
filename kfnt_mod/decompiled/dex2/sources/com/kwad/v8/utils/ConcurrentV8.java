package com.kwad.v8.utils;

import com.kwad.v8.V8;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class ConcurrentV8 {
    private V8 v8;

    public ConcurrentV8() {
        this.v8 = null;
        this.v8 = V8.createV8Runtime();
        this.v8.getLocker().release();
    }

    public V8 getV8() {
        return this.v8;
    }

    public void release() {
        if (this.v8 == null || this.v8.isReleased()) {
            return;
        }
        run(new V8Runnable() { // from class: com.kwad.v8.utils.ConcurrentV8.1
            @Override // com.kwad.v8.utils.V8Runnable
            public void run(V8 v8) {
                if (v8 == null || v8.isReleased()) {
                    return;
                }
                v8.close();
            }
        });
    }

    public synchronized void run(V8Runnable v8Runnable) {
        try {
            this.v8.getLocker().acquire();
            v8Runnable.run(this.v8);
        } finally {
            if (this.v8 != null && this.v8.getLocker() != null && this.v8.getLocker().hasLock()) {
                this.v8.getLocker().release();
            }
        }
    }
}
