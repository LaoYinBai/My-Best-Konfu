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
public class BDMarketingLabel extends LinearLayout {
    private View mAdView;
    private Context mContext;
    private ClassLoader mLoader;

    public BDMarketingLabel(Context context) {
        super(context);
        this.mContext = context;
        initView(context);
    }

    public BDMarketingLabel(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BDMarketingLabel(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView(context);
    }

    public void setAdData(NativeResponse nativeResponse) {
        if (this.mAdView != null) {
            aj.a(r.g, this.mAdView, this.mLoader, "setAdData", new Class[]{Object.class}, nativeResponse);
        }
    }

    private void initView(Context context) {
        this.mContext = context;
        Object[] objArr = {this.mContext};
        this.mLoader = ba.a(this.mContext);
        this.mAdView = (View) aj.a(r.g, this.mLoader, (Class<?>[]) new Class[]{Context.class}, objArr);
        if (this.mAdView != null) {
            addView(this.mAdView, new RelativeLayout.LayoutParams(-2, -2));
        }
    }

    public void setLabelFontSizeSp(int i) {
        if (this.mAdView != null) {
            aj.a(r.g, this.mAdView, this.mLoader, "setLabelFontSizeSp", new Class[]{Integer.TYPE}, Integer.valueOf(i));
        }
    }

    public void setLabelFontTypeFace(Typeface typeface) {
        if (this.mAdView != null) {
            aj.a(r.g, this.mAdView, this.mLoader, "setLabelFontTypeFace", new Class[]{Typeface.class}, typeface);
        }
    }
}
