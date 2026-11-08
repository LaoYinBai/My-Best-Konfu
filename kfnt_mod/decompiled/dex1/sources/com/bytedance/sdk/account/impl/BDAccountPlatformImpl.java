package com.bytedance.sdk.account.impl;

import android.content.Context;
import com.bytedance.sdk.account.api.IBDAccountPlatformAPI;
import com.bytedance.sdk.account.api.call.AbsApiCall;
import com.bytedance.sdk.account.api.call.BaseApiResponse;
import com.bytedance.sdk.account.api.call.PlatformAuthTokenResponse;
import com.bytedance.sdk.account.api.call.UserApiResponse;
import com.bytedance.sdk.account.sso.PlatformAuthTokenThread;
import com.bytedance.sdk.account.sso.SsoApiAuthRegisterThread;
import com.bytedance.sdk.account.sso.SsoApiBindThread;
import com.bytedance.sdk.account.sso.SsoApiLoginThread;
import com.bytedance.sdk.account.sso.SsoApiOnlyLoginThread;
import com.bytedance.sdk.account.sso.SsoApiSwitchBindThread;
import com.bytedance.sdk.account.sso.SsoApiUnbindTrhead;
import com.bytedance.sdk.account.sso.WebAuthUserThread;
import com.ss.android.account.UserBindCallback;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class BDAccountPlatformImpl implements IBDAccountPlatformAPI {
    Context mContext;

    public BDAccountPlatformImpl(Context context) {
        this.mContext = context;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountPlatformAPI
    public void unbindPlaform(String str, AbsApiCall<BaseApiResponse> absApiCall) {
        SsoApiUnbindTrhead.withUnbind(this.mContext, str, absApiCall).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountPlatformAPI
    public void unbindPlatform(String str, int i, String str2, AbsApiCall<BaseApiResponse> absApiCall) {
        SsoApiUnbindTrhead.withUnbind(this.mContext, str, i, str2, absApiCall).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountPlatformAPI
    public void ssoWithAuthCodeLogin(String str, String str2, String str3, long j, Map map, AbsApiCall<UserApiResponse> absApiCall) {
        SsoApiLoginThread.withAuthCodeLogin(this.mContext, str, str2, str3, String.valueOf(j), map, absApiCall).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountPlatformAPI
    public void ssoWithAccessTokenLogin(String str, String str2, String str3, long j, Map map, AbsApiCall<UserApiResponse> absApiCall) {
        SsoApiLoginThread.withAccessTokenLogin(this.mContext, str, str2, str3, String.valueOf(j), map, absApiCall).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountPlatformAPI
    public void ssoWithAccessTokenLogin(String str, String str2, String str3, String str4, long j, Map map, AbsApiCall<UserApiResponse> absApiCall) {
        SsoApiLoginThread.withAccessTokenLogin(this.mContext, str, str2, str3, str4, String.valueOf(j), map, absApiCall).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountPlatformAPI
    public void ssoWithProfileKeyLogin(String str, String str2, String str3, long j, Map map, AbsApiCall<UserApiResponse> absApiCall) {
        SsoApiLoginThread.withProfileKeyLogin(this.mContext, str, str2, str3, String.valueOf(j), map, absApiCall).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountPlatformAPI
    public void ssoWithAuthCodeOnlyLogin(String str, String str2, String str3, long j, Map map, AbsApiCall<UserApiResponse> absApiCall) {
        SsoApiOnlyLoginThread.withAuthCodeOnlyLogin(this.mContext, str, str2, str3, String.valueOf(j), map, absApiCall).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountPlatformAPI
    public void ssoWithAccessTokenOnlyLogin(String str, String str2, String str3, long j, Map map, AbsApiCall<UserApiResponse> absApiCall) {
        SsoApiOnlyLoginThread.withAccessTokenOnlyLogin(this.mContext, str, str2, str3, String.valueOf(j), map, absApiCall).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountPlatformAPI
    public void ssoWithAuthCodeBindMobileLogin(String str, String str2, String str3, String str4, boolean z, boolean z2, Map map, AbsApiCall<UserApiResponse> absApiCall) {
        SsoApiLoginThread.withAuthCodeBindMobileLogin(this.mContext, str, str2, str3, str4, z, z2, map, absApiCall).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountPlatformAPI
    public void ssoSwitchBindWithAuthToken(String str, String str2, String str3, long j, Map map, AbsApiCall<UserApiResponse> absApiCall) {
        SsoApiSwitchBindThread.withAuthTokenSwitchBind(this.mContext, str, str2, str3, String.valueOf(j), map, absApiCall).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountPlatformAPI
    public void switchBindWithAccessToken(String str, String str2, String str3, long j, Map map, UserBindCallback userBindCallback) {
        SsoApiSwitchBindThread.withAccessTokenSwitchBind(this.mContext, str, str2, str3, String.valueOf(j), map, userBindCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountPlatformAPI
    public void switchBindWithAuthCode(String str, String str2, String str3, long j, Map map, UserBindCallback userBindCallback) {
        SsoApiSwitchBindThread.withAuthCodeSwitchBind(this.mContext, str, str2, str3, String.valueOf(j), map, userBindCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountPlatformAPI
    public void ssoWithAuthCodeBind(String str, String str2, String str3, long j, Map map, UserBindCallback userBindCallback) {
        SsoApiBindThread.withAuthCodeBind(this.mContext, str, str2, str3, String.valueOf(j), map, userBindCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountPlatformAPI
    public void ssoWithAccessTokenBind(String str, String str2, String str3, long j, Map map, UserBindCallback userBindCallback) {
        SsoApiBindThread.withAccessTokenBind(this.mContext, str, str2, str3, String.valueOf(j), map, userBindCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountPlatformAPI
    public void ssoWithAccessTokenBind(String str, String str2, String str3, String str4, long j, Map map, UserBindCallback userBindCallback) {
        SsoApiBindThread.withAccessTokenBind(this.mContext, str, str2, str3, str4, String.valueOf(j), map, userBindCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountPlatformAPI
    public void ssoWithProfileKeyBind(String str, String str2, String str3, long j, Map map, UserBindCallback userBindCallback) {
        SsoApiBindThread.withProfileKeyBind(this.mContext, str, str2, str3, String.valueOf(j), map, userBindCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountPlatformAPI
    public void bindWithMobile(String str, String str2, String str3, String str4, String str5, String str6, String str7, long j, String str8, String str9, Map<String, String> map, UserBindCallback userBindCallback) {
        SsoApiBindThread.bindWithMobile(this.mContext, str, str2, str3, str4, str5, str6, str7, String.valueOf(j), str8, str9, map, userBindCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountPlatformAPI
    public void ssoWithProfileKeyRegister(String str, String str2, String str3, long j, Map map, UserBindCallback userBindCallback) {
        SsoApiAuthRegisterThread.withProfileKey(this.mContext, str, str2, str3, String.valueOf(j), map, userBindCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountPlatformAPI
    public void webAuth(String str, Map map, UserBindCallback userBindCallback) {
        WebAuthUserThread.withAuthUser(this.mContext, str, userBindCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountPlatformAPI
    public void platformAuthToken(String str, String str2, String str3, String str4, String str5, long j, Map map, AbsApiCall<PlatformAuthTokenResponse> absApiCall) {
        PlatformAuthTokenThread.withAuthCode(this.mContext, str, str2, str3, str4, str5, String.valueOf(j), map, absApiCall).start();
    }
}
