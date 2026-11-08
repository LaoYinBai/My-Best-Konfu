package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12;

import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.l;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.r;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.a0;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.t;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.y;
import java.io.IOException;
import java.net.ProtocolException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class b implements t {

    /* renamed from: a, reason: collision with root package name */
    private final boolean f1530a;

    static final class a extends bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.g {

        /* renamed from: b, reason: collision with root package name */
        long f1531b;

        a(r rVar) {
            super(rVar);
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.g, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.r
        public void b(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c cVar, long j) throws IOException {
            super.b(cVar, j);
            this.f1531b += j;
        }
    }

    public b(boolean z) {
        this.f1530a = z;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.t
    public a0 a(t.a aVar) throws IOException {
        g gVar = (g) aVar;
        c cVarH = gVar.h();
        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_if122.g gVarI = gVar.i();
        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_if122.c cVar = (bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_if122.c) gVar.f();
        y yVarB = gVar.b();
        long jCurrentTimeMillis = System.currentTimeMillis();
        gVar.g().d(gVar.e());
        cVarH.a(yVarB);
        gVar.g().a(gVar.e(), yVarB);
        a0.a aVarA = null;
        if (f.b(yVarB.e()) && yVarB.a() != null) {
            if ("100-continue".equalsIgnoreCase(yVarB.a("Expect"))) {
                cVarH.b();
                gVar.g().f(gVar.e());
                aVarA = cVarH.a(true);
            }
            if (aVarA == null) {
                gVar.g().c(gVar.e());
                a aVar2 = new a(cVarH.a(yVarB, yVarB.a().a()));
                bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.d dVarA = l.a(aVar2);
                yVarB.a().a(dVarA);
                dVarA.close();
                gVar.g().a(gVar.e(), aVar2.f1531b);
            } else if (!cVar.c()) {
                gVarI.d();
            }
        }
        cVarH.a();
        if (aVarA == null) {
            gVar.g().f(gVar.e());
            aVarA = cVarH.a(false);
        }
        a0 a0VarA = aVarA.a(yVarB).a(gVarI.b().b()).b(jCurrentTimeMillis).a(System.currentTimeMillis()).a();
        gVar.g().a(gVar.e(), a0VarA);
        int iK = a0VarA.k();
        a0 a0VarA2 = (this.f1530a && iK == 101) ? a0VarA.p().a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.f1678c).a() : a0VarA.p().a(cVarH.a(a0VarA)).a();
        if ("close".equalsIgnoreCase(a0VarA2.s().a("Connection")) || "close".equalsIgnoreCase(a0VarA2.b("Connection"))) {
            gVarI.d();
        }
        if ((iK != 204 && iK != 205) || a0VarA2.i().i() <= 0) {
            return a0VarA2;
        }
        throw new ProtocolException("HTTP " + iK + " had non-zero Content-Length: " + a0VarA2.i().i());
    }
}
