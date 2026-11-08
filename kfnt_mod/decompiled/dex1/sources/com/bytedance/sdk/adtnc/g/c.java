package com.bytedance.sdk.adtnc.g;

import java.util.LinkedHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {
    public static synchronized String a(com.bytedance.sdk.adtnc.sdk.b.a aVar, int i) {
        String strA;
        strA = "";
        if (i == 1) {
            try {
                strA = aVar.k().a(aVar.a(), "tnc_config_" + aVar.c(), "");
            } catch (Exception unused) {
                strA = "";
            }
        }
        return strA instanceof String ? String.valueOf(strA) : "";
    }

    public static synchronized void a(com.bytedance.sdk.adtnc.sdk.b.a aVar, int i, String str) {
        try {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            if (i == 1) {
                linkedHashMap.put("tnc_config_" + aVar.c(), str);
            }
            if (aVar.k() != null) {
                aVar.k().a(aVar.a(), linkedHashMap);
            }
        } catch (Exception unused) {
        }
    }
}
