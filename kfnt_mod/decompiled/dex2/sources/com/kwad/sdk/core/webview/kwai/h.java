package com.kwad.sdk.core.webview.kwai;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.kwad.sdk.api.KsAppDownloadListener;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.utils.bf;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class h {
    private WebView a;
    private AdTemplate b;
    private com.kwad.sdk.core.webview.a c;
    private KsAppDownloadListener d;
    private boolean e;
    private com.kwad.sdk.core.download.a.b f;
    private int g = -100;
    private int h = 0;

    public static final class a extends com.kwad.sdk.core.response.kwai.a {
        public String a;
        public String b;
    }

    public static final class b extends com.kwad.sdk.core.response.kwai.a {
        public int a;
        public int b;
    }

    public h(WebView webView, com.kwad.sdk.core.webview.a aVar) {
        this.a = webView;
        this.b = aVar.a();
        this.c = aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2) {
        this.h = i2;
        if (this.g != i) {
            this.g = i;
            c("onDownLoadStatusCallback", i + "");
        }
    }

    private void a(String str, String str2) {
        this.f = new com.kwad.sdk.core.download.a.b(this.b, null, str, str2);
        if (this.d == null) {
            this.d = b();
            this.f.a(this.d);
        }
    }

    private KsAppDownloadListener b() {
        return new com.kwad.sdk.core.download.a.c() { // from class: com.kwad.sdk.core.webview.kwai.h.1
            @Override // com.kwad.sdk.core.download.a.c
            public void a(int i) {
                h.this.a(4, i);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onDownloadFailed() {
                h.this.a(0, 0);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onDownloadFinished() {
                h.this.a(8, 100);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onIdle() {
                h.this.a(0, 0);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onInstalled() {
                h.this.a(12, 100);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onProgressUpdate(int i) {
                if (i == 0) {
                    h.this.a(1, 0);
                } else {
                    h.this.a(2, i);
                }
            }
        };
    }

    private boolean b(String str, String str2) {
        return TextUtils.isEmpty(str) || TextUtils.isEmpty(str2);
    }

    private void c(String str, String str2) {
        WebView webView;
        if (this.e || TextUtils.isEmpty(str) || (webView = this.a) == null) {
            return;
        }
        bf.a(webView, str, str2);
    }

    public void a() {
        this.e = true;
        if (this.f != null) {
            this.f.b(this.d);
        }
    }

    @JavascriptInterface
    public int getDownLoadStatus(String str, String str2) {
        if (b(str, str2)) {
            return 0;
        }
        if (this.f == null) {
            a(str, str2);
        }
        return this.f.c();
    }

    @JavascriptInterface
    public int getProgress(String str, String str2) {
        if (b(str, str2)) {
            return 0;
        }
        if (this.f == null) {
            a(str, str2);
        }
        return this.h;
    }

    @JavascriptInterface
    public void handleAdClick(String str) {
        try {
            a aVar = new a();
            aVar.parseJson(new JSONObject(str));
            if (b(aVar.a, aVar.b)) {
                return;
            }
            if (this.f == null) {
                a(aVar.a, aVar.b);
            }
            if (this.f.f()) {
                return;
            }
            this.f.c(this.d);
            this.f.a(this.c.e.getContext(), true);
        } catch (JSONException e) {
            com.kwad.sdk.core.d.a.a(e);
        }
    }
}
