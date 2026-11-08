package b.a.a;

import b.a.a.c;
import b.a.d.h;
import b.aa;
import b.ac;
import b.ae;
import b.af;
import b.u;
import b.w;
import c.p;
import c.x;
import c.y;
import c.z;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class a implements w {

    /* renamed from: a, reason: collision with root package name */
    final f f751a;

    public a(f fVar) {
        this.f751a = fVar;
    }

    @Override // b.w
    public ae a(w.a aVar) throws IOException {
        ae aeVarA = this.f751a != null ? this.f751a.a(aVar.a()) : null;
        c cVarA = new c.a(System.currentTimeMillis(), aVar.a(), aeVarA).a();
        ac acVar = cVarA.f755a;
        ae aeVar = cVarA.f756b;
        if (this.f751a != null) {
            this.f751a.a(cVarA);
        }
        if (aeVarA != null && aeVar == null) {
            b.a.c.a(aeVarA.h());
        }
        if (acVar == null && aeVar == null) {
            return new ae.a().a(aVar.a()).a(aa.HTTP_1_1).a(504).a("Unsatisfiable Request (only-if-cached)").a(b.a.c.f790c).a(-1L).b(System.currentTimeMillis()).a();
        }
        if (acVar == null) {
            return aeVar.i().b(a(aeVar)).a();
        }
        try {
            ae aeVarA2 = aVar.a(acVar);
            if (aeVarA2 == null && aeVarA != null) {
            }
            if (aeVar != null) {
                if (aeVarA2.c() == 304) {
                    ae aeVarA3 = aeVar.i().a(a(aeVar.g(), aeVarA2.g())).a(aeVarA2.p()).b(aeVarA2.q()).b(a(aeVar)).a(a(aeVarA2)).a();
                    aeVarA2.h().close();
                    this.f751a.a();
                    this.f751a.a(aeVar, aeVarA3);
                    return aeVarA3;
                }
                b.a.c.a(aeVar.h());
            }
            ae aeVarA4 = aeVarA2.i().b(a(aeVar)).a(a(aeVarA2)).a();
            if (this.f751a != null) {
                if (b.a.d.e.d(aeVarA4) && c.a(aeVarA4, acVar)) {
                    return a(this.f751a.a(aeVarA4), aeVarA4);
                }
                if (b.a.d.f.a(acVar.b())) {
                    try {
                        this.f751a.b(acVar);
                    } catch (IOException unused) {
                    }
                }
            }
            return aeVarA4;
        } finally {
            if (aeVarA != null) {
                b.a.c.a(aeVarA.h());
            }
        }
    }

    private static ae a(ae aeVar) {
        return (aeVar == null || aeVar.h() == null) ? aeVar : aeVar.i().a((af) null).a();
    }

    private ae a(final b bVar, ae aeVar) throws IOException {
        x xVarB;
        if (bVar == null || (xVarB = bVar.b()) == null) {
            return aeVar;
        }
        final c.e eVarC = aeVar.h().c();
        final c.d dVarA = p.a(xVarB);
        return aeVar.i().a(new h(aeVar.b("Content-Type"), aeVar.h().b(), p.a(new y() { // from class: b.a.a.a.1

            /* renamed from: a, reason: collision with root package name */
            boolean f752a;

            @Override // c.y
            public long a(c.c cVar, long j) throws IOException {
                try {
                    long jA = eVarC.a(cVar, j);
                    if (jA == -1) {
                        if (!this.f752a) {
                            this.f752a = true;
                            dVarA.close();
                        }
                        return -1L;
                    }
                    cVar.a(dVarA.c(), cVar.b() - jA, jA);
                    dVarA.I();
                    return jA;
                } catch (IOException e) {
                    if (!this.f752a) {
                        this.f752a = true;
                        bVar.a();
                    }
                    throw e;
                }
            }

            @Override // c.y
            public z a() {
                return eVarC.a();
            }

            @Override // c.y, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                if (!this.f752a && !b.a.c.a(this, 100, TimeUnit.MILLISECONDS)) {
                    this.f752a = true;
                    bVar.a();
                }
                eVarC.close();
            }
        }))).a();
    }

    private static u a(u uVar, u uVar2) {
        u.a aVar = new u.a();
        int iA = uVar.a();
        for (int i = 0; i < iA; i++) {
            String strA = uVar.a(i);
            String strB = uVar.b(i);
            if ((!"Warning".equalsIgnoreCase(strA) || !strB.startsWith("1")) && (b(strA) || !a(strA) || uVar2.a(strA) == null)) {
                b.a.a.f750a.a(aVar, strA, strB);
            }
        }
        int iA2 = uVar2.a();
        for (int i2 = 0; i2 < iA2; i2++) {
            String strA2 = uVar2.a(i2);
            if (!b(strA2) && a(strA2)) {
                b.a.a.f750a.a(aVar, strA2, uVar2.b(i2));
            }
        }
        return aVar.a();
    }

    static boolean a(String str) {
        return ("Connection".equalsIgnoreCase(str) || "Keep-Alive".equalsIgnoreCase(str) || "Proxy-Authenticate".equalsIgnoreCase(str) || "Proxy-Authorization".equalsIgnoreCase(str) || "TE".equalsIgnoreCase(str) || "Trailers".equalsIgnoreCase(str) || "Transfer-Encoding".equalsIgnoreCase(str) || "Upgrade".equalsIgnoreCase(str)) ? false : true;
    }

    static boolean b(String str) {
        return "Content-Length".equalsIgnoreCase(str) || "Content-Encoding".equalsIgnoreCase(str) || "Content-Type".equalsIgnoreCase(str);
    }
}
