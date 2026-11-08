package com.mbridge.msdk.mbjscommon.c;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.mbjscommon.windvane.d;

/* compiled from: DefaultWebViewListener.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b implements d {
    @Override // com.mbridge.msdk.mbjscommon.windvane.d
    public final void a(WebView webView, String str, Bitmap bitmap) {
        s.a("WindVaneWebView", "onPageStarted");
    }

    @Override // com.mbridge.msdk.mbjscommon.windvane.d
    public final boolean b(WebView webView, String str) {
        s.a("WindVaneWebView", "shouldOverrideUrlLoading");
        return true;
    }

    @Override // com.mbridge.msdk.mbjscommon.windvane.d
    public void a(WebView webView, int i, String str, String str2) {
        s.a("WindVaneWebView", "onReceivedError");
    }

    @Override // com.mbridge.msdk.mbjscommon.windvane.d
    public void a(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        s.a("WindVaneWebView", "onReceivedSslError");
    }

    @Override // com.mbridge.msdk.mbjscommon.windvane.d
    public void a(WebView webView, String str) {
        s.a("WindVaneWebView", "onPageFinished");
    }

    @Override // com.mbridge.msdk.mbjscommon.windvane.d
    public final void b(WebView webView, int i) {
        s.a("WindVaneWebView", "onProgressChanged");
    }

    @Override // com.mbridge.msdk.mbjscommon.windvane.d
    public void a(WebView webView, int i) {
        s.a("WindVaneWebView", "readyState");
    }

    @Override // com.mbridge.msdk.mbjscommon.windvane.d
    public void c(WebView webView, int i) {
        s.a("WindVaneWebView", "loadingResourceStatus");
    }
}
