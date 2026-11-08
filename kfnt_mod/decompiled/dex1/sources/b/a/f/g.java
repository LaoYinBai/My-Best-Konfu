package b.a.f;

import androidx.appcompat.widget.ActivityChooserView;
import androidx.core.internal.view.SupportMenu;
import b.a.f.h;
import b.aa;
import c.p;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class g implements Closeable {
    private static final int s = 16777216;

    /* renamed from: a, reason: collision with root package name */
    final boolean f870a;

    /* renamed from: b, reason: collision with root package name */
    final b f871b;
    final String d;
    int e;
    int f;
    boolean g;
    final l h;
    long j;
    final Socket n;
    final j o;
    final d p;
    private final ScheduledExecutorService u;
    private final ExecutorService v;
    private boolean w;
    static final /* synthetic */ boolean r = !g.class.desiredAssertionStatus();
    private static final ExecutorService t = new ThreadPoolExecutor(0, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, 60, TimeUnit.SECONDS, new SynchronousQueue(), b.a.c.a("OkHttp Http2Connection", true));

    /* renamed from: c, reason: collision with root package name */
    final Map<Integer, i> f872c = new LinkedHashMap();
    long i = 0;
    m k = new m();
    final m l = new m();
    boolean m = false;
    final Set<Integer> q = new LinkedHashSet();

    public static abstract class b {
        public static final b f = new b() { // from class: b.a.f.g.b.1
            @Override // b.a.f.g.b
            public void a(i iVar) throws IOException {
                iVar.a(b.a.f.b.REFUSED_STREAM);
            }
        };

        public void a(g gVar) {
        }

        public abstract void a(i iVar) throws IOException;
    }

    boolean c(int i) {
        return i != 0 && (i & 1) == 0;
    }

    g(a aVar) {
        this.h = aVar.f;
        this.f870a = aVar.g;
        this.f871b = aVar.e;
        this.f = aVar.g ? 1 : 2;
        if (aVar.g) {
            this.f += 2;
        }
        if (aVar.g) {
            this.k.a(7, 16777216);
        }
        this.d = aVar.f886b;
        this.u = new ScheduledThreadPoolExecutor(1, b.a.c.a(b.a.c.a("OkHttp %s Writer", this.d), false));
        if (aVar.h != 0) {
            this.u.scheduleAtFixedRate(new c(false, 0, 0), aVar.h, aVar.h, TimeUnit.MILLISECONDS);
        }
        this.v = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), b.a.c.a(b.a.c.a("OkHttp %s Push Observer", this.d), true));
        this.l.a(7, SupportMenu.USER_MASK);
        this.l.a(5, 16384);
        this.j = this.l.d();
        this.n = aVar.f885a;
        this.o = new j(aVar.d, this.f870a);
        this.p = new d(new h(aVar.f887c, this.f870a));
    }

    public aa a() {
        return aa.HTTP_2;
    }

    public synchronized int b() {
        return this.f872c.size();
    }

    synchronized i a(int i) {
        return this.f872c.get(Integer.valueOf(i));
    }

    synchronized i b(int i) {
        i iVarRemove;
        iVarRemove = this.f872c.remove(Integer.valueOf(i));
        notifyAll();
        return iVarRemove;
    }

    public synchronized int c() {
        return this.l.c(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
    }

    public i a(int i, List<b.a.f.c> list, boolean z) throws IOException {
        if (this.f870a) {
            throw new IllegalStateException("Client cannot push requests.");
        }
        return c(i, list, z);
    }

    public i a(List<b.a.f.c> list, boolean z) throws IOException {
        return c(0, list, z);
    }

    private i c(int i, List<b.a.f.c> list, boolean z) throws IOException {
        int i2;
        i iVar;
        boolean z2;
        boolean z3 = !z;
        synchronized (this.o) {
            synchronized (this) {
                if (this.f > 1073741823) {
                    a(b.a.f.b.REFUSED_STREAM);
                }
                if (this.g) {
                    throw new b.a.f.a();
                }
                i2 = this.f;
                this.f += 2;
                iVar = new i(i2, this, z3, false, list);
                z2 = !z || this.j == 0 || iVar.f904b == 0;
                if (iVar.b()) {
                    this.f872c.put(Integer.valueOf(i2), iVar);
                }
            }
            if (i == 0) {
                this.o.a(z3, i2, i, list);
            } else {
                if (this.f870a) {
                    throw new IllegalArgumentException("client streams shouldn't have associated stream IDs");
                }
                this.o.a(i, i2, list);
            }
        }
        if (z2) {
            this.o.b();
        }
        return iVar;
    }

    void a(int i, boolean z, List<b.a.f.c> list) throws IOException {
        this.o.a(z, i, list);
    }

    public void a(int i, boolean z, c.c cVar, long j) throws IOException {
        int iMin;
        long j2;
        if (j == 0) {
            this.o.a(z, i, cVar, 0);
            return;
        }
        while (j > 0) {
            synchronized (this) {
                while (this.j <= 0) {
                    try {
                        if (!this.f872c.containsKey(Integer.valueOf(i))) {
                            throw new IOException("stream closed");
                        }
                        wait();
                    } catch (InterruptedException unused) {
                        throw new InterruptedIOException();
                    }
                }
                iMin = Math.min((int) Math.min(j, this.j), this.o.c());
                j2 = iMin;
                this.j -= j2;
            }
            j -= j2;
            this.o.a(z && j == 0, i, cVar, iMin);
        }
    }

    void a(long j) {
        this.j += j;
        if (j > 0) {
            notifyAll();
        }
    }

    void a(final int i, final b.a.f.b bVar) {
        try {
            this.u.execute(new b.a.b("OkHttp %s stream %d", new Object[]{this.d, Integer.valueOf(i)}) { // from class: b.a.f.g.1
                @Override // b.a.b
                public void d() {
                    try {
                        g.this.b(i, bVar);
                    } catch (IOException unused) {
                        g.this.j();
                    }
                }
            });
        } catch (RejectedExecutionException unused) {
        }
    }

    void b(int i, b.a.f.b bVar) throws IOException {
        this.o.a(i, bVar);
    }

    void a(final int i, final long j) {
        try {
            this.u.execute(new b.a.b("OkHttp Window Update %s stream %d", new Object[]{this.d, Integer.valueOf(i)}) { // from class: b.a.f.g.2
                @Override // b.a.b
                public void d() {
                    try {
                        g.this.o.a(i, j);
                    } catch (IOException unused) {
                        g.this.j();
                    }
                }
            });
        } catch (RejectedExecutionException unused) {
        }
    }

    final class c extends b.a.b {

        /* renamed from: a, reason: collision with root package name */
        final boolean f888a;

        /* renamed from: c, reason: collision with root package name */
        final int f889c;
        final int d;

        c(boolean z, int i, int i2) {
            super("OkHttp %s ping %08x%08x", g.this.d, Integer.valueOf(i), Integer.valueOf(i2));
            this.f888a = z;
            this.f889c = i;
            this.d = i2;
        }

        @Override // b.a.b
        public void d() {
            g.this.a(this.f888a, this.f889c, this.d);
        }
    }

    void a(boolean z, int i, int i2) {
        boolean z2;
        if (!z) {
            synchronized (this) {
                z2 = this.w;
                this.w = true;
            }
            if (z2) {
                j();
                return;
            }
        }
        try {
            this.o.a(z, i, i2);
        } catch (IOException unused) {
            j();
        }
    }

    void d() throws InterruptedException, IOException {
        a(false, 1330343787, -257978967);
        e();
    }

    synchronized void e() throws InterruptedException, IOException {
        while (this.w) {
            wait();
        }
    }

    public void f() throws IOException {
        this.o.b();
    }

    public void a(b.a.f.b bVar) throws IOException {
        synchronized (this.o) {
            synchronized (this) {
                if (this.g) {
                    return;
                }
                this.g = true;
                this.o.a(this.e, bVar, b.a.c.f788a);
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        a(b.a.f.b.NO_ERROR, b.a.f.b.CANCEL);
    }

    void a(b.a.f.b bVar, b.a.f.b bVar2) throws IOException {
        if (!r && Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        i[] iVarArr = null;
        try {
            a(bVar);
            e = null;
        } catch (IOException e) {
            e = e;
        }
        synchronized (this) {
            if (!this.f872c.isEmpty()) {
                iVarArr = (i[]) this.f872c.values().toArray(new i[this.f872c.size()]);
                this.f872c.clear();
            }
        }
        if (iVarArr != null) {
            for (i iVar : iVarArr) {
                try {
                    iVar.a(bVar2);
                } catch (IOException e2) {
                    if (e != null) {
                        e = e2;
                    }
                }
            }
        }
        try {
            this.o.close();
        } catch (IOException e3) {
            if (e == null) {
                e = e3;
            }
        }
        try {
            this.n.close();
        } catch (IOException e4) {
            e = e4;
        }
        this.u.shutdown();
        this.v.shutdown();
        if (e != null) {
            throw e;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        try {
            a(b.a.f.b.PROTOCOL_ERROR, b.a.f.b.PROTOCOL_ERROR);
        } catch (IOException unused) {
        }
    }

    public void g() throws IOException {
        a(true);
    }

    void a(boolean z) throws IOException {
        if (z) {
            this.o.a();
            this.o.b(this.k);
            if (this.k.d() != 65535) {
                this.o.a(0, r6 - SupportMenu.USER_MASK);
            }
        }
        new Thread(this.p).start();
    }

    public void a(m mVar) throws IOException {
        synchronized (this.o) {
            synchronized (this) {
                if (this.g) {
                    throw new b.a.f.a();
                }
                this.k.a(mVar);
            }
            this.o.b(mVar);
        }
    }

    public synchronized boolean h() {
        return this.g;
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        Socket f885a;

        /* renamed from: b, reason: collision with root package name */
        String f886b;

        /* renamed from: c, reason: collision with root package name */
        c.e f887c;
        c.d d;
        b e = b.f;
        l f = l.f922a;
        boolean g;
        int h;

        public a(boolean z) {
            this.g = z;
        }

        public a a(Socket socket) throws IOException {
            return a(socket, ((InetSocketAddress) socket.getRemoteSocketAddress()).getHostName(), p.a(p.b(socket)), p.a(p.a(socket)));
        }

        public a a(Socket socket, String str, c.e eVar, c.d dVar) {
            this.f885a = socket;
            this.f886b = str;
            this.f887c = eVar;
            this.d = dVar;
            return this;
        }

        public a a(b bVar) {
            this.e = bVar;
            return this;
        }

        public a a(l lVar) {
            this.f = lVar;
            return this;
        }

        public a a(int i) {
            this.h = i;
            return this;
        }

        public g a() {
            return new g(this);
        }
    }

    class d extends b.a.b implements h.b {

        /* renamed from: a, reason: collision with root package name */
        final h f890a;

        @Override // b.a.f.h.b
        public void a() {
        }

        @Override // b.a.f.h.b
        public void a(int i, int i2, int i3, boolean z) {
        }

        @Override // b.a.f.h.b
        public void a(int i, String str, c.f fVar, String str2, int i2, long j) {
        }

        d(h hVar) {
            super("OkHttp %s", g.this.d);
            this.f890a = hVar;
        }

        @Override // b.a.b
        protected void d() throws Throwable {
            b.a.f.b bVar;
            b.a.f.b bVar2;
            g gVar;
            b.a.f.b bVar3 = b.a.f.b.INTERNAL_ERROR;
            b.a.f.b bVar4 = b.a.f.b.INTERNAL_ERROR;
            try {
                try {
                    try {
                        this.f890a.a(this);
                        while (this.f890a.a(false, (h.b) this)) {
                        }
                        bVar = b.a.f.b.NO_ERROR;
                    } catch (IOException unused) {
                    }
                } catch (Throwable th) {
                    th = th;
                    try {
                        g.this.a(bVar3, bVar4);
                    } catch (IOException unused2) {
                    }
                    b.a.c.a(this.f890a);
                    throw th;
                }
                try {
                    try {
                        bVar2 = b.a.f.b.CANCEL;
                        gVar = g.this;
                    } catch (IOException unused3) {
                        bVar3 = bVar;
                        bVar = b.a.f.b.PROTOCOL_ERROR;
                        bVar2 = b.a.f.b.PROTOCOL_ERROR;
                        gVar = g.this;
                        gVar.a(bVar, bVar2);
                        b.a.c.a(this.f890a);
                    }
                } catch (Throwable th2) {
                    b.a.f.b bVar5 = bVar;
                    th = th2;
                    bVar3 = bVar5;
                    g.this.a(bVar3, bVar4);
                    b.a.c.a(this.f890a);
                    throw th;
                }
            } catch (IOException unused4) {
            }
            gVar.a(bVar, bVar2);
            b.a.c.a(this.f890a);
        }

        @Override // b.a.f.h.b
        public void a(boolean z, int i, c.e eVar, int i2) throws IOException {
            if (g.this.c(i)) {
                g.this.a(i, eVar, i2, z);
                return;
            }
            i iVarA = g.this.a(i);
            if (iVarA == null) {
                g.this.a(i, b.a.f.b.PROTOCOL_ERROR);
                eVar.i(i2);
            } else {
                iVarA.a(eVar, i2);
                if (z) {
                    iVarA.l();
                }
            }
        }

        @Override // b.a.f.h.b
        public void a(boolean z, int i, int i2, List<b.a.f.c> list) {
            if (g.this.c(i)) {
                g.this.b(i, list, z);
                return;
            }
            synchronized (g.this) {
                i iVarA = g.this.a(i);
                if (iVarA == null) {
                    if (g.this.g) {
                        return;
                    }
                    if (i <= g.this.e) {
                        return;
                    }
                    if (i % 2 == g.this.f % 2) {
                        return;
                    }
                    final i iVar = new i(i, g.this, false, z, list);
                    g.this.e = i;
                    g.this.f872c.put(Integer.valueOf(i), iVar);
                    g.t.execute(new b.a.b("OkHttp %s stream %d", new Object[]{g.this.d, Integer.valueOf(i)}) { // from class: b.a.f.g.d.1
                        @Override // b.a.b
                        public void d() {
                            try {
                                g.this.f871b.a(iVar);
                            } catch (IOException e) {
                                b.a.h.f.c().a(4, "Http2Connection.Listener failure for " + g.this.d, e);
                                try {
                                    iVar.a(b.a.f.b.PROTOCOL_ERROR);
                                } catch (IOException unused) {
                                }
                            }
                        }
                    });
                    return;
                }
                iVarA.a(list);
                if (z) {
                    iVarA.l();
                }
            }
        }

        @Override // b.a.f.h.b
        public void a(int i, b.a.f.b bVar) {
            if (g.this.c(i)) {
                g.this.c(i, bVar);
                return;
            }
            i iVarB = g.this.b(i);
            if (iVarB != null) {
                iVarB.c(bVar);
            }
        }

        @Override // b.a.f.h.b
        public void a(boolean z, m mVar) {
            i[] iVarArr;
            long j;
            int i;
            synchronized (g.this) {
                int iD = g.this.l.d();
                if (z) {
                    g.this.l.a();
                }
                g.this.l.a(mVar);
                a(mVar);
                int iD2 = g.this.l.d();
                iVarArr = null;
                if (iD2 == -1 || iD2 == iD) {
                    j = 0;
                } else {
                    j = iD2 - iD;
                    if (!g.this.m) {
                        g.this.a(j);
                        g.this.m = true;
                    }
                    if (!g.this.f872c.isEmpty()) {
                        iVarArr = (i[]) g.this.f872c.values().toArray(new i[g.this.f872c.size()]);
                    }
                }
                g.t.execute(new b.a.b("OkHttp %s settings", g.this.d) { // from class: b.a.f.g.d.2
                    @Override // b.a.b
                    public void d() {
                        g.this.f871b.a(g.this);
                    }
                });
            }
            if (iVarArr == null || j == 0) {
                return;
            }
            for (i iVar : iVarArr) {
                synchronized (iVar) {
                    iVar.a(j);
                }
            }
        }

        private void a(final m mVar) {
            try {
                g.this.u.execute(new b.a.b("OkHttp %s ACK Settings", new Object[]{g.this.d}) { // from class: b.a.f.g.d.3
                    @Override // b.a.b
                    public void d() {
                        try {
                            g.this.o.a(mVar);
                        } catch (IOException unused) {
                            g.this.j();
                        }
                    }
                });
            } catch (RejectedExecutionException unused) {
            }
        }

        @Override // b.a.f.h.b
        public void a(boolean z, int i, int i2) {
            if (!z) {
                try {
                    g.this.u.execute(g.this.new c(true, i, i2));
                } catch (RejectedExecutionException unused) {
                }
            } else {
                synchronized (g.this) {
                    g.this.w = false;
                    g.this.notifyAll();
                }
            }
        }

        @Override // b.a.f.h.b
        public void a(int i, b.a.f.b bVar, c.f fVar) {
            i[] iVarArr;
            fVar.k();
            synchronized (g.this) {
                iVarArr = (i[]) g.this.f872c.values().toArray(new i[g.this.f872c.size()]);
                g.this.g = true;
            }
            for (i iVar : iVarArr) {
                if (iVar.a() > i && iVar.c()) {
                    iVar.c(b.a.f.b.REFUSED_STREAM);
                    g.this.b(iVar.a());
                }
            }
        }

        @Override // b.a.f.h.b
        public void a(int i, long j) {
            if (i == 0) {
                synchronized (g.this) {
                    g.this.j += j;
                    g.this.notifyAll();
                }
                return;
            }
            i iVarA = g.this.a(i);
            if (iVarA != null) {
                synchronized (iVarA) {
                    iVarA.a(j);
                }
            }
        }

        @Override // b.a.f.h.b
        public void a(int i, int i2, List<b.a.f.c> list) {
            g.this.a(i2, list);
        }
    }

    void a(final int i, final List<b.a.f.c> list) {
        synchronized (this) {
            if (this.q.contains(Integer.valueOf(i))) {
                a(i, b.a.f.b.PROTOCOL_ERROR);
                return;
            }
            this.q.add(Integer.valueOf(i));
            try {
                this.v.execute(new b.a.b("OkHttp %s Push Request[%s]", new Object[]{this.d, Integer.valueOf(i)}) { // from class: b.a.f.g.3
                    @Override // b.a.b
                    public void d() {
                        if (g.this.h.a(i, list)) {
                            try {
                                g.this.o.a(i, b.a.f.b.CANCEL);
                                synchronized (g.this) {
                                    g.this.q.remove(Integer.valueOf(i));
                                }
                            } catch (IOException unused) {
                            }
                        }
                    }
                });
            } catch (RejectedExecutionException unused) {
            }
        }
    }

    void b(final int i, final List<b.a.f.c> list, final boolean z) {
        try {
            this.v.execute(new b.a.b("OkHttp %s Push Headers[%s]", new Object[]{this.d, Integer.valueOf(i)}) { // from class: b.a.f.g.4
                @Override // b.a.b
                public void d() {
                    boolean zA = g.this.h.a(i, list, z);
                    if (zA) {
                        try {
                            g.this.o.a(i, b.a.f.b.CANCEL);
                        } catch (IOException unused) {
                            return;
                        }
                    }
                    if (zA || z) {
                        synchronized (g.this) {
                            g.this.q.remove(Integer.valueOf(i));
                        }
                    }
                }
            });
        } catch (RejectedExecutionException unused) {
        }
    }

    void a(final int i, c.e eVar, final int i2, final boolean z) throws IOException {
        final c.c cVar = new c.c();
        long j = i2;
        eVar.a(j);
        eVar.a(cVar, j);
        if (cVar.b() != j) {
            throw new IOException(cVar.b() + " != " + i2);
        }
        this.v.execute(new b.a.b("OkHttp %s Push Data[%s]", new Object[]{this.d, Integer.valueOf(i)}) { // from class: b.a.f.g.5
            @Override // b.a.b
            public void d() {
                try {
                    boolean zA = g.this.h.a(i, cVar, i2, z);
                    if (zA) {
                        g.this.o.a(i, b.a.f.b.CANCEL);
                    }
                    if (zA || z) {
                        synchronized (g.this) {
                            g.this.q.remove(Integer.valueOf(i));
                        }
                    }
                } catch (IOException unused) {
                }
            }
        });
    }

    void c(final int i, final b.a.f.b bVar) {
        this.v.execute(new b.a.b("OkHttp %s Push Reset[%s]", new Object[]{this.d, Integer.valueOf(i)}) { // from class: b.a.f.g.6
            @Override // b.a.b
            public void d() {
                g.this.h.a(i, bVar);
                synchronized (g.this) {
                    g.this.q.remove(Integer.valueOf(i));
                }
            }
        });
    }
}
