package com.ss.union.game.sdk.account.fragment.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytedance.sdk.account.api.response.LoginByTicketResponse;
import com.ss.android.account.model2.BDAccountPlatformEntity;
import com.ss.union.game.sdk.account.callback.IAuthorizeCallBack;
import com.ss.union.game.sdk.account.callback.IOneKeyLoginCallBack;
import com.ss.union.game.sdk.account.d.c;
import com.ss.union.game.sdk.account.f.b;
import com.ss.union.game.sdk.account.result.LGCarrierQueryResult;
import com.ss.union.game.sdk.c.a;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.util.AntiShakeClickListener;
import com.ss.union.game.sdk.common.util.LoadingUtils;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.common.util.ToastUtils;
import com.ss.union.game.sdk.common.util.UIUtils;
import com.ss.union.game.sdk.common.util.ViewPropertyUtils;
import com.ss.union.game.sdk.core.age_tips.AgeTipsImageView;
import com.ss.union.game.sdk.core.age_tips.LGAgeTipsDetailFragment;
import com.ss.union.game.sdk.core.base.account.LGAccountDataUtil;
import com.ss.union.game.sdk.core.base.account.constant.LGAccountConstant;
import com.ss.union.game.sdk.core.base.account.model.User;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.base.event.PageStater;
import com.ss.union.game.sdk.core.browser.BrowserFragment;
import com.ss.union.game.sdk.core.privacy.LGPrivacyPolicyManager;
import com.umeng.message.proguard.l;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class BaseOneKeyFragment extends BaseFragment implements View.OnClickListener {
    public static final String a = "key_is_show_close_btn";
    private static String r;
    protected View b;
    protected View c;
    protected Context d;
    protected TextView e;
    protected TextView f;
    protected ImageView g;
    protected TextView h;
    protected LinearLayout i;
    protected FrameLayout j;
    protected TextView k;
    protected TextView l;
    protected TextView m;
    public String p;
    protected LGCarrierQueryResult q;
    private CheckBox s;
    private AgeTipsImageView t;
    protected boolean n = false;
    protected boolean o = true;
    private long u = System.currentTimeMillis();

    protected abstract void b(String str, String str2);

    protected abstract void e();

    protected abstract String f();

    protected abstract String g();

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String getLayoutId() {
        return "lg_tt_ss_base_container";
    }

    protected abstract String h();

    protected abstract void i();

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initListener() {
    }

    protected abstract int j();

    protected static void a(final IAuthorizeCallBack iAuthorizeCallBack) {
        r = null;
        final ProgressDialog progressDialogShowDialog = LoadingUtils.showDialog();
        final b bVarA = b.a();
        bVarA.a(new IAuthorizeCallBack() { // from class: com.ss.union.game.sdk.account.fragment.base.BaseOneKeyFragment.1
            @Override // com.ss.union.game.sdk.account.callback.IAuthorizeCallBack
            public void onSuccess(String str, String str2) {
                String unused = BaseOneKeyFragment.r = str;
                a.a("authorizeSuccess,phone:" + str + ",netType:" + str2);
                LoadingUtils.dismissDialog(progressDialogShowDialog);
                iAuthorizeCallBack.onSuccess(str, str2);
            }

            @Override // com.ss.union.game.sdk.account.callback.IAuthorizeCallBack
            public void onFail(String str, String str2) {
                a.a("authorizeFail,code:" + str + ",msg:" + str2);
                LoadingUtils.dismissDialog(progressDialogShowDialog);
                iAuthorizeCallBack.onFail(str, str2);
                bVarA.b();
            }

            @Override // com.ss.union.game.sdk.account.callback.IAuthorizeCallBack
            public void onTimeout() {
                a.a("authorizeFail:onTimeout");
                LoadingUtils.dismissDialog(progressDialogShowDialog);
                iAuthorizeCallBack.onTimeout();
                bVarA.b();
            }
        });
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean initArgument(Bundle bundle) {
        if (bundle == null) {
            return true;
        }
        this.n = canShowBack();
        this.o = bundle.getBoolean(a, true);
        return true;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initView() {
        LayoutInflater.from(getContext()).inflate(ResourceUtils.getLayoutIdByName("lg_tt_ss_login_onekey"), (ViewGroup) this.containerView.findViewById(ResourceUtils.getIdByName("lg_content_container")));
        this.b = b("lg_btn_close").click(this).getView();
        this.c = b("lg_btn_back").click(this).getView();
        this.e = (TextView) b("lg_tv_login_user_agreement").click(this).underLine().getView();
        this.f = (TextView) b("lg_tv_login_privacy_policy").click(this).underLine().getView();
        this.g = (ImageView) a("lg_tt_ss_base_container_logo");
        this.h = (TextView) a("lg_tt_ss_base_container_title");
        this.i = (LinearLayout) a("lg_content_container");
        this.j = (FrameLayout) a("lg_title_container");
        this.c.setVisibility(this.n ? 0 : 8);
        this.b.setVisibility(this.o ? 0 : 8);
        this.k = (TextView) b("btn_lg_onekey_login").click(this).getView();
        this.l = (TextView) b("btn_lg_otherway_login").click(this).getView();
        a(ConfigManager.AppConfig.appIsOhayooProduct());
        this.t = (AgeTipsImageView) a("lg_tt_ss_base_container_age_tips");
        this.t.setOnClickListener(new AntiShakeClickListener(this));
        if (ConfigManager.AppConfig.appIsOhayooProduct()) {
            this.t.init(getActivity(), 2);
        }
        this.s = (CheckBox) a("lg_privacy_checkbox");
        UIUtils.expandClickRegion(this.s, 36);
        this.s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.ss.union.game.sdk.account.fragment.base.BaseOneKeyFragment.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                LGPrivacyPolicyManager.setLoginPrivacyChecked(z);
            }
        });
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void onVisible() {
        super.onVisible();
        this.s.setChecked(LGPrivacyPolicyManager.isLoginPrivacyChecked());
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initData() {
        this.h.setText(ResourceUtils.getString(f()));
        this.k.setText(ResourceUtils.getString(g()));
        this.l.setText(ResourceUtils.getString(h()));
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void loadData() throws JSONException {
        String strC = b.a().c();
        String strD = b.a().d();
        if (TextUtils.equals(strD, "mobile")) {
            a(com.ss.union.game.sdk.account.a.a.F, "yidong");
        } else if (TextUtils.equals(strD, "unicom")) {
            a(com.ss.union.game.sdk.account.a.a.F, "liantong");
        } else if (TextUtils.equals(strD, BDAccountPlatformEntity.PLAT_NAME_TELECOM)) {
            a(com.ss.union.game.sdk.account.a.a.F, "dianxin");
        }
        this.p = strC;
        a(LGCarrierQueryResult.parse(strC, strD));
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        b.a().b();
    }

    protected void a(LGCarrierQueryResult lGCarrierQueryResult) {
        this.q = lGCarrierQueryResult;
        View viewA = a("tv_lg_login_operator");
        TextView textView = (TextView) a("tv_lg_login_phone");
        View viewA2 = a("btn_lg_onekey_login");
        TextView textView2 = (TextView) a("btn_lg_otherway_login");
        View viewA3 = a("lg_tt_ss_login_one_key_privacy_container");
        b("tv_lg_login_operator").text(lGCarrierQueryResult.getCarrierTitle());
        b("tv_lg_login_phone").text(lGCarrierQueryResult.getPhone());
        this.m = (TextView) b("tv_lg_login_operator_protocol").text(lGCarrierQueryResult.getCarrierProtocol()).underLine().click(this).getView();
        if (ConfigManager.AppConfig.appIsOhayooProduct()) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewA.getLayoutParams();
        layoutParams.topMargin = UIUtils.dip2Px(18.0f);
        viewA.setLayoutParams(layoutParams);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) textView.getLayoutParams();
        marginLayoutParams.topMargin = UIUtils.dip2Px(8.0f);
        textView.setLayoutParams(marginLayoutParams);
        textView.setTextSize(2, 28.0f);
        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) viewA2.getLayoutParams();
        marginLayoutParams2.topMargin = UIUtils.dip2Px(34.0f);
        viewA2.setLayoutParams(marginLayoutParams2);
        ViewGroup.MarginLayoutParams marginLayoutParams3 = (ViewGroup.MarginLayoutParams) textView2.getLayoutParams();
        marginLayoutParams3.topMargin = UIUtils.dip2Px(16.0f);
        textView2.setLayoutParams(marginLayoutParams3);
        textView2.setTextSize(2, 12.0f);
        textView2.getPaint().setFakeBoldText(true);
        ViewGroup.MarginLayoutParams marginLayoutParams4 = (ViewGroup.MarginLayoutParams) viewA3.getLayoutParams();
        marginLayoutParams4.topMargin = UIUtils.dip2Px(13.0f);
        marginLayoutParams4.bottomMargin = UIUtils.dip2Px(26.0f);
        viewA3.setLayoutParams(marginLayoutParams4);
    }

    private void a(boolean z) {
        if (this.g == null) {
            return;
        }
        this.g.setVisibility(z ? 0 : 8);
        this.h.setVisibility(z ? 8 : 0);
        if (z) {
            return;
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.i.getLayoutParams();
        layoutParams.topMargin = 0;
        this.i.setLayoutParams(layoutParams);
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.j.getLayoutParams();
        layoutParams2.height = UIUtils.dip2Px(64.0f);
        this.j.setLayoutParams(layoutParams2);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.c.getLayoutParams();
        marginLayoutParams.topMargin = UIUtils.dip2Px(24.0f);
        marginLayoutParams.leftMargin = UIUtils.dip2Px(22.0f);
        this.c.setLayoutParams(marginLayoutParams);
        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) this.b.getLayoutParams();
        layoutParams3.gravity = 21;
        layoutParams3.topMargin = 0;
        layoutParams3.rightMargin = UIUtils.dip2Px(21.0f);
        this.b.setLayoutParams(layoutParams3);
    }

    public <T extends View> T a(String str) {
        return (T) this.containerView.findViewById(ResourceUtils.getIdByName(str));
    }

    protected ViewPropertyUtils b(String str) {
        return new ViewPropertyUtils(this.containerView.findViewById(ResourceUtils.getIdByName(str)));
    }

    protected void a(String str, String str2) throws JSONException {
        PageStater.V1.onEvent(str, str2);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) throws JSONException {
        if (System.currentTimeMillis() - this.u > 500) {
            a(view);
        }
        this.u = System.currentTimeMillis();
    }

    public void a(View view) throws JSONException {
        if (view == this.b) {
            close();
            e();
            LGPrivacyPolicyManager.setLoginPrivacyChecked(false);
            com.ss.union.game.sdk.account.a.a.a(j());
            com.ss.union.game.sdk.account.b.a.a().a(-1004, "您好，您已取消操作", j());
            return;
        }
        if (view == this.e) {
            com.ss.union.game.sdk.account.a.a.b(com.ss.union.game.sdk.account.a.a.q);
            LGPrivacyPolicyManager.openUserProtocol(this);
            return;
        }
        if (view == this.f) {
            com.ss.union.game.sdk.account.a.a.b(com.ss.union.game.sdk.account.a.a.r);
            LGPrivacyPolicyManager.openPrivacyProtocol(this);
            return;
        }
        if (view == this.c) {
            back();
            return;
        }
        if (view == this.k) {
            a();
            return;
        }
        if (view == this.l) {
            com.ss.union.game.sdk.account.a.a.b(com.ss.union.game.sdk.account.a.a.p);
            i();
            return;
        }
        if (view == this.m && this.q != null) {
            String carrier = this.q.getCarrier();
            int i = 1;
            if (!TextUtils.equals(carrier, "mobile")) {
                if (TextUtils.equals(carrier, "unicom")) {
                    i = 2;
                } else if (TextUtils.equals(carrier, BDAccountPlatformEntity.PLAT_NAME_TELECOM)) {
                    i = 3;
                }
            }
            PageStater.V1.onEvent(com.ss.union.game.sdk.account.a.a.E, com.ss.union.game.sdk.account.a.a.G, String.valueOf(i));
            a(this.q.getCarrierProtocolUrl(), this.q.getCarrierProtocol(), 0);
            return;
        }
        if (view == this.t) {
            LGAgeTipsDetailFragment.show(this);
        }
    }

    protected void a() {
        if (o()) {
            b();
            com.ss.union.game.sdk.account.a.a.a(com.ss.union.game.sdk.account.a.a.o, com.ss.union.game.sdk.account.a.a.x);
        }
    }

    protected void b() {
        showLoading();
        b.a().a(getActivity(), new IOneKeyLoginCallBack() { // from class: com.ss.union.game.sdk.account.fragment.base.BaseOneKeyFragment.3
            @Override // com.ss.union.game.sdk.account.callback.IOneKeyLoginCallBack
            public void onSuccess(LoginByTicketResponse loginByTicketResponse) {
                BaseOneKeyFragment.this.c();
            }

            @Override // com.ss.union.game.sdk.account.callback.IOneKeyLoginCallBack
            public void onFail(String str, String str2) {
                BaseOneKeyFragment.this.hideLoading();
                com.ss.union.game.sdk.account.a.a.a(com.ss.union.game.sdk.account.a.a.x, false, b.a(str));
                ToastUtils.getInstance().toast(ResourceUtils.getString("lg_login_onekey_fail"));
                BaseOneKeyFragment.this.b(str, str2);
            }
        });
    }

    protected void a(String str, String str2, int i) {
        navigation(BrowserFragment.newInstance(str, str2, false));
    }

    protected final void c() {
        com.ss.union.game.sdk.account.d.b.a(j(), new c() { // from class: com.ss.union.game.sdk.account.fragment.base.BaseOneKeyFragment.4
            @Override // com.ss.union.game.sdk.account.d.c
            public void a(int i, String str) throws JSONException {
                if (BaseOneKeyFragment.this.getActivity() == null) {
                    return;
                }
                BaseOneKeyFragment.this.hideLoading();
                if (BaseOneKeyFragment.this.k()) {
                    PageStater.V1.onEvent(com.ss.union.game.sdk.account.a.a.ad, LGAccountConstant.LoginType.LOGIN_TYPE_CLOUD_PHONE.getName(), "manual", i, 6L);
                } else if (BaseOneKeyFragment.this.m()) {
                    PageStater.V1.onEvent(com.ss.union.game.sdk.account.a.a.O, LGAccountConstant.LoginType.LOGIN_TYPE_CLOUD_PHONE.getName(), "manual", i, 6L);
                } else if (BaseOneKeyFragment.this.l()) {
                    PageStater.V1.onEvent(com.ss.union.game.sdk.account.a.a.ab, LGAccountConstant.LoginType.LOGIN_TYPE_CLOUD_PHONE.getName(), "manual", i, 6L);
                }
                if (!TextUtils.isEmpty(str)) {
                    ToastUtils.getInstance().toast(str);
                } else {
                    ToastUtils.getInstance().toast(BaseOneKeyFragment.this.getResources().getString(b.a(i)) + l.s + i + l.t);
                }
                com.ss.union.game.sdk.account.a.a.a(com.ss.union.game.sdk.account.a.a.x, false, i);
                a.a("handleMsg() login fail, error:" + i + ",msg:" + str);
            }

            @Override // com.ss.union.game.sdk.account.d.c
            public void a(User user) throws JSONException {
                BaseOneKeyFragment.this.close();
                BaseOneKeyFragment.this.hideLoading();
                if (!TextUtils.isEmpty(BaseOneKeyFragment.r)) {
                    user.mobile = LGAccountDataUtil.getMobileWithRegular(BaseOneKeyFragment.r.replace(" ", ""));
                    String unused = BaseOneKeyFragment.r = null;
                }
                if (BaseOneKeyFragment.this.k() || BaseOneKeyFragment.this.m()) {
                    PageStater.V1.onEvent(com.ss.union.game.sdk.account.a.a.ac, LGAccountConstant.LoginType.LOGIN_TYPE_CLOUD_PHONE.getName(), "manual");
                } else if (BaseOneKeyFragment.this.l()) {
                    PageStater.V1.onEvent(com.ss.union.game.sdk.account.a.a.aa, LGAccountConstant.LoginType.LOGIN_TYPE_CLOUD_PHONE.getName(), "manual");
                }
                com.ss.union.game.sdk.account.a.a.a(com.ss.union.game.sdk.account.a.a.x, true, 0);
                com.ss.union.game.sdk.account.b.a.a().a(user, BaseOneKeyFragment.this.j());
            }
        });
    }

    private boolean o() {
        if (this.s.isChecked()) {
            return true;
        }
        ToastUtils.getInstance().toast("阅读并同意底部相关协议才可登录");
        return false;
    }

    protected boolean d() {
        return this.o;
    }

    protected boolean k() {
        return j() == 1;
    }

    protected boolean l() {
        return j() == 3;
    }

    protected boolean m() {
        return j() == 2;
    }
}
