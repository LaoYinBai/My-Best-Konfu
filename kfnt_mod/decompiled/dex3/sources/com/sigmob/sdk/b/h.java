package com.sigmob.sdk.b;

import android.text.TextUtils;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.common.Constants;
import com.umeng.message.util.HttpRequest;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class h implements p {
    private final com.sigmob.sdk.b.c.c a;
    private final com.sigmob.sdk.b.b.b b;
    private q c;
    private HttpURLConnection d;
    private InputStream e;

    public h(h hVar) {
        this.c = hVar.c;
        this.a = hVar.a;
        this.b = hVar.b;
    }

    public h(String str) {
        this(str, com.sigmob.sdk.b.c.d.a());
    }

    public h(String str, com.sigmob.sdk.b.c.c cVar) {
        this(str, cVar, new com.sigmob.sdk.b.b.a());
    }

    public h(String str, com.sigmob.sdk.b.c.c cVar, com.sigmob.sdk.b.b.b bVar) {
        this.a = (com.sigmob.sdk.b.c.c) l.a(cVar);
        this.b = (com.sigmob.sdk.b.b.b) l.a(bVar);
        q qVarA = cVar.a(str);
        this.c = qVarA == null ? new q(str, -2147483648L, o.a(str)) : qVarA;
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

    private HttpURLConnection a(long j, int i) throws n, IOException {
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
            SigmobLog.d(sb.toString());
            httpURLConnection = (HttpURLConnection) new URL(headerField).openConnection();
            a(httpURLConnection, headerField);
            if (j > 0) {
                httpURLConnection.setRequestProperty(Constants.RANGE, Constants.RANGE_PARAMS + j + com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SERVER);
            }
            if (i > 0) {
                httpURLConnection.setConnectTimeout(i);
                httpURLConnection.setReadTimeout(i);
            }
            int responseCode = httpURLConnection.getResponseCode();
            z = responseCode == 301 || responseCode == 302 || responseCode == 303;
            if (z) {
                headerField = httpURLConnection.getHeaderField(HttpRequest.HEADER_LOCATION);
                i2++;
                httpURLConnection.disconnect();
            }
            if (i2 > 5) {
                throw new n("Too many redirects: " + i2);
            }
        } while (z);
        return httpURLConnection;
    }

    private void a(HttpURLConnection httpURLConnection, String str) {
        for (Map.Entry<String, String> entry : this.b.a(str).entrySet()) {
            httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
        }
    }

    private void e() throws Throwable {
        HttpURLConnection httpURLConnectionA;
        InputStream inputStream;
        long jA;
        String contentType;
        SigmobLog.d("Read content info from " + this.c.a);
        InputStream inputStream2 = null;
        try {
            httpURLConnectionA = a(0L, 10000);
            try {
                try {
                    jA = a(httpURLConnectionA);
                    contentType = httpURLConnectionA.getContentType();
                    inputStream = httpURLConnectionA.getInputStream();
                } catch (IOException e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
                inputStream = inputStream2;
            }
            try {
                this.c = new q(this.c.a, jA, contentType);
                this.a.a(this.c.a, this.c);
                SigmobLog.d("Source info fetched: " + this.c);
                o.a(inputStream);
                if (httpURLConnectionA == null) {
                    return;
                }
            } catch (IOException e2) {
                e = e2;
                inputStream2 = inputStream;
                SigmobLog.e("Error fetching info from " + this.c.a, e);
                o.a(inputStream2);
                if (httpURLConnectionA == null) {
                    return;
                }
                httpURLConnectionA.disconnect();
            } catch (Throwable th2) {
                th = th2;
                o.a(inputStream);
                if (httpURLConnectionA != null) {
                    httpURLConnectionA.disconnect();
                }
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            httpURLConnectionA = null;
        } catch (Throwable th3) {
            th = th3;
            httpURLConnectionA = null;
            inputStream = null;
        }
        httpURLConnectionA.disconnect();
    }

    @Override // com.sigmob.sdk.b.p
    public int a(byte[] bArr) throws n {
        if (this.e == null) {
            throw new n("Error reading data from " + this.c.a + ": connection is absent!");
        }
        try {
            return this.e.read(bArr, 0, bArr.length);
        } catch (InterruptedIOException e) {
            throw new j("Reading source " + this.c.a + " is interrupted", e);
        } catch (IOException e2) {
            throw new n("Error reading data from " + this.c.a, e2);
        }
    }

    @Override // com.sigmob.sdk.b.p
    public synchronized long a() {
        if (this.c.b == -2147483648L) {
            e();
        }
        return this.c.b;
    }

    @Override // com.sigmob.sdk.b.p
    public void a(long j) throws n {
        try {
            this.d = a(j, -1);
            String contentType = this.d.getContentType();
            this.e = new BufferedInputStream(this.d.getInputStream(), 8192);
            this.c = new q(this.c.a, a(this.d, j, this.d.getResponseCode()), contentType);
            this.a.a(this.c.a, this.c);
        } catch (IOException e) {
            throw new n("Error opening connection for " + this.c.a + " with offset " + j, e);
        }
    }

    @Override // com.sigmob.sdk.b.p
    public void b() {
        if (this.d != null) {
            try {
                this.d.disconnect();
            } catch (ArrayIndexOutOfBoundsException e) {
                SigmobLog.e("Error closing connection correctly. Should happen only on Android L. If anybody know how to fix it, please visit https://github.com/danikula/AndroidVideoCache/issues/88. Until good solution is not know, just ignore this issue :(", e);
            } catch (IllegalArgumentException | NullPointerException e2) {
                throw new RuntimeException("Wait... but why? WTF!? Really shouldn't happen any more after fixing https://github.com/danikula/AndroidVideoCache/issues/43. If you read it on your device log, please, notify me danikula@gmail.com or create issue here https://github.com/danikula/AndroidVideoCache/issues.", e2);
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
