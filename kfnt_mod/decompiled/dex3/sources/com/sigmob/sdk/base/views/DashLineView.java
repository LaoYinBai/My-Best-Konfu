package com.sigmob.sdk.base.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DashLineView extends View {
    private Paint a;
    private Path b;

    public DashLineView(Context context) {
        super(context);
        this.a = new Paint(1);
        this.a.setColor(-12303292);
        this.a.setStyle(Paint.Style.STROKE);
        this.a.setStrokeWidth(3.0f);
        this.a.setPathEffect(new DashPathEffect(new float[]{10.0f, 5.0f}, 0.0f));
        this.b = new Path();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        this.b.reset();
        getHeight();
        getWidth();
        this.b.moveTo(0.0f, 0.0f);
        this.b.lineTo(0.0f, getHeight());
        canvas.drawPath(this.b, this.a);
    }
}
