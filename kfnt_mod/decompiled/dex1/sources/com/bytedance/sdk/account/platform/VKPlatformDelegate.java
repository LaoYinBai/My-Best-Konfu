package com.bytedance.sdk.account.platform;

import android.os.Bundle;
import com.bytedance.sdk.account.platform.PlatformBindAdapter;
import com.bytedance.sdk.account.platform.PlatformBindAdapter.BindCallback;
import com.bytedance.sdk.account.platform.PlatformDelegate;
import com.bytedance.sdk.account.platform.PlatformLoginAdapter;
import com.bytedance.sdk.account.platform.PlatformLoginAdapter.LoginCallback;
import com.bytedance.sdk.account.platform.api.IVKService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class VKPlatformDelegate extends PlatformDelegate {
    String ACCESS_TOKEN;
    String CREATED;
    String EMAIL;
    String PHONE;
    String PHONE_ACCESS_KEY;
    String SECRET;
    String USER_ID;
    private String accessToken;
    private long created;
    private String email;
    private PlatformBindAdapter.BindCallback mBindCallback;
    private PlatformLoginAdapter.LoginCallback mLoginCallback;
    private String phone;
    private String phoneAccessKey;
    private String secret;
    private int userId;

    VKPlatformDelegate(PlatformBindAdapter platformBindAdapter) {
        super(platformBindAdapter);
        this.ACCESS_TOKEN = "access_token";
        this.USER_ID = "user_id";
        this.SECRET = "secret";
        this.CREATED = IVKService.ResponseConstants.CREATED;
        this.EMAIL = "email";
        this.PHONE = "phone";
        this.PHONE_ACCESS_KEY = IVKService.ResponseConstants.PHONE_ACCESS_KEY;
    }

    VKPlatformDelegate(PlatformLoginAdapter platformLoginAdapter) {
        super(platformLoginAdapter);
        this.ACCESS_TOKEN = "access_token";
        this.USER_ID = "user_id";
        this.SECRET = "secret";
        this.CREATED = IVKService.ResponseConstants.CREATED;
        this.EMAIL = "email";
        this.PHONE = "phone";
        this.PHONE_ACCESS_KEY = IVKService.ResponseConstants.PHONE_ACCESS_KEY;
    }

    private void parseData(Bundle bundle) {
        this.accessToken = bundle.getString("access_token");
        this.userId = bundle.getInt("user_id");
        this.secret = bundle.getString("secret");
        this.created = bundle.getLong(IVKService.ResponseConstants.CREATED);
        this.email = bundle.getString("email");
        this.phone = bundle.getString("phone");
        this.phoneAccessKey = bundle.getString(IVKService.ResponseConstants.PHONE_ACCESS_KEY);
    }

    @Override // com.bytedance.sdk.account.platform.PlatformDelegate
    void requestLogin(Bundle bundle) {
        if (this.loginDelegate != null) {
            parseData(bundle);
            PlatformLoginAdapter platformLoginAdapter = this.loginDelegate;
            platformLoginAdapter.getClass();
            this.mLoginCallback = platformLoginAdapter.new LoginCallback();
            this.loginDelegate.api.ssoWithAccessTokenLogin(this.loginDelegate.platformId, this.loginDelegate.platform, this.accessToken, this.secret, 0L, null, this.mLoginCallback);
        }
    }

    @Override // com.bytedance.sdk.account.platform.PlatformDelegate
    void requestBind(Bundle bundle) {
        if (this.bindDelegate != null) {
            parseData(bundle);
            PlatformBindAdapter platformBindAdapter = this.bindDelegate;
            platformBindAdapter.getClass();
            this.mBindCallback = platformBindAdapter.new BindCallback();
            this.bindDelegate.api.ssoWithAccessTokenBind(this.bindDelegate.platformId, this.bindDelegate.platform, this.accessToken, this.secret, 0L, null, this.mBindCallback);
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
            return new VKPlatformDelegate(platformLoginAdapter);
        }

        @Override // com.bytedance.sdk.account.platform.PlatformDelegate.IFactory
        public PlatformDelegate createBind(PlatformBindAdapter platformBindAdapter) {
            return new VKPlatformDelegate(platformBindAdapter);
        }
    }
}
