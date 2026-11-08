package com.vivo.push.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class h extends com.vivo.push.g {
    private String c;

    public h() {
        super(2013);
    }

    public h(String str) {
        this();
        this.c = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.g
    public final void b(com.vivo.push.a aVar) {
        aVar.a("MsgArriveCommand.MSG_TAG", this.c);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.g
    public final void c(com.vivo.push.a aVar) {
        this.c = aVar.a("MsgArriveCommand.MSG_TAG");
    }
}
