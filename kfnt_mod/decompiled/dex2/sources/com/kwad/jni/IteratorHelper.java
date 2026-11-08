package com.kwad.jni;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import java.util.Iterator;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class IteratorHelper {

    @Nullable
    @Keep
    private Object mElement;
    private final Iterator mIterator;

    @Keep
    public IteratorHelper(Iterable iterable) {
        this.mIterator = iterable.iterator();
    }

    @Keep
    public IteratorHelper(Iterator it) {
        this.mIterator = it;
    }

    @Keep
    boolean hasNext() {
        if (this.mIterator.hasNext()) {
            this.mElement = this.mIterator.next();
            return true;
        }
        this.mElement = null;
        return false;
    }
}
