package com.bytedance.sdk.account.platform.api;

import com.bytedance.sdk.account.platform.base.AuthorizeCallback;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IWeixinService extends IAuthorizeService {

    public interface ResponseConstants {
        public static final String AUTH_CODE = "auth_code";
        public static final String OPEN_ID = "openId";
        public static final String STATE = "state";
        public static final String URL = "url";
    }

    public interface Scope {
        public static final String WX_SCOPE_BASE = "snsapi_base";
        public static final String WX_SCOPE_INFO = "snsapi_userinfo";
    }

    boolean authorize(String str, String str2, AuthorizeCallback authorizeCallback);

    String getWxAppId();

    @Deprecated
    boolean isWXAppSupportAPI();

    void onDestroy();
}
