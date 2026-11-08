package com.bytedance.sdk.account.platform;

import android.os.Bundle;
import com.bytedance.sdk.account.platform.PlatformBindAdapter;
import com.bytedance.sdk.account.platform.PlatformBindAdapter.BindCallback;
import com.bytedance.sdk.account.platform.PlatformDelegate;
import com.bytedance.sdk.account.platform.PlatformLoginAdapter;
import com.bytedance.sdk.account.platform.PlatformLoginAdapter.LoginCallback;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class DouyinPlatformDelegate extends PlatformDelegate {
    private String authCode;
    private Bundle extras;
    private String grantedPermission;
    private boolean isBindMobileApi;
    private boolean isChangeBind;
    private boolean isNeedMobile;
    private PlatformBindAdapter.BindCallback mBindCallback;
    private PlatformLoginAdapter.LoginCallback mLoginCallback;
    private String state;

    DouyinPlatformDelegate(PlatformBindAdapter platformBindAdapter) {
        super(platformBindAdapter);
        this.isBindMobileApi = platformBindAdapter.isBindMobileApi;
    }

    DouyinPlatformDelegate(PlatformLoginAdapter platformLoginAdapter) {
        super(platformLoginAdapter);
        this.isBindMobileApi = platformLoginAdapter.isBindMobileApi;
        this.isNeedMobile = platformLoginAdapter.isNeedMobile;
        this.isChangeBind = platformLoginAdapter.isChangeBind;
    }

    private void parseData(Bundle bundle) {
        this.authCode = bundle.getString("auth_code");
        this.state = bundle.getString("state");
        this.grantedPermission = bundle.getString("granted_permission");
        this.extras = bundle.getBundle("extras");
    }

    @Override // com.bytedance.sdk.account.platform.PlatformDelegate
    void requestLogin(Bundle bundle) {
        if (this.loginDelegate != null) {
            parseData(bundle);
            PlatformLoginAdapter platformLoginAdapter = this.loginDelegate;
            platformLoginAdapter.getClass();
            this.mLoginCallback = platformLoginAdapter.new LoginCallback();
            if (this.isBindMobileApi) {
                this.loginDelegate.api.ssoWithAuthCodeBindMobileLogin(this.loginDelegate.platformId, this.loginDelegate.platform, this.authCode, null, this.isNeedMobile, this.isChangeBind, null, this.mLoginCallback);
            } else {
                this.loginDelegate.api.ssoWithAuthCodeLogin(this.loginDelegate.platformId, this.loginDelegate.platform, this.authCode, 0L, null, this.mLoginCallback);
            }
        }
    }

    @Override // com.bytedance.sdk.account.platform.PlatformDelegate
    void requestBind(Bundle bundle) {
        if (this.bindDelegate != null) {
            parseData(bundle);
            PlatformBindAdapter platformBindAdapter = this.bindDelegate;
            platformBindAdapter.getClass();
            this.mBindCallback = platformBindAdapter.new BindCallback();
            if (this.isBindMobileApi) {
                this.bindDelegate.api.bindWithMobile(this.bindDelegate.platformId, this.bindDelegate.platform, this.authCode, null, null, null, null, 0L, null, null, null, this.mBindCallback);
            } else {
                this.bindDelegate.api.ssoWithAuthCodeBind(this.bindDelegate.platformId, this.bindDelegate.platform, this.authCode, 0L, null, this.mBindCallback);
            }
        }
    }

    @Override // com.bytedance.sdk.account.platform.PlatformDelegate
    void cancelLogin() {
        if (this.mLoginCallback != null) {
            this.mLoginCallback.cancel();
            this.mLoginCallback = null;
        }
        this.loginDelegate = null;
    }

    @Override // com.bytedance.sdk.account.platform.PlatformDelegate
    void cancelBind() {
        if (this.mBindCallback != null) {
            this.mBindCallback.cancel();
            this.mBindCallback = null;
        }
        this.bindDelegate = null;
    }

    static class Factory implements PlatformDelegate.IFactory {
        Factory() {
        }

        @Override // com.bytedance.sdk.account.platform.PlatformDelegate.IFactory
        public PlatformDelegate createLogin(PlatformLoginAdapter platformLoginAdapter) {
            return new DouyinPlatformDelegate(platformLoginAdapter);
        }

        @Override // com.bytedance.sdk.account.platform.PlatformDelegate.IFactory
        public PlatformDelegate createBind(PlatformBindAdapter platformBindAdapter) {
            return new DouyinPlatformDelegate(platformBindAdapter);
        }
    }
}
