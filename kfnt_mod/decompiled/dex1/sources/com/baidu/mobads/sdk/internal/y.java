package com.baidu.mobads.sdk.internal;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.baidu.mobads.sdk.api.IAdInterListener;
import com.baidu.mobads.sdk.api.IOAdEvent;
import com.baidu.mobads.sdk.api.IXAdContainerFactory;
import com.baidu.mobads.sdk.api.NativeCPUAdData;
import com.baidu.mobads.sdk.api.NativeCPUManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class y extends ar {
    private int o;
    private int p;
    private int[] q;
    private boolean r;
    private int s;
    private HashMap<String, Object> t;
    private NativeCPUManager.CPUAdListener u;
    private NativeCPUManager v;
    private int w;
    private int x;
    private boolean y;

    public y(Context context, String str, NativeCPUManager nativeCPUManager) {
        super(context);
        this.w = 5;
        this.x = 60;
        this.n = str;
        this.v = nativeCPUManager;
    }

    public void a(NativeCPUManager.CPUAdListener cPUAdListener) {
        this.u = cPUAdListener;
    }

    public void a(int i, int i2, int[] iArr, boolean z, HashMap<String, Object> map) {
        this.p = i;
        this.o = i2;
        this.q = iArr;
        this.r = z;
        this.t = map;
        this.y = af.e();
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
            jSONObject3.put(IAdInterListener.AdReqParam.PROD, IAdInterListener.AdProdType.PRODUCT_CPU);
            this.j.createProdHandler(jSONObject3);
            n();
            this.j.addEventListener("Update_fbReader_Setting", new z(this));
            this.j.addEventListener("closeInterstitialAd", new aa(this));
            jSONObject.put(IAdInterListener.AdReqParam.PROD, IAdInterListener.AdProdType.PRODUCT_CPU);
            jSONObject.put("appsid", this.n);
            jSONObject.put("pageIndex", this.p);
            jSONObject.put("pageSize", this.o);
            jSONObject.put("channels", this.q);
            jSONObject.put("showAd", this.r);
            if (!TextUtils.isEmpty(this.n)) {
                jSONObject.put("appid", this.n);
            }
            jSONObject2.put("timeout", this.s);
            JSONObject jSONObjectA = j.a(this.t);
            if (jSONObjectA != null) {
                try {
                    jSONObjectA.put("isInitNovelSDK", this.y);
                } catch (Throwable th) {
                    th = th;
                    jSONObject2 = jSONObjectA;
                    th.printStackTrace();
                    this.j.loadAd(jSONObject, jSONObject2);
                }
            }
            jSONObject2 = jSONObjectA;
        } catch (Throwable th2) {
            th = th2;
        }
        this.j.loadAd(jSONObject, jSONObject2);
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
    public void w() {
        af.b();
    }

    public void a(int i) {
        this.s = i;
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void a(IOAdEvent iOAdEvent) {
        if (this.u != null) {
            ArrayList arrayList = new ArrayList();
            Iterator it = ((List) iOAdEvent.getData().get("cpuAdList")).iterator();
            while (it.hasNext()) {
                NativeCPUAdData nativeCPUAdData = new NativeCPUAdData(this.g, it.next(), this.t);
                arrayList.add(nativeCPUAdData);
                addObserver(nativeCPUAdData);
            }
            this.u.onAdLoaded(arrayList);
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void a(String str, int i) {
        super.a(str, i);
        if (this.u != null) {
            this.u.onAdError(str, i);
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void a(int i, String str) {
        super.a(i, str);
        if (this.u != null) {
            this.u.onAdError(str, i);
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void b(IOAdEvent iOAdEvent) {
        notifyObservers(iOAdEvent);
        setChanged();
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void d_() {
        if (this.u != null) {
            this.u.onVideoDownloadSuccess();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void c() {
        if (this.u != null) {
            this.u.onVideoDownloadFailed();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void c(IOAdEvent iOAdEvent) {
        if (iOAdEvent != null) {
            Map<String, Object> data = iOAdEvent.getData();
            Integer num = (Integer) data.get("position");
            String str = (String) data.get("mislikereason");
            if (this.u == null || num == null || str == null) {
                return;
            }
            this.u.onDisLikeAdClick(num.intValue(), str);
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void e() {
        if (this.u != null) {
            this.u.onExitLp();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.ar
    protected void d(IOAdEvent iOAdEvent) {
        if (this.u == null || iOAdEvent == null) {
            return;
        }
        this.u.onLpCustomEventCallBack((HashMap) iOAdEvent.getData());
    }

    public void f() {
        af.a(this.w);
        af.b(this.x);
        af.a(new ab(this));
    }

    private String a(String str) {
        IXAdContainerFactory iXAdContainerFactoryC;
        u uVarA = u.a();
        if (uVarA != null && (iXAdContainerFactoryC = uVarA.c()) != null) {
            Object remoteParam = iXAdContainerFactoryC.getRemoteParam(str, new Object[0]);
            if (remoteParam instanceof String) {
                return (String) remoteParam;
            }
        }
        return null;
    }

    public Activity g() {
        return af.c();
    }

    public boolean h() {
        return af.d();
    }
}
