package com.ss.union.game.sdk.common.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.lang.ref.WeakReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SSWebSettings {
    private WeakReference<Context> a;
    private boolean b = true;
    private boolean c = true;
    private boolean d = true;
    private boolean e = true;
    private boolean f = true;
    private boolean g = true;
    private boolean h = true;
    private boolean i = true;

    public static SSWebSettings with(Context context) {
        return new SSWebSettings(context);
    }

    private SSWebSettings(Context context) {
        this.a = new WeakReference<>(context);
    }

    public SSWebSettings forPreload() {
        this.e = false;
        this.b = false;
        this.c = false;
        this.d = true;
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = true;
        return this;
    }

    public SSWebSettings enableHardwareAcceleration(boolean z) {
        this.i = z;
        return this;
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public void apply(WebView webView) {
        if (webView == null || this.a.get() == null) {
            return;
        }
        WebSettings settings = webView.getSettings();
        try {
            settings.setJavaScriptEnabled(this.b);
        } catch (Exception unused) {
        }
        if (this.c) {
            settings.setSupportZoom(true);
            settings.setBuiltInZoomControls(true);
            a.a(settings, false);
        } else {
            settings.setSupportZoom(false);
        }
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(this.d);
        settings.setDomStorageEnabled(this.f);
        settings.setAllowFileAccess(this.g);
        settings.setBlockNetworkImage(!this.h);
        if (!this.i) {
            webView.setLayerType(1, null);
        }
        b.a(webView.getSettings(), true);
        a(webView, this.e);
    }

    private void a(WebView webView, boolean z) {
        if (z) {
            return;
        }
        webView.setOnLongClickListener(null);
        webView.setLongClickable(false);
    }
}
