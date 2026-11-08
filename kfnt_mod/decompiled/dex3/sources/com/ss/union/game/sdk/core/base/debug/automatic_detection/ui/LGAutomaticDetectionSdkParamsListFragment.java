package com.ss.union.game.sdk.core.base.debug.automatic_detection.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.dialog.OperationBuilder;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.core.base.debug.automatic_detection.LGDetectionManager;
import com.ss.union.game.sdk.core.base.debug.automatic_detection.a.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGAutomaticDetectionSdkParamsListFragment extends BaseFragment {
    private LGCommonHeaderLayout a;
    private ListView b;
    private b c;

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String getLayoutId() {
        return "lg_fragment_automatic_detection_sdk_params_list";
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean initArgument(Bundle bundle) {
        return true;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initData() {
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
    protected String statusBarColor() {
        return "#000000";
    }

    public static void a() {
        new OperationBuilder(b()).show();
    }

    public static LGAutomaticDetectionSdkParamsListFragment b() {
        Bundle bundle = new Bundle();
        LGAutomaticDetectionSdkParamsListFragment lGAutomaticDetectionSdkParamsListFragment = new LGAutomaticDetectionSdkParamsListFragment();
        lGAutomaticDetectionSdkParamsListFragment.setArguments(bundle);
        return lGAutomaticDetectionSdkParamsListFragment;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initView() {
        View viewFindViewById = findViewById("lg_automatic_detection_params_list_root_view");
        this.a = (LGCommonHeaderLayout) findViewById("lg_automatic_detection_params_list_header_layout");
        this.b = (ListView) findViewById("lg_automatic_detection_params_list_view");
        fitStatusBar(viewFindViewById);
        this.a.b(ResourceUtils.getString("lg_automatic_detection_sdk_params_btn")).a(0);
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initListener() {
        this.a.b(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.base.debug.automatic_detection.ui.LGAutomaticDetectionSdkParamsListFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LGAutomaticDetectionSdkParamsListFragment.this.back();
            }
        });
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void loadData() {
        if (this.c == null) {
            this.c = new b(LGDetectionManager.getDetectionParamsApi().getAllParams());
            this.c.a(new b.a() { // from class: com.ss.union.game.sdk.core.base.debug.automatic_detection.ui.LGAutomaticDetectionSdkParamsListFragment.2
                @Override // com.ss.union.game.sdk.core.base.debug.automatic_detection.a.b.a
                public void a(com.ss.union.game.sdk.core.base.debug.automatic_detection.c.b bVar) {
                    LGAutomaticDetectionSdkParamsListFragment.this.navigation(LGAutomaticDetectionSdkParamsDetailFragment.b(bVar.a, bVar.b()));
                }
            });
        }
        this.b.setAdapter((ListAdapter) this.c);
    }
}
