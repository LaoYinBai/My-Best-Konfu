package com.kwad.sdk.reward.presenter.platdetail.actionbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.SystemClock;
import android.util.Log;
import android.view.animation.DecelerateInterpolator;
import com.kwad.sdk.R;
import com.kwad.sdk.contentalliance.detail.video.e;
import com.kwad.sdk.core.page.widget.webview.KsAdWebView;
import com.kwad.sdk.core.webview.jshandler.b;
import com.kwad.sdk.core.webview.jshandler.i;
import com.kwad.sdk.core.webview.jshandler.j;
import com.kwad.sdk.core.webview.jshandler.k;
import com.kwad.sdk.core.webview.jshandler.p;
import com.kwad.sdk.core.webview.jshandler.s;
import com.kwad.sdk.core.webview.jshandler.t;
import com.kwad.sdk.reward.a.f;
import com.kwad.sdk.reward.g;
import com.kwad.sdk.reward.presenter.platdetail.actionbar.RewardActionBarControl;
import com.kwad.sdk.utils.az;
import com.kwad.sdk.utils.be;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends g {
    private KsAdWebView b;
    private j.a c;
    private com.kwad.sdk.core.download.a.b d;
    private RewardActionBarControl e;
    private boolean f;
    private com.kwad.sdk.core.webview.kwai.g g;
    private com.kwad.sdk.core.webview.a h;
    private s j;
    private boolean k;
    private long p;
    private ValueAnimator t;
    private ValueAnimator u;
    private int i = -1;
    private RewardActionBarControl.d l = new RewardActionBarControl.d() { // from class: com.kwad.sdk.reward.presenter.platdetail.actionbar.b.1
        @Override // com.kwad.sdk.reward.presenter.platdetail.actionbar.RewardActionBarControl.d
        public boolean a() {
            b.this.k = b.this.u();
            return b.this.k;
        }
    };
    private f m = new f() { // from class: com.kwad.sdk.reward.presenter.platdetail.actionbar.b.2
        @Override // com.kwad.sdk.reward.a.f
        public void a() {
            b.this.e();
        }
    };
    private com.kwad.sdk.contentalliance.detail.video.d n = new e() { // from class: com.kwad.sdk.reward.presenter.platdetail.actionbar.b.3
        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void c() {
            super.c();
            b.this.f = true;
        }
    };
    private b.c o = new b.c() { // from class: com.kwad.sdk.reward.presenter.platdetail.actionbar.b.4
        @Override // com.kwad.sdk.core.webview.jshandler.b.c
        public void a(b.a aVar) {
            b.this.a.b.a();
        }
    };
    private j.b q = new j.b() { // from class: com.kwad.sdk.reward.presenter.platdetail.actionbar.b.5
        @Override // com.kwad.sdk.core.webview.jshandler.j.b
        public void a(j.a aVar) {
            b.this.c = aVar;
            b.this.b.setTranslationY(aVar.a + aVar.d);
        }
    };
    private i.b r = new i.b() { // from class: com.kwad.sdk.reward.presenter.platdetail.actionbar.b.6
        @Override // com.kwad.sdk.core.webview.jshandler.i.b
        public void a(int i) {
            b.this.k = false;
            b.this.x();
        }
    };
    private p.b s = new p.b() { // from class: com.kwad.sdk.reward.presenter.platdetail.actionbar.b.7
        @Override // com.kwad.sdk.core.webview.jshandler.p.b
        public void a(p.a aVar) {
            b.this.i = aVar.a;
            long jElapsedRealtime = SystemClock.elapsedRealtime() - b.this.p;
            com.kwad.sdk.core.d.a.c("RewardActionBarWeb", "load time:" + jElapsedRealtime);
            if (b.this.i == 1) {
                com.kwad.sdk.core.report.d.b(b.this.a.g, jElapsedRealtime);
            }
            if (b.this.f) {
                b.this.e.b(b.this.a.k.f(), b.this.a.k.g());
            }
        }
    };

    private void A() {
        Log.w("RewardActionBarWeb", "show webCard fail, reason: " + (this.i == -1 ? "timeout" : this.i != 1 ? "h5error" : "others"));
    }

    private void a(com.kwad.sdk.core.webview.kwai.g gVar) {
        gVar.a(new com.kwad.sdk.core.webview.jshandler.b(this.h, this.d, this.o));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.a(this.h, this.d, this.o));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.f(this.h));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.g(this.h));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.d(this.h));
        gVar.a(new j(this.h, this.q));
        gVar.a(new p(this.s));
        this.j = new s();
        gVar.a(this.j);
        gVar.a(new t(this.h, this.d));
        gVar.a(new i(this.r));
        gVar.a(new k(this.h));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.i = -1;
        this.b.setVisibility(8);
        t();
    }

    private void i() {
        this.h = new com.kwad.sdk.core.webview.a();
        this.h.a(this.a.g);
        this.h.a = this.a.f;
        this.h.b = this.a.j;
        this.h.d = this.a.j;
        this.h.e = this.b;
    }

    private void r() {
        this.i = -1;
        s();
        this.b.setBackgroundColor(0);
        this.b.getBackground().setAlpha(0);
        this.b.setVisibility(4);
        this.p = SystemClock.elapsedRealtime();
        this.b.loadUrl(com.kwad.sdk.core.response.a.b.b(this.a.g));
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface", "JavascriptInterface"})
    private void s() {
        t();
        be.a(this.b);
        this.g = new com.kwad.sdk.core.webview.kwai.g(this.b);
        a(this.g);
        this.b.addJavascriptInterface(this.g, "KwaiAd");
    }

    private void t() {
        if (this.g != null) {
            this.g.a();
            this.g = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean u() {
        if (this.b == null) {
            return false;
        }
        if (this.b.getVisibility() == 0) {
            return true;
        }
        if (this.i == 1) {
            v();
            return true;
        }
        A();
        return false;
    }

    private void v() {
        if (this.c == null) {
            w();
            return;
        }
        z();
        this.b.setVisibility(0);
        this.t = az.b(this.b, this.c.a + this.c.d, 0);
        this.t.setInterpolator(new DecelerateInterpolator(2.0f));
        this.t.setDuration(300L);
        this.t.addListener(new AnimatorListenerAdapter() { // from class: com.kwad.sdk.reward.presenter.platdetail.actionbar.b.8
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                if (b.this.j != null) {
                    b.this.j.d();
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                if (b.this.j != null) {
                    b.this.j.c();
                }
            }
        });
        this.t.start();
    }

    private void w() {
        if (this.j != null) {
            this.j.c();
        }
        this.b.setVisibility(0);
        if (this.j != null) {
            this.j.d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        if (this.b.getVisibility() != 0) {
            return;
        }
        if (this.c == null) {
            y();
            return;
        }
        z();
        this.u = az.b(this.b, 0, this.c.a + this.c.d);
        this.u.setInterpolator(new DecelerateInterpolator(2.0f));
        this.u.setDuration(300L);
        this.u.addListener(new AnimatorListenerAdapter() { // from class: com.kwad.sdk.reward.presenter.platdetail.actionbar.b.9
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                b.this.b.setVisibility(4);
                if (b.this.j != null) {
                    b.this.j.f();
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                if (b.this.j != null) {
                    b.this.j.e();
                }
            }
        });
        this.u.start();
    }

    private void y() {
        if (this.b.getVisibility() != 0) {
            return;
        }
        if (this.j != null) {
            this.j.e();
        }
        this.b.setVisibility(4);
        if (this.j != null) {
            this.j.f();
        }
    }

    private void z() {
        if (this.t != null) {
            this.t.removeAllListeners();
            this.t.cancel();
        }
        if (this.u != null) {
            this.u.removeAllListeners();
            this.u.cancel();
        }
    }

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.d = this.a.l;
        this.e = this.a.n;
        this.e.a(this.l);
        i();
        r();
        this.a.a(this.m);
        this.a.k.a(this.n);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        this.e.a((RewardActionBarControl.d) null);
        this.a.b(this.m);
        this.a.k.b(this.n);
        z();
        e();
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.b = (KsAdWebView) a(R.id.ksad_play_web_card_webView);
    }
}
