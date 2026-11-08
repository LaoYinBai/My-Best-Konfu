package com.facebook.stetho.websocket;

import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
interface WriteCallback {
    void onFailure(IOException iOException);

    void onSuccess();
}
