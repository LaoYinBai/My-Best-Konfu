package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1;

import android.support.v4.media.session.PlaybackStateCompat;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.r;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.t;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class i {
    static final /* synthetic */ boolean l = !i.class.desiredAssertionStatus();

    /* renamed from: b, reason: collision with root package name */
    long f1634b;

    /* renamed from: c, reason: collision with root package name */
    final int f1635c;
    final g d;
    private List<bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.c> e;
    private boolean f;
    private final b g;
    final a h;

    /* renamed from: a, reason: collision with root package name */
    long f1633a = 0;
    final c i = new c();
    final c j = new c();
    bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b k = null;

    class c extends bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a {
        c() {
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a
        protected IOException b(IOException iOException) {
            SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
            if (iOException != null) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a
        protected void i() {
            i.this.b(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b.CANCEL);
        }

        public void k() throws IOException {
            if (h()) {
                throw b((IOException) null);
            }
        }
    }

    i(int i, g gVar, boolean z, boolean z2, List<bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.c> list) {
        if (gVar == null) {
            throw new NullPointerException("connection == null");
        }
        if (list == null) {
            throw new NullPointerException("requestHeaders == null");
        }
        this.f1635c = i;
        this.d = gVar;
        this.f1634b = gVar.n.c();
        this.g = new b(gVar.m.c());
        this.h = new a();
        this.g.e = z2;
        this.h.f1638c = z;
    }

    public void a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b bVar) throws IOException {
        if (d(bVar)) {
            this.d.b(this.f1635c, bVar);
        }
    }

    public void b(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b bVar) {
        if (d(bVar)) {
            this.d.c(this.f1635c, bVar);
        }
    }

    public int c() {
        return this.f1635c;
    }

    public r d() {
        synchronized (this) {
            if (!this.f && !f()) {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.h;
    }

    public s e() {
        return this.g;
    }

    public boolean f() {
        return this.d.f1599a == ((this.f1635c & 1) == 1);
    }

    public synchronized boolean g() {
        if (this.k != null) {
            return false;
        }
        b bVar = this.g;
        if (bVar.e || bVar.d) {
            a aVar = this.h;
            if (aVar.f1638c || aVar.f1637b) {
                if (this.f) {
                    return false;
                }
            }
        }
        return true;
    }

    public t h() {
        return this.i;
    }

    void i() {
        boolean zG;
        if (!l && Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        synchronized (this) {
            this.g.e = true;
            zG = g();
            notifyAll();
        }
        if (zG) {
            return;
        }
        this.d.d(this.f1635c);
    }

    public synchronized List<bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.c> j() throws IOException {
        List<bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.c> list;
        if (!f()) {
            throw new IllegalStateException("servers cannot read response headers");
        }
        this.i.g();
        while (this.e == null && this.k == null) {
            try {
                k();
            } catch (Throwable th) {
                this.i.k();
                throw th;
            }
        }
        this.i.k();
        list = this.e;
        if (list == null) {
            throw new o(this.k);
        }
        this.e = null;
        return list;
    }

    void k() throws InterruptedException, InterruptedIOException {
        try {
            wait();
        } catch (InterruptedException unused) {
            throw new InterruptedIOException();
        }
    }

    public t l() {
        return this.j;
    }

    synchronized void c(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b bVar) {
        if (this.k == null) {
            this.k = bVar;
            notifyAll();
        }
    }

    final class a implements r {
        static final /* synthetic */ boolean e = !i.class.desiredAssertionStatus();

        /* renamed from: a, reason: collision with root package name */
        private final bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c f1636a = new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c();

        /* renamed from: b, reason: collision with root package name */
        boolean f1637b;

        /* renamed from: c, reason: collision with root package name */
        boolean f1638c;

        a() {
        }

        private void a(boolean z) throws IOException {
            long jMin;
            synchronized (i.this) {
                i.this.j.g();
                while (i.this.f1634b <= 0 && !this.f1638c && !this.f1637b && i.this.k == null) {
                    try {
                        i.this.k();
                    } finally {
                    }
                }
                i.this.j.k();
                i.this.b();
                jMin = Math.min(i.this.f1634b, this.f1636a.o());
                i.this.f1634b -= jMin;
            }
            i.this.j.g();
            try {
                i.this.d.a(i.this.f1635c, z && jMin == this.f1636a.o(), this.f1636a, jMin);
            } finally {
            }
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.r
        public void b(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c cVar, long j) throws IOException {
            if (!e && Thread.holdsLock(i.this)) {
                throw new AssertionError();
            }
            this.f1636a.b(cVar, j);
            while (this.f1636a.o() >= PlaybackStateCompat.ACTION_PREPARE) {
                a(false);
            }
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.r, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (!e && Thread.holdsLock(i.this)) {
                throw new AssertionError();
            }
            synchronized (i.this) {
                if (this.f1637b) {
                    return;
                }
                if (!i.this.h.f1638c) {
                    if (this.f1636a.o() > 0) {
                        while (this.f1636a.o() > 0) {
                            a(true);
                        }
                    } else {
                        i iVar = i.this;
                        iVar.d.a(iVar.f1635c, true, (bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c) null, 0L);
                    }
                }
                synchronized (i.this) {
                    this.f1637b = true;
                }
                i.this.d.flush();
                i.this.a();
            }
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.r, java.io.Flushable
        public void flush() throws IOException {
            if (!e && Thread.holdsLock(i.this)) {
                throw new AssertionError();
            }
            synchronized (i.this) {
                i.this.b();
            }
            while (this.f1636a.o() > 0) {
                a(false);
                i.this.d.flush();
            }
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.r
        public t b() {
            return i.this.j;
        }
    }

    void a(List<bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.c> list) {
        boolean zG;
        if (!l && Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        synchronized (this) {
            zG = true;
            this.f = true;
            if (this.e == null) {
                this.e = list;
                zG = g();
                notifyAll();
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(this.e);
                arrayList.add(null);
                arrayList.addAll(list);
                this.e = arrayList;
            }
        }
        if (zG) {
            return;
        }
        this.d.d(this.f1635c);
    }

    void b() throws IOException {
        a aVar = this.h;
        if (!aVar.f1637b) {
            if (!aVar.f1638c) {
                bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b bVar = this.k;
                if (bVar != null) {
                    throw new o(bVar);
                }
                return;
            }
            throw new IOException("stream finished");
        }
        throw new IOException("stream closed");
    }

    private boolean d(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b bVar) {
        if (!l && Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        synchronized (this) {
            if (this.k != null) {
                return false;
            }
            if (this.g.e && this.h.f1638c) {
                return false;
            }
            this.k = bVar;
            notifyAll();
            this.d.d(this.f1635c);
            return true;
        }
    }

    void a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e eVar, int i) throws IOException {
        if (!l && Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        this.g.a(eVar, i);
    }

    void a() throws IOException {
        boolean z;
        boolean zG;
        if (!l && Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        synchronized (this) {
            z = !this.g.e && this.g.d && (this.h.f1638c || this.h.f1637b);
            zG = g();
        }
        if (z) {
            a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b.CANCEL);
        } else {
            if (zG) {
                return;
            }
            this.d.d(this.f1635c);
        }
    }

    private final class b implements s {
        static final /* synthetic */ boolean g = !i.class.desiredAssertionStatus();

        /* renamed from: a, reason: collision with root package name */
        private final bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c f1639a = new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c();

        /* renamed from: b, reason: collision with root package name */
        private final bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c f1640b = new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c();

        /* renamed from: c, reason: collision with root package name */
        private final long f1641c;
        boolean d;
        boolean e;

        b(long j) {
            this.f1641c = j;
        }

        private void h() throws IOException {
            if (this.d) {
                throw new IOException("stream closed");
            }
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b bVar = i.this.k;
            if (bVar != null) {
                throw new o(bVar);
            }
        }

        private void i() throws IOException {
            i.this.i.g();
            while (this.f1640b.o() == 0 && !this.e && !this.d && i.this.k == null) {
                try {
                    i.this.k();
                } finally {
                    i.this.i.k();
                }
            }
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s
        public long a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c cVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            synchronized (i.this) {
                i();
                h();
                if (this.f1640b.o() == 0) {
                    return -1L;
                }
                long jA = this.f1640b.a(cVar, Math.min(j, this.f1640b.o()));
                i.this.f1633a += jA;
                if (i.this.f1633a >= i.this.d.m.c() / 2) {
                    i.this.d.a(i.this.f1635c, i.this.f1633a);
                    i.this.f1633a = 0L;
                }
                synchronized (i.this.d) {
                    i.this.d.k += jA;
                    if (i.this.d.k >= i.this.d.m.c() / 2) {
                        i.this.d.a(0, i.this.d.k);
                        i.this.d.k = 0L;
                    }
                }
                return jA;
            }
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s
        public t b() {
            return i.this.i;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            synchronized (i.this) {
                this.d = true;
                this.f1640b.j();
                i.this.notifyAll();
            }
            i.this.a();
        }

        void a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e eVar, long j) throws IOException {
            boolean z;
            boolean z2;
            if (!g && Thread.holdsLock(i.this)) {
                throw new AssertionError();
            }
            while (j > 0) {
                synchronized (i.this) {
                    z = this.e;
                    z2 = this.f1640b.o() + j > this.f1641c;
                }
                if (z2) {
                    eVar.skip(j);
                    i.this.b(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b.FLOW_CONTROL_ERROR);
                    return;
                }
                if (z) {
                    eVar.skip(j);
                    return;
                }
                long jA = eVar.a(this.f1639a, j);
                if (jA != -1) {
                    j -= jA;
                    synchronized (i.this) {
                        boolean z3 = this.f1640b.o() == 0;
                        this.f1640b.a(this.f1639a);
                        if (z3) {
                            i.this.notifyAll();
                        }
                    }
                } else {
                    throw new EOFException();
                }
            }
        }
    }

    void a(long j) {
        this.f1634b += j;
        if (j > 0) {
            notifyAll();
        }
    }
}
