package com.kwad.v8.debug.mirror;

import com.kwad.v8.V8Object;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class UndefinedMirror extends ValueMirror {
    UndefinedMirror(V8Object v8Object) {
        super(v8Object);
    }

    @Override // com.kwad.v8.debug.mirror.Mirror
    public boolean isUndefined() {
        return true;
    }

    @Override // com.kwad.v8.debug.mirror.Mirror
    public String toString() {
        return "undefined";
    }
}
