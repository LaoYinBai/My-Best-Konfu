package com.xiaomi.push;

import com.xiaomi.push.gz;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.av;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class gw implements av.b.a {
    private int a;

    /* renamed from: a, reason: collision with other field name */
    private fp f450a;

    /* renamed from: a, reason: collision with other field name */
    private XMPushService f451a;

    /* renamed from: a, reason: collision with other field name */
    private av.b f452a;

    /* renamed from: a, reason: collision with other field name */
    private boolean f454a = false;

    /* renamed from: a, reason: collision with other field name */
    private av.c f453a = av.c.binding;

    gw(XMPushService xMPushService, av.b bVar) {
        this.f451a = xMPushService;
        this.f452a = bVar;
    }

    private void b() {
        this.f452a.b(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        fc fcVar;
        b();
        if (this.f454a && this.a != 11) {
            fd fdVarM376a = hb.m374a().m376a();
            switch (gy.a[this.f453a.ordinal()]) {
                case 1:
                    if (this.a != 17) {
                        if (this.a != 21) {
                            try {
                                gz.a aVarC = gz.c(hb.a().a());
                                fdVarM376a.f352a = aVarC.a.a();
                                fdVarM376a.c(aVarC.f455a);
                                break;
                            } catch (NullPointerException unused) {
                                fdVarM376a = null;
                                break;
                            }
                        } else {
                            fcVar = fc.BIND_TIMEOUT;
                        }
                    } else {
                        fcVar = fc.BIND_TCP_READ_TIMEOUT;
                    }
                    fdVarM376a.f352a = fcVar.a();
                    break;
                case 3:
                    fcVar = fc.BIND_SUCCESS;
                    fdVarM376a.f352a = fcVar.a();
                    break;
            }
            if (fdVarM376a != null) {
                fdVarM376a.b(this.f450a.mo331a());
                fdVarM376a.d(this.f452a.f906b);
                fdVarM376a.f355b = 1;
                try {
                    fdVarM376a.a((byte) Integer.parseInt(this.f452a.g));
                } catch (NumberFormatException unused2) {
                }
                hb.m374a().a(fdVarM376a);
            }
        }
    }

    void a() {
        this.f452a.a(this);
        this.f450a = this.f451a.m567a();
    }

    @Override // com.xiaomi.push.service.av.b.a
    public void a(av.c cVar, av.c cVar2, int i) {
        if (!this.f454a && cVar == av.c.binding) {
            this.f453a = cVar2;
            this.a = i;
            this.f454a = true;
        }
        this.f451a.a(new gx(this, 4));
    }
}
