package com.bytedance.sdk.account.platform.api;

import android.app.Activity;
import com.bytedance.sdk.account.platform.base.ActivityResultHandler;
import com.bytedance.sdk.account.platform.base.AuthorizeCallback;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IVKService extends IAuthorizeService {

    public interface CallbackHandler extends ActivityResultHandler {
    }

    public interface ResponseConstants {
        public static final String ACCESS_TOKEN = "access_token";
        public static final String CREATED = "created";
        public static final String EMAIL = "email";
        public static final String PHONE = "phone";
        public static final String PHONE_ACCESS_KEY = "phone_access_key";
        public static final String SECRET = "secret";
        public static final String USER_ID = "user_id";
    }

    public enum VKPermission {
        NOTIFY,
        FRIENDS,
        PHOTOS,
        AUDIO,
        VIDEO,
        STORIES,
        PAGES,
        STATUS,
        NOTES,
        MESSAGES,
        WALL,
        ADS,
        OFFLINE,
        DOCS,
        GROUPS,
        NOTIFICATIONS,
        STATS,
        EMAIL,
        MARKET,
        PHONE
    }

    CallbackHandler authorize(Activity activity, AuthorizeCallback authorizeCallback);

    CallbackHandler authorize(Activity activity, List<VKPermission> list, AuthorizeCallback authorizeCallback);
}
