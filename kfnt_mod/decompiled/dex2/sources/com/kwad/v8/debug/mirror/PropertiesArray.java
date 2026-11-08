package com.kwad.v8.debug.mirror;

import com.kwad.v8.Releasable;
import com.kwad.v8.V8Array;
import com.kwad.v8.V8Object;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class PropertiesArray implements Releasable {
    private V8Array v8Array;

    PropertiesArray(V8Array v8Array) {
        this.v8Array = v8Array.twin();
    }

    @Override // com.kwad.v8.Releasable, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.v8Array.isReleased()) {
            return;
        }
        this.v8Array.close();
    }

    public PropertyMirror getProperty(int i) {
        V8Object object = this.v8Array.getObject(i);
        try {
            return new PropertyMirror(object);
        } finally {
            object.close();
        }
    }

    public int length() {
        return this.v8Array.length();
    }

    @Override // com.kwad.v8.Releasable
    @Deprecated
    public void release() {
        close();
    }
}
