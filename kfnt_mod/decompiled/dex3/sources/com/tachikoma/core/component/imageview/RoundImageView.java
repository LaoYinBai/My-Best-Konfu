package com.tachikoma.core.component.imageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.tachikoma.core.Tachikoma;
import com.tachikoma.core.log.Logger;
import com.tachikoma.core.utility.TKConverter;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class RoundImageView extends ImageView {
    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
    private static final int COLORDRAWABLE_DIMENSION = 1;
    private static final int DEFAULT_BORDER_COLOR = 0;
    private static final int DEFAULT_BORDER_WIDTH = 0;
    private Bitmap mBitmap;
    private int mBitmapHeight;
    private final Paint mBitmapPaint;
    private BitmapShader mBitmapShader;
    private int mBitmapWidth;
    private int mBorderColor;
    private final Paint mBorderPaint;
    private final RectF mBorderRect;
    private float mBorderWidth;
    private float mBottomLeftRoundRadius;
    private float mBottomRightRoundRadius;
    private float mCircleRadius;
    private final RectF mDrawableRect;
    private boolean mReady;
    private boolean mSetupPending;
    private final Matrix mShaderMatrix;
    private float mShadowWidth;
    private float mTopLeftRoundRadius;
    private float mTopRightRoundRadius;

    public RoundImageView(Context context) {
        this(context, null);
    }

    public RoundImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoundImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDrawableRect = new RectF();
        this.mBorderRect = new RectF();
        this.mShaderMatrix = new Matrix();
        this.mBitmapPaint = new Paint();
        this.mBorderPaint = new Paint();
        this.mBorderColor = 0;
        this.mBorderWidth = 0.0f;
        this.mBorderWidth = 0.0f;
        this.mBorderColor = 0;
        this.mReady = true;
        if (this.mSetupPending) {
            setup();
            this.mSetupPending = false;
        }
    }

    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            Bitmap bitmapCreateBitmap = drawable instanceof ColorDrawable ? Bitmap.createBitmap(1, 1, BITMAP_CONFIG) : Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), BITMAP_CONFIG);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmapCreateBitmap;
        } catch (Throwable th) {
            Logger.logE("RoundImageView getBitmapFromDrawable exception:", th);
            return null;
        }
    }

    private void setup() {
        if (!this.mReady) {
            this.mSetupPending = true;
            return;
        }
        if (this.mBitmap == null) {
            return;
        }
        this.mBitmapShader = new BitmapShader(this.mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        this.mBitmapPaint.setAntiAlias(true);
        this.mBitmapPaint.setShader(this.mBitmapShader);
        this.mBorderPaint.setStyle(Paint.Style.STROKE);
        this.mBorderPaint.setAntiAlias(true);
        this.mBorderPaint.setColor(this.mBorderColor);
        this.mBorderPaint.setStrokeWidth(this.mBorderWidth);
        this.mBitmapHeight = this.mBitmap.getHeight();
        this.mBitmapWidth = this.mBitmap.getWidth();
        this.mBorderRect.set((this.mBorderWidth / 2.0f) + this.mShadowWidth, (this.mBorderWidth / 2.0f) + this.mShadowWidth, (getWidth() - (this.mBorderWidth / 2.0f)) - this.mShadowWidth, (getHeight() - (this.mBorderWidth / 2.0f)) - this.mShadowWidth);
        this.mDrawableRect.set((this.mBorderWidth / 2.0f) + this.mShadowWidth, (this.mBorderWidth / 2.0f) + this.mShadowWidth, (getWidth() - (this.mBorderWidth / 2.0f)) - this.mShadowWidth, (getHeight() - (this.mBorderWidth / 2.0f)) - this.mShadowWidth);
        this.mCircleRadius = Math.min(this.mDrawableRect.height() / 2.0f, this.mDrawableRect.width() / 2.0f);
        updateShaderMatrix();
        invalidate();
    }

    private void updateShaderMatrix() {
        float fWidth;
        float fHeight;
        this.mShaderMatrix.set(null);
        float fWidth2 = 0.0f;
        if (this.mBitmapWidth * this.mDrawableRect.height() > this.mDrawableRect.width() * this.mBitmapHeight) {
            fWidth = this.mDrawableRect.height() / this.mBitmapHeight;
            fWidth2 = (this.mDrawableRect.width() - (this.mBitmapWidth * fWidth)) * 0.5f;
            fHeight = 0.0f;
        } else {
            fWidth = this.mDrawableRect.width() / this.mBitmapWidth;
            fHeight = (this.mDrawableRect.height() - (this.mBitmapHeight * fWidth)) * 0.5f;
        }
        this.mShaderMatrix.setScale(fWidth, fWidth);
        this.mShaderMatrix.postTranslate(((int) (fWidth2 + 0.5f)) + this.mBorderWidth, ((int) (fHeight + 0.5f)) + this.mBorderWidth);
        this.mBitmapShader.setLocalMatrix(this.mShaderMatrix);
    }

    public int getBorderColor() {
        return this.mBorderColor;
    }

    public float getBorderWidth() {
        return this.mBorderWidth;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        if (getDrawable() == null) {
            return;
        }
        Path path = new Path();
        float fMin = Math.min(this.mDrawableRect.height() / 2.0f, this.mDrawableRect.width() / 2.0f);
        float fMin2 = Math.min(this.mTopLeftRoundRadius, fMin);
        float fMin3 = Math.min(this.mTopRightRoundRadius, fMin);
        float fMin4 = Math.min(this.mBottomRightRoundRadius, fMin);
        float fMin5 = Math.min(this.mBottomLeftRoundRadius, fMin);
        path.addRoundRect(this.mDrawableRect, new float[]{fMin2, fMin2, fMin3, fMin3, fMin4, fMin4, fMin5, fMin5}, Path.Direction.CW);
        canvas.drawPath(path, this.mBitmapPaint);
        if (this.mBorderWidth > 0.0f) {
            if (this.mTopLeftRoundRadius <= 0.0f && this.mTopRightRoundRadius <= 0.0f && this.mBottomRightRoundRadius <= 0.0f && this.mBottomLeftRoundRadius <= 0.0f) {
                canvas.drawRect(this.mBorderRect, this.mBorderPaint);
                return;
            }
            Path path2 = new Path();
            path2.addRoundRect(this.mBorderRect, new float[]{fMin2, fMin2, fMin3, fMin3, fMin4, fMin4, fMin5, fMin5}, Path.Direction.CW);
            canvas.drawPath(path2, this.mBorderPaint);
        }
    }

    public void onShadowSet(float f) {
        if (this.mShadowWidth != f) {
            this.mShadowWidth = f;
            setup();
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        setup();
    }

    public void setBorderColor(int i) {
        if (i == this.mBorderColor) {
            return;
        }
        this.mBorderColor = i;
        this.mBorderPaint.setColor(this.mBorderColor);
        invalidate();
    }

    protected void setBorderRadius(float f) {
        float fDp2px = TKConverter.dp2px(Tachikoma.sApplication, f);
        this.mTopLeftRoundRadius = fDp2px;
        this.mTopRightRoundRadius = fDp2px;
        this.mBottomRightRoundRadius = fDp2px;
        this.mBottomLeftRoundRadius = fDp2px;
        setup();
    }

    public void setBorderWidth(float f) {
        if (f == this.mBorderWidth) {
            return;
        }
        this.mBorderWidth = f;
        setup();
    }

    public void setBottomLeftRoundRadius(float f) {
        this.mBottomLeftRoundRadius = f;
    }

    public void setBottomRightRoundRadius(float f) {
        this.mBottomRightRoundRadius = f;
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        this.mBitmap = bitmap;
        setup();
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        this.mBitmap = getBitmapFromDrawable(drawable);
        setup();
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        super.setImageResource(i);
        this.mBitmap = getBitmapFromDrawable(getDrawable());
        setup();
    }

    public void setTopLeftRoundRadius(float f) {
        this.mTopLeftRoundRadius = f;
    }

    public void setTopRightRoundRadius(float f) {
        this.mTopRightRoundRadius = f;
    }
}
