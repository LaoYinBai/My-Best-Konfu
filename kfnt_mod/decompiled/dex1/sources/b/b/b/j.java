package b.b.b;

import android.content.Context;
import b.b.b.p;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class j implements p {

    /* renamed from: a, reason: collision with root package name */
    public static final b.b.b.b<Boolean> f1169a = new a();

    public static class a extends b.b.b.b<Boolean> {
        @Override // b.b.b.b
        public Boolean a(Object[] objArr) {
            return Boolean.valueOf(b.b.a.a.b.a((Context) objArr[0], "com.huawei.hwid"));
        }
    }

    public static class b extends p.a {

        /* renamed from: a, reason: collision with root package name */
        public long f1170a = 0;
    }

    public static boolean a(Context context) {
        if (context == null) {
            return false;
        }
        return f1169a.b(context).booleanValue();
    }

    @Override // b.b.b.p
    public boolean b(Context context) {
        return a(context);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0039  */
    @Override // b.b.b.p
    @androidx.annotation.Nullable
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public b.b.b.p.a c(android.content.Context r5) throws android.content.pm.PackageManager.NameNotFoundException {
        /*
            r4 = this;
            b.b.b.j$b r0 = new b.b.b.j$b
            r0.<init>()
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 24
            if (r1 < r2) goto L39
            android.content.ContentResolver r1 = r5.getContentResolver()     // Catch: java.lang.Throwable -> L35
            java.lang.String r2 = "pps_oaid"
            java.lang.String r1 = android.provider.Settings.Global.getString(r1, r2)     // Catch: java.lang.Throwable -> L35
            android.content.ContentResolver r2 = r5.getContentResolver()     // Catch: java.lang.Throwable -> L35
            java.lang.String r3 = "pps_track_limit"
            java.lang.String r2 = android.provider.Settings.Global.getString(r2, r3)     // Catch: java.lang.Throwable -> L35
            boolean r3 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Throwable -> L35
            if (r3 != 0) goto L39
            r0.f1176b = r1     // Catch: java.lang.Throwable -> L35
            boolean r1 = java.lang.Boolean.parseBoolean(r2)     // Catch: java.lang.Throwable -> L35
            r0.f1177c = r1     // Catch: java.lang.Throwable -> L35
            r1 = 202003021704(0x2f08517f88, double:9.980275338E-313)
            r0.f1170a = r1     // Catch: java.lang.Throwable -> L35
            goto L80
        L35:
            r1 = move-exception
            r1.printStackTrace()
        L39:
            android.content.Intent r1 = new android.content.Intent
            java.lang.String r2 = "com.uodis.opendevice.OPENIDS_SERVICE"
            r1.<init>(r2)
            java.lang.String r2 = "com.huawei.hwid"
            android.content.Intent r1 = r1.setPackage(r2)
            b.b.b.x r2 = new b.b.b.x
            b.b.b.k r3 = new b.b.b.k
            r3.<init>()
            r2.<init>(r5, r1, r3)
            java.lang.Object r1 = r2.a()
            android.util.Pair r1 = (android.util.Pair) r1
            if (r1 == 0) goto L80
            java.lang.Object r2 = r1.first
            java.lang.String r2 = (java.lang.String) r2
            r0.f1176b = r2
            java.lang.Object r1 = r1.second
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            r0.f1177c = r1
            r1 = 0
            android.content.pm.PackageManager r5 = r5.getPackageManager()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L78
            java.lang.String r2 = "com.huawei.hwid"
            android.content.pm.PackageInfo r5 = r5.getPackageInfo(r2, r1)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L78
            if (r5 == 0) goto L7c
            int r5 = r5.versionCode     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L78
            goto L7d
        L78:
            r5 = move-exception
            r5.printStackTrace()
        L7c:
            r5 = 0
        L7d:
            long r1 = (long) r5
            r0.f1170a = r1
        L80:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.b.j.c(android.content.Context):b.b.b.p$a");
    }
}
