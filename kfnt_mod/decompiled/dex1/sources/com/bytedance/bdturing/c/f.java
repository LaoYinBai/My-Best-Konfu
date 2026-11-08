package com.bytedance.bdturing.c;

import android.os.Build;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class f {

    /* renamed from: b, reason: collision with root package name */
    private static final String f3774b = "JsBridgeModule";

    /* renamed from: c, reason: collision with root package name */
    private static final String f3775c = "__callback_id";
    private static final String d = "__params";

    /* renamed from: a, reason: collision with root package name */
    private WebView f3776a;
    private g e = new g();

    private class a {

        /* renamed from: b, reason: collision with root package name */
        private com.bytedance.bdturing.f f3781b;

        public a(com.bytedance.bdturing.f fVar) {
            this.f3781b = fVar;
        }

        @JavascriptInterface
        public void callMethodParams(String str) {
            com.bytedance.bdturing.g.d(f.f3774b, "JS called method ======= callMethodParams(" + str + ")");
            f.this.e.a(this.f3781b, str);
        }

        @JavascriptInterface
        public void onMethodParams(String str) throws JSONException {
            com.bytedance.bdturing.g.d(f.f3774b, "JS called method ======= onMethodParams(" + str + ")");
            try {
                new JSONObject(str).getString(f.f3775c);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @JavascriptInterface
        public void offMethodParams(String str) {
            com.bytedance.bdturing.g.d(f.f3774b, "JS called method ======= offMethodParams(" + str + ")");
            f.this.e.a(str);
        }
    }

    public f(com.bytedance.bdturing.f fVar, WebView webView) {
        this.f3776a = webView;
        if (this.f3776a == null) {
            return;
        }
        this.f3776a.getSettings().setJavaScriptEnabled(true);
        this.f3776a.addJavascriptInterface(new a(fVar), "androidJsBridge");
    }

    public void a(final String str) {
        if (str == null || this.f3776a == null) {
            return;
        }
        this.f3776a.post(new Runnable() { // from class: com.bytedance.bdturing.c.f.1

            /* renamed from: a, reason: collision with root package name */
            WebView f3777a;

            {
                this.f3777a = f.this.f3776a;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (Build.VERSION.SDK_INT >= 19) {
                    this.f3777a.evaluateJavascript("javascript:window.Native2JSBridge._handleMessageFromApp(" + str + ")", null);
                    return;
                }
                this.f3777a.loadUrl("javascript:window.Native2JSBridge._handleMessageFromApp('" + str + "')");
            }
        });
        com.bytedance.bdturing.g.d(f3774b, "callJsCode ====== " + str);
    }

    public void a() {
        this.f3776a = null;
        this.e.a();
    }
}
