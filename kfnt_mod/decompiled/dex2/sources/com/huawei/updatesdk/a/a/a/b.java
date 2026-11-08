package com.huawei.updatesdk.a.a.a;

import android.content.Context;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.HttpsURLConnection;
import org.apache.http.conn.ssl.SSLSocketFactory;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private HttpURLConnection a = null;

    public static class a {
        private String a;
        private int b;

        public String a() {
            return (String) com.huawei.updatesdk.service.e.a.a(this.a);
        }

        public int b() {
            return ((Integer) com.huawei.updatesdk.service.e.a.a(Integer.valueOf(this.b))).intValue();
        }
    }

    private HttpURLConnection a(String str, Context context) throws IllegalAccessException, NoSuchAlgorithmException, IOException, CertificateException, KeyStoreException, KeyManagementException {
        HttpsURLConnection httpsURLConnection;
        URL url = new URL(str);
        Proxy proxyD = com.huawei.updatesdk.a.a.c.b.b.d(context);
        if ("https".equals(url.getProtocol())) {
            httpsURLConnection = (HttpsURLConnection) (proxyD == null ? url.openConnection() : url.openConnection(proxyD));
            HttpsURLConnection httpsURLConnection2 = httpsURLConnection;
            httpsURLConnection2.setSSLSocketFactory(d.a(context));
            httpsURLConnection2.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
        } else {
            httpsURLConnection = null;
        }
        return "http".equals(url.getProtocol()) ? (HttpURLConnection) url.openConnection() : httpsURLConnection;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.io.DataOutputStream] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.io.DataOutputStream] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    private byte[] a(byte[] bArr) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        DataOutputStream dataOutputStream;
        ?? r0 = 0;
        r0 = 0;
        r0 = 0;
        try {
            try {
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        dataOutputStream = new DataOutputStream(new GZIPOutputStream(byteArrayOutputStream, bArr.length));
                        r0 = 0;
                    } catch (IOException e) {
                        e = e;
                    }
                } catch (IOException e2) {
                    e = e2;
                    byteArrayOutputStream = null;
                }
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e3) {
            r0 = "HttpsUtil";
            com.huawei.updatesdk.a.a.b.a.a.a.a("HttpsUtil", "gzip error!", e3);
        }
        try {
            dataOutputStream.write(bArr, 0, bArr.length);
            dataOutputStream.flush();
            dataOutputStream.close();
        } catch (IOException e4) {
            e = e4;
            r0 = dataOutputStream;
            com.huawei.updatesdk.a.a.b.a.a.a.a("HttpsUtil", "gzip error!", e);
            if (r0 != 0) {
                r0.close();
                r0 = r0;
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th2) {
            th = th2;
            r0 = dataOutputStream;
            if (r0 != 0) {
                try {
                    r0.close();
                } catch (IOException e5) {
                    com.huawei.updatesdk.a.a.b.a.a.a.a("HttpsUtil", "gzip error!", e5);
                }
            }
            throw th;
        }
        return byteArrayOutputStream.toByteArray();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public a a(String str, String str2, String str3, String str4, Context context) throws Throwable {
        HttpURLConnection httpURLConnectionA;
        BufferedInputStream bufferedInputStream;
        a aVar = new a();
        BufferedInputStream bufferedInputStream2 = null;
        try {
            httpURLConnectionA = a(str, context);
            if (httpURLConnectionA == null) {
                if (httpURLConnectionA != null) {
                    httpURLConnectionA.disconnect();
                }
                com.huawei.updatesdk.a.a.c.d.a((Closeable) null);
                com.huawei.updatesdk.a.a.c.d.a((Closeable) null);
                return aVar;
            }
            try {
                this.a = httpURLConnectionA;
                httpURLConnectionA.setDoInput(true);
                httpURLConnectionA.setDoOutput(true);
                httpURLConnectionA.setUseCaches(false);
                httpURLConnectionA.setConnectTimeout(5000);
                httpURLConnectionA.setReadTimeout(10000);
                httpURLConnectionA.setRequestMethod("POST");
                httpURLConnectionA.setRequestProperty("Content-Type", "application/x-gzip");
                httpURLConnectionA.setRequestProperty("Content-Encoding", "gzip");
                httpURLConnectionA.setRequestProperty("Connection", "Keep-Alive");
                httpURLConnectionA.setRequestProperty("User-Agent", str4);
                DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnectionA.getOutputStream());
                try {
                    dataOutputStream.write(a(str2.getBytes(str3)));
                    dataOutputStream.flush();
                    int responseCode = httpURLConnectionA.getResponseCode();
                    aVar.b = responseCode;
                    bufferedInputStream2 = responseCode == 200 ? new BufferedInputStream(httpURLConnectionA.getInputStream()) : new BufferedInputStream(httpURLConnectionA.getErrorStream());
                    com.huawei.updatesdk.a.a.c.b bVar = new com.huawei.updatesdk.a.a.c.b();
                    byte[] bArrB = com.huawei.updatesdk.a.a.a.a.a().b();
                    while (true) {
                        int i = bufferedInputStream2.read(bArrB);
                        if (i == -1) {
                            break;
                        }
                        bVar.a(bArrB, i);
                    }
                    com.huawei.updatesdk.a.a.a.a.a().a(bArrB);
                    aVar.a = bVar.a();
                    if (httpURLConnectionA != null) {
                        httpURLConnectionA.disconnect();
                    }
                    com.huawei.updatesdk.a.a.c.d.a(dataOutputStream);
                    com.huawei.updatesdk.a.a.c.d.a(bufferedInputStream2);
                    return aVar;
                } catch (Throwable th) {
                    th = th;
                    bufferedInputStream = bufferedInputStream2;
                    bufferedInputStream2 = dataOutputStream;
                    if (httpURLConnectionA != null) {
                        httpURLConnectionA.disconnect();
                    }
                    com.huawei.updatesdk.a.a.c.d.a(bufferedInputStream2);
                    com.huawei.updatesdk.a.a.c.d.a(bufferedInputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream = null;
            }
        } catch (Throwable th3) {
            th = th3;
            httpURLConnectionA = null;
            bufferedInputStream = null;
        }
    }

    public void a() {
        if (this.a != null) {
            this.a.disconnect();
        }
    }
}
