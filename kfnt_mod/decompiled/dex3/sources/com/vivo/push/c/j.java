package com.vivo.push.c;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class j extends o {
    public j(com.vivo.push.g gVar) {
        super(gVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.e
    public final void a(com.vivo.push.g gVar) {
        final com.vivo.push.b.n nVar = (com.vivo.push.b.n) gVar;
        com.vivo.push.f.b(new Runnable() { // from class: com.vivo.push.c.j.1
            @Override // java.lang.Runnable
            public final void run() {
                j.this.c.onLog(j.this.a, nVar.c, nVar.d, nVar.e);
            }
        });
    }
}
