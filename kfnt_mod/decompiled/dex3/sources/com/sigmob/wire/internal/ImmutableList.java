package com.sigmob.wire.internal;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class ImmutableList<T> extends AbstractList<T> implements Serializable, RandomAccess {
    private final ArrayList<T> list;

    ImmutableList(List<T> list) {
        this.list = new ArrayList<>(list);
    }

    private Object writeReplace() {
        return Collections.unmodifiableList(this.list);
    }

    @Override // java.util.AbstractList, java.util.List
    public T get(int i) {
        return this.list.get(i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.list.size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public Object[] toArray() {
        return this.list.toArray();
    }
}
