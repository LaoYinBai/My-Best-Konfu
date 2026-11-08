package com.ss.union.game.sdk.core.splashEffect.util;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TimeCollector {
    private static final String a = "开屏特效";
    private static Map<String, TimeCollector> b = new HashMap();
    private long c = -1;
    private String d;

    public TimeCollector(String str) {
        this.d = str;
    }

    public static TimeCollector effect() {
        return tag(a);
    }

    public static TimeCollector tag(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "TC" + System.currentTimeMillis();
        }
        return a(str);
    }

    private static TimeCollector a(String str) {
        if (!b.containsKey(str)) {
            b.put(str, new TimeCollector(str));
        }
        return b.get(str);
    }

    public void start() {
        this.c = System.currentTimeMillis();
    }

    public void end(String str) {
        long jCurrentTimeMillis = System.currentTimeMillis() - this.c;
        com.ss.union.game.sdk.core.splashEffect.a.a.b(this.d, str + "耗时毫秒：" + jCurrentTimeMillis);
    }

    public void endAll(String str) {
        end(str);
        b.remove(this.d);
    }
}
