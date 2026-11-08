package com.kwad.yoga;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class YogaConstants {
    public static final float UNDEFINED = Float.NaN;

    public static float getUndefined() {
        return Float.NaN;
    }

    public static boolean isUndefined(float f) {
        return Float.compare(f, Float.NaN) == 0;
    }

    public static boolean isUndefined(YogaValue yogaValue) {
        return yogaValue.unit == YogaUnit.UNDEFINED;
    }
}
