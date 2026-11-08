package com.baidu.mobads.sdk.internal;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import com.baidu.mobads.sdk.api.IAdInterListener;
import com.baidu.mobads.sdk.api.IOAdEvent;
import com.baidu.mobads.sdk.api.RewardVideoAd;
import com.baidu.mobads.sdk.api.ScreenVideoAdListener;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import java.util.List;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class cx extends ar {
    private boolean o;
    private int p;
    private String q;
    private ScreenVideoAdListener r;
    private final String s;
    private int t;
    private int u;
    private String v;
    private String w;
    private a x;

    public cx(Context context, String str, boolean z) {
        this(context, str, z, IAdInterListener.AdProdType.PRODUCT_REWARDVIDEO);
    }

    public cx(Context context, String str, boolean z, String str2) {
        super(context);
        this.p = 3;
        this.q = str;
        this.o = z;
        this.s = str2;
    }

    public void f() {
        if (this.j != null) {
            this.j.showAd();
        }
    }

    public void a(int i) {
        this.p = i;
    }

    public void a(String str) {
        this.v = str;
    }

    public void h(String str) {
        this.w = str;
    }

    public void a(ScreenVideoAdListener screenVideoAdListener) {
        this.r = screenVideoAdListener;
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    public void b_() {
        if (this.j == null) {
            this.k = false;
        } else {
            this.k = true;
            this.j.loadAd(k(), l());
        }
    }

    public boolean g() {
        if (this.j != null) {
            return this.j.isAdReady();
        }
        return false;
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void q() {
        if (this.r != null) {
            this.r.onAdShow();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void a(IOAdEvent iOAdEvent) {
        List<a> listA;
        if (iOAdEvent != null && (listA = b.a(iOAdEvent.getMessage()).a()) != null && listA.size() > 0) {
            this.x = listA.get(0);
        }
        if (this.r != null) {
            this.r.onAdLoaded();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void h(IOAdEvent iOAdEvent) {
        if (this.r != null) {
            this.r.onAdClick();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void g(IOAdEvent iOAdEvent) {
        super.g(iOAdEvent);
        float fFloatValue = (iOAdEvent == null || iOAdEvent.getData() == null) ? 0.0f : ((Float) iOAdEvent.getData().get("play_scale")).floatValue();
        if (this.r != null) {
            this.r.onAdClose(fFloatValue);
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void e(String str) {
        if (this.r != null) {
            this.r.onAdSkip(Float.parseFloat(str));
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void d_() {
        if (this.r != null) {
            this.r.onVideoDownloadSuccess();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void c() {
        if (this.r != null) {
            this.r.onVideoDownloadFailed();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void a(String str, int i) {
        super.a(str, i);
        if (this.r != null) {
            this.r.onAdFailed(str);
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void a(int i, String str) {
        super.a(i, str);
        if (this.r != null) {
            this.r.onAdFailed(str);
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void s() {
        if (this.r != null) {
            this.r.playCompletion();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void b(boolean z) {
        if (this.r == null || !(this.r instanceof RewardVideoAd.RewardVideoAdListener)) {
            return;
        }
        ((RewardVideoAd.RewardVideoAdListener) this.r).onRewardVerify(z);
    }

    public String h() {
        return this.x != null ? this.x.y() : "";
    }

    public void a(boolean z, String str) {
        if (this.x != null) {
            a(this.x.F(), z, str);
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    public JSONObject k() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(IAdInterListener.AdReqParam.PROD, this.s);
            this.j.createProdHandler(jSONObject2);
            n();
            jSONObject.put(IAdInterListener.AdReqParam.PROD, this.s);
            jSONObject.put(IAdInterListener.AdReqParam.APID, this.q);
            jSONObject.put(IAdInterListener.AdReqParam.FET, "ANTI,MSSP,VIDEO,NMON");
            jSONObject.put(IAdInterListener.AdReqParam.AD_COUNT, "1");
            jSONObject.put(IAdInterListener.AdReqParam.AD_TYPE, "10");
            if (!TextUtils.isEmpty(this.n)) {
                jSONObject.put("appid", this.n);
            }
            Rect rectA = ak.a(this.g);
            this.t = rectA.width();
            this.u = rectA.height();
            if (this.g.getResources().getConfiguration().orientation == 2) {
                this.t = rectA.height();
                this.u = rectA.width();
            }
            jSONObject.put(IAdInterListener.AdReqParam.WIDTH, "" + this.t);
            jSONObject.put(IAdInterListener.AdReqParam.HEIGHT, "" + this.u);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    public JSONObject l() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("timeout", 8000);
            jSONObject.put("useSurfaceView", this.o);
            jSONObject.put("downloadConfirmPolicy", this.p);
            jSONObject.put("userid", this.v);
            jSONObject.put(AccountMonitorConstants.CommonParameter.EXTRA, this.w);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }
}
