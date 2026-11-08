package bykvm_19do.bykvm_19do.bykvm_19do;

import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class z1 extends s1 {
    private static final long[] f = {60000};
    private long d;
    private long e;

    z1(u1 u1Var) {
        super(u1Var);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.s1
    boolean a() {
        return true;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.s1
    long b() {
        long jN = this.f1378a.c().N();
        if (jN > 60000 || jN <= 0) {
            jN = 60000;
        }
        f[0] = jN;
        return this.d + jN;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.s1
    long[] c() {
        return f;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.s1
    public boolean d() {
        z zVarA;
        if (System.currentTimeMillis() > this.e + this.f1378a.c().N()) {
            JSONObject jSONObjectB = this.f1378a.d().b();
            a2 a2VarE = this.f1378a.e();
            if (a2VarE != null && jSONObjectB != null && (zVarA = a2VarE.a()) != null) {
                this.f1378a.b().a(jSONObjectB, zVarA, a2VarE.b());
                this.e = System.currentTimeMillis();
            }
        }
        ArrayList<a0> arrayListA = this.f1378a.b().a();
        ArrayList<a0> arrayList = new ArrayList<>(arrayListA.size());
        ArrayList<a0> arrayList2 = new ArrayList<>(arrayListA.size());
        u1 u1Var = this.f1378a;
        String[] strArrA = t.a(u1Var, u1Var.a(), this.f1378a.d().a());
        Iterator<a0> it = arrayListA.iterator();
        while (it.hasNext()) {
            a0 next = it.next();
            int iA = s.a(strArrA, next.i, this.f1378a.c());
            if (iA == 200) {
                arrayList.add(next);
            } else {
                next.k = iA;
                arrayList2.add(next);
            }
        }
        if (arrayList.size() > 0 || arrayList2.size() > 0) {
            this.f1378a.b().a(arrayList, arrayList2);
        }
        l0.d(e() + arrayList.size() + " " + arrayListA.size(), null);
        if (arrayList.size() != arrayListA.size()) {
            return false;
        }
        this.d = System.currentTimeMillis();
        return true;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.s1
    String e() {
        return "s";
    }
}
