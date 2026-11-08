package com.bytedance.sdk.account.platform;

import android.content.Context;
import android.os.Bundle;
import com.bytedance.sdk.account.api.IBDAccountAPI;
import com.bytedance.sdk.account.api.call.MobileApiResponse;
import com.bytedance.sdk.account.impl.BDAccountDelegate;
import com.bytedance.sdk.account.mobile.query.OneBindMobileQueryObj;
import com.bytedance.sdk.account.mobile.thread.call.OneBindMobileCallback;
import com.bytedance.sdk.account.platform.api.IOnekeyLoginService;
import com.bytedance.sdk.account.platform.base.AuthorizeErrorResponse;
import com.bytedance.sdk.account.platform.base.OnekeyLoginErrorResponse;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class OnekeyBindAdapter implements IOnekeyBindAdapter {
    protected IBDAccountAPI accountAPI;
    private OneBindMobileCallback mCallback;
    private Context mContext;
    private Map mExtendInfo;
    private String mFrom;
    private String mToken;
    private volatile boolean isCancel = false;
    private String mPassWord = "";
    private int mUnbindExits = 0;

    @Override // com.bytedance.sdk.account.platform.base.OneLoginCallback
    public JSONObject onSendEvent() {
        return null;
    }

    public OnekeyBindAdapter(Context context) {
        this.mContext = context.getApplicationContext();
        this.accountAPI = BDAccountDelegate.createBDAccountApi(this.mContext);
    }

    public void setExtraParams(String str, int i, Map map) {
        this.mPassWord = str;
        this.mUnbindExits = i;
        this.mExtendInfo = map;
    }

    @Override // com.bytedance.sdk.account.platform.base.AuthorizeCallback
    public final void onSuccess(Bundle bundle) {
        if (this.isCancel) {
            return;
        }
        parseData(bundle);
        this.mCallback = new OneBindMobileCallback() { // from class: com.bytedance.sdk.account.platform.OnekeyBindAdapter.1
            @Override // com.bytedance.sdk.account.NeedCaptchaCallBack, com.bytedance.sdk.account.CommonCallBack
            public void onSuccess(MobileApiResponse<OneBindMobileQueryObj> mobileApiResponse) {
                OnekeyBindAdapter.this.onBindSuccess(mobileApiResponse);
            }

            @Override // com.bytedance.sdk.account.NeedCaptchaCallBack, com.bytedance.sdk.account.CommonCallBack
            public void onError(MobileApiResponse<OneBindMobileQueryObj> mobileApiResponse, int i) {
                OnekeyLoginErrorResponse onekeyLoginErrorResponse = new OnekeyLoginErrorResponse();
                onekeyLoginErrorResponse.netType = OnekeyBindAdapter.this.mFrom;
                onekeyLoginErrorResponse.errorType = 4;
                onekeyLoginErrorResponse.platformErrorCode = String.valueOf(mobileApiResponse.error);
                onekeyLoginErrorResponse.platformErrorMsg = mobileApiResponse.errorMsg;
                if (mobileApiResponse.mobileObj != 0) {
                    if (((OneBindMobileQueryObj) mobileApiResponse.mobileObj).jsonResult != null) {
                        onekeyLoginErrorResponse.rawResult = ((OneBindMobileQueryObj) mobileApiResponse.mobileObj).jsonResult.optJSONObject("data");
                    }
                    if (mobileApiResponse.error == 1057) {
                        onekeyLoginErrorResponse.mConfirmSwitchBindTips = ((OneBindMobileQueryObj) mobileApiResponse.mobileObj).mConfirmSwitchBindTips;
                        onekeyLoginErrorResponse.mConfirmSwitchBindUrl = ((OneBindMobileQueryObj) mobileApiResponse.mobileObj).mConfirmSwitchBindUrl;
                    }
                }
                OnekeyBindAdapter.this.onBindError(onekeyLoginErrorResponse);
            }
        };
        this.accountAPI.oneBindMobile(this.mToken, this.mFrom, this.mPassWord, this.mUnbindExits, this.mExtendInfo, this.mCallback);
    }

    @Override // com.bytedance.sdk.account.platform.base.AuthorizeCallback
    public final void onError(AuthorizeErrorResponse authorizeErrorResponse) {
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
