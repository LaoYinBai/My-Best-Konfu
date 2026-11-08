package com.bytedance.sdk.open.aweme;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface TikTokConstants {
    public static final String ACTION_STAY_IN_DY = "com.aweme.opensdk.action.stay.in.dy";

    public interface AuthErrorCode {
        public static final int ERROR_CODE_EXPIRED = 10007;
        public static final int ERROR_CONFIG = 10003;
        public static final int ERROR_NETWORK_CONNECT_TIMEOUT = -13;
        public static final int ERROR_NETWORK_IO = -15;
        public static final int ERROR_NETWORK_NO_CONNECTION = -12;
        public static final int ERROR_NETWORK_SSL = -21;
        public static final int ERROR_NETWORK_TIMEOUT = -14;
        public static final int ERROR_NETWORK_UNKNOWN_HOST_ERROR = -16;
        public static final int ERROR_N_PARAMS = 10005;
        public static final int ERROR_PARAM = 10002;
        public static final int ERROR_REDIRECT_URL = 10006;
        public static final int ERROR_REFRESH_TOKEN = 10010;
        public static final int ERROR_SCOPE = 10004;
        public static final int ERROR_SYSTEM = 10001;
        public static final int ERROR_TICKET = 10009;
        public static final int ERROR_TOKEN = 10008;
    }

    public interface BaseErrorCode {
        public static final int ERROR_CANCEL = -2;
        public static final int ERROR_UNKNOW = -1;
        public static final int OK = 0;
    }

    public interface ModeType {
        public static final int SEND_AUTH_REQUEST = 1;
        public static final int SEND_AUTH_RESPONSE = 2;
        public static final int SHARE_CONTENT_TO_DY = 3;
        public static final int SHARE_CONTENT_TO_DY_RESP = 4;
    }

    public interface ShareErrorCode {
        public static final int ERROR_CODE_AUTH_DENIED = -4;
        public static final int ERROR_CODE_SEND_FAIL = -3;
        public static final int ERROR_CODE_UNSUPPORT = -5;
    }

    public interface TARGET_APP {
        public static final int AWEME = 1;
        public static final int TIKTOK = 2;
    }
}
