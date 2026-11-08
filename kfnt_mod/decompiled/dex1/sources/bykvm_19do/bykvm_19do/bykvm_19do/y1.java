package bykvm_19do.bykvm_19do.bykvm_19do;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class y1 extends s1 {
    static final long[] d = {60000, 60000, 60000, com.bytedance.b.a.a.c.g.g, com.bytedance.b.a.a.c.g.g, com.bytedance.b.a.a.c.g.g, 180000, 180000};
    static final long[] e = {180000, 180000, 360000, 360000, 540000, 540000};
    private static final long[] f = {10000, 10000, 20000, 20000, 60000, 6000, 180000, 180000, 540000, 540000};

    y1(u1 u1Var) {
        super(u1Var);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.s1
    boolean a() {
        return true;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.s1
    long b() {
        return this.f1378a.d().k() + (this.f1378a.e().c() ? 21600000 : 43200000);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.s1
    long[] c() {
        int iJ = this.f1378a.d().j();
        if (iJ == 0) {
            return f;
        }
        if (iJ == 1) {
            return e;
        }
        if (iJ == 2) {
            return d;
        }
        l0.a(null);
        return e;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.s1
    boolean d() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObjectA = this.f1378a.d().a();
        if (jSONObjectA == null) {
            l0.a(null);
            return false;
        }
        jSONObject.put("magic_tag", "ss_app_log");
        jSONObject.put("header", jSONObjectA);
        jSONObject.put("_gen_time", System.currentTimeMillis());
        JSONObject jSONObjectA2 = s.a(t.a(this.f1378a.a(), this.f1378a.d().a(), this.f1378a.f().a(), true, a.j()), jSONObject);
        if (jSONObjectA2 == null) {
            return false;
        }
        return this.f1378a.d().a(jSONObjectA2, jSONObjectA2.optString(com.bytedance.b.a.c.h.x, ""), jSONObjectA2.optString("install_id", ""), jSONObjectA2.optString("ssid", ""));
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.s1
    String e() {
        return "r";
    }
}
