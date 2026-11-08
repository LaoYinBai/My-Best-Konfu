package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122;

import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLSocket;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class k {
    private static final h[] e = {h.m, h.o, h.n, h.p, h.r, h.q, h.i, h.k, h.j, h.l, h.g, h.h, h.e, h.f, h.d};
    public static final k f = new a(true).a(e).a(d0.TLS_1_3, d0.TLS_1_2, d0.TLS_1_1, d0.TLS_1_0).a(true).a();
    public static final k g;

    /* renamed from: a, reason: collision with root package name */
    final boolean f1708a;

    /* renamed from: b, reason: collision with root package name */
    final boolean f1709b;

    /* renamed from: c, reason: collision with root package name */
    final String[] f1710c;
    final String[] d;

    static {
        new a(f).a(d0.TLS_1_0).a(true).a();
        g = new a(false).a();
    }

    k(a aVar) {
        this.f1708a = aVar.f1711a;
        this.f1710c = aVar.f1712b;
        this.d = aVar.f1713c;
        this.f1709b = aVar.d;
    }

    public List<h> a() {
        String[] strArr = this.f1710c;
        if (strArr != null) {
            return h.a(strArr);
        }
        return null;
    }

    public boolean b() {
        return this.f1708a;
    }

    public boolean c() {
        return this.f1709b;
    }

    public List<d0> d() {
        String[] strArr = this.d;
        if (strArr != null) {
            return d0.a(strArr);
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof k)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        k kVar = (k) obj;
        boolean z = this.f1708a;
        if (z != kVar.f1708a) {
            return false;
        }
        return !z || (Arrays.equals(this.f1710c, kVar.f1710c) && Arrays.equals(this.d, kVar.d) && this.f1709b == kVar.f1709b);
    }

    public int hashCode() {
        if (this.f1708a) {
            return ((((Arrays.hashCode(this.f1710c) + 527) * 31) + Arrays.hashCode(this.d)) * 31) + (!this.f1709b ? 1 : 0);
        }
        return 17;
    }

    public String toString() {
        if (!this.f1708a) {
            return "ConnectionSpec()";
        }
        return "ConnectionSpec(cipherSuites=" + (this.f1710c != null ? a().toString() : "[all enabled]") + ", tlsVersions=" + (this.d != null ? d().toString() : "[all enabled]") + ", supportsTlsExtensions=" + this.f1709b + ")";
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        boolean f1711a;

        /* renamed from: b, reason: collision with root package name */
        String[] f1712b;

        /* renamed from: c, reason: collision with root package name */
        String[] f1713c;
        boolean d;

        a(boolean z) {
            this.f1711a = z;
        }

        public a a(h... hVarArr) {
            if (!this.f1711a) {
                throw new IllegalStateException("no cipher suites for cleartext connections");
            }
            String[] strArr = new String[hVarArr.length];
            for (int i = 0; i < hVarArr.length; i++) {
                strArr[i] = hVarArr[i].f1703a;
            }
            return a(strArr);
        }

        public a b(String... strArr) {
            if (!this.f1711a) {
                throw new IllegalStateException("no TLS versions for cleartext connections");
            }
            if (strArr.length == 0) {
                throw new IllegalArgumentException("At least one TLS version is required");
            }
            this.f1713c = (String[]) strArr.clone();
            return this;
        }

        public a(k kVar) {
            this.f1711a = kVar.f1708a;
            this.f1712b = kVar.f1710c;
            this.f1713c = kVar.d;
            this.d = kVar.f1709b;
        }

        public a a(String... strArr) {
            if (this.f1711a) {
                if (strArr.length != 0) {
                    this.f1712b = (String[]) strArr.clone();
                    return this;
                }
                throw new IllegalArgumentException("At least one cipher suite is required");
            }
            throw new IllegalStateException("no cipher suites for cleartext connections");
        }

        public a a(d0... d0VarArr) {
            if (this.f1711a) {
                String[] strArr = new String[d0VarArr.length];
                for (int i = 0; i < d0VarArr.length; i++) {
                    strArr[i] = d0VarArr[i].f1693a;
                }
                return b(strArr);
            }
            throw new IllegalStateException("no TLS versions for cleartext connections");
        }

        public a a(boolean z) {
            if (this.f1711a) {
                this.d = z;
                return this;
            }
            throw new IllegalStateException("no TLS extensions for cleartext connections");
        }

        public k a() {
            return new k(this);
        }
    }

    private k b(SSLSocket sSLSocket, boolean z) {
        String[] strArrA = this.f1710c != null ? bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(h.f1701b, sSLSocket.getEnabledCipherSuites(), this.f1710c) : sSLSocket.getEnabledCipherSuites();
        String[] strArrA2 = this.d != null ? bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.o, sSLSocket.getEnabledProtocols(), this.d) : sSLSocket.getEnabledProtocols();
        String[] supportedCipherSuites = sSLSocket.getSupportedCipherSuites();
        int iA = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(h.f1701b, supportedCipherSuites, "TLS_FALLBACK_SCSV");
        if (z && iA != -1) {
            strArrA = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(strArrA, supportedCipherSuites[iA]);
        }
        return new a(this).a(strArrA).b(strArrA2).a();
    }

    void a(SSLSocket sSLSocket, boolean z) {
        k kVarB = b(sSLSocket, z);
        String[] strArr = kVarB.d;
        if (strArr != null) {
            sSLSocket.setEnabledProtocols(strArr);
        }
        String[] strArr2 = kVarB.f1710c;
        if (strArr2 != null) {
            sSLSocket.setEnabledCipherSuites(strArr2);
        }
    }

    public boolean a(SSLSocket sSLSocket) {
        if (!this.f1708a) {
            return false;
        }
        String[] strArr = this.d;
        if (strArr != null && !bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.b(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.o, strArr, sSLSocket.getEnabledProtocols())) {
            return false;
        }
        String[] strArr2 = this.f1710c;
        return strArr2 == null || bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.b(h.f1701b, strArr2, sSLSocket.getEnabledCipherSuites());
    }
}
