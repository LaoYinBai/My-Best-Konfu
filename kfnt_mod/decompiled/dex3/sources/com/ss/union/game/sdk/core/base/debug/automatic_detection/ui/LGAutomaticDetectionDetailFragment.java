package com.ss.union.game.sdk.core.base.debug.automatic_detection.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.ss.union.game.sdk.common.dialog.BaseDialogFragment;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.dialog.OperationBuilder;
import com.ss.union.game.sdk.common.util.ActivityUtils;
import com.ss.union.game.sdk.common.util.NetworkUtils;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.common.util.ToastUtils;
import com.ss.union.game.sdk.core.base.debug.automatic_detection.LGDetectionManager;
import com.ss.union.game.sdk.core.base.utils.LogCoreUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGAutomaticDetectionDetailFragment extends BaseFragment {
    public static String a = "key_activity_orientation";
    private static final String b = "LGAutomaticDetectionDetailFragment";
    private LGCommonHeaderLayout c;
    private ListView d;
    private com.ss.union.game.sdk.core.base.debug.automatic_detection.a.a e;
    private LGDarkCommonLoadingLayout f;
    private LGDarkCommonNetWorkLayout g;
    private LGDarkCommonRetryLayout h;
    private Integer i;

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String getLayoutId() {
        return "lg_fragment_automatic_detection_detail";
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
    protected String statusBarColor() {
        return "#000000";
    }

    public static void a() {
        new OperationBuilder(b()).animation(OperationBuilder.Animation.RIGHT).cancelable(true).show();
    }

    private static LGAutomaticDetectionDetailFragment b() {
        Bundle bundle = new Bundle();
        bundle.putInt(a, ActivityUtils.replaceOrientation(ActivityUtils.getTopActivity(), 1));
        LGAutomaticDetectionDetailFragment lGAutomaticDetectionDetailFragment = new LGAutomaticDetectionDetailFragment();
        lGAutomaticDetectionDetailFragment.setArguments(bundle);
        return lGAutomaticDetectionDetailFragment;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initView() {
        View viewFindViewById = findViewById("lg_automatic_detection_detail_root_view");
        this.c = (LGCommonHeaderLayout) findViewById("lg_automatic_detection_detail_header_layout");
        this.d = (ListView) findViewById("lg_automatic_detection_detail_list_view");
        this.f = (LGDarkCommonLoadingLayout) findViewById("lg_automatic_detection_detail_list_loading");
        this.g = (LGDarkCommonNetWorkLayout) findViewById("lg_automatic_detection_detail_list_no_network");
        this.h = (LGDarkCommonRetryLayout) findViewById("lg_automatic_detection_detail_list_retry");
        fitStatusBar(viewFindViewById);
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initData() {
        if (getArguments() == null || !getArguments().containsKey(a)) {
            return;
        }
        this.i = Integer.valueOf(getArguments().getInt(a));
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initListener() {
        this.c.b(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.base.debug.automatic_detection.ui.LGAutomaticDetectionDetailFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LGAutomaticDetectionDetailFragment.this.back();
            }
        }).c(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.base.debug.automatic_detection.ui.LGAutomaticDetectionDetailFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LGAutomaticDetectionDetailFragment.this.navigation(LGAutomaticDetectionSdkParamsListFragment.b());
            }
        });
        this.g.b(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.base.debug.automatic_detection.ui.LGAutomaticDetectionDetailFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!NetworkUtils.isNetworkAvailable()) {
                    ToastUtils.getInstance().toastRes("lg_common_toast_network_error");
                } else {
                    LGAutomaticDetectionDetailFragment.this.loadData();
                }
            }
        });
        this.h.setRetryBtnListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.base.debug.automatic_detection.ui.LGAutomaticDetectionDetailFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LGAutomaticDetectionDetailFragment.this.loadData();
            }
        });
        getDialog().addOnDismissListener(new BaseDialogFragment.OnDismissListener() { // from class: com.ss.union.game.sdk.core.base.debug.automatic_detection.ui.LGAutomaticDetectionDetailFragment.5
            @Override // com.ss.union.game.sdk.common.dialog.BaseDialogFragment.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                if (LGAutomaticDetectionDetailFragment.this.i != null) {
                    ActivityUtils.replaceOrientation(LGAutomaticDetectionDetailFragment.this.getActivity(), LGAutomaticDetectionDetailFragment.this.i.intValue());
                }
            }
        });
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void loadData() {
        if (!NetworkUtils.isNetworkAvailable()) {
            e();
        } else {
            c();
            LGDetectionManager.getDetectionApi().generateDetectionReport(new com.ss.union.game.sdk.core.base.debug.automatic_detection.b.a<com.ss.union.game.sdk.core.base.debug.automatic_detection.c.a>() { // from class: com.ss.union.game.sdk.core.base.debug.automatic_detection.ui.LGAutomaticDetectionDetailFragment.6
                @Override // com.ss.union.game.sdk.core.base.debug.automatic_detection.b.a
                public void a(int i, String str) {
                    LogCoreUtils.logAutomaticDetection("LGAutomaticDetectionDetailFragment code = " + i + "--message = " + str);
                    LGAutomaticDetectionDetailFragment.this.d();
                }

                @Override // com.ss.union.game.sdk.core.base.debug.automatic_detection.b.a
                public void a(com.ss.union.game.sdk.core.base.debug.automatic_detection.c.a aVar) {
                    LGAutomaticDetectionDetailFragment.this.a(aVar);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.ss.union.game.sdk.core.base.debug.automatic_detection.c.a aVar) {
        if (this.e == null) {
            this.e = new com.ss.union.game.sdk.core.base.debug.automatic_detection.a.a(aVar);
            this.d.setAdapter((ListAdapter) this.e);
        } else {
            this.e.a(aVar);
        }
        a(String.format(ResourceUtils.getString("lg_automatic_detection_id_format"), Long.valueOf(aVar.a)));
        f();
    }

    private void a(String str) {
        if (this.c != null) {
            this.c.b(str).a(0).c(ResourceUtils.getString("lg_automatic_detection_sdk_params_btn")).b(0);
        }
    }

    private void c() {
        this.f.setVisibility(0);
        this.f.i();
        this.d.setVisibility(8);
        this.g.setVisibility(8);
        this.h.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.f.setVisibility(8);
        this.f.j();
        this.d.setVisibility(8);
        this.g.setVisibility(8);
        this.h.setVisibility(0);
    }

    private void e() {
        this.f.setVisibility(8);
        this.f.j();
        this.d.setVisibility(8);
        this.g.setVisibility(0);
        this.h.setVisibility(8);
    }

    private void f() {
        this.f.setVisibility(8);
        this.f.j();
        this.d.setVisibility(0);
        this.g.setVisibility(8);
        this.h.setVisibility(8);
    }
}
