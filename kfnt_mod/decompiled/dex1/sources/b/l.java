package b;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLSocket;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class l {
    final boolean d;
    final boolean e;

    @Nullable
    final String[] f;

    @Nullable
    final String[] g;
    private static final i[] h = {i.aX, i.bb, i.aY, i.bc, i.bi, i.bh, i.aI, i.aJ, i.ag, i.ah, i.E, i.I, i.i};

    /* renamed from: a, reason: collision with root package name */
    public static final l f1249a = new a(true).a(h).a(ah.TLS_1_3, ah.TLS_1_2, ah.TLS_1_1, ah.TLS_1_0).a(true).c();

    /* renamed from: b, reason: collision with root package name */
    public static final l f1250b = new a(f1249a).a(ah.TLS_1_0).a(true).c();

    /* renamed from: c, reason: collision with root package name */
    public static final l f1251c = new a(false).c();

    l(a aVar) {
        this.d = aVar.f1252a;
        this.f = aVar.f1253b;
        this.g = aVar.f1254c;
        this.e = aVar.d;
    }

    public boolean a() {
        return this.d;
    }

    @Nullable
    public List<i> b() {
        if (this.f != null) {
            return i.a(this.f);
        }
        return null;
    }

    @Nullable
    public List<ah> c() {
        if (this.g != null) {
            return ah.a(this.g);
        }
        return null;
    }

    public boolean d() {
        return this.e;
    }

    void a(SSLSocket sSLSocket, boolean z) {
        l lVarB = b(sSLSocket, z);
        if (lVarB.g != null) {
            sSLSocket.setEnabledProtocols(lVarB.g);
        }
        if (lVarB.f != null) {
            sSLSocket.setEnabledCipherSuites(lVarB.f);
        }
    }

    private l b(SSLSocket sSLSocket, boolean z) {
        String[] enabledCipherSuites;
        String[] enabledProtocols;
        if (this.f != null) {
            enabledCipherSuites = b.a.c.a(i.f1242a, sSLSocket.getEnabledCipherSuites(), this.f);
        } else {
            enabledCipherSuites = sSLSocket.getEnabledCipherSuites();
        }
        if (this.g != null) {
            enabledProtocols = b.a.c.a(b.a.c.h, sSLSocket.getEnabledProtocols(), this.g);
        } else {
            enabledProtocols = sSLSocket.getEnabledProtocols();
        }
        String[] supportedCipherSuites = sSLSocket.getSupportedCipherSuites();
        int iA = b.a.c.a(i.f1242a, supportedCipherSuites, "TLS_FALLBACK_SCSV");
        if (z && iA != -1) {
            enabledCipherSuites = b.a.c.a(enabledCipherSuites, supportedCipherSuites[iA]);
        }
        return new a(this).a(enabledCipherSuites).b(enabledProtocols).c();
    }

    public boolean a(SSLSocket sSLSocket) {
        if (!this.d) {
            return false;
        }
        if (this.g == null || b.a.c.b(b.a.c.h, this.g, sSLSocket.getEnabledProtocols())) {
            return this.f == null || b.a.c.b(i.f1242a, this.f, sSLSocket.getEnabledCipherSuites());
        }
        return false;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof l)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        l lVar = (l) obj;
        if (this.d != lVar.d) {
            return false;
        }
        return !this.d || (Arrays.equals(this.f, lVar.f) && Arrays.equals(this.g, lVar.g) && this.e == lVar.e);
    }

    public int hashCode() {
        if (this.d) {
            return ((((527 + Arrays.hashCode(this.f)) * 31) + Arrays.hashCode(this.g)) * 31) + (!this.e ? 1 : 0);
        }
        return 17;
    }

    public String toString() {
        if (!this.d) {
            return "ConnectionSpec()";
        }
        return "ConnectionSpec(cipherSuites=" + (this.f != null ? b().toString() : "[all enabled]") + ", tlsVersions=" + (this.g != null ? c().toString() : "[all enabled]") + ", supportsTlsExtensions=" + this.e + ")";
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        boolean f1252a;

        /* renamed from: b, reason: collision with root package name */
        @Nullable
        String[] f1253b;

        /* renamed from: c, reason: collision with root package name */
        @Nullable
        String[] f1254c;
        boolean d;

        a(boolean z) {
            this.f1252a = z;
        }

        public a(l lVar) {
            this.f1252a = lVar.d;
            this.f1253b = lVar.f;
            this.f1254c = lVar.g;
            this.d = lVar.e;
        }

        public a a() {
            if (!this.f1252a) {
                throw new IllegalStateException("no cipher suites for cleartext connections");
            }
            this.f1253b = null;
            return this;
        }

        public a a(i... iVarArr) {
            if (!this.f1252a) {
                throw new IllegalStateException("no cipher suites for cleartext connections");
            }
            String[] strArr = new String[iVarArr.length];
            for (int i = 0; i < iVarArr.length; i++) {
                strArr[i] = iVarArr[i].bj;
            }
            return a(strArr);
        }

        public a a(String... strArr) {
            if (!this.f1252a) {
                throw new IllegalStateException("no cipher suites for cleartext connections");
            }
            if (strArr.length == 0) {
                throw new IllegalArgumentException("At least one cipher suite is required");
            }
            this.f1253b = (String[]) strArr.clone();
            return this;
        }

        public a b() {
            if (!this.f1252a) {
                throw new IllegalStateException("no TLS versions for cleartext connections");
            }
            this.f1254c = null;
            return this;
        }

        public a a(ah... ahVarArr) {
            if (!this.f1252a) {
                throw new IllegalStateException("no TLS versions for cleartext connections");
            }
            String[] strArr = new String[ahVarArr.length];
            for (int i = 0; i < ahVarArr.length; i++) {
                strArr[i] = ahVarArr[i].f;
            }
            return b(strArr);
        }

        public a b(String... strArr) {
            if (!this.f1252a) {
                throw new IllegalStateException("no TLS versions for cleartext connections");
            }
            if (strArr.length == 0) {
                throw new IllegalArgumentException("At least one TLS version is required");
            }
            this.f1254c = (String[]) strArr.clone();
            return this;
        }

        public a a(boolean z) {
            if (!this.f1252a) {
                throw new IllegalStateException("no TLS extensions for cleartext connections");
            }
            this.d = z;
            return this;
        }

        public l c() {
            return new l(this);
        }
    }
}
