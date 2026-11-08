package b.a.f;

import c.x;
import c.y;
import c.z;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class i {
    static final /* synthetic */ boolean i = !i.class.desiredAssertionStatus();

    /* renamed from: b, reason: collision with root package name */
    long f904b;

    /* renamed from: c, reason: collision with root package name */
    final int f905c;
    final g d;
    final a e;
    private final List<b.a.f.c> j;
    private List<b.a.f.c> k;
    private boolean l;
    private final b m;

    /* renamed from: a, reason: collision with root package name */
    long f903a = 0;
    final c f = new c();
    final c g = new c();
    b.a.f.b h = null;

    i(int i2, g gVar, boolean z, boolean z2, List<b.a.f.c> list) {
        if (gVar == null) {
            throw new NullPointerException("connection == null");
        }
        if (list == null) {
            throw new NullPointerException("requestHeaders == null");
        }
        this.f905c = i2;
        this.d = gVar;
        this.f904b = gVar.l.d();
        this.m = new b(gVar.k.d());
        this.e = new a();
        this.m.f911b = z2;
        this.e.f908b = z;
        this.j = list;
    }

    public int a() {
        return this.f905c;
    }

    public synchronized boolean b() {
        if (this.h != null) {
            return false;
        }
        if ((this.m.f911b || this.m.f910a) && (this.e.f908b || this.e.f907a)) {
            if (this.l) {
                return false;
            }
        }
        return true;
    }

    public boolean c() {
        return this.d.f870a == ((this.f905c & 1) == 1);
    }

    public g d() {
        return this.d;
    }

    public List<b.a.f.c> e() {
        return this.j;
    }

    public synchronized List<b.a.f.c> f() throws IOException {
        List<b.a.f.c> list;
        if (!c()) {
            throw new IllegalStateException("servers cannot read response headers");
        }
        this.f.c();
        while (this.k == null && this.h == null) {
            try {
                o();
            } catch (Throwable th) {
                this.f.b();
                throw th;
            }
        }
        this.f.b();
        list = this.k;
        if (list != null) {
            this.k = null;
        } else {
            throw new n(this.h);
        }
        return list;
    }

    public synchronized b.a.f.b g() {
        return this.h;
    }

    public void a(List<b.a.f.c> list, boolean z) throws IOException {
        if (!i && Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        if (list == null) {
            throw new NullPointerException("responseHeaders == null");
        }
        boolean z2 = false;
        synchronized (this) {
            this.l = true;
            if (!z) {
                this.e.f908b = true;
                z2 = true;
            }
        }
        this.d.a(this.f905c, z2, list);
        if (z2) {
            this.d.f();
        }
    }

    public z h() {
        return this.f;
    }

    public z i() {
        return this.g;
    }

    public y j() {
        return this.m;
    }

    public x k() {
        synchronized (this) {
            if (!this.l && !c()) {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.e;
    }

    public void a(b.a.f.b bVar) throws IOException {
        if (d(bVar)) {
            this.d.b(this.f905c, bVar);
        }
    }

    public void b(b.a.f.b bVar) {
        if (d(bVar)) {
            this.d.a(this.f905c, bVar);
        }
    }

    private boolean d(b.a.f.b bVar) {
        if (!i && Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        synchronized (this) {
            if (this.h != null) {
                return false;
            }
            if (this.m.f911b && this.e.f908b) {
                return false;
            }
            this.h = bVar;
            notifyAll();
            this.d.b(this.f905c);
            return true;
        }
    }

    void a(List<b.a.f.c> list) {
        boolean zB;
        if (!i && Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        synchronized (this) {
            zB = true;
            this.l = true;
            if (this.k == null) {
                this.k = list;
                zB = b();
                notifyAll();
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(this.k);
                arrayList.add(null);
                arrayList.addAll(list);
                this.k = arrayList;
            }
        }
        if (zB) {
            return;
        }
        this.d.b(this.f905c);
    }

    void a(c.e eVar, int i2) throws IOException {
        if (!i && Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        this.m.a(eVar, i2);
    }

    void l() {
        boolean zB;
        if (!i && Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        synchronized (this) {
            this.m.f911b = true;
            zB = b();
            notifyAll();
        }
        if (zB) {
            return;
        }
        this.d.b(this.f905c);
    }

    synchronized void c(b.a.f.b bVar) {
        if (this.h == null) {
            this.h = bVar;
            notifyAll();
        }
    }

    private final class b implements y {

        /* renamed from: c, reason: collision with root package name */
        static final /* synthetic */ boolean f909c = !i.class.desiredAssertionStatus();

        /* renamed from: a, reason: collision with root package name */
        boolean f910a;

        /* renamed from: b, reason: collision with root package name */
        boolean f911b;
        private final c.c e = new c.c();
        private final c.c f = new c.c();
        private final long g;

        b(long j) {
            this.g = j;
        }

        @Override // c.y
        public long a(c.c cVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            synchronized (i.this) {
                b();
                c();
                if (this.f.b() == 0) {
                    return -1L;
                }
                long jA = this.f.a(cVar, Math.min(j, this.f.b()));
                i.this.f903a += jA;
                if (i.this.f903a >= i.this.d.k.d() / 2) {
                    i.this.d.a(i.this.f905c, i.this.f903a);
                    i.this.f903a = 0L;
                }
                synchronized (i.this.d) {
                    i.this.d.i += jA;
                    if (i.this.d.i >= i.this.d.k.d() / 2) {
                        i.this.d.a(0, i.this.d.i);
                        i.this.d.i = 0L;
                    }
                }
                return jA;
            }
        }

        private void b() throws IOException {
            i.this.f.c();
            while (this.f.b() == 0 && !this.f911b && !this.f910a && i.this.h == null) {
                try {
                    i.this.o();
                } finally {
                    i.this.f.b();
                }
            }
        }

        void a(c.e eVar, long j) throws IOException {
            boolean z;
            boolean z2;
            if (!f909c && Thread.holdsLock(i.this)) {
                throw new AssertionError();
            }
            while (j > 0) {
                synchronized (i.this) {
                    z = this.f911b;
                    z2 = this.f.b() + j > this.g;
                }
                if (z2) {
                    eVar.i(j);
                    i.this.b(b.a.f.b.FLOW_CONTROL_ERROR);
                    return;
                }
                if (z) {
                    eVar.i(j);
                    return;
                }
                long jA = eVar.a(this.e, j);
                if (jA == -1) {
                    throw new EOFException();
                }
                j -= jA;
                synchronized (i.this) {
                    boolean z3 = this.f.b() == 0;
                    this.f.a((y) this.e);
                    if (z3) {
                        i.this.notifyAll();
                    }
                }
            }
        }

        @Override // c.y
        public z a() {
            return i.this.f;
        }

        @Override // c.y, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            synchronized (i.this) {
                this.f910a = true;
                this.f.y();
                i.this.notifyAll();
            }
            i.this.m();
        }

        private void c() throws IOException {
            if (this.f910a) {
                throw new IOException("stream closed");
            }
            if (i.this.h != null) {
                throw new n(i.this.h);
            }
        }
    }

    void m() throws IOException {
        boolean z;
        boolean zB;
        if (!i && Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        synchronized (this) {
            z = !this.m.f911b && this.m.f910a && (this.e.f908b || this.e.f907a);
            zB = b();
        }
        if (z) {
            a(b.a.f.b.CANCEL);
        } else {
            if (zB) {
                return;
            }
            this.d.b(this.f905c);
        }
    }

    final class a implements x {

        /* renamed from: c, reason: collision with root package name */
        static final /* synthetic */ boolean f906c = !i.class.desiredAssertionStatus();
        private static final long e = 16384;

        /* renamed from: a, reason: collision with root package name */
        boolean f907a;

        /* renamed from: b, reason: collision with root package name */
        boolean f908b;
        private final c.c f = new c.c();

        a() {
        }

        @Override // c.x
        public void a_(c.c cVar, long j) throws IOException {
            if (!f906c && Thread.holdsLock(i.this)) {
                throw new AssertionError();
            }
            this.f.a_(cVar, j);
            while (this.f.b() >= 16384) {
                a(false);
            }
        }

        private void a(boolean z) throws IOException {
            long jMin;
            synchronized (i.this) {
                i.this.g.c();
                while (i.this.f904b <= 0 && !this.f908b && !this.f907a && i.this.h == null) {
                    try {
                        i.this.o();
                    } finally {
                    }
                }
                i.this.g.b();
                i.this.n();
                jMin = Math.min(i.this.f904b, this.f.b());
                i.this.f904b -= jMin;
            }
            i.this.g.c();
            try {
                i.this.d.a(i.this.f905c, z && jMin == this.f.b(), this.f, jMin);
            } finally {
            }
        }

        @Override // c.x, java.io.Flushable
        public void flush() throws IOException {
            if (!f906c && Thread.holdsLock(i.this)) {
                throw new AssertionError();
            }
            synchronized (i.this) {
                i.this.n();
            }
            while (this.f.b() > 0) {
                a(false);
                i.this.d.f();
            }
        }

        @Override // c.x
        public z a() {
            return i.this.g;
        }

        @Override // c.x, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (!f906c && Thread.holdsLock(i.this)) {
                throw new AssertionError();
            }
            synchronized (i.this) {
                if (this.f907a) {
                    return;
                }
                if (!i.this.e.f908b) {
                    if (this.f.b() > 0) {
                        while (this.f.b() > 0) {
                            a(true);
                        }
                    } else {
                        i.this.d.a(i.this.f905c, true, (c.c) null, 0L);
                    }
                }
                synchronized (i.this) {
                    this.f907a = true;
                }
                i.this.d.f();
                i.this.m();
            }
        }
    }

    void a(long j) {
        this.f904b += j;
        if (j > 0) {
            notifyAll();
        }
    }

    void n() throws IOException {
        if (this.e.f907a) {
            throw new IOException("stream closed");
        }
        if (this.e.f908b) {
            throw new IOException("stream finished");
        }
        if (this.h != null) {
            throw new n(this.h);
        }
    }

    void o() throws InterruptedException, InterruptedIOException {
        try {
            wait();
        } catch (InterruptedException unused) {
            throw new InterruptedIOException();
        }
    }

    class c extends c.a {
        c() {
        }

        @Override // c.a
        protected void a() {
            i.this.b(b.a.f.b.CANCEL);
        }

        @Override // c.a
        protected IOException a(IOException iOException) {
            SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
            if (iOException != null) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }

        public void b() throws IOException {
            if (a_()) {
                throw a((IOException) null);
            }
        }
    }
}
