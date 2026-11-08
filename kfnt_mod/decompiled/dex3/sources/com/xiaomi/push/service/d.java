package com.xiaomi.push.service;

import com.xiaomi.push.ii;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d {
    private static a a;

    /* renamed from: a, reason: collision with other field name */
    private static b f947a;

    public interface a {
        boolean a(ii iiVar);
    }

    public interface b {
    }

    public static void a(b bVar) {
        f947a = bVar;
    }

    public static boolean a(ii iiVar) {
        String str;
        if (a == null || iiVar == null) {
            str = "rc params is null, not cpra";
        } else {
            if (com.xiaomi.push.l.m548a(com.xiaomi.push.t.m643a())) {
                return a.a(iiVar);
            }
            str = "rc app not permission to cpra";
        }
        com.xiaomi.channel.commonutils.logger.b.m22a(str);
        return false;
    }
}
