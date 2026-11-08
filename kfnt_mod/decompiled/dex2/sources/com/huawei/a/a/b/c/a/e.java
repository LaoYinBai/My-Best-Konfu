package com.huawei.a.a.b.c.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e {
    private c a;
    private c b;
    private c c;
    private c d;
    private String e;

    public e(String str) {
        this.e = str;
    }

    public c a() {
        return this.a;
    }

    public c a(String str) {
        if (str.equals("oper")) {
            return b();
        }
        if (str.equals("maint")) {
            return a();
        }
        if (str.equals("diffprivacy")) {
            return d();
        }
        if (str.equals("preins")) {
            return c();
        }
        com.huawei.a.a.b.e.a.c("hmsSdk", "HiAnalyticsInstData.getConfig(type): wrong type: " + str);
        return null;
    }

    public void a(c cVar) {
        this.a = cVar;
    }

    public c b() {
        return this.b;
    }

    public void b(c cVar) {
        this.b = cVar;
    }

    public c c() {
        return this.d;
    }

    public void c(c cVar) {
        this.d = cVar;
    }

    public c d() {
        return this.c;
    }

    public void d(c cVar) {
        this.c = cVar;
    }
}
