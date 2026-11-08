package com.huawei.a.a.b.g;

import android.text.TextUtils;
import com.huawei.a.a.b.a.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private static b a;
    private String b;
    private String c;

    public static b a() {
        if (a == null) {
            d();
        }
        return a;
    }

    private boolean a(String str) {
        com.huawei.a.a.b.e.a.b("RootKeyManager", "refresh sp aes key");
        String strB = com.huawei.a.a.b.a.b.a().a(b.a.AES).b(f(), str);
        if (TextUtils.isEmpty(strB)) {
            com.huawei.a.a.b.e.a.b("RootKeyManager", "refreshLocalKey(): encrypted key is empty");
            return false;
        }
        com.huawei.a.a.b.i.a.a(com.huawei.a.a.a.b.j(), "Privacy_MY", "PrivacyData", strB);
        com.huawei.a.a.b.i.a.a(com.huawei.a.a.a.b.j(), "Privacy_MY", "flashKeyTime", System.currentTimeMillis());
        return true;
    }

    private static synchronized void d() {
        if (a == null) {
            a = new b();
        }
    }

    private String e() {
        String strB = com.huawei.a.a.b.i.a.b(com.huawei.a.a.a.b.j(), "Privacy_MY", "PrivacyData", "");
        com.huawei.a.a.b.a.b bVarA = com.huawei.a.a.b.a.b.a();
        if (TextUtils.isEmpty(strB)) {
            String strB2 = bVarA.b(b.a.AES);
            a(strB2);
            return strB2;
        }
        String strA = bVarA.a(b.a.AES).a(f(), strB);
        if (!TextUtils.isEmpty(strA)) {
            return strA;
        }
        String strB3 = bVarA.b(b.a.AES);
        a(strB3);
        return strB3;
    }

    private String f() {
        if (TextUtils.isEmpty(this.c)) {
            this.c = new a().a();
        }
        return this.c;
    }

    public void b() {
        String strB = com.huawei.a.a.b.a.b.a().b(b.a.AES);
        if (a(strB)) {
            this.b = strB;
        }
    }

    public String c() {
        if (TextUtils.isEmpty(this.b)) {
            this.b = e();
        }
        return this.b;
    }
}
