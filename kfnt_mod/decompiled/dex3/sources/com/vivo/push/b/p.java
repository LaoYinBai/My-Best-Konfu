package com.vivo.push.b;

import android.text.TextUtils;
import com.vivo.push.model.InsideNotificationItem;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class p extends com.vivo.push.g {
    public String c;
    public String d;
    public long e;
    public InsideNotificationItem f;

    @Override // com.vivo.push.g
    public final String toString() {
        return "OnNotificationClickCommand";
    }

    public p(String str, long j, InsideNotificationItem insideNotificationItem) {
        super(5);
        this.c = str;
        this.e = j;
        this.f = insideNotificationItem;
        this.d = null;
    }

    public p() {
        super(5);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.g
    public final void b(com.vivo.push.a aVar) {
        aVar.a("package_name", this.c);
        aVar.a("notify_id", this.e);
        aVar.a("notification_v1", com.vivo.push.util.o.b(this.f));
        aVar.a("open_pkg_name", this.d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.g
    public final void c(com.vivo.push.a aVar) {
        this.c = aVar.a("package_name");
        this.e = aVar.b("notify_id", -1L);
        this.d = aVar.a("open_pkg_name");
        String strA = aVar.a("notification_v1");
        if (!TextUtils.isEmpty(strA)) {
            this.f = com.vivo.push.util.o.a(strA);
        }
        if (this.f != null) {
            this.f.setMsgId(this.e);
        }
    }
}
