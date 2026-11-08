package com.vivo.push.b;

import java.util.ArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class t extends s {
    public ArrayList<String> c;
    public ArrayList<String> d;

    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final String toString() {
        return "OnSetTagsCommand";
    }

    public t(int i) {
        super(i);
        this.c = null;
        this.d = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final void b(com.vivo.push.a aVar) {
        super.b(aVar);
        aVar.a(com.umeng.commonsdk.framework.c.a, this.c);
        aVar.a("error_msg", this.d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final void c(com.vivo.push.a aVar) {
        super.c(aVar);
        this.c = aVar.b(com.umeng.commonsdk.framework.c.a);
        this.d = aVar.b("error_msg");
    }
}
