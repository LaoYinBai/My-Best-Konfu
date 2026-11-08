package com.sigmob.sdk.base.common;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.sigmob.logger.SigmobLog;
import com.ss.android.socialbase.appdownloader.constants.Constants;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class m extends AsyncTask<String, Void, HashMap<String, String>> {
    private final a a;

    public interface a {
        void a(String str, String str2);

        void a(String str, Throwable th);
    }

    public static String a(String str) {
        if (!str.toLowerCase().contains(Constants.APK_SUFFIX)) {
            return null;
        }
        String strB = b(str);
        if (!TextUtils.isEmpty(strB)) {
            return strB;
        }
        String strC = c(str);
        if (TextUtils.isEmpty(strC)) {
            return null;
        }
        return strC;
    }

    private static String a(String str, HttpURLConnection httpURLConnection) throws URISyntaxException, IOException {
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode < 200 || responseCode >= 400) {
            return null;
        }
        try {
            return URLUtil.guessFileName(str, httpURLConnection.getHeaderField(DownloadUtils.CONTENT_DISPOSITION), null);
        } catch (IllegalArgumentException unused) {
            SigmobLog.e("Invalid URL redirection. baseUrl=" + str);
            throw new URISyntaxException(str, "Unable to parse invalid URL");
        } catch (NullPointerException e) {
            SigmobLog.e("Invalid URL redirection. baseUrl=" + str);
            throw e;
        }
    }

    public static String b(String str) {
        try {
            Matcher matcher = Pattern.compile("(fsname=)(.*?apk)", 2).matcher(str);
            if (matcher.find()) {
                return matcher.group(2);
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String c(String str) {
        try {
            Matcher matcher = Pattern.compile("[\\w\\.]+\\.apk", 2).matcher(str);
            if (matcher.find()) {
                return matcher.group(0);
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    private String d(String str) {
        String str2 = null;
        while (true) {
            String str3 = str2;
            str2 = str;
            if (str2 == null) {
                return str3;
            }
            try {
                str = e(str2);
            } catch (Throwable th) {
                SigmobLog.e(th.getMessage());
                return str2;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0032 A[PHI: r0 r5
  0x0032: PHI (r0v5 java.lang.String) = (r0v0 java.lang.String), (r0v6 java.lang.String) binds: [B:20:0x0044, B:11:0x0030] A[DONT_GENERATE, DONT_INLINE]
  0x0032: PHI (r5v8 ??) = (r5v6 ??), (r5v10 ??) binds: [B:20:0x0044, B:11:0x0030] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x004b  */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v10, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8, types: [java.net.HttpURLConnection] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String e(java.lang.String r5) throws java.lang.Throwable {
        /*
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch: java.lang.Throwable -> L38 java.lang.Throwable -> L3d
            r1.<init>(r5)     // Catch: java.lang.Throwable -> L38 java.lang.Throwable -> L3d
            java.net.URLConnection r5 = r1.openConnection()     // Catch: java.lang.Throwable -> L38 java.lang.Throwable -> L3d
            java.net.HttpURLConnection r5 = (java.net.HttpURLConnection) r5     // Catch: java.lang.Throwable -> L38 java.lang.Throwable -> L3d
            r1 = 0
            r5.setInstanceFollowRedirects(r1)     // Catch: java.lang.Throwable -> L36 java.lang.Throwable -> L48
            com.sigmob.sdk.common.e.i r1 = com.sigmob.sdk.common.e.i.USER_AGENT     // Catch: java.lang.Throwable -> L36 java.lang.Throwable -> L48
            java.lang.String r1 = r1.a()     // Catch: java.lang.Throwable -> L36 java.lang.Throwable -> L48
            java.lang.String r2 = com.sigmob.sdk.common.e.f.c()     // Catch: java.lang.Throwable -> L36 java.lang.Throwable -> L48
            r5.addRequestProperty(r1, r2)     // Catch: java.lang.Throwable -> L36 java.lang.Throwable -> L48
            int r1 = r5.getResponseCode()     // Catch: java.lang.Throwable -> L36 java.lang.Throwable -> L48
            java.lang.String r2 = "Location"
            java.lang.String r2 = r5.getHeaderField(r2)     // Catch: java.lang.Throwable -> L36 java.lang.Throwable -> L48
            r3 = 300(0x12c, float:4.2E-43)
            if (r1 < r3) goto L30
            r3 = 400(0x190, float:5.6E-43)
            if (r1 >= r3) goto L30
            r0 = r2
        L30:
            if (r5 == 0) goto L47
        L32:
            r5.disconnect()
            goto L47
        L36:
            r1 = move-exception
            goto L3f
        L38:
            r5 = move-exception
            r4 = r0
            r0 = r5
            r5 = r4
            goto L49
        L3d:
            r1 = move-exception
            r5 = r0
        L3f:
            java.lang.String r2 = "resolveRedirectLocation fail"
            com.sigmob.logger.SigmobLog.e(r2, r1)     // Catch: java.lang.Throwable -> L48
            if (r5 == 0) goto L47
            goto L32
        L47:
            return r0
        L48:
            r0 = move-exception
        L49:
            if (r5 == 0) goto L4e
            r5.disconnect()
        L4e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sigmob.sdk.base.common.m.e(java.lang.String):java.lang.String");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0094 A[PHI: r0 r1
  0x0094: PHI (r0v2 ??) = (r0v6 ?? I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), (r0v4 ??) binds: [B:38:0x00a4, B:29:0x0092] A[DONT_GENERATE, DONT_INLINE]
  0x0094: PHI (r1v4 java.net.HttpURLConnection) = (r1v3 java.net.HttpURLConnection), (r1v9 java.net.HttpURLConnection) binds: [B:38:0x00a4, B:29:0x0092] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00ab  */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String f(java.lang.String r7) throws java.lang.Throwable {
        /*
            r6 = this;
            r0 = 0
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Throwable -> L9a java.lang.Throwable -> L9d
            if (r1 != 0) goto Lf
            java.lang.String r7 = a(r7)     // Catch: java.lang.Throwable -> L9a java.lang.Throwable -> L9d
            r1 = r0
        Lc:
            r0 = r7
            goto L92
        Lf:
            java.net.URL r1 = new java.net.URL     // Catch: java.lang.Throwable -> L9a java.lang.Throwable -> L9d
            r1.<init>(r7)     // Catch: java.lang.Throwable -> L9a java.lang.Throwable -> L9d
            java.net.URLConnection r1 = r1.openConnection()     // Catch: java.lang.Throwable -> L9a java.lang.Throwable -> L9d
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch: java.lang.Throwable -> L9a java.lang.Throwable -> L9d
            r2 = 0
            r1.setInstanceFollowRedirects(r2)     // Catch: java.lang.Throwable -> L98 java.lang.Throwable -> La8
            com.sigmob.sdk.common.e.i r2 = com.sigmob.sdk.common.e.i.USER_AGENT     // Catch: java.lang.Throwable -> L98 java.lang.Throwable -> La8
            java.lang.String r2 = r2.a()     // Catch: java.lang.Throwable -> L98 java.lang.Throwable -> La8
            java.lang.String r3 = com.sigmob.sdk.common.e.f.c()     // Catch: java.lang.Throwable -> L98 java.lang.Throwable -> La8
            r1.addRequestProperty(r2, r3)     // Catch: java.lang.Throwable -> L98 java.lang.Throwable -> La8
            java.lang.String r2 = a(r7, r1)     // Catch: java.lang.Throwable -> L98 java.lang.Throwable -> La8
            if (r2 == 0) goto L4d
            java.lang.String r3 = "fsname="
            boolean r3 = r2.contains(r3)     // Catch: java.lang.Throwable -> L98 java.lang.Throwable -> La8
            if (r3 == 0) goto L3e
            java.lang.String r7 = b(r7)     // Catch: java.lang.Throwable -> L98 java.lang.Throwable -> La8
            goto Lc
        L3e:
            java.lang.String r7 = ".apk"
            boolean r7 = r2.contains(r7)     // Catch: java.lang.Throwable -> L98 java.lang.Throwable -> La8
            if (r7 == 0) goto L4b
            java.lang.String r7 = c(r2)     // Catch: java.lang.Throwable -> L98 java.lang.Throwable -> La8
            goto Lc
        L4b:
            r0 = r2
            goto L92
        L4d:
            boolean r3 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Throwable -> L98 java.lang.Throwable -> La8
            if (r3 != 0) goto L92
            android.net.Uri r3 = android.net.Uri.parse(r7)     // Catch: java.lang.Throwable -> L98 java.lang.Throwable -> La8
            java.lang.String r3 = r3.getPath()     // Catch: java.lang.Throwable -> L98 java.lang.Throwable -> La8
            java.lang.String r4 = "/"
            java.lang.String[] r3 = r3.split(r4)     // Catch: java.lang.Throwable -> L98 java.lang.Throwable -> La8
            int r4 = r3.length     // Catch: java.lang.Throwable -> L98 java.lang.Throwable -> La8
            r5 = 1
            if (r4 <= r5) goto L69
            int r2 = r3.length     // Catch: java.lang.Throwable -> L98 java.lang.Throwable -> La8
            int r2 = r2 - r5
            r2 = r3[r2]     // Catch: java.lang.Throwable -> L98 java.lang.Throwable -> La8
        L69:
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Throwable -> L98 java.lang.Throwable -> La8
            if (r3 != 0) goto L7b
            java.lang.String r2 = r2.toLowerCase()     // Catch: java.lang.Throwable -> L98 java.lang.Throwable -> La8
            java.lang.String r3 = ".apk"
            boolean r2 = r2.endsWith(r3)     // Catch: java.lang.Throwable -> L98 java.lang.Throwable -> La8
            if (r2 != 0) goto L92
        L7b:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L98 java.lang.Throwable -> La8
            r2.<init>()     // Catch: java.lang.Throwable -> L98 java.lang.Throwable -> La8
            java.lang.String r7 = com.sigmob.sdk.common.utils.k.a(r7)     // Catch: java.lang.Throwable -> L98 java.lang.Throwable -> La8
            r2.append(r7)     // Catch: java.lang.Throwable -> L98 java.lang.Throwable -> La8
            java.lang.String r7 = ".apk"
            r2.append(r7)     // Catch: java.lang.Throwable -> L98 java.lang.Throwable -> La8
            java.lang.String r7 = r2.toString()     // Catch: java.lang.Throwable -> L98 java.lang.Throwable -> La8
            goto Lc
        L92:
            if (r1 == 0) goto La7
        L94:
            r1.disconnect()
            goto La7
        L98:
            r7 = move-exception
            goto L9f
        L9a:
            r7 = move-exception
            r1 = r0
            goto La9
        L9d:
            r7 = move-exception
            r1 = r0
        L9f:
            java.lang.String r2 = "getDownloadUrlFilename"
            com.sigmob.logger.SigmobLog.e(r2, r7)     // Catch: java.lang.Throwable -> La8
            if (r1 == 0) goto La7
            goto L94
        La7:
            return r0
        La8:
            r7 = move-exception
        La9:
            if (r1 == 0) goto Lae
            r1.disconnect()
        Lae:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sigmob.sdk.base.common.m.f(java.lang.String):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public HashMap<String, String> doInBackground(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        HashMap<String, String> map = new HashMap<>();
        try {
            String strD = strArr[0];
            try {
                strD = d(strD);
            } catch (Throwable th) {
                SigmobLog.e(th.getMessage());
            }
            map.put("url", strD);
            map.put("fileName", f(strD));
        } catch (Throwable th2) {
            SigmobLog.e(th2.getMessage());
        }
        return map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(HashMap<String, String> map) {
        String str;
        String str2 = null;
        if (map != null) {
            str2 = map.get("fileName");
            str = map.get("url");
        } else {
            str = null;
        }
        this.a.a(str2, str);
    }

    @Override // android.os.AsyncTask
    protected void onCancelled() {
        super.onCancelled();
        this.a.a("Task for resolving url was cancelled", (Throwable) null);
    }
}
