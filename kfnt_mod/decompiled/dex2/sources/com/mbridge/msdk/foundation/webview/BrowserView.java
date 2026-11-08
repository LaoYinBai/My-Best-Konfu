package com.mbridge.msdk.foundation.webview;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.v;
import com.mbridge.msdk.foundation.tools.w;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class BrowserView extends LinearLayout {
    private String a;
    private ProgressBar b;
    private WebView c;
    private ToolBar d;
    private a e;
    private CampaignEx f;

    public interface a {
        void a();

        void a(WebView webView, String str, Bitmap bitmap);

        boolean a(WebView webView, String str);
    }

    public BrowserView(Context context, CampaignEx campaignEx) {
        super(context);
        this.f = campaignEx;
        init();
    }

    public BrowserView(Context context) {
        super(context);
        init();
    }

    public BrowserView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public void setListener(a aVar) {
        this.e = aVar;
    }

    public void loadUrl(String str) {
        if (this.c != null) {
            this.c.loadUrl(str);
        }
    }

    public void setWebView(WebView webView) {
        this.c = webView;
    }

    public void init() {
        WebChromeClient webChromeClient;
        setOrientation(1);
        setGravity(17);
        this.b = new ProgressBar(getContext());
        this.b.setLayoutParams(new LinearLayout.LayoutParams(-1, 4));
        try {
            if (this.c == null) {
                WebView webView = new WebView(getContext());
                webView.getSettings().setJavaScriptEnabled(true);
                webView.getSettings().setCacheMode(-1);
                webView.setDownloadListener(new MBDownloadListener(this.f));
                webView.setWebViewClient(new WebViewClient() { // from class: com.mbridge.msdk.foundation.webview.BrowserView.2
                    @Override // android.webkit.WebViewClient
                    public final void onPageStarted(WebView webView2, String str, Bitmap bitmap) {
                        s.b("BrowserView", "开始! = " + str);
                        BrowserView.this.a = str;
                        if (BrowserView.this.e != null) {
                            BrowserView.this.e.a(webView2, str, bitmap);
                        }
                        BrowserView.this.b.setVisible(true);
                        BrowserView.this.b.setProgressState(5);
                    }

                    @Override // android.webkit.WebViewClient
                    public final boolean shouldOverrideUrlLoading(WebView webView2, String str) {
                        s.b("BrowserView", "js大跳! = " + str);
                        BrowserView.this.d.getItem("backward").setEnabled(true);
                        BrowserView.this.d.getItem("forward").setEnabled(false);
                        if (BrowserView.this.e != null) {
                            BrowserView.this.e.a(webView2, str);
                        }
                        return false;
                    }
                });
                if (o.z() <= 10) {
                    webChromeClient = new WebChromeClient() { // from class: com.mbridge.msdk.foundation.webview.BrowserView.3
                        @Override // android.webkit.WebChromeClient
                        public final boolean onJsAlert(WebView webView2, String str, String str2, JsResult jsResult) {
                            return true;
                        }

                        @Override // android.webkit.WebChromeClient
                        public final boolean onJsConfirm(WebView webView2, String str, String str2, JsResult jsResult) {
                            return true;
                        }

                        @Override // android.webkit.WebChromeClient
                        public final boolean onJsPrompt(WebView webView2, String str, String str2, String str3, JsPromptResult jsPromptResult) {
                            return true;
                        }

                        @Override // android.webkit.WebChromeClient
                        public final void onProgressChanged(WebView webView2, int i) {
                            if (i == 100) {
                                BrowserView.this.b.setProgressState(7);
                                new Handler().postDelayed(new Runnable() { // from class: com.mbridge.msdk.foundation.webview.BrowserView.3.1
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        BrowserView.this.b.setVisible(false);
                                    }
                                }, 200L);
                            }
                        }
                    };
                } else {
                    webChromeClient = new WebChromeClient() { // from class: com.mbridge.msdk.foundation.webview.BrowserView.4
                        @Override // android.webkit.WebChromeClient
                        public final void onProgressChanged(WebView webView2, int i) {
                            if (i == 100) {
                                BrowserView.this.b.setProgressState(7);
                                new Handler().postDelayed(new Runnable() { // from class: com.mbridge.msdk.foundation.webview.BrowserView.4.1
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        BrowserView.this.b.setVisible(false);
                                    }
                                }, 200L);
                            }
                        }
                    };
                }
                webView.setWebChromeClient(webChromeClient);
                this.c = webView;
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
            layoutParams.weight = 1.0f;
            this.c.setLayoutParams(layoutParams);
        } catch (Throwable th) {
            s.b("BrowserView", "webview is error", th);
        }
        this.d = new ToolBar(getContext());
        this.d.setLayoutParams(new LinearLayout.LayoutParams(-1, w.b(getContext(), 40.0f)));
        this.d.setBackgroundColor(-1);
        addView(this.b);
        if (this.c != null) {
            addView(this.c);
        }
        addView(this.d);
        this.b.initResource(true);
        this.d.getItem("backward").setEnabled(false);
        this.d.getItem("forward").setEnabled(false);
        this.d.setOnItemClickListener(new View.OnClickListener() { // from class: com.mbridge.msdk.foundation.webview.BrowserView.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (BrowserView.this.c != null) {
                    BrowserView.this.c.stopLoading();
                }
                String str = (String) view.getTag();
                boolean z = false;
                if (TextUtils.equals(str, "backward")) {
                    BrowserView.this.d.getItem("forward").setEnabled(true);
                    if (BrowserView.this.c != null && BrowserView.this.c.canGoBack()) {
                        BrowserView.this.c.goBack();
                    }
                    View item = BrowserView.this.d.getItem("backward");
                    if (BrowserView.this.c != null && BrowserView.this.c.canGoBack()) {
                        z = true;
                    }
                    item.setEnabled(z);
                    return;
                }
                if (TextUtils.equals(str, "forward")) {
                    BrowserView.this.d.getItem("backward").setEnabled(true);
                    if (BrowserView.this.c != null && BrowserView.this.c.canGoForward()) {
                        BrowserView.this.c.goForward();
                    }
                    View item2 = BrowserView.this.d.getItem("forward");
                    if (BrowserView.this.c != null && BrowserView.this.c.canGoForward()) {
                        z = true;
                    }
                    item2.setEnabled(z);
                    return;
                }
                if (TextUtils.equals(str, "refresh")) {
                    BrowserView.this.d.getItem("backward").setEnabled(BrowserView.this.c != null && BrowserView.this.c.canGoBack());
                    View item3 = BrowserView.this.d.getItem("forward");
                    if (BrowserView.this.c != null && BrowserView.this.c.canGoForward()) {
                        z = true;
                    }
                    item3.setEnabled(z);
                    if (BrowserView.this.c != null) {
                        BrowserView.this.c.loadUrl(BrowserView.this.a);
                        return;
                    }
                    return;
                }
                if (!TextUtils.equals(str, "exits") || BrowserView.this.e == null) {
                    return;
                }
                BrowserView.this.e.a();
            }
        });
    }

    public static final class MBDownloadListener implements DownloadListener {
        private CampaignEx campaignEx;
        private String title;

        public MBDownloadListener(CampaignEx campaignEx) {
            this.campaignEx = campaignEx;
        }

        public MBDownloadListener() {
        }

        public final void setTitle(String str) {
            this.title = str;
        }

        @Override // android.webkit.DownloadListener
        public final void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            v.a(this.title, str, this.campaignEx, true);
        }
    }

    public void destroy() {
        if (this.c != null) {
            this.c.stopLoading();
            this.c.setWebViewClient(null);
            this.c.destroy();
            removeAllViews();
        }
    }
}
