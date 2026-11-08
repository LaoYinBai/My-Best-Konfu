package com.mbridge.msdk.b;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.kwad.sdk.core.response.model.SdkConfigData;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.DomainCampaignEx;
import com.mbridge.msdk.foundation.same.net.m;
import com.mbridge.msdk.foundation.tools.i;
import com.mbridge.msdk.foundation.tools.n;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.z;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Setting.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    private int A;
    private long B;
    private long C;
    private int D;
    private int E;
    private int F;
    private String G;
    private String H;
    private int I;
    private List<CampaignEx> J;
    private List<com.mbridge.msdk.foundation.entity.b> K;
    private LinkedList<String> O;
    private int U;
    private List<String> V;
    private int W;
    private int X;
    private int Y;
    private int Z;
    private int aA;
    private long aB;
    private int aD;
    private Map<String, C0102a> aV;
    private String aa;
    private String ab;
    private String ak;
    private int av;
    private long ax;
    private long ay;
    private int az;
    private String b;
    private List<com.mbridge.msdk.foundation.entity.a> ba;
    private String bb;
    private String bc;
    private ArrayList<Integer> bg;
    private HashMap<String, String> bh;
    private long c;
    private int d;
    private int f;
    private boolean g;
    private Map<String, String> h;
    private boolean i;
    private long j;
    private boolean l;
    private long m;
    private long n;
    private long o;
    private boolean p;
    private int q;
    private int r;
    private int s;
    private long t;
    private int u;
    private int v;
    private int w;
    private int x;
    private String y;
    private long z;
    private int a = 0;
    private long e = 86400;
    private String k = com.mbridge.msdk.foundation.same.net.g.d.c().c;
    private boolean L = false;
    private int M = 3;
    private boolean N = true;
    private int P = 0;
    private int Q = SdkConfigData.DEFAULT_REQUEST_INTERVAL;
    private int R = 10;
    private int S = 120;
    private String T = "";
    private int ac = 1;
    private int ad = 1;
    private int ae = 1;
    private int af = 0;
    private int ag = 1;
    private String ah = "";
    private int ai = 0;
    private int aj = 2;
    private int al = RemoteMessageConst.DEFAULT_TTL;
    private String am = "LdxThdi1WBK\\/WgfPhbxQYkeXHBPwHZKAJ7eXHM==";
    private String an = "LdxThdi1WBK\\/WgfPhbxQYkeXHBPwHZKsYFh=";
    private int ao = 1;
    private int ap = 30;
    private int aq = 5;
    private int ar = 0;
    private int as = 0;
    private int at = 9377;
    private List<Integer> au = new ArrayList();
    private int aw = 1;
    private int aC = 3;
    private String aE = "";
    private String aF = "";
    private String aG = "";
    private String aH = "";
    private String aI = "";
    private int aJ = 0;
    private int aK = 21600;
    private int aL = 2;
    private int aM = 0;
    private int aN = 0;
    private int aO = DomainCampaignEx.TTC_CT_DEFAULT_VALUE;
    private int aP = 0;
    private String aQ = "";
    private String aR = "";
    private String aS = "";
    private String aT = "";
    private String aU = "";
    private int aW = 0;
    private String aX = "";
    private int aY = 2;
    private int aZ = 7200;
    private int bd = 0;
    private boolean be = false;
    private int bf = 120;
    private int bi = 0;
    private int bj = 0;

    public final List<com.mbridge.msdk.foundation.entity.b> a() {
        return this.K;
    }

    public final String b() {
        return this.T;
    }

    public final void a(int i) {
        this.R = i;
    }

    public final int c() {
        return this.S;
    }

    public final void b(int i) {
        this.S = i;
    }

    public final int d() {
        return this.a;
    }

    public final void c(int i) {
        this.a = i;
    }

    public final boolean d(int i) {
        return this.au.contains(Integer.valueOf(i));
    }

    public final int e() {
        return this.al;
    }

    public final void e(int i) {
        this.al = i;
    }

    public final int f() {
        return this.aj;
    }

    public final void f(int i) {
        this.aj = i;
    }

    public final String g() {
        return this.ak;
    }

    public final int h() {
        return this.ac;
    }

    public final void g(int i) {
        this.ac = i;
    }

    public final int i() {
        return this.ad;
    }

    public final void h(int i) {
        this.ad = i;
    }

    public final int j() {
        return this.ae;
    }

    public final void i(int i) {
        this.ae = i;
    }

    public final int k() {
        return this.af;
    }

    public final void j(int i) {
        this.af = i;
    }

    public final int l() {
        return this.ag;
    }

    public final void k(int i) {
        this.ag = i;
    }

    public final void a(String str) {
        this.ah = str;
    }

    public final int m() {
        return this.ai;
    }

    public final void l(int i) {
        this.ai = i;
    }

    public final int n() {
        return this.I;
    }

    public final void m(int i) {
        this.I = i;
    }

    public final String o() {
        return this.H;
    }

    public final void b(String str) {
        this.H = str;
    }

    public final void n(int i) {
        this.aw = i;
    }

    public final void o(int i) {
        this.av = i;
    }

    public final void p(int i) {
        this.U = i;
    }

    public final void q(int i) {
        this.W = i;
    }

    public final void r(int i) {
        this.X = i;
    }

    public final void s(int i) {
        this.Y = i;
    }

    public final void t(int i) {
        this.Z = i;
    }

    public final void c(String str) {
        this.aa = str;
    }

    public final int p() {
        return this.D;
    }

    public final void u(int i) {
        this.D = i;
    }

    public final int q() {
        return this.E;
    }

    public final void v(int i) {
        this.E = i;
    }

    public final List<CampaignEx> r() {
        return this.J;
    }

    public final int s() {
        return this.F;
    }

    public final void w(int i) {
        this.F = i;
    }

    public final String t() {
        return this.G;
    }

    public final void d(String str) {
        this.G = str;
    }

    public final int u() {
        return this.A;
    }

    public final void x(int i) {
        this.A = i;
    }

    public final void a(long j) {
        this.B = j;
    }

    public final void y(int i) {
        this.C = i;
    }

    public final long v() {
        return this.z * 1000;
    }

    public final int w() {
        return this.s;
    }

    public final int x() {
        return this.x;
    }

    public final int y() {
        return this.u;
    }

    public final void z(int i) {
        this.u = i;
    }

    public final int z() {
        return this.v;
    }

    public final void A(int i) {
        this.v = i;
    }

    public final int A() {
        return this.w;
    }

    public final void B(int i) {
        this.w = i;
    }

    public final long B() {
        if (this.t <= 0) {
            this.t = 7200L;
        }
        return this.t;
    }

    public final void b(long j) {
        this.t = j;
    }

    public final void C(int i) {
        this.aC = i;
    }

    public final int C() {
        return this.aD;
    }

    public final void D(int i) {
        this.aD = i;
    }

    public final long D() {
        return this.aB;
    }

    public final void c(long j) {
        this.aB = j;
    }

    public final int E() {
        return this.az;
    }

    public final void E(int i) {
        this.az = i;
    }

    public final int F() {
        return this.aA;
    }

    public final void F(int i) {
        this.aA = i;
    }

    public final long G() {
        return this.ax * 1000;
    }

    public final void d(long j) {
        this.ax = j;
    }

    public final long H() {
        return this.ay * 1000;
    }

    public final void e(long j) {
        this.ay = j;
    }

    public final boolean I() {
        return this.p;
    }

    public final void a(boolean z) {
        this.p = z;
    }

    public final long J() {
        return this.o;
    }

    public final boolean K() {
        return this.l;
    }

    public final void b(boolean z) {
        this.l = z;
    }

    public final long L() {
        return this.m;
    }

    public final void f(long j) {
        this.m = j;
    }

    public final long M() {
        return this.n;
    }

    public final void g(long j) {
        this.n = j;
    }

    public final String toString() {
        return "cc=" + this.b + " upal=" + this.c + " cfc=" + this.d + " getpf=" + this.e + " uplc=" + this.f + " rurl=" + this.l;
    }

    public final String N() {
        return this.b;
    }

    public final void h(long j) {
        this.c = j;
    }

    public final int O() {
        return this.d;
    }

    public final void G(int i) {
        this.d = i;
    }

    public final long P() {
        return this.e;
    }

    public final void H(int i) {
        this.f = i;
    }

    public final String Q() {
        return this.ab;
    }

    public final String R() {
        return this.k;
    }

    public static a e(String str) throws JSONException, NumberFormatException {
        ArrayList arrayList;
        CampaignEx settingCampaign;
        a aVar = null;
        arrayList = null;
        ArrayList arrayList2 = null;
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(str);
            a aVar2 = new a();
            try {
                aVar2.b = jSONObject.optString("cc");
                aVar2.G = jSONObject.optString("apk_toast", "正在下载中，请去通知栏查看下载进度");
                aVar2.H = jSONObject.optString("mv_wildcard", "mbridge");
                aVar2.c = jSONObject.optLong("upal");
                aVar2.d = jSONObject.optInt("cfc");
                aVar2.e = jSONObject.optLong("getpf");
                aVar2.f = jSONObject.optInt("uplc");
                aVar2.g = jSONObject.optBoolean("aa");
                aVar2.j = jSONObject.optLong("current_time");
                aVar2.i = jSONObject.optBoolean("cfb");
                aVar2.n = jSONObject.optLong("awct");
                aVar2.m = jSONObject.optLong(CampaignEx.JSON_KEY_PLCT) == 0 ? 3600L : jSONObject.optLong(CampaignEx.JSON_KEY_PLCT);
                aVar2.l = jSONObject.optBoolean("rurl");
                aVar2.o = jSONObject.optLong("uct");
                aVar2.p = jSONObject.optBoolean("ujds");
                aVar2.q = jSONObject.optInt("n2");
                aVar2.r = jSONObject.optInt("n3");
                aVar2.I = jSONObject.optInt("is_startup_crashsystem");
                aVar2.s = jSONObject.optInt("n4", DomainCampaignEx.TTC_CT2_DEFAULT_VALUE);
                aVar2.u = jSONObject.optInt("pcrn");
                aVar2.t = jSONObject.optLong(CampaignEx.JSON_KEY_PLCTB) == 0 ? 7200L : jSONObject.optLong(CampaignEx.JSON_KEY_PLCTB);
                aVar2.az = jSONObject.optInt("upmi");
                aVar2.aA = jSONObject.optInt("upaid");
                aVar2.u = jSONObject.optInt("pcrn", 100);
                aVar2.v = jSONObject.optInt("wicon", 2);
                aVar2.w = jSONObject.optInt("wreq", 2);
                boolean z = true;
                aVar2.x = jSONObject.optInt("opent", 1);
                aVar2.aB = jSONObject.optLong("sfct", 1800L);
                aVar2.y = jSONObject.optString("t_vba");
                aVar2.z = jSONObject.optLong("tcct", 21600000L);
                aVar2.A = jSONObject.optInt("dlrf", 1);
                aVar2.B = jSONObject.optInt("dlrfct", DomainCampaignEx.TTC_CT_DEFAULT_VALUE);
                aVar2.C = jSONObject.optInt("pcct", 43200);
                aVar2.aC = jSONObject.optInt("pctn", 3);
                aVar2.aw = jSONObject.optInt("ilrf", 1);
                aVar2.ab = jSONObject.optString("pw", "");
                aVar2.av = jSONObject.optInt("dlapk", 1);
                aVar2.ad = jSONObject.optInt("upgd", 1);
                aVar2.ac = jSONObject.optInt("upsrl", 1);
                aVar2.ae = jSONObject.optInt("updevid", 1);
                aVar2.af = jSONObject.optInt("sc", 0);
                aVar2.ag = jSONObject.optInt("up_tips", 1);
                aVar2.ai = jSONObject.optInt("iseu", -1);
                aVar2.ah = jSONObject.optString("up_tips_url", com.mbridge.msdk.b.a.b.a);
                aVar2.aj = jSONObject.optInt("jmc", 2);
                aVar2.al = jSONObject.optInt("jmct", RemoteMessageConst.DEFAULT_TTL);
                aVar2.ak = jSONObject.optString("jm_unit");
                aVar2.an = jSONObject.optString("cdai");
                aVar2.am = jSONObject.optString("csdai");
                aVar2.ao = jSONObject.optInt("ils");
                aVar2.Q = jSONObject.optInt("clptm", SdkConfigData.DEFAULT_REQUEST_INTERVAL);
                aVar2.P = jSONObject.optInt("clptype", 0);
                try {
                    JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("clpcode");
                    if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
                        LinkedList<String> linkedList = new LinkedList<>();
                        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                            linkedList.add(jSONArrayOptJSONArray.optString(i));
                        }
                        aVar2.O = linkedList;
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                long jOptLong = jSONObject.optLong("pcto");
                if (jOptLong == 0) {
                    aVar2.ax = 20L;
                } else {
                    aVar2.ax = jOptLong;
                }
                long jOptLong2 = jSONObject.optLong("tcto");
                if (jOptLong2 == 0) {
                    aVar2.ay = 10L;
                } else {
                    aVar2.ay = jOptLong2;
                    JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray("jt");
                    if (jSONArrayOptJSONArray2 != null && jSONArrayOptJSONArray2.length() > 0) {
                        HashMap map = new HashMap();
                        for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                            JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray2.optJSONObject(i2);
                            map.put(jSONObjectOptJSONObject.optString(DomainCampaignEx.LOOPBACK_DOMAIN), jSONObjectOptJSONObject.optString("format"));
                        }
                        aVar2.h = map;
                    }
                }
                aVar2.D = jSONObject.optInt("plc", 3);
                aVar2.E = jSONObject.optInt("dut", RemoteMessageConst.DEFAULT_TTL);
                aVar2.F = jSONObject.optInt(CampaignEx.JSON_KEY_ST_IEX, 1);
                JSONArray jSONArrayOptJSONArray3 = jSONObject.optJSONArray("cal");
                if (jSONArrayOptJSONArray3 == null || jSONArrayOptJSONArray3.length() <= 0) {
                    arrayList = null;
                } else {
                    arrayList = new ArrayList();
                    for (int i3 = 0; i3 < jSONArrayOptJSONArray3.length(); i3++) {
                        String strOptString = jSONArrayOptJSONArray3.optString(i3);
                        if (z.b(strOptString) && (settingCampaign = CampaignEx.parseSettingCampaign(new JSONObject(strOptString))) != null) {
                            arrayList.add(settingCampaign);
                        }
                    }
                }
                if (arrayList != null) {
                    aVar2.J = arrayList;
                }
                try {
                    JSONArray jSONArrayOptJSONArray4 = jSONObject.optJSONArray("atf");
                    if (jSONArrayOptJSONArray4 != null && jSONArrayOptJSONArray4.length() > 0) {
                        arrayList2 = new ArrayList();
                        for (int i4 = 0; i4 < jSONArrayOptJSONArray4.length(); i4++) {
                            String strOptString2 = jSONArrayOptJSONArray4.optString(i4);
                            if (z.b(strOptString2)) {
                                JSONObject jSONObject2 = new JSONObject(strOptString2);
                                arrayList2.add(new com.mbridge.msdk.foundation.entity.b(jSONObject2.optInt("adtype"), jSONObject2.optString("unitid")));
                            }
                        }
                    }
                    if (arrayList2 != null) {
                        aVar2.K = arrayList2;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                aVar2.aD = jSONObject.optInt("adct", 259200);
                aVar2.U = jSONObject.optInt("pf", 900);
                aVar2.W = jSONObject.optInt("pmax", 20);
                aVar2.aa = jSONObject.optString("pid");
                JSONArray jSONArrayOptJSONArray5 = jSONObject.optJSONArray("pb");
                if (jSONArrayOptJSONArray5 != null && jSONArrayOptJSONArray5.length() > 0) {
                    ArrayList arrayList3 = new ArrayList();
                    for (int i5 = 0; i5 < jSONArrayOptJSONArray5.length(); i5++) {
                        arrayList3.add(jSONArrayOptJSONArray5.optString(i5));
                    }
                    aVar2.V = arrayList3;
                }
                JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("pctrl");
                if (jSONObjectOptJSONObject2 != null) {
                    aVar2.X = jSONObjectOptJSONObject2.optInt("full", 1);
                    aVar2.Y = jSONObjectOptJSONObject2.optInt("add", 1);
                    aVar2.Z = jSONObjectOptJSONObject2.optInt("delete", 1);
                }
                aVar2.aE = jSONObject.optString("confirm_title", "");
                aVar2.aF = jSONObject.optString("confirm_description", "");
                aVar2.aG = jSONObject.optString("confirm_t", "");
                aVar2.aH = jSONObject.optString("confirm_c_rv", "");
                aVar2.aI = jSONObject.optString("confirm_c_play", "");
                aVar2.aJ = jSONObject.optInt("offercacheRate", 0);
                aVar2.aK = jSONObject.optInt("offercachepacing", 21600);
                aVar2.aL = jSONObject.optInt("useexpriedcacheoffer", 2);
                aVar2.aM = jSONObject.optInt("retryoffer", 0);
                aVar2.aN = jSONObject.optInt("mapping_cache_rate", 0);
                aVar2.aO = jSONObject.optInt("tokencachetime", DomainCampaignEx.TTC_CT_DEFAULT_VALUE);
                aVar2.aP = jSONObject.optInt("protect", 0);
                aVar2.aQ = jSONObject.optString("adchoice_icon", "");
                aVar2.aS = jSONObject.optString("adchoice_link", "");
                aVar2.aR = jSONObject.optString("adchoice_size", "");
                aVar2.aU = jSONObject.optString("platform_logo", "");
                aVar2.aT = jSONObject.optString("platform_name", "");
                aVar2.aV = g(jSONObject.optString("cdnate_cfg", ""));
                aVar2.a = jSONObject.optInt("atrqt", 0);
                aVar2.aW = jSONObject.optInt("iupdid", 0);
                aVar2.S = jSONObject.optInt("mcs", 120);
                aVar2.R = jSONObject.optInt("mcto", 10);
                aVar2.T = jSONObject.optString("ab_id", "");
                aVar2.aX = jSONObject.optString("omsdkjs_url", "");
                aVar2.aY = jSONObject.optInt("activeAppStatus", 2);
                aVar2.aZ = jSONObject.optInt("activeAppTime", 7200);
                String strOptString3 = jSONObject.optString("activeAppConfig");
                if (!TextUtils.isEmpty(strOptString3)) {
                    String strB = n.b(strOptString3);
                    if (strB.startsWith("[")) {
                        JSONArray jSONArray = new JSONArray(strB);
                        if (jSONArray.length() > 0) {
                            ArrayList arrayList4 = new ArrayList();
                            for (int i6 = 0; i6 < jSONArray.length(); i6++) {
                                JSONObject jSONObject3 = jSONArray.getJSONObject(i6);
                                if (jSONObject3 != null) {
                                    com.mbridge.msdk.foundation.entity.a aVar3 = new com.mbridge.msdk.foundation.entity.a();
                                    aVar3.a(jSONObject3.optString("pn"));
                                    aVar3.b(jSONObject3.optString("at"));
                                    aVar3.c(jSONObject3.optString("ai"));
                                    aVar3.d(jSONObject3.optString("ac"));
                                    arrayList4.add(aVar3);
                                }
                            }
                            aVar2.ba = arrayList4;
                        }
                    }
                }
                aVar2.bc = jSONObject.optString("mraid_js");
                aVar2.bb = jSONObject.optString("web_env_url");
                int iOptInt = jSONObject.optInt("alrbs", 0);
                if (iOptInt > 2 || iOptInt < 0) {
                    iOptInt = 0;
                }
                aVar2.bd = iOptInt;
                aVar2.be = jSONObject.optBoolean("GDPR_area", false);
                aVar2.bf = jSONObject.optInt("ct", 120);
                JSONArray jSONArrayOptJSONArray6 = jSONObject.optJSONArray("ercd");
                if (jSONArrayOptJSONArray6 != null && jSONArrayOptJSONArray6.length() > 0) {
                    ArrayList<Integer> arrayList5 = new ArrayList<>();
                    for (int i7 = 0; i7 < jSONArrayOptJSONArray6.length(); i7++) {
                        int iOptInt2 = jSONArrayOptJSONArray6.optInt(i7);
                        if (iOptInt2 != 0) {
                            arrayList5.add(Integer.valueOf(iOptInt2));
                        }
                    }
                    if (arrayList5.size() > 0) {
                        arrayList5.add(-1);
                        aVar2.bg = arrayList5;
                    }
                }
                String strOptString4 = jSONObject.optString("hst");
                if (!TextUtils.isEmpty(strOptString4)) {
                    try {
                        JSONObject jSONObject4 = new JSONObject(n.b(strOptString4));
                        Iterator<String> itKeys = jSONObject4.keys();
                        HashMap<String, String> map2 = new HashMap<>();
                        while (itKeys.hasNext()) {
                            String next = itKeys.next();
                            if (!TextUtils.isEmpty(next) && !TextUtils.isEmpty(jSONObject4.optString(next)) && !TextUtils.isEmpty(n.b(jSONObject4.optString(next)))) {
                                map2.put(next, jSONObject4.optString(next));
                            }
                        }
                        if (map2.size() > 0) {
                            aVar2.bh = map2;
                        }
                    } catch (Exception e2) {
                        s.d("SETTING", e2.getMessage());
                    }
                }
                JSONArray jSONArrayOptJSONArray7 = jSONObject.optJSONArray("refactor_switch");
                if (jSONArrayOptJSONArray7 != null && jSONArrayOptJSONArray7.length() > 0) {
                    for (int i8 = 0; i8 < jSONArrayOptJSONArray7.length(); i8++) {
                        JSONObject jSONObject5 = jSONArrayOptJSONArray7.getJSONObject(i8);
                        Iterator<String> itKeys2 = jSONObject5.keys();
                        while (itKeys2.hasNext()) {
                            String next2 = itKeys2.next();
                            if (!TextUtils.isEmpty(next2) && jSONObject5.getBoolean(next2)) {
                                aVar2.au.add(Integer.valueOf(Integer.parseInt(next2)));
                            }
                        }
                    }
                }
                try {
                    int iOptInt3 = jSONObject.optInt("lqcnt", 30);
                    int iOptInt4 = jSONObject.optInt("lqto", 5);
                    int iOptInt5 = jSONObject.optInt("lqswt", 0);
                    int iOptInt6 = jSONObject.optInt("lqtype", 0);
                    aVar2.aq = iOptInt4;
                    aVar2.ap = iOptInt3;
                    aVar2.ar = iOptInt5;
                    aVar2.as = iOptInt6;
                    int i9 = Integer.parseInt(n.b(jSONObject.optString("lqpt")));
                    if (i9 > 0 && i9 < 65535) {
                        aVar2.at = i9;
                        m.a().a(i9);
                    }
                } catch (Exception unused) {
                }
                aVar2.bi = jSONObject.optInt("wvddt", 0);
                aVar2.k = jSONObject.optString("host_setting", com.mbridge.msdk.foundation.same.net.g.d.c().c);
                try {
                    int iOptInt7 = jSONObject.optInt(NotifyType.LIGHTS, 3);
                    boolean z2 = jSONObject.optInt(CampaignEx.JSON_KEY_AD_K, 0) == 1;
                    if (jSONObject.optInt("m", 1) != 1) {
                        z = false;
                    }
                    aVar2.M = iOptInt7;
                    aVar2.L = z2;
                    aVar2.N = z;
                } catch (Exception e3) {
                    s.d("Setting", e3.getMessage());
                }
                return aVar2;
            } catch (Exception e4) {
                e = e4;
                aVar = aVar2;
                e.printStackTrace();
                return aVar;
            }
        } catch (Exception e5) {
            e = e5;
        }
    }

    public final long S() {
        return this.j;
    }

    public final void c(boolean z) {
        this.g = z;
    }

    public static String a(Context context, String str) {
        try {
            a aVarB = b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
            if (aVarB != null && aVarB.h != null) {
                String host = Uri.parse(str).getHost();
                Iterator<Map.Entry<String, String>> it = aVarB.h.entrySet().iterator();
                while (it.hasNext()) {
                    String key = it.next().getKey();
                    if (!TextUtils.isEmpty(host) && host.contains(key)) {
                        String str2 = aVarB.h.get(key);
                        if (TextUtils.isEmpty(str2)) {
                            return "";
                        }
                        String strReplace = str2.replace("{gaid}", o.A());
                        return strReplace.contains("{android_id}") ? o.g(context) != null ? strReplace.replace("{android_id}", o.g(context)) : strReplace : (!strReplace.contains("{android_id_md5_upper}") || o.h(context) == null) ? strReplace : strReplace.replace("{android_id_md5_upper}", o.h(context));
                    }
                }
                return "";
            }
            return "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public final void d(boolean z) {
        this.i = z;
    }

    public final int T() {
        return this.q;
    }

    public final int U() {
        return this.r;
    }

    public static boolean V() {
        try {
            a aVarB = b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
            if (aVarB != null) {
                return aVarB.g;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public final String W() {
        return this.aE;
    }

    public final String X() {
        return this.aF;
    }

    public final String Y() {
        return this.aG;
    }

    public final String Z() {
        return this.aH;
    }

    public final String aa() {
        return this.aI;
    }

    public final void ab() {
        String language = Locale.getDefault().getLanguage();
        if (!((TextUtils.isEmpty(this.aE) || TextUtils.isEmpty(this.aF) || TextUtils.isEmpty(this.aG) || TextUtils.isEmpty(this.aH)) ? false : true)) {
            if (!TextUtils.isEmpty(language) && language.equals("zh")) {
                this.aE = "确认关闭？";
                this.aF = "关闭后您将不会获得任何奖励噢~ ";
                this.aG = "确认关闭";
                this.aH = "继续观看";
            } else {
                this.aE = "Confirm to close? ";
                this.aF = "You will not be rewarded after closing the window";
                this.aG = "Close it";
                this.aH = "Continue";
            }
        }
        if ((TextUtils.isEmpty(this.aE) || TextUtils.isEmpty(this.aF) || TextUtils.isEmpty(this.aG) || TextUtils.isEmpty(this.aI)) ? false : true) {
            return;
        }
        if (!TextUtils.isEmpty(language) && language.equals("zh")) {
            this.aE = "确认关闭？";
            this.aF = "关闭后您将不会获得任何奖励噢~ ";
            this.aG = "确认关闭";
            this.aI = "继续试玩";
            return;
        }
        this.aE = "Confirm to close? ";
        this.aF = "You will not be rewarded after closing the window";
        this.aG = "Close it";
        this.aI = "Continue";
    }

    public final String ac() {
        return this.aQ;
    }

    public final String ad() {
        return this.aR;
    }

    public final String ae() {
        return this.aS;
    }

    public final Map<String, C0102a> af() {
        return this.aV;
    }

    private static Map<String, C0102a> g(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            HashMap map = new HashMap();
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                C0102a c0102a = new C0102a();
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(next);
                if (jSONObjectOptJSONObject != null) {
                    c0102a.a(jSONObjectOptJSONObject);
                }
                map.put(next, c0102a);
            }
            return map;
        } catch (JSONException e) {
            if (MBridgeConstans.DEBUG) {
                e.printStackTrace();
            }
            return null;
        } catch (Exception e2) {
            if (MBridgeConstans.DEBUG) {
                e2.printStackTrace();
            }
            return null;
        }
    }

    public final int ag() {
        return this.ap;
    }

    public final int ah() {
        return this.aq;
    }

    public final int ai() {
        return this.ar;
    }

    public final int aj() {
        return this.as;
    }

    public final boolean ak() {
        return this.L;
    }

    public final int al() {
        return this.M;
    }

    public final boolean am() {
        return this.N;
    }

    /* compiled from: Setting.java */
    /* renamed from: com.mbridge.msdk.b.a$a, reason: collision with other inner class name */
    public static class C0102a {
        private List<String> a;
        private List<String> b;
        private List<String> c;
        private List<String> d;

        public final List<String> a() {
            return this.a;
        }

        public final List<String> b() {
            return this.b;
        }

        public final List<String> c() {
            return this.c;
        }

        public final List<String> d() {
            return this.d;
        }

        public final void a(JSONObject jSONObject) {
            try {
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("x");
                if (jSONArrayOptJSONArray != null) {
                    this.a = i.a(jSONArrayOptJSONArray);
                }
                JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray("y");
                if (jSONArrayOptJSONArray2 != null) {
                    this.b = i.a(jSONArrayOptJSONArray2);
                }
                JSONArray jSONArrayOptJSONArray3 = jSONObject.optJSONArray("width");
                if (jSONArrayOptJSONArray3 != null) {
                    this.c = i.a(jSONArrayOptJSONArray3);
                }
                JSONArray jSONArrayOptJSONArray4 = jSONObject.optJSONArray("height");
                if (jSONArrayOptJSONArray4 != null) {
                    this.d = i.a(jSONArrayOptJSONArray4);
                }
            } catch (Exception e) {
                if (MBridgeConstans.DEBUG) {
                    e.printStackTrace();
                }
            }
        }
    }

    public final String an() {
        return this.aX;
    }

    public final void f(String str) {
        this.aX = str;
    }

    public final int ao() {
        return this.aY;
    }

    public final int ap() {
        return this.aZ;
    }

    public final List<com.mbridge.msdk.foundation.entity.a> aq() {
        return this.ba;
    }

    public final String ar() {
        return this.bb;
    }

    public final String as() {
        return this.bc;
    }

    public final int at() {
        return this.bd;
    }

    public final void I(int i) {
        this.bd = i;
    }

    public final boolean au() {
        return this.be;
    }

    public final void e(boolean z) {
        this.be = z;
    }

    public final int av() {
        return this.bf;
    }

    public final void J(int i) {
        this.bf = i;
    }

    public final ArrayList<Integer> aw() {
        return this.bg;
    }

    public final HashMap<String, String> ax() {
        return this.bh;
    }

    public final int ay() {
        return this.bi;
    }

    public final void K(int i) {
        this.bj = i;
    }

    public final String az() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("cc", this.b);
            jSONObject.put("upal", this.c);
            jSONObject.put("cfc", this.d);
            jSONObject.put("uplc", this.f);
            jSONObject.put("aa", this.g);
            jSONObject.put("cfb", this.i);
            jSONObject.put(CampaignEx.JSON_KEY_PLCT, this.m);
            jSONObject.put("awct", this.n);
            jSONObject.put("rurl", this.l);
            jSONObject.put("ujds", this.p);
            jSONObject.put(CampaignEx.JSON_KEY_PLCTB, this.t);
            jSONObject.put("pcto", this.ax);
            jSONObject.put("tcto", this.ay);
            jSONObject.put("wicon", this.v);
            jSONObject.put("wreq", this.w);
            jSONObject.put("apk_toast", this.G);
            jSONObject.put("mv_wildcard", this.H);
            jSONObject.put("is_startup_crashsystem", this.I);
            jSONObject.put("sfct", this.aB);
            jSONObject.put("pcrn", this.u);
            jSONObject.put("pcct", this.C);
            jSONObject.put("dlrf", this.A);
            jSONObject.put("dlrfct", this.B);
            jSONObject.put("upaid", this.aA);
            jSONObject.put("pctn", this.aC);
            jSONObject.put("ilrf", this.aw);
            jSONObject.put(CampaignEx.JSON_KEY_ST_IEX, this.F);
            jSONObject.put("plc", this.D);
            jSONObject.put("dut", this.E);
            jSONObject.put("adct", this.aD);
            jSONObject.put("dlapk", this.av);
            jSONObject.put("pf", this.U);
            jSONObject.put("pmax", this.W);
            jSONObject.put("pid", this.aa);
            jSONObject.put("full", this.X);
            jSONObject.put("add", this.Y);
            jSONObject.put("delete", this.Z);
            jSONObject.put("upmi", this.az);
            jSONObject.put("upgd", this.ad);
            jSONObject.put("upsrl", this.ac);
            jSONObject.put("updevid", this.ae);
            jSONObject.put("sc", this.af);
            jSONObject.put("up_tips", this.ag);
            jSONObject.put("iseu", this.ai);
            jSONObject.put("up_tips_url", this.ah);
            jSONObject.put("jmc", this.aj);
            jSONObject.put("jmct", this.al);
            jSONObject.put("atrqt", this.a);
            jSONObject.put("omsdkjs_url", this.aX);
            jSONObject.put("mcs", this.S);
            jSONObject.put("mcto", this.R);
            jSONObject.put("GDPR_area", this.be);
            jSONObject.put("alrbs", this.bd);
            jSONObject.put("ct", this.bf);
            jSONObject.put("isDefault", this.bj);
            return jSONObject.toString();
        } catch (Throwable th) {
            s.d("Setting", th.getMessage());
            return null;
        }
    }
}
