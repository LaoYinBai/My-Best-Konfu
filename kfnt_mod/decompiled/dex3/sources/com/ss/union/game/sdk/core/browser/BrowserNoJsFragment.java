package com.ss.union.game.sdk.core.browser;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.dialog.OperationBuilder;
import com.ss.union.game.sdk.common.util.StatusBarUtils;
import com.ss.union.game.sdk.common.util.UIUtils;
import com.ss.union.game.sdk.common.webview.SSWebSettings;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class BrowserNoJsFragment extends BaseFragment {
    static final String a = "BrowserNoJsFragment";
    private static final String d = "extra_url";
    private static final String e = "extra_title";
    private static final String f = "extra_hide_title_layout";
    boolean b = false;
    boolean c = false;
    private View g;
    private View h;
    private TextView i;
    private View j;
    private WebView k;
    private View l;
    private View m;
    private ProgressBar n;
    private String o;
    private String p;
    private boolean q;

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String getLayoutId() {
        return "lg_browser";
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean isShowStatusBar() {
        return true;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean isStatusBarDarkMode() {
        return true;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void loadData() {
    }

    public static BrowserNoJsFragment a(String str) {
        return a(str, null, false);
    }

    public static BrowserNoJsFragment a(String str, String str2, boolean z) {
        Bundle bundle = new Bundle();
        BrowserNoJsFragment browserNoJsFragment = new BrowserNoJsFragment();
        bundle.putString(d, str);
        bundle.putString(e, str2);
        bundle.putBoolean(f, z);
        browserNoJsFragment.setArguments(bundle);
        return browserNoJsFragment;
    }

    public static void b(String str) {
        b(str, null, false);
    }

    public static void b(String str, String str2, boolean z) {
        new OperationBuilder(a(str, str2, z)).cancelable(true).show();
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment, android.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        try {
            this.k.stopLoading();
            this.k.clearView();
            this.k.removeAllViews();
            this.k.destroy();
            ViewGroup viewGroup = (ViewGroup) this.k.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this.k);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean initArgument(Bundle bundle) {
        if (bundle != null) {
            this.o = bundle.getString(d);
            this.p = bundle.getString(e);
            this.q = bundle.getBoolean(f, false);
            if (!TextUtils.isEmpty(this.o)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initView() {
        this.g = findViewById("lg_browser_container");
        fitStatusBar(this.g);
        this.h = findViewById("lg_browser_title_container");
        this.i = (TextView) findViewById("lg_browser_title");
        this.j = findViewById("lg_browser_back");
        this.k = (WebView) findViewById("lg_browser_web_view");
        this.l = findViewById("lg_browser_loading_fail_container");
        this.m = findViewById("lg_browser_loading_fail_reload");
        this.n = (ProgressBar) findViewById("lg_browser_progress_bar");
        if (getResources().getConfiguration().orientation == 1) {
            screenChange2Portrait();
        } else {
            screenChange2Landscape();
        }
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initData() {
        if (!TextUtils.isEmpty(this.p)) {
            this.i.setText(this.p);
        } else {
            this.i.setText("");
        }
        if (this.q) {
            this.h.setVisibility(8);
        }
        a();
        if (TextUtils.isEmpty(this.o)) {
            return;
        }
        this.k.loadUrl(this.o);
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initListener() {
        this.m.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.browser.BrowserNoJsFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BrowserNoJsFragment.this.b = true;
                BrowserNoJsFragment.this.c = false;
                BrowserNoJsFragment.this.k.reload();
                BrowserNoJsFragment.this.b();
            }
        });
        this.j.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.browser.BrowserNoJsFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                try {
                    if (BrowserNoJsFragment.this.k == null || !BrowserNoJsFragment.this.k.canGoBack()) {
                        BrowserNoJsFragment.this.back();
                    } else {
                        BrowserNoJsFragment.this.k.goBack();
                    }
                } catch (Throwable unused) {
                }
            }
        });
    }

    private void a() {
        SSWebSettings.with(getActivity()).enableHardwareAcceleration(true).apply(this.k);
        WebSettings settings = this.k.getSettings();
        try {
            settings.setJavaScriptEnabled(true);
        } catch (Throwable unused) {
        }
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        }
        String userAgentString = this.k.getSettings().getUserAgentString();
        this.k.getSettings().setUserAgentString(userAgentString + ";/android_ohayoo_sdk");
        this.k.setWebViewClient(new b());
        this.k.setWebChromeClient(new a());
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    public boolean onBackPressed() {
        if (this.k == null || !this.k.canGoBack()) {
            return false;
        }
        this.k.goBack();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.k.setVisibility(0);
        this.n.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.k.setVisibility(8);
        this.l.setVisibility(0);
        this.b = false;
        this.c = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        this.n.setProgress(i);
        if (i >= 100) {
            this.n.setVisibility(8);
        }
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void screenChange2Landscape() {
        super.screenChange2Landscape();
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.j.getLayoutParams();
        layoutParams.leftMargin = StatusBarUtils.getStatusBarHeight();
        this.j.setLayoutParams(layoutParams);
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void screenChange2Portrait() {
        super.screenChange2Portrait();
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.j.getLayoutParams();
        layoutParams.leftMargin = UIUtils.dip2Px(12.0f);
        this.j.setLayoutParams(layoutParams);
    }

    class a extends WebChromeClient {
        a() {
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            BrowserNoJsFragment.this.a(i);
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            if (BrowserNoJsFragment.this.i == null || str == null) {
                return;
            }
            BrowserNoJsFragment.this.i.setText(str);
        }
    }

    class b extends WebViewClient {
        @Override // android.webkit.WebViewClient
        public void onLoadResource(WebView webView, String str) {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return false;
        }

        b() {
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            if (Build.VERSION.SDK_INT >= 23) {
                return;
            }
            BrowserNoJsFragment.this.c();
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(23)
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            super.onReceivedError(webView, webResourceRequest, webResourceError);
            if (webResourceRequest.isForMainFrame()) {
                BrowserNoJsFragment.this.c();
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.proceed();
        }

        @Override // android.webkit.WebViewClient
        public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
            super.doUpdateVisitedHistory(webView, str, z);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (BrowserNoJsFragment.this.c) {
                return;
            }
            BrowserNoJsFragment.this.l.setVisibility(8);
            BrowserNoJsFragment.this.k.setVisibility(0);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            if (BrowserNoJsFragment.this.l.getVisibility() != 0 || BrowserNoJsFragment.this.b) {
                return;
            }
            BrowserNoJsFragment.this.l.setVisibility(8);
        }
    }
}
