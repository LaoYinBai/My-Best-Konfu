package com.bytedance.sdk.account.platform.api;

import android.app.Activity;
import com.bytedance.sdk.account.platform.base.AuthorizeCallback;
import com.bytedance.sdk.account.platform.base.Request;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface ITiktokService extends IAuthorizeService {

    public interface Extra {
        public static final String REQURE_MOBILE_BIND = "require_tel_num_bind";
    }

    public interface ResponseConstants {
        public static final String AUTH_CODE = "auth_code";
        public static final String EXTRA = "extras";
        public static final String GRANTED_PERMISSION = "granted_permission";
        public static final String STATE = "state";
    }

    public interface Scope {
        public static final String MOBILE = "mobile";
        public static final String MOBILE_ALERT = "mobile_alert";
        public static final String USER_INFO = "user_info";
    }

    boolean authorize(Activity activity, Request request, AuthorizeCallback authorizeCallback);

    boolean authorizeWeb(Activity activity, Request request, AuthorizeCallback authorizeCallback);

    boolean authorizeWeb(Activity activity, Request request, Class cls, AuthorizeCallback authorizeCallback);

    String getSdkVersion(Activity activity);

    boolean isAppInstalled(Activity activity);

    boolean isAppSupportAuthorization(Activity activity);

    void onDestroy();
}
