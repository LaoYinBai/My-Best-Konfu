package com.ss.union.game.sdk.core.browser;

import android.app.Activity;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.core.util.LogDevKitUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class e {
    private WebView a;
    private b b;
    private List<com.ss.union.game.sdk.core.browser.a> c;

    private e(WebView webView, b bVar, List<com.ss.union.game.sdk.core.browser.a> list) {
        this.a = webView;
        this.b = bVar;
        this.c = list;
    }

    public JSInterface a() {
        if (this.a == null) {
            LogDevKitUtils.logBrowser("初始化JSInit失败，webView == null");
            return null;
        }
        Iterator<com.ss.union.game.sdk.core.browser.a> it = this.c.iterator();
        while (it.hasNext()) {
            it.next().a(this.b);
        }
        JSInterface jSInterface = new JSInterface(this.b, this.c);
        this.a.addJavascriptInterface(jSInterface, "game_js_obj");
        return jSInterface;
    }

    public static class a {
        private WebView a;
        private BaseFragment b;
        private Activity c;
        private List<com.ss.union.game.sdk.core.browser.a> d = new ArrayList();

        private void c() {
        }

        public a(@NonNull WebView webView) {
            this.a = webView;
            c();
        }

        public a a(BaseFragment baseFragment) {
            this.b = baseFragment;
            return this;
        }

        public a a(Activity activity) {
            this.c = activity;
            return this;
        }

        public a a() {
            this.d.add(d.a());
            return this;
        }

        public a a(com.ss.union.game.sdk.core.browser.a aVar) {
            if (aVar != null) {
                this.d.add(aVar);
            }
            return this;
        }

        public e b() {
            if (this.b == null && this.c == null) {
                throw new RuntimeException("JSInit失败,必须要有view");
            }
            b bVarA = this.b != null ? g.a(this.b) : null;
            if (this.c != null) {
                bVarA = g.a(this.c);
            }
            return new e(this.a, bVarA, this.d);
        }
    }
}
