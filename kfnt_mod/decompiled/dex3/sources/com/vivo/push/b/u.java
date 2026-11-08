package com.vivo.push.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class u extends v {
    public long c;
    private int h;

    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final String toString() {
        return "OnUndoMsgCommand";
    }

    public u() {
        super(20);
        this.c = -1L;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.b.v, com.vivo.push.b.s, com.vivo.push.g
    public final void b(com.vivo.push.a aVar) {
        super.b(aVar);
        aVar.a("undo_msg_v1", this.c);
        aVar.a("undo_msg_type_v1", this.h);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.b.v, com.vivo.push.b.s, com.vivo.push.g
    public final void c(com.vivo.push.a aVar) {
        super.c(aVar);
        this.c = aVar.b("undo_msg_v1", this.c);
        this.h = aVar.b("undo_msg_type_v1", 0);
    }
}
