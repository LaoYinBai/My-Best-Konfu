package com.ss.union.game.sdk.account.e;

import com.ss.union.game.sdk.account.e.a;
import com.ss.union.game.sdk.common.mvp.BasePresenter;
import com.ss.union.game.sdk.common.util.GlobalApplicationUtils;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.common.util.ToastUtils;
import com.ss.union.game.sdk.core.base.account.LGAccountDataUtil;
import com.ss.union.game.sdk.core.base.account.constant.LGAccountConstant;
import com.ss.union.game.sdk.core.base.account.model.User;
import com.ss.union.game.sdk.core.base.account.model.VisitorUser;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.base.event.PageStater;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b extends BasePresenter<a.c> implements a.b {
    @Override // com.ss.union.game.sdk.account.e.a.InterfaceC0049a
    public void a() {
        if (!ConfigManager.LoginConfig.isAllowGuestLoginSwitch()) {
            ToastUtils.getInstance().toast(ResourceUtils.getString("cp_not_allow_guest_login_tip"));
            com.ss.union.game.sdk.c.a.a("cp do not allow guest login");
            return;
        }
        VisitorUser userDataFromSD = LGAccountDataUtil.readUserDataFromSD(GlobalApplicationUtils.getContext());
        if (userDataFromSD == null || !userDataFromSD.tokenValid) {
            com.ss.union.game.sdk.c.a.a("Visitor Login");
            b();
        } else {
            com.ss.union.game.sdk.c.a.a("slient mode SD Auto Login");
            a(userDataFromSD.user.token);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        showLoading();
        com.ss.union.game.sdk.account.d.b.a(new com.ss.union.game.sdk.account.d.c() { // from class: com.ss.union.game.sdk.account.e.b.1
            @Override // com.ss.union.game.sdk.account.d.c
            public void a(int i, String str) throws JSONException {
                b.this.hideLoading();
                if (b.this.mView != null) {
                    if (50000 == i) {
                        ((a.c) b.this.mView).f();
                    } else {
                        ((a.c) b.this.mView).c(i, str);
                    }
                }
                PageStater.V1.onEvent(com.ss.union.game.sdk.account.a.a.ad, LGAccountConstant.LoginType.LOGIN_TYPE_GUEST.getName(), "manual", i, 6L);
            }

            @Override // com.ss.union.game.sdk.account.d.c
            public void a(User user) {
                b.this.hideLoading();
                if (b.this.mView != null) {
                    ((a.c) b.this.mView).b(user);
                }
            }
        });
    }

    private void a(String str) throws JSONException {
        showLoading();
        PageStater.V1.onEvent(com.ss.union.game.sdk.account.a.a.ak, LGAccountConstant.LoginType.LOGIN_TYPE_GUEST.getName());
        com.ss.union.game.sdk.account.d.b.a(str, new com.ss.union.game.sdk.account.d.c() { // from class: com.ss.union.game.sdk.account.e.b.2
            @Override // com.ss.union.game.sdk.account.d.c
            public void a(int i, String str2) throws JSONException {
                b.this.hideLoading();
                if (i != 10000 && i != 10001 && i != 10010 && i != 10012) {
                    if (b.this.mView != null) {
                        ((a.c) b.this.mView).c(i, str2);
                    }
                } else {
                    VisitorUser userDataFromSD = LGAccountDataUtil.readUserDataFromSD(GlobalApplicationUtils.getContext());
                    if (userDataFromSD != null) {
                        userDataFromSD.tokenValid = false;
                        LGAccountDataUtil.saveUserData2SD(GlobalApplicationUtils.getContext(), userDataFromSD);
                    }
                    b.this.b();
                    com.ss.union.game.sdk.c.a.a("handleMsg() auto login fail ,token invalid and go to pop login");
                }
                com.ss.union.game.sdk.account.a.a.a("auto", false, i);
                PageStater.V1.onEvent(com.ss.union.game.sdk.account.a.a.ad, LGAccountDataUtil.getLoginType(), "auto", i, 6L);
            }

            @Override // com.ss.union.game.sdk.account.d.c
            public void a(User user) throws JSONException {
                b.this.hideLoading();
                if (b.this.mView != null) {
                    ((a.c) b.this.mView).b(user);
                }
                com.ss.union.game.sdk.account.a.a.a("auto", true, 0);
                PageStater.V1.onEvent(com.ss.union.game.sdk.account.a.a.ac, LGAccountDataUtil.getLoginType(), "auto");
            }
        });
    }
}
