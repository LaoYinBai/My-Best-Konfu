package com.bytedance.push;

import android.app.Application;
import android.text.TextUtils;
import com.bytedance.push.c.m;
import com.bytedance.push.c.o;
import com.bytedance.push.c.p;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    public final Application f4770a;

    /* renamed from: b, reason: collision with root package name */
    public final int f4771b;

    /* renamed from: c, reason: collision with root package name */
    public final int f4772c;
    public final int d;
    public final String e;
    public final boolean f;
    public final String g;
    public final String h;
    public final String i;
    public final b j;
    public final List<com.ss.android.message.b> k;
    public final com.bytedance.push.c.d l;
    public final com.bytedance.push.notification.h m;
    public final String n;
    public final p o;
    public final com.bytedance.push.c.a p;
    public final com.ss.android.pushmanager.b q;
    public final com.bytedance.push.c.c r;
    public final com.bytedance.push.c.l s;
    public final com.bytedance.push.f.a t;
    public final o u;
    public final String v;
    public final boolean w;
    public final com.bytedance.push.c.b x;
    public final boolean y;
    public final long z;

    private d(Application application, com.bytedance.push.b bVar, boolean z, String str, b bVar2, List<com.ss.android.message.b> list, com.bytedance.push.c.d dVar, com.bytedance.push.notification.h hVar, String str2, p pVar, com.bytedance.push.c.a aVar, com.ss.android.pushmanager.b bVar3, com.bytedance.push.c.c cVar, com.bytedance.push.c.l lVar, com.bytedance.push.f.a aVar2, o oVar, String str3, boolean z2, com.bytedance.push.c.b bVar4, a aVar3) {
        this.f4770a = application;
        this.f4771b = bVar.a();
        this.f4772c = bVar.b();
        this.d = bVar.c();
        this.e = bVar.d();
        this.g = bVar.e();
        this.i = bVar.f();
        this.f = z;
        this.h = str;
        this.j = bVar2;
        this.k = new CopyOnWriteArrayList(list);
        this.l = dVar;
        this.m = hVar;
        this.n = str2;
        this.o = pVar;
        this.p = aVar;
        this.q = bVar3;
        this.r = cVar;
        this.s = lVar;
        this.t = aVar2;
        this.u = oVar;
        this.v = str3;
        this.w = z2;
        this.x = bVar4;
        this.y = aVar3.v;
        this.z = aVar3.w;
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private final Application f4773a;

        /* renamed from: b, reason: collision with root package name */
        private boolean f4774b;

        /* renamed from: c, reason: collision with root package name */
        private String f4775c;
        private b d;
        private com.bytedance.push.c.d f;
        private m g;
        private String h;
        private p i;
        private com.bytedance.push.c.a j;
        private boolean k;
        private com.ss.android.pushmanager.b l;
        private com.bytedance.push.c.c m;
        private com.bytedance.push.b.a n;
        private com.bytedance.push.c.l o;
        private com.bytedance.push.f.a p;
        private o q;
        private com.bytedance.push.b r;
        private String s;
        private boolean t;
        private com.bytedance.push.c.b u;
        private boolean v;
        private List<com.ss.android.message.b> e = new ArrayList();
        private long w = TimeUnit.MINUTES.toMillis(2);

        public a(Application application, com.bytedance.push.b bVar) {
            this.f4773a = application;
            this.r = bVar;
        }

        public a a(boolean z) {
            this.f4774b = z;
            return this;
        }

        public a a(String str) {
            this.f4775c = str;
            return this;
        }

        public a a(com.ss.android.message.b bVar) {
            if (bVar != null && !this.e.contains(bVar)) {
                this.e.add(bVar);
            }
            return this;
        }

        public a a(com.bytedance.push.c.d dVar) {
            this.f = dVar;
            return this;
        }

        public a a(m mVar) {
            this.g = mVar;
            return this;
        }

        public a b(boolean z) {
            this.k = z;
            return this;
        }

        public a b(String str) {
            this.h = str;
            return this;
        }

        public a a(com.bytedance.push.c.c cVar) {
            this.m = cVar;
            return this;
        }

        public a a(com.bytedance.push.c.l lVar) {
            this.o = lVar;
            return this;
        }

        public d a() {
            c();
            if (TextUtils.isEmpty(this.f4775c)) {
                this.f4775c = com.ss.android.message.a.b.a(this.f4773a);
            }
            if (this.l == null) {
                e eVar = new e(this.k, this.r.e());
                this.l = eVar;
                if (this.f4774b) {
                    eVar.a(this.f4773a);
                }
            }
            if (this.n == null) {
                this.n = new com.bytedance.push.b.d();
            }
            if (this.q == null) {
                this.q = new o.a();
            }
            if (this.u == null) {
                this.u = new com.bytedance.push.j.a();
            }
            com.bytedance.push.notification.h hVar = new com.bytedance.push.notification.h(this.g, this.n);
            b();
            return new d(this.f4773a, this.r, this.f4774b, this.f4775c, this.d, this.e, this.f, hVar, this.h, this.i, this.j, this.l, this.m, this.o, this.p, this.q, this.s, this.t, this.u, this);
        }

        void b() {
            com.bytedance.push.utils.e.c("init", "debuggable = " + this.f4774b);
            if (this.f4774b) {
                com.bytedance.push.utils.e.a("init", this.r == null ? "" : this.r.toString());
                com.bytedance.push.utils.e.a("init", "process:\t" + this.f4775c);
            }
        }

        void c() {
            a(this.r);
            if (this.f == null) {
                c("please implement the event callback");
            }
            if (this.o == null) {
                c("click event listener is null, you'll not receive the event when user click notifications.Please implement it.");
            }
        }

        private void a(com.bytedance.push.b bVar) {
            if (bVar == null) {
                c("appinfo is null");
                return;
            }
            if (bVar.a() <= 0) {
                c(" aid {" + bVar.a() + "} is invalid");
            }
            if (TextUtils.isEmpty(bVar.f())) {
                c("appName {" + bVar.f() + "} is invalid");
            }
            if (TextUtils.isEmpty(bVar.d())) {
                c("versionName {" + bVar.d() + "} is invalid");
            }
            if (bVar.b() <= 0) {
                c("versionCode {" + bVar.b() + "} is invalid");
            }
            if (bVar.c() <= 0) {
                c("updateVersionCode {" + bVar.c() + "} is invalid");
            }
            if (TextUtils.isEmpty(bVar.e())) {
                c("channel {" + bVar.e() + "} is invalid");
            }
        }

        private void c(String str) {
            a(this.f4774b, str);
        }

        private void a(boolean z, String str) {
            if (z) {
                throw new IllegalArgumentException(str);
            }
            com.bytedance.push.utils.e.b("init", str);
        }

        public a a(com.ss.android.pushmanager.b bVar) {
            this.l = bVar;
            return this;
        }
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        public String f4776a;

        /* renamed from: b, reason: collision with root package name */
        public String f4777b;

        public b(String str, String str2) {
            this.f4776a = str2;
            this.f4777b = str;
        }

        public boolean a() {
            return (TextUtils.isEmpty(this.f4777b) || TextUtils.isEmpty(this.f4776a)) ? false : true;
        }
    }
}
