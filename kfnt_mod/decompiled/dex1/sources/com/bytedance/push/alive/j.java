package com.bytedance.push.alive;

import android.annotation.SuppressLint;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class j {
    @SuppressLint({"UseValueOf"})
    public static byte[] a(int i) {
        byte[] bArr = new byte[4];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = new Integer(i).byteValue();
            i >>= 8;
        }
        return bArr;
    }
}
