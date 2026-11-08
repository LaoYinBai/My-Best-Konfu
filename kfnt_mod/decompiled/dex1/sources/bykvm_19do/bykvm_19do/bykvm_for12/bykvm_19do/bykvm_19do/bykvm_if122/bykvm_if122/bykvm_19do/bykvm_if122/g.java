package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_if122;

import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_if122.f;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.o;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.c0;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.j;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.p;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.t;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.v;
import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class g {
    static final /* synthetic */ boolean o = !g.class.desiredAssertionStatus();

    /* renamed from: a, reason: collision with root package name */
    public final bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.a f1561a;

    /* renamed from: b, reason: collision with root package name */
    private f.a f1562b;

    /* renamed from: c, reason: collision with root package name */
    private c0 f1563c;
    private final j d;
    public final bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.e e;
    public final p f;
    private final Object g;
    private final f h;
    private int i;
    private c j;
    private boolean k;
    private boolean l;
    private boolean m;
    private bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12.c n;

    public static final class a extends WeakReference<g> {

        /* renamed from: a, reason: collision with root package name */
        public final Object f1564a;

        a(g gVar, Object obj) {
            super(gVar);
            this.f1564a = obj;
        }
    }

    public g(j jVar, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.a aVar, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.e eVar, p pVar, Object obj) {
        this.d = jVar;
        this.f1561a = aVar;
        this.e = eVar;
        this.f = pVar;
        this.h = new f(aVar, g(), eVar, pVar);
        this.g = obj;
    }

    private Socket f() {
        if (!o && !Thread.holdsLock(this.d)) {
            throw new AssertionError();
        }
        c cVar = this.j;
        if (cVar == null || !cVar.k) {
            return null;
        }
        return a(false, false, true);
    }

    private d g() {
        return bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.a.f1509a.a(this.d);
    }

    public bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12.c a(v vVar, t.a aVar, boolean z) {
        try {
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12.c cVarA = a(aVar.c(), aVar.d(), aVar.a(), vVar.v(), z).a(vVar, aVar, this);
            synchronized (this.d) {
                this.n = cVarA;
            }
            return cVarA;
        } catch (IOException e) {
            throw new e(e);
        }
    }

    public synchronized c b() {
        return this.j;
    }

    public boolean c() {
        f.a aVar;
        return this.f1563c != null || ((aVar = this.f1562b) != null && aVar.b()) || this.h.a();
    }

    public void d() throws IOException {
        c cVar;
        Socket socketA;
        synchronized (this.d) {
            cVar = this.j;
            socketA = a(true, false, false);
            if (this.j != null) {
                cVar = null;
            }
        }
        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(socketA);
        if (cVar != null) {
            this.f.b(this.e, cVar);
        }
    }

    public void e() throws IOException {
        c cVar;
        Socket socketA;
        synchronized (this.d) {
            cVar = this.j;
            socketA = a(false, true, false);
            if (this.j != null) {
                cVar = null;
            }
        }
        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(socketA);
        if (cVar != null) {
            this.f.b(this.e, cVar);
        }
    }

    public String toString() {
        c cVarB = b();
        return cVarB != null ? cVarB.toString() : this.f1561a.toString();
    }

    private void b(c cVar) {
        int size = cVar.n.size();
        for (int i = 0; i < size; i++) {
            if (cVar.n.get(i).get() == this) {
                cVar.n.remove(i);
                return;
            }
        }
        throw new IllegalStateException();
    }

    private c a(int i, int i2, int i3, boolean z, boolean z2) throws Throwable {
        while (true) {
            c cVarA = a(i, i2, i3, z);
            synchronized (this.d) {
                if (cVarA.l == 0) {
                    return cVarA;
                }
                if (cVarA.a(z2)) {
                    return cVarA;
                }
                d();
            }
        }
    }

    private c a(int i, int i2, int i3, boolean z) throws Throwable {
        Socket socketF;
        Socket socketA;
        c cVar;
        c cVar2;
        c cVar3;
        c0 c0VarC;
        boolean z2;
        boolean z3;
        f.a aVar;
        synchronized (this.d) {
            if (!this.l) {
                if (this.n == null) {
                    if (!this.m) {
                        c cVar4 = this.j;
                        socketF = f();
                        socketA = null;
                        if (this.j != null) {
                            cVar2 = this.j;
                            cVar = null;
                        } else {
                            cVar = cVar4;
                            cVar2 = null;
                        }
                        if (!this.k) {
                            cVar = null;
                        }
                        if (cVar2 == null) {
                            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.a.f1509a.a(this.d, this.f1561a, this, null);
                            if (this.j != null) {
                                cVar3 = this.j;
                                c0VarC = null;
                                z2 = true;
                            } else {
                                c0VarC = this.f1563c;
                                cVar3 = cVar2;
                            }
                        } else {
                            cVar3 = cVar2;
                            c0VarC = null;
                        }
                        z2 = false;
                    } else {
                        throw new IOException("Canceled");
                    }
                } else {
                    throw new IllegalStateException("codec != null");
                }
            } else {
                throw new IllegalStateException("released");
            }
        }
        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(socketF);
        if (cVar != null) {
            this.f.b(this.e, cVar);
        }
        if (z2) {
            this.f.a(this.e, cVar3);
        }
        if (cVar3 != null) {
            return cVar3;
        }
        if (c0VarC != null || ((aVar = this.f1562b) != null && aVar.b())) {
            z3 = false;
        } else {
            this.f1562b = this.h.b();
            z3 = true;
        }
        synchronized (this.d) {
            if (this.m) {
                throw new IOException("Canceled");
            }
            if (z3) {
                List<c0> listA = this.f1562b.a();
                int size = listA.size();
                int i4 = 0;
                while (true) {
                    if (i4 >= size) {
                        break;
                    }
                    c0 c0Var = listA.get(i4);
                    bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.a.f1509a.a(this.d, this.f1561a, this, c0Var);
                    if (this.j != null) {
                        cVar3 = this.j;
                        this.f1563c = c0Var;
                        z2 = true;
                        break;
                    }
                    i4++;
                }
            }
            if (!z2) {
                if (c0VarC == null) {
                    c0VarC = this.f1562b.c();
                }
                this.f1563c = c0VarC;
                this.i = 0;
                cVar3 = new c(this.d, c0VarC);
                a(cVar3, false);
            }
        }
        if (z2) {
            this.f.a(this.e, cVar3);
            return cVar3;
        }
        cVar3.a(i, i2, i3, z, this.e, this.f);
        g().a(cVar3.a());
        synchronized (this.d) {
            this.k = true;
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.a.f1509a.b(this.d, cVar3);
            if (cVar3.c()) {
                socketA = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.a.f1509a.a(this.d, this.f1561a, this);
                cVar3 = this.j;
            }
        }
        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(socketA);
        this.f.a(this.e, cVar3);
        return cVar3;
    }

    public void a(boolean z, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12.c cVar, long j, IOException iOException) throws IOException {
        c cVar2;
        Socket socketA;
        boolean z2;
        this.f.b(this.e, j);
        synchronized (this.d) {
            if (cVar != null) {
                if (cVar == this.n) {
                    if (!z) {
                        this.j.l++;
                    }
                    cVar2 = this.j;
                    socketA = a(z, false, true);
                    if (this.j != null) {
                        cVar2 = null;
                    }
                    z2 = this.l;
                }
            }
            throw new IllegalStateException("expected " + this.n + " but was " + cVar);
        }
        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(socketA);
        if (cVar2 != null) {
            this.f.b(this.e, cVar2);
        }
        if (iOException != null) {
            this.f.a(this.e, iOException);
        } else if (z2) {
            this.f.a(this.e);
        }
    }

    public bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12.c a() {
        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12.c cVar;
        synchronized (this.d) {
            cVar = this.n;
        }
        return cVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.net.Socket a(boolean r2, boolean r3, boolean r4) {
        /*
            r1 = this;
            boolean r0 = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_if122.g.o
            if (r0 != 0) goto L13
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.j r0 = r1.d
            boolean r0 = java.lang.Thread.holdsLock(r0)
            if (r0 == 0) goto Ld
            goto L13
        Ld:
            java.lang.AssertionError r2 = new java.lang.AssertionError
            r2.<init>()
            throw r2
        L13:
            r0 = 0
            if (r4 == 0) goto L18
            r1.n = r0
        L18:
            r4 = 1
            if (r3 == 0) goto L1d
            r1.l = r4
        L1d:
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_if122.c r3 = r1.j
            if (r3 == 0) goto L61
            if (r2 == 0) goto L25
            r3.k = r4
        L25:
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12.c r2 = r1.n
            if (r2 != 0) goto L61
            boolean r2 = r1.l
            if (r2 != 0) goto L33
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_if122.c r2 = r1.j
            boolean r2 = r2.k
            if (r2 == 0) goto L61
        L33:
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_if122.c r2 = r1.j
            r1.b(r2)
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_if122.c r2 = r1.j
            java.util.List<java.lang.ref.Reference<bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_if122.g>> r2 = r2.n
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L5d
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_if122.c r2 = r1.j
            long r3 = java.lang.System.nanoTime()
            r2.o = r3
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.a r2 = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.a.f1509a
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.j r3 = r1.d
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_if122.c r4 = r1.j
            boolean r2 = r2.a(r3, r4)
            if (r2 == 0) goto L5d
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_if122.c r2 = r1.j
            java.net.Socket r2 = r2.d()
            goto L5e
        L5d:
            r2 = r0
        L5e:
            r1.j = r0
            goto L62
        L61:
            r2 = r0
        L62:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_if122.g.a(boolean, boolean, boolean):java.net.Socket");
    }

    public void a(IOException iOException) throws IOException {
        boolean z;
        c cVar;
        Socket socketA;
        synchronized (this.d) {
            if (iOException instanceof o) {
                o oVar = (o) iOException;
                if (oVar.f1657a == bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b.REFUSED_STREAM) {
                    this.i++;
                }
                if (oVar.f1657a != bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.b.REFUSED_STREAM || this.i > 1) {
                    this.f1563c = null;
                    z = true;
                }
                z = false;
            } else {
                if (this.j != null && (!this.j.c() || (iOException instanceof bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1.a))) {
                    if (this.j.l == 0) {
                        if (this.f1563c != null && iOException != null) {
                            this.h.a(this.f1563c, iOException);
                        }
                        this.f1563c = null;
                    }
                    z = true;
                }
                z = false;
            }
            cVar = this.j;
            socketA = a(z, false, true);
            if (this.j != null || !this.k) {
                cVar = null;
            }
        }
        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(socketA);
        if (cVar != null) {
            this.f.b(this.e, cVar);
        }
    }

    public void a(c cVar, boolean z) {
        if (!o && !Thread.holdsLock(this.d)) {
            throw new AssertionError();
        }
        if (this.j == null) {
            this.j = cVar;
            this.k = z;
            cVar.n.add(new a(this, this.g));
            return;
        }
        throw new IllegalStateException();
    }

    public Socket a(c cVar) {
        if (!o && !Thread.holdsLock(this.d)) {
            throw new AssertionError();
        }
        if (this.n == null && this.j.n.size() == 1) {
            Reference<g> reference = this.j.n.get(0);
            Socket socketA = a(true, false, false);
            this.j = cVar;
            cVar.n.add(reference);
            return socketA;
        }
        throw new IllegalStateException();
    }
}
