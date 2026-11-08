package com.baidu.mobads.sdk.api;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.baidu.mobads.sdk.internal.aj;
import com.baidu.mobads.sdk.internal.ba;
import com.baidu.mobads.sdk.internal.r;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class FeedNativeView extends RelativeLayout {
    private View mAdView;
    private Context mContext;
    private ClassLoader mLoader;

    public FeedNativeView(Context context) {
        super(context);
        init(context);
    }

    public FeedNativeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public FeedNativeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    public void setAdData(XAdNativeResponse xAdNativeResponse) {
        if (this.mAdView != null) {
            aj.a(r.e, this.mAdView, this.mLoader, "setAdResponse", new Class[]{Object.class}, xAdNativeResponse);
        }
    }

    public void changeViewLayoutParams(Object obj) {
        if (this.mAdView != null) {
            aj.a(r.e, this.mAdView, this.mLoader, "changeLayoutParams", new Class[]{Object.class}, obj);
        }
    }

    private void init(Context context) {
        this.mContext = context;
        Object[] objArr = {this.mContext};
        this.mLoader = ba.a(this.mContext);
        this.mAdView = (View) aj.a(r.e, this.mLoader, (Class<?>[]) new Class[]{Context.class}, objArr);
        if (this.mAdView != null) {
            addView(this.mAdView, new RelativeLayout.LayoutParams(-2, -2));
        }
    }

    public int getAdContainerWidth() {
        if (this.mAdView != null) {
            return ((Integer) aj.a(r.e, this.mAdView, this.mLoader, "getAdContainerWidth", new Class[0], new Object[0])).intValue();
        }
        return 0;
    }

    public int getAdContainerHeight() {
        if (this.mAdView != null) {
            return ((Integer) aj.a(r.e, this.mAdView, this.mLoader, "getAdContainerHeight", new Class[0], new Object[0])).intValue();
        }
        return 0;
    }

    public RelativeLayout getContainerView() {
        if (this.mAdView != null) {
            return (RelativeLayout) aj.a(r.e, this.mAdView, this.mLoader, "getAdView", new Class[0], new Object[0]);
        }
        return null;
    }
}
