package com.ss.union.game.sdk.common.ui.roundedimageview;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.ImageView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class RoundedTransformationBuilder {
    private float[] b = {0.0f, 0.0f, 0.0f, 0.0f};
    private boolean c = false;
    private float d = 0.0f;
    private ColorStateList e = ColorStateList.valueOf(RoundedDrawable.DEFAULT_BORDER_COLOR);
    private ImageView.ScaleType f = ImageView.ScaleType.FIT_CENTER;
    private final DisplayMetrics a = Resources.getSystem().getDisplayMetrics();

    public RoundedTransformationBuilder scaleType(ImageView.ScaleType scaleType) {
        this.f = scaleType;
        return this;
    }

    public RoundedTransformationBuilder cornerRadius(float f) {
        this.b[0] = f;
        this.b[1] = f;
        this.b[2] = f;
        this.b[3] = f;
        return this;
    }

    public RoundedTransformationBuilder cornerRadius(int i, float f) {
        this.b[i] = f;
        return this;
    }

    public RoundedTransformationBuilder cornerRadiusDp(float f) {
        return cornerRadius(TypedValue.applyDimension(1, f, this.a));
    }

    public RoundedTransformationBuilder cornerRadiusDp(int i, float f) {
        return cornerRadius(i, TypedValue.applyDimension(1, f, this.a));
    }

    public RoundedTransformationBuilder borderWidth(float f) {
        this.d = f;
        return this;
    }

    public RoundedTransformationBuilder borderWidthDp(float f) {
        this.d = TypedValue.applyDimension(1, f, this.a);
        return this;
    }

    public RoundedTransformationBuilder borderColor(int i) {
        this.e = ColorStateList.valueOf(i);
        return this;
    }

    public RoundedTransformationBuilder borderColor(ColorStateList colorStateList) {
        this.e = colorStateList;
        return this;
    }

    public RoundedTransformationBuilder oval(boolean z) {
        this.c = z;
        return this;
    }
}
