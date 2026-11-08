package b;

import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class t {

    /* renamed from: a, reason: collision with root package name */
    private final ah f1274a;

    /* renamed from: b, reason: collision with root package name */
    private final i f1275b;

    /* renamed from: c, reason: collision with root package name */
    private final List<Certificate> f1276c;
    private final List<Certificate> d;

    private t(ah ahVar, i iVar, List<Certificate> list, List<Certificate> list2) {
        this.f1274a = ahVar;
        this.f1275b = iVar;
        this.f1276c = list;
        this.d = list2;
    }

    public static t a(SSLSession sSLSession) throws SSLPeerUnverifiedException {
        Certificate[] peerCertificates;
        List listEmptyList;
        List listEmptyList2;
        String cipherSuite = sSLSession.getCipherSuite();
        if (cipherSuite == null) {
            throw new IllegalStateException("cipherSuite == null");
        }
        i iVarA = i.a(cipherSuite);
        String protocol = sSLSession.getProtocol();
        if (protocol == null) {
            throw new IllegalStateException("tlsVersion == null");
        }
        ah ahVarA = ah.a(protocol);
        try {
            peerCertificates = sSLSession.getPeerCertificates();
        } catch (SSLPeerUnverifiedException unused) {
            peerCertificates = null;
        }
        if (peerCertificates != null) {
            listEmptyList = b.a.c.a(peerCertificates);
        } else {
            listEmptyList = Collections.emptyList();
        }
        Certificate[] localCertificates = sSLSession.getLocalCertificates();
        if (localCertificates != null) {
            listEmptyList2 = b.a.c.a(localCertificates);
        } else {
            listEmptyList2 = Collections.emptyList();
        }
        return new t(ahVarA, iVarA, listEmptyList, listEmptyList2);
    }

    public static t a(ah ahVar, i iVar, List<Certificate> list, List<Certificate> list2) {
        if (ahVar == null) {
            throw new NullPointerException("tlsVersion == null");
        }
        if (iVar == null) {
            throw new NullPointerException("cipherSuite == null");
        }
        return new t(ahVar, iVar, b.a.c.a(list), b.a.c.a(list2));
    }

    public ah a() {
        return this.f1274a;
    }

    public i b() {
        return this.f1275b;
    }

    public List<Certificate> c() {
        return this.f1276c;
    }

    @Nullable
    public Principal d() {
        if (this.f1276c.isEmpty()) {
            return null;
        }
        return ((X509Certificate) this.f1276c.get(0)).getSubjectX500Principal();
    }

    public List<Certificate> e() {
        return this.d;
    }

    @Nullable
    public Principal f() {
        if (this.d.isEmpty()) {
            return null;
        }
        return ((X509Certificate) this.d.get(0)).getSubjectX500Principal();
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof t)) {
            return false;
        }
        t tVar = (t) obj;
        return this.f1274a.equals(tVar.f1274a) && this.f1275b.equals(tVar.f1275b) && this.f1276c.equals(tVar.f1276c) && this.d.equals(tVar.d);
    }

    public int hashCode() {
        return ((((((527 + this.f1274a.hashCode()) * 31) + this.f1275b.hashCode()) * 31) + this.f1276c.hashCode()) * 31) + this.d.hashCode();
    }
}
