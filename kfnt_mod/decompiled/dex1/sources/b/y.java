package b;

import com.facebook.stetho.dumpapp.Framer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class y extends ad {

    /* renamed from: a, reason: collision with root package name */
    public static final x f1292a = x.a("multipart/mixed");

    /* renamed from: b, reason: collision with root package name */
    public static final x f1293b = x.a("multipart/alternative");

    /* renamed from: c, reason: collision with root package name */
    public static final x f1294c = x.a("multipart/digest");
    public static final x d = x.a("multipart/parallel");
    public static final x e = x.a("multipart/form-data");
    private static final byte[] f = {58, 32};
    private static final byte[] g = {13, 10};
    private static final byte[] h = {Framer.STDIN_FRAME_PREFIX, Framer.STDIN_FRAME_PREFIX};
    private final c.f i;
    private final x j;
    private final x k;
    private final List<b> l;
    private long m = -1;

    y(c.f fVar, x xVar, List<b> list) {
        this.i = fVar;
        this.j = xVar;
        this.k = x.a(xVar + "; boundary=" + fVar.a());
        this.l = b.a.c.a(list);
    }

    public x a() {
        return this.j;
    }

    public String d() {
        return this.i.a();
    }

    public int e() {
        return this.l.size();
    }

    public List<b> f() {
        return this.l;
    }

    public b a(int i) {
        return this.l.get(i);
    }

    @Override // b.ad
    public x b() {
        return this.k;
    }

    @Override // b.ad
    public long c() throws IOException {
        long j = this.m;
        if (j != -1) {
            return j;
        }
        long jA = a((c.d) null, true);
        this.m = jA;
        return jA;
    }

    @Override // b.ad
    public void a(c.d dVar) throws IOException {
        a(dVar, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private long a(@Nullable c.d dVar, boolean z) throws IOException {
        c.c cVar;
        if (z) {
            dVar = new c.c();
            cVar = dVar;
        } else {
            cVar = 0;
        }
        int size = this.l.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            b bVar = this.l.get(i);
            u uVar = bVar.f1298a;
            ad adVar = bVar.f1299b;
            dVar.d(h);
            dVar.g(this.i);
            dVar.d(g);
            if (uVar != null) {
                int iA = uVar.a();
                for (int i2 = 0; i2 < iA; i2++) {
                    dVar.b(uVar.a(i2)).d(f).b(uVar.b(i2)).d(g);
                }
            }
            x xVarB = adVar.b();
            if (xVarB != null) {
                dVar.b("Content-Type: ").b(xVarB.toString()).d(g);
            }
            long jC = adVar.c();
            if (jC != -1) {
                dVar.b("Content-Length: ").o(jC).d(g);
            } else if (z) {
                cVar.y();
                return -1L;
            }
            dVar.d(g);
            if (z) {
                j += jC;
            } else {
                adVar.a(dVar);
            }
            dVar.d(g);
        }
        dVar.d(h);
        dVar.g(this.i);
        dVar.d(h);
        dVar.d(g);
        if (!z) {
            return j;
        }
        long jB = j + cVar.b();
        cVar.y();
        return jB;
    }

    static StringBuilder a(StringBuilder sb, String str) {
        sb.append('\"');
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\n') {
                sb.append("%0A");
            } else if (cCharAt == '\r') {
                sb.append("%0D");
            } else if (cCharAt == '\"') {
                sb.append("%22");
            } else {
                sb.append(cCharAt);
            }
        }
        sb.append('\"');
        return sb;
    }

    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        @Nullable
        final u f1298a;

        /* renamed from: b, reason: collision with root package name */
        final ad f1299b;

        public static b a(ad adVar) {
            return a((u) null, adVar);
        }

        public static b a(@Nullable u uVar, ad adVar) {
            if (adVar == null) {
                throw new NullPointerException("body == null");
            }
            if (uVar != null && uVar.a("Content-Type") != null) {
                throw new IllegalArgumentException("Unexpected header: Content-Type");
            }
            if (uVar != null && uVar.a("Content-Length") != null) {
                throw new IllegalArgumentException("Unexpected header: Content-Length");
            }
            return new b(uVar, adVar);
        }

        public static b a(String str, String str2) {
            return a(str, null, ad.a((x) null, str2));
        }

        public static b a(String str, @Nullable String str2, ad adVar) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            StringBuilder sb = new StringBuilder("form-data; name=");
            y.a(sb, str);
            if (str2 != null) {
                sb.append("; filename=");
                y.a(sb, str2);
            }
            return a(u.a("Content-Disposition", sb.toString()), adVar);
        }

        private b(@Nullable u uVar, ad adVar) {
            this.f1298a = uVar;
            this.f1299b = adVar;
        }

        @Nullable
        public u a() {
            return this.f1298a;
        }

        public ad b() {
            return this.f1299b;
        }
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private final c.f f1295a;

        /* renamed from: b, reason: collision with root package name */
        private x f1296b;

        /* renamed from: c, reason: collision with root package name */
        private final List<b> f1297c;

        public a() {
            this(UUID.randomUUID().toString());
        }

        public a(String str) {
            this.f1296b = y.f1292a;
            this.f1297c = new ArrayList();
            this.f1295a = c.f.a(str);
        }

        public a a(x xVar) {
            if (xVar == null) {
                throw new NullPointerException("type == null");
            }
            if (!xVar.a().equals("multipart")) {
                throw new IllegalArgumentException("multipart != " + xVar);
            }
            this.f1296b = xVar;
            return this;
        }

        public a a(ad adVar) {
            return a(b.a(adVar));
        }

        public a a(@Nullable u uVar, ad adVar) {
            return a(b.a(uVar, adVar));
        }

        public a a(String str, String str2) {
            return a(b.a(str, str2));
        }

        public a a(String str, @Nullable String str2, ad adVar) {
            return a(b.a(str, str2, adVar));
        }

        public a a(b bVar) {
            if (bVar == null) {
                throw new NullPointerException("part == null");
            }
            this.f1297c.add(bVar);
            return this;
        }

        public y a() {
            if (this.f1297c.isEmpty()) {
                throw new IllegalStateException("Multipart body must have at least one part.");
            }
            return new y(this.f1295a, this.f1296b, this.f1297c);
        }
    }
}
