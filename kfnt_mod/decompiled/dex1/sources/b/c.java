package b;

import b.a.a.d;
import b.ac;
import b.ae;
import b.u;
import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import javax.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class c implements Closeable, Flushable {
    private static final int e = 201105;
    private static final int f = 0;
    private static final int g = 1;
    private static final int h = 2;

    /* renamed from: a, reason: collision with root package name */
    final b.a.a.f f1203a;

    /* renamed from: b, reason: collision with root package name */
    final b.a.a.d f1204b;

    /* renamed from: c, reason: collision with root package name */
    int f1205c;
    int d;
    private int i;
    private int j;
    private int k;

    public c(File file, long j) {
        this(file, j, b.a.g.a.f927a);
    }

    c(File file, long j, b.a.g.a aVar) {
        this.f1203a = new b.a.a.f() { // from class: b.c.1
            @Override // b.a.a.f
            public ae a(ac acVar) throws IOException {
                return c.this.a(acVar);
            }

            @Override // b.a.a.f
            public b.a.a.b a(ae aeVar) throws IOException {
                return c.this.a(aeVar);
            }

            @Override // b.a.a.f
            public void b(ac acVar) throws IOException {
                c.this.b(acVar);
            }

            @Override // b.a.a.f
            public void a(ae aeVar, ae aeVar2) {
                c.this.a(aeVar, aeVar2);
            }

            @Override // b.a.a.f
            public void a() {
                c.this.k();
            }

            @Override // b.a.a.f
            public void a(b.a.a.c cVar) {
                c.this.a(cVar);
            }
        };
        this.f1204b = b.a.a.d.a(aVar, file, e, 2, j);
    }

    public static String a(v vVar) {
        return c.f.a(vVar.toString()).c().h();
    }

    @Nullable
    ae a(ac acVar) throws IOException {
        try {
            d.c cVarA = this.f1204b.a(a(acVar.a()));
            if (cVarA == null) {
                return null;
            }
            try {
                C0035c c0035c = new C0035c(cVarA.a(0));
                ae aeVarA = c0035c.a(cVarA);
                if (c0035c.a(acVar, aeVarA)) {
                    return aeVarA;
                }
                b.a.c.a(aeVarA.h());
                return null;
            } catch (IOException unused) {
                b.a.c.a(cVarA);
                return null;
            }
        } catch (IOException unused2) {
            return null;
        }
    }

    @Nullable
    b.a.a.b a(ae aeVar) {
        d.a aVarB;
        String strB = aeVar.a().b();
        if (b.a.d.f.a(aeVar.a().b())) {
            try {
                b(aeVar.a());
            } catch (IOException unused) {
            }
            return null;
        }
        if (!strB.equals("GET") || b.a.d.e.b(aeVar)) {
            return null;
        }
        C0035c c0035c = new C0035c(aeVar);
        try {
            aVarB = this.f1204b.b(a(aeVar.a().a()));
            if (aVarB == null) {
                return null;
            }
            try {
                c0035c.a(aVarB);
                return new a(aVarB);
            } catch (IOException unused2) {
                a(aVarB);
                return null;
            }
        } catch (IOException unused3) {
            aVarB = null;
        }
    }

    void b(ac acVar) throws IOException {
        this.f1204b.c(a(acVar.a()));
    }

    void a(ae aeVar, ae aeVar2) {
        d.a aVarB;
        C0035c c0035c = new C0035c(aeVar2);
        try {
            aVarB = ((b) aeVar.h()).f1217a.b();
            if (aVarB != null) {
                try {
                    c0035c.a(aVarB);
                    aVarB.b();
                } catch (IOException unused) {
                    a(aVarB);
                }
            }
        } catch (IOException unused2) {
            aVarB = null;
        }
    }

    private void a(@Nullable d.a aVar) {
        if (aVar != null) {
            try {
                aVar.c();
            } catch (IOException unused) {
            }
        }
    }

    public void a() throws IOException {
        this.f1204b.a();
    }

    public void b() throws IOException {
        this.f1204b.i();
    }

    public void c() throws IOException {
        this.f1204b.j();
    }

    public Iterator<String> d() throws IOException {
        return new Iterator<String>() { // from class: b.c.2

            /* renamed from: a, reason: collision with root package name */
            final Iterator<d.c> f1207a;

            /* renamed from: b, reason: collision with root package name */
            @Nullable
            String f1208b;

            /* renamed from: c, reason: collision with root package name */
            boolean f1209c;

            {
                this.f1207a = c.this.f1204b.k();
            }

            @Override // java.util.Iterator
            public boolean hasNext() throws IOException {
                if (this.f1208b != null) {
                    return true;
                }
                this.f1209c = false;
                while (this.f1207a.hasNext()) {
                    d.c next = this.f1207a.next();
                    try {
                        this.f1208b = c.p.a(next.a(0)).v();
                        return true;
                    } catch (IOException unused) {
                    } finally {
                        next.close();
                    }
                }
                return false;
            }

            @Override // java.util.Iterator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public String next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                String str = this.f1208b;
                this.f1208b = null;
                this.f1209c = true;
                return str;
            }

            @Override // java.util.Iterator
            public void remove() {
                if (!this.f1209c) {
                    throw new IllegalStateException("remove() before next()");
                }
                this.f1207a.remove();
            }
        };
    }

    public synchronized int e() {
        return this.d;
    }

    public synchronized int f() {
        return this.f1205c;
    }

    public long g() throws IOException {
        return this.f1204b.e();
    }

    public long h() {
        return this.f1204b.d();
    }

    @Override // java.io.Flushable
    public void flush() throws IOException {
        this.f1204b.flush();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f1204b.close();
    }

    public File i() {
        return this.f1204b.c();
    }

    public boolean j() {
        return this.f1204b.g();
    }

    synchronized void a(b.a.a.c cVar) {
        this.k++;
        if (cVar.f755a != null) {
            this.i++;
        } else if (cVar.f756b != null) {
            this.j++;
        }
    }

    synchronized void k() {
        this.j++;
    }

    public synchronized int l() {
        return this.i;
    }

    public synchronized int m() {
        return this.j;
    }

    public synchronized int n() {
        return this.k;
    }

    private final class a implements b.a.a.b {

        /* renamed from: a, reason: collision with root package name */
        boolean f1210a;

        /* renamed from: c, reason: collision with root package name */
        private final d.a f1212c;
        private c.x d;
        private c.x e;

        a(final d.a aVar) {
            this.f1212c = aVar;
            this.d = aVar.b(1);
            this.e = new c.h(this.d) { // from class: b.c.a.1
                @Override // c.h, c.x, java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    synchronized (c.this) {
                        if (a.this.f1210a) {
                            return;
                        }
                        a.this.f1210a = true;
                        c.this.f1205c++;
                        super.close();
                        aVar.b();
                    }
                }
            };
        }

        @Override // b.a.a.b
        public void a() throws IOException {
            synchronized (c.this) {
                if (this.f1210a) {
                    return;
                }
                this.f1210a = true;
                c.this.d++;
                b.a.c.a(this.d);
                try {
                    this.f1212c.c();
                } catch (IOException unused) {
                }
            }
        }

        @Override // b.a.a.b
        public c.x b() {
            return this.e;
        }
    }

    /* renamed from: b.c$c, reason: collision with other inner class name */
    private static final class C0035c {

        /* renamed from: a, reason: collision with root package name */
        private static final String f1222a = b.a.h.f.c().d() + "-Sent-Millis";

        /* renamed from: b, reason: collision with root package name */
        private static final String f1223b = b.a.h.f.c().d() + "-Received-Millis";

        /* renamed from: c, reason: collision with root package name */
        private final String f1224c;
        private final u d;
        private final String e;
        private final aa f;
        private final int g;
        private final String h;
        private final u i;

        @Nullable
        private final t j;
        private final long k;
        private final long l;

        C0035c(c.y yVar) throws IOException {
            ah ahVarA;
            try {
                c.e eVarA = c.p.a(yVar);
                this.f1224c = eVarA.v();
                this.e = eVarA.v();
                u.a aVar = new u.a();
                int iA = c.a(eVarA);
                for (int i = 0; i < iA; i++) {
                    aVar.a(eVarA.v());
                }
                this.d = aVar.a();
                b.a.d.k kVarA = b.a.d.k.a(eVarA.v());
                this.f = kVarA.d;
                this.g = kVarA.e;
                this.h = kVarA.f;
                u.a aVar2 = new u.a();
                int iA2 = c.a(eVarA);
                for (int i2 = 0; i2 < iA2; i2++) {
                    aVar2.a(eVarA.v());
                }
                String strD = aVar2.d(f1222a);
                String strD2 = aVar2.d(f1223b);
                aVar2.c(f1222a);
                aVar2.c(f1223b);
                this.k = strD != null ? Long.parseLong(strD) : 0L;
                this.l = strD2 != null ? Long.parseLong(strD2) : 0L;
                this.i = aVar2.a();
                if (a()) {
                    String strV = eVarA.v();
                    if (strV.length() > 0) {
                        throw new IOException("expected \"\" but was \"" + strV + "\"");
                    }
                    i iVarA = i.a(eVarA.v());
                    List<Certificate> listA = a(eVarA);
                    List<Certificate> listA2 = a(eVarA);
                    if (!eVarA.g()) {
                        ahVarA = ah.a(eVarA.v());
                    } else {
                        ahVarA = ah.SSL_3_0;
                    }
                    this.j = t.a(ahVarA, iVarA, listA, listA2);
                } else {
                    this.j = null;
                }
            } finally {
                yVar.close();
            }
        }

        C0035c(ae aeVar) {
            this.f1224c = aeVar.a().a().toString();
            this.d = b.a.d.e.c(aeVar);
            this.e = aeVar.a().b();
            this.f = aeVar.b();
            this.g = aeVar.c();
            this.h = aeVar.e();
            this.i = aeVar.g();
            this.j = aeVar.f();
            this.k = aeVar.p();
            this.l = aeVar.q();
        }

        public void a(d.a aVar) throws IOException {
            c.d dVarA = c.p.a(aVar.b(0));
            dVarA.b(this.f1224c).m(10);
            dVarA.b(this.e).m(10);
            dVarA.o(this.d.a()).m(10);
            int iA = this.d.a();
            for (int i = 0; i < iA; i++) {
                dVarA.b(this.d.a(i)).b(": ").b(this.d.b(i)).m(10);
            }
            dVarA.b(new b.a.d.k(this.f, this.g, this.h).toString()).m(10);
            dVarA.o(this.i.a() + 2).m(10);
            int iA2 = this.i.a();
            for (int i2 = 0; i2 < iA2; i2++) {
                dVarA.b(this.i.a(i2)).b(": ").b(this.i.b(i2)).m(10);
            }
            dVarA.b(f1222a).b(": ").o(this.k).m(10);
            dVarA.b(f1223b).b(": ").o(this.l).m(10);
            if (a()) {
                dVarA.m(10);
                dVarA.b(this.j.b().a()).m(10);
                a(dVarA, this.j.c());
                a(dVarA, this.j.e());
                dVarA.b(this.j.a().a()).m(10);
            }
            dVarA.close();
        }

        private boolean a() {
            return this.f1224c.startsWith("https://");
        }

        private List<Certificate> a(c.e eVar) throws IOException, CertificateException {
            int iA = c.a(eVar);
            if (iA == -1) {
                return Collections.emptyList();
            }
            try {
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                ArrayList arrayList = new ArrayList(iA);
                for (int i = 0; i < iA; i++) {
                    String strV = eVar.v();
                    c.c cVar = new c.c();
                    cVar.g(c.f.b(strV));
                    arrayList.add(certificateFactory.generateCertificate(cVar.h()));
                }
                return arrayList;
            } catch (CertificateException e) {
                throw new IOException(e.getMessage());
            }
        }

        private void a(c.d dVar, List<Certificate> list) throws IOException {
            try {
                dVar.o(list.size()).m(10);
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    dVar.b(c.f.a(list.get(i).getEncoded()).b()).m(10);
                }
            } catch (CertificateEncodingException e) {
                throw new IOException(e.getMessage());
            }
        }

        public boolean a(ac acVar, ae aeVar) {
            return this.f1224c.equals(acVar.a().toString()) && this.e.equals(acVar.b()) && b.a.d.e.a(aeVar, this.d, acVar);
        }

        public ae a(d.c cVar) {
            String strA = this.i.a("Content-Type");
            String strA2 = this.i.a("Content-Length");
            return new ae.a().a(new ac.a().a(this.f1224c).a(this.e, (ad) null).a(this.d).d()).a(this.f).a(this.g).a(this.h).a(this.i).a(new b(cVar, strA, strA2)).a(this.j).a(this.k).b(this.l).a();
        }
    }

    static int a(c.e eVar) throws IOException {
        try {
            long jQ = eVar.q();
            String strV = eVar.v();
            if (jQ >= 0 && jQ <= 2147483647L && strV.isEmpty()) {
                return (int) jQ;
            }
            throw new IOException("expected an int but was \"" + jQ + strV + "\"");
        } catch (NumberFormatException e2) {
            throw new IOException(e2.getMessage());
        }
    }

    private static class b extends af {

        /* renamed from: a, reason: collision with root package name */
        final d.c f1217a;

        /* renamed from: b, reason: collision with root package name */
        private final c.e f1218b;

        /* renamed from: c, reason: collision with root package name */
        @Nullable
        private final String f1219c;

        @Nullable
        private final String d;

        b(final d.c cVar, String str, String str2) {
            this.f1217a = cVar;
            this.f1219c = str;
            this.d = str2;
            this.f1218b = c.p.a(new c.i(cVar.a(1)) { // from class: b.c.b.1
                @Override // c.i, c.y, java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    cVar.close();
                    super.close();
                }
            });
        }

        @Override // b.af
        public x a() {
            if (this.f1219c != null) {
                return x.a(this.f1219c);
            }
            return null;
        }

        @Override // b.af
        public long b() {
            try {
                if (this.d != null) {
                    return Long.parseLong(this.d);
                }
                return -1L;
            } catch (NumberFormatException unused) {
                return -1L;
            }
        }

        @Override // b.af
        public c.e c() {
            return this.f1218b;
        }
    }
}
