package com.huawei.a.a.b.d;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e {
    public static Map<String, List<com.huawei.a.a.b.c.b.d>> a(Context context, String str) {
        if (context == null) {
            return null;
        }
        Map<String, ?> mapB = com.huawei.a.a.b.i.a.b(context, str);
        a(mapB);
        return b(mapB);
    }

    public static Map<String, List<com.huawei.a.a.b.c.b.d>> a(Context context, String str, String str2) {
        Map<String, List<com.huawei.a.a.b.c.b.d>> mapA;
        Map<String, List<com.huawei.a.a.b.c.b.d>> mapA2;
        if ("alltype".equals(str2) || TextUtils.isEmpty(str)) {
            com.huawei.a.a.b.e.a.b("EventManager", "read all event records");
            mapA = a(context, "stat_v2_1");
            mapA2 = a(context, "cached_v2_1");
        } else {
            String strA = com.huawei.a.a.b.k.e.a(str, str2);
            mapA = b(context, "stat_v2_1", strA);
            mapA2 = b(context, "cached_v2_1", strA);
        }
        return a(mapA, mapA2);
    }

    private static Map<String, List<com.huawei.a.a.b.c.b.d>> a(Map<String, List<com.huawei.a.a.b.c.b.d>> map, Map<String, List<com.huawei.a.a.b.c.b.d>> map2) {
        if (map.size() == 0 && map2.size() == 0) {
            return new HashMap();
        }
        if (map.size() == 0) {
            return map2;
        }
        if (map2.size() == 0) {
            return map;
        }
        HashMap map3 = new HashMap();
        for (Map.Entry<String, List<com.huawei.a.a.b.c.b.d>> entry : map.entrySet()) {
            String key = entry.getKey();
            List<com.huawei.a.a.b.c.b.d> value = entry.getValue();
            value.addAll(map2.get(key));
            map3.put(key, value);
        }
        return map3;
    }

    private static void a(String str, String str2, Map<String, List<com.huawei.a.a.b.c.b.d>> map) {
        ArrayList arrayList = new ArrayList();
        try {
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            JSONArray jSONArray = new JSONArray(str2);
            if (jSONArray.length() == 0) {
                return;
            }
            for (int i = 0; i < jSONArray.length(); i++) {
                com.huawei.a.a.b.c.b.d dVar = new com.huawei.a.a.b.c.b.d();
                try {
                    dVar.a(jSONArray.getJSONObject(i));
                    arrayList.add(dVar);
                } catch (JSONException unused) {
                    com.huawei.a.a.b.e.a.c("EventManager", "JSON Exception happened when create data for report - readDataToRecord");
                }
            }
            map.put(str, arrayList);
        } catch (JSONException unused2) {
            com.huawei.a.a.b.e.a.c("EventManager", "When events turn to JSONArray,JSON Exception has happened");
        }
    }

    private static void a(Map<String, ?> map) {
        Iterator<Map.Entry<String, ?>> it = map.entrySet().iterator();
        Set<String> setA = com.huawei.a.a.b.k.e.a(com.huawei.a.a.a.b.a());
        while (it.hasNext()) {
            if (!setA.contains(it.next().getKey())) {
                it.remove();
            }
        }
    }

    public static Map<String, List<com.huawei.a.a.b.c.b.d>> b(Context context, String str, String str2) {
        String strB = com.huawei.a.a.b.i.a.b(context, str, str2, "");
        HashMap map = new HashMap();
        a(str2, strB, map);
        return map;
    }

    private static Map<String, List<com.huawei.a.a.b.c.b.d>> b(Map<String, ?> map) {
        HashMap map2 = new HashMap();
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            String key = entry.getKey();
            if (entry.getValue() instanceof String) {
                a(key, (String) entry.getValue(), map2);
            }
        }
        return map2;
    }
}
