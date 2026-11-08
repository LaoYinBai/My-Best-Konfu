package com.bytedance.sdk.account.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface AccountDef {

    public interface AccountInfoScene {
        public static final String BOOT = "boot";
        public static final String LOGIN = "login";
        public static final String NORMAL = "normal";
        public static final String POLLING = "polling";
        public static final String WAP_LOGIN = "wap_login";
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface AccountInfoSceneDef {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface EmailSendCodeTypeDef {
    }

    public interface LogoutScene {
        public static final String CANCEL_ACCOUNT_LOGOUT = "cancel_account_logout";
        public static final String SESSION_LOGOUT = "sdk_expired_logout";
        public static final String USER_LOGOUT = "user_logout";
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface LogoutSceneDef {
    }

    public interface LogoutSceneEvent {
        public static final int CANCEL_ACCOUNT_LOGOUT = 2;
        public static final int OTHER_LOGOUT = 3;
        public static final int SESSION_LOGOUT = 1;
        public static final int USER_LOGOUT = 0;
    }

    public interface SendCodeCheckRegister {
        public static final int CHECK = 1;
        public static final int DEFAULT = -1;
        public static final int NO_CHECK = 0;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SendCodeCheckRegisterDef {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SendCodeTypeDef {
    }
}
