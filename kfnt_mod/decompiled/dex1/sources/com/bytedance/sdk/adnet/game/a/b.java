package com.bytedance.sdk.adnet.game.a;

import android.os.Build;
import android.util.Log;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class b extends SSLSocketFactory {

    /* renamed from: a, reason: collision with root package name */
    public static final X509TrustManager f5008a;

    /* renamed from: b, reason: collision with root package name */
    private static final String[] f5009b;

    /* renamed from: c, reason: collision with root package name */
    private SSLSocketFactory f5010c;

    static {
        f5009b = Build.VERSION.SDK_INT >= 26 ? new String[]{"TLSv1", "TLSv1.1", "TLSv1.2"} : Build.VERSION.SDK_INT >= 16 ? new String[]{"SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"} : new String[]{"SSLv3", "TLSv1"};
        f5008a = new X509TrustManager() { // from class: com.bytedance.sdk.adnet.game.a.b.1
            @Override // javax.net.ssl.X509TrustManager
            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            }

            @Override // javax.net.ssl.X509TrustManager
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
    }

    public b(KeyManager[] keyManagerArr, TrustManager[] trustManagerArr) throws NoSuchAlgorithmException, KeyManagementException {
        if (trustManagerArr != null) {
            try {
                trustManagerArr = trustManagerArr.length <= 0 ? new TrustManager[]{f5008a} : trustManagerArr;
            } catch (Exception e) {
                Log.e("TLSSocketFactory", "TLSSocketFactory error: ", e);
                return;
            }
        }
        SSLContext sSLContext = SSLContext.getInstance("TLS");
        sSLContext.init(keyManagerArr, trustManagerArr, new SecureRandom());
        this.f5010c = sSLContext.getSocketFactory();
    }

    private static void a(Socket socket) {
        if (socket instanceof SSLSocket) {
            ((SSLSocket) socket).setEnabledProtocols(f5009b);
        }
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket() {
        if (this.f5010c == null) {
            return null;
        }
        Socket socketCreateSocket = this.f5010c.createSocket();
        a(socketCreateSocket);
        return socketCreateSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i) {
        if (this.f5010c == null) {
            return null;
        }
        Socket socketCreateSocket = this.f5010c.createSocket(str, i);
        a(socketCreateSocket);
        return socketCreateSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) {
        if (this.f5010c == null) {
            return null;
        }
        Socket socketCreateSocket = this.f5010c.createSocket(str, i, inetAddress, i2);
        a(socketCreateSocket);
        return socketCreateSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i) {
        if (this.f5010c == null) {
            return null;
        }
        Socket socketCreateSocket = this.f5010c.createSocket(inetAddress, i);
        a(socketCreateSocket);
        return socketCreateSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) {
        if (this.f5010c == null) {
            return null;
        }
        Socket socketCreateSocket = this.f5010c.createSocket(inetAddress, i, inetAddress2, i2);
        a(socketCreateSocket);
        return socketCreateSocket;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        if (this.f5010c == null) {
            return null;
        }
        Socket socketCreateSocket = this.f5010c.createSocket(socket, str, i, z);
        a(socketCreateSocket);
        return socketCreateSocket;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        if (this.f5010c != null) {
            return this.f5010c.getDefaultCipherSuites();
        }
        return null;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        if (this.f5010c != null) {
            return this.f5010c.getSupportedCipherSuites();
        }
        return null;
    }
}
