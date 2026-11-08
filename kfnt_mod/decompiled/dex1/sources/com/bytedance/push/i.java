package com.bytedance.push;

import android.content.Context;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class i implements com.bytedance.push.c.j {

    /* renamed from: a, reason: collision with root package name */
    private static i f4842a = new i();

    /* renamed from: b, reason: collision with root package name */
    private com.bytedance.push.d.b f4843b = new com.bytedance.push.d.b();

    /* renamed from: c, reason: collision with root package name */
    private d f4844c;
    private com.bytedance.push.g.a d;
    private volatile com.bytedance.push.c.f e;
    private volatile j f;
    private volatile k g;
    private volatile com.bytedance.push.c.g h;
    private volatile com.bytedance.push.c.e i;

    public static com.bytedance.push.c.j j() {
        return f4842a;
    }

    public static com.bytedance.push.c.d k() {
        return j().h();
    }

    public static com.bytedance.push.d.a l() {
        return j().f();
    }

    public static com.bytedance.push.c.k m() {
        return j().e();
    }

    public static com.bytedance.push.c.g n() {
        return j().g();
    }

    public static com.bytedance.push.c.e o() {
        return j().i();
    }

    @Override // com.bytedance.push.c.j
    public Map<String, String> a() {
        return this.d.a();
    }

    @Override // com.bytedance.push.c.j
    public com.bytedance.push.c.f b() {
        if (this.e == null) {
            synchronized (this) {
                if (this.e == null) {
                    this.e = new com.bytedance.push.notification.f(this);
                }
            }
        }
        return this.e;
    }

    @Override // com.bytedance.push.c.j
    public com.bytedance.push.c.i c() {
        if (this.f == null) {
            synchronized (this) {
                if (this.f == null) {
                    this.f = new j();
                }
            }
        }
        return this.f;
    }

    @Override // com.bytedance.push.c.j
    public d d() {
        return this.f4844c;
    }

    @Override // com.bytedance.push.c.j
    public com.bytedance.push.c.k e() {
        if (this.g == null) {
            synchronized (this) {
                if (this.g == null) {
                    this.g = new k(c(), g(), d());
                }
            }
        }
        return this.g;
    }

    @Override // com.bytedance.push.c.j
    public com.bytedance.push.d.a f() {
        return this.f4843b;
    }

    @Override // com.bytedance.push.c.j
    public com.bytedance.push.c.g g() {
        if (this.h == null) {
            synchronized (this) {
                if (this.h == null) {
                    this.h = new com.bytedance.push.notification.g(d());
                }
            }
        }
        return this.h;
    }

    @Override // com.bytedance.push.c.j
    public com.bytedance.push.c.d h() {
        return d().l;
    }

    @Override // com.bytedance.push.c.j
    public com.bytedance.push.c.e i() {
        if (this.i == null) {
            synchronized (this) {
                if (this.i == null) {
                    if (com.ss.android.message.a.b.c(d().f4770a)) {
                        this.i = new com.bytedance.push.f.c(d());
                    } else {
                        this.i = new com.bytedance.push.f.d();
                    }
                }
            }
        }
        return this.i;
    }

    @Override // com.bytedance.push.c.j
    public String a(Context context, String str) {
        return d().o != null ? d().o.a(context, str) : str;
    }

    @Override // com.bytedance.push.c.j
    public void a(d dVar, com.bytedance.push.g.a aVar) {
        this.f4844c = dVar;
        this.d = aVar;
    }
}
