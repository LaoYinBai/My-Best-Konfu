package com.kwad.sdk.core.page.kwai;

import android.annotation.SuppressLint;
import com.kwad.sdk.R;
import com.kwad.sdk.core.page.recycle.NestedScrollWebView;
import com.kwad.sdk.core.page.recycle.e;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.webview.jshandler.f;
import com.kwad.sdk.core.webview.jshandler.k;
import com.kwad.sdk.core.webview.jshandler.p;
import com.kwad.sdk.core.webview.kwai.g;
import com.kwad.sdk.mvp.Presenter;
import com.kwad.sdk.utils.be;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends Presenter {
    private g a;
    private com.kwad.sdk.core.webview.a b;
    private NestedScrollWebView c;
    private AdTemplate d;
    private p.b e = new p.b() { // from class: com.kwad.sdk.core.page.kwai.b.1
        @Override // com.kwad.sdk.core.webview.jshandler.p.b
        public void a(p.a aVar) {
        }
    };

    private void a(g gVar) {
        gVar.a(new f(this.b));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.g(this.b));
        gVar.a(new p(this.e));
        gVar.a(new k(this.b));
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface", "JavascriptInterface"})
    private void e() {
        f();
        be.a(this.c);
        this.a = new g(this.c);
        a(this.a);
        this.c.addJavascriptInterface(this.a, "KwaiAd");
    }

    private void f() {
        if (this.a != null) {
            this.a.a();
            this.a = null;
        }
    }

    private void g() {
        this.b = new com.kwad.sdk.core.webview.a();
        this.b.a(this.d);
        this.b.a = 0;
        this.b.e = this.c;
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.d = ((e) p()).c;
        this.c = (NestedScrollWebView) m().findViewById(R.id.ksad_video_webView);
        this.c.setTemplateData(this.d);
        this.c.setNestedScrollingEnabled(true);
        g();
        e();
        this.c.loadUrl(com.kwad.sdk.core.response.a.a.G(com.kwad.sdk.core.response.a.d.j(this.d)));
        this.c.b();
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void d_() {
        super.d_();
        f();
        if (this.c != null) {
            this.c.c();
            this.c = null;
        }
    }
}
