package com.meizu.cloud.pushsdk.b.c;

import android.net.TrafficStats;
import com.meizu.cloud.pushsdk.b.c.k;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.util.MinSdkChecker;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e implements a {
    private final com.meizu.cloud.pushsdk.b.h.a a = new com.meizu.cloud.pushsdk.b.h.a(null);

    private static l a(final HttpURLConnection httpURLConnection) throws IOException {
        if (!httpURLConnection.getDoInput()) {
            return null;
        }
        final com.meizu.cloud.pushsdk.b.g.d dVarA = com.meizu.cloud.pushsdk.b.g.g.a(com.meizu.cloud.pushsdk.b.g.g.a(a(httpURLConnection.getResponseCode()) ? httpURLConnection.getInputStream() : httpURLConnection.getErrorStream()));
        return new l() { // from class: com.meizu.cloud.pushsdk.b.c.e.1
            @Override // com.meizu.cloud.pushsdk.b.c.l
            public com.meizu.cloud.pushsdk.b.g.d a() {
                return dVarA;
            }
        };
    }

    private static void a(HttpURLConnection httpURLConnection, i iVar) throws IOException {
        String str;
        String str2;
        switch (iVar.c()) {
            case 0:
                str = "GET";
                httpURLConnection.setRequestMethod(str);
                return;
            case 1:
                str2 = "POST";
                httpURLConnection.setRequestMethod(str2);
                b(httpURLConnection, iVar);
                return;
            case 2:
                str2 = "PUT";
                httpURLConnection.setRequestMethod(str2);
                b(httpURLConnection, iVar);
                return;
            case 3:
                str = "DELETE";
                httpURLConnection.setRequestMethod(str);
                return;
            case 4:
                str = "HEAD";
                httpURLConnection.setRequestMethod(str);
                return;
            case 5:
                str2 = "PATCH";
                httpURLConnection.setRequestMethod(str2);
                b(httpURLConnection, iVar);
                return;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
    }

    protected static boolean a(int i) {
        return i >= 200 && i < 300;
    }

    private HttpURLConnection b(i iVar) throws IOException {
        String string = iVar.a().toString();
        URL url = new URL(string);
        if (MinSdkChecker.isSupportNotificationChannel()) {
            TrafficStats.setThreadStatsTag("HttpURLConnectionCall".hashCode());
        }
        HttpURLConnection httpURLConnectionA = a(url);
        httpURLConnectionA.setConnectTimeout(60000);
        httpURLConnectionA.setReadTimeout(60000);
        httpURLConnectionA.setUseCaches(false);
        httpURLConnectionA.setDoInput(true);
        if (iVar.f() && string.startsWith(PushConstants.URL_API_SERVER)) {
            ((HttpsURLConnection) httpURLConnectionA).setSSLSocketFactory(this.a);
        }
        return httpURLConnectionA;
    }

    private static void b(HttpURLConnection httpURLConnection, i iVar) throws IOException {
        j jVarE = iVar.e();
        if (jVarE != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty("Content-Type", jVarE.a().toString());
            com.meizu.cloud.pushsdk.b.g.c cVarA = com.meizu.cloud.pushsdk.b.g.g.a(com.meizu.cloud.pushsdk.b.g.g.a(httpURLConnection.getOutputStream()));
            jVarE.a(cVarA);
            cVarA.close();
        }
    }

    @Override // com.meizu.cloud.pushsdk.b.c.a
    public k a(i iVar) throws IOException {
        HttpURLConnection httpURLConnectionB = b(iVar);
        for (String str : iVar.d().b()) {
            String strA = iVar.a(str);
            com.meizu.cloud.pushsdk.b.a.a.b("current header name " + str + " value " + strA);
            httpURLConnectionB.addRequestProperty(str, strA);
        }
        a(httpURLConnectionB, iVar);
        return new k.a().a(httpURLConnectionB.getResponseCode()).a(iVar.d()).a(httpURLConnectionB.getResponseMessage()).a(iVar).a(a(httpURLConnectionB)).a();
    }

    protected HttpURLConnection a(URL url) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setInstanceFollowRedirects(HttpURLConnection.getFollowRedirects());
        return httpURLConnection;
    }
}
