package com.mbridge.msdk.foundation.db;

import android.content.ContentValues;
import android.database.Cursor;
import com.kwad.sdk.collector.AppStatusRules;

/* compiled from: FrequenceDao.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class m extends a<com.mbridge.msdk.foundation.entity.i> {
    private static m a;

    private m(h hVar) {
        super(hVar);
    }

    public static m a(h hVar) {
        if (a == null) {
            synchronized (m.class) {
                if (a == null) {
                    a = new m(hVar);
                }
            }
        }
        return a;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x003b A[Catch: all -> 0x004f, PHI: r0
  0x003b: PHI (r0v7 android.database.Cursor) = (r0v6 android.database.Cursor), (r0v8 android.database.Cursor) binds: [B:28:0x004a, B:20:0x0039] A[DONT_GENERATE, DONT_INLINE], TRY_ENTER, TryCatch #3 {, blocks: (B:15:0x0032, B:21:0x003b, B:25:0x0045, B:26:0x0048), top: B:36:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized long[] a() {
        /*
            r7 = this;
            monitor-enter(r7)
            java.lang.String r0 = "SELECT id FROM frequence WHERE fc_a<impression_count"
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r7.getReadableDatabase()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L49
            android.database.Cursor r0 = r2.rawQuery(r0, r1)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L49
            if (r0 == 0) goto L39
            int r2 = r0.getCount()     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L4a
            if (r2 <= 0) goto L39
            int r2 = r0.getCount()     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L4a
            long[] r2 = new long[r2]     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L4a
            r3 = 0
        L1b:
            boolean r4 = r0.moveToNext()     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L4a
            if (r4 == 0) goto L30
            java.lang.String r4 = "id"
            int r4 = r0.getColumnIndexOrThrow(r4)     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L4a
            long r4 = r0.getLong(r4)     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L4a
            r2[r3] = r4     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L4a
            int r3 = r3 + 1
            goto L1b
        L30:
            if (r0 == 0) goto L35
            r0.close()     // Catch: java.lang.Throwable -> L4f
        L35:
            monitor-exit(r7)
            return r2
        L37:
            r1 = move-exception
            goto L43
        L39:
            if (r0 == 0) goto L4d
        L3b:
            r0.close()     // Catch: java.lang.Throwable -> L4f
            goto L4d
        L3f:
            r0 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
        L43:
            if (r0 == 0) goto L48
            r0.close()     // Catch: java.lang.Throwable -> L4f
        L48:
            throw r1     // Catch: java.lang.Throwable -> L4f
        L49:
            r0 = r1
        L4a:
            if (r0 == 0) goto L4d
            goto L3b
        L4d:
            monitor-exit(r7)
            return r1
        L4f:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.db.m.a():long[]");
    }

    public final synchronized void b() {
        try {
            String str = "ts<" + (System.currentTimeMillis() - AppStatusRules.DEFAULT_START_TIME);
            if (getWritableDatabase() != null) {
                getWritableDatabase().delete("frequence", str, null);
            }
        } catch (Exception unused) {
        }
    }

    public final synchronized boolean a(String str) {
        Cursor cursor = null;
        try {
            try {
                synchronized (str) {
                    try {
                        Cursor cursorRawQuery = getReadableDatabase().rawQuery("SELECT id FROM frequence WHERE id='" + str + "'", null);
                        if (cursorRawQuery != null) {
                            try {
                                if (cursorRawQuery.getCount() > 0) {
                                    if (cursorRawQuery != null) {
                                        cursorRawQuery.close();
                                    }
                                    return true;
                                }
                            } catch (Throwable th) {
                                th = th;
                                cursor = cursorRawQuery;
                                throw th;
                            }
                        }
                        if (cursorRawQuery != null) {
                            cursorRawQuery.close();
                        }
                        return false;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        } catch (Exception unused) {
            return false;
        }
    }

    public final synchronized void b(String str) {
        if (a(str)) {
            String str2 = "UPDATE frequence Set impression_count=impression_count+1 WHERE id=" + str;
            if (getReadableDatabase() != null) {
                getReadableDatabase().execSQL(str2);
            }
        }
    }

    private synchronized long b(com.mbridge.msdk.foundation.entity.i iVar) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", iVar.a());
            contentValues.put("fc_a", Integer.valueOf(iVar.b()));
            contentValues.put("fc_b", Integer.valueOf(iVar.c()));
            contentValues.put("ts", Long.valueOf(iVar.f()));
            contentValues.put("impression_count", Integer.valueOf(iVar.d()));
            contentValues.put("click_count", Integer.valueOf(iVar.e()));
            contentValues.put("ts", Long.valueOf(iVar.f()));
            if (getWritableDatabase() == null) {
                return -1L;
            }
            return getWritableDatabase().insert("frequence", null, contentValues);
        } catch (Exception unused) {
            return -1L;
        }
    }

    public final synchronized void a(com.mbridge.msdk.foundation.entity.i iVar) {
        if (!a(iVar.a())) {
            b(iVar);
        }
    }
}
