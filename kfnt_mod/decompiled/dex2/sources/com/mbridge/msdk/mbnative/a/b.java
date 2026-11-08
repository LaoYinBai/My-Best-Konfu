package com.mbridge.msdk.mbnative.a;

import android.text.TextUtils;
import com.mbridge.msdk.foundation.tools.z;
import com.mbridge.msdk.out.Campaign;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: AbsNativeCache.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class b<K, V> {
    public V a(K k, int i) {
        return null;
    }

    public abstract void a(K k, V v);

    public abstract void a(K k, V v, String str);

    public abstract void a(String str);

    public abstract void a(String str, Campaign campaign, String str2);

    public abstract V b(K k, int i);

    public final long a() {
        com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
        if (aVarB == null) {
            aVarB = com.mbridge.msdk.b.b.a().b();
        }
        return aVarB.L() * 1000;
    }

    public final void a(String str, List<Campaign> list, Map<String, Map<Long, Object>> map) {
        if (TextUtils.isEmpty(str) || list == null || list.size() <= 0) {
            return;
        }
        HashMap map2 = new HashMap();
        map2.put(Long.valueOf(System.currentTimeMillis()), list);
        map.put(str, map2);
    }

    public final List<Campaign> a(String str, Map<String, Map<Long, Object>> map, int i) {
        Map<Long, Object> map2;
        if (!TextUtils.isEmpty(str) && map != null && map.containsKey(str) && (map2 = map.get(str)) != null && map2.size() > 0) {
            Iterator<Map.Entry<Long, Object>> it = map2.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Long, Object> next = it.next();
                if (System.currentTimeMillis() - next.getKey().longValue() > a()) {
                    it.remove();
                    return null;
                }
                List list = (List) next.getValue();
                if (list != null && list.size() > 0) {
                    if (i == 0) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.addAll(list);
                        it.remove();
                        return arrayList;
                    }
                    return list.subList(0, Math.min(list.size(), i));
                }
            }
        }
        return null;
    }

    public final void a(String str, Map<String, Map<Long, Object>> map) {
        try {
            if (!z.a(str) && map != null && map.containsKey(str)) {
                Iterator<String> it = map.keySet().iterator();
                while (it.hasNext()) {
                    String next = it.next();
                    if (z.b(next) && next.equals(str)) {
                        it.remove();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
