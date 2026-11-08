package com.kwad.sdk.core.page.kwai;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.kwad.sdk.R;
import com.kwad.sdk.core.page.AdWebViewActivityProxy;
import com.kwad.sdk.core.webview.jshandler.b;
import com.kwad.sdk.core.webview.jshandler.f;
import com.kwad.sdk.core.webview.jshandler.k;
import com.kwad.sdk.core.webview.jshandler.o;
import com.kwad.sdk.core.webview.jshandler.p;
import com.kwad.sdk.core.webview.jshandler.s;
import com.kwad.sdk.core.webview.jshandler.t;
import com.kwad.sdk.core.webview.kwai.i;
import com.kwad.sdk.reward.g;
import com.kwad.sdk.utils.be;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d extends g {
    private com.kwad.sdk.core.webview.a b;
    private WebView c;
    private int d = -1;
    private com.kwad.sdk.core.webview.kwai.g e;
    private ViewGroup f;
    private final a g;

    public interface a {
        void a(int i);
    }

    public d(a aVar) {
        this.g = aVar;
    }

    private void a(com.kwad.sdk.core.webview.kwai.g gVar) {
        com.kwad.sdk.core.d.a.a("LandPageWebViewPresenter", "registerWebCardHandler");
        com.kwad.sdk.core.download.a.b bVar = new com.kwad.sdk.core.download.a.b(this.a.g);
        gVar.a(new f(this.b));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.g(this.b));
        gVar.a(new t(this.b, bVar));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.d(this.b));
        gVar.a(new s());
        gVar.a(new p(t()));
        gVar.a(new k(this.b));
        gVar.a(new o(u()));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.b(this.b, bVar, null));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.a(this.b, bVar, (b.c) null));
    }

    private void i() {
        this.b = new com.kwad.sdk.core.webview.a();
        this.b.a(this.a.g);
        this.b.a = this.a.f;
        this.b.d = this.f;
        this.b.e = this.c;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002a  */
    @androidx.annotation.NonNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String r() {
        /*
            r2 = this;
            android.content.Context r0 = r2.q()
            if (r0 == 0) goto L2a
            android.content.Context r0 = r2.q()
            java.io.File r0 = com.kwad.sdk.core.config.b.i(r0)
            boolean r1 = r0.exists()
            if (r1 == 0) goto L1d
            android.net.Uri r0 = android.net.Uri.fromFile(r0)
            java.lang.String r0 = r0.toString()
            goto L2b
        L1d:
            com.kwad.sdk.core.response.model.SdkConfigData$TemplateConfig r0 = com.kwad.sdk.core.config.b.e()
            if (r0 == 0) goto L2a
            com.kwad.sdk.core.response.model.SdkConfigData$TemplateConfig r0 = com.kwad.sdk.core.config.b.e()
            java.lang.String r0 = r0.h5Url
            goto L2b
        L2a:
            r0 = 0
        L2b:
            if (r0 != 0) goto L2f
            java.lang.String r0 = ""
        L2f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.core.page.kwai.d.r():java.lang.String");
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface", "JavascriptInterface"})
    private void s() {
        v();
        be.b(this.c);
        this.e = new com.kwad.sdk.core.webview.kwai.g(this.c);
        a(this.e);
        this.c.addJavascriptInterface(this.e, "KwaiAd");
    }

    private p.b t() {
        return new p.b() { // from class: com.kwad.sdk.core.page.kwai.d.1
            @Override // com.kwad.sdk.core.webview.jshandler.p.b
            public void a(p.a aVar) {
                d.this.d = aVar.a;
                if (d.this.g != null) {
                    d.this.g.a(aVar.a);
                }
                if (aVar.a == 1) {
                    d.this.f.setVisibility(0);
                } else {
                    d.this.c.setVisibility(8);
                }
            }
        };
    }

    private o.a u() {
        return new o.a() { // from class: com.kwad.sdk.core.page.kwai.d.2
            @Override // com.kwad.sdk.core.webview.jshandler.o.a
            public void a(i iVar) {
                AdWebViewActivityProxy.launch(d.this.c.getContext(), new AdWebViewActivityProxy.a.C0042a().a(iVar.b).b(iVar.a).a(d.this.a.g).a());
            }
        };
    }

    private void v() {
        if (this.e != null) {
            this.e.a();
            this.e = null;
        }
    }

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        i();
        e();
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        v();
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.c = (WebView) a(R.id.ksad_landing_page_webview);
        this.f = (ViewGroup) a(R.id.ksad_web_card_container);
        this.c.setBackgroundColor(0);
    }

    public void e() {
        s();
        if (!TextUtils.isEmpty(r())) {
            this.c.loadUrl(r());
        } else if (this.g != null) {
            this.g.a(this.d);
        }
    }
}
