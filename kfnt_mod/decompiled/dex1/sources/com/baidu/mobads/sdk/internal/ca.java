package com.baidu.mobads.sdk.internal;

import android.content.Context;
import android.text.TextUtils;
import android.widget.RelativeLayout;
import com.baidu.mobads.sdk.api.AdView;
import com.baidu.mobads.sdk.api.AdViewListener;
import com.baidu.mobads.sdk.api.IAdInterListener;
import com.baidu.mobads.sdk.api.IOAdEvent;
import com.baidu.mobads.sdk.api.RequestParameters;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ca extends ar {
    private RelativeLayout o;
    private String p;
    private boolean q;
    private AdViewListener r;
    private int s;
    private int t;
    private AdView u;
    private RequestParameters v;

    public ca(AdView adView, Context context, RelativeLayout relativeLayout, String str, boolean z) {
        super(context);
        this.u = adView;
        this.o = relativeLayout;
        this.p = str;
        this.q = z;
    }

    public void a(int i) {
        this.s = i;
    }

    public void c(int i) {
        this.t = i;
    }

    public void a(AdViewListener adViewListener) {
        this.r = adViewListener;
    }

    public void a(RequestParameters requestParameters) {
        this.v = requestParameters;
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
            jSONObject3.put(IAdInterListener.AdReqParam.PROD, IAdInterListener.AdProdType.PRODUCT_BANNER);
            this.j.createProdHandler(jSONObject3);
            this.j.setAdContainer(this.o);
            n();
            jSONObject.put(IAdInterListener.AdReqParam.PROD, IAdInterListener.AdProdType.PRODUCT_BANNER);
            jSONObject.put(IAdInterListener.AdReqParam.APID, this.p);
            jSONObject.put(IAdInterListener.AdReqParam.AD_TYPE, OnekeyLoginConstants.UNICOM_TYPE);
            jSONObject.put("ABILITY", "BANNER_CLOSE,PAUSE,UNLIMITED_BANNER_SIZE,");
            jSONObject.put("AP", this.q);
            jSONObject.put(IAdInterListener.AdReqParam.WIDTH, "" + this.s);
            jSONObject.put(IAdInterListener.AdReqParam.HEIGHT, "" + this.t);
            if (!TextUtils.isEmpty(this.n)) {
                jSONObject.put("appid", this.n);
            }
            if (this.v != null) {
                a(this.v.getExtras());
            }
            jSONObject2 = b(this.l);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.j.loadAd(jSONObject, jSONObject2);
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void h(IOAdEvent iOAdEvent) {
        if (this.r != null) {
            this.r.onAdClick(new JSONObject());
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void a(IOAdEvent iOAdEvent) {
        if (this.r != null) {
            this.r.onAdReady(this.u);
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void q() {
        if (this.r != null) {
            this.r.onAdSwitch();
            this.r.onAdShow(new JSONObject());
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void g(IOAdEvent iOAdEvent) {
        super.g(iOAdEvent);
        if (this.r != null) {
            this.r.onAdClose(new JSONObject());
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void a(String str, int i) {
        if (this.r != null) {
            this.r.onAdFailed(str);
        }
    }
}
