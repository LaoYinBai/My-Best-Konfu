package com.bytedance.sdk.account.utils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class TypeUtils {
    public static long parseLong(String str, long j) {
        if (str == null || str.isEmpty()) {
            return j;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return j;
        }
    }
}
