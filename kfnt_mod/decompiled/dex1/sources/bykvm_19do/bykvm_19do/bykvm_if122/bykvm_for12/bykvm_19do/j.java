package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.bytedance.msdk.adapter.util.Logger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class j extends SQLiteOpenHelper {
    public j(Context context, String str, int i) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, i);
    }

    private void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
        Logger.d("DBHelper", "initDB........");
        sQLiteDatabase.execSQL(b.c());
        sQLiteDatabase.execSQL(b.b());
        sQLiteDatabase.execSQL(f.a());
        sQLiteDatabase.execSQL(d.a());
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            a(sQLiteDatabase);
        } catch (Throwable unused) {
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        try {
            Logger.d("DBHelper", "onUpgrade....数据库版本升级.....");
            if (i == 1) {
                Logger.d("DBHelper", "onUpgrade.....执行表创建.....");
            } else if (i == 2) {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS 'ad_video_info';");
            }
            if (i2 != 2) {
                return;
            }
            Logger.d("DBHelper", "onUpgrade.....执行表pacing & freqctl创建.....");
            sQLiteDatabase.execSQL(f.a());
            sQLiteDatabase.execSQL(d.a());
        } catch (Throwable unused) {
        }
    }
}
