package com.bytedance.gameprotect;

import androidx.annotation.NonNull;
import com.ss.sys.ces.gg.tt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class o {
    @NonNull
    private static Map<String, List<String>> a(@NonNull Map<String, String> map) {
        HashMap map2 = new HashMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String lowerCase = entry.getKey().toLowerCase(Locale.US);
            List arrayList = (List) map2.get(lowerCase);
            if (arrayList == null) {
                arrayList = new ArrayList(2);
                map2.put(lowerCase, arrayList);
            }
            arrayList.add(entry.getValue());
        }
        return map2;
    }

    public static boolean a(@NonNull String str, @NonNull Map<String, String> map) {
        return b(str, map);
    }

    public static boolean a(@NonNull String str, @NonNull Map<String, String> map, @NonNull byte[] bArr) {
        map.put("X-SS-STUB", c.a.a.a.a.g.b(bArr).toUpperCase());
        boolean zB = b(str, map);
        if (!zB) {
            map.remove("X-SS-STUB");
        }
        return zB;
    }

    private static boolean b(@NonNull String str, @NonNull Map<String, String> map) {
        Map<? extends String, ? extends String> mapCalcHttpRequestChecksum = tt.calcHttpRequestChecksum(str, a(map));
        if (mapCalcHttpRequestChecksum.isEmpty()) {
            return false;
        }
        map.putAll(mapCalcHttpRequestChecksum);
        return true;
    }
}
