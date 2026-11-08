package com.kwad.yoga;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class YogaMeasureOutput {
    public static float getHeight(long j) {
        return Float.intBitsToFloat((int) (j & (-1)));
    }

    public static float getWidth(long j) {
        return Float.intBitsToFloat((int) ((j >> 32) & (-1)));
    }

    public static long make(float f, float f2) {
        return Float.floatToRawIntBits(f2) | (Float.floatToRawIntBits(f) << 32);
    }

    public static long make(int i, int i2) {
        return make(i, i2);
    }
}
