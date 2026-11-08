package b.b.a.h;

import com.bytedance.b.a.a.c.g;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    public static final long[][] f1088a = {new long[]{g.g, 0, 12}, new long[]{g.g, 5, 1}, new long[]{g.h, 5, 1}, new long[]{480000, 4, 1}, new long[]{960000, 2, 1}};

    /* renamed from: b, reason: collision with root package name */
    public String f1089b;

    /* renamed from: c, reason: collision with root package name */
    public b.b.a.f.g f1090c;
    public int d;
    public int e;
    public int f;
    public long g;
    public long h;

    public a(String str, b.b.a.f.g gVar) {
        this.f1090c = gVar;
        this.f1089b = str;
        this.d = 0;
        if (System.currentTimeMillis() - this.f1090c.e.getLong(b.a.a.a.a.a(new StringBuilder(), this.f1089b, "downgrade_time"), 0L) < 10800000) {
            this.d = this.f1090c.e.getInt(this.f1089b + "downgrade_index", 0);
            return;
        }
        this.f1090c.e.edit().remove(this.f1089b + "downgrade_time").remove(this.f1089b + "downgrade_index").apply();
    }

    public final boolean a() {
        return this.f1090c.f1070b.N();
    }
}
