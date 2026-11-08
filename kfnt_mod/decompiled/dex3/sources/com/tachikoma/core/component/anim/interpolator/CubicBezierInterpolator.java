package com.tachikoma.core.component.anim.interpolator;

import android.graphics.PointF;
import android.view.animation.Interpolator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CubicBezierInterpolator implements Interpolator {
    protected PointF a;
    protected PointF b;
    protected PointF c;
    protected PointF end;
    protected PointF start;

    public CubicBezierInterpolator(double d, double d2, double d3, double d4) {
        this((float) d, (float) d2, (float) d3, (float) d4);
    }

    public CubicBezierInterpolator(float f, float f2, float f3, float f4) {
        this(new PointF(f, f2), new PointF(f3, f4));
    }

    public CubicBezierInterpolator(PointF pointF, PointF pointF2) {
        this.a = new PointF();
        this.b = new PointF();
        this.c = new PointF();
        if (pointF.x < 0.0f || pointF.x > 1.0f) {
            throw new IllegalArgumentException("startX value must be in the range [0, 1]");
        }
        if (pointF2.x < 0.0f || pointF2.x > 1.0f) {
            throw new IllegalArgumentException("endX value must be in the range [0, 1]");
        }
        this.start = pointF;
        this.end = pointF2;
    }

    public static CubicBezierInterpolator buildDefaultEaseInInterpolator() {
        return new CubicBezierInterpolator(0.42f, 0.0f, 1.0f, 1.0f);
    }

    public static CubicBezierInterpolator buildDefaultEaseInOutInterpolator() {
        return new CubicBezierInterpolator(0.42f, 0.0f, 0.58f, 1.0f);
    }

    public static CubicBezierInterpolator buildDefaultEaseInterpolator() {
        return new CubicBezierInterpolator(0.25f, 0.1f, 0.25f, 1.0f);
    }

    public static CubicBezierInterpolator buildDefaultEaseOutInterpolator() {
        return new CubicBezierInterpolator(0.0f, 0.0f, 0.58f, 1.0f);
    }

    public static CubicBezierInterpolator buildDefaultLinearInterpolator() {
        return new CubicBezierInterpolator(0.0f, 0.0f, 1.0f, 1.0f);
    }

    private float getBezierCoordinateX(float f) {
        this.c.x = this.start.x * 3.0f;
        this.b.x = ((this.end.x - this.start.x) * 3.0f) - this.c.x;
        this.a.x = (1.0f - this.c.x) - this.b.x;
        return f * (this.c.x + ((this.b.x + (this.a.x * f)) * f));
    }

    private float getXDerivate(float f) {
        return this.c.x + (f * ((this.b.x * 2.0f) + (this.a.x * 3.0f * f)));
    }

    protected float getBezierCoordinateY(float f) {
        this.c.y = this.start.y * 3.0f;
        this.b.y = ((this.end.y - this.start.y) * 3.0f) - this.c.y;
        this.a.y = (1.0f - this.c.y) - this.b.y;
        return f * (this.c.y + ((this.b.y + (this.a.y * f)) * f));
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        return getBezierCoordinateY(getXForTime(f));
    }

    protected float getXForTime(float f) {
        float xDerivate = f;
        for (int i = 1; i < 14; i++) {
            float bezierCoordinateX = getBezierCoordinateX(xDerivate) - f;
            if (Math.abs(bezierCoordinateX) < 0.001d) {
                break;
            }
            xDerivate -= bezierCoordinateX / getXDerivate(xDerivate);
        }
        return xDerivate;
    }
}
