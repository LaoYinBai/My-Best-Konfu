package com.bytedance.sdk.account.platform;

import android.os.Bundle;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import com.bytedance.sdk.account.platform.base.AuthorizeCallback;
import com.bytedance.sdk.account.platform.base.AuthorizeErrorResponse;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class AuthorizeAdapter implements AuthorizeCallback {
    private String platform;

    public abstract void onAuthError(AuthorizeErrorResponse authorizeErrorResponse);

    public abstract void onAuthSuccess(Bundle bundle);

    public AuthorizeAdapter(String str) {
        this.platform = str;
    }

    @Override // com.bytedance.sdk.account.platform.base.AuthorizeCallback
    public void onSuccess(Bundle bundle) throws JSONException {
        AccountMonitorUtil.platformAuthEvent(this.platform, "auth", 1, null, null, false, null);
        onAuthSuccess(bundle);
    }

    @Override // com.bytedance.sdk.account.platform.base.AuthorizeCallback
    public void onError(AuthorizeErrorResponse authorizeErrorResponse) throws JSONException {
        AccountMonitorUtil.platformAuthEvent(this.platform, "auth", 0, authorizeErrorResponse.platformErrorCode, authorizeErrorResponse.platformErrorMsg, authorizeErrorResponse.isCancel, null);
        onAuthError(authorizeErrorResponse);
    }
}
