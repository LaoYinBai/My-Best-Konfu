package com.mbridge.msdk.foundation.webview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import com.mbridge.msdk.foundation.webview.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ProgressBar extends View implements a {
    Runnable a;
    private Rect b;
    private float c;
    private float d;
    private long e;
    private float f;
    private boolean g;
    private float h;
    private float i;
    private float j;
    private long k;
    private int l;
    private int m;
    private int n;
    private int o;
    private long p;
    private Drawable q;
    private Drawable r;
    private Drawable s;
    private Drawable t;
    private boolean u;
    private a.InterfaceC0114a v;
    private Handler w;
    private boolean x;
    private boolean y;

    @Override // android.view.View
    public Bitmap getDrawingCache(boolean z) {
        return null;
    }

    public ProgressBar(Context context) {
        super(context);
        this.b = new Rect();
        this.d = 0.95f;
        this.p = 25L;
        this.u = false;
        this.w = new Handler(Looper.getMainLooper());
        this.a = new Runnable() { // from class: com.mbridge.msdk.foundation.webview.ProgressBar.1
            @Override // java.lang.Runnable
            public final void run() {
                ProgressBar.this.invalidate();
            }
        };
        this.y = false;
        setWillNotDraw(false);
    }

    public ProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = new Rect();
        this.d = 0.95f;
        this.p = 25L;
        this.u = false;
        this.w = new Handler(Looper.getMainLooper());
        this.a = new Runnable() { // from class: com.mbridge.msdk.foundation.webview.ProgressBar.1
            @Override // java.lang.Runnable
            public final void run() {
                ProgressBar.this.invalidate();
            }
        };
        this.y = false;
        setWillNotDraw(false);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0061  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void draw(android.graphics.Canvas r11) {
        /*
            Method dump skipped, instructions count: 436
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.webview.ProgressBar.draw(android.graphics.Canvas):void");
    }

    public void onThemeChange() {
        if (this.u) {
            initResource(true);
        }
    }

    public void initResource(boolean z) {
        if (z || (this.t == null && this.q == null && this.r == null && this.s == null)) {
            this.t = getResources().getDrawable(getResources().getIdentifier("mbridge_cm_highlight", "drawable", com.mbridge.msdk.foundation.controller.a.f().d()));
            if (this.t != null) {
                this.t.setBounds(0, 0, this.t.getIntrinsicWidth(), this.t.getIntrinsicHeight());
            }
            this.q = getResources().getDrawable(getResources().getIdentifier("mbridge_cm_head", "drawable", com.mbridge.msdk.foundation.controller.a.f().d()));
            if (this.q != null) {
                this.q.setBounds(0, 0, this.q.getIntrinsicWidth(), this.q.getIntrinsicHeight());
            }
            this.r = getResources().getDrawable(getResources().getIdentifier("mbridge_cm_tail", "drawable", com.mbridge.msdk.foundation.controller.a.f().d()));
            this.s = getResources().getDrawable(getResources().getIdentifier("mbridge_cm_end_animation", "drawable", com.mbridge.msdk.foundation.controller.a.f().d()));
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.t != null) {
            this.t.setBounds(0, 0, (int) (this.t.getIntrinsicWidth() * 1.5d), getHeight());
        }
        if (this.q != null) {
            this.q.setBounds(0, 0, getWidth(), getHeight());
        }
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    public void startEndAnimation() {
        if (this.g) {
            return;
        }
        this.g = true;
        this.h = 0.0f;
    }

    public void setProgress(float f, boolean z) {
        if (!z || f < 1.0f) {
            return;
        }
        startEndAnimation();
    }

    public void setVisible(boolean z) {
        if (z) {
            this.x = true;
            this.e = System.currentTimeMillis();
            this.f = 0.0f;
            this.k = 0L;
            this.g = false;
            this.h = 0.0f;
            this.i = 0.0f;
            this.c = getMeasuredWidth();
            this.y = false;
            this.m = 0;
            this.n = 0;
            this.o = 0;
            if (this.t != null) {
                this.l = -this.t.getIntrinsicWidth();
            } else {
                this.l = 0;
            }
            if (this.r != null) {
                this.r.setAlpha(255);
            }
            if (this.s != null) {
                this.s.setAlpha(255);
            }
            if (this.q != null) {
                this.q.setAlpha(255);
            }
            setVisibility(0);
            invalidate();
            return;
        }
        setVisibility(4);
    }

    public float getProgress() {
        return this.i;
    }

    public void setProgressBarListener(a.InterfaceC0114a interfaceC0114a) {
        this.v = interfaceC0114a;
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        if (i == 0) {
            if (this.v != null) {
                this.v.a(true);
            }
        } else if (this.v != null) {
            this.v.a(false);
        }
    }

    public void setProgressState(int i) {
        switch (i) {
            case 5:
                this.m = 1;
                this.n = 0;
                this.o = 0;
                this.k = 0L;
                break;
            case 6:
                this.n = 1;
                if (this.o == 1) {
                    startEndAnimation();
                }
                this.k = 0L;
                break;
            case 7:
                startEndAnimation();
                break;
            case 8:
                this.o = 1;
                if (this.n == 1) {
                    startEndAnimation();
                    break;
                }
                break;
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.c = getMeasuredWidth();
    }

    public void setPaused(boolean z) {
        this.y = z;
        if (z) {
            return;
        }
        this.e = System.currentTimeMillis();
    }
}
