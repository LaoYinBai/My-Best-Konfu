package com.mbridge.msdk.click;

import android.text.TextUtils;
import android.webkit.URLUtil;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.s;
import java.net.HttpURLConnection;
import java.net.URL;

/* compiled from: JavaHttpSpider.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f {
    private static final String a = "f";
    private com.mbridge.msdk.b.a b;
    private String c;
    private boolean d = true;
    private final int e = 3145728;
    private a f;

    public f() {
        this.b = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
        if (this.b == null) {
            this.b = com.mbridge.msdk.b.b.a().b();
        }
    }

    /* compiled from: JavaHttpSpider.java */
    public static class a {
        public String a;
        public String b;
        public String c;
        public String d;
        public int e;
        public int f;
        public String g;
        public String h;

        public final String toString() {
            return "http响应头：...\nstatusCode=" + this.f + ", location=" + this.a + ", contentType=" + this.b + ", contentLength=" + this.e + ", contentEncoding=" + this.c + ", referer=" + this.d;
        }

        public final String a() {
            return "statusCode=" + this.f + ", location=" + this.a + ", contentType=" + this.b + ", contentLength=" + this.e + ", contentEncoding=" + this.c + ", referer=" + this.d;
        }
    }

    public final a a(String str, boolean z, boolean z2, CampaignEx campaignEx) throws Throwable {
        HttpURLConnection httpURLConnection;
        byte[] bytes;
        HttpURLConnection httpURLConnection2 = null;
        if (!URLUtil.isNetworkUrl(str)) {
            return null;
        }
        String strReplace = str.replace(" ", "%20");
        URLUtil.isHttpsUrl(strReplace);
        s.b(a, strReplace);
        this.f = new a();
        try {
            try {
                httpURLConnection = (HttpURLConnection) new URL(strReplace).openConnection();
                try {
                    try {
                        httpURLConnection.setRequestMethod("GET");
                        if ((!z && !z2) || campaignEx == null) {
                            httpURLConnection.setRequestProperty("User-Agent", o.w());
                        }
                        if (z && campaignEx != null && campaignEx.getcUA() == 1) {
                            httpURLConnection.setRequestProperty("User-Agent", o.w());
                        }
                        if (z2 && campaignEx != null && campaignEx.getImpUA() == 1) {
                            httpURLConnection.setRequestProperty("User-Agent", o.w());
                        }
                        httpURLConnection.setRequestProperty("Accept-Encoding", "gzip");
                        if (this.b.K() && !TextUtils.isEmpty(this.c)) {
                            httpURLConnection.setRequestProperty("referer", this.c);
                        }
                        httpURLConnection.setConnectTimeout(60000);
                        httpURLConnection.setReadTimeout(60000);
                        httpURLConnection.setInstanceFollowRedirects(false);
                        httpURLConnection.connect();
                        this.f.a = httpURLConnection.getHeaderField("Location");
                        this.f.d = httpURLConnection.getHeaderField("Referer");
                        this.f.f = httpURLConnection.getResponseCode();
                        this.f.b = httpURLConnection.getContentType();
                        this.f.e = httpURLConnection.getContentLength();
                        this.f.c = httpURLConnection.getContentEncoding();
                        s.b(a, this.f.toString());
                        boolean zEqualsIgnoreCase = "gzip".equalsIgnoreCase(this.f.c);
                        if (this.f.f == 200 && this.d && this.f.e > 0 && this.f.e < 3145728 && !TextUtils.isEmpty(strReplace) && !strReplace.endsWith(".apk")) {
                            try {
                                String strA = a(httpURLConnection.getInputStream(), zEqualsIgnoreCase);
                                if (!TextUtils.isEmpty(strA) && (bytes = strA.getBytes()) != null && bytes.length > 0 && bytes.length < 3145728) {
                                    this.f.g = strA.trim();
                                }
                            } catch (Throwable unused) {
                            }
                        }
                        this.c = strReplace;
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        return this.f;
                    } catch (Throwable th) {
                        th = th;
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    httpURLConnection2 = httpURLConnection;
                    this.f.h = th.getMessage();
                    s.c("http jump", "connecting");
                    a aVar = this.f;
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    return aVar;
                }
            } catch (Throwable th3) {
                th = th3;
                httpURLConnection = httpURLConnection2;
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0034 A[Catch: all -> 0x000f, TryCatch #5 {all -> 0x000f, blocks: (B:4:0x0008, B:9:0x0013, B:20:0x0030, B:22:0x0034, B:23:0x0043), top: B:42:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0048 A[Catch: Exception -> 0x004c, TRY_ENTER, TRY_LEAVE, TryCatch #1 {Exception -> 0x004c, blocks: (B:14:0x0027, B:25:0x0048), top: B:38:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0057 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String a(java.io.InputStream r4, boolean r5) throws java.lang.Throwable {
        /*
            r3 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            if (r5 == 0) goto L13
            java.util.zip.GZIPInputStream r5 = new java.util.zip.GZIPInputStream     // Catch: java.lang.Throwable -> Lf java.lang.Exception -> L11
            r5.<init>(r4)     // Catch: java.lang.Throwable -> Lf java.lang.Exception -> L11
            r4 = r5
            goto L13
        Lf:
            r4 = move-exception
            goto L55
        L11:
            r4 = move-exception
            goto L30
        L13:
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> Lf java.lang.Exception -> L11
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> Lf java.lang.Exception -> L11
            r2.<init>(r4)     // Catch: java.lang.Throwable -> Lf java.lang.Exception -> L11
            r5.<init>(r2)     // Catch: java.lang.Throwable -> Lf java.lang.Exception -> L11
        L1d:
            java.lang.String r4 = r5.readLine()     // Catch: java.lang.Throwable -> L2b java.lang.Exception -> L2e
            if (r4 == 0) goto L27
            r0.append(r4)     // Catch: java.lang.Throwable -> L2b java.lang.Exception -> L2e
            goto L1d
        L27:
            r5.close()     // Catch: java.lang.Exception -> L4c
            goto L50
        L2b:
            r4 = move-exception
            r1 = r5
            goto L55
        L2e:
            r4 = move-exception
            r1 = r5
        L30:
            com.mbridge.msdk.click.f$a r5 = r3.f     // Catch: java.lang.Throwable -> Lf
            if (r5 != 0) goto L43
            com.mbridge.msdk.click.f$a r5 = new com.mbridge.msdk.click.f$a     // Catch: java.lang.Throwable -> Lf
            r5.<init>()     // Catch: java.lang.Throwable -> Lf
            r3.f = r5     // Catch: java.lang.Throwable -> Lf
            com.mbridge.msdk.click.f$a r5 = r3.f     // Catch: java.lang.Throwable -> Lf
            java.lang.String r2 = r4.getMessage()     // Catch: java.lang.Throwable -> Lf
            r5.h = r2     // Catch: java.lang.Throwable -> Lf
        L43:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> Lf
            if (r1 == 0) goto L50
            r1.close()     // Catch: java.lang.Exception -> L4c
            goto L50
        L4c:
            r4 = move-exception
            r4.printStackTrace()
        L50:
            java.lang.String r4 = r0.toString()
            return r4
        L55:
            if (r1 == 0) goto L5f
            r1.close()     // Catch: java.lang.Exception -> L5b
            goto L5f
        L5b:
            r5 = move-exception
            r5.printStackTrace()
        L5f:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.click.f.a(java.io.InputStream, boolean):java.lang.String");
    }
}
