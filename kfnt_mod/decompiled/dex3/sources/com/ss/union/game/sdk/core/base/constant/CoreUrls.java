package com.ss.union.game.sdk.core.base.constant;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class CoreUrls {
    public static final String URL_CONFIG = LGUris.path("/game_sdk/config");

    public static final class DeviceInfo {
        public static final String URL_POST_QUERY_DEVICE_INFO = LGUris.path("/game_sdk/light_game/privacy/device/info");
        public static final String URL_POST_CLOSE_DEVICE_INFO = LGUris.path("/game_sdk/light_game/device/close");
    }

    public static final class Init {
        public static final String URL_INIT_UPDATE_APP_ID = LGUris.path("/game_sdk/light_game_init_param");
    }

    public static final class Privacy {
        public static final String URL_PRIVACY_POLICY_CONFIG = LGUris.path("/game_sdk/light_game/privacy");
    }

    public static final class RealName {
        public static final String URL_IDENTIFY_VALIDATE = LGUris.path("/game_sdk/light_game/identify_validate");
        public static final String URL_CHECK_DEVICE_REAL_NAME = LGUris.path("/game_sdk/light_game/identify_validate_info/device");
        public static final String URL_DEVICE_REAL_NAME = LGUris.path("/game_sdk/light_game/identify_validate/device");
        public static final String URL_GET_ANTI_ADDICTION_INFO = LGUris.path("/game_sdk/light_game/anti_addiction_info");
    }
}
