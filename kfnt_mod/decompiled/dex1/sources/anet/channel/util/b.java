package anet.channel.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {
    public static final HostnameVerifier ALLOW_ALL_HOSTNAME_VERIFIER = new a();
    public static final SSLSocketFactory TRUST_ALL_SSL_SOCKET_FACTORY = C0009b.a();

    /* renamed from: a, reason: collision with root package name */
    static SSLSocketFactory f603a;

    /* renamed from: b, reason: collision with root package name */
    static HostnameVerifier f604b;

    public static SSLSocketFactory a() {
        return f603a;
    }

    public static void a(SSLSocketFactory sSLSocketFactory) {
        f603a = sSLSocketFactory;
    }

    public static HostnameVerifier b() {
        return f604b;
    }

    public static void a(HostnameVerifier hostnameVerifier) {
        f604b = hostnameVerifier;
    }

    private static class a implements HostnameVerifier {
        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            return true;
        }

        private a() {
        }
    }

    /* renamed from: anet.channel.util.b$b, reason: collision with other inner class name */
    private static class C0009b {
        private C0009b() {
        }

        /* renamed from: anet.channel.util.b$b$a */
        private static class a implements X509TrustManager {
            @Override // javax.net.ssl.X509TrustManager
            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }

            @Override // javax.net.ssl.X509TrustManager
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            private a() {
            }
        }

        public static SSLSocketFactory a() {
            try {
                SSLContext sSLContext = SSLContext.getInstance("TLS");
                sSLContext.init(null, new TrustManager[]{new a()}, null);
                return sSLContext.getSocketFactory();
            } catch (Throwable th) {
                ALog.w("awcn.SSLTrustAllSocketFactory", "getSocketFactory error :" + th.getMessage(), null, new Object[0]);
                return null;
            }
        }
    }
}
