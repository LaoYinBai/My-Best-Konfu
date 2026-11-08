package com.huawei.a.a.b.k;

import android.util.Pair;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class e {
    public static long a(String str, long j) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, Locale.getDefault());
            return simpleDateFormat.parse(simpleDateFormat.format(Long.valueOf(j))).getTime();
        } catch (ParseException unused) {
            com.huawei.a.a.b.e.a.c("hmsSdk/stringUtil", "getMillisOfDate(): Time conversion Exception !");
            return 0L;
        }
    }

    public static Pair<String, String> a(String str) {
        String strSubstring;
        String str2;
        if ("_default_config_tag".equals(str)) {
            return new Pair<>(str, "");
        }
        String[] strArrSplit = str.split("-");
        if (strArrSplit.length > 2) {
            str2 = strArrSplit[strArrSplit.length - 1];
            strSubstring = str.substring(0, (str.length() - str2.length()) - 1);
        } else {
            strSubstring = strArrSplit[0];
            str2 = strArrSplit[1];
        }
        return new Pair<>(strSubstring, str2);
    }

    public static String a(int i) {
        switch (i) {
            case 0:
                return "oper";
            case 1:
                return "maint";
            case 2:
                return "preins";
            case 3:
                return "diffprivacy";
            default:
                return "alltype";
        }
    }

    public static String a(String str, String str2) {
        if ("_default_config_tag".equals(str)) {
            return str;
        }
        return str + "-" + str2;
    }

    public static String a(String str, String str2, String str3) {
        if ("_default_config_tag".equals(str)) {
            return "_default_config_tag#" + str3;
        }
        return str + "-" + str2 + "#" + str3;
    }

    public static Set<String> a(Set<String> set) {
        String str;
        if (set == null || set.size() == 0) {
            return new HashSet();
        }
        HashSet hashSet = new HashSet();
        for (String str2 : set) {
            if ("_default_config_tag".equals(str2)) {
                str = "_default_config_tag";
            } else {
                String str3 = str2 + "-oper";
                String str4 = str2 + "-maint";
                str = str2 + "-diffprivacy";
                hashSet.add(str3);
                hashSet.add(str4);
            }
            hashSet.add(str);
        }
        return hashSet;
    }
}
