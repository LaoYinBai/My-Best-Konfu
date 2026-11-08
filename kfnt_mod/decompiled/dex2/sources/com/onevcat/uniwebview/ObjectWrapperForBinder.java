package com.onevcat.uniwebview;

import android.os.Binder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ObjectWrapperForBinder extends Binder {
    private final Object mData;

    public ObjectWrapperForBinder(Object obj) {
        this.mData = obj;
    }

    public Object getData() {
        return this.mData;
    }
}
