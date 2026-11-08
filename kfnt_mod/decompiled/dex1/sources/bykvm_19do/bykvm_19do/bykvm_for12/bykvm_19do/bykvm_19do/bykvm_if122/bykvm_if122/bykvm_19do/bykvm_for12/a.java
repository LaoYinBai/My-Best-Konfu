package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12;

import anet.channel.util.HttpConstant;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.a0;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.l;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.m;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.t;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.u;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.y;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.z;
import java.io.IOException;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class a implements t {

    /* renamed from: a, reason: collision with root package name */
    private final m f1529a;

    public a(m mVar) {
        this.f1529a = mVar;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.t
    public a0 a(t.a aVar) throws IOException {
        y yVarB = aVar.b();
        y.a aVarF = yVarB.f();
        z zVarA = yVarB.a();
        if (zVarA != null) {
            u uVarB = zVarA.b();
            if (uVarB != null) {
                aVarF.b("Content-Type", uVarB.toString());
            }
            long jA = zVarA.a();
            if (jA != -1) {
                aVarF.b("Content-Length", Long.toString(jA));
                aVarF.a("Transfer-Encoding");
            } else {
                aVarF.b("Transfer-Encoding", "chunked");
                aVarF.a("Content-Length");
            }
        }
        boolean z = false;
        if (yVarB.a(HttpConstant.HOST) == null) {
            aVarF.b(HttpConstant.HOST, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(yVarB.g(), false));
        }
        if (yVarB.a("Connection") == null) {
            aVarF.b("Connection", "Keep-Alive");
        }
        if (yVarB.a(HttpConstant.ACCEPT_ENCODING) == null && yVarB.a("Range") == null) {
            z = true;
            aVarF.b(HttpConstant.ACCEPT_ENCODING, HttpConstant.GZIP);
        }
        List<l> listA = this.f1529a.a(yVarB.g());
        if (!listA.isEmpty()) {
            aVarF.b(HttpConstant.COOKIE, a(listA));
        }
        if (yVarB.a("User-Agent") == null) {
            aVarF.b("User-Agent", bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.d.a());
        }
        a0 a0VarA = aVar.a(aVarF.a());
        e.a(this.f1529a, yVarB.g(), a0VarA.m());
        a0.a aVarA = a0VarA.p().a(yVarB);
        if (z && HttpConstant.GZIP.equalsIgnoreCase(a0VarA.b("Content-Encoding")) && e.b(a0VarA)) {
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.j jVar = new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.j(a0VarA.i().k());
            aVarA.a(a0VarA.m().a().b("Content-Encoding").b("Content-Length").a());
            aVarA.a(new h(a0VarA.b("Content-Type"), -1L, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.l.a(jVar)));
        }
        return aVarA.a();
    }

    private String a(List<l> list) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append("; ");
            }
            l lVar = list.get(i);
            sb.append(lVar.a());
            sb.append('=');
            sb.append(lVar.b());
        }
        return sb.toString();
    }
}
