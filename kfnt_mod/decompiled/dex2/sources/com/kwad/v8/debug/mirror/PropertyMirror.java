package com.kwad.v8.debug.mirror;

import com.kwad.v8.V8Object;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class PropertyMirror extends Mirror {
    PropertyMirror(V8Object v8Object) {
        super(v8Object);
    }

    public String getName() {
        return this.v8Object.executeStringFunction("name", null);
    }

    public Mirror getValue() {
        V8Object v8ObjectExecuteObjectFunction = this.v8Object.executeObjectFunction("value", null);
        try {
            return createMirror(v8ObjectExecuteObjectFunction);
        } finally {
            v8ObjectExecuteObjectFunction.close();
        }
    }

    @Override // com.kwad.v8.debug.mirror.Mirror
    public boolean isProperty() {
        return true;
    }
}
