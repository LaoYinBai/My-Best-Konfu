package com.kwad.yoga;

import com.kwad.proguard.annotations.DoNotStrip;

@DoNotStrip
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public enum YogaPositionType {
    RELATIVE(0),
    ABSOLUTE(1);

    private final int mIntValue;

    YogaPositionType(int i) {
        this.mIntValue = i;
    }

    public static YogaPositionType fromInt(int i) {
        switch (i) {
            case 0:
                return RELATIVE;
            case 1:
                return ABSOLUTE;
            default:
                throw new IllegalArgumentException("Unknown enum value: " + i);
        }
    }

    public int intValue() {
        return this.mIntValue;
    }
}
