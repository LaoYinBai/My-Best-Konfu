package com.bytedance.a;

import android.content.Context;
import android.content.pm.PackageManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class ax implements bm {

    /* renamed from: a, reason: collision with root package name */
    public dt<Boolean> f3381a = new a(this);

    public class a extends dt<Boolean> {
        public a(ax axVar) {
        }

        @Override // com.bytedance.a.dt
        public Boolean a(Object[] objArr) {
            try {
                PackageManager packageManager = ((Context) objArr[0]).getPackageManager();
                if (packageManager != null) {
                    return Boolean.valueOf(packageManager.resolveContentProvider("com.meizu.flyme.openidsdk", 0) != null);
                }
            } catch (Exception unused) {
            }
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002b  */
    @Override // com.bytedance.a.bm
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.bytedance.a.bm.a a(android.content.Context r8) {
        /*
            r7 = this;
            java.lang.String r0 = "content://com.meizu.flyme.openidsdk/"
            android.net.Uri r2 = android.net.Uri.parse(r0)
            android.content.ContentResolver r1 = r8.getContentResolver()
            r8 = 0
            java.lang.String r0 = "oaid"
            java.lang.String[] r5 = new java.lang.String[]{r0}     // Catch: java.lang.Throwable -> L44
            r6 = 0
            r3 = 0
            r4 = 0
            android.database.Cursor r0 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L44
            if (r0 != 0) goto L20
            if (r0 == 0) goto L1f
            r0.close()
        L1f:
            return r8
        L20:
            com.bytedance.a.bm$a r1 = new com.bytedance.a.bm$a     // Catch: java.lang.Throwable -> L42
            r1.<init>()     // Catch: java.lang.Throwable -> L42
            boolean r2 = r0.isClosed()     // Catch: java.lang.Throwable -> L42
            if (r2 == 0) goto L2d
        L2b:
            r2 = r8
            goto L3c
        L2d:
            r0.moveToFirst()     // Catch: java.lang.Throwable -> L42
            java.lang.String r2 = "value"
            int r2 = r0.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L42
            if (r2 < 0) goto L2b
            java.lang.String r2 = r0.getString(r2)     // Catch: java.lang.Throwable -> L42
        L3c:
            r1.f3415b = r2     // Catch: java.lang.Throwable -> L42
            r0.close()
            return r1
        L42:
            r1 = move-exception
            goto L46
        L44:
            r1 = move-exception
            r0 = r8
        L46:
            java.lang.String r2 = ""
            com.bytedance.a.de.a(r2, r1)     // Catch: java.lang.Throwable -> L51
            if (r0 == 0) goto L50
            r0.close()
        L50:
            return r8
        L51:
            r8 = move-exception
            if (r0 == 0) goto L57
            r0.close()
        L57:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.a.ax.a(android.content.Context):com.bytedance.a.bm$a");
    }

    @Override // com.bytedance.a.bm
    public boolean c(Context context) {
        if (context == null) {
            return false;
        }
        return this.f3381a.b(context).booleanValue();
    }
}
