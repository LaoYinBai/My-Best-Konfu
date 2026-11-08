package com.ss.union.game.sdk.core.base.a;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class b {
    protected SQLiteDatabase a;

    public abstract void a(SQLiteDatabase sQLiteDatabase, int i, int i2);

    public abstract void b(SQLiteDatabase sQLiteDatabase);

    public void a(SQLiteDatabase sQLiteDatabase) {
        this.a = sQLiteDatabase;
    }

    protected void a(Cursor cursor) {
        if (cursor != null) {
            try {
                if (cursor.isClosed()) {
                    return;
                }
                cursor.close();
            } catch (Exception unused) {
            }
        }
    }
}
