package bykvm_19do.bykvm_19do.bykvm_19do;

import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class r1 extends s1 {
    private boolean d;

    r1(u1 u1Var) {
        super(u1Var);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.s1
    boolean a() {
        return true;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.s1
    long b() {
        return this.d ? Long.MAX_VALUE : 0L;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.s1
    long[] c() {
        return y1.d;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.s1
    boolean d() {
        if (this.f1378a.d().j() != 0) {
            JSONObject jSONObjectA = this.f1378a.d().a();
            if (jSONObjectA != null) {
                this.d = s.b(t.a(this.f1378a.a(), this.f1378a.d().a(), this.f1378a.f().b(), true, a.j()), jSONObjectA);
            } else {
                l0.a(null);
            }
        }
        return this.d;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.s1
    String e() {
        return "ac";
    }
}
