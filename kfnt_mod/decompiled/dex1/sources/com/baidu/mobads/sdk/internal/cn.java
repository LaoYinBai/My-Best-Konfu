package com.baidu.mobads.sdk.internal;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.mobads.sdk.api.BaiduNativeAdPlacement;
import com.baidu.mobads.sdk.api.BaiduNativeH5AdView;
import com.baidu.mobads.sdk.api.IAdInterListener;
import com.baidu.mobads.sdk.api.IOAdEvent;
import com.baidu.mobads.sdk.api.RequestParameters;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class cn extends ar {
    private BaiduNativeAdPlacement A;
    a o;
    private String p;
    private String q;
    private int r;
    private int s;
    private BaiduNativeH5AdView t;
    private int u;
    private int v;
    private int w;
    private BaiduNativeH5AdView.BaiduNativeH5EventListner x;
    private boolean y;
    private boolean z;

    public cn(Context context, String str, BaiduNativeH5AdView baiduNativeH5AdView) {
        super(context);
        this.u = 1;
        this.v = 1;
        this.w = 1;
        this.y = false;
        this.o = null;
        this.z = false;
        this.t = baiduNativeH5AdView;
        this.q = baiduNativeH5AdView.getAdPlacement().getApId();
        this.p = str;
        this.f = baiduNativeH5AdView.getAdPlacement().getAdView();
    }

    public void a(BaiduNativeH5AdView.BaiduNativeH5EventListner baiduNativeH5EventListner) {
        this.x = baiduNativeH5EventListner;
    }

    public void a(RequestParameters requestParameters) {
        int width = requestParameters.getWidth();
        int height = requestParameters.getHeight();
        if (width <= 0 || height <= 0) {
            return;
        }
        this.r = width;
        this.s = height;
    }

    public boolean f() {
        return this.y;
    }

    public void c(boolean z) {
        this.y = z;
    }

    public boolean g() {
        return this.z;
    }

    public void a(int i) {
        this.u = i;
    }

    public void c(int i) {
        this.v = i;
    }

    public void a(BaiduNativeAdPlacement baiduNativeAdPlacement) {
        this.A = baiduNativeAdPlacement;
    }

    public void d(int i) {
        this.w = i;
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    public void b_() throws JSONException {
        if (this.j == null) {
            this.k = false;
            return;
        }
        this.k = true;
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put(IAdInterListener.AdReqParam.PROD, this.p);
            this.j.createProdHandler(jSONObject3);
            this.j.setAdContainer(this.f);
            n();
            jSONObject.put(IAdInterListener.AdReqParam.PROD, this.p);
            jSONObject.put(IAdInterListener.AdReqParam.APID, this.q);
            jSONObject.put(IAdInterListener.AdReqParam.AD_COUNT, "1");
            if (!TextUtils.isEmpty(this.n)) {
                jSONObject.put("appid", this.n);
            }
            jSONObject.put(IAdInterListener.AdReqParam.AD_TYPE, "2");
            jSONObject.put(IAdInterListener.AdReqParam.WIDTH, "" + this.r);
            jSONObject.put(IAdInterListener.AdReqParam.HEIGHT, "" + this.s);
            jSONObject = j.a(jSONObject, b(this.l));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.j.loadAd(jSONObject, jSONObject2);
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void q() {
        this.y = true;
        this.A.setRequestStarted(false);
        if (this.x != null) {
            this.x.onAdShow();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void a(String str, int i) {
        this.A.setRequestStarted(false);
        if (this.x != null) {
            this.x.onAdFail(str);
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void h(IOAdEvent iOAdEvent) {
        this.A.setClicked(true);
        if (this.x != null) {
            this.x.onAdClick();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void a(IOAdEvent iOAdEvent) {
        this.z = true;
        this.t.getAdPlacement().setAdResponse(b.a(iOAdEvent.getMessage()).a().get(0));
        if (this.x != null) {
            this.x.onAdDataLoaded();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void a(int i, String str) {
        r();
        this.A.setRequestStarted(false);
        if (this.x != null) {
            this.x.onAdFail(str);
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void e(IOAdEvent iOAdEvent) {
        this.A.setWinSended(true);
    }
}
