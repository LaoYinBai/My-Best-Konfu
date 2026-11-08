package com.ss.union.game.sdk.core.glide.util.pool;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class GlideTrace {
    private static final boolean a = false;
    private static final int b = 127;

    public static void beginSection(String str) {
    }

    public static void beginSectionFormat(String str, Object obj) {
    }

    public static void beginSectionFormat(String str, Object obj, Object obj2) {
    }

    public static void beginSectionFormat(String str, Object obj, Object obj2, Object obj3) {
    }

    public static void endSection() {
    }

    private GlideTrace() {
    }

    private static String a(String str) {
        return str.length() > b ? str.substring(0, 126) : str;
    }
}
