package com.kwad.sdk.collector;

import android.content.Context;
import androidx.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class e<T> {
    protected boolean a;
    protected boolean b = false;

    public e(boolean z) {
        this.a = z;
    }

    @Nullable
    public final T a(Context context) {
        if (!this.a || this.b) {
            return null;
        }
        try {
            return b(context);
        } catch (Throwable th) {
            com.kwad.sdk.core.d.a.b(th);
            return null;
        }
    }

    public void a(boolean z) {
        this.a = z;
    }

    @Nullable
    protected abstract T b(Context context);
}
