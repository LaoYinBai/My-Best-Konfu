package com.ss.union.game.sdk.common.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import com.ss.union.game.sdk.common.ui.roundedimageview.RoundedDrawable;
import com.ss.union.game.sdk.common.util.ResourceUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class RoundedUI {
    private float a;
    private float b;
    private float c;
    private float d;
    private float e;
    private boolean f;
    private Paint g = new Paint();
    private Paint h;
    private RoundedView i;

    public interface RoundedView {
        void drawSuper(Canvas canvas);

        Context getContext();

        int getHeight();

        RoundedUI getRoundedUI();

        int getWidth();

        void invalidate();

        void radius(int i);

        void radius(int i, int i2, int i3, int i4);

        void setCircle(boolean z);

        void setWillNotDraw(boolean z);
    }

    public RoundedUI(RoundedView roundedView, AttributeSet attributeSet) {
        this.i = roundedView;
        this.g.setColor(-1);
        this.g.setAntiAlias(true);
        this.g.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        roundedView.setWillNotDraw(false);
        a(attributeSet);
    }

    private void a(AttributeSet attributeSet) {
        float f = this.i.getContext().getResources().getDisplayMetrics().density;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = this.i.getContext().obtainStyledAttributes(attributeSet, ResourceUtils.getStyleableInts("styleable", "LGRoundedUI"));
            this.a = typedArrayObtainStyledAttributes.getDimensionPixelSize(ResourceUtils.getStyleableInt("styleable", "LGRoundedUI_radius"), 0);
            this.b = typedArrayObtainStyledAttributes.getDimensionPixelSize(ResourceUtils.getStyleableInt("styleable", "LGRoundedUI_topLeftRadius"), (int) this.a);
            this.c = typedArrayObtainStyledAttributes.getDimensionPixelSize(ResourceUtils.getStyleableInt("styleable", "LGRoundedUI_topRightRadius"), (int) this.a);
            this.d = typedArrayObtainStyledAttributes.getDimensionPixelSize(ResourceUtils.getStyleableInt("styleable", "LGRoundedUI_bottomLeftRadius"), (int) this.a);
            this.e = typedArrayObtainStyledAttributes.getDimensionPixelSize(ResourceUtils.getStyleableInt("styleable", "LGRoundedUI_bottomRightRadius"), (int) this.a);
            this.f = typedArrayObtainStyledAttributes.getBoolean(ResourceUtils.getStyleableInt("styleable", "LGRoundedUI_isCircle"), false);
            int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(ResourceUtils.getStyleableInt("styleable", "LGRoundedUI_borderWidth"), 0);
            int color = typedArrayObtainStyledAttributes.getColor(ResourceUtils.getStyleableInt("styleable", "LGRoundedUI_borderColor"), RoundedDrawable.DEFAULT_BORDER_COLOR);
            if (dimensionPixelSize > 0) {
                this.h = new Paint();
                this.h.setStyle(Paint.Style.STROKE);
                this.h.setStrokeWidth(dimensionPixelSize);
                this.h.setColor(color);
                this.h.setAntiAlias(true);
            }
        }
    }

    public void a(int i, int i2) {
        if (this.h == null) {
            this.h = new Paint();
            this.h.setStyle(Paint.Style.STROKE);
            this.h.setAntiAlias(true);
        }
        this.h.setStrokeWidth(i);
        this.h.setColor(i2);
        this.i.invalidate();
    }

    public void a(int i) {
        float f = i;
        this.a = f;
        this.b = f;
        this.c = f;
        this.d = f;
        this.e = f;
        this.i.invalidate();
    }

    public void a(int i, int i2, int i3, int i4) {
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = i4;
    }

    public void a(boolean z) {
        if (this.f != z) {
            this.f = z;
        }
    }

    public void a(Canvas canvas) {
        int iA = a();
        int iB = b();
        if (iB <= 0 || iA <= 0) {
            return;
        }
        float f = this.b;
        float f2 = this.c;
        float f3 = this.d;
        float f4 = this.e;
        if (this.f) {
            f = iB / 2.0f;
            f2 = f;
            f3 = f2;
            f4 = f3;
        }
        if (f <= 0.0f && f2 <= 0.0f && f3 <= 0.0f && f4 <= 0.0f) {
            this.i.drawSuper(canvas);
            b(canvas);
            return;
        }
        int iSave = canvas.save();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iB, iA, Bitmap.Config.ARGB_8888);
        Canvas canvas2 = new Canvas(bitmapCreateBitmap);
        int iSaveLayer = canvas2.saveLayer(0.0f, 0.0f, iB, iA, null, 31);
        this.i.drawSuper(canvas2);
        a(canvas2, f);
        d(canvas2, f2);
        b(canvas2, f3);
        c(canvas2, f3);
        canvas2.restoreToCount(iSaveLayer);
        canvas.drawBitmap(bitmapCreateBitmap, 0.0f, 0.0f, (Paint) null);
        bitmapCreateBitmap.recycle();
        b(canvas);
        canvas.restoreToCount(iSave);
    }

    private void b(Canvas canvas) {
        if (this.h != null) {
            float strokeWidth = (int) ((this.h.getStrokeWidth() / 2.0f) + 0.5f);
            canvas.drawRoundRect(new RectF(strokeWidth, strokeWidth, b() - r0, a() - r0), this.a, this.a, this.h);
        }
    }

    private void a(Canvas canvas, float f) {
        Path path = new Path();
        path.moveTo(0.0f, f);
        path.lineTo(0.0f, 0.0f);
        path.lineTo(f, 0.0f);
        float f2 = f * 2.0f;
        path.arcTo(new RectF(0.0f, 0.0f, f2, f2), -90.0f, -90.0f);
        path.close();
        canvas.drawPath(path, this.g);
    }

    private void b(Canvas canvas, float f) {
        int iA = a();
        Path path = new Path();
        float f2 = iA;
        path.moveTo(0.0f, f2 - f);
        path.lineTo(0.0f, f2);
        path.lineTo(f, f2);
        float f3 = f * 2.0f;
        path.arcTo(new RectF(0.0f, f2 - f3, f3, a()), 90.0f, 90.0f);
        path.close();
        canvas.drawPath(path, this.g);
    }

    private void c(Canvas canvas, float f) {
        int iA = a();
        int iB = b();
        Path path = new Path();
        float f2 = iB;
        float f3 = iA;
        path.moveTo(f2 - f, f3);
        path.lineTo(f2, f3);
        path.lineTo(f2, f3 - f);
        float f4 = f * 2.0f;
        path.arcTo(new RectF(f2 - f4, f3 - f4, f2, f3), 0.0f, 90.0f);
        path.close();
        canvas.drawPath(path, this.g);
    }

    private void d(Canvas canvas, float f) {
        int iB = b();
        Path path = new Path();
        float f2 = iB;
        path.moveTo(f2, f);
        path.lineTo(f2, 0.0f);
        path.lineTo(f2 - f, 0.0f);
        float f3 = f * 2.0f;
        path.arcTo(new RectF(f2 - f3, 0.0f, f2, f3), -90.0f, 90.0f);
        path.close();
        canvas.drawPath(path, this.g);
    }

    public static Bitmap a(Bitmap bitmap, int i, int i2) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        int iSave = canvas.save();
        Paint paint = new Paint();
        RectF rectF = new RectF(0.0f, 0.0f, bitmap.getWidth(), bitmap.getHeight());
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(rectF, i, i2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        canvas.restoreToCount(iSave);
        return bitmapCreateBitmap;
    }

    public int a() {
        return this.i.getHeight();
    }

    public int b() {
        return this.i.getWidth();
    }
}
