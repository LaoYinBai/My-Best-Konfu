package com.bytedance.sdk.account.platform.api;

import android.app.Activity;
import androidx.annotation.Nullable;
import com.bytedance.sdk.account.platform.base.ActivityResultHandler;
import com.bytedance.sdk.account.platform.base.AuthorizeCallback;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IQQService extends IAuthorizeService {

    public interface CallbackHandler extends ActivityResultHandler {
    }

    public interface ErrorCode {
        public static final int APP_CHECK = 110406;
        public static final int APP_NOT_EXIST = 110401;
        public static final int APP_SOLD_OUT = 110407;
        public static final int ERR_GET_APP_AUTH_INFO = 110501;
        public static final int ERR_GET_TOKEN = 110503;
        public static final int ERR_GET_USER_AUTH_INFO = 110500;
        public static final int ERR_INTERNAL = 110504;
        public static final int ERR_SET_USER_AUTH = 110502;
        public static final int ERR_SIGN = 100044;
        public static final int LOGIN_LIMIT = 110405;
        public static final int NOT_LOGIN = 110201;
        public static final int NO_APP_ID = 110404;
    }

    public interface ResponseConstants {
        public static final String ACCESS_TOKEN = "access_token";
        public static final String EXPIRES_IN = "expires_in";
        public static final String OPENID = "openid";
    }

    public interface Scope {
        public static final String SCOPE_GET_USER_INFO = "get_simple_userinfo";
    }

    CallbackHandler authorize(Activity activity, @Nullable String str, AuthorizeCallback authorizeCallback);
}
