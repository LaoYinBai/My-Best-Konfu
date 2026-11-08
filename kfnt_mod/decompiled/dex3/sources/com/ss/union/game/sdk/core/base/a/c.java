package com.ss.union.game.sdk.core.base.a;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseIntArray;
import com.ss.union.game.sdk.core.init.bean.GameSDKOption;
import com.xiaomi.mipush.sdk.Constants;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c extends b {
    static final String b = "CREATE TABLE high_quality_share_video ( share_video_id VARCHAR PRIMARY KEY , share_video_url VARCHAR, show_count INTEGER, share_cover_image VARCHAR  )";
    private static final String c = "high_quality_share_video";
    private static final String d = "share_video_id";
    private static final String e = "share_video_url";
    private static final String f = "share_cover_image";
    private static final String g = "show_count";
    private static final String[] h = {d, e, f, g};

    public void a(int i, int i2) {
        if (this.a == null) {
            return;
        }
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(g, Integer.valueOf(i2));
            this.a.update(c, contentValues, "share_video_id =?", new String[]{String.valueOf(i)});
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void a(List<GameSDKOption.VideoShareConfig> list) {
        if (this.a == null || list == null) {
            return;
        }
        this.a.beginTransaction();
        try {
            try {
                SparseIntArray sparseIntArrayB = b();
                for (GameSDKOption.VideoShareConfig videoShareConfig : list) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(d, Integer.valueOf(videoShareConfig.f));
                    contentValues.put(e, videoShareConfig.g);
                    contentValues.put(f, videoShareConfig.h);
                    contentValues.put(g, Integer.valueOf(sparseIntArrayB.get(videoShareConfig.f)));
                    sparseIntArrayB.delete(videoShareConfig.f);
                    this.a.replace(c, null, contentValues);
                }
                int size = sparseIntArrayB.size();
                int[] iArr = new int[size];
                int i = 0;
                if (size > 0) {
                    for (int i2 = 0; i2 < size; i2++) {
                        iArr[i2] = sparseIntArrayB.keyAt(i2);
                    }
                }
                if (iArr.length > 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("DELETE FROM ");
                    sb.append(c);
                    sb.append(" WHERE ");
                    sb.append(d);
                    sb.append(" in ");
                    sb.append('(');
                    while (i < iArr.length) {
                        sb.append(i > 0 ? Constants.ACCEPT_TIME_SEPARATOR_SP : "");
                        sb.append(iArr[i]);
                        i++;
                    }
                    sb.append(')');
                    this.a.execSQL(sb.toString());
                }
                this.a.setTransactionSuccessful();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } finally {
            this.a.endTransaction();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x004d A[PHI: r2
  0x004d: PHI (r2v5 android.database.Cursor) = (r2v4 android.database.Cursor), (r2v7 android.database.Cursor) binds: [B:18:0x004b, B:11:0x003e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.util.SparseIntArray b() throws java.lang.Throwable {
        /*
            r11 = this;
            android.util.SparseIntArray r0 = new android.util.SparseIntArray
            r0.<init>()
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r11.a     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L44
            java.lang.String r3 = "high_quality_share_video"
            java.lang.String r4 = "share_video_id"
            java.lang.String r5 = "show_count"
            java.lang.String[] r4 = new java.lang.String[]{r4, r5}     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L44
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            java.lang.String r9 = "show_count ASC "
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L44
        L1c:
            if (r2 == 0) goto L3e
            boolean r1 = r2.moveToNext()     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L51
            if (r1 == 0) goto L3e
            java.lang.String r1 = "share_video_id"
            int r1 = r2.getColumnIndexOrThrow(r1)     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L51
            int r1 = r2.getInt(r1)     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L51
            java.lang.String r3 = "show_count"
            int r3 = r2.getColumnIndexOrThrow(r3)     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L51
            int r3 = r2.getInt(r3)     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L51
            r0.put(r1, r3)     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L51
            goto L1c
        L3c:
            r1 = move-exception
            goto L48
        L3e:
            if (r2 == 0) goto L50
            goto L4d
        L41:
            r0 = move-exception
            r2 = r1
            goto L52
        L44:
            r2 = move-exception
            r10 = r2
            r2 = r1
            r1 = r10
        L48:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L51
            if (r2 == 0) goto L50
        L4d:
            r2.close()
        L50:
            return r0
        L51:
            r0 = move-exception
        L52:
            if (r2 == 0) goto L57
            r2.close()
        L57:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.union.game.sdk.core.base.a.c.b():android.util.SparseIntArray");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v3 */
    public GameSDKOption.VideoShareConfig a() throws Throwable {
        GameSDKOption.VideoShareConfig videoShareConfig;
        Exception e2;
        Cursor cursorQuery;
        ?? r0 = this.a;
        GameSDKOption.VideoShareConfig videoShareConfig2 = null;
        try {
            if (r0 == 0) {
                return null;
            }
            try {
                cursorQuery = this.a.query(c, h, null, null, null, null, "show_count ASC ", "  1");
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.moveToFirst()) {
                            videoShareConfig = new GameSDKOption.VideoShareConfig();
                            try {
                                videoShareConfig.f = cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow(d));
                                videoShareConfig.i = cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow(g));
                                videoShareConfig.g = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow(e));
                                videoShareConfig.h = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow(f));
                                videoShareConfig2 = videoShareConfig;
                            } catch (Exception e3) {
                                e2 = e3;
                                e2.printStackTrace();
                                if (cursorQuery != null) {
                                    cursorQuery.close();
                                }
                                return videoShareConfig;
                            }
                        }
                    } catch (Exception e4) {
                        videoShareConfig = null;
                        e2 = e4;
                    }
                }
                if (cursorQuery == null) {
                    return videoShareConfig2;
                }
                cursorQuery.close();
                return videoShareConfig2;
            } catch (Exception e5) {
                videoShareConfig = null;
                e2 = e5;
                cursorQuery = null;
            } catch (Throwable th) {
                th = th;
                r0 = 0;
                if (r0 != 0) {
                    r0.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // com.ss.union.game.sdk.core.base.a.b
    public void b(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL(b);
        } catch (Throwable unused) {
        }
    }

    @Override // com.ss.union.game.sdk.core.base.a.b
    public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) throws SQLException {
        if (i != 1) {
            return;
        }
        sQLiteDatabase.execSQL(b);
    }
}
