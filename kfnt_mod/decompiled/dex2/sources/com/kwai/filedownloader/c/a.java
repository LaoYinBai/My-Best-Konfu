package com.kwai.filedownloader.c;

import android.content.ContentValues;
import com.kwai.filedownloader.e.f;
import com.liulishuo.filedownloader.model.ConnectionModel;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private int a;
    private int b;
    private long c;
    private long d;
    private long e;

    public static long a(List<a> list) {
        long jD = 0;
        for (a aVar : list) {
            jD += aVar.d() - aVar.c();
        }
        return jD;
    }

    public int a() {
        return this.a;
    }

    public void a(int i) {
        this.a = i;
    }

    public void a(long j) {
        this.c = j;
    }

    public int b() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }

    public void b(long j) {
        this.d = j;
    }

    public long c() {
        return this.c;
    }

    public void c(long j) {
        this.e = j;
    }

    public long d() {
        return this.d;
    }

    public long e() {
        return this.e;
    }

    public ContentValues f() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Integer.valueOf(this.a));
        contentValues.put(ConnectionModel.INDEX, Integer.valueOf(this.b));
        contentValues.put(ConnectionModel.START_OFFSET, Long.valueOf(this.c));
        contentValues.put(ConnectionModel.CURRENT_OFFSET, Long.valueOf(this.d));
        contentValues.put(ConnectionModel.END_OFFSET, Long.valueOf(this.e));
        return contentValues;
    }

    public String toString() {
        return f.a("id[%d] index[%d] range[%d, %d) current offset(%d)", Integer.valueOf(this.a), Integer.valueOf(this.b), Long.valueOf(this.c), Long.valueOf(this.e), Long.valueOf(this.d));
    }
}
