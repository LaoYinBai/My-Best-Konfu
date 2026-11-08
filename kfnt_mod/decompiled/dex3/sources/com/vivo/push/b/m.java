package com.vivo.push.b;

import java.util.ArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class m extends s {
    public ArrayList<String> c;

    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final String toString() {
        return "OnListTagCommand";
    }

    public m() {
        super(8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final void b(com.vivo.push.a aVar) {
        super.b(aVar);
        aVar.a("tags_list", this.c);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final void c(com.vivo.push.a aVar) {
        super.c(aVar);
        this.c = aVar.b("tags_list");
    }
}
