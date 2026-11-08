package com.bytedance.sdk.account.api;

import com.bytedance.sdk.account.api.call.AbsApiCall;
import com.bytedance.sdk.account.api.call.BaseApiResponse;
import com.bytedance.sdk.account.api.call.PlatformAuthTokenResponse;
import com.bytedance.sdk.account.api.call.UserApiResponse;
import com.ss.android.account.UserBindCallback;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IBDAccountPlatformAPI {
    void bindWithMobile(String str, String str2, String str3, String str4, String str5, String str6, String str7, long j, String str8, String str9, Map<String, String> map, UserBindCallback userBindCallback);

    void platformAuthToken(String str, String str2, String str3, String str4, String str5, long j, Map map, AbsApiCall<PlatformAuthTokenResponse> absApiCall);

    void ssoSwitchBindWithAuthToken(String str, String str2, String str3, long j, Map map, AbsApiCall<UserApiResponse> absApiCall);

    void ssoWithAccessTokenBind(String str, String str2, String str3, long j, Map map, UserBindCallback userBindCallback);

    void ssoWithAccessTokenBind(String str, String str2, String str3, String str4, long j, Map map, UserBindCallback userBindCallback);

    void ssoWithAccessTokenLogin(String str, String str2, String str3, long j, Map map, AbsApiCall<UserApiResponse> absApiCall);

    void ssoWithAccessTokenLogin(String str, String str2, String str3, String str4, long j, Map map, AbsApiCall<UserApiResponse> absApiCall);

    void ssoWithAccessTokenOnlyLogin(String str, String str2, String str3, long j, Map map, AbsApiCall<UserApiResponse> absApiCall);

    void ssoWithAuthCodeBind(String str, String str2, String str3, long j, Map map, UserBindCallback userBindCallback);

    void ssoWithAuthCodeBindMobileLogin(String str, String str2, String str3, String str4, boolean z, boolean z2, Map map, AbsApiCall<UserApiResponse> absApiCall);

    void ssoWithAuthCodeLogin(String str, String str2, String str3, long j, Map map, AbsApiCall<UserApiResponse> absApiCall);

    void ssoWithAuthCodeOnlyLogin(String str, String str2, String str3, long j, Map map, AbsApiCall<UserApiResponse> absApiCall);

    void ssoWithProfileKeyBind(String str, String str2, String str3, long j, Map map, UserBindCallback userBindCallback);

    void ssoWithProfileKeyLogin(String str, String str2, String str3, long j, Map map, AbsApiCall<UserApiResponse> absApiCall);

    void ssoWithProfileKeyRegister(String str, String str2, String str3, long j, Map map, UserBindCallback userBindCallback);

    void switchBindWithAccessToken(String str, String str2, String str3, long j, Map map, UserBindCallback userBindCallback);

    void switchBindWithAuthCode(String str, String str2, String str3, long j, Map map, UserBindCallback userBindCallback);

    void unbindPlaform(String str, AbsApiCall<BaseApiResponse> absApiCall);

    void unbindPlatform(String str, int i, String str2, AbsApiCall<BaseApiResponse> absApiCall);

    void webAuth(String str, Map map, UserBindCallback userBindCallback);
}
