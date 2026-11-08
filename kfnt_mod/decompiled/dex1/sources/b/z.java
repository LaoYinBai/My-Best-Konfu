package b;

import b.ae;
import b.ai;
import b.e;
import b.r;
import b.u;
import com.bytedance.sdk.adnet.game.http.cb.NetCallback;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class z implements ai.a, e.a, Cloneable {

    /* renamed from: a, reason: collision with root package name */
    static final List<aa> f1300a = b.a.c.a(aa.HTTP_2, aa.HTTP_1_1);

    /* renamed from: b, reason: collision with root package name */
    static final List<l> f1301b = b.a.c.a(l.f1249a, l.f1251c);
    final int A;
    final int B;
    final int C;

    /* renamed from: c, reason: collision with root package name */
    final p f1302c;

    @Nullable
    final Proxy d;
    final List<aa> e;
    final List<l> f;
    final List<w> g;
    final List<w> h;
    final r.a i;
    final ProxySelector j;
    final n k;

    @Nullable
    final c l;

    @Nullable
    final b.a.a.f m;
    final SocketFactory n;

    @Nullable
    final SSLSocketFactory o;

    @Nullable
    final b.a.j.c p;
    final HostnameVerifier q;
    final g r;
    final b s;
    final b t;
    final k u;
    final q v;
    final boolean w;
    final boolean x;
    final boolean y;
    final int z;

    static {
        b.a.a.f750a = new b.a.a() { // from class: b.z.1
            @Override // b.a.a
            public void a(u.a aVar, String str) {
                aVar.a(str);
            }

            @Override // b.a.a
            public void a(u.a aVar, String str, String str2) {
                aVar.b(str, str2);
            }

            @Override // b.a.a
            public void a(a aVar, b.a.a.f fVar) {
                aVar.a(fVar);
            }

            @Override // b.a.a
            public boolean a(k kVar, b.a.c.c cVar) {
                return kVar.b(cVar);
            }

            @Override // b.a.a
            public b.a.c.c a(k kVar, b.a aVar, b.a.c.g gVar, ag agVar) {
                return kVar.a(aVar, gVar, agVar);
            }

            @Override // b.a.a
            public boolean a(b.a aVar, b.a aVar2) {
                return aVar.a(aVar2);
            }

            @Override // b.a.a
            public Socket a(k kVar, b.a aVar, b.a.c.g gVar) {
                return kVar.a(aVar, gVar);
            }

            @Override // b.a.a
            public void b(k kVar, b.a.c.c cVar) {
                kVar.a(cVar);
            }

            @Override // b.a.a
            public b.a.c.d a(k kVar) {
                return kVar.f1246a;
            }

            @Override // b.a.a
            public int a(ae.a aVar) {
                return aVar.f1014c;
            }

            @Override // b.a.a
            public void a(l lVar, SSLSocket sSLSocket, boolean z) {
                lVar.a(sSLSocket, z);
            }

            @Override // b.a.a
            public v a(String str) throws MalformedURLException, UnknownHostException {
                return v.h(str);
            }

            @Override // b.a.a
            public b.a.c.g a(e eVar) {
                return ((ab) eVar).h();
            }

            @Override // b.a.a
            public e a(z zVar, ac acVar) {
                return ab.a(zVar, acVar, true);
            }
        };
    }

    public z() {
        this(new a());
    }

    z(a aVar) throws NoSuchAlgorithmException, KeyStoreException {
        boolean z;
        this.f1302c = aVar.f1303a;
        this.d = aVar.f1304b;
        this.e = aVar.f1305c;
        this.f = aVar.d;
        this.g = b.a.c.a(aVar.e);
        this.h = b.a.c.a(aVar.f);
        this.i = aVar.g;
        this.j = aVar.h;
        this.k = aVar.i;
        this.l = aVar.j;
        this.m = aVar.k;
        this.n = aVar.l;
        Iterator<l> it = this.f.iterator();
        loop0: while (true) {
            while (it.hasNext()) {
                z = z || it.next().a();
            }
        }
        if (aVar.m != null || !z) {
            this.o = aVar.m;
            this.p = aVar.n;
        } else {
            X509TrustManager x509TrustManagerB = B();
            this.o = a(x509TrustManagerB);
            this.p = b.a.j.c.a(x509TrustManagerB);
        }
        this.q = aVar.o;
        this.r = aVar.p.a(this.p);
        this.s = aVar.q;
        this.t = aVar.r;
        this.u = aVar.s;
        this.v = aVar.t;
        this.w = aVar.u;
        this.x = aVar.v;
        this.y = aVar.w;
        this.z = aVar.x;
        this.A = aVar.y;
        this.B = aVar.z;
        this.C = aVar.A;
        if (this.g.contains(null)) {
            throw new IllegalStateException("Null interceptor: " + this.g);
        }
        if (this.h.contains(null)) {
            throw new IllegalStateException("Null network interceptor: " + this.h);
        }
    }

    private X509TrustManager B() throws NoSuchAlgorithmException, KeyStoreException {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
            }
            return (X509TrustManager) trustManagers[0];
        } catch (GeneralSecurityException e) {
            throw b.a.c.a("No System TLS", (Exception) e);
        }
    }

    private SSLSocketFactory a(X509TrustManager x509TrustManager) throws KeyManagementException {
        try {
            SSLContext sSLContextE_ = b.a.h.f.c().e_();
            sSLContextE_.init(null, new TrustManager[]{x509TrustManager}, null);
            return sSLContextE_.getSocketFactory();
        } catch (GeneralSecurityException e) {
            throw b.a.c.a("No System TLS", (Exception) e);
        }
    }

    public int a() {
        return this.z;
    }

    public int b() {
        return this.A;
    }

    public int c() {
        return this.B;
    }

    public int d() {
        return this.C;
    }

    public Proxy e() {
        return this.d;
    }

    public ProxySelector f() {
        return this.j;
    }

    public n g() {
        return this.k;
    }

    public c h() {
        return this.l;
    }

    b.a.a.f i() {
        return this.l != null ? this.l.f1203a : this.m;
    }

    public q j() {
        return this.v;
    }

    public SocketFactory k() {
        return this.n;
    }

    public SSLSocketFactory l() {
        return this.o;
    }

    public HostnameVerifier m() {
        return this.q;
    }

    public g n() {
        return this.r;
    }

    public b o() {
        return this.t;
    }

    public b p() {
        return this.s;
    }

    public k q() {
        return this.u;
    }

    public boolean r() {
        return this.w;
    }

    public boolean s() {
        return this.x;
    }

    public boolean t() {
        return this.y;
    }

    public p u() {
        return this.f1302c;
    }

    public List<aa> v() {
        return this.e;
    }

    public List<l> w() {
        return this.f;
    }

    public List<w> x() {
        return this.g;
    }

    public List<w> y() {
        return this.h;
    }

    public r.a z() {
        return this.i;
    }

    @Override // b.e.a
    public e a(ac acVar) {
        return ab.a(this, acVar, false);
    }

    @Override // b.ai.a
    public ai a(ac acVar, aj ajVar) {
        b.a.k.a aVar = new b.a.k.a(acVar, ajVar, new Random(), this.C);
        aVar.a(this);
        return aVar;
    }

    public a A() {
        return new a(this);
    }

    public static final class a {
        int A;

        /* renamed from: a, reason: collision with root package name */
        p f1303a;

        /* renamed from: b, reason: collision with root package name */
        @Nullable
        Proxy f1304b;

        /* renamed from: c, reason: collision with root package name */
        List<aa> f1305c;
        List<l> d;
        final List<w> e;
        final List<w> f;
        r.a g;
        ProxySelector h;
        n i;

        @Nullable
        c j;

        @Nullable
        b.a.a.f k;
        SocketFactory l;

        @Nullable
        SSLSocketFactory m;

        @Nullable
        b.a.j.c n;
        HostnameVerifier o;
        g p;
        b q;
        b r;
        k s;
        q t;
        boolean u;
        boolean v;
        boolean w;
        int x;
        int y;
        int z;

        public a() {
            this.e = new ArrayList();
            this.f = new ArrayList();
            this.f1303a = new p();
            this.f1305c = z.f1300a;
            this.d = z.f1301b;
            this.g = r.a(r.f1266a);
            this.h = ProxySelector.getDefault();
            this.i = n.f1261a;
            this.l = SocketFactory.getDefault();
            this.o = b.a.j.e.f959a;
            this.p = g.f1232a;
            this.q = b.f1028a;
            this.r = b.f1028a;
            this.s = new k();
            this.t = q.f1265a;
            this.u = true;
            this.v = true;
            this.w = true;
            this.x = NetCallback.ERROR_CODE_REQUEST_SUCCESS;
            this.y = NetCallback.ERROR_CODE_REQUEST_SUCCESS;
            this.z = NetCallback.ERROR_CODE_REQUEST_SUCCESS;
            this.A = 0;
        }

        a(z zVar) {
            this.e = new ArrayList();
            this.f = new ArrayList();
            this.f1303a = zVar.f1302c;
            this.f1304b = zVar.d;
            this.f1305c = zVar.e;
            this.d = zVar.f;
            this.e.addAll(zVar.g);
            this.f.addAll(zVar.h);
            this.g = zVar.i;
            this.h = zVar.j;
            this.i = zVar.k;
            this.k = zVar.m;
            this.j = zVar.l;
            this.l = zVar.n;
            this.m = zVar.o;
            this.n = zVar.p;
            this.o = zVar.q;
            this.p = zVar.r;
            this.q = zVar.s;
            this.r = zVar.t;
            this.s = zVar.u;
            this.t = zVar.v;
            this.u = zVar.w;
            this.v = zVar.x;
            this.w = zVar.y;
            this.x = zVar.z;
            this.y = zVar.A;
            this.z = zVar.B;
            this.A = zVar.C;
        }

        public a a(long j, TimeUnit timeUnit) {
            this.x = b.a.c.a("timeout", j, timeUnit);
            return this;
        }

        public a b(long j, TimeUnit timeUnit) {
            this.y = b.a.c.a("timeout", j, timeUnit);
            return this;
        }

        public a c(long j, TimeUnit timeUnit) {
            this.z = b.a.c.a("timeout", j, timeUnit);
            return this;
        }

        public a d(long j, TimeUnit timeUnit) {
            this.A = b.a.c.a("interval", j, timeUnit);
            return this;
        }

        public a a(@Nullable Proxy proxy) {
            this.f1304b = proxy;
            return this;
        }

        public a a(ProxySelector proxySelector) {
            this.h = proxySelector;
            return this;
        }

        public a a(n nVar) {
            if (nVar == null) {
                throw new NullPointerException("cookieJar == null");
            }
            this.i = nVar;
            return this;
        }

        void a(@Nullable b.a.a.f fVar) {
            this.k = fVar;
            this.j = null;
        }

        public a a(@Nullable c cVar) {
            this.j = cVar;
            this.k = null;
            return this;
        }

        public a a(q qVar) {
            if (qVar == null) {
                throw new NullPointerException("dns == null");
            }
            this.t = qVar;
            return this;
        }

        public a a(SocketFactory socketFactory) {
            if (socketFactory == null) {
                throw new NullPointerException("socketFactory == null");
            }
            this.l = socketFactory;
            return this;
        }

        public a a(SSLSocketFactory sSLSocketFactory) {
            if (sSLSocketFactory == null) {
                throw new NullPointerException("sslSocketFactory == null");
            }
            this.m = sSLSocketFactory;
            this.n = b.a.h.f.c().b(sSLSocketFactory);
            return this;
        }

        public a a(SSLSocketFactory sSLSocketFactory, X509TrustManager x509TrustManager) {
            if (sSLSocketFactory == null) {
                throw new NullPointerException("sslSocketFactory == null");
            }
            if (x509TrustManager == null) {
                throw new NullPointerException("trustManager == null");
            }
            this.m = sSLSocketFactory;
            this.n = b.a.j.c.a(x509TrustManager);
            return this;
        }

        public a a(HostnameVerifier hostnameVerifier) {
            if (hostnameVerifier == null) {
                throw new NullPointerException("hostnameVerifier == null");
            }
            this.o = hostnameVerifier;
            return this;
        }

        public a a(g gVar) {
            if (gVar == null) {
                throw new NullPointerException("certificatePinner == null");
            }
            this.p = gVar;
            return this;
        }

        public a a(b bVar) {
            if (bVar == null) {
                throw new NullPointerException("authenticator == null");
            }
            this.r = bVar;
            return this;
        }

        public a b(b bVar) {
            if (bVar == null) {
                throw new NullPointerException("proxyAuthenticator == null");
            }
            this.q = bVar;
            return this;
        }

        public a a(k kVar) {
            if (kVar == null) {
                throw new NullPointerException("connectionPool == null");
            }
            this.s = kVar;
            return this;
        }

        public a a(boolean z) {
            this.u = z;
            return this;
        }

        public a b(boolean z) {
            this.v = z;
            return this;
        }

        public a c(boolean z) {
            this.w = z;
            return this;
        }

        public a a(p pVar) {
            if (pVar == null) {
                throw new IllegalArgumentException("dispatcher == null");
            }
            this.f1303a = pVar;
            return this;
        }

        public a a(List<aa> list) {
            ArrayList arrayList = new ArrayList(list);
            if (!arrayList.contains(aa.HTTP_1_1)) {
                throw new IllegalArgumentException("protocols doesn't contain http/1.1: " + arrayList);
            }
            if (arrayList.contains(aa.HTTP_1_0)) {
                throw new IllegalArgumentException("protocols must not contain http/1.0: " + arrayList);
            }
            if (arrayList.contains(null)) {
                throw new IllegalArgumentException("protocols must not contain null");
            }
            arrayList.remove(aa.SPDY_3);
            this.f1305c = Collections.unmodifiableList(arrayList);
            return this;
        }

        public a b(List<l> list) {
            this.d = b.a.c.a(list);
            return this;
        }

        public List<w> a() {
            return this.e;
        }

        public a a(w wVar) {
            if (wVar == null) {
                throw new IllegalArgumentException("interceptor == null");
            }
            this.e.add(wVar);
            return this;
        }

        public List<w> b() {
            return this.f;
        }

        public a b(w wVar) {
            if (wVar == null) {
                throw new IllegalArgumentException("interceptor == null");
            }
            this.f.add(wVar);
            return this;
        }

        public a a(r rVar) {
            if (rVar == null) {
                throw new NullPointerException("eventListener == null");
            }
            this.g = r.a(rVar);
            return this;
        }

        public a a(r.a aVar) {
            if (aVar == null) {
                throw new NullPointerException("eventListenerFactory == null");
            }
            this.g = aVar;
            return this;
        }

        public z c() {
            return new z(this);
        }
    }
}
