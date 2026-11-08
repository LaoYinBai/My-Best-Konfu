package com.vivo.push.c;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class n extends o {
    public n(com.vivo.push.g gVar) {
        super(gVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.e
    public final void a(com.vivo.push.g gVar) {
        final com.vivo.push.b.r rVar = (com.vivo.push.b.r) gVar;
        com.vivo.push.f.b(new Runnable() { // from class: com.vivo.push.c.n.1
            @Override // java.lang.Runnable
            public final void run() {
                n.this.c.onPublish(n.this.a, rVar.g, rVar.f);
            }
        });
    }
}
