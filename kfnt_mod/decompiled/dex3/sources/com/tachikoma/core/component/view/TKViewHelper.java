package com.tachikoma.core.component.view;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKViewHelper {
    public static void setBackground(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }
}
