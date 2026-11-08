package com.sigmob.sdk.base.common;

import android.net.Uri;
import android.os.AsyncTask;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.common.b.c;
import com.umeng.message.util.HttpRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class z extends AsyncTask<String, Void, String> {
    private final a a;

    public interface a {
        void a(String str);

        void a(String str, Throwable th);
    }

    private z(a aVar) {
        this.a = aVar;
    }

    private static String a(String str, HttpURLConnection httpURLConnection) throws URISyntaxException, IOException {
        URI uri = new URI(str);
        int responseCode = httpURLConnection.getResponseCode();
        String headerField = httpURLConnection.getHeaderField(HttpRequest.HEADER_LOCATION);
        if (responseCode < 300 || responseCode >= 400) {
            return null;
        }
        try {
            return uri.resolve(headerField).toString();
        } catch (IllegalArgumentException unused) {
            SigmobLog.e("Invalid URL redirection. baseUrl=" + str + "\n redirectUrl=" + headerField);
            throw new URISyntaxException(headerField, "Unable to parse invalid URL");
        } catch (Throwable th) {
            SigmobLog.e("Invalid URL redirection. baseUrl=" + str + "\n redirectUrl=" + headerField);
            throw th;
        }
    }

    public static void a(String str, a aVar) {
        try {
            new z(aVar).executeOnExecutor(c.a.b().a(), str);
        } catch (Throwable th) {
            aVar.a("Failed to resolve url", th);
        }
    }

    private String b(String str) throws Throwable {
        HttpURLConnection httpURLConnection;
        try {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection.setInstanceFollowRedirects(false);
                httpURLConnection.addRequestProperty(com.sigmob.sdk.common.e.i.USER_AGENT.a(), com.sigmob.sdk.common.e.f.c());
                String strA = a(str, httpURLConnection);
                if (httpURLConnection != null) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable unused) {
                            SigmobLog.d("IOException when closing httpUrlConnection. Ignoring.");
                        }
                    }
                    httpURLConnection.disconnect();
                }
                return strA;
            } catch (Throwable th) {
                th = th;
                if (httpURLConnection != null) {
                    InputStream inputStream2 = httpURLConnection.getInputStream();
                    if (inputStream2 != null) {
                        try {
                            inputStream2.close();
                        } catch (Throwable unused2) {
                            SigmobLog.d("IOException when closing httpUrlConnection. Ignoring.");
                        }
                    }
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            httpURLConnection = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public String doInBackground(String... strArr) {
        Throwable th;
        String str;
        String str2 = null;
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        try {
            String strB = strArr[0];
            while (true) {
                String str3 = str2;
                str2 = strB;
                str = str3;
                if (str2 == null) {
                    break;
                }
                try {
                    if (!x.OPEN_WITH_BROWSER.a(Uri.parse(str2))) {
                        return str2;
                    }
                    strB = b(str2);
                } catch (Throwable th2) {
                    th = th2;
                    SigmobLog.w(th.getMessage());
                    return str;
                }
            }
        } catch (Throwable th3) {
            String str4 = str2;
            th = th3;
            str = str4;
        }
    }

    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(String str) {
        super.onPostExecute(str);
        if (isCancelled() || str == null) {
            onCancelled();
        } else {
            this.a.a(str);
        }
    }

    @Override // android.os.AsyncTask
    public void onCancelled() {
        super.onCancelled();
        this.a.a("Task for resolving url was cancelled", null);
    }
}
