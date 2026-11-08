package com.bytedance.sdk.adnet.game.ok3.okhttp3;

import java.net.Socket;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface Connection {
    Handshake handshake();

    Protocol protocol();

    Route route();

    Socket socket();
}
