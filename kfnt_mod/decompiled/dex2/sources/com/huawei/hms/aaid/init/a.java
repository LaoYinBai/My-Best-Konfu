package com.huawei.hms.aaid.init;

import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a implements Runnable {
    private Context a;

    a(Context context) {
        this.a = context;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:14:0x007d
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1178)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    @Override // java.lang.Runnable
    public void run() {
        /*
            r5 = this;
            android.content.Context r0 = r5.a     // Catch: java.lang.Exception -> L85
            com.huawei.hms.aaid.HmsInstanceId r0 = com.huawei.hms.aaid.HmsInstanceId.getInstance(r0)     // Catch: java.lang.Exception -> L85
            android.content.Context r1 = r5.a     // Catch: java.lang.Exception -> L85
            java.lang.String r1 = com.huawei.hms.utils.Util.getAppId(r1)     // Catch: java.lang.Exception -> L85
            java.lang.String r2 = "HCM"
            java.lang.String r0 = r0.getToken(r1, r2)     // Catch: java.lang.Exception -> L85
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Exception -> L85
            if (r1 != 0) goto L8d
            java.lang.String r1 = "AutoInit"
            java.lang.String r2 = "Push init succeed"
            com.huawei.hms.support.log.HMSLog.i(r1, r2)     // Catch: java.lang.Exception -> L85
            android.content.Context r1 = r5.a     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L7d java.lang.Exception -> L85
            android.content.pm.PackageManager r1 = r1.getPackageManager()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L7d java.lang.Exception -> L85
            android.content.Context r2 = r5.a     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L7d java.lang.Exception -> L85
            java.lang.String r2 = r2.getPackageName()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L7d java.lang.Exception -> L85
            r3 = 128(0x80, float:1.8E-43)
            android.content.pm.ApplicationInfo r1 = r1.getApplicationInfo(r2, r3)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L7d java.lang.Exception -> L85
            android.os.Bundle r2 = r1.metaData     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L7d java.lang.Exception -> L85
            if (r2 == 0) goto L75
            android.os.Bundle r1 = r1.metaData     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L7d java.lang.Exception -> L85
            java.lang.String r2 = "com.huawei.hms.client.service.name:push"
            java.lang.String r1 = r1.getString(r2)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L7d java.lang.Exception -> L85
            if (r1 == 0) goto L75
            android.content.Intent r1 = new android.content.Intent     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L7d java.lang.Exception -> L85
            java.lang.String r2 = "com.huawei.push.action.MESSAGING_EVENT"
            r1.<init>(r2)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L7d java.lang.Exception -> L85
            android.content.Context r2 = r5.a     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L7d java.lang.Exception -> L85
            java.lang.String r2 = r2.getPackageName()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L7d java.lang.Exception -> L85
            r1.setPackage(r2)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L7d java.lang.Exception -> L85
            android.os.Bundle r2 = new android.os.Bundle     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L7d java.lang.Exception -> L85
            r2.<init>()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L7d java.lang.Exception -> L85
            java.lang.String r3 = "message_type"
            java.lang.String r4 = "new_token"
            r2.putString(r3, r4)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L7d java.lang.Exception -> L85
            java.lang.String r3 = "device_token"
            r2.putString(r3, r0)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L7d java.lang.Exception -> L85
            com.huawei.hms.aaid.init.b r0 = new com.huawei.hms.aaid.init.b     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L7d java.lang.Exception -> L85
            r0.<init>()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L7d java.lang.Exception -> L85
            android.content.Context r3 = r5.a     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L7d java.lang.Exception -> L85
            boolean r0 = r0.a(r3, r2, r1)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L7d java.lang.Exception -> L85
            if (r0 != 0) goto L8d
            java.lang.String r0 = "AutoInit"
            java.lang.String r1 = "start service failed"
            com.huawei.hms.support.log.HMSLog.e(r0, r1)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L7d java.lang.Exception -> L85
            goto L8d
        L75:
            java.lang.String r0 = "AutoInit"
            java.lang.String r1 = "push kit sdk not exists"
            com.huawei.hms.support.log.HMSLog.i(r0, r1)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L7d java.lang.Exception -> L85
            goto L8d
        L7d:
            java.lang.String r0 = "AutoInit"
            java.lang.String r1 = "push kit sdk not exists"
            com.huawei.hms.support.log.HMSLog.i(r0, r1)     // Catch: java.lang.Exception -> L85
            goto L8d
        L85:
            r0 = move-exception
            java.lang.String r1 = "AutoInit"
            java.lang.String r2 = "Push init failed"
            com.huawei.hms.support.log.HMSLog.e(r1, r2, r0)
        L8d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.aaid.init.a.run():void");
    }
}
