package com.vivo.push.c;

import android.content.pm.PackageManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class a extends com.vivo.push.e {
    public a(com.vivo.push.g gVar) {
        super(gVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.e
    public final void a(com.vivo.push.g gVar) throws PackageManager.NameNotFoundException {
        com.vivo.push.b.c cVar = (com.vivo.push.b.c) gVar;
        com.vivo.push.model.b bVarA = com.vivo.push.util.r.a(this.a);
        if (bVarA == null) {
            com.vivo.push.d.a().a(cVar.e, 1005, new Object[0]);
            return;
        }
        String str = bVarA.a;
        if (bVarA.e) {
            com.vivo.push.d.a().a(cVar.e, 1004, new Object[0]);
            gVar = new com.vivo.push.b.e();
        } else {
            int iA = com.vivo.push.util.q.a(cVar);
            if (iA != 0) {
                com.vivo.push.d.a().a(cVar.e, iA, new Object[0]);
                return;
            }
        }
        com.vivo.push.a.a.a(this.a, str, gVar);
    }
}
