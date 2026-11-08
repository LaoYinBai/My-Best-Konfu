package com.bytedance.sdk.open.aweme.common.constants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface ParamKeyConstants {
    public static final String AWEME_EXTRA_MEDIA_MESSAGE_IMAGE_PATH = "AWEME_EXTRA_IMAGE_MESSAGE_PATH";
    public static final String AWEME_EXTRA_MEDIA_MESSAGE_VIDEO_PATH = "AWEME_EXTRA_VIDEO_MESSAGE_PATH";
    public static final String META_PLATFORM_SDK_VERSION = "BD_PLATFORM_SDK_VERSION";
    public static final int META_PLATFORM_SDK_VERSION_ERROR = -1;
    public static final String OAUTH_PATH = "/oauth/authorize/";
    public static final String REDIRECT_URL_PATH = "/oauth/authorize/callback/";

    public interface AuthParams {
        public static final String AUTH_CODE = "_bytedance_params_authcode";
        public static final String CLIENT_KEY = "_bytedance_params_client_key";
        public static final String GRANTED_PERMISSION = "_bytedance_params_granted_permission";
        public static final String OPTIONAL_SCOPE0 = "_bytedance_params_optional_scope0";
        public static final String OPTIONAL_SCOPE1 = "_bytedance_params_optional_scope1";
        public static final String REDIRECT_URI = "_bytedance_params_redirect_uri";
        public static final String SCOPE = "_bytedance_params_scope";
        public static final String STATE = "_bytedance_params_state";
        public static final String WAP_REQUESETED_ORIENTATION = "wap_requested_orientation";
    }

    public interface BaseParams {
        public static final String CALLER_BASE_OPEN_VERSION = "__bytedance_base_caller_version";
        public static final String CALLER_PKG = "_bytedance_params_type_caller_package";
        public static final String ERROR_CODE = "_bytedance_params_error_code";
        public static final String ERROR_MSG = "_bytedance_params_error_msg";
        public static final String EXTRA = "_bytedance_params_extra";
        public static final String FROM_ENTRY = "_bytedance_params_from_entry";
        public static final String TYPE = "_bytedance_params_type";
    }

    public interface REQUIRED_API_VERSION {
        public static final int AUTH_REQUIRE_API = 1;
        public static final int MIN_SDK_NEW_TIKTOK_API = 3;
        public static final int SHARE_REQUIRED_MIN_VERSION = 2;
    }

    public interface SdkVersion {
        public static final String VERSION = "1";
    }

    public interface ShareParams {
        public static final String CALLER_LOCAL_ENTRY = "_aweme_open_sdk_params_caller_local_entry";
        public static final String CALLER_PKG = "_aweme_open_sdk_params_caller_package";
        public static final String CALLER_SDK_VERSION = "_aweme_open_sdk_params_caller_sdk_version";
        public static final String CLIENT_KEY = "_aweme_open_sdk_params_client_key";
        public static final String ERROR_CODE = "_aweme_open_sdk_params_error_code";
        public static final String ERROR_MSG = "_aweme_open_sdk_params_error_msg";
        public static final String EXTRA = "_aweme_open_sdk_params_extra";
        public static final String SHARE_DEFAULT_HASHTAG = "_aweme_open_sdk_params_target_scene";
        public static final String SHARE_MICROAPP_INFO = "_aweme_open_sdk_params_micro_app_info";
        public static final String SHARE_TARGET_SCENE = "_aweme_open_sdk_params_target_scene";
        public static final String STATE = "_aweme_open_sdk_params_state";
        public static final String TYPE = "_aweme_open_sdk_params_type";
    }

    public interface TargetSceneType {
        public static final int SHARE_DEFAULT_TYPE = 0;
    }

    public interface WebViewConstants {
        public static final String QUERY_CLIENT_KEY = "client_key";
        public static final String QUERY_FROM = "from";
        public static final String QUERY_OPTIONAL_SCOPE = "optionalScope";
        public static final String QUERY_REDIRECT_URI = "redirect_uri";
        public static final String QUERY_RESPONSE_TYPE = "response_type";
        public static final String QUERY_SCOPE = "scope";
        public static final String QUERY_SIGNATURE = "signature";
        public static final String QUERY_STATE = "state";
        public static final String REDIRECT_QUERY_CODE = "code";
        public static final String REDIRECT_QUERY_ERROR_CODE = "errCode";
        public static final String REDIRECT_QUERY_SCOPE = "scopes";
        public static final String REDIRECT_QUERY_STATE = "state";
        public static final String SCHEMA_HTTPS = "https";
        public static final String VALUE_FROM_OPENSDK = "opensdk";
        public static final String VALUE_RESPONSE_TYPE_CODE = "code";
    }
}
