package b.a.c;

import androidx.appcompat.widget.ActivityChooserView;
import anet.channel.util.HttpConstant;
import b.a.f.g;
import b.a.f.i;
import b.a.k.a;
import b.aa;
import b.ac;
import b.ae;
import b.ag;
import b.j;
import b.k;
import b.l;
import b.r;
import b.t;
import b.v;
import b.w;
import b.z;
import c.p;
import c.y;
import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class c extends g.b implements j {
    private static final String g = "throw with null exception";
    private static final int h = 21;

    /* renamed from: a, reason: collision with root package name */
    public boolean f797a;

    /* renamed from: b, reason: collision with root package name */
    public int f798b;

    /* renamed from: c, reason: collision with root package name */
    public int f799c = 1;
    public final List<Reference<g>> d = new ArrayList();
    public long e = Long.MAX_VALUE;
    private final k i;
    private final ag j;
    private Socket k;
    private Socket l;
    private t m;
    private aa n;
    private b.a.f.g o;
    private c.e p;
    private c.d q;

    public c(k kVar, ag agVar) {
        this.i = kVar;
        this.j = agVar;
    }

    public static c a(k kVar, ag agVar, Socket socket, long j) {
        c cVar = new c(kVar, agVar);
        cVar.l = socket;
        cVar.e = j;
        return cVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00e2 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0131 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(int r17, int r18, int r19, int r20, boolean r21, b.e r22, b.r r23) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 314
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.c.c.a(int, int, int, int, boolean, b.e, b.r):void");
    }

    private void a(int i, int i2, int i3, b.e eVar, r rVar) throws IOException {
        ac acVarG = g();
        v vVarA = acVarG.a();
        for (int i4 = 0; i4 < 21; i4++) {
            a(i, i2, eVar, rVar);
            acVarG = a(i2, i3, acVarG, vVarA);
            if (acVarG == null) {
                return;
            }
            b.a.c.a(this.k);
            this.k = null;
            this.q = null;
            this.p = null;
            rVar.a(eVar, this.j.c(), this.j.b(), null);
        }
    }

    private void a(int i, int i2, b.e eVar, r rVar) throws IOException {
        Socket socketCreateSocket;
        Proxy proxyB = this.j.b();
        b.a aVarA = this.j.a();
        if (proxyB.type() == Proxy.Type.DIRECT || proxyB.type() == Proxy.Type.HTTP) {
            socketCreateSocket = aVarA.c().createSocket();
        } else {
            socketCreateSocket = new Socket(proxyB);
        }
        this.k = socketCreateSocket;
        rVar.a(eVar, this.j.c(), proxyB);
        this.k.setSoTimeout(i2);
        try {
            b.a.h.f.c().a(this.k, this.j.c(), i);
            try {
                this.p = p.a(p.b(this.k));
                this.q = p.a(p.a(this.k));
            } catch (NullPointerException e) {
                if (g.equals(e.getMessage())) {
                    throw new IOException(e);
                }
            }
        } catch (ConnectException e2) {
            ConnectException connectException = new ConnectException("Failed to connect to " + this.j.c());
            connectException.initCause(e2);
            throw connectException;
        }
    }

    private void a(b bVar, int i, b.e eVar, r rVar) throws Throwable {
        if (this.j.a().i() == null) {
            this.n = aa.HTTP_1_1;
            this.l = this.k;
            return;
        }
        rVar.b(eVar);
        a(bVar);
        rVar.a(eVar, this.m);
        if (this.n == aa.HTTP_2) {
            this.l.setSoTimeout(0);
            this.o = new g.a(true).a(this.l, this.j.a().a().i(), this.p, this.q).a(this).a(i).a();
            this.o.g();
        }
    }

    private void a(b bVar) throws Throwable {
        SSLSocket sSLSocket;
        aa aaVarA;
        b.a aVarA = this.j.a();
        try {
            try {
                sSLSocket = (SSLSocket) aVarA.i().createSocket(this.k, aVarA.a().i(), aVarA.a().j(), true);
            } catch (AssertionError e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
            sSLSocket = null;
        }
        try {
            l lVarA = bVar.a(sSLSocket);
            if (lVarA.d()) {
                b.a.h.f.c().a(sSLSocket, aVarA.a().i(), aVarA.e());
            }
            sSLSocket.startHandshake();
            SSLSession session = sSLSocket.getSession();
            if (!a(session)) {
                throw new IOException("a valid ssl session was not established");
            }
            t tVarA = t.a(session);
            if (!aVarA.j().verify(aVarA.a().i(), session)) {
                X509Certificate x509Certificate = (X509Certificate) tVarA.c().get(0);
                throw new SSLPeerUnverifiedException("Hostname " + aVarA.a().i() + " not verified:\n    certificate: " + b.g.a((Certificate) x509Certificate) + "\n    DN: " + x509Certificate.getSubjectDN().getName() + "\n    subjectAltNames: " + b.a.j.e.a(x509Certificate));
            }
            aVarA.k().a(aVarA.a().i(), tVarA.c());
            String strA = lVarA.d() ? b.a.h.f.c().a(sSLSocket) : null;
            this.l = sSLSocket;
            this.p = p.a(p.b(this.l));
            this.q = p.a(p.a(this.l));
            this.m = tVarA;
            if (strA != null) {
                aaVarA = aa.a(strA);
            } else {
                aaVarA = aa.HTTP_1_1;
            }
            this.n = aaVarA;
            if (sSLSocket != null) {
                b.a.h.f.c().b(sSLSocket);
            }
        } catch (AssertionError e2) {
            e = e2;
            if (!b.a.c.a(e)) {
                throw e;
            }
            throw new IOException(e);
        } catch (Throwable th2) {
            th = th2;
            if (sSLSocket != null) {
                b.a.h.f.c().b(sSLSocket);
            }
            b.a.c.a((Socket) sSLSocket);
            throw th;
        }
    }

    private boolean a(SSLSession sSLSession) {
        return ("NONE".equals(sSLSession.getProtocol()) || "SSL_NULL_WITH_NULL_NULL".equals(sSLSession.getCipherSuite())) ? false : true;
    }

    private ac a(int i, int i2, ac acVar, v vVar) throws IOException {
        String str = "CONNECT " + b.a.c.a(vVar, true) + " HTTP/1.1";
        while (true) {
            b.a.e.a aVar = new b.a.e.a(null, null, this.p, this.q);
            this.p.a().a(i, TimeUnit.MILLISECONDS);
            this.q.a().a(i2, TimeUnit.MILLISECONDS);
            aVar.a(acVar.c(), str);
            aVar.b();
            ae aeVarA = aVar.a(false).a(acVar).a();
            long jA = b.a.d.e.a(aeVarA);
            if (jA == -1) {
                jA = 0;
            }
            y yVarB = aVar.b(jA);
            b.a.c.b(yVarB, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, TimeUnit.MILLISECONDS);
            yVarB.close();
            int iC = aeVarA.c();
            if (iC == 200) {
                if (this.p.c().g() && this.q.c().g()) {
                    return null;
                }
                throw new IOException("TLS tunnel buffered too many bytes!");
            }
            if (iC == 407) {
                ac acVarA = this.j.a().d().a(this.j, aeVarA);
                if (acVarA == null) {
                    throw new IOException("Failed to authenticate with proxy");
                }
                if ("close".equalsIgnoreCase(aeVarA.b("Connection"))) {
                    return acVarA;
                }
                acVar = acVarA;
            } else {
                throw new IOException("Unexpected response code for CONNECT: " + aeVarA.c());
            }
        }
    }

    private ac g() {
        return new ac.a().a(this.j.a().a()).a(HttpConstant.HOST, b.a.c.a(this.j.a().a(), true)).a("Proxy-Connection", "Keep-Alive").a("User-Agent", b.a.d.a()).d();
    }

    public boolean a(b.a aVar, @Nullable ag agVar) {
        if (this.d.size() >= this.f799c || this.f797a || !b.a.a.f750a.a(this.j.a(), aVar)) {
            return false;
        }
        if (aVar.a().i().equals(a().a().a().i())) {
            return true;
        }
        if (this.o == null || agVar == null || agVar.b().type() != Proxy.Type.DIRECT || this.j.b().type() != Proxy.Type.DIRECT || !this.j.c().equals(agVar.c()) || agVar.a().j() != b.a.j.e.f959a || !a(aVar.a())) {
            return false;
        }
        try {
            aVar.k().a(aVar.a().i(), c().c());
            return true;
        } catch (SSLPeerUnverifiedException unused) {
            return false;
        }
    }

    public boolean a(v vVar) {
        if (vVar.j() != this.j.a().a().j()) {
            return false;
        }
        if (vVar.i().equals(this.j.a().a().i())) {
            return true;
        }
        return this.m != null && b.a.j.e.f959a.a(vVar.i(), (X509Certificate) this.m.c().get(0));
    }

    public b.a.d.c a(z zVar, w.a aVar, g gVar) throws SocketException {
        if (this.o != null) {
            return new b.a.f.f(zVar, aVar, gVar, this.o);
        }
        this.l.setSoTimeout(aVar.e());
        this.p.a().a(aVar.e(), TimeUnit.MILLISECONDS);
        this.q.a().a(aVar.f(), TimeUnit.MILLISECONDS);
        return new b.a.e.a(zVar, gVar, this.p, this.q);
    }

    public a.e a(final g gVar) {
        return new a.e(true, this.p, this.q) { // from class: b.a.c.c.1
            @Override // java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                gVar.a(true, gVar.a(), -1L, null);
            }
        };
    }

    @Override // b.j
    public ag a() {
        return this.j;
    }

    public void e() throws IOException {
        b.a.c.a(this.k);
    }

    @Override // b.j
    public Socket b() {
        return this.l;
    }

    public boolean a(boolean z) throws SocketException {
        if (this.l.isClosed() || this.l.isInputShutdown() || this.l.isOutputShutdown()) {
            return false;
        }
        if (this.o != null) {
            return !this.o.h();
        }
        if (z) {
            try {
                int soTimeout = this.l.getSoTimeout();
                try {
                    this.l.setSoTimeout(1);
                    return !this.p.g();
                } finally {
                    this.l.setSoTimeout(soTimeout);
                }
            } catch (SocketTimeoutException unused) {
            } catch (IOException unused2) {
                return false;
            }
        }
        return true;
    }

    @Override // b.a.f.g.b
    public void a(i iVar) throws IOException {
        iVar.a(b.a.f.b.REFUSED_STREAM);
    }

    @Override // b.a.f.g.b
    public void a(b.a.f.g gVar) {
        synchronized (this.i) {
            this.f799c = gVar.c();
        }
    }

    @Override // b.j
    public t c() {
        return this.m;
    }

    public boolean f() {
        return this.o != null;
    }

    @Override // b.j
    public aa d() {
        return this.n;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Connection{");
        sb.append(this.j.a().a().i());
        sb.append(":");
        sb.append(this.j.a().a().j());
        sb.append(", proxy=");
        sb.append(this.j.b());
        sb.append(" hostAddress=");
        sb.append(this.j.c());
        sb.append(" cipherSuite=");
        sb.append(this.m != null ? this.m.b() : com.baidu.mobads.sdk.internal.a.f3089a);
        sb.append(" protocol=");
        sb.append(this.n);
        sb.append('}');
        return sb.toString();
    }
}
