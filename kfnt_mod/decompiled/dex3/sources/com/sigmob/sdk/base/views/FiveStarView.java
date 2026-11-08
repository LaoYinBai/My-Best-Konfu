package com.sigmob.sdk.base.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class FiveStarView extends View {
    Paint a;
    private final float b;

    public FiveStarView(Context context, float f) {
        super(context);
        this.a = new Paint();
        this.b = f;
    }

    private Path a(float f, float f2) {
        Path path = new Path();
        path.moveTo(a(288) * f, b(288) * f);
        path.lineTo(a(108) * f2, b(108) * f2);
        path.lineTo(a(144) * f, b(144) * f);
        path.lineTo(a(180) * f2, b(180) * f2);
        path.lineTo(a(216) * f, f * b(216));
        path.lineTo(a(252) * f2, f2 * b(252));
        path.close();
        return path;
    }

    private Path b(float f, float f2) {
        Path path = new Path();
        path.moveTo(a(0) * f, b(0) * f);
        path.lineTo(a(36) * f2, b(36) * f2);
        path.lineTo(a(72) * f, b(72) * f);
        path.lineTo(a(108) * f2, b(108) * f2);
        path.lineTo(a(144) * f, b(144) * f);
        path.lineTo(a(180) * f2, b(180) * f2);
        path.lineTo(a(216) * f, b(216) * f);
        path.lineTo(a(252) * f2, b(252) * f2);
        path.lineTo(a(288) * f, f * b(288));
        path.lineTo(a(324) * f2, f2 * b(324));
        path.close();
        return path;
    }

    float a(int i) {
        return (float) Math.cos((i * 3.141592653589793d) / 180.0d);
    }

    float b(int i) {
        return (float) Math.sin((i * 3.141592653589793d) / 180.0d);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Path pathB;
        super.onDraw(canvas);
        int height = getHeight();
        int i = (int) this.b;
        float f = height / 3.0f;
        float fB = (b(18) * f) / b(126);
        boolean z = true;
        this.a.setAntiAlias(true);
        this.a.setColor(Color.parseColor("#FFA500"));
        for (int i2 = i; i2 > 0; i2--) {
            if (z) {
                z = false;
                canvas.translate(2.0f + f, height / 2);
            } else {
                canvas.rotate(18.0f);
                canvas.translate(2.2f * f, 0.0f);
            }
            canvas.rotate(-18.0f);
            Path pathB2 = b(f, fB);
            this.a.setStyle(Paint.Style.STROKE);
            canvas.drawPath(pathB2, this.a);
            this.a.setStyle(Paint.Style.FILL);
            canvas.drawPath(pathB2, this.a);
        }
        float f2 = i;
        if (this.b > f2) {
            float f3 = (this.b - f2) + 0.25f;
            if (f3 >= 1.0f) {
                canvas.rotate(18.0f);
                canvas.translate(f * 2.2f, 0.0f);
                canvas.rotate(-18.0f);
                pathB = b(f, fB);
                this.a.setStyle(Paint.Style.STROKE);
                this.a.setColor(Color.parseColor("#FFA500"));
                canvas.drawPath(pathB, this.a);
            } else if (f3 >= 0.5f) {
                canvas.rotate(18.0f);
                canvas.translate(f * 2.2f, 0.0f);
                canvas.rotate(-18.0f);
                Path pathB3 = b(f, fB);
                this.a.setColor(Color.parseColor("#FFA500"));
                this.a.setStyle(Paint.Style.STROKE);
                canvas.drawPath(pathB3, this.a);
                pathB = a(f, fB);
                this.a.setColor(Color.parseColor("#FFA500"));
            } else {
                canvas.rotate(18.0f);
                canvas.translate(f * 2.2f, 0.0f);
                canvas.rotate(-18.0f);
                pathB = b(f, fB);
                this.a.setStyle(Paint.Style.STROKE);
                this.a.setColor(Color.parseColor("#FFA500"));
                canvas.drawPath(pathB, this.a);
            }
            this.a.setStyle(Paint.Style.FILL);
            canvas.drawPath(pathB, this.a);
        }
        for (int i3 = (int) (5.0f - this.b); i3 > 0; i3--) {
            canvas.rotate(18.0f);
            canvas.translate(f * 2.2f, 0.0f);
            canvas.rotate(-18.0f);
            Path pathB4 = b(f, fB);
            this.a.setStyle(Paint.Style.STROKE);
            this.a.setColor(Color.parseColor("#FFA500"));
            canvas.drawPath(pathB4, this.a);
        }
    }
}
