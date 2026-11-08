package com.ss.union.game.sdk.core.base.event;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.ss.union.game.sdk.common.util.GlobalApplicationUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class e {
    private static final String a = "lg_light_game_log.db";
    private static final int b = 1;
    private static final String c = "lg_event";
    private static final String d = "_id";
    private static final String e = "session_id";
    private static final String f = "name";
    private static final String h = "ext_json";
    private static volatile e j;
    private SQLiteDatabase k;
    private static final String g = "e_timestamp";
    private static final String[] i = {"_id", "name", g, "ext_json"};

    private e(Context context) {
        this.k = new a(context).getWritableDatabase();
    }

    public static e a() {
        if (j == null) {
            synchronized (e.class) {
                if (j == null) {
                    j = new e(GlobalApplicationUtils.getContext());
                }
            }
        }
        return j;
    }

    private static void a(Cursor cursor) {
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

    synchronized boolean a(c cVar) {
        if (cVar.a() <= 0) {
            g.b("appEvent less than 0,skip", null);
            return false;
        }
        if (this.k != null && this.k.isOpen()) {
            this.k.beginTransaction();
            boolean z = true;
            try {
                try {
                    StringBuilder sb = new StringBuilder();
                    StringBuilder sb2 = new StringBuilder();
                    for (b bVar : cVar.b()) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("name", bVar.b());
                        contentValues.put(g, Long.valueOf(bVar.d()));
                        contentValues.put("ext_json", bVar.c());
                        long jInsert = this.k.insert(c, null, contentValues);
                        if (jInsert > 0) {
                            sb.append(bVar.b());
                            sb.append("\n");
                        } else {
                            sb2.append(bVar.b());
                            sb2.append("\n");
                        }
                        PageStater.a(jInsert > 0, bVar.b(), -1L);
                    }
                    g.a("插入事件数目总和: " + cVar.a());
                    this.k.setTransactionSuccessful();
                } catch (Throwable th) {
                    th.printStackTrace();
                    this.k.endTransaction();
                    z = false;
                }
                return z;
            } finally {
                this.k.endTransaction();
            }
        }
        g.b("db not establish and open", null);
        return false;
    }

    public synchronized void b() {
        if (this.k != null && this.k.isOpen()) {
            try {
                this.k.delete(c, "e_timestamp <= ?", new String[]{String.valueOf(System.currentTimeMillis() - 864000000)});
            } catch (Exception unused) {
                g.a("清除过期日志失败");
            }
            return;
        }
        g.b("db not establish and open", null);
    }

    synchronized boolean a(long j2, boolean z) {
        return false;
    }

    synchronized int a(long[] jArr, boolean z) {
        if (this.k != null && this.k.isOpen()) {
            if (jArr != null) {
                try {
                    if (jArr.length != 0) {
                        this.k.beginTransaction();
                        int iDelete = 0;
                        for (long j2 : jArr) {
                            iDelete += this.k.delete(c, "_id = ?", new String[]{String.valueOf(j2)});
                        }
                        this.k.setTransactionSuccessful();
                        return iDelete;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    g.b("onLogSent :", e2);
                    return -1;
                } finally {
                    this.k.endTransaction();
                }
            }
            return -1;
        }
        g.b("db not establish and open", null);
        return -1;
    }

    synchronized long c() {
        Cursor cursorRawQuery;
        Cursor cursor = null;
        if (this.k != null) {
            try {
                if (this.k.isOpen()) {
                    try {
                        cursorRawQuery = this.k.rawQuery("select count(*) from lg_event", null);
                    } catch (Exception e2) {
                        e = e2;
                    }
                    if (cursorRawQuery == null) {
                        a(cursorRawQuery);
                        return -1L;
                    }
                    try {
                        cursorRawQuery.moveToFirst();
                        long j2 = cursorRawQuery.getLong(0);
                        a(cursorRawQuery);
                        return j2;
                    } catch (Exception e3) {
                        cursor = cursorRawQuery;
                        e = e3;
                        e.printStackTrace();
                        g.b("queryEventCount fail:", e);
                        a(cursor);
                        return -1L;
                    } catch (Throwable th) {
                        th = th;
                        cursor = cursorRawQuery;
                        a(cursor);
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        g.b("db not establish and open", null);
        return -1L;
    }

    synchronized c a(int i2, long j2) {
        Cursor cursorQuery;
        Cursor cursor = null;
        int i3 = 0;
        Cursor cursor2 = null;
        if (this.k != null && this.k.isOpen()) {
            String[] strArr = {String.valueOf(j2)};
            c cVar = new c();
            try {
                try {
                    cursorQuery = this.k.query(c, i, "_id > ?", strArr, null, null, "_id ASC ", String.valueOf(i2));
                    while (cursorQuery.moveToNext()) {
                        try {
                            int columnIndex = cursorQuery.getColumnIndex("_id");
                            int columnIndex2 = cursorQuery.getColumnIndex("name");
                            int columnIndex3 = cursorQuery.getColumnIndex("ext_json");
                            cVar.a(new b(cursorQuery.getLong(columnIndex), cursorQuery.getLong(cursorQuery.getColumnIndex(g)), cursorQuery.getString(columnIndex2), cursorQuery.getString(columnIndex3)));
                            i3 = columnIndex3;
                        } catch (Exception e2) {
                            e = e2;
                            cursor2 = cursorQuery;
                            e.printStackTrace();
                            g.b("queryEvents fail:", e);
                            a(cursor2);
                            cursor = cursor2;
                            return cVar;
                        } catch (Throwable th) {
                            th = th;
                            a(cursorQuery);
                            throw th;
                        }
                    }
                    a(cursorQuery);
                    cursor = i3;
                } catch (Throwable th2) {
                    th = th2;
                    cursorQuery = cursor;
                }
            } catch (Exception e3) {
                e = e3;
            }
            return cVar;
        }
        g.b("db not establish and open", null);
        return new c();
    }

    synchronized void d() {
        try {
        } catch (Throwable th) {
            g.a("closeDatabase error: ", th);
        }
        if (this.k != null && this.k.isOpen()) {
            this.k.close();
            this.k = null;
        }
    }

    private static class a extends SQLiteOpenHelper {
        static final String a = "CREATE TABLE lg_event ( _id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, e_timestamp INTEGER, ext_json VARCHAR  )";
        static final String b = "DROP TABLE IF EXISTS lg_event";

        a(Context context) {
            super(context, e.a, (SQLiteDatabase.CursorFactory) null, 1);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) throws SQLException {
            try {
                sQLiteDatabase.execSQL(a);
            } catch (Exception e) {
                g.a("fail to create event table", e);
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            g.b("onUpgrade: oldVersion =" + i + "  newVersion =" + i2, null);
        }
    }
}
