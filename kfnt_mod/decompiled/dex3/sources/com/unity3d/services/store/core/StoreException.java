package com.unity3d.services.store.core;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class StoreException extends Exception {
    private int _resultCode;

    public StoreException() {
        super("Unknown store exception");
        this._resultCode = -1;
    }

    public StoreException(int i) {
        super("Store exception with result code " + i);
        this._resultCode = i;
    }

    public int getResultCode() {
        return this._resultCode;
    }
}
