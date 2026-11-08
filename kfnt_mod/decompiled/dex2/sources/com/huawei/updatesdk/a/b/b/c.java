package com.huawei.updatesdk.a.b.b;

import com.huawei.updatesdk.a.a.a.d;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import javax.net.ssl.HttpsURLConnection;
import org.apache.http.conn.ssl.SSLSocketFactory;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    private static volatile c a;

    public static synchronized c a() {
        if (a == null) {
            a = new c();
        }
        return a;
    }

    private Proxy b() {
        if (com.huawei.updatesdk.a.a.c.b.b.b(com.huawei.updatesdk.a.b.a.a.a().b())) {
            return com.huawei.updatesdk.a.a.c.b.b.a();
        }
        return null;
    }

    public HttpURLConnection a(String str) throws IllegalAccessException, NoSuchAlgorithmException, IOException, CertificateException, KeyStoreException, KeyManagementException {
        URL url = new URL(str);
        Proxy proxyB = b();
        HttpURLConnection httpURLConnection = (HttpURLConnection) (proxyB == null ? url.openConnection() : url.openConnection(proxyB));
        httpURLConnection.setConnectTimeout(7000);
        httpURLConnection.setReadTimeout(10000);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setRequestProperty("Accept-Encoding", "identity");
        httpURLConnection.setInstanceFollowRedirects(true);
        if (httpURLConnection instanceof HttpsURLConnection) {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
            httpsURLConnection.setSSLSocketFactory(d.a(com.huawei.updatesdk.a.b.a.a.a().b()));
            httpsURLConnection.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
        }
        return httpURLConnection;
    }
}
