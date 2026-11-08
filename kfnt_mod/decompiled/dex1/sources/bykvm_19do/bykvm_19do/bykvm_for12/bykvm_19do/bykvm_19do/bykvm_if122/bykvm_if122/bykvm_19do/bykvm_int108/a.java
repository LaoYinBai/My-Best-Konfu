package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_int108;

import android.support.v4.media.session.PlaybackStateCompat;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.i;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.l;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.r;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.t;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.a0;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.b0;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12.h;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12.k;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.r;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.s;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.v;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.y;
import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class a implements bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12.c {

    /* renamed from: a, reason: collision with root package name */
    final v f1565a;

    /* renamed from: b, reason: collision with root package name */
    final bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_if122.g f1566b;

    /* renamed from: c, reason: collision with root package name */
    final bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e f1567c;
    final bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.d d;
    int e = 0;
    private long f = PlaybackStateCompat.ACTION_SET_REPEAT_MODE;

    private final class c implements r {

        /* renamed from: a, reason: collision with root package name */
        private final i f1571a;

        /* renamed from: b, reason: collision with root package name */
        private boolean f1572b;

        c() {
            this.f1571a = new i(a.this.d.b());
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.r
        public t b() {
            return this.f1571a;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.r, java.io.Closeable, java.lang.AutoCloseable
        public synchronized void close() throws IOException {
            if (this.f1572b) {
                return;
            }
            this.f1572b = true;
            a.this.d.a("0\r\n\r\n");
            a.this.a(this.f1571a);
            a.this.e = 3;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.r, java.io.Flushable
        public synchronized void flush() throws IOException {
            if (this.f1572b) {
                return;
            }
            a.this.d.flush();
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.r
        public void b(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c cVar, long j) throws IOException {
            if (this.f1572b) {
                throw new IllegalStateException("closed");
            }
            if (j == 0) {
                return;
            }
            a.this.d.e(j);
            a.this.d.a("\r\n");
            a.this.d.b(cVar, j);
            a.this.d.a("\r\n");
        }
    }

    private class d extends b {
        private final s e;
        private long f;
        private boolean g;

        d(s sVar) {
            super();
            this.f = -1L;
            this.g = true;
            this.e = sVar;
        }

        private void h() throws IOException {
            if (this.f != -1) {
                a.this.f1567c.d();
            }
            try {
                this.f = a.this.f1567c.g();
                String strTrim = a.this.f1567c.d().trim();
                if (this.f < 0 || !(strTrim.isEmpty() || strTrim.startsWith(com.alipay.sdk.util.f.f2657b))) {
                    throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.f + strTrim + "\"");
                }
                if (this.f == 0) {
                    this.g = false;
                    bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12.e.a(a.this.f1565a.f(), this.e, a.this.e());
                    a(true, (IOException) null);
                }
            } catch (NumberFormatException e) {
                throw new ProtocolException(e.getMessage());
            }
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_int108.a.b, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s
        public long a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c cVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (this.f1569b) {
                throw new IllegalStateException("closed");
            }
            if (!this.g) {
                return -1L;
            }
            long j2 = this.f;
            if (j2 == 0 || j2 == -1) {
                h();
                if (!this.g) {
                    return -1L;
                }
            }
            long jA = super.a(cVar, Math.min(j, this.f));
            if (jA != -1) {
                this.f -= jA;
                return jA;
            }
            ProtocolException protocolException = new ProtocolException("unexpected end of stream");
            a(false, (IOException) protocolException);
            throw protocolException;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.f1569b) {
                return;
            }
            if (this.g && !bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(this, 100, TimeUnit.MILLISECONDS)) {
                a(false, (IOException) null);
            }
            this.f1569b = true;
        }
    }

    private final class e implements r {

        /* renamed from: a, reason: collision with root package name */
        private final i f1574a;

        /* renamed from: b, reason: collision with root package name */
        private boolean f1575b;

        /* renamed from: c, reason: collision with root package name */
        private long f1576c;

        e(long j) {
            this.f1574a = new i(a.this.d.b());
            this.f1576c = j;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.r
        public t b() {
            return this.f1574a;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.r, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.f1575b) {
                return;
            }
            this.f1575b = true;
            if (this.f1576c > 0) {
                throw new ProtocolException("unexpected end of stream");
            }
            a.this.a(this.f1574a);
            a.this.e = 3;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.r, java.io.Flushable
        public void flush() throws IOException {
            if (this.f1575b) {
                return;
            }
            a.this.d.flush();
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.r
        public void b(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c cVar, long j) throws IOException {
            if (this.f1575b) {
                throw new IllegalStateException("closed");
            }
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(cVar.o(), 0L, j);
            if (j <= this.f1576c) {
                a.this.d.b(cVar, j);
                this.f1576c -= j;
                return;
            }
            throw new ProtocolException("expected " + this.f1576c + " bytes but received " + j);
        }
    }

    private class f extends b {
        private long e;

        f(a aVar, long j) throws IOException {
            super();
            this.e = j;
            if (this.e == 0) {
                a(true, (IOException) null);
            }
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_int108.a.b, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s
        public long a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c cVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (this.f1569b) {
                throw new IllegalStateException("closed");
            }
            long j2 = this.e;
            if (j2 == 0) {
                return -1L;
            }
            long jA = super.a(cVar, Math.min(j2, j));
            if (jA == -1) {
                ProtocolException protocolException = new ProtocolException("unexpected end of stream");
                a(false, (IOException) protocolException);
                throw protocolException;
            }
            this.e -= jA;
            if (this.e == 0) {
                a(true, (IOException) null);
            }
            return jA;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.f1569b) {
                return;
            }
            if (this.e != 0 && !bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(this, 100, TimeUnit.MILLISECONDS)) {
                a(false, (IOException) null);
            }
            this.f1569b = true;
        }
    }

    private class g extends b {
        private boolean e;

        g(a aVar) {
            super();
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_int108.a.b, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s
        public long a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c cVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (this.f1569b) {
                throw new IllegalStateException("closed");
            }
            if (this.e) {
                return -1L;
            }
            long jA = super.a(cVar, j);
            if (jA != -1) {
                return jA;
            }
            this.e = true;
            a(true, (IOException) null);
            return -1L;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.f1569b) {
                return;
            }
            if (!this.e) {
                a(false, (IOException) null);
            }
            this.f1569b = true;
        }
    }

    public a(v vVar, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_if122.g gVar, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e eVar, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.d dVar) {
        this.f1565a = vVar;
        this.f1566b = gVar;
        this.f1567c = eVar;
        this.d = dVar;
    }

    private String f() throws IOException {
        String strC = this.f1567c.c(this.f);
        this.f -= strC.length();
        return strC;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12.c
    public r a(y yVar, long j) {
        if ("chunked".equalsIgnoreCase(yVar.a("Transfer-Encoding"))) {
            return c();
        }
        if (j != -1) {
            return a(j);
        }
        throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12.c
    public void b() throws IOException {
        this.d.flush();
    }

    public r c() {
        if (this.e == 1) {
            this.e = 2;
            return new c();
        }
        throw new IllegalStateException("state: " + this.e);
    }

    public bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s d() throws IOException {
        if (this.e != 4) {
            throw new IllegalStateException("state: " + this.e);
        }
        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_if122.g gVar = this.f1566b;
        if (gVar == null) {
            throw new IllegalStateException("streamAllocation == null");
        }
        this.e = 5;
        gVar.d();
        return new g(this);
    }

    public bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.r e() throws IOException {
        r.a aVar = new r.a();
        while (true) {
            String strF = f();
            if (strF.length() == 0) {
                return aVar.a();
            }
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.a.f1509a.a(aVar, strF);
        }
    }

    public bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s b(long j) throws IOException {
        if (this.e == 4) {
            this.e = 5;
            return new f(this, j);
        }
        throw new IllegalStateException("state: " + this.e);
    }

    private abstract class b implements bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s {

        /* renamed from: a, reason: collision with root package name */
        protected final i f1568a;

        /* renamed from: b, reason: collision with root package name */
        protected boolean f1569b;

        /* renamed from: c, reason: collision with root package name */
        protected long f1570c;

        private b() {
            this.f1568a = new i(a.this.f1567c.b());
            this.f1570c = 0L;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s
        public long a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c cVar, long j) throws IOException {
            try {
                long jA = a.this.f1567c.a(cVar, j);
                if (jA > 0) {
                    this.f1570c += jA;
                }
                return jA;
            } catch (IOException e) {
                a(false, e);
                throw e;
            }
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s
        public t b() {
            return this.f1568a;
        }

        protected final void a(boolean z, IOException iOException) throws IOException {
            a aVar = a.this;
            int i = aVar.e;
            if (i == 6) {
                return;
            }
            if (i == 5) {
                aVar.a(this.f1568a);
                a aVar2 = a.this;
                aVar2.e = 6;
                bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_if122.g gVar = aVar2.f1566b;
                if (gVar != null) {
                    gVar.a(!z, aVar2, this.f1570c, iOException);
                    return;
                }
                return;
            }
            throw new IllegalStateException("state: " + a.this.e);
        }
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12.c
    public void a(y yVar) throws IOException {
        a(yVar.c(), bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12.i.a(yVar, this.f1566b.b().a().b().type()));
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12.c
    public b0 a(a0 a0Var) throws IOException {
        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_if122.g gVar = this.f1566b;
        gVar.f.e(gVar.e);
        String strB = a0Var.b("Content-Type");
        if (!bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12.e.b(a0Var)) {
            return new h(strB, 0L, l.a(b(0L)));
        }
        if ("chunked".equalsIgnoreCase(a0Var.b("Transfer-Encoding"))) {
            return new h(strB, -1L, l.a(a(a0Var.s().g())));
        }
        long jA = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12.e.a(a0Var);
        if (jA != -1) {
            return new h(strB, jA, l.a(b(jA)));
        }
        return new h(strB, -1L, l.a(d()));
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12.c
    public void a() throws IOException {
        this.d.flush();
    }

    public void a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.r rVar, String str) throws IOException {
        if (this.e == 0) {
            this.d.a(str).a("\r\n");
            int iB = rVar.b();
            for (int i = 0; i < iB; i++) {
                this.d.a(rVar.a(i)).a(": ").a(rVar.b(i)).a("\r\n");
            }
            this.d.a("\r\n");
            this.e = 1;
            return;
        }
        throw new IllegalStateException("state: " + this.e);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12.c
    public a0.a a(boolean z) throws NumberFormatException, IOException {
        int i = this.e;
        if (i != 1 && i != 3) {
            throw new IllegalStateException("state: " + this.e);
        }
        try {
            k kVarA = k.a(f());
            a0.a aVarA = new a0.a().a(kVarA.f1544a).a(kVarA.f1545b).a(kVarA.f1546c).a(e());
            if (z && kVarA.f1545b == 100) {
                return null;
            }
            this.e = 4;
            return aVarA;
        } catch (EOFException e2) {
            IOException iOException = new IOException("unexpected end of stream on " + this.f1566b);
            iOException.initCause(e2);
            throw iOException;
        }
    }

    public bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.r a(long j) {
        if (this.e == 1) {
            this.e = 2;
            return new e(j);
        }
        throw new IllegalStateException("state: " + this.e);
    }

    public bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s a(s sVar) throws IOException {
        if (this.e == 4) {
            this.e = 5;
            return new d(sVar);
        }
        throw new IllegalStateException("state: " + this.e);
    }

    void a(i iVar) {
        t tVarG = iVar.g();
        iVar.a(t.d);
        tVarG.a();
        tVarG.b();
    }
}
