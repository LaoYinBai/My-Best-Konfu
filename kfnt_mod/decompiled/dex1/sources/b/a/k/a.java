package b.a.k;

import b.a.c.g;
import b.a.k.c;
import b.aa;
import b.ac;
import b.ae;
import b.ai;
import b.aj;
import b.r;
import b.z;
import c.f;
import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class a implements c.a, ai {

    /* renamed from: b, reason: collision with root package name */
    static final /* synthetic */ boolean f962b = !a.class.desiredAssertionStatus();

    /* renamed from: c, reason: collision with root package name */
    private static final List<aa> f963c = Collections.singletonList(aa.HTTP_1_1);
    private static final long d = 16777216;
    private static final long e = 60000;
    private boolean A;

    /* renamed from: a, reason: collision with root package name */
    final aj f964a;
    private final ac f;
    private final Random g;
    private final long h;
    private final String i;
    private b.e j;
    private final Runnable k;
    private b.a.k.c l;
    private b.a.k.d m;
    private ScheduledExecutorService n;
    private e o;
    private long r;
    private boolean s;
    private ScheduledFuture<?> t;
    private String v;
    private boolean w;
    private int x;
    private int y;
    private int z;
    private final ArrayDeque<f> p = new ArrayDeque<>();
    private final ArrayDeque<Object> q = new ArrayDeque<>();
    private int u = -1;

    public a(ac acVar, aj ajVar, Random random, long j) {
        if (!"GET".equals(acVar.b())) {
            throw new IllegalArgumentException("Request must be GET: " + acVar.b());
        }
        this.f = acVar;
        this.f964a = ajVar;
        this.g = random;
        this.h = j;
        byte[] bArr = new byte[16];
        random.nextBytes(bArr);
        this.i = f.a(bArr).b();
        this.k = new Runnable() { // from class: b.a.k.a.1
            @Override // java.lang.Runnable
            public void run() throws IOException {
                do {
                    try {
                    } catch (IOException e2) {
                        a.this.a(e2, (ae) null);
                        return;
                    }
                } while (a.this.j());
            }
        };
    }

    @Override // b.ai
    public ac a() {
        return this.f;
    }

    @Override // b.ai
    public synchronized long b() {
        return this.r;
    }

    @Override // b.ai
    public void c() {
        this.j.c();
    }

    public void a(z zVar) {
        z zVarC = zVar.A().a(r.f1266a).a(f963c).c();
        final ac acVarD = this.f.f().a("Upgrade", "websocket").a("Connection", "Upgrade").a("Sec-WebSocket-Key", this.i).a("Sec-WebSocket-Version", "13").d();
        this.j = b.a.a.f750a.a(zVarC, acVarD);
        this.j.a(new b.f() { // from class: b.a.k.a.2
            @Override // b.f
            public void a(b.e eVar, ae aeVar) throws IOException {
                try {
                    a.this.a(aeVar);
                    g gVarA = b.a.a.f750a.a(eVar);
                    gVarA.e();
                    e eVarA = gVarA.c().a(gVarA);
                    try {
                        a.this.f964a.a(a.this, aeVar);
                        a.this.a("OkHttp WebSocket " + acVarD.a().u(), eVarA);
                        gVarA.c().b().setSoTimeout(0);
                        a.this.d();
                    } catch (Exception e2) {
                        a.this.a(e2, (ae) null);
                    }
                } catch (ProtocolException e3) {
                    a.this.a(e3, aeVar);
                    b.a.c.a(aeVar);
                }
            }

            @Override // b.f
            public void a(b.e eVar, IOException iOException) throws IOException {
                a.this.a(iOException, (ae) null);
            }
        });
    }

    void a(ae aeVar) throws ProtocolException {
        if (aeVar.c() != 101) {
            throw new ProtocolException("Expected HTTP 101 response but was '" + aeVar.c() + " " + aeVar.e() + "'");
        }
        String strB = aeVar.b("Connection");
        if (!"Upgrade".equalsIgnoreCase(strB)) {
            throw new ProtocolException("Expected 'Connection' header value 'Upgrade' but was '" + strB + "'");
        }
        String strB2 = aeVar.b("Upgrade");
        if (!"websocket".equalsIgnoreCase(strB2)) {
            throw new ProtocolException("Expected 'Upgrade' header value 'websocket' but was '" + strB2 + "'");
        }
        String strB3 = aeVar.b("Sec-WebSocket-Accept");
        String strB4 = f.a(this.i + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").d().b();
        if (strB4.equals(strB3)) {
            return;
        }
        throw new ProtocolException("Expected 'Sec-WebSocket-Accept' header value '" + strB4 + "' but was '" + strB3 + "'");
    }

    public void a(String str, e eVar) throws IOException {
        synchronized (this) {
            this.o = eVar;
            this.m = new b.a.k.d(eVar.f975c, eVar.e, this.g);
            this.n = new ScheduledThreadPoolExecutor(1, b.a.c.a(str, false));
            if (this.h != 0) {
                this.n.scheduleAtFixedRate(new d(), this.h, this.h, TimeUnit.MILLISECONDS);
            }
            if (!this.q.isEmpty()) {
                l();
            }
        }
        this.l = new b.a.k.c(eVar.f975c, eVar.d, this);
    }

    public void d() throws IOException {
        while (this.u == -1) {
            this.l.a();
        }
    }

    boolean e() throws IOException {
        try {
            this.l.a();
            return this.u == -1;
        } catch (Exception e2) {
            a(e2, (ae) null);
            return false;
        }
    }

    void a(int i, TimeUnit timeUnit) throws InterruptedException {
        this.n.awaitTermination(i, timeUnit);
    }

    void f() throws InterruptedException {
        if (this.t != null) {
            this.t.cancel(false);
        }
        this.n.shutdown();
        this.n.awaitTermination(10L, TimeUnit.SECONDS);
    }

    synchronized int g() {
        return this.x;
    }

    synchronized int h() {
        return this.y;
    }

    synchronized int i() {
        return this.z;
    }

    @Override // b.a.k.c.a
    public void b(String str) throws IOException {
        this.f964a.a(this, str);
    }

    @Override // b.a.k.c.a
    public void b(f fVar) throws IOException {
        this.f964a.a(this, fVar);
    }

    @Override // b.a.k.c.a
    public synchronized void c(f fVar) {
        if (!this.w && (!this.s || !this.q.isEmpty())) {
            this.p.add(fVar);
            l();
            this.y++;
        }
    }

    @Override // b.a.k.c.a
    public synchronized void d(f fVar) {
        this.z++;
        this.A = false;
    }

    @Override // b.a.k.c.a
    public void b(int i, String str) throws IOException {
        e eVar;
        if (i == -1) {
            throw new IllegalArgumentException();
        }
        synchronized (this) {
            if (this.u != -1) {
                throw new IllegalStateException("already closed");
            }
            this.u = i;
            this.v = str;
            if (this.s && this.q.isEmpty()) {
                eVar = this.o;
                this.o = null;
                if (this.t != null) {
                    this.t.cancel(false);
                }
                this.n.shutdown();
            } else {
                eVar = null;
            }
        }
        try {
            this.f964a.a(this, i, str);
            if (eVar != null) {
                this.f964a.b(this, i, str);
            }
        } finally {
            b.a.c.a(eVar);
        }
    }

    @Override // b.ai
    public boolean a(String str) {
        if (str == null) {
            throw new NullPointerException("text == null");
        }
        return a(f.a(str), 1);
    }

    @Override // b.ai
    public boolean a(f fVar) {
        if (fVar == null) {
            throw new NullPointerException("bytes == null");
        }
        return a(fVar, 2);
    }

    private synchronized boolean a(f fVar, int i) {
        if (!this.w && !this.s) {
            if (this.r + fVar.k() > d) {
                a(1001, (String) null);
                return false;
            }
            this.r += fVar.k();
            this.q.add(new c(i, fVar));
            l();
            return true;
        }
        return false;
    }

    synchronized boolean e(f fVar) {
        if (!this.w && (!this.s || !this.q.isEmpty())) {
            this.p.add(fVar);
            l();
            return true;
        }
        return false;
    }

    @Override // b.ai
    public boolean a(int i, String str) {
        return a(i, str, 60000L);
    }

    synchronized boolean a(int i, String str, long j) {
        b.a.k.b.b(i);
        f fVarA = null;
        if (str != null) {
            fVarA = f.a(str);
            if (fVarA.k() > 123) {
                throw new IllegalArgumentException("reason.size() > 123: " + str);
            }
        }
        if (!this.w && !this.s) {
            this.s = true;
            this.q.add(new b(i, fVarA, j));
            l();
            return true;
        }
        return false;
    }

    private void l() {
        if (!f962b && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        if (this.n != null) {
            this.n.execute(this.k);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0052 A[Catch: all -> 0x0056, TRY_ENTER, TryCatch #1 {all -> 0x0056, blocks: (B:23:0x0052, B:26:0x0058, B:28:0x005c, B:29:0x0078, B:35:0x0087, B:36:0x0088, B:38:0x008c, B:40:0x0097, B:43:0x00a1, B:44:0x00a6, B:30:0x0079, B:31:0x0083), top: B:52:0x0050, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0058 A[Catch: all -> 0x0056, TryCatch #1 {all -> 0x0056, blocks: (B:23:0x0052, B:26:0x0058, B:28:0x005c, B:29:0x0078, B:35:0x0087, B:36:0x0088, B:38:0x008c, B:40:0x0097, B:43:0x00a1, B:44:0x00a6, B:30:0x0079, B:31:0x0083), top: B:52:0x0050, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    boolean j() throws java.io.IOException {
        /*
            r11 = this;
            monitor-enter(r11)
            boolean r0 = r11.w     // Catch: java.lang.Throwable -> Lab
            r1 = 0
            if (r0 == 0) goto L8
            monitor-exit(r11)     // Catch: java.lang.Throwable -> Lab
            return r1
        L8:
            b.a.k.d r0 = r11.m     // Catch: java.lang.Throwable -> Lab
            java.util.ArrayDeque<c.f> r2 = r11.p     // Catch: java.lang.Throwable -> Lab
            java.lang.Object r2 = r2.poll()     // Catch: java.lang.Throwable -> Lab
            c.f r2 = (c.f) r2     // Catch: java.lang.Throwable -> Lab
            r3 = -1
            r4 = 0
            if (r2 != 0) goto L4c
            java.util.ArrayDeque<java.lang.Object> r5 = r11.q     // Catch: java.lang.Throwable -> Lab
            java.lang.Object r5 = r5.poll()     // Catch: java.lang.Throwable -> Lab
            boolean r6 = r5 instanceof b.a.k.a.b     // Catch: java.lang.Throwable -> Lab
            if (r6 == 0) goto L46
            int r1 = r11.u     // Catch: java.lang.Throwable -> Lab
            java.lang.String r6 = r11.v     // Catch: java.lang.Throwable -> Lab
            if (r1 == r3) goto L31
            b.a.k.a$e r3 = r11.o     // Catch: java.lang.Throwable -> Lab
            r11.o = r4     // Catch: java.lang.Throwable -> Lab
            java.util.concurrent.ScheduledExecutorService r4 = r11.n     // Catch: java.lang.Throwable -> Lab
            r4.shutdown()     // Catch: java.lang.Throwable -> Lab
            r4 = r3
            goto L4f
        L31:
            java.util.concurrent.ScheduledExecutorService r3 = r11.n     // Catch: java.lang.Throwable -> Lab
            b.a.k.a$a r7 = new b.a.k.a$a     // Catch: java.lang.Throwable -> Lab
            r7.<init>()     // Catch: java.lang.Throwable -> Lab
            r8 = r5
            b.a.k.a$b r8 = (b.a.k.a.b) r8     // Catch: java.lang.Throwable -> Lab
            long r8 = r8.f971c     // Catch: java.lang.Throwable -> Lab
            java.util.concurrent.TimeUnit r10 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.Throwable -> Lab
            java.util.concurrent.ScheduledFuture r3 = r3.schedule(r7, r8, r10)     // Catch: java.lang.Throwable -> Lab
            r11.t = r3     // Catch: java.lang.Throwable -> Lab
            goto L4f
        L46:
            if (r5 != 0) goto L4a
            monitor-exit(r11)     // Catch: java.lang.Throwable -> Lab
            return r1
        L4a:
            r6 = r4
            goto L4e
        L4c:
            r5 = r4
            r6 = r5
        L4e:
            r1 = -1
        L4f:
            monitor-exit(r11)     // Catch: java.lang.Throwable -> Lab
            if (r2 == 0) goto L58
            r0.b(r2)     // Catch: java.lang.Throwable -> L56
            goto L9c
        L56:
            r0 = move-exception
            goto La7
        L58:
            boolean r2 = r5 instanceof b.a.k.a.c     // Catch: java.lang.Throwable -> L56
            if (r2 == 0) goto L88
            r1 = r5
            b.a.k.a$c r1 = (b.a.k.a.c) r1     // Catch: java.lang.Throwable -> L56
            c.f r1 = r1.f973b     // Catch: java.lang.Throwable -> L56
            b.a.k.a$c r5 = (b.a.k.a.c) r5     // Catch: java.lang.Throwable -> L56
            int r2 = r5.f972a     // Catch: java.lang.Throwable -> L56
            int r3 = r1.k()     // Catch: java.lang.Throwable -> L56
            long r5 = (long) r3     // Catch: java.lang.Throwable -> L56
            c.x r0 = r0.a(r2, r5)     // Catch: java.lang.Throwable -> L56
            c.d r0 = c.p.a(r0)     // Catch: java.lang.Throwable -> L56
            r0.g(r1)     // Catch: java.lang.Throwable -> L56
            r0.close()     // Catch: java.lang.Throwable -> L56
            monitor-enter(r11)     // Catch: java.lang.Throwable -> L56
            long r2 = r11.r     // Catch: java.lang.Throwable -> L85
            int r0 = r1.k()     // Catch: java.lang.Throwable -> L85
            long r0 = (long) r0     // Catch: java.lang.Throwable -> L85
            long r2 = r2 - r0
            r11.r = r2     // Catch: java.lang.Throwable -> L85
            monitor-exit(r11)     // Catch: java.lang.Throwable -> L85
            goto L9c
        L85:
            r0 = move-exception
            monitor-exit(r11)     // Catch: java.lang.Throwable -> L85
            throw r0     // Catch: java.lang.Throwable -> L56
        L88:
            boolean r2 = r5 instanceof b.a.k.a.b     // Catch: java.lang.Throwable -> L56
            if (r2 == 0) goto La1
            b.a.k.a$b r5 = (b.a.k.a.b) r5     // Catch: java.lang.Throwable -> L56
            int r2 = r5.f969a     // Catch: java.lang.Throwable -> L56
            c.f r3 = r5.f970b     // Catch: java.lang.Throwable -> L56
            r0.a(r2, r3)     // Catch: java.lang.Throwable -> L56
            if (r4 == 0) goto L9c
            b.aj r0 = r11.f964a     // Catch: java.lang.Throwable -> L56
            r0.b(r11, r1, r6)     // Catch: java.lang.Throwable -> L56
        L9c:
            r0 = 1
            b.a.c.a(r4)
            return r0
        La1:
            java.lang.AssertionError r0 = new java.lang.AssertionError     // Catch: java.lang.Throwable -> L56
            r0.<init>()     // Catch: java.lang.Throwable -> L56
            throw r0     // Catch: java.lang.Throwable -> L56
        La7:
            b.a.c.a(r4)
            throw r0
        Lab:
            r0 = move-exception
            monitor-exit(r11)     // Catch: java.lang.Throwable -> Lab
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.k.a.j():boolean");
    }

    private final class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() throws IOException {
            a.this.k();
        }
    }

    void k() throws IOException {
        synchronized (this) {
            if (this.w) {
                return;
            }
            b.a.k.d dVar = this.m;
            int i = this.A ? this.x : -1;
            this.x++;
            this.A = true;
            if (i != -1) {
                a(new SocketTimeoutException("sent ping but didn't receive pong within " + this.h + "ms (after " + (i - 1) + " successful ping/pongs)"), (ae) null);
                return;
            }
            try {
                dVar.a(f.f2139b);
            } catch (IOException e2) {
                a(e2, (ae) null);
            }
        }
    }

    public void a(Exception exc, @Nullable ae aeVar) throws IOException {
        synchronized (this) {
            if (this.w) {
                return;
            }
            this.w = true;
            e eVar = this.o;
            this.o = null;
            if (this.t != null) {
                this.t.cancel(false);
            }
            if (this.n != null) {
                this.n.shutdown();
            }
            try {
                this.f964a.a(this, exc, aeVar);
            } finally {
                b.a.c.a(eVar);
            }
        }
    }

    static final class c {

        /* renamed from: a, reason: collision with root package name */
        final int f972a;

        /* renamed from: b, reason: collision with root package name */
        final f f973b;

        c(int i, f fVar) {
            this.f972a = i;
            this.f973b = fVar;
        }
    }

    static final class b {

        /* renamed from: a, reason: collision with root package name */
        final int f969a;

        /* renamed from: b, reason: collision with root package name */
        final f f970b;

        /* renamed from: c, reason: collision with root package name */
        final long f971c;

        b(int i, f fVar, long j) {
            this.f969a = i;
            this.f970b = fVar;
            this.f971c = j;
        }
    }

    public static abstract class e implements Closeable {

        /* renamed from: c, reason: collision with root package name */
        public final boolean f975c;
        public final c.e d;
        public final c.d e;

        public e(boolean z, c.e eVar, c.d dVar) {
            this.f975c = z;
            this.d = eVar;
            this.e = dVar;
        }
    }

    /* renamed from: b.a.k.a$a, reason: collision with other inner class name */
    final class RunnableC0022a implements Runnable {
        RunnableC0022a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.c();
        }
    }
}
