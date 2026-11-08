package com.vivo.push.b;

import android.text.TextUtils;
import com.vivo.push.model.UnvarnishedMessage;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class o extends v {
    protected UnvarnishedMessage c;

    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final String toString() {
        return "OnMessageCommand";
    }

    public o() {
        super(3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.b.v, com.vivo.push.b.s, com.vivo.push.g
    public final void b(com.vivo.push.a aVar) {
        super.b(aVar);
        aVar.a("msg_v1", this.c.unpackToJson());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.b.v, com.vivo.push.b.s, com.vivo.push.g
    public final void c(com.vivo.push.a aVar) {
        super.c(aVar);
        String strA = aVar.a("msg_v1");
        if (TextUtils.isEmpty(strA)) {
            return;
        }
        this.c = new UnvarnishedMessage(strA);
        this.c.setMsgId(this.e);
    }

    public final String b() {
        if (this.c == null) {
            return null;
        }
        return this.c.unpackToJson();
    }

    public final UnvarnishedMessage c() {
        return this.c;
    }
}
