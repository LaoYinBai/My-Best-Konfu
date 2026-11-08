package com.ss.android.downloadlib.constants;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DownloadConstants {
    public static final int BUTTON_CLICK_NORMAL_TYPE = 0;
    public static final int BUTTON_CLICK_OPEN_WEBVIEW_TYPE = 1;
    public static final long CLEAR_HANDLER_POOL_INTERVAL = 300000;
    public static final long DEFAULT_INSTALL_INTERVAL = 300000;
    public static final long DEFAULT_NEXT_INSTALL_MIN_INTERVAL = 10000;
    public static final long DOWNLOAD_COMPLETE_FILE_EXPIRE_INTERVAL = 604800000;
    public static final long DOWNLOAD_FILE_EXPIRE_INTERVAL = 604800000;
    public static final String DOWNLOAD_FINISH_REASON_BEFORE_DOWNLOAD_CANCEL = "7";
    public static final String DOWNLOAD_FINISH_REASON_BEFORE_DOWNLOAD_ERROR = "5";
    public static final String DOWNLOAD_FINISH_REASON_BEFORE_DOWNLOAD_NO_INIT = "8";
    public static final String DOWNLOAD_FINISH_REASON_BEFORE_DOWNLOAD_SUCCESS = "6";
    public static final String DOWNLOAD_FINISH_REASON_CANCEL_CLEAN = "3";
    public static final String DOWNLOAD_FINISH_REASON_CLEAN_APP_CACHE = "1";
    public static final String DOWNLOAD_FINISH_REASON_CLEAN_DIALOG = "2";
    public static final String DOWNLOAD_FINISH_REASON_SHOW_DIALOG_ERROR = "4";
    public static final long DOWNLOAD_OPTIMIZATION_SPACE_INTERNAL = 600000;
    public static final String ERROR_MSG_NO_AVAILABLE_SPACE = "availableSpace <= 0";
    public static final String ERROR_MSG_NO_SPACE_LEFT = "ENOSPC (No space left on device)";
    public static final String EVENT_EXT_VALUE = "ext_value";
    public static final String EVENT_LABEL_DELAY_INSTALL = "delay_install";
    public static final String EVENT_LABEL_OPTIMIZATION_CLEAN = "cleanup";
    public static final String EVENT_SCENE = "scene";
    public static final int EVENT_SOURCE_SDK_INSIDE = 2;
    public static final int EVENT_SOURCE_USER = 1;
    public static final long HOUR = 3600000;
    public static final int INSTALL_FINISH_MATCHED = 3000;
    public static final int INSTALL_FINISH_MAY_MATCH = 3001;
    public static final int INSTALL_FINISH_NOT_MATCHED = 3002;
    public static final int INSTALL_FINISH_VERSION_HIGHER = 3011;
    public static final int INSTALL_FINISH_VERSION_LOWER = 3010;
    public static final String KEY_AD_ID = "ad_id";
    public static final String KEY_BACK_USE_SOFTREF_LISTENER = "back_use_softref_listener";
    public static final String KEY_BUSINESS_TYPE = "business_type";
    public static final String KEY_ENABLE_SHOW_MINIAPP_DIALOG = "enable_miniapp_dialog";
    public static final String KEY_EXTRA = "extra";
    public static final String KEY_EXTRA_JSON = "extra_json";
    public static final String KEY_EXTRA_VALUE = "ext_value";
    public static final String KEY_FORCE_UPDATE = "force_update";
    public static final String KEY_IS_ENABLE_BACKDIALOG = "is_enable_backdialog";
    public static final String KEY_LOG_EXTRA = "log_extra";
    public static final String KEY_NOTIFICATION_JUMP_URL = "notification_jump_url";
    public static final String KEY_OPEN_URL = "open_url";
    public static final String KEY_PACKAGE_NAME = "package_name";
    public static final String KEY_POSITION = "position";
    public static final String KEY_QUICK_APP_CHECK_INTERNAL = "quick_app_check_internal";
    public static final String KEY_QUICK_APP_ENABLE_SWITCH = "quick_app_enable_switch";
    public static final String KEY_TOAST = "show_toast";
    public static final long MAX_IDLE_HANDLE_LIVE_TIME = 300000;
    public static final String MIME_APK = "application/vnd.android.package-archive";
    public static final int MIN_CHUNK_COUNT = 1;
    public static final int MSG_START_INSTALL = 200;
    public static final int MSG_STATUS_CHECK_INSTALL_BACKGROUD = 1;
    public static final long OPEN_APP_DIALOG_EXPIRED_TIME = 3600000;
    public static final String SCHEME_MARKET = "market";
    public static final int SHOW_CLEAN_SPACE_DIALOG_FAILURE_WITH_NO_SCAN_RESULT = 2;
    public static final int SHOW_CLEAN_SPACE_DIALOG_FAILURE_WITH_SWITCH_OFF = 4;
    public static final int SHOW_CLEAN_SPACE_DIALOG_FAILURE_WITH_UNSUPPORT_STATUS = 3;
    public static final int SHOW_CLEAN_SPACE_DIALOG_SUCCESS = 1;
    public static final String SP_AD_DOWNLOAD_EVENT = "sp_ad_download_event";
    public static final String SP_ANTI_MI = "sp_a_b_c";
    public static final String SP_CACHE_NAME = "sp_download_finish_cache";
    public static final String SP_CLEAN = "sp_ttdownloader_clean";
    public static final String SP_DELAY_OPERATION_INFO = "sp_delay_operation_info";
    public static final String SP_MD5 = "sp_ttdownloader_md5";
    public static final String SP_NAME = "sp_webview_ad_download_info";
    public static final String SP_NAME_INSTALLED_APP = "sp_name_installed_app";
    public static final String SP_NAME_MISC_CONFIG = "misc_config";
    public static final String SP_NAME_UNINSTALLED_APP = "sp_ad_install_back_dialog";
    public static final String SP_ORDER_DOWNLOAD = "sp_order_download";
    public static final int STATUS_DOWNLOAD_FINISH = 2;
    public static final int STATUS_START_DOWNLOAD = 1;
    public static final String USER_AGENT = "User-Agent";

    @Retention(RetentionPolicy.SOURCE)
    public @interface DOWNLOAD_STATUS {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Message {
        public static final int FROM_DOWNLOADER = 3;
        public static final int QUICK_APP_BUTTON_CLICK = 4;
        public static final int QUICK_APP_ITEM_CLICK = 5;
        public static final int QUICK_APP_VIDEO_ITEM_CLICK = 7;
    }
}
