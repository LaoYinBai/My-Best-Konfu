package com.kwad.sdk.reward.presenter.c;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.core.page.widget.webview.KsAdWebView;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.view.AdBaseFrameLayout;
import com.kwad.sdk.core.webview.jshandler.b;
import com.kwad.sdk.core.webview.jshandler.f;
import com.kwad.sdk.core.webview.jshandler.j;
import com.kwad.sdk.core.webview.jshandler.k;
import com.kwad.sdk.core.webview.jshandler.p;
import com.kwad.sdk.core.webview.jshandler.t;
import com.kwad.sdk.core.webview.jshandler.x;
import com.kwad.sdk.mvp.Presenter;
import com.kwad.sdk.reward.d.c;
import com.kwad.sdk.reward.g;
import com.kwad.sdk.utils.af;
import com.kwad.sdk.utils.at;
import com.kwad.sdk.utils.be;
import com.kwad.sdk.utils.e;
import com.kwad.sdk.utils.u;
import com.kwad.sdk.widget.KSFrameLayout;
import java.util.Observable;
import java.util.Observer;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends g implements b.c, c.a, e.a, com.kwad.sdk.widget.d, Observer {
    private static float b = 0.4548105f;
    private ViewGroup c;
    private d d;
    private e e;
    private com.kwad.sdk.reward.b.a.a f;
    private ViewGroup g;
    private a h;
    private com.kwad.sdk.reward.d.c i;
    private com.kwad.sdk.reward.d.c j;
    private int k = 15;
    private long l = -1;
    private boolean m = false;

    static class a extends com.kwad.sdk.core.download.a.c implements j.b, p.b, Observer {
        private View b;
        private KsAdWebView c;
        private com.kwad.sdk.core.webview.a d;
        private com.kwad.sdk.core.webview.kwai.g e;
        private com.kwad.sdk.core.download.a.b f;
        private com.kwad.sdk.reward.b.a.a g;
        private x h;
        private b.c i;
        private InterfaceC0077b j;

        @Nullable
        private AdTemplate k;

        public a(KsAdWebView ksAdWebView, View view, com.kwad.sdk.core.download.a.b bVar, com.kwad.sdk.reward.b.a.a aVar, b.c cVar) {
            this.b = view;
            this.c = ksAdWebView;
            this.f = bVar;
            this.g = aVar;
            this.i = cVar;
        }

        private void a(com.kwad.sdk.core.webview.kwai.g gVar) {
            gVar.a(new com.kwad.sdk.core.webview.jshandler.a(this.d, this.f, this.i));
            gVar.a(new f(this.d));
            gVar.a(new com.kwad.sdk.core.webview.jshandler.d(this.d));
            gVar.a(new p(this));
            this.h = new x();
            gVar.a(this.h);
            gVar.a(new k(this.d));
            gVar.a(new com.kwad.sdk.core.webview.jshandler.g(this.d));
            gVar.a(new com.kwad.sdk.core.webview.a.g());
            gVar.a(new j(this.d, this));
            gVar.a(new t(this.d, this.f));
        }

        @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface", "JavascriptInterface"})
        private void a(String str) {
            if (at.a(str)) {
                c();
                return;
            }
            d();
            be.b(this.c);
            this.c.setHttpErrorListener(new KsAdWebView.d() { // from class: com.kwad.sdk.reward.presenter.c.b.a.1
                @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
                public void a() {
                }

                @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
                public void a(int i, String str2, String str3) {
                    com.kwad.sdk.core.d.a.a("LaunchAppTaskPresenter", "onReceivedHttpError: " + i + ", " + str2);
                    a.this.c();
                }

                @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
                public void b() {
                }
            });
            this.e = new com.kwad.sdk.core.webview.kwai.g(this.c);
            a(this.e);
            this.c.addJavascriptInterface(this.e, "KwaiAd");
            this.c.loadUrl(str);
        }

        private void a(String str, int i) {
            if (this.j != null) {
                this.j.a(str, i);
            }
        }

        private void b(AdTemplate adTemplate, ViewGroup viewGroup) {
            this.d = new com.kwad.sdk.core.webview.a();
            this.d.a(adTemplate);
            this.d.a = 0;
            this.d.b = null;
            this.d.d = viewGroup;
            this.d.e = this.c;
            this.d.c = null;
            this.d.h = true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c() {
            if (this.b != null) {
                this.b.setVisibility(0);
            }
            a(false);
        }

        private void d() {
            if (this.e != null) {
                this.e.a();
                this.e = null;
            }
        }

        @Override // com.kwad.sdk.core.download.a.c
        public void a(int i) {
            a(com.kwad.sdk.core.response.a.a.b(i), i);
        }

        public void a(AdTemplate adTemplate, ViewGroup viewGroup) {
            if (this.c != null) {
                this.c.setBackgroundColor(0);
                if (this.c.getBackground() != null) {
                    this.c.getBackground().setAlpha(0);
                }
            }
            this.k = adTemplate;
            b(adTemplate, viewGroup);
            a(com.kwad.sdk.core.response.a.b.b(this.k));
            if (this.g != null) {
                this.g.addObserver(this);
            }
            this.f.a(this);
        }

        @Override // com.kwad.sdk.core.webview.jshandler.j.b
        public void a(@NonNull j.a aVar) {
            com.kwad.sdk.core.d.a.a("LaunchAppTaskPresenter", "onAdFrameValid : " + aVar.toJson());
        }

        @Override // com.kwad.sdk.core.webview.jshandler.p.b
        public void a(p.a aVar) {
            com.kwad.sdk.core.d.a.a("LaunchAppTaskPresenter", getClass().getName() + " updatePageStatus: " + aVar);
            if (aVar.a == 1) {
                a(true);
            } else {
                c();
            }
        }

        public void a(InterfaceC0077b interfaceC0077b) {
            this.j = interfaceC0077b;
        }

        public void a(boolean z) {
            com.kwad.sdk.core.d.a.a("LaunchAppTaskPresenter", "switchWebView: " + z);
            if (this.c == null) {
                return;
            }
            this.c.setVisibility(z ? 0 : 8);
        }

        public void b() {
            if (this.g != null) {
                this.g.deleteObserver(this);
            }
            this.f.b(this);
        }

        @Override // com.kwad.sdk.api.KsAppDownloadListener
        public void onDownloadFailed() {
            a(this.k != null ? com.kwad.sdk.core.response.a.a.A(com.kwad.sdk.core.response.a.d.j(this.k)) : "立即下载", 100);
        }

        @Override // com.kwad.sdk.api.KsAppDownloadListener
        public void onDownloadFinished() {
            a(this.k == null ? "" : com.kwad.sdk.core.response.a.a.a(this.k), 100);
        }

        @Override // com.kwad.sdk.api.KsAppDownloadListener
        public void onIdle() {
            a(this.k != null ? com.kwad.sdk.core.response.a.a.A(com.kwad.sdk.core.response.a.d.j(this.k)) : "立即下载", 100);
        }

        @Override // com.kwad.sdk.api.KsAppDownloadListener
        public void onInstalled() {
            a(this.k != null ? com.kwad.sdk.core.response.a.a.m(com.kwad.sdk.core.response.a.d.j(this.k)) : "立即打开", 100);
        }

        @Override // com.kwad.sdk.api.KsAppDownloadListener
        public void onProgressUpdate(int i) {
            a(com.kwad.sdk.core.response.a.a.a(i), i);
        }

        @Override // java.util.Observer
        public void update(Observable observable, Object obj) {
            com.kwad.sdk.core.d.a.a("LaunchAppTaskPresenter", "BottomCardWebViewHelper update: " + obj);
            if (obj instanceof Boolean) {
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                if (this.h != null) {
                    this.h.a(zBooleanValue);
                }
            }
        }
    }

    /* renamed from: com.kwad.sdk.reward.presenter.c.b$b, reason: collision with other inner class name */
    interface InterfaceC0077b {
        void a(String str, int i);
    }

    private static class c extends com.kwad.sdk.reward.presenter.a {
        private c() {
        }

        @Override // com.kwad.sdk.reward.presenter.a
        protected int i() {
            return (!f() || af.e(q())) ? 8 : 0;
        }
    }

    static class d extends a {
        public d(KsAdWebView ksAdWebView, View view, com.kwad.sdk.core.download.a.b bVar, com.kwad.sdk.reward.b.a.a aVar, b.c cVar) {
            super(ksAdWebView, view, bVar, aVar, cVar);
        }
    }

    public b() {
        a((Presenter) new c());
    }

    private void a(View view, boolean z) {
        this.a.a(q(), z ? 1 : 153, 1);
    }

    private void a(AdBaseFrameLayout adBaseFrameLayout) throws Resources.NotFoundException {
        if (!af.e(q())) {
            com.kwad.sdk.core.d.a.a("LaunchAppTaskPresenter", "initBottomActionBar screen is horizontal");
            return;
        }
        ((ViewStub) a(R.id.ksad_reward_apk_info_stub)).inflate();
        this.g = (ViewGroup) a(R.id.ksad_reward_apk_info_card_native_container);
        this.g.setClickable(true);
        new com.kwad.sdk.widget.f(this.g, this);
        final KSFrameLayout kSFrameLayout = (KSFrameLayout) a(R.id.ksad_reward_apk_info_card_root);
        kSFrameLayout.setRadius(q().getResources().getDimension(R.dimen.ksad_reward_apk_info_card_step_icon_radius));
        final float dimension = q().getResources().getDimension(R.dimen.ksad_reward_apk_info_card_height);
        kSFrameLayout.post(new Runnable() { // from class: com.kwad.sdk.reward.presenter.c.b.1
            @Override // java.lang.Runnable
            public void run() {
                kSFrameLayout.getHeight();
                float f = dimension;
            }
        });
        this.g = (ViewGroup) a(R.id.ksad_reward_apk_info_card_native_container);
        this.i = new com.kwad.sdk.reward.d.c(this.g);
        this.i.a(this);
        this.i.a(this.a.g, false);
        this.h = new a((KsAdWebView) a(R.id.ksad_reward_apk_info_card_h5), this.g, this.a.l, this.f, this);
        this.h.a(new InterfaceC0077b() { // from class: com.kwad.sdk.reward.presenter.c.b.2
            @Override // com.kwad.sdk.reward.presenter.c.b.InterfaceC0077b
            public void a(String str, int i) {
                int i2 = com.kwad.sdk.core.response.a.d.j(b.this.a.g).status;
                com.kwad.sdk.core.d.a.a("LaunchAppTaskPresenter", "onUpdateDownloadProgress downloadStatus: " + i2);
                b.this.i.a(str, i, (i2 == 1 || i2 == 2 || i2 == 3) ? false : true);
            }
        });
        this.h.a(this.a.g, adBaseFrameLayout);
    }

    private void b(boolean z) {
        com.kwad.sdk.core.d.a.a("LaunchAppTaskPresenter", "showTaskToast hasShowCompletedToast: " + this.m + " completed: " + z);
        if (this.m) {
            return;
        }
        u.a(q(), z ? "恭喜！任务达标啦，成功获取奖励~" : "哎呀，差一点就达标啦，再试一次~", 0);
        if (z) {
            this.m = true;
        }
    }

    private boolean e() {
        com.kwad.sdk.core.d.a.a("LaunchAppTaskPresenter", "checkUseAppTime appBackgroundTimestamp: " + this.l);
        return this.l >= 0 && System.currentTimeMillis() - this.l > ((long) (this.k * 1000));
    }

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() throws Resources.NotFoundException {
        super.a();
        com.kwad.sdk.core.d.a.a("LaunchAppTaskPresenter", "onBind");
        if (com.kwad.sdk.core.response.a.d.u(this.a.g)) {
            this.k = com.kwad.sdk.core.config.b.az();
            com.kwad.sdk.utils.x.a().addObserver(this);
            this.f = com.kwad.sdk.reward.b.c.a();
            this.a.y = this.f;
            this.f.g();
            com.kwad.sdk.reward.b.a.a.a(this.f, q(), this.a.g);
            AdBaseFrameLayout adBaseFrameLayout = (AdBaseFrameLayout) a(R.id.ksad_root_container);
            this.c = (ViewGroup) a(R.id.ksad_activity_apk_info_area_native);
            this.j = new com.kwad.sdk.reward.d.c(this.c);
            this.j.a(this);
            this.j.a(this.a.g, false);
            ((KSFrameLayout) a(R.id.ksad_activity_apk_info_webview_container)).setWidthBasedRatio(false);
            this.d = new d((KsAdWebView) a(R.id.ksad_activity_apk_info_webview), this.c, this.a.l, this.f, this);
            this.d.a(this.a.g, adBaseFrameLayout);
            AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(this.a.g);
            if (this.e == null) {
                this.e = new e(com.kwad.sdk.core.response.a.a.v(adInfoJ));
            } else {
                this.e.a(com.kwad.sdk.core.response.a.a.v(adInfoJ));
            }
            this.e.a(q(), this);
            a(adBaseFrameLayout);
        }
    }

    @Override // com.kwad.sdk.core.webview.jshandler.b.c
    public void a(@Nullable b.a aVar) {
    }

    @Override // com.kwad.sdk.reward.d.c.a
    public void a(com.kwad.sdk.reward.d.c cVar, View view, boolean z) {
        this.a.a(q(), z ? 1 : 153, 1);
    }

    @Override // com.kwad.sdk.utils.e.a
    public void a(String str) {
        if (this.a.y == null || !f()) {
            return;
        }
        this.a.y.h();
        if (com.kwad.sdk.utils.x.a().b()) {
            return;
        }
        this.l = System.currentTimeMillis();
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
        com.kwad.sdk.core.d.a.a("LaunchAppTaskPresenter", "onUnbind");
        com.kwad.sdk.utils.x.a().deleteObserver(this);
        if (this.e != null) {
            this.e.a(q());
        }
        if (this.h != null) {
            this.h.b();
            this.h = null;
        }
        this.a.y = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x004e  */
    @Override // java.util.Observer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void update(java.util.Observable r4, java.lang.Object r5) {
        /*
            r3 = this;
            com.kwad.sdk.utils.x r0 = com.kwad.sdk.utils.x.a()
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L86
            boolean r4 = r5 instanceof java.lang.String
            if (r4 == 0) goto L86
            java.lang.String r5 = (java.lang.String) r5
            com.kwad.sdk.reward.b.a.a r4 = r3.f
            if (r4 != 0) goto L15
            return
        L15:
            com.kwad.sdk.reward.b.a.a r4 = r3.f
            android.content.Context r0 = r3.q()
            com.kwad.sdk.reward.a r1 = r3.a
            com.kwad.sdk.core.response.model.AdTemplate r1 = r1.g
            com.kwad.sdk.reward.b.a.a.a(r4, r0, r1)
            com.kwad.sdk.reward.b.a.a r4 = r3.f
            boolean r4 = r4.k()
            r0 = 0
            if (r4 == 0) goto L7b
            r4 = -1
            int r1 = r5.hashCode()
            r2 = -1891028138(0xffffffff8f493356, float:-9.919952E-30)
            if (r1 == r2) goto L44
            r2 = 321925954(0x13303342, float:2.2239595E-27)
            if (r1 == r2) goto L3b
            goto L4e
        L3b:
            java.lang.String r1 = "ACTION_APP_GO_TO_BACKGROUND"
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L4e
            goto L4f
        L44:
            java.lang.String r0 = "ACTION_APP_BACK_TO_FOREGROUND"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L4e
            r0 = 1
            goto L4f
        L4e:
            r0 = -1
        L4f:
            switch(r0) {
                case 0: goto L74;
                case 1: goto L53;
                default: goto L52;
            }
        L52:
            goto L86
        L53:
            boolean r4 = r3.e()
            if (r4 == 0) goto L70
            com.kwad.sdk.reward.b.a.a r5 = r3.f
            if (r5 == 0) goto L70
            com.kwad.sdk.reward.b.a.a r5 = r3.f
            r5.j()
            com.kwad.sdk.reward.d r5 = com.kwad.sdk.reward.d.a()
            r5.b()
            com.kwad.sdk.reward.a r5 = r3.a
            com.kwad.sdk.reward.a.b r5 = r5.b
            r5.e()
        L70:
            r3.b(r4)
            goto L86
        L74:
            long r4 = java.lang.System.currentTimeMillis()
            r3.l = r4
            goto L86
        L7b:
            java.lang.String r4 = "ACTION_APP_BACK_TO_FOREGROUND"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L86
            r3.b(r0)
        L86:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.reward.presenter.c.b.update(java.util.Observable, java.lang.Object):void");
    }
}
