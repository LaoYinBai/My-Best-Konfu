package com.bytedance.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    public static final long[][] f3447a = {new long[]{com.bytedance.b.a.a.c.g.g, 0, 12}, new long[]{com.bytedance.b.a.a.c.g.g, 5, 1}, new long[]{com.bytedance.b.a.a.c.g.h, 5, 1}, new long[]{480000, 4, 1}, new long[]{960000, 2, 1}};

    /* renamed from: b, reason: collision with root package name */
    public String f3448b;

    /* renamed from: c, reason: collision with root package name */
    public az f3449c;
    public int d;
    public int e;
    public int f;
    public long g;
    public long h;

    public c(String str, az azVar) {
        this.f3449c = azVar;
        this.f3448b = str;
        this.d = 0;
        if (System.currentTimeMillis() - this.f3449c.e.getLong(a.a(new StringBuilder(), this.f3448b, "downgrade_time"), 0L) < 10800000) {
            this.d = this.f3449c.e.getInt(this.f3448b + "downgrade_index", 0);
            return;
        }
        this.f3449c.e.edit().remove(this.f3448b + "downgrade_time").remove(this.f3448b + "downgrade_index").apply();
    }

    public final boolean a() {
        return this.f3449c.f3386b.isCongestionControlEnable();
    }
}
