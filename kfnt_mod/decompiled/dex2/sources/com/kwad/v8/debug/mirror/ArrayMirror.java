package com.kwad.v8.debug.mirror;

import com.kwad.v8.V8Object;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ArrayMirror extends ObjectMirror {
    private static final String LENGTH = "length";

    ArrayMirror(V8Object v8Object) {
        super(v8Object);
    }

    @Override // com.kwad.v8.debug.mirror.Mirror
    public boolean isArray() {
        return true;
    }

    public int length() {
        return this.v8Object.executeIntegerFunction(LENGTH, null);
    }
}
