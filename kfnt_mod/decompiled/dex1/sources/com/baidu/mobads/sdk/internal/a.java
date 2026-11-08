package com.baidu.mobads.sdk.internal;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    public static final String f3089a = "none";

    /* renamed from: b, reason: collision with root package name */
    public static final String f3090b = "text";

    /* renamed from: c, reason: collision with root package name */
    public static final String f3091c = "static_image";
    public static final String d = "gif";
    public static final String e = "rich_media";
    public static final String f = "html";
    public static final String g = "hybrid";
    public static final String h = "video";
    private static final long i = 1750000;
    private String A;
    private int B;
    private String C;
    private int D;
    private int E;
    private int F;
    private int G;
    private int H;
    private String I;
    private List<String> K;
    private JSONObject L;
    private long M;
    private long N;
    private String O;
    private String P;
    private String Q;
    private String R;
    private String S;
    private String T;
    private String U;
    private JSONObject V;
    private int W;
    private int X;
    private int Y;
    private List<String> Z;
    private String j;
    private String k;
    private String l;
    private String m;
    private int n;
    private int o;
    private String p;
    private long q;
    private int r;
    private String t;
    private String u;
    private String v;
    private String w;
    private String x;
    private String y;
    private String z;
    private int s = 1;
    private String J = f3089a;

    /* JADX WARN: Removed duplicated region for block: B:40:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0152 A[Catch: Throwable -> 0x0183, JSONException -> 0x0188, TryCatch #3 {JSONException -> 0x0188, Throwable -> 0x0183, blocks: (B:5:0x0073, B:7:0x007f, B:9:0x0090, B:10:0x00ad, B:12:0x00b9, B:14:0x00c6, B:16:0x00ce, B:17:0x00d2, B:19:0x00da, B:21:0x00e2, B:23:0x00ea, B:27:0x00f3, B:29:0x00fd, B:30:0x0120, B:32:0x0125, B:33:0x0131, B:35:0x0139, B:37:0x013f, B:39:0x0149, B:42:0x0152, B:44:0x0162, B:46:0x0169, B:47:0x0171, B:49:0x0177), top: B:98:0x0073 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.baidu.mobads.sdk.internal.a a(org.json.JSONObject r11) {
        /*
            Method dump skipped, instructions count: 808
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobads.sdk.internal.a.a(org.json.JSONObject):com.baidu.mobads.sdk.internal.a");
    }

    public static List<a> a(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                try {
                    arrayList.add(a(jSONArray.getJSONObject(i2)));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return arrayList;
    }

    public String a() {
        return this.j;
    }

    public String b() {
        return this.k;
    }

    public String c() {
        return this.l;
    }

    public String d() {
        return this.m;
    }

    public int e() {
        return this.n;
    }

    public int f() {
        return this.o;
    }

    public String g() {
        return this.p;
    }

    public String h() {
        return this.P;
    }

    public String i() {
        return this.Q;
    }

    public long j() {
        return this.q;
    }

    public int k() {
        return this.r;
    }

    public int l() {
        return this.s;
    }

    public String m() {
        return this.u;
    }

    public String n() {
        return this.v;
    }

    public String o() {
        return this.w;
    }

    public int p() {
        return this.B;
    }

    public String q() {
        return this.C;
    }

    public int r() {
        return this.D;
    }

    public int s() {
        return this.E;
    }

    public int t() {
        return this.F;
    }

    public int u() {
        return this.G;
    }

    public int v() {
        return this.H;
    }

    public String w() {
        return this.J;
    }

    public long x() {
        return this.M;
    }

    public String y() {
        return this.t;
    }

    public String z() {
        return this.y;
    }

    public String A() {
        return this.x;
    }

    public String B() {
        return this.z;
    }

    public String C() {
        return this.A;
    }

    public long D() {
        return this.N;
    }

    public List<String> E() {
        return this.K;
    }

    public String F() {
        return this.O;
    }

    public JSONObject G() {
        return this.L;
    }

    public String H() {
        return this.R;
    }

    public String I() {
        return this.S;
    }

    public String J() {
        return this.T;
    }

    public String K() {
        return (this.U == null || this.U.length() <= 4) ? this.U : "";
    }

    public int L() {
        return this.Y;
    }

    public List<String> M() {
        return this.Z;
    }

    public JSONObject N() {
        return this.V;
    }

    public int O() {
        return this.W;
    }

    public int P() {
        return this.X;
    }

    public JSONObject Q() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("uniqueId", this.O);
            jSONObject.put("tit", this.j);
            jSONObject.put("desc", this.k);
            jSONObject.put("pk", this.u);
            jSONObject.put("appname", this.p);
            jSONObject.put("act", this.B);
            return jSONObject;
        } catch (Throwable unused) {
            return null;
        }
    }
}
