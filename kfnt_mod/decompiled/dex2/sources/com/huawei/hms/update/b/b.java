package com.huawei.hms.update.b;

import android.content.Context;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.IOUtils;
import com.mbridge.msdk.foundation.download.Command;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b implements d {
    private HttpURLConnection a;
    private volatile int b = -1;

    @Override // com.huawei.hms.update.b.d
    public void a() {
        this.b = -1;
        if (this.a != null) {
            this.a.disconnect();
        }
    }

    @Override // com.huawei.hms.update.b.d
    public void b() {
        this.b = 1;
    }

    @Override // com.huawei.hms.update.b.d
    public int a(String str, OutputStream outputStream, int i, int i2, Context context) throws Throwable {
        InputStream inputStream;
        InputStream inputStream2 = null;
        try {
            a(str, context);
            this.a.setRequestMethod("GET");
            if (i > 0) {
                this.a.addRequestProperty(Command.HTTP_HEADER_RANGE, "bytes=" + i + "-" + i2);
            }
            int responseCode = this.a.getResponseCode();
            if ((i <= 0 || responseCode != 206) && (i > 0 || responseCode != 200)) {
                inputStream = null;
            } else {
                inputStream = this.a.getInputStream();
                try {
                    a(new BufferedInputStream(inputStream, 4096), outputStream);
                    outputStream.flush();
                } catch (Throwable th) {
                    th = th;
                    inputStream2 = inputStream;
                    IOUtils.closeQuietly(inputStream2);
                    throw th;
                }
            }
            IOUtils.closeQuietly(inputStream);
            return responseCode;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private void a(String str, Context context) throws IOException {
        if (this.b == 0) {
            HMSLog.e("HttpRequestHelper", "Not allowed to repeat open http(s) connection.");
        }
        this.a = (HttpURLConnection) new URL(str).openConnection();
        if (this.a instanceof HttpsURLConnection) {
            c.a((HttpsURLConnection) this.a, context);
        }
        this.a.setConnectTimeout(30000);
        this.a.setReadTimeout(30000);
        this.a.setDoInput(true);
        this.a.setDoOutput(true);
        this.a.setUseCaches(false);
        this.a.setInstanceFollowRedirects(true);
        this.b = 0;
    }

    private void a(InputStream inputStream, OutputStream outputStream) throws IOException, a {
        byte[] bArr = new byte[4096];
        do {
            int i = inputStream.read(bArr);
            if (-1 == i) {
                return;
            } else {
                outputStream.write(bArr, 0, i);
            }
        } while (this.b != 1);
        throw new a("HTTP(s) request was canceled.");
    }
}
