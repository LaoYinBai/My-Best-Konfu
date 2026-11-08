package com.bytedance.sdk.account.api;

import android.net.Uri;
import com.bytedance.sdk.account.utils.IProjectMode;
import com.ss.android.account.TTAccountInit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class BDAccountNetApi {

    public static class Platform {
        private static final String ACCOUNT_USERINFO_URL_PATH = "/passport/account/info/";
        private static final String ACCOUNT_USERINFO_URL_PATH_NEW = "/passport/account/info/v2/";
        private static final String AUTH_BIND_WITH_MOBILE_BIND_PATH = "/passport/auth/bind_with_mobile_authorize/";
        private static final String AUTH_BIND_WITH_MOBILE_LOGIN_PATH = "/passport/auth/bind_with_mobile_login/";
        private static final String BIND_WITH_MOBILE_PATH = "/passport/auth/bind_with_mobile/";
        private static final String LOGIN_CONTINUE_URL_PATH = "/2/auth/login_continue/";
        private static final String LOGIN_URL_PATH = "/passport/auth/wap_login/";
        public static final String PROJECT_MODE_SCHEME = "http://";
        private static final String SCHEME = "https://";
        private static final String SSO_AUTH_REGISTER = "/passport/auth/register/";
        private static final String SSO_CALLBACK_BIND_PATH = "/passport/auth/bind/";
        private static final String SSO_CALLBACK_URL_PATH = "/passport/auth/login/";
        public static final String SSO_LOGIN_ONLY_PATH = "/passport/auth/login_only/";
        private static final String SSO_SWITCH_BIND_PATH = "/passport/auth/switch_bind/";
        private static final String UNBIND_URL_PATH_NEW = "/passport/auth/unbind/";
        private static final String USERINFO_URL_PATH = "/2/user/info/";
        private static final String USER_LOGOUT_URL_PATH = "/2/user/logout/";
        private static final String USER_LOGOUT_URL_PATH_NEW = "/passport/user/logout/";

        public static String host() {
            return TTAccountInit.getConfig().host();
        }

        private static String accountUrl(String str) {
            IProjectMode projectMode = TTAccountInit.getProjectMode();
            if (projectMode != null && projectMode.isProjectMode()) {
                return PROJECT_MODE_SCHEME + host() + str;
            }
            return "https://" + host() + str;
        }

        public static String getLoginUrl() {
            return accountUrl(LOGIN_URL_PATH);
        }

        public static String getLoginContinueUrl() {
            return accountUrl(LOGIN_CONTINUE_URL_PATH);
        }

        public static String getUnbindUrl() {
            return accountUrl(UNBIND_URL_PATH_NEW);
        }

        public static String getSsoSwitchBind() {
            return accountUrl(SSO_SWITCH_BIND_PATH);
        }

        public static String getSsoCallbackUrl() {
            return accountUrl(SSO_CALLBACK_URL_PATH);
        }

        public static String getSsoOnlyLoginUrl() {
            return accountUrl(SSO_LOGIN_ONLY_PATH);
        }

        public static String getSsoAuthRegister() {
            return accountUrl(SSO_AUTH_REGISTER);
        }

        public static String getSsoCallbackBind() {
            return accountUrl(SSO_CALLBACK_BIND_PATH);
        }

        public static String getUserLogoutUrl() {
            return accountUrl(USER_LOGOUT_URL_PATH_NEW);
        }

        public static String getUserinfoUrl() {
            return accountUrl(USERINFO_URL_PATH);
        }

        public static String getAccountUserInfo() {
            return accountUrl(ACCOUNT_USERINFO_URL_PATH);
        }

        public static String getAuthBindWithMobileBind() {
            return accountUrl(AUTH_BIND_WITH_MOBILE_BIND_PATH);
        }

        public static String getAuthBindWithMobileLogin() {
            return accountUrl(AUTH_BIND_WITH_MOBILE_LOGIN_PATH);
        }

        public static String getBindWithMobilePath() {
            return accountUrl(BIND_WITH_MOBILE_PATH);
        }

        public static String getNewAccountUserInfo() {
            return accountUrl(ACCOUNT_USERINFO_URL_PATH_NEW);
        }

        public static String getLoginUrl(String str) {
            return getLoginUrl() + "?platform=" + Uri.encode(str);
        }

        public static String getLoginContinueUrl(String str, String str2) {
            return getLoginContinueUrl() + "?platform=" + Uri.encode(str) + "&auth_token=" + Uri.encode(str2) + "&unbind_exist=1";
        }

        public static String getLogoutUrl(String str) {
            return getUnbindUrl() + "?platform=" + Uri.encode(str);
        }
    }

    public static class Account {
        public static final String AFTER_REGISTER_LOGIN_BY_TICKET_PATH = "/passport/mobile/login_by_ticket/";
        private static final String AUTHORIZE_APP_SCAN_QRCODE = "/passport/mobile/scan_qrcode/";
        private static final String AUTHORIZE_QRCODE_TO_LOGIN = "/passport/mobile/confirm_qrcode/";
        public static final String AUTH_PATH = "/passport/auth/authorize/";
        public static final String AUTH_TICKET_PATH = "/passport/auth/auth_get_ticket/";
        public static final String AVAILABLE_WAYS_PATH = "/passport/auth/available_ways/";
        protected static final String BEAT_URL = "/passport/token/beat/v2/";
        public static final String BIND_LOGIN = "/passport/mobile/bind_login/";
        public static final String CANCEL_CLOSE_ACCOUNT = "/passport/cancel/login/";
        public static final String CANCEL_DO = "/passport/cancel/do/";
        public static final String CANCEL_INDEX = "/passport/cancel/index/";
        public static final String CANCEL_POST = "/passport/cancel/post/";
        public static final String CAN_AWEME_QUICK_LOGIN_PATH = "/passport/can_aweme_quick_login/";
        public static final String CHECK_CODE = "/passport/mobile/check_code/";
        public static final String CHECK_ENV_PATH = "/passport/safe/check_env/v1/";
        public static final String CHECK_LOGIN_PATH = "/passport/open/check_login/";
        public static final String CHECK_MOBILE_REGISTERED_PATH = "/passport/user/check_mobile_registered";
        public static final String CHECK_MOBILE_UNUSABLE_PATH = "/passport/mobile/check_unusable/";
        public static final String CHECK_TV_QRCONNECT = "/passport/related_login/check_qrconnect/";
        public static final String CHECK_USER_NAME = "/2/user/check_name/";
        public static final String DEL_DEVICE_PATH = "/passport/safe/login_device/del/";
        public static final String DEVICES_LIST_PATH = "/passport/safe/login_device/list/";
        public static final String EMAIL_CHECK_CODE = "/passport/email/check_code/";
        public static final String EMAIL_CHECK_REGISTER_PATH = "/passport/user/check_email_registered";
        public static final String EMAIL_LOGIN_PATH_NEW = "/passport/email/login/";
        public static final String EMAIL_REGISTER_PATH_NEW = "/passport/email/register/v2/";
        public static final String EMAIL_REGISTER_VERIFY_LOGIN_PATH = "/passport/email/register_verify_login/";
        public static final String EMAIL_REGISTER_VERIFY_PATH = "/passport/email/register_verify/";
        public static final String EMAIL_RESET_PASSWORD = "/passport/password/reset_by_email_ticket/";
        public static final String EMAIL_SEND_CODE_PATH = "/passport/email/send_code/";
        public static final String GENERATE_USER_INFO_TICKET_PATH = "/passport/mobile/generate_user_info_ticket/";
        private static final String GET_QRCODE = "/passport/mobile/get_qrcode/";
        private static final String GET_QRCODE_STATUS = "/passport/mobile/check_qrconnect/";
        public static final String GET_TV_QRCODE = "/passport/related_login/get_qrcode/";
        public static final String LOGIN_BY_TICKET_PATH = "/passport/auth/one_login_by_ticket/";
        public static final String LOGOUT_OTHERS_PATH = "/passport/account/logout_others/";
        public static final String MOBILE_AUTH_PATH = "/passport/mobile/authorize/";
        public static final String MOBILE_CHECK_PATH = "/passport/mobile/check/";
        public static final String ONE_LOGIN_PATH = "/passport/one_login/";
        public static String PROJECT_MODE_SCHEME = "http://";
        public static final String PWD_CHECK_PATH = "/passport/password/check/";
        public static final String PWD_UPDATE_PATH = "/passport/password/update/";
        public static final String QUICK_AUTH_LOGIN_PATH = "/passport/auth/one_login/";
        public static String SCHEME = "https://";
        public static final String SET_PASS_PATH = "/passport/password/set/";
        public static final String SHARE_LOGIN_PATH = "/passport/share_login/";
        public static final String SMS_AUTH_PATH = "/passport/sms/authorize/";
        public static final String SWITCH_AUTH_PATH = "/passport/account/switch/";
        public static final String SWITCH_TICKET = "/passport/auth/switch_ticket/";
        public static final String TICKET_RESET_PASSOWRD = "/passport/password/reset_by_ticket/";
        public static final String USER_BIND_MOBILE_V1_PATH_NEW = "/passport/mobile/bind/v1/";
        public static final String USER_BIND_MOBILE_V2_PATH_NEW = "/passport/mobile/bind/v2/";
        public static final String USER_CHANGE_MOBILE_PATH_NEW = "/passport/mobile/change/v1/";
        public static final String USER_CHANGE_PASSWORD_PATH_NEW = "/passport/password/change/";
        public static final String USER_LOGIN_PATH = "/passport/mobile/login/";
        public static final String USER_ONE_BIND_MOBILE_V1_PATH = "/passport/auth/one_bind_mobile/";
        public static final String USER_ONE_BIND_MOBILE_V2_PATH = "/passport/auth/one_bind_mobile/v2/";
        public static final String USER_PWD_LOGIN_PATH = "/passport/user/login/";
        public static final String USER_PWD_REGISTER_PATH = "/passport/username/register/";
        public static final String USER_QUICK_LOGIN_CONTINUE_PAH = "/passport/mobile/sms_login_continue/";
        public static final String USER_QUICK_LOGIN_ONLY_PAH = "/passport/mobile/sms_login_only/";
        public static final String USER_QUICK_LOGIN_PAH = "/passport/mobile/sms_login/";
        private static final String USER_REFRESH_CAPTCHA_PATH_NEW = "/passport/mobile/refresh_captcha/";
        public static final String USER_REGISTER_PATH = "/passport/mobile/register/";
        public static final String USER_RESET_PASSWORD_PATH_NEW = "/passport/password/reset/";
        public static final String USER_SEND_CODE_PATH = "/passport/mobile/send_code/v1/";
        public static final String USER_SEN_VOICO_CODE_PATH = "/passport/mobile/send_voice_code/";
        public static final String USER_UNBIND_MOBILE_PATH_NEW = "/passport/mobile/unbind/";
        public static final String VALIDATE_SMS_CODE_PATH = "/passport/mobile/validate_code/v1/";
        public static final String VERIFY_ACCOUNT_PASSWORD_PATH = "/passport/account/verify/";

        public static String host() {
            return TTAccountInit.getConfig().host();
        }

        public static String getPath(String str) {
            IProjectMode projectMode = TTAccountInit.getProjectMode();
            if (projectMode != null && projectMode.isProjectMode()) {
                return PROJECT_MODE_SCHEME + host() + str;
            }
            return SCHEME + host() + str;
        }

        public static String getTokenBeatHost() {
            IProjectMode projectMode = TTAccountInit.getProjectMode();
            if (projectMode != null && projectMode.isProjectMode()) {
                return PROJECT_MODE_SCHEME + host();
            }
            return SCHEME + host();
        }

        public static String getUserRefreshCaptcha() {
            return getPath(USER_REFRESH_CAPTCHA_PATH_NEW);
        }

        public static String getSwitchTicket() {
            return getPath(SWITCH_TICKET);
        }

        public static String getUserSendCode() {
            return getPath(USER_SEND_CODE_PATH);
        }

        public static String getUserRegister() {
            return getPath(USER_REGISTER_PATH);
        }

        public static String getUserLogin() {
            return getPath(USER_LOGIN_PATH);
        }

        public static String getEmailLogin() {
            return getPath(EMAIL_LOGIN_PATH_NEW);
        }

        public static String getEmailRegister() {
            return getPath(EMAIL_REGISTER_PATH_NEW);
        }

        public static String getUserQuickLogin() {
            return getPath(USER_QUICK_LOGIN_PAH);
        }

        public static String getUserQuickOnlyLogin() {
            return getPath(USER_QUICK_LOGIN_ONLY_PAH);
        }

        public static String getUserQuickContinueLogin() {
            return getPath(USER_QUICK_LOGIN_CONTINUE_PAH);
        }

        public static String getUserResetPassword() {
            return getPath(USER_RESET_PASSWORD_PATH_NEW);
        }

        private static String getDebugPath(String str, String str2, String str3) {
            return str + str2 + str3;
        }

        public static String getCheckCode() {
            return getPath(CHECK_CODE);
        }

        public static String getTicketResetPassowrd() {
            return getPath(TICKET_RESET_PASSOWRD);
        }

        public static String getEmailCheckCode() {
            return getPath(EMAIL_CHECK_CODE);
        }

        public static String getEmailTicketResetPassword() {
            return getPath(EMAIL_RESET_PASSWORD);
        }

        public static String getEmailSendCode() {
            return getPath(EMAIL_SEND_CODE_PATH);
        }

        public static String getEmailRegisterVerifyPath() {
            return getPath(EMAIL_REGISTER_VERIFY_PATH);
        }

        public static String getEmailRegisterVerifyLoginPath() {
            return getPath(EMAIL_REGISTER_VERIFY_LOGIN_PATH);
        }

        public static String getEmailCheckRegisterPath() {
            return getPath(EMAIL_CHECK_REGISTER_PATH);
        }

        public static String getUserChangePassword() {
            return getPath(USER_CHANGE_PASSWORD_PATH_NEW);
        }

        public static String getUserBindMobileV2() {
            return getPath(USER_BIND_MOBILE_V2_PATH_NEW);
        }

        public static String getUserBindMobileV1() {
            return getPath(USER_BIND_MOBILE_V1_PATH_NEW);
        }

        public static String getUserUnbindMobile() {
            return getPath(USER_UNBIND_MOBILE_PATH_NEW);
        }

        public static String getUserChangeMobile() {
            return getPath(USER_CHANGE_MOBILE_PATH_NEW);
        }

        public static String getSetPass() {
            return getPath(SET_PASS_PATH);
        }

        public static String getCheckUserName() {
            return getPath(CHECK_USER_NAME);
        }

        public static String getCancelPost() {
            return getPath(CANCEL_POST);
        }

        public static String getCancelDo() {
            return getPath(CANCEL_DO);
        }

        public static String getCancelIndex() {
            return getPath(CANCEL_INDEX);
        }

        public static String getValidateCodePath() {
            return getPath(VALIDATE_SMS_CODE_PATH);
        }

        public static String getUserPasswordLogin() {
            return getPath(USER_PWD_LOGIN_PATH);
        }

        public static String getUserPasswordRegister() {
            return getPath(USER_PWD_REGISTER_PATH);
        }

        public static String getMobileCheckPath() {
            return getPath(MOBILE_CHECK_PATH);
        }

        public static String getVoiceCodePath() {
            return getPath(USER_SEN_VOICO_CODE_PATH);
        }

        public static String getMergeAuthPath() {
            return getPath(AUTH_PATH);
        }

        public static String getSMSAuthPath() {
            return getPath(SMS_AUTH_PATH);
        }

        public static String getMobileAuthPath() {
            return getPath(MOBILE_AUTH_PATH);
        }

        public static String getSwitchAuthHost() {
            return getPath(SWITCH_AUTH_PATH);
        }

        public static String getSwitchAuthPath() {
            return new StringBuilder(getPath(SWITCH_AUTH_PATH)).toString();
        }

        public static String getCancelCLoseAccountPath() {
            return getPath(CANCEL_CLOSE_ACCOUNT);
        }

        public static String getTVQrcodePath() {
            return getPath(GET_TV_QRCODE);
        }

        public static String getTVCheckQrconnectPath() {
            return getPath(CHECK_TV_QRCONNECT);
        }

        public static String getDeviceListPath() {
            return getPath(DEVICES_LIST_PATH);
        }

        public static String getDelDevicePath() {
            return getPath(DEL_DEVICE_PATH);
        }

        public static String getCheckEnvPath() {
            return getPath(CHECK_ENV_PATH);
        }

        public static String getPwdCheckPath() {
            return getPath(PWD_CHECK_PATH);
        }

        public static String getUpdatePwdPath() {
            return getPath(PWD_UPDATE_PATH);
        }

        public static String getAuthTicketPath() {
            return getPath(AUTH_TICKET_PATH);
        }

        public static String getLoginByTicketPath() {
            return getPath(LOGIN_BY_TICKET_PATH);
        }

        public static String getQuickAuthLoginPath() {
            return getPath(QUICK_AUTH_LOGIN_PATH);
        }

        public static String getOneBindMobileV1() {
            return getPath(USER_ONE_BIND_MOBILE_V1_PATH);
        }

        public static String getOneBindMobileV2() {
            return getPath(USER_ONE_BIND_MOBILE_V2_PATH);
        }

        public static String getBindLogin() {
            return getPath(BIND_LOGIN);
        }

        public static String getQRCodePath() {
            return getPath(GET_QRCODE);
        }

        public static String getQRCodeStatusPath() {
            return getPath(GET_QRCODE_STATUS);
        }

        public static String getAuthorizeScanQRCodePath() {
            return getPath(AUTHORIZE_APP_SCAN_QRCODE);
        }

        public static String getAuthorizeQRCodeToLoginPath() {
            return getPath(AUTHORIZE_QRCODE_TO_LOGIN);
        }

        public static String getLogoutOthersPath() {
            return getPath(LOGOUT_OTHERS_PATH);
        }

        public static String getCheckMobileUnusablePath() {
            return getPath(CHECK_MOBILE_UNUSABLE_PATH);
        }

        public static String getAvailableWaysPath() {
            return getPath(AVAILABLE_WAYS_PATH);
        }

        public static String getVerifyAccountPasswordPath() {
            return getPath(VERIFY_ACCOUNT_PASSWORD_PATH);
        }

        public static String getGenerateUserInfoTicketPath() {
            return getPath(GENERATE_USER_INFO_TICKET_PATH);
        }

        public static String getAfterRegisterLoginByTicketPath() {
            return getPath(AFTER_REGISTER_LOGIN_BY_TICKET_PATH);
        }

        public static String getCheckMobileRegisteredPath() {
            return getPath(CHECK_MOBILE_REGISTERED_PATH);
        }

        public static String getCheckLoginPath() {
            return getPath(CHECK_LOGIN_PATH);
        }

        public static String getCanAwemeQuickLoginPath() {
            return getPath(CAN_AWEME_QUICK_LOGIN_PATH);
        }
    }
}
