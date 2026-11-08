package com.facebook.stetho.websocket;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface SimpleEndpoint {
    void onClose(SimpleSession simpleSession, int i, String str);

    void onError(SimpleSession simpleSession, Throwable th);

    void onMessage(SimpleSession simpleSession, String str);

    void onMessage(SimpleSession simpleSession, byte[] bArr, int i);

    void onOpen(SimpleSession simpleSession);
}
