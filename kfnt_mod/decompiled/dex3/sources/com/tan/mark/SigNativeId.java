package com.tan.mark;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SigNativeId {
    static {
        System.loadLibrary("sig_native");
    }

    public static native byte[] getBootFromJNI();

    public static native String getUpdateFromJNI();
}
