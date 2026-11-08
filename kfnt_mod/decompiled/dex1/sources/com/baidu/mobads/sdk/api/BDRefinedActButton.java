package com.baidu.mobads.sdk.api;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.baidu.mobads.sdk.internal.aj;
import com.baidu.mobads.sdk.internal.ba;
import com.baidu.mobads.sdk.internal.r;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class BDRefinedActButton extends LinearLayout {
    private View mAdView;
    private Context mContext;
    private ClassLoader mLoader;

    public BDRefinedActButton(Context context) {
        this(context, null, 0);
    }

    public BDRefinedActButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BDRefinedActButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView(context);
    }

    public void setAdData(NativeResponse nativeResponse) {
        if (this.mAdView != null) {
            aj.a(r.h, this.mAdView, this.mLoader, "setAdData", new Class[]{Object.class}, nativeResponse);
        }
    }

    private void initView(Context context) {
        try {
            this.mContext = context;
            Object[] objArr = {this.mContext};
            this.mLoader = ba.a(this.mContext);
            this.mAdView = (View) aj.a(r.h, this.mLoader, (Class<?>[]) new Class[]{Context.class}, objArr);
            if (this.mAdView != null) {
                addView(this.mAdView, new RelativeLayout.LayoutParams(-2, -2));
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setButtonFontTypeFace(Typeface typeface) {
        if (this.mAdView != null) {
            aj.a(r.h, this.mAdView, this.mLoader, "setButtonFontTypeFace", new Class[]{Typeface.class}, typeface);
        }
    }

    public void setButtonFontSizeSp(int i) {
        if (this.mAdView != null) {
            aj.a(r.h, this.mAdView, this.mLoader, "setButtonFontSizeSp", new Class[]{Integer.TYPE}, Integer.valueOf(i));
        }
    }

    public void setButtonTextColor(int i) {
        if (this.mAdView != null) {
            aj.a(r.h, this.mAdView, this.mLoader, "setButtonTextColor", new Class[]{Integer.TYPE}, Integer.valueOf(i));
        }
    }

    public void setButtonBackgroundColor(int i) {
        if (this.mAdView != null) {
            aj.a(r.h, this.mAdView, this.mLoader, "setButtonBackgroundColor", new Class[]{Integer.TYPE}, Integer.valueOf(i));
        }
    }

    public void setIsShowDialog(boolean z) {
        if (this.mAdView != null) {
            aj.a(r.h, this.mAdView, this.mLoader, "setIsShowDialog", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }
}
