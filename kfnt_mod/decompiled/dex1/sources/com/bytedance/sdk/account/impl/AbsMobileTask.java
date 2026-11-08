package com.bytedance.sdk.account.impl;

import com.bytedance.sdk.account.api.call.AbsApiCall;
import com.bytedance.sdk.account.api.call.ApiTask;
import com.bytedance.sdk.account.api.call.BaseApiResponse;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class AbsMobileTask<T extends AbsApiCall<U>, U extends BaseApiResponse> implements ApiTask<T, U> {
    private T mCall;

    protected abstract void realEnqueue(T t);

    @Override // com.bytedance.sdk.account.api.call.ApiTask
    public void enqueue(T t) {
        this.mCall = t;
        realEnqueue(this.mCall);
    }
}
