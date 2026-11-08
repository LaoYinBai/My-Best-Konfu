package com.tachikoma.core.component.text;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.style.ImageSpan;
import androidx.annotation.NonNull;
import java.lang.ref.WeakReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CenterImageSpan extends ImageSpan {
    private WeakReference<Drawable> mDrawableRef;
    private boolean mFitTextLine;
    private Paint.FontMetricsInt mFm;
    private Rect mImageRect;
    private float mMarginLeft;
    private float mMarginRight;
    private float mOffsetY;
    private Object mTag;

    public CenterImageSpan(Drawable drawable, String str) {
        super(drawable, str, 1);
        this.mFitTextLine = true;
        this.mFm = new Paint.FontMetricsInt();
    }

    private Drawable getCachedDrawable(Paint paint) {
        WeakReference<Drawable> weakReference = this.mDrawableRef;
        Drawable drawable = weakReference != null ? weakReference.get() : null;
        if (drawable == null) {
            drawable = getDrawable();
            if (this.mFitTextLine) {
                int fontMetricsInt = paint.getFontMetricsInt(null);
                drawable.setBounds(0, 0, fontMetricsInt, fontMetricsInt);
            } else if (this.mImageRect != null) {
                drawable.setBounds(this.mImageRect);
            }
            this.mDrawableRef = new WeakReference<>(drawable);
        }
        return drawable;
    }

    @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
    public void draw(@NonNull Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, @NonNull Paint paint) {
        Drawable cachedDrawable = getCachedDrawable(paint);
        if (cachedDrawable == null) {
            return;
        }
        if (paint instanceof TextPaint) {
            cachedDrawable.setState(((TextPaint) paint).drawableState);
        }
        int fontMetricsInt = paint.getFontMetricsInt(this.mFm);
        int i6 = i4 + this.mFm.ascent;
        int i7 = (fontMetricsInt - (cachedDrawable.getBounds().bottom - cachedDrawable.getBounds().top)) / 2;
        canvas.save();
        canvas.translate(f + this.mMarginLeft, i6 + i7 + this.mOffsetY);
        cachedDrawable.draw(canvas);
        canvas.restore();
    }

    @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
    public int getSize(@NonNull Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        Drawable cachedDrawable = getCachedDrawable(paint);
        if (cachedDrawable == null) {
            return 0;
        }
        Rect bounds = cachedDrawable.getBounds();
        if (fontMetricsInt != null) {
            paint.getFontMetricsInt(fontMetricsInt);
            fontMetricsInt.ascent = fontMetricsInt.ascent;
            fontMetricsInt.descent = fontMetricsInt.descent;
            fontMetricsInt.top = fontMetricsInt.top;
            fontMetricsInt.bottom = fontMetricsInt.bottom;
        }
        return (int) (bounds.right + this.mMarginRight + this.mMarginLeft);
    }

    public Object getTag() {
        return this.mTag;
    }

    public CenterImageSpan setFitTextLine(boolean z) {
        this.mFitTextLine = z;
        return this;
    }

    public CenterImageSpan setImageRect(int i, int i2) {
        this.mFitTextLine = false;
        this.mImageRect = new Rect(0, 0, i, i2);
        return this;
    }

    public CenterImageSpan setImageRect(Rect rect) {
        this.mFitTextLine = false;
        this.mImageRect = rect;
        return this;
    }

    public CenterImageSpan setMarginLeft(float f) {
        this.mMarginLeft = f;
        return this;
    }

    public CenterImageSpan setMarginRight(float f) {
        this.mMarginRight = f;
        return this;
    }

    public CenterImageSpan setOffsetY(float f) {
        this.mOffsetY = f;
        return this;
    }

    public CenterImageSpan setTag(Object obj) {
        this.mTag = obj;
        return this;
    }
}
