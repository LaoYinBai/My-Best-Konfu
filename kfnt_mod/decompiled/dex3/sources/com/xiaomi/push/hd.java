package com.xiaomi.push;

import com.xiaomi.push.gz;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.av;
import java.util.Hashtable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class hd {
    private static final int a = fc.PING_RTT.a();

    static class a {
        static Hashtable<Integer, Long> a = new Hashtable<>();
    }

    public static void a() {
        a(0, a);
    }

    public static void a(int i) {
        fd fdVarM376a = hb.m374a().m376a();
        fdVarM376a.a(fc.CHANNEL_STATS_COUNTER.a());
        fdVarM376a.c(i);
        hb.m374a().a(fdVarM376a);
    }

    public static synchronized void a(int i, int i2) {
        try {
            if (i2 < 16777215) {
                a.a.put(Integer.valueOf((i << 24) | i2), Long.valueOf(System.currentTimeMillis()));
            } else {
                com.xiaomi.channel.commonutils.logger.b.d("stats key should less than 16777215");
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public static void a(int i, int i2, int i3, String str, int i4) {
        fd fdVarM376a = hb.m374a().m376a();
        fdVarM376a.a((byte) i);
        fdVarM376a.a(i2);
        fdVarM376a.b(i3);
        fdVarM376a.b(str);
        fdVarM376a.c(i4);
        hb.m374a().a(fdVarM376a);
    }

    public static synchronized void a(int i, int i2, String str, int i3) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        int i4 = (i << 24) | i2;
        if (a.a.containsKey(Integer.valueOf(i4))) {
            fd fdVarM376a = hb.m374a().m376a();
            fdVarM376a.a(i2);
            fdVarM376a.b((int) (jCurrentTimeMillis - a.a.get(Integer.valueOf(i4)).longValue()));
            fdVarM376a.b(str);
            if (i3 > -1) {
                fdVarM376a.c(i3);
            }
            hb.m374a().a(fdVarM376a);
            a.a.remove(Integer.valueOf(i2));
        } else {
            com.xiaomi.channel.commonutils.logger.b.d("stats key not found");
        }
    }

    public static void a(XMPushService xMPushService, av.b bVar) {
        new gw(xMPushService, bVar).a();
    }

    public static void a(String str, int i, Exception exc) {
        fd fdVarM376a = hb.m374a().m376a();
        if (i > 0) {
            fdVarM376a.a(fc.GSLB_REQUEST_SUCCESS.a());
            fdVarM376a.b(str);
            fdVarM376a.b(i);
            hb.m374a().a(fdVarM376a);
            return;
        }
        try {
            gz.a aVarA = gz.a(exc);
            fdVarM376a.a(aVarA.a.a());
            fdVarM376a.c(aVarA.f455a);
            fdVarM376a.b(str);
            hb.m374a().a(fdVarM376a);
        } catch (NullPointerException unused) {
        }
    }

    public static void a(String str, Exception exc) {
        try {
            gz.a aVarB = gz.b(exc);
            fd fdVarM376a = hb.m374a().m376a();
            fdVarM376a.a(aVarB.a.a());
            fdVarM376a.c(aVarB.f455a);
            fdVarM376a.b(str);
            hb.m374a().a(fdVarM376a);
        } catch (NullPointerException unused) {
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public static byte[] m380a() {
        fe feVarM377a = hb.m374a().m377a();
        if (feVarM377a != null) {
            return it.a(feVarM377a);
        }
        return null;
    }

    public static void b() {
        a(0, a, null, -1);
    }

    public static void b(String str, Exception exc) {
        try {
            gz.a aVarD = gz.d(exc);
            fd fdVarM376a = hb.m374a().m376a();
            fdVarM376a.a(aVarD.a.a());
            fdVarM376a.c(aVarD.f455a);
            fdVarM376a.b(str);
            hb.m374a().a(fdVarM376a);
        } catch (NullPointerException unused) {
        }
    }
}
