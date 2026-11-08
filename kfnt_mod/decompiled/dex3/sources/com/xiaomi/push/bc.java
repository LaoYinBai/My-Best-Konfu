package com.xiaomi.push;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import com.sigmob.sdk.common.Constants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class bc implements ar {
    private static String a = "content://com.vivo.vms.IdProvider/IdentifierId/";
    private static String b = a + "OAID";
    private static String c = a + "VAID_";
    private static String d = a + "AAID_";
    private static String e = a + "OAIDSTATUS";
    private static String f = "persist.sys.identifierid.supported";

    /* renamed from: a, reason: collision with other field name */
    private Context f148a;

    public bc(Context context) {
        this.f148a = context;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002e A[PHI: r0 r10
  0x002e: PHI (r0v3 java.lang.String) = (r0v9 java.lang.String), (r0v7 java.lang.String) binds: [B:18:0x003a, B:11:0x002c] A[DONT_GENERATE, DONT_INLINE]
  0x002e: PHI (r10v5 android.database.Cursor) = (r10v4 android.database.Cursor), (r10v6 android.database.Cursor) binds: [B:18:0x003a, B:11:0x002c] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String a(java.lang.String r10) throws java.lang.Throwable {
        /*
            r9 = this;
            r0 = 0
            android.content.Context r1 = r9.f148a     // Catch: java.lang.Throwable -> L32 java.lang.Exception -> L39
            android.content.ContentResolver r2 = r1.getContentResolver()     // Catch: java.lang.Throwable -> L32 java.lang.Exception -> L39
            android.net.Uri r3 = android.net.Uri.parse(r10)     // Catch: java.lang.Throwable -> L32 java.lang.Exception -> L39
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r10 = r2.query(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L32 java.lang.Exception -> L39
            if (r10 == 0) goto L2c
            boolean r1 = r10.moveToNext()     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L3a
            if (r1 == 0) goto L2c
            java.lang.String r1 = "value"
            int r1 = r10.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L3a
            java.lang.String r1 = r10.getString(r1)     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L3a
            r0 = r1
            goto L2c
        L27:
            r0 = move-exception
            r8 = r0
            r0 = r10
            r10 = r8
            goto L33
        L2c:
            if (r10 == 0) goto L3d
        L2e:
            r10.close()
            goto L3d
        L32:
            r10 = move-exception
        L33:
            if (r0 == 0) goto L38
            r0.close()
        L38:
            throw r10
        L39:
            r10 = r0
        L3a:
            if (r10 == 0) goto L3d
            goto L2e
        L3d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.bc.a(java.lang.String):java.lang.String");
    }

    public static boolean a(Context context) {
        try {
            ProviderInfo providerInfoResolveContentProvider = context.getPackageManager().resolveContentProvider(Uri.parse(a).getAuthority(), 128);
            if (providerInfoResolveContentProvider != null) {
                if ((providerInfoResolveContentProvider.applicationInfo.flags & 1) != 0) {
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    @Override // com.xiaomi.push.ar
    /* renamed from: a */
    public String mo100a() {
        return null;
    }

    @Override // com.xiaomi.push.ar
    /* renamed from: a */
    public boolean mo101a() {
        return "1".equals(s.a(f, Constants.FAIL));
    }

    @Override // com.xiaomi.push.ar
    /* renamed from: b */
    public String mo102b() {
        return a(b);
    }

    @Override // com.xiaomi.push.ar
    public String c() {
        return null;
    }

    @Override // com.xiaomi.push.ar
    public String d() {
        return null;
    }
}
