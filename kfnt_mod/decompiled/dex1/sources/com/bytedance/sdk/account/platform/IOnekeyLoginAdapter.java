package com.bytedance.sdk.account.platform;

import com.bytedance.sdk.account.api.response.LoginByTicketResponse;
import com.bytedance.sdk.account.platform.base.AuthorizeErrorResponse;
import com.bytedance.sdk.account.platform.base.OneLoginCallback;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
interface IOnekeyLoginAdapter extends OneLoginCallback {
    void onLoginError(AuthorizeErrorResponse authorizeErrorResponse);

    void onLoginSuccess(LoginByTicketResponse loginByTicketResponse);
}
