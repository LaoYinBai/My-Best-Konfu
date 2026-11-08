package com.bytedance.sdk.account.platform.api;

import android.app.Activity;
import com.bytedance.sdk.account.platform.base.ActivityResultHandler;
import com.bytedance.sdk.account.platform.base.AuthorizeCallback;
import java.util.Collection;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IFacebookService extends IAuthorizeService {

    public interface CallbackHandler extends ActivityResultHandler {
    }

    public interface Permission {
        public static final String PUBLIC = "public_profile";
    }

    public interface ResponseKey {
        public static final String ACCESS_TOKEN = "access_token";
        public static final String EXPIRES_IN = "expires_in";
        public static final String USER_ID = "user_id";
    }

    CallbackHandler loginWithPublishPermissions(Activity activity, Collection<String> collection, AuthorizeCallback authorizeCallback);

    CallbackHandler loginWithReadPermissions(Activity activity, Collection<String> collection, AuthorizeCallback authorizeCallback);
}
