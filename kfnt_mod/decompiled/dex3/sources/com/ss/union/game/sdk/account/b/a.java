package com.ss.union.game.sdk.account.b;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.bytedance.sdk.account.IMonitor;
import com.bytedance.sdk.account.INetWork;
import com.bytedance.sdk.account.impl.BDAccountDelegate;
import com.bytedance.sdk.account.platform.api.IAccountSettingsService;
import com.bytedance.sdk.account.platform.base.AuthorizeFramework;
import com.bytedance.sdk.account.platform.onekey.IOnekeyMonitor;
import com.bytedance.sdk.account.platform.onekey.OnekeyLoginConfig;
import com.bytedance.sdk.account.platform.onekey.OnekeyLoginServiceIniter;
import com.bytedance.sdk.open.aweme.TikTokOpenApiFactory;
import com.bytedance.sdk.open.aweme.TikTokOpenConfig;
import com.ss.android.TTAccountConfig;
import com.ss.android.account.TTAccountInit;
import com.ss.union.game.sdk.a.b;
import com.ss.union.game.sdk.account.LGSDKAccountService;
import com.ss.union.game.sdk.account.callback.ICloseAccountCallback;
import com.ss.union.game.sdk.account.callback.ILoginBoxPopCallBack;
import com.ss.union.game.sdk.account.callback.INickNameUpdate;
import com.ss.union.game.sdk.account.callback.IQueryAccountInfoCallback;
import com.ss.union.game.sdk.account.callback.IUnbindAccountCallback;
import com.ss.union.game.sdk.account.callback.LGGlobalLoginCallback;
import com.ss.union.game.sdk.account.d.b;
import com.ss.union.game.sdk.account.fragment.SwitchUserWarningFragment;
import com.ss.union.game.sdk.account.fragment.oneKey.OneKeyBindFragment;
import com.ss.union.game.sdk.account.fragment.oneKey.OneKeyLoginFragment;
import com.ss.union.game.sdk.account.fragment.oneKey.OneKeySwitchFragment;
import com.ss.union.game.sdk.common.callback.IFailCallback;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.net.CoreNetClient;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestPost;
import com.ss.union.game.sdk.common.result.LGSDKResult;
import com.ss.union.game.sdk.common.util.ActivityUtils;
import com.ss.union.game.sdk.common.util.GlobalApplicationUtils;
import com.ss.union.game.sdk.common.util.NetworkUtils;
import com.ss.union.game.sdk.core.LGSDKCore;
import com.ss.union.game.sdk.core.applog.AppLogIdUpdateListener;
import com.ss.union.game.sdk.core.applog.AppLogManager;
import com.ss.union.game.sdk.core.base.account.LGAccountDataUtil;
import com.ss.union.game.sdk.core.base.account.constant.LGAccountConstant;
import com.ss.union.game.sdk.core.base.account.model.User;
import com.ss.union.game.sdk.core.base.account.model.VisitorUser;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.base.constant.LGUris;
import com.ss.union.game.sdk.core.base.debug.automatic_detection.LGDetectionManager;
import com.ss.union.game.sdk.core.base.debug.automatic_detection.constants.LGDetectionConstant;
import com.ss.union.game.sdk.core.base.event.PageStater;
import com.ss.union.game.sdk.core.base.utils.DanJuanUtils;
import com.ss.union.game.sdk.core.realName.callback.LGRealNameCallback;
import com.ss.union.game.sdk.core.realName.entity.LGRealNameRewardInfo;
import com.ss.union.game.sdk.core.realName.event.RealNameLogEvent;
import com.ss.union.game.sdk.core.vapp.VLog;
import com.ss.union.game.sdk.core.vapp.VUserInfoManager;
import com.ss.union.game.sdk.vcenter.account.b.a;
import com.ss.union.game.sdk.vcenter.c;
import com.xiaomi.mipush.sdk.Constants;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a implements LGSDKAccountService {
    public static ILoginBoxPopCallBack a = null;
    private static volatile a b = null;
    private static LGGlobalLoginCallback c = null;
    private static final int e = 1000;
    private long d = 0;

    private a() {
    }

    public static a a() {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a();
                }
            }
        }
        return b;
    }

    public void b() {
        AppLogManager.getInstance().registerIdUpdateListener(new AppLogIdUpdateListener() { // from class: com.ss.union.game.sdk.account.b.a.1
            @Override // com.ss.union.game.sdk.core.applog.AppLogIdUpdateListener
            public void onIdChanged(String str, String str2) {
                try {
                    TikTokOpenApiFactory.init(new TikTokOpenConfig(AppIdManager.dyAppKey()));
                    LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.INIT, LGDetectionConstant.InitItem.DETECTION_ID_DOU_YIN, LGDetectionConstant.DetectionState.PASS);
                } catch (Exception e2) {
                    com.ss.union.game.sdk.c.a.a("TikTok init error : " + Log.getStackTraceString(e2));
                    LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.INIT, LGDetectionConstant.InitItem.DETECTION_ID_DOU_YIN, LGDetectionConstant.DetectionState.FAIL);
                }
                a.this.d();
                a.this.e();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        TTAccountInit.init(new TTAccountConfig() { // from class: com.ss.union.game.sdk.account.b.a.3
            @Override // com.ss.android.TTAccountConfig
            public IMonitor getMonitor() {
                return null;
            }

            @Override // com.ss.android.TTAccountConfig
            public boolean isSecureCaptchaEnabled() {
                return true;
            }

            @Override // com.ss.android.TTAccountConfig
            public boolean isSupportMultiLogin() {
                return false;
            }

            @Override // com.ss.android.TTAccountConfig
            public String host() {
                return LGUris.getCurrentDomain();
            }

            @Override // com.ss.android.TTAccountConfig
            public Context getApplicationContext() {
                return GlobalApplicationUtils.getContext();
            }

            @Override // com.ss.android.TTAccountConfig
            public INetWork getNetwork() {
                return new INetWork() { // from class: com.ss.union.game.sdk.account.b.a.3.1
                    public int checkResponseException(Context context, Throwable th) {
                        return 0;
                    }

                    public String executeGet(int i, String str) throws Exception {
                        return CoreNetClient.get(str).string().httpBody;
                    }

                    public String executePost(int i, String str, Map<String, String> map) throws Exception {
                        ACoreRequestPost aCoreRequestPostPost = CoreNetClient.post(str);
                        for (Map.Entry<String, String> entry : map.entrySet()) {
                            aCoreRequestPostPost.param(entry.getKey(), entry.getValue());
                        }
                        return aCoreRequestPostPost.string().httpBody;
                    }
                };
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        try {
            AuthorizeFramework.init(GlobalApplicationUtils.getContext(), new OnekeyLoginServiceIniter[]{new OnekeyLoginServiceIniter(new OnekeyLoginConfig(new IOnekeyMonitor() { // from class: com.ss.union.game.sdk.account.b.a.4
                public void onEvent(String str, JSONObject jSONObject) {
                    AppLogManager.getInstance().onEventV3(str, jSONObject);
                }
            }).setCMSetting(ConfigManager.LoginConfig.oneKeyCMAppID(), ConfigManager.LoginConfig.getOneKeyCMAppKey()).setCUSetting(ConfigManager.LoginConfig.oneKeyCUAppID(), ConfigManager.LoginConfig.getOneKeyCUAppKey()).setCTSetting(ConfigManager.LoginConfig.oneKeyCTAppID(), ConfigManager.LoginConfig.getOneKeyCTAppKey()))});
            IAccountSettingsService settingsInstance = BDAccountDelegate.getSettingsInstance(GlobalApplicationUtils.getContext());
            if (settingsInstance != null) {
                try {
                    settingsInstance.updateSettings(new JSONObject(LGAccountConstant.ONE_KEY_LOGIN_SETTING));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        } catch (Exception e3) {
            com.ss.union.game.sdk.c.a.a("initOneKeyLoginConfig() exception:" + Log.getStackTraceString(e3));
        }
    }

    @Override // com.ss.union.game.sdk.account.LGSDKAccountService
    public void setGlobalLoginCallback(LGGlobalLoginCallback lGGlobalLoginCallback) {
        c = lGGlobalLoginCallback;
    }

    @Override // com.ss.union.game.sdk.account.LGSDKAccountService
    public void registerLoginBoxPopCallBack(ILoginBoxPopCallBack iLoginBoxPopCallBack) {
        a = iLoginBoxPopCallBack;
    }

    @Override // com.ss.union.game.sdk.account.LGSDKAccountService
    public void loginNormal(Activity activity) throws JSONException {
        if (activity == null) {
            a(-203, "参数错误", 1);
            return;
        }
        if (!LGSDKCore.isSdkInitSuccess()) {
            a(-204, "SDK还未初始化完成", 1);
            com.ss.union.game.sdk.c.a.a("SDK has not yet been initialized");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("loginNormal(), is Slient Mode:");
        sb.append(ConfigManager.LoginConfig.isSilentLogin());
        sb.append(",did ready:");
        sb.append(!TextUtils.isEmpty(AppLogManager.getInstance().getDid()));
        com.ss.union.game.sdk.c.a.a(sb.toString());
        LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.ACCOUNT, LGDetectionConstant.AccountItem.DETECTION_ID_INVOKE_LOGIN, LGDetectionConstant.DetectionState.PASS);
        if (ConfigManager.LoginConfig.isNoUserLogin()) {
            com.ss.union.game.sdk.c.a.a("login config isNoUseLogin return");
            a(-203, "参数错误", 1);
        } else if (ConfigManager.LoginConfig.isSilentLogin()) {
            b(activity);
        } else if (ConfigManager.LoginConfig.isDialogLogin()) {
            a(activity);
        }
    }

    private void a(Activity activity) {
        if (LGAccountDataUtil.isLogined()) {
            c.b().a(new a.InterfaceC0103a() { // from class: com.ss.union.game.sdk.account.b.a.5
                @Override // com.ss.union.game.sdk.vcenter.account.b.a.InterfaceC0103a
                public void a(boolean z) throws JSONException {
                    VLog.Account.d("拦截自动结果: " + z);
                    if (z) {
                        OneKeyLoginFragment.a((BaseFragment) null, true, false);
                    } else {
                        com.ss.union.game.sdk.c.a.a("pop mode Auto Login");
                        a.this.a(LGAccountDataUtil.getLoginToken());
                    }
                }
            });
        } else {
            com.ss.union.game.sdk.c.a.a("pop mode OneKey login");
            OneKeyLoginFragment.a((BaseFragment) null, true, false);
        }
    }

    private void b(Activity activity) throws JSONException {
        if (LGAccountDataUtil.isLogined()) {
            com.ss.union.game.sdk.c.a.a("slient mode Local Auto Login");
            a(LGAccountDataUtil.getLoginToken());
            return;
        }
        VisitorUser userDataFromSD = LGAccountDataUtil.readUserDataFromSD(activity);
        if (userDataFromSD == null) {
            com.ss.union.game.sdk.c.a.a("slient mode Visitor Login");
            f();
        } else {
            com.ss.union.game.sdk.c.a.a("slient mode SD Auto Login");
            a(userDataFromSD.user.token);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        b.a(new com.ss.union.game.sdk.account.d.c() { // from class: com.ss.union.game.sdk.account.b.a.6
            @Override // com.ss.union.game.sdk.account.d.c
            public void a(int i, String str) throws JSONException {
                PageStater.V1.onEvent(com.ss.union.game.sdk.account.a.a.ad, LGAccountConstant.LoginType.LOGIN_TYPE_GUEST.getName(), "auto", i);
                if (i == 50000) {
                    a.this.c();
                    return;
                }
                com.ss.union.game.sdk.c.a.a("handleMsg() visitor login fail code:" + i + ",msg:" + str);
                if (ConfigManager.LoginConfig.canShowToastWhenSilentLoginFail()) {
                    com.ss.union.game.sdk.account.f.b.a(i, str);
                }
                a.this.a(i, str, 1);
                com.ss.union.game.sdk.account.a.a.a("auto", false, i);
            }

            @Override // com.ss.union.game.sdk.account.d.c
            public void a(User user) {
                a.this.a(user, 1);
                com.ss.union.game.sdk.account.a.a.a("auto", true, 0);
            }
        });
    }

    public void c() {
        com.ss.union.game.sdk.a.b.a().a(new LGRealNameCallback() { // from class: com.ss.union.game.sdk.account.b.a.7
            @Override // com.ss.union.game.sdk.core.realName.callback.LGRealNameCallback
            public void onFail(int i, String str) {
            }

            @Override // com.ss.union.game.sdk.core.realName.callback.LGRealNameCallback
            public void onSuccess(boolean z, boolean z2, LGRealNameRewardInfo lGRealNameRewardInfo) {
                a.this.f();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) throws JSONException {
        PageStater.V1.onEvent(com.ss.union.game.sdk.account.a.a.ak, LGAccountDataUtil.getLoginType());
        b.a(str, new com.ss.union.game.sdk.account.d.c() { // from class: com.ss.union.game.sdk.account.b.a.8
            @Override // com.ss.union.game.sdk.account.d.c
            public void a(int i, String str2) throws JSONException {
                VisitorUser userDataFromSD;
                if (i == 10000 || i == 10001 || i == 10010 || i == 10012) {
                    if (ConfigManager.LoginConfig.canShowToastWhenSilentLoginFail() && i != 10012) {
                        com.ss.union.game.sdk.account.f.b.a(i, str2);
                    }
                    if (i == 10012) {
                        com.ss.union.game.sdk.c.a.a("login fail,appId not match openid : GameSdk 自动登录");
                    }
                    if (LGAccountDataUtil.getCurrentUserLocal() == null && (userDataFromSD = LGAccountDataUtil.readUserDataFromSD(GlobalApplicationUtils.getContext())) != null) {
                        userDataFromSD.tokenValid = false;
                        LGAccountDataUtil.saveUserData2SD(GlobalApplicationUtils.getContext(), userDataFromSD);
                    }
                    OneKeyLoginFragment.a((BaseFragment) null, true, true);
                    com.ss.union.game.sdk.c.a.a("handleMsg() auto login fail ,token invalid and go to pop login");
                } else {
                    if (ConfigManager.LoginConfig.canShowToastWhenSilentLoginFail()) {
                        com.ss.union.game.sdk.account.f.b.a(i, str2);
                    }
                    a.this.a(i, str2, 1);
                }
                com.ss.union.game.sdk.account.a.a.a("auto", false, i);
                PageStater.V1.onEvent(com.ss.union.game.sdk.account.a.a.ad, LGAccountDataUtil.getLoginType(), "auto", i, 6L);
            }

            @Override // com.ss.union.game.sdk.account.d.c
            public void a(User user) throws JSONException {
                a.this.a(user, 1);
                com.ss.union.game.sdk.account.a.a.a("auto", true, 0);
                PageStater.V1.onEvent(com.ss.union.game.sdk.account.a.a.ac, LGAccountDataUtil.getLoginType(), "auto");
            }
        });
    }

    @Override // com.ss.union.game.sdk.account.LGSDKAccountService
    public void switchAccount(Activity activity) {
        if (activity == null) {
            a(-203, "参数错误", 1);
            return;
        }
        if (!LGSDKCore.isSdkInitSuccess()) {
            a(-204, "SDK还未初始化完成", 3);
            com.ss.union.game.sdk.c.a.a("SDK has not yet been initialized");
            return;
        }
        com.ss.union.game.sdk.c.a.a("switchAccount()");
        LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.ACCOUNT, LGDetectionConstant.AccountItem.DETECTION_ID_INVOKE_SWITCH, LGDetectionConstant.DetectionState.PASS);
        if (LGAccountDataUtil.isLogined()) {
            if (LGAccountDataUtil.isVisitor()) {
                SwitchUserWarningFragment.a(new SwitchUserWarningFragment.a() { // from class: com.ss.union.game.sdk.account.b.a.9
                    @Override // com.ss.union.game.sdk.account.fragment.SwitchUserWarningFragment.a
                    public void a(int i, String str, int i2) {
                        a.this.c(i, str, i2);
                    }
                });
                return;
            } else {
                OneKeySwitchFragment.a((BaseFragment) null, true);
                return;
            }
        }
        a(com.ss.union.game.sdk.account.result.c.UN_LOGIN.a(), com.ss.union.game.sdk.account.result.c.UN_LOGIN.b(), 3);
    }

    @Override // com.ss.union.game.sdk.account.LGSDKAccountService
    public void visitorBindAccount(Activity activity) {
        if (activity == null) {
            a(-203, "参数错误", 1);
            return;
        }
        if (!LGSDKCore.isSdkInitSuccess()) {
            a(-204, "SDK还未初始化完成", 2);
            com.ss.union.game.sdk.c.a.a("SDK has not yet been initialized");
            return;
        }
        LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.ACCOUNT, LGDetectionConstant.AccountItem.DETECTION_ID_VISITOR_BIND, LGDetectionConstant.DetectionState.PASS);
        com.ss.union.game.sdk.c.a.a("visitorBindAccount()");
        User currentUserLocal = LGAccountDataUtil.getCurrentUserLocal();
        if (currentUserLocal == null || !currentUserLocal.mIsLogin) {
            a(-4001, com.ss.union.game.sdk.account.result.b.k, 2);
        } else if (!LGAccountConstant.LoginType.LOGIN_TYPE_GUEST.getName().equals(currentUserLocal.login_type)) {
            a(com.ss.union.game.sdk.account.result.a.a, com.ss.union.game.sdk.account.result.a.c, 2);
        } else {
            OneKeyBindFragment.a((BaseFragment) null, true);
        }
    }

    @Override // com.ss.union.game.sdk.account.LGSDKAccountService
    public boolean isVisitor() {
        return LGAccountDataUtil.isVisitor();
    }

    @Override // com.ss.union.game.sdk.account.LGSDKAccountService
    public void queryAccountInfo(IQueryAccountInfoCallback iQueryAccountInfoCallback) {
        if (a(iQueryAccountInfoCallback) && b(iQueryAccountInfoCallback)) {
            b.a(iQueryAccountInfoCallback);
        }
    }

    @Override // com.ss.union.game.sdk.account.LGSDKAccountService
    public void unBind(IUnbindAccountCallback iUnbindAccountCallback) {
        if (a(iUnbindAccountCallback) && b(iUnbindAccountCallback)) {
            if (LGAccountDataUtil.isDyLogin()) {
                b.a(iUnbindAccountCallback);
            } else if (iUnbindAccountCallback != null) {
                iUnbindAccountCallback.onFail(LGSDKResult.ERROR_NO_LOGIN, LGSDKResult.ERROR_NO_LOGIN_MSG);
            } else {
                Log.e("LGSDK", "callback is null...");
            }
        }
    }

    @Override // com.ss.union.game.sdk.account.LGSDKAccountService
    public void closeAccount(ICloseAccountCallback iCloseAccountCallback) {
        if (a(iCloseAccountCallback) && b(iCloseAccountCallback)) {
            b.a(iCloseAccountCallback);
        }
    }

    @Override // com.ss.union.game.sdk.account.LGSDKAccountService
    public void updateNickName(INickNameUpdate iNickNameUpdate) {
        if (a(iNickNameUpdate) && b(iNickNameUpdate)) {
            if (ConfigManager.LoginConfig.isUseNickNameSystem()) {
                com.ss.union.game.sdk.nick.a.a(4, iNickNameUpdate);
            } else if (iNickNameUpdate != null) {
                iNickNameUpdate.onFail(-3, "该用户未开启昵称功能, 不能修改昵称");
                Log.e("LGSDK", "该用户未开启昵称功能, 不能修改昵称");
            } else {
                Log.e("LGSDK", "callback is null...");
            }
        }
    }

    private boolean a(IFailCallback iFailCallback) {
        if (!LGSDKCore.isSdkInitSuccess()) {
            if (iFailCallback != null) {
                iFailCallback.onFail(-204, "SDK还未初始化完成");
                Log.e("LGSDK", "SDK has not yet been initialized");
            } else {
                Log.e("LGSDK", "callback is null...");
            }
            return false;
        }
        if (NetworkUtils.isNetworkAvailable()) {
            return true;
        }
        if (iFailCallback != null) {
            iFailCallback.onFail(100, "网络异常，请保持网络连接畅通，再重新操作");
            Log.e("LGSDK", "网络异常，请保持网络连接畅通，再重新操作");
        } else {
            Log.e("LGSDK", "callback is null...");
        }
        return false;
    }

    private boolean b(IFailCallback iFailCallback) {
        if (LGAccountDataUtil.isLogined()) {
            return true;
        }
        if (iFailCallback != null) {
            iFailCallback.onFail(LGSDKResult.ERROR_NO_LOGIN, LGSDKResult.ERROR_NO_LOGIN_MSG);
            return false;
        }
        Log.e("LGSDK", "callback is null...");
        return false;
    }

    private boolean g() {
        if (System.currentTimeMillis() - this.d < 1000) {
            return true;
        }
        this.d = System.currentTimeMillis();
        return false;
    }

    public void a(User user, int i) {
        LGAccountDataUtil.saveUserData(user);
        a(user);
        b(user, i);
    }

    private void a(User user) {
        if (user.is_identify_validated) {
            RealNameLogEvent.reportHasRealName();
        } else {
            RealNameLogEvent.reportNoRealName();
        }
    }

    public void a(int i, String str, int i2) {
        b(i, str, i2);
    }

    private void b(final User user, final int i) {
        if (com.ss.union.game.sdk.nick.a.a(LGAccountDataUtil.getCurrentUserLocal())) {
            com.ss.union.game.sdk.nick.a.a(i, new com.ss.union.game.sdk.nick.a.a() { // from class: com.ss.union.game.sdk.account.b.a.10
                @Override // com.ss.union.game.sdk.nick.a.a
                public void a() {
                    a.this.c(user, i);
                }
            });
        } else {
            c(user, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final User user, final int i) {
        com.ss.union.game.sdk.a.b.a().a(new b.InterfaceC0048b() { // from class: com.ss.union.game.sdk.account.b.a.2
            @Override // com.ss.union.game.sdk.a.b.InterfaceC0048b
            public void a() {
                a.this.d(user, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(User user, int i) {
        String str;
        VUserInfoManager.refreshAccountVUserInfo();
        if (i == 1 || i == 3) {
            if (!DanJuanUtils.isRunningDanJuanVirtual()) {
                com.ss.union.game.sdk.account.f.c.a(ActivityUtils.getTopActivity());
                com.ss.union.game.sdk.c.a.a("SplashToast:show in ohayoo");
            } else {
                com.ss.union.game.sdk.c.a.a("SplashToast:not show");
            }
        }
        if (i == 1) {
            LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.ACCOUNT, LGDetectionConstant.AccountItem.DETECTION_ID_LOGIN_SUCCESS, LGDetectionConstant.DetectionState.PASS);
        } else if (i == 2) {
            LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.ACCOUNT, LGDetectionConstant.AccountItem.DETECTION_ID_VISITOR_BIND_SUCCESS, LGDetectionConstant.DetectionState.PASS);
        } else if (i == 3) {
            LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.ACCOUNT, LGDetectionConstant.AccountItem.DETECTION_ID_SWITCH_SUCCESS, LGDetectionConstant.DetectionState.PASS);
        }
        if (c != null) {
            c.onSuccess(user, i);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("loginSuccess(), user:");
        if (user != null) {
            str = user.open_id + Constants.COLON_SEPARATOR + user.login_type;
        } else {
            str = "";
        }
        sb.append(str);
        sb.append("，method：");
        sb.append(i);
        com.ss.union.game.sdk.c.a.a(sb.toString());
    }

    private void b(int i, String str, int i2) {
        if (i2 == 1) {
            LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.ACCOUNT, LGDetectionConstant.AccountItem.DETECTION_ID_INVOKE_LOGIN, LGDetectionConstant.DetectionState.FAIL);
        } else if (i2 == 2) {
            LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.ACCOUNT, LGDetectionConstant.AccountItem.DETECTION_ID_VISITOR_BIND, LGDetectionConstant.DetectionState.FAIL);
        } else if (i2 == 3) {
            LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.ACCOUNT, LGDetectionConstant.AccountItem.DETECTION_ID_INVOKE_SWITCH, LGDetectionConstant.DetectionState.FAIL);
        }
        c(i, str, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, String str, int i2) {
        if (c != null) {
            c.onFail(new LGSDKResult(i, str), i2);
        }
        com.ss.union.game.sdk.c.a.a("loginFailure(), code: " + i + " msg: " + str + " loginType: " + i2);
    }
}
