package com.bytedance.sdk.account.platform;

import com.bytedance.sdk.account.api.call.UserApiResponse;
import com.bytedance.sdk.account.platform.BaseAccountAdapter;
import com.bytedance.sdk.account.platform.base.AuthorizeCallback;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
interface IPlatformBindAdapter extends AuthorizeCallback {

    public interface BindController {
        void doNext();
    }

    void onBindError(BaseAccountAdapter.ErrorResponse errorResponse);

    void onBindExist(UserApiResponse userApiResponse, String str, String str2, String str3, BindController bindController);

    void onBindSuccess(UserApiResponse userApiResponse);
}
