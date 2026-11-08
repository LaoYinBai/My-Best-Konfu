package com.bytedance.sdk.account.platform.api;

import android.app.Activity;
import com.bytedance.sdk.account.platform.base.ActivityResultHandler;
import com.bytedance.sdk.account.platform.base.AuthorizeCallback;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IGoogleService extends IAuthorizeService {
    public static final int APP_AUTH_0 = 0;
    public static final int APP_AUTH_1 = 1;
    public static final int APP_AUTH_2 = 2;

    public interface CallbackHandler extends ActivityResultHandler {
    }

    public interface ResponseKey {
        public static final String DISPLAY_NAME = "display_name";
        public static final String EXPIRE_IN = "expire_in";
        public static final String ID = "id";
        public static final String ID_TOKEN = "idToken";
    }

    CallbackHandler authorize(Activity activity, int i, AuthorizeCallback authorizeCallback);

    void googleSignOut();

    boolean isGooglePlayServiceAvailable();
}
