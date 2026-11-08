package com.vivo.push.c;

import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class d extends o {
    public d(com.vivo.push.g gVar) {
        super(gVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.e
    public final void a(com.vivo.push.g gVar) {
        final com.vivo.push.b.i iVar = (com.vivo.push.b.i) gVar;
        final String str = iVar.d;
        com.vivo.push.d.a().a(iVar.f, iVar.g, str);
        if (TextUtils.isEmpty(iVar.f) && !TextUtils.isEmpty(str)) {
            com.vivo.push.d.a().a(str);
        }
        com.vivo.push.f.b(new Runnable() { // from class: com.vivo.push.c.d.1
            @Override // java.lang.Runnable
            public final void run() {
                if (!TextUtils.isEmpty(str)) {
                    d.this.c.onReceiveRegId(d.this.a, str);
                }
                d.this.c.onBind(d.this.a, iVar.g, iVar.c);
            }
        });
    }
}
