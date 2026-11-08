package com.huawei.updatesdk.service.e;

import android.text.TextUtils;
import com.mbridge.msdk.MBridgeConstans;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class b {
    private static final Map<Integer, String> a = new HashMap();
    private static b b = new b();
    private int c;

    static {
        a.put(1, "1.0");
        a.put(2, "1.5");
        a.put(3, "1.6");
        a.put(4, MBridgeConstans.NATIVE_VIDEO_VERSION);
        a.put(5, MBridgeConstans.NATIVE_VIDEO_VERSION);
        a.put(6, "2.3");
        a.put(7, "3.0");
        a.put(8, "3.0.5");
        a.put(8, "3.1");
        a.put(9, "4.0");
        a.put(10, "4.1");
        a.put(11, "5.0");
        a.put(12, "5.1");
    }

    private b() {
        this.c = 0;
        this.c = c();
        if (this.c == 0) {
            this.c = d();
        }
        com.huawei.updatesdk.a.a.b.a.a.a.a("EMUISupportUtil", "emuiVersion:" + this.c);
    }

    public static b a() {
        return b;
    }

    private String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String[] strArrSplit = str.split("_");
        return strArrSplit.length == 2 ? strArrSplit[1] : "";
    }

    private int c() {
        return com.huawei.updatesdk.a.a.c.a.b.a("ro.build.hw_emui_api_level", 0);
    }

    private int d() {
        String strA = a(com.huawei.updatesdk.a.a.c.a.b.a("ro.build.version.emui", ""));
        if (TextUtils.isEmpty(strA)) {
            return 0;
        }
        for (Map.Entry<Integer, String> entry : a.entrySet()) {
            if (strA.equals(entry.getValue())) {
                return entry.getKey().intValue();
            }
        }
        return 0;
    }

    public int b() {
        return this.c;
    }
}
