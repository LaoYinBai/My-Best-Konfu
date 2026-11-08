package com.mbridge.msdk.mbjscommon.c;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.mbjscommon.windvane.c;

/* compiled from: DefaultRVWebViewListener.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a implements c {
    public void a(Object obj) {
        s.a("RVWindVaneWebView", "getEndScreenInfo");
    }

    public void a(WebView webView, String str, String str2, int i, int i2) {
        s.a("RVWindVaneWebView", "loadAds");
    }

    public void a(Object obj, String str) {
        s.a("RVWindVaneWebView", "operateComponent");
    }

    @Override // com.mbridge.msdk.mbjscommon.windvane.d
    public final void a(WebView webView, String str, Bitmap bitmap) {
        s.a("RVWindVaneWebView", "onPageStarted");
    }

    @Override // com.mbridge.msdk.mbjscommon.windvane.d
    public final boolean b(WebView webView, String str) {
        s.a("RVWindVaneWebView", "shouldOverrideUrlLoading");
        return true;
    }

    @Override // com.mbridge.msdk.mbjscommon.windvane.d
    public void a(WebView webView, int i, String str, String str2) {
        s.a("RVWindVaneWebView", "onReceivedError");
    }

    @Override // com.mbridge.msdk.mbjscommon.windvane.d
    public void a(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        s.a("RVWindVaneWebView", "onReceivedSslError");
    }

    @Override // com.mbridge.msdk.mbjscommon.windvane.d
    public void a(WebView webView, String str) {
        s.a("RVWindVaneWebView", "onPageFinished");
    }

    @Override // com.mbridge.msdk.mbjscommon.windvane.d
    public final void b(WebView webView, int i) {
        s.a("RVWindVaneWebView", "onProgressChanged");
    }

    @Override // com.mbridge.msdk.mbjscommon.windvane.d
    public void a(WebView webView, int i) {
        s.a("RVWindVaneWebView", "readyState");
    }

    @Override // com.mbridge.msdk.mbjscommon.windvane.d
    public final void c(WebView webView, int i) {
        s.a("RVWindVaneWebView", "loadingResourceStatus");
    }
}
