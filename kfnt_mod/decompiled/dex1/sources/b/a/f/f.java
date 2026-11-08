package b.a.f;

import anet.channel.util.HttpConstant;
import b.aa;
import b.ac;
import b.ae;
import b.af;
import b.u;
import b.w;
import b.z;
import c.p;
import c.x;
import c.y;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class f implements b.a.d.c {

    /* renamed from: c, reason: collision with root package name */
    private static final c.f f865c = c.f.a("connection");
    private static final c.f d = c.f.a(com.alipay.sdk.cons.c.f);
    private static final c.f e = c.f.a("keep-alive");
    private static final c.f f = c.f.a("proxy-connection");
    private static final c.f g = c.f.a("transfer-encoding");
    private static final c.f h = c.f.a("te");
    private static final c.f i = c.f.a("encoding");
    private static final c.f j = c.f.a("upgrade");
    private static final List<c.f> k = b.a.c.a(f865c, d, e, f, h, g, i, j, c.f852c, c.d, c.e, c.f);
    private static final List<c.f> l = b.a.c.a(f865c, d, e, f, h, g, i, j);

    /* renamed from: b, reason: collision with root package name */
    final b.a.c.g f866b;
    private final z m;
    private final w.a n;
    private final g o;
    private i p;

    public f(z zVar, w.a aVar, b.a.c.g gVar, g gVar2) {
        this.m = zVar;
        this.n = aVar;
        this.f866b = gVar;
        this.o = gVar2;
    }

    @Override // b.a.d.c
    public x a(ac acVar, long j2) {
        return this.p.k();
    }

    @Override // b.a.d.c
    public void a(ac acVar) throws IOException {
        if (this.p != null) {
            return;
        }
        this.p = this.o.a(b(acVar), acVar.d() != null);
        this.p.h().a(this.n.e(), TimeUnit.MILLISECONDS);
        this.p.i().a(this.n.f(), TimeUnit.MILLISECONDS);
    }

    @Override // b.a.d.c
    public void a() throws IOException {
        this.o.f();
    }

    @Override // b.a.d.c
    public void b() throws IOException {
        this.p.k().close();
    }

    @Override // b.a.d.c
    public ae.a a(boolean z) throws NumberFormatException, IOException {
        ae.a aVarA = a(this.p.f());
        if (z && b.a.a.f750a.a(aVarA) == 100) {
            return null;
        }
        return aVarA;
    }

    public static List<c> b(ac acVar) {
        u uVarC = acVar.c();
        ArrayList arrayList = new ArrayList(uVarC.a() + 4);
        arrayList.add(new c(c.f852c, acVar.b()));
        arrayList.add(new c(c.d, b.a.d.i.a(acVar.a())));
        String strA = acVar.a(HttpConstant.HOST);
        if (strA != null) {
            arrayList.add(new c(c.f, strA));
        }
        arrayList.add(new c(c.e, acVar.a().c()));
        int iA = uVarC.a();
        for (int i2 = 0; i2 < iA; i2++) {
            c.f fVarA = c.f.a(uVarC.a(i2).toLowerCase(Locale.US));
            if (!k.contains(fVarA)) {
                arrayList.add(new c(fVarA, uVarC.b(i2)));
            }
        }
        return arrayList;
    }

    public static ae.a a(List<c> list) throws NumberFormatException, IOException {
        u.a aVar = new u.a();
        int size = list.size();
        u.a aVar2 = aVar;
        b.a.d.k kVarA = null;
        for (int i2 = 0; i2 < size; i2++) {
            c cVar = list.get(i2);
            if (cVar == null) {
                if (kVarA != null && kVarA.e == 100) {
                    aVar2 = new u.a();
                    kVarA = null;
                }
            } else {
                c.f fVar = cVar.g;
                String strA = cVar.h.a();
                if (fVar.equals(c.f851b)) {
                    kVarA = b.a.d.k.a("HTTP/1.1 " + strA);
                } else if (!l.contains(fVar)) {
                    b.a.a.f750a.a(aVar2, fVar.a(), strA);
                }
            }
        }
        if (kVarA == null) {
            throw new ProtocolException("Expected ':status' header not present");
        }
        return new ae.a().a(aa.HTTP_2).a(kVarA.e).a(kVarA.f).a(aVar2.a());
    }

    @Override // b.a.d.c
    public af a(ae aeVar) throws IOException {
        this.f866b.f812c.f(this.f866b.f811b);
        return new b.a.d.h(aeVar.b("Content-Type"), b.a.d.e.a(aeVar), p.a(new a(this.p.j())));
    }

    @Override // b.a.d.c
    public void c() {
        if (this.p != null) {
            this.p.b(b.CANCEL);
        }
    }

    class a extends c.i {

        /* renamed from: a, reason: collision with root package name */
        boolean f867a;

        /* renamed from: b, reason: collision with root package name */
        long f868b;

        a(y yVar) {
            super(yVar);
            this.f867a = false;
            this.f868b = 0L;
        }

        @Override // c.i, c.y
        public long a(c.c cVar, long j) throws IOException {
            try {
                long jA = b().a(cVar, j);
                if (jA > 0) {
                    this.f868b += jA;
                }
                return jA;
            } catch (IOException e) {
                a(e);
                throw e;
            }
        }

        @Override // c.i, c.y, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            super.close();
            a(null);
        }

        private void a(IOException iOException) {
            if (this.f867a) {
                return;
            }
            this.f867a = true;
            f.this.f866b.a(false, f.this, this.f868b, iOException);
        }
    }
}
