package b.a.c;

import b.a.c.f;
import b.a.f.n;
import b.ag;
import b.k;
import b.r;
import b.w;
import b.z;
import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class g {
    static final /* synthetic */ boolean d = !g.class.desiredAssertionStatus();

    /* renamed from: a, reason: collision with root package name */
    public final b.a f810a;

    /* renamed from: b, reason: collision with root package name */
    public final b.e f811b;

    /* renamed from: c, reason: collision with root package name */
    public final r f812c;
    private f.a e;
    private ag f;
    private final k g;
    private final Object h;
    private final f i;
    private int j;
    private c k;
    private boolean l;
    private boolean m;
    private boolean n;
    private b.a.d.c o;

    public g(k kVar, b.a aVar, b.e eVar, r rVar, Object obj) {
        this.g = kVar;
        this.f810a = aVar;
        this.f811b = eVar;
        this.f812c = rVar;
        this.i = new f(aVar, i(), eVar, rVar);
        this.h = obj;
    }

    public b.a.d.c a(z zVar, w.a aVar, boolean z) {
        try {
            b.a.d.c cVarA = a(aVar.d(), aVar.e(), aVar.f(), zVar.d(), zVar.t(), z).a(zVar, aVar, this);
            synchronized (this.g) {
                this.o = cVarA;
            }
            return cVarA;
        } catch (IOException e) {
            throw new e(e);
        }
    }

    private c a(int i, int i2, int i3, int i4, boolean z, boolean z2) throws Throwable {
        while (true) {
            c cVarA = a(i, i2, i3, i4, z);
            synchronized (this.g) {
                if (cVarA.f798b == 0) {
                    return cVarA;
                }
                if (cVarA.a(z2)) {
                    return cVarA;
                }
                e();
            }
        }
    }

    private c a(int i, int i2, int i3, int i4, boolean z) throws Throwable {
        Socket socketH;
        Socket socketA;
        c cVar;
        c cVar2;
        c cVar3;
        ag agVarB;
        boolean z2;
        boolean z3;
        synchronized (this.g) {
            if (this.m) {
                throw new IllegalStateException("released");
            }
            if (this.o != null) {
                throw new IllegalStateException("codec != null");
            }
            if (this.n) {
                throw new IOException("Canceled");
            }
            c cVar4 = this.k;
            socketH = h();
            socketA = null;
            if (this.k != null) {
                cVar2 = this.k;
                cVar = null;
            } else {
                cVar = cVar4;
                cVar2 = null;
            }
            if (!this.l) {
                cVar = null;
            }
            if (cVar2 == null) {
                b.a.a.f750a.a(this.g, this.f810a, this, null);
                if (this.k != null) {
                    cVar3 = this.k;
                    agVarB = null;
                    z2 = true;
                } else {
                    agVarB = this.f;
                    cVar3 = cVar2;
                }
            } else {
                cVar3 = cVar2;
                agVarB = null;
            }
            z2 = false;
        }
        b.a.c.a(socketH);
        if (cVar != null) {
            this.f812c.b(this.f811b, cVar);
        }
        if (z2) {
            this.f812c.a(this.f811b, cVar3);
        }
        if (cVar3 != null) {
            return cVar3;
        }
        if (agVarB != null || (this.e != null && this.e.a())) {
            z3 = false;
        } else {
            this.e = this.i.b();
            z3 = true;
        }
        synchronized (this.g) {
            if (this.n) {
                throw new IOException("Canceled");
            }
            if (z3) {
                List<ag> listC = this.e.c();
                int size = listC.size();
                int i5 = 0;
                while (true) {
                    if (i5 >= size) {
                        break;
                    }
                    ag agVar = listC.get(i5);
                    b.a.a.f750a.a(this.g, this.f810a, this, agVar);
                    if (this.k != null) {
                        cVar3 = this.k;
                        this.f = agVar;
                        z2 = true;
                        break;
                    }
                    i5++;
                }
            }
            if (!z2) {
                if (agVarB == null) {
                    agVarB = this.e.b();
                }
                this.f = agVarB;
                this.j = 0;
                cVar3 = new c(this.g, agVarB);
                a(cVar3, false);
            }
        }
        if (z2) {
            this.f812c.a(this.f811b, cVar3);
            return cVar3;
        }
        cVar3.a(i, i2, i3, i4, z, this.f811b, this.f812c);
        i().b(cVar3.a());
        synchronized (this.g) {
            this.l = true;
            b.a.a.f750a.b(this.g, cVar3);
            if (cVar3.f()) {
                socketA = b.a.a.f750a.a(this.g, this.f810a, this);
                cVar3 = this.k;
            }
        }
        b.a.c.a(socketA);
        this.f812c.a(this.f811b, cVar3);
        return cVar3;
    }

    private Socket h() {
        if (!d && !Thread.holdsLock(this.g)) {
            throw new AssertionError();
        }
        c cVar = this.k;
        if (cVar == null || !cVar.f797a) {
            return null;
        }
        return a(false, false, true);
    }

    public void a(boolean z, b.a.d.c cVar, long j, IOException iOException) {
        c cVar2;
        Socket socketA;
        boolean z2;
        this.f812c.b(this.f811b, j);
        synchronized (this.g) {
            if (cVar != null) {
                if (cVar == this.o) {
                    if (!z) {
                        this.k.f798b++;
                    }
                    cVar2 = this.k;
                    socketA = a(z, false, true);
                    if (this.k != null) {
                        cVar2 = null;
                    }
                    z2 = this.m;
                }
            }
            throw new IllegalStateException("expected " + this.o + " but was " + cVar);
        }
        b.a.c.a(socketA);
        if (cVar2 != null) {
            this.f812c.b(this.f811b, cVar2);
        }
        if (iOException != null) {
            this.f812c.a(this.f811b, iOException);
        } else if (z2) {
            this.f812c.g(this.f811b);
        }
    }

    public b.a.d.c a() {
        b.a.d.c cVar;
        synchronized (this.g) {
            cVar = this.o;
        }
        return cVar;
    }

    private d i() {
        return b.a.a.f750a.a(this.g);
    }

    public ag b() {
        return this.f;
    }

    public synchronized c c() {
        return this.k;
    }

    public void d() throws IOException {
        c cVar;
        Socket socketA;
        synchronized (this.g) {
            cVar = this.k;
            socketA = a(false, true, false);
            if (this.k != null) {
                cVar = null;
            }
        }
        b.a.c.a(socketA);
        if (cVar != null) {
            this.f812c.b(this.f811b, cVar);
        }
    }

    public void e() throws IOException {
        c cVar;
        Socket socketA;
        synchronized (this.g) {
            cVar = this.k;
            socketA = a(true, false, false);
            if (this.k != null) {
                cVar = null;
            }
        }
        b.a.c.a(socketA);
        if (cVar != null) {
            this.f812c.b(this.f811b, cVar);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x005f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.net.Socket a(boolean r2, boolean r3, boolean r4) {
        /*
            r1 = this;
            boolean r0 = b.a.c.g.d
            if (r0 != 0) goto L13
            b.k r0 = r1.g
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
            r1.o = r0
        L18:
            r4 = 1
            if (r3 == 0) goto L1d
            r1.m = r4
        L1d:
            b.a.c.c r3 = r1.k
            if (r3 == 0) goto L63
            if (r2 == 0) goto L27
            b.a.c.c r2 = r1.k
            r2.f797a = r4
        L27:
            b.a.d.c r2 = r1.o
            if (r2 != 0) goto L63
            boolean r2 = r1.m
            if (r2 != 0) goto L35
            b.a.c.c r2 = r1.k
            boolean r2 = r2.f797a
            if (r2 == 0) goto L63
        L35:
            b.a.c.c r2 = r1.k
            r1.b(r2)
            b.a.c.c r2 = r1.k
            java.util.List<java.lang.ref.Reference<b.a.c.g>> r2 = r2.d
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L5f
            b.a.c.c r2 = r1.k
            long r3 = java.lang.System.nanoTime()
            r2.e = r3
            b.a.a r2 = b.a.a.f750a
            b.k r3 = r1.g
            b.a.c.c r4 = r1.k
            boolean r2 = r2.a(r3, r4)
            if (r2 == 0) goto L5f
            b.a.c.c r2 = r1.k
            java.net.Socket r2 = r2.b()
            goto L60
        L5f:
            r2 = r0
        L60:
            r1.k = r0
            goto L64
        L63:
            r2 = r0
        L64:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.c.g.a(boolean, boolean, boolean):java.net.Socket");
    }

    public void f() throws IOException {
        b.a.d.c cVar;
        c cVar2;
        synchronized (this.g) {
            this.n = true;
            cVar = this.o;
            cVar2 = this.k;
        }
        if (cVar != null) {
            cVar.c();
        } else if (cVar2 != null) {
            cVar2.e();
        }
    }

    public void a(IOException iOException) throws IOException {
        boolean z;
        c cVar;
        Socket socketA;
        synchronized (this.g) {
            if (iOException instanceof n) {
                n nVar = (n) iOException;
                if (nVar.f926a == b.a.f.b.REFUSED_STREAM) {
                    this.j++;
                }
                if (nVar.f926a != b.a.f.b.REFUSED_STREAM || this.j > 1) {
                    this.f = null;
                    z = true;
                }
                z = false;
            } else {
                if (this.k != null && (!this.k.f() || (iOException instanceof b.a.f.a))) {
                    if (this.k.f798b == 0) {
                        if (this.f != null && iOException != null) {
                            this.i.a(this.f, iOException);
                        }
                        this.f = null;
                    }
                    z = true;
                }
                z = false;
            }
            cVar = this.k;
            socketA = a(z, false, true);
            if (this.k != null || !this.l) {
                cVar = null;
            }
        }
        b.a.c.a(socketA);
        if (cVar != null) {
            this.f812c.b(this.f811b, cVar);
        }
    }

    public void a(c cVar, boolean z) {
        if (!d && !Thread.holdsLock(this.g)) {
            throw new AssertionError();
        }
        if (this.k != null) {
            throw new IllegalStateException();
        }
        this.k = cVar;
        this.l = z;
        cVar.d.add(new a(this, this.h));
    }

    private void b(c cVar) {
        int size = cVar.d.size();
        for (int i = 0; i < size; i++) {
            if (cVar.d.get(i).get() == this) {
                cVar.d.remove(i);
                return;
            }
        }
        throw new IllegalStateException();
    }

    public Socket a(c cVar) {
        if (!d && !Thread.holdsLock(this.g)) {
            throw new AssertionError();
        }
        if (this.o != null || this.k.d.size() != 1) {
            throw new IllegalStateException();
        }
        Reference<g> reference = this.k.d.get(0);
        Socket socketA = a(true, false, false);
        this.k = cVar;
        cVar.d.add(reference);
        return socketA;
    }

    public boolean g() {
        return this.f != null || (this.e != null && this.e.a()) || this.i.a();
    }

    public String toString() {
        c cVarC = c();
        return cVarC != null ? cVarC.toString() : this.f810a.toString();
    }

    public static final class a extends WeakReference<g> {

        /* renamed from: a, reason: collision with root package name */
        public final Object f813a;

        a(g gVar, Object obj) {
            super(gVar);
            this.f813a = obj;
        }
    }
}
