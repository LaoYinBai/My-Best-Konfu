package com.mbridge.msdk.thrid.okio;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class Utf8 {
    private Utf8() {
    }

    public static long size(String str) {
        return size(str, 0, str.length());
    }

    public static long size(String str, int i, int i2) {
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        }
        if (i < 0) {
            throw new IllegalArgumentException("beginIndex < 0: " + i);
        }
        if (i2 < i) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + i2 + " < " + i);
        }
        if (i2 > str.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + i2 + " > " + str.length());
        }
        long j = 0;
        while (i < i2) {
            char cCharAt = str.charAt(i);
            if (cCharAt < 128) {
                j++;
                i++;
            } else if (cCharAt < 2048) {
                j += 2;
                i++;
            } else if (cCharAt < 55296 || cCharAt > 57343) {
                j += 3;
                i++;
            } else {
                int i3 = i + 1;
                char cCharAt2 = i3 < i2 ? str.charAt(i3) : (char) 0;
                if (cCharAt > 56319 || cCharAt2 < 56320 || cCharAt2 > 57343) {
                    j++;
                    i = i3;
                } else {
                    j += 4;
                    i += 2;
                }
            }
        }
        return j;
    }
}
