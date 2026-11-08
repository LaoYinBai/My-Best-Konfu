package com.ss.union.game.sdk.account.e;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.bdturing.c;
import com.bytedance.sdk.account.api.IBDAccountAPI;
import com.bytedance.sdk.account.api.call.MobileApiResponse;
import com.bytedance.sdk.account.impl.BDAccountDelegate;
import com.bytedance.sdk.account.mobile.query.QuickLoginQueryObj;
import com.bytedance.sdk.account.mobile.query.SendCodeQueryObj;
import com.bytedance.sdk.account.mobile.thread.call.QuickLoginCallback;
import com.bytedance.sdk.account.mobile.thread.call.SendCodeCallback;
import com.sigmob.sdk.common.Constants;
import com.ss.union.game.sdk.account.e.e;
import com.ss.union.game.sdk.common.mvp.BasePresenter;
import com.ss.union.game.sdk.common.util.ActivityUtils;
import com.ss.union.game.sdk.common.util.GlobalApplicationUtils;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.core.applog.AppLogManager;
import com.ss.union.game.sdk.core.base.account.LGAccountDataUtil;
import com.ss.union.game.sdk.core.base.account.constant.LGAccountConstant;
import com.ss.union.game.sdk.core.base.account.model.User;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.base.event.PageStater;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class f extends BasePresenter<e.c> implements e.b {
    public static final int a = 30;
    private int b;
    private IBDAccountAPI c;
    private com.bytedance.bdturing.a d;

    public f(int i) {
        this.b = i;
        b();
    }

    private void b() {
        Context context = GlobalApplicationUtils.getContext();
        this.c = BDAccountDelegate.createBDAccountApi(context);
        try {
            this.d = com.bytedance.bdturing.a.a().a(new c.a().a(AppIdManager.apAppID()).b(ConfigManager.AppConfig.appName()).c("2.3.3.3").d(context.getResources().getConfiguration().locale.getLanguage()).e(ConfigManager.AppConfig.appChannel()).a(c.b.a).i(AppLogManager.getInstance().getDid()).h(AppLogManager.getInstance().getIid()).a(context.getApplicationContext()).a(com.ss.union.game.sdk.account.b.h));
        } catch (Throwable unused) {
        }
    }

    @Override // com.ss.union.game.sdk.account.e.e.a
    public void a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            if (this.mView != 0) {
                ((e.c) this.mView).a(-1, "手机号不能为空");
                return;
            }
            return;
        }
        String strReplace = str.replace(" ", "");
        if (!com.ss.union.game.sdk.account.c.a.a(strReplace, str2)) {
            if (this.mView != 0) {
                ((e.c) this.mView).a(-1, "手机号错误");
                return;
            }
            return;
        }
        if (com.ss.union.game.sdk.account.c.a.TWN.f.equals(str2) && strReplace.length() == 10 && strReplace.startsWith(Constants.FAIL)) {
            strReplace = strReplace.substring(1, strReplace.length());
        }
        if (com.ss.union.game.sdk.account.f.a.a(strReplace)) {
            b(strReplace, str2);
        } else {
            c(strReplace, str2);
        }
    }

    private void b(String str, String str2) {
        if (this.mView != 0) {
            ((e.c) this.mView).a(str, str2, 30);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final String str, final String str2) {
        if (this.c != null) {
            showLoading();
            this.c.sendCode(str2 + com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SERVER + str, (String) null, 24, new SendCodeCallback() { // from class: com.ss.union.game.sdk.account.e.f.1
                public void onSuccess(MobileApiResponse<SendCodeQueryObj> mobileApiResponse) throws JSONException {
                    PageStater.V1.onEvent("send_message", "success", "sms_code_page", 0, f.this.a());
                    f.this.hideLoading();
                    if (f.this.mView != null) {
                        if (mobileApiResponse == null || mobileApiResponse.mobileObj == null || mobileApiResponse.mobileObj.mResendTime <= 0) {
                            ((e.c) f.this.mView).a(str, str2, 30);
                        } else {
                            ((e.c) f.this.mView).a(str, str2, mobileApiResponse.mobileObj.mResendTime);
                        }
                    }
                }

                public void onError(MobileApiResponse<SendCodeQueryObj> mobileApiResponse, int i) {
                    int i2;
                    f.this.hideLoading();
                    String str3 = "";
                    if (mobileApiResponse == null || mobileApiResponse.mobileObj == null) {
                        i2 = -1;
                    } else {
                        str3 = mobileApiResponse.mobileObj.mErrorMsg;
                        i2 = mobileApiResponse.mobileObj.mError;
                    }
                    com.ss.union.game.sdk.account.a.a.a(com.ss.union.game.sdk.account.a.a.x, false, i2);
                    if (f.this.mView != null) {
                        ((e.c) f.this.mView).a(i2, f.this.a(i2, str3));
                    }
                }

                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNeedSecureCaptcha(MobileApiResponse<SendCodeQueryObj> mobileApiResponse) throws JSONException {
                    f.this.hideLoading();
                    com.ss.union.game.sdk.account.a.a.a(com.ss.union.game.sdk.account.a.a.x, false, 3);
                    PageStater.V1.onEvent("verification_code", "new_show_slider_verification_code", "", 0, f.this.a());
                    if (f.this.d != null) {
                        f.this.d.a(ActivityUtils.getTopActivity(), 2, new com.bytedance.bdturing.b() { // from class: com.ss.union.game.sdk.account.e.f.1.1
                            public void a(int i) throws JSONException {
                                PageStater.V1.onEvent("slide_verification_code_result", "new_slide_verification_code_fail", "", i, f.this.a());
                                if (f.this.mView != null) {
                                    ((e.c) f.this.mView).a(i, f.this.a(i, "验证失败 " + i));
                                }
                            }

                            public void a(int i, String str3, String str4) throws JSONException {
                                PageStater.V1.onEvent("slide_verification_code_result", "new_slide_verification_code_success", "", 0, f.this.a());
                                f.this.c(str, str2);
                            }
                        });
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(int i, String str) {
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        switch (i) {
            case com.ss.union.game.sdk.account.b.j /* 1201 */:
                return "验证码为空，请重试";
            case com.ss.union.game.sdk.account.b.k /* 1202 */:
                return "验证码错误,请重新输入";
            case com.ss.union.game.sdk.account.b.l /* 1203 */:
                return "确认验证码正确性，请稍后重试";
            case com.ss.union.game.sdk.account.b.m /* 1204 */:
                return "确认验证码发送场景的正确性，联系值周人员";
            case com.ss.union.game.sdk.account.b.n /* 1205 */:
                return "验证码发送失败,请稍后重试";
            case com.ss.union.game.sdk.account.b.o /* 1206 */:
                return "验证码发送太频繁,请稍后再试";
            default:
                return !TextUtils.isEmpty(str) ? str : ResourceUtils.getString("lg_net_work_error", "网络请求失败，请重试");
        }
    }

    @Override // com.ss.union.game.sdk.account.e.e.a
    public void a(String str, String str2, String str3) {
        if (com.ss.union.game.sdk.account.f.a.a(str)) {
            b(str, str2, str3);
        } else {
            c(str, str2, str3);
        }
    }

    private void b(final String str, String str2, String str3) {
        if (com.ss.union.game.sdk.account.f.a.b(str3)) {
            showLoading();
            com.ss.union.game.sdk.account.d.b.a(str, str3, this.b, new com.ss.union.game.sdk.account.d.c() { // from class: com.ss.union.game.sdk.account.e.f.2
                @Override // com.ss.union.game.sdk.account.d.c
                public void a(int i, String str4) {
                    f.this.hideLoading();
                    if (f.this.mView != null) {
                        ((e.c) f.this.mView).b(i, str4);
                    }
                }

                @Override // com.ss.union.game.sdk.account.d.c
                public void a(User user) {
                    user.mobile = LGAccountDataUtil.getMobileWithRegular(str.replace(" ", ""));
                    f.this.hideLoading();
                    if (f.this.mView != null) {
                        ((e.c) f.this.mView).a(user);
                    }
                }
            });
        } else if (this.mView != 0) {
            ((e.c) this.mView).b(-1, ResourceUtils.getString("lg_virtual_sms_code_error", "验证码输入错误"));
        }
    }

    private void c(final String str, String str2, String str3) {
        showLoading();
        this.c.quickLogin(str2 + com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SERVER + str, str3, (String) null, new QuickLoginCallback() { // from class: com.ss.union.game.sdk.account.e.f.3
            public void onSuccess(MobileApiResponse<QuickLoginQueryObj> mobileApiResponse) {
                com.ss.union.game.sdk.account.d.b.a(f.this.b, LGAccountConstant.LoginType.LOGIN_TYPE_CLOUD_PHONE, new com.ss.union.game.sdk.account.d.c() { // from class: com.ss.union.game.sdk.account.e.f.3.1
                    @Override // com.ss.union.game.sdk.account.d.c
                    public void a(int i, String str4) {
                        f.this.hideLoading();
                        if (f.this.mView != null) {
                            ((e.c) f.this.mView).b(i, str4);
                        }
                    }

                    @Override // com.ss.union.game.sdk.account.d.c
                    public void a(User user) {
                        user.mobile = LGAccountDataUtil.getMobileWithRegular(str.replace(" ", ""));
                        f.this.hideLoading();
                        if (f.this.mView != null) {
                            ((e.c) f.this.mView).a(user);
                        }
                    }
                });
            }

            public void onError(MobileApiResponse<QuickLoginQueryObj> mobileApiResponse, int i) {
                f.this.hideLoading();
                if (f.this.mView != null) {
                    ((e.c) f.this.mView).b(i, mobileApiResponse.errorMsg);
                }
            }
        });
    }

    int a() {
        switch (this.b) {
            case 1:
            default:
                return 1;
            case 2:
                return 3;
            case 3:
                return 2;
        }
    }
}
