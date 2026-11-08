package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1;

import android.os.SystemClock;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.core.internal.view.SupportMenu;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.h;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class g implements Closeable {

    /* renamed from: a, reason: collision with root package name */
    final boolean f1599a;

    /* renamed from: b, reason: collision with root package name */
    final i f1600b;
    final String d;
    int e;
    int f;
    boolean g;
    private final ExecutorService h;
    private Map<Integer, l> i;
    final m j;
    long l;
    final Socket p;
    final bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.j q;
    final j r;
    static final /* synthetic */ boolean u = !g.class.desiredAssertionStatus();
    static final ExecutorService t = new ThreadPoolExecutor(0, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, 60, TimeUnit.SECONDS, new SynchronousQueue(), bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a("OkHttp Http2Connection", true));

    /* renamed from: c, reason: collision with root package name */
    final Map<Integer, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.i> f1601c = new LinkedHashMap();
    long k = 0;
    n m = new n();
    final n n = new n();
    boolean o = false;
    final Set<Integer> s = new LinkedHashSet();

    class a extends bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.b {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f1602b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b f1603c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str, Object[] objArr, int i, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b bVar) {
            super(str, objArr);
            this.f1602b = i;
            this.f1603c = bVar;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.b
        public void b() {
            try {
                g.this.b(this.f1602b, this.f1603c);
            } catch (IOException unused) {
            }
        }
    }

    class b extends bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.b {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f1604b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ long f1605c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(String str, Object[] objArr, int i, long j) {
            super(str, objArr);
            this.f1604b = i;
            this.f1605c = j;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.b
        public void b() {
            try {
                g.this.q.a(this.f1604b, this.f1605c);
            } catch (IOException unused) {
            }
        }
    }

    class c extends bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.b {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f1606b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f1607c;
        final /* synthetic */ int d;
        final /* synthetic */ l e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(String str, Object[] objArr, boolean z, int i, int i2, l lVar) {
            super(str, objArr);
            this.f1606b = z;
            this.f1607c = i;
            this.d = i2;
            this.e = lVar;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.b
        public void b() {
            try {
                g.this.a(this.f1606b, this.f1607c, this.d, this.e);
            } catch (IOException unused) {
            }
        }
    }

    class d extends bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.b {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f1608b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ List f1609c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(String str, Object[] objArr, int i, List list) {
            super(str, objArr);
            this.f1608b = i;
            this.f1609c = list;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.b
        public void b() {
            if (g.this.j.a(this.f1608b, this.f1609c)) {
                try {
                    g.this.q.a(this.f1608b, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b.CANCEL);
                    synchronized (g.this) {
                        g.this.s.remove(Integer.valueOf(this.f1608b));
                    }
                } catch (IOException unused) {
                }
            }
        }
    }

    class e extends bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.b {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f1610b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ List f1611c;
        final /* synthetic */ boolean d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(String str, Object[] objArr, int i, List list, boolean z) {
            super(str, objArr);
            this.f1610b = i;
            this.f1611c = list;
            this.d = z;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.b
        public void b() {
            boolean zA = g.this.j.a(this.f1610b, this.f1611c, this.d);
            if (zA) {
                try {
                    g.this.q.a(this.f1610b, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b.CANCEL);
                } catch (IOException unused) {
                    return;
                }
            }
            if (zA || this.d) {
                synchronized (g.this) {
                    g.this.s.remove(Integer.valueOf(this.f1610b));
                }
            }
        }
    }

    class f extends bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.b {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f1612b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c f1613c;
        final /* synthetic */ int d;
        final /* synthetic */ boolean e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        f(String str, Object[] objArr, int i, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c cVar, int i2, boolean z) {
            super(str, objArr);
            this.f1612b = i;
            this.f1613c = cVar;
            this.d = i2;
            this.e = z;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.b
        public void b() {
            try {
                boolean zA = g.this.j.a(this.f1612b, this.f1613c, this.d, this.e);
                if (zA) {
                    g.this.q.a(this.f1612b, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b.CANCEL);
                }
                if (zA || this.e) {
                    synchronized (g.this) {
                        g.this.s.remove(Integer.valueOf(this.f1612b));
                    }
                }
            } catch (IOException unused) {
            }
        }
    }

    /* renamed from: bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.g$g, reason: collision with other inner class name */
    class C0053g extends bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.b {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f1614b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b f1615c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C0053g(String str, Object[] objArr, int i, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b bVar) {
            super(str, objArr);
            this.f1614b = i;
            this.f1615c = bVar;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.b
        public void b() {
            g.this.j.a(this.f1614b, this.f1615c);
            synchronized (g.this) {
                g.this.s.remove(Integer.valueOf(this.f1614b));
            }
        }
    }

    public static abstract class i {

        /* renamed from: a, reason: collision with root package name */
        public static final i f1619a = new a();

        static class a extends i {
            a() {
            }

            @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.g.i
            public void a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.i iVar) throws IOException {
                iVar.a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b.REFUSED_STREAM);
            }
        }

        public void a(g gVar) {
        }

        public abstract void a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.i iVar) throws IOException;
    }

    g(h hVar) {
        this.j = hVar.f;
        boolean z = hVar.g;
        this.f1599a = z;
        this.f1600b = hVar.e;
        this.f = z ? 1 : 2;
        if (hVar.g) {
            this.f += 2;
        }
        boolean z2 = hVar.g;
        if (hVar.g) {
            this.m.a(7, 16777216);
        }
        this.d = hVar.f1617b;
        this.h = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a("OkHttp %s Push Observer", this.d), true));
        this.n.a(7, SupportMenu.USER_MASK);
        this.n.a(5, 16384);
        this.l = this.n.c();
        this.p = hVar.f1616a;
        this.q = new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.j(hVar.d, this.f1599a);
        this.r = new j(new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.h(hVar.f1618c, this.f1599a));
    }

    private bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.i b(int i2, List<bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.c> list, boolean z) throws IOException {
        int i3;
        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.i iVar;
        boolean z2;
        boolean z3 = !z;
        synchronized (this.q) {
            synchronized (this) {
                if (this.g) {
                    throw new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.a();
                }
                i3 = this.f;
                this.f += 2;
                iVar = new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.i(i3, this, z3, false, list);
                z2 = !z || this.l == 0 || iVar.f1634b == 0;
                if (iVar.g()) {
                    this.f1601c.put(Integer.valueOf(i3), iVar);
                }
            }
            if (i2 == 0) {
                this.q.a(z3, i3, i2, list);
            } else {
                if (this.f1599a) {
                    throw new IllegalArgumentException("client streams shouldn't have associated stream IDs");
                }
                this.q.a(i2, i3, list);
            }
        }
        if (z2) {
            this.q.flush();
        }
        return iVar;
    }

    synchronized bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.i a(int i2) {
        return this.f1601c.get(Integer.valueOf(i2));
    }

    boolean b(int i2) {
        return i2 != 0 && (i2 & 1) == 0;
    }

    void c(int i2, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b bVar) {
        t.execute(new a("OkHttp %s stream %d", new Object[]{this.d, Integer.valueOf(i2)}, i2, bVar));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b.NO_ERROR, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b.CANCEL);
    }

    synchronized bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.i d(int i2) {
        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.i iVarRemove;
        iVarRemove = this.f1601c.remove(Integer.valueOf(i2));
        notifyAll();
        return iVarRemove;
    }

    void f(long j2) {
        this.l += j2;
        if (j2 > 0) {
            notifyAll();
        }
    }

    public void flush() throws IOException {
        this.q.flush();
    }

    public synchronized boolean i() {
        return this.g;
    }

    public synchronized int j() {
        return this.n.b(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
    }

    public void k() throws IOException {
        a(true);
    }

    public bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.i a(List<bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.c> list, boolean z) throws IOException {
        return b(0, list, z);
    }

    synchronized l c(int i2) {
        Map<Integer, l> map;
        map = this.i;
        return map != null ? map.remove(Integer.valueOf(i2)) : null;
    }

    public void a(int i2, boolean z, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c cVar, long j2) throws IOException {
        int iMin;
        long j3;
        if (j2 == 0) {
            this.q.a(z, i2, cVar, 0);
            return;
        }
        while (j2 > 0) {
            synchronized (this) {
                while (this.l <= 0) {
                    try {
                        if (this.f1601c.containsKey(Integer.valueOf(i2))) {
                            wait();
                        } else {
                            throw new IOException("stream closed");
                        }
                    } catch (InterruptedException unused) {
                        throw new InterruptedIOException();
                    }
                }
                iMin = Math.min((int) Math.min(j2, this.l), this.q.j());
                j3 = iMin;
                this.l -= j3;
            }
            j2 -= j3;
            this.q.a(z && j2 == 0, i2, cVar, iMin);
        }
    }

    public static class h {

        /* renamed from: a, reason: collision with root package name */
        Socket f1616a;

        /* renamed from: b, reason: collision with root package name */
        String f1617b;

        /* renamed from: c, reason: collision with root package name */
        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e f1618c;
        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.d d;
        i e = i.f1619a;
        m f = m.f1654a;
        boolean g;

        public h(boolean z) {
            this.g = z;
        }

        public h a(Socket socket, String str, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e eVar, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.d dVar) {
            this.f1616a = socket;
            this.f1617b = str;
            this.f1618c = eVar;
            this.d = dVar;
            return this;
        }

        public h a(i iVar) {
            this.e = iVar;
            return this;
        }

        public g a() {
            return new g(this);
        }
    }

    class j extends bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.b implements h.b {

        /* renamed from: b, reason: collision with root package name */
        final bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.h f1620b;

        class a extends bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.b {

            /* renamed from: b, reason: collision with root package name */
            final /* synthetic */ bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.i f1622b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(String str, Object[] objArr, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.i iVar) {
                super(str, objArr);
                this.f1622b = iVar;
            }

            @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.b
            public void b() {
                try {
                    g.this.f1600b.a(this.f1622b);
                } catch (IOException e) {
                    bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_try19.e.b().a(4, "Http2Connection.Listener failure for " + g.this.d, e);
                    try {
                        this.f1622b.a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b.PROTOCOL_ERROR);
                    } catch (IOException unused) {
                    }
                }
            }
        }

        class b extends bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.b {
            b(String str, Object... objArr) {
                super(str, objArr);
            }

            @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.b
            public void b() {
                g gVar = g.this;
                gVar.f1600b.a(gVar);
            }
        }

        class c extends bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.b {

            /* renamed from: b, reason: collision with root package name */
            final /* synthetic */ n f1625b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            c(String str, Object[] objArr, n nVar) {
                super(str, objArr);
                this.f1625b = nVar;
            }

            @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.b
            public void b() {
                try {
                    g.this.q.a(this.f1625b);
                } catch (IOException unused) {
                }
            }
        }

        j(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.h hVar) {
            super("OkHttp %s", g.this.d);
            this.f1620b = hVar;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.h.b
        public void a() {
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.h.b
        public void a(int i, int i2, int i3, boolean z) {
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.h.b
        public void a(boolean z, int i, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e eVar, int i2) throws IOException {
            if (g.this.b(i)) {
                g.this.a(i, eVar, i2, z);
                return;
            }
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.i iVarA = g.this.a(i);
            if (iVarA == null) {
                g.this.c(i, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b.PROTOCOL_ERROR);
                eVar.skip(i2);
            } else {
                iVarA.a(eVar, i2);
                if (z) {
                    iVarA.i();
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.b
        protected void b() throws Throwable {
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b bVar;
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b bVar2;
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b bVar3 = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b.INTERNAL_ERROR;
            try {
                try {
                    try {
                        this.f1620b.a(this);
                        while (this.f1620b.a(false, (h.b) this)) {
                        }
                        bVar2 = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b.NO_ERROR;
                        try {
                            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b bVar4 = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b.CANCEL;
                            g gVar = g.this;
                            gVar.a(bVar2, bVar4);
                            bVar3 = gVar;
                        } catch (IOException unused) {
                            bVar = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b.PROTOCOL_ERROR;
                            try {
                                bVar2 = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b.PROTOCOL_ERROR;
                                g gVar2 = g.this;
                                gVar2.a(bVar, bVar2);
                                bVar3 = gVar2;
                                bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(this.f1620b);
                            } catch (Throwable th) {
                                th = th;
                                try {
                                    g.this.a(bVar, bVar3);
                                } catch (IOException unused2) {
                                }
                                bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(this.f1620b);
                                throw th;
                            }
                        }
                    } catch (Throwable th2) {
                        bVar = bVar2;
                        th = th2;
                        g.this.a(bVar, bVar3);
                        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(this.f1620b);
                        throw th;
                    }
                } catch (IOException unused3) {
                } catch (Throwable th3) {
                    th = th3;
                    bVar = bVar3;
                    g.this.a(bVar, bVar3);
                    bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(this.f1620b);
                    throw th;
                }
            } catch (IOException unused4) {
            }
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(this.f1620b);
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.h.b
        public void a(boolean z, int i, int i2, List<bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.c> list) {
            if (g.this.b(i)) {
                g.this.a(i, list, z);
                return;
            }
            synchronized (g.this) {
                bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.i iVarA = g.this.a(i);
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
                    bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.i iVar = new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.i(i, g.this, false, z, list);
                    g.this.e = i;
                    g.this.f1601c.put(Integer.valueOf(i), iVar);
                    g.t.execute(new a("OkHttp %s stream %d", new Object[]{g.this.d, Integer.valueOf(i)}, iVar));
                    return;
                }
                iVarA.a(list);
                if (z) {
                    iVarA.i();
                }
            }
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.h.b
        public void a(int i, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b bVar) {
            if (g.this.b(i)) {
                g.this.a(i, bVar);
                return;
            }
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.i iVarD = g.this.d(i);
            if (iVarD != null) {
                iVarD.c(bVar);
            }
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.h.b
        public void a(boolean z, n nVar) {
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.i[] iVarArr;
            long j;
            int i;
            synchronized (g.this) {
                int iC = g.this.n.c();
                if (z) {
                    g.this.n.a();
                }
                g.this.n.a(nVar);
                a(nVar);
                int iC2 = g.this.n.c();
                iVarArr = null;
                if (iC2 == -1 || iC2 == iC) {
                    j = 0;
                } else {
                    j = iC2 - iC;
                    if (!g.this.o) {
                        g.this.f(j);
                        g.this.o = true;
                    }
                    if (!g.this.f1601c.isEmpty()) {
                        iVarArr = (bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.i[]) g.this.f1601c.values().toArray(new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.i[g.this.f1601c.size()]);
                    }
                }
                g.t.execute(new b("OkHttp %s settings", g.this.d));
            }
            if (iVarArr == null || j == 0) {
                return;
            }
            for (bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.i iVar : iVarArr) {
                synchronized (iVar) {
                    iVar.a(j);
                }
            }
        }

        private void a(n nVar) {
            g.t.execute(new c("OkHttp %s ACK Settings", new Object[]{g.this.d}, nVar));
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.h.b
        public void a(boolean z, int i, int i2) {
            if (z) {
                l lVarC = g.this.c(i);
                if (lVarC != null) {
                    lVarC.b();
                    return;
                }
                return;
            }
            g.this.b(true, i, i2, null);
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.h.b
        public void a(int i, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b bVar, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.f fVar) {
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.i[] iVarArr;
            fVar.e();
            synchronized (g.this) {
                iVarArr = (bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.i[]) g.this.f1601c.values().toArray(new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.i[g.this.f1601c.size()]);
                g.this.g = true;
            }
            for (bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.i iVar : iVarArr) {
                if (iVar.c() > i && iVar.f()) {
                    iVar.c(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b.REFUSED_STREAM);
                    g.this.d(iVar.c());
                }
            }
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.h.b
        public void a(int i, long j) {
            if (i == 0) {
                synchronized (g.this) {
                    g.this.l += j;
                    g.this.notifyAll();
                }
                return;
            }
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.i iVarA = g.this.a(i);
            if (iVarA != null) {
                synchronized (iVarA) {
                    iVarA.a(j);
                }
            }
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.h.b
        public void a(int i, int i2, List<bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.c> list) {
            g.this.a(i2, list);
        }
    }

    void a(int i2, long j2) {
        t.execute(new b("OkHttp Window Update %s stream %d", new Object[]{this.d, Integer.valueOf(i2)}, i2, j2));
    }

    void a(boolean z, int i2, int i3, l lVar) throws IOException {
        synchronized (this.q) {
            if (lVar != null) {
                try {
                    lVar.c();
                } catch (Throwable th) {
                    throw th;
                }
            }
            this.q.a(z, i2, i3);
        }
    }

    void b(int i2, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b bVar) throws IOException {
        this.q.a(i2, bVar);
    }

    void b(boolean z, int i2, int i3, l lVar) {
        t.execute(new c("OkHttp %s ping %08x%08x", new Object[]{this.d, Integer.valueOf(i2), Integer.valueOf(i3)}, z, i2, i3, lVar));
    }

    public void a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b bVar) throws IOException {
        synchronized (this.q) {
            synchronized (this) {
                if (this.g) {
                    return;
                }
                this.g = true;
                this.q.a(this.e, bVar, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.f1676a);
            }
        }
    }

    void a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b bVar, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b bVar2) throws IOException {
        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.i[] iVarArr;
        if (!u && Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        l[] lVarArr = null;
        try {
            a(bVar);
            e = null;
        } catch (IOException e2) {
            e = e2;
        }
        synchronized (this) {
            if (this.f1601c.isEmpty()) {
                iVarArr = null;
            } else {
                iVarArr = (bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.i[]) this.f1601c.values().toArray(new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.i[this.f1601c.size()]);
                this.f1601c.clear();
            }
            if (this.i != null) {
                l[] lVarArr2 = (l[]) this.i.values().toArray(new l[this.i.size()]);
                this.i = null;
                lVarArr = lVarArr2;
            }
        }
        if (iVarArr != null) {
            IOException iOException = e;
            for (bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.i iVar : iVarArr) {
                try {
                    iVar.a(bVar2);
                } catch (IOException e3) {
                    if (iOException != null) {
                        iOException = e3;
                    }
                }
            }
            e = iOException;
        }
        if (lVarArr != null) {
            for (l lVar : lVarArr) {
                lVar.a();
            }
        }
        try {
            this.q.close();
        } catch (IOException e4) {
            if (e == null) {
                e = e4;
            }
        }
        try {
            this.p.close();
        } catch (IOException e5) {
            e = e5;
        }
        if (e != null) {
            throw e;
        }
    }

    void a(boolean z) throws IOException {
        if (z) {
            this.q.i();
            this.q.b(this.m);
            if (this.m.c() != 65535) {
                this.q.a(0, r5 - SupportMenu.USER_MASK);
            }
        }
        Thread thread = new Thread(this.r);
        thread.setName("tt_pangle_thread_http2_connection" + SystemClock.uptimeMillis());
        thread.start();
    }

    void a(int i2, List<bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.c> list) {
        synchronized (this) {
            if (this.s.contains(Integer.valueOf(i2))) {
                c(i2, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b.PROTOCOL_ERROR);
            } else {
                this.s.add(Integer.valueOf(i2));
                this.h.execute(new d("OkHttp %s Push Request[%s]", new Object[]{this.d, Integer.valueOf(i2)}, i2, list));
            }
        }
    }

    void a(int i2, List<bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.c> list, boolean z) {
        this.h.execute(new e("OkHttp %s Push Headers[%s]", new Object[]{this.d, Integer.valueOf(i2)}, i2, list, z));
    }

    void a(int i2, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e eVar, int i3, boolean z) throws IOException {
        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c cVar = new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c();
        long j2 = i3;
        eVar.a(j2);
        eVar.a(cVar, j2);
        if (cVar.o() == j2) {
            this.h.execute(new f("OkHttp %s Push Data[%s]", new Object[]{this.d, Integer.valueOf(i2)}, i2, cVar, i3, z));
            return;
        }
        throw new IOException(cVar.o() + " != " + i3);
    }

    void a(int i2, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b bVar) {
        this.h.execute(new C0053g("OkHttp %s Push Reset[%s]", new Object[]{this.d, Integer.valueOf(i2)}, i2, bVar));
    }
}
