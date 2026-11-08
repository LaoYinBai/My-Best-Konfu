package com.kwad.jni;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import java.util.Iterator;
import java.util.Map;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MapIteratorHelper {

    @Keep
    private final Iterator<Map.Entry> mIterator;

    @Nullable
    @Keep
    private Object mKey;

    @Nullable
    @Keep
    private Object mValue;

    @Keep
    public MapIteratorHelper(Map map) {
        this.mIterator = map.entrySet().iterator();
    }

    @Keep
    boolean hasNext() {
        if (!this.mIterator.hasNext()) {
            this.mKey = null;
            this.mValue = null;
            return false;
        }
        Map.Entry next = this.mIterator.next();
        this.mKey = next.getKey();
        this.mValue = next.getValue();
        return true;
    }
}
