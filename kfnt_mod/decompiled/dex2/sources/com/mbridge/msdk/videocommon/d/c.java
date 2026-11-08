package com.mbridge.msdk.videocommon.d;

import android.text.TextUtils;
import com.heytap.mcssdk.mode.Message;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.DomainCampaignEx;
import com.mbridge.msdk.foundation.entity.RewardPlus;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: RewardUnitSetting.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class c {
    private static i O;
    public static String a;
    private int M;
    private int P;
    private String b;
    private String c;
    private List<com.mbridge.msdk.videocommon.b.b> d;
    private long e;
    private com.mbridge.msdk.videocommon.b.c u;
    private ArrayList<Integer> z;
    private int f = -1;
    private int g = 0;
    private int h = 0;
    private int i = 1;
    private int j = 1;
    private int k = 1;
    private int l = 1;
    private double m = 1.0d;
    private int n = 2;
    private int o = 5;
    private int p = 1;
    private int q = 3;
    private int r = 80;
    private int s = 100;
    private int t = 0;
    private double v = 1.0d;
    private int w = -1;
    private int x = 2;
    private double y = 1.0d;
    private int A = 3;
    private int B = 1;
    private int C = 0;
    private int D = 10;
    private int E = 60;
    private String F = "";
    private int G = 0;
    private int H = 70;
    private int I = 0;
    private int J = -1;
    private int K = -1;
    private int L = -1;
    private int N = 20;
    private int Q = 0;
    private int R = 1;
    private String S = "";
    private int T = 1;
    private String U = "";
    private int V = 1;
    private String W = "Virtual Item";
    private int X = 0;
    private int Y = 1;
    private int Z = 60;

    public final String a() {
        return this.F;
    }

    public final int b() {
        return this.D;
    }

    public final int c() {
        return this.E;
    }

    public final int d() {
        return this.C;
    }

    public final void a(int i) {
        this.C = i;
    }

    public final void a(ArrayList<Integer> arrayList) {
        this.z = arrayList;
    }

    public final int e() {
        return this.G;
    }

    public final void b(int i) {
        this.G = i;
    }

    public final int f() {
        return this.I;
    }

    public final void c(int i) {
        this.I = i;
    }

    public final int g() {
        return this.J;
    }

    public final void d(int i) {
        this.J = i;
    }

    public final int h() {
        return this.K;
    }

    public final void e(int i) {
        this.K = i;
    }

    public final int i() {
        return this.L;
    }

    public final void f(int i) {
        this.L = i;
    }

    public final int j() {
        return this.f;
    }

    public final int k() {
        return this.h;
    }

    public final int l() {
        return this.T;
    }

    private void B(int i) {
        if (i <= 0) {
            this.T = 1;
        } else {
            this.T = i;
        }
    }

    public final String m() {
        return this.W;
    }

    private void d(String str) {
        if (TextUtils.isEmpty(str)) {
            this.W = this.S;
        } else {
            this.W = str;
        }
    }

    public final int n() {
        return this.N;
    }

    public final int o() {
        return this.M;
    }

    public final void g(int i) {
        this.M = i;
    }

    public final int p() {
        return this.P;
    }

    public final void h(int i) {
        this.P = i;
    }

    public final void i(int i) {
        this.q = i;
    }

    public final void j(int i) {
        this.r = i;
    }

    public final int q() {
        return this.s;
    }

    public final void k(int i) {
        this.s = i;
    }

    public final int r() {
        return this.t;
    }

    public final void l(int i) {
        this.t = i;
    }

    public final int s() {
        return this.j;
    }

    public final void m(int i) {
        this.j = i;
    }

    public final int t() {
        return this.k;
    }

    public final void n(int i) {
        this.k = i;
    }

    public final void o(int i) {
        this.l = i;
    }

    public final void a(double d) {
        this.m = d;
    }

    public final void p(int i) {
        this.n = i;
    }

    public final int u() {
        return this.o;
    }

    public final void q(int i) {
        this.o = i;
    }

    public final int v() {
        return this.p;
    }

    public final void r(int i) {
        this.p = i;
    }

    public final void s(int i) {
        this.i = i;
    }

    public final long w() {
        return this.e;
    }

    public final void a(String str) {
        this.b = str;
    }

    public final List<com.mbridge.msdk.videocommon.b.b> x() {
        return this.d;
    }

    public final void a(List<com.mbridge.msdk.videocommon.b.b> list) {
        this.d = list;
    }

    public final com.mbridge.msdk.videocommon.b.c y() {
        return this.u;
    }

    public final double z() {
        return this.v;
    }

    public final int A() {
        return this.x;
    }

    public final void t(int i) {
        this.x = i;
    }

    public final double B() {
        return this.y;
    }

    public final void b(double d) {
        this.y = d;
    }

    public final int C() {
        return this.B;
    }

    public final void u(int i) {
        this.B = i;
    }

    public final boolean v(int i) {
        if (this.z == null || this.z.size() <= 0) {
            return false;
        }
        return this.z.contains(Integer.valueOf(i));
    }

    public final String D() {
        return this.c;
    }

    public final void b(String str) {
        this.c = str;
    }

    public final int E() {
        return this.A;
    }

    public final void w(int i) {
        this.A = i;
    }

    public final Queue<Integer> F() {
        LinkedList linkedList;
        Exception e;
        try {
        } catch (Exception e2) {
            linkedList = null;
            e = e2;
        }
        if (this.d == null || this.d.size() <= 0) {
            return null;
        }
        linkedList = new LinkedList();
        for (int i = 0; i < this.d.size(); i++) {
            try {
                linkedList.add(Integer.valueOf(this.d.get(i).b()));
            } catch (Exception e3) {
                e = e3;
                e.printStackTrace();
                return linkedList;
            }
        }
        return linkedList;
    }

    public static c c(String str) {
        JSONObject jSONObjectOptJSONObject;
        if (O == null) {
            O = i.a(com.mbridge.msdk.foundation.controller.a.f().j());
        }
        c cVar = null;
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("unitSetting");
                if (jSONArrayOptJSONArray == null || (jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(0)) == null) {
                    return null;
                }
                String strOptString = jSONObjectOptJSONObject.optString("unitId");
                if (TextUtils.isEmpty(strOptString)) {
                    return null;
                }
                c cVar2 = new c();
                try {
                    List<com.mbridge.msdk.videocommon.b.b> listA = com.mbridge.msdk.videocommon.b.b.a(jSONObjectOptJSONObject.optJSONArray("adSourceList"));
                    cVar2.b = strOptString;
                    cVar2.d = listA;
                    cVar2.i = jSONObjectOptJSONObject.optInt("callbackType");
                    int iOptInt = jSONObjectOptJSONObject.optInt("aqn", 1);
                    if (iOptInt <= 0) {
                        iOptInt = 1;
                    }
                    cVar2.j = iOptInt;
                    int iOptInt2 = jSONObjectOptJSONObject.optInt(RemoteMessageConst.Notification.CLICK_ACTION, 1);
                    if (iOptInt2 < 0) {
                        iOptInt2 = 1;
                    }
                    cVar2.k = iOptInt2;
                    cVar2.l = jSONObjectOptJSONObject.optInt("vcn", 5);
                    cVar2.m = jSONObjectOptJSONObject.optDouble("cbp", 1.0d);
                    cVar2.n = jSONObjectOptJSONObject.optInt(DomainCampaignEx.JSON_KEY_TTC_TYPE, 2);
                    cVar2.o = jSONObjectOptJSONObject.optInt("offset", 5);
                    cVar2.p = jSONObjectOptJSONObject.optInt("dlnet", 1);
                    cVar2.P = jSONObjectOptJSONObject.optInt("endscreen_type", 2);
                    cVar2.q = jSONObjectOptJSONObject.optInt("tv_start", 3);
                    cVar2.r = jSONObjectOptJSONObject.optInt("tv_end", 80);
                    cVar2.s = jSONObjectOptJSONObject.optInt(CampaignEx.JSON_KEY_READY_RATE, 100);
                    cVar2.t = jSONObjectOptJSONObject.optInt("cd_rate", 0);
                    cVar2.e = jSONObject.optLong("current_time");
                    cVar2.G = jSONObjectOptJSONObject.optInt("orientation", 0);
                    cVar2.I = jSONObjectOptJSONObject.optInt("daily_play_cap", 0);
                    cVar2.J = jSONObjectOptJSONObject.optInt("video_skip_time", -1);
                    cVar2.K = jSONObjectOptJSONObject.optInt("video_skip_result", 2);
                    cVar2.L = jSONObjectOptJSONObject.optInt("video_interactive_type", -1);
                    cVar2.M = jSONObjectOptJSONObject.optInt("close_button_delay", 1);
                    cVar2.f = jSONObjectOptJSONObject.optInt("playclosebtn_tm", -1);
                    cVar2.g = jSONObjectOptJSONObject.optInt("play_ctdown", 0);
                    cVar2.h = jSONObjectOptJSONObject.optInt("close_alert", 0);
                    cVar2.N = jSONObjectOptJSONObject.optInt("rdrct", 20);
                    cVar2.H = jSONObjectOptJSONObject.optInt("load_global_timeout", 70);
                    cVar2.w = jSONObjectOptJSONObject.optInt("rfpv", -1);
                    cVar2.v = jSONObjectOptJSONObject.optDouble("vdcmp", 1.0d);
                    cVar2.u = com.mbridge.msdk.videocommon.b.c.a(jSONObjectOptJSONObject.optJSONObject("cbprule"));
                    cVar2.x = jSONObjectOptJSONObject.optInt("ccbprule", 2);
                    cVar2.y = jSONObjectOptJSONObject.optDouble("ccbp", 1.0d);
                    a = jSONObjectOptJSONObject.optString("atzu");
                    JSONArray jSONArrayOptJSONArray2 = jSONObjectOptJSONObject.optJSONArray("atl_type");
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    try {
                        if (jSONArrayOptJSONArray2 != null) {
                            for (int i = 0; i < jSONArrayOptJSONArray2.length(); i++) {
                                arrayList.add(Integer.valueOf(jSONArrayOptJSONArray2.getInt(i)));
                            }
                        } else {
                            arrayList.add(4);
                            arrayList.add(6);
                        }
                        cVar2.z = arrayList;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    int iOptInt3 = jSONObjectOptJSONObject.optInt("atl_dyt", 0);
                    if (iOptInt3 <= 0) {
                        iOptInt3 = 3;
                    }
                    cVar2.A = iOptInt3;
                    int iOptInt4 = jSONObjectOptJSONObject.optInt("tmorl", 1);
                    if (iOptInt4 > 2 || iOptInt4 <= 0) {
                        iOptInt4 = 1;
                    }
                    cVar2.B = iOptInt4;
                    cVar2.C = (cVar2.m != 1.0d && new Random().nextDouble() > cVar2.m) ? 1 : 0;
                    cVar2.c = jSONObjectOptJSONObject.optString("placementid");
                    cVar2.D = jSONObjectOptJSONObject.optInt("ltafemty", 10);
                    cVar2.E = jSONObjectOptJSONObject.optInt("ltorwc", 60);
                    cVar2.F = jSONObjectOptJSONObject.optString("ab_id");
                    cVar2.Q = jSONObjectOptJSONObject.optInt(RewardPlus.AMOUNT_MAX, 0);
                    cVar2.R = jSONObjectOptJSONObject.optInt(RewardPlus.CALLBACK_RULE, 1);
                    cVar2.S = jSONObjectOptJSONObject.optString(RewardPlus.VIRTUAL_CURRENCY, "");
                    cVar2.B(jSONObjectOptJSONObject.optInt(RewardPlus.AMOUNT, 1));
                    cVar2.U = jSONObjectOptJSONObject.optString("icon", "");
                    cVar2.V = jSONObjectOptJSONObject.optInt(RewardPlus.CURRENCY_ID, 1);
                    cVar2.d(jSONObjectOptJSONObject.optString("name", "Virtual Item"));
                    cVar2.Y = jSONObjectOptJSONObject.optInt("video_error_rule", 1);
                    cVar2.Z = jSONObjectOptJSONObject.optInt("loadtmo", 60);
                    return cVar2;
                } catch (Exception e2) {
                    e = e2;
                    cVar = cVar2;
                    e.printStackTrace();
                    return cVar;
                }
            } catch (Exception e3) {
                e = e3;
            }
        }
        return cVar;
    }

    public final JSONObject G() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("unitId", this.b);
            jSONObject.put("callbackType", this.i);
            if (this.d != null && this.d.size() > 0) {
                JSONArray jSONArray = new JSONArray();
                for (com.mbridge.msdk.videocommon.b.b bVar : this.d) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("id", bVar.a());
                    jSONObject2.put("timeout", bVar.b());
                    jSONArray.put(jSONObject2);
                }
                jSONObject.put("adSourceList", jSONArray);
            }
            jSONObject.put("aqn", this.j);
            jSONObject.put(RemoteMessageConst.Notification.CLICK_ACTION, this.k);
            jSONObject.put("vcn", this.l);
            jSONObject.put("cbp", this.m);
            jSONObject.put(DomainCampaignEx.JSON_KEY_TTC_TYPE, this.n);
            jSONObject.put("offset", this.o);
            jSONObject.put("dlnet", this.p);
            jSONObject.put("tv_start", this.q);
            jSONObject.put("tv_end", this.r);
            jSONObject.put(CampaignEx.JSON_KEY_READY_RATE, this.s);
            jSONObject.put("endscreen_type", this.P);
            jSONObject.put("daily_play_cap", this.I);
            jSONObject.put("video_skip_time", this.J);
            jSONObject.put("video_skip_result", this.K);
            jSONObject.put("video_interactive_type", this.L);
            jSONObject.put("orientation", this.G);
            jSONObject.put("close_button_delay", this.M);
            jSONObject.put("playclosebtn_tm", this.f);
            jSONObject.put("play_ctdown", this.g);
            jSONObject.put("close_alert", this.h);
            jSONObject.put("rfpv", this.w);
            jSONObject.put("vdcmp", this.v);
            if (this.u != null) {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put(Message.TYPE, this.u.a());
                JSONArray jSONArray2 = new JSONArray();
                Iterator<Integer> it = this.u.b().iterator();
                while (it.hasNext()) {
                    jSONArray2.put(it.next());
                }
                jSONObject3.put("value", jSONArray2);
                jSONObject.put("cbprule", jSONObject3);
            }
            jSONObject.put("ccbprule", this.x);
            jSONObject.put("ccbp", this.y);
            JSONArray jSONArray3 = new JSONArray();
            if (this.z != null) {
                if (this.z.size() > 0) {
                    Iterator<Integer> it2 = this.z.iterator();
                    while (it2.hasNext()) {
                        jSONArray3.put(it2.next());
                    }
                }
                jSONObject.put("atl_type", jSONArray3);
            }
            jSONObject.put("atl_dyt", this.A);
            jSONObject.put("tmorl", this.B);
            jSONObject.put("hitcbp", this.C);
            jSONObject.put("placementid", this.c);
            jSONObject.put("ltafemty", this.D);
            jSONObject.put("ltorwc", this.E);
            jSONObject.put(RewardPlus.AMOUNT_MAX, this.Q);
            jSONObject.put(RewardPlus.CALLBACK_RULE, this.R);
            jSONObject.put(RewardPlus.VIRTUAL_CURRENCY, this.S);
            jSONObject.put(RewardPlus.AMOUNT, this.T);
            jSONObject.put("icon", this.U);
            jSONObject.put(RewardPlus.CURRENCY_ID, this.V);
            jSONObject.put("name", this.W);
            jSONObject.put("isDefault", this.X);
            jSONObject.put("video_error_rule", this.Y);
            jSONObject.put("loadtmo", this.Z);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public static c a(JSONObject jSONObject) {
        c cVar;
        if (jSONObject == null) {
            return null;
        }
        try {
            cVar = new c();
        } catch (Exception e) {
            e = e;
            cVar = null;
        }
        try {
            cVar.d = com.mbridge.msdk.videocommon.b.b.a(jSONObject.optJSONArray("adSourceList"));
            cVar.i = jSONObject.optInt("callbackType");
            int iOptInt = jSONObject.optInt("aqn", 1);
            if (iOptInt <= 0) {
                iOptInt = 1;
            }
            cVar.j = iOptInt;
            int iOptInt2 = jSONObject.optInt(RemoteMessageConst.Notification.CLICK_ACTION, 1);
            if (iOptInt2 < 0) {
                iOptInt2 = 1;
            }
            cVar.k = iOptInt2;
            cVar.l = jSONObject.optInt("vcn", 5);
            cVar.m = jSONObject.optDouble("cbp", 1.0d);
            cVar.n = jSONObject.optInt(DomainCampaignEx.JSON_KEY_TTC_TYPE, 2);
            cVar.o = jSONObject.optInt("offset", 5);
            cVar.p = jSONObject.optInt("dlnet", 2);
            cVar.P = jSONObject.optInt("endscreen_type", 2);
            cVar.q = jSONObject.optInt("tv_start", 3);
            cVar.r = jSONObject.optInt("tv_end", 80);
            cVar.s = jSONObject.optInt(CampaignEx.JSON_KEY_READY_RATE, 100);
            cVar.e = jSONObject.optLong("current_time");
            cVar.G = jSONObject.optInt("orientation", 0);
            cVar.I = jSONObject.optInt("daily_play_cap", 0);
            cVar.J = jSONObject.optInt("video_skip_time", -1);
            cVar.K = jSONObject.optInt("video_skip_result", 2);
            cVar.L = jSONObject.optInt("video_interactive_type", -1);
            cVar.M = jSONObject.optInt("close_button_delay", 1);
            cVar.f = jSONObject.optInt("playclosebtn_tm", -1);
            cVar.g = jSONObject.optInt("play_ctdown", 0);
            cVar.h = jSONObject.optInt("close_alert", 0);
            cVar.N = jSONObject.optInt("rdrct", 20);
            cVar.w = jSONObject.optInt("rfpv", -1);
            cVar.v = jSONObject.optDouble("vdcmp", 1.0d);
            cVar.u = com.mbridge.msdk.videocommon.b.c.a(jSONObject.optJSONObject("cbprule"));
            cVar.x = jSONObject.optInt("ccbprule", 2);
            cVar.y = jSONObject.optDouble("ccbp", 1.0d);
            cVar.H = jSONObject.optInt("load_global_timeout", 70);
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("atl_type");
            ArrayList<Integer> arrayList = new ArrayList<>();
            try {
                if (jSONArrayOptJSONArray != null) {
                    for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                        arrayList.add(Integer.valueOf(jSONArrayOptJSONArray.getInt(i)));
                    }
                } else {
                    arrayList.add(4);
                    arrayList.add(6);
                }
                cVar.z = arrayList;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            cVar.A = jSONObject.optInt("atl_dyt", 3);
            int iOptInt3 = jSONObject.optInt("tmorl", 1);
            if (iOptInt3 > 2 || iOptInt3 <= 0) {
                iOptInt3 = 1;
            }
            cVar.B = iOptInt3;
            cVar.C = (cVar.m != 1.0d && new Random().nextDouble() > cVar.m) ? 1 : 0;
            cVar.c = jSONObject.optString("placementid");
            cVar.D = jSONObject.optInt("ltafemty", 10);
            cVar.E = jSONObject.optInt("ltorwc", 60);
            cVar.F = jSONObject.optString("ab_id");
            cVar.Q = jSONObject.optInt(RewardPlus.AMOUNT_MAX, 0);
            cVar.R = jSONObject.optInt(RewardPlus.CALLBACK_RULE, 1);
            cVar.S = jSONObject.optString(RewardPlus.VIRTUAL_CURRENCY, "");
            cVar.B(jSONObject.optInt(RewardPlus.AMOUNT, 1));
            cVar.U = jSONObject.optString("icon", "");
            cVar.V = jSONObject.optInt(RewardPlus.CURRENCY_ID, 1);
            cVar.d(jSONObject.optString("name", "Virtual Item"));
            cVar.Y = jSONObject.optInt("video_error_rule", 1);
            cVar.Z = jSONObject.optInt("loadtmo", 60);
            return cVar;
        } catch (Exception e3) {
            e = e3;
            e.printStackTrace();
            return cVar;
        }
    }

    public final int H() {
        return this.H;
    }

    public final void x(int i) {
        this.H = i;
    }

    public final void y(int i) {
        this.X = i;
    }

    public final int I() {
        return this.Y;
    }

    public final void z(int i) {
        this.Y = i;
    }

    public final int J() {
        return this.Z;
    }

    public final void A(int i) {
        this.Z = i;
    }
}
