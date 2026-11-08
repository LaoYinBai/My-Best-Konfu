package com.sigmob.sdk.blurkit;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import java.lang.ref.WeakReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class BlurLayout extends FrameLayout {
    private float a;
    private int b;
    private int c;
    private float d;
    private float e;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    private RoundedImageView j;
    private WeakReference<View> k;
    private Point l;
    private Bitmap m;
    private Choreographer.FrameCallback n;

    public BlurLayout(Context context) {
        super(context, null);
        this.a = 0.12f;
        this.b = 12;
        this.c = 24;
        this.d = 0.0f;
        this.e = Float.NaN;
        this.n = new Choreographer.FrameCallback() { // from class: com.sigmob.sdk.blurkit.BlurLayout.1
            @Override // android.view.Choreographer.FrameCallback
            public void doFrame(long j) {
                BlurLayout.this.invalidate();
                Choreographer.getInstance().postFrameCallbackDelayed(this, 1000 / BlurLayout.this.c);
            }
        };
    }

    public BlurLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = 0.12f;
        this.b = 12;
        this.c = 24;
        this.d = 0.0f;
        this.e = Float.NaN;
        this.n = new Choreographer.FrameCallback() { // from class: com.sigmob.sdk.blurkit.BlurLayout.1
            @Override // android.view.Choreographer.FrameCallback
            public void doFrame(long j) {
                BlurLayout.this.invalidate();
                Choreographer.getInstance().postFrameCallbackDelayed(this, 1000 / BlurLayout.this.c);
            }
        };
        if (!isInEditMode()) {
            a.a(context);
        }
        this.j = new RoundedImageView(getContext());
        this.j.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(this.j);
        setCornerRadius(this.d);
    }

    private Bitmap a(View view, Rect rect, float f) throws b {
        View rootView = view.getRootView();
        int iWidth = (int) (rect.width() * f);
        int iHeight = (int) (rect.height() * f);
        if (rootView.getWidth() <= 0 || rootView.getHeight() <= 0 || iWidth <= 0 || iHeight <= 0) {
            throw new b("No screen available (width or height = 0)");
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iWidth, iHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Matrix matrix = new Matrix();
        matrix.preScale(f, f);
        matrix.postTranslate((-rect.left) * f, (-rect.top) * f);
        canvas.setMatrix(matrix);
        rootView.draw(canvas);
        return bitmapCreateBitmap;
    }

    private PointF a(View view) {
        if (getParent() == null) {
            return new PointF();
        }
        try {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup == null) {
                return new PointF();
            }
            PointF pointFA = a(viewGroup);
            pointFA.offset(view.getX(), view.getY());
            return pointFA;
        } catch (Exception unused) {
            return new PointF();
        }
    }

    private Bitmap d() {
        Point positionInScreen;
        Bitmap bitmapA;
        if (getContext() != null && !isInEditMode()) {
            if (this.k == null || this.k.get() == null) {
                this.k = new WeakReference<>(getActivityView());
                if (this.k.get() == null) {
                    return null;
                }
            }
            if (this.h) {
                if (this.l == null) {
                    this.l = getPositionInScreen();
                }
                positionInScreen = this.l;
            } else {
                positionInScreen = getPositionInScreen();
            }
            super.setAlpha(0.0f);
            int width = this.k.get().getWidth();
            int height = this.k.get().getHeight();
            int width2 = (int) (getWidth() * this.a);
            int height2 = (int) (getHeight() * this.a);
            int i = (int) (positionInScreen.x * this.a);
            int i2 = (int) (positionInScreen.y * this.a);
            int width3 = getWidth() / 8;
            int height3 = getHeight() / 8;
            int i3 = -width3;
            if (i + i3 < 0) {
                i3 = 0;
            }
            if ((i + width) - width3 > width) {
                width3 = (width + width) - i;
            }
            int i4 = -height3;
            if (i2 + i4 < 0) {
                i4 = 0;
            }
            if (getHeight() + i2 + height3 > height) {
                height3 = 0;
            }
            if (this.i) {
                if (this.m == null) {
                    c();
                }
                if (width2 == 0 || height2 == 0) {
                    return null;
                }
                bitmapA = Bitmap.createBitmap(this.m, i, i2, width2, height2);
            } else {
                try {
                    bitmapA = a(this.k.get(), new Rect(positionInScreen.x + i3, positionInScreen.y + i4, positionInScreen.x + getWidth() + Math.abs(i3) + width3, positionInScreen.y + getHeight() + Math.abs(i4) + height3), this.a);
                } catch (b | NullPointerException unused) {
                }
            }
            if (!this.i) {
                bitmapA = Bitmap.createBitmap(a.a().a(bitmapA, this.b), (int) (Math.abs(i3) * this.a), (int) (Math.abs(i4) * this.a), width2, height2);
            }
            super.setAlpha(Float.isNaN(this.e) ? 1.0f : this.e);
            return bitmapA;
        }
        return null;
    }

    private View getActivityView() {
        try {
            return ((Activity) getContext()).getWindow().getDecorView().findViewById(R.id.content);
        } catch (ClassCastException unused) {
            return null;
        }
    }

    private Point getPositionInScreen() {
        PointF pointFA = a((View) this);
        return new Point((int) pointFA.x, (int) pointFA.y);
    }

    public void a() {
        if (!this.f && this.c > 0) {
            this.f = true;
            Choreographer.getInstance().postFrameCallback(this.n);
        }
    }

    public void b() {
        if (this.f) {
            this.f = false;
            Choreographer.getInstance().removeFrameCallback(this.n);
        }
    }

    public void c() {
        this.i = true;
        if (this.k == null || this.k.get() == null) {
            return;
        }
        View rootView = this.k.get().getRootView();
        try {
            super.setAlpha(0.0f);
            this.m = a(rootView, new Rect(0, 0, rootView.getWidth(), rootView.getHeight()), this.a);
            if (Float.isNaN(this.e)) {
                super.setAlpha(1.0f);
            } else {
                super.setAlpha(this.e);
            }
            this.m = a.a().a(this.m, this.b);
        } catch (Exception unused) {
        }
    }

    @Override // android.view.View
    public float getAlpha() {
        return this.e;
    }

    public int getBlurRadius() {
        return this.b;
    }

    public float getCornerRadius() {
        return this.d;
    }

    public float getDownscaleFactor() {
        return this.a;
    }

    public int getFPS() {
        return this.c;
    }

    public boolean getPositionLocked() {
        return this.h;
    }

    public boolean getViewLocked() {
        return this.i;
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        Bitmap bitmapD = d();
        if (bitmapD != null) {
            this.j.setImageBitmap(bitmapD);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.g = true;
        a();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.g = false;
        b();
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        invalidate();
    }

    @Override // android.view.View
    public void setAlpha(float f) {
        this.e = f;
        if (this.i) {
            return;
        }
        super.setAlpha(this.e);
    }

    public void setBlurRadius(int i) {
        this.b = i;
        this.m = null;
        invalidate();
    }

    public void setCornerRadius(float f) {
        this.d = f;
        if (this.j != null) {
            this.j.setCornerRadius(f);
        }
        invalidate();
    }

    public void setDownscaleFactor(float f) {
        this.a = f;
        this.m = null;
        invalidate();
    }

    public void setFPS(int i) {
        if (this.f) {
            b();
        }
        this.c = i;
        if (this.g) {
            a();
        }
    }
}
