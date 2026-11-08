package b.a.d;

import b.ac;
import b.ae;
import b.w;
import c.p;
import c.x;
import java.io.IOException;
import java.net.ProtocolException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class b implements w {

    /* renamed from: a, reason: collision with root package name */
    private final boolean f815a;

    public b(boolean z) {
        this.f815a = z;
    }

    @Override // b.w
    public ae a(w.a aVar) throws IOException {
        ae aeVarA;
        g gVar = (g) aVar;
        c cVarH = gVar.h();
        b.a.c.g gVarG = gVar.g();
        b.a.c.c cVar = (b.a.c.c) gVar.b();
        ac acVarA = gVar.a();
        long jCurrentTimeMillis = System.currentTimeMillis();
        gVar.i().c(gVar.c());
        cVarH.a(acVarA);
        gVar.i().a(gVar.c(), acVarA);
        ae.a aVarA = null;
        if (f.c(acVarA.b()) && acVarA.d() != null) {
            if ("100-continue".equalsIgnoreCase(acVarA.a("Expect"))) {
                cVarH.a();
                gVar.i().e(gVar.c());
                aVarA = cVarH.a(true);
            }
            if (aVarA == null) {
                gVar.i().d(gVar.c());
                a aVar2 = new a(cVarH.a(acVarA, acVarA.d().c()));
                c.d dVarA = p.a(aVar2);
                acVarA.d().a(dVarA);
                dVarA.close();
                gVar.i().a(gVar.c(), aVar2.f816a);
            } else if (!cVar.f()) {
                gVarG.e();
            }
        }
        cVarH.b();
        if (aVarA == null) {
            gVar.i().e(gVar.c());
            aVarA = cVarH.a(false);
        }
        ae aeVarA2 = aVarA.a(acVarA).a(gVarG.c().c()).a(jCurrentTimeMillis).b(System.currentTimeMillis()).a();
        int iC = aeVarA2.c();
        if (iC == 100) {
            aeVarA2 = cVarH.a(false).a(acVarA).a(gVarG.c().c()).a(jCurrentTimeMillis).b(System.currentTimeMillis()).a();
            iC = aeVarA2.c();
        }
        gVar.i().a(gVar.c(), aeVarA2);
        if (this.f815a && iC == 101) {
            aeVarA = aeVarA2.i().a(b.a.c.f790c).a();
        } else {
            aeVarA = aeVarA2.i().a(cVarH.a(aeVarA2)).a();
        }
        if ("close".equalsIgnoreCase(aeVarA.a().a("Connection")) || "close".equalsIgnoreCase(aeVarA.b("Connection"))) {
            gVarG.e();
        }
        if ((iC != 204 && iC != 205) || aeVarA.h().b() <= 0) {
            return aeVarA;
        }
        throw new ProtocolException("HTTP " + iC + " had non-zero Content-Length: " + aeVarA.h().b());
    }

    static final class a extends c.h {

        /* renamed from: a, reason: collision with root package name */
        long f816a;

        a(x xVar) {
            super(xVar);
        }

        @Override // c.h, c.x
        public void a_(c.c cVar, long j) throws IOException {
            super.a_(cVar, j);
            this.f816a += j;
        }
    }
}
