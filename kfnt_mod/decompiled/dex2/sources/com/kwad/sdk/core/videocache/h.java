package com.kwad.sdk.core.videocache;

import android.text.TextUtils;
import com.mbridge.msdk.foundation.download.Command;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class h implements m {
    private final com.kwad.sdk.core.videocache.c.b a;
    private final com.kwad.sdk.core.videocache.a.b b;
    private n c;
    private HttpURLConnection d;
    private InputStream e;

    public h(h hVar) {
        this.c = hVar.c;
        this.a = hVar.a;
        this.b = hVar.b;
    }

    public h(String str, com.kwad.sdk.core.videocache.c.b bVar, com.kwad.sdk.core.videocache.a.b bVar2) {
        this.a = (com.kwad.sdk.core.videocache.c.b) j.a(bVar);
        this.b = (com.kwad.sdk.core.videocache.a.b) j.a(bVar2);
        n nVarA = bVar.a(str);
        this.c = nVarA == null ? new n(str, -2147483648L, l.a(str)) : nVarA;
    }

    private long a(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField("Content-Length");
        if (headerField == null) {
            return -1L;
        }
        return Long.parseLong(headerField);
    }

    private long a(HttpURLConnection httpURLConnection, long j, int i) {
        long jA = a(httpURLConnection);
        return i == 200 ? jA : i == 206 ? jA + j : this.c.b;
    }

    private HttpURLConnection a(long j, int i) throws NoSuchAlgorithmException, IOException, ProxyCacheException, KeyManagementException {
        String str;
        HttpURLConnection httpURLConnection;
        boolean z;
        String headerField = this.c.a;
        int i2 = 0;
        do {
            StringBuilder sb = new StringBuilder();
            sb.append("Open connection ");
            if (j > 0) {
                str = " with offset " + j;
            } else {
                str = "";
            }
            sb.append(str);
            sb.append(" to ");
            sb.append(headerField);
            com.kwad.sdk.core.d.a.a("HttpUrlSource", sb.toString());
            httpURLConnection = (HttpURLConnection) new URL(headerField).openConnection();
            com.kwad.sdk.core.network.m.a(httpURLConnection);
            a(httpURLConnection, headerField);
            if (j > 0) {
                httpURLConnection.setRequestProperty(Command.HTTP_HEADER_RANGE, "bytes=" + j + "-");
            }
            if (i > 0) {
                httpURLConnection.setConnectTimeout(i);
                httpURLConnection.setReadTimeout(i);
            }
            httpURLConnection.setRequestProperty("User-Agent", com.kwad.sdk.core.network.k.c());
            httpURLConnection.setRequestProperty("BrowserUa", com.kwad.sdk.core.network.k.d());
            httpURLConnection.setRequestProperty("SystemUa", com.kwad.sdk.core.network.k.a());
            int responseCode = httpURLConnection.getResponseCode();
            z = responseCode == 301 || responseCode == 302 || responseCode == 303;
            if (z) {
                headerField = httpURLConnection.getHeaderField("Location");
                i2++;
                httpURLConnection.disconnect();
            }
            if (i2 > 5) {
                throw new ProxyCacheException("Too many redirects: " + i2);
            }
        } while (z);
        return httpURLConnection;
    }

    private void a(HttpURLConnection httpURLConnection, String str) {
        for (Map.Entry<String, String> entry : this.b.a(str).entrySet()) {
            httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:20:0x008e A[PHI: r0 r3
  0x008e: PHI (r0v8 java.net.HttpURLConnection) = (r0v10 java.net.HttpURLConnection), (r0v11 java.net.HttpURLConnection) binds: [B:19:0x008c, B:7:0x0060] A[DONT_GENERATE, DONT_INLINE]
  0x008e: PHI (r3v4 java.io.InputStream) = (r3v12 java.io.InputStream), (r3v13 java.io.InputStream) binds: [B:19:0x008c, B:7:0x0060] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r2v8, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r3v9, types: [com.kwad.sdk.core.videocache.n, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.kwad.sdk.core.videocache.h] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void e() throws java.lang.Throwable {
        /*
            r7 = this;
            java.lang.String r0 = "HttpUrlSource"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Read content info from "
            r1.append(r2)
            com.kwad.sdk.core.videocache.n r2 = r7.c
            java.lang.String r2 = r2.a
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.kwad.sdk.core.d.a.a(r0, r1)
            r0 = 0
            r2 = 10000(0x2710, float:1.4013E-41)
            r3 = 0
            java.net.HttpURLConnection r0 = r7.a(r0, r2)     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6e
            long r1 = r7.a(r0)     // Catch: java.lang.Throwable -> L67 java.io.IOException -> L6f
            java.lang.String r4 = r0.getContentType()     // Catch: java.lang.Throwable -> L67 java.io.IOException -> L6f
            java.io.InputStream r5 = r0.getInputStream()     // Catch: java.lang.Throwable -> L67 java.io.IOException -> L6f
            com.kwad.sdk.core.videocache.n r3 = new com.kwad.sdk.core.videocache.n     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            com.kwad.sdk.core.videocache.n r6 = r7.c     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            java.lang.String r6 = r6.a     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            r3.<init>(r6, r1, r4)     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            r7.c = r3     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            com.kwad.sdk.core.videocache.c.b r1 = r7.a     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            com.kwad.sdk.core.videocache.n r2 = r7.c     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            java.lang.String r2 = r2.a     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            com.kwad.sdk.core.videocache.n r3 = r7.c     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            r1.a(r2, r3)     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            java.lang.String r1 = "HttpUrlSource"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            r2.<init>()     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            java.lang.String r3 = "Source info fetched: "
            r2.append(r3)     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            com.kwad.sdk.core.videocache.n r3 = r7.c     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            r2.append(r3)     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            com.kwad.sdk.core.d.a.a(r1, r2)     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65
            com.kwad.sdk.core.videocache.l.a(r5)
            if (r0 == 0) goto L91
            goto L8e
        L63:
            r1 = move-exception
            goto L92
        L65:
            r3 = r5
            goto L6f
        L67:
            r1 = move-exception
            r5 = r3
            goto L92
        L6a:
            r1 = move-exception
            r0 = r3
            r5 = r0
            goto L92
        L6e:
            r0 = r3
        L6f:
            java.lang.String r1 = "HttpUrlSource"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L67
            r2.<init>()     // Catch: java.lang.Throwable -> L67
            java.lang.String r4 = "Error fetching info from "
            r2.append(r4)     // Catch: java.lang.Throwable -> L67
            com.kwad.sdk.core.videocache.n r4 = r7.c     // Catch: java.lang.Throwable -> L67
            java.lang.String r4 = r4.a     // Catch: java.lang.Throwable -> L67
            r2.append(r4)     // Catch: java.lang.Throwable -> L67
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L67
            com.kwad.sdk.core.d.a.e(r1, r2)     // Catch: java.lang.Throwable -> L67
            com.kwad.sdk.core.videocache.l.a(r3)
            if (r0 == 0) goto L91
        L8e:
            r0.disconnect()
        L91:
            return
        L92:
            com.kwad.sdk.core.videocache.l.a(r5)
            if (r0 == 0) goto L9a
            r0.disconnect()
        L9a:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.core.videocache.h.e():void");
    }

    @Override // com.kwad.sdk.core.videocache.m
    public int a(byte[] bArr) throws ProxyCacheException {
        if (this.e == null) {
            throw new ProxyCacheException("Error reading data from " + this.c.a + ": connection is absent!");
        }
        try {
            return this.e.read(bArr, 0, bArr.length);
        } catch (InterruptedIOException e) {
            throw new InterruptedProxyCacheException("Reading source " + this.c.a + " is interrupted", e);
        } catch (IOException e2) {
            throw new ProxyCacheException("Error reading data from " + this.c.a, e2);
        }
    }

    @Override // com.kwad.sdk.core.videocache.m
    public synchronized long a() {
        if (this.c.b == -2147483648L) {
            e();
        }
        return this.c.b;
    }

    @Override // com.kwad.sdk.core.videocache.m
    public void a(long j) throws ProxyCacheException {
        try {
            this.d = a(j, -1);
            String contentType = this.d.getContentType();
            this.e = new BufferedInputStream(this.d.getInputStream(), 8192);
            this.c = new n(this.c.a, a(this.d, j, this.d.getResponseCode()), contentType);
            this.a.a(this.c.a, this.c);
        } catch (IOException e) {
            throw new ProxyCacheException("Error opening connection for " + this.c.a + " with offset " + j, e);
        }
    }

    @Override // com.kwad.sdk.core.videocache.m
    public void b() {
        if (this.d != null) {
            try {
                this.d.disconnect();
            } catch (ArrayIndexOutOfBoundsException unused) {
                com.kwad.sdk.core.d.a.e("HttpUrlSource", "Error closing connection correctly. Should happen only on Android L. If anybody know how to fix it, please visit https://github.com/danikula/AndroidVideoCache/issues/88. Until good solution is not know, just ignore this issue.");
            } catch (IllegalArgumentException | NullPointerException e) {
                throw new RuntimeException("Wait... but why? WTF!? Really shouldn't happen any more after fixing https://github.com/danikula/AndroidVideoCache/issues/43. If you read it on your device log, please, notify me danikula@gmail.com or create issue here https://github.com/danikula/AndroidVideoCache/issues.", e);
            }
        }
    }

    public synchronized String c() {
        if (TextUtils.isEmpty(this.c.c)) {
            e();
        }
        return this.c.c;
    }

    public String d() {
        return this.c.a;
    }

    public String toString() {
        return "HttpUrlSource{sourceInfo='" + this.c + "}";
    }
}
