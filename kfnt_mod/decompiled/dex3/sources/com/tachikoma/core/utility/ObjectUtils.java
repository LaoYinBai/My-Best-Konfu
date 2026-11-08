package com.tachikoma.core.utility;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ObjectUtils {
    public static <T> T requireNonNull(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }
}
