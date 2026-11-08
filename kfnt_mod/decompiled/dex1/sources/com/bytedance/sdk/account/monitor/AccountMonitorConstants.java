package com.bytedance.sdk.account.monitor;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface AccountMonitorConstants {
    public static final String EVENT_LOGIN_FINALIZE = "passport_login_finalize";
    public static final String EVENT_LOGOUT = "passport_logout";
    public static final String EVENT_LOGOUT_OTHERS = "passport_logout_others";
    public static final int NEW_SDK_VERSION = 2;
    public static final String NEW_SDK_VERSION_KEY = "sdkVersion";
    public static final String SDK_SOURCE = "account_sdk_source";

    public interface CommonParameter {
        public static final String AUTH_ERROR_CANCEL = "errorCancel";
        public static final String AUTH_ERROR_CODE = "errCode";
        public static final String AUTH_ERROR_DESC = "errDesc";
        public static final String ERROR = "errorCode";
        public static final String ERRORDESC = "errDesc";
        public static final String EXTRA = "extra";
        public static final String LOGID = "logid";
        public static final String LOGIN_ERROR_CODE = "login_error_code";
        public static final String LOGIN_ERROR_MSG = "login_error_msg";
        public static final String LOGIN_PAGE = "login_page";
        public static final String LOGIN_PLATFORM = "login_platform";
        public static final String LOGIN_PLATFORM_LIST = "login_platform_list";
        public static final String LOGIN_POSITION = "login_position";
        public static final String LOGIN_RESULT = "login_result";
        public static final String LOGOUT_SCENE = "logout_from";
        public static final String PLATFORM = "platform";
        public static final String RAW = "raw";
        public static final String RESULT = "result";
        public static final String RESULT_FAIL = "fail";
        public static final String RESULT_SUCCESS = "success";
        public static final String SCENE = "scene";
        public static final String SENDCODE_ERROR_CODE = "sendcode_error_code";
        public static final String SENDCODE_ERROR_MSG = "sendcode_error_msg";
        public static final String SENDCODE_POSITION = "sendcode_position";
        public static final String SENDCODE_RESULT = "result_value";
        public static final String URLPATH = "urlpath";
    }

    public interface EventAccount {
        public static final String AUTH_ONE_LOGIN = "passport_auth_one_login";
        public static final String AUTH_ONE_LOGIN_BY_TICKET = "passport_auth_one_login_by_ticket";
        public static final String AUTH_ONE_LOGIN_GET_TICKET = "passport_auth_get_ticket";
        public static final String CANCEL_LOGIN = "passport_cancel_login";
        public static final String CAN_AWEME_QUICK_LOGIN = "passport_can_aweme_quick_login";
        public static final String CHECK_ENV = "passport_check_env";
        public static final String CHECK_LOGIN = "passport_open_check_login";
        public static final String CHECK_PASSWORD = "passport_check_password";
        public static final String DELETE_LOGIN_DEVICE = "passport_login_device_del";
        public static final String EMAIL_REGISTER = "passport_email_register";
        public static final String GET_ACCOUNT_INFO = "passport_account_info";
        public static final String GET_AVAILABLE_WAYS = "passport_auth_available_ways";
        public static final String GET_LOGIN_DEVICE = "passport_login_device_list";
        public static final String LOGIN = "passport_account_login";
        public static final String RECAPTCHA_CAPTCHA = "passport_account_recaptcha_captcha";
        public static final String REGISTER = "passport_account_register";
        public static final String SHOW_CAPTCHA = "passport_account_show_captcha";
        public static final String UPDATE_PWD = "passport_update_pwd";
        public static final String VERIFY_ACCOUNT_PASSWORD = "passport_account_verify";
    }

    public interface EventMobile {
        public static final String BIND = "passport_mobile_bind";
        public static final String BIND_LOGIN = "passport_mobile_bind_login";
        public static final String CHANGE = "passport_mobile_change";
        public static final String CHANGE_PASSWORD = "passport_mobile_change_password";
        public static final String CHECK_MOBILE_REGISTERED = "passport_user_check_mobile_registered";
        public static final String CHECK_MOBILE_UNUSABLE = "passport_mobile_check_unusable";
        public static final String EMAIL_CHECK_REGISTER = "passport_email_check_register";
        public static final String EMAIL_REGISTER_VERIFY = "passport_email_register_verify";
        public static final String EMAIL_RESET_PASSWORD = "passport_email_reset_password";
        public static final String EMAIL_SEND_CODE = "passport_email_send_code";
        public static final String GENERATE_USER_INFO_TICKET = "passport_mobile_generate_user_info_ticket";
        public static final String LOGIN = "passport_mobile_login";
        public static final String LOGIN_BY_TICKET_AFTER_REGISTER = "passport_mobile_login_by_ticket";
        public static final String LOGIN_CONTINUE = "passport_mobile_login_continue";
        public static final String LOGIN_ONLY = "passport_mobile_login_only";
        public static final String ONE_BIND_MOBILE = "passport_one_bind_mobile";
        public static final String RECAPTCHA_CAPTCHA = "passport_recaptcha_captcha";
        public static final String REGISTER = "passport_mobile_register";
        public static final String RESET_PASSWORD = "passport_mobile_reset_password";
        public static final String SEND_CODE = "passport_mobile_sendcode";
        public static final String SET_PASSWORD = "passport_mobile_set_password";
        public static final String SHOW_CAPTCHA = "passport_show_captcha";
    }

    public interface EventPlatform {
        public static final String AUTH_BIND_WITH_MOBILE_LOGIN = "passport_auth_bind_with_mobile_login_click";
        public static final String BIND_WITH_MOBILE = "passport_oauth_bind_with_mobile_click";
        public static final String DIALOG_SHOW = "passport_dialog_show";
        public static final String LOGIN_CLICK_EVENT = "login_click";
        public static final String LOGIN_CLICK_RESULT_EVENT = "login_click_result";
        public static final String LOGIN_ENTRANCE_EVENT = "login_entrance";
        public static final String LOGIN_PAGE_SHOW_EVENT = "login_page_show";
        public static final String MOBILE_CODE_CLICK_EVENT = "mobile_code_click";
        public static final String MOBILE_CODE_INPUT_EVENT = "mobile_code_input";
        public static final String MOBILE_CODE_SUBMIT_EVENT = "mobile_code_submit";
        public static final String MOBILE_CODE_SUBMIT_RESULT_EVENT = "mobile_code_submit_result";
        public static final String OAUTH_APICALL = "passport_oauth_apicall";
        public static final String OAUTH_BIND_CLICK = "passport_oauth_bind_click";
        public static final String OAUTH_CALLBACK = "passport_oauth_callback";
        public static final String OAUTH_GET_CODE_TOKEN = "passport_oauth_callback";
        public static final String OAUTH_LOGIN_CLICK = "passport_oauth_login_click";
        public static final String OAUTH_LOGIN_RESULT = "passport_oauth_result";
        public static final String OAUTH_SWITCH_CLICK = "passport_oauth_switch_click";
        public static final String OAUTH_UBIND_CLICK = "passport_oauth_unbind_click";
        public static final String SCENE_BIND = "auth_bind";
        public static final String SCENE_BIND_RESULT = "passport_oauth_bind_result";
        public static final String SCENE_FORCE_BIND_MOBILE = "auth_force_bind_mobile";
        public static final String SCENE_UNBIND = "auth_unbind";
    }

    public interface EventTVQR {
        public static final String CHECK_QRCONNECT = "passport_related_login_check_qrconnect";
        public static final String GET_QRCODE = "passport_related_login_get_qrcode";
    }

    public interface LoginUiScene {
        public static final String BIND_MOBILE = "enter_bind_mobile_page";
        public static final String CHANGE_MOBILE_NUM = "enter_change_mobile_num_page";
        public static final String CHANGE_PASSWORD = "enter_change_password_page";
        public static final String DIALOG_BIND_EXIST = "show_dialog_bind_exist";
        public static final String DIALOG_BIND_MOBILE = "show_dialog_bind_mobile";
        public static final String DIALOG_CHANGE_PASSWORD = "show_dialog_change_password";
        public static final String DIALOG_LOGOUT_CONFIRM = "show_dialog_logout_confirm";
        public static final String DIALOG_QUICK_LOGIN = "show_dialog_quick_login";
        public static final String DIALOG_UNBIND_CONFIRM = "show_dialog_unbind_confirm";
        public static final String LOGIN_AUTHCODE = "enter_login_authcode_page";
        public static final String LOGIN_EMAIL = "enter_login_email_page";
        public static final String LOGIN_PASSWORD = "enter_login_password_page";
        public static final String REGISTER = "enter_register_page";
        public static final String RESET_PASSWORD = "enter_reset_password_page";
        public static final String RETRIEVE_PASSWORD = "enter_retrieve_password_page";
    }

    public interface Result {
        public static final int FAILURE = 0;
        public static final int SUCCESS = 1;
    }

    public interface Scene {
        public static final String SCENE_AUTH = "auth";
        public static final String SCENE_AUTH_BIND = "bind";
        public static final String SCENE_AUTH_LOGIN = "login";
    }

    public interface SendCodeScene {
        public static final String MOBILE_BIND = "mobile_bind";
        public static final String MOBILE_LOGIN = "mobile";
        public static final String MOBILE_REGISTER = "mobile_register";
        public static final String OTHERS = "others";
    }
}
