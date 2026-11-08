package bykvm_19do.bykvm_19do.bykvm_19do;

import android.os.Bundle;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class x1 extends s1 {
    private static final long[] e = {60000};
    private long d;

    x1(u1 u1Var) {
        super(u1Var);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.s1
    boolean a() {
        return false;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.s1
    long b() {
        return this.d + 60000;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.s1
    long[] c() {
        return e;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.s1
    public boolean d() {
        Bundle bundleA;
        long jCurrentTimeMillis = System.currentTimeMillis();
        a2 a2VarE = this.f1378a.e();
        if (a2VarE != null && (bundleA = a2VarE.a(jCurrentTimeMillis, 50000L)) != null) {
            a.a("play_session", bundleA);
            a.a();
        }
        if (this.f1378a.d().j() == 0) {
            return false;
        }
        JSONObject jSONObjectB = this.f1378a.d().b();
        if (jSONObjectB == null) {
            l0.a(null);
            return false;
        }
        boolean zA = this.f1378a.b().a(jSONObjectB);
        this.d = System.currentTimeMillis();
        return zA;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.s1
    String e() {
        return "p";
    }
}
