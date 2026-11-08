package com.ss.union.game.sdk.core.init.fragment;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Process;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.dialog.OperationBuilder;
import com.ss.union.game.sdk.common.mvp.BasePresenter;
import com.ss.union.game.sdk.common.util.ActivityUtils;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.common.util.SPUtils;
import com.ss.union.game.sdk.common.util.SpannableStringUtils;
import com.ss.union.game.sdk.common.util.ToastUtils;
import com.ss.union.game.sdk.core.base.event.NoneDidUploadStrategy;
import com.ss.union.game.sdk.core.init.a.c;
import com.ss.union.game.sdk.core.privacy.LGPrivacyPolicyManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PrivacyPolicyFragment extends BaseFragment<a, BasePresenter> {
    public static final String a = "PrivacyPolicyFragment";
    public static boolean b = true;
    private Activity c;
    private View d;
    private View e;
    private TextView f;
    private TextView g;
    private View h;
    private View i;
    private boolean j = true;
    private boolean k = false;
    private int l = -1;

    public interface a {
        void a();
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String getLayoutId() {
        return "lg_fragment_privacy_policy";
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
    protected void loadData() {
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    public boolean onBackPressed() {
        return true;
    }

    public static void a(a aVar) {
        new OperationBuilder(b(aVar)).show();
    }

    public static PrivacyPolicyFragment b(a aVar) {
        PrivacyPolicyFragment privacyPolicyFragment = new PrivacyPolicyFragment();
        privacyPolicyFragment.setCallback(aVar);
        return privacyPolicyFragment;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment, android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.c = getActivity();
        if (this.c == null) {
            return;
        }
        initView();
        NoneDidUploadStrategy.getInstance().onEvent(c.a, c.b);
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (this.k) {
            try {
                SPUtils.getInstance("lg_init_config").put("key_is_agree_privacy_policy", true);
            } catch (Throwable unused) {
            }
            if (getCallback() != null) {
                getCallback().a();
            }
        }
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initView() {
        this.d = findViewById("lg_privacy_policy_body");
        this.e = findViewById("lg_privacy_policy_content");
        this.g = (TextView) findViewById("lg_privacy_policy_webView");
        this.i = findViewById("lg_privacy_policy_agree");
        this.h = findViewById("lg_privacy_policy_deny");
        a();
        this.h.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.init.fragment.PrivacyPolicyFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PrivacyPolicyFragment.this.d();
            }
        });
        this.i.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.init.fragment.PrivacyPolicyFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NoneDidUploadStrategy.getInstance().onEvent(c.a, c.c);
                PrivacyPolicyFragment.this.f();
            }
        });
    }

    private void a() {
        String string = ResourceUtils.getString("lg_privacy_policy_desc");
        String string2 = ResourceUtils.getString("lg_privacy_policy_desc_user_agreement");
        String string3 = ResourceUtils.getString("lg_privacy_policy_desc_privacy_policy");
        int iIndexOf = string.indexOf(string2);
        int length = string2.length() + iIndexOf;
        int iIndexOf2 = string.indexOf(string3);
        int length2 = string3.length() + iIndexOf2;
        int color = Color.parseColor("#FF8A00");
        this.g.setText(SpannableStringUtils.create(string).setTextForegroundColor(color, iIndexOf, length).setClickable(new ClickableSpan() { // from class: com.ss.union.game.sdk.core.init.fragment.PrivacyPolicyFragment.4
            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
            }

            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                PrivacyPolicyFragment.this.c();
            }
        }, iIndexOf, length).setTextForegroundColor(color, iIndexOf2, length2).setClickable(new ClickableSpan() { // from class: com.ss.union.game.sdk.core.init.fragment.PrivacyPolicyFragment.3
            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
            }

            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                PrivacyPolicyFragment.this.b();
            }
        }, iIndexOf2, length2).makeText());
        this.g.setHighlightColor(Color.parseColor("#00000000"));
        this.g.setMovementMethod(new LinkMovementMethod());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LGPrivacyPolicyManager.openPrivacyProtocol(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LGPrivacyPolicyManager.openUserProtocol(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (this.j) {
            NoneDidUploadStrategy.getInstance().onEvent(c.a, c.d);
            NoneDidUploadStrategy.getInstance().onEvent(c.f, c.i);
            this.j = false;
            ToastUtils.getInstance().toastRes("lg_privacy_policy_waring_toast");
            return;
        }
        NoneDidUploadStrategy.getInstance().onEvent(c.a, c.e, new NoneDidUploadStrategy.SendLogCallback() { // from class: com.ss.union.game.sdk.core.init.fragment.PrivacyPolicyFragment.5
            @Override // com.ss.union.game.sdk.core.base.event.NoneDidUploadStrategy.SendLogCallback
            public void onSuccess() {
                PrivacyPolicyFragment.this.e();
            }

            @Override // com.ss.union.game.sdk.core.base.event.NoneDidUploadStrategy.SendLogCallback
            public void onFailure() {
                PrivacyPolicyFragment.this.e();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        try {
            if (b) {
                this.c.moveTaskToBack(true);
            }
            b = true;
            ActivityUtils.exit();
            Process.killProcess(Process.myPid());
            System.exit(0);
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.k = true;
        this.d.setVisibility(8);
        back();
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment, android.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.l == configuration.orientation) {
            return;
        }
        this.l = configuration.orientation;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.g.getLayoutParams();
        marginLayoutParams.height = ResourceUtils.getDimen("lg_privacy_policy_web_height");
        this.g.setLayoutParams(marginLayoutParams);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.e.getLayoutParams();
        layoutParams.height = ResourceUtils.getDimen("lg_privacy_policy_container_height");
        layoutParams.width = ResourceUtils.getDimen("lg_privacy_policy_container_width");
        this.e.setLayoutParams(layoutParams);
    }
}
