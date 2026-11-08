package com.bytedance.sdk.account.platform.onekey;

import com.bytedance.sdk.account.platform.base.AuthorizeCallback;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class OnekeyResponseCallable<T> {
    public AuthorizeCallback mCallback;
    public final T response;

    public OnekeyResponseCallable(AuthorizeCallback authorizeCallback, T t) {
        this.mCallback = authorizeCallback;
        this.response = t;
    }
}
