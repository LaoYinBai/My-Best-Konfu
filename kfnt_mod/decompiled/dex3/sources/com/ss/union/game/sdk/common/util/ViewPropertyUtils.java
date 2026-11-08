package com.ss.union.game.sdk.common.util;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ViewPropertyUtils {
    private View a;

    public ViewPropertyUtils(View view) {
        this.a = view;
    }

    public ViewPropertyUtils text(String str) {
        if (this.a instanceof TextView) {
            ((TextView) this.a).setText(str);
        }
        return this;
    }

    public ViewPropertyUtils text(int i) {
        return text(this.a.getContext().getString(i));
    }

    public ViewPropertyUtils resText(String str) {
        return text(ResourceUtils.getStringIdByName(str));
    }

    public ViewPropertyUtils visibly(int i) {
        this.a.setVisibility(i);
        return this;
    }

    public ViewPropertyUtils iv(Drawable drawable) {
        if (this.a instanceof ImageView) {
            ((ImageView) this.a).setImageDrawable(drawable);
        }
        return this;
    }

    public ViewPropertyUtils iv(Bitmap bitmap) {
        if (this.a instanceof ImageView) {
            ((ImageView) this.a).setImageBitmap(bitmap);
        }
        return this;
    }

    public ViewPropertyUtils click(View.OnClickListener onClickListener) {
        this.a.setOnClickListener(onClickListener);
        return this;
    }

    public ViewPropertyUtils enable() {
        this.a.setEnabled(true);
        return this;
    }

    public ViewPropertyUtils disable() {
        this.a.setEnabled(false);
        return this;
    }

    public ViewPropertyUtils background(Drawable drawable) {
        this.a.setBackground(drawable);
        return this;
    }

    public ViewPropertyUtils background(int i) {
        this.a.setBackgroundColor(i);
        return this;
    }

    public ViewPropertyUtils background(String str) {
        this.a.setBackgroundResource(ResourceUtils.getDrawableIdByName(str));
        return this;
    }

    public ViewPropertyUtils alpha(float f) {
        this.a.setAlpha(f);
        return this;
    }

    public ViewPropertyUtils anim(Animation animation) {
        this.a.startAnimation(animation);
        return this;
    }

    public ViewPropertyUtils underLine() {
        if (this.a instanceof TextView) {
            TextView textView = (TextView) this.a;
            textView.getPaint().setFlags(8);
            textView.getPaint().setAntiAlias(true);
        }
        return this;
    }

    public <T extends View> T getView() {
        return (T) this.a;
    }
}
