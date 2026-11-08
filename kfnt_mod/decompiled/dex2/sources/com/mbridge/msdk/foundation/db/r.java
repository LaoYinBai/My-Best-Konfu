package com.mbridge.msdk.foundation.db;

import android.content.ContentValues;
import android.database.Cursor;
import com.mbridge.msdk.foundation.entity.CampaignEx;

/* compiled from: SettingCampaignDao.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class r extends a {
    private static r a;

    private r(h hVar) {
        super(hVar);
    }

    public static r a(h hVar) {
        if (a == null) {
            synchronized (r.class) {
                if (a == null) {
                    a = new r(hVar);
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
                Cursor cursorQuery = getReadableDatabase().query("settingCampaign", new String[]{" count(*) "}, null, null, null, null, null);
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

    public final synchronized int b() {
        try {
            if (getWritableDatabase() == null) {
                return -1;
            }
            return getWritableDatabase().delete("settingCampaign", null, null);
        } catch (Exception unused) {
            return -1;
        }
    }

    public final synchronized int a(String str) {
        try {
            String[] strArr = {str};
            if (getWritableDatabase() == null) {
                return -1;
            }
            return getWritableDatabase().delete("settingCampaign", "id=?", strArr);
        } catch (Exception unused) {
            return -1;
        }
    }

    public final synchronized int b(String str) {
        try {
            String[] strArr = {str};
            if (getWritableDatabase() == null) {
                return -1;
            }
            return getWritableDatabase().delete("settingCampaign", "iex=?", strArr);
        } catch (Exception unused) {
            return -1;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0081 A[Catch: all -> 0x0085, TRY_ENTER, TryCatch #3 {all -> 0x0085, blocks: (B:27:0x0078, B:19:0x0066, B:33:0x0081, B:36:0x0087), top: B:43:0x0002 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized java.util.List<com.mbridge.msdk.foundation.entity.o> c() {
        /*
            r10 = this;
            monitor-enter(r10)
            r0 = 0
            java.lang.String r1 = "select * from settingCampaign ORDER BY iex LIMIT 3"
            android.database.sqlite.SQLiteDatabase r2 = r10.getReadableDatabase()     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6f
            android.database.Cursor r1 = r2.rawQuery(r1, r0)     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6f
            if (r1 == 0) goto L64
            int r2 = r1.getCount()     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L7e
            if (r2 <= 0) goto L64
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L7e
            r2.<init>()     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> L7e
        L19:
            boolean r0 = r1.moveToNext()     // Catch: java.lang.Exception -> L5d java.lang.Throwable -> L7e
            if (r0 == 0) goto L5b
            java.lang.String r0 = "url"
            int r0 = r1.getColumnIndex(r0)     // Catch: java.lang.Exception -> L5d java.lang.Throwable -> L7e
            java.lang.String r4 = r1.getString(r0)     // Catch: java.lang.Exception -> L5d java.lang.Throwable -> L7e
            java.lang.String r0 = "data"
            int r0 = r1.getColumnIndex(r0)     // Catch: java.lang.Exception -> L5d java.lang.Throwable -> L7e
            java.lang.String r6 = r1.getString(r0)     // Catch: java.lang.Exception -> L5d java.lang.Throwable -> L7e
            java.lang.String r0 = "method"
            int r0 = r1.getColumnIndex(r0)     // Catch: java.lang.Exception -> L5d java.lang.Throwable -> L7e
            java.lang.String r5 = r1.getString(r0)     // Catch: java.lang.Exception -> L5d java.lang.Throwable -> L7e
            java.lang.String r0 = "iex"
            int r0 = r1.getColumnIndex(r0)     // Catch: java.lang.Exception -> L5d java.lang.Throwable -> L7e
            java.lang.String r7 = r1.getString(r0)     // Catch: java.lang.Exception -> L5d java.lang.Throwable -> L7e
            java.lang.String r0 = "id"
            int r0 = r1.getColumnIndex(r0)     // Catch: java.lang.Exception -> L5d java.lang.Throwable -> L7e
            int r8 = r1.getInt(r0)     // Catch: java.lang.Exception -> L5d java.lang.Throwable -> L7e
            com.mbridge.msdk.foundation.entity.o r0 = new com.mbridge.msdk.foundation.entity.o     // Catch: java.lang.Exception -> L5d java.lang.Throwable -> L7e
            r3 = r0
            r3.<init>(r4, r5, r6, r7, r8)     // Catch: java.lang.Exception -> L5d java.lang.Throwable -> L7e
            r2.add(r0)     // Catch: java.lang.Exception -> L5d java.lang.Throwable -> L7e
            goto L19
        L5b:
            r0 = r2
            goto L64
        L5d:
            r0 = move-exception
            goto L73
        L5f:
            r2 = move-exception
            r9 = r2
            r2 = r0
            r0 = r9
            goto L73
        L64:
            if (r1 == 0) goto L7c
            r1.close()     // Catch: java.lang.Throwable -> L85
            goto L7c
        L6a:
            r1 = move-exception
            r9 = r1
            r1 = r0
            r0 = r9
            goto L7f
        L6f:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r2
        L73:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L7e
            if (r1 == 0) goto L7b
            r1.close()     // Catch: java.lang.Throwable -> L85
        L7b:
            r0 = r2
        L7c:
            monitor-exit(r10)
            return r0
        L7e:
            r0 = move-exception
        L7f:
            if (r1 == 0) goto L87
            r1.close()     // Catch: java.lang.Throwable -> L85
            goto L87
        L85:
            r0 = move-exception
            goto L88
        L87:
            throw r0     // Catch: java.lang.Throwable -> L85
        L88:
            monitor-exit(r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.db.r.c():java.util.List");
    }

    public final synchronized long a(com.mbridge.msdk.foundation.entity.o oVar) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("url", oVar.b());
            contentValues.put("method", oVar.c());
            contentValues.put("data", oVar.d());
            contentValues.put(CampaignEx.JSON_KEY_ST_IEX, oVar.a());
            if (getWritableDatabase() == null) {
                return -1L;
            }
            return getWritableDatabase().insert("settingCampaign", null, contentValues);
        } catch (Exception unused) {
            return -1L;
        }
    }
}
