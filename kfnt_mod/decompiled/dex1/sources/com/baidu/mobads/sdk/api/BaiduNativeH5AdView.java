package com.baidu.mobads.sdk.api;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.baidu.mobads.sdk.api.IAdInterListener;
import com.baidu.mobads.sdk.api.RequestParameters;
import com.baidu.mobads.sdk.internal.aj;
import com.baidu.mobads.sdk.internal.cn;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class BaiduNativeH5AdView extends RelativeLayout {
    private BaiduNativeAdPlacement mAdPlacement;
    private cn mAdProd;
    private BaiduNativeH5EventListner mAdViewListener;
    private RequestParameters mRequestParameters;

    public interface BaiduNativeH5EventListner {
        void onAdClick();

        void onAdDataLoaded();

        void onAdFail(String str);

        void onAdShow();
    }

    private void cancel() {
    }

    public boolean isAdDataLoaded() {
        if (this.mAdProd != null) {
            return this.mAdProd.g();
        }
        return false;
    }

    @SuppressLint({"NewApi"})
    private void initView(Context context, int i) {
        if (i != 0) {
            setBackgroundResource(i);
        }
    }

    public BaiduNativeH5AdView(Context context, int i) {
        super(context);
        this.mAdViewListener = null;
        initView(context, i);
    }

    public BaiduNativeH5AdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAdViewListener = null;
        initView(context, 0);
    }

    public BaiduNativeH5AdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mAdViewListener = null;
        initView(context, 0);
    }

    public BaiduNativeAdPlacement getAdPlacement() {
        return this.mAdPlacement;
    }

    public void setAdPlacement(BaiduNativeAdPlacement baiduNativeAdPlacement) {
        this.mAdPlacement = baiduNativeAdPlacement;
    }

    public void makeRequest(RequestParameters requestParameters) throws JSONException {
        if (requestParameters == null) {
            requestParameters = new RequestParameters.Builder().build();
        }
        this.mRequestParameters = requestParameters;
        if (this.mAdProd != null) {
            onDetach();
        }
        this.mAdProd = new cn(getContext(), IAdInterListener.AdProdType.PRODUCT_FEEDS, this);
        this.mAdProd.a(requestParameters);
        this.mAdProd.a(this.mAdPlacement);
        this.mAdProd.a(this.mAdPlacement.getSessionId());
        this.mAdProd.c(this.mAdPlacement.getPosistionId());
        this.mAdProd.d(this.mAdPlacement.getSequenceId());
        if (this.mAdViewListener != null) {
            this.mAdProd.a(this.mAdViewListener);
        }
        if (this.mAdPlacement != null) {
            if (this.mAdPlacement.hasValidResponse()) {
                if (this.mAdProd.f()) {
                    return;
                }
            } else {
                this.mAdProd.c(false);
                if (this.mAdPlacement.getRequestStarted()) {
                    return;
                } else {
                    this.mAdPlacement.setRequestStarted(true);
                }
            }
        }
        this.mAdProd.b_();
    }

    public void recordImpression() {
        if (this.mAdPlacement == null || this.mAdPlacement.getResponse() == null || this.mAdPlacement.isWinSended()) {
            return;
        }
        this.mAdProd.a(this, this.mAdPlacement.getResponse().Q());
    }

    private void onDetach() {
        cancel();
        if (this.mAdProd != null) {
            this.mAdProd.d();
        }
    }

    public void setAdPlacementData(Object obj) {
        BaiduNativeAdPlacement baiduNativeAdPlacement = new BaiduNativeAdPlacement();
        baiduNativeAdPlacement.setApId((String) aj.a(obj, "getApId", (Class<?>[]) new Class[0], new Object[0]));
        this.mAdPlacement = baiduNativeAdPlacement;
    }

    public void setEventListener(BaiduNativeH5EventListner baiduNativeH5EventListner) {
        this.mAdViewListener = baiduNativeH5EventListner;
    }
}
