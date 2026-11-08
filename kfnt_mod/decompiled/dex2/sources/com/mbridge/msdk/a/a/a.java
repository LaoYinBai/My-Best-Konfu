package com.mbridge.msdk.a.a;

import com.mbridge.msdk.out.PreloadListener;
import java.lang.ref.WeakReference;

/* compiled from: PreloadListenerEx.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a implements PreloadListener {
    WeakReference<PreloadListener> a;
    private boolean b = false;
    private int c = 0;

    public final boolean a() {
        return this.b;
    }

    public final void a(boolean z) {
        this.b = z;
    }

    public a(PreloadListener preloadListener) {
        if (preloadListener != null) {
            this.a = new WeakReference<>(preloadListener);
        }
    }

    @Override // com.mbridge.msdk.out.PreloadListener
    public final void onPreloadSucceed() {
        if (this.a == null || this.a.get() == null) {
            return;
        }
        this.a.get().onPreloadSucceed();
    }

    @Override // com.mbridge.msdk.out.PreloadListener
    public final void onPreloadFaild(String str) {
        if (this.a == null || this.a.get() == null) {
            return;
        }
        this.a.get().onPreloadFaild(str);
    }
}
