package com.facebook.stetho.websocket;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface SimpleSession {
    void close(int i, String str);

    boolean isOpen();

    void sendBinary(byte[] bArr);

    void sendText(String str);
}
