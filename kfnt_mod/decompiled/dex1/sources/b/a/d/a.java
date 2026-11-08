package b.a.d;

import anet.channel.util.HttpConstant;
import b.ac;
import b.ad;
import b.ae;
import b.m;
import b.n;
import b.w;
import b.x;
import c.p;
import java.io.IOException;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class a implements w {

    /* renamed from: a, reason: collision with root package name */
    private final n f814a;

    public a(n nVar) {
        this.f814a = nVar;
    }

    @Override // b.w
    public ae a(w.a aVar) throws IOException {
        ac acVarA = aVar.a();
        ac.a aVarF = acVarA.f();
        ad adVarD = acVarA.d();
        if (adVarD != null) {
            x xVarB = adVarD.b();
            if (xVarB != null) {
                aVarF.a("Content-Type", xVarB.toString());
            }
            long jC = adVarD.c();
            if (jC != -1) {
                aVarF.a("Content-Length", Long.toString(jC));
                aVarF.b("Transfer-Encoding");
            } else {
                aVarF.a("Transfer-Encoding", "chunked");
                aVarF.b("Content-Length");
            }
        }
        boolean z = false;
        if (acVarA.a(HttpConstant.HOST) == null) {
            aVarF.a(HttpConstant.HOST, b.a.c.a(acVarA.a(), false));
        }
        if (acVarA.a("Connection") == null) {
            aVarF.a("Connection", "Keep-Alive");
        }
        if (acVarA.a(HttpConstant.ACCEPT_ENCODING) == null && acVarA.a("Range") == null) {
            z = true;
            aVarF.a(HttpConstant.ACCEPT_ENCODING, HttpConstant.GZIP);
        }
        List<m> listA = this.f814a.a(acVarA.a());
        if (!listA.isEmpty()) {
            aVarF.a(HttpConstant.COOKIE, a(listA));
        }
        if (acVarA.a("User-Agent") == null) {
            aVarF.a("User-Agent", b.a.d.a());
        }
        ae aeVarA = aVar.a(aVarF.d());
        e.a(this.f814a, acVarA.a(), aeVarA.g());
        ae.a aVarA = aeVarA.i().a(acVarA);
        if (z && HttpConstant.GZIP.equalsIgnoreCase(aeVarA.b("Content-Encoding")) && e.d(aeVarA)) {
            c.l lVar = new c.l(aeVarA.h().c());
            aVarA.a(aeVarA.g().d().c("Content-Encoding").c("Content-Length").a());
            aVarA.a(new h(aeVarA.b("Content-Type"), -1L, p.a(lVar)));
        }
        return aVarA.a();
    }

    private String a(List<m> list) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append("; ");
            }
            m mVar = list.get(i);
            sb.append(mVar.a());
            sb.append('=');
            sb.append(mVar.b());
        }
        return sb.toString();
    }
}
