package com.kwad.sdk.utils;

import android.os.Build;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class bf {
    public static void a(final WebView webView, final String str, final ValueCallback<String> valueCallback) {
        if (Build.VERSION.SDK_INT >= 19) {
            ax.a(new Runnable() { // from class: com.kwad.sdk.utils.bf.1
                @Override // java.lang.Runnable
                public void run() {
                    webView.evaluateJavascript(str, valueCallback);
                }
            });
            return;
        }
        webView.loadUrl(str);
        if (valueCallback != null) {
            valueCallback.onReceiveValue(null);
        }
    }

    public static void a(WebView webView, String str, String str2) {
        a(webView, "javascript:" + str + "(" + JSONObject.quote(str2) + ")", (ValueCallback<String>) null);
    }
}
