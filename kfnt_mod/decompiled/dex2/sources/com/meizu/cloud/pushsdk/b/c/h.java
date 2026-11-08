package com.meizu.cloud.pushsdk.b.c;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class h extends j {
    public static final g a = g.a("multipart/mixed");
    public static final g b = g.a("multipart/alternative");
    public static final g c = g.a("multipart/digest");
    public static final g d = g.a("multipart/parallel");
    public static final g e = g.a("multipart/form-data");
    private static final byte[] f = {58, 32};
    private static final byte[] g = {13, 10};
    private static final byte[] h = {45, 45};
    private final com.meizu.cloud.pushsdk.b.g.e i;
    private final g j;
    private final g k;
    private final List<b> l;
    private long m = -1;

    public static final class a {
        private final com.meizu.cloud.pushsdk.b.g.e a;
        private g b;
        private final List<b> c;

        public a() {
            this(UUID.randomUUID().toString());
        }

        public a(String str) {
            this.b = h.a;
            this.c = new ArrayList();
            this.a = com.meizu.cloud.pushsdk.b.g.e.a(str);
        }

        public a a(c cVar, j jVar) {
            return a(b.a(cVar, jVar));
        }

        public a a(g gVar) {
            if (gVar == null) {
                throw new NullPointerException("type == null");
            }
            if ("multipart".equals(gVar.a())) {
                this.b = gVar;
                return this;
            }
            throw new IllegalArgumentException("multipart != " + gVar);
        }

        public a a(b bVar) {
            if (bVar == null) {
                throw new NullPointerException("part == null");
            }
            this.c.add(bVar);
            return this;
        }

        public h a() {
            if (this.c.isEmpty()) {
                throw new IllegalStateException("Multipart body must have at least one part.");
            }
            return new h(this.a, this.b, this.c);
        }
    }

    public static final class b {
        private final c a;
        private final j b;

        private b(c cVar, j jVar) {
            this.a = cVar;
            this.b = jVar;
        }

        public static b a(c cVar, j jVar) {
            if (jVar == null) {
                throw new NullPointerException("body == null");
            }
            if (cVar != null && cVar.a("Content-Type") != null) {
                throw new IllegalArgumentException("Unexpected header: Content-Type");
            }
            if (cVar == null || cVar.a("Content-Length") == null) {
                return new b(cVar, jVar);
            }
            throw new IllegalArgumentException("Unexpected header: Content-Length");
        }
    }

    h(com.meizu.cloud.pushsdk.b.g.e eVar, g gVar, List<b> list) {
        this.i = eVar;
        this.j = gVar;
        this.k = g.a(gVar + "; boundary=" + eVar.a());
        this.l = m.a(list);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private long a(com.meizu.cloud.pushsdk.b.g.c cVar, boolean z) throws IOException {
        com.meizu.cloud.pushsdk.b.g.b bVar;
        if (z) {
            cVar = new com.meizu.cloud.pushsdk.b.g.b();
            bVar = cVar;
        } else {
            bVar = 0;
        }
        int size = this.l.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            b bVar2 = this.l.get(i);
            c cVar2 = bVar2.a;
            j jVar = bVar2.b;
            cVar.c(h);
            cVar.b(this.i);
            cVar.c(g);
            if (cVar2 != null) {
                int iA = cVar2.a();
                for (int i2 = 0; i2 < iA; i2++) {
                    cVar.b(cVar2.a(i2)).c(f).b(cVar2.b(i2)).c(g);
                }
            }
            g gVarA = jVar.a();
            if (gVarA != null) {
                cVar.b("Content-Type: ").b(gVarA.toString()).c(g);
            }
            long jB = jVar.b();
            if (jB != -1) {
                cVar.b("Content-Length: ").e(jB).c(g);
            } else if (z) {
                bVar.j();
                return -1L;
            }
            cVar.c(g);
            if (z) {
                j += jB;
            } else {
                jVar.a(cVar);
            }
            cVar.c(g);
        }
        cVar.c(h);
        cVar.b(this.i);
        cVar.c(h);
        cVar.c(g);
        if (!z) {
            return j;
        }
        long jA = j + bVar.a();
        bVar.j();
        return jA;
    }

    @Override // com.meizu.cloud.pushsdk.b.c.j
    public g a() {
        return this.k;
    }

    @Override // com.meizu.cloud.pushsdk.b.c.j
    public void a(com.meizu.cloud.pushsdk.b.g.c cVar) throws IOException {
        a(cVar, false);
    }

    @Override // com.meizu.cloud.pushsdk.b.c.j
    public long b() throws IOException {
        long j = this.m;
        if (j != -1) {
            return j;
        }
        long jA = a((com.meizu.cloud.pushsdk.b.g.c) null, true);
        this.m = jA;
        return jA;
    }
}
