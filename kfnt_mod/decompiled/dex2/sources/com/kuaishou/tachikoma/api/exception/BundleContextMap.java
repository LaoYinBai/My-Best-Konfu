package com.kuaishou.tachikoma.api.exception;

import com.kuaishou.tachikoma.api.model.TKBundle;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class BundleContextMap {
    private static Map<Integer, TKBundle> sMap = new HashMap();

    public static TKBundle get(int i) {
        return sMap.get(Integer.valueOf(i));
    }

    public static void remove(int i) {
        sMap.remove(Integer.valueOf(i));
    }

    public static void setData(int i, TKBundle tKBundle) {
        sMap.put(Integer.valueOf(i), tKBundle);
    }
}
