package com.ss.union.game.sdk.vcenter.c.a;

import android.content.Intent;
import com.ss.union.game.sdk.vcenter.b.a.e;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a extends com.ss.union.game.sdk.vcenter.b.a.a {
    @Override // com.ss.union.game.sdk.vcenter.b.a.a
    public int a() {
        return 1001;
    }

    @Override // com.ss.union.game.sdk.vcenter.b.a.a
    public e.b a(e.a aVar, Intent intent) {
        com.ss.union.game.sdk.vcenter.c.d().a(aVar.b().optBoolean("selfCall", false));
        return e.b.a(aVar);
    }

    @Override // com.ss.union.game.sdk.vcenter.b.a.a
    public void a(e.a aVar, Intent intent, com.ss.union.game.sdk.vcenter.b.a.c cVar) {
        com.ss.union.game.sdk.vcenter.c.d().a(aVar.b().optBoolean("selfCall", false));
        cVar.a(e.b.a(aVar));
    }
}
