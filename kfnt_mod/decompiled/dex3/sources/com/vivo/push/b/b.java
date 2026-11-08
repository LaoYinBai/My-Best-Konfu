package com.vivo.push.b;

import com.ss.union.game.sdk.core.splashEffect.view.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class b extends c {
    public String c;
    public String d;
    private String j;
    private String k;

    public b(boolean z, String str) {
        super(z ? 2006 : a.h.c, str);
    }

    @Override // com.vivo.push.b.c, com.vivo.push.g
    public final void b(com.vivo.push.a aVar) {
        super.b(aVar);
        aVar.a("sdk_clients", this.j);
        aVar.a("sdk_version", 305L);
        aVar.a("BaseAppCommand.EXTRA_APPID", this.d);
        aVar.a("BaseAppCommand.EXTRA_APPKEY", this.c);
        aVar.a("PUSH_REGID", this.k);
    }

    @Override // com.vivo.push.b.c, com.vivo.push.g
    public final void c(com.vivo.push.a aVar) {
        super.c(aVar);
        this.j = aVar.a("sdk_clients");
        this.d = aVar.a("BaseAppCommand.EXTRA_APPID");
        this.c = aVar.a("BaseAppCommand.EXTRA_APPKEY");
        this.k = aVar.a("PUSH_REGID");
    }

    @Override // com.vivo.push.b.c, com.vivo.push.g
    public final String toString() {
        return "AppCommand:" + this.a;
    }
}
