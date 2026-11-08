package com.tachikoma.core.component.listview;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.tachikoma.core.component.listview.TKPagerIndicatorDecoration;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKCircleIndicator implements TKPagerIndicatorDecoration.IIndicatorRender {
    private static final float DP = Resources.getSystem().getDisplayMetrics().density;
    private final int mActiveColor;
    private final int mInActiveColor;
    private final float mPadding;
    private final int mRadius;
    private final float mTopMargin;

    TKCircleIndicator(int i, float f, float f2, int i2, int i3) {
        this.mRadius = (int) (i * DP);
        this.mTopMargin = f * DP;
        this.mPadding = f2 * DP;
        this.mInActiveColor = i2;
        this.mActiveColor = i3;
    }

    @Override // com.tachikoma.core.component.listview.TKPagerIndicatorDecoration.IIndicatorRender
    public void drawHighlights(float f, float f2, float f3, float f4, int i, int i2, Paint paint, Canvas canvas) {
        paint.setColor(this.mActiveColor);
        canvas.drawCircle(f - (getIndicatorWidth() / 2.0f), f2 - (getIndicatorHeight() / 2.0f), this.mRadius, paint);
    }

    @Override // com.tachikoma.core.component.listview.TKPagerIndicatorDecoration.IIndicatorRender
    public void drawInactiveIndicators(float f, float f2, float f3, float f4, Paint paint, Canvas canvas) {
        paint.setColor(this.mInActiveColor);
        canvas.drawCircle(f - (getIndicatorWidth() / 2.0f), f2 - (getIndicatorHeight() / 2.0f), this.mRadius, paint);
    }

    @Override // com.tachikoma.core.component.listview.TKPagerIndicatorDecoration.IIndicatorRender
    public float getIndicatorHeight() {
        return this.mRadius * 2;
    }

    @Override // com.tachikoma.core.component.listview.TKPagerIndicatorDecoration.IIndicatorRender
    public float getIndicatorPadding() {
        return 0.0f;
    }

    @Override // com.tachikoma.core.component.listview.TKPagerIndicatorDecoration.IIndicatorRender
    public int getIndicatorTopMargin() {
        return (int) this.mTopMargin;
    }

    @Override // com.tachikoma.core.component.listview.TKPagerIndicatorDecoration.IIndicatorRender
    public float getIndicatorWidth() {
        return (this.mRadius * 2) + this.mPadding;
    }

    @Override // com.tachikoma.core.component.listview.TKPagerIndicatorDecoration.IIndicatorRender
    public Paint getPaint() {
        Paint paint = new Paint();
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        return paint;
    }

    @Override // com.tachikoma.core.component.listview.TKPagerIndicatorDecoration.IIndicatorRender
    public void setItemWidth(int i) {
    }

    @Override // com.tachikoma.core.component.listview.TKPagerIndicatorDecoration.IIndicatorRender
    public void setTextSize(int i) {
    }
}
