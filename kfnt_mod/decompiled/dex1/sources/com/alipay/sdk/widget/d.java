package com.alipay.sdk.widget;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.JsPromptResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.widget.ImageView;
import anet.channel.util.HttpConstant;
import com.alipay.sdk.util.k;
import com.alipay.sdk.widget.e;
import com.bytedance.msdk.adapter.config.TTBaseAdapterConfiguration;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import com.bytedance.sdk.openadsdk.api.plugin.PluginConstants;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d extends c implements e.b, e.c, e.d {
    public static final String d = "alipayjsbridge://";
    public static final String e = "onBack";
    public static final String f = "setTitle";
    public static final String g = "onRefresh";
    public static final String h = "showBackButton";
    public static final String i = "onExit";
    public static final String j = "onLoadJs";
    public static final String k = "callNativeFunc";
    public static final String l = "back";
    public static final String m = "title";
    public static final String n = "refresh";
    public static final String o = "backButton";
    public static final String p = "refreshButton";
    public static final String q = "exit";
    public static final String r = "action";
    public static final String s = "pushWindow";
    public static final String t = "h5JsFuncCallback";
    public static final String u = "sdkInfo";
    private static final String v = "sdk_result_code:";
    private boolean A;
    private e B;
    private f C;
    private boolean w;
    private String x;
    private boolean y;
    private final com.alipay.sdk.sys.a z;

    @Override // android.view.ViewGroup
    public synchronized boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.y ? true : super.onInterceptTouchEvent(motionEvent);
    }

    public d(Activity activity, com.alipay.sdk.sys.a aVar, String str) {
        super(activity, str);
        this.w = true;
        this.x = "GET";
        this.y = false;
        this.B = null;
        this.C = new f();
        this.z = aVar;
        e();
    }

    private synchronized boolean e() {
        try {
            this.B = new e(this.f2683a, this.z, new e.a(!c(), !c()));
            this.B.setChromeProxy(this);
            this.B.setWebClientProxy(this);
            this.B.setWebEventProxy(this);
            addView(this.B);
        } catch (Exception unused) {
            return false;
        }
        return true;
    }

    private synchronized void f() {
        Activity activity = this.f2683a;
        e eVar = this.B;
        if (activity != null && eVar != null) {
            if (this.w) {
                activity.finish();
            } else {
                eVar.a("javascript:window.AlipayJSBridge.callListener('h5BackAction');");
            }
        }
    }

    private synchronized void g() {
        WebView webView = this.B.getWebView();
        if (webView.canGoBack()) {
            webView.goBack();
        } else if (this.C != null && !this.C.b()) {
            h();
        } else {
            a(false);
        }
    }

    public synchronized void a(String str, String str2, boolean z) {
        this.x = str2;
        this.B.getTitle().setText(str);
        this.w = z;
    }

    private synchronized void a(boolean z) {
        com.alipay.sdk.app.b.a(z);
        this.f2683a.finish();
    }

    @Override // com.alipay.sdk.widget.c
    public synchronized void a(String str) {
        if ("POST".equals(this.x)) {
            this.B.a(str, (byte[]) null);
        } else {
            this.B.a(str);
        }
        a(this.B.getWebView());
    }

    @Override // com.alipay.sdk.widget.c
    public synchronized void a() {
        this.B.a();
        this.C.c();
    }

    @Override // com.alipay.sdk.widget.c
    public synchronized boolean b() {
        Activity activity = this.f2683a;
        if (activity == null) {
            return true;
        }
        if (c()) {
            e eVar = this.B;
            if (eVar != null && eVar.getWebView() != null) {
                if (eVar.getWebView().canGoBack()) {
                    if (d()) {
                        com.alipay.sdk.app.c cVarB = com.alipay.sdk.app.c.b(com.alipay.sdk.app.c.NETWORK_ERROR.a());
                        com.alipay.sdk.app.b.a(com.alipay.sdk.app.b.a(cVarB.a(), cVarB.b(), ""));
                        activity.finish();
                    }
                } else {
                    com.alipay.sdk.app.b.a(com.alipay.sdk.app.b.c());
                    activity.finish();
                }
                return true;
            }
            activity.finish();
            return true;
        }
        if (!this.y) {
            f();
        }
        return true;
    }

    @Override // com.alipay.sdk.widget.e.b
    public synchronized boolean a(e eVar, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        if (str2.startsWith("<head>") && str2.contains(v)) {
            this.f2683a.runOnUiThread(new Runnable() { // from class: com.alipay.sdk.widget.d.1
                @Override // java.lang.Runnable
                public void run() {
                    d.this.f2683a.finish();
                }
            });
        }
        jsPromptResult.cancel();
        return true;
    }

    @Override // com.alipay.sdk.widget.e.b
    public synchronized void a(e eVar, String str) {
        if (!str.startsWith(HttpConstant.HTTP) && !eVar.getUrl().endsWith(str)) {
            this.B.getTitle().setText(str);
        }
    }

    private synchronized boolean h() {
        if (this.C.b()) {
            this.f2683a.finish();
        } else {
            this.y = true;
            final e eVar = this.B;
            this.B = this.C.a();
            TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 1.0f, 1, 0.0f, 1, 0.0f);
            translateAnimation.setDuration(400L);
            translateAnimation.setFillAfter(false);
            translateAnimation.setAnimationListener(new a() { // from class: com.alipay.sdk.widget.d.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super();
                }

                @Override // com.alipay.sdk.widget.d.a, android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    eVar.a();
                    d.this.y = false;
                }
            });
            eVar.setAnimation(translateAnimation);
            removeView(eVar);
            addView(this.B);
        }
        return true;
    }

    private synchronized boolean b(final String str, String str2) {
        final e eVar = this.B;
        try {
            this.B = new e(this.f2683a, this.z, new e.a(!c(), !c()));
            this.B.setChromeProxy(this);
            this.B.setWebClientProxy(this);
            this.B.setWebEventProxy(this);
            if (!TextUtils.isEmpty(str2)) {
                this.B.getTitle().setText(str2);
            }
            this.y = true;
            this.C.a(eVar);
            TranslateAnimation translateAnimation = new TranslateAnimation(1, 1.0f, 1, 0.0f, 1, 0.0f, 1, 0.0f);
            translateAnimation.setDuration(400L);
            translateAnimation.setFillAfter(false);
            translateAnimation.setAnimationListener(new a() { // from class: com.alipay.sdk.widget.d.3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super();
                }

                @Override // com.alipay.sdk.widget.d.a, android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    d.this.removeView(eVar);
                    d.this.B.a(str);
                    d.this.y = false;
                }
            });
            this.B.setAnimation(translateAnimation);
            addView(this.B);
        } catch (Throwable unused) {
            return false;
        }
        return true;
    }

    @Override // com.alipay.sdk.widget.e.c
    public synchronized boolean b(e eVar, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Activity activity = this.f2683a;
        if (activity == null) {
            return true;
        }
        if (k.a(this.z, str, activity)) {
            return true;
        }
        if (str.startsWith(d)) {
            b(str.substring(d.length()));
        } else if (TextUtils.equals(str, com.alipay.sdk.cons.a.o)) {
            a(false);
        } else if (str.startsWith(BDAccountNetApi.Platform.PROJECT_MODE_SCHEME) || str.startsWith("https://")) {
            this.B.a(str);
        } else {
            try {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(str));
                activity.startActivity(intent);
            } catch (Throwable th) {
                com.alipay.sdk.app.statistic.a.a(this.z, com.alipay.sdk.app.statistic.b.f2572b, th);
            }
        }
        return true;
    }

    @Override // com.alipay.sdk.widget.e.c
    public synchronized boolean c(e eVar, String str) {
        com.alipay.sdk.app.statistic.a.b(this.z, com.alipay.sdk.app.statistic.b.f2572b, "h5ld", SystemClock.elapsedRealtime() + "|" + k.e(str));
        return false;
    }

    @Override // com.alipay.sdk.widget.e.c
    public synchronized boolean d(e eVar, String str) {
        com.alipay.sdk.app.statistic.a.b(this.z, com.alipay.sdk.app.statistic.b.f2572b, "h5ldd", SystemClock.elapsedRealtime() + "|" + k.e(str));
        eVar.a("javascript:window.prompt('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');(function() {\n    if (window.AlipayJSBridge) {\n        return\n    }\n\n    function alipayjsbridgeFunc(url) {\n        var iframe = document.createElement(\"iframe\");\n        iframe.style.width = \"1px\";\n        iframe.style.height = \"1px\";\n        iframe.style.display = \"none\";\n        iframe.src = url;\n        document.body.appendChild(iframe);\n        setTimeout(function() {\n            document.body.removeChild(iframe)\n        }, 100)\n    }\n    window.alipayjsbridgeSetTitle = function(title) {\n        document.title = title;\n        alipayjsbridgeFunc(\"alipayjsbridge://setTitle?title=\" + encodeURIComponent(title))\n    };\n    window.alipayjsbridgeRefresh = function() {\n        alipayjsbridgeFunc(\"alipayjsbridge://onRefresh?\")\n    };\n    window.alipayjsbridgeBack = function() {\n        alipayjsbridgeFunc(\"alipayjsbridge://onBack?\")\n    };\n    window.alipayjsbridgeExit = function(bsucc) {\n        alipayjsbridgeFunc(\"alipayjsbridge://onExit?bsucc=\" + bsucc)\n    };\n    window.alipayjsbridgeShowBackButton = function(bshow) {\n        alipayjsbridgeFunc(\"alipayjsbridge://showBackButton?bshow=\" + bshow)\n    };\n    window.AlipayJSBridge = {\n        version: \"2.0\",\n        addListener: addListener,\n        hasListener: hasListener,\n        callListener: callListener,\n        callNativeFunc: callNativeFunc,\n        callBackFromNativeFunc: callBackFromNativeFunc\n    };\n    var uniqueId = 1;\n    var h5JsCallbackMap = {};\n\n    function iframeCall(paramStr) {\n        setTimeout(function() {\n        \tvar iframe = document.createElement(\"iframe\");\n        \tiframe.style.width = \"1px\";\n        \tiframe.style.height = \"1px\";\n        \tiframe.style.display = \"none\";\n        \tiframe.src = \"alipayjsbridge://callNativeFunc?\" + paramStr;\n        \tvar parent = document.body || document.documentElement;\n        \tparent.appendChild(iframe);\n        \tsetTimeout(function() {\n            \tparent.removeChild(iframe)\n        \t}, 0)\n        }, 0)\n    }\n\n    function callNativeFunc(nativeFuncName, data, h5JsCallback) {\n        var h5JsCallbackId = \"\";\n        if (h5JsCallback) {\n            h5JsCallbackId = \"cb_\" + (uniqueId++) + \"_\" + new Date().getTime();\n            h5JsCallbackMap[h5JsCallbackId] = h5JsCallback\n        }\n        var dataStr = \"\";\n        if (data) {\n            dataStr = encodeURIComponent(JSON.stringify(data))\n        }\n        var paramStr = \"func=\" + nativeFuncName + \"&cbId=\" + h5JsCallbackId + \"&data=\" + dataStr;\n        iframeCall(paramStr)\n    }\n\n    function callBackFromNativeFunc(h5JsCallbackId, data) {\n        var h5JsCallback = h5JsCallbackMap[h5JsCallbackId];\n        if (h5JsCallback) {\n            h5JsCallback(data);\n            delete h5JsCallbackMap[h5JsCallbackId]\n        }\n    }\n    var h5ListenerMap = {};\n\n    function addListener(jsFuncName, jsFunc) {\n        h5ListenerMap[jsFuncName] = jsFunc\n    }\n\n    function hasListener(jsFuncName) {\n        var jsFunc = h5ListenerMap[jsFuncName];\n        if (!jsFunc) {\n            return false\n        }\n        return true\n    }\n\n    function callListener(h5JsFuncName, data, nativeCallbackId) {\n        var responseCallback;\n        if (nativeCallbackId) {\n            responseCallback = function(responseData) {\n                var dataStr = \"\";\n                if (responseData) {\n                    dataStr = encodeURIComponent(JSON.stringify(responseData))\n                }\n                var paramStr = \"func=h5JsFuncCallback\" + \"&cbId=\" + nativeCallbackId + \"&data=\" + dataStr;\n                iframeCall(paramStr)\n            }\n        }\n        var h5JsFunc = h5ListenerMap[h5JsFuncName];\n        if (h5JsFunc) {\n            h5JsFunc(data, responseCallback)\n        } else if (h5JsFuncName == \"h5BackAction\") {\n            if (!window.alipayjsbridgeH5BackAction || !alipayjsbridgeH5BackAction()) {\n                var paramStr = \"func=back\";\n                iframeCall(paramStr)\n            }\n        } else {\n            console.log(\"AlipayJSBridge: no h5JsFunc \" + h5JsFuncName + data)\n        }\n    }\n    var event;\n    if (window.CustomEvent) {\n        event = new CustomEvent(\"alipayjsbridgeready\")\n    } else {\n        event = document.createEvent(\"Event\");\n        event.initEvent(\"alipayjsbridgeready\", true, true)\n    }\n    document.dispatchEvent(event);\n    setTimeout(excuteH5InitFuncs, 0);\n\n    function excuteH5InitFuncs() {\n        if (window.AlipayJSBridgeInitArray) {\n            var h5InitFuncs = window.AlipayJSBridgeInitArray;\n            delete window.AlipayJSBridgeInitArray;\n            for (var i = 0; i < h5InitFuncs.length; i++) {\n                try {\n                    h5InitFuncs[i](AlipayJSBridge)\n                } catch (e) {\n                    setTimeout(function() {\n                        throw e\n                    })\n                }\n            }\n        }\n    }\n})();\n;window.AlipayJSBridge.callListener('h5PageFinished');");
        eVar.getRefreshButton().setVisibility(0);
        return true;
    }

    @Override // com.alipay.sdk.widget.e.c
    public synchronized boolean a(e eVar, int i2, String str, String str2) {
        this.A = true;
        com.alipay.sdk.app.statistic.a.a(this.z, com.alipay.sdk.app.statistic.b.f2571a, com.alipay.sdk.app.statistic.b.s, "onReceivedError:" + str2);
        eVar.getRefreshButton().setVisibility(0);
        return false;
    }

    @Override // com.alipay.sdk.widget.e.c
    public synchronized boolean a(e eVar, final SslErrorHandler sslErrorHandler, SslError sslError) {
        final Activity activity = this.f2683a;
        if (activity == null) {
            return true;
        }
        com.alipay.sdk.app.statistic.a.a(this.z, com.alipay.sdk.app.statistic.b.f2571a, com.alipay.sdk.app.statistic.b.s, "2-" + sslError);
        activity.runOnUiThread(new Runnable() { // from class: com.alipay.sdk.widget.d.4
            @Override // java.lang.Runnable
            public void run() {
                b.a(activity, "安全警告", "安全连接证书校验无效，将无法保证访问数据的安全性，请安装支付宝后重试。", "确定", new DialogInterface.OnClickListener() { // from class: com.alipay.sdk.widget.d.4.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        sslErrorHandler.cancel();
                        com.alipay.sdk.app.statistic.a.a(d.this.z, com.alipay.sdk.app.statistic.b.f2571a, com.alipay.sdk.app.statistic.b.u, OnekeyLoginConstants.UNICOM_TYPE);
                        com.alipay.sdk.app.b.a(com.alipay.sdk.app.b.c());
                        activity.finish();
                    }
                }, null, null);
            }
        });
        return true;
    }

    public boolean d() {
        return this.A;
    }

    private synchronized void b(String str) {
        Map<String, String> mapA = k.a(this.z, str);
        if (str.startsWith(k)) {
            a(mapA.get("func"), mapA.get("cbId"), mapA.get("data"));
        } else if (str.startsWith(e)) {
            g();
        } else if (str.startsWith(f) && mapA.containsKey(m)) {
            this.B.getTitle().setText(mapA.get(m));
        } else if (str.startsWith(g)) {
            this.B.getWebView().reload();
        } else if (str.startsWith(h) && mapA.containsKey("bshow")) {
            this.B.getBackButton().setVisibility(TextUtils.equals("true", mapA.get("bshow")) ? 0 : 4);
        } else if (str.startsWith(i)) {
            com.alipay.sdk.app.b.a(mapA.get("result"));
            a(TextUtils.equals("true", mapA.get("bsucc")));
        } else if (str.startsWith(j)) {
            this.B.a("javascript:(function() {\n    if (window.AlipayJSBridge) {\n        return\n    }\n\n    function alipayjsbridgeFunc(url) {\n        var iframe = document.createElement(\"iframe\");\n        iframe.style.width = \"1px\";\n        iframe.style.height = \"1px\";\n        iframe.style.display = \"none\";\n        iframe.src = url;\n        document.body.appendChild(iframe);\n        setTimeout(function() {\n            document.body.removeChild(iframe)\n        }, 100)\n    }\n    window.alipayjsbridgeSetTitle = function(title) {\n        document.title = title;\n        alipayjsbridgeFunc(\"alipayjsbridge://setTitle?title=\" + encodeURIComponent(title))\n    };\n    window.alipayjsbridgeRefresh = function() {\n        alipayjsbridgeFunc(\"alipayjsbridge://onRefresh?\")\n    };\n    window.alipayjsbridgeBack = function() {\n        alipayjsbridgeFunc(\"alipayjsbridge://onBack?\")\n    };\n    window.alipayjsbridgeExit = function(bsucc) {\n        alipayjsbridgeFunc(\"alipayjsbridge://onExit?bsucc=\" + bsucc)\n    };\n    window.alipayjsbridgeShowBackButton = function(bshow) {\n        alipayjsbridgeFunc(\"alipayjsbridge://showBackButton?bshow=\" + bshow)\n    };\n    window.AlipayJSBridge = {\n        version: \"2.0\",\n        addListener: addListener,\n        hasListener: hasListener,\n        callListener: callListener,\n        callNativeFunc: callNativeFunc,\n        callBackFromNativeFunc: callBackFromNativeFunc\n    };\n    var uniqueId = 1;\n    var h5JsCallbackMap = {};\n\n    function iframeCall(paramStr) {\n        setTimeout(function() {\n        \tvar iframe = document.createElement(\"iframe\");\n        \tiframe.style.width = \"1px\";\n        \tiframe.style.height = \"1px\";\n        \tiframe.style.display = \"none\";\n        \tiframe.src = \"alipayjsbridge://callNativeFunc?\" + paramStr;\n        \tvar parent = document.body || document.documentElement;\n        \tparent.appendChild(iframe);\n        \tsetTimeout(function() {\n            \tparent.removeChild(iframe)\n        \t}, 0)\n        }, 0)\n    }\n\n    function callNativeFunc(nativeFuncName, data, h5JsCallback) {\n        var h5JsCallbackId = \"\";\n        if (h5JsCallback) {\n            h5JsCallbackId = \"cb_\" + (uniqueId++) + \"_\" + new Date().getTime();\n            h5JsCallbackMap[h5JsCallbackId] = h5JsCallback\n        }\n        var dataStr = \"\";\n        if (data) {\n            dataStr = encodeURIComponent(JSON.stringify(data))\n        }\n        var paramStr = \"func=\" + nativeFuncName + \"&cbId=\" + h5JsCallbackId + \"&data=\" + dataStr;\n        iframeCall(paramStr)\n    }\n\n    function callBackFromNativeFunc(h5JsCallbackId, data) {\n        var h5JsCallback = h5JsCallbackMap[h5JsCallbackId];\n        if (h5JsCallback) {\n            h5JsCallback(data);\n            delete h5JsCallbackMap[h5JsCallbackId]\n        }\n    }\n    var h5ListenerMap = {};\n\n    function addListener(jsFuncName, jsFunc) {\n        h5ListenerMap[jsFuncName] = jsFunc\n    }\n\n    function hasListener(jsFuncName) {\n        var jsFunc = h5ListenerMap[jsFuncName];\n        if (!jsFunc) {\n            return false\n        }\n        return true\n    }\n\n    function callListener(h5JsFuncName, data, nativeCallbackId) {\n        var responseCallback;\n        if (nativeCallbackId) {\n            responseCallback = function(responseData) {\n                var dataStr = \"\";\n                if (responseData) {\n                    dataStr = encodeURIComponent(JSON.stringify(responseData))\n                }\n                var paramStr = \"func=h5JsFuncCallback\" + \"&cbId=\" + nativeCallbackId + \"&data=\" + dataStr;\n                iframeCall(paramStr)\n            }\n        }\n        var h5JsFunc = h5ListenerMap[h5JsFuncName];\n        if (h5JsFunc) {\n            h5JsFunc(data, responseCallback)\n        } else if (h5JsFuncName == \"h5BackAction\") {\n            if (!window.alipayjsbridgeH5BackAction || !alipayjsbridgeH5BackAction()) {\n                var paramStr = \"func=back\";\n                iframeCall(paramStr)\n            }\n        } else {\n            console.log(\"AlipayJSBridge: no h5JsFunc \" + h5JsFuncName + data)\n        }\n    }\n    var event;\n    if (window.CustomEvent) {\n        event = new CustomEvent(\"alipayjsbridgeready\")\n    } else {\n        event = document.createEvent(\"Event\");\n        event.initEvent(\"alipayjsbridgeready\", true, true)\n    }\n    document.dispatchEvent(event);\n    setTimeout(excuteH5InitFuncs, 0);\n\n    function excuteH5InitFuncs() {\n        if (window.AlipayJSBridgeInitArray) {\n            var h5InitFuncs = window.AlipayJSBridgeInitArray;\n            delete window.AlipayJSBridgeInitArray;\n            for (var i = 0; i < h5InitFuncs.length; i++) {\n                try {\n                    h5InitFuncs[i](AlipayJSBridge)\n                } catch (e) {\n                    setTimeout(function() {\n                        throw e\n                    })\n                }\n            }\n        }\n    }\n})();\n");
        }
    }

    private synchronized void a(String str, String str2, String str3) {
        e eVar = this.B;
        if (eVar == null) {
            return;
        }
        JSONObject jSONObjectC = k.c(str3);
        if (m.equals(str) && jSONObjectC.has(m)) {
            eVar.getTitle().setText(jSONObjectC.optString(m, ""));
        } else if (n.equals(str)) {
            eVar.getWebView().reload();
        } else if (l.equals(str)) {
            g();
        } else {
            int i2 = 0;
            if (q.equals(str)) {
                com.alipay.sdk.app.b.a(jSONObjectC.optString("result", null));
                a(jSONObjectC.optBoolean("success", false));
            } else if (o.equals(str)) {
                boolean zOptBoolean = jSONObjectC.optBoolean(TTLogUtil.TAG_EVENT_SHOW, true);
                ImageView backButton = eVar.getBackButton();
                if (!zOptBoolean) {
                    i2 = 4;
                }
                backButton.setVisibility(i2);
            } else if (p.equals(str)) {
                boolean zOptBoolean2 = jSONObjectC.optBoolean(TTLogUtil.TAG_EVENT_SHOW, true);
                ImageView refreshButton = eVar.getRefreshButton();
                if (!zOptBoolean2) {
                    i2 = 4;
                }
                refreshButton.setVisibility(i2);
            } else if (s.equals(str) && jSONObjectC.optString("url", null) != null) {
                b(jSONObjectC.optString("url"), jSONObjectC.optString(m, ""));
            } else if (u.equals(str)) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put(PluginConstants.KEY_SDK_VERSION, "15.7.9");
                    jSONObject.put(TTBaseAdapterConfiguration.APP_NAME_EXTRA_KEY, this.z.b());
                    jSONObject.put("app_version", this.z.c());
                } catch (Throwable th) {
                    com.alipay.sdk.app.statistic.a.a(this.z, com.alipay.sdk.app.statistic.b.f2572b, "jInfoErr", th);
                }
                eVar.a(String.format("window.AlipayJSBridge.callBackFromNativeFunc('%s','%s');", str2, jSONObject.toString().replace("'", "")));
            }
        }
    }

    @Override // com.alipay.sdk.widget.e.d
    public synchronized void a(e eVar) {
        f();
    }

    @Override // com.alipay.sdk.widget.e.d
    public synchronized void b(e eVar) {
        eVar.getWebView().reload();
        eVar.getRefreshButton().setVisibility(4);
    }

    private abstract class a implements Animation.AnimationListener {
        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }

        private a() {
        }
    }
}
