package com.vivo.push.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class s extends com.vivo.push.g {
    public String f;
    public int g;

    @Override // com.vivo.push.g
    public String toString() {
        return "OnReceiveCommand";
    }

    public s(int i) {
        super(i);
        this.f = null;
        this.g = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.g
    public void b(com.vivo.push.a aVar) {
        aVar.a("req_id", this.f);
        aVar.a("status_msg_code", this.g);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.g
    public void c(com.vivo.push.a aVar) {
        this.f = aVar.a("req_id");
        this.g = aVar.b("status_msg_code", this.g);
    }
}
