package com.bytedance.gameprotect;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class p {
    public static Object a(int i, long j, Object... objArr) {
        try {
            return IlIlllIiI.a(i, j, objArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String a(byte[] bArr) {
        Object objA = a(8, 0L, bArr);
        return (objA == null || !(objA instanceof String)) ? "" : (String) objA;
    }

    public static void a(int i) {
        a(4, 0L, String.valueOf(i));
    }

    public static void a(int i, String str) {
        a(7, i, str);
    }

    public static void a(int i, String str, String str2) {
        a(9, i, str, str2);
    }

    public static void a(long j) {
        a(5, j, new Object[0]);
    }

    public static void a(long j, String str) {
        a(2, j, str);
    }

    public static void a(long j, String str, byte[] bArr) {
        a(1, j, str, bArr);
    }

    public static void a(Object obj) {
        a(13, 0L, obj);
    }
}
