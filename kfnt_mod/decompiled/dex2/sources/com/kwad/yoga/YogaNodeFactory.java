package com.kwad.yoga;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class YogaNodeFactory {
    public static YogaNode create() {
        return new YogaNodeJNIFinalizer();
    }

    public static YogaNode create(YogaConfig yogaConfig) {
        return new YogaNodeJNIFinalizer(yogaConfig);
    }
}
