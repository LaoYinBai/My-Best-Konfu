package com.sigmob.volley.toolbox;

import com.sigmob.sdk.common.Constants;
import com.umeng.message.util.HttpRequest;
import java.io.DataOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class j extends com.sigmob.volley.toolbox.a {
    private final b a;
    private final SSLSocketFactory b;

    static class a extends FilterInputStream {
        private final HttpURLConnection a;

        a(HttpURLConnection httpURLConnection) {
            super(j.b(httpURLConnection));
            this.a = httpURLConnection;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            super.close();
            this.a.disconnect();
        }
    }

    public interface b {
        String a(String str);
    }

    public j(b bVar, SSLSocketFactory sSLSocketFactory) {
        this.a = bVar;
        this.b = sSLSocketFactory;
    }

    private HttpURLConnection a(URL url, com.sigmob.volley.l<?> lVar) {
        HttpURLConnection httpURLConnectionA = a(url);
        int iP = lVar.p();
        httpURLConnectionA.setConnectTimeout(lVar.q());
        httpURLConnectionA.setReadTimeout(iP);
        httpURLConnectionA.setUseCaches(false);
        httpURLConnectionA.setDoInput(true);
        if (Constants.HTTPS.equals(url.getProtocol()) && this.b != null) {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnectionA;
            httpsURLConnection.setSSLSocketFactory(this.b);
            httpsURLConnection.setHostnameVerifier(new HostnameVerifier() { // from class: com.sigmob.volley.toolbox.j.1
                @Override // javax.net.ssl.HostnameVerifier
                public boolean verify(String str, SSLSession sSLSession) {
                    return true;
                }
            });
        }
        return httpURLConnectionA;
    }

    static List<com.sigmob.volley.e> a(Map<String, List<String>> map) {
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            if (entry.getKey() != null) {
                Iterator<String> it = entry.getValue().iterator();
                while (it.hasNext()) {
                    arrayList.add(new com.sigmob.volley.e(entry.getKey(), it.next()));
                }
            }
        }
        return arrayList;
    }

    static void a(HttpURLConnection httpURLConnection, com.sigmob.volley.l<?> lVar) throws ProtocolException {
        String str;
        String str2;
        switch (lVar.f()) {
            case -1:
                byte[] bArrB = lVar.b();
                if (bArrB != null) {
                    httpURLConnection.setRequestMethod("POST");
                    a(httpURLConnection, lVar, bArrB);
                    return;
                }
                return;
            case 0:
                str = "GET";
                httpURLConnection.setRequestMethod(str);
                return;
            case 1:
                str2 = "POST";
                httpURLConnection.setRequestMethod(str2);
                b(httpURLConnection, lVar);
                return;
            case 2:
                str2 = HttpRequest.METHOD_PUT;
                httpURLConnection.setRequestMethod(str2);
                b(httpURLConnection, lVar);
                return;
            case 3:
                str = HttpRequest.METHOD_DELETE;
                httpURLConnection.setRequestMethod(str);
                return;
            case 4:
                str = HttpRequest.METHOD_HEAD;
                httpURLConnection.setRequestMethod(str);
                return;
            case 5:
                str = HttpRequest.METHOD_OPTIONS;
                httpURLConnection.setRequestMethod(str);
                return;
            case 6:
                str = HttpRequest.METHOD_TRACE;
                httpURLConnection.setRequestMethod(str);
                return;
            case 7:
                str2 = "PATCH";
                httpURLConnection.setRequestMethod(str2);
                b(httpURLConnection, lVar);
                return;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
    }

    private static void a(HttpURLConnection httpURLConnection, com.sigmob.volley.l<?> lVar, byte[] bArr) {
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("Content-Type", lVar.a());
        DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
        dataOutputStream.write(bArr);
        dataOutputStream.close();
    }

    private static boolean a(int i, int i2) {
        return (i == 4 || (100 <= i2 && i2 < 200) || i2 == 204 || i2 == 304) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static InputStream b(HttpURLConnection httpURLConnection) {
        try {
            return httpURLConnection.getInputStream();
        } catch (IOException unused) {
            return httpURLConnection.getErrorStream();
        }
    }

    private static void b(HttpURLConnection httpURLConnection, com.sigmob.volley.l<?> lVar) {
        byte[] bArrB = lVar.b();
        if (bArrB != null) {
            a(httpURLConnection, lVar, bArrB);
        }
    }

    @Override // com.sigmob.volley.toolbox.a
    public i a(com.sigmob.volley.l<?> lVar, Map<String, String> map) throws IOException {
        String strA;
        String strH = lVar.h();
        HashMap map2 = new HashMap();
        map2.putAll(map);
        map2.putAll(lVar.c());
        if (this.a != null) {
            strA = this.a.a(strH);
            if (strA == null) {
                throw new IOException("URL blocked by rewriter: " + strH);
            }
        } else {
            strA = strH;
        }
        HttpURLConnection httpURLConnectionA = a(new URL(strA), lVar);
        try {
            for (String str : map2.keySet()) {
                httpURLConnectionA.setRequestProperty(str, (String) map2.get(str));
            }
            a(httpURLConnectionA, lVar);
            int responseCode = httpURLConnectionA.getResponseCode();
            if (responseCode == -1) {
                throw new IOException("Could not retrieve response code from HttpUrlConnection.");
            }
            if (a(lVar.f(), responseCode)) {
                return new i(responseCode, a(httpURLConnectionA.getHeaderFields()), httpURLConnectionA.getContentLength(), new a(httpURLConnectionA));
            }
            i iVar = new i(responseCode, a(httpURLConnectionA.getHeaderFields()));
            httpURLConnectionA.disconnect();
            return iVar;
        } catch (Throwable th) {
            if (0 == 0) {
                httpURLConnectionA.disconnect();
            }
            throw th;
        }
    }

    protected HttpURLConnection a(URL url) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setInstanceFollowRedirects(HttpURLConnection.getFollowRedirects());
        return httpURLConnection;
    }
}
