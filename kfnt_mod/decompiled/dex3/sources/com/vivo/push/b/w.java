package com.vivo.push.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class w extends com.vivo.push.g {
    public int c;

    @Override // com.vivo.push.g
    public final boolean a() {
        return true;
    }

    @Override // com.vivo.push.g
    public final String toString() {
        return "PushModeCommand";
    }

    public w() {
        super(2011);
        this.c = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.g
    public final void b(com.vivo.push.a aVar) {
        aVar.a("com.bbk.push.ikey.MODE_TYPE", this.c);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.g
    public final void c(com.vivo.push.a aVar) {
        this.c = aVar.b("com.bbk.push.ikey.MODE_TYPE", 0);
    }
}
