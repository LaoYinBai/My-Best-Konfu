package com.vivo.push.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class v extends s {
    public String d;
    public long e;

    public v(int i) {
        super(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.b.s, com.vivo.push.g
    public void b(com.vivo.push.a aVar) {
        super.b(aVar);
        aVar.a("OnVerifyCallBackCommand.EXTRA_SECURITY_CONTENT", this.d);
        aVar.a("notify_id", this.e);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.b.s, com.vivo.push.g
    public void c(com.vivo.push.a aVar) {
        super.c(aVar);
        this.d = aVar.a("OnVerifyCallBackCommand.EXTRA_SECURITY_CONTENT");
        this.e = aVar.b("notify_id", -1L);
    }
}
