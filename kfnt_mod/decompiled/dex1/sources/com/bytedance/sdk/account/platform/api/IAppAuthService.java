package com.bytedance.sdk.account.platform.api;

import android.app.Activity;
import com.bytedance.sdk.account.platform.base.ActivityResultHandler;
import com.bytedance.sdk.account.platform.base.AuthorizeCallback;
import com.bytedance.sdk.account.platform.base.Request;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IAppAuthService extends IAuthorizeService {

    public interface CallbackHandler extends ActivityResultHandler {
        void onDestroy();
    }

    public interface ResponseKey {
        public static final String ACCESS_TOKEN = "access_token";
        public static final String ACCESS_TOKEN_EXPIRATION_TIME = "access_token_expiration_time";
        public static final String AUTH_CODE = "auth_code";
        public static final String ID_TOKEN = "id_token";
        public static final String REFRESH_TOKEN = "refresh_token";
        public static final String SCOPE = "scope";
        public static final String STATE = "state";
    }

    public interface Scope {
        public static final String ADDRESS = "address";
        public static final String EMAIL = "email";
        public static final String OFFLINE_ACCESS = "offline_access";
        public static final String OPENID = "openid";
        public static final String PHONE = "phone";
        public static final String PROFILE = "profile";
    }

    CallbackHandler authorize(Activity activity, Request request, AuthorizeCallback authorizeCallback);
}
