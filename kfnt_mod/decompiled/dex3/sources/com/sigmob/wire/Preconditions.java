package com.sigmob.wire;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class Preconditions {
    private Preconditions() {
    }

    static void checkNotNull(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
    }
}
