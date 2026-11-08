package com.kwad.sdk.d.kwai;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Outline;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewParent;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.api.KsAdVideoPlayConfig;
import com.kwad.sdk.core.page.widget.webview.KsAdWebView;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.video.videoview.a;
import com.kwad.sdk.core.webview.a.j;
import com.kwad.sdk.core.webview.a.k;
import com.kwad.sdk.core.webview.a.n;
import com.kwad.sdk.core.webview.jshandler.WebCardVideoPositionHandler;
import com.kwad.sdk.core.webview.jshandler.aa;
import com.kwad.sdk.core.webview.jshandler.b;
import com.kwad.sdk.core.webview.jshandler.d;
import com.kwad.sdk.core.webview.jshandler.i;
import com.kwad.sdk.core.webview.jshandler.j;
import com.kwad.sdk.core.webview.jshandler.p;
import com.kwad.sdk.core.webview.jshandler.s;
import com.kwad.sdk.core.webview.jshandler.t;
import com.kwad.sdk.core.webview.jshandler.u;
import com.kwad.sdk.d.kwai.c;
import com.kwad.sdk.utils.af;
import com.kwad.sdk.utils.ax;
import com.kwad.sdk.utils.bb;
import com.kwad.sdk.utils.be;
import com.kwad.sdk.widget.KSFrameLayout;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d extends b {
    private static float c = 0.61f;
    private static float d = 0.8f;
    private static float e = 0.768f;
    private static float f = 1.0f;
    private static float g = 0.749f;
    private static float h = 0.637f;
    private k A;
    private s B;

    @Nullable
    private u C;
    private com.kwad.sdk.core.j.f E;

    @NonNull
    protected Context a;
    protected AdInfo b;
    private c i;
    private com.kwad.sdk.core.webview.kwai.g j;
    private com.kwad.sdk.core.webview.a k;
    private AdTemplate l;
    private boolean m;
    private KSFrameLayout n;
    private KSFrameLayout o;
    private Dialog q;
    private KsAdWebView r;

    @Nullable
    private com.kwad.sdk.core.download.a.b s;
    private List<Integer> u;
    private com.kwad.sdk.core.video.videoview.b v;
    private ImageView w;

    @NonNull
    private KsAdVideoPlayConfig x;
    private ImageView y;
    private a.b z;
    private Handler p = new Handler(Looper.getMainLooper());
    private int t = -1;
    private boolean D = false;
    private com.kwad.sdk.core.j.d F = new com.kwad.sdk.core.j.e() { // from class: com.kwad.sdk.d.kwai.d.1
        @Override // com.kwad.sdk.core.j.e, com.kwad.sdk.core.j.d
        public void e() {
            com.kwad.sdk.core.d.a.a("InterstitialWebViewPresenter", "onPageVisible");
            if (d.this.B != null) {
                d.this.B.c();
                d.this.B.d();
            }
        }

        @Override // com.kwad.sdk.core.j.e, com.kwad.sdk.core.j.d
        public void f() {
            com.kwad.sdk.core.d.a.a("InterstitialWebViewPresenter", "onPageInvisible");
            if (d.this.B != null) {
                d.this.B.e();
                d.this.B.f();
            }
        }
    };
    private final Runnable G = new Runnable() { // from class: com.kwad.sdk.d.kwai.d.12
        @Override // java.lang.Runnable
        public void run() {
            com.kwad.sdk.core.d.a.a("InterstitialWebViewPresenter", "rollBackRunnable run");
            d.this.r();
            d.this.i.b();
        }
    };
    private p.b H = new p.b() { // from class: com.kwad.sdk.d.kwai.d.11
        @Override // com.kwad.sdk.core.webview.jshandler.p.b
        public void a(p.a aVar) {
            if (d.this.m || d.this.D) {
                return;
            }
            d.this.t = aVar.a;
            if (d.this.t != 1) {
                d.this.b(PushConstants.PUSH_FLYME_3_CHANGE_VERSION_START);
                return;
            }
            d.this.r.setVisibility(0);
            com.kwad.sdk.core.report.d.a(d.this.l);
            d.this.p.removeCallbacksAndMessages(null);
            ax.b(d.this.G);
            if (d.this.C != null) {
                d.this.C.c();
            }
        }
    };

    @NonNull
    private WebCardVideoPositionHandler A() {
        return new WebCardVideoPositionHandler(new WebCardVideoPositionHandler.a() { // from class: com.kwad.sdk.d.kwai.d.4
            @Override // com.kwad.sdk.core.webview.jshandler.WebCardVideoPositionHandler.a
            public void a(WebCardVideoPositionHandler.VideoPosition videoPosition) {
                com.kwad.sdk.core.d.a.a("InterstitialWebViewPresenter", "onVideoSetPosition hadRollBackToNative: " + d.this.D);
                if (d.this.D || d.this.b == null || !com.kwad.sdk.core.response.a.a.P(d.this.b)) {
                    return;
                }
                d.this.a(d.this.b, videoPosition, d.this.o, d.this.i.a(d.this.a, d.this.b));
                d.this.o.setVisibility(0);
                d.this.r.setVisibility(0);
            }
        });
    }

    private aa B() {
        aa aaVar = new aa(q(), this.l);
        aaVar.a(new aa.a() { // from class: com.kwad.sdk.d.kwai.d.5
            @Override // com.kwad.sdk.core.webview.jshandler.aa.a
            public boolean a() {
                if (d.this.A != null) {
                    d.this.A.a(false);
                }
                ax.a(new Runnable() { // from class: com.kwad.sdk.d.kwai.d.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (d.this.q != null) {
                            d.this.q.dismiss();
                            if (d.this.i.b != null) {
                                d.this.i.b.onAdClosed();
                            }
                        }
                    }
                });
                boolean z = !d.this.i.j;
                if (z) {
                    d.this.i.a(true, d.this.i.i);
                }
                return z;
            }
        });
        return aaVar;
    }

    @NonNull
    private i C() {
        return new i(new i.b() { // from class: com.kwad.sdk.d.kwai.d.6
            @Override // com.kwad.sdk.core.webview.jshandler.i.b
            public void a(final int i) {
                if (d.this.q != null) {
                    d.this.p.post(new Runnable() { // from class: com.kwad.sdk.d.kwai.d.6.1
                        @Override // java.lang.Runnable
                        public void run() {
                            d.this.q.dismiss();
                            d.this.i.a(i == 3, d.this.v);
                            if (d.this.i.b != null) {
                                d.this.i.b.onAdClosed();
                            }
                        }
                    });
                }
            }
        });
    }

    private k D() {
        k kVar = new k() { // from class: com.kwad.sdk.d.kwai.d.7
            @Override // com.kwad.sdk.core.webview.a.i, com.kwad.sdk.core.webview.kwai.a
            public void a(String str, @NonNull com.kwad.sdk.core.webview.kwai.c cVar) {
                super.a(str, cVar);
                if (d.this.v.d()) {
                    com.kwad.sdk.core.webview.a.kwai.f fVar = new com.kwad.sdk.core.webview.a.kwai.f();
                    fVar.b = false;
                    fVar.c = false;
                    fVar.a = 0;
                    cVar.a(fVar);
                }
                if (d.this.v.i()) {
                    com.kwad.sdk.core.webview.a.kwai.f fVar2 = new com.kwad.sdk.core.webview.a.kwai.f();
                    fVar2.b = false;
                    fVar2.c = true;
                    fVar2.a = com.kwad.sdk.core.response.a.a.b(com.kwad.sdk.core.response.a.d.j(d.this.k.a()));
                    cVar.a(fVar2);
                }
            }
        };
        this.A = kVar;
        return kVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void E() {
        if (this.i.i != null) {
            this.i.i.k();
        }
        this.i.c.dismiss();
        if (this.i.b != null) {
            this.i.b.onAdClosed();
        }
    }

    @NonNull
    private com.kwad.sdk.core.webview.jshandler.a F() {
        return new com.kwad.sdk.core.webview.jshandler.a(this.k, this.s, new b.c() { // from class: com.kwad.sdk.d.kwai.d.8
            @Override // com.kwad.sdk.core.webview.jshandler.b.c
            public void a(b.a aVar) {
                if (aVar == null || d.this.i.c == null || !com.kwad.sdk.core.config.b.aC()) {
                    return;
                }
                d.this.i.c.dismiss();
            }
        });
    }

    @NonNull
    private com.kwad.sdk.core.webview.jshandler.b G() {
        return new com.kwad.sdk.core.webview.jshandler.b(this.k, this.s, new b.c() { // from class: com.kwad.sdk.d.kwai.d.9
            @Override // com.kwad.sdk.core.webview.jshandler.b.c
            public void a(b.a aVar) {
                if (aVar == null || d.this.i.c == null || !com.kwad.sdk.core.config.b.aC()) {
                    return;
                }
                d.this.i.c.dismiss();
            }
        });
    }

    private static float a(boolean z, boolean z2) {
        if (z && z2) {
            return 1.7777778f;
        }
        if (z) {
            return 0.749f;
        }
        return s();
    }

    private static ViewGroup.LayoutParams a(int i, boolean z) {
        boolean z2 = com.kwad.sdk.core.config.b.q() == 0;
        int i2 = (int) (i * (z ? z2 ? d : c : z2 ? f : e));
        return new ViewGroup.LayoutParams(i2, (int) (i2 * (z ? 1.7777778f : 0.749f)));
    }

    private static ViewGroup.MarginLayoutParams a(Context context, WebCardVideoPositionHandler.VideoPosition videoPosition, ViewGroup.MarginLayoutParams marginLayoutParams) {
        marginLayoutParams.topMargin = videoPosition.topMargin;
        marginLayoutParams.leftMargin = videoPosition.leftMargin;
        marginLayoutParams.width = videoPosition.width;
        marginLayoutParams.height = videoPosition.height;
        return marginLayoutParams;
    }

    private static void a(ViewGroup.MarginLayoutParams marginLayoutParams, ViewGroup.MarginLayoutParams marginLayoutParams2) {
        marginLayoutParams2.leftMargin = marginLayoutParams.leftMargin;
        marginLayoutParams2.rightMargin = marginLayoutParams.rightMargin;
        marginLayoutParams2.topMargin = marginLayoutParams.topMargin;
        marginLayoutParams2.bottomMargin = marginLayoutParams.bottomMargin;
        marginLayoutParams2.width = marginLayoutParams.width;
        marginLayoutParams2.height = marginLayoutParams.height;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(AdInfo adInfo, WebCardVideoPositionHandler.VideoPosition videoPosition, KSFrameLayout kSFrameLayout, boolean z) {
        kSFrameLayout.setWidthBasedRatio(!z);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) kSFrameLayout.getLayoutParams();
        com.kwad.sdk.core.d.a.a("InterstitialWebViewPresenter", "updateVideoContainerSize before size: " + marginLayoutParams.width + ", " + marginLayoutParams.height);
        a(this.a, videoPosition, marginLayoutParams);
        ViewGroup.MarginLayoutParams marginLayoutParams2 = new ViewGroup.MarginLayoutParams(marginLayoutParams);
        float fL = com.kwad.sdk.core.response.a.a.L(adInfo);
        com.kwad.sdk.core.d.a.a("InterstitialWebViewPresenter", "updateVideoContainerSize materialRatio: " + fL);
        if (z && fL > 0.0f) {
            int i = marginLayoutParams.width;
            int i2 = (int) (marginLayoutParams.height / fL);
            marginLayoutParams.width = i2;
            marginLayoutParams.leftMargin += (i - i2) / 2;
            if (marginLayoutParams.width != 0) {
                float f2 = marginLayoutParams.height / marginLayoutParams.width;
                com.kwad.sdk.core.d.a.a("InterstitialWebViewPresenter", "cardParams ratio: " + f2);
                kSFrameLayout.setRatio(f2);
            }
        }
        com.kwad.sdk.core.d.a.a("InterstitialWebViewPresenter", "cardParams width: " + marginLayoutParams.width + ", height: " + marginLayoutParams.height);
        kSFrameLayout.setLayoutParams(marginLayoutParams);
        if (z) {
            this.w.setVisibility(0);
            ViewGroup.MarginLayoutParams marginLayoutParams3 = (ViewGroup.MarginLayoutParams) this.w.getLayoutParams();
            a(marginLayoutParams2, marginLayoutParams3);
            this.w.setLayoutParams(marginLayoutParams3);
            this.i.a(this.a, adInfo, this.l, this.w);
        } else {
            this.w.setVisibility(8);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            kSFrameLayout.setOutlineProvider(new ViewOutlineProvider() { // from class: com.kwad.sdk.d.kwai.d.10
                @Override // android.view.ViewOutlineProvider
                public void getOutline(View view, Outline outline) {
                    outline.setRect(0, 0, view.getWidth(), view.getHeight());
                }
            });
            kSFrameLayout.setClipToOutline(true);
        }
    }

    private void a(com.kwad.sdk.core.webview.kwai.g gVar) {
        gVar.a(G());
        gVar.a(F());
        gVar.a(new com.kwad.sdk.core.webview.a.g());
        gVar.a(y());
        gVar.a(new com.kwad.sdk.core.webview.jshandler.f(this.k));
        gVar.a(x());
        gVar.a(w());
        gVar.a(C());
        this.B = new s();
        gVar.a(this.B);
        this.i.a(z());
        gVar.a(D());
        gVar.a(A());
        gVar.a(new com.kwad.sdk.core.webview.jshandler.g(this.k));
        gVar.a(u());
        gVar.a(new p(this.H));
        gVar.a(t());
        gVar.a(new com.kwad.sdk.core.webview.jshandler.k(this.k));
        gVar.a(B());
        if (v() != null) {
            gVar.a(this.C);
        }
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface", "JavascriptInterface"})
    private void a(String str) {
        i();
        be.b(this.r);
        this.r.setHttpErrorListener(new KsAdWebView.d() { // from class: com.kwad.sdk.d.kwai.d.14
            @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
            public void a() {
            }

            @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
            public void a(int i, String str2, String str3) {
                d.this.b("1");
            }

            @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
            public void b() {
            }
        });
        this.j = new com.kwad.sdk.core.webview.kwai.g(this.r);
        a(this.j);
        this.r.addJavascriptInterface(this.j, "KwaiAd");
        this.r.loadUrl(str);
        ax.a(this.G, null, 3000L);
    }

    private static ViewGroup.LayoutParams b(int i) {
        boolean z = com.kwad.sdk.core.config.b.q() == 0;
        float f2 = z ? 0.749f : 0.8f;
        int i2 = (int) (i * (z ? g : h));
        return new ViewGroup.LayoutParams((int) (i2 / f2), i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        com.kwad.sdk.core.d.a.a("InterstitialWebViewPresenter", "handleWebViewError " + str);
        this.p.removeCallbacksAndMessages(null);
        if (this.m) {
            return;
        }
        this.m = true;
        com.kwad.sdk.core.report.d.c(this.l, com.kwad.sdk.core.response.a.b.n(this.l), str);
        this.p.post(new Runnable() { // from class: com.kwad.sdk.d.kwai.d.15
            @Override // java.lang.Runnable
            public void run() {
                d.this.r();
                d.this.i.b();
            }
        });
    }

    private void f() {
        this.k = new com.kwad.sdk.core.webview.a();
        this.k.a(this.i.a);
        this.k.a = 0;
        this.k.b = null;
        this.k.d = this.n;
        this.k.e = this.r;
        this.k.c = null;
    }

    private void i() {
        if (this.j != null) {
            this.j.a();
            this.j = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        boolean zK = com.kwad.sdk.core.response.a.a.K(this.b);
        boolean zE = af.e(this.a);
        float fA = a(zE, zK);
        this.i.e.setRatio(fA);
        com.kwad.sdk.core.d.a.a("InterstitialWebViewPresenter", "replaceNativeView cardRatio: " + fA);
        ViewGroup.LayoutParams layoutParamsA = zE ? a(bb.b(this.a), zK) : b(bb.a(this.a));
        this.r.setVisibility(8);
        this.o.setVisibility(8);
        ViewGroup viewGroup = (ViewGroup) this.n.findViewById(R.id.ksad_interstitial_native);
        viewGroup.setVisibility(0);
        ViewParent parent = this.i.e.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(this.i.e);
        }
        viewGroup.addView(this.i.e);
        this.i.e.a(layoutParamsA.width, layoutParamsA.height);
        this.v.requestLayout();
        this.D = true;
    }

    private static float s() {
        return com.kwad.sdk.core.config.b.q() == 0 ? 0.749f : 0.8f;
    }

    private t t() {
        return new t(this.k, this.s);
    }

    private com.kwad.sdk.core.webview.jshandler.d u() {
        com.kwad.sdk.core.webview.jshandler.d dVar = new com.kwad.sdk.core.webview.jshandler.d(this.k);
        dVar.a(new d.b() { // from class: com.kwad.sdk.d.kwai.d.16
            @Override // com.kwad.sdk.core.webview.jshandler.d.b
            public void a(d.a aVar) {
                aVar.b = bb.a(d.this.a);
                aVar.a = bb.a(d.this.a);
            }
        });
        return dVar;
    }

    private u v() {
        this.C = u.a(q(), this.l);
        if (this.C != null) {
            this.C.a(new u.b() { // from class: com.kwad.sdk.d.kwai.d.17
                @Override // com.kwad.sdk.core.webview.jshandler.u.b
                public void a(int i) {
                    if (i == u.b) {
                        d.this.i.a(d.this.q(), d.this.l);
                        d.this.i.a(true, d.this.i.i);
                    }
                    d.this.E();
                }
            });
        }
        return this.C;
    }

    private n w() {
        n nVar = new n();
        nVar.a(new n.a() { // from class: com.kwad.sdk.d.kwai.d.18
            @Override // com.kwad.sdk.core.webview.a.n.a
            public void a(com.kwad.sdk.core.webview.a.kwai.b bVar) {
                d.this.v.setVideoSoundEnable(!bVar.a);
            }
        });
        return nVar;
    }

    @NonNull
    private j x() {
        return new j() { // from class: com.kwad.sdk.d.kwai.d.19
            @Override // com.kwad.sdk.core.webview.a.i, com.kwad.sdk.core.webview.kwai.a
            public void a(String str, @NonNull com.kwad.sdk.core.webview.kwai.c cVar) {
                super.a(str, cVar);
                com.kwad.sdk.core.webview.a.kwai.b bVar = new com.kwad.sdk.core.webview.a.kwai.b();
                bVar.a = !d.this.x.isVideoSoundEnable();
                cVar.a(bVar);
            }
        };
    }

    @NonNull
    private com.kwad.sdk.core.webview.jshandler.j y() {
        return new com.kwad.sdk.core.webview.jshandler.j(this.k, new j.b() { // from class: com.kwad.sdk.d.kwai.d.2
            @Override // com.kwad.sdk.core.webview.jshandler.j.b
            public void a(j.a aVar) {
            }
        });
    }

    private a.b z() {
        final com.kwad.sdk.core.webview.a.kwai.f fVar = new com.kwad.sdk.core.webview.a.kwai.f();
        this.z = new a.b() { // from class: com.kwad.sdk.d.kwai.d.3
            @Override // com.kwad.sdk.core.video.videoview.a.b
            public void a(long j) {
                fVar.b = false;
                fVar.c = false;
                fVar.a = (int) Math.ceil(j / 1000.0f);
                d.this.A.a(fVar);
            }

            @Override // com.kwad.sdk.core.video.videoview.a.b
            public void b_() {
                fVar.b = false;
                fVar.c = false;
                fVar.a = 0;
                d.this.A.a(fVar);
            }

            @Override // com.kwad.sdk.core.video.videoview.a.b
            public void e() {
                if (d.this.y.getVisibility() == 0) {
                    d.this.y.setVisibility(8);
                }
                fVar.b = false;
                fVar.c = false;
                fVar.a = (int) Math.ceil(d.this.v.getCurrentPosition() / 1000.0f);
                d.this.A.a(fVar);
            }

            @Override // com.kwad.sdk.core.video.videoview.a.b
            public void f() {
                fVar.b = false;
                fVar.c = true;
                fVar.a = com.kwad.sdk.core.response.a.a.b(com.kwad.sdk.core.response.a.d.j(d.this.k.a()));
                d.this.A.a(fVar);
            }
        };
        return this.z;
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.i = (c) p();
        this.D = false;
        this.q = this.i.c;
        this.x = this.i.g;
        this.s = this.i.d;
        this.l = this.i.a;
        this.b = com.kwad.sdk.core.response.a.d.j(this.l);
        this.u = com.kwad.sdk.core.response.a.a.S(this.b);
        this.v = this.i.i;
        this.E.a(this.F);
        this.E.a();
        f();
        e();
        this.i.a(new c.b() { // from class: com.kwad.sdk.d.kwai.d.13
            @Override // com.kwad.sdk.d.kwai.c.b
            public void a() {
                if (d.this.t == 1) {
                    d.this.r.reload();
                }
            }
        });
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        this.p.removeCallbacksAndMessages(null);
        this.E.b(this.F);
        this.E.b();
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.r = (KsAdWebView) m().findViewById(R.id.ksad_web_card_webView);
        this.r.setBackgroundColor(0);
        this.r.setVisibility(4);
        this.E = new com.kwad.sdk.core.j.f(this.r, 70);
        this.n = (KSFrameLayout) m().findViewById(R.id.ksad_container);
        this.o = (KSFrameLayout) m().findViewById(R.id.ksad_video_container);
        this.y = (ImageView) m().findViewById(R.id.ksad_video_first_frame_container);
        this.w = (ImageView) m().findViewById(R.id.ksad_interstitial_video_blur);
        this.a = q();
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void d_() {
        super.d_();
    }

    public void e() {
        String strN = com.kwad.sdk.core.response.a.b.n(this.l);
        if (TextUtils.isEmpty(strN)) {
            b("1");
        } else {
            a(strN);
        }
    }

    @Override // com.kwad.sdk.d.kwai.b
    public void g() {
        super.g();
        if (this.C != null) {
            this.C.e();
        }
    }

    @Override // com.kwad.sdk.d.kwai.b
    public void h() {
        super.h();
        if (this.C != null) {
            this.C.d();
        }
    }
}
