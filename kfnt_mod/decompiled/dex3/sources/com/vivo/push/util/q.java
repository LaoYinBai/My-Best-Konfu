package com.vivo.push.util;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class q {
    public static int a(com.vivo.push.b.c cVar) {
        v vVarB = v.b();
        int i = cVar.a;
        long jCurrentTimeMillis = System.currentTimeMillis();
        int iB = vVarB.b("com.vivo.push_preferences.operate." + i + "OPERATE_COUNT");
        long jB = jCurrentTimeMillis - vVarB.b("com.vivo.push_preferences.operate." + i + "START_TIME", 0L);
        if (jB > 86400000 || jB < 0) {
            vVarB.a("com.vivo.push_preferences.operate." + i + "START_TIME", System.currentTimeMillis());
            vVarB.a("com.vivo.push_preferences.operate." + i + "OPERATE_COUNT", 1);
            return 0;
        }
        if (iB >= cVar.h) {
            return 1001;
        }
        vVarB.a("com.vivo.push_preferences.operate." + i + "OPERATE_COUNT", iB + 1);
        return 0;
    }
}
