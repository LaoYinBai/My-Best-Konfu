package com.bytedance.b.a.a;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.bytedance.b.a.c.i;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a extends SQLiteOpenHelper {

    /* renamed from: a, reason: collision with root package name */
    public static final String f3582a = "sdkmon_v2.db";

    /* renamed from: b, reason: collision with root package name */
    public static final int f3583b = 1;

    /* renamed from: c, reason: collision with root package name */
    public static final String f3584c = "monitor_log";
    public static final String d = "_id";
    public static final String e = "aid";
    public static final String f = "type";
    public static final String g = "type2";
    public static final String h = "time";
    public static final String i = "data";
    public static final String j = "data2";
    public static final String k = "data3";
    public static final String l = "CREATE TABLE monitor_log ( _id Integer PRIMARY KEY AUTOINCREMENT, aid Integer, type VARCHAR, type2 VARCHAR, time Integer, data TEXT, data2 TEXT, data3 TEXT  )";
    public static final String m = "queue";
    public static final String n = "value";
    public static final String o = "timestamp";
    public static final String p = "retry_count";
    public static final String q = "retry_time";
    public static final String r = "CREATE TABLE queue ( _id INTEGER PRIMARY KEY AUTOINCREMENT, value BLOB, type TEXT, timestamp INTEGER, retry_count INTEGER, retry_time INTEGER )";
    private static final String s = "DBHelper";
    private static a t;

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
    }

    public static a a(Context context) {
        if (t == null) {
            synchronized (a.class) {
                if (t == null) {
                    t = new a(context, i.a(context) + f3582a);
                }
            }
        }
        return t;
    }

    public a(Context context, String str) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) throws SQLException {
        try {
            sQLiteDatabase.execSQL(l);
            sQLiteDatabase.execSQL(r);
        } catch (Exception unused) {
        }
    }
}
