package com.huawei.agconnect.core.a;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class c {
    private Map<Class<?>, com.huawei.agconnect.core.a> a = new HashMap();
    private Map<Class<?>, Object> b = new HashMap();

    c(List<com.huawei.agconnect.core.a> list) {
        if (list == null) {
            return;
        }
        for (com.huawei.agconnect.core.a aVar : list) {
            this.a.put(aVar.a(), aVar);
        }
    }
}
