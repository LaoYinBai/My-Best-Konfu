package com.kwad.sdk.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.kwad.sdk.R;
import com.kwad.sdk.widget.i;

@SuppressLint({"AppCompatCustomView"})
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class KSCornerImageView extends ImageView {
    private i a;

    public KSCornerImageView(Context context) {
        super(context);
        a(context, null, 0);
    }

    public KSCornerImageView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet, 0);
    }

    public KSCornerImageView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet, i);
    }

    @RequiresApi(api = 21)
    public KSCornerImageView(Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(context, attributeSet, i);
    }

    private void a(Context context, @Nullable AttributeSet attributeSet, int i) {
        i.a aVar = new i.a();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ksad_KSCornerImageView);
        aVar.a(typedArrayObtainStyledAttributes.getBoolean(R.styleable.ksad_KSCornerImageView_ksad_leftTopCorner, true));
        aVar.b(typedArrayObtainStyledAttributes.getBoolean(R.styleable.ksad_KSCornerImageView_ksad_topRightCorner, true));
        aVar.c(typedArrayObtainStyledAttributes.getBoolean(R.styleable.ksad_KSCornerImageView_ksad_rightBottomCorner, true));
        aVar.d(typedArrayObtainStyledAttributes.getBoolean(R.styleable.ksad_KSCornerImageView_ksad_bottomLeftCorner, true));
        typedArrayObtainStyledAttributes.recycle();
        this.a = new i(aVar);
        this.a.a(context, attributeSet);
    }

    @Override // android.view.View
    protected void dispatchDraw(Canvas canvas) {
        this.a.c(canvas);
        super.dispatchDraw(canvas);
        this.a.d(canvas);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        this.a.a(canvas);
        super.draw(canvas);
        this.a.b(canvas);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.a.a(i, i2);
    }

    public void setRadius(float f) {
        this.a.a(f);
        postInvalidate();
    }
}
