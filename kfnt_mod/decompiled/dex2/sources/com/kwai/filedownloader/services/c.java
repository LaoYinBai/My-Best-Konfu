package com.kwai.filedownloader.services;

import com.kwai.filedownloader.d.b;
import com.kwai.filedownloader.e.c;
import com.kwai.filedownloader.kwai.c;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    private final a a;

    public static class a {
        c.InterfaceC0095c a;
        Integer b;
        c.e c;
        c.b d;
        c.a e;
        c.d f;

        public a a(int i) {
            if (i > 0) {
                this.b = Integer.valueOf(i);
            }
            return this;
        }

        public a a(c.b bVar) {
            this.d = bVar;
            return this;
        }

        public String toString() {
            return com.kwai.filedownloader.e.f.a("component: database[%s], maxNetworkCount[%s], outputStream[%s], connection[%s], connectionCountAdapter[%s]", this.a, this.b, this.c, this.d, this.e);
        }
    }

    public c() {
        this.a = null;
    }

    public c(a aVar) {
        this.a = aVar;
    }

    private c.d g() {
        return new b();
    }

    private int h() {
        return com.kwai.filedownloader.e.e.a().e;
    }

    private com.kwai.filedownloader.a.a i() {
        return new com.kwai.filedownloader.a.c();
    }

    private c.e j() {
        return new b.a();
    }

    private c.b k() {
        return new c.b();
    }

    private c.a l() {
        return new com.kwai.filedownloader.kwai.a();
    }

    public int a() {
        Integer num;
        if (this.a != null && (num = this.a.b) != null) {
            if (com.kwai.filedownloader.e.d.a) {
                com.kwai.filedownloader.e.d.c(this, "initial FileDownloader manager with the customize maxNetworkThreadCount: %d", num);
            }
            return com.kwai.filedownloader.e.e.a(num.intValue());
        }
        return h();
    }

    public com.kwai.filedownloader.a.a b() {
        if (this.a == null || this.a.a == null) {
            return i();
        }
        com.kwai.filedownloader.a.a aVarA = this.a.a.a();
        if (aVarA == null) {
            return i();
        }
        if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.c(this, "initial FileDownloader manager with the customize database: %s", aVarA);
        }
        return aVarA;
    }

    public c.e c() {
        c.e eVar;
        if (this.a != null && (eVar = this.a.c) != null) {
            if (com.kwai.filedownloader.e.d.a) {
                com.kwai.filedownloader.e.d.c(this, "initial FileDownloader manager with the customize output stream: %s", eVar);
            }
            return eVar;
        }
        return j();
    }

    public c.b d() {
        c.b bVar;
        if (this.a != null && (bVar = this.a.d) != null) {
            if (com.kwai.filedownloader.e.d.a) {
                com.kwai.filedownloader.e.d.c(this, "initial FileDownloader manager with the customize connection creator: %s", bVar);
            }
            return bVar;
        }
        return k();
    }

    public c.a e() {
        c.a aVar;
        if (this.a != null && (aVar = this.a.e) != null) {
            if (com.kwai.filedownloader.e.d.a) {
                com.kwai.filedownloader.e.d.c(this, "initial FileDownloader manager with the customize connection count adapter: %s", aVar);
            }
            return aVar;
        }
        return l();
    }

    public c.d f() {
        c.d dVar;
        if (this.a != null && (dVar = this.a.f) != null) {
            if (com.kwai.filedownloader.e.d.a) {
                com.kwai.filedownloader.e.d.c(this, "initial FileDownloader manager with the customize id generator: %s", dVar);
            }
            return dVar;
        }
        return g();
    }
}
