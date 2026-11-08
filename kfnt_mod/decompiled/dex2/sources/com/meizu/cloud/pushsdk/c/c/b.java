package com.meizu.cloud.pushsdk.c.c;

import com.meizu.cloud.pushsdk.c.a.c;
import com.meizu.cloud.pushsdk.c.c.a;
import com.meizu.cloud.pushsdk.notification.model.AppIconSetting;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends com.meizu.cloud.pushsdk.c.c.a {
    private final String a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final String g;
    private final String h;
    private final int i;

    public static abstract class a<T extends a<T>> extends a.AbstractC0155a<T> {
        private String a;
        private String b;
        private String c;
        private String d;
        private String e;
        private String f;
        private String g;
        private String h;
        private int i = 0;

        public T a(int i) {
            this.i = i;
            return a();
        }

        public T a(String str) {
            this.a = str;
            return a();
        }

        public T b(String str) {
            this.b = str;
            return a();
        }

        public b b() {
            return new b(this);
        }

        public T c(String str) {
            this.c = str;
            return a();
        }

        public T d(String str) {
            this.d = str;
            return a();
        }

        public T e(String str) {
            this.e = str;
            return a();
        }

        public T f(String str) {
            this.f = str;
            return a();
        }

        public T g(String str) {
            this.g = str;
            return a();
        }

        public T h(String str) {
            this.h = str;
            return a();
        }
    }

    /* renamed from: com.meizu.cloud.pushsdk.c.c.b$b, reason: collision with other inner class name */
    private static class C0156b extends a<C0156b> {
        private C0156b() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.meizu.cloud.pushsdk.c.c.a.AbstractC0155a
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public C0156b a() {
            return this;
        }
    }

    protected b(a<?> aVar) {
        super(aVar);
        this.b = ((a) aVar).b;
        this.c = ((a) aVar).c;
        this.a = ((a) aVar).a;
        this.d = ((a) aVar).d;
        this.e = ((a) aVar).e;
        this.f = ((a) aVar).f;
        this.g = ((a) aVar).g;
        this.h = ((a) aVar).h;
        this.i = ((a) aVar).i;
    }

    public static a<?> d() {
        return new C0156b();
    }

    public c e() {
        c cVar = new c();
        cVar.a("en", this.a);
        cVar.a("ti", this.b);
        cVar.a(AppIconSetting.DEFAULT_LARGE_ICON, this.c);
        cVar.a("pv", this.d);
        cVar.a("pn", this.e);
        cVar.a("si", this.f);
        cVar.a("ms", this.g);
        cVar.a("ect", this.h);
        cVar.a("br", Integer.valueOf(this.i));
        return a(cVar);
    }
}
