package com.sigmob.wire;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class Wire {
    private Wire() {
    }

    public static <T> T get(T t, T t2) {
        return t != null ? t : t2;
    }
}
