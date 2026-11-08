package com.mbridge.msdk.advanced.view;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.mbridge.msdk.advanced.c.c;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.mbjscommon.base.b;
import com.mbridge.msdk.playercommon.exoplayer2.util.MimeTypes;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URLDecoder;

/* compiled from: NativeAdvancedWebViewClient.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a extends b {
    String a;
    com.mbridge.msdk.advanced.c.a b;
    private final String c = "NativeAdvancedWebViewClient";
    private c d;

    public a(String str, com.mbridge.msdk.advanced.c.a aVar, c cVar) {
        this.a = str;
        this.b = aVar;
        this.d = cVar;
    }

    @Override // android.webkit.WebViewClient
    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
        try {
            StringBuilder sb = new StringBuilder("javascript:");
            sb.append(com.mbridge.msdk.b.b.b.a().b());
            if (Build.VERSION.SDK_INT <= 19) {
                webView.loadUrl(sb.toString());
            } else {
                webView.evaluateJavascript(sb.toString(), new ValueCallback<String>() { // from class: com.mbridge.msdk.advanced.view.a.1
                    @Override // android.webkit.ValueCallback
                    public final /* bridge */ /* synthetic */ void onReceiveValue(String str2) {
                    }
                });
            }
        } catch (Throwable th) {
            s.b("NativeAdvancedWebViewClient", "onPageStarted", th);
        }
    }

    @Override // com.mbridge.msdk.mbjscommon.base.b, android.webkit.WebViewClient
    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        try {
            s.d("NativeAdvancedWebViewClient", "Use html to open url.");
            if (this.b == null) {
                return true;
            }
            this.b.a(false, str);
            return true;
        } catch (Throwable th) {
            s.b("NativeAdvancedWebViewClient", "shouldOverrideUrlLoading", th);
            return false;
        }
    }

    @Override // android.webkit.WebViewClient
    public final WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        s.a("NativeAdvancedWebViewClient", "============shouldInterceptRequest:" + str);
        return a(webView, str);
    }

    @Override // android.webkit.WebViewClient
    public final WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
        return a(webView, Build.VERSION.SDK_INT >= 21 ? webResourceRequest.getUrl().toString() : "");
    }

    private WebResourceResponse a(WebView webView, String str) {
        Uri uri;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        boolean z = false;
        if (!TextUtils.isEmpty(str) && (uri = Uri.parse(str)) != null && "mb-h5".equals(uri.getScheme())) {
            z = true;
        }
        if (!z || this.d == null) {
            return null;
        }
        String strD = this.d.d(URLDecoder.decode(Uri.parse(str).getQueryParameter("uri")));
        try {
            if (TextUtils.isEmpty(strD)) {
                return null;
            }
            s.a("webviewclient", "relace url" + strD);
            if (strD.contains("127.0.0.1") || strD.startsWith("http")) {
                return null;
            }
            return new WebResourceResponse(MimeTypes.VIDEO_MP4, "utf-8", new FileInputStream(strD));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public final void a() {
        if (this.d != null) {
            this.d = null;
        }
    }
}
