package com.umeng.message.proguard;

import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class i {
    public static String a(Map<String, Object> map, String str) {
        if (map == null || map.isEmpty()) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        Set<String> setKeySet = map.keySet();
        if (!str.endsWith("?")) {
            sb.append("?");
        }
        for (String str2 : setKeySet) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(URLEncoder.encode(str2));
            sb2.append("=");
            sb2.append(URLEncoder.encode(map.get(str2) == null ? "" : map.get(str2).toString()));
            sb2.append("&");
            sb.append(sb2.toString());
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
