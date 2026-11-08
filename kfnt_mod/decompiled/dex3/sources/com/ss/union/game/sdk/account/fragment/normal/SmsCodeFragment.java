package com.ss.union.game.sdk.account.fragment.normal;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.ss.union.game.sdk.account.c.a;
import com.ss.union.game.sdk.account.d.c;
import com.ss.union.game.sdk.account.e.e;
import com.ss.union.game.sdk.account.e.f;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.ui.LGFormattedEditText;
import com.ss.union.game.sdk.common.ui.verifyCode.VerifyCodeEditText;
import com.ss.union.game.sdk.common.util.CountDownUtils;
import com.ss.union.game.sdk.common.util.KeyboardUtils;
import com.ss.union.game.sdk.common.util.UIUtils;
import com.ss.union.game.sdk.core.base.account.constant.LGAccountConstant;
import com.ss.union.game.sdk.core.base.account.model.User;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.base.event.PageStater;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SmsCodeFragment extends BaseFragment<c, f> implements e.c {
    private static final String a = "mobile";
    private static final String b = "area";
    private static final String c = "retry_time";
    private static final String d = "operation_type";
    private View e;
    private View f;
    private View g;
    private LGFormattedEditText h;
    private TextView i;
    private VerifyCodeEditText j;
    private String k;
    private String l;
    private int m;
    private int n;
    private KeyboardUtils.KeyboardObserver o;

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initData() {
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void loadData() {
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean onBackPressed() {
        return true;
    }

    public static SmsCodeFragment a(String str, String str2, int i, int i2) {
        Bundle bundle = new Bundle();
        bundle.putString("mobile", str);
        bundle.putString(b, str2);
        bundle.putInt(c, i);
        bundle.putInt(d, i2);
        SmsCodeFragment smsCodeFragment = new SmsCodeFragment();
        smsCodeFragment.setArguments(bundle);
        return smsCodeFragment;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String getLayoutId() {
        return ConfigManager.AppConfig.appIsOhayooProduct() ? "lg_fragment_sms_captcha_logo" : "lg_fragment_sms_captcha";
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean initArgument(Bundle bundle) {
        if (bundle != null) {
            this.k = bundle.getString("mobile");
            this.l = bundle.getString(b);
            this.m = bundle.getInt(c);
            this.n = bundle.getInt(d);
        }
        return !TextUtils.isEmpty(this.k);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public f initPresenter() {
        return new f(this.n);
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initView() throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        this.e = findViewById("content_root");
        this.f = findViewById("lg_btn_back");
        this.g = findViewById("lg_btn_close");
        this.h = (LGFormattedEditText) findViewById("lg_send_code_phone");
        this.i = (TextView) findViewById("lg_sms_code_resend_btn");
        this.j = (VerifyCodeEditText) findViewById("sms_code_et");
        if (!a.CN.f.equals(this.l)) {
            this.h.setFormatStyle("333333333333333333");
        }
        this.h.setText(this.k);
        this.j.initStyle(new VerifyCodeEditText.VerifyCodeEditTextParams().setShowCursor(true).setFlickerCursor(true).setCursorColor(Color.parseColor("#FFFFCA00")).setTextSize(24).setTextColor(Color.parseColor("#ff333333")).setUnSelectedLineColor(Color.parseColor("#FFE5E5E5")).setSelectedLineColor(Color.parseColor("#FFFFCA00")).setSplitWidth(13).setCodeLength(4).setBgColor(0).setInputType(2).setErrorColor(Color.parseColor("#fffe496a")));
        this.j.requestInputFocus();
        b(this.m);
        com.ss.union.game.sdk.account.a.a.a(com.ss.union.game.sdk.account.a.a.j);
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initListener() {
        this.f.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.account.fragment.normal.SmsCodeFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SmsCodeFragment.this.back();
                com.ss.union.game.sdk.account.a.a.b(com.ss.union.game.sdk.account.a.a.v);
            }
        });
        this.i.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.account.fragment.normal.SmsCodeFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SmsCodeFragment.this.b();
                com.ss.union.game.sdk.account.a.a.b(com.ss.union.game.sdk.account.a.a.u);
            }
        });
        this.j.setOnTextFinishListener(new VerifyCodeEditText.OnTextFinishListener() { // from class: com.ss.union.game.sdk.account.fragment.normal.SmsCodeFragment.3
            @Override // com.ss.union.game.sdk.common.ui.verifyCode.VerifyCodeEditText.OnTextFinishListener
            public void onFinish(String str) {
                SmsCodeFragment.this.a(str);
            }
        });
        this.o = KeyboardUtils.observeKeyboard(getContext(), getWindow(), new KeyboardUtils.OnKeyboardChangeListener() { // from class: com.ss.union.game.sdk.account.fragment.normal.SmsCodeFragment.4
            @Override // com.ss.union.game.sdk.common.util.KeyboardUtils.OnKeyboardChangeListener
            public void keyboardShow(int i) {
                if (SmsCodeFragment.this.e.getScrollY() < UIUtils.dip2Px(85.0f) / 2) {
                    SmsCodeFragment.this.e.scrollBy(0, UIUtils.dip2Px(85.0f));
                }
            }

            @Override // com.ss.union.game.sdk.common.util.KeyboardUtils.OnKeyboardChangeListener
            public void keyboardHide(int i) {
                SmsCodeFragment.this.e.scrollTo(0, 0);
            }
        });
        a(this.m);
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment, android.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        if (this.o != null) {
            this.o.release();
        }
    }

    private void a(int i) {
        CountDownUtils.createTimer(i, 1L, TimeUnit.SECONDS, new CountDownUtils.OnCountDownTimerListener() { // from class: com.ss.union.game.sdk.account.fragment.normal.SmsCodeFragment.5
            @Override // com.ss.union.game.sdk.common.util.CountDownUtils.OnCountDownTimerListener
            public void onTick(long j) {
                SmsCodeFragment.this.b((int) j);
            }

            @Override // com.ss.union.game.sdk.common.util.CountDownUtils.OnCountDownTimerListener
            public void onFinish() {
                SmsCodeFragment.this.b(0);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        if (i > 0) {
            this.i.setText(i + "s重发");
            this.i.setTextColor(Color.parseColor("#ff999999"));
            this.i.setEnabled(false);
            return;
        }
        this.i.setText("重新发送");
        this.i.setTextColor(Color.parseColor("#ffffb200"));
        this.i.setBackground(null);
        this.i.setEnabled(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.i.setEnabled(false);
        this.j.clearText();
        ((f) this.mPresenter).a(this.k, this.l);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        com.ss.union.game.sdk.account.a.a.a(com.ss.union.game.sdk.account.a.a.o, com.ss.union.game.sdk.account.a.a.x);
        ((f) this.mPresenter).a(this.k, this.l, str);
    }

    @Override // com.ss.union.game.sdk.account.e.e.c
    public void a(String str, String str2, int i) {
        a(i);
    }

    @Override // com.ss.union.game.sdk.account.e.e.c
    public void a(int i, String str) {
        toast(str);
        a(30);
    }

    @Override // com.ss.union.game.sdk.account.e.e.c
    public void a(User user) throws JSONException {
        com.ss.union.game.sdk.account.a.a.a(com.ss.union.game.sdk.account.a.a.x, true, 0);
        if (1 == this.n || 2 == this.n) {
            PageStater.V1.onEvent(com.ss.union.game.sdk.account.a.a.ac, LGAccountConstant.LoginType.LOGIN_TYPE_CLOUD_PHONE.getName(), "manual");
        } else if (3 == this.n) {
            PageStater.V1.onEvent(com.ss.union.game.sdk.account.a.a.aa, LGAccountConstant.LoginType.LOGIN_TYPE_CLOUD_PHONE.getName(), "manual");
        }
        com.ss.union.game.sdk.account.b.a.a().a(user, this.n);
        close();
    }

    @Override // com.ss.union.game.sdk.account.e.e.c
    public void b(int i, String str) throws JSONException {
        com.ss.union.game.sdk.account.a.a.a(com.ss.union.game.sdk.account.a.a.x, false, i);
        if (1 == this.n) {
            PageStater.V1.onEvent(com.ss.union.game.sdk.account.a.a.ad, LGAccountConstant.LoginType.LOGIN_TYPE_CLOUD_PHONE.getName(), "manual", i, 6L);
        } else if (2 == this.n) {
            PageStater.V1.onEvent(com.ss.union.game.sdk.account.a.a.O, LGAccountConstant.LoginType.LOGIN_TYPE_CLOUD_PHONE.getName(), "manual", i, 6L);
        } else if (3 == this.n) {
            PageStater.V1.onEvent(com.ss.union.game.sdk.account.a.a.ab, LGAccountConstant.LoginType.LOGIN_TYPE_CLOUD_PHONE.getName(), "manual", i, 6L);
        }
        toast(str);
        this.j.clearText();
    }
}
