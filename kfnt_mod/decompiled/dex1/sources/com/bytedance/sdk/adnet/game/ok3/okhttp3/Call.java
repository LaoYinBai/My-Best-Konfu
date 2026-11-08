package com.bytedance.sdk.adnet.game.ok3.okhttp3;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface Call extends Cloneable {

    public interface Factory {
        Call newCall(Request request);
    }

    void cancel();

    Call clone();

    void enqueue(Callback callback);

    Response execute();

    boolean isCanceled();

    boolean isExecuted();

    Request request();
}
