package com.vivo.push.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class j extends s {
    public int c;
    public int d;

    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final String toString() {
        return "OnChangePushStatusCommand";
    }

    public j() {
        super(12);
        this.c = -1;
        this.d = -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final void b(com.vivo.push.a aVar) {
        super.b(aVar);
        aVar.a("OnChangePushStatus.EXTRA_REQ_SERVICE_STATUS", this.c);
        aVar.a("OnChangePushStatus.EXTRA_REQ_RECEIVER_STATUS", this.d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final void c(com.vivo.push.a aVar) {
        super.c(aVar);
        this.c = aVar.b("OnChangePushStatus.EXTRA_REQ_SERVICE_STATUS", this.c);
        this.d = aVar.b("OnChangePushStatus.EXTRA_REQ_RECEIVER_STATUS", this.d);
    }
}
