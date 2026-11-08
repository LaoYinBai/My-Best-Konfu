package com.huawei.a.a.b.d;

import android.text.TextUtils;
import com.huawei.a.a.b.a.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static a a = new a();
    private final Object b = new Object();
    private String c;

    public static a a() {
        return a;
    }

    private String c() {
        return com.huawei.a.a.b.a.b.a().a(b.a.AES).a(com.huawei.a.a.b.g.b.a().c(), com.huawei.a.a.b.i.a.b(com.huawei.a.a.a.b.j(), "global_v2", "common_prop", ""));
    }

    private void d() {
        com.huawei.a.a.b.i.a.a(com.huawei.a.a.a.b.j(), "global_v2", "common_prop", com.huawei.a.a.b.a.b.a().a(b.a.AES).b(com.huawei.a.a.b.g.b.a().c(), this.c));
    }

    public void a(String str) {
        synchronized (this.b) {
            this.c = str;
            d();
        }
    }

    public String b() {
        String str;
        synchronized (this.b) {
            if (TextUtils.isEmpty(this.c)) {
                this.c = c();
            }
            str = this.c;
        }
        return str;
    }
}
