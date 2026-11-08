package com.alipay.sdk.widget;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.JsPromptResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.alipay.sdk.util.h;
import com.alipay.sdk.util.k;
import java.lang.reflect.Method;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e extends LinearLayout {
    private static Handler g = new Handler(Looper.getMainLooper());

    /* renamed from: a, reason: collision with root package name */
    private ImageView f2694a;

    /* renamed from: b, reason: collision with root package name */
    private TextView f2695b;

    /* renamed from: c, reason: collision with root package name */
    private ImageView f2696c;
    private ProgressBar d;
    private WebView e;
    private final a f;
    private b h;
    private c i;
    private d j;
    private final com.alipay.sdk.sys.a k;
    private View.OnClickListener l;
    private final float m;

    public interface b {
        void a(e eVar, String str);

        boolean a(e eVar, String str, String str2, String str3, JsPromptResult jsPromptResult);
    }

    public interface c {
        boolean a(e eVar, int i, String str, String str2);

        boolean a(e eVar, SslErrorHandler sslErrorHandler, SslError sslError);

        boolean b(e eVar, String str);

        boolean c(e eVar, String str);

        boolean d(e eVar, String str);
    }

    public interface d {
        void a(e eVar);

        void b(e eVar);
    }

    static final class a {

        /* renamed from: a, reason: collision with root package name */
        private boolean f2704a;

        /* renamed from: b, reason: collision with root package name */
        private boolean f2705b;

        a(boolean z, boolean z2) {
            this.f2704a = z;
            this.f2705b = z2;
        }
    }

    public e(Context context, com.alipay.sdk.sys.a aVar, a aVar2) {
        this(context, null, aVar, aVar2);
    }

    public e(Context context, AttributeSet attributeSet, com.alipay.sdk.sys.a aVar, a aVar2) {
        super(context, attributeSet);
        this.l = new View.OnClickListener() { // from class: com.alipay.sdk.widget.e.1
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                d dVar = e.this.j;
                if (dVar != null) {
                    view.setEnabled(false);
                    e.g.postDelayed(new Runnable() { // from class: com.alipay.sdk.widget.e.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            view.setEnabled(true);
                        }
                    }, 256L);
                    if (view != e.this.f2694a) {
                        if (view == e.this.f2696c) {
                            dVar.b(e.this);
                            return;
                        }
                        return;
                    }
                    dVar.a(e.this);
                }
            }
        };
        this.f = aVar2 == null ? new a(false, false) : aVar2;
        this.k = aVar;
        this.m = context.getResources().getDisplayMetrics().density;
        setOrientation(1);
        a(context);
        b(context);
        c(context);
    }

    private void a(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setBackgroundColor(-218103809);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        linearLayout.setVisibility(this.f.f2704a ? 0 : 8);
        this.f2694a = new ImageView(context);
        this.f2694a.setOnClickListener(this.l);
        this.f2694a.setScaleType(ImageView.ScaleType.CENTER);
        this.f2694a.setImageDrawable(h.a(h.f2660a, context));
        this.f2694a.setPadding(a(12), 0, a(12), 0);
        linearLayout.addView(this.f2694a, new LinearLayout.LayoutParams(-2, -2));
        View view = new View(context);
        view.setBackgroundColor(-2500135);
        linearLayout.addView(view, new LinearLayout.LayoutParams(a(1), a(25)));
        this.f2695b = new TextView(context);
        this.f2695b.setTextColor(-15658735);
        this.f2695b.setTextSize(17.0f);
        this.f2695b.setMaxLines(1);
        this.f2695b.setEllipsize(TextUtils.TruncateAt.END);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(a(17), 0, 0, 0);
        layoutParams.weight = 1.0f;
        linearLayout.addView(this.f2695b, layoutParams);
        this.f2696c = new ImageView(context);
        this.f2696c.setOnClickListener(this.l);
        this.f2696c.setScaleType(ImageView.ScaleType.CENTER);
        this.f2696c.setImageDrawable(h.a(h.f2661b, context));
        this.f2696c.setPadding(a(12), 0, a(12), 0);
        linearLayout.addView(this.f2696c, new LinearLayout.LayoutParams(-2, -2));
        addView(linearLayout, new LinearLayout.LayoutParams(-1, a(48)));
    }

    private void b(Context context) {
        this.d = new ProgressBar(context, null, R.style.Widget.ProgressBar.Horizontal);
        this.d.setProgressDrawable(context.getResources().getDrawable(R.drawable.progress_horizontal));
        this.d.setMax(100);
        this.d.setBackgroundColor(-218103809);
        addView(this.d, new LinearLayout.LayoutParams(-1, a(2)));
    }

    private void c(final Context context) {
        this.e = new WebView(context);
        this.e.setVerticalScrollbarOverlay(true);
        a(this.e, context);
        WebSettings settings = this.e.getSettings();
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setSupportMultipleWindows(true);
        settings.setUseWideViewPort(true);
        settings.setAppCacheMaxSize(5242880L);
        settings.setAppCachePath(context.getCacheDir().getAbsolutePath());
        settings.setAllowFileAccess(false);
        settings.setTextSize(WebSettings.TextSize.NORMAL);
        if (Build.VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(false);
            settings.setAllowUniversalAccessFromFileURLs(false);
        }
        settings.setAppCacheEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setCacheMode(1);
        settings.setDomStorageEnabled(true);
        settings.setAllowContentAccess(false);
        this.e.setVerticalScrollbarOverlay(true);
        this.e.setDownloadListener(new DownloadListener() { // from class: com.alipay.sdk.widget.e.2
            @Override // android.webkit.DownloadListener
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                try {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                    intent.setFlags(268435456);
                    context.startActivity(intent);
                } catch (Throwable unused) {
                }
            }
        });
        try {
            try {
                this.e.removeJavascriptInterface("searchBoxJavaBridge_");
                this.e.removeJavascriptInterface("accessibility");
                this.e.removeJavascriptInterface("accessibilityTraversal");
            } catch (Throwable unused) {
            }
        } catch (Exception unused2) {
            Method method = this.e.getClass().getMethod("removeJavascriptInterface", new Class[0]);
            if (method != null) {
                method.invoke(this.e, "searchBoxJavaBridge_");
                method.invoke(this.e, "accessibility");
                method.invoke(this.e, "accessibilityTraversal");
            }
        }
        com.alipay.sdk.widget.c.a(this.e);
        addView(this.e, new LinearLayout.LayoutParams(-1, -1));
    }

    protected void a(WebView webView, Context context) {
        String userAgentString = webView.getSettings().getUserAgentString();
        webView.getSettings().setUserAgentString(userAgentString + k.c(context));
    }

    public void setChromeProxy(b bVar) {
        this.h = bVar;
        if (bVar == null) {
            this.e.setWebChromeClient(null);
        } else {
            this.e.setWebChromeClient(new WebChromeClient() { // from class: com.alipay.sdk.widget.e.3
                @Override // android.webkit.WebChromeClient
                public void onProgressChanged(WebView webView, int i) {
                    if (!e.this.f.f2705b) {
                        e.this.d.setVisibility(8);
                    } else {
                        if (i > 90) {
                            e.this.d.setVisibility(4);
                            return;
                        }
                        if (e.this.d.getVisibility() == 4) {
                            e.this.d.setVisibility(0);
                        }
                        e.this.d.setProgress(i);
                    }
                }

                @Override // android.webkit.WebChromeClient
                public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
                    return e.this.h.a(e.this, str, str2, str3, jsPromptResult);
                }

                @Override // android.webkit.WebChromeClient
                public void onReceivedTitle(WebView webView, String str) {
                    e.this.h.a(e.this, str);
                }
            });
        }
    }

    public void setWebClientProxy(c cVar) {
        this.i = cVar;
        if (cVar == null) {
            this.e.setWebViewClient(null);
        } else {
            this.e.setWebViewClient(new WebViewClient() { // from class: com.alipay.sdk.widget.e.4
                @Override // android.webkit.WebViewClient
                public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                    if (e.this.i.b(e.this, str)) {
                        return true;
                    }
                    return super.shouldOverrideUrlLoading(webView, str);
                }

                @Override // android.webkit.WebViewClient
                public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                    if (e.this.i.c(e.this, str)) {
                        return;
                    }
                    super.onPageFinished(webView, str);
                }

                @Override // android.webkit.WebViewClient
                public void onPageFinished(WebView webView, String str) {
                    if (e.this.i.d(e.this, str)) {
                        return;
                    }
                    super.onPageFinished(webView, str);
                }

                @Override // android.webkit.WebViewClient
                public void onReceivedError(WebView webView, int i, String str, String str2) {
                    if (e.this.i.a(e.this, i, str, str2)) {
                        return;
                    }
                    super.onReceivedError(webView, i, str, str2);
                }

                @Override // android.webkit.WebViewClient
                public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                    if (e.this.i.a(e.this, sslErrorHandler, sslError)) {
                        return;
                    }
                    super.onReceivedSslError(webView, sslErrorHandler, sslError);
                }
            });
        }
    }

    public void setWebEventProxy(d dVar) {
        this.j = dVar;
    }

    public String getUrl() {
        return this.e.getUrl();
    }

    public void a(String str) {
        this.e.loadUrl(str);
        com.alipay.sdk.widget.c.a(this.e);
    }

    public void a(String str, byte[] bArr) {
        this.e.postUrl(str, bArr);
    }

    public ImageView getBackButton() {
        return this.f2694a;
    }

    public TextView getTitle() {
        return this.f2695b;
    }

    public ImageView getRefreshButton() {
        return this.f2696c;
    }

    public ProgressBar getProgressbar() {
        return this.d;
    }

    public WebView getWebView() {
        return this.e;
    }

    public void a() {
        removeAllViews();
        this.e.removeAllViews();
        this.e.setWebViewClient(null);
        this.e.setWebChromeClient(null);
        this.e.destroy();
    }

    private int a(int i) {
        return (int) (i * this.m);
    }
}
