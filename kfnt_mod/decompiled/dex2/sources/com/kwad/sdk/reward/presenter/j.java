package com.kwad.sdk.reward.presenter;

import android.annotation.SuppressLint;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.core.page.AdWebViewActivityProxy;
import com.kwad.sdk.core.page.widget.webview.KsAdWebView;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.webview.jshandler.b;
import com.kwad.sdk.core.webview.jshandler.m;
import com.kwad.sdk.core.webview.jshandler.o;
import com.kwad.sdk.core.webview.jshandler.p;
import com.kwad.sdk.core.webview.jshandler.s;
import com.kwad.sdk.internal.api.SceneImpl;
import com.kwad.sdk.reflux.KsRefluxView;
import com.kwad.sdk.reflux.a;
import com.kwad.sdk.utils.at;
import com.kwad.sdk.utils.be;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class j extends com.kwad.sdk.reward.g implements KsRefluxView.a, a.InterfaceC0066a {
    private List<AdTemplate> b;
    private KsRefluxView d;
    private KsAdWebView e;
    private com.kwad.sdk.core.webview.kwai.g f;
    private com.kwad.sdk.core.webview.a g;
    private s i;
    private List<com.kwad.sdk.core.download.a.b> c = new ArrayList();
    private int h = -1;
    private p.b j = new p.b() { // from class: com.kwad.sdk.reward.presenter.j.2
        @Override // com.kwad.sdk.core.webview.jshandler.p.b
        public void a(p.a aVar) {
            j.this.h = aVar.a;
            if (j.this.h == 1) {
                j.this.d.a(true);
            }
        }
    };
    private KsAdWebView.d k = new KsAdWebView.d() { // from class: com.kwad.sdk.reward.presenter.j.3
        @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
        public void a() {
        }

        @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
        public void a(int i, String str, String str2) {
            j.this.t();
        }

        @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
        public void b() {
        }
    };

    private void a(com.kwad.sdk.core.webview.kwai.g gVar) {
        this.i = new s();
        gVar.a(this.i);
        gVar.a(new com.kwad.sdk.core.webview.jshandler.a(this.g, this.c, (b.c) null));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.f(this.g));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.g(this.g));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.d(this.g));
        gVar.a(new p(this.j));
        gVar.a(new m(this.b, this.c));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.i(null));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.k(this.g));
        gVar.a(new o(new o.a() { // from class: com.kwad.sdk.reward.presenter.j.1
            @Override // com.kwad.sdk.core.webview.jshandler.o.a
            public void a(com.kwad.sdk.core.webview.kwai.i iVar) {
                if (iVar.a() <= 0 || j.this.b == null) {
                    return;
                }
                AdTemplate adTemplate = null;
                Iterator it = j.this.b.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    AdTemplate adTemplate2 = (AdTemplate) it.next();
                    if (com.kwad.sdk.core.response.a.d.y(adTemplate2) == iVar.a()) {
                        adTemplate = adTemplate2;
                        break;
                    }
                }
                if (adTemplate != null) {
                    AdWebViewActivityProxy.launch(j.this.q(), new AdWebViewActivityProxy.a.C0042a().a(iVar.b).b(iVar.a).a(adTemplate).a());
                }
            }
        }));
    }

    private void s() {
        SceneImpl sceneImplM2clone = this.a.g.mAdScene != null ? this.a.g.mAdScene.m2clone() : null;
        if (sceneImplM2clone != null) {
            com.kwad.sdk.reflux.a.a(sceneImplM2clone, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        this.d.getNativeView().a(com.kwad.sdk.reflux.b.a(this.b, this.c));
        this.d.a(false);
    }

    private void u() {
        this.g = new com.kwad.sdk.core.webview.a();
        this.g.a(this.b);
        this.g.a = this.a.f;
        this.g.b = this.a.j;
        this.g.d = this.a.j;
        this.g.e = this.e;
    }

    private void v() {
        this.h = -1;
        x();
        this.d.a(false);
        String strW = w();
        com.kwad.sdk.core.d.a.a("RewardRefluxPresenter", "startPreloadWebView url : " + strW);
        if (at.a(strW)) {
            t();
        } else {
            this.e.loadUrl(strW);
        }
    }

    @Nullable
    private String w() {
        return com.kwad.sdk.core.config.b.a(q());
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface", "JavascriptInterface"})
    private void x() {
        y();
        be.a(this.e);
        this.f = new com.kwad.sdk.core.webview.kwai.g(this.e);
        a(this.f);
        this.e.addJavascriptInterface(this.f, "KwaiAd");
    }

    private void y() {
        if (this.f != null) {
            this.f.a();
            this.f = null;
        }
    }

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        if (this.e != null) {
            this.e.setHttpErrorListener(this.k);
        }
        s();
    }

    @Override // com.kwad.sdk.reflux.a.InterfaceC0066a
    public void a(int i, String str) {
    }

    @Override // com.kwad.sdk.reflux.a.InterfaceC0066a
    public void a(@Nullable List<AdTemplate> list) {
        com.kwad.sdk.core.d.a.a("RewardRefluxPresenter", "onRefluxAdLoad :" + list.size());
        this.b = list;
        if (list.size() > 0) {
            this.c.clear();
            Iterator<AdTemplate> it = this.b.iterator();
            while (it.hasNext()) {
                com.kwad.sdk.core.download.a.b bVar = new com.kwad.sdk.core.download.a.b(it.next());
                com.kwad.sdk.core.d.a.a("RewardRefluxPresenter", "onRefluxAdLoad helper: " + bVar.a());
                this.c.add(bVar);
            }
            if (this.e == null) {
                t();
            } else {
                u();
                v();
            }
        }
    }

    @Override // com.kwad.sdk.reflux.a.InterfaceC0066a
    public void a_(int i) {
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.d = (KsRefluxView) a(R.id.ksad_reward_reflux);
        this.e = this.d.getWebView();
        this.d.setViewListener(this);
    }

    @Override // com.kwad.sdk.reflux.KsRefluxView.a
    public void d() {
        this.a.b.a(false);
        h();
    }

    @Override // com.kwad.sdk.reflux.KsRefluxView.a
    public void e() {
        this.a.k.c();
    }

    public int i() {
        if (this.d.isShown()) {
            return 3;
        }
        if (!this.a.v || this.b == null || this.b.size() <= 0) {
            return 1;
        }
        if (this.i != null) {
            this.i.c();
        }
        this.d.c();
        if (this.i == null) {
            return 2;
        }
        this.i.d();
        return 2;
    }

    public boolean r() {
        return this.d != null && this.d.getVisibility() == 0;
    }
}
