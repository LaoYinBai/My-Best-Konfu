package com.tachikoma.core.component.listview;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.tachikoma.core.component.listview.TKPagerIndicatorDecoration;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class TKNumberIndicator implements TKPagerIndicatorDecoration.IIndicatorRender {
    private static final float DP = Resources.getSystem().getDisplayMetrics().density;
    private final int mActiveColor;
    private int mTextSize = (int) getIndicatorHeight();
    private int mItemWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

    TKNumberIndicator(int i) {
        this.mActiveColor = i;
    }

    @Override // com.tachikoma.core.component.listview.TKPagerIndicatorDecoration.IIndicatorRender
    public void drawHighlights(float f, float f2, float f3, float f4, int i, int i2, Paint paint, Canvas canvas) {
        String str = (i2 + 1) + "/" + i;
        canvas.drawText(str, 0, str.length(), (this.mItemWidth - getIndicatorWidth()) / 2.0f, f2, paint);
    }

    @Override // com.tachikoma.core.component.listview.TKPagerIndicatorDecoration.IIndicatorRender
    public void drawInactiveIndicators(float f, float f2, float f3, float f4, Paint paint, Canvas canvas) {
    }

    @Override // com.tachikoma.core.component.listview.TKPagerIndicatorDecoration.IIndicatorRender
    public float getIndicatorHeight() {
        return DP * 16.0f;
    }

    @Override // com.tachikoma.core.component.listview.TKPagerIndicatorDecoration.IIndicatorRender
    public float getIndicatorPadding() {
        return DP * 4.0f;
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
        paint.setTextSize(this.mTextSize);
        paint.setColor(this.mActiveColor);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        return paint;
    }

    @Override // com.tachikoma.core.component.listview.TKPagerIndicatorDecoration.IIndicatorRender
    public void setItemWidth(int i) {
        this.mItemWidth = i;
    }

    @Override // com.tachikoma.core.component.listview.TKPagerIndicatorDecoration.IIndicatorRender
    public void setTextSize(int i) {
        this.mTextSize = (int) (i * DP);
    }
}
