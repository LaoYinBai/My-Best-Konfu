package com.ss.union.game.sdk.core.realName.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.dialog.OperationBuilder;
import com.ss.union.game.sdk.common.util.UIUtils;
import com.ss.union.game.sdk.core.realName.callback.LGRealNameReminderCallback;
import com.ss.union.game.sdk.core.realName.event.RealNameLogEvent;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class RealNameReminderFragment extends BaseFragment {
    public static final String KEY_CONTENT = "key_content";
    private static final int a = 420;
    private static final int b = 178;
    private TextView c;
    private ScrollView d;
    private TextView e;
    private TextView f;
    private ImageView g;
    private String h;
    private LGRealNameReminderCallback i;

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String getLayoutId() {
        return "lg_fragment_real_name_reminder";
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void loadData() {
    }

    public static void show(String str, LGRealNameReminderCallback lGRealNameReminderCallback) {
        RealNameReminderFragment realNameReminderFragment = new RealNameReminderFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key_content", str);
        realNameReminderFragment.setArguments(bundle);
        realNameReminderFragment.a(lGRealNameReminderCallback);
        new OperationBuilder(realNameReminderFragment).show();
    }

    private void a(LGRealNameReminderCallback lGRealNameReminderCallback) {
        this.i = lGRealNameReminderCallback;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean initArgument(Bundle bundle) {
        if (bundle == null) {
            return true;
        }
        this.h = bundle.getString("key_content");
        return true;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initView() {
        this.c = (TextView) findViewById("lg_real_name_reminder_title");
        this.d = (ScrollView) findViewById("lg_real_name_reminder_scroll_view");
        this.e = (TextView) findViewById("lg_real_name_reminder_tv_content");
        this.f = (TextView) findViewById("lg_real_name_reminder_confirm");
        this.g = (ImageView) findViewById("lg_real_name_reminder_iv_shadow");
        if (isLandscape()) {
            c();
        } else {
            b();
        }
        RealNameLogEvent.reportDeviceRealNameShow();
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initData() {
        if (TextUtils.isEmpty(this.h)) {
            return;
        }
        this.e.setText(this.h);
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initListener() {
        this.f.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.realName.fragment.RealNameReminderFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RealNameLogEvent.reportDeviceRealNameClick();
                RealNameReminderFragment.this.a();
            }
        });
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean onBackPressed() {
        a();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (this.i != null) {
            this.i.close();
        }
        back();
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void screenChange2Portrait() {
        super.screenChange2Portrait();
        b();
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void screenChange2Landscape() {
        super.screenChange2Landscape();
        c();
    }

    private void b() {
        a(UIUtils.dip2Px(420.0f));
    }

    private void c() {
        a(UIUtils.dip2Px(178.0f));
    }

    private void a(final int i) {
        this.e.post(new Runnable() { // from class: com.ss.union.game.sdk.core.realName.fragment.RealNameReminderFragment.2
            @Override // java.lang.Runnable
            public void run() {
                int height = RealNameReminderFragment.this.e.getHeight();
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) RealNameReminderFragment.this.d.getLayoutParams();
                if (height >= i) {
                    layoutParams.height = i;
                    RealNameReminderFragment.this.g.setVisibility(0);
                } else {
                    layoutParams.height = -2;
                }
                RealNameReminderFragment.this.d.setLayoutParams(layoutParams);
            }
        });
    }
}
