package com.bytedance.sdk.account.api;

import com.bytedance.sdk.account.api.call.AbsApiCall;
import com.bytedance.sdk.account.api.call.CheckMobileRegisterResponse;
import com.bytedance.sdk.account.api.call.LogoutApiResponse;
import com.bytedance.sdk.account.api.call.LogoutOthersApiResponse;
import com.bytedance.sdk.account.api.callback.AuthTicketCallback;
import com.bytedance.sdk.account.api.callback.AuthorizeQRCodeLoginCallback;
import com.bytedance.sdk.account.api.callback.CanAwemeQuickLoginCallback;
import com.bytedance.sdk.account.api.callback.CancelCloseAccountCallback;
import com.bytedance.sdk.account.api.callback.CancelDoCallback;
import com.bytedance.sdk.account.api.callback.CancelIndexCallback;
import com.bytedance.sdk.account.api.callback.CancelPostCallback;
import com.bytedance.sdk.account.api.callback.CheckCodeCallback;
import com.bytedance.sdk.account.api.callback.CheckEvnCallback;
import com.bytedance.sdk.account.api.callback.CheckMobileUnusableCallback;
import com.bytedance.sdk.account.api.callback.CheckPwdCallback;
import com.bytedance.sdk.account.api.callback.CheckQRCodeStatusCallback;
import com.bytedance.sdk.account.api.callback.DeleteDeviceCallback;
import com.bytedance.sdk.account.api.callback.GenerateUserInfoTicketCallback;
import com.bytedance.sdk.account.api.callback.GetAccountInfoCallback;
import com.bytedance.sdk.account.api.callback.GetAvailableWaysCallback;
import com.bytedance.sdk.account.api.callback.GetLoginDevicesCallback;
import com.bytedance.sdk.account.api.callback.GetQRCodeCallback;
import com.bytedance.sdk.account.api.callback.GetTvQRCodeCallback;
import com.bytedance.sdk.account.api.callback.LoginByTicketAfterRegisterCallback;
import com.bytedance.sdk.account.api.callback.LoginByTicketCallback;
import com.bytedance.sdk.account.api.callback.ScanQRCodeCallback;
import com.bytedance.sdk.account.api.callback.SwitchAuthCallback;
import com.bytedance.sdk.account.api.callback.SwitchTicketCallback;
import com.bytedance.sdk.account.api.callback.TVCheckQRConnectCallback;
import com.bytedance.sdk.account.api.callback.TicketResetPasswordCallback;
import com.bytedance.sdk.account.api.callback.UpdatePwdCallback;
import com.bytedance.sdk.account.api.callback.VerifyAccountPasswordCallback;
import com.bytedance.sdk.account.mobile.thread.call.BindLoginCallback;
import com.bytedance.sdk.account.mobile.thread.call.BindMobileCallback;
import com.bytedance.sdk.account.mobile.thread.call.ChangeMobileNumCallback;
import com.bytedance.sdk.account.mobile.thread.call.ChangePasswordCallback;
import com.bytedance.sdk.account.mobile.thread.call.EmailCheckRegisterCallback;
import com.bytedance.sdk.account.mobile.thread.call.EmailLoginQueryCallback;
import com.bytedance.sdk.account.mobile.thread.call.EmailRegisterQueryCallback;
import com.bytedance.sdk.account.mobile.thread.call.EmailRegisterVerifyCallback;
import com.bytedance.sdk.account.mobile.thread.call.EmailSendCodeCallback;
import com.bytedance.sdk.account.mobile.thread.call.LoginQueryCallback;
import com.bytedance.sdk.account.mobile.thread.call.MobilePassAuthCallback;
import com.bytedance.sdk.account.mobile.thread.call.MobileQuickAuthCallback;
import com.bytedance.sdk.account.mobile.thread.call.OneBindMobileCallback;
import com.bytedance.sdk.account.mobile.thread.call.OneForceBindMobileCallback;
import com.bytedance.sdk.account.mobile.thread.call.QuickLoginCallback;
import com.bytedance.sdk.account.mobile.thread.call.QuickLoginContinueCallback;
import com.bytedance.sdk.account.mobile.thread.call.QuickLoginOnlyCallback;
import com.bytedance.sdk.account.mobile.thread.call.RefreshCaptchaCallback;
import com.bytedance.sdk.account.mobile.thread.call.RegisterCallback;
import com.bytedance.sdk.account.mobile.thread.call.ResetPasswordCallback;
import com.bytedance.sdk.account.mobile.thread.call.SendCodeCallback;
import com.bytedance.sdk.account.mobile.thread.call.SetPassCall;
import com.bytedance.sdk.account.mobile.thread.call.UnbindMobileCallback;
import com.bytedance.sdk.account.mobile.thread.call.UserPasswordLoginQueryCallback;
import com.bytedance.sdk.account.mobile.thread.call.UserPasswordRegisterQueryCallback;
import com.bytedance.sdk.account.mobile.thread.call.ValidateCodeCallBack;
import com.bytedance.sdk.account.mobile.thread.call.VerifyDeviceCallBack;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IBDAccountAPI {
    public static final int DEFAULT_RESEND_TIME = 30;
    public static final int OP_ERROR_USER_EXIST = 1001;

    public interface Scenario extends ISendCodeScenario {
    }

    void accountEmailLogin(String str, String str2, String str3, UserPasswordLoginQueryCallback userPasswordLoginQueryCallback);

    @Deprecated
    void accountLogin(String str, String str2, String str3, int i, UserPasswordLoginQueryCallback userPasswordLoginQueryCallback);

    void accountMobileLogin(String str, String str2, String str3, UserPasswordLoginQueryCallback userPasswordLoginQueryCallback);

    void accountUserNameLogin(String str, String str2, String str3, UserPasswordLoginQueryCallback userPasswordLoginQueryCallback);

    void accountUserNameRegister(String str, String str2, UserPasswordRegisterQueryCallback userPasswordRegisterQueryCallback);

    void authorizeQRCodeLogin(String str, String str2, String str3, AuthorizeQRCodeLoginCallback authorizeQRCodeLoginCallback);

    void authorizeScanQRCode(String str, ScanQRCodeCallback scanQRCodeCallback);

    void bindLogin(String str, String str2, String str3, String str4, BindLoginCallback bindLoginCallback);

    void bindLogin(String str, String str2, String str3, String str4, Map map, BindLoginCallback bindLoginCallback);

    void bindMobile(String str, String str2, String str3, String str4, int i, BindMobileCallback bindMobileCallback);

    void bindMobile(String str, String str2, String str3, String str4, BindMobileCallback bindMobileCallback);

    void bindMobileNoPassword(String str, String str2, String str3, int i, BindMobileCallback bindMobileCallback);

    void canAwemeQuickLogin(CanAwemeQuickLoginCallback canAwemeQuickLoginCallback);

    void cancelCloseAccountWithToken(String str, CancelCloseAccountCallback cancelCloseAccountCallback);

    void cancelDo(boolean z, CancelDoCallback cancelDoCallback);

    void cancelIndex(CancelIndexCallback cancelIndexCallback);

    void cancelPost(String str, String str2, String str3, String str4, CancelPostCallback cancelPostCallback);

    void changeMobileNum(String str, String str2, String str3, ChangeMobileNumCallback changeMobileNumCallback);

    void changeMobileNum(String str, String str2, String str3, String str4, ChangeMobileNumCallback changeMobileNumCallback);

    void changeMobileNum(String str, String str2, String str3, String str4, Map map, ChangeMobileNumCallback changeMobileNumCallback);

    void changePassword(String str, String str2, String str3, ChangePasswordCallback changePasswordCallback);

    void checkCode(String str, String str2, int i, CheckCodeCallback checkCodeCallback);

    void checkCode(String str, String str2, int i, Map map, CheckCodeCallback checkCodeCallback);

    void checkEnv(int i, CheckEvnCallback checkEvnCallback);

    void checkMobileRegister(String str, AbsApiCall<CheckMobileRegisterResponse> absApiCall);

    void checkMobileUnusable(String str, String str2, String str3, CheckMobileUnusableCallback checkMobileUnusableCallback);

    void checkPwd(String str, CheckPwdCallback checkPwdCallback);

    void checkQRCodeStatus(String str, String str2, CheckQRCodeStatusCallback checkQRCodeStatusCallback);

    void checkQRConnect(String str, String str2, TVCheckQRConnectCallback tVCheckQRConnectCallback);

    void deleteDevice(String str, DeleteDeviceCallback deleteDeviceCallback);

    void emailCheckCode(String str, String str2, int i, Map map, String str3, CheckCodeCallback checkCodeCallback);

    void emailCheckRegister(String str, Map map, String str2, EmailCheckRegisterCallback emailCheckRegisterCallback);

    void emailLoginWithToken(String str, String str2, String str3, String str4, UserPasswordLoginQueryCallback userPasswordLoginQueryCallback);

    void emailRegisterVerify(String str, String str2, int i, Map map, String str3, EmailRegisterVerifyCallback emailRegisterVerifyCallback);

    void emailRegisterVerifyLogin(String str, String str2, int i, Map map, String str3, EmailRegisterVerifyCallback emailRegisterVerifyCallback);

    void emailSendCode(String str, String str2, String str3, int i, String str4, Map map, String str5, EmailSendCodeCallback emailSendCodeCallback);

    void emailTicketResetPassword(String str, String str2, Map map, String str3, TicketResetPasswordCallback ticketResetPasswordCallback);

    void generateUserInfoTicket(String str, String str2, GenerateUserInfoTicketCallback generateUserInfoTicketCallback);

    void getAuthTicket(String str, String str2, AuthTicketCallback authTicketCallback);

    void getAvailableWays(int i, String str, GetAvailableWaysCallback getAvailableWaysCallback);

    void getLoginDevices(GetLoginDevicesCallback getLoginDevicesCallback);

    void getNewAccountInfo(String str, GetAccountInfoCallback getAccountInfoCallback);

    void getQRCode(String str, GetQRCodeCallback getQRCodeCallback);

    void getTvQRCode(String str, GetTvQRCodeCallback getTvQRCodeCallback);

    void login(String str, String str2, String str3, LoginQueryCallback loginQueryCallback);

    void loginByAuthTicket(String str, LoginByTicketCallback loginByTicketCallback);

    void loginByTicketAfterRegister(String str, String str2, LoginByTicketAfterRegisterCallback loginByTicketAfterRegisterCallback);

    void loginWithEmail(String str, String str2, String str3, EmailLoginQueryCallback emailLoginQueryCallback);

    void logout(String str, Map map, AbsApiCall<LogoutApiResponse> absApiCall);

    void logoutOthers(AbsApiCall<LogoutOthersApiResponse> absApiCall);

    void mobileLoginWithToken(String str, String str2, String str3, String str4, UserPasswordLoginQueryCallback userPasswordLoginQueryCallback);

    void mobilePassAuth(String str, String str2, String str3, MobilePassAuthCallback mobilePassAuthCallback);

    void mobileQuickAuth(String str, String str2, String str3, MobileQuickAuthCallback mobileQuickAuthCallback);

    void oneBindMobile(String str, String str2, String str3, int i, Map map, OneBindMobileCallback oneBindMobileCallback);

    void oneForceBindLogin(String str, String str2, String str3, int i, OneForceBindMobileCallback oneForceBindMobileCallback);

    void quickAuthlogin(String str, String str2, LoginByTicketCallback loginByTicketCallback);

    void quickLogin(String str, String str2, String str3, QuickLoginCallback quickLoginCallback);

    void quickLoginContinue(String str, String str2, QuickLoginContinueCallback quickLoginContinueCallback);

    void quickLoginOnly(String str, String str2, String str3, QuickLoginOnlyCallback quickLoginOnlyCallback);

    @Deprecated
    void refreshCaptcha(int i, RefreshCaptchaCallback refreshCaptchaCallback);

    void refreshCaptcha(RefreshCaptchaCallback refreshCaptchaCallback);

    void register(String str, String str2, String str3, String str4, RegisterCallback registerCallback);

    void registerWithEmail(String str, String str2, String str3, String str4, EmailRegisterQueryCallback emailRegisterQueryCallback);

    void requestValidateSMSCode(String str, int i, boolean z, int i2, String str2, ValidateCodeCallBack validateCodeCallBack);

    void requestValidateSMSCode(String str, int i, boolean z, ValidateCodeCallBack validateCodeCallBack);

    void resetPassword(String str, String str2, String str3, String str4, ResetPasswordCallback resetPasswordCallback);

    void sendCode(int i, String str, String str2, int i2, int i3, String str3, String str4, String str5, SendCodeCallback sendCodeCallback);

    void sendCode(String str, String str2, int i, int i2, int i3, SendCodeCallback sendCodeCallback);

    void sendCode(String str, String str2, int i, int i2, SendCodeCallback sendCodeCallback);

    void sendCode(String str, String str2, int i, int i2, String str3, int i3, int i4, SendCodeCallback sendCodeCallback);

    void sendCode(String str, String str2, int i, int i2, String str3, int i3, int i4, String str4, String str5, SendCodeCallback sendCodeCallback);

    void sendCode(String str, String str2, int i, int i2, String str3, int i3, int i4, String str4, String str5, Map map, SendCodeCallback sendCodeCallback);

    void sendCode(String str, String str2, int i, SendCodeCallback sendCodeCallback);

    void sendCode(String str, String str2, String str3, int i, SendCodeCallback sendCodeCallback);

    void sendVoiceCode(String str, String str2, int i, int i2, SendCodeCallback sendCodeCallback);

    void sendVoiceCode(String str, String str2, int i, SendCodeCallback sendCodeCallback);

    void setPassword(String str, String str2, SetPassCall setPassCall);

    void switchAuth(String str, SwitchAuthCallback switchAuthCallback);

    void switchTicket(String str, SwitchTicketCallback switchTicketCallback);

    void ticketResetPassword(String str, String str2, TicketResetPasswordCallback ticketResetPasswordCallback);

    @Deprecated
    void unbindMobile(String str, UnbindMobileCallback unbindMobileCallback);

    void updatePwd(String str, String str2, UpdatePwdCallback updatePwdCallback);

    void userNameLoginWithToken(String str, String str2, String str3, String str4, UserPasswordLoginQueryCallback userPasswordLoginQueryCallback);

    void verifyDevice(VerifyDeviceCallBack verifyDeviceCallBack);

    void verifyEmailPassword(String str, String str2, String str3, VerifyAccountPasswordCallback verifyAccountPasswordCallback);

    void verifyMobilePassword(String str, String str2, String str3, VerifyAccountPasswordCallback verifyAccountPasswordCallback);

    void verifyUserNamePassword(String str, String str2, String str3, VerifyAccountPasswordCallback verifyAccountPasswordCallback);
}
