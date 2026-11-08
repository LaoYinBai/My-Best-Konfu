package com.vivo.push.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class i extends s {
    public String c;
    public String d;
    private String e;

    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final String toString() {
        return "OnBindCommand";
    }

    public i(int i) {
        super(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final void b(com.vivo.push.a aVar) {
        super.b(aVar);
        aVar.a("app_id", this.c);
        aVar.a("client_id", this.e);
        aVar.a("client_token", this.d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final void c(com.vivo.push.a aVar) {
        super.c(aVar);
        this.c = aVar.a("app_id");
        this.e = aVar.a("client_id");
        this.d = aVar.a("client_token");
    }
}
