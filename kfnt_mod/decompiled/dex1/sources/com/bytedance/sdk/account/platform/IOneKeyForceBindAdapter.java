package com.bytedance.sdk.account.platform;

import com.bytedance.sdk.account.api.call.MobileApiResponse;
import com.bytedance.sdk.account.mobile.query.OneForceBindMobileQueryObj;
import com.bytedance.sdk.account.platform.base.AuthorizeCallback;
import com.bytedance.sdk.account.platform.base.AuthorizeErrorResponse;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IOneKeyForceBindAdapter extends AuthorizeCallback {
    void onBindError(AuthorizeErrorResponse authorizeErrorResponse);

    void onBindSuccess(MobileApiResponse<OneForceBindMobileQueryObj> mobileApiResponse);
}
