package com.facebook.stetho.inspector.network;

import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface ResponseHandler {
    void onEOF();

    void onError(IOException iOException);

    void onRead(int i);

    void onReadDecoded(int i);
}
