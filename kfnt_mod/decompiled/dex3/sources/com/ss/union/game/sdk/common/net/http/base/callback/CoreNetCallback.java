package com.ss.union.game.sdk.common.net.http.base.callback;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface CoreNetCallback<T> {
    void onError(int i, String str);

    void onSuccess(T t);
}
