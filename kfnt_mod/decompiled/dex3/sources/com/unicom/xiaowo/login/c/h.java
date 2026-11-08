package com.unicom.xiaowo.login.c;

import android.content.Context;
import android.net.Network;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class h implements com.unicom.xiaowo.login.d.e {
    private /* synthetic */ g a;

    h(g gVar) {
        this.a = gVar;
    }

    @Override // com.unicom.xiaowo.login.d.e
    public final void a(boolean z, Network network) {
        com.unicom.xiaowo.login.d.f.a("ForceMobileNet result:" + z);
        if (!z) {
            this.a.c.a(2, "获取鉴权信息失败");
            com.unicom.xiaowo.login.d.a.a().b();
        } else {
            e eVar = this.a.d;
            Context context = this.a.a;
            eVar.a(this.a.b, network, this.a.c);
        }
    }
}
