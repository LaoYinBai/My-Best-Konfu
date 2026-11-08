package com.ss.union.game.sdk.pay.fragment;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.ss.android.downloadlib.addownload.AdQuickAppManager;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.dialog.OperationBuilder;
import com.ss.union.game.sdk.common.mvp.IPresenter;
import com.ss.union.game.sdk.common.util.ActivityUtils;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.common.util.SpannableStringUtils;
import com.ss.union.game.sdk.common.util.ToastUtils;
import com.ss.union.game.sdk.pay.b.a;
import com.ss.union.game.sdk.pay.callback.CheckoutPayModeCallBack;
import com.ss.union.game.sdk.pay.callback.LGPayCallback;
import com.umeng.message.proguard.l;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class GalaxyPayFragment extends BaseFragment<LGPayCallback, IPresenter> {
    private static final String a = "needMoney";
    private static final String b = "existGalaxy";
    private static final String c = "orderSign";
    private static final int d = 3;
    private CheckoutPayModeCallBack e;
    private ImageView f;
    private TextView g;
    private TextView h;
    private RadioGroup i;
    private RadioButton j;
    private RadioButton k;
    private TextView l;
    private String m;
    private int n;
    private int o;

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String getLayoutId() {
        return "lg_fragment_galaxy_pay";
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void loadData() {
    }

    public CheckoutPayModeCallBack a() {
        return this.e;
    }

    public void a(CheckoutPayModeCallBack checkoutPayModeCallBack) {
        this.e = checkoutPayModeCallBack;
    }

    public static void a(String str, int i, int i2, LGPayCallback lGPayCallback, CheckoutPayModeCallBack checkoutPayModeCallBack) {
        new OperationBuilder(b(str, i, i2, lGPayCallback, checkoutPayModeCallBack)).show();
    }

    public static GalaxyPayFragment b(String str, int i, int i2, LGPayCallback lGPayCallback, CheckoutPayModeCallBack checkoutPayModeCallBack) {
        Bundle bundle = new Bundle();
        bundle.putString(c, str);
        bundle.putInt(b, i);
        bundle.putInt(a, i2);
        GalaxyPayFragment galaxyPayFragment = new GalaxyPayFragment();
        galaxyPayFragment.setArguments(bundle);
        galaxyPayFragment.setCallback(lGPayCallback);
        galaxyPayFragment.a(checkoutPayModeCallBack);
        return galaxyPayFragment;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean initArgument(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        this.m = bundle.getString(c, "");
        this.o = bundle.getInt(a, 0);
        this.n = bundle.getInt(b, 0);
        return !TextUtils.isEmpty(this.m);
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initView() {
        this.h = (TextView) findViewById("lg_pay_galaxy_choose_pay_mode");
        this.f = (ImageView) findViewById("lg_pay_galaxy_close");
        this.g = (TextView) findViewById("lg_pay_galaxy");
        this.i = (RadioGroup) findViewById("lg_pay_galaxy_radiogroup");
        this.j = (RadioButton) findViewById("lg_pay_galaxy_radiobutton");
        this.k = (RadioButton) findViewById("lg_pay_other_radiobutton");
        this.l = (TextView) findViewById("lg_pay_galaxy_exchange_or_pay_btn");
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initData() {
        this.h.setText(ResourceUtils.getString("lg_pay_galaxy_choose_pay_mode"));
        RadioButton radioButton = this.j;
        StringBuilder sb = new StringBuilder("小鱼币兑换（可用余额");
        sb.append(this.n + l.t);
        radioButton.setText(sb);
        this.j.setCompoundDrawables(null, null, c(), null);
        this.k.setCompoundDrawables(null, null, c(), null);
        a(true);
        this.k.setText(ResourceUtils.getString("lg_pay_galaxy_other_pay_mode"));
    }

    private Drawable c() {
        Drawable drawable = ResourceUtils.getDrawable("lg_pay_galaxy_choose_radionbutton");
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        return drawable;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initListener() {
        this.f.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.pay.fragment.GalaxyPayFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) throws JSONException {
                GalaxyPayFragment.this.e();
            }
        });
        this.i.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.ss.union.game.sdk.pay.fragment.GalaxyPayFragment.2
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (GalaxyPayFragment.this.j.getId() == i) {
                    GalaxyPayFragment.this.a(true);
                }
                if (GalaxyPayFragment.this.k.getId() == i) {
                    GalaxyPayFragment.this.a(false);
                }
            }
        });
        this.l.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.pay.fragment.GalaxyPayFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (GalaxyPayFragment.this.i.getCheckedRadioButtonId() == GalaxyPayFragment.this.k.getId()) {
                    GalaxyPayFragment.this.e.onCheckoutNativePayMode();
                    GalaxyPayFragment.this.back();
                } else {
                    GalaxyPayFragment.d();
                    GalaxyPayFragment.this.e.onCheckoutGalaxyPayMode();
                    GalaxyPayFragment.this.back();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d() {
        try {
            if (ActivityUtils.getTopActivity() == null) {
                return;
            }
            View viewInflate = View.inflate(ActivityUtils.getTopActivity(), ResourceUtils.getLayoutIdByName("lg_pay_galaxy_toast_onpay"), null);
            ImageView imageView = (ImageView) viewInflate.findViewById(ResourceUtils.getIdByName("lg_user_feedback_submit_result_toast_icon"));
            imageView.setImageResource(ResourceUtils.getDrawableIdByName("lg_pay_galaxy_toast_onpay_image"));
            RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
            rotateAnimation.setFillAfter(true);
            rotateAnimation.setInterpolator(new LinearInterpolator());
            rotateAnimation.setDuration(AdQuickAppManager.DEFAULT_QUICK_APP_CHECK_TIME);
            rotateAnimation.setRepeatCount(-1);
            rotateAnimation.setRepeatMode(1);
            imageView.setAnimation(rotateAnimation);
            ((TextView) viewInflate.findViewById(ResourceUtils.getIdByName("lg_user_feedback_submit_result_toast_content"))).setText(ResourceUtils.getString("lg_pay_galaxy_toast_onpay"));
            ToastUtils.getInstance().toast(new ToastUtils.Builder().view(viewInflate).toast("placeHolder"));
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        if (z) {
            this.g.setTypeface(Typeface.defaultFromStyle(0));
            SpannableStringUtils spannableStringUtilsCreate = SpannableStringUtils.create(this.o + "小鱼币");
            int length = spannableStringUtilsCreate.makeText().length();
            int i = length + (-3);
            spannableStringUtilsCreate.setTextSize(0.42857143f, i, length);
            spannableStringUtilsCreate.setBold(0, i);
            this.g.setText(spannableStringUtilsCreate.makeText());
            this.l.setText(ResourceUtils.getString("lg_pay_galaxy_exchange_right_now"));
            return;
        }
        this.l.setText(ResourceUtils.getString("lg_pay_galaxy_confirm_payment"));
        try {
            TextView textView = this.g;
            textView.setText("￥" + ((float) (new JSONObject(this.m).optInt("total_amount") / 100.0d)));
            this.g.setTypeface(Typeface.defaultFromStyle(1));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean onBackPressed() throws JSONException {
        e();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() throws JSONException {
        a.d();
        back();
        a(104, "用户点击返回按钮取消支付");
    }

    private void a(int i, String str) {
        if (getCallback() != null) {
            getCallback().onPayResult(i, str);
        }
    }
}
