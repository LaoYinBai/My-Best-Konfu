package b.b.b;

import android.content.Context;
import android.content.pm.PackageManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class m implements p {

    /* renamed from: a, reason: collision with root package name */
    public b<Boolean> f1171a = new a(this);

    public class a extends b<Boolean> {
        public a(m mVar) {
        }

        @Override // b.b.b.b
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

    @Override // b.b.b.p
    public boolean b(Context context) {
        if (context == null) {
            return false;
        }
        return this.f1171a.b(context).booleanValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002b  */
    @Override // b.b.b.p
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public b.b.b.p.a c(android.content.Context r8) {
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
            b.b.b.p$a r1 = new b.b.b.p$a     // Catch: java.lang.Throwable -> L42
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
            r1.f1176b = r2     // Catch: java.lang.Throwable -> L42
            r0.close()
            return r1
        L42:
            r1 = move-exception
            goto L46
        L44:
            r1 = move-exception
            r0 = r8
        L46:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L4f
            if (r0 == 0) goto L4e
            r0.close()
        L4e:
            return r8
        L4f:
            r8 = move-exception
            if (r0 == 0) goto L55
            r0.close()
        L55:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.b.m.c(android.content.Context):b.b.b.p$a");
    }
}
