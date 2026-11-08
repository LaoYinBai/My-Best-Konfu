package com.mbridge.msdk.foundation.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.mbridge.msdk.MBridgeConstans;

/* compiled from: CommonAbsDBHelper.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class h {
    private a a;

    protected abstract void a(SQLiteDatabase sQLiteDatabase);

    protected abstract void a(SQLiteDatabase sQLiteDatabase, int i, int i2);

    protected abstract void b(SQLiteDatabase sQLiteDatabase, int i, int i2);

    protected abstract String c();

    protected abstract int d();

    public h(Context context) {
        this.a = new a(context, c(), d());
    }

    public final SQLiteDatabase a() {
        return this.a.getReadableDatabase();
    }

    public final synchronized SQLiteDatabase b() {
        SQLiteDatabase writableDatabase;
        writableDatabase = null;
        try {
            writableDatabase = this.a.getWritableDatabase();
        } catch (Exception unused) {
        }
        return writableDatabase;
    }

    /* compiled from: CommonAbsDBHelper.java */
    private class a extends SQLiteOpenHelper {
        public a(Context context, String str, int i) {
            super(context, str, (SQLiteDatabase.CursorFactory) null, i);
            if (MBridgeConstans.DEBUG) {
                com.mbridge.msdk.foundation.tools.s.a("DatabaseOpenHelper", "数据库： name :" + str + "  " + i);
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public final void onCreate(SQLiteDatabase sQLiteDatabase) {
            if (MBridgeConstans.DEBUG) {
                com.mbridge.msdk.foundation.tools.s.a("DatabaseOpenHelper", "数据库创建了");
            }
            h.this.a(sQLiteDatabase);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            if (MBridgeConstans.DEBUG) {
                com.mbridge.msdk.foundation.tools.s.a("DatabaseOpenHelper", "数据库升级了");
            }
            h.this.a(sQLiteDatabase, i, i2);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            h.this.b(sQLiteDatabase, i, i2);
        }
    }
}
