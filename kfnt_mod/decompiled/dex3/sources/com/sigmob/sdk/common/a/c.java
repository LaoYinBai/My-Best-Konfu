package com.sigmob.sdk.common.a;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.sigmob.logger.SigmobLog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c extends SQLiteOpenHelper {
    private static String a = "CREATE TABLE ads ( endcard_md5 text  ,video_md5 text  ,ad blob  ,ad_source_channel text  ,ad_type integer  ,create_time integer  ,crid text  ,load_id text  ,adTrackersMap blob  ,adslot_id text  ,camp_id text  ,request_id text , primary key ( crid ,adslot_id) ); \n";
    private static String b = "CREATE TRIGGER trigger_insert after insert on ads begin insert or replace into file_reference (crid,adslot_id,video_md5,endcard_md5) values(new.crid,new.adslot_id,new.video_md5,new.endcard_md5) ; end; \n";
    private static String c = "CREATE TRIGGER trigger_delete after delete on ads begin delete from file_reference where crid = old.crid  and adslot_id == old.adslot_id; end;\n";
    private static String d = "CREATE TABLE file_reference ( endcard_md5 blob  ,video_md5 blob  ,crid text  ,adslot_id text  , primary key ( crid ,adslot_id ) );\n";
    private static String e = "CREATE TABLE point ( point_id integer  primary key  ,item text   );\n";
    private static String f = "CREATE TABLE tracks ( id integer primary key AUTOINCREMENT ,retryNum integer   ,source text   ,event text   ,request_id text   ,url text   ,timestamp integer   );\n";
    private static c g;
    private static SQLiteDatabase h;

    public interface a {
        void onFailed(Throwable th);

        void onSuccess();
    }

    private c(Context context) {
        super(context, "sigmob.db", (SQLiteDatabase.CursorFactory) null, 8);
        getWritableDatabase().enableWriteAheadLogging();
    }

    public static c a() {
        return g;
    }

    public static void a(Context context) {
        if (g == null) {
            synchronized (c.class) {
                if (g == null) {
                    g = new c(context);
                }
            }
        }
    }

    private void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ads");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS file_reference");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS point");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS tracks");
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS trigger_insert");
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS trigger_delete");
        com.sigmob.sdk.common.utils.d.f();
        onCreate(sQLiteDatabase);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0025 A[Catch: all -> 0x0075, Throwable -> 0x0077, TryCatch #3 {Throwable -> 0x0077, blocks: (B:3:0x0004, B:4:0x000b, B:6:0x0013, B:8:0x0025, B:21:0x005a, B:9:0x0029, B:11:0x002d, B:12:0x0033, B:14:0x0037, B:15:0x0041, B:17:0x0045, B:18:0x004f, B:20:0x0053, B:22:0x005d), top: B:53:0x0004, outer: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(android.database.sqlite.SQLiteDatabase r6, com.sigmob.sdk.common.a.b.C0018b r7, com.sigmob.sdk.common.a.c.a r8) {
        /*
            r5 = this;
            r6.beginTransaction()
            r0 = 1
            java.lang.String r1 = r7.b     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L77
            android.database.sqlite.SQLiteStatement r1 = r6.compileStatement(r1)     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L77
            r2 = 1
        Lb:
            java.util.List<java.lang.Object> r3 = r7.d     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L77
            int r3 = r3.size()     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L77
            if (r2 > r3) goto L5d
            java.util.List<java.lang.Object> r3 = r7.d     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L77
            int r4 = r2 + (-1)
            java.lang.Object r3 = r3.get(r4)     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L77
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L77
            java.util.Map<java.lang.String, java.lang.Object> r4 = r7.c     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L77
            java.lang.Object r3 = r4.get(r3)     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L77
            if (r3 != 0) goto L29
        L25:
            r1.bindNull(r2)     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L77
            goto L5a
        L29:
            boolean r4 = r3 instanceof java.lang.String     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L77
            if (r4 == 0) goto L33
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L77
            r1.bindString(r2, r3)     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L77
            goto L5a
        L33:
            boolean r4 = r3 instanceof java.lang.Double     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L77
            if (r4 == 0) goto L41
            java.lang.Double r3 = (java.lang.Double) r3     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L77
            double r3 = r3.doubleValue()     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L77
            r1.bindDouble(r2, r3)     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L77
            goto L5a
        L41:
            boolean r4 = r3 instanceof java.lang.Number     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L77
            if (r4 == 0) goto L4f
            java.lang.Number r3 = (java.lang.Number) r3     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L77
            long r3 = r3.longValue()     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L77
            r1.bindLong(r2, r3)     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L77
            goto L5a
        L4f:
            boolean r4 = r3 instanceof byte[]     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L77
            if (r4 == 0) goto L25
            byte[] r3 = (byte[]) r3     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L77
            byte[] r3 = (byte[]) r3     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L77
            r1.bindBlob(r2, r3)     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L77
        L5a:
            int r2 = r2 + 1
            goto Lb
        L5d:
            r1.execute()     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L77
            r6.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L75 java.lang.Throwable -> L77
            r6.endTransaction()     // Catch: java.lang.Throwable -> L67
            goto L96
        L67:
            r6 = move-exception
            java.lang.String r7 = r6.getMessage()
            com.sigmob.logger.SigmobLog.e(r7)
            if (r8 == 0) goto L96
            r8.onFailed(r6)
            goto L96
        L75:
            r7 = move-exception
            goto L9e
        L77:
            r7 = move-exception
            java.lang.String r0 = r7.getMessage()     // Catch: java.lang.Throwable -> L75
            com.sigmob.logger.SigmobLog.e(r0)     // Catch: java.lang.Throwable -> L75
            if (r8 == 0) goto L84
            r8.onFailed(r7)     // Catch: java.lang.Throwable -> L75
        L84:
            r6.endTransaction()     // Catch: java.lang.Throwable -> L88
            goto L95
        L88:
            r6 = move-exception
            java.lang.String r7 = r6.getMessage()
            com.sigmob.logger.SigmobLog.e(r7)
            if (r8 == 0) goto L95
            r8.onFailed(r6)
        L95:
            r0 = 0
        L96:
            if (r0 == 0) goto L9d
            if (r8 == 0) goto L9d
            r8.onSuccess()
        L9d:
            return
        L9e:
            r6.endTransaction()     // Catch: java.lang.Throwable -> La2
            goto Laf
        La2:
            r6 = move-exception
            java.lang.String r0 = r6.getMessage()
            com.sigmob.logger.SigmobLog.e(r0)
            if (r8 == 0) goto Laf
            r8.onFailed(r6)
        Laf:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sigmob.sdk.common.a.c.a(android.database.sqlite.SQLiteDatabase, com.sigmob.sdk.common.a.b$b, com.sigmob.sdk.common.a.c$a):void");
    }

    public void a(SQLiteDatabase sQLiteDatabase, String str, a aVar) {
        sQLiteDatabase.beginTransaction();
        boolean z = false;
        try {
            try {
                sQLiteDatabase.execSQL(str, new Object[0]);
                sQLiteDatabase.setTransactionSuccessful();
                z = true;
            } catch (Throwable th) {
                SigmobLog.e(th.getMessage());
                if (aVar != null) {
                    aVar.onFailed(th);
                }
            }
            if (!z || aVar == null) {
                return;
            }
            aVar.onSuccess();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    public void b() throws SQLException {
        a(getWritableDatabase());
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) throws SQLException {
        sQLiteDatabase.execSQL(a);
        sQLiteDatabase.execSQL(b);
        sQLiteDatabase.execSQL(c);
        sQLiteDatabase.execSQL(d);
        sQLiteDatabase.execSQL(e);
        sQLiteDatabase.execSQL(f);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) throws SQLException {
        Log.w(c.class.getName(), "Downgrading database from version " + i + " to " + i2 + ", which will destroy all old data");
        a(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) throws SQLException {
        Log.w(c.class.getName(), "Upgrading database from version " + i + " to " + i2 + ", which will destroy all old data");
        a(sQLiteDatabase);
    }
}
