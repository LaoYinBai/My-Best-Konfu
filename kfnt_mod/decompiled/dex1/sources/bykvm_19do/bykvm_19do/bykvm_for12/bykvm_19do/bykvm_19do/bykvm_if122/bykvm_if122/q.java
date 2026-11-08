package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122;

import java.security.cert.Certificate;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class q {

    /* renamed from: a, reason: collision with root package name */
    private final d0 f1724a;

    /* renamed from: b, reason: collision with root package name */
    private final h f1725b;

    /* renamed from: c, reason: collision with root package name */
    private final List<Certificate> f1726c;
    private final List<Certificate> d;

    private q(d0 d0Var, h hVar, List<Certificate> list, List<Certificate> list2) {
        this.f1724a = d0Var;
        this.f1725b = hVar;
        this.f1726c = list;
        this.d = list2;
    }

    public static q a(SSLSession sSLSession) throws SSLPeerUnverifiedException {
        String cipherSuite;
        Certificate[] peerCertificates = null;
        try {
            cipherSuite = sSLSession.getCipherSuite();
        } catch (Exception unused) {
            cipherSuite = null;
        }
        if (cipherSuite == null) {
            throw new IllegalStateException("cipherSuite == null");
        }
        h hVarA = h.a(cipherSuite);
        String protocol = sSLSession.getProtocol();
        if (protocol == null) {
            throw new IllegalStateException("tlsVersion == null");
        }
        d0 d0VarA = d0.a(protocol);
        try {
            peerCertificates = sSLSession.getPeerCertificates();
        } catch (SSLPeerUnverifiedException unused2) {
        }
        List listA = peerCertificates != null ? bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(peerCertificates) : Collections.emptyList();
        Certificate[] localCertificates = sSLSession.getLocalCertificates();
        return new q(d0VarA, hVarA, listA, localCertificates != null ? bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a(localCertificates) : Collections.emptyList());
    }

    public List<Certificate> b() {
        return this.f1726c;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof q)) {
            return false;
        }
        q qVar = (q) obj;
        return this.f1724a.equals(qVar.f1724a) && this.f1725b.equals(qVar.f1725b) && this.f1726c.equals(qVar.f1726c) && this.d.equals(qVar.d);
    }

    public int hashCode() {
        return ((((((this.f1724a.hashCode() + 527) * 31) + this.f1725b.hashCode()) * 31) + this.f1726c.hashCode()) * 31) + this.d.hashCode();
    }

    public h a() {
        return this.f1725b;
    }
}
