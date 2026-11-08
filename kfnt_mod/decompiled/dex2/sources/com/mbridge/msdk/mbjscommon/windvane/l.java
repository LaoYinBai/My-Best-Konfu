package com.mbridge.msdk.mbjscommon.windvane;

import android.text.TextUtils;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.mbridge.msdk.foundation.tools.s;

/* compiled from: WindVaneWebViewChromeClient.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class l extends WebChromeClient {
    WindVaneWebView a;
    private d b;

    public l(WindVaneWebView windVaneWebView) {
        this.a = windVaneWebView;
    }

    public final void a(d dVar) {
        this.b = dVar;
    }

    @Override // android.webkit.WebChromeClient
    public final boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        s.d("H5_ENTRY", str2 + "");
        b jsBridge = this.a.getJsBridge();
        if (jsBridge == null || str3 == null || !jsBridge.a(str3)) {
            return false;
        }
        jsBridge.b(str2);
        jsPromptResult.confirm("");
        return true;
    }

    @Override // android.webkit.WebChromeClient
    public final void onProgressChanged(WebView webView, int i) {
        super.onProgressChanged(webView, i);
        if (this.b != null) {
            this.b.b(webView, i);
        }
    }

    @Override // android.webkit.WebChromeClient
    public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        if (consoleMessage.messageLevel() != ConsoleMessage.MessageLevel.LOG) {
            return super.onConsoleMessage(consoleMessage);
        }
        b jsBridge = this.a.getJsBridge();
        if (jsBridge != null && consoleMessage != null) {
            String strMessage = consoleMessage.message();
            if (TextUtils.isEmpty(strMessage) || !strMessage.startsWith("mv://")) {
                return false;
            }
            s.a("H5_ENTRY", "onConsoleMessage: message.length() = " + strMessage.length() + " " + strMessage);
            if (strMessage.contains("wv_hybrid:") && jsBridge.a("wv_hybrid:")) {
                String strSubstring = strMessage.substring(0, strMessage.lastIndexOf(" ") + 1);
                s.a("H5_ENTRY", "message = " + strSubstring);
                jsBridge.b(strSubstring);
                return true;
            }
        }
        return super.onConsoleMessage(consoleMessage);
    }
}
