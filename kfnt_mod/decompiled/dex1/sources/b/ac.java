package b;

import anet.channel.request.Request;
import anet.channel.util.HttpConstant;
import b.u;
import java.net.URL;
import java.util.List;
import javax.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class ac {

    /* renamed from: a, reason: collision with root package name */
    final v f996a;

    /* renamed from: b, reason: collision with root package name */
    final String f997b;

    /* renamed from: c, reason: collision with root package name */
    final u f998c;

    @Nullable
    final ad d;
    final Object e;
    private volatile d f;

    ac(a aVar) {
        this.f996a = aVar.f999a;
        this.f997b = aVar.f1000b;
        this.f998c = aVar.f1001c.a();
        this.d = aVar.d;
        this.e = aVar.e != null ? aVar.e : this;
    }

    public v a() {
        return this.f996a;
    }

    public String b() {
        return this.f997b;
    }

    public u c() {
        return this.f998c;
    }

    @Nullable
    public String a(String str) {
        return this.f998c.a(str);
    }

    public List<String> b(String str) {
        return this.f998c.c(str);
    }

    @Nullable
    public ad d() {
        return this.d;
    }

    public Object e() {
        return this.e;
    }

    public a f() {
        return new a(this);
    }

    public d g() {
        d dVar = this.f;
        if (dVar != null) {
            return dVar;
        }
        d dVarA = d.a(this.f998c);
        this.f = dVarA;
        return dVarA;
    }

    public boolean h() {
        return this.f996a.d();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Request{method=");
        sb.append(this.f997b);
        sb.append(", url=");
        sb.append(this.f996a);
        sb.append(", tag=");
        sb.append(this.e != this ? this.e : null);
        sb.append('}');
        return sb.toString();
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        v f999a;

        /* renamed from: b, reason: collision with root package name */
        String f1000b;

        /* renamed from: c, reason: collision with root package name */
        u.a f1001c;
        ad d;
        Object e;

        public a() {
            this.f1000b = "GET";
            this.f1001c = new u.a();
        }

        a(ac acVar) {
            this.f999a = acVar.f996a;
            this.f1000b = acVar.f997b;
            this.d = acVar.d;
            this.e = acVar.e;
            this.f1001c = acVar.f998c.d();
        }

        public a a(v vVar) {
            if (vVar == null) {
                throw new NullPointerException("url == null");
            }
            this.f999a = vVar;
            return this;
        }

        public a a(String str) {
            if (str == null) {
                throw new NullPointerException("url == null");
            }
            if (str.regionMatches(true, 0, "ws:", 0, 3)) {
                str = "http:" + str.substring(3);
            } else if (str.regionMatches(true, 0, "wss:", 0, 4)) {
                str = "https:" + str.substring(4);
            }
            v vVarG = v.g(str);
            if (vVarG == null) {
                throw new IllegalArgumentException("unexpected url: " + str);
            }
            return a(vVarG);
        }

        public a a(URL url) {
            if (url == null) {
                throw new NullPointerException("url == null");
            }
            v vVarA = v.a(url);
            if (vVarA == null) {
                throw new IllegalArgumentException("unexpected url: " + url);
            }
            return a(vVarA);
        }

        public a a(String str, String str2) {
            this.f1001c.c(str, str2);
            return this;
        }

        public a b(String str, String str2) {
            this.f1001c.a(str, str2);
            return this;
        }

        public a b(String str) {
            this.f1001c.c(str);
            return this;
        }

        public a a(u uVar) {
            this.f1001c = uVar.d();
            return this;
        }

        public a a(d dVar) {
            String string = dVar.toString();
            return string.isEmpty() ? b(HttpConstant.CACHE_CONTROL) : a(HttpConstant.CACHE_CONTROL, string);
        }

        public a a() {
            return a("GET", (ad) null);
        }

        public a b() {
            return a(Request.Method.HEAD, (ad) null);
        }

        public a a(ad adVar) {
            return a("POST", adVar);
        }

        public a b(@Nullable ad adVar) {
            return a(Request.Method.DELETE, adVar);
        }

        public a c() {
            return b(b.a.c.d);
        }

        public a c(ad adVar) {
            return a(Request.Method.PUT, adVar);
        }

        public a d(ad adVar) {
            return a("PATCH", adVar);
        }

        public a a(String str, @Nullable ad adVar) {
            if (str == null) {
                throw new NullPointerException("method == null");
            }
            if (str.length() == 0) {
                throw new IllegalArgumentException("method.length() == 0");
            }
            if (adVar != null && !b.a.d.f.c(str)) {
                throw new IllegalArgumentException("method " + str + " must not have a request body.");
            }
            if (adVar == null && b.a.d.f.b(str)) {
                throw new IllegalArgumentException("method " + str + " must have a request body.");
            }
            this.f1000b = str;
            this.d = adVar;
            return this;
        }

        public a a(Object obj) {
            this.e = obj;
            return this;
        }

        public ac d() {
            if (this.f999a == null) {
                throw new IllegalStateException("url == null");
            }
            return new ac(this);
        }
    }
}
