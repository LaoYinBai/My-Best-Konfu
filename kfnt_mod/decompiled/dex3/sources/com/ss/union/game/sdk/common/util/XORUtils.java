package com.ss.union.game.sdk.common.util;

import android.util.Base64;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class XORUtils {
    private static final byte[] a = "light__game__sdk".getBytes();

    private static byte[] a(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        byte[] bArr3 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr3[i] = (byte) ((bArr[i] ^ a[i % a.length]) ^ bArr2[i % bArr2.length]);
        }
        return bArr3;
    }

    public static String decrypt(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        try {
            byte[] bArrDecode = Base64.decode(str, 0);
            int i = bArrDecode[bArrDecode.length - 1];
            byte[] bArr = new byte[i];
            byte[] bArr2 = new byte[bArrDecode.length - i];
            System.arraycopy(bArrDecode, bArrDecode.length - i, bArr, 0, i);
            System.arraycopy(bArrDecode, 0, bArr2, 0, bArrDecode.length - i);
            return new String(a(bArr2, bArr));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
