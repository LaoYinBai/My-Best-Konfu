package com.bytedance.sdk.account.platform;

import android.os.Bundle;
import com.bytedance.sdk.account.platform.PlatformBindAdapter;
import com.bytedance.sdk.account.platform.PlatformBindAdapter.BindCallback;
import com.bytedance.sdk.account.platform.PlatformDelegate;
import com.bytedance.sdk.account.platform.PlatformLoginAdapter;
import com.bytedance.sdk.account.platform.PlatformLoginAdapter.LoginCallback;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class FacebookPlatformDelegate extends PlatformDelegate {
    private String accessToken;
    private long expiresIn;
    private PlatformBindAdapter.BindCallback mBindCallback;
    private PlatformLoginAdapter.LoginCallback mLoginCallback;
    private String userId;

    FacebookPlatformDelegate(PlatformBindAdapter platformBindAdapter) {
        super(platformBindAdapter);
    }

    FacebookPlatformDelegate(PlatformLoginAdapter platformLoginAdapter) {
        super(platformLoginAdapter);
    }

    private void parseData(Bundle bundle) {
        this.accessToken = bundle.getString("access_token");
        this.userId = bundle.getString("user_id");
        this.expiresIn = bundle.getLong("expires_in");
    }

    @Override // com.bytedance.sdk.account.platform.PlatformDelegate
    void requestLogin(Bundle bundle) {
        if (this.loginDelegate != null) {
            parseData(bundle);
            PlatformLoginAdapter platformLoginAdapter = this.loginDelegate;
            platformLoginAdapter.getClass();
            this.mLoginCallback = platformLoginAdapter.new LoginCallback();
            this.loginDelegate.api.ssoWithAccessTokenLogin(this.loginDelegate.platformId, this.loginDelegate.platform, this.accessToken, this.expiresIn, null, this.mLoginCallback);
        }
    }

    @Override // com.bytedance.sdk.account.platform.PlatformDelegate
    void requestBind(Bundle bundle) {
        if (this.bindDelegate != null) {
            parseData(bundle);
            PlatformBindAdapter platformBindAdapter = this.bindDelegate;
            platformBindAdapter.getClass();
            this.mBindCallback = platformBindAdapter.new BindCallback();
            this.bindDelegate.api.ssoWithAccessTokenBind(this.bindDelegate.platformId, this.bindDelegate.platform, this.accessToken, this.expiresIn, null, this.mBindCallback);
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
            return new FacebookPlatformDelegate(platformLoginAdapter);
        }

        @Override // com.bytedance.sdk.account.platform.PlatformDelegate.IFactory
        public PlatformDelegate createBind(PlatformBindAdapter platformBindAdapter) {
            return new FacebookPlatformDelegate(platformBindAdapter);
        }
    }
}
