package com.bytedance.sdk.account.execute;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ApiResponse {
    public String mDetailErrorMsg;
    public int mError = 0;
    public String mErrorMsg;
    public String mRequestUrl;
    public ApiRequest request;

    public ApiResponse(ApiRequest apiRequest) {
        this.request = apiRequest;
    }
}
