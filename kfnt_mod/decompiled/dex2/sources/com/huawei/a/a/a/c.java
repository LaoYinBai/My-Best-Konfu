package com.huawei.a.a.a;

import com.huawei.a.a.b.c.a.e;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class c {
    public static String a(String str, String str2) {
        com.huawei.a.a.b.c.a.c cVarO = o(str, str2);
        return cVarO != null ? cVarO.l() : "";
    }

    public static void a(String str, String str2, long j) {
        com.huawei.a.a.b.c.a.c cVarO = o(str, str2);
        if (cVarO != null) {
            cVarO.a(j);
        }
    }

    public static String b(String str, String str2) {
        com.huawei.a.a.b.c.a.c cVarO = o(str, str2);
        return cVarO != null ? cVarO.g() : "";
    }

    public static String c(String str, String str2) {
        com.huawei.a.a.b.c.a.c cVarO = o(str, str2);
        return cVarO != null ? cVarO.h() : "";
    }

    public static String d(String str, String str2) {
        com.huawei.a.a.b.c.a.c cVarO = o(str, str2);
        return cVarO != null ? cVarO.i() : "";
    }

    public static String e(String str, String str2) {
        com.huawei.a.a.b.c.a.c cVarO = o(str, str2);
        return cVarO != null ? cVarO.j() : "";
    }

    public static String f(String str, String str2) {
        com.huawei.a.a.b.c.a.c cVarO = o(str, str2);
        return cVarO != null ? cVarO.k() : "";
    }

    public static int g(String str, String str2) {
        com.huawei.a.a.b.c.a.c cVarO = o(str, str2);
        if (cVarO != null) {
            return cVarO.c();
        }
        return 7;
    }

    public static int h(String str, String str2) {
        com.huawei.a.a.b.c.a.c cVarO = o(str, str2);
        if (cVarO != null) {
            return cVarO.d();
        }
        return 10;
    }

    public static boolean i(String str, String str2) {
        com.huawei.a.a.b.c.a.c cVarO = o(str, str2);
        if (cVarO != null) {
            return cVarO.n();
        }
        return true;
    }

    public static boolean j(String str, String str2) {
        com.huawei.a.a.b.c.a.c cVarO = o(str, str2);
        if (cVarO != null) {
            return cVarO.o();
        }
        return false;
    }

    public static boolean k(String str, String str2) {
        com.huawei.a.a.b.c.a.c cVarO = o(str, str2);
        if (cVarO != null) {
            return cVarO.m();
        }
        return true;
    }

    public static Map<String, String> l(String str, String str2) {
        com.huawei.a.a.b.c.a.c cVarO = o(str, str2);
        if (cVarO != null) {
            return cVarO.a();
        }
        return null;
    }

    public static String m(String str, String str2) {
        com.huawei.a.a.b.c.a.c cVarO = o(str, str2);
        return cVarO != null ? cVarO.p() : "";
    }

    public static long n(String str, String str2) {
        com.huawei.a.a.b.c.a.c cVarO = o(str, str2);
        if (cVarO != null) {
            return cVarO.q();
        }
        return 0L;
    }

    private static com.huawei.a.a.b.c.a.c o(String str, String str2) {
        e eVarA = com.huawei.a.a.b.c.a.a.a().a(str);
        if (eVarA == null) {
            return null;
        }
        if (!"alltype".equals(str2)) {
            return eVarA.a(str2);
        }
        com.huawei.a.a.b.c.a.c cVarA = eVarA.a("oper");
        return cVarA == null ? eVarA.a("maint") : cVarA;
    }
}
