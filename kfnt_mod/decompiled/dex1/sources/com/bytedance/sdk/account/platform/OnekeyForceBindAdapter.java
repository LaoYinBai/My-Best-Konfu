package com.bytedance.sdk.account.platform;

import android.content.Context;
import android.os.Bundle;
import com.bytedance.sdk.account.api.IBDAccountAPI;
import com.bytedance.sdk.account.api.call.MobileApiResponse;
import com.bytedance.sdk.account.impl.BDAccountDelegate;
import com.bytedance.sdk.account.mobile.query.OneForceBindMobileQueryObj;
import com.bytedance.sdk.account.mobile.thread.call.OneForceBindMobileCallback;
import com.bytedance.sdk.account.platform.api.IOnekeyLoginService;
import com.bytedance.sdk.account.platform.base.AuthorizeErrorResponse;
import com.bytedance.sdk.account.platform.base.OnekeyLoginErrorResponse;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class OnekeyForceBindAdapter implements IOneKeyForceBindAdapter {
    private static final int BIND_LOGIC_TYPE = 1;
    protected IBDAccountAPI accountAPI;
    private volatile boolean isCancel = false;
    private OneForceBindMobileCallback mCallback;
    private Context mContext;
    private String mFrom;
    private String mProfileKey;
    private String mToken;

    public OnekeyForceBindAdapter(Context context, String str) {
        this.mContext = context;
        this.accountAPI = BDAccountDelegate.createBDAccountApi(context);
        this.mProfileKey = str;
    }

    @Override // com.bytedance.sdk.account.platform.base.AuthorizeCallback
    public void onSuccess(Bundle bundle) {
        if (this.isCancel) {
            return;
        }
        parseData(bundle);
        this.mCallback = new OneForceBindMobileCallback() { // from class: com.bytedance.sdk.account.platform.OnekeyForceBindAdapter.1
            @Override // com.bytedance.sdk.account.NeedCaptchaCallBack, com.bytedance.sdk.account.CommonCallBack
            public void onSuccess(MobileApiResponse<OneForceBindMobileQueryObj> mobileApiResponse) {
                OnekeyForceBindAdapter.this.onBindSuccess(mobileApiResponse);
            }

            @Override // com.bytedance.sdk.account.NeedCaptchaCallBack, com.bytedance.sdk.account.CommonCallBack
            public void onError(MobileApiResponse<OneForceBindMobileQueryObj> mobileApiResponse, int i) {
                OnekeyLoginErrorResponse onekeyLoginErrorResponse = new OnekeyLoginErrorResponse();
                onekeyLoginErrorResponse.netType = OnekeyForceBindAdapter.this.mFrom;
                onekeyLoginErrorResponse.errorType = 4;
                onekeyLoginErrorResponse.platformErrorCode = String.valueOf(mobileApiResponse.error);
                onekeyLoginErrorResponse.platformErrorMsg = mobileApiResponse.errorMsg;
                if (mobileApiResponse.mobileObj != 0) {
                    if (((OneForceBindMobileQueryObj) mobileApiResponse.mobileObj).mResultCode != null) {
                        onekeyLoginErrorResponse.resultCode = ((OneForceBindMobileQueryObj) mobileApiResponse.mobileObj).mResultCode;
                    }
                    if (((OneForceBindMobileQueryObj) mobileApiResponse.mobileObj).jsonResult != null) {
                        onekeyLoginErrorResponse.rawResult = ((OneForceBindMobileQueryObj) mobileApiResponse.mobileObj).jsonResult.optJSONObject("data");
                    }
                }
                OnekeyForceBindAdapter.this.onBindError(onekeyLoginErrorResponse);
            }
        };
        this.accountAPI.oneForceBindLogin(this.mToken, this.mFrom, this.mProfileKey, 1, this.mCallback);
    }

    @Override // com.bytedance.sdk.account.platform.base.AuthorizeCallback
    public void onError(AuthorizeErrorResponse authorizeErrorResponse) {
        if (this.isCancel) {
            return;
        }
        onBindError(authorizeErrorResponse);
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
