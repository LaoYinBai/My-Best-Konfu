package com.bytedance.sdk.account.platform;

import android.content.Context;
import android.os.Bundle;
import com.bytedance.sdk.account.CommonCallBack;
import com.bytedance.sdk.account.api.call.UserApiResponse;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import com.bytedance.sdk.account.platform.BaseAccountAdapter;
import com.bytedance.sdk.account.platform.IPlatformBindAdapter;
import com.bytedance.sdk.account.platform.PlatformDelegate;
import com.bytedance.sdk.account.platform.base.AuthorizeErrorResponse;
import com.ss.android.account.UserBindCallback;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class PlatformBindAdapter extends BaseAccountAdapter implements IPlatformBindAdapter {
    boolean isBindMobileApi;
    private PlatformDelegate mBindDelegate;

    public PlatformBindAdapter(Context context, String str, String str2) {
        super(context, str, str2);
        this.isBindMobileApi = false;
    }

    public PlatformBindAdapter(Context context, String str, String str2, boolean z) {
        super(context, str, str2);
        this.isBindMobileApi = z;
    }

    @Override // com.bytedance.sdk.account.platform.base.AuthorizeCallback
    public void onSuccess(Bundle bundle) throws JSONException {
        AccountMonitorUtil.platformAuthEvent(this.platform, AccountMonitorConstants.Scene.SCENE_AUTH_BIND, 1, null, null, false, null);
        PlatformDelegate.IFactory iFactory = delegateMap.get(this.platform);
        if (iFactory != null) {
            this.mBindDelegate = iFactory.createBind(this);
            this.mBindDelegate.requestBind(bundle);
        }
    }

    @Override // com.bytedance.sdk.account.platform.base.AuthorizeCallback
    public void onError(AuthorizeErrorResponse authorizeErrorResponse) throws JSONException {
        AccountMonitorUtil.platformAuthEvent(this.platform, AccountMonitorConstants.Scene.SCENE_AUTH_BIND, 0, authorizeErrorResponse.platformErrorCode, authorizeErrorResponse.platformErrorMsg, authorizeErrorResponse.isCancel, null);
        onBindError(new BaseAccountAdapter.ErrorResponse(authorizeErrorResponse));
    }

    public void cancelBind() {
        if (this.mBindDelegate != null) {
            this.mBindDelegate.cancelBind();
            this.mBindDelegate = null;
        }
    }

    class BindCallback extends UserBindCallback {
        BindCallback() {
        }

        public void onBindExist(UserApiResponse userApiResponse, String str, String str2, final String str3) {
            PlatformBindAdapter.this.onBindExist(userApiResponse, str, str2, str3, new IPlatformBindAdapter.BindController() { // from class: com.bytedance.sdk.account.platform.PlatformBindAdapter.BindCallback.1
                @Override // com.bytedance.sdk.account.platform.IPlatformBindAdapter.BindController
                public void doNext() {
                    PlatformBindAdapter.this.api.ssoSwitchBindWithAuthToken(PlatformBindAdapter.this.platformId, PlatformBindAdapter.this.platform, str3, 0L, null, new CommonCallBack<UserApiResponse>() { // from class: com.bytedance.sdk.account.platform.PlatformBindAdapter.BindCallback.1.1
                        @Override // com.bytedance.sdk.account.CommonCallBack
                        public void onSuccess(UserApiResponse userApiResponse2) {
                            PlatformBindAdapter.this.onBindSuccess(userApiResponse2);
                        }

                        @Override // com.bytedance.sdk.account.CommonCallBack
                        public void onError(UserApiResponse userApiResponse2, int i) {
                            PlatformBindAdapter.this.onBindError(new BaseAccountAdapter.ErrorResponse(userApiResponse2));
                        }
                    });
                }
            });
        }

        public void onBindError(UserApiResponse userApiResponse) {
            PlatformBindAdapter.this.onBindError(new BaseAccountAdapter.ErrorResponse(userApiResponse));
        }

        public void onBindSuccess(UserApiResponse userApiResponse) {
            PlatformBindAdapter.this.onBindSuccess(userApiResponse);
        }
    }
}
