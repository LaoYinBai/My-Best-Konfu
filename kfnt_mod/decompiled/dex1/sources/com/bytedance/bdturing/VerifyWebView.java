package com.bytedance.bdturing;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.ConsoleMessage;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class VerifyWebView extends WebView {

    /* renamed from: a, reason: collision with root package name */
    private static final String f3734a = "VerifyWebView";

    /* renamed from: b, reason: collision with root package name */
    private static final int f3735b = 3;

    /* renamed from: c, reason: collision with root package name */
    private f f3736c;
    private boolean d;
    private boolean e;
    private h f;
    private int g;
    private WebChromeClient h;
    private WebViewClient i;

    public VerifyWebView(Context context) {
        super(context, null);
        this.d = false;
        this.e = false;
        this.f = new h();
        this.g = 0;
        this.h = new WebChromeClient() { // from class: com.bytedance.bdturing.VerifyWebView.1
            @Override // android.webkit.WebChromeClient
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                return super.onConsoleMessage(consoleMessage);
            }
        };
        this.i = new WebViewClient() { // from class: com.bytedance.bdturing.VerifyWebView.2
            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                g.a(VerifyWebView.f3734a, "onPageStarted ");
                super.onPageStarted(webView, str, bitmap);
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView, String str) {
                g.a(VerifyWebView.f3734a, "onPageFinished ");
                if (!VerifyWebView.this.d && !VerifyWebView.this.e) {
                    VerifyWebView.this.e = true;
                    VerifyWebView.this.f3736c.a();
                }
                super.onPageFinished(webView, str);
            }

            @Override // android.webkit.WebViewClient
            public void onLoadResource(WebView webView, String str) {
                super.onLoadResource(webView, str);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView webView, int i, String str, String str2) {
                VerifyWebView.this.d = true;
                g.a(VerifyWebView.f3734a, i + " onReceivedError " + str);
                VerifyWebView.this.f3736c.a(i, str);
                super.onReceivedError(webView, i, str, str2);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                if (g.c()) {
                    Toast.makeText(VerifyWebView.this.getContext(), "onReceivedSslError : " + sslError, 1).show();
                }
                super.onReceivedSslError(webView, sslErrorHandler, sslError);
                VerifyWebView.this.a();
            }

            @Override // android.webkit.WebViewClient
            @RequiresApi(api = 23)
            public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
                if (g.c()) {
                    Toast.makeText(VerifyWebView.this.getContext(), "onReceivedHttpError : " + webResourceResponse.getStatusCode(), 1).show();
                }
                super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
                try {
                    if (webResourceRequest.getUrl().toString().toLowerCase().contains("/favicon.ico")) {
                        return;
                    }
                } catch (Exception e) {
                    g.a(e);
                }
                VerifyWebView.this.a();
            }

            @Override // android.webkit.WebViewClient
            public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
                if (str.toLowerCase().contains("/favicon.ico")) {
                    try {
                        return new WebResourceResponse("image/png", null, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return super.shouldInterceptRequest(webView, str);
            }
        };
    }

    public VerifyWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = false;
        this.e = false;
        this.f = new h();
        this.g = 0;
        this.h = new WebChromeClient() { // from class: com.bytedance.bdturing.VerifyWebView.1
            @Override // android.webkit.WebChromeClient
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                return super.onConsoleMessage(consoleMessage);
            }
        };
        this.i = new WebViewClient() { // from class: com.bytedance.bdturing.VerifyWebView.2
            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                g.a(VerifyWebView.f3734a, "onPageStarted ");
                super.onPageStarted(webView, str, bitmap);
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView, String str) {
                g.a(VerifyWebView.f3734a, "onPageFinished ");
                if (!VerifyWebView.this.d && !VerifyWebView.this.e) {
                    VerifyWebView.this.e = true;
                    VerifyWebView.this.f3736c.a();
                }
                super.onPageFinished(webView, str);
            }

            @Override // android.webkit.WebViewClient
            public void onLoadResource(WebView webView, String str) {
                super.onLoadResource(webView, str);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView webView, int i, String str, String str2) {
                VerifyWebView.this.d = true;
                g.a(VerifyWebView.f3734a, i + " onReceivedError " + str);
                VerifyWebView.this.f3736c.a(i, str);
                super.onReceivedError(webView, i, str, str2);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                if (g.c()) {
                    Toast.makeText(VerifyWebView.this.getContext(), "onReceivedSslError : " + sslError, 1).show();
                }
                super.onReceivedSslError(webView, sslErrorHandler, sslError);
                VerifyWebView.this.a();
            }

            @Override // android.webkit.WebViewClient
            @RequiresApi(api = 23)
            public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
                if (g.c()) {
                    Toast.makeText(VerifyWebView.this.getContext(), "onReceivedHttpError : " + webResourceResponse.getStatusCode(), 1).show();
                }
                super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
                try {
                    if (webResourceRequest.getUrl().toString().toLowerCase().contains("/favicon.ico")) {
                        return;
                    }
                } catch (Exception e) {
                    g.a(e);
                }
                VerifyWebView.this.a();
            }

            @Override // android.webkit.WebViewClient
            public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
                if (str.toLowerCase().contains("/favicon.ico")) {
                    try {
                        return new WebResourceResponse("image/png", null, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return super.shouldInterceptRequest(webView, str);
            }
        };
    }

    public void setCallback(f fVar) {
        this.f3736c = fVar;
    }

    public void a(boolean z) {
        WebSettings settings = getSettings();
        settings.setLoadWithOverviewMode(true);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheEnabled(false);
        settings.setUseWideViewPort(true);
        settings.setSupportZoom(false);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setLoadsImagesAutomatically(true);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setCacheMode(2);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        if (z && Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        }
        setOverScrollMode(2);
        setScrollContainer(false);
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        if (g.c()) {
            setWebChromeClient(this.h);
        }
        setWebViewClient(this.i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (this.g < 3) {
            this.g++;
            super.reload();
        } else {
            this.d = true;
            this.f3736c.a(-1, "ssl or http error, reload but fail again");
        }
    }

    @Override // android.webkit.WebView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.f.a(true, motionEvent);
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.webkit.WebView, android.view.View
    protected void onConfigurationChanged(Configuration configuration) throws JSONException {
        g.d(f3734a, "webview onConfigurationChanged " + configuration.orientation);
        switch (configuration.orientation) {
            case 1:
                e.e(2);
                break;
            case 2:
                e.e(1);
                break;
        }
        super.onConfigurationChanged(configuration);
    }
}
