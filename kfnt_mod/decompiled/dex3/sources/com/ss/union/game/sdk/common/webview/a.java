package com.ss.union.game.sdk.common.webview;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class a {
    static Method a;
    static Method b;
    static boolean c;

    a() {
    }

    @TargetApi(11)
    /* renamed from: com.ss.union.game.sdk.common.webview.a$a, reason: collision with other inner class name */
    static class C0063a {
        C0063a() {
        }

        public static Context a(AlertDialog.Builder builder) {
            return builder.getContext();
        }

        public static void a(WebSettings webSettings, boolean z) {
            webSettings.setDisplayZoomControls(z);
        }

        public static void a(WebView webView) {
            webView.onPause();
        }

        public static void b(WebView webView) {
            webView.onResume();
        }
    }

    public static Context a(Context context, AlertDialog.Builder builder) {
        return Build.VERSION.SDK_INT >= 11 ? C0063a.a(builder) : context;
    }

    public static void a(WebSettings webSettings, boolean z) {
        if (Build.VERSION.SDK_INT >= 11) {
            C0063a.a(webSettings, z);
        }
    }

    public static void a(WebView webView) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (webView == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 11) {
            C0063a.a(webView);
            return;
        }
        a();
        if (a != null) {
            try {
                a.invoke(webView, (Object[]) null);
            } catch (Exception unused) {
            }
        }
    }

    public static void b(WebView webView) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (webView == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 11) {
            C0063a.b(webView);
            return;
        }
        a();
        if (b != null) {
            try {
                b.invoke(webView, (Object[]) null);
            } catch (Exception unused) {
            }
        }
    }

    private static void a() {
        if (c) {
            return;
        }
        c = true;
        try {
            a = WebView.class.getMethod("onPause", (Class[]) null);
            b = WebView.class.getMethod("onResume", (Class[]) null);
        } catch (Exception unused) {
        }
    }
}
