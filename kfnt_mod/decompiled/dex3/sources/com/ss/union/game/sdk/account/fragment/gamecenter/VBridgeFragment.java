package com.ss.union.game.sdk.account.fragment.gamecenter;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ss.union.game.sdk.account.d.b;
import com.ss.union.game.sdk.account.d.c;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.util.ActivityUtils;
import com.ss.union.game.sdk.common.util.MainThreadExecutor;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.common.util.ToastUtils;
import com.ss.union.game.sdk.core.base.account.LGAccountDataUtil;
import com.ss.union.game.sdk.core.base.account.model.User;
import com.ss.union.game.sdk.core.base.account.model.VisitorUser;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.base.event.PageStater;
import com.ss.union.game.sdk.core.vapp.VLog;
import com.ss.union.game.sdk.vcenter.b.a;
import com.ss.union.game.sdk.vcenter.c.a.d;
import com.ss.union.game.sdk.vcenter.g;
import com.tachikoma.core.component.anim.AnimationProperty;
import com.xiaomi.mipush.sdk.Constants;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class VBridgeFragment extends BaseFragment implements d.a {
    public static final String a = "KEY_API_TYPE";
    public static final String b = "KEY_SHOW_CLOSE_BTN";
    public static final String c = "KEY_FROM_AUTO_LOGIN_FAIL";
    private static final String d = "V_ACCOUNT";
    private VisitorUser e;
    private c f = new c() { // from class: com.ss.union.game.sdk.account.fragment.gamecenter.VBridgeFragment.1
        @Override // com.ss.union.game.sdk.account.d.c
        public void a(int i, String str) throws JSONException {
            VLog.Account.d("visitor Login fail code = " + i + " msg = " + str);
            a.a(false, i, str);
            VBridgeFragment.this.hideLoading();
            com.ss.union.game.sdk.account.a.a.a(com.ss.union.game.sdk.account.a.a.y, false, i);
            PageStater.V1.onEvent(com.ss.union.game.sdk.account.a.a.ad, "GUEST", "manual", i, 6L);
            if (i == 50000) {
                com.ss.union.game.sdk.account.b.a.a().a(i, str, 1);
                com.ss.union.game.sdk.account.b.a.a().c();
                return;
            }
            ToastUtils.getInstance().toast(i + Constants.COLON_SEPARATOR + str);
            VBridgeFragment.this.a(1, i, str);
        }

        @Override // com.ss.union.game.sdk.account.d.c
        public void a(User user) throws JSONException {
            VLog.Account.d("visitor Login success");
            VBridgeFragment.this.hideLoading();
            PageStater.V1.onEvent(com.ss.union.game.sdk.account.a.a.ac, "GUEST", "manual");
            VBridgeFragment.this.a(user, 1);
            com.ss.union.game.sdk.account.a.a.a(com.ss.union.game.sdk.account.a.a.y, true, 0);
            a.a(true, 0, "");
        }
    };
    private c g = new c() { // from class: com.ss.union.game.sdk.account.fragment.gamecenter.VBridgeFragment.2
        @Override // com.ss.union.game.sdk.account.d.c
        public void a(int i, String str) throws JSONException {
            VLog.Account.d("visitor auto Login, from auto login fail, " + i + Constants.COLON_SEPARATOR + str);
            VBridgeFragment.this.hideLoading();
            PageStater.V1.onEvent(com.ss.union.game.sdk.account.a.a.ad, com.ss.union.game.sdk.account.a.a.y, "manual", i, 6L);
            if (i == 10000 || i == 10001 || i == 10010 || i == 10012) {
                if (VBridgeFragment.this.e != null) {
                    VBridgeFragment.this.e.tokenValid = false;
                    LGAccountDataUtil.saveUserData2SD(ActivityUtils.getTopActivity(), VBridgeFragment.this.e);
                }
                b.a(VBridgeFragment.this.f);
                return;
            }
            a.a(false, i, str);
            ToastUtils.getInstance().toast(i + Constants.COLON_SEPARATOR + str);
            VBridgeFragment.this.a(1, i, str);
        }

        @Override // com.ss.union.game.sdk.account.d.c
        public void a(User user) {
            VLog.Account.d("visitor auto Login success");
            VBridgeFragment.this.hideLoading();
            VBridgeFragment.this.a(user, 1);
            a.a(true, 0, "");
        }
    };
    private c h = new c() { // from class: com.ss.union.game.sdk.account.fragment.gamecenter.VBridgeFragment.3
        @Override // com.ss.union.game.sdk.account.d.c
        public void a(int i, String str) throws JSONException {
            VLog.Account.d("auto Login, from auto login fail, " + i + Constants.COLON_SEPARATOR + str);
            a.a(false, i, str);
            VBridgeFragment.this.hideLoading();
            PageStater.V1.onEvent(com.ss.union.game.sdk.account.a.a.ad, com.ss.union.game.sdk.account.a.a.y, "manual", i, 6L);
            if (i == 10000 || i == 10001 || i == 10010 || i == 10012) {
                MainThreadExecutor.postDelayed(new Runnable() { // from class: com.ss.union.game.sdk.account.fragment.gamecenter.VBridgeFragment.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        VBridgeFragment.this.a(com.ss.union.game.sdk.vcenter.account.c.a().c(VBridgeFragment.this.canShowBack()).d(VBridgeFragment.this.getArguments().getBoolean(VBridgeFragment.b)).a().b().l());
                    }
                }, 50L);
                return;
            }
            ToastUtils.getInstance().toast(i + Constants.COLON_SEPARATOR + str);
            VBridgeFragment.this.a(1, i, str);
        }

        @Override // com.ss.union.game.sdk.account.d.c
        public void a(User user) {
            VLog.Account.d("auto Login, from auto login success");
            VBridgeFragment.this.hideLoading();
            VBridgeFragment.this.a(user, 1);
            a.a(true, 0, "");
        }
    };

    @Override // com.ss.union.game.sdk.vcenter.c.a.d.a
    public void b() {
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String getLayoutId() {
        return null;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean initArgument(Bundle bundle) {
        return true;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initData() {
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initListener() {
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initView() {
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void loadData() {
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment, android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return null;
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        d.b().a(this);
        Bundle arguments = getArguments();
        int i = arguments.getInt(a);
        boolean z = arguments.getBoolean(b);
        boolean z2 = arguments.getBoolean(c, false);
        a.C0106a c0106aA = com.ss.union.game.sdk.vcenter.account.c.a();
        if (z2) {
            c0106aA.a();
        }
        String strL = c0106aA.c(canShowBack()).d(z).b().l();
        if (i == 1) {
            a(strL);
        } else if (i == 3) {
            b(strL);
        } else if (i == 2) {
            c(strL);
        }
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment, android.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }

    protected void a() throws JSONException {
        if (!ConfigManager.LoginConfig.isAllowGuestLoginSwitch()) {
            ToastUtils.getInstance().toast(ResourceUtils.getString("cp_not_allow_guest_login_tip"));
            com.ss.union.game.sdk.c.a.a("cp do not allow guest login");
            return;
        }
        VLog.Account.d("visitorOauth");
        PageStater.V1.onEvent(com.ss.union.game.sdk.account.a.a.k, "GUEST");
        showLoading();
        this.e = LGAccountDataUtil.readUserDataFromSD(ActivityUtils.getTopActivity());
        if (this.e == null || !this.e.tokenValid) {
            VLog.Account.d("visitor Login, login select");
            b.a(this.f);
        } else {
            VLog.Account.d("auto Login, login select");
            PageStater.V1.onEvent(com.ss.union.game.sdk.account.a.a.ak, "GUEST");
            b.a(this.e.user.token, this.g);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(User user, int i) {
        com.ss.union.game.sdk.account.b.a.a().a(user, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        VLog.Account.d("login start " + str);
        com.ss.union.game.sdk.vcenter.c.b().a(str, new com.ss.union.game.sdk.vcenter.account.a.b() { // from class: com.ss.union.game.sdk.account.fragment.gamecenter.VBridgeFragment.4
            @Override // com.ss.union.game.sdk.vcenter.account.callback.d
            public void a(g gVar) throws JSONException {
                VLog.Account.d("login start onGuestNormalLogin");
                VBridgeFragment.this.back();
                a.a = gVar;
                VBridgeFragment.this.a();
            }

            @Override // com.ss.union.game.sdk.vcenter.account.callback.d
            public void b(g gVar) {
                VLog.Account.d("login start onContinueAutoLogin");
                VBridgeFragment.this.back();
                a.a = gVar;
                b.a(LGAccountDataUtil.getLoginToken(), VBridgeFragment.this.h);
            }

            @Override // com.ss.union.game.sdk.vcenter.account.callback.c
            public void a(int i, User user) {
                VLog.Account.d("login start onSuccess");
                VBridgeFragment.this.close();
                VBridgeFragment.this.a(user, 1);
            }

            @Override // com.ss.union.game.sdk.vcenter.account.callback.c
            public void a(int i, int i2, String str2, String str3) {
                VLog.Account.d("login start onFail " + i2 + " " + str2);
                VBridgeFragment.this.close();
                VBridgeFragment.this.a(1, i2, str2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2, String str) {
        if (i2 == -301) {
            i2 = -1004;
        }
        if (i2 == -1004) {
            back();
        }
        com.ss.union.game.sdk.account.b.a.a().a(i2, str, i);
    }

    private void b(String str) {
        VLog.Account.d("switchAccount start " + str);
        com.ss.union.game.sdk.vcenter.c.b().a(str, new com.ss.union.game.sdk.vcenter.account.a.a() { // from class: com.ss.union.game.sdk.account.fragment.gamecenter.VBridgeFragment.5
            @Override // com.ss.union.game.sdk.vcenter.account.callback.c
            public void a(int i, User user) {
                VLog.Account.d("switchAccount start success ");
                VBridgeFragment.this.close();
                VBridgeFragment.this.a(user, 3);
            }

            @Override // com.ss.union.game.sdk.vcenter.account.callback.c
            public void a(int i, int i2, String str2, String str3) {
                VLog.Account.d("switchAccount start onFail " + i2 + " " + str2);
                VBridgeFragment.this.close();
                VBridgeFragment.this.a(3, i2, str2);
            }
        });
    }

    private void c(String str) {
        VLog.Account.d("bindAccount start " + str);
        com.ss.union.game.sdk.vcenter.c.b().b(str, new com.ss.union.game.sdk.vcenter.account.a.a() { // from class: com.ss.union.game.sdk.account.fragment.gamecenter.VBridgeFragment.6
            @Override // com.ss.union.game.sdk.vcenter.account.callback.c
            public void a(int i, User user) {
                VLog.Account.d("bindAccount start success");
                VBridgeFragment.this.close();
                com.ss.union.game.sdk.account.b.a.a().a(user, 2);
            }

            @Override // com.ss.union.game.sdk.vcenter.account.callback.c
            public void a(int i, int i2, String str2, String str3) {
                VLog.Account.d("bindAccount start onFail " + i2 + " " + str2);
                VBridgeFragment.this.back();
                VBridgeFragment.this.a(2, i2, str2);
            }
        });
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        d.b().b(this);
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment, android.app.Fragment
    public Animator onCreateAnimator(int i, boolean z, int i2) {
        try {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat((Object) null, AnimationProperty.TRANSLATE_X, 0.0f, 0.0f);
            objectAnimatorOfFloat.setDuration(10L);
            return objectAnimatorOfFloat;
        } catch (Throwable unused) {
            return super.onCreateAnimator(i, z, i2);
        }
    }
}
