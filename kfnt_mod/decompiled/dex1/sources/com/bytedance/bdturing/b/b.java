package com.bytedance.bdturing.b;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.bytedance.bdturing.g;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: b, reason: collision with root package name */
    public static final String f3762b = "=?";

    /* renamed from: c, reason: collision with root package name */
    public static final String f3763c = ">?";
    public static final String d = "<?";
    public static final String e = "verifystorage.db";
    public static final int f = 1;
    public static final String g = "h5_storage";
    public static final String h = "primary_key";
    public static final String i = "content";
    public static final String j = "time_stamp";
    public static final String k = "event";
    public static final String l = "url";
    private static final String m = "b";
    private static volatile b n;
    private static long p;

    /* renamed from: a, reason: collision with root package name */
    SQLiteDatabase f3764a;
    private a o = null;

    private b() {
    }

    public static b a() {
        if (n == null) {
            synchronized (b.class) {
                if (n == null) {
                    n = new b();
                }
            }
        }
        return n;
    }

    public synchronized void a(Context context) {
        if (context != null) {
            if (this.o == null) {
                this.o = new a(context);
            }
        }
    }

    private void e() {
        if (this.f3764a != null) {
            return;
        }
        synchronized (b.class) {
            if (this.f3764a == null) {
                try {
                    this.f3764a = this.o.getWritableDatabase();
                } catch (SQLException unused) {
                    this.f3764a = null;
                }
            }
        }
    }

    private static long f() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (p >= jCurrentTimeMillis) {
            p++;
        } else {
            p = jCurrentTimeMillis;
        }
        return p;
    }

    public long a(String str) {
        e();
        g.a(m, "insertEvent : insertEvent");
        g.d(m, "insertEvent : " + str);
        if (this.f3764a == null || !this.f3764a.isOpen()) {
            return -1L;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(h, "event");
        contentValues.put(j, Long.valueOf(f()));
        contentValues.put("content", str);
        try {
            return this.f3764a.insert(g, null, contentValues);
        } catch (SQLException unused) {
            g.c(m, "insertEvent fail ");
            return -1L;
        }
    }

    private long a(String str, String str2) {
        long j2;
        e();
        g.a(m, "updateOrInsertSingleData : " + str);
        g.d(m, "updateOrInsertSingleData : " + str2);
        if (this.f3764a == null || !this.f3764a.isOpen()) {
            return -1L;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(h, str);
        contentValues.put(j, Long.valueOf(f()));
        contentValues.put("content", str2);
        try {
            long jUpdate = this.f3764a.update(g, contentValues, "primary_key=?", new String[]{"" + str});
            if (jUpdate != 0) {
                return jUpdate;
            }
            try {
                return this.f3764a.insert(g, null, contentValues);
            } catch (SQLException unused) {
                j2 = jUpdate;
                g.c(m, "updateOrInsertSingleData fail: " + str);
                return j2;
            }
        } catch (SQLException unused2) {
            j2 = 0;
        }
    }

    private String c(String str) {
        e();
        if (this.f3764a == null || !this.f3764a.isOpen()) {
            return null;
        }
        Cursor cursorQuery = this.f3764a.query(g, new String[]{"content"}, "primary_key=?", new String[]{"" + str}, null, null, null, String.valueOf(1));
        if (cursorQuery == null || !cursorQuery.moveToFirst()) {
            return null;
        }
        String string = cursorQuery.getString(0);
        cursorQuery.close();
        return string;
    }

    private int a(long j2) {
        e();
        if (this.f3764a == null || !this.f3764a.isOpen()) {
            return -1;
        }
        try {
            return this.f3764a.delete(g, "time_stamp=?", new String[]{"" + j2});
        } catch (SQLException unused) {
            g.c(m, "delete fail: ");
            return 0;
        }
    }

    public long b(String str) {
        return a("url", str);
    }

    public String b() {
        return c("url");
    }

    private void g() {
        try {
            if (this.f3764a == null || !this.f3764a.isOpen()) {
                return;
            }
            this.f3764a.close();
            this.f3764a = null;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void c() {
        synchronized (b.class) {
            if (n != null) {
                n.g();
                n = null;
            }
        }
    }

    public String d() {
        e();
        if (this.f3764a == null || !this.f3764a.isOpen()) {
            return null;
        }
        Cursor cursorQuery = this.f3764a.query(g, new String[]{j, "content"}, "primary_key=?", new String[]{"event"}, null, null, null, String.valueOf(1));
        if (cursorQuery == null || !cursorQuery.moveToFirst()) {
            return null;
        }
        String string = cursorQuery.getString(1);
        a(cursorQuery.getLong(0));
        cursorQuery.close();
        g.a(m, "querySingleEvent : querySingleEvent");
        g.d(m, "insertEvent : " + string);
        return string;
    }
}
