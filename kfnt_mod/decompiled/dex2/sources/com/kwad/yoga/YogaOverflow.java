package com.kwad.yoga;

import com.kwad.proguard.annotations.DoNotStrip;

@DoNotStrip
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public enum YogaOverflow {
    VISIBLE(0),
    HIDDEN(1),
    SCROLL(2);

    private final int mIntValue;

    YogaOverflow(int i) {
        this.mIntValue = i;
    }

    public static YogaOverflow fromInt(int i) {
        switch (i) {
            case 0:
                return VISIBLE;
            case 1:
                return HIDDEN;
            case 2:
                return SCROLL;
            default:
                throw new IllegalArgumentException("Unknown enum value: " + i);
        }
    }

    public int intValue() {
        return this.mIntValue;
    }
}
