package com.unity3d.services.ar;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ARCheck {
    public static boolean isFrameworkPresent() throws ClassNotFoundException {
        try {
            Class.forName("com.google.ar.core.Session");
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
