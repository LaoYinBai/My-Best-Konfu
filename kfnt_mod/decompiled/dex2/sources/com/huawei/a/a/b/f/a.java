package com.huawei.a.a.b.f;

import android.content.Context;
import android.os.Build;
import com.mbridge.msdk.foundation.tools.SameMD5;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a extends SSLSocketFactory {
    private static final String[] a = {"3DES", "DES", SameMD5.TAG, "RC4", "aNULL", "eNULL", "TEA", "SHA0", "MD2", "MD4", "RIPEMD", "DESX", "DES40", "RC2", "ANON", "NULL", "TLS_EMPTY_RENEGOTIATION_INFO_SCSV", "TLS_RSA"};
    private static SocketFactory c;
    private final SSLContext b = SSLContext.getInstance("TLSv1.2");

    private a(Context context) throws KeyManagementException {
        this.b.init(null, new X509TrustManager[]{new d(context)}, null);
    }

    public static SocketFactory a(Context context) {
        return b(context);
    }

    private void a(Socket socket) {
        if (socket instanceof SSLSocket) {
            SSLSocket sSLSocket = (SSLSocket) socket;
            a(sSLSocket);
            b(sSLSocket);
        }
    }

    private void a(SSLSocket sSLSocket) {
        if (sSLSocket == null || Build.VERSION.SDK_INT < 16) {
            return;
        }
        sSLSocket.setEnabledProtocols(new String[]{"TLSv1.2"});
    }

    private static boolean a(String str) {
        for (String str2 : a) {
            if (str.contains(str2)) {
                return true;
            }
        }
        return false;
    }

    private static synchronized SocketFactory b(Context context) {
        String str;
        String str2;
        try {
            if (c == null) {
                c = new a(context);
            }
            return c;
        } catch (IOException unused) {
            str = "Factory";
            str2 = "getLocalInstanceLock(): Failed to new SSLSocketFactory instance,IO!";
            com.huawei.a.a.b.e.a.c(str, str2);
            return null;
        } catch (KeyManagementException unused2) {
            str = "Factory";
            str2 = "getLocalInstanceLock(): Failed to new SSLSocketFactory instance,Key Manage!";
            com.huawei.a.a.b.e.a.c(str, str2);
            return null;
        } catch (KeyStoreException unused3) {
            str = "Factory";
            str2 = "getLocalInstanceLock(): Failed to new SSLSocketFactory instance,Key Store!";
            com.huawei.a.a.b.e.a.c(str, str2);
            return null;
        } catch (NoSuchAlgorithmException unused4) {
            str = "Factory";
            str2 = "getLocalInstanceLock(): Failed to new SSLSocketFactory instance,Algorithm Exception!";
            com.huawei.a.a.b.e.a.c(str, str2);
            return null;
        } catch (GeneralSecurityException unused5) {
            str = "Factory";
            str2 = "getLocalInstanceLock(): GeneralSecurityException: Failed to new SSLSocketFactory instance";
            com.huawei.a.a.b.e.a.c(str, str2);
            return null;
        }
    }

    private static void b(SSLSocket sSLSocket) {
        String[] enabledCipherSuites = sSLSocket.getEnabledCipherSuites();
        if (enabledCipherSuites == null || enabledCipherSuites.length == 0) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : enabledCipherSuites) {
            if (!a(str)) {
                arrayList.add(str);
            }
        }
        sSLSocket.setEnabledCipherSuites((String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i) {
        Socket socketCreateSocket = this.b.getSocketFactory().createSocket(str, i);
        a(socketCreateSocket);
        return socketCreateSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) {
        return createSocket(str, i);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i) {
        return createSocket(inetAddress.getHostAddress(), i);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) {
        return createSocket(inetAddress.getHostAddress(), i);
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        Socket socketCreateSocket = this.b.getSocketFactory().createSocket(socket, str, i, z);
        a(socketCreateSocket);
        return socketCreateSocket;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return new String[0];
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        return new String[0];
    }
}
