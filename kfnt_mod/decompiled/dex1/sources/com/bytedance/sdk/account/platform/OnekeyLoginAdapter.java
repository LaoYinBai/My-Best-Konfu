package com.bytedance.sdk.account.platform;

import android.content.Context;
import android.os.Bundle;
import com.bytedance.sdk.account.api.IBDAccountAPI;
import com.bytedance.sdk.account.api.callback.LoginByTicketCallback;
import com.bytedance.sdk.account.api.response.LoginByTicketResponse;
import com.bytedance.sdk.account.impl.BDAccountDelegate;
import com.bytedance.sdk.account.platform.api.IOnekeyLoginService;
import com.bytedance.sdk.account.platform.base.AuthorizeErrorResponse;
import com.bytedance.sdk.account.platform.base.OnekeyLoginErrorResponse;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class OnekeyLoginAdapter implements IOnekeyLoginAdapter {
    protected IBDAccountAPI accountAPI;
    private volatile boolean isCancel = false;
    private LoginByTicketCallback mCallback;
    private Context mContext;
    private String mFrom;
    private String mToken;

    @Override // com.bytedance.sdk.account.platform.base.OneLoginCallback
    public JSONObject onSendEvent() {
        return null;
    }

    public OnekeyLoginAdapter(Context context) {
        this.mContext = context.getApplicationContext();
        this.accountAPI = BDAccountDelegate.createBDAccountApi(this.mContext);
    }

    @Override // com.bytedance.sdk.account.platform.base.AuthorizeCallback
    public final void onSuccess(Bundle bundle) {
        if (this.isCancel) {
            return;
        }
        parseData(bundle);
        this.mCallback = new LoginByTicketCallback() { // from class: com.bytedance.sdk.account.platform.OnekeyLoginAdapter.1
            @Override // com.bytedance.sdk.account.CommonCallBack
            public void onSuccess(LoginByTicketResponse loginByTicketResponse) {
                OnekeyLoginAdapter.this.onLoginSuccess(loginByTicketResponse);
            }

            @Override // com.bytedance.sdk.account.CommonCallBack
            public void onError(LoginByTicketResponse loginByTicketResponse, int i) {
                OnekeyLoginErrorResponse onekeyLoginErrorResponse = new OnekeyLoginErrorResponse();
                onekeyLoginErrorResponse.netType = OnekeyLoginAdapter.this.mFrom;
                onekeyLoginErrorResponse.errorType = 4;
                onekeyLoginErrorResponse.platformErrorCode = String.valueOf(loginByTicketResponse.error);
                onekeyLoginErrorResponse.platformErrorMsg = loginByTicketResponse.errorMsg;
                onekeyLoginErrorResponse.rawResult = loginByTicketResponse.rawData;
                if (loginByTicketResponse.error == 1075) {
                    onekeyLoginErrorResponse.mCancelApplyTime = loginByTicketResponse.mCancelApplyTime;
                    onekeyLoginErrorResponse.mCancelAvatarUrl = loginByTicketResponse.mCancelAvatarUrl;
                    onekeyLoginErrorResponse.mCancelNickName = loginByTicketResponse.mCancelNickName;
                    onekeyLoginErrorResponse.mCancelTime = loginByTicketResponse.mCancelTime;
                    onekeyLoginErrorResponse.mCancelToken = loginByTicketResponse.mCancelToken;
                }
                OnekeyLoginAdapter.this.onLoginError(onekeyLoginErrorResponse);
            }
        };
        this.accountAPI.quickAuthlogin(this.mToken, this.mFrom, this.mCallback);
    }

    @Override // com.bytedance.sdk.account.platform.base.AuthorizeCallback
    public final void onError(AuthorizeErrorResponse authorizeErrorResponse) {
        if (this.isCancel) {
            return;
        }
        onLoginError(authorizeErrorResponse);
    }

    public void cancel() {
        this.isCancel = true;
        if (this.mCallback != null) {
            this.mCallback.cancel();
        }
    }

    private void parseData(Bundle bundle) {
        this.mToken = bundle.getString("access_token");
        this.mFrom = bundle.getString(IOnekeyLoginService.ResponseConstants.NET_TYPE);
    }
}
