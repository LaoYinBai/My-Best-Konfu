package com.bytedance.sdk.account.platform.api;

import com.bytedance.sdk.account.platform.base.AuthorizeCallback;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IOnekeyLoginService extends IAuthorizeService {

    public interface ResponseConstants {
        public static final String ACCESS_TOKEN = "access_token";
        public static final String CM_AUTH_TYPE = "authType";
        public static final String CM_AUTH_TYPE_DES = "authTypeDes";
        public static final String CT_REFRESH_TOKEN = "refreshToken";
        public static final String NET_STATUS = "net_status";
        public static final String NET_TYPE = "net_type";
        public static final String OPEN_ID = "openId";
        public static final String RAW_RESULT = "raw_result";
        public static final String SECURITY_PHONE = "security_phone";
        public static final String TOKEN_EXPIRES_IN = "expires_in";
    }

    void cancel();

    void getAuthToken(AuthorizeCallback authorizeCallback);

    String getCarrier();

    int getNetworkStatus();

    void getPhoneInfo(AuthorizeCallback authorizeCallback);
}
