package com.apm.insight.e;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.apm.insight.h;
import com.apm.insight.l.q;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static volatile a f2823a;

    /* renamed from: b, reason: collision with root package name */
    private com.apm.insight.e.b.b f2824b;

    /* renamed from: c, reason: collision with root package name */
    private SQLiteDatabase f2825c;

    private a() {
    }

    public static a a() {
        if (f2823a == null) {
            synchronized (a.class) {
                if (f2823a == null) {
                    f2823a = new a();
                }
            }
        }
        return f2823a;
    }

    private void b() {
        if (this.f2824b == null) {
            a(h.g());
        }
    }

    public synchronized void a(Context context) {
        try {
            this.f2825c = new b(context).getWritableDatabase();
        } catch (Throwable th) {
            q.b(th);
        }
        this.f2824b = new com.apm.insight.e.b.b();
    }

    public synchronized void a(com.apm.insight.e.a.a aVar) {
        b();
        if (this.f2824b != null) {
            this.f2824b.a(this.f2825c, aVar);
        }
    }

    public synchronized boolean a(String str) {
        b();
        if (this.f2824b == null) {
            return false;
        }
        return this.f2824b.a(this.f2825c, str);
    }
}
