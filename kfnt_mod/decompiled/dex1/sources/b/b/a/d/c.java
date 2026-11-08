package b.b.a.d;

import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c extends a {
    public final String f;
    public final com.apm.applog.a g;
    public final b h;
    public int i;

    public c(b bVar, String str) {
        super(bVar);
        this.i = 0;
        this.f = str;
        this.h = bVar;
        this.g = com.apm.applog.a.a(bVar.g.f());
    }

    @Override // b.b.a.d.a
    public boolean b() {
        return true;
    }

    @Override // b.b.a.d.a
    public long c() {
        return 1000L;
    }

    @Override // b.b.a.d.a
    public long[] d() {
        return new long[]{1000};
    }

    @Override // b.b.a.d.a
    public boolean e() {
        this.i = b.b.a.i.a.a(this.h, (JSONObject) null, this.f) ? 0 : this.i + 1;
        if (this.i > 3) {
            this.g.a(false, this.f);
        }
        return true;
    }

    @Override // b.b.a.d.a
    public String f() {
        return "RangersEventVerify";
    }
}
