package com.ss.union.game.sdk.common.webview;

import android.annotation.TargetApi;
import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class b {
    static final boolean a = false;
    static final a b;

    b() {
    }

    private static class a {
        public String a(Context context) {
            return null;
        }

        public void a(WebSettings webSettings, boolean z) {
        }

        private a() {
        }

        public long a(Location location) {
            return location.getTime();
        }

        public float a(WebView webView) {
            if (webView == null) {
                return 1.0f;
            }
            try {
                return webView.getScale();
            } catch (Exception unused) {
                return 1.0f;
            }
        }
    }

    @TargetApi(17)
    /* renamed from: com.ss.union.game.sdk.common.webview.b$b, reason: collision with other inner class name */
    private static class C0064b extends a {
        private C0064b() {
            super();
        }

        @Override // com.ss.union.game.sdk.common.webview.b.a
        public long a(Location location) {
            return location.getTime();
        }

        @Override // com.ss.union.game.sdk.common.webview.b.a
        public void a(WebSettings webSettings, boolean z) {
            try {
                webSettings.setMediaPlaybackRequiresUserGesture(z);
            } catch (Throwable unused) {
            }
        }

        @Override // com.ss.union.game.sdk.common.webview.b.a
        public float a(WebView webView) {
            if (webView == null) {
                return 1.0f;
            }
            try {
                return webView.getScale();
            } catch (Exception unused) {
                return 1.0f;
            }
        }

        @Override // com.ss.union.game.sdk.common.webview.b.a
        public String a(Context context) {
            try {
                return WebSettings.getDefaultUserAgent(context);
            } catch (Throwable unused) {
                return null;
            }
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 17) {
            b = new C0064b();
        } else {
            b = new a();
        }
    }

    public static long a(Location location) {
        return b.a(location);
    }

    public static void a(WebSettings webSettings, boolean z) {
        b.a(webSettings, z);
    }

    public static float a(WebView webView) {
        return b.a(webView);
    }

    public static String a(Context context) {
        return b.a(context);
    }
}
