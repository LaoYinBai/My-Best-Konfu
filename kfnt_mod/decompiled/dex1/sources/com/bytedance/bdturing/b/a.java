package com.bytedance.bdturing.b;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class a extends SQLiteOpenHelper {
    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    a(Context context) {
        super(context, b.e, (SQLiteDatabase.CursorFactory) null, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) throws SQLException {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS h5_storage ( primary_key TEXT, time_stamp LONG PRIMARY KEY, content TEXT ) ");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) throws SQLException {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS h5_storage ( primary_key TEXT, time_stamp LONG PRIMARY KEY, content TEXT ) ");
    }
}
