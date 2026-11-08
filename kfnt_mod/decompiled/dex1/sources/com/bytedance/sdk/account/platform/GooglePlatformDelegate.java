package com.bytedance.sdk.account.platform;

import android.os.Bundle;
import com.bytedance.sdk.account.platform.PlatformBindAdapter;
import com.bytedance.sdk.account.platform.PlatformBindAdapter.BindCallback;
import com.bytedance.sdk.account.platform.PlatformDelegate;
import com.bytedance.sdk.account.platform.PlatformLoginAdapter;
import com.bytedance.sdk.account.platform.PlatformLoginAdapter.LoginCallback;
import com.bytedance.sdk.account.platform.api.IGoogleService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class GooglePlatformDelegate extends PlatformDelegate {
    private String displayName;
    private long expiresIn;
    private String id;
    private String idToken;
    private PlatformBindAdapter.BindCallback mBindCallback;
    private PlatformLoginAdapter.LoginCallback mLoginCallback;

    GooglePlatformDelegate(PlatformBindAdapter platformBindAdapter) {
        super(platformBindAdapter);
    }

    GooglePlatformDelegate(PlatformLoginAdapter platformLoginAdapter) {
        super(platformLoginAdapter);
    }

    private void parseData(Bundle bundle) {
        this.id = bundle.getString("id");
        this.idToken = bundle.getString(IGoogleService.ResponseKey.ID_TOKEN);
        this.expiresIn = bundle.getLong(IGoogleService.ResponseKey.EXPIRE_IN, 0L);
        this.displayName = bundle.getString(IGoogleService.ResponseKey.DISPLAY_NAME);
    }

    @Override // com.bytedance.sdk.account.platform.PlatformDelegate
    void requestLogin(Bundle bundle) {
        if (this.loginDelegate != null) {
            parseData(bundle);
            PlatformLoginAdapter platformLoginAdapter = this.loginDelegate;
            platformLoginAdapter.getClass();
            this.mLoginCallback = platformLoginAdapter.new LoginCallback();
            this.loginDelegate.api.ssoWithAccessTokenLogin(this.loginDelegate.platformId, this.loginDelegate.platform, null, this.idToken, this.expiresIn, null, this.mLoginCallback);
        }
    }

    @Override // com.bytedance.sdk.account.platform.PlatformDelegate
    void requestBind(Bundle bundle) {
        if (this.bindDelegate != null) {
            parseData(bundle);
            PlatformBindAdapter platformBindAdapter = this.bindDelegate;
            platformBindAdapter.getClass();
            this.mBindCallback = platformBindAdapter.new BindCallback();
            this.bindDelegate.api.ssoWithAccessTokenBind(this.bindDelegate.platformId, this.bindDelegate.platform, null, this.idToken, this.expiresIn, null, this.mBindCallback);
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
            return new GooglePlatformDelegate(platformLoginAdapter);
        }

        @Override // com.bytedance.sdk.account.platform.PlatformDelegate.IFactory
        public PlatformDelegate createBind(PlatformBindAdapter platformBindAdapter) {
            return new GooglePlatformDelegate(platformBindAdapter);
        }
    }
}
