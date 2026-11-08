package com.kwad.sdk.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.utils.z;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class KSFrameLayout extends FrameLayout implements j {
    private final AtomicBoolean a;
    private h b;
    private j c;
    private i d;
    private float e;
    private final z.a f;
    private boolean g;

    public KSFrameLayout(@NonNull Context context) {
        super(context);
        this.a = new AtomicBoolean(true);
        this.e = 0.0f;
        this.f = new z.a();
        this.g = true;
        a(context, null);
    }

    public KSFrameLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new AtomicBoolean(true);
        this.e = 0.0f;
        this.f = new z.a();
        this.g = true;
        a(context, attributeSet);
    }

    public KSFrameLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new AtomicBoolean(true);
        this.e = 0.0f;
        this.f = new z.a();
        this.g = true;
        a(context, attributeSet);
    }

    private void a(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        if (attributeSet != null) {
            int i = R.attr.ksad_ratio;
            int[] iArr = {i};
            Arrays.sort(iArr);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr);
            this.e = typedArrayObtainStyledAttributes.getFloat(Arrays.binarySearch(iArr, i), 0.0f);
            typedArrayObtainStyledAttributes.recycle();
        }
        this.b = new h(this, this);
        this.b.a(n());
        this.d = new i();
        this.d.a(context, attributeSet);
    }

    private float[] b(float f, float f2, float f3, float f4) {
        return new float[]{f, f, f2, f2, f3, f3, f4, f4};
    }

    private void c() {
        if (this.a.getAndSet(false)) {
            com.kwad.sdk.core.d.a.c("KSFrameLayout", "onViewAttached");
            a();
        }
    }

    private void d() {
        if (this.a.getAndSet(true)) {
            return;
        }
        com.kwad.sdk.core.d.a.c("KSFrameLayout", "onViewDetached");
        b();
    }

    @CallSuper
    protected void a() {
        this.b.b();
    }

    public void a(float f, float f2, float f3, float f4) {
        this.d.a(b(f, f2, f3, f4));
        postInvalidate();
    }

    @CallSuper
    public void a(View view) {
        if (this.c != null) {
            this.c.a(view);
        }
    }

    @CallSuper
    protected void b() {
        this.b.c();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        this.d.c(canvas);
        super.dispatchDraw(canvas);
        this.d.d(canvas);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.f.a(getWidth(), getHeight());
                this.f.a(motionEvent.getX(), motionEvent.getY());
                break;
            case 1:
                this.f.b(motionEvent.getX(), motionEvent.getY());
                break;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        this.d.a(canvas);
        super.draw(canvas);
        this.d.b(canvas);
    }

    @MainThread
    public z.a getTouchCoords() {
        return this.f;
    }

    public float getVisiblePercent() {
        return this.b.a();
    }

    protected boolean n() {
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    @Deprecated
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c();
    }

    @Override // android.view.ViewGroup, android.view.View
    @Deprecated
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        d();
    }

    @Override // android.view.View
    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        c();
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.e != 0.0f) {
            if (this.g) {
                i2 = View.MeasureSpec.makeMeasureSpec((int) (View.MeasureSpec.getSize(i) * this.e), 1073741824);
            } else {
                i = View.MeasureSpec.makeMeasureSpec((int) (View.MeasureSpec.getSize(i2) / this.e), 1073741824);
            }
        }
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        this.b.a(i, i2, i3, i4);
        super.onSizeChanged(i, i2, i3, i4);
        this.b.b(i, i2, i3, i4);
        this.d.a(i, i2);
    }

    @Override // android.view.View
    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        d();
    }

    public void setRadius(float f) {
        this.d.a(f);
        postInvalidate();
    }

    public void setRatio(float f) {
        this.e = f;
    }

    public void setViewVisibleListener(j jVar) {
        this.c = jVar;
    }

    public void setVisiblePercent(float f) {
        this.b.a(f);
    }

    public void setWidthBasedRatio(boolean z) {
        this.g = z;
    }
}
