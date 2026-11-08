package b.a.f;

import anet.channel.util.HttpConstant;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class c {

    /* renamed from: a, reason: collision with root package name */
    public static final c.f f850a = c.f.a(":");

    /* renamed from: b, reason: collision with root package name */
    public static final c.f f851b = c.f.a(HttpConstant.STATUS);

    /* renamed from: c, reason: collision with root package name */
    public static final c.f f852c = c.f.a(":method");
    public static final c.f d = c.f.a(":path");
    public static final c.f e = c.f.a(":scheme");
    public static final c.f f = c.f.a(":authority");
    public final c.f g;
    public final c.f h;
    final int i;

    public c(String str, String str2) {
        this(c.f.a(str), c.f.a(str2));
    }

    public c(c.f fVar, String str) {
        this(fVar, c.f.a(str));
    }

    public c(c.f fVar, c.f fVar2) {
        this.g = fVar;
        this.h = fVar2;
        this.i = fVar.k() + 32 + fVar2.k();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return this.g.equals(cVar.g) && this.h.equals(cVar.h);
    }

    public int hashCode() {
        return ((527 + this.g.hashCode()) * 31) + this.h.hashCode();
    }

    public String toString() {
        return b.a.c.a("%s: %s", this.g.a(), this.h.a());
    }
}
