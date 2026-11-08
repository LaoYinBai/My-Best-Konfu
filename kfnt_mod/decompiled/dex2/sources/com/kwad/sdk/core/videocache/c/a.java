package com.kwad.sdk.core.videocache.c;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.kwad.sdk.core.videocache.j;
import com.kwad.sdk.core.videocache.n;
import com.liulishuo.filedownloader.model.FileDownloadModel;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class a extends SQLiteOpenHelper implements b {
    private static final String[] a = {FileDownloadModel.ID, "url", "length", "mime"};

    a(Context context) {
        super(context, "AndroidVideoCache.db", (SQLiteDatabase.CursorFactory) null, 1);
        j.a(context);
    }

    private ContentValues a(n nVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("url", nVar.a);
        contentValues.put("length", Long.valueOf(nVar.b));
        contentValues.put("mime", nVar.c);
        return contentValues;
    }

    private n a(Cursor cursor) {
        return new n(cursor.getString(cursor.getColumnIndexOrThrow("url")), cursor.getLong(cursor.getColumnIndexOrThrow("length")), cursor.getString(cursor.getColumnIndexOrThrow("mime")));
    }

    @Override // com.kwad.sdk.core.videocache.c.b
    public n a(String str) throws Throwable {
        j.a(str);
        Cursor cursor = null;
        nVarA = null;
        n nVarA = null;
        try {
            Cursor cursorQuery = getReadableDatabase().query("SourceInfo", a, "url=?", new String[]{str}, null, null, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToFirst()) {
                        nVarA = a(cursorQuery);
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
            return nVarA;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // com.kwad.sdk.core.videocache.c.b
    public void a(String str, n nVar) {
        j.a(str, nVar);
        boolean z = a(str) != null;
        ContentValues contentValuesA = a(nVar);
        if (z) {
            getWritableDatabase().update("SourceInfo", contentValuesA, "url=?", new String[]{str});
        } else {
            getWritableDatabase().insert("SourceInfo", null, contentValuesA);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) throws SQLException {
        j.a(sQLiteDatabase);
        sQLiteDatabase.execSQL("CREATE TABLE SourceInfo (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,url TEXT NOT NULL,mime TEXT,length INTEGER);");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        throw new IllegalStateException("Should not be called. There is no any migration");
    }
}
