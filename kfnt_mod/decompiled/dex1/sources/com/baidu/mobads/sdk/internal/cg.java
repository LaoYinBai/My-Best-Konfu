package com.baidu.mobads.sdk.internal;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.baidu.mobads.sdk.api.CPUNovelAd;
import com.baidu.mobads.sdk.api.CPUWebAdRequestParam;
import com.baidu.mobads.sdk.api.IAdInterListener;
import com.baidu.mobads.sdk.api.IOAdEvent;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class cg extends ar {
    private static final String F = "cg";
    public static final String o = "enter_refresh_bookstore";
    public static final String p = "enter_reader";
    public static final String q = "notify_impression";
    public static final String r = "request_int_ad_view";
    public static final String s = "request_banner_ad_view";
    public static final String t = "request_bookstore_bottom_view";
    public static final String u = "request_shelf_ad_view";
    public static final String v = "reader_background_status_change";
    public static final String w = "pre_chapter_adstart_countdown";
    public static final String x = "try_get_cuid";
    public int A;
    public int B;
    public int C;
    public int D;
    public String E;
    private CPUNovelAd.CpuNovelListener G;
    public HashMap<String, Object> y;
    public String z;

    public cg(Context context, String str, CPUWebAdRequestParam cPUWebAdRequestParam) {
        super(context);
        this.A = 5;
        this.B = 60;
        this.C = 0;
        this.D = 10006;
        this.E = "";
        this.z = str;
        this.y = (HashMap) cPUWebAdRequestParam.getParameters();
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    public void b_() {
        f();
        if (this.j == null) {
            this.k = false;
            return;
        }
        this.k = true;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(IAdInterListener.AdReqParam.PROD, "novel");
            this.j.createProdHandler(jSONObject);
            n();
            this.j.addEventListener("Update_fbReader_Setting", new ch(this));
            this.j.addEventListener("closeInterstitialAd", new ci(this));
            JSONObject jSONObject2 = new JSONObject();
            if (!TextUtils.isEmpty(this.z)) {
                jSONObject2.put("appid", this.z);
            }
            jSONObject2.put(IAdInterListener.AdReqParam.PROD, "novel");
            this.j.loadAd(jSONObject2, j.a(this.y));
        } catch (Throwable th) {
            this.h.c(F, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        af.b();
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

    public void f() {
        af.a(this.A);
        af.b(this.B);
        af.a(new cj(this));
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void h(IOAdEvent iOAdEvent) {
        if (this.G != null) {
            this.G.onAdClick();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void e(IOAdEvent iOAdEvent) {
        if (this.G != null) {
            this.G.onAdImpression();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    public View v() {
        View viewA = af.a(this.g);
        if (viewA instanceof FrameLayout) {
            RelativeLayout relativeLayout = new RelativeLayout(this.g);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, ak.a(this.g, 53.0f));
            layoutParams.gravity = 80;
            ((FrameLayout) viewA).addView(relativeLayout, layoutParams);
            if (av.a((Context) null).a() >= 18) {
                relativeLayout.getViewTreeObserver().addOnWindowFocusChangeListener(new ck(this, relativeLayout));
            }
        }
        return viewA;
    }

    public Activity g() {
        return af.c();
    }

    public boolean h() {
        return af.d();
    }

    public void a(CPUNovelAd.CpuNovelListener cpuNovelListener) {
        this.G = cpuNovelListener;
    }
}
