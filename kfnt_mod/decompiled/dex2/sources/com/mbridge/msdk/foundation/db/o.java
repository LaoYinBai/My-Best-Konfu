package com.mbridge.msdk.foundation.db;

import android.content.ContentValues;
import android.database.Cursor;
import org.json.JSONObject;

/* compiled from: ReplaceTempDao.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class o extends a {
    private static String a = "ReplaceTempDao";
    private static o b;

    private o(h hVar) {
        super(hVar);
    }

    public static o a(h hVar) {
        if (b == null) {
            synchronized (f.class) {
                if (b == null) {
                    b = new o(hVar);
                }
            }
        }
        return b;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final org.json.JSONObject a(java.lang.String r4) {
        /*
            r3 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            r1 = 0
            if (r0 == 0) goto L8
            return r1
        L8:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "SELECT * FROM c_replace_temp WHERE d_key = '"
            r0.append(r2)
            r0.append(r4)
            java.lang.String r4 = "'"
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            android.database.sqlite.SQLiteDatabase r0 = r3.getReadableDatabase()
            android.database.Cursor r4 = r0.rawQuery(r4, r1)
            if (r4 == 0) goto L4f
            int r0 = r4.getCount()     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L4d
            if (r0 <= 0) goto L4f
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L4d
            r0.<init>()     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L4d
            r1 = r0
        L34:
            boolean r0 = r4.moveToNext()     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L4d
            if (r0 == 0) goto L6a
            java.lang.String r0 = "d_value"
            int r0 = r4.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L4d
            java.lang.String r0 = r4.getString(r0)     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L4d
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L4d
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L4d
            r1 = r2
            goto L34
        L4b:
            r0 = move-exception
            goto L64
        L4d:
            r0 = move-exception
            goto L55
        L4f:
            if (r4 == 0) goto L6a
            r4.close()     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L4d
            goto L6a
        L55:
            java.lang.String r2 = com.mbridge.msdk.foundation.db.o.a     // Catch: java.lang.Throwable -> L4b
            java.lang.String r0 = r0.getMessage()     // Catch: java.lang.Throwable -> L4b
            com.mbridge.msdk.foundation.tools.s.d(r2, r0)     // Catch: java.lang.Throwable -> L4b
            if (r4 == 0) goto L6d
        L60:
            r4.close()
            goto L6d
        L64:
            if (r4 == 0) goto L69
            r4.close()
        L69:
            throw r0
        L6a:
            if (r4 == 0) goto L6d
            goto L60
        L6d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.db.o.a(java.lang.String):org.json.JSONObject");
    }

    public final void a(String str, JSONObject jSONObject) {
        boolean z;
        try {
            if (getWritableDatabase() == null) {
                return;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("d_key", str);
            contentValues.put("d_value", jSONObject.toString());
            contentValues.put("time", Long.valueOf(System.currentTimeMillis()));
            Cursor cursorRawQuery = getReadableDatabase().rawQuery("SELECT d_value FROM c_replace_temp WHERE d_key='" + str + "'", null);
            if (cursorRawQuery == null || cursorRawQuery.getCount() <= 0) {
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                z = false;
            } else {
                cursorRawQuery.close();
                z = true;
            }
            if (!z) {
                getWritableDatabase().insert("c_replace_temp", null, contentValues);
                return;
            }
            getWritableDatabase().update("c_replace_temp", contentValues, "d_key = '" + str + "'", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x007f A[DONT_GENERATE, PHI: r1
  0x007f: PHI (r1v7 org.json.JSONObject) = (r1v4 org.json.JSONObject), (r1v9 org.json.JSONObject) binds: [B:29:0x007d, B:35:0x008a] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final org.json.JSONObject a() {
        /*
            r8 = this;
            r0 = 604800000(0x240c8400, float:3.046947E-17)
            long r0 = (long) r0
            long r2 = java.lang.System.currentTimeMillis()
            long r2 = r2 - r0
            android.database.sqlite.SQLiteDatabase r0 = r8.getWritableDatabase()     // Catch: java.lang.Exception -> L26
            if (r0 != 0) goto L10
            goto L2a
        L10:
            android.database.sqlite.SQLiteDatabase r0 = r8.getWritableDatabase()     // Catch: java.lang.Exception -> L26
            java.lang.String r1 = "c_replace_temp"
            java.lang.String r4 = "time<?"
            r5 = 1
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch: java.lang.Exception -> L26
            r6 = 0
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch: java.lang.Exception -> L26
            r5[r6] = r2     // Catch: java.lang.Exception -> L26
            r0.delete(r1, r4, r5)     // Catch: java.lang.Exception -> L26
            goto L2a
        L26:
            r0 = move-exception
            r0.printStackTrace()
        L2a:
            java.lang.String r0 = "SELECT * FROM c_replace_temp"
            android.database.sqlite.SQLiteDatabase r1 = r8.getReadableDatabase()
            r2 = 0
            android.database.Cursor r0 = r1.rawQuery(r0, r2)
            if (r0 == 0) goto L6e
            int r1 = r0.getCount()     // Catch: java.lang.Throwable -> L67 java.lang.Throwable -> L69
            if (r1 <= 0) goto L6e
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L67 java.lang.Throwable -> L69
            r1.<init>()     // Catch: java.lang.Throwable -> L67 java.lang.Throwable -> L69
        L42:
            boolean r2 = r0.moveToNext()     // Catch: java.lang.Throwable -> L65 java.lang.Throwable -> L67
            if (r2 == 0) goto L8a
            java.lang.String r2 = "d_key"
            int r2 = r0.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L65 java.lang.Throwable -> L67
            java.lang.String r2 = r0.getString(r2)     // Catch: java.lang.Throwable -> L65 java.lang.Throwable -> L67
            java.lang.String r3 = "d_value"
            int r3 = r0.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L65 java.lang.Throwable -> L67
            java.lang.String r3 = r0.getString(r3)     // Catch: java.lang.Throwable -> L65 java.lang.Throwable -> L67
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L65 java.lang.Throwable -> L67
            r4.<init>(r3)     // Catch: java.lang.Throwable -> L65 java.lang.Throwable -> L67
            r1.put(r2, r4)     // Catch: java.lang.Throwable -> L65 java.lang.Throwable -> L67
            goto L42
        L65:
            r2 = move-exception
            goto L74
        L67:
            r1 = move-exception
            goto L83
        L69:
            r1 = move-exception
            r7 = r2
            r2 = r1
            r1 = r7
            goto L74
        L6e:
            if (r0 == 0) goto L89
            r0.close()     // Catch: java.lang.Throwable -> L67 java.lang.Throwable -> L69
            goto L89
        L74:
            java.lang.String r3 = com.mbridge.msdk.foundation.db.o.a     // Catch: java.lang.Throwable -> L67
            java.lang.String r2 = r2.getMessage()     // Catch: java.lang.Throwable -> L67
            com.mbridge.msdk.foundation.tools.s.d(r3, r2)     // Catch: java.lang.Throwable -> L67
            if (r0 == 0) goto L8d
        L7f:
            r0.close()
            goto L8d
        L83:
            if (r0 == 0) goto L88
            r0.close()
        L88:
            throw r1
        L89:
            r1 = r2
        L8a:
            if (r0 == 0) goto L8d
            goto L7f
        L8d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.db.o.a():org.json.JSONObject");
    }
}
