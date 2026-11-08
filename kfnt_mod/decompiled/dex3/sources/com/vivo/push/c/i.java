package com.vivo.push.c;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class i extends o {
    public i(com.vivo.push.g gVar) {
        super(gVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.e
    public final void a(com.vivo.push.g gVar) {
        final com.vivo.push.b.m mVar = (com.vivo.push.b.m) gVar;
        com.vivo.push.f.b(new Runnable() { // from class: com.vivo.push.c.i.1
            @Override // java.lang.Runnable
            public final void run() {
                i.this.c.onListTags(i.this.a, mVar.g, mVar.c, mVar.f);
            }
        });
    }
}
