package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122;

import anet.channel.util.HttpConstant;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.r;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class y {

    /* renamed from: a, reason: collision with root package name */
    final s f1755a;

    /* renamed from: b, reason: collision with root package name */
    final String f1756b;

    /* renamed from: c, reason: collision with root package name */
    final r f1757c;
    final z d;
    final Object e;
    private volatile d f;

    y(a aVar) {
        this.f1755a = aVar.f1758a;
        this.f1756b = aVar.f1759b;
        this.f1757c = aVar.f1760c.a();
        this.d = aVar.d;
        Object obj = aVar.e;
        this.e = obj == null ? this : obj;
    }

    public String a(String str) {
        return this.f1757c.a(str);
    }

    public d b() {
        d dVar = this.f;
        if (dVar != null) {
            return dVar;
        }
        d dVarA = d.a(this.f1757c);
        this.f = dVarA;
        return dVarA;
    }

    public r c() {
        return this.f1757c;
    }

    public boolean d() {
        return this.f1755a.h();
    }

    public String e() {
        return this.f1756b;
    }

    public a f() {
        return new a(this);
    }

    public s g() {
        return this.f1755a;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Request{method=");
        sb.append(this.f1756b);
        sb.append(", url=");
        sb.append(this.f1755a);
        sb.append(", tag=");
        Object obj = this.e;
        if (obj == this) {
            obj = null;
        }
        sb.append(obj);
        sb.append('}');
        return sb.toString();
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        s f1758a;

        /* renamed from: b, reason: collision with root package name */
        String f1759b;

        /* renamed from: c, reason: collision with root package name */
        r.a f1760c;
        z d;
        Object e;

        public a() {
            this.f1759b = "GET";
            this.f1760c = new r.a();
        }

        public a a(s sVar) {
            if (sVar == null) {
                throw new NullPointerException("url == null");
            }
            this.f1758a = sVar;
            return this;
        }

        public a b(String str) {
            if (str == null) {
                throw new NullPointerException("url == null");
            }
            if (str.regionMatches(true, 0, "ws:", 0, 3)) {
                str = "http:" + str.substring(3);
            } else if (str.regionMatches(true, 0, "wss:", 0, 4)) {
                str = "https:" + str.substring(4);
            }
            s sVarD = s.d(str);
            if (sVarD != null) {
                return a(sVarD);
            }
            throw new IllegalArgumentException("unexpected url: " + str);
        }

        public a a(String str, String str2) {
            this.f1760c.a(str, str2);
            return this;
        }

        a(y yVar) {
            this.f1758a = yVar.f1755a;
            this.f1759b = yVar.f1756b;
            this.d = yVar.d;
            this.e = yVar.e;
            this.f1760c = yVar.f1757c.a();
        }

        public a a(String str) {
            this.f1760c.b(str);
            return this;
        }

        public a a(r rVar) {
            this.f1760c = rVar.a();
            return this;
        }

        public a a(d dVar) {
            String string = dVar.toString();
            return string.isEmpty() ? a(HttpConstant.CACHE_CONTROL) : b(HttpConstant.CACHE_CONTROL, string);
        }

        public a a(z zVar) {
            return a("POST", zVar);
        }

        public a a(String str, z zVar) {
            if (str != null) {
                if (str.length() != 0) {
                    if (zVar != null && !bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12.f.b(str)) {
                        throw new IllegalArgumentException("method " + str + " must not have a request body.");
                    }
                    if (zVar == null && bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12.f.e(str)) {
                        throw new IllegalArgumentException("method " + str + " must have a request body.");
                    }
                    this.f1759b = str;
                    this.d = zVar;
                    return this;
                }
                throw new IllegalArgumentException("method.length() == 0");
            }
            throw new NullPointerException("method == null");
        }

        public a b(String str, String str2) {
            this.f1760c.c(str, str2);
            return this;
        }

        public a b() {
            return a("GET", (z) null);
        }

        public a a(Object obj) {
            this.e = obj;
            return this;
        }

        public y a() {
            if (this.f1758a != null) {
                return new y(this);
            }
            throw new IllegalStateException("url == null");
        }
    }

    public z a() {
        return this.d;
    }
}
