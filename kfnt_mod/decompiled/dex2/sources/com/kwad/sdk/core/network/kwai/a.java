package com.kwad.sdk.core.network.kwai;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.huawei.hms.framework.common.ContainerUtils;
import com.kwad.sdk.core.network.c;
import com.kwad.sdk.core.network.f;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    public static c a(String str, Map<String, String> map) {
        return a(str, map, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00b6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static com.kwad.sdk.core.network.c a(java.lang.String r4, java.util.Map<java.lang.String, java.lang.String> r5, java.lang.String r6, boolean r7) throws java.lang.Throwable {
        /*
            com.kwad.sdk.core.network.c r0 = new com.kwad.sdk.core.network.c
            r0.<init>()
            r1 = 0
            java.net.URL r2 = new java.net.URL     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> La2
            r2.<init>(r4)     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> La2
            java.net.URLConnection r4 = r2.openConnection()     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> La2
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4     // Catch: java.lang.Throwable -> L9f java.lang.Exception -> La2
            com.kwad.sdk.core.network.m.a(r4)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a
            r2 = 1
            r4.setDoInput(r2)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a
            r4.setDoOutput(r2)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a
            java.lang.String r2 = "POST"
            r4.setRequestMethod(r2)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a
            if (r7 == 0) goto L2a
            java.lang.String r7 = "Content-Type"
            java.lang.String r2 = "application/json"
        L26:
            r4.setRequestProperty(r7, r2)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a
            goto L2f
        L2a:
            java.lang.String r7 = "Content-Type"
            java.lang.String r2 = "application/x-www-form-urlencoded"
            goto L26
        L2f:
            java.lang.String r7 = "User-Agent"
            java.lang.String r2 = com.kwad.sdk.core.network.k.c()     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a
            r4.setRequestProperty(r7, r2)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a
            java.lang.String r7 = "BrowserUa"
            java.lang.String r2 = com.kwad.sdk.core.network.k.d()     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a
            r4.setRequestProperty(r7, r2)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a
            java.lang.String r7 = "SystemUa"
            java.lang.String r2 = com.kwad.sdk.core.network.k.a()     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a
            r4.setRequestProperty(r7, r2)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a
            a(r4, r5)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a
            r5 = 5000(0x1388, float:7.006E-42)
            r4.setConnectTimeout(r5)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a
            r4.setReadTimeout(r5)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a
            r5 = 0
            r4.setUseCaches(r5)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a
            r4.connect()     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a
            boolean r5 = android.text.TextUtils.isEmpty(r6)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a
            if (r5 != 0) goto L7b
            java.io.OutputStream r5 = r4.getOutputStream()     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a
            byte[] r6 = r6.getBytes()     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L76
            r5.write(r6)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L76
            r5.flush()     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L76
            r1 = r5
            goto L7b
        L72:
            r6 = move-exception
            r1 = r5
            r5 = r6
            goto Lb4
        L76:
            r6 = move-exception
            r1 = r4
            r4 = r5
            r5 = r6
            goto La4
        L7b:
            int r5 = r4.getResponseCode()     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a
            r0.a = r5     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a
            r6 = 200(0xc8, float:2.8E-43)
            if (r5 != r6) goto L8f
            java.io.InputStream r5 = r4.getInputStream()     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a
            java.lang.String r5 = a(r5)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a
            r0.b = r5     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> L9a
        L8f:
            if (r4 == 0) goto L94
            r4.disconnect()
        L94:
            com.kwad.sdk.crash.utils.b.a(r1)
            goto Laf
        L98:
            r5 = move-exception
            goto Lb4
        L9a:
            r5 = move-exception
            r3 = r1
            r1 = r4
            r4 = r3
            goto La4
        L9f:
            r5 = move-exception
            r4 = r1
            goto Lb4
        La2:
            r5 = move-exception
            r4 = r1
        La4:
            a(r0, r5)     // Catch: java.lang.Throwable -> Lb0
            if (r1 == 0) goto Lac
            r1.disconnect()
        Lac:
            com.kwad.sdk.crash.utils.b.a(r4)
        Laf:
            return r0
        Lb0:
            r5 = move-exception
            r3 = r1
            r1 = r4
            r4 = r3
        Lb4:
            if (r4 == 0) goto Lb9
            r4.disconnect()
        Lb9:
            com.kwad.sdk.crash.utils.b.a(r1)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.core.network.kwai.a.a(java.lang.String, java.util.Map, java.lang.String, boolean):com.kwad.sdk.core.network.c");
    }

    public static c a(String str, Map<String, String> map, Map<String, String> map2) {
        String strSubstring;
        if (map2 != null) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : map2.entrySet()) {
                String strA = a(entry.getValue());
                sb.append(entry.getKey());
                sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
                sb.append(strA);
                sb.append(ContainerUtils.FIELD_DELIMITER);
            }
            strSubstring = sb.substring(0, sb.length() - 1);
        } else {
            strSubstring = null;
        }
        return a(str, map, strSubstring, false);
    }

    public static c a(String str, Map<String, String> map, JSONObject jSONObject) {
        return a(str, map, jSONObject != null ? jSONObject.toString() : null, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0095  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.kwad.sdk.core.network.c a(java.lang.String r5, java.util.Map<java.lang.String, java.lang.String> r6, boolean r7) throws java.lang.Throwable {
        /*
            com.kwad.sdk.core.network.c r0 = new com.kwad.sdk.core.network.c
            r0.<init>()
            r1 = 0
            java.net.URL r2 = new java.net.URL     // Catch: java.lang.Throwable -> L7f java.lang.Exception -> L82
            r2.<init>(r5)     // Catch: java.lang.Throwable -> L7f java.lang.Exception -> L82
            java.net.URLConnection r5 = r2.openConnection()     // Catch: java.lang.Throwable -> L7f java.lang.Exception -> L82
            java.net.HttpURLConnection r5 = (java.net.HttpURLConnection) r5     // Catch: java.lang.Throwable -> L7f java.lang.Exception -> L82
            com.kwad.sdk.core.network.m.a(r5)     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b
            a(r5, r6)     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b
            java.lang.String r6 = "GET"
            r5.setRequestMethod(r6)     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b
            java.lang.String r6 = "Accept"
            java.lang.String r2 = "application/json"
            r5.setRequestProperty(r6, r2)     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b
            java.lang.String r6 = "User-Agent"
            java.lang.String r2 = com.kwad.sdk.core.network.k.c()     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b
            r5.setRequestProperty(r6, r2)     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b
            java.lang.String r6 = "BrowserUa"
            java.lang.String r2 = com.kwad.sdk.core.network.k.d()     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b
            r5.setRequestProperty(r6, r2)     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b
            java.lang.String r6 = "SystemUa"
            java.lang.String r2 = com.kwad.sdk.core.network.k.a()     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b
            r5.setRequestProperty(r6, r2)     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b
            int r6 = r5.getResponseCode()     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b
            r0.a = r6     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b
            r6.<init>()     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b
            if (r7 == 0) goto L6a
            java.io.InputStream r7 = r5.getInputStream()     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b
            r1 = 1024(0x400, float:1.435E-42)
            byte[] r1 = new byte[r1]     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L68
        L53:
            int r2 = r7.read(r1)     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L68
            r3 = -1
            if (r2 == r3) goto L64
            java.lang.String r3 = new java.lang.String     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L68
            r4 = 0
            r3.<init>(r1, r4, r2)     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L68
            r6.append(r3)     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L68
            goto L53
        L64:
            r1 = r7
            goto L6a
        L66:
            r6 = move-exception
            goto L92
        L68:
            r6 = move-exception
            goto L7d
        L6a:
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b
            r0.b = r6     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b
            if (r5 == 0) goto L75
            r5.disconnect()
        L75:
            com.kwad.sdk.crash.utils.b.a(r1)
            goto L8f
        L79:
            r6 = move-exception
            goto L93
        L7b:
            r6 = move-exception
            r7 = r1
        L7d:
            r1 = r5
            goto L84
        L7f:
            r6 = move-exception
            r5 = r1
            goto L93
        L82:
            r6 = move-exception
            r7 = r1
        L84:
            a(r0, r6)     // Catch: java.lang.Throwable -> L90
            if (r1 == 0) goto L8c
            r1.disconnect()
        L8c:
            com.kwad.sdk.crash.utils.b.a(r7)
        L8f:
            return r0
        L90:
            r6 = move-exception
            r5 = r1
        L92:
            r1 = r7
        L93:
            if (r5 == 0) goto L98
            r5.disconnect()
        L98:
            com.kwad.sdk.crash.utils.b.a(r1)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.core.network.kwai.a.a(java.lang.String, java.util.Map, boolean):com.kwad.sdk.core.network.c");
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x0056 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0060 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String a(java.io.InputStream r6) throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L39
            r1.<init>()     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L39
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r2]     // Catch: java.io.IOException -> L32 java.lang.Throwable -> L53
        La:
            int r3 = r6.read(r2)     // Catch: java.io.IOException -> L32 java.lang.Throwable -> L53
            r4 = -1
            if (r3 == r4) goto L16
            r4 = 0
            r1.write(r2, r4, r3)     // Catch: java.io.IOException -> L32 java.lang.Throwable -> L53
            goto La
        L16:
            java.lang.String r2 = new java.lang.String     // Catch: java.io.IOException -> L32 java.lang.Throwable -> L53
            byte[] r3 = r1.toByteArray()     // Catch: java.io.IOException -> L32 java.lang.Throwable -> L53
            r2.<init>(r3)     // Catch: java.io.IOException -> L32 java.lang.Throwable -> L53
            if (r6 == 0) goto L29
            r6.close()     // Catch: java.io.IOException -> L25
            goto L29
        L25:
            r6 = move-exception
            com.kwad.sdk.core.d.a.b(r6)
        L29:
            r1.close()     // Catch: java.io.IOException -> L2d
            goto L31
        L2d:
            r6 = move-exception
            com.kwad.sdk.core.d.a.b(r6)
        L31:
            return r2
        L32:
            r2 = move-exception
            goto L3b
        L34:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L54
        L39:
            r2 = move-exception
            r1 = r0
        L3b:
            com.kwad.sdk.core.d.a.b(r2)     // Catch: java.lang.Throwable -> L53
            if (r6 == 0) goto L48
            r6.close()     // Catch: java.io.IOException -> L44
            goto L48
        L44:
            r6 = move-exception
            com.kwad.sdk.core.d.a.b(r6)
        L48:
            if (r1 == 0) goto L52
            r1.close()     // Catch: java.io.IOException -> L4e
            goto L52
        L4e:
            r6 = move-exception
            com.kwad.sdk.core.d.a.b(r6)
        L52:
            return r0
        L53:
            r0 = move-exception
        L54:
            if (r6 == 0) goto L5e
            r6.close()     // Catch: java.io.IOException -> L5a
            goto L5e
        L5a:
            r6 = move-exception
            com.kwad.sdk.core.d.a.b(r6)
        L5e:
            if (r1 == 0) goto L68
            r1.close()     // Catch: java.io.IOException -> L64
            goto L68
        L64:
            r6 = move-exception
            com.kwad.sdk.core.d.a.b(r6)
        L68:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.core.network.kwai.a.a(java.io.InputStream):java.lang.String");
    }

    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return URLEncoder.encode(str, C.UTF8_NAME);
        } catch (UnsupportedEncodingException e) {
            com.kwad.sdk.core.d.a.a(e);
            return "";
        }
    }

    private static void a(@NonNull c cVar, Exception exc) {
        f fVar;
        if (exc instanceof SocketTimeoutException) {
            cVar.a = f.a.n;
            fVar = f.a;
        } else {
            cVar.a = f.b.n;
            fVar = f.b;
        }
        cVar.b = fVar.o;
        if (com.kwad.sdk.b.c.booleanValue()) {
            com.kwad.sdk.core.d.a.b(exc);
        }
    }

    private static void a(HttpURLConnection httpURLConnection, Map<String, String> map) {
        if (map == null || httpURLConnection == null) {
            return;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
        }
    }
}
