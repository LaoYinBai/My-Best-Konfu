package com.mbridge.msdk.mbnative.a;

import com.mbridge.msdk.out.Campaign;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: NativeCacheFactory.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class f {
    public static Map<Integer, b<String, List<Campaign>>> a = new HashMap();

    public static b<String, List<Campaign>> a(int i) {
        b<String, List<Campaign>> aVar;
        if (a.containsKey(Integer.valueOf(i))) {
            return a.get(Integer.valueOf(i));
        }
        switch (i) {
            case 1:
                aVar = new a(i);
                break;
            case 2:
                aVar = new a(i);
                break;
            case 3:
                aVar = new d();
                break;
            case 4:
            case 5:
            default:
                aVar = new a(i);
                break;
            case 6:
                aVar = new c();
                break;
            case 7:
                aVar = new e();
                break;
        }
        a.put(Integer.valueOf(i), aVar);
        return aVar;
    }
}
