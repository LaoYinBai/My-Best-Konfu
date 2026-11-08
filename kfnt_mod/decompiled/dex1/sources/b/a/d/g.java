package b.a.d;

import b.ac;
import b.ae;
import b.r;
import b.w;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class g implements w.a {

    /* renamed from: a, reason: collision with root package name */
    private final List<w> f824a;

    /* renamed from: b, reason: collision with root package name */
    private final b.a.c.g f825b;

    /* renamed from: c, reason: collision with root package name */
    private final c f826c;
    private final b.a.c.c d;
    private final int e;
    private final ac f;
    private final b.e g;
    private final r h;
    private final int i;
    private final int j;
    private final int k;
    private int l;

    public g(List<w> list, b.a.c.g gVar, c cVar, b.a.c.c cVar2, int i, ac acVar, b.e eVar, r rVar, int i2, int i3, int i4) {
        this.f824a = list;
        this.d = cVar2;
        this.f825b = gVar;
        this.f826c = cVar;
        this.e = i;
        this.f = acVar;
        this.g = eVar;
        this.h = rVar;
        this.i = i2;
        this.j = i3;
        this.k = i4;
    }

    @Override // b.w.a
    public b.j b() {
        return this.d;
    }

    @Override // b.w.a
    public int d() {
        return this.i;
    }

    @Override // b.w.a
    public w.a a(int i, TimeUnit timeUnit) {
        return new g(this.f824a, this.f825b, this.f826c, this.d, this.e, this.f, this.g, this.h, b.a.c.a("timeout", i, timeUnit), this.j, this.k);
    }

    @Override // b.w.a
    public int e() {
        return this.j;
    }

    @Override // b.w.a
    public w.a b(int i, TimeUnit timeUnit) {
        return new g(this.f824a, this.f825b, this.f826c, this.d, this.e, this.f, this.g, this.h, this.i, b.a.c.a("timeout", i, timeUnit), this.k);
    }

    @Override // b.w.a
    public int f() {
        return this.k;
    }

    @Override // b.w.a
    public w.a c(int i, TimeUnit timeUnit) {
        return new g(this.f824a, this.f825b, this.f826c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, b.a.c.a("timeout", i, timeUnit));
    }

    public b.a.c.g g() {
        return this.f825b;
    }

    public c h() {
        return this.f826c;
    }

    @Override // b.w.a
    public b.e c() {
        return this.g;
    }

    public r i() {
        return this.h;
    }

    @Override // b.w.a
    public ac a() {
        return this.f;
    }

    @Override // b.w.a
    public ae a(ac acVar) throws IOException {
        return a(acVar, this.f825b, this.f826c, this.d);
    }

    public ae a(ac acVar, b.a.c.g gVar, c cVar, b.a.c.c cVar2) throws IOException {
        if (this.e >= this.f824a.size()) {
            throw new AssertionError();
        }
        this.l++;
        if (this.f826c != null && !this.d.a(acVar.a())) {
            throw new IllegalStateException("network interceptor " + this.f824a.get(this.e - 1) + " must retain the same host and port");
        }
        if (this.f826c != null && this.l > 1) {
            throw new IllegalStateException("network interceptor " + this.f824a.get(this.e - 1) + " must call proceed() exactly once");
        }
        g gVar2 = new g(this.f824a, gVar, cVar, cVar2, this.e + 1, acVar, this.g, this.h, this.i, this.j, this.k);
        w wVar = this.f824a.get(this.e);
        ae aeVarA = wVar.a(gVar2);
        if (cVar != null && this.e + 1 < this.f824a.size() && gVar2.l != 1) {
            throw new IllegalStateException("network interceptor " + wVar + " must call proceed() exactly once");
        }
        if (aeVarA == null) {
            throw new NullPointerException("interceptor " + wVar + " returned null");
        }
        if (aeVarA.h() != null) {
            return aeVarA;
        }
        throw new IllegalStateException("interceptor " + wVar + " returned a response with no body");
    }
}
