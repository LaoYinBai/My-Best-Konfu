package com.apm.insight.l;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class u {
    public static boolean a(String str) {
        try {
            System.loadLibrary(str);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }
}
