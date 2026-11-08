package com.appsflyer;

import android.content.Context;
import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class d implements InstallReferrerStateListener {

    /* renamed from: ˊ, reason: contains not printable characters */
    private InstallReferrerClient f174;

    /* renamed from: ॱ, reason: contains not printable characters */
    private a f175;

    d() {
    }

    /* renamed from: ॱ, reason: contains not printable characters */
    protected final void m147(Context context, a aVar) {
        this.f175 = aVar;
        this.f174 = InstallReferrerClient.newBuilder(context).build();
        try {
            this.f174.startConnection(this);
        } catch (Exception e) {
            AFLogger.afErrorLog("referrerClient -> startConnection", e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0071 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:? A[RETURN, SYNTHETIC] */
    @Override // com.android.installreferrer.api.InstallReferrerStateListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onInstallReferrerSetupFinished(int r6) {
        /*
            r5 = this;
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.lang.String r1 = "code"
            java.lang.String r2 = java.lang.String.valueOf(r6)
            r0.put(r1, r2)
            r1 = 0
            switch(r6) {
                case 0: goto L24;
                case 1: goto L1e;
                case 2: goto L18;
                default: goto L12;
            }
        L12:
            java.lang.String r6 = "responseCode not found."
            com.appsflyer.AFLogger.afWarnLog(r6)
            goto L6f
        L18:
            java.lang.String r6 = "InstallReferrer not supported"
            com.appsflyer.AFLogger.afWarnLog(r6)
            goto L6f
        L1e:
            java.lang.String r6 = "InstallReferrer not supported"
            com.appsflyer.AFLogger.afWarnLog(r6)
            goto L6f
        L24:
            java.lang.String r6 = "InstallReferrer connected"
            com.appsflyer.AFLogger.afDebugLog(r6)     // Catch: java.lang.Throwable -> L50
            com.android.installreferrer.api.InstallReferrerClient r6 = r5.f174     // Catch: java.lang.Throwable -> L50
            boolean r6 = r6.isReady()     // Catch: java.lang.Throwable -> L50
            if (r6 == 0) goto L43
            com.android.installreferrer.api.InstallReferrerClient r6 = r5.f174     // Catch: java.lang.Throwable -> L50
            com.android.installreferrer.api.ReferrerDetails r6 = r6.getInstallReferrer()     // Catch: java.lang.Throwable -> L50
            com.android.installreferrer.api.InstallReferrerClient r1 = r5.f174     // Catch: java.lang.Throwable -> L3e
            r1.endConnection()     // Catch: java.lang.Throwable -> L3e
            r1 = r6
            goto L6f
        L3e:
            r1 = move-exception
            r4 = r1
            r1 = r6
            r6 = r4
            goto L51
        L43:
            java.lang.String r6 = "ReferrerClient: InstallReferrer is not ready"
            com.appsflyer.AFLogger.afWarnLog(r6)     // Catch: java.lang.Throwable -> L50
            java.lang.String r6 = "err"
            java.lang.String r2 = "ReferrerClient: InstallReferrer is not ready"
            r0.put(r6, r2)     // Catch: java.lang.Throwable -> L50
            goto L6f
        L50:
            r6 = move-exception
        L51:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Failed to get install referrer: "
            r2.<init>(r3)
            java.lang.String r3 = r6.getMessage()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.appsflyer.AFLogger.afWarnLog(r2)
            java.lang.String r2 = "err"
            java.lang.String r6 = r6.getMessage()
            r0.put(r2, r6)
        L6f:
            if (r1 == 0) goto Lb4
            java.lang.String r6 = r1.getInstallReferrer()     // Catch: java.lang.Exception -> L9b
            if (r6 == 0) goto L80
            java.lang.String r6 = "val"
            java.lang.String r2 = r1.getInstallReferrer()     // Catch: java.lang.Exception -> L9b
            r0.put(r6, r2)     // Catch: java.lang.Exception -> L9b
        L80:
            java.lang.String r6 = "clk"
            long r2 = r1.getReferrerClickTimestampSeconds()     // Catch: java.lang.Exception -> L9b
            java.lang.String r2 = java.lang.Long.toString(r2)     // Catch: java.lang.Exception -> L9b
            r0.put(r6, r2)     // Catch: java.lang.Exception -> L9b
            java.lang.String r6 = "install"
            long r1 = r1.getInstallBeginTimestampSeconds()     // Catch: java.lang.Exception -> L9b
            java.lang.String r1 = java.lang.Long.toString(r1)     // Catch: java.lang.Exception -> L9b
            r0.put(r6, r1)     // Catch: java.lang.Exception -> L9b
            goto Lb4
        L9b:
            r6 = move-exception
            r6.printStackTrace()
            java.lang.String r6 = "val"
            java.lang.String r1 = "-1"
            r0.put(r6, r1)
            java.lang.String r6 = "clk"
            java.lang.String r1 = "-1"
            r0.put(r6, r1)
            java.lang.String r6 = "install"
            java.lang.String r1 = "-1"
            r0.put(r6, r1)
        Lb4:
            com.appsflyer.a r6 = r5.f175
            if (r6 == 0) goto Lbd
            com.appsflyer.a r6 = r5.f175
            r6.onHandleReferrer(r0)
        Lbd:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.d.onInstallReferrerSetupFinished(int):void");
    }

    @Override // com.android.installreferrer.api.InstallReferrerStateListener
    public final void onInstallReferrerServiceDisconnected() {
        AFLogger.afDebugLog("Install Referrer service disconnected");
    }
}
