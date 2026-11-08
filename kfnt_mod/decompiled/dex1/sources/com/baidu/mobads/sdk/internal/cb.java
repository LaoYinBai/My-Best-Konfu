package com.baidu.mobads.sdk.internal;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.RelativeLayout;
import com.baidu.mobads.sdk.api.CPUWebAdRequestParam;
import com.baidu.mobads.sdk.api.CpuAdView;
import com.baidu.mobads.sdk.api.IAdInterListener;
import com.baidu.mobads.sdk.api.IOAdEvent;
import com.bytedance.sdk.adnet.game.http.cb.NetCallback;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class cb extends ar {
    private HashMap<String, Object> o;
    private int p;
    private RelativeLayout q;
    private CpuAdView.CpuAdViewInternalStatusListener r;

    public cb(Context context, RelativeLayout relativeLayout, String str, int i, CPUWebAdRequestParam cPUWebAdRequestParam) {
        super(context);
        this.n = str;
        this.q = relativeLayout;
        this.p = i;
        this.o = (HashMap) cPUWebAdRequestParam.getParameters();
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    public void b_() {
        if (this.j == null) {
            this.k = false;
            return;
        }
        this.k = true;
        JSONObject jSONObject = new JSONObject();
        if (this.j != null) {
            try {
                jSONObject.put("channel", this.p);
                jSONObject.put(IAdInterListener.AdReqParam.PROD, "cpu_h5");
                jSONObject.put("timeout", NetCallback.ERROR_CODE_REQUEST_SUCCESS);
                if (!TextUtils.isEmpty(this.n)) {
                    jSONObject.put("appid", this.n);
                }
                if (this.q != null) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(IAdInterListener.AdReqParam.PROD, "cpu_h5");
                    this.j.createProdHandler(jSONObject2);
                    this.j.setAdContainer(this.q);
                    n();
                    this.j.addEventListener("Update_fbReader_Setting", new cc(this));
                    this.j.addEventListener("closeInterstitialAd", new cd(this));
                    this.j.addEventListener("feOpenFbReader", new ce(this));
                    JSONObject jSONObjectA = j.a(this.o);
                    jSONObjectA.put("isInitNovelSDK", af.e());
                    this.j.loadAd(jSONObject, jSONObjectA);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Map<String, Object> map) {
        if (map != null) {
            Object obj = map.get("adInnerPageInterval");
            Object obj2 = map.get("adBottomRefreshInterval");
            Object obj3 = map.get("adFrontChapterInterval");
            if (obj == null || obj2 == null || obj3 == null) {
                return;
            }
            af.a(((Integer) obj).intValue());
            af.b(((Integer) obj2).intValue());
            af.c(((Integer) obj3).intValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        af.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2, String str, int i3, int i4, String str2) {
        af.a(i);
        af.b(i2);
        af.a(new cf(this, i3, i4, str2));
        af.a(this.g, str);
    }

    public Activity f() {
        return af.c();
    }

    public boolean g() {
        return af.d();
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void h(IOAdEvent iOAdEvent) {
        Boolean bool = (Boolean) iOAdEvent.getData().get("isClickFeAd");
        if (this.r != null && bool != null && bool.booleanValue()) {
            this.r.onAdClick();
        } else {
            if (this.r == null || bool == null) {
                return;
            }
            this.r.onContentClick();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void e(IOAdEvent iOAdEvent) {
        Map<String, Object> data = iOAdEvent.getData();
        Boolean bool = (Boolean) data.get("isImpressionFeAd");
        String str = (String) data.get("nums");
        if (this.r != null && bool != null && bool.booleanValue()) {
            this.r.onAdImpression(str);
        } else {
            if (this.r == null || bool == null) {
                return;
            }
            this.r.onContentImpression(str);
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void a(String str, int i) {
        if (this.r != null) {
            this.r.loadDataError(str);
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void e() {
        if (this.r != null) {
            this.r.onExitLp();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void d(IOAdEvent iOAdEvent) {
        if (this.r == null || iOAdEvent == null) {
            return;
        }
        this.r.onLpContentStatus(iOAdEvent.getData());
    }

    public void a(CpuAdView.CpuAdViewInternalStatusListener cpuAdViewInternalStatusListener) {
        this.r = cpuAdViewInternalStatusListener;
    }
}
