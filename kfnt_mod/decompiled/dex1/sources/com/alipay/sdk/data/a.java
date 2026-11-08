package com.alipay.sdk.data;

import android.content.Context;
import android.text.TextUtils;
import anet.channel.strategy.dispatch.DispatchConstants;
import com.alipay.sdk.util.c;
import com.alipay.sdk.util.g;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class a {
    private static final String A = "prev_jump_dual";
    private static final String B = "use_sc_only";
    private static final String C = "bind_use_imp";
    private static final String D = "retry_bnd_once";
    private static final String E = "skip_trans";
    private static final String F = "up_before_pay";
    private static final String G = "scheme_pay_2";
    private static final String H = "intercept_batch";
    private static a X = null;

    /* renamed from: b, reason: collision with root package name */
    private static final String f2583b = "DynCon";

    /* renamed from: c, reason: collision with root package name */
    private static final int f2584c = 10000;
    private static final String d = "https://h5.m.taobao.com/mlapp/olist.html";
    private static final int e = 10;
    private static final boolean f = true;
    private static final boolean g = true;
    private static final boolean h = false;
    private static final boolean i = true;
    private static final boolean j = true;
    private static final String k = "";
    private static final boolean l = false;
    private static final boolean m = false;
    private static final boolean n = false;
    private static final boolean o = true;
    private static final int p = 1000;
    private static final int q = 20000;
    private static final String r = "alipay_cashier_dynamic_config";
    private static final String s = "timeout";
    private static final String t = "h5_port_degrade";
    private static final String u = "st_sdk_config";
    private static final String v = "tbreturl";
    private static final String w = "launchAppSwitch";
    private static final String x = "configQueryInterval";
    private static final String y = "deg_log_mcgw";
    private static final String z = "deg_start_srv_first";
    private int I = 10000;
    private boolean J = false;
    private String K = d;
    private int L = 10;
    private boolean M = true;
    private boolean N = true;

    /* renamed from: a, reason: collision with root package name */
    public boolean f2585a = false;
    private boolean O = false;
    private boolean P = true;
    private boolean Q = true;
    private String R = "";
    private boolean S = false;
    private boolean T = false;
    private boolean U = false;
    private boolean V = true;
    private List<C0078a> W = null;

    public int a() {
        if (this.I < 1000 || this.I > 20000) {
            c.a(f2583b, "time(def) = 10000");
            return 10000;
        }
        c.a(f2583b, "time = " + this.I);
        return this.I;
    }

    public boolean b() {
        return this.J;
    }

    public boolean c() {
        return this.M;
    }

    public boolean d() {
        return this.N;
    }

    public String e() {
        return this.K;
    }

    public int f() {
        return this.L;
    }

    public boolean g() {
        return this.O;
    }

    public boolean h() {
        return this.P;
    }

    public boolean i() {
        return this.Q;
    }

    public String j() {
        return this.R;
    }

    public boolean k() {
        return this.S;
    }

    public boolean l() {
        return this.T;
    }

    public boolean m() {
        return this.U;
    }

    public boolean n() {
        return this.V;
    }

    public List<C0078a> o() {
        return this.W;
    }

    public void a(boolean z2) {
        this.f2585a = z2;
    }

    public static a p() {
        if (X == null) {
            X = new a();
            X.q();
        }
        return X;
    }

    private void q() {
        a(g.b(com.alipay.sdk.sys.a.a(), com.alipay.sdk.sys.b.a().b(), r, null));
    }

    private void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            a(new JSONObject(str));
        } catch (Throwable th) {
            c.a(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.alipay.sdk.sys.a aVar) {
        try {
            g.a(aVar, com.alipay.sdk.sys.b.a().b(), r, r().toString());
        } catch (Exception e2) {
            c.a(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONObject(u);
            if (jSONObjectOptJSONObject != null) {
                a(jSONObjectOptJSONObject);
            } else {
                c.c(f2583b, "empty config");
            }
        } catch (Throwable th) {
            c.a(th);
        }
    }

    private JSONObject r() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("timeout", a());
        jSONObject.put(t, b());
        jSONObject.put(v, e());
        jSONObject.put(x, f());
        jSONObject.put(w, C0078a.a(o()));
        jSONObject.put(G, c());
        jSONObject.put(H, d());
        jSONObject.put(y, g());
        jSONObject.put(z, h());
        jSONObject.put(A, i());
        jSONObject.put(B, j());
        jSONObject.put(C, k());
        jSONObject.put(D, l());
        jSONObject.put(E, m());
        jSONObject.put(F, n());
        return jSONObject;
    }

    private void a(JSONObject jSONObject) {
        this.I = jSONObject.optInt("timeout", 10000);
        this.J = jSONObject.optBoolean(t, false);
        this.K = jSONObject.optString(v, d).trim();
        this.L = jSONObject.optInt(x, 10);
        this.W = C0078a.a(jSONObject.optJSONArray(w));
        this.M = jSONObject.optBoolean(G, true);
        this.N = jSONObject.optBoolean(H, true);
        this.O = jSONObject.optBoolean(y, false);
        this.P = jSONObject.optBoolean(z, true);
        this.Q = jSONObject.optBoolean(A, true);
        this.R = jSONObject.optString(B, "");
        this.S = jSONObject.optBoolean(C, false);
        this.T = jSONObject.optBoolean(D, false);
        this.U = jSONObject.optBoolean(E, false);
        this.V = jSONObject.optBoolean(F, true);
    }

    public void a(final com.alipay.sdk.sys.a aVar, final Context context) {
        new Thread(new Runnable() { // from class: com.alipay.sdk.data.a.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    com.alipay.sdk.packet.b bVarA = new com.alipay.sdk.packet.impl.b().a(aVar, context);
                    if (bVarA != null) {
                        a.this.b(bVarA.b());
                        a.this.a(com.alipay.sdk.sys.a.a());
                    }
                } catch (Throwable th) {
                    c.a(th);
                }
            }
        }).start();
    }

    /* renamed from: com.alipay.sdk.data.a$a, reason: collision with other inner class name */
    public static final class C0078a {

        /* renamed from: a, reason: collision with root package name */
        public final String f2589a;

        /* renamed from: b, reason: collision with root package name */
        public final int f2590b;

        /* renamed from: c, reason: collision with root package name */
        public final String f2591c;

        public C0078a(String str, int i, String str2) {
            this.f2589a = str;
            this.f2590b = i;
            this.f2591c = str2;
        }

        public static C0078a a(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            return new C0078a(jSONObject.optString("pn"), jSONObject.optInt(DispatchConstants.VERSION, 0), jSONObject.optString("pk"));
        }

        public static List<C0078a> a(JSONArray jSONArray) {
            if (jSONArray == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                C0078a c0078aA = a(jSONArray.optJSONObject(i));
                if (c0078aA != null) {
                    arrayList.add(c0078aA);
                }
            }
            return arrayList;
        }

        public static JSONObject a(C0078a c0078a) {
            if (c0078a == null) {
                return null;
            }
            try {
                return new JSONObject().put("pn", c0078a.f2589a).put(DispatchConstants.VERSION, c0078a.f2590b).put("pk", c0078a.f2591c);
            } catch (JSONException e) {
                c.a(e);
                return null;
            }
        }

        public static JSONArray a(List<C0078a> list) {
            if (list == null) {
                return null;
            }
            JSONArray jSONArray = new JSONArray();
            Iterator<C0078a> it = list.iterator();
            while (it.hasNext()) {
                jSONArray.put(a(it.next()));
            }
            return jSONArray;
        }

        public String toString() {
            return String.valueOf(a(this));
        }
    }
}
