package com.meizu.cloud.pushsdk.b.h;

import android.content.Context;
import android.net.SSLSessionCache;
import android.os.Environment;
import com.meizu.cloud.pushinternal.DebugLogger;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends SSLSocketFactory {
    private SSLSocketFactory a;

    public a(Context context) throws NoSuchAlgorithmException {
        SSLSessionCache sSLSessionCache;
        try {
            SSLContext sSLContext = SSLContext.getDefault();
            try {
                sSLSessionCache = new SSLSessionCache(new File(Environment.getExternalStorageDirectory(), "sslCache"));
            } catch (IOException e) {
                DebugLogger.e("TlsSessionTicket", e.getMessage());
                sSLSessionCache = new SSLSessionCache(context);
            }
            DebugLogger.i("TlsSessionTicket", "install tls session cache " + com.meizu.cloud.pushsdk.base.a.a.a(sSLSessionCache).a("install", SSLSessionCache.class, SSLContext.class).a(sSLSessionCache, sSLSessionCache, sSLContext).a);
            this.a = sSLContext.getSocketFactory();
        } catch (Exception e2) {
            DebugLogger.e("TlsSessionTicket", e2.getMessage());
            this.a = (SSLSocketFactory) SSLSocketFactory.getDefault();
        }
    }

    private Socket a(Socket socket) throws NoSuchMethodException, SecurityException {
        if (socket instanceof SSLSocket) {
            DebugLogger.i("TlsSessionTicket", "set ssl session ticket support " + com.meizu.cloud.pushsdk.base.a.a.a(socket).a("setUseSessionTickets", Boolean.TYPE).a(socket, true).a);
        }
        return socket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i) throws IOException {
        return a(this.a.createSocket(str, i));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
        return a(this.a.createSocket(str, i, inetAddress, i2));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        return a(this.a.createSocket(inetAddress, i));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        return a(this.a.createSocket(inetAddress, i, inetAddress2, i2));
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        return a(this.a.createSocket(socket, str, i, z));
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return this.a.getDefaultCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        return this.a.getSupportedCipherSuites();
    }
}
