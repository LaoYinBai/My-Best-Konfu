package com.taobao.accs.init;

import android.app.Application;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class e implements Runnable {
    final /* synthetic */ HashMap a;
    final /* synthetic */ Application b;
    final /* synthetic */ Launcher_Login c;

    e(Launcher_Login launcher_Login, HashMap map, Application application) {
        this.c = launcher_Login;
        this.a = map;
        this.b = application;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0058 A[Catch: Throwable -> 0x0093, TryCatch #0 {Throwable -> 0x0093, blocks: (B:23:0x0052, B:25:0x0058, B:26:0x0063, B:22:0x0049), top: B:31:0x0049 }] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() {
        /*
            r7 = this;
            r0 = 0
            r1 = 1
            r2 = 0
            java.util.HashMap r3 = r7.a     // Catch: java.lang.Throwable -> L48
            java.lang.String r4 = "envIndex"
            java.lang.Object r3 = r3.get(r4)     // Catch: java.lang.Throwable -> L48
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch: java.lang.Throwable -> L48
            int r3 = r3.intValue()     // Catch: java.lang.Throwable -> L48
            java.util.HashMap r4 = r7.a     // Catch: java.lang.Throwable -> L48
            java.lang.String r5 = "onlineAppKey"
            java.lang.Object r4 = r4.get(r5)     // Catch: java.lang.Throwable -> L48
            java.lang.String r4 = (java.lang.String) r4     // Catch: java.lang.Throwable -> L48
            if (r3 != r1) goto L2c
            java.util.HashMap r0 = r7.a     // Catch: java.lang.Throwable -> L28
            java.lang.String r3 = "preAppKey"
            java.lang.Object r0 = r0.get(r3)     // Catch: java.lang.Throwable -> L28
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.Throwable -> L28
            goto L52
        L28:
            r0 = move-exception
            r3 = r0
            r0 = r4
            goto L49
        L2c:
            r0 = 2
            if (r3 != r0) goto L31
            r0 = 1
            goto L32
        L31:
            r0 = 0
        L32:
            r5 = 3
            if (r3 != r5) goto L37
            r3 = 1
            goto L38
        L37:
            r3 = 0
        L38:
            r0 = r0 | r3
            if (r0 == 0) goto L46
            java.util.HashMap r0 = r7.a     // Catch: java.lang.Throwable -> L28
            java.lang.String r3 = "dailyAppkey"
            java.lang.Object r0 = r0.get(r3)     // Catch: java.lang.Throwable -> L28
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.Throwable -> L28
            goto L52
        L46:
            r0 = r4
            goto L52
        L48:
            r3 = move-exception
        L49:
            java.lang.String r4 = "Launcher_Login"
            java.lang.String r5 = "login get param error"
            java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L93
            com.taobao.accs.utl.ALog.e(r4, r5, r3, r6)     // Catch: java.lang.Throwable -> L93
        L52:
            boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Throwable -> L93
            if (r3 == 0) goto L63
            java.lang.String r0 = "Launcher_Login"
            java.lang.String r3 = "login get appkey null"
            java.lang.Object[] r4 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L93
            com.taobao.accs.utl.ALog.e(r0, r3, r4)     // Catch: java.lang.Throwable -> L93
            java.lang.String r0 = "21646297"
        L63:
            com.taobao.accs.init.Launcher_InitAccs.mForceBindUser = r1     // Catch: java.lang.Throwable -> L93
            java.util.HashMap r1 = r7.a     // Catch: java.lang.Throwable -> L93
            java.lang.String r3 = "userId"
            java.lang.Object r1 = r1.get(r3)     // Catch: java.lang.Throwable -> L93
            java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.Throwable -> L93
            com.taobao.accs.init.Launcher_InitAccs.mUserId = r1     // Catch: java.lang.Throwable -> L93
            java.util.HashMap r1 = r7.a     // Catch: java.lang.Throwable -> L93
            java.lang.String r3 = "sid"
            java.lang.Object r1 = r1.get(r3)     // Catch: java.lang.Throwable -> L93
            java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.Throwable -> L93
            com.taobao.accs.init.Launcher_InitAccs.mSid = r1     // Catch: java.lang.Throwable -> L93
            android.app.Application r1 = r7.b     // Catch: java.lang.Throwable -> L93
            android.content.Context r1 = r1.getApplicationContext()     // Catch: java.lang.Throwable -> L93
            java.util.HashMap r3 = r7.a     // Catch: java.lang.Throwable -> L93
            java.lang.String r4 = "ttid"
            java.lang.Object r3 = r3.get(r4)     // Catch: java.lang.Throwable -> L93
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Throwable -> L93
            com.taobao.accs.IAppReceiver r4 = com.taobao.accs.init.Launcher_InitAccs.mAppReceiver     // Catch: java.lang.Throwable -> L93
            com.taobao.accs.ACCSManager.bindApp(r1, r0, r3, r4)     // Catch: java.lang.Throwable -> L93
            goto L9d
        L93:
            r0 = move-exception
            java.lang.String r1 = "Launcher_Login"
            java.lang.String r3 = "login"
            java.lang.Object[] r2 = new java.lang.Object[r2]
            com.taobao.accs.utl.ALog.e(r1, r3, r0, r2)
        L9d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.accs.init.e.run():void");
    }
}
