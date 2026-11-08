package com.ss.sys.ck;

import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;
import java.lang.ref.WeakReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d {
    static {
        new Handler(Looper.getMainLooper());
    }

    public d(WebView webView, String str) {
        new WeakReference(webView);
    }
}
