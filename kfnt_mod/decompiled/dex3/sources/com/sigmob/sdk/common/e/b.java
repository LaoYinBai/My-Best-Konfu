package com.sigmob.sdk.common.e;

import android.net.SSLCertificateSocketFactory;
import android.os.Build;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.common.utils.m;
import com.sigmob.sdk.common.utils.n;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b extends SSLSocketFactory {
    private static final TrustManager[] b = {new X509TrustManager() { // from class: com.sigmob.sdk.common.e.b.1
        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }};
    private SSLSocketFactory a;

    private b() {
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x002d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x002e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.sigmob.sdk.common.e.b a() throws java.security.NoSuchAlgorithmException, java.security.KeyManagementException {
        /*
            com.sigmob.sdk.common.e.b r0 = new com.sigmob.sdk.common.e.b
            r0.<init>()
            r1 = 0
            java.lang.String r2 = "SSL"
            javax.net.ssl.SSLContext r2 = javax.net.ssl.SSLContext.getInstance(r2)     // Catch: java.security.NoSuchAlgorithmException -> L1b java.security.KeyManagementException -> L22
            javax.net.ssl.TrustManager[] r3 = com.sigmob.sdk.common.e.b.b     // Catch: java.security.NoSuchAlgorithmException -> L17 java.security.KeyManagementException -> L19
            java.security.SecureRandom r4 = new java.security.SecureRandom     // Catch: java.security.NoSuchAlgorithmException -> L17 java.security.KeyManagementException -> L19
            r4.<init>()     // Catch: java.security.NoSuchAlgorithmException -> L17 java.security.KeyManagementException -> L19
            r2.init(r1, r3, r4)     // Catch: java.security.NoSuchAlgorithmException -> L17 java.security.KeyManagementException -> L19
            goto L2b
        L17:
            r3 = move-exception
            goto L1d
        L19:
            r3 = move-exception
            goto L24
        L1b:
            r3 = move-exception
            r2 = r1
        L1d:
            java.lang.String r3 = r3.getMessage()
            goto L28
        L22:
            r3 = move-exception
            r2 = r1
        L24:
            java.lang.String r3 = r3.getMessage()
        L28:
            com.sigmob.logger.SigmobLog.e(r3)
        L2b:
            if (r2 != 0) goto L2e
            return r1
        L2e:
            javax.net.ssl.SSLSocketFactory r1 = r2.getSocketFactory()
            r0.a = r1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sigmob.sdk.common.e.b.a():com.sigmob.sdk.common.e.b");
    }

    public static b a(int i) {
        b bVar = new b();
        bVar.a = SSLCertificateSocketFactory.getDefault(i, null);
        return bVar;
    }

    private static void a(SSLCertificateSocketFactory sSLCertificateSocketFactory, SSLSocket sSLSocket, String str) {
        m.a.a(sSLCertificateSocketFactory);
        m.a.a(sSLSocket);
        if (Build.VERSION.SDK_INT >= 17) {
            sSLCertificateSocketFactory.setHostname(sSLSocket, str);
            return;
        }
        try {
            new n.a(sSLSocket, "setHostname").a(String.class, str).a();
        } catch (Throwable unused) {
            SigmobLog.d("Unable to call setHostname() on the socket");
        }
    }

    private void a(Socket socket) {
        if (socket instanceof SSLSocket) {
            SSLSocket sSLSocket = (SSLSocket) socket;
            sSLSocket.setEnabledProtocols(sSLSocket.getSupportedProtocols());
        }
    }

    private void a(Socket socket, String str) throws SocketException {
        m.a.a(socket);
        if (this.a == null) {
            throw new SocketException("SSLSocketFactory was null. Unable to create socket.");
        }
        if (socket instanceof SSLSocket) {
            try {
                SSLSocket sSLSocket = (SSLSocket) socket;
                if (this.a instanceof SSLCertificateSocketFactory) {
                    a((SSLCertificateSocketFactory) this.a, sSLSocket, str);
                    a(sSLSocket, str);
                }
            } catch (Throwable th) {
                SigmobLog.e(th.getMessage());
            }
        }
    }

    private static void a(SSLSocket sSLSocket, String str) throws IOException {
        m.a.a(sSLSocket);
        sSLSocket.startHandshake();
        if (!HttpsURLConnection.getDefaultHostnameVerifier().verify(str, sSLSocket.getSession())) {
            throw new SSLHandshakeException("Server Name Identification failed.");
        }
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket() throws SocketException {
        if (this.a == null) {
            throw new SocketException("SSLSocketFactory was null. Unable to create socket.");
        }
        Socket socketCreateSocket = this.a.createSocket();
        a(socketCreateSocket);
        return socketCreateSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i) throws SocketException {
        if (this.a == null) {
            throw new SocketException("SSLSocketFactory was null. Unable to create socket.");
        }
        Socket socketCreateSocket = this.a.createSocket(str, i);
        a(socketCreateSocket);
        return socketCreateSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws SocketException {
        if (this.a == null) {
            throw new SocketException("SSLSocketFactory was null. Unable to create socket.");
        }
        Socket socketCreateSocket = this.a.createSocket(str, i, inetAddress, i2);
        a(socketCreateSocket);
        return socketCreateSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i) throws SocketException {
        if (this.a == null) {
            throw new SocketException("SSLSocketFactory was null. Unable to create socket.");
        }
        Socket socketCreateSocket = this.a.createSocket(inetAddress, i);
        a(socketCreateSocket);
        return socketCreateSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws SocketException {
        if (this.a == null) {
            throw new SocketException("SSLSocketFactory was null. Unable to create socket.");
        }
        Socket socketCreateSocket = this.a.createSocket(inetAddress, i, inetAddress2, i2);
        a(socketCreateSocket);
        return socketCreateSocket;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        if (this.a == null) {
            throw new SocketException("SSLSocketFactory was null. Unable to create socket.");
        }
        if (Build.VERSION.SDK_INT >= 23) {
            Socket socketCreateSocket = this.a.createSocket(socket, str, i, z);
            a(socketCreateSocket);
            return socketCreateSocket;
        }
        if (z && socket != null) {
            socket.close();
        }
        Socket socketCreateSocket2 = this.a.createSocket(c.a(str), i);
        a(socketCreateSocket2);
        a(socketCreateSocket2, str);
        return socketCreateSocket2;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return this.a == null ? new String[0] : this.a.getDefaultCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        return this.a == null ? new String[0] : this.a.getSupportedCipherSuites();
    }
}
