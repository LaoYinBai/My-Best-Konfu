package com.mbridge.msdk.widget.custom.baseview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBRoundRectImageViewC extends MBImageView {
    private Paint a;

    public MBRoundRectImageViewC(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new Paint();
    }

    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            int width = getWidth();
            float height = getHeight() / bitmap.getHeight();
            Matrix matrix = new Matrix();
            matrix.postScale(width / bitmap.getWidth(), height);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            int iA = com.mbridge.msdk.widget.custom.b.a.a(getContext(), 10.0f);
            BitmapShader bitmapShader = new BitmapShader(bitmapCreateBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(bitmapCreateBitmap.getWidth(), bitmapCreateBitmap.getHeight(), Bitmap.Config.ARGB_4444);
            Canvas canvas2 = new Canvas(bitmapCreateBitmap2);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setShader(bitmapShader);
            RectF rectF = new RectF(0.0f, 0.0f, bitmapCreateBitmap.getWidth(), bitmapCreateBitmap.getHeight());
            float f = iA;
            canvas2.drawRoundRect(rectF, f, f, paint);
            Rect rect = new Rect(0, 0, bitmapCreateBitmap2.getWidth(), bitmapCreateBitmap2.getHeight());
            this.a.reset();
            canvas.drawBitmap(bitmapCreateBitmap2, rect, rect, this.a);
            return;
        }
        super.onDraw(canvas);
    }
}
