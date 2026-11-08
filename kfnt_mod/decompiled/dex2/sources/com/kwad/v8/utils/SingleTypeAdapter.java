package com.kwad.v8.utils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class SingleTypeAdapter implements TypeAdapter {
    private int typeToAdapt;

    public SingleTypeAdapter(int i) {
        this.typeToAdapt = i;
    }

    @Override // com.kwad.v8.utils.TypeAdapter
    public Object adapt(int i, Object obj) {
        return i == this.typeToAdapt ? adapt(obj) : TypeAdapter.DEFAULT;
    }

    public abstract Object adapt(Object obj);
}
