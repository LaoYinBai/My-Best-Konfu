package com.bytedance.sdk.account.platform.api;

import android.app.Activity;
import com.bytedance.sdk.account.platform.base.ActivityResultHandler;
import com.bytedance.sdk.account.platform.base.AuthorizeCallback;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface ITwitterService extends IAuthorizeService {

    public interface CallbackHandler extends ActivityResultHandler {
    }

    public interface ResponseConstants {
        public static final String AUTH_TOKEN = "auth_token";
        public static final String ID = "id";
        public static final String SECRET = "secret";
        public static final String USER_NAME = "user_name";
    }

    CallbackHandler authorize(Activity activity, AuthorizeCallback authorizeCallback);
}
