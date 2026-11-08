package com.kwad.sdk.core.report;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.scene.EntryPackage;
import com.kwad.sdk.core.scene.URLPackage;
import com.kwad.sdk.internal.api.SceneImpl;
import com.kwad.sdk.live.mode.LiveInfo;
import com.kwad.sdk.utils.t;
import java.io.Serializable;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ReportAction extends c {
    public long A;
    public long C;
    public String D;
    public int E;
    public int F;
    public String K;
    public URLPackage L;
    public URLPackage M;
    public long N;
    public int O;
    public String P;
    public int Q;
    public int R;
    public long S;
    public long T;
    public int U;
    public JSONArray X;
    public JSONArray Y;
    public String Z;
    public int aA;
    public int aB;
    public String aC;
    public String aD;
    public int aE;
    public long aF;
    public String aH;
    public int aI;
    public int aJ;
    public String aK;
    public String aL;
    public JSONObject aM;
    public JSONArray aN;
    public long aO;
    public int aP;
    public int aQ;
    public int aR;
    public JSONArray aV;
    public boolean aW;
    public String aX;
    public String aa;
    public String ab;
    public String ac;
    public long ad;
    public long ae;
    public int af;
    public int ag;
    public String ah;
    public a ai;
    public int aj;
    public int ak;
    public String al;
    public long am;
    public String an;
    public String ao;
    public long ap;
    public int as;
    public int at;
    public int au;
    public int aw;
    public int ax;
    public int ay;
    public JSONArray az;
    public transient SceneImpl b;
    public String bA;
    public int bB;
    public int bC;
    public long bD;
    public int bE;
    public String bF;
    public int bG;
    public double bH;
    public String bI;
    public int bJ;
    public String bL;
    public String bM;
    public String bb;
    public long bc;
    public long bd;
    public int be;
    public String bf;
    public long bg;
    public long bh;
    public long bi;
    public long bj;
    public String bk;
    public String bl;
    public long bm;
    public long bn;
    public JSONArray bo;
    public long bp;
    public long bq;
    public long br;
    public JSONArray bs;
    public int bt;
    public String bu;
    public String bv;
    public String bw;
    public String bx;
    public String by;
    public String bz;

    @Nullable
    public transient AdTemplate c;
    public long d;
    public String e;
    public long f;
    public long h;
    public long i;
    public long j;
    public JSONObject k;
    public JSONObject l;
    public long m;
    public long p;
    public long q;
    public long r;
    public long s;
    public long t;
    public long u;
    public long v;
    public long w;
    public long x;
    public long y;
    public long z;
    public int g = -1;
    public int n = 0;
    public int o = 0;
    public long B = -1;
    public int G = 0;
    public int H = 0;
    public int I = -1;
    public int J = 0;
    public int V = -1;
    public int W = 0;
    public long aq = -1;
    public long ar = -1;
    public int av = -1;
    public LiveLogInfo aG = new LiveLogInfo();
    public int aS = -1;
    public int aT = -1;
    public long aU = 0;
    public int aY = 0;
    public int aZ = -1;
    public int ba = 0;
    public int bK = 0;

    public static class LiveLogInfo extends com.kwad.sdk.core.response.kwai.a implements Serializable {
        private static final long serialVersionUID = 4898843925487388950L;
        public String liveStreamId;
    }

    public static final class a extends com.kwad.sdk.core.response.kwai.a {
        public int a;
        public int b;

        public static a a() {
            a aVar = new a();
            aVar.a = b.a;
            aVar.b = b.b;
            return aVar;
        }
    }

    public static class b {
        public static int a;
        public static int b;
    }

    public ReportAction(long j) {
        this.i = j;
    }

    public ReportAction(long j, @NonNull AdTemplate adTemplate) {
        this.i = j;
        this.c = adTemplate;
    }

    public ReportAction(String str) {
        try {
            parseJson(new JSONObject(str));
        } catch (JSONException e) {
            com.kwad.sdk.core.d.a.a(e);
        }
    }

    public ReportAction(JSONObject jSONObject) {
        parseJson(jSONObject);
    }

    public static ReportAction a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            return jSONObject.optBoolean("ecIdentityFlag", false) ? new m(jSONObject) : new ReportAction(jSONObject);
        } catch (JSONException e) {
            com.kwad.sdk.core.d.a.a(e);
            return new ReportAction("");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void a(@Nullable AdTemplate adTemplate) {
        this.ak = 3;
        com.kwad.sdk.plugin.b bVar = (com.kwad.sdk.plugin.b) com.kwad.sdk.plugin.f.a(com.kwad.sdk.plugin.b.class);
        if (bVar != null) {
            this.aY = bVar.d();
        }
        this.a = UUID.randomUUID().toString();
        this.d = System.currentTimeMillis();
        this.e = n.b();
        try {
            this.f = n.c();
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.b(e);
        }
        this.h = n.e();
        if (this.b != null) {
            this.m = this.b.getPosId();
            this.L = this.b.getUrlPackage();
            this.g = this.b.getAdStyle();
        } else if (adTemplate != null && adTemplate.mAdScene != null) {
            this.b = adTemplate.mAdScene;
            this.m = this.b.getPosId();
            this.L = this.b.getUrlPackage();
            this.g = this.b.getAdStyle();
        }
        if (adTemplate != null) {
            this.q = adTemplate.getShowPosition() + 1;
            this.r = adTemplate.getServerPosition() + 1;
            this.j = com.kwad.sdk.core.response.a.d.g(adTemplate);
            String strH = com.kwad.sdk.core.response.a.d.h(adTemplate);
            if (!TextUtils.isEmpty(strH)) {
                try {
                    this.k = new JSONObject(strH);
                } catch (Exception e2) {
                    com.kwad.sdk.core.d.a.b(e2);
                }
            }
            String strI = com.kwad.sdk.core.response.a.d.i(adTemplate);
            if (!TextUtils.isEmpty(strI)) {
                try {
                    this.l = new JSONObject(strI);
                } catch (Exception e3) {
                    com.kwad.sdk.core.d.a.b(e3);
                }
            }
            this.m = com.kwad.sdk.core.response.a.d.e(adTemplate);
            this.n = com.kwad.sdk.core.response.a.d.f(adTemplate);
            this.o = adTemplate.realShowType;
            this.p = com.kwad.sdk.core.response.a.d.p(adTemplate);
            if (this.o == 1) {
                this.s = com.kwad.sdk.core.response.a.f.c(adTemplate.photoInfo).longValue();
                this.N = com.kwad.sdk.core.response.a.f.h(adTemplate.photoInfo);
                this.ah = com.kwad.sdk.core.response.a.f.g(adTemplate.photoInfo);
                this.al = com.kwad.sdk.core.response.a.f.k(adTemplate.photoInfo);
                this.am = com.kwad.sdk.core.response.a.f.j(adTemplate.photoInfo);
                this.an = com.kwad.sdk.core.response.a.f.l(adTemplate.photoInfo);
                this.ap = com.kwad.sdk.core.response.a.f.m(adTemplate.photoInfo);
                this.ao = com.kwad.sdk.core.response.a.f.n(adTemplate.photoInfo);
                this.ay = adTemplate.mPreloadData != null ? adTemplate.mPreloadData.isPreload : 0;
                this.bK = com.kwad.sdk.core.response.a.f.a(adTemplate) ? 1 : 0;
            } else if (this.o == 2) {
                AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(adTemplate);
                this.aF = adInfoJ.adBaseInfo.creativeId;
                this.s = com.kwad.sdk.core.response.a.a.b(adInfoJ) * 1000;
                this.N = adInfoJ.advertiserInfo.userId;
            } else if (this.o == 4) {
                LiveInfo liveInfoR = com.kwad.sdk.core.response.a.d.r(adTemplate);
                this.aG.liveStreamId = com.kwad.sdk.live.mode.a.a(liveInfoR);
                this.N = com.kwad.sdk.live.mode.a.b(liveInfoR);
                if (com.kwad.sdk.b.d.booleanValue()) {
                    this.bK = 1;
                }
            } else if (this.o == 5) {
                this.bJ = com.kwad.sdk.core.response.a.e.c(com.kwad.sdk.core.response.a.d.l(this.c));
            }
            this.aj = adTemplate.mMediaPlayerType;
            this.ax = adTemplate.mIsLeftSlipStatus;
            this.aB = adTemplate.mPhotoResponseType;
            if (adTemplate.mPageInfo != null) {
                this.aP = adTemplate.mPageInfo.pageType;
            }
            this.F = com.kwad.sdk.core.response.a.d.q(adTemplate);
        }
        this.ai = a.a();
        if (this.b == null && adTemplate != null) {
            this.b = adTemplate.mAdScene;
        }
        if (this.b != null) {
            this.m = this.b.getPosId();
            this.L = this.b.getUrlPackage();
        }
        if (this.L != null) {
            EntryPackage entryPackageB = com.kwad.sdk.core.scene.a.a().b(this.L.identity);
            this.K = entryPackageB.entryPageSource;
            this.ab = entryPackageB.entryId;
            this.M = com.kwad.sdk.core.scene.a.a().a(this.L.identity);
        }
    }

    @Override // com.kwad.sdk.core.report.c, com.kwad.sdk.core.response.kwai.a
    public void afterParseJson(@Nullable JSONObject jSONObject) {
        super.afterParseJson(jSONObject);
        if (jSONObject == null) {
            return;
        }
        this.ak = 3;
        this.g = jSONObject.optInt("adStyle", -1);
        this.as = jSONObject.optInt("adHorizontalFeedType");
        this.at = jSONObject.optInt("videoPlayMode");
        this.au = jSONObject.optInt("autoReplayTimes");
        this.V = jSONObject.optInt("reportType");
        this.I = jSONObject.optInt("playerControlledType", -1);
        this.aJ = jSONObject.optInt("num");
        this.aI = jSONObject.optInt("state");
        this.aq = jSONObject.optLong("relatedFromPhotoId", -1L);
        this.ar = jSONObject.optLong("relatedContentSourceType", -1L);
        this.av = jSONObject.optInt("hotCompType", -1);
        this.aU = jSONObject.optLong("timeSpend");
        this.bq = jSONObject.optLong("loadingDuration");
        this.br = jSONObject.optLong("loadingDurationLimt");
        this.aZ = jSONObject.optInt("playerTypeInfo", -1);
        if (jSONObject.has("actionId")) {
            this.a = jSONObject.optString("actionId");
        }
        this.bK = jSONObject.optInt("adBizType");
    }

    @Override // com.kwad.sdk.core.report.c, com.kwad.sdk.core.response.kwai.a
    public void afterToJson(JSONObject jSONObject) throws JSONException {
        super.afterToJson(jSONObject);
        t.a(jSONObject, "actionId", this.a);
        if (this.g > 0) {
            t.a(jSONObject, "adStyle", this.g);
        }
        if (this.V != -1) {
            t.a(jSONObject, "reportType", this.V);
        }
        if (this.I != -1) {
            t.a(jSONObject, "playerControlledType", this.I);
        }
        if (this.aJ > 0) {
            t.a(jSONObject, "num", this.aJ);
        }
        if (this.aI != 0) {
            t.a(jSONObject, "state", this.aI);
        }
        if (this.aq != -1) {
            t.a(jSONObject, "relatedFromPhotoId", this.aq);
        }
        if (this.ar != -1) {
            t.a(jSONObject, "relatedContentSourceType", this.ar);
        }
        if (this.av != -1) {
            t.a(jSONObject, "hotCompType", this.av);
        }
        if (this.aU > 0) {
            t.a(jSONObject, "timeSpend", this.aU);
        }
        if (this.bq > 0) {
            t.a(jSONObject, "loadingDuration", this.bq);
        }
        if (this.br > 0) {
            t.a(jSONObject, "loadingDurationLimt", this.br);
        }
        t.a(jSONObject, "adHorizontalFeedType", this.as);
        t.a(jSONObject, "videoPlayMode", this.at);
        t.a(jSONObject, "autoReplayTimes", this.au);
        t.a(jSONObject, "playerTypeInfo", this.aZ);
        if (this.bK > 0) {
            t.a(jSONObject, "adBizType", this.bK);
        }
    }

    public ReportAction b() {
        a(this.c);
        return this;
    }

    public void c() {
        com.kwad.sdk.core.h.b bVarA = com.kwad.sdk.core.h.b.a();
        this.aR = bVarA.b() ? 1 : 0;
        this.aS = bVarA.c();
        this.aT = bVarA.d();
    }
}
