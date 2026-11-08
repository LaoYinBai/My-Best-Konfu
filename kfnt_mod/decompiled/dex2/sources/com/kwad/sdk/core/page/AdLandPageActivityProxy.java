package com.kwad.sdk.core.page;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Keep;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.R;
import com.kwad.sdk.api.core.KsAdSdkDynamicImpl;
import com.kwad.sdk.api.core.ResContext;
import com.kwad.sdk.api.proxy.app.AdWebViewActivity;
import com.kwad.sdk.core.page.widget.webview.KsAdWebView;
import com.kwad.sdk.core.report.p;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.webview.jshandler.b;
import com.kwad.sdk.core.webview.jshandler.f;
import com.kwad.sdk.core.webview.jshandler.k;
import com.kwad.sdk.core.webview.jshandler.p;
import com.kwad.sdk.core.webview.jshandler.s;
import com.kwad.sdk.core.webview.jshandler.t;
import com.kwad.sdk.core.webview.kwai.g;
import com.kwad.sdk.utils.be;
import org.json.JSONObject;

@KsAdSdkDynamicImpl(AdWebViewActivity.class)
@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class AdLandPageActivityProxy extends com.kwad.sdk.h.a {
    private static final String KEY_DIALOG_URL = "key_dialog_url";
    public static final String KEY_TEMPLATE = "key_template_json";
    private static final String TAG = "AdWebViewActivityProxy";
    private static boolean mIsDownloadDialogShowing = false;
    private AdTemplate mAdTemplate;
    private KsAdWebView mAdWebView;
    private String mDialogUrl;
    private com.kwad.sdk.core.webview.a mJsBridgeContext;
    private g mJsInterface;
    private ViewGroup mWebContainer;
    private ImageView mWebTipBarCloseBtn;
    private LinearLayout mWebTipBarLayout;
    private TextView mWebTipBarTitle;
    private s webCardRegisterLifecycleListenerHandler;

    private void clearJsInterfaceRegister() {
        if (this.mJsInterface != null) {
            this.mJsInterface.a();
            this.mJsInterface = null;
        }
    }

    private void handlerLandingPageAtEndCard(KsAdWebView ksAdWebView) {
        p.a aVar = new p.a();
        aVar.n = 0;
        ksAdWebView.setClientParams(aVar);
    }

    private void inflateJsBridgeContext() {
        this.mJsBridgeContext = new com.kwad.sdk.core.webview.a();
        this.mJsBridgeContext.a(this.mAdTemplate);
        this.mJsBridgeContext.a = 0;
        this.mJsBridgeContext.e = this.mAdWebView;
        this.mJsBridgeContext.d = this.mWebContainer;
    }

    private void initTipBarView() {
        this.mWebTipBarLayout = (LinearLayout) findViewById(R.id.ksad_web_tip_bar);
        this.mWebTipBarTitle = (TextView) findViewById(R.id.ksad_web_tip_bar_textview);
        this.mWebTipBarCloseBtn = (ImageView) findViewById(R.id.ksad_web_tip_close_btn);
        this.mWebTipBarCloseBtn.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.core.page.AdLandPageActivityProxy.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AdLandPageActivityProxy.this.mWebTipBarLayout.setVisibility(8);
            }
        });
        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(this.mAdTemplate);
        boolean zAf = com.kwad.sdk.core.response.a.a.af(adInfoJ);
        String strAb = com.kwad.sdk.core.response.a.a.ab(adInfoJ);
        if (!zAf) {
            this.mWebTipBarLayout.setVisibility(8);
            return;
        }
        this.mWebTipBarLayout.setVisibility(0);
        this.mWebTipBarTitle.setText(strAb);
        this.mWebTipBarTitle.setSelected(true);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0079  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void initView() {
        /*
            r6 = this;
            int r0 = com.kwad.sdk.R.id.ksad_video_webview
            android.view.View r0 = r6.findViewById(r0)
            com.kwad.sdk.core.page.widget.webview.KsAdWebView r0 = (com.kwad.sdk.core.page.widget.webview.KsAdWebView) r0
            r6.mAdWebView = r0
            com.kwad.sdk.core.page.widget.webview.KsAdWebView r0 = r6.mAdWebView
            r6.handlerLandingPageAtEndCard(r0)
            com.kwad.sdk.core.page.widget.webview.KsAdWebView r0 = r6.mAdWebView
            com.kwad.sdk.core.response.model.AdTemplate r1 = r6.mAdTemplate
            r0.setTemplateData(r1)
            com.kwad.sdk.core.page.widget.webview.KsAdWebView r0 = r6.mAdWebView
            r0.b()
            com.kwad.sdk.core.page.widget.webview.KsAdWebView r0 = r6.mAdWebView
            com.kwad.sdk.core.page.AdLandPageActivityProxy$2 r1 = new com.kwad.sdk.core.page.AdLandPageActivityProxy$2
            r1.<init>()
            r0.setOnWebViewScrollChangeListener(r1)
            int r0 = com.kwad.sdk.R.id.ksad_kwad_titlebar_title
            android.view.View r0 = r6.findViewById(r0)
            android.widget.TextView r0 = (android.widget.TextView) r0
            int r1 = com.kwad.sdk.R.id.ksad_kwad_web_navi_back
            android.view.View r1 = r6.findViewById(r1)
            android.widget.ImageView r1 = (android.widget.ImageView) r1
            int r2 = com.kwad.sdk.R.id.ksad_kwad_web_navi_close
            android.view.View r2 = r6.findViewById(r2)
            android.widget.ImageView r2 = (android.widget.ImageView) r2
            int r3 = com.kwad.sdk.R.id.ksad_landing_page_root
            android.view.View r3 = r6.findViewById(r3)
            android.view.ViewGroup r3 = (android.view.ViewGroup) r3
            r6.mWebContainer = r3
            com.kwad.sdk.core.response.model.AdTemplate r3 = r6.mAdTemplate
            java.util.List<com.kwad.sdk.core.response.model.AdInfo> r3 = r3.adInfoList
            r4 = 0
            if (r3 == 0) goto L79
            com.kwad.sdk.core.response.model.AdTemplate r3 = r6.mAdTemplate
            java.util.List<com.kwad.sdk.core.response.model.AdInfo> r3 = r3.adInfoList
            int r3 = r3.size()
            if (r3 <= 0) goto L79
            com.kwad.sdk.core.response.model.AdTemplate r3 = r6.mAdTemplate
            java.util.List<com.kwad.sdk.core.response.model.AdInfo> r3 = r3.adInfoList
            java.lang.Object r3 = r3.get(r4)
            if (r3 == 0) goto L79
            com.kwad.sdk.core.response.model.AdTemplate r3 = r6.mAdTemplate
            java.util.List<com.kwad.sdk.core.response.model.AdInfo> r3 = r3.adInfoList
            java.lang.Object r3 = r3.get(r4)
            com.kwad.sdk.core.response.model.AdInfo r3 = (com.kwad.sdk.core.response.model.AdInfo) r3
            com.kwad.sdk.core.response.model.AdInfo$AdBaseInfo r3 = r3.adBaseInfo
            java.lang.String r5 = r3.productName
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            if (r5 != 0) goto L79
            java.lang.String r3 = r3.productName
            goto L7b
        L79:
            java.lang.String r3 = "详情页面"
        L7b:
            r0.setText(r3)
            com.kwad.sdk.core.page.AdLandPageActivityProxy$3 r0 = new com.kwad.sdk.core.page.AdLandPageActivityProxy$3
            r0.<init>()
            r2.setOnClickListener(r0)
            com.kwad.sdk.core.page.AdLandPageActivityProxy$4 r0 = new com.kwad.sdk.core.page.AdLandPageActivityProxy$4
            r0.<init>()
            r1.setOnClickListener(r0)
            r6.initTipBarView()
            java.lang.String r0 = r6.mDialogUrl
            if (r0 == 0) goto Lc0
            r6.inflateJsBridgeContext()
            com.kwad.sdk.core.download.a.b r0 = new com.kwad.sdk.core.download.a.b
            com.kwad.sdk.core.response.model.AdTemplate r1 = r6.mAdTemplate
            r0.<init>(r1)
            com.kwad.sdk.core.page.widget.webview.KsAdWebView r1 = r6.mAdWebView
            r6.setupJsBridge(r1, r0)
            boolean r1 = com.kwad.sdk.core.config.b.Z()
            if (r1 == 0) goto Lbb
            android.app.Activity r1 = r6.getActivity()
            boolean r1 = com.ksad.download.c.b.b(r1)
            if (r1 == 0) goto Lbb
            android.app.Activity r1 = r6.getActivity()
            r0.a(r1, r4)
        Lbb:
            com.kwad.sdk.core.page.widget.webview.KsAdWebView r0 = r6.mAdWebView
            java.lang.String r1 = r6.mDialogUrl
            goto Lcc
        Lc0:
            com.kwad.sdk.core.page.widget.webview.KsAdWebView r0 = r6.mAdWebView
            com.kwad.sdk.core.response.model.AdTemplate r1 = r6.mAdTemplate
            com.kwad.sdk.core.response.model.AdInfo r1 = com.kwad.sdk.core.response.a.d.j(r1)
            java.lang.String r1 = com.kwad.sdk.core.response.a.a.G(r1)
        Lcc:
            r0.loadUrl(r1)
            com.kwad.sdk.core.page.widget.webview.KsAdWebView r0 = r6.mAdWebView
            com.kwad.sdk.core.page.AdLandPageActivityProxy$5 r1 = new com.kwad.sdk.core.page.AdLandPageActivityProxy$5
            r1.<init>()
            r0.setHttpErrorListener(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.core.page.AdLandPageActivityProxy.initView():void");
    }

    public static boolean isDownloadDialogShowing() {
        return mIsDownloadDialogShowing;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void launch(Context context, AdTemplate adTemplate, String str) {
        if (context == 0 || adTemplate == null) {
            return;
        }
        KsAdSDKImpl.putComponentProxy(AdWebViewActivity.class, AdLandPageActivityProxy.class);
        Intent intent = new Intent(context, (Class<?>) AdWebViewActivity.class);
        intent.addFlags(268435456);
        intent.putExtra("key_template_json", adTemplate.toJson().toString());
        intent.putExtra(KEY_DIALOG_URL, str);
        context.startActivity(intent);
        boolean z = context instanceof ResContext;
        Object delegatedContext = context;
        if (z) {
            delegatedContext = ((ResContext) context).getDelegatedContext();
        }
        if (delegatedContext instanceof Activity) {
            ((Activity) delegatedContext).overridePendingTransition(0, 0);
        }
        mIsDownloadDialogShowing = true;
    }

    private void registerWebCardHandler(g gVar, com.kwad.sdk.core.download.a.b bVar) {
        gVar.a(new com.kwad.sdk.core.webview.jshandler.b(this.mJsBridgeContext, bVar, null));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.a(this.mJsBridgeContext, bVar, (b.c) null));
        gVar.a(new f(this.mJsBridgeContext));
        gVar.a(new t(this.mJsBridgeContext, bVar));
        gVar.a(new k(this.mJsBridgeContext));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.p(new p.b() { // from class: com.kwad.sdk.core.page.AdLandPageActivityProxy.1
            @Override // com.kwad.sdk.core.webview.jshandler.p.b
            public void a(p.a aVar) {
            }
        }));
        this.webCardRegisterLifecycleListenerHandler = new s();
        gVar.a(this.webCardRegisterLifecycleListenerHandler);
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface", "JavascriptInterface"})
    private void setupJsBridge(KsAdWebView ksAdWebView, com.kwad.sdk.core.download.a.b bVar) {
        clearJsInterfaceRegister();
        be.a(this.mAdWebView);
        this.mJsInterface = new g(ksAdWebView);
        registerWebCardHandler(this.mJsInterface, bVar);
        ksAdWebView.addJavascriptInterface(this.mJsInterface, "KwaiAd");
    }

    @Override // com.kwad.sdk.h.a
    protected String getPageName() {
        return "AdLandPageActivityProxy";
    }

    @Override // com.kwad.sdk.api.proxy.IActivityProxy
    public void onBackPressed() {
        if (this.mAdWebView == null || !this.mAdWebView.canGoBack()) {
            super.onBackPressed();
        } else {
            this.mAdWebView.goBack();
            com.kwad.sdk.core.report.a.l(this.mAdTemplate);
        }
    }

    @Override // com.kwad.sdk.h.a, com.kwad.sdk.api.proxy.IActivityProxy
    public void onCreate(Bundle bundle) {
        setContentView(R.layout.ksad_activity_ad_webview);
        String stringExtra = getIntent().getStringExtra("key_template_json");
        this.mDialogUrl = getIntent().getStringExtra(KEY_DIALOG_URL);
        try {
            AdTemplate adTemplate = new AdTemplate();
            adTemplate.parseJson(new JSONObject(stringExtra));
            this.mAdTemplate = adTemplate;
        } catch (Throwable th) {
            com.kwad.sdk.core.d.a.a(th);
        }
        if (this.mAdTemplate != null) {
            initView();
        } else {
            finish();
        }
    }

    @Override // com.kwad.sdk.h.a, com.kwad.sdk.api.proxy.IActivityProxy
    protected void onDestroy() {
        if (this.webCardRegisterLifecycleListenerHandler != null) {
            this.webCardRegisterLifecycleListenerHandler.b();
        }
        if (this.mAdWebView != null) {
            this.mAdWebView.c();
            this.mAdWebView = null;
        }
        super.onDestroy();
        mIsDownloadDialogShowing = false;
    }

    @Override // com.kwad.sdk.api.proxy.IActivityProxy
    public void onPause() {
        overridePendingTransition(0, 0);
    }

    @Override // com.kwad.sdk.h.a, com.kwad.sdk.api.proxy.IActivityProxy
    public void onResume() {
        super.onResume();
    }

    @Override // com.kwad.sdk.api.proxy.IActivityProxy
    public void onStop() {
        super.onStop();
    }
}
