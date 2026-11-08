package com.kwad.sdk.reward.presenter.c;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.core.page.widget.webview.KsAdWebView;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.view.AdBaseFrameLayout;
import com.kwad.sdk.core.webview.jshandler.b;
import com.kwad.sdk.core.webview.jshandler.f;
import com.kwad.sdk.core.webview.jshandler.j;
import com.kwad.sdk.core.webview.jshandler.k;
import com.kwad.sdk.core.webview.jshandler.p;
import com.kwad.sdk.core.webview.jshandler.x;
import com.kwad.sdk.mvp.Presenter;
import com.kwad.sdk.reward.d.c;
import com.kwad.sdk.reward.g;
import com.kwad.sdk.utils.af;
import com.kwad.sdk.utils.at;
import com.kwad.sdk.utils.ax;
import com.kwad.sdk.utils.be;
import com.kwad.sdk.widget.KSFrameLayout;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends g implements b.c, c.a, com.kwad.sdk.widget.d {
    private ViewGroup b;
    private d c;
    private ViewGroup d;
    private C0076a e;
    private com.kwad.sdk.reward.d.c f;
    private com.kwad.sdk.reward.d.c g;
    private com.kwad.sdk.reward.b.kwai.a h;

    /* renamed from: com.kwad.sdk.reward.presenter.c.a$a, reason: collision with other inner class name */
    static class C0076a implements j.b, p.b {
        private View a;
        private KsAdWebView b;
        private com.kwad.sdk.core.webview.a c;
        private com.kwad.sdk.core.webview.kwai.g d;
        private x e;
        private b.c f;
        private b g;

        @Nullable
        private AdTemplate h;
        private boolean i = false;

        public C0076a(KsAdWebView ksAdWebView, View view, b.c cVar) {
            this.a = view;
            this.b = ksAdWebView;
            this.f = cVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            if (this.a != null) {
                this.a.setVisibility(0);
            }
            a(false);
        }

        private void a(com.kwad.sdk.core.webview.kwai.g gVar) {
            gVar.a(new com.kwad.sdk.core.webview.jshandler.a(this.c, (com.kwad.sdk.core.download.a.b) null, this.f));
            gVar.a(new f(this.c));
            gVar.a(new com.kwad.sdk.core.webview.jshandler.d(this.c));
            gVar.a(new p(this));
            this.e = new x();
            gVar.a(this.e);
            gVar.a(new k(this.c));
            gVar.a(new com.kwad.sdk.core.webview.jshandler.g(this.c));
            gVar.a(new com.kwad.sdk.core.webview.a.g());
            gVar.a(new j(this.c, this));
        }

        @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface", "JavascriptInterface"})
        private void a(String str) {
            if (at.a(str)) {
                a();
                return;
            }
            b();
            be.b(this.b);
            this.b.setHttpErrorListener(new KsAdWebView.d() { // from class: com.kwad.sdk.reward.presenter.c.a.a.1
                @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
                public void a() {
                }

                @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
                public void a(int i, String str2, String str3) {
                    com.kwad.sdk.core.d.a.a("LandPageOpenTaskPresenter", "onReceivedHttpError: " + i + ", " + str2);
                    C0076a.this.a();
                }

                @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
                public void b() {
                }
            });
            this.d = new com.kwad.sdk.core.webview.kwai.g(this.b);
            a(this.d);
            this.b.addJavascriptInterface(this.d, "KwaiAd");
            this.b.loadUrl(str);
            ax.a(new Runnable() { // from class: com.kwad.sdk.reward.presenter.c.a.a.2
                @Override // java.lang.Runnable
                public void run() {
                    if (C0076a.this.i) {
                        return;
                    }
                    C0076a.this.i = true;
                    C0076a.this.a();
                }
            }, null, 1000L);
        }

        private void b() {
            if (this.d != null) {
                this.d.a();
                this.d = null;
            }
        }

        private void b(AdTemplate adTemplate, ViewGroup viewGroup) {
            this.c = new com.kwad.sdk.core.webview.a();
            this.c.a(adTemplate);
            this.c.a = 0;
            this.c.b = null;
            this.c.d = viewGroup;
            this.c.e = this.b;
            this.c.c = null;
            this.c.h = true;
        }

        public void a(AdTemplate adTemplate, ViewGroup viewGroup) {
            if (this.b != null) {
                this.b.setBackgroundColor(0);
                if (this.b.getBackground() != null) {
                    this.b.getBackground().setAlpha(0);
                }
            }
            this.h = adTemplate;
            b(adTemplate, viewGroup);
            String strB = com.kwad.sdk.core.response.a.b.b(adTemplate);
            com.kwad.sdk.core.d.a.a("LandPageOpenTaskPresenter", "init url: " + strB);
            a(strB);
        }

        @Override // com.kwad.sdk.core.webview.jshandler.j.b
        public void a(@NonNull j.a aVar) {
            com.kwad.sdk.core.d.a.a("LandPageOpenTaskPresenter", "onAdFrameValid : " + aVar.toJson());
        }

        @Override // com.kwad.sdk.core.webview.jshandler.p.b
        public void a(p.a aVar) {
            if (this.i) {
                return;
            }
            this.i = true;
            com.kwad.sdk.core.d.a.a("LandPageOpenTaskPresenter", getClass().getName() + " updatePageStatus: " + aVar);
            if (aVar.a == 1) {
                a(true);
            } else {
                a();
            }
        }

        public void a(b bVar) {
            this.g = bVar;
        }

        public void a(boolean z) {
            com.kwad.sdk.core.d.a.a("LandPageOpenTaskPresenter", "switchWebView: " + z);
            if (this.b == null) {
                return;
            }
            this.b.setVisibility(z ? 0 : 8);
        }
    }

    interface b {
    }

    private static class c extends com.kwad.sdk.reward.presenter.a {
        private c() {
        }

        @Override // com.kwad.sdk.reward.presenter.a
        protected int i() {
            return (!g() || af.e(q())) ? 8 : 0;
        }
    }

    static class d extends C0076a {
        public d(KsAdWebView ksAdWebView, View view, b.c cVar) {
            super(ksAdWebView, view, cVar);
        }
    }

    public a() {
        a((Presenter) new c());
    }

    private void a(View view, boolean z) {
        int id = view.getId();
        if (id == R.id.ksad_reward_apk_info_card_native_container || id == R.id.ksad_activity_apk_info_area_native) {
            b(z);
        }
    }

    private void a(AdBaseFrameLayout adBaseFrameLayout) {
        Context contextQ;
        float f;
        if (!af.e(q())) {
            com.kwad.sdk.core.d.a.a("LandPageOpenTaskPresenter", "initBottomActionBar screen is horizontal");
            return;
        }
        ((ViewStub) a(R.id.ksad_reward_apk_info_stub)).inflate();
        KSFrameLayout kSFrameLayout = (KSFrameLayout) a(R.id.ksad_reward_apk_info_card_root);
        kSFrameLayout.setRadius(q().getResources().getDimension(R.dimen.ksad_reward_apk_info_card_step_icon_radius));
        if (com.kwad.sdk.core.response.a.c.i(this.a.g).size() == 0) {
            kSFrameLayout.setRatio(0.0f);
            contextQ = q();
            f = 136.0f;
        } else {
            kSFrameLayout.setRatio(0.0f);
            contextQ = q();
            f = 155.0f;
        }
        com.kwad.sdk.a.kwai.a.a(kSFrameLayout, com.kwad.sdk.a.kwai.a.a(contextQ, f));
        this.d = (ViewGroup) a(R.id.ksad_reward_apk_info_card_native_container);
        this.d.setClickable(true);
        new com.kwad.sdk.widget.f(this.d, this);
        this.f = new com.kwad.sdk.reward.d.c(this.d);
        this.f.a(this);
        this.f.a(this.a.g, true);
        this.e = new C0076a((KsAdWebView) a(R.id.ksad_reward_apk_info_card_h5), this.d, this);
        this.e.a(new b() { // from class: com.kwad.sdk.reward.presenter.c.a.1
        });
        this.e.a(this.a.g, adBaseFrameLayout);
    }

    private void b(boolean z) {
        this.a.a(q(), z ? 1 : 153, 1);
    }

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        com.kwad.sdk.core.d.a.a("LandPageOpenTaskPresenter", "onBind");
        if (g()) {
            this.h = com.kwad.sdk.reward.b.c.b();
            this.a.z = this.h;
            this.h.g();
            AdBaseFrameLayout adBaseFrameLayout = (AdBaseFrameLayout) a(R.id.ksad_root_container);
            this.b = (ViewGroup) a(R.id.ksad_activity_apk_info_area_native);
            this.b.setClickable(true);
            new com.kwad.sdk.widget.f(this.b, this);
            this.g = new com.kwad.sdk.reward.d.c(this.b);
            this.g.a(this);
            this.g.a(this.a.g, false);
            ((KSFrameLayout) a(R.id.ksad_activity_apk_info_webview_container)).setWidthBasedRatio(false);
            this.c = new d((KsAdWebView) a(R.id.ksad_activity_apk_info_webview), this.b, this);
            this.c.a(this.a.g, adBaseFrameLayout);
            a(adBaseFrameLayout);
        }
    }

    @Override // com.kwad.sdk.core.webview.jshandler.b.c
    public void a(@Nullable b.a aVar) {
    }

    @Override // com.kwad.sdk.reward.d.c.a
    public void a(com.kwad.sdk.reward.d.c cVar, View view, boolean z) {
        b(z);
    }

    @Override // com.kwad.sdk.widget.d
    public void a_(View view) {
        a(view, true);
    }

    @Override // com.kwad.sdk.widget.d
    public void b(View view) {
        if (com.kwad.sdk.core.response.a.c.g(this.a.g)) {
            a(view, false);
        }
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        com.kwad.sdk.core.d.a.a("LandPageOpenTaskPresenter", "onUnbind");
        if (this.e != null) {
            this.e = null;
        }
        this.a.z = null;
    }
}
