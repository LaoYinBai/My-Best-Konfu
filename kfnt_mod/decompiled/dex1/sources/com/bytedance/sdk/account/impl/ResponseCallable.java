package com.bytedance.sdk.account.impl;

import com.bytedance.sdk.account.api.call.AbsApiCall;
import com.bytedance.sdk.account.api.call.BaseApiResponse;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ResponseCallable<T extends BaseApiResponse> {
    public final AbsApiCall apiCall;
    public final T response;

    public ResponseCallable(AbsApiCall absApiCall, T t) {
        this.apiCall = absApiCall;
        this.response = t;
    }
}
