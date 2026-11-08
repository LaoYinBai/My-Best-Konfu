package com.umeng.commonsdk.config;

import android.content.Context;
import android.util.Pair;
import com.umeng.commonsdk.config.d;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class FieldManager {
    private static final String a = "cfgfield";
    private static b b = b.a();

    private FieldManager() {
    }

    private static class a {
        private static final FieldManager a = new FieldManager();

        private a() {
        }
    }

    public static FieldManager a() {
        return a.a;
    }

    public static boolean allow(String str) {
        b bVar = b;
        return b.a(str);
    }

    public void a(Context context) {
        String str;
        String[] strArr = {d.a.class.getName(), d.b.class.getName(), d.c.class.getName(), d.EnumC0139d.class.getName()};
        Pair<Long, String> pairA = a(UMEnvelopeBuild.imprintProperty(context, a, "1001@1073741823,536870911,262143,2047"));
        if (((Long) pairA.first).longValue() <= 1000 || (str = (String) pairA.second) == null || str.length() <= 0) {
            str = "1001@1073741823,536870911,262143,2047";
        }
        String[] strArrSplit = str.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        int length = strArrSplit.length;
        if (length > 0) {
            ArrayList arrayList = new ArrayList();
            g gVar = new g();
            for (int i = 0; i < length; i++) {
                arrayList.add(gVar);
                ((e) arrayList.get(i)).a(strArrSplit[i], b, d.b(strArr[i]));
            }
        }
    }

    private static Pair<Long, String> a(String str) {
        Pair<Long, String> pair = new Pair<>(-1L, null);
        if (str == null || str.length() < 2) {
            return pair;
        }
        String[] strArrSplit = str.split("@");
        if (strArrSplit.length < 2) {
            return pair;
        }
        try {
            long j = Long.parseLong(strArrSplit[0]);
            return new Pair<>(Long.valueOf(j), strArrSplit[1]);
        } catch (Throwable unused) {
            return pair;
        }
    }
}
