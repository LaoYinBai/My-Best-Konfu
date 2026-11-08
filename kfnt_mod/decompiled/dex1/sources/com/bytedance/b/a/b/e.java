package com.bytedance.b.a.b;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class e {

    /* renamed from: a, reason: collision with root package name */
    static final String[] f3661a = {"_id", com.bytedance.b.a.a.a.n, "type", "timestamp", com.bytedance.b.a.a.a.p, com.bytedance.b.a.a.a.q};

    /* renamed from: b, reason: collision with root package name */
    private static final String f3662b = "_id = ?";

    /* renamed from: c, reason: collision with root package name */
    private static e f3663c;
    private SQLiteDatabase d;

    static e a(Context context) {
        if (f3663c == null) {
            synchronized (e.class) {
                if (f3663c == null) {
                    f3663c = new e(context);
                }
            }
        }
        return f3663c;
    }

    private e(Context context) {
        if (context == null) {
            return;
        }
        try {
            this.d = com.bytedance.b.a.a.a.a(context).getWritableDatabase();
        } catch (Throwable unused) {
        }
    }

    synchronized boolean a() {
        if (this.d != null && this.d.isOpen()) {
            return true;
        }
        d.a("db not establish and open");
        return false;
    }

    synchronized long a(String str) {
        Cursor cursorRawQuery;
        if (!a()) {
            return 0L;
        }
        Cursor cursor = null;
        try {
            String str2 = "select count(*) from queue";
            if (!TextUtils.isEmpty(str)) {
                str2 = "select count(*) from queue " + str;
            }
            cursorRawQuery = this.d.rawQuery(str2, null);
        } catch (Throwable th) {
            th = th;
        }
        try {
            j = cursorRawQuery.moveToNext() ? cursorRawQuery.getLong(0) : 0L;
            a(cursorRawQuery);
        } catch (Throwable th2) {
            cursor = cursorRawQuery;
            th = th2;
            a(cursor);
            throw th;
        }
        return j;
    }

    synchronized void b() {
        try {
        } catch (Throwable th) {
            d.a("closeDatabase error: " + th);
        }
        if (a()) {
            this.d.close();
            this.d = null;
        }
    }

    synchronized long a(String str, byte[] bArr) {
        if (a() && bArr != null && bArr.length > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(com.bytedance.b.a.a.a.n, bArr);
            contentValues.put("type", str);
            contentValues.put("timestamp", Long.valueOf(System.currentTimeMillis()));
            contentValues.put(com.bytedance.b.a.a.a.p, (Integer) 0);
            contentValues.put(com.bytedance.b.a.a.a.q, (Long) 0L);
            return this.d.insert(com.bytedance.b.a.a.a.m, null, contentValues);
        }
        return -1L;
    }

    synchronized void c() {
        if (a()) {
            try {
                this.d.execSQL("DROP TABLE IF EXISTS queue");
                this.d.execSQL(com.bytedance.b.a.a.a.r);
            } catch (Exception e) {
                d.a("recreateTableQueue db exception " + e);
            }
        }
    }

    synchronized void a(String str, int i, long j) {
        String str2;
        String[] strArr;
        if (a()) {
            long jCurrentTimeMillis = System.currentTimeMillis() - j;
            if (TextUtils.isEmpty(str)) {
                str2 = "timestamp <= ? ";
                strArr = new String[]{String.valueOf(jCurrentTimeMillis)};
            } else {
                String str3 = "(timestamp <= ? OR retry_count > " + i + ") and type = ?";
                String[] strArr2 = {String.valueOf(jCurrentTimeMillis), str};
                str2 = str3;
                strArr = strArr2;
            }
            try {
                this.d.delete(com.bytedance.b.a.a.a.m, str2, strArr);
            } catch (Exception e) {
                d.a("delete expire log error:" + e);
            }
        }
    }

    synchronized b a(long j) {
        b bVar;
        Cursor cursor = null;
        b bVar2 = null;
        cursor = null;
        if (!a()) {
            return null;
        }
        try {
            try {
                Cursor cursorQuery = this.d.query(com.bytedance.b.a.a.a.m, f3661a, "_id > ?", new String[]{String.valueOf(j)}, null, null, "_id ASC", "1");
                try {
                    try {
                        if (cursorQuery.moveToNext()) {
                            bVar = new b();
                            try {
                                bVar.f3652a = cursorQuery.getLong(0);
                                bVar.f3653b = cursorQuery.getBlob(1);
                                bVar.f = cursorQuery.getString(2);
                                bVar.f3654c = cursorQuery.getLong(3);
                                bVar.d = cursorQuery.getInt(4);
                                bVar.e = cursorQuery.getLong(5);
                                bVar2 = bVar;
                            } catch (Exception e) {
                                e = e;
                                cursor = cursorQuery;
                                d.a("getLog exception " + e);
                                a(cursor);
                                bVar2 = bVar;
                                return bVar2;
                            }
                        }
                        a(cursorQuery);
                    } catch (Throwable th) {
                        th = th;
                        cursor = cursorQuery;
                        a(cursor);
                        throw th;
                    }
                } catch (Exception e2) {
                    e = e2;
                    bVar = null;
                }
            } catch (Exception e3) {
                e = e3;
                bVar = null;
            }
            return bVar2;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x00c8 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00a9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    synchronized boolean a(long r16, boolean r18, long r19, int r21) {
        /*
            Method dump skipped, instructions count: 207
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.b.a.b.e.a(long, boolean, long, int):boolean");
    }

    static void a(Cursor cursor) {
        if (cursor != null) {
            try {
                if (cursor.isClosed()) {
                    return;
                }
                cursor.close();
            } catch (Exception unused) {
            }
        }
    }
}
