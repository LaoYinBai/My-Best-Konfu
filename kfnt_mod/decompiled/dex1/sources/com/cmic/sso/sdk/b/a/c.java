package com.cmic.sso.sdk.b.a;

import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c implements b {

    /* renamed from: a, reason: collision with root package name */
    private b f5385a;

    /* renamed from: b, reason: collision with root package name */
    private com.cmic.sso.sdk.b.d.c f5386b;

    /* renamed from: c, reason: collision with root package name */
    private com.cmic.sso.sdk.b.a f5387c = new com.cmic.sso.sdk.b.a();

    @Override // com.cmic.sso.sdk.b.a.b
    public void a(com.cmic.sso.sdk.b.c.c cVar, com.cmic.sso.sdk.b.d.c cVar2, com.cmic.sso.sdk.a aVar) {
        b(cVar, cVar2, aVar);
    }

    public void a(b bVar) {
        this.f5385a = bVar;
    }

    public void b(final com.cmic.sso.sdk.b.c.c cVar, final com.cmic.sso.sdk.b.d.c cVar2, final com.cmic.sso.sdk.a aVar) {
        if (this.f5385a != null) {
            this.f5386b = new com.cmic.sso.sdk.b.d.c() { // from class: com.cmic.sso.sdk.b.a.c.1
                @Override // com.cmic.sso.sdk.b.d.c
                public void a(com.cmic.sso.sdk.b.d.b bVar) {
                    if (bVar.d()) {
                        c.this.b(c.this.f5387c.a(cVar, bVar, aVar), c.this.f5386b, aVar);
                    } else if (!TextUtils.isEmpty(c.this.f5387c.a())) {
                        c.this.b(c.this.f5387c.b(cVar, bVar, aVar), c.this.f5386b, aVar);
                    } else {
                        cVar2.a(bVar);
                    }
                }

                @Override // com.cmic.sso.sdk.b.d.c
                public void a(com.cmic.sso.sdk.b.d.a aVar2) {
                    if (cVar.j()) {
                        com.cmic.sso.sdk.d.c.a("RetryAndRedirectInterceptor", "retry");
                        c.this.b(cVar, cVar2, aVar);
                    } else {
                        cVar2.a(aVar2);
                    }
                }
            };
            if (cVar.g()) {
                this.f5385a.a(cVar, this.f5386b, aVar);
            } else {
                cVar2.a(com.cmic.sso.sdk.b.d.a.a(200025));
            }
        }
    }
}
