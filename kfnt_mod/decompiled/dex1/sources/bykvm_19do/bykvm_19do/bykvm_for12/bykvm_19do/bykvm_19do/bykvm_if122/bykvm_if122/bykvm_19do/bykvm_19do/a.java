package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_19do;

import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.l;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.r;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.a0;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.b0;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_19do.c;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12.f;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12.h;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.r;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.t;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.w;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.y;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class a implements t {

    /* renamed from: a, reason: collision with root package name */
    final d f1511a;

    /* renamed from: bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_19do.a$a, reason: collision with other inner class name */
    class C0051a implements s {

        /* renamed from: a, reason: collision with root package name */
        boolean f1512a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ e f1513b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ b f1514c;
        final /* synthetic */ bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.d d;

        C0051a(a aVar, e eVar, b bVar, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.d dVar) {
            this.f1513b = eVar;
            this.f1514c = bVar;
            this.d = dVar;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s
        public long a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c cVar, long j) throws IOException {
            try {
                long jA = this.f1513b.a(cVar, j);
                if (jA != -1) {
                    cVar.a(this.d.a(), cVar.o() - jA, jA);
                    this.d.h();
                    return jA;
                }
                if (!this.f1512a) {
                    this.f1512a = true;
                    this.d.close();
                }
                return -1L;
            } catch (IOException e) {
                if (!this.f1512a) {
                    this.f1512a = true;
                    this.f1514c.a();
                }
                throw e;
            }
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s
        public bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.t b() {
            return this.f1513b.b();
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (!this.f1512a && !bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(this, 100, TimeUnit.MILLISECONDS)) {
                this.f1512a = true;
                this.f1514c.a();
            }
            this.f1513b.close();
        }
    }

    public a(d dVar) {
        this.f1511a = dVar;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.t
    public a0 a(t.a aVar) throws IOException {
        d dVar = this.f1511a;
        a0 a0VarB = dVar != null ? dVar.b(aVar.b()) : null;
        c cVarA = new c.a(System.currentTimeMillis(), aVar.b(), a0VarB).a();
        y yVar = cVarA.f1515a;
        a0 a0Var = cVarA.f1516b;
        d dVar2 = this.f1511a;
        if (dVar2 != null) {
            dVar2.a(cVarA);
        }
        if (a0VarB != null && a0Var == null) {
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(a0VarB.i());
        }
        if (yVar == null && a0Var == null) {
            return new a0.a().a(aVar.b()).a(w.HTTP_1_1).a(504).a("Unsatisfiable Request (only-if-cached)").a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.f1678c).b(-1L).a(System.currentTimeMillis()).a();
        }
        if (yVar == null) {
            return a0Var.p().a(a(a0Var)).a();
        }
        try {
            a0 a0VarA = aVar.a(yVar);
            if (a0VarA == null && a0VarB != null) {
            }
            if (a0Var != null) {
                if (a0VarA.k() == 304) {
                    a0 a0VarA2 = a0Var.p().a(a(a0Var.m(), a0VarA.m())).b(a0VarA.t()).a(a0VarA.r()).a(a(a0Var)).b(a(a0VarA)).a();
                    a0VarA.i().close();
                    this.f1511a.a();
                    this.f1511a.a(a0Var, a0VarA2);
                    return a0VarA2;
                }
                bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(a0Var.i());
            }
            a0 a0VarA3 = a0VarA.p().a(a(a0Var)).b(a(a0VarA)).a();
            if (this.f1511a != null) {
                if (bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12.e.b(a0VarA3) && c.a(a0VarA3, yVar)) {
                    return a(this.f1511a.a(a0VarA3), a0VarA3);
                }
                if (f.a(yVar.e())) {
                    try {
                        this.f1511a.a(yVar);
                    } catch (IOException unused) {
                    }
                }
            }
            return a0VarA3;
        } finally {
            if (a0VarB != null) {
                bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(a0VarB.i());
            }
        }
    }

    private static a0 a(a0 a0Var) {
        return (a0Var == null || a0Var.i() == null) ? a0Var : a0Var.p().a((b0) null).a();
    }

    private a0 a(b bVar, a0 a0Var) throws IOException {
        r rVarB;
        if (bVar == null || (rVarB = bVar.b()) == null) {
            return a0Var;
        }
        return a0Var.p().a(new h(a0Var.b("Content-Type"), a0Var.i().i(), l.a(new C0051a(this, a0Var.i().k(), bVar, l.a(rVarB))))).a();
    }

    private static bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.r a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.r rVar, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.r rVar2) {
        r.a aVar = new r.a();
        int iB = rVar.b();
        for (int i = 0; i < iB; i++) {
            String strA = rVar.a(i);
            String strB = rVar.b(i);
            if ((!"Warning".equalsIgnoreCase(strA) || !strB.startsWith("1")) && (!a(strA) || rVar2.a(strA) == null)) {
                bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.a.f1509a.a(aVar, strA, strB);
            }
        }
        int iB2 = rVar2.b();
        for (int i2 = 0; i2 < iB2; i2++) {
            String strA2 = rVar2.a(i2);
            if (!"Content-Length".equalsIgnoreCase(strA2) && a(strA2)) {
                bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.a.f1509a.a(aVar, strA2, rVar2.b(i2));
            }
        }
        return aVar.a();
    }

    static boolean a(String str) {
        return ("Connection".equalsIgnoreCase(str) || "Keep-Alive".equalsIgnoreCase(str) || "Proxy-Authenticate".equalsIgnoreCase(str) || "Proxy-Authorization".equalsIgnoreCase(str) || "TE".equalsIgnoreCase(str) || "Trailers".equalsIgnoreCase(str) || "Transfer-Encoding".equalsIgnoreCase(str) || "Upgrade".equalsIgnoreCase(str)) ? false : true;
    }
}
