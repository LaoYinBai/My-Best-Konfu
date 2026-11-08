package com.tachikoma.core.component.listview;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.tachikoma.core.component.listview.TKPagerIndicatorDecoration;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class TKLineIndicator implements TKPagerIndicatorDecoration.IIndicatorRender {
    private static final float DP = Resources.getSystem().getDisplayMetrics().density;

    TKLineIndicator() {
    }

    @Override // com.tachikoma.core.component.listview.TKPagerIndicatorDecoration.IIndicatorRender
    public void drawHighlights(float f, float f2, float f3, float f4, int i, int i2, Paint paint, Canvas canvas) {
        paint.setColor(-1);
        canvas.drawLine(f, f2, f3, f4, paint);
    }

    @Override // com.tachikoma.core.component.listview.TKPagerIndicatorDecoration.IIndicatorRender
    public void drawInactiveIndicators(float f, float f2, float f3, float f4, Paint paint, Canvas canvas) {
        paint.setColor(1728053247);
        canvas.drawLine(f, f2, f3, f4, paint);
    }

    @Override // com.tachikoma.core.component.listview.TKPagerIndicatorDecoration.IIndicatorRender
    public float getIndicatorHeight() {
        return DP * 16.0f;
    }

    @Override // com.tachikoma.core.component.listview.TKPagerIndicatorDecoration.IIndicatorRender
    public float getIndicatorPadding() {
        return DP * 4.0f;
    }

    public float getIndicatorStrokeWidth() {
        return DP * 2.0f;
    }

    @Override // com.tachikoma.core.component.listview.TKPagerIndicatorDecoration.IIndicatorRender
    public int getIndicatorTopMargin() {
        return (int) (getIndicatorHeight() * (-1.0f) * 2.0f);
    }

    @Override // com.tachikoma.core.component.listview.TKPagerIndicatorDecoration.IIndicatorRender
    public float getIndicatorWidth() {
        return DP * 16.0f;
    }

    @Override // com.tachikoma.core.component.listview.TKPagerIndicatorDecoration.IIndicatorRender
    public Paint getPaint() {
        Paint paint = new Paint();
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(getIndicatorStrokeWidth());
        paint.setStyle(Paint.Style.STROKE);
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
