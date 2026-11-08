package com.vivo.push.b;

import android.text.TextUtils;
import com.vivo.push.model.InsideNotificationItem;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class q extends v {
    protected InsideNotificationItem c;
    private String h;

    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final String toString() {
        return "OnNotifyArrivedCommand";
    }

    public q() {
        super(4);
    }

    public final InsideNotificationItem b() {
        return this.c;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.b.v, com.vivo.push.b.s, com.vivo.push.g
    public final void b(com.vivo.push.a aVar) {
        super.b(aVar);
        this.h = com.vivo.push.util.o.b(this.c);
        aVar.a("notification_v1", this.h);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.b.v, com.vivo.push.b.s, com.vivo.push.g
    public final void c(com.vivo.push.a aVar) {
        super.c(aVar);
        this.h = aVar.a("notification_v1");
        if (TextUtils.isEmpty(this.h)) {
            return;
        }
        this.c = com.vivo.push.util.o.a(this.h);
        if (this.c != null) {
            this.c.setMsgId(this.e);
        }
    }

    public final String c() {
        if (!TextUtils.isEmpty(this.h)) {
            return this.h;
        }
        if (this.c == null) {
            return null;
        }
        return com.vivo.push.util.o.b(this.c);
    }
}
