package com.kwad.sdk.contentalliance;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import com.kwad.sdk.core.e.b;
import com.kwad.sdk.mvp.Presenter;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class b<T extends com.kwad.sdk.core.e.b> extends FrameLayout {
    protected Presenter a;
    protected T b;
    protected ViewGroup c;

    public b(Context context) {
        super(context);
        this.c = (ViewGroup) inflate(getContext(), getLayoutId(), this);
    }

    protected void a() {
        if (this.a != null) {
            this.a.k();
        }
        this.c = null;
    }

    protected abstract T b();

    @NonNull
    public abstract Presenter c();

    @LayoutRes
    protected abstract int getLayoutId();

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.b = (T) b();
        if (this.a == null) {
            this.a = c();
            this.a.c(this.c);
        }
        this.a.a(this.b);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.b != null) {
            this.b.a();
        }
        a();
    }
}
