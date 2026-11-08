package com.mbridge.msdk.out;

import android.graphics.drawable.Drawable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public interface OnImageLoadListener {
    void loadError(String str);

    void loadSuccess(Drawable drawable, int i);
}
