package com.kwad.v8.debug.mirror;

import com.kwad.v8.V8Object;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ValueMirror extends Mirror {
    private static final String VALUE = "value";

    ValueMirror(V8Object v8Object) {
        super(v8Object);
    }

    public Object getValue() {
        return this.v8Object.executeFunction("value", null);
    }

    @Override // com.kwad.v8.debug.mirror.Mirror
    public boolean isValue() {
        return true;
    }
}
