package b;

import anet.channel.util.HttpConstant;
import b.u;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import java.io.Closeable;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class ae implements Closeable {

    /* renamed from: a, reason: collision with root package name */
    final ac f1009a;

    /* renamed from: b, reason: collision with root package name */
    final aa f1010b;

    /* renamed from: c, reason: collision with root package name */
    final int f1011c;
    final String d;

    @Nullable
    final t e;
    final u f;

    @Nullable
    final af g;

    @Nullable
    final ae h;

    @Nullable
    final ae i;

    @Nullable
    final ae j;
    final long k;
    final long l;
    private volatile d m;

    ae(a aVar) {
        this.f1009a = aVar.f1012a;
        this.f1010b = aVar.f1013b;
        this.f1011c = aVar.f1014c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f.a();
        this.g = aVar.g;
        this.h = aVar.h;
        this.i = aVar.i;
        this.j = aVar.j;
        this.k = aVar.k;
        this.l = aVar.l;
    }

    public ac a() {
        return this.f1009a;
    }

    public aa b() {
        return this.f1010b;
    }

    public int c() {
        return this.f1011c;
    }

    public boolean d() {
        return this.f1011c >= 200 && this.f1011c < 300;
    }

    public String e() {
        return this.d;
    }

    public t f() {
        return this.e;
    }

    public List<String> a(String str) {
        return this.f.c(str);
    }

    @Nullable
    public String b(String str) {
        return a(str, null);
    }

    @Nullable
    public String a(String str, @Nullable String str2) {
        String strA = this.f.a(str);
        return strA != null ? strA : str2;
    }

    public u g() {
        return this.f;
    }

    public af a(long j) throws IOException {
        c.e eVarC = this.g.c();
        eVarC.b(j);
        c.c cVarClone = eVarC.c().clone();
        if (cVarClone.b() > j) {
            c.c cVar = new c.c();
            cVar.a_(cVarClone, j);
            cVarClone.y();
            cVarClone = cVar;
        }
        return af.a(this.g.a(), cVarClone.b(), cVarClone);
    }

    @Nullable
    public af h() {
        return this.g;
    }

    public a i() {
        return new a(this);
    }

    public boolean j() {
        switch (this.f1011c) {
            case TTAdConstant.DEFAULT_LIVE_SHOW_TIME_MAX /* 300 */:
            case 301:
            case 302:
            case 303:
            case 307:
            case 308:
                return true;
            case HttpConstant.SC_NOT_MODIFIED /* 304 */:
            case 305:
            case 306:
            default:
                return false;
        }
    }

    @Nullable
    public ae k() {
        return this.h;
    }

    @Nullable
    public ae l() {
        return this.i;
    }

    @Nullable
    public ae m() {
        return this.j;
    }

    public List<h> n() {
        String str;
        if (this.f1011c == 401) {
            str = "WWW-Authenticate";
        } else {
            if (this.f1011c != 407) {
                return Collections.emptyList();
            }
            str = "Proxy-Authenticate";
        }
        return b.a.d.e.a(g(), str);
    }

    public d o() {
        d dVar = this.m;
        if (dVar != null) {
            return dVar;
        }
        d dVarA = d.a(this.f);
        this.m = dVarA;
        return dVarA;
    }

    public long p() {
        return this.k;
    }

    public long q() {
        return this.l;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.g == null) {
            throw new IllegalStateException("response is not eligible for a body and must not be closed");
        }
        this.g.close();
    }

    public String toString() {
        return "Response{protocol=" + this.f1010b + ", code=" + this.f1011c + ", message=" + this.d + ", url=" + this.f1009a.a() + '}';
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        ac f1012a;

        /* renamed from: b, reason: collision with root package name */
        aa f1013b;

        /* renamed from: c, reason: collision with root package name */
        int f1014c;
        String d;

        @Nullable
        t e;
        u.a f;
        af g;
        ae h;
        ae i;
        ae j;
        long k;
        long l;

        public a() {
            this.f1014c = -1;
            this.f = new u.a();
        }

        a(ae aeVar) {
            this.f1014c = -1;
            this.f1012a = aeVar.f1009a;
            this.f1013b = aeVar.f1010b;
            this.f1014c = aeVar.f1011c;
            this.d = aeVar.d;
            this.e = aeVar.e;
            this.f = aeVar.f.d();
            this.g = aeVar.g;
            this.h = aeVar.h;
            this.i = aeVar.i;
            this.j = aeVar.j;
            this.k = aeVar.k;
            this.l = aeVar.l;
        }

        public a a(ac acVar) {
            this.f1012a = acVar;
            return this;
        }

        public a a(aa aaVar) {
            this.f1013b = aaVar;
            return this;
        }

        public a a(int i) {
            this.f1014c = i;
            return this;
        }

        public a a(String str) {
            this.d = str;
            return this;
        }

        public a a(@Nullable t tVar) {
            this.e = tVar;
            return this;
        }

        public a a(String str, String str2) {
            this.f.c(str, str2);
            return this;
        }

        public a b(String str, String str2) {
            this.f.a(str, str2);
            return this;
        }

        public a b(String str) {
            this.f.c(str);
            return this;
        }

        public a a(u uVar) {
            this.f = uVar.d();
            return this;
        }

        public a a(@Nullable af afVar) {
            this.g = afVar;
            return this;
        }

        public a a(@Nullable ae aeVar) {
            if (aeVar != null) {
                a("networkResponse", aeVar);
            }
            this.h = aeVar;
            return this;
        }

        public a b(@Nullable ae aeVar) {
            if (aeVar != null) {
                a("cacheResponse", aeVar);
            }
            this.i = aeVar;
            return this;
        }

        private void a(String str, ae aeVar) {
            if (aeVar.g != null) {
                throw new IllegalArgumentException(str + ".body != null");
            }
            if (aeVar.h != null) {
                throw new IllegalArgumentException(str + ".networkResponse != null");
            }
            if (aeVar.i != null) {
                throw new IllegalArgumentException(str + ".cacheResponse != null");
            }
            if (aeVar.j == null) {
                return;
            }
            throw new IllegalArgumentException(str + ".priorResponse != null");
        }

        public a c(@Nullable ae aeVar) {
            if (aeVar != null) {
                d(aeVar);
            }
            this.j = aeVar;
            return this;
        }

        private void d(ae aeVar) {
            if (aeVar.g != null) {
                throw new IllegalArgumentException("priorResponse.body != null");
            }
        }

        public a a(long j) {
            this.k = j;
            return this;
        }

        public a b(long j) {
            this.l = j;
            return this;
        }

        public ae a() {
            if (this.f1012a == null) {
                throw new IllegalStateException("request == null");
            }
            if (this.f1013b == null) {
                throw new IllegalStateException("protocol == null");
            }
            if (this.f1014c < 0) {
                throw new IllegalStateException("code < 0: " + this.f1014c);
            }
            if (this.d == null) {
                throw new IllegalStateException("message == null");
            }
            return new ae(this);
        }
    }
}
