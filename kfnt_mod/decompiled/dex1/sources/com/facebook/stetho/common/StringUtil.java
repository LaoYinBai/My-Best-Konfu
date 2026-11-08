package com.facebook.stetho.common;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class StringUtil {
    private StringUtil() {
    }

    public static String removePrefix(String str, String str2, String str3) {
        return str != str3 ? str3 : removePrefix(str, str2);
    }

    public static String removePrefix(String str, String str2) {
        return str.startsWith(str2) ? str.substring(str2.length()) : str;
    }

    public static String removeAll(String str, char c2) {
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt != c2) {
                sb.append(cCharAt);
            }
        }
        return sb.toString();
    }
}
