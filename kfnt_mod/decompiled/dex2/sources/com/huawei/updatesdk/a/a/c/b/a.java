package com.huawei.updatesdk.a.a.c.b;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static final Uri a = Uri.parse("content://telephony/carriers/preferapn");
    private static final Uri b = Uri.parse("content://telephony/carriers/preferapn/0");
    private static final Uri c = Uri.parse("content://telephony/carriers/preferapn/1");

    /* renamed from: com.huawei.updatesdk.a.a.c.b.a$a, reason: collision with other inner class name */
    public static class C0012a {
        private String a;
        private String b;
        private String c;

        public String a() {
            return this.a;
        }

        public void a(String str) {
            this.a = str;
        }

        public String b() {
            return this.b;
        }

        public void b(String str) {
            this.b = str;
        }

        public void c(String str) {
            this.c = str;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:27:0x006b A[PHI: r5
  0x006b: PHI (r5v9 android.database.Cursor) = (r5v7 android.database.Cursor), (r5v12 android.database.Cursor) binds: [B:26:0x0069, B:15:0x0053] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r5v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.huawei.updatesdk.a.a.c.b.a.C0012a a(android.content.Context r5) throws java.lang.Throwable {
        /*
            com.huawei.updatesdk.a.a.c.b.a$a r0 = new com.huawei.updatesdk.a.a.c.b.a$a
            r0.<init>()
            r1 = 0
            boolean r2 = com.huawei.updatesdk.a.a.c.b.a.b.b()     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5f java.lang.SecurityException -> L78
            if (r2 == 0) goto L11
            android.database.Cursor r5 = d(r5)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5f java.lang.SecurityException -> L78
            goto L15
        L11:
            android.database.Cursor r5 = c(r5)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5f java.lang.SecurityException -> L78
        L15:
            if (r5 != 0) goto L1d
            if (r5 == 0) goto L1c
            r5.close()
        L1c:
            return r1
        L1d:
            boolean r2 = r5.moveToNext()     // Catch: java.lang.Exception -> L56 java.lang.SecurityException -> L58 java.lang.Throwable -> L76
            if (r2 == 0) goto L53
            java.lang.String r2 = "_id"
            int r2 = r5.getColumnIndex(r2)     // Catch: java.lang.Exception -> L56 java.lang.SecurityException -> L58 java.lang.Throwable -> L76
            java.lang.String r2 = r5.getString(r2)     // Catch: java.lang.Exception -> L56 java.lang.SecurityException -> L58 java.lang.Throwable -> L76
            r0.a(r2)     // Catch: java.lang.Exception -> L56 java.lang.SecurityException -> L58 java.lang.Throwable -> L76
            java.lang.String r2 = "name"
            int r2 = r5.getColumnIndex(r2)     // Catch: java.lang.Exception -> L56 java.lang.SecurityException -> L58 java.lang.Throwable -> L76
            java.lang.String r2 = r5.getString(r2)     // Catch: java.lang.Exception -> L56 java.lang.SecurityException -> L58 java.lang.Throwable -> L76
            r0.c(r2)     // Catch: java.lang.Exception -> L56 java.lang.SecurityException -> L58 java.lang.Throwable -> L76
            java.lang.String r2 = "apn"
            int r2 = r5.getColumnIndex(r2)     // Catch: java.lang.Exception -> L56 java.lang.SecurityException -> L58 java.lang.Throwable -> L76
            java.lang.String r2 = r5.getString(r2)     // Catch: java.lang.Exception -> L56 java.lang.SecurityException -> L58 java.lang.Throwable -> L76
            java.util.Locale r3 = java.util.Locale.getDefault()     // Catch: java.lang.Exception -> L56 java.lang.SecurityException -> L58 java.lang.Throwable -> L76
            java.lang.String r2 = r2.toLowerCase(r3)     // Catch: java.lang.Exception -> L56 java.lang.SecurityException -> L58 java.lang.Throwable -> L76
            r0.b(r2)     // Catch: java.lang.Exception -> L56 java.lang.SecurityException -> L58 java.lang.Throwable -> L76
            goto L1d
        L53:
            if (r5 == 0) goto L6e
            goto L6b
        L56:
            r2 = move-exception
            goto L62
        L58:
            r0 = move-exception
            r1 = r5
            goto L7a
        L5b:
            r5 = move-exception
            r0 = r5
            r5 = r1
            goto L82
        L5f:
            r5 = move-exception
            r2 = r5
            r5 = r1
        L62:
            java.lang.String r3 = "ApnUtil"
            java.lang.String r4 = "getDefaultAPN, Exception: "
            com.huawei.updatesdk.a.a.b.a.a.a.a(r3, r4, r2)     // Catch: java.lang.Throwable -> L76
            if (r5 == 0) goto L6e
        L6b:
            r5.close()
        L6e:
            java.lang.String r5 = r0.a()
            if (r5 != 0) goto L75
            r0 = r1
        L75:
            return r0
        L76:
            r0 = move-exception
            goto L82
        L78:
            r5 = move-exception
            r0 = r5
        L7a:
            java.lang.String r5 = "ApnUtil"
            java.lang.String r2 = "getDefaultAPN, SecurityException: "
            com.huawei.updatesdk.a.a.b.a.a.a.a(r5, r2, r0)     // Catch: java.lang.Throwable -> L5b
            throw r0     // Catch: java.lang.Throwable -> L5b
        L82:
            if (r5 == 0) goto L87
            r5.close()
        L87:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.updatesdk.a.a.c.b.a.a(android.content.Context):com.huawei.updatesdk.a.a.c.b.a$a");
    }

    public static boolean b(Context context) throws Throwable {
        try {
            C0012a c0012aA = a(context);
            if (c0012aA == null) {
                return true;
            }
            if (c0012aA.b() != null) {
                if (c0012aA.b().contains("wap")) {
                    return true;
                }
            }
            return false;
        } catch (SecurityException e) {
            com.huawei.updatesdk.a.a.b.a.a.a.a("ApnUtil", "isWap(), SecurityException: ", e);
            return false;
        }
    }

    private static Cursor c(Context context) {
        return context.getContentResolver().query(a, null, null, null, null);
    }

    private static Cursor d(Context context) {
        Cursor cursorE;
        if (com.huawei.updatesdk.a.a.c.b.a.b.a().a() == 0) {
            cursorE = f(context);
            if (cursorE == null) {
                cursorE = e(context);
            }
        } else {
            cursorE = e(context);
            if (cursorE == null) {
                cursorE = f(context);
            }
        }
        return cursorE == null ? c(context) : cursorE;
    }

    private static Cursor e(Context context) {
        return context.getContentResolver().query(c, null, null, null, null);
    }

    private static Cursor f(Context context) {
        return context.getContentResolver().query(b, null, null, null, null);
    }
}
