package b;

import java.io.IOException;
import java.util.ArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class ab implements e {

    /* renamed from: a, reason: collision with root package name */
    final z f991a;

    /* renamed from: b, reason: collision with root package name */
    final b.a.d.j f992b;

    /* renamed from: c, reason: collision with root package name */
    final ac f993c;
    final boolean d;
    private r e;
    private boolean f;

    private ab(z zVar, ac acVar, boolean z) {
        this.f991a = zVar;
        this.f993c = acVar;
        this.d = z;
        this.f992b = new b.a.d.j(zVar, z);
    }

    static ab a(z zVar, ac acVar, boolean z) {
        ab abVar = new ab(zVar, acVar, z);
        abVar.e = zVar.z().a(abVar);
        return abVar;
    }

    @Override // b.e
    public ac a() {
        return this.f993c;
    }

    @Override // b.e
    public ae b() throws IOException {
        synchronized (this) {
            if (this.f) {
                throw new IllegalStateException("Already Executed");
            }
            this.f = true;
        }
        l();
        this.e.a(this);
        try {
            try {
                this.f991a.u().a(this);
                ae aeVarK = k();
                if (aeVarK != null) {
                    return aeVarK;
                }
                throw new IOException("Canceled");
            } catch (IOException e) {
                this.e.a(this, e);
                throw e;
            }
        } finally {
            this.f991a.u().b(this);
        }
    }

    private void l() {
        this.f992b.a(b.a.h.f.c().a("response.body().close()"));
    }

    @Override // b.e
    public void a(f fVar) {
        synchronized (this) {
            if (this.f) {
                throw new IllegalStateException("Already Executed");
            }
            this.f = true;
        }
        l();
        this.e.a(this);
        this.f991a.u().a(new a(fVar));
    }

    @Override // b.e
    public void c() throws IOException {
        this.f992b.a();
    }

    @Override // b.e
    public synchronized boolean d() {
        return this.f;
    }

    @Override // b.e
    public boolean e() {
        return this.f992b.b();
    }

    @Override // b.e
    /* renamed from: g, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ab f() {
        return a(this.f991a, this.f993c, this.d);
    }

    b.a.c.g h() {
        return this.f992b.c();
    }

    final class a extends b.a.b {

        /* renamed from: c, reason: collision with root package name */
        private final f f995c;

        a(f fVar) {
            super("OkHttp %s", ab.this.j());
            this.f995c = fVar;
        }

        String a() {
            return ab.this.f993c.a().i();
        }

        ac b() {
            return ab.this.f993c;
        }

        ab c() {
            return ab.this;
        }

        @Override // b.a.b
        protected void d() {
            IOException e;
            ae aeVarK;
            boolean z = true;
            try {
                try {
                    aeVarK = ab.this.k();
                } catch (IOException e2) {
                    e = e2;
                    z = false;
                }
                try {
                    if (ab.this.f992b.b()) {
                        this.f995c.a(ab.this, new IOException("Canceled"));
                    } else {
                        this.f995c.a(ab.this, aeVarK);
                    }
                } catch (IOException e3) {
                    e = e3;
                    if (!z) {
                        ab.this.e.a(ab.this, e);
                        this.f995c.a(ab.this, e);
                    } else {
                        b.a.h.f.c().a(4, "Callback failure for " + ab.this.i(), e);
                    }
                }
            } finally {
                ab.this.f991a.u().b(this);
            }
        }
    }

    String i() {
        StringBuilder sb = new StringBuilder();
        sb.append(e() ? "canceled " : "");
        sb.append(this.d ? "web socket" : "call");
        sb.append(" to ");
        sb.append(j());
        return sb.toString();
    }

    String j() {
        return this.f993c.a().u();
    }

    ae k() throws IOException {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f991a.x());
        arrayList.add(this.f992b);
        arrayList.add(new b.a.d.a(this.f991a.g()));
        arrayList.add(new b.a.a.a(this.f991a.i()));
        arrayList.add(new b.a.c.a(this.f991a));
        if (!this.d) {
            arrayList.addAll(this.f991a.y());
        }
        arrayList.add(new b.a.d.b(this.d));
        return new b.a.d.g(arrayList, null, null, null, 0, this.f993c, this, this.e, this.f991a.a(), this.f991a.b(), this.f991a.c()).a(this.f993c);
    }
}
