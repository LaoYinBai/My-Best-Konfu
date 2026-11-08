package com.tachikoma.core.component.view;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.View;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ViewUtils {
    public static Drawable generateBackgroundWithShadow(View view, int i, float f, int i2, int i3, float f2, int i4) {
        int i5;
        float f3 = 2.0f * f;
        int i6 = (int) f2;
        float[] fArr = {f3, f3, f3, f3, f3, f3, f3, f3};
        Rect rect = new Rect();
        rect.left = i6;
        rect.right = i6;
        if (i4 == 17) {
            rect.top = i6;
            rect.bottom = i6;
            i5 = 0;
        } else if (i4 != 48) {
            rect.top = i6;
            rect.bottom = i6 * 2;
            i5 = i6 / 3;
        } else {
            rect.top = i6 * 2;
            rect.bottom = i6;
            i5 = (i6 * (-1)) / 3;
        }
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        shapeDrawable.setPadding(rect);
        shapeDrawable.getPaint().setColor(i);
        shapeDrawable.getPaint().setShadowLayer(f, i3, i5, i2);
        view.setLayerType(1, null);
        shapeDrawable.setShape(new RoundRectShape(fArr, null, null));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{shapeDrawable, view.getBackground()});
        int i7 = i6 * 2;
        layerDrawable.setLayerInset(0, i7, i7, i6, i7);
        return layerDrawable;
    }
}
