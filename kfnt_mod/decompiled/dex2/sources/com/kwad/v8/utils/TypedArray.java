package com.kwad.v8.utils;

import com.kwad.v8.V8;
import com.kwad.v8.V8ArrayBuffer;
import com.kwad.v8.V8TypedArray;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class TypedArray {
    private V8TypedArray typedArray;

    public TypedArray(V8 v8, ArrayBuffer arrayBuffer, int i, int i2, int i3) {
        V8ArrayBuffer v8ArrayBuffer = arrayBuffer.getV8ArrayBuffer();
        V8TypedArray v8TypedArray = new V8TypedArray(v8, v8ArrayBuffer, i, i2, i3);
        try {
            this.typedArray = (V8TypedArray) v8TypedArray.twin().setWeak();
        } finally {
            v8ArrayBuffer.close();
            v8TypedArray.close();
        }
    }

    TypedArray(V8TypedArray v8TypedArray) {
        this.typedArray = (V8TypedArray) v8TypedArray.twin().setWeak();
    }

    public V8TypedArray getV8TypedArray() {
        return (V8TypedArray) this.typedArray.twin();
    }

    public boolean isAvailable() {
        return !this.typedArray.isReleased();
    }
}
