package com.bytedance.sdk.account.platform.api;

import com.bytedance.sdk.account.platform.base.AuthorizeCallback;
import com.bytedance.sdk.account.platform.base.Request;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface ITouTiaoService extends IAuthorizeService {

    public interface ResponseConstants {
        public static final String AUTH_CODE = "auth_code";
        public static final String EXTRA = "extras";
        public static final String GRANTED_PERMISSION = "grantedPermissions";
        public static final String STATE = "state";
    }

    public interface Scope {
        public static final String TT_SCOPE_INFO = "user_info";
    }

    boolean authorize(Request request, AuthorizeCallback authorizeCallback);

    boolean isAppInstalled();

    boolean isAppSupportAuthorization();

    void onDestroy();
}
