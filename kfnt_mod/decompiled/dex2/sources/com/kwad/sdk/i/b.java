package com.kwad.sdk.i;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.core.page.widget.webview.KsAdWebView;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.view.AdBaseFrameLayout;
import com.kwad.sdk.core.webview.jshandler.b;
import com.kwad.sdk.core.webview.jshandler.d;
import com.kwad.sdk.core.webview.jshandler.f;
import com.kwad.sdk.core.webview.jshandler.i;
import com.kwad.sdk.core.webview.jshandler.k;
import com.kwad.sdk.core.webview.jshandler.p;
import com.kwad.sdk.core.webview.jshandler.s;
import com.kwad.sdk.core.webview.jshandler.t;
import com.kwad.sdk.core.webview.kwai.g;
import com.kwad.sdk.utils.bb;
import com.kwad.sdk.utils.be;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    protected View a;
    private AdBaseFrameLayout c;
    private FrameLayout d;
    private KsAdWebView e;

    @Nullable
    private JSONObject f;
    private int g;
    private AdTemplate h;

    @Nullable
    private com.kwad.sdk.core.download.a.b i;

    @Nullable
    private b.c j;
    private g k;
    private com.kwad.sdk.core.webview.a l;
    private s n;
    private int m = -1;
    protected boolean b = false;
    private i.b o = new i.b() { // from class: com.kwad.sdk.i.b.2
        @Override // com.kwad.sdk.core.webview.jshandler.i.b
        public void a(int i) {
            b.this.e();
        }
    };
    private p.b p = new p.b() { // from class: com.kwad.sdk.i.b.3
        @Override // com.kwad.sdk.core.webview.jshandler.p.b
        public void a(p.a aVar) {
            b.this.m = aVar.a;
            com.kwad.sdk.core.d.a.c("PlayEndWebCard", "updatePageStatus mPageState: " + aVar);
        }
    };

    public b() {
    }

    public b(@Nullable JSONObject jSONObject) {
        this.f = jSONObject;
    }

    private void a(g gVar) {
        gVar.a(new com.kwad.sdk.core.webview.jshandler.b(this.l, this.i, this.j));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.a(this.l, this.i, this.j));
        gVar.a(new f(this.l));
        gVar.a(new com.kwad.sdk.core.webview.a.g());
        gVar.a(new com.kwad.sdk.core.webview.jshandler.g(this.l));
        gVar.a(new d(this.l));
        gVar.a(new p(this.p));
        this.n = new s();
        gVar.a(this.n);
        gVar.a(new t(this.l, this.i));
        gVar.a(new i(this.o));
        gVar.a(new k(this.l));
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void h() {
        this.d.removeAllViews();
        this.d.setVisibility(4);
        this.a = com.kwad.sdk.a.kwai.a.a((ViewGroup) this.d, a(), true);
        this.e = (KsAdWebView) this.d.findViewById(R.id.ksad_web_card_webView);
        this.e.setBackgroundColor(0);
        this.e.getBackground().setAlpha(0);
        this.e.setHttpErrorListener(new KsAdWebView.d() { // from class: com.kwad.sdk.i.b.1
            @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
            public void a() {
            }

            @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
            public void a(int i, String str, String str2) {
                b.this.b = false;
            }

            @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
            public void b() {
                b.this.b = true;
            }
        });
    }

    private void i() {
        this.l = new com.kwad.sdk.core.webview.a();
        this.l.a(this.h);
        this.l.a = this.g;
        this.l.b = this.c;
        this.l.d = this.d;
        this.l.e = this.e;
        this.l.c = this.f;
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface", "JavascriptInterface"})
    private void j() {
        k();
        WebSettings webSettingsA = be.a(this.e);
        webSettingsA.setAllowContentAccess(false);
        webSettingsA.setAllowFileAccess(true);
        this.k = new g(this.e);
        a(this.k);
        this.e.addJavascriptInterface(this.k, "KwaiAd");
    }

    private void k() {
        if (this.k != null) {
            this.k.a();
            this.k = null;
        }
    }

    private void l() {
        Log.w("PlayEndWebCard", "show webCard fail, reason: " + (this.m == -1 ? "timeout" : this.m != 1 ? "h5error" : "others"));
    }

    protected int a() {
        return R.layout.ksad_ad_web_card_layout;
    }

    protected String a(AdTemplate adTemplate) {
        return com.kwad.sdk.core.response.a.b.g(this.h);
    }

    public void a(FrameLayout frameLayout, AdBaseFrameLayout adBaseFrameLayout, AdTemplate adTemplate, @Nullable com.kwad.sdk.core.download.a.b bVar) {
        a(frameLayout, adBaseFrameLayout, adTemplate, bVar, 0);
    }

    public void a(FrameLayout frameLayout, AdBaseFrameLayout adBaseFrameLayout, AdTemplate adTemplate, com.kwad.sdk.core.download.a.b bVar, int i) {
        this.i = bVar;
        this.c = adBaseFrameLayout;
        this.d = frameLayout;
        this.g = i;
        this.h = adTemplate;
        h();
        i();
    }

    public void a(b.c cVar) {
        this.j = cVar;
    }

    public void b() {
        this.d.setVisibility(4);
        this.m = -1;
        String strA = a(this.h);
        if (TextUtils.isEmpty(strA)) {
            return;
        }
        j();
        this.e.loadUrl(strA);
    }

    protected boolean c() {
        return this.m == 1;
    }

    public boolean d() {
        if (!c()) {
            if (this.d != null) {
                this.d.setVisibility(4);
            }
            l();
            return false;
        }
        if (this.n != null) {
            this.n.c();
        }
        if (this.d != null) {
            this.d.setVisibility(0);
        }
        if (this.n == null) {
            return true;
        }
        this.n.d();
        return true;
    }

    public void e() {
        if (bb.a(this.e, 50, false)) {
            if (this.n != null) {
                this.n.e();
            }
            this.d.setVisibility(4);
            if (this.n != null) {
                this.n.f();
            }
        }
    }

    public void f() {
        k();
    }

    public void g() {
        k();
    }
}
