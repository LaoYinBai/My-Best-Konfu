package cn.com.chinatelecom.account.api.d;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.SSLCertificateSocketFactory;
import android.os.Build;
import android.text.TextUtils;
import anet.channel.strategy.dispatch.DispatchConstants;
import anet.channel.util.HttpConstant;
import cn.com.chinatelecom.account.api.CtAuth;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class f implements e {

    /* renamed from: b, reason: collision with root package name */
    private static final String f2268b = "f";

    /* renamed from: a, reason: collision with root package name */
    protected Context f2269a;

    public f(Context context) {
        this.f2269a = context;
    }

    public static void a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager.getNetworkInfo(5).getState().compareTo(NetworkInfo.State.CONNECTED) == 0) {
                ((Boolean) Class.forName("android.net.ConnectivityManager").getMethod("requestRouteToHost", Integer.TYPE, Integer.TYPE).invoke(connectivityManager, 5, Integer.valueOf(cn.com.chinatelecom.account.api.c.c.a(cn.com.chinatelecom.account.api.c.c.b(str))))).booleanValue();
            }
        } catch (Throwable th) {
            CtAuth.warn(f2268b, "http doPost > requestUrlToRoute error", th);
        }
    }

    private void a(HttpsURLConnection httpsURLConnection, final String str) {
        httpsURLConnection.setHostnameVerifier(new HostnameVerifier() { // from class: cn.com.chinatelecom.account.api.d.f.1
            @Override // javax.net.ssl.HostnameVerifier
            public boolean verify(String str2, SSLSession sSLSession) {
                return HttpsURLConnection.getDefaultHostnameVerifier().verify(str, sSLSession);
            }
        });
        httpsURLConnection.setSSLSocketFactory(new SSLSocketFactory() { // from class: cn.com.chinatelecom.account.api.d.f.2
            @Override // javax.net.SocketFactory
            public Socket createSocket(String str2, int i) {
                return null;
            }

            @Override // javax.net.SocketFactory
            public Socket createSocket(String str2, int i, InetAddress inetAddress, int i2) {
                return null;
            }

            @Override // javax.net.SocketFactory
            public Socket createSocket(InetAddress inetAddress, int i) {
                return null;
            }

            @Override // javax.net.SocketFactory
            public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) {
                return null;
            }

            @Override // javax.net.ssl.SSLSocketFactory
            public Socket createSocket(Socket socket, String str2, int i, boolean z) {
                SSLSocket sSLSocket;
                try {
                    SSLCertificateSocketFactory sSLCertificateSocketFactory = (SSLCertificateSocketFactory) SSLCertificateSocketFactory.getDefault(0);
                    sSLSocket = (SSLSocket) sSLCertificateSocketFactory.createSocket(socket, str, i, z);
                    try {
                        sSLSocket.setEnabledProtocols(sSLSocket.getSupportedProtocols());
                        sSLCertificateSocketFactory.setHostname(sSLSocket, str);
                    } catch (Throwable th) {
                        th = th;
                        th.printStackTrace();
                        return sSLSocket;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    sSLSocket = null;
                }
                return sSLSocket;
            }

            @Override // javax.net.ssl.SSLSocketFactory
            public String[] getDefaultCipherSuites() {
                return new String[0];
            }

            @Override // javax.net.ssl.SSLSocketFactory
            public String[] getSupportedCipherSuites() {
                return new String[0];
            }
        });
    }

    private boolean b() {
        return cn.com.chinatelecom.account.api.e.g.c(this.f2269a);
    }

    protected boolean a() {
        return Build.VERSION.SDK_INT >= 21;
    }

    protected boolean a(String str) {
        return str.startsWith("https");
    }

    protected boolean a(boolean z, String str) {
        return z && str != null && Build.VERSION.SDK_INT >= 17;
    }

    protected HttpURLConnection d(String str, String str2, int i, g gVar) throws IOException {
        URL url = new URL(str);
        HttpURLConnection httpURLConnection = (HttpURLConnection) ((gVar.f2274a == null || !a()) ? url.openConnection() : gVar.f2274a.openConnection(url));
        httpURLConnection.setRequestProperty("accept", "*/*");
        if (i == 0) {
            httpURLConnection.setRequestMethod("GET");
        } else {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
        }
        httpURLConnection.setConnectTimeout(gVar.a());
        httpURLConnection.setReadTimeout(gVar.b());
        httpURLConnection.setUseCaches(false);
        if (!b() && !a()) {
            httpURLConnection.setInstanceFollowRedirects(false);
        }
        httpURLConnection.addRequestProperty("Accept-Charset", "UTF-8");
        httpURLConnection.addRequestProperty("reqId", gVar.d);
        httpURLConnection.addRequestProperty(DispatchConstants.DEVICEID, cn.com.chinatelecom.account.api.e.d.a(this.f2269a));
        if (TextUtils.isEmpty(str2)) {
            httpURLConnection.connect();
        } else {
            DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(httpURLConnection.getOutputStream()));
            dataOutputStream.write(str2.getBytes("UTF-8"));
            dataOutputStream.flush();
            dataOutputStream.close();
        }
        return httpURLConnection;
    }

    protected HttpsURLConnection e(String str, String str2, int i, g gVar) throws IOException {
        URL url = new URL(str);
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) ((gVar.f2274a == null || !a()) ? url.openConnection() : gVar.f2274a.openConnection(url));
        httpsURLConnection.setRequestProperty("accept", "*/*");
        if (a(gVar.f, gVar.g)) {
            httpsURLConnection.addRequestProperty(HttpConstant.HOST, gVar.h);
            a(httpsURLConnection, gVar.h);
        }
        if (i == 0) {
            httpsURLConnection.setRequestMethod("GET");
        } else {
            httpsURLConnection.setRequestMethod("POST");
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setDoInput(true);
        }
        httpsURLConnection.setConnectTimeout(gVar.a());
        httpsURLConnection.setReadTimeout(gVar.b());
        httpsURLConnection.setUseCaches(false);
        if (!b() && !a()) {
            httpsURLConnection.setInstanceFollowRedirects(false);
        }
        httpsURLConnection.addRequestProperty("Accept-Charset", "UTF-8");
        httpsURLConnection.addRequestProperty("reqId", gVar.d);
        httpsURLConnection.addRequestProperty(DispatchConstants.DEVICEID, cn.com.chinatelecom.account.api.e.d.a(this.f2269a));
        if (gVar.i != null && !gVar.i.isEmpty()) {
            for (Map.Entry<String, String> entry : gVar.i.entrySet()) {
                httpsURLConnection.addRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        if (TextUtils.isEmpty(str2)) {
            httpsURLConnection.connect();
        } else {
            DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(httpsURLConnection.getOutputStream()));
            dataOutputStream.write(str2.getBytes("UTF-8"));
            dataOutputStream.flush();
            dataOutputStream.close();
        }
        return httpsURLConnection;
    }
}
