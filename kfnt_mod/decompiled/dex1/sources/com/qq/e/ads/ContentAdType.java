package com.qq.e.ads;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public enum ContentAdType {
    AD,
    INFORMATION;

    public static ContentAdType fromString(String str) {
        if ("ad".equals(str)) {
            return AD;
        }
        if ("information".equals(str)) {
            return INFORMATION;
        }
        return null;
    }
}
