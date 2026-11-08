package com.umeng.message.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.Button;
import com.ss.union.game.sdk.common.ui.roundedimageview.RoundedDrawable;
import com.umeng.message.proguard.j;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UCloseView extends Button {
    private float cx;
    private float cy;
    private float lineWith;
    private Paint mPaint;
    private float p;
    private float radius;
    private int remain;

    public UCloseView(Context context) {
        super(context);
        setBackgroundColor(0);
        this.mPaint = new Paint();
        this.remain = j.a(context, 1.0f);
        this.lineWith = j.a(context, 2.0f);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.cx = getWidth() / 2;
        this.cy = getHeight() / 2;
        this.radius = (Math.min(getHeight(), getWidth()) / 2) - this.remain;
        this.p = this.radius / 1.4142f;
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(RoundedDrawable.DEFAULT_BORDER_COLOR);
        this.mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(this.cx, this.cy, this.radius, this.mPaint);
        this.mPaint.setColor(-1);
        this.mPaint.setStrokeWidth(this.lineWith);
        this.mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(this.cx, this.cy, this.radius, this.mPaint);
        canvas.drawLine(this.cx - this.p, this.cy - this.p, this.cx + this.p, this.cy + this.p, this.mPaint);
        canvas.drawLine(this.cx + this.p, this.cy - this.p, this.cx - this.p, this.cy + this.p, this.mPaint);
    }
}
