package com.ss.union.game.sdk.core.base.debug.automatic_detection.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.dialog.OperationBuilder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGAutomaticDetectionSdkParamsDetailFragment extends BaseFragment {
    public static final String a = "key_title";
    public static final String b = "key_detail";
    private LGCommonHeaderLayout c;
    private TextView d;

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String getLayoutId() {
        return "lg_fragment_automatic_detection_sdk_params_detail";
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean initArgument(Bundle bundle) {
        return true;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean isShowStatusBar() {
        return true;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean isStatusBarDarkMode() {
        return false;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void loadData() {
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String statusBarColor() {
        return "#000000";
    }

    public static void a(String str, String str2) {
        new OperationBuilder(b(str, str2)).show();
    }

    public static LGAutomaticDetectionSdkParamsDetailFragment b(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(a, str);
        bundle.putString(b, str2);
        LGAutomaticDetectionSdkParamsDetailFragment lGAutomaticDetectionSdkParamsDetailFragment = new LGAutomaticDetectionSdkParamsDetailFragment();
        lGAutomaticDetectionSdkParamsDetailFragment.setArguments(bundle);
        return lGAutomaticDetectionSdkParamsDetailFragment;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initView() {
        View viewFindViewById = findViewById("lg_detection_parameter_detail_root_view");
        this.c = (LGCommonHeaderLayout) findViewById("lg_detection_parameter_detail_header_layout");
        this.d = (TextView) findViewById("lg_detection_parameter_detail_tv_params_description");
        fitStatusBar(viewFindViewById);
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initData() {
        String string = "";
        String string2 = "";
        Bundle arguments = getArguments();
        if (arguments != null) {
            string = arguments.getString(a);
            string2 = arguments.getString(b);
        }
        this.c.b(string).a(0);
        this.d.setText(string2);
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initListener() {
        this.c.b(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.base.debug.automatic_detection.ui.LGAutomaticDetectionSdkParamsDetailFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LGAutomaticDetectionSdkParamsDetailFragment.this.back();
            }
        });
    }
}
