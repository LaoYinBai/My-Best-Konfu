package com.bytedance.sdk.account.platform;

import com.bytedance.sdk.account.api.call.UserApiResponse;
import com.bytedance.sdk.account.platform.BaseAccountAdapter;
import com.bytedance.sdk.account.platform.base.AuthorizeCallback;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
interface IPlatformLoginAdapter extends AuthorizeCallback {
    void onLoginError(BaseAccountAdapter.ErrorResponse errorResponse);

    void onLoginSuccess(UserApiResponse userApiResponse);
}
