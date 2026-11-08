package com.bytedance.b.a.a;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import androidx.annotation.Nullable;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    static boolean f3593a = false;

    /* renamed from: b, reason: collision with root package name */
    static boolean f3594b = false;

    /* renamed from: c, reason: collision with root package name */
    static long f3595c = 5000;
    static final int d = 500;
    private static final String g = "LogStoreManager";
    private static final String j = "_id ASC ";
    private static final String k = "aid= ?";
    private Context l;
    private SQLiteDatabase m;
    static final String[] e = {"_id", "data"};
    private static c n = null;
    private static String o = "SELECT count(*) FROM monitor_log WHERE aid = ?";
    private static String p = "SELECT count(*) FROM monitor_log";
    private Map<String, Integer> h = new HashMap(2);
    private int i = 0;
    String f = "INSERT INTO monitor_log(aid,type,type2,time,data) VALUES ( ?, ?, ?, ?, ?)";

    public static c a(Context context) {
        if (n == null) {
            synchronized (a.class) {
                if (n == null) {
                    n = new c(context);
                }
            }
        }
        return n;
    }

    private c(Context context) {
        this.l = context;
        this.m = a.a(context).getWritableDatabase();
    }

    public synchronized void a(String str, List<com.bytedance.b.a.a.b.a> list) {
        SQLiteDatabase sQLiteDatabase;
        if (this.m != null && !com.bytedance.b.a.a.e.d.a(list)) {
            c();
            this.m.beginTransaction();
            try {
                try {
                    SQLiteStatement sQLiteStatementCompileStatement = this.m.compileStatement(this.f);
                    for (com.bytedance.b.a.a.b.a aVar : list) {
                        sQLiteStatementCompileStatement.bindString(1, String.valueOf(aVar.f3591b));
                        sQLiteStatementCompileStatement.bindString(2, aVar.f3592c == null ? "" : aVar.f3592c);
                        sQLiteStatementCompileStatement.bindString(3, aVar.d == null ? "" : aVar.d);
                        sQLiteStatementCompileStatement.bindLong(4, aVar.f);
                        sQLiteStatementCompileStatement.bindString(5, aVar.e == null ? "" : aVar.e);
                        sQLiteStatementCompileStatement.executeInsert();
                    }
                    this.m.setTransactionSuccessful();
                    a(str, list.size());
                    sQLiteDatabase = this.m;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    sQLiteDatabase = this.m;
                }
                sQLiteDatabase.endTransaction();
            } catch (Throwable th) {
                this.m.endTransaction();
                throw th;
            }
        }
    }

    private void a(String str, int i) {
        if (!this.h.containsKey(str) && i > 0) {
            this.h.put(str, Integer.valueOf(i));
        } else {
            this.h.put(str, Integer.valueOf(Math.max(0, i + this.h.get(str).intValue())));
        }
    }

    @Nullable
    public List<com.bytedance.b.a.a.b.a> a(int i, int i2) throws Throwable {
        Cursor cursor = null;
        try {
            Cursor cursorQuery = this.m.query(a.f3584c, e, k, new String[]{String.valueOf(i)}, null, null, j, i2 + "");
            try {
                if (cursorQuery.getCount() != 0) {
                    LinkedList linkedList = new LinkedList();
                    while (cursorQuery.moveToNext()) {
                        linkedList.add(new com.bytedance.b.a.a.b.a(cursorQuery.getLong(cursorQuery.getColumnIndex("_id")), cursorQuery.getString(cursorQuery.getColumnIndex("data"))));
                    }
                    a(cursorQuery);
                    return linkedList;
                }
                a(cursorQuery);
                return null;
            } catch (Throwable unused) {
                cursor = cursorQuery;
                a(cursor);
                return Collections.emptyList();
            }
        } catch (Throwable th) {
            th = th;
        }
    }

    public synchronized int a(String str, long j2) {
        if (this.m != null && j2 >= 0) {
            int iDelete = this.m.delete(a.f3584c, "aid = ? AND _id<= ? ", new String[]{str, String.valueOf(j2)});
            a(str, iDelete * (-1));
            return iDelete;
        }
        return -1;
    }

    private synchronized int b(String str) {
        Cursor cursorRawQuery;
        if (this.m == null || !this.m.isOpen()) {
            return -1;
        }
        Cursor cursor = null;
        try {
            cursorRawQuery = this.m.rawQuery(o, new String[]{str});
        } catch (Exception unused) {
        } catch (Throwable th) {
            th = th;
        }
        try {
            i = cursorRawQuery.moveToNext() ? cursorRawQuery.getInt(0) : -1;
            a(cursorRawQuery);
        } catch (Exception unused2) {
            cursor = cursorRawQuery;
            a(cursor);
            return i;
        } catch (Throwable th2) {
            cursor = cursorRawQuery;
            th = th2;
            a(cursor);
            throw th;
        }
        return i;
    }

    private synchronized int b() {
        if (this.m == null || !this.m.isOpen()) {
            return -1;
        }
        Cursor cursor = null;
        try {
            Cursor cursorRawQuery = this.m.rawQuery(p, null);
            try {
                i = cursorRawQuery.moveToNext() ? cursorRawQuery.getInt(0) : -1;
                a(cursorRawQuery);
            } catch (Exception unused) {
                cursor = cursorRawQuery;
                a(cursor);
                return i;
            } catch (Throwable th) {
                th = th;
                cursor = cursorRawQuery;
                a(cursor);
                throw th;
            }
        } catch (Exception unused2) {
        } catch (Throwable th2) {
            th = th2;
        }
        return i;
    }

    public synchronized int a(String str) {
        int iIntValue;
        if (this.i > 10 || !this.h.containsKey(str)) {
            int iB = b(str);
            this.h.put(str, Integer.valueOf(iB));
            this.i = 0;
            iIntValue = iB;
        } else {
            iIntValue = this.h.get(str).intValue();
            this.i++;
        }
        return iIntValue;
    }

    private synchronized void c() {
        if (!f3594b) {
            f3594b = true;
            if (b() >= f3595c) {
                a(500L);
            }
        }
        if (!f3593a) {
            f3593a = true;
            d();
        }
    }

    public synchronized void a(long j2) {
        if (this.m == null || j2 <= 0) {
            return;
        }
        try {
            this.m.execSQL(" DELETE FROM monitor_log WHERE _id IN (SELECT _id FROM monitor_log ORDER BY _id ASC LIMIT " + j2 + ")");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    protected static void a(Cursor cursor) {
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

    synchronized boolean a() {
        return true;
    }

    private void d() {
        c("psdkmon");
    }

    private void c(String str) {
        try {
            File databasePath = this.l.getDatabasePath(str + ".db");
            if (databasePath.exists()) {
                databasePath.delete();
            }
        } catch (Exception unused) {
        }
    }
}
