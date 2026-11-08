package com.ss.android.downloadlib.guide.install;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ClipImageView extends ImageView {
    private boolean mClip;
    private Paint mPaint;
    private Path mPath;
    private float[] mRadius;
    private RectF mRectF;

    public ClipImageView(Context context) {
        super(context);
        this.mClip = true;
        init(context);
    }

    public ClipImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mClip = true;
        init(context);
    }

    public ClipImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mClip = true;
        init(context);
    }

    protected void init(Context context) {
        this.mPath = new Path();
        this.mRectF = new RectF();
    }

    public void setRadius(float[] fArr) {
        if (fArr == null || fArr.length != 8) {
            return;
        }
        this.mRadius = fArr;
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.mPaint = new Paint(1);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(i);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mClip) {
            this.mPath.reset();
            this.mRectF.set(0.0f, 0.0f, getWidth(), getHeight());
            if (this.mRadius != null) {
                this.mPath.addRoundRect(this.mRectF, this.mRadius, Path.Direction.CW);
            }
            canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
            canvas.clipPath(this.mPath);
            if (this.mPaint != null) {
                canvas.drawPath(this.mPath, this.mPaint);
            }
        }
        super.onDraw(canvas);
    }

    public void setRoundRadius(int i) {
        if (i > 0) {
            float f = i;
            setRadius(new float[]{f, f, f, f, f, f, f, f});
        }
    }

    public void setClip(boolean z) {
        this.mClip = z;
    }

    public boolean isClip() {
        return this.mClip;
    }
}
