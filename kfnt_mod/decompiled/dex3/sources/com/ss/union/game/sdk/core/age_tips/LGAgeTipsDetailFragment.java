package com.ss.union.game.sdk.core.age_tips;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.dialog.OperationBuilder;
import com.ss.union.game.sdk.common.net.http.base.callback.CoreNetCallback;
import com.ss.union.game.sdk.common.util.UIUtils;
import com.ss.union.game.sdk.core.base.view.NoNetWorkCard;
import com.ss.union.game.sdk.core.init.config.GameOptionConfig;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGAgeTipsDetailFragment extends BaseFragment {
    private LinearLayout a;
    private ScrollView b;
    private TextView c;
    private ImageView d;
    private NoNetWorkCard e;

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String getLayoutId() {
        return "lg_age_tips_window";
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean initArgument(Bundle bundle) {
        return true;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initData() {
    }

    public static void show(BaseFragment baseFragment) {
        if (baseFragment != null) {
            baseFragment.navigation(newInstance());
        } else {
            new OperationBuilder(newInstance()).show();
        }
    }

    public static LGAgeTipsDetailFragment newInstance() {
        return new LGAgeTipsDetailFragment();
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initView() {
        this.a = (LinearLayout) findViewById("lg_age_tips_window_container");
        this.b = (ScrollView) findViewById("lg_age_tips_window_scroll_view");
        this.c = (TextView) findViewById("lg_age_tips_window_text_content");
        this.d = (ImageView) findViewById("lg_btn_close");
        this.e = (NoNetWorkCard) findViewById("lg_age_tips_window_no_net_card");
        a(getActivity().getResources().getConfiguration().orientation);
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initListener() {
        this.d.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.age_tips.LGAgeTipsDetailFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LGAgeTipsDetailFragment.this.back();
            }
        });
        this.e.listenRetryClick(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.age_tips.LGAgeTipsDetailFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LGAgeTipsDetailFragment.this.a();
            }
        });
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void loadData() {
        String strAgeTipsContent = GameOptionConfig.GameOption.AgeTip.ageTipsContent();
        if (TextUtils.isEmpty(strAgeTipsContent)) {
            a();
        } else {
            this.c.setText(strAgeTipsContent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        c();
        showLoading();
        a.a(new CoreNetCallback<String>() { // from class: com.ss.union.game.sdk.core.age_tips.LGAgeTipsDetailFragment.3
            @Override // com.ss.union.game.sdk.common.net.http.base.callback.CoreNetCallback
            public void onError(int i, String str) {
                LGAgeTipsDetailFragment.this.hideLoading();
                LGAgeTipsDetailFragment.this.b();
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.callback.CoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str) {
                LGAgeTipsDetailFragment.this.hideLoading();
                if (TextUtils.isEmpty(str)) {
                    LGAgeTipsDetailFragment.this.b();
                } else {
                    LGAgeTipsDetailFragment.this.c();
                    LGAgeTipsDetailFragment.this.c.setText(str);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.b.setVisibility(8);
        this.e.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.b.setVisibility(0);
        this.e.setVisibility(8);
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment, android.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a(configuration.orientation);
    }

    private void a(int i) {
        int iDip2Px = UIUtils.dip2Px(24.0f);
        int iDip2Px2 = UIUtils.dip2Px(126.0f);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -1);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -1);
        if (i == 2) {
            layoutParams.leftMargin = iDip2Px2;
            layoutParams.rightMargin = iDip2Px2;
            layoutParams.topMargin = iDip2Px;
            layoutParams.bottomMargin = iDip2Px;
            this.a.setLayoutParams(layoutParams);
            layoutParams2.bottomMargin = UIUtils.dip2Px(24.0f);
            this.b.setLayoutParams(layoutParams2);
            this.e.setLayoutParams(layoutParams3);
            return;
        }
        if (i == 1) {
            layoutParams.leftMargin = iDip2Px;
            layoutParams.rightMargin = iDip2Px;
            layoutParams.topMargin = iDip2Px2;
            layoutParams.bottomMargin = iDip2Px2;
            this.a.setLayoutParams(layoutParams);
            layoutParams2.bottomMargin = UIUtils.dip2Px(54.0f);
            this.b.setLayoutParams(layoutParams2);
            layoutParams3.topMargin = UIUtils.dip2Px(55.0f);
            this.e.setLayoutParams(layoutParams3);
        }
    }
}
