package com.apm.insight.runtime;

import com.appsflyer.share.Constants;
import com.bytedance.common.utility.date.DateDef;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static long f3020a = -30000;

    /* renamed from: b, reason: collision with root package name */
    private static File f3021b;

    public static String a(long j, String str) {
        try {
            return com.apm.insight.l.i.c(new File(com.apm.insight.l.o.j(com.apm.insight.h.g()), "apminsight/TrackInfo/" + ((j - (j % DateDef.DAY)) / DateDef.DAY) + Constants.URL_PATH_DELIMITER + str));
        } catch (Throwable th) {
            return th.getMessage();
        }
    }

    public static void a() {
        File file = new File(com.apm.insight.l.o.j(com.apm.insight.h.g()), "apminsight/TrackInfo/");
        String[] list = file.list();
        if (list != null && list.length > 5) {
            Arrays.sort(list);
            for (int i = 0; i < list.length - 5; i++) {
                com.apm.insight.l.i.a(new File(file, list[i]));
            }
        }
    }

    public static void a(long j) throws Throwable {
        if (j - f3020a < com.bytedance.b.a.a.c.g.e) {
            return;
        }
        f3020a = j;
        try {
            com.apm.insight.l.i.a(b(), String.valueOf(System.currentTimeMillis()), false);
        } catch (IOException unused) {
        }
    }

    private static File b() {
        if (f3021b == null) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            f3021b = new File(com.apm.insight.l.o.j(com.apm.insight.h.g()), "apminsight/TrackInfo/" + ((jCurrentTimeMillis - (jCurrentTimeMillis % DateDef.DAY)) / DateDef.DAY) + Constants.URL_PATH_DELIMITER + com.apm.insight.h.f());
        }
        return f3021b;
    }
}
