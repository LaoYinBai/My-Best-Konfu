package com.kwad.yoga;

import com.kwad.proguard.annotations.DoNotStrip;

@DoNotStrip
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public enum YogaWrap {
    NO_WRAP(0),
    WRAP(1),
    WRAP_REVERSE(2);

    private final int mIntValue;

    YogaWrap(int i) {
        this.mIntValue = i;
    }

    public static YogaWrap fromInt(int i) {
        switch (i) {
            case 0:
                return NO_WRAP;
            case 1:
                return WRAP;
            case 2:
                return WRAP_REVERSE;
            default:
                throw new IllegalArgumentException("Unknown enum value: " + i);
        }
    }

    public int intValue() {
        return this.mIntValue;
    }
}
