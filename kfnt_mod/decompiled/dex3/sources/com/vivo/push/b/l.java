package com.vivo.push.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class l extends s {
    public int c;
    public int d;

    public l() {
        super(2016);
        this.c = -1;
        this.d = -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final void b(com.vivo.push.a aVar) {
        super.b(aVar);
        aVar.a("key_dispatch_environment", this.c);
        aVar.a("key_dispatch_area", this.d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final void c(com.vivo.push.a aVar) {
        super.c(aVar);
        this.c = aVar.b("key_dispatch_environment", 1);
        this.d = aVar.b("key_dispatch_area", 1);
    }
}
