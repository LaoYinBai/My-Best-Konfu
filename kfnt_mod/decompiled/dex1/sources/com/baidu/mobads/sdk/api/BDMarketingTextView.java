package com.baidu.mobads.sdk.api;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.baidu.mobads.sdk.internal.aj;
import com.baidu.mobads.sdk.internal.ba;
import com.baidu.mobads.sdk.internal.r;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class BDMarketingTextView extends RelativeLayout {
    private View mAdView;
    private Context mContext;
    private ClassLoader mLoader;

    public BDMarketingTextView(Context context) {
        super(context);
        this.mContext = context;
        initView(context);
    }

    public BDMarketingTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BDMarketingTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView(context);
    }

    public void setAdData(NativeResponse nativeResponse, String str) {
        if (this.mAdView != null) {
            aj.a(r.f, this.mAdView, this.mLoader, "setAdData", new Class[]{Object.class, String.class}, nativeResponse, str);
        }
    }

    private void initView(Context context) {
        try {
            this.mContext = context;
            Object[] objArr = {this.mContext};
            this.mLoader = ba.a(this.mContext);
            this.mAdView = (View) aj.a(r.f, this.mLoader, (Class<?>[]) new Class[]{Context.class}, objArr);
            if (this.mAdView != null) {
                addView(this.mAdView, new RelativeLayout.LayoutParams(-2, -2));
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setTextFontSizeSp(int i) {
        if (this.mAdView != null) {
            aj.a(r.f, this.mAdView, this.mLoader, "setTextFontSizeSp", new Class[]{Integer.TYPE}, Integer.valueOf(i));
        }
    }

    public void setTextFontTypeFace(Typeface typeface) {
        if (this.mAdView != null) {
            aj.a(r.f, this.mAdView, this.mLoader, "setTextFontTypeFace", new Class[]{Typeface.class}, typeface);
        }
    }

    public void setTextFontColor(int i) {
        if (this.mAdView != null) {
            aj.a(r.f, this.mAdView, this.mLoader, "setTextFontColor", new Class[]{Integer.TYPE}, Integer.valueOf(i));
        }
    }

    public void setTextMaxLines(int i) {
        if (this.mAdView != null) {
            aj.a(r.f, this.mAdView, this.mLoader, "setTextMaxLines", new Class[]{Integer.TYPE}, Integer.valueOf(i));
        }
    }

    public void setEllipsize(TextUtils.TruncateAt truncateAt) {
        if (this.mAdView != null) {
            aj.a(r.f, this.mAdView, this.mLoader, "setEllipsize", new Class[]{TextUtils.TruncateAt.class}, truncateAt);
        }
    }

    public void setLineSpacingExtra(int i) {
        if (this.mAdView != null) {
            aj.a(r.f, this.mAdView, this.mLoader, "setLineSpacingExtra", new Class[]{Integer.TYPE}, Integer.valueOf(i));
        }
    }

    public void setLabelVisibility(int i) {
        if (this.mAdView != null) {
            aj.a(r.f, this.mAdView, this.mLoader, "setLabelVisibility", new Class[]{Integer.TYPE}, Integer.valueOf(i));
        }
    }

    public void setLabelFontSizeSp(int i) {
        if (this.mAdView != null) {
            aj.a(r.f, this.mAdView, this.mLoader, "setLabelFontSizeSp", new Class[]{Integer.TYPE}, Integer.valueOf(i));
        }
    }

    public void setLabelFontTypeFace(Typeface typeface) {
        if (this.mAdView != null) {
            aj.a(r.f, this.mAdView, this.mLoader, "setLabelFontTypeFace", new Class[]{Typeface.class}, typeface);
        }
    }
}
