package com.bytedance.sdk.account.platform;

import android.os.Bundle;
import com.bytedance.sdk.account.api.IBDAccountPlatformAPI;
import com.bytedance.sdk.account.platform.PlatformBindAdapter;
import com.bytedance.sdk.account.platform.PlatformBindAdapter.BindCallback;
import com.bytedance.sdk.account.platform.PlatformDelegate;
import com.bytedance.sdk.account.platform.PlatformLoginAdapter;
import com.bytedance.sdk.account.platform.PlatformLoginAdapter.LoginCallback;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class LinePlatformDelegate extends PlatformDelegate {
    private String accessToken;
    private PlatformBindAdapter.BindCallback mBindCallback;
    private PlatformLoginAdapter.LoginCallback mLoginCallback;

    LinePlatformDelegate(PlatformBindAdapter platformBindAdapter) {
        super(platformBindAdapter);
    }

    LinePlatformDelegate(PlatformLoginAdapter platformLoginAdapter) {
        super(platformLoginAdapter);
    }

    private void parseData(Bundle bundle) {
        this.accessToken = bundle.getString("access_token");
    }

    @Override // com.bytedance.sdk.account.platform.PlatformDelegate
    void requestLogin(Bundle bundle) {
        if (this.loginDelegate != null) {
            parseData(bundle);
            PlatformLoginAdapter platformLoginAdapter = this.loginDelegate;
            platformLoginAdapter.getClass();
            this.mLoginCallback = platformLoginAdapter.new LoginCallback();
            this.loginDelegate.api.ssoWithAccessTokenLogin(this.loginDelegate.platformId, this.loginDelegate.platform, this.accessToken, 0L, null, this.mLoginCallback);
        }
    }

    @Override // com.bytedance.sdk.account.platform.PlatformDelegate
    void requestBind(Bundle bundle) {
        if (this.bindDelegate != null) {
            parseData(bundle);
            PlatformBindAdapter platformBindAdapter = this.bindDelegate;
            platformBindAdapter.getClass();
            this.mBindCallback = platformBindAdapter.new BindCallback();
            IBDAccountPlatformAPI iBDAccountPlatformAPI = this.bindDelegate.api;
            String str = this.bindDelegate.platformId;
            String str2 = this.bindDelegate.platform;
            String str3 = this.accessToken;
            PlatformBindAdapter platformBindAdapter2 = this.bindDelegate;
            platformBindAdapter2.getClass();
            iBDAccountPlatformAPI.ssoWithAccessTokenBind(str, str2, str3, 0L, null, platformBindAdapter2.new BindCallback());
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
            return new LinePlatformDelegate(platformLoginAdapter);
        }

        @Override // com.bytedance.sdk.account.platform.PlatformDelegate.IFactory
        public PlatformDelegate createBind(PlatformBindAdapter platformBindAdapter) {
            return new LinePlatformDelegate(platformBindAdapter);
        }
    }
}
