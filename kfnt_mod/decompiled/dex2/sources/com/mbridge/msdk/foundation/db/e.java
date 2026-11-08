package com.mbridge.msdk.foundation.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.entity.DomainCampaignEx;

/* compiled from: CampaignDAIDao.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class e extends a {
    private static e a;
    private int b;

    private e(h hVar) {
        super(hVar);
        this.b = 100;
    }

    public static synchronized e a(h hVar) {
        if (a == null) {
            synchronized (c.class) {
                if (a == null) {
                    a = new e(hVar);
                }
            }
        }
        return a;
    }

    public final synchronized void a(CampaignEx campaignEx) {
        if (campaignEx != null) {
            if (!m(campaignEx.getAkdlui())) {
                if (getWritableDatabase() == null) {
                    return;
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put("unique_key", campaignEx.getAkdlui());
                contentValues.put("id", campaignEx.getId());
                contentValues.put(DomainCampaignEx.JSON_KEY_ATAT_TYPE, Integer.valueOf(campaignEx.getAtatType()));
                contentValues.put(DomainCampaignEx.JSON_KEY_NTBARPASBL, Integer.valueOf(campaignEx.getNtbarpasbl()));
                contentValues.put(DomainCampaignEx.JSON_KEY_NTBARPT, Integer.valueOf(campaignEx.getNtbarpt()));
                contentValues.put(CampaignEx.JSON_KEY_PACKAGE_NAME, campaignEx.getPackageName());
                contentValues.put("rid", campaignEx.getRequestId());
                contentValues.put("rid_n", campaignEx.getRequestIdNotice());
                contentValues.put("unitid", campaignEx.getCampaignUnitId());
                contentValues.put("url", campaignEx.getClickURL());
                contentValues.put("status", (Integer) (-1));
                contentValues.put("progress", (Integer) (-1));
                contentValues.put("save_path", "");
                contentValues.put("app_name", campaignEx.getAppName());
                getWritableDatabase().insert("campaignDAIDao", null, contentValues);
            }
        }
    }

    public final synchronized void a(String str, String str2) {
        if (m(str)) {
            if (getWritableDatabase() == null) {
                return;
            }
            String str3 = "unique_key = '" + str + "'";
            ContentValues contentValues = new ContentValues();
            if (!TextUtils.isEmpty(str2)) {
                contentValues.put("url", str2);
            }
            getWritableDatabase().update("campaignDAIDao", contentValues, str3, null);
        }
    }

    public final synchronized void a(String str) {
        a(str, 10);
    }

    public final synchronized void a(String str, int i) {
        if (m(str)) {
            if (getWritableDatabase() == null) {
                return;
            }
            String str2 = "unique_key = '" + str + "'";
            ContentValues contentValues = new ContentValues();
            if (i != 0) {
                contentValues.put("status", Integer.valueOf(i));
            }
            getWritableDatabase().update("campaignDAIDao", contentValues, str2, null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0012 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0014 A[Catch: all -> 0x000a, TRY_ENTER, TryCatch #0 {all -> 0x000a, blocks: (B:4:0x0003, B:9:0x000c, B:13:0x0014, B:15:0x0031), top: B:20:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized void b(java.lang.String r4, int r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            if (r5 > 0) goto Lc
            boolean r0 = r3.m(r4)     // Catch: java.lang.Throwable -> La
            if (r0 == 0) goto L44
            goto Lc
        La:
            r4 = move-exception
            goto L46
        Lc:
            android.database.sqlite.SQLiteDatabase r0 = r3.getWritableDatabase()     // Catch: java.lang.Throwable -> La
            if (r0 != 0) goto L14
            monitor-exit(r3)
            return
        L14:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La
            r0.<init>()     // Catch: java.lang.Throwable -> La
            java.lang.String r1 = "unique_key = '"
            r0.append(r1)     // Catch: java.lang.Throwable -> La
            r0.append(r4)     // Catch: java.lang.Throwable -> La
            java.lang.String r4 = "'"
            r0.append(r4)     // Catch: java.lang.Throwable -> La
            java.lang.String r4 = r0.toString()     // Catch: java.lang.Throwable -> La
            android.content.ContentValues r0 = new android.content.ContentValues     // Catch: java.lang.Throwable -> La
            r0.<init>()     // Catch: java.lang.Throwable -> La
            if (r5 <= 0) goto L44
            java.lang.String r1 = "progress"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch: java.lang.Throwable -> La
            r0.put(r1, r5)     // Catch: java.lang.Throwable -> La
            android.database.sqlite.SQLiteDatabase r5 = r3.getWritableDatabase()     // Catch: java.lang.Throwable -> La
            java.lang.String r1 = "campaignDAIDao"
            r2 = 0
            r5.update(r1, r0, r4, r2)     // Catch: java.lang.Throwable -> La
        L44:
            monitor-exit(r3)
            return
        L46:
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.db.e.b(java.lang.String, int):void");
    }

    public final synchronized void b(String str, String str2) {
        if (m(str)) {
            if (getWritableDatabase() == null) {
                return;
            }
            String str3 = "unique_key = '" + str + "'";
            ContentValues contentValues = new ContentValues();
            if (!TextUtils.isEmpty(str2)) {
                contentValues.put("save_path", str2);
            }
            getWritableDatabase().update("campaignDAIDao", contentValues, str3, null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v5, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v9 */
    public final synchronized String b(String str) {
        String str2 = "";
        if (m(str)) {
            if (getReadableDatabase() == null) {
                return "";
            }
            ?? IsClosed = 0;
            IsClosed = 0;
            IsClosed = 0;
            try {
                try {
                    Cursor cursorQuery = getReadableDatabase().query("campaignDAIDao", new String[]{"url"}, "unique_key = ?", new String[]{str}, null, null, "unique_key");
                    if (cursorQuery != null) {
                        try {
                            int count = cursorQuery.getCount();
                            IsClosed = count;
                            if (count > 0) {
                                boolean zMoveToFirst = cursorQuery.moveToFirst();
                                IsClosed = zMoveToFirst;
                                if (zMoveToFirst) {
                                    String string = cursorQuery.getString(0);
                                    str2 = string;
                                    IsClosed = string;
                                }
                            }
                        } catch (Exception e) {
                            IsClosed = cursorQuery;
                            e = e;
                            e.printStackTrace();
                            if (IsClosed != 0 && !IsClosed.isClosed()) {
                                IsClosed.close();
                            }
                            return str2;
                        } catch (Throwable th) {
                            th = th;
                            IsClosed = cursorQuery;
                            if (IsClosed != 0 && !IsClosed.isClosed()) {
                                IsClosed.close();
                            }
                            throw th;
                        }
                    }
                    if (cursorQuery != null && (IsClosed = cursorQuery.isClosed()) == 0) {
                        cursorQuery.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e2) {
                e = e2;
            }
        }
        return str2;
    }

    public final synchronized com.mbridge.msdk.foundation.entity.g a() {
        com.mbridge.msdk.foundation.entity.g gVar;
        Throwable th;
        Cursor cursorQuery;
        Cursor cursor = null;
        gVar = null;
        gVar = null;
        com.mbridge.msdk.foundation.entity.g gVar2 = null;
        cursor = null;
        if (getReadableDatabase() == null) {
            return null;
        }
        try {
            try {
                cursorQuery = getReadableDatabase().query("campaignDAIDao", new String[]{"save_path", "url", "unique_key", "rid", CampaignEx.JSON_KEY_PACKAGE_NAME, "rid_n", "id", "unitid"}, "status = ?", new String[]{"10"}, null, null, "unique_key");
                if (cursorQuery != null) {
                    try {
                        try {
                            if (cursorQuery.moveToFirst() && cursorQuery.getCount() > 0) {
                                gVar = new com.mbridge.msdk.foundation.entity.g();
                                try {
                                    gVar.a(cursorQuery.getString(0));
                                    gVar.b(cursorQuery.getString(1));
                                    gVar.c(cursorQuery.getString(2));
                                    gVar.d(cursorQuery.getString(3));
                                    gVar.e(cursorQuery.getString(4));
                                    gVar.f(cursorQuery.getString(5));
                                    gVar.h(cursorQuery.getString(6));
                                    gVar.g(cursorQuery.getString(7));
                                    gVar2 = gVar;
                                } catch (Exception e) {
                                    cursor = cursorQuery;
                                    e = e;
                                    e.printStackTrace();
                                    if (cursor != null && !cursor.isClosed()) {
                                        cursor.close();
                                    }
                                    gVar2 = gVar;
                                    return gVar2;
                                }
                            }
                        } catch (Exception e2) {
                            cursor = cursorQuery;
                            e = e2;
                            gVar = null;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (cursorQuery != null && !cursorQuery.isClosed()) {
                            cursorQuery.close();
                        }
                        throw th;
                    }
                }
                if (cursorQuery != null && !cursorQuery.isClosed()) {
                    cursorQuery.close();
                }
            } catch (Throwable th3) {
                Cursor cursor2 = cursor;
                th = th3;
                cursorQuery = cursor2;
            }
        } catch (Exception e3) {
            e = e3;
            gVar = null;
        }
        return gVar2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v5, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v8 */
    public final synchronized int c(String str) {
        int i = 0;
        if (m(str)) {
            if (getReadableDatabase() == null) {
                return 0;
            }
            ?? IsClosed = 0;
            IsClosed = 0;
            IsClosed = 0;
            try {
                try {
                    Cursor cursorQuery = getReadableDatabase().query("campaignDAIDao", new String[]{"status"}, "unique_key = ?", new String[]{str}, null, null, "unique_key");
                    if (cursorQuery != null) {
                        try {
                            int count = cursorQuery.getCount();
                            IsClosed = count;
                            if (count > 0) {
                                boolean zMoveToFirst = cursorQuery.moveToFirst();
                                IsClosed = zMoveToFirst;
                                if (zMoveToFirst) {
                                    int i2 = cursorQuery.getInt(0);
                                    i = i2;
                                    IsClosed = i2;
                                }
                            }
                        } catch (Exception e) {
                            IsClosed = cursorQuery;
                            e = e;
                            e.printStackTrace();
                            if (IsClosed != 0 && !IsClosed.isClosed()) {
                                IsClosed.close();
                            }
                            return i;
                        } catch (Throwable th) {
                            IsClosed = cursorQuery;
                            th = th;
                            if (IsClosed != 0 && !IsClosed.isClosed()) {
                                IsClosed.close();
                            }
                            throw th;
                        }
                    }
                    if (cursorQuery != null && (IsClosed = cursorQuery.isClosed()) == 0) {
                        cursorQuery.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e2) {
                e = e2;
            }
        }
        return i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v5, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v9 */
    public final synchronized String d(String str) {
        String str2 = "";
        if (m(str)) {
            if (getReadableDatabase() == null) {
                return "";
            }
            ?? IsClosed = 0;
            IsClosed = 0;
            IsClosed = 0;
            try {
                try {
                    Cursor cursorQuery = getReadableDatabase().query("campaignDAIDao", new String[]{"rid"}, "unique_key = ?", new String[]{str}, null, null, "unique_key");
                    if (cursorQuery != null) {
                        try {
                            int count = cursorQuery.getCount();
                            IsClosed = count;
                            if (count > 0) {
                                boolean zMoveToFirst = cursorQuery.moveToFirst();
                                IsClosed = zMoveToFirst;
                                if (zMoveToFirst) {
                                    String string = cursorQuery.getString(0);
                                    str2 = string;
                                    IsClosed = string;
                                }
                            }
                        } catch (Exception e) {
                            IsClosed = cursorQuery;
                            e = e;
                            e.printStackTrace();
                            if (IsClosed != 0 && !IsClosed.isClosed()) {
                                IsClosed.close();
                            }
                            return str2;
                        } catch (Throwable th) {
                            th = th;
                            IsClosed = cursorQuery;
                            if (IsClosed != 0 && !IsClosed.isClosed()) {
                                IsClosed.close();
                            }
                            throw th;
                        }
                    }
                    if (cursorQuery != null && (IsClosed = cursorQuery.isClosed()) == 0) {
                        cursorQuery.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e2) {
                e = e2;
            }
        }
        return str2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v5, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v9 */
    public final synchronized String e(String str) {
        String str2 = "";
        if (m(str)) {
            if (getReadableDatabase() == null) {
                return "";
            }
            ?? IsClosed = 0;
            IsClosed = 0;
            IsClosed = 0;
            try {
                try {
                    Cursor cursorQuery = getReadableDatabase().query("campaignDAIDao", new String[]{"rid_n"}, "unique_key = ?", new String[]{str}, null, null, "unique_key");
                    if (cursorQuery != null) {
                        try {
                            int count = cursorQuery.getCount();
                            IsClosed = count;
                            if (count > 0) {
                                boolean zMoveToFirst = cursorQuery.moveToFirst();
                                IsClosed = zMoveToFirst;
                                if (zMoveToFirst) {
                                    String string = cursorQuery.getString(0);
                                    str2 = string;
                                    IsClosed = string;
                                }
                            }
                        } catch (Exception e) {
                            IsClosed = cursorQuery;
                            e = e;
                            e.printStackTrace();
                            if (IsClosed != 0 && !IsClosed.isClosed()) {
                                IsClosed.close();
                            }
                            return str2;
                        } catch (Throwable th) {
                            th = th;
                            IsClosed = cursorQuery;
                            if (IsClosed != 0 && !IsClosed.isClosed()) {
                                IsClosed.close();
                            }
                            throw th;
                        }
                    }
                    if (cursorQuery != null && (IsClosed = cursorQuery.isClosed()) == 0) {
                        cursorQuery.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e2) {
                e = e2;
            }
        }
        return str2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v5, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v9 */
    public final synchronized String f(String str) {
        String str2 = "";
        if (m(str)) {
            if (getReadableDatabase() == null) {
                return "";
            }
            ?? IsClosed = 0;
            IsClosed = 0;
            IsClosed = 0;
            try {
                try {
                    Cursor cursorQuery = getReadableDatabase().query("campaignDAIDao", new String[]{"unitid"}, "unique_key = ?", new String[]{str}, null, null, "unique_key");
                    if (cursorQuery != null) {
                        try {
                            int count = cursorQuery.getCount();
                            IsClosed = count;
                            if (count > 0) {
                                boolean zMoveToFirst = cursorQuery.moveToFirst();
                                IsClosed = zMoveToFirst;
                                if (zMoveToFirst) {
                                    String string = cursorQuery.getString(0);
                                    str2 = string;
                                    IsClosed = string;
                                }
                            }
                        } catch (Exception e) {
                            IsClosed = cursorQuery;
                            e = e;
                            e.printStackTrace();
                            if (IsClosed != 0 && !IsClosed.isClosed()) {
                                IsClosed.close();
                            }
                            return str2;
                        } catch (Throwable th) {
                            th = th;
                            IsClosed = cursorQuery;
                            if (IsClosed != 0 && !IsClosed.isClosed()) {
                                IsClosed.close();
                            }
                            throw th;
                        }
                    }
                    if (cursorQuery != null && (IsClosed = cursorQuery.isClosed()) == 0) {
                        cursorQuery.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e2) {
                e = e2;
            }
        }
        return str2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v5, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v9 */
    public final synchronized String g(String str) {
        String str2 = "";
        if (m(str)) {
            if (getReadableDatabase() == null) {
                return "";
            }
            ?? IsClosed = 0;
            IsClosed = 0;
            IsClosed = 0;
            try {
                try {
                    Cursor cursorQuery = getReadableDatabase().query("campaignDAIDao", new String[]{"id"}, "unique_key = ?", new String[]{str}, null, null, "unique_key");
                    if (cursorQuery != null) {
                        try {
                            int count = cursorQuery.getCount();
                            IsClosed = count;
                            if (count > 0) {
                                boolean zMoveToFirst = cursorQuery.moveToFirst();
                                IsClosed = zMoveToFirst;
                                if (zMoveToFirst) {
                                    String string = cursorQuery.getString(0);
                                    str2 = string;
                                    IsClosed = string;
                                }
                            }
                        } catch (Exception e) {
                            IsClosed = cursorQuery;
                            e = e;
                            e.printStackTrace();
                            if (IsClosed != 0 && !IsClosed.isClosed()) {
                                IsClosed.close();
                            }
                            return str2;
                        } catch (Throwable th) {
                            th = th;
                            IsClosed = cursorQuery;
                            if (IsClosed != 0 && !IsClosed.isClosed()) {
                                IsClosed.close();
                            }
                            throw th;
                        }
                    }
                    if (cursorQuery != null && (IsClosed = cursorQuery.isClosed()) == 0) {
                        cursorQuery.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e2) {
                e = e2;
            }
        }
        return str2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v5, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v9 */
    public final synchronized String h(String str) {
        String str2 = "";
        if (m(str)) {
            if (getReadableDatabase() == null) {
                return "";
            }
            ?? IsClosed = 0;
            IsClosed = 0;
            IsClosed = 0;
            try {
                try {
                    Cursor cursorQuery = getReadableDatabase().query("campaignDAIDao", new String[]{CampaignEx.JSON_KEY_PACKAGE_NAME}, "unique_key = ?", new String[]{str}, null, null, "unique_key");
                    if (cursorQuery != null) {
                        try {
                            int count = cursorQuery.getCount();
                            IsClosed = count;
                            if (count > 0) {
                                boolean zMoveToFirst = cursorQuery.moveToFirst();
                                IsClosed = zMoveToFirst;
                                if (zMoveToFirst) {
                                    String string = cursorQuery.getString(0);
                                    str2 = string;
                                    IsClosed = string;
                                }
                            }
                        } catch (Exception e) {
                            IsClosed = cursorQuery;
                            e = e;
                            e.printStackTrace();
                            if (IsClosed != 0 && !IsClosed.isClosed()) {
                                IsClosed.close();
                            }
                            return str2;
                        } catch (Throwable th) {
                            th = th;
                            IsClosed = cursorQuery;
                            if (IsClosed != 0 && !IsClosed.isClosed()) {
                                IsClosed.close();
                            }
                            throw th;
                        }
                    }
                    if (cursorQuery != null && (IsClosed = cursorQuery.isClosed()) == 0) {
                        cursorQuery.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e2) {
                e = e2;
            }
        }
        return str2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v5, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v9 */
    public final synchronized String i(String str) {
        String str2 = "";
        if (m(str)) {
            if (getReadableDatabase() == null) {
                return "";
            }
            ?? IsClosed = 0;
            IsClosed = 0;
            IsClosed = 0;
            try {
                try {
                    Cursor cursorQuery = getReadableDatabase().query("campaignDAIDao", new String[]{"save_path"}, "unique_key = ?", new String[]{str}, null, null, "unique_key");
                    if (cursorQuery != null) {
                        try {
                            int count = cursorQuery.getCount();
                            IsClosed = count;
                            if (count > 0) {
                                boolean zMoveToFirst = cursorQuery.moveToFirst();
                                IsClosed = zMoveToFirst;
                                if (zMoveToFirst) {
                                    String string = cursorQuery.getString(0);
                                    str2 = string;
                                    IsClosed = string;
                                }
                            }
                        } catch (Exception e) {
                            IsClosed = cursorQuery;
                            e = e;
                            e.printStackTrace();
                            if (IsClosed != 0 && !IsClosed.isClosed()) {
                                IsClosed.close();
                            }
                            return str2;
                        } catch (Throwable th) {
                            th = th;
                            IsClosed = cursorQuery;
                            if (IsClosed != 0 && !IsClosed.isClosed()) {
                                IsClosed.close();
                            }
                            throw th;
                        }
                    }
                    if (cursorQuery != null && (IsClosed = cursorQuery.isClosed()) == 0) {
                        cursorQuery.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e2) {
                e = e2;
            }
        }
        return str2;
    }

    public final synchronized int j(String str) {
        int i = -2;
        if (getReadableDatabase() == null) {
            return -2;
        }
        Cursor cursor = null;
        try {
            try {
                Cursor cursorQuery = getReadableDatabase().query("campaignDAIDao", new String[]{DomainCampaignEx.JSON_KEY_ATAT_TYPE}, "package_name = ?", new String[]{str}, null, null, "unique_key");
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.getCount() > 0 && cursorQuery.moveToFirst()) {
                            i = cursorQuery.getInt(0);
                        }
                    } catch (Exception e) {
                        cursor = cursorQuery;
                        e = e;
                        e.printStackTrace();
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                        }
                        return i;
                    } catch (Throwable th) {
                        th = th;
                        cursor = cursorQuery;
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
                if (cursorQuery != null && !cursorQuery.isClosed()) {
                    cursorQuery.close();
                }
            } catch (Exception e2) {
                e = e2;
            }
            return i;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v4, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v8 */
    public final synchronized String k(String str) {
        String str2 = "";
        if (getReadableDatabase() == null) {
            return "";
        }
        ?? IsClosed = 0;
        IsClosed = 0;
        IsClosed = 0;
        try {
            try {
                Cursor cursorQuery = getReadableDatabase().query("campaignDAIDao", new String[]{"app_name"}, "package_name = ?", new String[]{str}, null, null, "unique_key");
                if (cursorQuery != null) {
                    try {
                        int count = cursorQuery.getCount();
                        IsClosed = count;
                        if (count > 0) {
                            boolean zMoveToFirst = cursorQuery.moveToFirst();
                            IsClosed = zMoveToFirst;
                            if (zMoveToFirst) {
                                String string = cursorQuery.getString(0);
                                str2 = string;
                                IsClosed = string;
                            }
                        }
                    } catch (Exception e) {
                        IsClosed = cursorQuery;
                        e = e;
                        e.printStackTrace();
                        if (IsClosed != 0 && !IsClosed.isClosed()) {
                            IsClosed.close();
                        }
                        return str2;
                    } catch (Throwable th) {
                        th = th;
                        IsClosed = cursorQuery;
                        if (IsClosed != 0 && !IsClosed.isClosed()) {
                            IsClosed.close();
                        }
                        throw th;
                    }
                }
                if (cursorQuery != null && (IsClosed = cursorQuery.isClosed()) == 0) {
                    cursorQuery.close();
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
        }
        return str2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v4, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v8 */
    public final synchronized String l(String str) {
        String str2 = "";
        if (getReadableDatabase() == null) {
            return "";
        }
        ?? IsClosed = 0;
        IsClosed = 0;
        IsClosed = 0;
        try {
            try {
                Cursor cursorQuery = getReadableDatabase().query("campaignDAIDao", new String[]{"unique_key"}, "package_name = ?", new String[]{str}, null, null, "unique_key");
                if (cursorQuery != null) {
                    try {
                        int count = cursorQuery.getCount();
                        IsClosed = count;
                        if (count > 0) {
                            boolean zMoveToFirst = cursorQuery.moveToFirst();
                            IsClosed = zMoveToFirst;
                            if (zMoveToFirst) {
                                String string = cursorQuery.getString(0);
                                str2 = string;
                                IsClosed = string;
                            }
                        }
                    } catch (Exception e) {
                        IsClosed = cursorQuery;
                        e = e;
                        e.printStackTrace();
                        if (IsClosed != 0 && !IsClosed.isClosed()) {
                            IsClosed.close();
                        }
                        return str2;
                    } catch (Throwable th) {
                        th = th;
                        IsClosed = cursorQuery;
                        if (IsClosed != 0 && !IsClosed.isClosed()) {
                            IsClosed.close();
                        }
                        throw th;
                    }
                }
                if (cursorQuery != null && (IsClosed = cursorQuery.isClosed()) == 0) {
                    cursorQuery.close();
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
        }
        return str2;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x003d A[Catch: all -> 0x0063, PHI: r0 r5
  0x003d: PHI (r0v7 boolean) = (r0v5 boolean), (r0v10 boolean) binds: [B:26:0x0051, B:16:0x003b] A[DONT_GENERATE, DONT_INLINE]
  0x003d: PHI (r5v10 android.database.Cursor) = (r5v9 android.database.Cursor), (r5v11 android.database.Cursor) binds: [B:26:0x0051, B:16:0x003b] A[DONT_GENERATE, DONT_INLINE], TRY_LEAVE, TryCatch #2 {, blocks: (B:3:0x0001, B:15:0x0037, B:17:0x003d, B:25:0x004d, B:32:0x0059, B:34:0x005f, B:35:0x0062), top: B:41:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private synchronized boolean m(java.lang.String r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L63
            r0.<init>()     // Catch: java.lang.Throwable -> L63
            java.lang.String r1 = "SELECT id FROM campaignDAIDao WHERE unique_key='"
            r0.append(r1)     // Catch: java.lang.Throwable -> L63
            r0.append(r5)     // Catch: java.lang.Throwable -> L63
            java.lang.String r5 = "'"
            r0.append(r5)     // Catch: java.lang.Throwable -> L63
            java.lang.String r5 = r0.toString()     // Catch: java.lang.Throwable -> L63
            r0 = 0
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r4.getReadableDatabase()     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L44
            android.database.Cursor r5 = r2.rawQuery(r5, r1)     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L44
            if (r5 == 0) goto L30
            int r1 = r5.getCount()     // Catch: java.lang.Exception -> L2e java.lang.Throwable -> L56
            if (r1 <= 0) goto L30
            r5.close()     // Catch: java.lang.Exception -> L2e java.lang.Throwable -> L56
            r0 = 1
            goto L35
        L2e:
            r1 = move-exception
            goto L48
        L30:
            if (r5 == 0) goto L35
            r5.close()     // Catch: java.lang.Exception -> L2e java.lang.Throwable -> L56
        L35:
            if (r5 == 0) goto L54
            boolean r1 = r5.isClosed()     // Catch: java.lang.Throwable -> L63
            if (r1 != 0) goto L54
        L3d:
            r5.close()     // Catch: java.lang.Throwable -> L63
            goto L54
        L41:
            r0 = move-exception
            r5 = r1
            goto L57
        L44:
            r5 = move-exception
            r3 = r1
            r1 = r5
            r5 = r3
        L48:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L56
            if (r5 == 0) goto L54
            boolean r1 = r5.isClosed()     // Catch: java.lang.Throwable -> L63
            if (r1 != 0) goto L54
            goto L3d
        L54:
            monitor-exit(r4)
            return r0
        L56:
            r0 = move-exception
        L57:
            if (r5 == 0) goto L62
            boolean r1 = r5.isClosed()     // Catch: java.lang.Throwable -> L63
            if (r1 != 0) goto L62
            r5.close()     // Catch: java.lang.Throwable -> L63
        L62:
            throw r0     // Catch: java.lang.Throwable -> L63
        L63:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.db.e.m(java.lang.String):boolean");
    }
}
