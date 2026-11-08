package com.bytedance.sdk.account.impl;

import android.content.Context;
import com.bytedance.sdk.account.ApiLogoutThread;
import com.bytedance.sdk.account.api.IBDAccountAPI;
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
import com.bytedance.sdk.account.job.AuthorizeQRCodeLoginJob;
import com.bytedance.sdk.account.job.CanAwemeQuickLoginJob;
import com.bytedance.sdk.account.job.CancelCloseAccountJob;
import com.bytedance.sdk.account.job.CancelDoJob;
import com.bytedance.sdk.account.job.CancelIndexJob;
import com.bytedance.sdk.account.job.CancelPostJob;
import com.bytedance.sdk.account.job.CheckCodeJob;
import com.bytedance.sdk.account.job.CheckEnvJob;
import com.bytedance.sdk.account.job.CheckMobileUnusableJob;
import com.bytedance.sdk.account.job.CheckPwdJob;
import com.bytedance.sdk.account.job.CheckQRCodeStatusJob;
import com.bytedance.sdk.account.job.DeleteDeviceJob;
import com.bytedance.sdk.account.job.GenerateUserInfoTicketJob;
import com.bytedance.sdk.account.job.GetAccountInfoJob;
import com.bytedance.sdk.account.job.GetAuthTicketJob;
import com.bytedance.sdk.account.job.GetAvailableWaysJob;
import com.bytedance.sdk.account.job.GetLoginDevicesJob;
import com.bytedance.sdk.account.job.GetQRCodeJob;
import com.bytedance.sdk.account.job.GetTvQRCodeJob;
import com.bytedance.sdk.account.job.LoginByTicketAfterRegisterJob;
import com.bytedance.sdk.account.job.LoginByTicketJob;
import com.bytedance.sdk.account.job.QuickAuthLoginJob;
import com.bytedance.sdk.account.job.ScanQRCodeJob;
import com.bytedance.sdk.account.job.SwitchAuthJob;
import com.bytedance.sdk.account.job.SwitchTicketJob;
import com.bytedance.sdk.account.job.TVCheckQRConnectJob;
import com.bytedance.sdk.account.job.TicketResetPasswordJob;
import com.bytedance.sdk.account.job.UpdatePwdJob;
import com.bytedance.sdk.account.job.VerifyAccountPasswordJob;
import com.bytedance.sdk.account.mobile.thread.BindLoginThread;
import com.bytedance.sdk.account.mobile.thread.BindMobileApiThread;
import com.bytedance.sdk.account.mobile.thread.BindMobileNoPassApiThread;
import com.bytedance.sdk.account.mobile.thread.ChangeMobileNumApiThread;
import com.bytedance.sdk.account.mobile.thread.ChangePasswordApiThread;
import com.bytedance.sdk.account.mobile.thread.CheckMobileRegisterApiThread;
import com.bytedance.sdk.account.mobile.thread.EmailCheckRegisterApiThread;
import com.bytedance.sdk.account.mobile.thread.EmailLoginApiThread;
import com.bytedance.sdk.account.mobile.thread.EmailRegisterApiThread;
import com.bytedance.sdk.account.mobile.thread.EmailRegisterVerifyApiThread;
import com.bytedance.sdk.account.mobile.thread.EmailSendCodeApiThread;
import com.bytedance.sdk.account.mobile.thread.LoginApiThread;
import com.bytedance.sdk.account.mobile.thread.LogoutOthersThread;
import com.bytedance.sdk.account.mobile.thread.MobilePassAuthThread;
import com.bytedance.sdk.account.mobile.thread.MobileQuickAuthThread;
import com.bytedance.sdk.account.mobile.thread.OneBindMobileApiThread;
import com.bytedance.sdk.account.mobile.thread.OneForceBindLoginApiThread;
import com.bytedance.sdk.account.mobile.thread.QuickLoginApiThread;
import com.bytedance.sdk.account.mobile.thread.QuickLoginContinueApiThread;
import com.bytedance.sdk.account.mobile.thread.QuickLoginOnlyApiThread;
import com.bytedance.sdk.account.mobile.thread.RefreshCaptchaApiThread;
import com.bytedance.sdk.account.mobile.thread.RegisterApiThread;
import com.bytedance.sdk.account.mobile.thread.ResetPasswordApiThread;
import com.bytedance.sdk.account.mobile.thread.SendCodeApiThread;
import com.bytedance.sdk.account.mobile.thread.SendVoiceCodeApiThread;
import com.bytedance.sdk.account.mobile.thread.SetpassThread;
import com.bytedance.sdk.account.mobile.thread.UnbindMobileApiThread;
import com.bytedance.sdk.account.mobile.thread.UserPasswordLoginApiThread;
import com.bytedance.sdk.account.mobile.thread.UserPasswordRegisterApiThread;
import com.bytedance.sdk.account.mobile.thread.ValidateCodeThread;
import com.bytedance.sdk.account.mobile.thread.VerifyDeviceThread;
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
class BDAccountApiImpl implements IBDAccountAPI {
    Context mContext;

    public BDAccountApiImpl(Context context) {
        this.mContext = context;
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void logout(String str, Map map, AbsApiCall<LogoutApiResponse> absApiCall) {
        ApiLogoutThread.logout(this.mContext, str, map, absApiCall).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void register(String str, String str2, String str3, String str4, RegisterCallback registerCallback) {
        RegisterApiThread.register(this.mContext, str, str2, str3, str4, registerCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void loginWithEmail(String str, String str2, String str3, EmailLoginQueryCallback emailLoginQueryCallback) {
        EmailLoginApiThread.emailLogin(this.mContext, str, str2, str3, emailLoginQueryCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void registerWithEmail(String str, String str2, String str3, String str4, EmailRegisterQueryCallback emailRegisterQueryCallback) {
        EmailRegisterApiThread.emailRegister(this.mContext, str, str2, str3, str4, emailRegisterQueryCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void login(String str, String str2, String str3, LoginQueryCallback loginQueryCallback) {
        LoginApiThread.login(this.mContext, str, str2, str3, loginQueryCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void quickLogin(String str, String str2, String str3, QuickLoginCallback quickLoginCallback) {
        QuickLoginApiThread.quickLogin(this.mContext, str, str2, str3, quickLoginCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void quickLoginOnly(String str, String str2, String str3, QuickLoginOnlyCallback quickLoginOnlyCallback) {
        QuickLoginOnlyApiThread.quickLoginOnly(this.mContext, str, str2, str3, quickLoginOnlyCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void quickLoginContinue(String str, String str2, QuickLoginContinueCallback quickLoginContinueCallback) {
        QuickLoginContinueApiThread.quickLoginContinue(this.mContext, str, str2, quickLoginContinueCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void refreshCaptcha(RefreshCaptchaCallback refreshCaptchaCallback) {
        RefreshCaptchaApiThread.refreshCaptcha(this.mContext, refreshCaptchaCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void refreshCaptcha(int i, RefreshCaptchaCallback refreshCaptchaCallback) {
        RefreshCaptchaApiThread.refreshCaptcha(this.mContext, i, refreshCaptchaCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void sendCode(String str, String str2, int i, SendCodeCallback sendCodeCallback) {
        SendCodeApiThread.sendCode(this.mContext, str, str2, i, sendCodeCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void sendCode(String str, String str2, int i, int i2, SendCodeCallback sendCodeCallback) {
        SendCodeApiThread.sendCode(this.mContext, str, str2, i, i2, -1, sendCodeCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void sendCode(String str, String str2, int i, int i2, int i3, SendCodeCallback sendCodeCallback) {
        SendCodeApiThread.sendCode(this.mContext, str, str2, i, i2, i3, sendCodeCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void sendCode(String str, String str2, int i, int i2, String str3, int i3, int i4, SendCodeCallback sendCodeCallback) {
        SendCodeApiThread.sendCode(this.mContext, str, str2, i, i2, str3, i3, i4, sendCodeCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void sendCode(String str, String str2, int i, int i2, String str3, int i3, int i4, String str4, String str5, SendCodeCallback sendCodeCallback) {
        SendCodeApiThread.sendCode(this.mContext, str, str2, i, i2, str3, i3, i4, str4, str5, sendCodeCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void sendCode(String str, String str2, String str3, int i, SendCodeCallback sendCodeCallback) {
        SendCodeApiThread.sendCode(this.mContext, str, str2, str3, i, sendCodeCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void sendCode(String str, String str2, int i, int i2, String str3, int i3, int i4, String str4, String str5, Map map, SendCodeCallback sendCodeCallback) {
        SendCodeApiThread.sendCode(this.mContext, str, str2, i, i2, str3, i3, i4, str4, str5, map, sendCodeCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void sendCode(int i, String str, String str2, int i2, int i3, String str3, String str4, String str5, SendCodeCallback sendCodeCallback) {
        SendCodeApiThread.sendCode(this.mContext, i, str, str2, i2, i3, str3, str4, str5, sendCodeCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void resetPassword(String str, String str2, String str3, String str4, ResetPasswordCallback resetPasswordCallback) {
        ResetPasswordApiThread.resetPassword(this.mContext, str, str2, str3, str4, resetPasswordCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void checkCode(String str, String str2, int i, CheckCodeCallback checkCodeCallback) {
        CheckCodeJob.checkCode(this.mContext, str, str2, i, checkCodeCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void checkCode(String str, String str2, int i, Map map, CheckCodeCallback checkCodeCallback) {
        CheckCodeJob.checkCode(this.mContext, str, str2, i, map, checkCodeCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void ticketResetPassword(String str, String str2, TicketResetPasswordCallback ticketResetPasswordCallback) {
        TicketResetPasswordJob.resetPassword(this.mContext, str, str2, ticketResetPasswordCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void emailCheckCode(String str, String str2, int i, Map map, String str3, CheckCodeCallback checkCodeCallback) {
        CheckCodeJob.checkEmailCode(this.mContext, str, str2, i, map, str3, checkCodeCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void emailTicketResetPassword(String str, String str2, Map map, String str3, TicketResetPasswordCallback ticketResetPasswordCallback) {
        TicketResetPasswordJob.resetEmailPassword(this.mContext, str, str2, map, str3, ticketResetPasswordCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void emailSendCode(String str, String str2, String str3, int i, String str4, Map map, String str5, EmailSendCodeCallback emailSendCodeCallback) {
        EmailSendCodeApiThread.emailSendCode(this.mContext, str, str2, str3, i, str4, map, str5, emailSendCodeCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void emailRegisterVerify(String str, String str2, int i, Map map, String str3, EmailRegisterVerifyCallback emailRegisterVerifyCallback) {
        EmailRegisterVerifyApiThread.emailRegisterVerify(this.mContext, str, str2, i, map, str3, emailRegisterVerifyCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void emailRegisterVerifyLogin(String str, String str2, int i, Map map, String str3, EmailRegisterVerifyCallback emailRegisterVerifyCallback) {
        EmailRegisterVerifyApiThread.emailRegisterVerifyLogin(this.mContext, str, str2, i, map, str3, emailRegisterVerifyCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void emailCheckRegister(String str, Map map, String str2, EmailCheckRegisterCallback emailCheckRegisterCallback) {
        EmailCheckRegisterApiThread.emailCheckRegister(this.mContext, str, map, str2, emailCheckRegisterCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void changePassword(String str, String str2, String str3, ChangePasswordCallback changePasswordCallback) {
        ChangePasswordApiThread.changePassword(this.mContext, str, str2, str3, changePasswordCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void changeMobileNum(String str, String str2, String str3, String str4, Map map, ChangeMobileNumCallback changeMobileNumCallback) {
        ChangeMobileNumApiThread.changeMobilenum(this.mContext, str, str2, str3, str4, map, changeMobileNumCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void changeMobileNum(String str, String str2, String str3, String str4, ChangeMobileNumCallback changeMobileNumCallback) {
        ChangeMobileNumApiThread.changeMobilenum(this.mContext, str, str2, str3, str4, changeMobileNumCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void changeMobileNum(String str, String str2, String str3, ChangeMobileNumCallback changeMobileNumCallback) {
        ChangeMobileNumApiThread.changeMobilenum(this.mContext, str, str2, str3, changeMobileNumCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void bindMobile(String str, String str2, String str3, String str4, BindMobileCallback bindMobileCallback) {
        bindMobile(str, str2, str3, str4, 0, bindMobileCallback);
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void bindMobile(String str, String str2, String str3, String str4, int i, BindMobileCallback bindMobileCallback) {
        BindMobileApiThread.bindMobile(this.mContext, str, str2, str3, str4, i, bindMobileCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void bindMobileNoPassword(String str, String str2, String str3, int i, BindMobileCallback bindMobileCallback) {
        BindMobileNoPassApiThread.bindMobileNoPass(this.mContext, str, str2, null, str3, i, bindMobileCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void unbindMobile(String str, UnbindMobileCallback unbindMobileCallback) {
        UnbindMobileApiThread.unbindMobile(this.mContext, str, unbindMobileCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    @Deprecated
    public void accountLogin(String str, String str2, String str3, int i, UserPasswordLoginQueryCallback userPasswordLoginQueryCallback) {
        UserPasswordLoginApiThread.userPasswordLogin(this.mContext, null, null, null, str, str2, null, str3, i, userPasswordLoginQueryCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void accountUserNameLogin(String str, String str2, String str3, UserPasswordLoginQueryCallback userPasswordLoginQueryCallback) {
        UserPasswordLoginApiThread.userPasswordLogin(this.mContext, null, null, str, null, str2, null, str3, 0, userPasswordLoginQueryCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void accountEmailLogin(String str, String str2, String str3, UserPasswordLoginQueryCallback userPasswordLoginQueryCallback) {
        UserPasswordLoginApiThread.userPasswordLogin(this.mContext, null, str, null, null, str2, null, str3, 0, userPasswordLoginQueryCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void accountMobileLogin(String str, String str2, String str3, UserPasswordLoginQueryCallback userPasswordLoginQueryCallback) {
        UserPasswordLoginApiThread.userPasswordLogin(this.mContext, str, null, null, null, str2, null, str3, 0, userPasswordLoginQueryCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void userNameLoginWithToken(String str, String str2, String str3, String str4, UserPasswordLoginQueryCallback userPasswordLoginQueryCallback) {
        UserPasswordLoginApiThread.userPasswordLogin(this.mContext, null, null, str, null, str2, str3, str4, 0, userPasswordLoginQueryCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void emailLoginWithToken(String str, String str2, String str3, String str4, UserPasswordLoginQueryCallback userPasswordLoginQueryCallback) {
        UserPasswordLoginApiThread.userPasswordLogin(this.mContext, null, str, null, null, str2, str3, str4, 0, userPasswordLoginQueryCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void mobileLoginWithToken(String str, String str2, String str3, String str4, UserPasswordLoginQueryCallback userPasswordLoginQueryCallback) {
        UserPasswordLoginApiThread.userPasswordLogin(this.mContext, str, null, null, null, str2, str3, str4, 0, userPasswordLoginQueryCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void accountUserNameRegister(String str, String str2, UserPasswordRegisterQueryCallback userPasswordRegisterQueryCallback) {
        UserPasswordRegisterApiThread.userPasswordRegister(this.mContext, str, str2, userPasswordRegisterQueryCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void sendVoiceCode(String str, String str2, int i, SendCodeCallback sendCodeCallback) {
        SendVoiceCodeApiThread.sendVoiceCode(this.mContext, str, str2, i, sendCodeCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void sendVoiceCode(String str, String str2, int i, int i2, SendCodeCallback sendCodeCallback) {
        SendVoiceCodeApiThread.sendVoiceCode(this.mContext, str, str2, i, i2, sendCodeCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void requestValidateSMSCode(String str, int i, boolean z, ValidateCodeCallBack validateCodeCallBack) {
        ValidateCodeThread.validateCode(this.mContext, str, i, z, validateCodeCallBack).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void requestValidateSMSCode(String str, int i, boolean z, int i2, String str2, ValidateCodeCallBack validateCodeCallBack) {
        ValidateCodeThread.validateCode(this.mContext, str, i, z, i2, str2, validateCodeCallBack).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void verifyDevice(VerifyDeviceCallBack verifyDeviceCallBack) {
        VerifyDeviceThread.verifyDevice(this.mContext, verifyDeviceCallBack).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void setPassword(String str, String str2, SetPassCall setPassCall) {
        SetpassThread.setPassword(this.mContext, str, str2, setPassCall).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void mobilePassAuth(String str, String str2, String str3, MobilePassAuthCallback mobilePassAuthCallback) {
        MobilePassAuthThread.passAuth(this.mContext, str, str2, str3, mobilePassAuthCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void mobileQuickAuth(String str, String str2, String str3, MobileQuickAuthCallback mobileQuickAuthCallback) {
        MobileQuickAuthThread.quickAuth(this.mContext, str, str2, str3, mobileQuickAuthCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void bindLogin(String str, String str2, String str3, String str4, BindLoginCallback bindLoginCallback) {
        BindLoginThread.bindLogin(this.mContext, str, str2, str3, str4, bindLoginCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void bindLogin(String str, String str2, String str3, String str4, Map map, BindLoginCallback bindLoginCallback) {
        BindLoginThread.bindLogin(this.mContext, str, str2, str3, str4, map, bindLoginCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void switchTicket(String str, SwitchTicketCallback switchTicketCallback) {
        SwitchTicketJob.switchTicket(this.mContext, str, switchTicketCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void switchAuth(String str, SwitchAuthCallback switchAuthCallback) {
        SwitchAuthJob.switchAuth(this.mContext, str, switchAuthCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void getTvQRCode(String str, GetTvQRCodeCallback getTvQRCodeCallback) {
        GetTvQRCodeJob.getTvQRCode(this.mContext, str, getTvQRCodeCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void checkQRConnect(String str, String str2, TVCheckQRConnectCallback tVCheckQRConnectCallback) {
        TVCheckQRConnectJob.checkQRConnect(this.mContext, str, str2, tVCheckQRConnectCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void cancelPost(String str, String str2, String str3, String str4, CancelPostCallback cancelPostCallback) {
        CancelPostJob.cancelPost(this.mContext, str, str2, str3, str4, cancelPostCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void cancelDo(boolean z, CancelDoCallback cancelDoCallback) {
        CancelDoJob.cancelDo(this.mContext, z, cancelDoCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void cancelIndex(CancelIndexCallback cancelIndexCallback) {
        CancelIndexJob.cancelIndex(this.mContext, cancelIndexCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void checkEnv(int i, CheckEvnCallback checkEvnCallback) {
        CheckEnvJob.checkEnv(this.mContext, i, checkEvnCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void checkPwd(String str, CheckPwdCallback checkPwdCallback) {
        CheckPwdJob.checkPwd(this.mContext, str, checkPwdCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void updatePwd(String str, String str2, UpdatePwdCallback updatePwdCallback) {
        UpdatePwdJob.updatePwd(this.mContext, str, str2, updatePwdCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void getAuthTicket(String str, String str2, AuthTicketCallback authTicketCallback) {
        GetAuthTicketJob.getAuthTicket(this.mContext, str, str2, authTicketCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void loginByAuthTicket(String str, LoginByTicketCallback loginByTicketCallback) {
        LoginByTicketJob.loginByAuthTicket(this.mContext, str, loginByTicketCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void quickAuthlogin(String str, String str2, LoginByTicketCallback loginByTicketCallback) {
        QuickAuthLoginJob.quickAuthlogin(this.mContext, str, str2, loginByTicketCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void oneBindMobile(String str, String str2, String str3, int i, Map map, OneBindMobileCallback oneBindMobileCallback) {
        OneBindMobileApiThread.oneBindMobile(this.mContext, str, str2, str3, i, map, oneBindMobileCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void oneForceBindLogin(String str, String str2, String str3, int i, OneForceBindMobileCallback oneForceBindMobileCallback) {
        OneForceBindLoginApiThread.oneForceBindLogin(this.mContext, str, str2, str3, i, oneForceBindMobileCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void getLoginDevices(GetLoginDevicesCallback getLoginDevicesCallback) {
        GetLoginDevicesJob.getLoginDevices(this.mContext, getLoginDevicesCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void deleteDevice(String str, DeleteDeviceCallback deleteDeviceCallback) {
        DeleteDeviceJob.deleteDevice(this.mContext, str, deleteDeviceCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void getNewAccountInfo(String str, GetAccountInfoCallback getAccountInfoCallback) {
        GetAccountInfoJob.getNewAccountInfo(this.mContext, str, getAccountInfoCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void getQRCode(String str, GetQRCodeCallback getQRCodeCallback) {
        GetQRCodeJob.getQRCode(this.mContext, str, getQRCodeCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void checkQRCodeStatus(String str, String str2, CheckQRCodeStatusCallback checkQRCodeStatusCallback) {
        CheckQRCodeStatusJob.checkQRCodeStatus(this.mContext, str, str2, checkQRCodeStatusCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void authorizeScanQRCode(String str, ScanQRCodeCallback scanQRCodeCallback) {
        ScanQRCodeJob.scanQRCode(this.mContext, str, scanQRCodeCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void authorizeQRCodeLogin(String str, String str2, String str3, AuthorizeQRCodeLoginCallback authorizeQRCodeLoginCallback) {
        AuthorizeQRCodeLoginJob.authorizeLogin(this.mContext, str, str2, str3, authorizeQRCodeLoginCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void cancelCloseAccountWithToken(String str, CancelCloseAccountCallback cancelCloseAccountCallback) {
        CancelCloseAccountJob.cancelCloseAccountWithToken(this.mContext, str, cancelCloseAccountCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void logoutOthers(AbsApiCall<LogoutOthersApiResponse> absApiCall) {
        LogoutOthersThread.logoutOthers(this.mContext, absApiCall).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void checkMobileUnusable(String str, String str2, String str3, CheckMobileUnusableCallback checkMobileUnusableCallback) {
        CheckMobileUnusableJob.checkMobileUnusable(this.mContext, str, str2, str3, checkMobileUnusableCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void getAvailableWays(int i, String str, GetAvailableWaysCallback getAvailableWaysCallback) {
        GetAvailableWaysJob.getAvailableWays(this.mContext, i, str, getAvailableWaysCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void verifyUserNamePassword(String str, String str2, String str3, VerifyAccountPasswordCallback verifyAccountPasswordCallback) {
        VerifyAccountPasswordJob.verifyAccountPassword(this.mContext, str, null, null, str2, str3, verifyAccountPasswordCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void verifyMobilePassword(String str, String str2, String str3, VerifyAccountPasswordCallback verifyAccountPasswordCallback) {
        VerifyAccountPasswordJob.verifyAccountPassword(this.mContext, null, str, null, str2, str3, verifyAccountPasswordCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void verifyEmailPassword(String str, String str2, String str3, VerifyAccountPasswordCallback verifyAccountPasswordCallback) {
        VerifyAccountPasswordJob.verifyAccountPassword(this.mContext, null, null, str, str2, str3, verifyAccountPasswordCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void generateUserInfoTicket(String str, String str2, GenerateUserInfoTicketCallback generateUserInfoTicketCallback) {
        GenerateUserInfoTicketJob.generateUserInfoTicket(this.mContext, str, str2, generateUserInfoTicketCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void loginByTicketAfterRegister(String str, String str2, LoginByTicketAfterRegisterCallback loginByTicketAfterRegisterCallback) {
        LoginByTicketAfterRegisterJob.loginByTicketAfterRegister(this.mContext, str, str2, loginByTicketAfterRegisterCallback).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void checkMobileRegister(String str, AbsApiCall<CheckMobileRegisterResponse> absApiCall) {
        CheckMobileRegisterApiThread.checkMobileRegister(this.mContext, str, absApiCall).start();
    }

    @Override // com.bytedance.sdk.account.api.IBDAccountAPI
    public void canAwemeQuickLogin(CanAwemeQuickLoginCallback canAwemeQuickLoginCallback) {
        CanAwemeQuickLoginJob.canAwemeQuickLogin(this.mContext, canAwemeQuickLoginCallback).start();
    }
}
