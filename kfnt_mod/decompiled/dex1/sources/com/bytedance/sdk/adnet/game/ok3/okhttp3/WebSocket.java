package com.bytedance.sdk.adnet.game.ok3.okhttp3;

import com.bytedance.sdk.adnet.game.ok3.okio.ByteString;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface WebSocket {

    public interface Factory {
        WebSocket newWebSocket(Request request, WebSocketListener webSocketListener);
    }

    void cancel();

    boolean close(int i, String str);

    long queueSize();

    Request request();

    boolean send(ByteString byteString);

    boolean send(String str);
}
