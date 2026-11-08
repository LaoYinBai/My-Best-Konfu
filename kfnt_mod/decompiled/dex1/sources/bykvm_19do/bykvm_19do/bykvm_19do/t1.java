package bykvm_19do.bykvm_19do.bykvm_19do;

import com.appsflyer.share.Constants;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class t1 extends s1 {
    t1(u1 u1Var) {
        super(u1Var);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.s1
    boolean a() {
        return true;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.s1
    long b() {
        return this.f1378a.c().w() + 21600000;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.s1
    long[] c() {
        return y1.e;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.s1
    public boolean d() throws JSONException {
        JSONObject jSONObjectA = this.f1378a.d().a();
        if (this.f1378a.d().j() == 0 || jSONObjectA == null || this.f1378a.c().w() + 21600000 > System.currentTimeMillis()) {
            return false;
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("magic_tag", "ss_app_log");
        jSONObject.put("header", jSONObjectA);
        jSONObject.put("_gen_time", System.currentTimeMillis());
        JSONObject jSONObjectC = s.c(s.a(t.a(this.f1378a.a(), this.f1378a.d().a(), this.f1378a.f().d(), true, a.j()), s.e), jSONObject);
        a.f().a(!m0.a(jSONObjectC, this.f1378a.c().v()), jSONObjectC);
        if (jSONObjectC == null) {
            return false;
        }
        this.f1378a.c().a(jSONObjectC);
        return true;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.s1
    String e() {
        return Constants.URL_CAMPAIGN;
    }
}
