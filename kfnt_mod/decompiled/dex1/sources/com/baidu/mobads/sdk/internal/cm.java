package com.baidu.mobads.sdk.internal;

import android.content.Context;
import android.text.TextUtils;
import android.widget.RelativeLayout;
import com.baidu.mobads.sdk.api.ExpressInterstitialAd;
import com.baidu.mobads.sdk.api.ExpressInterstitialListener;
import com.baidu.mobads.sdk.api.IAdInterListener;
import com.baidu.mobads.sdk.api.IOAdEvent;
import com.baidu.mobads.sdk.api.RequestParameters;
import com.baidu.mobads.sdk.api.SplashAd;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class cm extends ar {
    RelativeLayout o;
    public boolean p;
    private int q;
    private String r;
    private String s;
    private int t;
    private int u;
    private ExpressInterstitialListener v;
    private ExpressInterstitialAd.InterAdDownloadWindowListener w;
    private a x;
    private boolean y;

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void c(String str) {
    }

    public cm(Context context, RelativeLayout relativeLayout, String str) {
        super(context);
        this.q = 8000;
        this.r = IAdInterListener.AdProdType.PRODUCT_INTERSTITIAL;
        this.t = 600;
        this.u = 500;
        this.y = false;
        this.o = relativeLayout;
        this.s = str;
    }

    public void a(ExpressInterstitialAd.InterAdDownloadWindowListener interAdDownloadWindowListener) {
        this.w = interAdDownloadWindowListener;
    }

    public void a(int i) {
        this.q = i;
    }

    public void a(ExpressInterstitialListener expressInterstitialListener) {
        this.v = expressInterstitialListener;
    }

    public String f() {
        return this.r;
    }

    public void c(boolean z) {
        this.y = z;
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    public void b_() {
        if (this.j == null) {
            this.k = false;
            return;
        }
        this.k = true;
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put(IAdInterListener.AdReqParam.PROD, this.r);
            jSONObject3.put("isNewInterstitial", true);
            this.j.createProdHandler(jSONObject3);
            this.j.setAdContainer(this.o);
            n();
            jSONObject.put(IAdInterListener.AdReqParam.PROD, this.r);
            jSONObject.put(IAdInterListener.AdReqParam.APID, this.s);
            jSONObject.put(IAdInterListener.AdReqParam.AD_COUNT, "1");
            if (!TextUtils.isEmpty(this.n)) {
                jSONObject.put("appid", this.n);
            }
            if (by.a().b()) {
                jSONObject.put(IAdInterListener.AdReqParam.FET, "ANTI,MSSP,VIDEO,NMON,HTML");
            } else {
                jSONObject.put(IAdInterListener.AdReqParam.FET, "ANTI,MSSP,VIDEO,NMON,HTML,CLICK2VIDEO");
            }
            jSONObject.put(IAdInterListener.AdReqParam.AD_TYPE, "10");
            jSONObject.put(IAdInterListener.AdReqParam.WIDTH, "" + ak.b(this.g));
            jSONObject.put(IAdInterListener.AdReqParam.HEIGHT, "" + ak.c(this.g));
            jSONObject2.put("onlyLoadAd", this.p);
            jSONObject2.put("isNewInterstitial", true);
            jSONObject2.put(SplashAd.KEY_POPDIALOG_DOWNLOAD, this.y);
            jSONObject.put("msa", 23);
            jSONObject2.put("timeout", this.q);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.j.loadAd(jSONObject, jSONObject2);
    }

    public void a(RequestParameters requestParameters) {
        int width = requestParameters.getWidth();
        int height = requestParameters.getHeight();
        if (width > 0 && height > 0) {
            this.t = width;
            this.u = height;
        }
        a(requestParameters.getExtras());
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    public void a(String str, int i) {
        if (this.v != null) {
            this.v.onAdFailed(i, str);
        }
        super.a(str, i);
    }

    public void g() {
        if (this.j != null) {
            this.j.showAd();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void a(int i, String str) {
        if (this.v != null) {
            this.v.onNoAd(i, str);
        }
        super.a(i, str);
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void e(IOAdEvent iOAdEvent) {
        if (this.v != null) {
            this.v.onADExposed();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void f(IOAdEvent iOAdEvent) {
        if (this.v != null) {
            this.v.onADExposureFailed();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void h(IOAdEvent iOAdEvent) {
        if (this.v != null) {
            this.v.onAdClick();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void e() {
        if (this.v != null) {
            this.v.onLpClosed();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void d_() {
        if (this.v != null) {
            this.v.onVideoDownloadSuccess();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void c() {
        if (this.v != null) {
            this.v.onVideoDownloadFailed();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void g(IOAdEvent iOAdEvent) {
        super.g(iOAdEvent);
        if (this.v != null) {
            this.v.onAdClose();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void a(String str, boolean z) {
        if (this.w != null) {
            if (z) {
                this.w.onADPermissionShow();
            } else {
                this.w.onADPermissionClose();
            }
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void b(String str, boolean z) {
        if (this.w != null) {
            if (z) {
                this.w.adDownloadWindowShow();
            } else {
                this.w.adDownloadWindowClose();
            }
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void u() {
        if (this.w != null) {
            this.w.onADPrivacyClose();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void d(String str) {
        if (this.w != null) {
            this.w.onADPrivacyClick();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void a(IOAdEvent iOAdEvent) {
        List<a> listA;
        if (iOAdEvent != null && (listA = b.a(iOAdEvent.getMessage()).a()) != null && listA.size() > 0) {
            this.x = listA.get(0);
        }
        if (this.v != null) {
            this.v.onADLoaded();
        }
    }

    public a h() {
        return this.x;
    }

    public void a(boolean z, String str) {
        if (this.x != null) {
            a(this.x.F(), z, str);
        }
    }
}
