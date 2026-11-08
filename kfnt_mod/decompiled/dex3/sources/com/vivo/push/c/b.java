package com.vivo.push.c;

import android.content.pm.PackageManager;
import com.vivo.push.b.y;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class b extends com.vivo.push.e {
    public b(com.vivo.push.g gVar) {
        super(gVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.e
    public final void a(com.vivo.push.g gVar) throws PackageManager.NameNotFoundException {
        boolean zB;
        com.vivo.push.model.b bVarA = com.vivo.push.util.r.a(this.a);
        try {
            if (((com.vivo.push.b.d) gVar).c) {
                zB = e.a(this.a);
            } else {
                zB = e.b(this.a);
            }
            if (zB) {
                com.vivo.push.model.b bVarA2 = com.vivo.push.util.r.a(this.a);
                if (bVarA == null || bVarA2 == null || bVarA2.a == null || !bVarA2.a.equals(bVarA.a)) {
                    if (bVarA != null && bVarA.a != null) {
                        com.vivo.push.a.a.a(this.a, bVarA.a, new y(bVarA.a));
                    }
                    if (bVarA2 == null || bVarA2.a == null) {
                        return;
                    }
                    com.vivo.push.a.a.a(this.a, bVarA2.a, new com.vivo.push.b.f());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
