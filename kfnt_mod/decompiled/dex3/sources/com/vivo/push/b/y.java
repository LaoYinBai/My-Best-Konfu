package com.vivo.push.b;

import com.ss.union.game.sdk.core.splashEffect.view.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class y extends com.vivo.push.g {
    private String c;

    @Override // com.vivo.push.g
    public final String toString() {
        return "StopServiceCommand";
    }

    public y(String str) {
        super(a.h.d);
        this.c = str;
    }

    public y() {
        super(a.h.d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.g
    public final void b(com.vivo.push.a aVar) {
        aVar.a("package_name", this.c);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.g
    public final void c(com.vivo.push.a aVar) {
        this.c = aVar.a("package_name");
    }
}
