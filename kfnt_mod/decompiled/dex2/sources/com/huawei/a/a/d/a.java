package com.huawei.a.a.d;

import android.util.Pair;
import com.huawei.a.a.a.c;
import com.huawei.a.a.b.c.b.e;
import com.huawei.a.a.b.d.d;
import com.huawei.a.a.b.d.f;
import com.huawei.a.a.e.b;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends d {
    public static e a(List<com.huawei.a.a.b.c.b.d> list, String str, String str2, String str3, String str4) {
        com.huawei.a.a.b.e.a.b("hmsSdk", "generate UploadData");
        e eVarA = d.a();
        if (eVarA == null) {
            return null;
        }
        eVarA.a(b(f.a().d(), str, str2, str3));
        eVarA.a(c(str, str2));
        eVarA.a(b(str2, str, str4));
        eVarA.a(c.f(str, str2));
        eVarA.a(list);
        return eVarA;
    }

    protected static b b(String str, String str2, String str3, String str4) {
        b bVarA = d.a(str, str2, str3, str4);
        String strA = com.huawei.a.a.c.b.a.a().a(c.k(str2, str3));
        long jCurrentTimeMillis = System.currentTimeMillis();
        String strA2 = com.huawei.a.a.b.a.b.a().a(com.huawei.a.a.a.b.e() + strA + jCurrentTimeMillis);
        bVarA.b(String.valueOf(jCurrentTimeMillis));
        bVarA.g(strA2);
        return bVarA;
    }

    protected static com.huawei.a.a.e.c b(String str, String str2, String str3) {
        com.huawei.a.a.e.c cVarA = d.a(str, str2, str3);
        Pair<String, String> pairB = com.huawei.a.a.c.b.a.a().b(str2, str);
        cVarA.f((String) pairB.first);
        cVarA.g((String) pairB.second);
        cVarA.h(com.huawei.a.a.c.b.c.b());
        cVarA.e(com.huawei.a.a.c.b.a.a().f(str2, str));
        return cVarA;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    protected static com.huawei.a.a.e.a c(String str, String str2) {
        com.huawei.a.a.e.a aVarA = d.a(str, str2);
        com.huawei.a.a.c.a.a aVarA2 = com.huawei.a.a.c.b.a.a().a(str, str2);
        aVarA.f(com.huawei.a.a.c.b.a.a().a(c.k(str, str2)));
        aVarA.d(c.a(str, str2));
        aVarA.g(com.huawei.a.a.c.b.a.a().c(str, str2));
        switch (aVarA2.a()) {
            case SN:
                aVarA.c(aVarA2.b());
                return aVarA;
            case IMEI:
                aVarA.b(aVarA2.b());
                return aVarA;
            case UDID:
                aVarA.e(aVarA2.b());
                return aVarA;
            default:
                return aVarA;
        }
    }

    public static Map<String, String> c(String str, String str2, String str3) {
        Map<String, String> mapB = b(str, str3);
        Map<String, String> mapL = c.l(str, str2);
        if (mapL == null) {
            return mapB;
        }
        mapB.putAll(mapL);
        return mapB;
    }
}
