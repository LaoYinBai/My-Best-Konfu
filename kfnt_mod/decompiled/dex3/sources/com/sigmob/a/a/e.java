package com.sigmob.a.a;

import kotlin.UByte;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class e {
    public static long a(byte[] bArr, int i, int i2) {
        boolean z;
        int i3 = i + i2;
        if (i2 < 2) {
            throw new IllegalArgumentException("Length " + i2 + " must be at least 2");
        }
        int i4 = i;
        while (true) {
            if (i4 >= i3) {
                z = true;
                break;
            }
            if (bArr[i4] != 0) {
                z = false;
                break;
            }
            i4++;
        }
        long j = 0;
        if (z) {
            return 0L;
        }
        int i5 = i;
        while (i5 < i3 && bArr[i5] == 32) {
            i5++;
        }
        int i6 = i3 - 1;
        byte b = bArr[i6];
        if (b != 0 && b != 32) {
            throw new IllegalArgumentException(a(bArr, i, i2, i6, b));
        }
        int i7 = i3 - 1;
        byte b2 = bArr[i7 - 1];
        if (b2 == 0 || b2 == 32) {
            i7--;
        }
        while (i5 < i7) {
            byte b3 = bArr[i5];
            if (b3 < 48 || b3 > 55) {
                throw new IllegalArgumentException(a(bArr, i, i2, i5, b3));
            }
            j = (j << 3) + (b3 - 48);
            i5++;
        }
        return j;
    }

    private static String a(byte[] bArr, int i, int i2, int i3, byte b) {
        return "Invalid byte " + ((int) b) + " at offset " + (i3 - i) + " in '" + new String(bArr, i, i2).replaceAll("\u0000", "{NUL}") + "' len=" + i2;
    }

    public static boolean a(byte[] bArr, int i) {
        return bArr[i] == 1;
    }

    public static String b(byte[] bArr, int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer(i2);
        int i3 = i2 + i;
        while (i < i3) {
            byte b = bArr[i];
            if (b == 0) {
                break;
            }
            stringBuffer.append((char) (b & UByte.MAX_VALUE));
            i++;
        }
        return stringBuffer.toString();
    }
}
