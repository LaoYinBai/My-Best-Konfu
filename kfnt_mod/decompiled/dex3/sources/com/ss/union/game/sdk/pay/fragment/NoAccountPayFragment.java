package com.ss.union.game.sdk.pay.fragment;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.alipay.sdk.app.H5PayCallback;
import com.alipay.sdk.app.PayTask;
import com.alipay.sdk.util.H5PayResultModel;
import com.qq.e.comm.constants.ErrorCode;
import com.sigmob.sdk.common.Constants;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.dialog.OperationBuilder;
import com.ss.union.game.sdk.common.util.AppUtils;
import com.ss.union.game.sdk.common.util.MainThreadExecutor;
import com.ss.union.game.sdk.common.util.ToastUtils;
import com.ss.union.game.sdk.common.util.UIUtils;
import com.ss.union.game.sdk.common.webview.SSWebSettings;
import com.ss.union.game.sdk.pay.callback.LGPayCallback;
import com.ss.union.game.sdk.pay.d.b;
import com.umeng.message.util.HttpRequest;
import java.util.HashMap;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NoAccountPayFragment extends BaseFragment<LGPayCallback, com.ss.union.game.sdk.pay.d.a> implements b.InterfaceC0099b {
    private static final String a = "LGPayFragment";
    private static final int b = 1;
    private static final int c = 2;
    private static final String d = "cash_desk_h5_url";
    private static final String e = "pay_title";
    private static final String f = "order_no";
    private static final String g = "refer";
    private static final String h = "cp_user_id";
    private View i;
    private View j;
    private TextView k;
    private WebView l;
    private View m;
    private Button n;
    private ProgressBar o;
    private String p;
    private String q;
    private String r;
    private String s;
    private String t;
    private int u = -1;

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String getLayoutId() {
        return "lg_fragment_pay";
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
        return true;
    }

    public static void a(String str, String str2, String str3, String str4, LGPayCallback lGPayCallback) {
        new OperationBuilder(b(str, str2, str3, str4, lGPayCallback)).show();
    }

    public static NoAccountPayFragment b(String str, String str2, String str3, String str4, LGPayCallback lGPayCallback) {
        Bundle bundle = new Bundle();
        bundle.putString(d, str);
        bundle.putString(f, str2);
        bundle.putString("refer", "http://www." + str3);
        bundle.putString(e, "收银台");
        bundle.putString(h, str4);
        NoAccountPayFragment noAccountPayFragment = new NoAccountPayFragment();
        noAccountPayFragment.setArguments(bundle);
        noAccountPayFragment.setCallback(lGPayCallback);
        return noAccountPayFragment;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean initArgument(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        this.p = bundle.getString(d, "");
        this.q = bundle.getString(e, "");
        this.r = bundle.getString(f, "");
        this.s = bundle.getString("refer", "");
        this.t = bundle.getString(h, "");
        return !TextUtils.isEmpty(this.p);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public com.ss.union.game.sdk.pay.d.a initPresenter() {
        return new com.ss.union.game.sdk.pay.d.a();
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initView() throws JSONException {
        this.i = findViewById("id_lg_pay_root");
        this.j = findViewById("lg_pay_head_back");
        this.k = (TextView) findViewById("lg_pay_head_title");
        this.l = (WebView) findViewById("lg_pay_web_view");
        this.m = findViewById("lg_pay_loading_fail_container");
        this.n = (Button) findViewById("lg_pay_loading_fail_reload");
        this.o = (ProgressBar) findViewById("lg_pay_progress_bar");
        fitStatusBar(this.i);
        if (!TextUtils.isEmpty(this.q)) {
            this.k.setText(this.q);
        }
        c();
        com.ss.union.game.sdk.pay.b.a.a();
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initListener() {
        this.j.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.pay.fragment.NoAccountPayFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) throws JSONException {
                NoAccountPayFragment.this.b();
            }
        });
        this.n.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.pay.fragment.NoAccountPayFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NoAccountPayFragment.this.loadData();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() throws JSONException {
        com.ss.union.game.sdk.pay.b.a.d();
        back();
        c(104, "用户点击返回按钮取消支付");
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void loadData() {
        if (this.l != null) {
            this.l.loadUrl(this.p);
        }
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment, android.app.Fragment
    public void onResume() {
        super.onResume();
        if (2 == this.u) {
            ((com.ss.union.game.sdk.pay.d.a) this.mPresenter).a(this.r, this.t);
        }
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment, android.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        try {
            this.l.stopLoading();
            this.l.clearView();
            this.l.removeAllViews();
            this.l.destroy();
            ViewGroup viewGroup = (ViewGroup) this.l.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this.l);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.ss.union.game.sdk.pay.d.b.InterfaceC0099b
    public void a(int i, String str) throws JSONException {
        if (2 == this.u) {
            b(i, str);
        }
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean onBackPressed() throws JSONException {
        b();
        return true;
    }

    private void c() {
        SSWebSettings.with(getActivity()).enableHardwareAcceleration(true).apply(this.l);
        WebSettings settings = this.l.getSettings();
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        }
        this.l.setWebViewClient(new b());
        this.l.setWebChromeClient(new a());
    }

    class a extends WebChromeClient {
        a() {
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            if (NoAccountPayFragment.this.o != null) {
                NoAccountPayFragment.this.o.setProgress(i);
                if (i >= 100) {
                    NoAccountPayFragment.this.o.setVisibility(8);
                }
            }
        }
    }

    class b extends WebViewClient {
        boolean a = false;
        boolean b = true;

        b() {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Log.e(NoAccountPayFragment.a, "shouldOverrideUrlLoading: url --" + str);
            if (str.startsWith("weixin://")) {
                NoAccountPayFragment.this.u = 2;
                if (AppUtils.isInstalledApp("com.tencent.mm")) {
                    try {
                        NoAccountPayFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                        return true;
                    } catch (ActivityNotFoundException unused) {
                        ToastUtils.getInstance().toast("尚未安装微信,请使用其他方式支付");
                        return true;
                    } catch (Exception unused2) {
                        return true;
                    }
                }
                ToastUtils.getInstance().toast("尚未安装微信,请使用其他方式支付");
                return true;
            }
            boolean zPayInterceptorWithUrl = new PayTask(NoAccountPayFragment.this.getActivity()).payInterceptorWithUrl(str, true, new H5PayCallback() { // from class: com.ss.union.game.sdk.pay.fragment.NoAccountPayFragment.b.1
                public void onPayResult(final H5PayResultModel h5PayResultModel) {
                    MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.pay.fragment.NoAccountPayFragment.b.1.1
                        @Override // java.lang.Runnable
                        public void run() throws JSONException {
                            NoAccountPayFragment.this.a(h5PayResultModel);
                        }
                    });
                }
            });
            if (zPayInterceptorWithUrl) {
                NoAccountPayFragment.this.u = 1;
            }
            if (!str.startsWith(Constants.HTTP) && !str.startsWith(Constants.HTTPS)) {
                return true;
            }
            if (!str.contains("wx.tenpay.com")) {
                if (!zPayInterceptorWithUrl) {
                    webView.loadUrl(str);
                }
                return true;
            }
            if ("4.4.3".equals(Build.VERSION.RELEASE) || "4.4.4".equals(Build.VERSION.RELEASE)) {
                if (this.b) {
                    webView.loadDataWithBaseURL(NoAccountPayFragment.this.s, "<script>window.location.href=\"" + str + "\";</script>", "text/html", "utf-8", null);
                    this.b = false;
                }
                return false;
            }
            HashMap map = new HashMap(1);
            map.put(HttpRequest.HEADER_REFERER, NoAccountPayFragment.this.s);
            webView.loadUrl(str, map);
            return true;
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            if (Build.VERSION.SDK_INT >= 23) {
                return;
            }
            a();
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(23)
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            super.onReceivedError(webView, webResourceRequest, webResourceError);
            if (webResourceRequest.isForMainFrame()) {
                a();
            }
        }

        private void a() {
            this.a = true;
            if (NoAccountPayFragment.this.l != null) {
                NoAccountPayFragment.this.l.setVisibility(8);
            }
            if (NoAccountPayFragment.this.m != null) {
                NoAccountPayFragment.this.m.setVisibility(0);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.proceed();
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (NoAccountPayFragment.this.getActivity() != null && NoAccountPayFragment.this.getActivity().getRequestedOrientation() != 1) {
                ViewGroup.LayoutParams layoutParams = NoAccountPayFragment.this.l.getLayoutParams();
                layoutParams.width = Math.min(UIUtils.getScreenWidth(), UIUtils.getScreenHeight());
                NoAccountPayFragment.this.l.setLayoutParams(layoutParams);
            }
            if (this.a) {
                return;
            }
            NoAccountPayFragment.this.m.setVisibility(8);
            NoAccountPayFragment.this.l.setVisibility(0);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            this.a = false;
            if (NoAccountPayFragment.this.o != null) {
                NoAccountPayFragment.this.o.setVisibility(0);
            }
            if (NoAccountPayFragment.this.m != null) {
                NoAccountPayFragment.this.m.setVisibility(8);
            }
            if (NoAccountPayFragment.this.l != null) {
                NoAccountPayFragment.this.l.setVisibility(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(H5PayResultModel h5PayResultModel) throws JSONException {
        if (h5PayResultModel != null) {
            String resultCode = h5PayResultModel.getResultCode();
            if ("6001".equals(resultCode)) {
                com.ss.union.game.sdk.pay.b.a.a((String) null, 6001);
                c(104, "6001:用户中途取消");
            } else if ("9000".equals(resultCode)) {
                com.ss.union.game.sdk.pay.b.a.b("success", ErrorCode.PrivateError.LOAD_TIME_OUT);
                c(0, "9000:支付成功");
            } else {
                try {
                    com.ss.union.game.sdk.pay.b.a.b("fail", Integer.parseInt(resultCode));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                c(102, "" + resultCode);
            }
            back();
        }
    }

    public void b(int i, String str) throws JSONException {
        if (i == 0) {
            com.ss.union.game.sdk.pay.b.a.b("success", 0);
            c(0, "PAID:支付成功");
        } else if (i == 104) {
            com.ss.union.game.sdk.pay.b.a.a(str, i);
            c(104, i + com.xiaomi.mipush.sdk.Constants.COLON_SEPARATOR + str);
        } else {
            com.ss.union.game.sdk.pay.b.a.b("fail", i);
            c(102, i + com.xiaomi.mipush.sdk.Constants.COLON_SEPARATOR + str);
        }
        back();
    }

    private void c(int i, String str) {
        if (getCallback() != null) {
            getCallback().onPayResult(i, this.u + "---" + str);
        }
    }
}
