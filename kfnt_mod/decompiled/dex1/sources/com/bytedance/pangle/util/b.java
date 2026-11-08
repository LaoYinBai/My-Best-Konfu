package com.bytedance.pangle.util;

import androidx.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class b {
    public static <T> boolean a(@Nullable T[] tArr) {
        return tArr == null || tArr.length == 0;
    }

    public static <T> boolean a(@Nullable T[] tArr, T[] tArr2) {
        if (tArr2 == null) {
            return true;
        }
        for (T t : tArr2) {
            if (!a(tArr, t)) {
                return false;
            }
        }
        return true;
    }

    private static <T> boolean a(@Nullable T[] tArr, T t) {
        int i;
        if (tArr != null) {
            i = 0;
            while (i < tArr.length) {
                if (tArr[i] == t || (tArr[i] != null && tArr[i].equals(t))) {
                    break;
                }
                i++;
            }
            i = -1;
        } else {
            i = -1;
        }
        return i != -1;
    }
}
