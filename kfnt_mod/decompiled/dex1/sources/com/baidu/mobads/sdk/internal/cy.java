package com.baidu.mobads.sdk.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.baidu.mobads.sdk.api.IAdInterListener;
import com.baidu.mobads.sdk.api.IOAdEvent;
import com.baidu.mobads.sdk.api.RequestParameters;
import com.baidu.mobads.sdk.api.SplashAd;
import com.baidu.mobads.sdk.api.SplashAdListener;
import com.baidu.mobads.sdk.api.SplashInteractionListener;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class cy extends ar {
    private static int D;
    private static cy I;
    private boolean A;
    private boolean B;
    private int C;
    private SplashAdListener E;
    private SplashAd.OnFinishListener F;
    private SplashAd.SplashFocusAdListener G;
    private RequestParameters H;
    private a J;
    private SplashAd.SplashAdDownloadDialogListener K;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;
    private RelativeLayout s;
    private String t;
    private int u;
    private int v;
    private int w;
    private int x;
    private boolean y;
    private boolean z;

    public cy(Context context, RelativeLayout relativeLayout, String str, int i, int i2, int i3, int i4, boolean z, boolean z2, boolean z3, boolean z4) {
        super(context);
        this.C = 60;
        this.p = false;
        this.q = false;
        this.r = false;
        this.s = relativeLayout;
        this.t = str;
        this.u = i;
        this.v = i2;
        this.w = i3;
        this.x = i4;
        this.y = z;
        this.z = z2;
        this.A = z4;
        this.B = z3;
    }

    public void a(int i) {
        this.C = i;
    }

    public void a(SplashAdListener splashAdListener) {
        this.E = splashAdListener;
    }

    public void a(SplashAd.SplashFocusAdListener splashFocusAdListener) {
        this.G = splashFocusAdListener;
    }

    public void a(RequestParameters requestParameters) {
        this.H = requestParameters;
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

    public static void c(int i) {
        D = i;
    }

    public void f() {
        if (this.o || this.j == null) {
            return;
        }
        this.j.showAd();
    }

    public void a(@NonNull Intent intent, @Nullable SplashAd.OnFinishListener onFinishListener) {
        try {
            if (!this.p && intent != null && this.j != null && !this.r && (this.g instanceof Activity)) {
                this.F = onFinishListener;
                JSONObject jSONObject = new JSONObject();
                HashMap map = new HashMap();
                try {
                    jSONObject.putOpt("event_type", "splash_focus_start_activity");
                    map.put("splash_focus_user_intent", intent);
                } catch (JSONException e) {
                    bb.a().a(e);
                }
                this.j.removeAllListeners();
                this.j.addEventListener(r.X, this.i);
                this.j.addEventListener(r.L, this.i);
                this.j.addEventListener(r.F, this.i);
                this.j.addEventListener(r.U, this.i);
                this.j.addEventListener(r.V, this.i);
                a(jSONObject, map);
                this.E = null;
                I = this;
                am.a().a(new cz(this), 3L, TimeUnit.SECONDS);
                return;
            }
            b(intent, onFinishListener);
        } catch (Throwable th) {
            th.printStackTrace();
            b(intent, onFinishListener);
        }
    }

    private void b(Intent intent, SplashAd.OnFinishListener onFinishListener) {
        if (this.g == null || intent == null) {
            return;
        }
        if (!(this.g instanceof Activity)) {
            intent.addFlags(268435456);
        }
        this.g.startActivity(intent);
        if (onFinishListener != null) {
            onFinishListener.onFinishActivity();
        } else if (this.g instanceof Activity) {
            ((Activity) this.g).finish();
        }
    }

    public static void a(Activity activity, JSONObject jSONObject, SplashAd.SplashFocusAdListener splashFocusAdListener) {
        if (I != null) {
            I.a(splashFocusAdListener);
            HashMap map = new HashMap();
            JSONObject jSONObject2 = new JSONObject();
            try {
                try {
                    jSONObject2.putOpt("event_type", "splash_focus_register_transition");
                    jSONObject2.putOpt("splash_focus_params", jSONObject);
                    map.put("splash_focus_activity", activity);
                    I.a(jSONObject2, map);
                } catch (JSONException e) {
                    bb.a().c(e);
                } catch (Throwable th) {
                    bb.a().c(th);
                }
            } finally {
                I = null;
            }
        }
    }

    public a g() {
        return this.J;
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void e(IOAdEvent iOAdEvent) {
        Map<String, Object> data = iOAdEvent.getData();
        if (this.G == null || data == null || !data.containsKey("splash_show_reason")) {
            return;
        }
        this.G.onAdIconShow();
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void h(IOAdEvent iOAdEvent) {
        this.p = true;
        if (this.E != null && (this.E instanceof SplashInteractionListener)) {
            ((SplashInteractionListener) this.E).onAdClick();
        }
        if (this.G != null) {
            this.G.onAdClick();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void a(IOAdEvent iOAdEvent) {
        List<a> listA;
        if (iOAdEvent != null && (listA = b.a(iOAdEvent.getMessage()).a()) != null && listA.size() > 0) {
            this.J = listA.get(0);
        }
        if (this.E != null) {
            this.E.onADLoaded();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void q() {
        if (this.E == null || !(this.E instanceof SplashInteractionListener)) {
            return;
        }
        ((SplashInteractionListener) this.E).onAdPresent();
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void g(IOAdEvent iOAdEvent) {
        Map<String, Object> data = iOAdEvent.getData();
        if (this.G != null && data != null && data.containsKey("splash_close_reason")) {
            this.G.onAdClose();
            return;
        }
        if (this.q) {
            return;
        }
        super.g(iOAdEvent);
        if (this.E != null && (this.E instanceof SplashInteractionListener)) {
            ((SplashInteractionListener) this.E).onAdDismissed();
        }
        this.q = true;
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void a(int i, String str) {
        this.r = true;
        if (this.E != null) {
            this.E.onAdFailed("广告无填充");
        }
        super.a(i, str);
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void a(String str, int i) {
        this.r = true;
        if (this.E != null) {
            this.E.onAdFailed(str);
        }
        super.a(str, i);
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void e() {
        if (this.E != null && (this.E instanceof SplashInteractionListener)) {
            ((SplashInteractionListener) this.E).onLpClosed();
        }
        if (this.G != null) {
            this.G.onLpClosed();
        }
        super.e();
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void d_() {
        if (this.E != null && (this.E instanceof SplashInteractionListener)) {
            ((SplashInteractionListener) this.E).onAdCacheSuccess();
        }
        super.d_();
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void c() {
        if (this.E != null && (this.E instanceof SplashInteractionListener)) {
            ((SplashInteractionListener) this.E).onAdCacheFailed();
        }
        super.c();
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void t() {
        if (this.F != null) {
            this.F.onFinishActivity();
            this.F = null;
        } else if (this.g instanceof Activity) {
            ((Activity) this.g).finish();
        }
        this.g = null;
        this.s = null;
        super.t();
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void a(String str, boolean z) {
        if (this.K == null || !(this.K instanceof SplashAd.SplashAdDownloadDialogListener)) {
            return;
        }
        if (z) {
            this.K.onADPermissionShow();
        } else {
            this.K.onADPermissionClose();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void b(String str, boolean z) {
        if (this.K == null || !(this.K instanceof SplashAd.SplashAdDownloadDialogListener)) {
            return;
        }
        if (z) {
            this.K.adDownloadWindowShow();
        } else {
            this.K.adDownloadWindowClose();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void u() {
        if (this.K == null || !(this.K instanceof SplashAd.SplashAdDownloadDialogListener)) {
            return;
        }
        this.K.onADPrivacyLpClose();
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void d(String str) {
        if (this.K == null || !(this.K instanceof SplashAd.SplashAdDownloadDialogListener)) {
            return;
        }
        this.K.onADPrivacyLpShow();
    }

    public void a(SplashAd.SplashAdDownloadDialogListener splashAdDownloadDialogListener) {
        this.K = splashAdDownloadDialogListener;
    }

    private String a(String str) {
        if (this.H == null || TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            Map<String, String> extras = this.H.getExtras();
            if (extras != null) {
                return extras.get(str);
            }
            return null;
        } catch (Throwable th) {
            this.h.d(ar.f3124a, th);
            return null;
        }
    }

    public void a(boolean z, String str) {
        if (this.J != null) {
            a(this.J.F(), z, str);
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    public JSONObject k() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(IAdInterListener.AdReqParam.PROD, IAdInterListener.AdProdType.PRODUCT_SPLASH);
            this.j.createProdHandler(jSONObject2);
            this.j.setAdContainer(this.s);
            n();
            jSONObject.put(IAdInterListener.AdReqParam.PROD, IAdInterListener.AdProdType.PRODUCT_SPLASH);
            jSONObject.put(IAdInterListener.AdReqParam.APID, this.t);
            jSONObject.put(IAdInterListener.AdReqParam.FET, "ANTI,HTML,MSSP,VIDEO,RSPLASHHTML");
            jSONObject.put(IAdInterListener.AdReqParam.AD_COUNT, "1");
            jSONObject.put(IAdInterListener.AdReqParam.AD_TYPE, "26");
            jSONObject.put(IAdInterListener.AdReqParam.MIME_TYPE, "video/mp4,image/jpg,image/gif,image/png");
            jSONObject.put(IAdInterListener.AdReqParam.WIDTH, "" + this.u);
            jSONObject.put(IAdInterListener.AdReqParam.HEIGHT, "" + this.v);
            jSONObject.put("msa", 7);
            if (!TextUtils.isEmpty(this.n)) {
                jSONObject.put("appid", this.n);
            }
            String strA = a(SplashAd.KEY_USE_ADAPTIVE_AD);
            if (!TextUtils.isEmpty(strA)) {
                jSONObject.put("adtv", Boolean.parseBoolean(strA) ? "1" : OnekeyLoginConstants.CU_RESULT_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    public JSONObject l() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("timeout", this.x);
            jSONObject.put("splashTipStyle", this.w);
            jSONObject.put("bitmapDisplayMode", D);
            jSONObject.put("countDownNew", "true");
            jSONObject.put("Display_Down_Info", "" + this.y);
            jSONObject.put("popDialogIfDl", "" + this.z);
            jSONObject.put("limitRegionClick", "" + this.A);
            jSONObject.put("displayClickButton", "" + this.B);
            jSONObject.put("needCache", true);
            jSONObject.put("onlyLoadAd", this.o);
            jSONObject.put("cacheVideoOnlyWifi", true);
            jSONObject.put("shakeLogoSize", this.C);
            if (this.H != null) {
                a(this.H.getExtras());
            }
            return j.a(jSONObject, b(this.l));
        } catch (Exception e) {
            e.printStackTrace();
            return jSONObject;
        }
    }
}
