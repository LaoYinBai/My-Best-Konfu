package com.huawei.a.a.c.b;

import android.content.Context;
import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class b {
    private com.huawei.a.a.c.a.a a(int i, Context context) {
        String strB = "";
        if ((i & 4) != 0 && (i & 1) != 0) {
            return new com.huawei.a.a.c.a.a(com.huawei.a.a.c.a.b.UDID, a(b(context)));
        }
        if ((i & 1) != 0) {
            strB = b(context);
            if (!TextUtils.isEmpty(strB)) {
                return new com.huawei.a.a.c.a.a(com.huawei.a.a.c.a.b.SN, strB);
            }
        }
        if ((i & 2) == 0) {
            return new com.huawei.a.a.c.a.a(com.huawei.a.a.c.a.b.EMPTY, strB);
        }
        return new com.huawei.a.a.c.a.a(com.huawei.a.a.c.a.b.IMEI, c(context));
    }

    private com.huawei.a.a.c.a.a b(int i, Context context) {
        String strC = "";
        if (i != 0) {
            strC = f();
            if (!TextUtils.isEmpty(strC)) {
                return new com.huawei.a.a.c.a.a(com.huawei.a.a.c.a.b.UDID, strC);
            }
        }
        if ((i & 2) != 0) {
            strC = c(context);
            if (!TextUtils.isEmpty(strC)) {
                return new com.huawei.a.a.c.a.a(com.huawei.a.a.c.a.b.IMEI, strC);
            }
        }
        if ((i & 1) == 0) {
            return new com.huawei.a.a.c.a.a(com.huawei.a.a.c.a.b.EMPTY, strC);
        }
        return new com.huawei.a.a.c.a.a(com.huawei.a.a.c.a.b.SN, b(context));
    }

    private String b(Context context) {
        com.huawei.a.a.b.c.a.d dVarC = com.huawei.a.a.b.c.a.a.a().c();
        if (TextUtils.isEmpty(dVarC.d())) {
            dVarC.a(c.f(context));
        }
        return dVarC.d();
    }

    private String c(Context context) {
        com.huawei.a.a.b.c.a.d dVarC = com.huawei.a.a.b.c.a.a.a().c();
        if (TextUtils.isEmpty(dVarC.g())) {
            dVarC.d(c.e(context));
        }
        return dVarC.g();
    }

    private boolean e() {
        com.huawei.a.a.b.c.a.d dVarC = com.huawei.a.a.b.c.a.a.a().c();
        if (TextUtils.isEmpty(dVarC.f())) {
            dVarC.c(c.a());
        }
        return !TextUtils.isEmpty(dVarC.f());
    }

    private String f() {
        com.huawei.a.a.b.c.a.d dVarC = com.huawei.a.a.b.c.a.a.a().c();
        if (TextUtils.isEmpty(dVarC.h())) {
            dVarC.e(c.c());
        }
        return dVarC.h();
    }

    public com.huawei.a.a.c.a.a a(Context context) {
        String strA = a();
        if (!TextUtils.isEmpty(strA)) {
            return new com.huawei.a.a.c.a.a(com.huawei.a.a.c.a.b.UDID, strA);
        }
        String strB = b();
        if (!TextUtils.isEmpty(strB)) {
            return new com.huawei.a.a.c.a.a(com.huawei.a.a.c.a.b.IMEI, strB);
        }
        boolean zE = e();
        String strC = c();
        return !TextUtils.isEmpty(strC) ? zE ? new com.huawei.a.a.c.a.a(com.huawei.a.a.c.a.b.SN, strC) : new com.huawei.a.a.c.a.a(com.huawei.a.a.c.a.b.UDID, a(strC)) : zE ? b(d(), context) : a(d(), context);
    }

    public abstract String a();

    public abstract String a(String str);

    public abstract String b();

    public abstract String c();

    public abstract int d();
}
