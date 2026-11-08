package bykvm_19do.bykvm_19do.bykvm_19do;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class v1 extends x1 {
    private boolean f;

    v1(u1 u1Var) {
        super(u1Var);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.x1, bykvm_19do.bykvm_19do.bykvm_19do.s1
    long b() {
        return this.f ? Long.MAX_VALUE : 0L;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.x1, bykvm_19do.bykvm_19do.bykvm_19do.s1
    public boolean d() {
        boolean zD = super.d();
        this.f = zD;
        return zD;
    }
}
