package com.ss.union.game.sdk.core.realName.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.common.util.SpannableStringUtils;
import com.ss.union.game.sdk.common.util.ToastUtils;
import com.ss.union.game.sdk.common.util.UIUtils;
import com.ss.union.game.sdk.core.base.account.LGAccountDataUtil;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.base.event.PageStater;
import com.ss.union.game.sdk.core.init.config.GameOptionConfig;
import com.ss.union.game.sdk.core.privacy.LGPrivacyPolicyManager;
import com.ss.union.game.sdk.core.realName.LGRealNameManagerImpl;
import com.ss.union.game.sdk.core.realName.callback.LGRealNameCallback;
import com.ss.union.game.sdk.core.realName.e.a;
import com.ss.union.game.sdk.core.realName.e.b;
import com.ss.union.game.sdk.core.realName.entity.LGRealNameRewardInfo;
import com.ss.union.game.sdk.core.realName.event.RealNameLogEvent;
import java.util.HashMap;
import java.util.regex.PatternSyntaxException;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class RealNameFragment extends BaseFragment<LGRealNameCallback, b> implements a.b {
    protected static final String KEY_BUNDLE_TYPE = "key_bundle_type";
    protected static final String KEY_SHOW_CLOSE_BTN = "key_show_close_btn";
    public static final int KEY_TYPE_22_POINT_REAL_NAME_TEENAGER = 106;
    public static final int KEY_TYPE_22_POINT_UN_REAL_NAME_TEENAGER = 107;
    public static final int KEY_TYPE_FROM_PAY = 109;
    public static final int KEY_TYPE_REAL_NAME_INVOKE_OUTER = 108;
    public static final int KEY_TYPE_VISITOR_TIME_LIMITED = 104;
    public static final int TYPE_ACCOUNT_REAL_NAME_ALTER_LOGIN = 101;
    public static final int TYPE_AFTER_GUEST_LOGIN = 102;
    public static final int TYPE_CREATE_GUEST_FAIL = 103;
    public static final int TYPE_DEVICE_REAL_NAME_AFTER_INIT = 100;
    public static final int TYPE_TEENAGER_ANTI_ADDI = 105;
    private static final int t = 1;
    private static final int u = 2;
    private static final String v = "填写格式不正确";
    private static final String w = "身份证有非法字符！";
    private static final String x = "身份证长度不能超过18位！";
    LinearLayout a;
    ImageView b;
    ImageView c;
    ImageView d;
    EditText e;
    EditText f;
    TextView g;
    TextView h;
    TextView i;
    ProgressBar j;
    FrameLayout k;
    TextView l;
    ImageView m;
    protected CheckBox mCheckbox;
    View n;
    private TextView q;
    private int s;
    private View.OnClickListener y;
    private a z;
    private boolean r = true;
    boolean o = true;
    boolean p = true;

    protected interface a {
        void a(int i, String str);

        void a(boolean z, boolean z2);
    }

    private static String a(int i) {
        if (i == -1004) {
            return "取消实名认证";
        }
        if (i == 4) {
            return "参数错误";
        }
        if (i == 10002) {
            return "自动登录token不合法";
        }
        if (i == 41000) {
            return "当天实名认证次数达到限制";
        }
        switch (i) {
            case com.taobao.accs.net.b.ACCS_RECEIVE_TIMEOUT /* 40000 */:
                return "不能对游客账号进行实名";
            case 40001:
                return "该账号已经完成实名认证";
            case 40002:
                return "身份证号不合法";
            case 40003:
                return "实名认证失败";
            default:
                return "服务器出错,请稍后重试";
        }
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initData() {
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void loadData() {
    }

    protected void setSubmitEventListener(View.OnClickListener onClickListener) {
        this.y = onClickListener;
    }

    protected void setRealNameEvent(a aVar) {
        this.z = aVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    public b initPresenter() {
        return new b();
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean initArgument(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        this.s = bundle.getInt(KEY_BUNDLE_TYPE, this.s);
        if (this.s == 108) {
            this.r = true;
        } else {
            if (!bundle.getBoolean(KEY_SHOW_CLOSE_BTN, true)) {
                this.r = false;
                return true;
            }
            if (ConfigManager.LoginConfig.isNoUserLogin()) {
                this.r = GameOptionConfig.GameOption.AntiAddiction.Device.isRealNameWindowCanClose();
            } else {
                this.r = GameOptionConfig.GameOption.AntiAddiction.Account.isRealNameWindowCanClose();
            }
        }
        return true;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initView() {
        this.n = findViewById("root_view");
        this.a = (LinearLayout) findViewById("lg_rl_ll");
        this.b = (ImageView) findViewById("lg_rl_close");
        this.c = (ImageView) findViewById("lg_name_del_iv");
        this.d = (ImageView) findViewById("lg_card_del_iv");
        this.e = (EditText) findViewById("lg_rl_name_et");
        this.f = (EditText) findViewById("lg_rl_card_et");
        this.g = (TextView) findViewById("lg_card_error_tv");
        this.h = (TextView) findViewById("lg_name_error_tv");
        this.i = (TextView) findViewById("lg_rl_next");
        this.j = (ProgressBar) findViewById("lg_rl_loading");
        this.k = (FrameLayout) findViewById("lg_submit_fl");
        this.l = (TextView) findViewById("real_name_msg_tv");
        this.m = (ImageView) findViewById("lg_real_name_back");
        this.mCheckbox = (CheckBox) findViewById("lg_privacy_checkbox");
        UIUtils.expandClickRegion(this.mCheckbox, 36);
        this.q = (TextView) findViewById("lg_real_name_privacy_container");
        this.q.setText(SpannableStringUtils.create("认证服务协议").setUnderLine(0, 6).setClickable(new ClickableSpan() { // from class: com.ss.union.game.sdk.core.realName.fragment.RealNameFragment.1
            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(@NonNull TextPaint textPaint) {
            }

            @Override // android.text.style.ClickableSpan
            public void onClick(@NonNull View view) {
                LGPrivacyPolicyManager.openIdentifyProtocol(RealNameFragment.this);
            }
        }, 0, 6).makeText());
        this.q.setHighlightColor(Color.parseColor("#00000000"));
        this.q.setMovementMethod(new LinkMovementMethod());
        if (LGAccountDataUtil.isLogined()) {
            boolean z = this.s == 108;
            if (LGAccountDataUtil.isVisitor()) {
                RealNameLogEvent.reportShow(z ? RealNameLogEvent.REAL_NAME_EVENT_VALUE_WINDOW_LOGIN_ZHUDONG : RealNameLogEvent.REAL_NAME_EVENT_VALUE_WINDOW_LOGIN_BEIDONG);
            } else {
                RealNameLogEvent.reportShow(RealNameLogEvent.REAL_NAME_EVENT_VALUE_WINDOW_LOGIN_NONTOURIST);
            }
        }
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initListener() {
        this.c.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.realName.fragment.RealNameFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (RealNameFragment.this.e != null) {
                    RealNameFragment.this.e.setText("");
                }
            }
        });
        this.d.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.realName.fragment.RealNameFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (RealNameFragment.this.f != null) {
                    RealNameFragment.this.f.setText("");
                }
            }
        });
        b();
        this.f.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.ss.union.game.sdk.core.realName.fragment.RealNameFragment.4
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    RealNameFragment.this.a(2, (String) null);
                }
            }
        });
        this.f.addTextChangedListener(new TextWatcher() { // from class: com.ss.union.game.sdk.core.realName.fragment.RealNameFragment.5
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                String string = RealNameFragment.this.f.getText().toString();
                if (TextUtils.isEmpty(string)) {
                    RealNameFragment.this.a(2, (String) null);
                    if (RealNameFragment.this.d != null && RealNameFragment.this.d.getVisibility() == 0) {
                        RealNameFragment.this.d.setVisibility(8);
                    }
                } else {
                    int length = string.length();
                    if (length > 0) {
                        if (length >= 15) {
                            if (length == 15 || length == 18) {
                                if (!RealNameFragment.isLegalId(string)) {
                                    RealNameFragment.this.a(1, RealNameFragment.v);
                                } else {
                                    RealNameFragment.this.a(2, (String) null);
                                }
                            } else if (length > 18) {
                                RealNameFragment.this.a(1, RealNameFragment.x);
                            }
                        }
                        if (RealNameFragment.this.d != null && RealNameFragment.this.d.getVisibility() == 8) {
                            RealNameFragment.this.d.setVisibility(0);
                        }
                    }
                }
                RealNameFragment.this.b();
            }
        });
        this.e.addTextChangedListener(new TextWatcher() { // from class: com.ss.union.game.sdk.core.realName.fragment.RealNameFragment.6
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editable != null && editable.length() > 0) {
                    if (RealNameFragment.this.c != null && RealNameFragment.this.c.getVisibility() == 8) {
                        RealNameFragment.this.c.setVisibility(0);
                    }
                    RealNameFragment.this.o = false;
                } else {
                    if (RealNameFragment.this.c != null && RealNameFragment.this.c.getVisibility() == 0) {
                        RealNameFragment.this.c.setVisibility(8);
                    }
                    RealNameFragment.this.o = true;
                }
                RealNameFragment.this.b();
            }
        });
        this.b.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.realName.fragment.RealNameFragment.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) throws JSONException {
                RealNameFragment.this.a(-1004, true);
                RealNameLogEvent.closeRealNameWindow(RealNameFragment.this.s);
                RealNameFragment.this.close();
            }
        });
        this.k.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.realName.fragment.RealNameFragment.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (RealNameFragment.this.d()) {
                    HashMap map = new HashMap();
                    map.put("realname_click", "realname_submit");
                    PageStater.onEvent(RealNameLogEvent.REAL_NAME_EVENT_TYPE_VALUE, map);
                    if (RealNameFragment.this.y != null) {
                        RealNameFragment.this.y.onClick(view);
                    }
                    RealNameFragment.this.c();
                }
            }
        });
        this.m.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.realName.fragment.RealNameFragment.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RealNameFragment.this.a(-1004, false);
            }
        });
        if (this.r) {
            this.b.setVisibility(0);
        } else {
            this.b.setVisibility(8);
        }
        this.m.setVisibility(canShowBack() ? 0 : 8);
        a();
    }

    private void a() {
        if (this.s == 109) {
            this.l.setText(ResourceUtils.getString("lg_tt_ss_real_name_msg_for_pay"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (TextUtils.isEmpty(this.e.getText().toString()) || TextUtils.isEmpty(this.f.getText().toString()) || this.o || this.p) {
            if (this.k != null) {
                this.k.setEnabled(false);
                this.i.setEnabled(false);
                return;
            }
            return;
        }
        if (this.k != null) {
            this.k.setEnabled(true);
            this.i.setEnabled(true);
        }
    }

    public static boolean isLegalId(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return str.toUpperCase().matches("(^\\d{15}$)|(^\\d{17}([0-9]|X)$)");
        } catch (PatternSyntaxException unused) {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, String str) {
        if (this.f == null || this.g == null) {
            return;
        }
        switch (i) {
            case 1:
                if (!TextUtils.isEmpty(str)) {
                    this.g.setVisibility(0);
                    this.g.setText(str);
                }
                this.f.setBackgroundResource(ResourceUtils.getDrawableIdByName("lg_real_name_input_error"));
                this.p = true;
                break;
            case 2:
                if (this.g.getVisibility() == 0) {
                    this.g.setVisibility(8);
                }
                this.f.setBackgroundResource(ResourceUtils.getDrawableIdByName("lg_selector_real_name_input"));
                this.p = false;
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, boolean z) {
        HashMap map = new HashMap();
        map.put(RealNameLogEvent.REAL_NAME_EVENT_KEY_STATUS, "unnamed");
        PageStater.onEvent(RealNameLogEvent.REAL_NAME_EVENT_TYPE_VALUE, map);
        if (z) {
            LGRealNameManagerImpl.getInstance().globalRealNameFail(i, a(i));
            if (getCallback() != null) {
                getCallback().onFail(i, a(i));
            }
            close();
            return;
        }
        back();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.j.setVisibility(0);
        this.i.setVisibility(8);
        hideKeyboard();
        String string = this.e.getText().toString();
        String string2 = this.f.getText().toString();
        if (LGAccountDataUtil.isLogined()) {
            b(string2, string);
        } else {
            a(string2, string);
        }
    }

    private void a(String str, String str2) {
        ((b) this.mPresenter).a(str, str2);
    }

    @Override // com.ss.union.game.sdk.core.realName.e.a.b
    public void onDeviceRealNameSuccess(boolean z, boolean z2, LGRealNameRewardInfo lGRealNameRewardInfo) throws JSONException {
        a(z, z2, lGRealNameRewardInfo);
        com.ss.union.game.sdk.core.realName.a.a.a(z2);
    }

    @Override // com.ss.union.game.sdk.core.realName.e.a.b
    public void onDeviceRealNameFailure(int i, String str) throws JSONException {
        b(i, str);
    }

    private void b(String str, String str2) {
        ((b) this.mPresenter).b(str, str2);
    }

    @Override // com.ss.union.game.sdk.core.realName.e.a.b
    public void onAccountRealNameSuccess(boolean z, boolean z2, LGRealNameRewardInfo lGRealNameRewardInfo) throws JSONException {
        a(z, z2, lGRealNameRewardInfo);
    }

    @Override // com.ss.union.game.sdk.core.realName.e.a.b
    public void onAccountRealNameFailure(int i, String str) throws JSONException {
        b(i, str);
    }

    private void a(boolean z, boolean z2, LGRealNameRewardInfo lGRealNameRewardInfo) throws JSONException {
        if (this.z != null) {
            this.z.a(z, z2);
        }
        RealNameLogEvent.realNameAuthResult(true, this.s, 0);
        if (z) {
            RealNameLogEvent.reportRealNameSuccess(z2 ? RealNameLogEvent.REAL_NAME_EVENT_VALUE_ADULT : RealNameLogEvent.REAL_NAME_EVENT_VALUE_MINOR);
        }
        this.j.setVisibility(8);
        this.i.setVisibility(0);
        if (getCallback() != null) {
            getCallback().onSuccess(z, z2, lGRealNameRewardInfo);
        }
        LGRealNameManagerImpl.getInstance().globalRealNameSuccess(z, z2, lGRealNameRewardInfo);
        close();
    }

    private void b(int i, String str) throws JSONException {
        RealNameLogEvent.realNameAuthResult(false, this.s, i);
        RealNameLogEvent.reportRealNameFail(i + "");
        if (this.z != null) {
            this.z.a(i, str);
        }
        this.j.setVisibility(8);
        this.g.setVisibility(0);
        this.i.setVisibility(0);
        if (i == 40001) {
            a(i, true);
        } else if (!TextUtils.isEmpty(str)) {
            this.g.setText(str);
        } else {
            this.g.setText(a(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d() {
        if (this.mCheckbox.isChecked()) {
            return true;
        }
        ToastUtils.getInstance().toast("阅读并同意底部协议才可进行实名认证");
        return false;
    }
}
