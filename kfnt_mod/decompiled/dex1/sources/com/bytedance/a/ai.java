package com.bytedance.a;

import android.content.Context;
import com.bytedance.a.bm;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class ai implements bm {

    /* renamed from: a, reason: collision with root package name */
    public static final dt<Boolean> f3350a = new a();

    public static class a extends dt<Boolean> {
        @Override // com.bytedance.a.dt
        public Boolean a(Object[] objArr) {
            return Boolean.valueOf(m.a((Context) objArr[0], "com.huawei.hwid"));
        }
    }

    public static class b extends bm.a {

        /* renamed from: a, reason: collision with root package name */
        public long f3351a = 0;
    }

    public static boolean b(Context context) {
        if (context == null) {
            return false;
        }
        return f3350a.b(context).booleanValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003b  */
    @Override // com.bytedance.a.bm
    @androidx.annotation.Nullable
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.bytedance.a.bm.a a(android.content.Context r5) throws android.content.pm.PackageManager.NameNotFoundException {
        /*
            r4 = this;
            com.bytedance.a.ai$b r0 = new com.bytedance.a.ai$b
            r0.<init>()
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 24
            if (r1 < r2) goto L3b
            android.content.ContentResolver r1 = r5.getContentResolver()     // Catch: java.lang.Throwable -> L35
            java.lang.String r2 = "pps_oaid"
            java.lang.String r1 = android.provider.Settings.Global.getString(r1, r2)     // Catch: java.lang.Throwable -> L35
            android.content.ContentResolver r2 = r5.getContentResolver()     // Catch: java.lang.Throwable -> L35
            java.lang.String r3 = "pps_track_limit"
            java.lang.String r2 = android.provider.Settings.Global.getString(r2, r3)     // Catch: java.lang.Throwable -> L35
            boolean r3 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Throwable -> L35
            if (r3 != 0) goto L3b
            r0.f3415b = r1     // Catch: java.lang.Throwable -> L35
            boolean r1 = java.lang.Boolean.parseBoolean(r2)     // Catch: java.lang.Throwable -> L35
            r0.f3416c = r1     // Catch: java.lang.Throwable -> L35
            r1 = 202003021704(0x2f08517f88, double:9.980275338E-313)
            r0.f3351a = r1     // Catch: java.lang.Throwable -> L35
            goto L84
        L35:
            r1 = move-exception
            java.lang.String r2 = ""
            com.bytedance.a.de.a(r2, r1)
        L3b:
            android.content.Intent r1 = new android.content.Intent
            java.lang.String r2 = "com.uodis.opendevice.OPENIDS_SERVICE"
            r1.<init>(r2)
            java.lang.String r2 = "com.huawei.hwid"
            android.content.Intent r1 = r1.setPackage(r2)
            com.bytedance.a.da r2 = new com.bytedance.a.da
            com.bytedance.a.an r3 = new com.bytedance.a.an
            r3.<init>()
            r2.<init>(r5, r1, r3)
            java.lang.Object r1 = r2.a()
            android.util.Pair r1 = (android.util.Pair) r1
            if (r1 == 0) goto L84
            java.lang.Object r2 = r1.first
            java.lang.String r2 = (java.lang.String) r2
            r0.f3415b = r2
            java.lang.Object r1 = r1.second
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            r0.f3416c = r1
            r1 = 0
            android.content.pm.PackageManager r5 = r5.getPackageManager()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L7a
            java.lang.String r2 = "com.huawei.hwid"
            android.content.pm.PackageInfo r5 = r5.getPackageInfo(r2, r1)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L7a
            if (r5 == 0) goto L80
            int r5 = r5.versionCode     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L7a
            goto L81
        L7a:
            r5 = move-exception
            java.lang.String r2 = ""
            com.bytedance.a.de.a(r2, r5)
        L80:
            r5 = 0
        L81:
            long r1 = (long) r5
            r0.f3351a = r1
        L84:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.a.ai.a(android.content.Context):com.bytedance.a.bm$a");
    }

    @Override // com.bytedance.a.bm
    public boolean c(Context context) {
        return b(context);
    }
}
