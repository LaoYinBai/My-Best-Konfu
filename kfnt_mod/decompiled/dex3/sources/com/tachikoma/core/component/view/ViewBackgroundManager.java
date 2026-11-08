package com.tachikoma.core.component.view;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.text.TextUtils;
import android.view.View;
import com.tachikoma.core.utility.TKUtility;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ViewBackgroundManager {
    private float mFullRadius;
    private TKViewBackgroundDrawable mReactBackgroundDrawable;
    private View mView;
    private int mBackgroundColor = -1;
    private float[] mRadius = new float[8];

    public ViewBackgroundManager(View view) {
        this.mView = view;
    }

    private TKViewBackgroundDrawable getOrCreateReactViewBackground() {
        if (this.mReactBackgroundDrawable == null) {
            this.mReactBackgroundDrawable = new TKViewBackgroundDrawable(this.mView.getContext());
            setBackgroundDrawable(this.mReactBackgroundDrawable);
        }
        return this.mReactBackgroundDrawable;
    }

    private boolean hasRoundedRadius() {
        for (float f : this.mRadius) {
            if (f > 0.0f) {
                return true;
            }
        }
        return false;
    }

    private void setBackgroundDrawable(Drawable drawable) {
        TKViewHelper.setBackground(this.mView, null);
        Drawable background = this.mView.getBackground();
        if (background == null) {
            TKViewHelper.setBackground(this.mView, drawable);
        } else {
            TKViewHelper.setBackground(this.mView, new LayerDrawable(new Drawable[]{drawable, background}));
        }
    }

    public void destroy() {
        this.mView = null;
    }

    public int getBackgroundColor() {
        return getOrCreateReactViewBackground().getColor();
    }

    public void setBackgroundColor(int i) {
        if (i == 0 && this.mReactBackgroundDrawable == null) {
            return;
        }
        this.mBackgroundColor = i;
        getOrCreateReactViewBackground().setColor(i);
    }

    public void setBackgroundGradientColor(int i, int[] iArr, float[] fArr) {
        getOrCreateReactViewBackground().setBackgroundGradientColor(i, iArr, fArr);
    }

    public void setBackgroundImage(String str) {
        Drawable drawable = null;
        if (!TextUtils.isEmpty(str)) {
            if (str.startsWith("/")) {
                drawable = new BitmapDrawable(BitmapFactory.decodeFile(str));
            } else {
                drawable = this.mView.getResources().getDrawable(TKUtility.getResourceId(str, "drawable", null));
            }
        }
        setBackgroundDrawable(drawable);
    }

    public void setBorderColor(int i, float f, float f2) {
        getOrCreateReactViewBackground().setBorderColor(i, f, f2);
    }

    public void setBorderColor(String str) {
        getOrCreateReactViewBackground().setBorderColor(str);
    }

    public void setBorderRadius(float f) {
        getOrCreateReactViewBackground().setRadius(f);
        this.mFullRadius = f;
    }

    public void setBorderRadius(float f, int i) {
        getOrCreateReactViewBackground().setRadius(f, i);
        int i2 = (i % 4) * 2;
        this.mRadius[i2] = f;
        this.mRadius[i2 + 1] = f;
    }

    public void setBorderStyle(String str) {
        getOrCreateReactViewBackground().setBorderStyle(str);
    }

    public void setBorderWidth(int i, float f) {
        getOrCreateReactViewBackground().setBorderWidth(i, f);
    }

    public void setOpacity(float f) {
        getOrCreateReactViewBackground().setAlpha((int) (f * 255.0f));
    }

    public void setShadow(float f, float f2, float f3, int i) {
        getOrCreateReactViewBackground().setShadow(f, f2, f3, i);
    }
}
