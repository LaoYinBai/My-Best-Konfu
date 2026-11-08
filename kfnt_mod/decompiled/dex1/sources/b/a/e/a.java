package b.a.e;

import android.support.v4.media.session.PlaybackStateCompat;
import b.a.c.g;
import b.a.d.h;
import b.a.d.i;
import b.a.d.k;
import b.ac;
import b.ae;
import b.af;
import b.u;
import b.v;
import b.z;
import c.j;
import c.p;
import c.x;
import c.y;
import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class a implements b.a.d.c {
    private static final int g = 0;
    private static final int h = 1;
    private static final int i = 2;
    private static final int j = 3;
    private static final int k = 4;
    private static final int l = 5;
    private static final int m = 6;
    private static final int n = 262144;

    /* renamed from: b, reason: collision with root package name */
    final z f836b;

    /* renamed from: c, reason: collision with root package name */
    final g f837c;
    final c.e d;
    final c.d e;
    int f = 0;
    private long o = PlaybackStateCompat.ACTION_SET_REPEAT_MODE;

    public a(z zVar, g gVar, c.e eVar, c.d dVar) {
        this.f836b = zVar;
        this.f837c = gVar;
        this.d = eVar;
        this.e = dVar;
    }

    @Override // b.a.d.c
    public x a(ac acVar, long j2) {
        if ("chunked".equalsIgnoreCase(acVar.a("Transfer-Encoding"))) {
            return f();
        }
        if (j2 != -1) {
            return a(j2);
        }
        throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
    }

    @Override // b.a.d.c
    public void c() throws IOException {
        b.a.c.c cVarC = this.f837c.c();
        if (cVarC != null) {
            cVarC.e();
        }
    }

    @Override // b.a.d.c
    public void a(ac acVar) throws IOException {
        a(acVar.c(), i.a(acVar, this.f837c.c().a().b().type()));
    }

    @Override // b.a.d.c
    public af a(ae aeVar) throws IOException {
        this.f837c.f812c.f(this.f837c.f811b);
        String strB = aeVar.b("Content-Type");
        if (!b.a.d.e.d(aeVar)) {
            return new h(strB, 0L, p.a(b(0L)));
        }
        if ("chunked".equalsIgnoreCase(aeVar.b("Transfer-Encoding"))) {
            return new h(strB, -1L, p.a(a(aeVar.a().a())));
        }
        long jA = b.a.d.e.a(aeVar);
        if (jA != -1) {
            return new h(strB, jA, p.a(b(jA)));
        }
        return new h(strB, -1L, p.a(g()));
    }

    public boolean d() {
        return this.f == 6;
    }

    @Override // b.a.d.c
    public void a() throws IOException {
        this.e.flush();
    }

    @Override // b.a.d.c
    public void b() throws IOException {
        this.e.flush();
    }

    public void a(u uVar, String str) throws IOException {
        if (this.f != 0) {
            throw new IllegalStateException("state: " + this.f);
        }
        this.e.b(str).b("\r\n");
        int iA = uVar.a();
        for (int i2 = 0; i2 < iA; i2++) {
            this.e.b(uVar.a(i2)).b(": ").b(uVar.b(i2)).b("\r\n");
        }
        this.e.b("\r\n");
        this.f = 1;
    }

    @Override // b.a.d.c
    public ae.a a(boolean z) throws NumberFormatException, IOException {
        if (this.f != 1 && this.f != 3) {
            throw new IllegalStateException("state: " + this.f);
        }
        try {
            k kVarA = k.a(h());
            ae.a aVarA = new ae.a().a(kVarA.d).a(kVarA.e).a(kVarA.f).a(e());
            if (z && kVarA.e == 100) {
                return null;
            }
            if (kVarA.e == 100) {
                this.f = 3;
                return aVarA;
            }
            this.f = 4;
            return aVarA;
        } catch (EOFException e2) {
            IOException iOException = new IOException("unexpected end of stream on " + this.f837c);
            iOException.initCause(e2);
            throw iOException;
        }
    }

    private String h() throws IOException {
        String strF = this.d.f(this.o);
        this.o -= strF.length();
        return strF;
    }

    public u e() throws IOException {
        u.a aVar = new u.a();
        while (true) {
            String strH = h();
            if (strH.length() != 0) {
                b.a.a.f750a.a(aVar, strH);
            } else {
                return aVar.a();
            }
        }
    }

    public x f() {
        if (this.f != 1) {
            throw new IllegalStateException("state: " + this.f);
        }
        this.f = 2;
        return new b();
    }

    public x a(long j2) {
        if (this.f != 1) {
            throw new IllegalStateException("state: " + this.f);
        }
        this.f = 2;
        return new d(j2);
    }

    public y b(long j2) throws IOException {
        if (this.f != 4) {
            throw new IllegalStateException("state: " + this.f);
        }
        this.f = 5;
        return new e(j2);
    }

    public y a(v vVar) throws IOException {
        if (this.f != 4) {
            throw new IllegalStateException("state: " + this.f);
        }
        this.f = 5;
        return new c(vVar);
    }

    public y g() throws IOException {
        if (this.f != 4) {
            throw new IllegalStateException("state: " + this.f);
        }
        if (this.f837c == null) {
            throw new IllegalStateException("streamAllocation == null");
        }
        this.f = 5;
        this.f837c.e();
        return new f();
    }

    void a(j jVar) {
        c.z zVarA = jVar.a();
        jVar.a(c.z.f2188c);
        zVarA.f();
        zVarA.d_();
    }

    private final class d implements x {

        /* renamed from: b, reason: collision with root package name */
        private final j f845b;

        /* renamed from: c, reason: collision with root package name */
        private boolean f846c;
        private long d;

        d(long j) {
            this.f845b = new j(a.this.e.a());
            this.d = j;
        }

        @Override // c.x
        public c.z a() {
            return this.f845b;
        }

        @Override // c.x
        public void a_(c.c cVar, long j) throws IOException {
            if (this.f846c) {
                throw new IllegalStateException("closed");
            }
            b.a.c.a(cVar.b(), 0L, j);
            if (j > this.d) {
                throw new ProtocolException("expected " + this.d + " bytes but received " + j);
            }
            a.this.e.a_(cVar, j);
            this.d -= j;
        }

        @Override // c.x, java.io.Flushable
        public void flush() throws IOException {
            if (this.f846c) {
                return;
            }
            a.this.e.flush();
        }

        @Override // c.x, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.f846c) {
                return;
            }
            this.f846c = true;
            if (this.d > 0) {
                throw new ProtocolException("unexpected end of stream");
            }
            a.this.a(this.f845b);
            a.this.f = 3;
        }
    }

    private final class b implements x {

        /* renamed from: b, reason: collision with root package name */
        private final j f842b;

        /* renamed from: c, reason: collision with root package name */
        private boolean f843c;

        b() {
            this.f842b = new j(a.this.e.a());
        }

        @Override // c.x
        public c.z a() {
            return this.f842b;
        }

        @Override // c.x
        public void a_(c.c cVar, long j) throws IOException {
            if (this.f843c) {
                throw new IllegalStateException("closed");
            }
            if (j == 0) {
                return;
            }
            a.this.e.n(j);
            a.this.e.b("\r\n");
            a.this.e.a_(cVar, j);
            a.this.e.b("\r\n");
        }

        @Override // c.x, java.io.Flushable
        public synchronized void flush() throws IOException {
            if (this.f843c) {
                return;
            }
            a.this.e.flush();
        }

        @Override // c.x, java.io.Closeable, java.lang.AutoCloseable
        public synchronized void close() throws IOException {
            if (this.f843c) {
                return;
            }
            this.f843c = true;
            a.this.e.b("0\r\n\r\n");
            a.this.a(this.f842b);
            a.this.f = 3;
        }
    }

    /* renamed from: b.a.e.a$a, reason: collision with other inner class name */
    private abstract class AbstractC0020a implements y {

        /* renamed from: a, reason: collision with root package name */
        protected final j f838a;

        /* renamed from: b, reason: collision with root package name */
        protected boolean f839b;

        /* renamed from: c, reason: collision with root package name */
        protected long f840c;

        private AbstractC0020a() {
            this.f838a = new j(a.this.d.a());
            this.f840c = 0L;
        }

        @Override // c.y
        public c.z a() {
            return this.f838a;
        }

        @Override // c.y
        public long a(c.c cVar, long j) throws IOException {
            try {
                long jA = a.this.d.a(cVar, j);
                if (jA > 0) {
                    this.f840c += jA;
                }
                return jA;
            } catch (IOException e) {
                a(false, e);
                throw e;
            }
        }

        protected final void a(boolean z, IOException iOException) throws IOException {
            if (a.this.f == 6) {
                return;
            }
            if (a.this.f != 5) {
                throw new IllegalStateException("state: " + a.this.f);
            }
            a.this.a(this.f838a);
            a.this.f = 6;
            if (a.this.f837c != null) {
                a.this.f837c.a(!z, a.this, this.f840c, iOException);
            }
        }
    }

    private class e extends AbstractC0020a {
        private long f;

        e(long j) throws IOException {
            super();
            this.f = j;
            if (this.f == 0) {
                a(true, (IOException) null);
            }
        }

        @Override // b.a.e.a.AbstractC0020a, c.y
        public long a(c.c cVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (this.f839b) {
                throw new IllegalStateException("closed");
            }
            if (this.f == 0) {
                return -1L;
            }
            long jA = super.a(cVar, Math.min(this.f, j));
            if (jA == -1) {
                ProtocolException protocolException = new ProtocolException("unexpected end of stream");
                a(false, (IOException) protocolException);
                throw protocolException;
            }
            this.f -= jA;
            if (this.f == 0) {
                a(true, (IOException) null);
            }
            return jA;
        }

        @Override // c.y, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.f839b) {
                return;
            }
            if (this.f != 0 && !b.a.c.a(this, 100, TimeUnit.MILLISECONDS)) {
                a(false, (IOException) null);
            }
            this.f839b = true;
        }
    }

    private class c extends AbstractC0020a {
        private static final long f = -1;
        private final v g;
        private long h;
        private boolean i;

        c(v vVar) {
            super();
            this.h = -1L;
            this.i = true;
            this.g = vVar;
        }

        @Override // b.a.e.a.AbstractC0020a, c.y
        public long a(c.c cVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (this.f839b) {
                throw new IllegalStateException("closed");
            }
            if (!this.i) {
                return -1L;
            }
            if (this.h == 0 || this.h == -1) {
                b();
                if (!this.i) {
                    return -1L;
                }
            }
            long jA = super.a(cVar, Math.min(j, this.h));
            if (jA == -1) {
                ProtocolException protocolException = new ProtocolException("unexpected end of stream");
                a(false, (IOException) protocolException);
                throw protocolException;
            }
            this.h -= jA;
            return jA;
        }

        private void b() throws IOException {
            if (this.h != -1) {
                a.this.d.v();
            }
            try {
                this.h = a.this.d.r();
                String strTrim = a.this.d.v().trim();
                if (this.h < 0 || !(strTrim.isEmpty() || strTrim.startsWith(com.alipay.sdk.util.f.f2657b))) {
                    throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.h + strTrim + "\"");
                }
                if (this.h == 0) {
                    this.i = false;
                    b.a.d.e.a(a.this.f836b.g(), this.g, a.this.e());
                    a(true, (IOException) null);
                }
            } catch (NumberFormatException e) {
                throw new ProtocolException(e.getMessage());
            }
        }

        @Override // c.y, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.f839b) {
                return;
            }
            if (this.i && !b.a.c.a(this, 100, TimeUnit.MILLISECONDS)) {
                a(false, (IOException) null);
            }
            this.f839b = true;
        }
    }

    private class f extends AbstractC0020a {
        private boolean f;

        f() {
            super();
        }

        @Override // b.a.e.a.AbstractC0020a, c.y
        public long a(c.c cVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (this.f839b) {
                throw new IllegalStateException("closed");
            }
            if (this.f) {
                return -1L;
            }
            long jA = super.a(cVar, j);
            if (jA != -1) {
                return jA;
            }
            this.f = true;
            a(true, (IOException) null);
            return -1L;
        }

        @Override // c.y, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.f839b) {
                return;
            }
            if (!this.f) {
                a(false, (IOException) null);
            }
            this.f839b = true;
        }
    }
}
