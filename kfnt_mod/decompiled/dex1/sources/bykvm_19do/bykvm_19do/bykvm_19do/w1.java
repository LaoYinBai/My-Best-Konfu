package bykvm_19do.bykvm_19do.bykvm_19do;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class w1 extends z1 {
    private boolean g;

    w1(u1 u1Var) {
        super(u1Var);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.z1, bykvm_19do.bykvm_19do.bykvm_19do.s1
    long b() {
        return this.g ? Long.MAX_VALUE : 0L;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.z1, bykvm_19do.bykvm_19do.bykvm_19do.s1
    public boolean d() {
        boolean zD = super.d();
        this.g = zD;
        return zD;
    }
}
