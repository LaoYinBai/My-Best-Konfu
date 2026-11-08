package com.bytedance.sdk.account.api.call;

import com.bytedance.sdk.account.api.call.AbsApiCall;
import com.bytedance.sdk.account.api.call.BaseApiResponse;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface ApiTask<T extends AbsApiCall<U>, U extends BaseApiResponse> {
    void enqueue(T t);

    U execute();
}
