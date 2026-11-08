package com.unicom.xiaowo.login.c;

import android.text.TextUtils;
import com.umeng.message.util.HttpRequest;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class k {
    private static String a(InputStream inputStream) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[com.ss.union.game.sdk.feedback.picture.select.d.d];
            while (true) {
                int i = inputStream.read(bArr);
                if (i == -1) {
                    byteArrayOutputStream.close();
                    inputStream.close();
                    return new String(byteArrayOutputStream.toByteArray());
                }
                byteArrayOutputStream.write(bArr, 0, i);
            }
        } catch (Exception unused) {
            return null;
        }
    }

    public final String a(String str, String str2, HashMap map) throws Throwable {
        HttpsURLConnection httpsURLConnection;
        String str3;
        String str4;
        try {
            com.unicom.xiaowo.login.d.f.a(str);
            httpsURLConnection = (HttpsURLConnection) new URL(str).openConnection();
            try {
                httpsURLConnection.setDoInput(true);
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setUseCaches(false);
                httpsURLConnection.setInstanceFollowRedirects(true);
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setReadTimeout(30000);
                httpsURLConnection.setConnectTimeout(10000);
                httpsURLConnection.setHostnameVerifier(new l(this));
                if (str == null || str.contains("token")) {
                    str3 = "Content-Type";
                    str4 = HttpRequest.CONTENT_TYPE_FORM;
                } else {
                    str3 = "Content-Type";
                    str4 = "application/json;charset=utf-8";
                }
                httpsURLConnection.setRequestProperty(str3, str4);
                httpsURLConnection.addRequestProperty("Connection", "close");
                if (map != null) {
                    for (String str5 : map.keySet()) {
                        httpsURLConnection.setRequestProperty(str5, (String) map.get(str5));
                    }
                }
                httpsURLConnection.connect();
                if (!TextUtils.isEmpty(str2)) {
                    DataOutputStream dataOutputStream = new DataOutputStream(httpsURLConnection.getOutputStream());
                    dataOutputStream.write(str2.getBytes());
                    dataOutputStream.flush();
                    dataOutputStream.close();
                }
                String strA = httpsURLConnection.getResponseCode() == 200 ? a(httpsURLConnection.getInputStream()) : null;
                if (httpsURLConnection != null) {
                    httpsURLConnection.disconnect();
                }
                return strA;
            } catch (Exception unused) {
                if (httpsURLConnection != null) {
                    httpsURLConnection.disconnect();
                }
                return null;
            } catch (Throwable th) {
                th = th;
                if (httpsURLConnection != null) {
                    httpsURLConnection.disconnect();
                }
                throw th;
            }
        } catch (Exception unused2) {
            httpsURLConnection = null;
        } catch (Throwable th2) {
            th = th2;
            httpsURLConnection = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00bd  */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.util.HashMap] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String a(java.lang.String r4, java.util.HashMap r5, android.net.Network r6) throws java.lang.Throwable {
        /*
            r3 = this;
            r0 = 0
            com.unicom.xiaowo.login.d.f.a(r4)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9c
            java.net.URL r1 = new java.net.URL     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9c
            r1.<init>(r4)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9c
            if (r6 == 0) goto L18
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9c
            r2 = 21
            if (r4 < r2) goto L18
            java.net.URLConnection r4 = r6.openConnection(r1)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9c
        L15:
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9c
            goto L1d
        L18:
            java.net.URLConnection r4 = r1.openConnection()     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9c
            goto L15
        L1d:
            r6 = 1
            r4.setDoInput(r6)     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lba
            r4.setDoOutput(r6)     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lba
            r1 = 0
            r4.setUseCaches(r1)     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lba
            r4.setInstanceFollowRedirects(r6)     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lba
            r6 = 10000(0x2710, float:1.4013E-41)
            r4.setConnectTimeout(r6)     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lba
            r4.setReadTimeout(r6)     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lba
            java.lang.String r6 = "GET"
            r4.setRequestMethod(r6)     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lba
            java.lang.String r6 = "Content-Type"
            java.lang.String r1 = "application/x-www-form-urlencoded"
            r4.setRequestProperty(r6, r1)     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lba
            if (r5 == 0) goto L5f
            java.util.Set r6 = r5.keySet()     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lba
            java.util.Iterator r6 = r6.iterator()     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lba
        L49:
            boolean r1 = r6.hasNext()     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lba
            if (r1 == 0) goto L5f
            java.lang.Object r1 = r6.next()     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lba
            java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lba
            java.lang.Object r2 = r5.get(r1)     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lba
            java.lang.String r2 = (java.lang.String) r2     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lba
            r4.setRequestProperty(r1, r2)     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lba
            goto L49
        L5f:
            java.lang.String r5 = "Connection"
            java.lang.String r6 = "close"
            r4.addRequestProperty(r5, r6)     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lba
            r4.connect()     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lba
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lba
            java.lang.String r6 = "getResponseCode:"
            r5.<init>(r6)     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lba
            int r6 = r4.getResponseCode()     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lba
            r5.append(r6)     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lba
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lba
            com.unicom.xiaowo.login.d.f.a(r5)     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lba
            int r5 = r4.getResponseCode()     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lba
            r6 = 200(0xc8, float:2.8E-43)
            if (r5 != r6) goto L8f
            java.io.InputStream r5 = r4.getInputStream()     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lba
            java.lang.String r5 = a(r5)     // Catch: java.lang.Exception -> L96 java.lang.Throwable -> Lba
            goto L90
        L8f:
            r5 = r0
        L90:
            if (r4 == 0) goto L95
            r4.disconnect()
        L95:
            return r5
        L96:
            r5 = move-exception
            goto L9f
        L98:
            r4 = move-exception
            r5 = r4
            r4 = r0
            goto Lbb
        L9c:
            r4 = move-exception
            r5 = r4
            r4 = r0
        L9f:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lba
            java.lang.String r1 = "requestGet error!"
            r6.<init>(r1)     // Catch: java.lang.Throwable -> Lba
            java.lang.String r5 = r5.getMessage()     // Catch: java.lang.Throwable -> Lba
            r6.append(r5)     // Catch: java.lang.Throwable -> Lba
            java.lang.String r5 = r6.toString()     // Catch: java.lang.Throwable -> Lba
            com.unicom.xiaowo.login.d.f.b(r5)     // Catch: java.lang.Throwable -> Lba
            if (r4 == 0) goto Lb9
            r4.disconnect()
        Lb9:
            return r0
        Lba:
            r5 = move-exception
        Lbb:
            if (r4 == 0) goto Lc0
            r4.disconnect()
        Lc0:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unicom.xiaowo.login.c.k.a(java.lang.String, java.util.HashMap, android.net.Network):java.lang.String");
    }
}
