package com.mbridge.msdk.foundation.db;

import android.content.ContentValues;
import android.database.Cursor;
import com.mbridge.msdk.out.Campaign;

/* compiled from: DailyPlayCapDao.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class j extends a<Campaign> {
    private static final String a = "com.mbridge.msdk.foundation.db.j";
    private static j b;

    private j(h hVar) {
        super(hVar);
    }

    public static j a(h hVar) {
        if (b == null) {
            synchronized (j.class) {
                if (b == null) {
                    b = new j(hVar);
                }
            }
        }
        return b;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:31:0x0074
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1178)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    public final boolean a(java.lang.String r12, int r13) {
        /*
            r11 = this;
            r0 = 0
            r1 = 0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            r2.<init>()     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            java.lang.String r3 = "SELECT * FROM dailyplaycap where unit_id ='"
            r2.append(r3)     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            r2.append(r12)     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            java.lang.String r3 = "'"
            r2.append(r3)     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            monitor-enter(r11)     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
            android.database.sqlite.SQLiteDatabase r3 = r11.getReadableDatabase()     // Catch: java.lang.Throwable -> L74
            android.database.Cursor r2 = r3.rawQuery(r2, r0)     // Catch: java.lang.Throwable -> L74
            monitor-exit(r11)     // Catch: java.lang.Throwable -> L71
            if (r2 == 0) goto L6b
            int r0 = r2.getCount()     // Catch: java.lang.Throwable -> L65 java.lang.Throwable -> L68
            if (r0 <= 0) goto L6b
            boolean r0 = r2.moveToFirst()     // Catch: java.lang.Throwable -> L65 java.lang.Throwable -> L68
            if (r0 == 0) goto L6b
            java.lang.String r0 = "first_insert_timestamp"
            int r0 = r2.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L65 java.lang.Throwable -> L68
            long r3 = r2.getLong(r0)     // Catch: java.lang.Throwable -> L65 java.lang.Throwable -> L68
            java.lang.String r0 = "play_time"
            int r0 = r2.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L65 java.lang.Throwable -> L68
            int r0 = r2.getInt(r0)     // Catch: java.lang.Throwable -> L65 java.lang.Throwable -> L68
            long r5 = (long) r0     // Catch: java.lang.Throwable -> L65 java.lang.Throwable -> L68
            r7 = 0
            int r0 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r0 == 0) goto L6b
            long r7 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L65 java.lang.Throwable -> L68
            r9 = 86400000(0x5265c00, double:4.2687272E-316)
            long r7 = r7 - r9
            int r0 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r0 <= 0) goto L5b
            r11.b(r12)     // Catch: java.lang.Throwable -> L65 java.lang.Throwable -> L68
            goto L6b
        L5b:
            if (r13 <= 0) goto L6b
            long r12 = (long) r13
            int r12 = (r5 > r12 ? 1 : (r5 == r12 ? 0 : -1))
            if (r12 < 0) goto L6b
            r12 = 1
            r1 = 1
            goto L6b
        L65:
            r12 = move-exception
            r0 = r2
            goto L96
        L68:
            r12 = move-exception
            r0 = r2
            goto L7a
        L6b:
            if (r2 == 0) goto L95
            r2.close()     // Catch: java.lang.Throwable -> L95
            goto L95
        L71:
            r12 = move-exception
            r0 = r2
            goto L75
        L74:
            r12 = move-exception
        L75:
            monitor-exit(r11)     // Catch: java.lang.Throwable -> L74
            throw r12     // Catch: java.lang.Throwable -> L77 java.lang.Throwable -> L79
        L77:
            r12 = move-exception
            goto L96
        L79:
            r12 = move-exception
        L7a:
            java.lang.String r13 = com.mbridge.msdk.foundation.db.j.a     // Catch: java.lang.Throwable -> L77
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L77
            r2.<init>()     // Catch: java.lang.Throwable -> L77
            java.lang.String r3 = "isOverCap is error"
            r2.append(r3)     // Catch: java.lang.Throwable -> L77
            r2.append(r12)     // Catch: java.lang.Throwable -> L77
            java.lang.String r12 = r2.toString()     // Catch: java.lang.Throwable -> L77
            com.mbridge.msdk.foundation.tools.s.b(r13, r12)     // Catch: java.lang.Throwable -> L77
            if (r0 == 0) goto L95
            r0.close()     // Catch: java.lang.Throwable -> L95
        L95:
            return r1
        L96:
            if (r0 == 0) goto L9b
            r0.close()     // Catch: java.lang.Throwable -> L9b
        L9b:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.db.j.a(java.lang.String, int):boolean");
    }

    private synchronized void b(String str) {
        try {
        } catch (Throwable unused) {
            com.mbridge.msdk.foundation.tools.s.b(a, "resetTimeAndTimestamp error");
        }
        if (getWritableDatabase() == null) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("first_insert_timestamp", (Integer) 0);
        contentValues.put("play_time", (Integer) 0);
        getWritableDatabase().update("dailyplaycap", contentValues, "unit_id = '" + str + "'", null);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00cf A[EXC_TOP_SPLITTER, PHI: r1
  0x00cf: PHI (r1v3 android.database.Cursor) = (r1v2 android.database.Cursor), (r1v4 android.database.Cursor) binds: [B:37:0x00d9, B:30:0x00cd] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized void a(java.lang.String r17) {
        /*
            Method dump skipped, instructions count: 233
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.db.j.a(java.lang.String):void");
    }

    private synchronized boolean c(String str) {
        try {
            Cursor cursorRawQuery = getReadableDatabase().rawQuery("SELECT play_time FROM dailyplaycap WHERE unit_id='" + str + "'", null);
            if (cursorRawQuery != null && cursorRawQuery.getCount() > 0) {
                cursorRawQuery.close();
                return true;
            }
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }
}
