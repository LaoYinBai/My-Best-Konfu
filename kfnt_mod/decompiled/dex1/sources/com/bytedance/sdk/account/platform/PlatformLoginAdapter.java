package com.bytedance.sdk.account.platform;

import android.content.Context;
import android.os.Bundle;
import com.bytedance.sdk.account.CommonCallBack;
import com.bytedance.sdk.account.api.call.UserApiResponse;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import com.bytedance.sdk.account.platform.BaseAccountAdapter;
import com.bytedance.sdk.account.platform.PlatformDelegate;
import com.bytedance.sdk.account.platform.base.AuthorizeErrorResponse;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class PlatformLoginAdapter extends BaseAccountAdapter implements IPlatformLoginAdapter {
    boolean isBindMobileApi;
    boolean isChangeBind;
    boolean isNeedMobile;
    private PlatformDelegate mPlatformDelegate;

    public PlatformLoginAdapter(Context context, String str, String str2) {
        super(context, str, str2);
        this.isBindMobileApi = false;
    }

    public PlatformLoginAdapter(Context context, String str, String str2, boolean z, boolean z2) {
        super(context, str, str2);
        this.isBindMobileApi = true;
        this.isNeedMobile = z;
        this.isChangeBind = z2;
    }

    @Override // com.bytedance.sdk.account.platform.base.AuthorizeCallback
    public final void onSuccess(Bundle bundle) throws JSONException {
        AccountMonitorUtil.platformAuthEvent(this.platform, "login", 1, null, null, false, null);
        PlatformDelegate.IFactory iFactory = delegateMap.get(this.platform);
        if (iFactory != null) {
            this.mPlatformDelegate = iFactory.createLogin(this);
            this.mPlatformDelegate.requestLogin(bundle);
        }
    }

    @Override // com.bytedance.sdk.account.platform.base.AuthorizeCallback
    public final void onError(AuthorizeErrorResponse authorizeErrorResponse) throws JSONException {
        AccountMonitorUtil.platformAuthEvent(this.platform, "login", 0, authorizeErrorResponse.platformErrorCode, authorizeErrorResponse.platformErrorMsg, authorizeErrorResponse.isCancel, null);
        onLoginError(new BaseAccountAdapter.ErrorResponse(authorizeErrorResponse));
    }

    public void cancelLogin() {
        if (this.mPlatformDelegate != null) {
            this.mPlatformDelegate.cancelLogin();
            this.mPlatformDelegate = null;
        }
    }

    protected class LoginCallback extends CommonCallBack<UserApiResponse> {
        protected LoginCallback() {
        }

        @Override // com.bytedance.sdk.account.CommonCallBack
        public void onSuccess(UserApiResponse userApiResponse) {
            PlatformLoginAdapter.this.onLoginSuccess(userApiResponse);
        }

        @Override // com.bytedance.sdk.account.CommonCallBack
        public void onError(UserApiResponse userApiResponse, int i) {
            PlatformLoginAdapter.this.onLoginError(new BaseAccountAdapter.ErrorResponse(userApiResponse));
        }
    }
}
