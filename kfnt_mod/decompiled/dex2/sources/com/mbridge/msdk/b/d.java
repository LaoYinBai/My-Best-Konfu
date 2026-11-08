package com.mbridge.msdk.b;

import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.DomainCampaignEx;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: UnitSetting.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class d {
    public static String a;
    private long A;
    private long B;
    private int D;
    private int E;
    private int F;
    private int N;
    private int O;
    private String P;
    private String Q;
    private int b;
    private int c;
    private int d;
    private String f;
    private String g;
    private List<Integer> h;
    private long i;
    private int k;
    private List<Integer> l;
    private int m;
    private int n;
    private int o;
    private int p;
    private String r;
    private int s;
    private int v;
    private int w;
    private long x;
    private String y;
    private int z;
    private int e = 30;
    private int j = 1;
    private double q = 1.0d;
    private int t = 1;
    private int u = 5000;
    private int C = 60;
    private String G = "";
    private int H = 10;
    private int I = 60;
    private String J = "";
    private int K = 1;
    private int L = 100;
    private int M = 0;

    public final String a() {
        return this.J;
    }

    public final void a(int i) {
        this.E = i;
    }

    public final void b(int i) {
        this.F = i;
    }

    public final int b() {
        return this.C;
    }

    public final int c() {
        return this.D;
    }

    public final int d() {
        return this.O;
    }

    public final double e() {
        return this.q;
    }

    public final String f() {
        return this.r;
    }

    public final int g() {
        return this.L;
    }

    public final int h() {
        return this.M;
    }

    public final int i() {
        return this.N;
    }

    public final long j() {
        return this.x;
    }

    public final int k() {
        return this.v;
    }

    public final void c(int i) {
        this.v = i;
    }

    public final int l() {
        return this.w;
    }

    public final int m() {
        if (this.e <= 0 || this.e > 100) {
            this.e = 30;
        }
        return this.e;
    }

    public static d n() {
        return new d();
    }

    public final int o() {
        return this.p;
    }

    public final int p() {
        return this.t;
    }

    public final int q() {
        return this.u;
    }

    public final int r() {
        return this.n;
    }

    public final int s() {
        return this.o;
    }

    public final String toString() {
        String str = "";
        if (this.h != null && this.h.size() > 0) {
            Iterator<Integer> it = this.h.iterator();
            while (it.hasNext()) {
                str = str + it.next() + ",";
            }
        }
        return "offset = " + this.j + " unitId = " + this.f + " fbPlacementId = " + this.g + str;
    }

    public final void a(String str) {
        this.f = str;
    }

    public final List<Integer> t() {
        return this.h;
    }

    public final List<Integer> u() {
        return this.l;
    }

    public static d b(String str) {
        d dVar = null;
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            d dVar2 = new d();
            try {
                JSONObject jSONObject = new JSONObject(str);
                dVar2.f = jSONObject.optString("unitId");
                a = jSONObject.optString("atzu");
                dVar2.J = jSONObject.optString("ab_id");
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("adSourceList");
                if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                        arrayList.add(Integer.valueOf(jSONArrayOptJSONArray.optInt(i)));
                    }
                    dVar2.h = arrayList;
                }
                JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray("ad_source_timeout");
                if (jSONArrayOptJSONArray2 != null && jSONArrayOptJSONArray2.length() > 0) {
                    ArrayList arrayList2 = new ArrayList();
                    for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                        arrayList2.add(Integer.valueOf(jSONArrayOptJSONArray2.optInt(i2)));
                    }
                    dVar2.l = arrayList2;
                }
                dVar2.m = jSONObject.optInt("tpqn");
                dVar2.n = jSONObject.optInt("aqn");
                dVar2.o = jSONObject.optInt(RemoteMessageConst.Notification.CLICK_ACTION);
                dVar2.p = jSONObject.optInt("wt");
                dVar2.t = jSONObject.optInt("iscasf", 1);
                dVar2.u = jSONObject.optInt("spmxrt", 5000);
                dVar2.k = jSONObject.optInt(DomainCampaignEx.JSON_KEY_TTC_TYPE);
                dVar2.g = jSONObject.optString("fbPlacementId");
                dVar2.i = jSONObject.optLong("current_time");
                dVar2.j = jSONObject.optInt("offset");
                dVar2.P = jSONObject.optString("admobUnitId");
                dVar2.Q = jSONObject.optString("myTargetSlotId");
                dVar2.x = jSONObject.optLong("dlct", 3600L);
                dVar2.v = jSONObject.optInt("autoplay", 0);
                dVar2.w = jSONObject.optInt("dlnet", 2);
                dVar2.y = jSONObject.optString("no_offer");
                dVar2.z = jSONObject.optInt("cb_type");
                dVar2.A = jSONObject.optLong("clct", 86400L);
                dVar2.B = jSONObject.optLong("clcq", 300L);
                dVar2.L = jSONObject.optInt(CampaignEx.JSON_KEY_READY_RATE, 100);
                dVar2.M = jSONObject.optInt("cd_rate", 0);
                dVar2.N = jSONObject.optInt("content", 1);
                dVar2.O = jSONObject.optInt("impt", 0);
                dVar2.q = jSONObject.optDouble("cbp", 1.0d);
                dVar2.s = jSONObject.optInt("icon_type", 1);
                dVar2.r = jSONObject.optString("no_ads_url", "");
                dVar2.b = jSONObject.optInt("playclosebtn_tm", -1);
                dVar2.c = jSONObject.optInt("play_ctdown", 0);
                dVar2.d = jSONObject.optInt("close_alert", 0);
                dVar2.e = jSONObject.optInt("intershowlimit", 30);
                dVar2.C = jSONObject.optInt("refreshFq", 60);
                dVar2.D = jSONObject.optInt("closeBtn", 0);
                int iOptInt = jSONObject.optInt("tmorl", 1);
                if (iOptInt > 2 || iOptInt <= 0) {
                    iOptInt = 1;
                }
                dVar2.K = iOptInt;
                dVar2.G = jSONObject.optString("placementid", "");
                dVar2.H = jSONObject.optInt("ltafemty", 10);
                dVar2.I = jSONObject.optInt("ltorwc", 60);
                return dVar2;
            } catch (Exception e) {
                e = e;
                dVar = dVar2;
                e.printStackTrace();
                return dVar;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public final JSONObject v() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.h != null && this.h.size() > 0) {
                int size = this.h.size();
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < size; i++) {
                    jSONArray.put(this.h.get(i));
                }
                jSONObject.put("adSourceList", jSONArray);
            }
            if (this.l != null && this.l.size() > 0) {
                int size2 = this.l.size();
                JSONArray jSONArray2 = new JSONArray();
                for (int i2 = 0; i2 < size2; i2++) {
                    jSONArray2.put(this.l.get(i2));
                }
                jSONObject.put("ad_source_timeout", jSONArray2);
            }
            jSONObject.put("tpqn", this.m);
            jSONObject.put("aqn", this.n);
            jSONObject.put(RemoteMessageConst.Notification.CLICK_ACTION, this.o);
            jSONObject.put("wt", this.p);
            jSONObject.put(DomainCampaignEx.JSON_KEY_TTC_TYPE, this.k);
            jSONObject.put("fbPlacementId", this.g);
            jSONObject.put("current_time", this.i);
            jSONObject.put("offset", this.j);
            jSONObject.put("admobUnitId", this.P);
            jSONObject.put("myTargetSlotId", this.Q);
            jSONObject.put("dlct", this.x);
            jSONObject.put("autoplay", this.v);
            jSONObject.put("dlnet", this.w);
            jSONObject.put("no_offer", this.y);
            jSONObject.put("cb_type", this.z);
            jSONObject.put("clct", this.A);
            jSONObject.put("clcq", this.B);
            jSONObject.put(CampaignEx.JSON_KEY_READY_RATE, this.L);
            jSONObject.put("content", this.N);
            jSONObject.put("impt", this.O);
            jSONObject.put("cbp", this.q);
            jSONObject.put("icon_type", this.s);
            jSONObject.put("no_ads_url", this.r);
            jSONObject.put("playclosebtn_tm", this.b);
            jSONObject.put("play_ctdown", this.c);
            jSONObject.put("close_alert", this.d);
            jSONObject.put("closeBtn", this.D);
            jSONObject.put("refreshFq", this.C);
            jSONObject.put("countdown", this.F);
            jSONObject.put("allowSkip", this.E);
            jSONObject.put("tmorl", this.K);
            jSONObject.put("unitId", this.f);
            jSONObject.put("placementid", this.G);
            jSONObject.put("ltafemty", this.H);
            jSONObject.put("ltorwc", this.I);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public final long w() {
        return this.i;
    }

    public final int x() {
        return this.j;
    }

    public static d c(String str) {
        d dVarN = n();
        dVarN.v = 0;
        return dVarN;
    }

    public static d d(String str) {
        d dVar = new d();
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(8);
        arrayList2.add(8);
        dVar.k = 2;
        dVar.j = 1;
        dVar.f = str;
        dVar.h = arrayList;
        dVar.l = arrayList2;
        dVar.m = 1;
        dVar.o = -2;
        dVar.n = -2;
        dVar.p = 5;
        dVar.x = 3600L;
        dVar.w = 2;
        dVar.v = 1;
        dVar.L = 100;
        dVar.M = 0;
        dVar.N = 1;
        dVar.O = 0;
        dVar.C = 60;
        dVar.H = 10;
        dVar.I = 60;
        return dVar;
    }

    public static d e(String str) {
        d dVar = new d();
        try {
            ArrayList arrayList = new ArrayList();
            arrayList.add(1);
            dVar.h = arrayList;
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(30);
            dVar.l = arrayList2;
            dVar.f = str;
            dVar.k = 2;
            dVar.j = 1;
            dVar.m = 1;
            dVar.o = -2;
            dVar.n = -2;
            dVar.p = 5;
            dVar.x = 3600L;
            dVar.w = 2;
            dVar.v = 3;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dVar;
    }

    public static d f(String str) {
        d dVar = new d();
        try {
            dVar.f = str;
            dVar.k = 2;
            dVar.j = 1;
            dVar.m = 1;
            dVar.p = 5;
            dVar.k = 2;
            dVar.o = 1;
            dVar.n = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dVar;
    }

    public static d g(String str) {
        d dVarD = d(str);
        try {
            dVarD.q = 1.0d;
            dVarD.s = 1;
            dVarD.r = "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dVarD;
    }

    public final void h(String str) {
        this.G = str;
    }

    public final int y() {
        return this.H;
    }

    public final int z() {
        return this.I;
    }
}
