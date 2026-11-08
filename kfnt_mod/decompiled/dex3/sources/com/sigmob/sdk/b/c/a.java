package com.sigmob.sdk.b.c;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.sigmob.sdk.b.l;
import com.sigmob.sdk.b.q;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class a extends SQLiteOpenHelper implements c {
    private static final String[] a = {"_id", "url", "length", "mime"};

    a(Context context) {
        super(context, "AndroidVideoCache.db", (SQLiteDatabase.CursorFactory) null, 1);
        l.a(context);
    }

    private ContentValues a(q qVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("url", qVar.a);
        contentValues.put("length", Long.valueOf(qVar.b));
        contentValues.put("mime", qVar.c);
        return contentValues;
    }

    private q a(Cursor cursor) {
        return new q(cursor.getString(cursor.getColumnIndexOrThrow("url")), cursor.getLong(cursor.getColumnIndexOrThrow("length")), cursor.getString(cursor.getColumnIndexOrThrow("mime")));
    }

    @Override // com.sigmob.sdk.b.c.c
    public q a(String str) throws Throwable {
        l.a(str);
        Cursor cursor = null;
        qVarA = null;
        q qVarA = null;
        try {
            Cursor cursorQuery = getReadableDatabase().query("SourceInfo", a, "url=?", new String[]{str}, null, null, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToFirst()) {
                        qVarA = a(cursorQuery);
                    }
                } catch (Throwable th) {
                    cursor = cursorQuery;
                    th = th;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return qVarA;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // com.sigmob.sdk.b.c.c
    public void a(String str, q qVar) {
        l.a(str, qVar);
        boolean z = a(str) != null;
        ContentValues contentValuesA = a(qVar);
        if (z) {
            getWritableDatabase().update("SourceInfo", contentValuesA, "url=?", new String[]{str});
        } else {
            getWritableDatabase().insert("SourceInfo", null, contentValuesA);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) throws SQLException {
        l.a(sQLiteDatabase);
        sQLiteDatabase.execSQL("CREATE TABLE SourceInfo (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,url TEXT NOT NULL,mime TEXT,length INTEGER);");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        throw new IllegalStateException("Should not be called. There is no any migration");
    }
}
