package com.ss.android.downloadlib.event;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ClickEventDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "click_event";
    static final String KEY_AD_ID = "ad_id";
    static final String KEY_ID = "_id";
    static final String KEY_TIME = "time";
    static final String TABLE_NAME = "click_event";
    private static final int VERSION = 1;
    static final String KEY_REQ_ID = "req_id";
    static final String[] EVENT_COLS = {"_id", "ad_id", KEY_REQ_ID, "time"};

    public ClickEventDBHelper(@Nullable Context context) {
        super(context, "click_event", (SQLiteDatabase.CursorFactory) null, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) throws SQLException {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS click_event(_id INTEGER PRIMARY KEY AUTOINCREMENT,ad_id INTEGER,req_id TEXT,time INTEGER)");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) throws SQLException {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS click_event");
        onCreate(sQLiteDatabase);
    }
}
