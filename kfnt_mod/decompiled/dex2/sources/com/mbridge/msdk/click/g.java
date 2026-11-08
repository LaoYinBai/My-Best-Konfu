package com.mbridge.msdk.click;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.playercommon.exoplayer2.DefaultLoadControl;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.util.HashMap;

/* compiled from: WebViewSpider.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class g {
    public static long a = 0;
    private static final String d = "g";
    boolean b;
    boolean c;
    private int e;
    private int f;
    private com.mbridge.msdk.b.a h;
    private a i;
    private String j;
    private String k;
    private WebView l;
    private boolean m;
    private String n;
    private int o;
    private boolean q;
    private boolean p = false;
    private final Runnable r = new Runnable() { // from class: com.mbridge.msdk.click.g.4
        @Override // java.lang.Runnable
        public final void run() {
            g.this.p = true;
            g.this.o = 1;
            s.d(g.d, "js超时！超时上限：" + g.this.f + "ms");
            g.n(g.this);
        }
    };
    private final Runnable s = new Runnable() { // from class: com.mbridge.msdk.click.g.5
        @Override // java.lang.Runnable
        public final void run() {
            g.this.p = true;
            g.this.o = 2;
            s.d(g.d, "http超时！超时上限：" + g.this.e + "ms");
            g.n(g.this);
        }
    };
    private Handler g = new Handler(Looper.getMainLooper());

    /* compiled from: WebViewSpider.java */
    interface a {
        void a(int i, String str, String str2, String str3);

        void a(String str, boolean z, String str2);

        boolean a(String str);

        boolean b(String str);

        boolean c(String str);
    }

    public g(boolean z) {
        this.e = DefaultLoadControl.DEFAULT_MIN_BUFFER_MS;
        this.f = PushConstants.WORK_RECEIVER_EVENT_CORE_ERROR;
        this.h = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
        if (this.h == null) {
            this.h = com.mbridge.msdk.b.b.a().b();
        }
        this.m = this.h.K();
        if (z) {
            this.e = (int) this.h.G();
            this.f = (int) this.h.G();
        } else {
            this.e = (int) this.h.H();
            this.f = (int) this.h.H();
        }
    }

    public final void a(String str, String str2, String str3, Context context, String str4, String str5, a aVar) {
        if (aVar == null) {
            throw new NullPointerException("OverrideUrlLoadingListener can not be null");
        }
        this.k = str5;
        this.j = str4;
        this.i = aVar;
        a(str, str2, str3, context);
    }

    public final void a(String str, String str2, String str3, Context context, String str4, a aVar) {
        if (aVar == null) {
            throw new NullPointerException("OverrideUrlLoadingListener can not be null");
        }
        this.j = str4;
        this.i = aVar;
        a(str, str2, str3, context);
    }

    private void a(final String str, final String str2, final String str3, final Context context) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            a(str, str2, str3, context, this.j);
        } else {
            this.g.post(new Runnable() { // from class: com.mbridge.msdk.click.g.1
                @Override // java.lang.Runnable
                public final void run() {
                    g.this.a(str, str2, str3, context, g.this.j);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, String str3, Context context, String str4) {
        try {
            a(context, str, str2, str3);
            if (!TextUtils.isEmpty(this.k)) {
                this.l.getSettings().setDefaultTextEncodingName("utf-8");
                this.f = 2000;
                this.e = 2000;
                s.b(d, this.k);
                this.l.loadDataWithBaseURL(str4, this.k, "*/*", "utf-8", str4);
                return;
            }
            if (this.m) {
                HashMap map = new HashMap();
                if (this.l.getUrl() != null) {
                    map.put("Referer", this.l.getUrl());
                }
                this.l.loadUrl(str4, map);
                return;
            }
            this.l.loadUrl(str4);
        } catch (Throwable th) {
            try {
                if (this.i != null) {
                    this.i.a(0, this.j, th.getMessage(), this.n);
                }
            } catch (Exception unused) {
            }
        }
    }

    private void a(final Context context, final String str, final String str2, final String str3) {
        this.l = new WebView(context);
        this.l.getSettings().setJavaScriptEnabled(true);
        this.l.getSettings().setCacheMode(2);
        this.l.getSettings().setLoadsImagesAutomatically(false);
        this.l.setWebViewClient(new WebViewClient() { // from class: com.mbridge.msdk.click.g.2
            @Override // android.webkit.WebViewClient
            public final void onPageStarted(WebView webView, String str4, Bitmap bitmap) {
                try {
                    webView.loadUrl("javascript:window.navigator.vibrate([]);");
                    if (g.this.q) {
                        g.this.o = 0;
                        g.c(g.this);
                        return;
                    }
                    g.this.c = false;
                    if (webView.getTag() == null) {
                        webView.setTag("has_first_started");
                    } else {
                        g.this.b = true;
                    }
                    synchronized (g.d) {
                        String str5 = "加载页面-开始：";
                        if (g.this.b || g.this.c) {
                            str5 = "加载页面-开始：（重定向）";
                        }
                        if (URLUtil.isHttpsUrl(str4)) {
                            s.d(g.d, str5 + str4);
                        } else {
                            s.b(g.d, str5 + str4);
                        }
                        g.this.j = str4;
                        if (g.this.i != null && g.this.i.a(str4)) {
                            g.this.q = true;
                            g.c(g.this);
                        } else {
                            g.e(g.this);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // android.webkit.WebViewClient
            public final boolean shouldOverrideUrlLoading(WebView webView, String str4) {
                synchronized (g.d) {
                    s.a(g.d, "override js跳转：" + str4);
                    g.this.c = true;
                    g.this.d();
                    if (g.this.q) {
                        g.this.c();
                        g.c(g.this);
                        return true;
                    }
                    g.this.j = str4;
                    if (g.this.i == null || !g.this.i.b(str4)) {
                        if (!g.this.m) {
                            g.this.l.loadUrl(str4);
                        } else {
                            HashMap map = new HashMap();
                            if (g.this.l.getUrl() != null) {
                                map.put("Referer", g.this.l.getUrl());
                            }
                            g.this.l.loadUrl(str4, map);
                        }
                        return true;
                    }
                    g.this.q = true;
                    g.this.c();
                    g.c(g.this);
                    return true;
                }
            }

            @Override // android.webkit.WebViewClient
            public final void onReceivedError(WebView webView, int i, String str4, String str5) {
                s.b(g.d, "onReceivedError: errno = " + i + ", url: " + webView.getUrl() + ",\n onReceivedError：, description: " + str4 + ", failingUrl: " + str5);
                synchronized (g.d) {
                    g.this.q = true;
                    g.this.b();
                    g.c(g.this);
                }
                if (g.this.i != null) {
                    g.this.i.a(i, webView.getUrl(), str4, g.this.n);
                }
            }

            @Override // android.webkit.WebViewClient
            public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                try {
                    s.a(g.d, "onReceivedSslError IS_SP_CBT_CF:" + MBridgeConstans.IS_SP_CBT_CF);
                    if (MBridgeConstans.IS_SP_CBT_CF && sslErrorHandler != null) {
                        sslErrorHandler.cancel();
                    }
                    if (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str2)) {
                        return;
                    }
                    new com.mbridge.msdk.foundation.same.report.c(context).a(str, str2, str3, webView.getUrl());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // android.webkit.WebViewClient
            public final void onPageFinished(WebView webView, String str4) {
                super.onPageFinished(webView, str4);
                try {
                    webView.loadUrl("javascript:window.navigator.vibrate([]);");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.l.setWebChromeClient(new WebChromeClient() { // from class: com.mbridge.msdk.click.g.3
            @Override // android.webkit.WebChromeClient
            public final boolean onJsAlert(WebView webView, String str4, String str5, JsResult jsResult) {
                return true;
            }

            @Override // android.webkit.WebChromeClient
            public final boolean onJsConfirm(WebView webView, String str4, String str5, JsResult jsResult) {
                return true;
            }

            @Override // android.webkit.WebChromeClient
            public final boolean onJsPrompt(WebView webView, String str4, String str5, String str6, JsPromptResult jsPromptResult) {
                return true;
            }

            @Override // android.webkit.WebChromeClient
            public final void onProgressChanged(WebView webView, int i) {
                if (i == 100) {
                    try {
                        s.b(g.d, "加载页面-进度完成：" + webView.getUrl());
                        webView.loadUrl("javascript:window.navigator.vibrate([]);");
                        if (!g.this.q && !g.this.c) {
                            g.l(g.this);
                        }
                        if (g.this.i != null) {
                            g.this.i.c(webView.getUrl());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        d();
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.g.removeCallbacks(this.s);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.g.removeCallbacks(this.r);
    }

    static /* synthetic */ void c(g gVar) {
        synchronized (d) {
            try {
                gVar.b();
            } catch (Exception unused) {
                s.d(d, "webview colse to failed");
            } catch (Throwable unused2) {
                s.d(d, "webview colse to failed");
            }
            if (gVar.i != null) {
                gVar.i.a(gVar.j, gVar.p, gVar.n);
            }
        }
    }

    static /* synthetic */ void e(g gVar) {
        gVar.c();
        boolean z = gVar.b;
        gVar.g.postDelayed(gVar.s, gVar.e);
    }

    static /* synthetic */ void l(g gVar) {
        gVar.d();
        boolean z = gVar.b;
        gVar.g.postDelayed(gVar.r, gVar.f);
    }

    static /* synthetic */ void n(g gVar) {
        synchronized (d) {
            try {
                gVar.b();
                gVar.l.destroy();
            } catch (Exception unused) {
                s.d(d, "webview colse to failed");
            } catch (Throwable unused2) {
                s.d(d, "webview colse to failed");
            }
            if (gVar.i != null) {
                gVar.i.a(gVar.j, gVar.p, gVar.n);
            }
        }
    }
}
