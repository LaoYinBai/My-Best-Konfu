package com.kwad.sdk.core.page;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import androidx.annotation.Keep;
import com.kwad.sdk.R;
import com.kwad.sdk.api.core.KsAdSdkDynamicImpl;
import com.kwad.sdk.api.core.ResContext;
import com.kwad.sdk.api.proxy.app.AdWebViewActivity;
import com.kwad.sdk.api.proxy.app.BaseFragmentActivity;
import com.kwad.sdk.core.download.a.a;
import com.kwad.sdk.core.page.widget.b;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.widget.DownloadProgressBar;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

@KsAdSdkDynamicImpl(AdWebViewActivity.class)
@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class AdWebViewVideoActivityProxy extends com.kwad.sdk.h.b {
    public static final String KEY_REPORTED = "key_reported";
    public static final String KEY_TEMPLATE = "key_template";
    public static boolean showingAdWebViewVideoActivity = false;
    private AdTemplate mAdTemplate;
    private com.kwad.sdk.core.download.a.b mApkDownloadHelper;
    private b mFragment;
    private DownloadProgressBar mProgressbar;
    private ViewGroup mWebDownloadContainer;

    /* JADX INFO: Access modifiers changed from: private */
    public a.C0035a getAdClickConfig(boolean z) {
        return new a.C0035a(getActivity()).a(z).b(false).a(this.mAdTemplate).d(false);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x008f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void initView() throws java.lang.IllegalAccessException, java.lang.NoSuchMethodException, java.lang.ClassNotFoundException, java.lang.SecurityException, java.lang.IllegalArgumentException, java.lang.reflect.InvocationTargetException {
        /*
            r6 = this;
            int r0 = com.kwad.sdk.R.id.ksad_web_download_progress
            android.view.View r0 = r6.findViewById(r0)
            com.kwad.sdk.widget.DownloadProgressBar r0 = (com.kwad.sdk.widget.DownloadProgressBar) r0
            r6.mProgressbar = r0
            int r0 = com.kwad.sdk.R.id.ksad_web_download_container
            android.view.View r0 = r6.findViewById(r0)
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            r6.mWebDownloadContainer = r0
            com.kwad.sdk.core.response.model.AdTemplate r0 = r6.mAdTemplate
            com.kwad.sdk.core.response.model.AdInfo r0 = com.kwad.sdk.core.response.a.d.j(r0)
            boolean r1 = com.kwad.sdk.core.response.a.a.B(r0)
            r2 = 0
            if (r1 == 0) goto L3f
            android.view.ViewGroup r1 = r6.mWebDownloadContainer
            r1.setVisibility(r2)
            com.kwad.sdk.core.download.a.b r1 = new com.kwad.sdk.core.download.a.b
            com.kwad.sdk.core.response.model.AdTemplate r3 = r6.mAdTemplate
            com.kwad.sdk.core.page.AdWebViewVideoActivityProxy$1 r4 = new com.kwad.sdk.core.page.AdWebViewVideoActivityProxy$1
            r4.<init>()
            r1.<init>(r3, r4)
            r6.mApkDownloadHelper = r1
            com.kwad.sdk.widget.DownloadProgressBar r0 = r6.mProgressbar
            com.kwad.sdk.core.page.AdWebViewVideoActivityProxy$2 r1 = new com.kwad.sdk.core.page.AdWebViewVideoActivityProxy$2
            r1.<init>()
            r0.setOnClickListener(r1)
            goto L46
        L3f:
            android.view.ViewGroup r0 = r6.mWebDownloadContainer
            r1 = 8
            r0.setVisibility(r1)
        L46:
            int r0 = com.kwad.sdk.R.id.ksad_kwad_titlebar_title
            android.view.View r0 = r6.findViewById(r0)
            android.widget.TextView r0 = (android.widget.TextView) r0
            int r1 = com.kwad.sdk.R.id.ksad_kwad_web_navi_back
            android.view.View r1 = r6.findViewById(r1)
            android.widget.ImageView r1 = (android.widget.ImageView) r1
            int r3 = com.kwad.sdk.R.id.ksad_kwad_web_navi_close
            android.view.View r3 = r6.findViewById(r3)
            android.widget.ImageView r3 = (android.widget.ImageView) r3
            com.kwad.sdk.core.response.model.AdTemplate r4 = r6.mAdTemplate
            java.util.List<com.kwad.sdk.core.response.model.AdInfo> r4 = r4.adInfoList
            if (r4 == 0) goto L8f
            com.kwad.sdk.core.response.model.AdTemplate r4 = r6.mAdTemplate
            java.util.List<com.kwad.sdk.core.response.model.AdInfo> r4 = r4.adInfoList
            int r4 = r4.size()
            if (r4 <= 0) goto L8f
            com.kwad.sdk.core.response.model.AdTemplate r4 = r6.mAdTemplate
            java.util.List<com.kwad.sdk.core.response.model.AdInfo> r4 = r4.adInfoList
            java.lang.Object r4 = r4.get(r2)
            if (r4 == 0) goto L8f
            com.kwad.sdk.core.response.model.AdTemplate r4 = r6.mAdTemplate
            java.util.List<com.kwad.sdk.core.response.model.AdInfo> r4 = r4.adInfoList
            java.lang.Object r4 = r4.get(r2)
            com.kwad.sdk.core.response.model.AdInfo r4 = (com.kwad.sdk.core.response.model.AdInfo) r4
            com.kwad.sdk.core.response.model.AdInfo$AdBaseInfo r4 = r4.adBaseInfo
            java.lang.String r5 = r4.productName
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            if (r5 != 0) goto L8f
            java.lang.String r4 = r4.productName
            goto L91
        L8f:
            java.lang.String r4 = "详情页面"
        L91:
            r0.setText(r4)
            com.kwad.sdk.core.page.AdWebViewVideoActivityProxy$3 r0 = new com.kwad.sdk.core.page.AdWebViewVideoActivityProxy$3
            r0.<init>()
            r3.setOnClickListener(r0)
            com.kwad.sdk.core.page.AdWebViewVideoActivityProxy$4 r0 = new com.kwad.sdk.core.page.AdWebViewVideoActivityProxy$4
            r0.<init>()
            r1.setOnClickListener(r0)
            android.app.Activity r0 = r6.getActivity()
            com.kwad.sdk.utils.af.b(r0)
            android.app.Activity r0 = r6.getActivity()
            r1 = 1
            com.kwad.sdk.utils.d.a(r0, r2, r1)
            int r0 = com.kwad.sdk.R.id.ksad_recycler_container
            com.kwad.sdk.core.response.model.AdTemplate r1 = r6.mAdTemplate
            com.kwad.sdk.core.page.b r1 = com.kwad.sdk.core.page.b.a(r1)
            r6.mFragment = r1
            com.kwad.sdk.core.page.b r1 = r6.mFragment
            com.kwad.sdk.core.download.a.b r2 = r6.mApkDownloadHelper
            r1.a(r2)
            com.kwad.sdk.api.core.fragment.KsFragmentManager r1 = r6.getSupportFragmentManager()
            com.kwad.sdk.api.core.fragment.KsFragmentTransaction r1 = r1.beginTransaction()
            com.kwad.sdk.core.page.b r2 = r6.mFragment
            com.kwad.sdk.api.core.fragment.KsFragmentTransaction r0 = r1.replace(r0, r2)
            r0.commitAllowingStateLoss()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.core.page.AdWebViewVideoActivityProxy.initView():void");
    }

    private boolean isFormAdExitInterceptEnable() {
        if (this.mAdTemplate == null) {
            return false;
        }
        if (com.kwad.sdk.core.config.b.D() && this.mAdTemplate.mIsFromContent) {
            return true;
        }
        return com.kwad.sdk.core.config.b.E() && !this.mAdTemplate.mIsFromContent;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void launch(Context context, AdTemplate adTemplate) {
        if (context == 0 || adTemplate == null) {
            return;
        }
        Intent intent = new Intent(context, (Class<?>) BaseFragmentActivity.FragmentActivity3.class);
        intent.addFlags(268435456);
        intent.putExtra(KEY_TEMPLATE, adTemplate);
        intent.putExtra(KEY_REPORTED, adTemplate.mPvReported);
        context.startActivity(intent);
        boolean z = context instanceof ResContext;
        Object delegatedContext = context;
        if (z) {
            delegatedContext = ((ResContext) context).getDelegatedContext();
        }
        if (delegatedContext instanceof Activity) {
            ((Activity) delegatedContext).overridePendingTransition(0, 0);
        }
    }

    private void showWaitDialog() {
        if (isFormAdExitInterceptEnable()) {
            com.kwad.sdk.core.page.widget.b bVar = new com.kwad.sdk.core.page.widget.b(getActivity(), new b.a() { // from class: com.kwad.sdk.core.page.AdWebViewVideoActivityProxy.5
                @Override // com.kwad.sdk.core.page.widget.b.a
                public void a(DialogInterface dialogInterface) {
                    com.kwad.sdk.core.report.a.f(AdWebViewVideoActivityProxy.this.mAdTemplate, 104);
                    dialogInterface.dismiss();
                }

                @Override // com.kwad.sdk.core.page.widget.b.a
                public void b(DialogInterface dialogInterface) {
                    AdWebViewVideoActivityProxy.super.onBackPressed();
                    com.kwad.sdk.core.report.a.f(AdWebViewVideoActivityProxy.this.mAdTemplate, 105);
                }

                @Override // com.kwad.sdk.core.page.widget.b.a
                public void c(DialogInterface dialogInterface) {
                    com.kwad.sdk.core.report.a.f(AdWebViewVideoActivityProxy.this.mAdTemplate, 106);
                    dialogInterface.dismiss();
                }
            });
            com.kwad.sdk.core.report.a.c(this.mAdTemplate, 103, null);
            bVar.show();
        }
    }

    @Override // com.kwad.sdk.h.b
    protected String getPageName() {
        return "AdWebViewVideoActivityProxy";
    }

    @Override // com.kwad.sdk.h.b, com.kwad.sdk.api.proxy.IActivityProxy
    public void onBackPressed() {
        if (this.mFragment == null || !this.mFragment.a_()) {
            showWaitDialog();
        } else {
            super.onBackPressed();
        }
    }

    @Override // com.kwad.sdk.h.b, com.kwad.sdk.api.proxy.IActivityProxy
    public void onCreate(Bundle bundle) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, SecurityException, IllegalArgumentException, InvocationTargetException {
        super.onCreate(bundle);
        setContentView(R.layout.ksad_activity_ad_video_webview);
        Serializable serializableExtra = getIntent().getSerializableExtra(KEY_TEMPLATE);
        showingAdWebViewVideoActivity = true;
        if (!(serializableExtra instanceof AdTemplate)) {
            finish();
            return;
        }
        this.mAdTemplate = (AdTemplate) serializableExtra;
        this.mAdTemplate.mPvReported = getIntent().getBooleanExtra(KEY_REPORTED, false);
        initView();
    }

    @Override // com.kwad.sdk.h.b, com.kwad.sdk.api.proxy.IActivityProxy
    protected void onDestroy() {
        super.onDestroy();
        showingAdWebViewVideoActivity = false;
        if (this.mApkDownloadHelper != null) {
            this.mApkDownloadHelper.i();
        }
    }

    @Override // com.kwad.sdk.api.proxy.IActivityProxy
    public void onPause() {
        showingAdWebViewVideoActivity = false;
        overridePendingTransition(0, 0);
    }

    @Override // com.kwad.sdk.h.b, com.kwad.sdk.api.proxy.IActivityProxy
    public void onResume() {
        super.onResume();
        showingAdWebViewVideoActivity = true;
    }
}
