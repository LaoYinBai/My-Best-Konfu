package com.kwad.v8.debug.mirror;

import com.kwad.v8.V8Object;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class NullMirror extends ValueMirror {
    NullMirror(V8Object v8Object) {
        super(v8Object);
    }

    @Override // com.kwad.v8.debug.mirror.Mirror
    public boolean isNull() {
        return true;
    }

    @Override // com.kwad.v8.debug.mirror.Mirror
    public String toString() {
        return "null";
    }
}
