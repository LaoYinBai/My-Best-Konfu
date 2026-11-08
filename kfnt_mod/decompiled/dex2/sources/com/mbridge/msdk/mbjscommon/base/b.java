package com.mbridge.msdk.mbjscommon.base;

import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/* compiled from: BaseWebViewClient.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends WebViewClient {
    private a a;
    private com.mbridge.msdk.mbjscommon.windvane.d b;

    public final void a(a aVar) {
        this.a = aVar;
    }

    public final a b() {
        return this.a;
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (this.a != null && this.a.a(str)) {
            return true;
        }
        if (this.b != null) {
            this.b.b(webView, str);
        }
        return super.shouldOverrideUrlLoading(webView, str);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
        if (this.b != null) {
            this.b.a(webView, i, str, str2);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
        if (this.b != null) {
            this.b.a(webView, sslErrorHandler, sslError);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        if (this.b != null) {
            this.b.a(webView, str);
        }
    }

    public final void a(com.mbridge.msdk.mbjscommon.windvane.d dVar) {
        this.b = dVar;
    }
}
