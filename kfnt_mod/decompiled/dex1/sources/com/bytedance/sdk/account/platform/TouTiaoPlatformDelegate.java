package com.bytedance.sdk.account.platform;

import android.os.Bundle;
import com.bytedance.sdk.account.api.IBDAccountPlatformAPI;
import com.bytedance.sdk.account.platform.PlatformBindAdapter;
import com.bytedance.sdk.account.platform.PlatformBindAdapter.BindCallback;
import com.bytedance.sdk.account.platform.PlatformDelegate;
import com.bytedance.sdk.account.platform.PlatformLoginAdapter;
import com.bytedance.sdk.account.platform.PlatformLoginAdapter.LoginCallback;
import com.bytedance.sdk.account.platform.api.ITouTiaoService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class TouTiaoPlatformDelegate extends PlatformDelegate {
    private String authCode;
    private String grantedPermission;
    private PlatformBindAdapter.BindCallback mBindCallback;
    private PlatformLoginAdapter.LoginCallback mLoginCallback;
    private String state;

    TouTiaoPlatformDelegate(PlatformBindAdapter platformBindAdapter) {
        super(platformBindAdapter);
    }

    TouTiaoPlatformDelegate(PlatformLoginAdapter platformLoginAdapter) {
        super(platformLoginAdapter);
    }

    private void parseData(Bundle bundle) {
        this.authCode = bundle.getString("auth_code");
        this.state = bundle.getString("state");
        this.grantedPermission = bundle.getString(ITouTiaoService.ResponseConstants.GRANTED_PERMISSION);
    }

    @Override // com.bytedance.sdk.account.platform.PlatformDelegate
    void requestLogin(Bundle bundle) {
        if (this.loginDelegate != null) {
            parseData(bundle);
            PlatformLoginAdapter platformLoginAdapter = this.loginDelegate;
            platformLoginAdapter.getClass();
            this.mLoginCallback = platformLoginAdapter.new LoginCallback();
            this.loginDelegate.api.ssoWithAuthCodeLogin(this.loginDelegate.platformId, this.loginDelegate.platform, this.authCode, 0L, null, this.mLoginCallback);
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
            String str3 = this.authCode;
            PlatformBindAdapter platformBindAdapter2 = this.bindDelegate;
            platformBindAdapter2.getClass();
            iBDAccountPlatformAPI.ssoWithAuthCodeBind(str, str2, str3, 0L, null, platformBindAdapter2.new BindCallback());
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
            return new TouTiaoPlatformDelegate(platformLoginAdapter);
        }

        @Override // com.bytedance.sdk.account.platform.PlatformDelegate.IFactory
        public PlatformDelegate createBind(PlatformBindAdapter platformBindAdapter) {
            return new TouTiaoPlatformDelegate(platformBindAdapter);
        }
    }
}
