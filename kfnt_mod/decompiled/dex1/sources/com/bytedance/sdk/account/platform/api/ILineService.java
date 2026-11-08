package com.bytedance.sdk.account.platform.api;

import android.app.Activity;
import com.bytedance.sdk.account.platform.base.ActivityResultHandler;
import com.bytedance.sdk.account.platform.base.AuthorizeCallback;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface ILineService extends IAuthorizeService {

    public interface CallbackHandler extends ActivityResultHandler {
    }

    public interface ResponseKey {
        public static final String ACCESS_TOKEN = "access_token";
    }

    CallbackHandler authorize(Activity activity, AuthorizeCallback authorizeCallback);
}
