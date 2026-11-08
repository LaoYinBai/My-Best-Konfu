package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.push.service.XMJobService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class ex {

    /* renamed from: a, reason: collision with other field name */
    private static a f340a;

    /* renamed from: a, reason: collision with other field name */
    private static final String f341a = XMJobService.class.getCanonicalName();
    private static int a = 0;

    interface a {
        /* renamed from: a */
        void mo307a();

        void a(boolean z);

        /* renamed from: a, reason: collision with other method in class */
        boolean mo306a();
    }

    public static synchronized void a() {
        if (f340a == null) {
            return;
        }
        com.xiaomi.channel.commonutils.logger.b.c("stop alarm.");
        f340a.mo307a();
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0064 A[Catch: Exception -> 0x007d, PHI: r4
  0x0064: PHI (r4v6 boolean) = (r4v5 boolean), (r4v8 boolean) binds: [B:13:0x003c, B:20:0x0061] A[DONT_GENERATE, DONT_INLINE], TRY_ENTER, TryCatch #0 {Exception -> 0x007d, blocks: (B:12:0x0032, B:14:0x003e, B:22:0x0064, B:24:0x006e), top: B:43:0x0032 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x009b A[EDGE_INSN: B:50:0x009b->B:34:0x009b BREAK  A[LOOP:0: B:11:0x0030->B:27:0x007a], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(android.content.Context r8) throws android.content.pm.PackageManager.NameNotFoundException {
        /*
            android.content.Context r8 = r8.getApplicationContext()
            java.lang.String r0 = "com.xiaomi.xmsf"
            java.lang.String r1 = r8.getPackageName()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L19
            com.xiaomi.push.ey r0 = new com.xiaomi.push.ey
            r0.<init>(r8)
        L15:
            com.xiaomi.push.ex.f340a = r0
            goto Ld7
        L19:
            android.content.pm.PackageManager r0 = r8.getPackageManager()
            r1 = 0
            java.lang.String r2 = r8.getPackageName()     // Catch: java.lang.Exception -> L81
            r3 = 4
            android.content.pm.PackageInfo r0 = r0.getPackageInfo(r2, r3)     // Catch: java.lang.Exception -> L81
            android.content.pm.ServiceInfo[] r2 = r0.services     // Catch: java.lang.Exception -> L81
            r3 = 1
            if (r2 == 0) goto L7f
            android.content.pm.ServiceInfo[] r0 = r0.services     // Catch: java.lang.Exception -> L81
            int r2 = r0.length     // Catch: java.lang.Exception -> L81
            r4 = 0
        L30:
            if (r1 >= r2) goto L9b
            r5 = r0[r1]     // Catch: java.lang.Exception -> L7d
            java.lang.String r6 = "android.permission.BIND_JOB_SERVICE"
            java.lang.String r7 = r5.permission     // Catch: java.lang.Exception -> L7d
            boolean r6 = r6.equals(r7)     // Catch: java.lang.Exception -> L7d
            if (r6 == 0) goto L64
            java.lang.String r6 = com.xiaomi.push.ex.f341a     // Catch: java.lang.Exception -> L7d
            java.lang.String r7 = r5.name     // Catch: java.lang.Exception -> L7d
            boolean r6 = r6.equals(r7)     // Catch: java.lang.Exception -> L7d
            if (r6 == 0) goto L4a
        L48:
            r4 = 1
            goto L61
        L4a:
            java.lang.String r6 = r5.name     // Catch: java.lang.Exception -> L61
            java.lang.Class r6 = com.xiaomi.push.t.a(r8, r6)     // Catch: java.lang.Exception -> L61
            java.lang.String r7 = com.xiaomi.push.ex.f341a     // Catch: java.lang.Exception -> L61
            java.lang.Class r6 = r6.getSuperclass()     // Catch: java.lang.Exception -> L61
            java.lang.String r6 = r6.getCanonicalName()     // Catch: java.lang.Exception -> L61
            boolean r6 = r7.equals(r6)     // Catch: java.lang.Exception -> L61
            if (r6 == 0) goto L61
            goto L48
        L61:
            if (r4 != r3) goto L64
            goto L9b
        L64:
            java.lang.String r6 = com.xiaomi.push.ex.f341a     // Catch: java.lang.Exception -> L7d
            java.lang.String r7 = r5.name     // Catch: java.lang.Exception -> L7d
            boolean r6 = r6.equals(r7)     // Catch: java.lang.Exception -> L7d
            if (r6 == 0) goto L7a
            java.lang.String r6 = "android.permission.BIND_JOB_SERVICE"
            java.lang.String r5 = r5.permission     // Catch: java.lang.Exception -> L7d
            boolean r5 = r6.equals(r5)     // Catch: java.lang.Exception -> L7d
            if (r5 == 0) goto L7a
            r4 = 1
            goto L9b
        L7a:
            int r1 = r1 + 1
            goto L30
        L7d:
            r0 = move-exception
            goto L83
        L7f:
            r4 = 0
            goto L9b
        L81:
            r0 = move-exception
            r4 = 0
        L83:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "check service err : "
            r1.append(r2)
            java.lang.String r0 = r0.getMessage()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.xiaomi.channel.commonutils.logger.b.m22a(r0)
        L9b:
            if (r4 != 0) goto Lcc
            boolean r0 = com.xiaomi.push.t.m647a(r8)
            if (r0 != 0) goto La4
            goto Lcc
        La4:
            java.lang.RuntimeException r8 = new java.lang.RuntimeException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Should export service: "
            r0.append(r1)
            java.lang.String r1 = com.xiaomi.push.ex.f341a
            r0.append(r1)
            java.lang.String r1 = " with permission "
            r0.append(r1)
            java.lang.String r1 = "android.permission.BIND_JOB_SERVICE"
            r0.append(r1)
            java.lang.String r1 = " in AndroidManifest.xml file"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r8.<init>(r0)
            throw r8
        Lcc:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 21
            com.xiaomi.push.ey r0 = new com.xiaomi.push.ey
            r0.<init>(r8)
            goto L15
        Ld7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.ex.a(android.content.Context):void");
    }

    public static synchronized void a(Context context, int i) {
        int i2 = a;
        if (!"com.xiaomi.xmsf".equals(context.getPackageName())) {
            if (i == 2) {
                a = 2;
            } else {
                a = 0;
            }
        }
        if (i2 != a && a == 2) {
            a();
            f340a = new fa(context);
        }
    }

    public static synchronized void a(boolean z) {
        if (f340a == null) {
            com.xiaomi.channel.commonutils.logger.b.m22a("timer is not initialized");
            return;
        }
        com.xiaomi.channel.commonutils.logger.b.c("register alarm. (" + z + com.umeng.message.proguard.l.t);
        f340a.a(z);
    }

    /* renamed from: a, reason: collision with other method in class */
    public static synchronized boolean m305a() {
        if (f340a == null) {
            return false;
        }
        return f340a.mo306a();
    }
}
