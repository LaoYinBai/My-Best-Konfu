package com.mbridge.msdk.foundation.db;

import android.database.Cursor;

/* compiled from: CampaignClickTimeDao.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d extends a<com.mbridge.msdk.foundation.entity.e> {
    private static d a;

    private d(h hVar) {
        super(hVar);
    }

    public static d a(h hVar) {
        if (a == null) {
            synchronized (d.class) {
                if (a == null) {
                    a = new d(hVar);
                }
            }
        }
        return a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0135 A[Catch: all -> 0x014d, PHI: r1
  0x0135: PHI (r1v6 android.database.Cursor) = (r1v5 android.database.Cursor), (r1v7 android.database.Cursor) binds: [B:34:0x0141, B:26:0x0133] A[DONT_GENERATE, DONT_INLINE], TRY_ENTER, TRY_LEAVE, TryCatch #3 {, blocks: (B:4:0x0003, B:21:0x012c, B:27:0x0135, B:40:0x0149, B:41:0x014c), top: B:46:0x0003 }] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r2v2, types: [android.database.sqlite.SQLiteDatabase] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized java.util.List<com.mbridge.msdk.foundation.entity.e> a(int r28) {
        /*
            Method dump skipped, instructions count: 336
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.db.d.a(int):java.util.List");
    }

    public final synchronized int a() {
        int i;
        Cursor cursor = null;
        i = 0;
        try {
            try {
                try {
                    Cursor cursorRawQuery = getReadableDatabase().rawQuery("select count(*) from click_time", null);
                    if (cursorRawQuery != null) {
                        try {
                            if (cursorRawQuery.moveToFirst()) {
                                i = cursorRawQuery.getInt(0);
                            }
                        } catch (Exception e) {
                            e = e;
                            cursor = cursorRawQuery;
                            e.printStackTrace();
                            if (cursor != null) {
                                cursor.close();
                            }
                            return i;
                        } catch (Throwable th) {
                            th = th;
                            cursor = cursorRawQuery;
                            if (cursor != null) {
                                cursor.close();
                            }
                            throw th;
                        }
                    }
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            throw th3;
        }
        return i;
    }
}
