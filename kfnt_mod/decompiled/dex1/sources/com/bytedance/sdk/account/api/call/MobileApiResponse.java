package com.bytedance.sdk.account.api.call;

import com.bytedance.sdk.account.mobile.query.MobileQueryObj;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class MobileApiResponse<T extends MobileQueryObj> extends BaseApiResponse {
    public T mobileObj;

    public MobileApiResponse(boolean z, int i, T t) {
        super(z, i);
        this.mobileObj = t;
        this.error = t.mError;
        this.errorMsg = t.mErrorMsg;
    }
}
