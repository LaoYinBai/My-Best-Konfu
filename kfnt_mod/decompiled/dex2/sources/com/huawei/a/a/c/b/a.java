package com.huawei.a.a.c.b;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import java.util.UUID;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static a b;
    private Context a;

    /* renamed from: com.huawei.a.a.c.b.a$a, reason: collision with other inner class name */
    private static class C0003a extends b {
        String a;
        String b;

        public C0003a(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        @Override // com.huawei.a.a.c.b.b
        public String a() {
            return com.huawei.a.a.a.a.b(this.a, this.b);
        }

        @Override // com.huawei.a.a.c.b.b
        public String a(String str) {
            return com.huawei.a.a.b.a.b.a().a(str);
        }

        @Override // com.huawei.a.a.c.b.b
        public String b() {
            return com.huawei.a.a.a.a.a(this.a, this.b);
        }

        @Override // com.huawei.a.a.c.b.b
        public String c() {
            return com.huawei.a.a.a.a.d(this.a, this.b);
        }

        @Override // com.huawei.a.a.c.b.b
        public int d() {
            return (com.huawei.a.a.a.a.h(this.a, this.b) ? 4 : 0) | 0 | (com.huawei.a.a.a.a.g(this.a, this.b) ? 2 : 0) | (com.huawei.a.a.a.a.j(this.a, this.b) ? 1 : 0);
        }
    }

    public static a a() {
        a aVar;
        synchronized (a.class) {
            if (b == null) {
                b = new a();
            }
            aVar = b;
        }
        return aVar;
    }

    public com.huawei.a.a.c.a.a a(String str, String str2) {
        return new C0003a(str, str2).a(this.a);
    }

    public String a(boolean z) {
        if (!z) {
            return "";
        }
        String strG = com.huawei.a.a.a.b.g();
        if (TextUtils.isEmpty(strG)) {
            strG = com.huawei.a.a.b.i.a.b(this.a, "global_v2", "uuid", "");
            if (TextUtils.isEmpty(strG)) {
                strG = UUID.randomUUID().toString().replace("-", "");
                com.huawei.a.a.b.i.a.a(this.a, "global_v2", "uuid", strG);
            }
            com.huawei.a.a.a.b.a(strG);
        }
        return strG;
    }

    public void a(Context context) {
        if (this.a == null) {
            this.a = context;
        }
    }

    public Pair<String, String> b(String str, String str2) {
        if (!com.huawei.a.a.a.a.f(str, str2)) {
            return new Pair<>("", "");
        }
        String strN = com.huawei.a.a.b.c.a.a.a().c().n();
        String strO = com.huawei.a.a.b.c.a.a.a().c().o();
        if (!TextUtils.isEmpty(strN) && !TextUtils.isEmpty(strO)) {
            return new Pair<>(strN, strO);
        }
        Pair<String, String> pairG = c.g(this.a);
        com.huawei.a.a.b.c.a.a.a().c().l((String) pairG.first);
        com.huawei.a.a.b.c.a.a.a().c().m((String) pairG.second);
        return pairG;
    }

    public String c(String str, String str2) {
        return d.b(str, str2);
    }

    public String d(String str, String str2) {
        return d.a(this.a, str, str2);
    }

    public String e(String str, String str2) {
        return d.b(this.a, str, str2);
    }

    public String f(String str, String str2) {
        return d.a(str, str2);
    }
}
