package com.kwad.yoga;

import com.kwad.proguard.annotations.DoNotStrip;

@DoNotStrip
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public enum YogaNodeType {
    DEFAULT(0),
    TEXT(1);

    private final int mIntValue;

    YogaNodeType(int i) {
        this.mIntValue = i;
    }

    public static YogaNodeType fromInt(int i) {
        switch (i) {
            case 0:
                return DEFAULT;
            case 1:
                return TEXT;
            default:
                throw new IllegalArgumentException("Unknown enum value: " + i);
        }
    }

    public int intValue() {
        return this.mIntValue;
    }
}
