package com.ss.android.socialbase.appdownloader.setting;

import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface DownloadSettingKeys extends com.ss.android.socialbase.downloader.setting.DownloadSettingKeys {
    public static final String AUTO_INSTALL_WHEN_RESUME = "auto_install_when_resume";
    public static final String BACK_MIUI_SILENT_INSTALL = "back_miui_silent_install";
    public static final String DEFAULT_SAVE_DIR_NAME = "default_save_dir_name";
    public static final String FAILED_RESUME_MAX_COUNT = "failed_resume_max_count";
    public static final String FAILED_RESUME_MAX_HOURS = "failed_resume_max_hours";
    public static final String FAILED_RESUME_MIN_HOURS = "failed_resume_min_hours";
    public static final String FAILED_RESUME_NEED_WAIT_WIFI = "failed_resume_need_wait_wifi";
    public static final String FAILED_RESUME_NEED_WIFI = "failed_resume_need_wifi";
    public static final String INSTALL_WITH_SAME_VERSION_CODE = "install_with_same_version_code";
    public static final String KEY_INSTALL_ON_RESUME_INSTALL_INTERVAL = "install_on_resume_install_interval";
    public static final String KEY_INSTALL_QUEUE_ENABLE = "install_queue_enable";
    public static final String KEY_INSTALL_QUEUE_SIZE = "install_queue_size";
    public static final String KEY_INSTALL_QUEUE_TIMEOUT = "install_queue_timeout";
    public static final String MODIFY_FORCE = "modify_force";
    public static final String NOTIFICATION_CLICK_INSTALL_AUTO_CANCEL = "notification_click_install_auto_cancel";
    public static final String NOTIFICATION_ONGOING = "notification_ongoing";
    public static final String NOTIFICATION_OPT_2 = "notification_opt_2";
    public static final String NOTI_PROGRESS_SHOW_TH = "noti_progress_show_th";
    public static final String NO_HIDE_NOTIFICATION = "no_hide_notification";
    public static final String OPT_NOTIFICATION_UI = "enable_notification_ui";
    public static final String PAUSED_RESUME_MAX_COUNT = "paused_resume_max_count";
    public static final String PAUSED_RESUME_MAX_HOURS = "paused_resume_max_hours";
    public static final String UNINSTALL_RESUME_MAX_COUNT = "uninstall_resume_max_count";
    public static final String UNINSTALL_RESUME_MAX_HOURS = "uninstall_resume_max_hours";
    public static final String UNINSTALL_RESUME_MIN_HOURS = "uninstall_resume_min_hours";

    public interface BugFix extends DownloadSettingKeys.BugFix {
        public static final String BUGFIX_INSTALL_CALLBACK_ERROR = "install_callback_error";
        public static final String FIX_UI_THREAD_PARSER_APK_FILE = "fix_ui_thread_parser_apk_file";
        public static final String UNINSTALL_CAN_NOT_RESUME_FOR_FORCE_TASK = "uninstall_can_not_resume_for_force_task";
    }
}
