package com.tachikoma.core.bridge;

import android.os.Handler;
import android.os.Message;
import com.kwad.v8.V8;
import com.kwad.v8.V8Array;
import com.kwad.v8.V8Object;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class TKV8 extends V8 {
    private int jsCallJavaStack = 0;
    private boolean isRelease = false;

    protected TKV8() {
    }

    public static TKV8 createV8Runtime() {
        return new TKV8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void realRelease(boolean z) {
        if (this.isRelease) {
            return;
        }
        this.isRelease = true;
        super.release(z);
    }

    protected Object callObjectJavaMethod(long j, V8Object v8Object, V8Array v8Array) {
        try {
            this.jsCallJavaStack++;
            return super.callObjectJavaMethod(j, v8Object, v8Array);
        } finally {
            this.jsCallJavaStack--;
        }
    }

    protected void callVoidJavaMethod(long j, V8Object v8Object, V8Array v8Array) {
        try {
            this.jsCallJavaStack++;
            super.callVoidJavaMethod(j, v8Object, v8Array);
        } finally {
            this.jsCallJavaStack--;
        }
    }

    public void release(final boolean z) {
        if (this.jsCallJavaStack <= 0) {
            realRelease(z);
        } else {
            Handler handler = new Handler();
            handler.sendMessageAtFrontOfQueue(Message.obtain(handler, new Runnable() { // from class: com.tachikoma.core.bridge.TKV8.1
                @Override // java.lang.Runnable
                public void run() {
                    TKV8.this.realRelease(z);
                }
            }));
        }
    }
}
