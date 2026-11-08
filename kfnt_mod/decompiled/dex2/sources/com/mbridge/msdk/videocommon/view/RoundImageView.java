package com.mbridge.msdk.videocommon.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.widget.MBImageView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class RoundImageView extends MBImageView {
    private int a;
    private int b;
    private Paint c;
    private int d;
    private Matrix e;
    private BitmapShader f;
    private int g;
    private RectF h;

    public RoundImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = new Matrix();
        this.c = new Paint();
        this.c.setAntiAlias(true);
        this.b = (int) TypedValue.applyDimension(1, 5.0f, getResources().getDisplayMetrics());
        this.a = 1;
    }

    public RoundImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = new Matrix();
        this.c = new Paint();
        this.c.setAntiAlias(true);
        this.b = (int) TypedValue.applyDimension(1, 5.0f, getResources().getDisplayMetrics());
        this.a = 1;
    }

    public RoundImageView(Context context) {
        super(context);
        this.e = new Matrix();
        this.c = new Paint();
        this.c.setAntiAlias(true);
        this.b = (int) TypedValue.applyDimension(1, 5.0f, getResources().getDisplayMetrics());
        this.a = 1;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.a == 0) {
            this.g = Math.min(getMeasuredWidth(), getMeasuredHeight());
            this.d = this.g / 2;
            setMeasuredDimension(this.g, this.g);
        }
    }

    @Override // com.mbridge.msdk.widget.MBImageView, android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        float fMin;
        if (getDrawable() == null) {
            return;
        }
        Drawable drawable = getDrawable();
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(bitmapCreateBitmap);
            drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
            drawable.draw(canvas2);
            this.f = new BitmapShader(bitmapCreateBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            float fMin2 = 1.0f;
            if (this.a == 0) {
                float fMin3 = Math.min(bitmapCreateBitmap.getWidth(), bitmapCreateBitmap.getHeight());
                fMin2 = (this.g * 1.0f) / fMin3;
                fMin = (this.g * 1.0f) / fMin3;
            } else if (this.a == 1) {
                fMin = Math.min((getWidth() * 1.0f) / bitmapCreateBitmap.getWidth(), 1.0f);
                fMin2 = Math.min((getHeight() * 1.0f) / bitmapCreateBitmap.getHeight(), 1.0f);
            } else {
                fMin = 1.0f;
            }
            this.e.setScale(fMin, fMin2);
            this.f.setLocalMatrix(this.e);
            this.c.setShader(this.f);
        }
        if (this.a == 1) {
            canvas.drawRoundRect(this.h, this.b, this.b, this.c);
        } else {
            canvas.drawCircle(this.d, this.d, this.d, this.c);
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.a == 1) {
            this.h = new RectF(0.0f, 0.0f, getWidth(), getHeight());
        }
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("state_instance", super.onSaveInstanceState());
        bundle.putInt("state_type", this.a);
        bundle.putInt("state_border_radius", this.b);
        return bundle;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            super.onRestoreInstanceState(bundle.getParcelable("state_instance"));
            this.a = bundle.getInt("state_type");
            this.b = bundle.getInt("state_border_radius");
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    public void setBorderRadius(int i) {
        int iB = w.b(getContext(), i);
        if (this.b != iB) {
            this.b = iB;
            invalidate();
        }
    }

    public void setType(int i) {
        if (this.a != i) {
            this.a = i;
            if (this.a != 1 && this.a != 0) {
                this.a = 0;
            }
            requestLayout();
        }
    }
}
