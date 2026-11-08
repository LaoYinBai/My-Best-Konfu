package com.appsflyer;

import java.io.IOException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class OneLinkHttpTask implements Runnable {

    /* renamed from: ˊ, reason: contains not printable characters */
    private HttpsUrlConnectionProvider f156;

    /* renamed from: ˏ, reason: contains not printable characters */
    private AppsFlyerLib f157;

    /* renamed from: ॱ, reason: contains not printable characters */
    String f158;

    /* renamed from: ˊ */
    abstract void mo122(HttpsURLConnection httpsURLConnection) throws JSONException, IOException;

    /* renamed from: ˋ */
    abstract void mo123();

    /* renamed from: ˎ */
    abstract String mo124();

    /* renamed from: ˏ */
    abstract void mo125(String str);

    OneLinkHttpTask(AppsFlyerLib appsFlyerLib) {
        this.f157 = appsFlyerLib;
    }

    public void setConnProvider(HttpsUrlConnectionProvider httpsUrlConnectionProvider) {
        this.f156 = httpsUrlConnectionProvider;
    }

    public static class HttpsUrlConnectionProvider {
        /* renamed from: ˎ, reason: contains not printable characters */
        static HttpsURLConnection m126(String str) throws IOException {
            return (HttpsURLConnection) new URL(str).openConnection();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00c8  */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() {
        /*
            r10 = this;
            long r0 = java.lang.System.currentTimeMillis()
            r2 = 1000(0x3e8, double:4.94E-321)
            long r0 = r0 / r2
            java.lang.String r2 = ""
            java.lang.String r3 = ""
            java.lang.String r4 = r10.mo124()
            java.lang.String r5 = "oneLinkUrl: "
            java.lang.String r6 = java.lang.String.valueOf(r4)
            java.lang.String r5 = r5.concat(r6)
            com.appsflyer.AFLogger.afRDLog(r5)
            javax.net.ssl.HttpsURLConnection r5 = com.appsflyer.OneLinkHttpTask.HttpsUrlConnectionProvider.m126(r4)     // Catch: java.lang.Throwable -> L88
            java.lang.String r6 = "content-type"
            java.lang.String r7 = "application/json"
            r5.addRequestProperty(r6, r7)     // Catch: java.lang.Throwable -> L88
            java.lang.String r6 = "authorization"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L88
            r7.<init>()     // Catch: java.lang.Throwable -> L88
            com.appsflyer.AppsFlyerProperties r8 = com.appsflyer.AppsFlyerProperties.getInstance()     // Catch: java.lang.Throwable -> L88
            java.lang.String r9 = "AppsFlyerKey"
            java.lang.String r8 = r8.getString(r9)     // Catch: java.lang.Throwable -> L88
            r7.append(r8)     // Catch: java.lang.Throwable -> L88
            r7.append(r0)     // Catch: java.lang.Throwable -> L88
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L88
            java.lang.String r7 = com.appsflyer.t.m242(r7)     // Catch: java.lang.Throwable -> L88
            r5.addRequestProperty(r6, r7)     // Catch: java.lang.Throwable -> L88
            java.lang.String r6 = "af-timestamp"
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch: java.lang.Throwable -> L88
            r5.addRequestProperty(r6, r0)     // Catch: java.lang.Throwable -> L88
            r0 = 3000(0xbb8, float:4.204E-42)
            r5.setReadTimeout(r0)     // Catch: java.lang.Throwable -> L88
            r5.setConnectTimeout(r0)     // Catch: java.lang.Throwable -> L88
            r10.mo122(r5)     // Catch: java.lang.Throwable -> L88
            int r0 = r5.getResponseCode()     // Catch: java.lang.Throwable -> L88
            java.lang.String r1 = com.appsflyer.AppsFlyerLib.m68(r5)     // Catch: java.lang.Throwable -> L88
            r2 = 200(0xc8, float:2.8E-43)
            if (r0 != r2) goto L71
            java.lang.String r0 = "Status 200 ok"
            com.appsflyer.AFLogger.afInfoLog(r0)     // Catch: java.lang.Throwable -> L6f
            goto Lb1
        L6f:
            r0 = move-exception
            goto L8a
        L71:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6f
            java.lang.String r3 = "Response code = "
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L6f
            r2.append(r0)     // Catch: java.lang.Throwable -> L6f
            java.lang.String r0 = " content = "
            r2.append(r0)     // Catch: java.lang.Throwable -> L6f
            r2.append(r1)     // Catch: java.lang.Throwable -> L6f
            java.lang.String r3 = r2.toString()     // Catch: java.lang.Throwable -> L6f
            goto Lb1
        L88:
            r0 = move-exception
            r1 = r2
        L8a:
            java.lang.String r2 = "Error while calling "
            java.lang.String r3 = java.lang.String.valueOf(r4)
            java.lang.String r2 = r2.concat(r3)
            com.appsflyer.AFLogger.afErrorLog(r2, r0)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Error while calling "
            r2.<init>(r3)
            r2.append(r4)
            java.lang.String r3 = " stacktrace: "
            r2.append(r3)
            java.lang.String r0 = r0.toString()
            r2.append(r0)
            java.lang.String r3 = r2.toString()
        Lb1:
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 == 0) goto Lc8
            java.lang.String r0 = "Connection call succeeded: "
            java.lang.String r2 = java.lang.String.valueOf(r1)
            java.lang.String r0 = r0.concat(r2)
            com.appsflyer.AFLogger.afInfoLog(r0)
            r10.mo125(r1)
            return
        Lc8:
            java.lang.String r0 = "Connection error: "
            java.lang.String r1 = java.lang.String.valueOf(r3)
            java.lang.String r0 = r0.concat(r1)
            com.appsflyer.AFLogger.afWarnLog(r0)
            r10.mo123()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.OneLinkHttpTask.run():void");
    }
}
