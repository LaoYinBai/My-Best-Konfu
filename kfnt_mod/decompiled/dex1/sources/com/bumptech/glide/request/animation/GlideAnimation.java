package com.bumptech.glide.request.animation;

import android.graphics.drawable.Drawable;
import android.view.View;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface GlideAnimation<R> {

    public interface ViewAdapter {
        Drawable getCurrentDrawable();

        View getView();

        void setDrawable(Drawable drawable);
    }

    boolean animate(R r, ViewAdapter viewAdapter);
}
