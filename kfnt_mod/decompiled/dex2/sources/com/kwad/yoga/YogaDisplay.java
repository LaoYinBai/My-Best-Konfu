package com.kwad.yoga;

import com.kwad.proguard.annotations.DoNotStrip;

@DoNotStrip
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public enum YogaDisplay {
    FLEX(0),
    NONE(1);

    private final int mIntValue;

    YogaDisplay(int i) {
        this.mIntValue = i;
    }

    public static YogaDisplay fromInt(int i) {
        switch (i) {
            case 0:
                return FLEX;
            case 1:
                return NONE;
            default:
                throw new IllegalArgumentException("Unknown enum value: " + i);
        }
    }

    public int intValue() {
        return this.mIntValue;
    }
}
