package com.sigmob.sdk.blurkit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.ss.union.game.sdk.common.ui.roundedimageview.RoundedDrawable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class RoundedImageView extends ImageView {
    private float a;
    private RectF b;
    private PorterDuffXfermode c;

    public RoundedImageView(Context context) {
        super(context, null);
        this.a = 0.0f;
        this.b = new RectF();
        this.c = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    }

    public RoundedImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = 0.0f;
        this.b = new RectF();
        this.c = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    }

    public float getCornerRadius() {
        return this.a;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable == null || !(drawable instanceof BitmapDrawable) || this.a <= 0.0f) {
            super.onDraw(canvas);
            return;
        }
        this.b.set(drawable.getBounds());
        int iSaveLayer = canvas.saveLayer(this.b, null, 31);
        getImageMatrix().mapRect(this.b);
        Paint paint = ((BitmapDrawable) drawable).getPaint();
        paint.setAntiAlias(true);
        paint.setColor(RoundedDrawable.DEFAULT_BORDER_COLOR);
        Xfermode xfermode = paint.getXfermode();
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(this.b, this.a, this.a, paint);
        paint.setXfermode(this.c);
        super.onDraw(canvas);
        paint.setXfermode(xfermode);
        canvas.restoreToCount(iSaveLayer);
    }

    public void setCornerRadius(float f) {
        this.a = f;
    }
}
