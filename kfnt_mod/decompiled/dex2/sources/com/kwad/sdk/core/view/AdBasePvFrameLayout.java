package com.kwad.sdk.core.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.core.d.a;
import com.kwad.sdk.utils.av;
import com.kwad.sdk.utils.ba;
import com.kwad.sdk.widget.k;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class AdBasePvFrameLayout extends AdBaseFrameLayout {
    private long a;
    private float b;
    private boolean c;
    private boolean d;
    private int e;
    private ViewTreeObserver.OnScrollChangedListener f;
    private ViewTreeObserver g;
    private ba h;
    private k i;

    public AdBasePvFrameLayout(@NonNull Context context) {
        super(context);
        this.a = 500L;
        this.b = 0.1f;
        this.d = true;
        a();
    }

    public AdBasePvFrameLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = 500L;
        this.b = 0.1f;
        this.d = true;
        a();
    }

    public AdBasePvFrameLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = 500L;
        this.b = 0.1f;
        this.d = true;
        a();
    }

    private void a() {
        this.h = new ba(this);
        this.e = av.o(getContext());
        this.d = l();
    }

    private void b() {
        if (c()) {
            n();
        } else {
            d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c() {
        return this.h.a() && ((float) Math.abs(this.h.a.height() - getHeight())) <= ((float) getHeight()) * (1.0f - this.b) && getHeight() > 0 && getWidth() > 0 && this.h.a.bottom > 0 && this.h.a.top < this.e;
    }

    private void d() {
        if (this.f == null) {
            this.f = new ViewTreeObserver.OnScrollChangedListener() { // from class: com.kwad.sdk.core.view.AdBasePvFrameLayout.1
                @Override // android.view.ViewTreeObserver.OnScrollChangedListener
                public void onScrollChanged() {
                    if (AdBasePvFrameLayout.this.c()) {
                        AdBasePvFrameLayout.this.n();
                    }
                }
            };
            this.g = getViewTreeObserver();
            if (this.g != null) {
                this.g.addOnScrollChangedListener(this.f);
            }
        }
    }

    protected boolean l() {
        return true;
    }

    protected void m() {
        if (this.d) {
            b();
        }
    }

    protected void n() {
        o();
        if (this.i != null) {
            this.i.a();
        }
    }

    protected void o() {
        try {
            if (this.f != null && this.g != null && this.g.isAlive()) {
                this.g.removeOnScrollChangedListener(this.f);
            }
            this.f = null;
        } catch (Exception e) {
            a.a(e);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        d();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        o();
        this.c = false;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        boolean z = true;
        if (this.c || (i3 | i4) != 0 || (i | i2) == 0) {
            z = false;
        } else {
            this.c = true;
        }
        super.onSizeChanged(i, i2, i3, i4);
        if (z) {
            m();
        }
    }

    public void setCheckDefaultImpressionLogThreshold(float f) {
        this.b = f;
    }

    public void setVisibleListener(k kVar) {
        this.i = kVar;
    }
}
