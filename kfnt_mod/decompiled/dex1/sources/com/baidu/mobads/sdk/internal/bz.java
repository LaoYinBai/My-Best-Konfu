package com.baidu.mobads.sdk.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.KeyEvent;
import android.widget.RelativeLayout;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class bz extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    public static final String f3197a = "ContainerView";

    /* renamed from: b, reason: collision with root package name */
    private a f3198b;

    public interface a {
        void onAttachedToWindow();

        @SuppressLint({"MissingSuperCall"})
        void onDetachedFromWindow();

        boolean onKeyDown(int i, KeyEvent keyEvent);

        void onLayoutComplete(int i, int i2);

        void onWindowFocusChanged(boolean z);

        void onWindowVisibilityChanged(int i);
    }

    public bz(Context context) {
        super(context);
    }

    public void a(a aVar) {
        this.f3198b = aVar;
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (!z || this.f3198b == null) {
            return;
        }
        this.f3198b.onLayoutComplete(getWidth(), getHeight());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f3198b != null) {
            this.f3198b.onAttachedToWindow();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f3198b != null) {
            this.f3198b.onDetachedFromWindow();
        }
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (this.f3198b != null) {
            this.f3198b.onWindowVisibilityChanged(i);
        }
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.f3198b != null) {
            this.f3198b.onWindowFocusChanged(z);
        }
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.f3198b != null) {
            return this.f3198b.onKeyDown(i, keyEvent);
        }
        super.onKeyDown(i, keyEvent);
        return false;
    }
}
