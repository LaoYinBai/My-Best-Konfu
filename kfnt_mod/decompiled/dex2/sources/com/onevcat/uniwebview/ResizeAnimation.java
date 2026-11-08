package com.onevcat.uniwebview;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ResizeAnimation extends Animation {
    private int deltaHeight;
    private int deltaWidth;
    private boolean fillEnabled = false;
    private int originalHeight;
    private int originalWidth;
    private int startHeight;
    private int startWidth;
    private View view;

    public ResizeAnimation(View view, int i, int i2, int i3, int i4) {
        this.view = view;
        this.startWidth = i;
        this.deltaWidth = i2 - i;
        this.startHeight = i3;
        this.deltaHeight = i4 - i3;
        this.originalHeight = view.getHeight();
        this.originalWidth = view.getWidth();
    }

    @Override // android.view.animation.Animation
    public void setFillEnabled(boolean z) {
        this.fillEnabled = z;
        super.setFillEnabled(z);
    }

    @Override // android.view.animation.Animation
    protected void applyTransformation(float f, Transformation transformation) {
        if (f == 1.0d && !this.fillEnabled) {
            this.view.getLayoutParams().height = this.originalHeight;
            this.view.getLayoutParams().width = this.originalWidth;
        } else {
            if (this.deltaHeight != 0) {
                this.view.getLayoutParams().height = (int) (this.startHeight + (this.deltaHeight * f));
            }
            if (this.deltaWidth != 0) {
                this.view.getLayoutParams().width = (int) (this.startWidth + (this.deltaWidth * f));
            }
        }
        this.view.requestLayout();
    }
}
