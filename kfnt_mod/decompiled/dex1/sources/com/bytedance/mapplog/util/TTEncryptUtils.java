package com.bytedance.mapplog.util;

import bykvm_19do.bykvm_19do.bykvm_19do.l0;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class TTEncryptUtils {
    static {
        try {
            System.loadLibrary("tobEmbedEncryptForM");
        } catch (UnsatisfiedLinkError e) {
            l0.a(e);
        }
    }

    public static byte[] a(byte[] bArr, int i) {
        try {
            return ttEncrypt(bArr, i);
        } catch (Throwable unused) {
            return null;
        }
    }

    private static native byte[] ttEncrypt(byte[] bArr, int i);
}
