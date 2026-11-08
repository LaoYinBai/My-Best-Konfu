package com.ss.union.game.sdk.core.realName.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.ss.union.game.sdk.common.util.KeyboardUtils;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.common.util.UIUtils;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.glide.Glide;
import com.ss.union.game.sdk.core.realName.callback.LGRealNameCallback;
import com.ss.union.game.sdk.core.realName.event.RealNameLogEvent;
import com.ss.union.game.sdk.core.realName.fragment.RealNameFragment;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AwardRealNameFragment extends RealNameFragment {
    private static final String q = "key_award_name";
    private static final String r = "key_award_icon";
    private static final String s = "key_award_custom_info";
    private boolean A;
    private KeyboardUtils.KeyboardObserver B;
    private TextView t;
    private ImageView u;
    private String v;
    private String w;
    private String x;
    private String y;
    private String z;

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String getLayoutId() {
        return "";
    }

    public static RealNameFragment a(int i, boolean z, String str, String str2, String str3, LGRealNameCallback lGRealNameCallback) {
        Bundle bundle = new Bundle();
        AwardRealNameFragment awardRealNameFragment = new AwardRealNameFragment();
        bundle.putInt("key_bundle_type", i);
        bundle.putBoolean("key_show_close_btn", z);
        bundle.putString(q, str);
        bundle.putString(r, str2);
        bundle.putString(s, str3);
        awardRealNameFragment.setArguments(bundle);
        awardRealNameFragment.setCallback(lGRealNameCallback);
        return awardRealNameFragment;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected View getContentView() {
        if (isLandscape()) {
            return b();
        }
        return c();
    }

    @Override // com.ss.union.game.sdk.core.realName.fragment.RealNameFragment, com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean initArgument(Bundle bundle) {
        if (bundle != null) {
            this.v = bundle.getString(q, "");
            this.w = bundle.getString(r, "");
            this.x = bundle.getString(s, "");
        }
        return super.initArgument(bundle);
    }

    @Override // com.ss.union.game.sdk.core.realName.fragment.RealNameFragment, com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initView() {
        super.initView();
        this.t = (TextView) findViewById("lg_reward_real_name_reward_name");
        this.u = (ImageView) findViewById("lg_reward_real_name_icon");
    }

    @Override // com.ss.union.game.sdk.core.realName.fragment.RealNameFragment, com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initData() {
        super.initData();
        if (!TextUtils.isEmpty(this.v)) {
            this.t.setText(this.v);
        }
        if (!TextUtils.isEmpty(this.w)) {
            Glide.with(getContext()).load(this.w).into(this.u);
        }
        e();
        a();
    }

    private void a() {
        if (ConfigManager.LoginConfig.isDialogLogin()) {
            RealNameLogEvent.reportRealNameAwardShowFromLoginWindow();
        } else if (ConfigManager.LoginConfig.isSilentLogin()) {
            RealNameLogEvent.reportRealNameAwardShowFromSilentWindow();
        } else {
            RealNameLogEvent.reportRealNameAwardShowFromNoAccount();
        }
    }

    @Override // com.ss.union.game.sdk.core.realName.fragment.RealNameFragment, com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initListener() {
        super.initListener();
        setSubmitEventListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.realName.fragment.AwardRealNameFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RealNameLogEvent.reportRealNameSubmit();
            }
        });
        setRealNameEvent(new RealNameFragment.a() { // from class: com.ss.union.game.sdk.core.realName.fragment.AwardRealNameFragment.2
            @Override // com.ss.union.game.sdk.core.realName.fragment.RealNameFragment.a
            public void a(boolean z, boolean z2) {
                RealNameLogEvent.reportRealNameAwardResultSuccess();
            }

            @Override // com.ss.union.game.sdk.core.realName.fragment.RealNameFragment.a
            public void a(int i, String str) {
                RealNameLogEvent.reportRealNameAwardResultFail();
            }
        });
        this.B = KeyboardUtils.observeKeyboard(getContext(), getWindow(), new KeyboardUtils.OnKeyboardChangeListener() { // from class: com.ss.union.game.sdk.core.realName.fragment.AwardRealNameFragment.3
            @Override // com.ss.union.game.sdk.common.util.KeyboardUtils.OnKeyboardChangeListener
            public void keyboardShow(int i) {
                int iDip2Px = UIUtils.dip2Px(100.0f);
                if (AwardRealNameFragment.this.n.getScrollY() < iDip2Px / 2) {
                    AwardRealNameFragment.this.n.scrollBy(0, iDip2Px);
                }
            }

            @Override // com.ss.union.game.sdk.common.util.KeyboardUtils.OnKeyboardChangeListener
            public void keyboardHide(int i) {
                AwardRealNameFragment.this.n.scrollTo(0, 0);
            }
        });
    }

    private View b() {
        return LayoutInflater.from(getContext()).inflate(ResourceUtils.getLayoutIdByName("lg_fragment_landscape_real_name_authentication"), (ViewGroup) null);
    }

    private View c() {
        return LayoutInflater.from(getContext()).inflate(ResourceUtils.getLayoutIdByName("lg_fragment_portrait_real_name_authentication"), (ViewGroup) null);
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void screenChange2Landscape() {
        super.screenChange2Landscape();
        d();
        this.containerView.removeAllViews();
        this.containerView.addView(b());
        init();
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void screenChange2Portrait() {
        super.screenChange2Portrait();
        d();
        this.containerView.removeAllViews();
        this.containerView.addView(c());
        init();
    }

    private void d() {
        this.y = this.e.getText().toString();
        this.z = this.f.getText().toString();
        this.A = this.mCheckbox.isChecked();
    }

    private void e() {
        this.e.setText(this.y);
        this.f.setText(this.z);
        this.mCheckbox.setChecked(this.A);
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment, android.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        if (this.B != null) {
            this.B.release();
        }
    }
}
