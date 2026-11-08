package b.a.j;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.X509TrustManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class c {
    public abstract List<Certificate> a(List<Certificate> list, String str) throws SSLPeerUnverifiedException;

    public static c a(X509TrustManager x509TrustManager) {
        return b.a.h.f.c().a(x509TrustManager);
    }

    public static c a(X509Certificate... x509CertificateArr) {
        return new a(new b(x509CertificateArr));
    }
}
