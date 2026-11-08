package com.bytedance.frameworks.core.encrypt;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class TTEncryptUtils {
    private static native byte[] handleData(byte[] bArr, int i);

    static {
        try {
            System.loadLibrary("ttEncrypt");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }
    }

    public static byte[] a(byte[] bArr, int i) {
        if (bArr != null && i > 0) {
            try {
                if (bArr.length == i) {
                    return handleData(bArr, i);
                }
            } catch (Throwable unused) {
                return null;
            }
        }
        return null;
    }
}
