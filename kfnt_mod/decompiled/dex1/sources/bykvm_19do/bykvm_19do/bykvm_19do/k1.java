package bykvm_19do.bykvm_19do.bykvm_19do;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class k1 extends s1 {
    private long d;

    k1(u1 u1Var) {
        super(u1Var);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.s1
    boolean a() {
        return true;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.s1
    long b() {
        long jH = this.f1378a.c().H();
        if (jH < 600000) {
            jH = 600000;
        }
        return this.d + jH;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.s1
    long[] c() {
        return y1.e;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.s1
    boolean d() throws JSONException {
        JSONObject jSONObjectA = this.f1378a.d().a();
        if (this.f1378a.d().j() == 0 || jSONObjectA == null) {
            return false;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("header", this.f1378a.d().a());
        jSONObject.put("magic_tag", "ss_app_log");
        jSONObject.put("_gen_time", jCurrentTimeMillis);
        JSONObject jSONObjectD = s.d(s.a(t.a(this.f1378a.a(), this.f1378a.d().a(), this.f1378a.f().e(), true, a.j()), s.e), jSONObject);
        if (jSONObjectD == null) {
            return false;
        }
        a.f().b(!m0.a(a.b(), jSONObjectD), jSONObjectD);
        if (l0.f1356a) {
            l0.a("getAbConfig " + jSONObjectD, null);
        }
        this.f1378a.d().a(jSONObjectD);
        this.d = jCurrentTimeMillis;
        return true;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.s1
    String e() {
        return "ab";
    }
}
