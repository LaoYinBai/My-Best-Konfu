package com.alipay.sdk.widget;

import android.app.Activity;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.widget.FrameLayout;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class c extends FrameLayout {

    /* renamed from: b, reason: collision with root package name */
    public static final String f2681b = "v1";

    /* renamed from: c, reason: collision with root package name */
    public static final String f2682c = "v2";

    /* renamed from: a, reason: collision with root package name */
    protected Activity f2683a;
    private final String d;

    public abstract void a();

    public abstract void a(String str);

    public abstract boolean b();

    public c(Activity activity, String str) {
        super(activity);
        this.f2683a = activity;
        this.d = str;
    }

    public void a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        CookieSyncManager.createInstance(this.f2683a.getApplicationContext()).sync();
        CookieManager.getInstance().setCookie(str, str2);
        CookieSyncManager.getInstance().sync();
    }

    public boolean c() {
        return f2681b.equals(this.d);
    }

    static void a(WebView webView) {
        if (webView != null) {
            try {
                webView.resumeTimers();
            } catch (Throwable unused) {
            }
        }
    }
}
