package com.bytedance.gameprotect;

import androidx.annotation.Keep;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class IlIlllIiI {
    @Keep
    private static native Object IlIllLIiI(int i, long j, Object... objArr);

    @Keep
    private static native void IlIllLIiI();

    @Keep
    private static native void IlIllLIiI(int i);

    @Keep
    private static native void IlIllLIiI(int i, String str);

    @Keep
    private static native void IlIllLIiI(int i, String str, long j, String str2);

    @Keep
    private static native void IlIllLIiI(int i, String[] strArr, String[] strArr2);

    @Keep
    private static native void IlIllLIiI(String str, String str2);

    @Keep
    private static native void IlIllLIiI(String str, String str2, int i, boolean z, boolean z2, boolean z3);

    @Keep
    private static Object IlIllLIiL(int i, long j, Object... objArr) {
        return n.a(i, j, objArr);
    }

    public static Object a(int i, long j, Object... objArr) {
        return IlIllLIiI(i, j, objArr);
    }

    public static void a() {
        IlIllLIiI();
    }

    public static void a(int i) {
        IlIllLIiI(i);
    }

    public static void a(int i, String str) {
        IlIllLIiI(i, str);
    }

    public static void a(int i, String str, long j, String str2) {
        IlIllLIiI(i, str, j, str2);
    }

    public static void a(String str, String str2) {
        IlIllLIiI(str, str2);
    }

    public static void a(String str, String str2, int i, boolean z, boolean z2, boolean z3) {
        IlIllLIiI(str, str2, i, z, z2, z3);
    }
}
