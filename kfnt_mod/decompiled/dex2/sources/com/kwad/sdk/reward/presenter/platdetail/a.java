package com.kwad.sdk.reward.presenter.platdetail;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.kwad.sdk.R;
import com.kwad.sdk.contentalliance.detail.video.d;
import com.kwad.sdk.contentalliance.detail.video.e;
import com.kwad.sdk.core.page.widget.webview.KsAdWebView;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.webview.jshandler.b;
import com.kwad.sdk.core.webview.jshandler.i;
import com.kwad.sdk.core.webview.jshandler.j;
import com.kwad.sdk.core.webview.jshandler.k;
import com.kwad.sdk.core.webview.jshandler.p;
import com.kwad.sdk.core.webview.jshandler.s;
import com.kwad.sdk.core.webview.jshandler.t;
import com.kwad.sdk.reward.a.f;
import com.kwad.sdk.reward.g;
import com.kwad.sdk.reward.widget.actionbar.ActionBarLandscapeVertical;
import com.kwad.sdk.reward.widget.actionbar.ActionBarPortraitHorizontal;
import com.kwad.sdk.utils.av;
import com.kwad.sdk.utils.bd;
import com.kwad.sdk.utils.be;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends g {
    private ActionBarLandscapeVertical b;
    private ActionBarPortraitHorizontal c;
    private s d;
    private AdTemplate e;
    private com.kwad.sdk.core.download.a.b f;
    private Handler g;
    private long h;
    private KsAdWebView i;
    private com.kwad.sdk.core.webview.kwai.g j;
    private com.kwad.sdk.core.webview.a k;
    private boolean l;
    private boolean m;
    private long r;
    private KsAdWebView.d n = new KsAdWebView.d() { // from class: com.kwad.sdk.reward.presenter.platdetail.a.1
        @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
        public void a() {
            a.this.g.removeCallbacksAndMessages(null);
            a.this.g.postDelayed(a.this.t, a.this.h);
        }

        @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
        public void a(int i, String str, String str2) {
            a.this.g.removeCallbacksAndMessages(null);
            a.this.s.run();
        }

        @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
        public void b() {
        }
    };
    private d o = new e() { // from class: com.kwad.sdk.reward.presenter.platdetail.a.3
        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void c() {
            super.c();
            if (a.this.m) {
                a.this.i.setVisibility(4);
                a.this.a(a.this.a.k.f(), a.this.a.k.g());
            }
            a.this.l = true;
        }
    };
    private f p = new f() { // from class: com.kwad.sdk.reward.presenter.platdetail.a.4
        @Override // com.kwad.sdk.reward.a.f
        public void a() {
            a.this.l = false;
            a.this.e();
            if (a.this.m) {
                a.this.i();
            }
        }
    };
    private b.c q = new b.c() { // from class: com.kwad.sdk.reward.presenter.platdetail.a.7
        @Override // com.kwad.sdk.core.webview.jshandler.b.c
        public void a(b.a aVar) {
            com.kwad.sdk.core.d.a.a("NewStylePresenter", "onAdClicked");
            a.this.a.b.a();
        }
    };
    private Runnable s = new Runnable() { // from class: com.kwad.sdk.reward.presenter.platdetail.a.8
        @Override // java.lang.Runnable
        public void run() {
            a.this.m = true;
            a.this.i.setVisibility(4);
            if (a.this.l) {
                a.this.a(a.this.a.k.f(), a.this.a.k.g());
            }
        }
    };
    private bd t = new bd(this.s);
    private j.b u = new j.b() { // from class: com.kwad.sdk.reward.presenter.platdetail.a.9
        @Override // com.kwad.sdk.core.webview.jshandler.j.b
        public void a(j.a aVar) {
        }
    };
    private i.b v = new i.b() { // from class: com.kwad.sdk.reward.presenter.platdetail.a.10
        @Override // com.kwad.sdk.core.webview.jshandler.i.b
        public void a(int i) {
            a.this.d.e();
            a.this.i.setVisibility(4);
            a.this.d.f();
        }
    };
    private p.b w = new p.b() { // from class: com.kwad.sdk.reward.presenter.platdetail.a.2
        @Override // com.kwad.sdk.core.webview.jshandler.p.b
        public void a(p.a aVar) {
            com.kwad.sdk.core.d.a.c("NewStylePresenter", "load time:" + (System.currentTimeMillis() - a.this.r));
            a.this.g.removeCallbacksAndMessages(null);
            if (aVar.a != 1) {
                com.kwad.sdk.core.d.a.a("NewStylePresenter", "show webCard fail, reason: timeout");
                a.this.s.run();
            } else {
                a.this.i();
                a.this.i.setVisibility(0);
                a.this.d.d();
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2) {
        int iMin = Math.min(av.o(q()), av.n(q()));
        if (this.a.f == 1) {
            if (i <= i2) {
                b((int) ((i / (i2 * 1.0f)) * iMin));
            }
        } else if (i >= i2) {
            c((int) ((i2 / (i * 1.0f)) * iMin));
        }
    }

    private void a(com.kwad.sdk.core.webview.kwai.g gVar) {
        gVar.a(new com.kwad.sdk.core.webview.jshandler.b(this.k, this.f, this.q));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.a(this.k, this.f, this.q));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.f(this.k));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.g(this.k));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.d(this.k));
        gVar.a(new j(this.k, this.u));
        gVar.a(new p(this.w));
        gVar.a(this.d);
        gVar.a(new t(this.k, this.f));
        gVar.a(new i(this.v));
        gVar.a(new k(this.k));
    }

    private void b(int i) {
        this.b.a(this.e, this.f, new ActionBarLandscapeVertical.a() { // from class: com.kwad.sdk.reward.presenter.platdetail.a.5
            @Override // com.kwad.sdk.reward.widget.actionbar.ActionBarLandscapeVertical.a
            public void a(boolean z) {
                a.this.b(z);
            }
        }, i);
        this.b.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        com.kwad.sdk.core.report.a.a(this.e, z ? 1 : 153, this.a.j.getTouchCoords(), this.a.e);
        this.a.b.a();
    }

    private void c(int i) {
        this.c.a(this.e, this.f, new ActionBarPortraitHorizontal.a() { // from class: com.kwad.sdk.reward.presenter.platdetail.a.6
            @Override // com.kwad.sdk.reward.widget.actionbar.ActionBarPortraitHorizontal.a
            public void a(boolean z) {
                a.this.b(z);
            }
        }, i);
        this.c.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.d.b();
        this.i.setVisibility(8);
        this.i.setHttpErrorListener(null);
        u();
        if (this.g != null) {
            this.g.removeCallbacksAndMessages(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        this.b.setVisibility(8);
        this.c.setVisibility(8);
    }

    private void r() {
        this.k = new com.kwad.sdk.core.webview.a();
        this.k.a(this.a.g);
        this.k.a = this.a.f;
        this.k.b = this.a.j;
        this.k.d = this.a.j;
        this.k.e = this.i;
    }

    private void s() {
        t();
        this.r = System.currentTimeMillis();
        if (TextUtils.isEmpty(com.kwad.sdk.core.response.a.b.e(this.e))) {
            this.s.run();
            return;
        }
        this.d.c();
        this.i.setVisibility(4);
        this.i.loadUrl(com.kwad.sdk.core.response.a.b.e(this.e));
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface", "JavascriptInterface"})
    private void t() {
        u();
        be.a(this.i);
        this.j = new com.kwad.sdk.core.webview.kwai.g(this.i);
        a(this.j);
        this.i.addJavascriptInterface(this.j, "KwaiAd");
    }

    private void u() {
        if (this.j != null) {
            this.j.a();
            this.j = null;
        }
    }

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.e = this.a.g;
        this.f = this.a.l;
        long jF = com.kwad.sdk.core.response.a.b.f(this.e);
        if (jF <= 0) {
            jF = 1000;
        }
        this.h = jF;
        this.i.setHttpErrorListener(this.n);
        r();
        s();
        this.a.a(this.p);
        this.a.k.a(this.o);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        this.a.b(this.p);
        e();
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.i = (KsAdWebView) a(R.id.ksad_actionbar_black_style_h5);
        this.i.setBackgroundColor(0);
        this.i.getBackground().setAlpha(0);
        this.b = (ActionBarLandscapeVertical) a(R.id.ksad_actionbar_landscape_vertical);
        this.c = (ActionBarPortraitHorizontal) a(R.id.ksad_actionbar_portrait_horizontal);
        this.d = new s();
        this.g = new Handler(Looper.getMainLooper());
    }
}
