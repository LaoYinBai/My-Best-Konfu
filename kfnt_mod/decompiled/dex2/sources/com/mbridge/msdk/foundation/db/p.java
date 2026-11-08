package com.mbridge.msdk.foundation.db;

import android.database.Cursor;

/* compiled from: ReportErrorDao.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class p extends a {
    private static p a;

    private p(h hVar) {
        super(hVar);
    }

    public static p a(h hVar) {
        if (a == null) {
            synchronized (p.class) {
                if (a == null) {
                    a = new p(hVar);
                }
            }
        }
        return a;
    }

    public final synchronized int a() {
        int i;
        Cursor cursor = null;
        cursor = null;
        cursor = null;
        i = 0;
        try {
            try {
                Cursor cursorQuery = getReadableDatabase().query("reporterror", new String[]{" count(*) "}, null, null, null, null, null);
                if (cursorQuery != null) {
                    try {
                        boolean zMoveToFirst = cursorQuery.moveToFirst();
                        cursor = zMoveToFirst;
                        if (zMoveToFirst) {
                            int i2 = cursorQuery.getInt(0);
                            i = i2;
                            cursor = i2;
                        }
                    } catch (Exception e) {
                        e = e;
                        cursor = cursorQuery;
                        e.printStackTrace();
                        if (cursor != null) {
                            cursor.close();
                        }
                        return i;
                    } catch (Throwable th) {
                        th = th;
                        cursor = cursorQuery;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
        }
        return i;
    }

    public final synchronized int a(String str) {
        try {
            String[] strArr = {str};
            if (getWritableDatabase() == null) {
                return -1;
            }
            return getWritableDatabase().delete("reporterror", "url=?", strArr);
        } catch (Exception unused) {
            return -1;
        }
    }

    public final synchronized int a(String str, String str2) {
        if (str == null) {
            str = "";
        }
        try {
            String[] strArr = {str2, str};
            if (getWritableDatabase() == null) {
                return -1;
            }
            return getWritableDatabase().delete("reporterror", "url=? and data=?", strArr);
        } catch (Exception unused) {
            return -1;
        }
    }

    public final synchronized int b() {
        int count;
        Cursor cursorQuery;
        Cursor cursor = null;
        try {
            try {
                cursorQuery = getReadableDatabase().query("reporterror", new String[]{" count(*) "}, null, null, null, null, null, null);
            } catch (Exception e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            count = cursorQuery.getCount();
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Exception e2) {
            e = e2;
            cursor = cursorQuery;
            e.printStackTrace();
            if (cursor != null) {
                cursor.close();
            }
            count = 0;
            return count;
        } catch (Throwable th2) {
            th = th2;
            cursor = cursorQuery;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return count;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x008d A[Catch: all -> 0x0091, TRY_ENTER, TryCatch #0 {all -> 0x0091, blocks: (B:27:0x0084, B:19:0x0072, B:33:0x008d, B:36:0x0093), top: B:39:0x0002 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized java.util.List<com.mbridge.msdk.foundation.entity.o> a(int r12) {
        /*
            r11 = this;
            monitor-enter(r11)
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r11.getReadableDatabase()     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L7b
            java.lang.String r2 = "reporterror"
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L7b
            r9.<init>()     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L7b
            r9.append(r12)     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L7b
            java.lang.String r12 = ""
            r9.append(r12)     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L7b
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L7b
            android.database.Cursor r12 = r1.query(r2, r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L7b
            if (r12 == 0) goto L70
            int r1 = r12.getCount()     // Catch: java.lang.Exception -> L6b java.lang.Throwable -> L8a
            if (r1 <= 0) goto L70
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: java.lang.Exception -> L6b java.lang.Throwable -> L8a
            r1.<init>()     // Catch: java.lang.Exception -> L6b java.lang.Throwable -> L8a
        L30:
            boolean r0 = r12.moveToNext()     // Catch: java.lang.Exception -> L69 java.lang.Throwable -> L8a
            if (r0 == 0) goto L67
            java.lang.String r0 = "url"
            int r0 = r12.getColumnIndex(r0)     // Catch: java.lang.Exception -> L69 java.lang.Throwable -> L8a
            java.lang.String r0 = r12.getString(r0)     // Catch: java.lang.Exception -> L69 java.lang.Throwable -> L8a
            java.lang.String r2 = "data"
            int r2 = r12.getColumnIndex(r2)     // Catch: java.lang.Exception -> L69 java.lang.Throwable -> L8a
            java.lang.String r2 = r12.getString(r2)     // Catch: java.lang.Exception -> L69 java.lang.Throwable -> L8a
            java.lang.String r3 = "method"
            int r3 = r12.getColumnIndex(r3)     // Catch: java.lang.Exception -> L69 java.lang.Throwable -> L8a
            java.lang.String r3 = r12.getString(r3)     // Catch: java.lang.Exception -> L69 java.lang.Throwable -> L8a
            java.lang.String r4 = "unitId"
            int r4 = r12.getColumnIndex(r4)     // Catch: java.lang.Exception -> L69 java.lang.Throwable -> L8a
            java.lang.String r4 = r12.getString(r4)     // Catch: java.lang.Exception -> L69 java.lang.Throwable -> L8a
            com.mbridge.msdk.foundation.entity.o r5 = new com.mbridge.msdk.foundation.entity.o     // Catch: java.lang.Exception -> L69 java.lang.Throwable -> L8a
            r5.<init>(r0, r3, r2, r4)     // Catch: java.lang.Exception -> L69 java.lang.Throwable -> L8a
            r1.add(r5)     // Catch: java.lang.Exception -> L69 java.lang.Throwable -> L8a
            goto L30
        L67:
            r0 = r1
            goto L70
        L69:
            r0 = move-exception
            goto L7f
        L6b:
            r1 = move-exception
            r10 = r1
            r1 = r0
            r0 = r10
            goto L7f
        L70:
            if (r12 == 0) goto L88
            r12.close()     // Catch: java.lang.Throwable -> L91
            goto L88
        L76:
            r12 = move-exception
            r10 = r0
            r0 = r12
            r12 = r10
            goto L8b
        L7b:
            r12 = move-exception
            r1 = r0
            r0 = r12
            r12 = r1
        L7f:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L8a
            if (r12 == 0) goto L87
            r12.close()     // Catch: java.lang.Throwable -> L91
        L87:
            r0 = r1
        L88:
            monitor-exit(r11)
            return r0
        L8a:
            r0 = move-exception
        L8b:
            if (r12 == 0) goto L93
            r12.close()     // Catch: java.lang.Throwable -> L91
            goto L93
        L91:
            r12 = move-exception
            goto L94
        L93:
            throw r0     // Catch: java.lang.Throwable -> L91
        L94:
            monitor-exit(r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.db.p.a(int):java.util.List");
    }
}
