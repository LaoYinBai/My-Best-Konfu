package com.ss.sys.ck;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import c.a.a.a.a.u;
import com.umeng.message.proguard.l;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SCWebView extends WebView {
    private Context a;
    private List<JSONObject> b;
    private List<JSONObject> c;

    public class a extends WebChromeClient {
        a(SCWebView sCWebView, SCWebView sCWebView2) {
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
            if (!str2.equals(com.ss.sys.ck.b.a)) {
                return super.onJsPrompt(webView, str, str2, str3, jsPromptResult);
            }
            jsPromptResult.confirm();
            new u.a(str3).start();
            return true;
        }
    }

    public class b extends WebViewClient {
        private SCWebView a;

        b(SCWebView sCWebView, SCWebView sCWebView2) {
            this.a = sCWebView2;
        }

        @Override // android.webkit.WebViewClient
        public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
            super.doUpdateVisitedHistory(webView, str, z);
        }

        @Override // android.webkit.WebViewClient
        public void onLoadResource(WebView webView, String str) {
            this.a.a("onLoadResource", str != null ? str : "");
            super.onLoadResource(webView, str);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            this.a.a("onPageStarted", str != null ? str : "");
            super.onPageFinished(webView, str);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            this.a.a("onPageStarted", str != null ? str : "");
            super.onPageStarted(webView, str, bitmap);
            SCCheckListener listener = SCCheckUtils.getInstance().getListener();
            if (listener != null) {
                listener.dialogOnReady();
            }
        }

        @Override // android.webkit.WebViewClient
        @SuppressLint({"DefaultLocale"})
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            SCWebView sCWebView = this.a;
            Object[] objArr = new Object[3];
            if (str2 == null) {
                str2 = "";
            }
            objArr[0] = str2;
            if (str == null) {
                str = "";
            }
            objArr[1] = str;
            objArr[2] = Integer.valueOf(i);
            sCWebView.a("onReceivedError", String.format("URL:%s DES:%s ERR:%d", objArr));
            SCCheckListener listener = SCCheckUtils.getInstance().getListener();
            if (listener != null) {
                listener.dialogOnError("" + i);
            }
            SCCheckUtils.getInstance().netWorkError();
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            try {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
            } catch (Throwable unused) {
            }
        }

        @Override // android.webkit.WebViewClient
        public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
            try {
                if (Build.VERSION.SDK_INT >= 21 && !webResourceRequest.isForMainFrame() && webResourceRequest.getUrl().getPath().endsWith("/favicon.ico")) {
                    return new WebResourceResponse("image/png", null, null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return super.shouldInterceptRequest(webView, webResourceRequest);
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

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) throws UnsupportedEncodingException {
            try {
                str = URLDecoder.decode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
            }
            if (!str.startsWith("jsbridge://")) {
                return super.shouldOverrideUrlLoading(webView, str);
            }
            SCWebView.a(webView, str);
            return true;
        }
    }

    public SCWebView(Context context) {
        super(context);
        this.b = new ArrayList();
        this.c = new ArrayList();
        this.a = context;
        b();
        a();
    }

    public SCWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = new ArrayList();
        this.c = new ArrayList();
        this.a = context;
        b();
        a();
    }

    public SCWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = new ArrayList();
        this.c = new ArrayList();
        this.a = context;
        b();
        a();
    }

    private void a() {
        setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
    }

    static boolean a(WebView webView, String str) {
        return c.a(webView, str);
    }

    public void a(int i, MotionEvent motionEvent) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ac", i);
            jSONObject.put("dn", motionEvent.getDevice().getName());
            jSONObject.put("db", motionEvent.getDeviceId());
            jSONObject.put("lc", String.format("%s_%s", Float.valueOf(motionEvent.getX()), Float.valueOf(motionEvent.getY())));
            this.b.add(jSONObject);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("action", str);
            jSONObject.put(l.D, Calendar.getInstance().getTimeInMillis());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("url", str2);
            jSONObject.put("detailinfos", jSONObject2);
            this.c.add(jSONObject);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void b() {
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheEnabled(false);
        settings.setUseWideViewPort(true);
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        }
        settings.setCacheMode(2);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        setOverScrollMode(2);
        setScrollContainer(false);
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        onResume();
        setWebChromeClient(new a(this, this));
        setWebViewClient(new b(this, this));
        clearCache(true);
        clearHistory();
    }

    public void b(String str, String str2) {
        CookieSyncManager.createInstance(this.a);
        CookieManager.getInstance().setCookie(str, "sec_sessionid=" + str2);
        CookieSyncManager.getInstance().sync();
    }

    public JSONArray getActionList() {
        if (this.c.size() > 50) {
            this.c = this.c.subList(0, 50);
        }
        return new JSONArray((Collection) this.c);
    }

    public JSONArray getMotionList() {
        if (this.b.size() > 50) {
            this.b = this.b.subList(0, 50);
        }
        return new JSONArray((Collection) this.b);
    }

    @Override // android.webkit.WebView, android.widget.AbsoluteLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (getMeasuredWidth() != 0) {
            getMeasuredWidth();
        }
        if (getMeasuredHeight() != 0) {
            getMeasuredHeight();
        }
    }

    @Override // android.webkit.WebView
    public void onPause() {
        super.onPause();
    }

    @Override // android.webkit.WebView
    public void onResume() {
        super.onResume();
    }

    @Override // android.webkit.WebView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0 || action == 1 || action == 2) {
            a(action, motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }
}
