package com.kwad.sdk.draw.a.a;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.util.Log;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import com.kwad.sdk.R;
import com.kwad.sdk.contentalliance.detail.video.d;
import com.kwad.sdk.contentalliance.detail.video.e;
import com.kwad.sdk.core.page.widget.webview.KsAdWebView;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.webview.jshandler.b;
import com.kwad.sdk.core.webview.jshandler.f;
import com.kwad.sdk.core.webview.jshandler.i;
import com.kwad.sdk.core.webview.jshandler.j;
import com.kwad.sdk.core.webview.jshandler.k;
import com.kwad.sdk.core.webview.jshandler.p;
import com.kwad.sdk.core.webview.jshandler.s;
import com.kwad.sdk.core.webview.jshandler.t;
import com.kwad.sdk.core.webview.kwai.g;
import com.kwad.sdk.draw.a.a.a;
import com.kwad.sdk.utils.az;
import com.kwad.sdk.utils.be;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c extends com.kwad.sdk.draw.kwai.a {
    private ViewGroup b;
    private KsAdWebView c;
    private j.a d;
    private com.kwad.sdk.core.download.a.b e;
    private g f;
    private com.kwad.sdk.core.webview.a g;
    private s i;
    private AdTemplate j;
    private ValueAnimator q;
    private ValueAnimator r;
    private int h = -1;
    private d k = new e() { // from class: com.kwad.sdk.draw.a.a.c.1
        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void f() {
            super.f();
            c.this.e();
        }
    };
    private a.b l = new a.b() { // from class: com.kwad.sdk.draw.a.a.c.2
        @Override // com.kwad.sdk.draw.a.a.a.b
        public boolean a() {
            return c.this.r();
        }
    };
    private b.c m = new b.c() { // from class: com.kwad.sdk.draw.a.a.c.3
        @Override // com.kwad.sdk.core.webview.jshandler.b.c
        public void a(b.a aVar) {
            if (c.this.a.a != null) {
                c.this.a.a.onAdClicked();
            }
        }
    };
    private j.b n = new j.b() { // from class: com.kwad.sdk.draw.a.a.c.4
        @Override // com.kwad.sdk.core.webview.jshandler.j.b
        public void a(j.a aVar) {
            c.this.d = aVar;
            c.this.c.setTranslationY(aVar.a + aVar.d);
        }
    };
    private i.b o = new i.b() { // from class: com.kwad.sdk.draw.a.a.c.5
        @Override // com.kwad.sdk.core.webview.jshandler.i.b
        public void a(int i) {
            c.this.u();
        }
    };
    private p.b p = new p.b() { // from class: com.kwad.sdk.draw.a.a.c.6
        @Override // com.kwad.sdk.core.webview.jshandler.p.b
        public void a(p.a aVar) {
            c.this.h = aVar.a;
            com.kwad.sdk.core.d.a.c("DrawPlayWebCard", "updatePageStatus mPageState: " + aVar);
        }
    };

    private void a(g gVar) {
        gVar.a(new com.kwad.sdk.core.webview.jshandler.b(this.g, this.e, this.m));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.a(this.g, this.e, this.m));
        gVar.a(new f(this.g));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.g(this.g));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.d(this.g));
        gVar.a(new j(this.g, this.n));
        gVar.a(new p(this.p));
        this.i = new s();
        gVar.a(this.i);
        gVar.a(new t(this.g, this.e));
        gVar.a(new i(this.o));
        gVar.a(new k(this.g));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.h = -1;
        this.c.setVisibility(8);
        i();
    }

    private void f() {
        this.g = new com.kwad.sdk.core.webview.a();
        this.g.a(this.a.c);
        this.g.a = 0;
        this.g.b = this.a.b;
        this.g.d = this.a.b;
        this.g.e = this.c;
    }

    private void g() {
        this.h = -1;
        h();
        this.c.setBackgroundColor(0);
        this.c.getBackground().setAlpha(0);
        this.c.setVisibility(4);
        this.c.loadUrl(com.kwad.sdk.core.response.a.b.b(this.j));
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface", "JavascriptInterface"})
    private void h() {
        i();
        be.a(this.c);
        this.f = new g(this.c);
        a(this.f);
        this.c.addJavascriptInterface(this.f, "KwaiAd");
    }

    private void i() {
        if (this.f != null) {
            this.f.a();
            this.f = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean r() {
        if (this.h == 1) {
            s();
            return true;
        }
        x();
        return false;
    }

    private void s() {
        if (this.d == null) {
            t();
            return;
        }
        w();
        this.b.setVisibility(8);
        this.c.setVisibility(0);
        this.q = az.b(this.c, this.d.a + this.d.d, 0);
        this.q.setInterpolator(new DecelerateInterpolator(2.0f));
        this.q.setDuration(300L);
        this.q.addListener(new AnimatorListenerAdapter() { // from class: com.kwad.sdk.draw.a.a.c.7
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                if (c.this.i != null) {
                    c.this.i.d();
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                if (c.this.i != null) {
                    c.this.i.c();
                }
            }
        });
        this.q.start();
    }

    private void t() {
        if (this.i != null) {
            this.i.c();
        }
        this.b.setVisibility(8);
        this.c.setVisibility(0);
        if (this.i != null) {
            this.i.d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        if (this.c.getVisibility() != 0) {
            return;
        }
        if (this.d == null) {
            v();
            return;
        }
        w();
        this.r = az.b(this.c, 0, this.d.a + this.d.d);
        this.r.setInterpolator(new DecelerateInterpolator(2.0f));
        this.r.setDuration(300L);
        this.r.addListener(new AnimatorListenerAdapter() { // from class: com.kwad.sdk.draw.a.a.c.8
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                c.this.c.setVisibility(4);
                c.this.b.setVisibility(0);
                if (c.this.i != null) {
                    c.this.i.f();
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                if (c.this.i != null) {
                    c.this.i.e();
                }
            }
        });
        this.r.start();
    }

    private void v() {
        if (this.c.getVisibility() != 0) {
            return;
        }
        if (this.i != null) {
            this.i.e();
        }
        this.c.setVisibility(4);
        this.b.setVisibility(0);
        if (this.i != null) {
            this.i.f();
        }
    }

    private void w() {
        if (this.q != null) {
            this.q.removeAllListeners();
            this.q.cancel();
        }
        if (this.r != null) {
            this.r.removeAllListeners();
            this.r.cancel();
        }
    }

    private void x() {
        Log.w("DrawPlayWebCard", "show webCard fail, reason: " + (this.h == -1 ? "timeout" : this.h != 1 ? "h5error" : "others"));
    }

    @Override // com.kwad.sdk.draw.kwai.a, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.j = this.a.c;
        this.a.f.a(this.l);
        this.e = this.a.d;
        this.a.e.a(this.k);
        f();
        g();
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        this.a.f.a((a.b) null);
        this.a.e.b(this.k);
        w();
        e();
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.b = (ViewGroup) a(R.id.ksad_ad_normal_container);
        this.c = (KsAdWebView) a(R.id.ksad_play_web_card_webView);
    }
}
