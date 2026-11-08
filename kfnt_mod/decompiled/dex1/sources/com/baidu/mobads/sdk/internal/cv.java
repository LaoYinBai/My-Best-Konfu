package com.baidu.mobads.sdk.internal;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.RelativeLayout;
import com.baidu.mobads.sdk.api.AdSize;
import com.baidu.mobads.sdk.api.IAdInterListener;
import com.baidu.mobads.sdk.api.IOAdEvent;
import com.baidu.mobads.sdk.api.InterstitialAd;
import com.baidu.mobads.sdk.api.InterstitialAdListener;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class cv extends ar implements t {
    private static final String o = "preload_end";
    private AdSize p;
    private String q;
    private boolean r;
    private boolean s;
    private RelativeLayout t;
    private InterstitialAd u;
    private InterstitialAdListener v;

    public void f() {
    }

    public cv(Context context, RelativeLayout relativeLayout, InterstitialAd interstitialAd, String str) {
        this(context, relativeLayout, interstitialAd, AdSize.InterstitialGame, str);
    }

    public cv(Context context, RelativeLayout relativeLayout, InterstitialAd interstitialAd, AdSize adSize, String str) {
        super(context);
        this.r = false;
        this.s = false;
        this.u = interstitialAd;
        this.t = relativeLayout;
        this.p = adSize;
        this.q = str;
    }

    @Override // com.baidu.mobads.sdk.internal.t
    public void a_() throws JSONException {
        b_();
    }

    @Override // com.baidu.mobads.sdk.internal.t
    public void a(String str) {
        super.f(str);
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
            jSONObject3.put(IAdInterListener.AdReqParam.PROD, IAdInterListener.AdProdType.PRODUCT_INTERSTITIAL);
            this.j.createProdHandler(jSONObject3);
            this.j.setAdContainer(this.t);
            n();
            jSONObject.put(IAdInterListener.AdReqParam.PROD, IAdInterListener.AdProdType.PRODUCT_INTERSTITIAL);
            jSONObject.put(IAdInterListener.AdReqParam.APID, this.q);
            jSONObject.put(IAdInterListener.AdReqParam.AD_TYPE, OnekeyLoginConstants.UNICOM_TYPE);
            jSONObject.put(IAdInterListener.AdReqParam.WIDTH, OnekeyLoginConstants.CU_RESULT_SUCCESS);
            jSONObject.put(IAdInterListener.AdReqParam.HEIGHT, OnekeyLoginConstants.CU_RESULT_SUCCESS);
            if (!TextUtils.isEmpty(this.n)) {
                jSONObject.put("appid", this.n);
            }
            if (AdSize.InterstitialGame.equals(this.p)) {
                jSONObject2.put("ABILITY", "PAUSE,");
            }
            jSONObject2.put("APT", this.p.getValue());
            jSONObject2.put("onlyLoadAd", true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.j.loadAd(jSONObject, jSONObject2);
    }

    @Override // com.baidu.mobads.sdk.internal.t
    public void a() {
        if (this.j != null) {
            this.j.showAd();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.t
    public void a(int i, int i2) throws JSONException {
        if (this.j == null || this.r || this.s) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(IAdInterListener.AdReqParam.WIDTH, i);
            jSONObject.put(IAdInterListener.AdReqParam.HEIGHT, i2);
        } catch (JSONException unused) {
        }
        a(jSONObject);
        this.j.showAd();
    }

    @Deprecated
    public void a(Activity activity) {
        c_();
    }

    @Override // com.baidu.mobads.sdk.internal.t
    public void c_() {
        if (this.r && !this.s) {
            this.s = true;
            this.r = false;
            if (this.j != null) {
                this.j.showAd();
                return;
            }
            return;
        }
        if (this.s) {
            this.h.b("interstitial ad is showing now");
        } else {
            if (this.r) {
                return;
            }
            this.h.b("interstitial ad is not ready");
        }
    }

    @Override // com.baidu.mobads.sdk.internal.t
    public void a(RelativeLayout relativeLayout) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        HashMap map = new HashMap();
        try {
            jSONObject.putOpt("event_type", "interstitial_set_video_parent");
            map.put("interstitial_video_parent", relativeLayout);
        } catch (JSONException e) {
            bb.a().a(e);
        }
        a(jSONObject, map);
        c_();
    }

    @Override // com.baidu.mobads.sdk.internal.t
    public void a(InterstitialAdListener interstitialAdListener) {
        this.v = interstitialAdListener;
    }

    @Override // com.baidu.mobads.sdk.internal.t
    public boolean b() {
        return this.r;
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void a(IOAdEvent iOAdEvent) {
        if (o.equals(iOAdEvent.getMessage())) {
            this.r = true;
            if (this.v != null) {
                this.v.onAdReady();
            }
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void q() {
        if (this.v != null) {
            this.v.onAdPresent();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void h(IOAdEvent iOAdEvent) {
        if (this.v != null) {
            this.v.onAdClick(this.u);
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void g(IOAdEvent iOAdEvent) {
        this.s = false;
        if (this.v != null) {
            this.v.onAdDismissed();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void a(String str, int i) {
        if (this.v != null) {
            this.v.onAdFailed(str);
        }
    }
}
