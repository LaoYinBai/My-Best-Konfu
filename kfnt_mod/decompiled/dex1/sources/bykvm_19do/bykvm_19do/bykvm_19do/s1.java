package bykvm_19do.bykvm_19do.bykvm_19do;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
abstract class s1 {

    /* renamed from: a, reason: collision with root package name */
    final u1 f1378a;

    /* renamed from: b, reason: collision with root package name */
    private int f1379b = 0;

    /* renamed from: c, reason: collision with root package name */
    private boolean f1380c;

    s1(u1 u1Var) {
        this.f1378a = u1Var;
    }

    abstract boolean a();

    abstract long b();

    abstract long[] c();

    abstract boolean d();

    abstract String e();

    void f() {
        this.f1380c = true;
    }

    boolean g() {
        return this.f1380c;
    }

    final long h() {
        boolean zD;
        long jB;
        if (!(!a() || h0.a(this.f1378a.a()))) {
            return 60000L;
        }
        long jB2 = b();
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jB2 > 1000 + jCurrentTimeMillis) {
            return jB2 - jCurrentTimeMillis;
        }
        try {
            zD = d();
        } catch (Exception e) {
            l0.a(e);
            zD = false;
        }
        if (zD) {
            this.f1379b = 0;
            jB = b() - System.currentTimeMillis();
        } else {
            long[] jArrC = c();
            int i = this.f1379b;
            this.f1379b = i + 1;
            jB = jArrC[i % jArrC.length];
        }
        l0.d(e() + " worked:" + zD + " " + jB, null);
        return jB;
    }
}
