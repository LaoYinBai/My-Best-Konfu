package com.alibaba.sdk.android.httpdns.b;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class d extends SQLiteOpenHelper {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f2327a = new Object();

    d(Context context) {
        super(context, "aliclound_httpdns.db", (SQLiteDatabase.CursorFactory) null, 1);
    }

    private long a(SQLiteDatabase sQLiteDatabase, g gVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("host_id", Long.valueOf(gVar.h));
        contentValues.put("ip", gVar.k);
        contentValues.put("ttl", gVar.l);
        try {
            return sQLiteDatabase.insert("ip", null, contentValues);
        } catch (Exception unused) {
            return 0L;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00ac A[PHI: r3
  0x00ac: PHI (r3v4 android.database.sqlite.SQLiteDatabase) = (r3v3 android.database.sqlite.SQLiteDatabase), (r3v5 android.database.sqlite.SQLiteDatabase) binds: [B:31:0x00aa, B:16:0x008d] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.List<com.alibaba.sdk.android.httpdns.b.g> a(long r7) throws java.lang.Throwable {
        /*
            r6 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "SELECT * FROM "
            r1.append(r2)
            java.lang.String r2 = "ip"
            r1.append(r2)
            java.lang.String r2 = " WHERE "
            r1.append(r2)
            java.lang.String r2 = "host_id"
            r1.append(r2)
            java.lang.String r2 = " =? ;"
            r1.append(r2)
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = r6.getWritableDatabase()     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> La3
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            r5 = 0
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            r4[r5] = r7     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            android.database.Cursor r7 = r3.rawQuery(r1, r4)     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            if (r7 == 0) goto L88
            int r8 = r7.getCount()     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            if (r8 <= 0) goto L88
            r7.moveToFirst()     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
        L45:
            com.alibaba.sdk.android.httpdns.b.g r8 = new com.alibaba.sdk.android.httpdns.b.g     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            r8.<init>()     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            java.lang.String r1 = "id"
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            int r1 = r7.getInt(r1)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            long r1 = (long) r1     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            r8.id = r1     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            java.lang.String r1 = "host_id"
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            int r1 = r7.getInt(r1)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            long r1 = (long) r1     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            r8.h = r1     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            java.lang.String r1 = "ip"
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            java.lang.String r1 = r7.getString(r1)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            r8.k = r1     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            java.lang.String r1 = "ttl"
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            java.lang.String r1 = r7.getString(r1)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            r8.l = r1     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            r0.add(r8)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            boolean r8 = r7.moveToNext()     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            if (r8 != 0) goto L45
            goto L88
        L86:
            r8 = move-exception
            goto L98
        L88:
            if (r7 == 0) goto L8d
            r7.close()
        L8d:
            if (r3 == 0) goto Laf
            goto Lac
        L90:
            r8 = move-exception
            r7 = r2
            goto L98
        L93:
            r7 = r2
            goto La5
        L95:
            r8 = move-exception
            r7 = r2
            r3 = r7
        L98:
            if (r7 == 0) goto L9d
            r7.close()
        L9d:
            if (r3 == 0) goto La2
            r3.close()
        La2:
            throw r8
        La3:
            r7 = r2
            r3 = r7
        La5:
            if (r7 == 0) goto Laa
            r7.close()
        Laa:
            if (r3 == 0) goto Laf
        Lac:
            r3.close()
        Laf:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.httpdns.b.d.a(long):java.util.List");
    }

    private List<g> a(e eVar) {
        return a(eVar.id);
    }

    /* renamed from: a, reason: collision with other method in class */
    private void m8a(long j) throws Throwable {
        SQLiteDatabase writableDatabase;
        try {
            writableDatabase = getWritableDatabase();
            try {
                writableDatabase.delete(com.alipay.sdk.cons.c.f, "id = ?", new String[]{String.valueOf(j)});
                if (writableDatabase == null) {
                    return;
                }
            } catch (Exception unused) {
                if (writableDatabase == null) {
                    return;
                }
                writableDatabase.close();
            } catch (Throwable th) {
                th = th;
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
                throw th;
            }
        } catch (Exception unused2) {
            writableDatabase = null;
        } catch (Throwable th2) {
            th = th2;
            writableDatabase = null;
        }
        writableDatabase.close();
    }

    private void a(g gVar) throws Throwable {
        m9b(gVar.id);
    }

    private long b(SQLiteDatabase sQLiteDatabase, g gVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("host_id", Long.valueOf(gVar.h));
        contentValues.put("ip", gVar.k);
        contentValues.put("ttl", gVar.l);
        try {
            return sQLiteDatabase.insert("ipv6", null, contentValues);
        } catch (Exception unused) {
            return 0L;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00ac A[PHI: r3
  0x00ac: PHI (r3v4 android.database.sqlite.SQLiteDatabase) = (r3v3 android.database.sqlite.SQLiteDatabase), (r3v5 android.database.sqlite.SQLiteDatabase) binds: [B:31:0x00aa, B:16:0x008d] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.List<com.alibaba.sdk.android.httpdns.b.g> b(long r7) throws java.lang.Throwable {
        /*
            r6 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "SELECT * FROM "
            r1.append(r2)
            java.lang.String r2 = "ipv6"
            r1.append(r2)
            java.lang.String r2 = " WHERE "
            r1.append(r2)
            java.lang.String r2 = "host_id"
            r1.append(r2)
            java.lang.String r2 = " =? ;"
            r1.append(r2)
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = r6.getWritableDatabase()     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> La3
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            r5 = 0
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            r4[r5] = r7     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            android.database.Cursor r7 = r3.rawQuery(r1, r4)     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            if (r7 == 0) goto L88
            int r8 = r7.getCount()     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            if (r8 <= 0) goto L88
            r7.moveToFirst()     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
        L45:
            com.alibaba.sdk.android.httpdns.b.g r8 = new com.alibaba.sdk.android.httpdns.b.g     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            r8.<init>()     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            java.lang.String r1 = "id"
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            int r1 = r7.getInt(r1)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            long r1 = (long) r1     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            r8.id = r1     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            java.lang.String r1 = "host_id"
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            int r1 = r7.getInt(r1)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            long r1 = (long) r1     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            r8.h = r1     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            java.lang.String r1 = "ip"
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            java.lang.String r1 = r7.getString(r1)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            r8.k = r1     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            java.lang.String r1 = "ttl"
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            java.lang.String r1 = r7.getString(r1)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            r8.l = r1     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            r0.add(r8)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            boolean r8 = r7.moveToNext()     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> La5
            if (r8 != 0) goto L45
            goto L88
        L86:
            r8 = move-exception
            goto L98
        L88:
            if (r7 == 0) goto L8d
            r7.close()
        L8d:
            if (r3 == 0) goto Laf
            goto Lac
        L90:
            r8 = move-exception
            r7 = r2
            goto L98
        L93:
            r7 = r2
            goto La5
        L95:
            r8 = move-exception
            r7 = r2
            r3 = r7
        L98:
            if (r7 == 0) goto L9d
            r7.close()
        L9d:
            if (r3 == 0) goto La2
            r3.close()
        La2:
            throw r8
        La3:
            r7 = r2
            r3 = r7
        La5:
            if (r7 == 0) goto Laa
            r7.close()
        Laa:
            if (r3 == 0) goto Laf
        Lac:
            r3.close()
        Laf:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.httpdns.b.d.b(long):java.util.List");
    }

    private List<g> b(e eVar) {
        return b(eVar.id);
    }

    /* renamed from: b, reason: collision with other method in class */
    private void m9b(long j) throws Throwable {
        SQLiteDatabase writableDatabase;
        try {
            writableDatabase = getWritableDatabase();
            try {
                writableDatabase.delete("ip", "id = ?", new String[]{String.valueOf(j)});
                if (writableDatabase == null) {
                    return;
                }
            } catch (Exception unused) {
                if (writableDatabase == null) {
                    return;
                }
                writableDatabase.close();
            } catch (Throwable th) {
                th = th;
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
                throw th;
            }
        } catch (Exception unused2) {
            writableDatabase = null;
        } catch (Throwable th2) {
            th = th2;
            writableDatabase = null;
        }
        writableDatabase.close();
    }

    private void b(g gVar) throws Throwable {
        c(gVar.id);
    }

    private void c(long j) throws Throwable {
        SQLiteDatabase writableDatabase;
        try {
            writableDatabase = getWritableDatabase();
            try {
                writableDatabase.delete("ipv6", "id = ?", new String[]{String.valueOf(j)});
                if (writableDatabase == null) {
                    return;
                }
            } catch (Exception unused) {
                if (writableDatabase == null) {
                    return;
                }
                writableDatabase.close();
            } catch (Throwable th) {
                th = th;
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
                throw th;
            }
        } catch (Exception unused2) {
            writableDatabase = null;
        } catch (Throwable th2) {
            th = th2;
            writableDatabase = null;
        }
        writableDatabase.close();
    }

    private void c(e eVar) throws Throwable {
        m8a(eVar.id);
    }

    /* renamed from: a, reason: collision with other method in class */
    long m10a(e eVar) {
        SQLiteDatabase writableDatabase;
        synchronized (f2327a) {
            b(eVar.i, eVar.h);
            ContentValues contentValues = new ContentValues();
            try {
                writableDatabase = getWritableDatabase();
            } catch (Exception unused) {
                writableDatabase = null;
            } catch (Throwable th) {
                th = th;
                writableDatabase = null;
            }
            try {
                writableDatabase.beginTransaction();
                contentValues.put(com.alipay.sdk.cons.c.f, eVar.h);
                contentValues.put("sp", eVar.i);
                contentValues.put(com.bytedance.b.a.a.a.h, c.c(eVar.j));
                long jInsert = writableDatabase.insert(com.alipay.sdk.cons.c.f, null, contentValues);
                eVar.id = jInsert;
                if (eVar.f2328a != null) {
                    Iterator<g> it = eVar.f2328a.iterator();
                    while (it.hasNext()) {
                        g next = it.next();
                        next.h = jInsert;
                        next.id = a(writableDatabase, next);
                    }
                }
                if (eVar.f2329b != null) {
                    Iterator<g> it2 = eVar.f2329b.iterator();
                    while (it2.hasNext()) {
                        g next2 = it2.next();
                        next2.h = jInsert;
                        next2.id = b(writableDatabase, next2);
                    }
                }
                writableDatabase.setTransactionSuccessful();
                if (writableDatabase != null) {
                    writableDatabase.endTransaction();
                    writableDatabase.close();
                }
                return jInsert;
            } catch (Exception unused2) {
                if (writableDatabase != null) {
                    writableDatabase.endTransaction();
                    writableDatabase.close();
                }
                return 0L;
            } catch (Throwable th2) {
                th = th2;
                if (writableDatabase != null) {
                    writableDatabase.endTransaction();
                    writableDatabase.close();
                }
                throw th;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00a0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    com.alibaba.sdk.android.httpdns.b.e a(java.lang.String r7, java.lang.String r8) {
        /*
            r6 = this;
            java.lang.Object r0 = com.alibaba.sdk.android.httpdns.b.d.f2327a
            monitor-enter(r0)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lcd
            r1.<init>()     // Catch: java.lang.Throwable -> Lcd
            java.lang.String r2 = "SELECT * FROM "
            r1.append(r2)     // Catch: java.lang.Throwable -> Lcd
            java.lang.String r2 = "host"
            r1.append(r2)     // Catch: java.lang.Throwable -> Lcd
            java.lang.String r2 = " WHERE "
            r1.append(r2)     // Catch: java.lang.Throwable -> Lcd
            java.lang.String r2 = "sp"
            r1.append(r2)     // Catch: java.lang.Throwable -> Lcd
            java.lang.String r2 = " =? "
            r1.append(r2)     // Catch: java.lang.Throwable -> Lcd
            java.lang.String r2 = " AND "
            r1.append(r2)     // Catch: java.lang.Throwable -> Lcd
            java.lang.String r2 = "host"
            r1.append(r2)     // Catch: java.lang.Throwable -> Lcd
            java.lang.String r2 = " =? ;"
            r1.append(r2)     // Catch: java.lang.Throwable -> Lcd
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = r6.getReadableDatabase()     // Catch: java.lang.Throwable -> Lb2 java.lang.Exception -> Lc0
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> Lac java.lang.Exception -> Laf
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> Lac java.lang.Exception -> Laf
            r5 = 0
            r4[r5] = r7     // Catch: java.lang.Throwable -> Lac java.lang.Exception -> Laf
            r7 = 1
            r4[r7] = r8     // Catch: java.lang.Throwable -> Lac java.lang.Exception -> Laf
            android.database.Cursor r7 = r3.rawQuery(r1, r4)     // Catch: java.lang.Throwable -> Lac java.lang.Exception -> Laf
            if (r7 == 0) goto La0
            int r8 = r7.getCount()     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
            if (r8 <= 0) goto La0
            r7.moveToFirst()     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
            com.alibaba.sdk.android.httpdns.b.e r8 = new com.alibaba.sdk.android.httpdns.b.e     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
            r8.<init>()     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9e
            java.lang.String r1 = "id"
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> Lc3
            int r1 = r7.getInt(r1)     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> Lc3
            long r1 = (long) r1     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> Lc3
            r8.id = r1     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> Lc3
            java.lang.String r1 = "host"
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> Lc3
            java.lang.String r1 = r7.getString(r1)     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> Lc3
            r8.h = r1     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> Lc3
            java.lang.String r1 = "sp"
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> Lc3
            java.lang.String r1 = r7.getString(r1)     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> Lc3
            r8.i = r1     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> Lc3
            java.lang.String r1 = "time"
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> Lc3
            java.lang.String r1 = r7.getString(r1)     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> Lc3
            java.lang.String r1 = com.alibaba.sdk.android.httpdns.b.c.d(r1)     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> Lc3
            r8.j = r1     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> Lc3
            java.util.List r1 = r6.a(r8)     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> Lc3
            java.util.ArrayList r1 = (java.util.ArrayList) r1     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> Lc3
            r8.f2328a = r1     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> Lc3
            java.util.List r1 = r6.b(r8)     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> Lc3
            java.util.ArrayList r1 = (java.util.ArrayList) r1     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> Lc3
            r8.f2329b = r1     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> Lc3
            goto La1
        L9c:
            r8 = move-exception
            goto Lb5
        L9e:
            r8 = r2
            goto Lc3
        La0:
            r8 = r2
        La1:
            if (r7 == 0) goto La6
            r7.close()     // Catch: java.lang.Throwable -> Lcd
        La6:
            if (r3 == 0) goto Lcb
        La8:
            r3.close()     // Catch: java.lang.Throwable -> Lcd
            goto Lcb
        Lac:
            r8 = move-exception
            r7 = r2
            goto Lb5
        Laf:
            r7 = r2
            r8 = r7
            goto Lc3
        Lb2:
            r8 = move-exception
            r7 = r2
            r3 = r7
        Lb5:
            if (r7 == 0) goto Lba
            r7.close()     // Catch: java.lang.Throwable -> Lcd
        Lba:
            if (r3 == 0) goto Lbf
            r3.close()     // Catch: java.lang.Throwable -> Lcd
        Lbf:
            throw r8     // Catch: java.lang.Throwable -> Lcd
        Lc0:
            r7 = r2
            r8 = r7
            r3 = r8
        Lc3:
            if (r7 == 0) goto Lc8
            r7.close()     // Catch: java.lang.Throwable -> Lcd
        Lc8:
            if (r3 == 0) goto Lcb
            goto La8
        Lcb:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lcd
            return r8
        Lcd:
            r7 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lcd
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.httpdns.b.d.a(java.lang.String, java.lang.String):com.alibaba.sdk.android.httpdns.b.e");
    }

    List<e> b() {
        ArrayList arrayList;
        Cursor cursorRawQuery;
        SQLiteDatabase readableDatabase;
        synchronized (f2327a) {
            arrayList = new ArrayList();
            try {
                readableDatabase = getReadableDatabase();
                try {
                    cursorRawQuery = readableDatabase.rawQuery("SELECT * FROM " + com.alipay.sdk.cons.c.f + " ; ", null);
                    if (cursorRawQuery != null) {
                        try {
                            if (cursorRawQuery.getCount() > 0) {
                                cursorRawQuery.moveToFirst();
                                do {
                                    e eVar = new e();
                                    eVar.id = cursorRawQuery.getInt(cursorRawQuery.getColumnIndex("id"));
                                    eVar.h = cursorRawQuery.getString(cursorRawQuery.getColumnIndex(com.alipay.sdk.cons.c.f));
                                    eVar.i = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("sp"));
                                    eVar.j = c.d(cursorRawQuery.getString(cursorRawQuery.getColumnIndex(com.bytedance.b.a.a.a.h)));
                                    eVar.f2328a = (ArrayList) a(eVar);
                                    eVar.f2329b = (ArrayList) b(eVar);
                                    arrayList.add(eVar);
                                } while (cursorRawQuery.moveToNext());
                            }
                        } catch (Exception unused) {
                            if (cursorRawQuery != null) {
                                cursorRawQuery.close();
                            }
                            if (readableDatabase != null) {
                                readableDatabase.close();
                            }
                            return arrayList;
                        } catch (Throwable th) {
                            th = th;
                            if (cursorRawQuery != null) {
                                cursorRawQuery.close();
                            }
                            if (readableDatabase != null) {
                                readableDatabase.close();
                            }
                            throw th;
                        }
                    }
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                } catch (Exception unused2) {
                    cursorRawQuery = null;
                } catch (Throwable th2) {
                    th = th2;
                    cursorRawQuery = null;
                }
            } catch (Exception unused3) {
                cursorRawQuery = null;
                readableDatabase = null;
            } catch (Throwable th3) {
                th = th3;
                cursorRawQuery = null;
                readableDatabase = null;
            }
            if (readableDatabase != null) {
                readableDatabase.close();
            }
        }
        return arrayList;
    }

    void b(String str, String str2) {
        synchronized (f2327a) {
            e eVarA = a(str, str2);
            if (eVarA != null) {
                c(eVarA);
                if (eVarA.f2328a != null) {
                    Iterator<g> it = eVarA.f2328a.iterator();
                    while (it.hasNext()) {
                        a(it.next());
                    }
                }
                if (eVarA.f2329b != null) {
                    Iterator<g> it2 = eVarA.f2329b.iterator();
                    while (it2.hasNext()) {
                        b(it2.next());
                    }
                }
            }
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) throws SQLException {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE host (id INTEGER PRIMARY KEY,host TEXT,sp TEXT,time TEXT);");
            sQLiteDatabase.execSQL("CREATE TABLE ip (id INTEGER PRIMARY KEY,host_id INTEGER,ip TEXT,ttl TEXT);");
            sQLiteDatabase.execSQL("CREATE TABLE ipv6 (id INTEGER PRIMARY KEY,host_id INTEGER,ip TEXT,ttl TEXT);");
        } catch (Exception unused) {
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) throws SQLException {
        if (i != i2) {
            try {
                sQLiteDatabase.beginTransaction();
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS host;");
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ip;");
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ipv6;");
                sQLiteDatabase.setTransactionSuccessful();
                sQLiteDatabase.endTransaction();
                onCreate(sQLiteDatabase);
            } catch (Exception unused) {
            }
        }
    }
}
