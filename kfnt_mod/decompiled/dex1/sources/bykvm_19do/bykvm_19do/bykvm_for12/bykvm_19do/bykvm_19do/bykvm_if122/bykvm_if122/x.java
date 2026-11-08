package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122;

import java.io.IOException;
import java.util.ArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class x implements e {

    /* renamed from: a, reason: collision with root package name */
    final v f1750a;

    /* renamed from: b, reason: collision with root package name */
    final bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12.j f1751b;

    /* renamed from: c, reason: collision with root package name */
    private p f1752c;
    final y d;
    final boolean e;
    private boolean f;

    final class a extends bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.b {

        /* renamed from: b, reason: collision with root package name */
        private final f f1753b;

        a(f fVar) {
            super("OkHttp %s", x.this.c());
            this.f1753b = fVar;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.b
        protected void b() {
            IOException e;
            boolean z = true;
            try {
                try {
                    a0 a0VarA = x.this.a();
                    try {
                        if (x.this.f1751b.a()) {
                            this.f1753b.a(x.this, new IOException("Canceled"));
                        } else {
                            this.f1753b.a(x.this, a0VarA);
                        }
                    } catch (IOException e2) {
                        e = e2;
                        if (z) {
                            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_try19.e.b().a(4, "Callback failure for " + x.this.d(), e);
                        } else {
                            x.this.f1752c.a(x.this, e);
                            this.f1753b.a(x.this, e);
                        }
                    }
                } finally {
                    x.this.f1750a.g().b(this);
                }
            } catch (IOException e3) {
                e = e3;
                z = false;
            }
        }

        String c() {
            return x.this.d.g().g();
        }
    }

    private x(v vVar, y yVar, boolean z) {
        this.f1750a = vVar;
        this.d = yVar;
        this.e = z;
        this.f1751b = new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12.j(vVar, z);
    }

    private void e() {
        this.f1751b.a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_try19.e.b().a("response.body().close()"));
    }

    public boolean b() {
        return this.f1751b.a();
    }

    String c() {
        return this.d.g().l();
    }

    String d() {
        StringBuilder sb = new StringBuilder();
        sb.append(b() ? "canceled " : "");
        sb.append(this.e ? "web socket" : "call");
        sb.append(" to ");
        sb.append(c());
        return sb.toString();
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.e
    public a0 i() throws IOException {
        synchronized (this) {
            if (this.f) {
                throw new IllegalStateException("Already Executed");
            }
            this.f = true;
        }
        e();
        this.f1752c.b(this);
        try {
            try {
                this.f1750a.g().a(this);
                a0 a0VarA = a();
                if (a0VarA != null) {
                    return a0VarA;
                }
                throw new IOException("Canceled");
            } catch (IOException e) {
                this.f1752c.a(this, e);
                throw e;
            }
        } finally {
            this.f1750a.g().b(this);
        }
    }

    static x a(v vVar, y yVar, boolean z) {
        x xVar = new x(vVar, yVar, z);
        xVar.f1752c = vVar.j().a(xVar);
        return xVar;
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public x m5clone() {
        return a(this.f1750a, this.d, this.e);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.e
    public void a(f fVar) {
        synchronized (this) {
            if (!this.f) {
                this.f = true;
            } else {
                throw new IllegalStateException("Already Executed");
            }
        }
        e();
        this.f1752c.b(this);
        this.f1750a.g().a(new a(fVar));
    }

    a0 a() throws IOException {
        ArrayList arrayList = new ArrayList(this.f1750a.n());
        arrayList.add(this.f1751b);
        arrayList.add(new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12.a(this.f1750a.f()));
        arrayList.add(new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_19do.a(this.f1750a.o()));
        arrayList.add(new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_if122.a(this.f1750a));
        if (!this.e) {
            arrayList.addAll(this.f1750a.p());
        }
        arrayList.add(new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12.b(this.e));
        return new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12.g(arrayList, null, null, null, 0, this.d, this, this.f1752c, this.f1750a.c(), this.f1750a.u(), this.f1750a.y()).a(this.d);
    }
}
