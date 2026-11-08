package com.vivo.push.b;

import com.umeng.message.MsgConstant;
import java.util.ArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class a extends c {
    private ArrayList<String> c;

    public a(boolean z, String str, ArrayList<String> arrayList) {
        super(z ? 2002 : 2003, str);
        this.c = arrayList;
    }

    @Override // com.vivo.push.b.c, com.vivo.push.g
    public final void b(com.vivo.push.a aVar) {
        super.b(aVar);
        aVar.a(MsgConstant.KEY_TAGS, this.c);
    }

    @Override // com.vivo.push.b.c, com.vivo.push.g
    public final void c(com.vivo.push.a aVar) {
        super.c(aVar);
        this.c = aVar.b(MsgConstant.KEY_TAGS);
    }

    @Override // com.vivo.push.b.c, com.vivo.push.g
    public final String toString() {
        return "AliasCommand:" + this.a;
    }
}
