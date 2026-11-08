package com.ss.union.game.sdk.customerSystem.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.dialog.OperationBuilder;
import com.ss.union.game.sdk.common.webview.SSWebSettings;
import com.ss.union.game.sdk.core.base.account.LGAccountDataUtil;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import com.ss.union.game.sdk.customerSystem.a.a;
import com.ss.union.game.sdk.feedback.picture.select.d;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CustomerSystemFragment extends BaseFragment {
    public static final int a = 1001;
    private WebView b;
    private View c;
    private View d;
    private View e;
    private ImageView f;
    private ImageView g;
    private ValueCallback<Uri> h;
    private ValueCallback<Uri[]> i;

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String getLayoutId() {
        return "lg_fragment_customer_system";
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean initArgument(Bundle bundle) {
        return true;
    }

    public static void a() {
        new OperationBuilder(b()).show();
    }

    public static CustomerSystemFragment b() {
        Bundle bundle = new Bundle();
        CustomerSystemFragment customerSystemFragment = new CustomerSystemFragment();
        customerSystemFragment.setArguments(bundle);
        return customerSystemFragment;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initView() {
        this.b = (WebView) findViewById("customer_system_browser");
        this.c = findViewById("rv_browser_left");
        this.d = findViewById("rv_browser_right");
        this.e = findViewById("rv_browser_exit");
        this.f = (ImageView) findViewById("lg_iv_browser_go_back");
        this.g = (ImageView) findViewById("lg_iv_browser_go_forward");
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initData() {
        a(this.b);
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initListener() {
        this.c.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.customerSystem.fragment.CustomerSystemFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CustomerSystemFragment.this.b == null || !CustomerSystemFragment.this.b.canGoBack()) {
                    return;
                }
                CustomerSystemFragment.this.b.goBack();
            }
        });
        this.d.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.customerSystem.fragment.CustomerSystemFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CustomerSystemFragment.this.b == null || !CustomerSystemFragment.this.b.canGoForward()) {
                    return;
                }
                CustomerSystemFragment.this.b.goForward();
            }
        });
        this.e.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.customerSystem.fragment.CustomerSystemFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CustomerSystemFragment.this.back();
            }
        });
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void loadData() {
        this.b.loadUrl(String.format(d(), AppIdManager.apAppID(), LGAccountDataUtil.getCurrentUserOpenId()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.b == null) {
            return;
        }
        this.g.setEnabled(this.b.canGoForward());
        this.f.setEnabled(this.b.canGoBack());
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void a(WebView webView) {
        SSWebSettings.with(getActivity()).enableHardwareAcceleration(true).apply(webView);
        WebSettings settings = webView.getSettings();
        try {
            settings.setJavaScriptEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        }
        String userAgentString = webView.getSettings().getUserAgentString();
        webView.getSettings().setUserAgentString(userAgentString + ";/android_ohayoo_sdk");
        webView.setWebViewClient(new b());
        webView.setWebChromeClient(new a());
    }

    private String d() {
        return a.C0095a.b();
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean onBackPressed() {
        if (this.b != null && this.b.canGoBack()) {
            this.b.goBack();
            return true;
        }
        return super.onBackPressed();
    }

    @Override // android.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1001) {
            if (this.h != null) {
                b(i2, intent);
            } else if (this.i != null) {
                a(i2, intent);
            }
        }
    }

    private class a extends WebChromeClient {
        private a() {
        }

        public void openFileChooser(ValueCallback<Uri> valueCallback, String str, String str2) {
            CustomerSystemFragment.this.h = valueCallback;
            CustomerSystemFragment.this.e();
        }

        @Override // android.webkit.WebChromeClient
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            CustomerSystemFragment.this.i = valueCallback;
            CustomerSystemFragment.this.e();
            return true;
        }
    }

    private class b extends WebViewClient {
        private b() {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            CustomerSystemFragment.this.c();
            return super.shouldOverrideUrlLoading(webView, webResourceRequest);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            CustomerSystemFragment.this.c();
            return super.shouldOverrideUrlLoading(webView, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        try {
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType(d.e);
            startActivityForResult(Intent.createChooser(intent, "选择图片"), 1001);
        } catch (Throwable unused) {
        }
    }

    private void a(int i, Intent intent) {
        if (-1 != i) {
            this.i.onReceiveValue(null);
        } else if (intent != null) {
            Uri data = intent.getData();
            if (data != null) {
                this.i.onReceiveValue(new Uri[]{data});
            } else {
                this.i.onReceiveValue(null);
            }
        } else {
            this.i.onReceiveValue(new Uri[]{null});
        }
        this.i = null;
    }

    private void b(int i, Intent intent) {
        Uri data;
        if (-1 == i && intent != null && (data = intent.getData()) != null) {
            this.h.onReceiveValue(data);
        } else {
            this.h.onReceiveValue(null);
        }
        this.h = null;
    }
}
