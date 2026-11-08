package com.ss.union.game.sdk.account.fragment.base;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.SpannedString;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.TypefaceSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.ss.union.game.sdk.account.c.a;
import com.ss.union.game.sdk.account.d.c;
import com.ss.union.game.sdk.account.e.c;
import com.ss.union.game.sdk.account.e.d;
import com.ss.union.game.sdk.account.fragment.normal.SmsCodeFragment;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.ui.LGFormattedEditText;
import com.ss.union.game.sdk.common.util.AntiShakeClickListener;
import com.ss.union.game.sdk.common.util.KeyboardUtils;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.common.util.SpannableStringUtils;
import com.ss.union.game.sdk.common.util.ToastUtils;
import com.ss.union.game.sdk.common.util.UIUtils;
import com.ss.union.game.sdk.core.age_tips.AgeTipsImageView;
import com.ss.union.game.sdk.core.age_tips.LGAgeTipsDetailFragment;
import com.ss.union.game.sdk.core.base.account.constant.LGAccountConstant;
import com.ss.union.game.sdk.core.base.account.model.User;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.base.event.PageStater;
import com.ss.union.game.sdk.core.base.utils.DanJuanUtils;
import com.ss.union.game.sdk.core.privacy.LGPrivacyPolicyManager;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class BaseNormalLoginFragment extends BaseFragment<c, d> implements c.b {
    protected static String a = "key_is_show_close_btn";
    private AgeTipsImageView A;
    private View b;
    private View c;
    private View d;
    private View e;
    private TextView f;
    private TextView g;
    private View h;
    private LGFormattedEditText i;
    private View j;
    private TextView k;
    private View l;
    private TextView m;
    private View n;
    private View o;
    private View p;
    private TextView q;
    private View r;
    private View s;
    private View t;
    private View u;
    private View v;
    private CheckBox w;
    private KeyboardUtils.KeyboardObserver y;
    private a x = a.CN;
    private boolean z = false;

    @Override // com.ss.union.game.sdk.account.e.e.c
    public void a(User user) {
    }

    @Override // com.ss.union.game.sdk.account.e.e.c
    public void b(int i, String str) {
    }

    protected abstract String c();

    protected abstract String d();

    protected abstract int e();

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initData() {
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void loadData() {
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String getLayoutId() {
        return ConfigManager.AppConfig.appIsOhayooProduct() ? "lg_fragment_normal_login_logo" : "lg_fragment_normal_login";
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean initArgument(Bundle bundle) {
        if (bundle == null) {
            return true;
        }
        this.z = bundle.getBoolean(a, this.z);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public d initPresenter() {
        return new d(e());
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initView() throws JSONException {
        this.b = findViewById("lg_login_root");
        this.c = findViewById("lg_login_content");
        this.d = findViewById("lg_btn_back");
        this.e = findViewById("lg_btn_close");
        this.f = (TextView) findViewById("lg_tt_ss_base_container_title");
        this.g = (TextView) findViewById("lg_tv_phone_area");
        this.h = findViewById("lg_btn_input_phone_select");
        this.i = (LGFormattedEditText) findViewById("lg_et_input_phone");
        this.j = findViewById("lg_slice_iv");
        this.k = (TextView) findViewById("lg_btn_next");
        this.l = findViewById("lg_tt_ss_login_third_title_container");
        this.m = (TextView) findViewById("lg_tv_login_other_way");
        this.n = findViewById("lg_tt_ss_login_third_container");
        this.o = findViewById("lg_login_visitor_view");
        this.p = findViewById("lg_login_dy_view");
        this.q = (TextView) findViewById("lg_tt_ss_login_phone_privacy_container");
        this.r = findViewById("lg_ll_area_select_root");
        this.s = findViewById("lg_ll_area_select_cn");
        this.t = findViewById("lg_ll_area_select_twn");
        this.u = findViewById("lg_ll_area_select_hk");
        this.v = findViewById("lg_ll_area_select_macao");
        this.w = (CheckBox) findViewById("lg_privacy_checkbox");
        UIUtils.expandClickRegion(this.w, 36);
        this.w.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.ss.union.game.sdk.account.fragment.base.BaseNormalLoginFragment.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                LGPrivacyPolicyManager.setLoginPrivacyChecked(z);
            }
        });
        this.A = (AgeTipsImageView) findViewById("lg_tt_ss_base_container_age_tips");
        if (this.A != null && ConfigManager.AppConfig.appIsOhayooProduct()) {
            this.A.init(getActivity(), 2);
        }
        this.q.setText(SpannableStringUtils.create("同意 用户协议 和 隐私政策").setUnderLine(3, 7).setClickable(new ClickableSpan() { // from class: com.ss.union.game.sdk.account.fragment.base.BaseNormalLoginFragment.13
            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
            }

            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                LGPrivacyPolicyManager.openUserProtocol(BaseNormalLoginFragment.this);
                com.ss.union.game.sdk.account.a.a.b(com.ss.union.game.sdk.account.a.a.q);
            }
        }, 3, 7).setUnderLine(10, 14).setClickable(new ClickableSpan() { // from class: com.ss.union.game.sdk.account.fragment.base.BaseNormalLoginFragment.12
            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
            }

            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                LGPrivacyPolicyManager.openPrivacyProtocol(BaseNormalLoginFragment.this);
                com.ss.union.game.sdk.account.a.a.b(com.ss.union.game.sdk.account.a.a.r);
            }
        }, 10, 14).makeText());
        this.q.setHighlightColor(Color.parseColor("#00000000"));
        this.q.setMovementMethod(new LinkMovementMethod());
        j();
        this.f.setText(c());
        this.m.setText(d());
        if (this.z) {
            this.e.setVisibility(0);
        } else {
            this.e.setVisibility(4);
            this.e.setEnabled(false);
        }
        if (1 == e() && ConfigManager.LoginConfig.canShowGuestLoginButton()) {
            this.o.setVisibility(0);
        } else {
            this.o.setVisibility(8);
        }
        if (DanJuanUtils.isRunningDanJuanVirtual()) {
            this.p.setVisibility(8);
            if (1 != e()) {
                this.l.setVisibility(8);
                this.n.setVisibility(8);
            }
        }
        if (canShowBack()) {
            this.d.setVisibility(0);
        }
        if (g()) {
            com.ss.union.game.sdk.account.a.a.a(com.ss.union.game.sdk.account.a.a.d);
        } else if (h()) {
            com.ss.union.game.sdk.account.a.a.a(com.ss.union.game.sdk.account.a.a.f);
        } else if (i()) {
            com.ss.union.game.sdk.account.a.a.a(com.ss.union.game.sdk.account.a.a.e);
        }
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void onVisible() {
        super.onVisible();
        this.w.setChecked(LGPrivacyPolicyManager.isLoginPrivacyChecked());
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initListener() {
        this.b.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.account.fragment.base.BaseNormalLoginFragment.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BaseNormalLoginFragment.this.r.setVisibility(8);
            }
        });
        this.d.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.account.fragment.base.BaseNormalLoginFragment.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BaseNormalLoginFragment.this.back();
                com.ss.union.game.sdk.account.a.a.b(com.ss.union.game.sdk.account.a.a.t);
            }
        });
        this.e.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.account.fragment.base.BaseNormalLoginFragment.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) throws JSONException {
                BaseNormalLoginFragment.this.close();
                LGPrivacyPolicyManager.setLoginPrivacyChecked(false);
                BaseNormalLoginFragment.this.d(-1004, "您好，您已取消操作");
                com.ss.union.game.sdk.account.a.a.a(BaseNormalLoginFragment.this.e());
            }
        });
        this.h.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.account.fragment.base.BaseNormalLoginFragment.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BaseNormalLoginFragment.this.r.setVisibility(0);
            }
        });
        this.j.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.account.fragment.base.BaseNormalLoginFragment.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BaseNormalLoginFragment.this.i.setText("");
                BaseNormalLoginFragment.this.j.setVisibility(4);
            }
        });
        this.k.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.account.fragment.base.BaseNormalLoginFragment.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view) throws JSONException {
                if (BaseNormalLoginFragment.this.m()) {
                    BaseNormalLoginFragment.this.b();
                    com.ss.union.game.sdk.account.a.a.a(BaseNormalLoginFragment.this.e(), "phone");
                    com.ss.union.game.sdk.account.a.a.b(com.ss.union.game.sdk.account.a.a.s);
                }
            }
        });
        this.o.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.account.fragment.base.BaseNormalLoginFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) throws JSONException {
                if (BaseNormalLoginFragment.this.m()) {
                    BaseNormalLoginFragment.this.k();
                    com.ss.union.game.sdk.account.a.a.a(com.ss.union.game.sdk.account.a.a.y, com.ss.union.game.sdk.account.a.a.y);
                    com.ss.union.game.sdk.account.a.a.a(BaseNormalLoginFragment.this.e(), com.ss.union.game.sdk.account.a.a.y);
                }
            }
        });
        this.p.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.account.fragment.base.BaseNormalLoginFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) throws JSONException {
                if (BaseNormalLoginFragment.this.m()) {
                    BaseNormalLoginFragment.this.l();
                    com.ss.union.game.sdk.account.a.a.a(com.ss.union.game.sdk.account.a.a.z, com.ss.union.game.sdk.account.a.a.z);
                    com.ss.union.game.sdk.account.a.a.a(BaseNormalLoginFragment.this.e(), com.ss.union.game.sdk.account.a.a.z);
                }
            }
        });
        this.s.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.account.fragment.base.BaseNormalLoginFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) throws JSONException {
                BaseNormalLoginFragment.this.a(a.CN);
            }
        });
        this.t.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.account.fragment.base.BaseNormalLoginFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) throws JSONException {
                BaseNormalLoginFragment.this.a(a.TWN);
            }
        });
        this.u.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.account.fragment.base.BaseNormalLoginFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) throws JSONException {
                BaseNormalLoginFragment.this.a(a.HK);
            }
        });
        this.v.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.account.fragment.base.BaseNormalLoginFragment.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) throws JSONException {
                BaseNormalLoginFragment.this.a(a.Macao);
            }
        });
        if (this.A != null) {
            this.A.setOnClickListener(new AntiShakeClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.account.fragment.base.BaseNormalLoginFragment.8
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LGAgeTipsDetailFragment.show(BaseNormalLoginFragment.this);
                }
            }));
        }
        this.y = KeyboardUtils.observeKeyboard(getContext(), getWindow(), new KeyboardUtils.OnKeyboardChangeListener() { // from class: com.ss.union.game.sdk.account.fragment.base.BaseNormalLoginFragment.9
            @Override // com.ss.union.game.sdk.common.util.KeyboardUtils.OnKeyboardChangeListener
            public void keyboardShow(int i) {
                if (BaseNormalLoginFragment.this.b.getScrollY() < UIUtils.dip2Px(85.0f) / 2) {
                    BaseNormalLoginFragment.this.b.scrollBy(0, UIUtils.dip2Px(85.0f));
                }
            }

            @Override // com.ss.union.game.sdk.common.util.KeyboardUtils.OnKeyboardChangeListener
            public void keyboardHide(int i) {
                BaseNormalLoginFragment.this.b.scrollTo(0, 0);
            }
        });
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment, android.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        if (this.y != null) {
            this.y.release();
        }
    }

    private void j() throws JSONException {
        this.i.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.ss.union.game.sdk.account.fragment.base.BaseNormalLoginFragment.10
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6 || !BaseNormalLoginFragment.this.m()) {
                    return false;
                }
                BaseNormalLoginFragment.this.b();
                return false;
            }
        });
        this.i.setInputType(2);
        this.i.addTextChangedListener(new TextWatcher() { // from class: com.ss.union.game.sdk.account.fragment.base.BaseNormalLoginFragment.11
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    BaseNormalLoginFragment.this.j.setVisibility(0);
                } else {
                    BaseNormalLoginFragment.this.j.setVisibility(4);
                }
                BaseNormalLoginFragment.this.k.setEnabled(a.a(editable.toString(), BaseNormalLoginFragment.this.x.f));
            }
        });
        SpannableString spannableString = new SpannableString(ResourceUtils.getString("lg_error_mobile_empty"));
        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(16, true);
        spannableString.setSpan(new TypefaceSpan("default"), 0, spannableString.length(), 33);
        spannableString.setSpan(absoluteSizeSpan, 0, spannableString.length(), 33);
        this.i.setHint(new SpannedString(spannableString));
        a(a.CN);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(a aVar) throws JSONException {
        this.r.setVisibility(8);
        this.x = aVar;
        this.i.setText("");
        this.i.setFilters(new InputFilter[]{new InputFilter.LengthFilter(aVar.g + 2)});
        this.g.setText(aVar.f);
        PageStater.V1.onEvent("phone_area", aVar.e);
    }

    protected void b() {
        ((d) this.mPresenter).a(this.i.getText().toString().replace(" ", ""), this.x.f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        ((d) this.mPresenter).a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() throws JSONException {
        if (e() != 1) {
            if (e() == 2) {
                PageStater.V1.onEvent(com.ss.union.game.sdk.account.a.a.L, com.ss.union.game.sdk.account.a.a.am);
            } else {
                e();
            }
        }
        ((d) this.mPresenter).b();
    }

    @Override // com.ss.union.game.sdk.account.e.e.c
    public void a(String str, String str2, int i) {
        navigation(SmsCodeFragment.a(str, str2, i, e()));
    }

    @Override // com.ss.union.game.sdk.account.e.e.c
    public void a(int i, String str) {
        toast(str);
    }

    @Override // com.ss.union.game.sdk.account.e.a.c
    public void b(User user) {
        close();
        c(user);
        com.ss.union.game.sdk.account.a.a.a(com.ss.union.game.sdk.account.a.a.y, true, 0);
    }

    @Override // com.ss.union.game.sdk.account.e.a.c
    public void c(int i, String str) {
        toast(str);
        com.ss.union.game.sdk.account.a.a.a(com.ss.union.game.sdk.account.a.a.y, false, i);
    }

    @Override // com.ss.union.game.sdk.account.e.a.c
    public void f() {
        close();
        com.ss.union.game.sdk.account.b.a.a().c();
    }

    @Override // com.ss.union.game.sdk.account.e.g.c
    public void a(User user, LGAccountConstant.LoginType loginType) {
        close();
        c(user);
        com.ss.union.game.sdk.account.a.a.a(com.ss.union.game.sdk.account.a.a.z, true, 0);
    }

    @Override // com.ss.union.game.sdk.account.e.g.c
    public void a(int i, String str, LGAccountConstant.LoginType loginType) {
        toast(str);
        com.ss.union.game.sdk.account.a.a.a(com.ss.union.game.sdk.account.a.a.z, false, i);
    }

    private void c(User user) {
        com.ss.union.game.sdk.account.b.a.a().a(user, e());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, String str) {
        com.ss.union.game.sdk.account.b.a.a().a(i, str, e());
    }

    protected boolean g() {
        return e() == 1;
    }

    protected boolean h() {
        return e() == 3;
    }

    protected boolean i() {
        return e() == 2;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment, android.app.Fragment
    public void onResume() {
        super.onResume();
        hideLoading();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean m() {
        if (this.w.isChecked()) {
            return true;
        }
        ToastUtils.getInstance().toast("阅读并同意底部相关协议才可登录");
        return false;
    }
}
