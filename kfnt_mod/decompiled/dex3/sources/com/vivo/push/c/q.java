package com.vivo.push.c;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class q extends o {
    public q(com.vivo.push.g gVar) {
        super(gVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.e
    public final void a(com.vivo.push.g gVar) {
        final com.vivo.push.b.i iVar = (com.vivo.push.b.i) gVar;
        com.vivo.push.d.a().a(iVar.f, iVar.g, new Object[0]);
        com.vivo.push.f.b(new Runnable() { // from class: com.vivo.push.c.q.1
            @Override // java.lang.Runnable
            public final void run() {
                q.this.c.onUnBind(q.this.a, iVar.g, iVar.c);
            }
        });
    }
}
