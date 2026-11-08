package com.ss.union.game.sdk.common.util;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DuplicateClickUtils {
    private static long a = 0;
    private static final int b = 1000;

    private DuplicateClickUtils() {
    }

    public static boolean prevent() {
        return prevent(1000);
    }

    public static boolean prevent(int i) {
        if (System.currentTimeMillis() - a < i) {
            return true;
        }
        a = System.currentTimeMillis();
        return false;
    }
}
