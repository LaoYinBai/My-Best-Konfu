package com.kwad.sdk.core.report;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.huawei.updatesdk.service.otaupdate.UpdateKey;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.utils.at;
import com.kwad.sdk.utils.z;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class p extends com.kwad.sdk.core.network.b {
    int b;
    private AdTemplate c;

    @Nullable
    private a d;

    @Nullable
    private JSONObject e;

    public static class a extends com.kwad.sdk.core.response.kwai.a {
        public double B;
        public int a;
        public int b;
        public int c;
        public int d;
        public int f;
        public z.a g;
        public String h;
        public int i;
        public int j;
        public int k;
        public String m;
        public int n;
        public int o;
        public String p;
        public String q;
        public int r;
        public int s;
        public long t;
        public int v;
        public int w;
        public long x;
        public int y;
        public int e = -1;
        public String l = "";
        public int u = 0;
        public int z = -1;
        public int A = -1;
    }

    p(@NonNull AdTemplate adTemplate, int i, @Nullable a aVar, @Nullable JSONObject jSONObject) {
        this.c = adTemplate;
        this.b = i;
        this.d = aVar;
        this.e = jSONObject;
    }

    private void a(String str, @Nullable a aVar) {
        if (aVar == null || TextUtils.isEmpty(str)) {
            return;
        }
        if (aVar.b != 0) {
            a("itemClickType", aVar.b);
        }
        if (!TextUtils.isEmpty(aVar.h)) {
            b("payload", aVar.h);
        }
        if (aVar.o != 0) {
            a("adAggPageSource", aVar.o);
        }
        if (aVar.z >= 0) {
            a("adOrder", aVar.z);
        }
        if (aVar.A >= 0) {
            a("adInterstitialSource", aVar.A);
        }
        if (aVar.B > 0.0d) {
            a("splashShakeAcceleration", aVar.B);
        }
    }

    private void a(String str, AdTemplate adTemplate, @Nullable a aVar) {
        if (aVar == null || TextUtils.isEmpty(str)) {
            return;
        }
        if (aVar.z >= 0) {
            a("adOrder", aVar.z);
        }
        if (aVar.A >= 0) {
            a("adInterstitialSource", aVar.A);
        }
    }

    private void a(@Nullable JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        try {
            jSONObject.put("clientTimestamp", System.currentTimeMillis());
        } catch (JSONException e) {
            com.kwad.sdk.core.d.a.a(e);
        }
        b("extData", jSONObject.toString());
    }

    private void b(String str, @Nullable a aVar) {
        if (aVar == null || TextUtils.isEmpty(str)) {
            return;
        }
        if (aVar.c != 0) {
            a("itemCloseType", aVar.c);
        }
        if (aVar.a > 0) {
            a("photoPlaySecond", aVar.a);
        }
        if (aVar.d != 0) {
            a("elementType", aVar.d);
        }
        if (!TextUtils.isEmpty(aVar.h)) {
            b("payload", aVar.h);
        }
        if (aVar.i > 0) {
            a("deeplinkType", aVar.i);
        }
        if (aVar.j > 0) {
            a("downloadSource", aVar.j);
        }
        if (aVar.k > 0) {
            a("isPackageChanged", aVar.k);
        }
        b("installedFrom", aVar.l);
        a("isChangedEndcard", aVar.n);
        if (aVar.o != 0) {
            a("adAggPageSource", aVar.o);
        }
        if (aVar.m != null) {
            b("downloadFailedReason", aVar.m);
        }
        if (!at.a(aVar.q)) {
            b("installedPackageName", aVar.q);
        }
        if (!at.a(aVar.p)) {
            b("serverPackageName", aVar.p);
        }
        if (aVar.s > 0) {
            a("closeButtonClickTime", aVar.s);
        }
        if (aVar.r > 0) {
            a("closeButtonImpressionTime", aVar.r);
        }
        if (aVar.u >= 0) {
            a(UpdateKey.MARKET_DLD_STATUS, aVar.u);
        }
        if (aVar.t > 0) {
            a("landingPageLoadedDuration", aVar.t);
        }
        if (aVar.e > -1) {
            a("impFailReason", aVar.e);
        }
        if (aVar.f > 0) {
            a("winEcpm", aVar.f);
        }
        a("downloadCardType", aVar.v);
        a("landingPageType", aVar.w);
        if (aVar.A >= 0) {
            a("adInterstitialSource", aVar.A);
        }
    }

    private void b(String str, AdTemplate adTemplate, @Nullable a aVar) {
        String str2;
        int i;
        if (TextUtils.isEmpty(str) || adTemplate == null) {
            return;
        }
        if (adTemplate.mInitVoiceStatus != 0) {
            a("initVoiceStatus", adTemplate.mInitVoiceStatus);
        }
        if (this.c.mBidEcpm == 0) {
            str2 = "ecpmType";
            i = 2;
        } else {
            str2 = "ecpmType";
            i = 1;
        }
        a(str2, i);
        if (aVar == null) {
            return;
        }
        if (aVar.o != 0) {
            a("adAggPageSource", aVar.o);
        }
        if (TextUtils.isEmpty(aVar.h)) {
            return;
        }
        b("payload", aVar.h);
    }

    @Override // com.kwad.sdk.core.network.b, com.kwad.sdk.core.network.g
    public String a() throws JSONException {
        String strReplaceFirst;
        String str;
        int iF;
        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(this.c);
        if (this.b == 1) {
            String str2 = adInfoJ.adBaseInfo.showUrl;
            if (this.c.mBidEcpm == 0 && com.kwad.sdk.core.config.b.ax()) {
                str = "__PR__";
                iF = com.kwad.sdk.core.response.a.a.F(com.kwad.sdk.core.response.a.d.j(this.c));
            } else {
                str = "__PR__";
                iF = this.c.mBidEcpm;
            }
            strReplaceFirst = str2.replaceFirst(str, String.valueOf(iF)).replaceFirst("__TYPE__", String.valueOf(this.c.mVideoPlayerStatus.mVideoPlayerType)).replaceFirst("__BEHAVIOR__", String.valueOf(this.c.mVideoPlayerStatus.mVideoPlayerBehavior));
            a(strReplaceFirst, this.c, this.d);
        } else {
            if (this.b != 2) {
                strReplaceFirst = adInfoJ.adBaseInfo.convUrl.replaceFirst("__ACTION__", String.valueOf(this.b)).replaceFirst("__PR__", String.valueOf(this.c.mBidEcpm)).replaceFirst("__TYPE__", String.valueOf(this.c.mVideoPlayerStatus.mVideoPlayerType)).replaceFirst("__BEHAVIOR__", String.valueOf(this.c.mVideoPlayerStatus.mVideoPlayerBehavior));
                b(strReplaceFirst, this.d);
                a(this.e);
                return strReplaceFirst;
            }
            String strB = adInfoJ.adBaseInfo.clickUrl;
            if (this.d != null) {
                strB = z.b(strB, this.d.g);
            }
            strReplaceFirst = z.b(strB).replaceFirst("__PR__", String.valueOf(this.c.mBidEcpm)).replaceFirst("__TYPE__", String.valueOf(this.c.mVideoPlayerStatus.mVideoPlayerType)).replaceFirst("__BEHAVIOR__", String.valueOf(this.c.mVideoPlayerStatus.mVideoPlayerBehavior));
            a(strReplaceFirst, this.d);
        }
        b(strReplaceFirst, this.c, this.d);
        a(this.e);
        return strReplaceFirst;
    }

    @Override // com.kwad.sdk.core.network.b, com.kwad.sdk.core.network.g
    public JSONObject e() {
        return this.a;
    }

    @Override // com.kwad.sdk.core.network.b
    protected void g() {
    }

    @Override // com.kwad.sdk.core.network.b
    protected void h() {
    }

    public AdTemplate i() {
        return this.c;
    }

    List<String> j() {
        z.a aVar;
        AdInfo.AdTrackInfo next;
        ArrayList arrayList = new ArrayList();
        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(this.c);
        if (!adInfoJ.adTrackInfoList.isEmpty()) {
            Iterator<AdInfo.AdTrackInfo> it = adInfoJ.adTrackInfoList.iterator();
            while (true) {
                aVar = null;
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (next.type == this.b && next.urls != null) {
                    break;
                }
            }
            if (next != null) {
                if (next.type == 2 && this.d != null) {
                    aVar = this.d.g;
                }
                Iterator<String> it2 = next.urls.iterator();
                while (it2.hasNext()) {
                    arrayList.add(z.a(it2.next(), aVar));
                }
            }
        }
        return arrayList;
    }
}
