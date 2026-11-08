package com.apm.insight.l;

import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class r {

    /* renamed from: a, reason: collision with root package name */
    private static int f2966a;

    public static int a(Object obj, int i) {
        if (obj == null) {
            return i;
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        if (obj instanceof String) {
            try {
                return Integer.parseInt(String.valueOf(obj));
            } catch (Throwable unused) {
            }
        }
        return i;
    }

    public static Float a(Map<? super String, Float> map) {
        float fFloatValue = 0.0f;
        for (Float f : map.values()) {
            if (f != null) {
                fFloatValue += f.floatValue();
            }
        }
        return Float.valueOf(fFloatValue);
    }

    public static Long a(Map<? super String, Long> map, String str, Long l) {
        if (str == null || map == null) {
            return -1L;
        }
        Long l2 = map.get(str);
        if (l2 != null) {
            l = Long.valueOf(l2.longValue() + l.longValue());
        }
        map.put(str, l);
        return l;
    }

    public static void a(JSONObject jSONObject) {
    }

    public static boolean a(int i) {
        return false;
    }

    public static int b(int i) {
        switch (i) {
            case 0:
                return 4;
            case 1:
                return 8;
            case 2:
                return 16;
            case 3:
                return 32;
            case 4:
                return 64;
            case 5:
                return 128;
            default:
                return 0;
        }
    }
}
