package com.kwad.sdk.core.page;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.kwad.sdk.R;
import com.kwad.sdk.core.page.widget.webview.KsAdWebView;
import com.kwad.sdk.core.report.p;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.webview.jshandler.b;
import com.kwad.sdk.core.webview.jshandler.f;
import com.kwad.sdk.core.webview.jshandler.k;
import com.kwad.sdk.core.webview.jshandler.p;
import com.kwad.sdk.core.webview.jshandler.q;
import com.kwad.sdk.core.webview.jshandler.r;
import com.kwad.sdk.core.webview.jshandler.s;
import com.kwad.sdk.core.webview.jshandler.t;
import com.kwad.sdk.core.webview.jshandler.y;
import com.kwad.sdk.core.webview.kwai.g;
import com.kwad.sdk.core.webview.kwai.h;
import com.kwad.sdk.utils.at;
import com.meizu.cloud.pushsdk.constants.PushConstants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c implements p.b {
    private boolean B;
    private Context a;
    private AdTemplate b;
    private int c;
    private View d;
    private KsAdWebView e;
    private ImageView f;
    private ImageView g;
    private LinearLayout h;
    private TextView i;
    private TextView j;
    private TextView k;
    private TextView l;
    private LinearLayout o;
    private TextView p;
    private ImageView q;
    private ViewGroup r;
    private g s;
    private h t;
    private com.kwad.sdk.core.webview.a u;
    private a w;
    private s x;
    private boolean z;
    private Handler m = new Handler(Looper.getMainLooper());
    private boolean n = true;
    private int v = com.kwad.sdk.core.config.b.aA();
    private int y = -1;
    private boolean A = false;

    public interface a {
        void onBackBtnClicked(View view);

        void onCloseBtnClicked(View view);
    }

    public static class b {
        private final boolean a;
        private final boolean b;
        private final String c;
        private final String d;

        private b(C0043c c0043c) {
            this.a = c0043c.a;
            this.b = c0043c.b;
            this.c = c0043c.d;
            this.d = c0043c.c;
        }
    }

    /* renamed from: com.kwad.sdk.core.page.c$c, reason: collision with other inner class name */
    public static class C0043c {
        private boolean a = true;
        private boolean b = true;
        private String c;
        private String d;

        public b a() {
            return new b(this);
        }

        public C0043c a(String str) {
            this.c = str;
            return this;
        }

        public C0043c a(boolean z) {
            this.a = z;
            return this;
        }

        public C0043c b(String str) {
            this.d = str;
            return this;
        }

        public C0043c b(boolean z) {
            this.b = z;
            return this;
        }
    }

    public c(Context context, AdTemplate adTemplate, int i, boolean z) {
        this.z = false;
        this.a = context;
        this.b = adTemplate;
        this.c = i;
        this.z = z;
        this.d = LayoutInflater.from(this.a).inflate(R.layout.ksad_activity_ad_webview, (ViewGroup) null);
    }

    private <T extends View> T a(int i) {
        if (this.d == null) {
            return null;
        }
        return (T) this.d.findViewById(i);
    }

    private void a(WebView webView) {
        webView.getSettings().setAllowFileAccess(true);
    }

    private void a(KsAdWebView ksAdWebView) {
        m();
        this.t = new h(ksAdWebView, this.u);
        ksAdWebView.addJavascriptInterface(this.t, "KwaiAdForThird");
    }

    private void a(KsAdWebView ksAdWebView, g gVar) {
        this.x = new s();
        gVar.a(this.x);
        gVar.a(new p(this));
        com.kwad.sdk.core.download.a.b bVar = new com.kwad.sdk.core.download.a.b(this.b);
        gVar.a(new com.kwad.sdk.core.webview.jshandler.b(this.u, bVar, n(), true));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.a(this.u, bVar, n(), true));
        gVar.a(new t(this.u, bVar));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.h(this.u));
        gVar.a(new q(this.u));
        gVar.a(new k(this.u));
        gVar.a(new f(this.u));
        gVar.a(new y(new y.a() { // from class: com.kwad.sdk.core.page.c.7
            @Override // com.kwad.sdk.core.webview.jshandler.y.a
            public void a() {
                String strA = com.kwad.sdk.core.response.a.a.a(c.this.a, com.kwad.sdk.core.response.a.d.j(c.this.b));
                if (at.a(strA)) {
                    return;
                }
                com.kwad.sdk.core.download.kwai.b.a(c.this.a, c.this.b, strA);
            }
        }));
        final r rVar = new r();
        ksAdWebView.setDeepLinkListener(new KsAdWebView.b() { // from class: com.kwad.sdk.core.page.c.8
            @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.b
            public void a() {
                rVar.c();
            }

            @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.b
            public void b() {
                rVar.d();
            }
        });
        gVar.a(rVar);
    }

    private void b(b bVar) {
        if (bVar == null) {
            return;
        }
        if (this.g != null) {
            this.g.setVisibility(bVar.b ? 0 : 8);
        }
        if (this.f != null) {
            this.f.setVisibility(bVar.a ? 0 : 8);
        }
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface", "JavascriptInterface"})
    private void b(KsAdWebView ksAdWebView) {
        m();
        this.s = new g(ksAdWebView);
        a(ksAdWebView, this.s);
        ksAdWebView.addJavascriptInterface(this.s, "KwaiAd");
    }

    private void c(KsAdWebView ksAdWebView) {
        p.a aVar = new p.a();
        aVar.n = 0;
        aVar.w = this.c;
        ksAdWebView.setClientParams(aVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        this.j.setText(i());
        this.l.setText(j());
    }

    private String i() {
        StringBuilder sb;
        int i = this.v / 60;
        if (i >= 10) {
            sb = new StringBuilder();
        } else {
            sb = new StringBuilder();
            sb.append(PushConstants.PUSH_TYPE_NOTIFY);
        }
        sb.append(i);
        sb.append("");
        return sb.toString();
    }

    private String j() {
        StringBuilder sb;
        int i = this.v % 60;
        if (i >= 10) {
            sb = new StringBuilder();
        } else {
            sb = new StringBuilder();
            sb.append(PushConstants.PUSH_TYPE_NOTIFY);
        }
        sb.append(i);
        sb.append("");
        return sb.toString();
    }

    private void k() {
        this.o = (LinearLayout) a(R.id.ksad_web_tip_bar);
        if (this.B) {
            this.o.setVisibility(8);
            return;
        }
        this.p = (TextView) a(R.id.ksad_web_tip_bar_textview);
        this.q = (ImageView) a(R.id.ksad_web_tip_close_btn);
        this.q.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.core.page.c.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                c.this.o.setVisibility(8);
            }
        });
        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(this.b);
        boolean zAf = com.kwad.sdk.core.response.a.a.af(adInfoJ);
        String strAb = com.kwad.sdk.core.response.a.a.ab(adInfoJ);
        if (!zAf) {
            this.o.setVisibility(8);
            return;
        }
        this.o.setVisibility(0);
        this.p.setText(strAb);
        this.p.setSelected(true);
    }

    private void l() {
        this.u = new com.kwad.sdk.core.webview.a();
        this.u.a(this.b);
        this.u.a = 0;
        this.u.e = this.e;
        this.u.d = this.r;
    }

    private void m() {
        if (this.s != null) {
            this.s.a();
            this.s = null;
        }
        if (this.t != null) {
            this.t.a();
            this.t = null;
        }
    }

    @NonNull
    private b.c n() {
        return new b.c() { // from class: com.kwad.sdk.core.page.c.9
            @Override // com.kwad.sdk.core.webview.jshandler.b.c
            public void a(b.a aVar) {
            }
        };
    }

    static /* synthetic */ int o(c cVar) {
        int i = cVar.v;
        cVar.v = i - 1;
        return i;
    }

    public View a() {
        return this.d;
    }

    public void a(a aVar) {
        this.w = aVar;
    }

    public void a(b bVar) {
        AdInfo.AdBaseInfo adBaseInfo;
        this.B = (TextUtils.isEmpty(bVar.d) || TextUtils.isEmpty(bVar.c)) ? false : true;
        this.e = (KsAdWebView) a(R.id.ksad_video_webview);
        TextView textView = (TextView) a(R.id.ksad_kwad_titlebar_title);
        this.f = (ImageView) a(R.id.ksad_kwad_web_navi_back);
        a((WebView) this.e);
        c(this.e);
        this.e.setTemplateData(this.b);
        this.e.loadUrl(bVar.c);
        this.e.b();
        this.e.setOnWebViewScrollChangeListener(new KsAdWebView.c() { // from class: com.kwad.sdk.core.page.c.1
            @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.c
            public void a(View view, int i, int i2, int i3, int i4) {
                if (c.this.B && c.this.o.getVisibility() == 0) {
                    c.this.o.setVisibility(8);
                }
            }
        });
        this.e.setHttpErrorListener(new KsAdWebView.d() { // from class: com.kwad.sdk.core.page.c.2
            @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
            public void a() {
            }

            @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
            public void a(int i, String str, String str2) {
                c.this.A = false;
            }

            @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
            public void b() {
                c.this.A = true;
                if (c.this.z) {
                    c.this.e();
                }
            }
        });
        this.g = (ImageView) a(R.id.ksad_kwad_web_navi_close);
        this.r = (ViewGroup) a(R.id.ksad_landing_page_root);
        textView.setText(this.B ? bVar.d : (this.b.adInfoList == null || this.b.adInfoList.size() <= 0 || this.b.adInfoList.get(0) == null || (adBaseInfo = this.b.adInfoList.get(0).adBaseInfo) == null || TextUtils.isEmpty(adBaseInfo.productName)) ? "详情页面" : adBaseInfo.productName);
        this.g.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.core.page.c.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (c.this.e != null && c.this.e.canGoBack()) {
                    c.this.e.goBack();
                    com.kwad.sdk.core.report.a.l(c.this.b);
                } else if (c.this.w != null) {
                    c.this.w.onCloseBtnClicked(view);
                }
            }
        });
        this.f.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.core.page.c.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (c.this.e != null && c.this.e.canGoBack()) {
                    c.this.e.goBack();
                    com.kwad.sdk.core.report.a.l(c.this.b);
                } else if (c.this.w != null) {
                    c.this.w.onBackBtnClicked(view);
                }
            }
        });
        if (this.b == null) {
            return;
        }
        k();
        if (com.kwad.sdk.core.response.a.a.d(com.kwad.sdk.core.response.a.d.j(this.b))) {
            l();
            a(this.e);
        } else if (this.c == 4) {
            this.b.interactLandingPageShowing = true;
            l();
            b(this.e);
        }
        b(bVar);
        this.h = (LinearLayout) a(R.id.ksad_reward_land_page_open_tip);
        this.i = (TextView) a(R.id.ksad_reward_land_page_open_desc);
        this.j = (TextView) a(R.id.ksad_reward_land_page_open_minute);
        this.k = (TextView) a(R.id.ksad_reward_land_page_open_colon);
        this.l = (TextView) a(R.id.ksad_reward_land_page_open_second);
        if (com.kwad.sdk.core.response.a.d.v(this.b)) {
            this.h.setVisibility(0);
            if (!this.b.mRewardVerifyCalled) {
                h();
                this.m.postDelayed(new Runnable() { // from class: com.kwad.sdk.core.page.c.5
                    @Override // java.lang.Runnable
                    @SuppressLint({"SetTextI18n"})
                    public void run() {
                        if (c.this.n) {
                            c.this.m.postDelayed(this, 500L);
                            return;
                        }
                        if (c.this.v <= 0) {
                            c.this.i.setText("任务已完成");
                            c.this.j.setVisibility(8);
                            c.this.k.setVisibility(8);
                            c.this.l.setVisibility(8);
                            com.kwad.sdk.reward.d.a().b();
                        } else {
                            c.this.h();
                            c.this.m.postDelayed(this, 1000L);
                        }
                        c.o(c.this);
                    }
                }, 1000L);
            } else {
                this.i.setText("任务已完成");
                this.j.setVisibility(8);
                this.k.setVisibility(8);
                this.l.setVisibility(8);
            }
        }
    }

    @Override // com.kwad.sdk.core.webview.jshandler.p.b
    public void a(p.a aVar) {
        this.y = aVar.a;
    }

    public View b() {
        return this.f;
    }

    public View c() {
        return this.g;
    }

    public boolean d() {
        return this.y == 1;
    }

    public void e() {
        if (this.A) {
            if (this.x != null) {
                this.x.c();
            }
            if (this.d != null) {
                this.d.setVisibility(0);
            }
            if (this.x != null) {
                this.x.d();
            }
            if (com.kwad.sdk.core.response.a.d.v(this.b)) {
                this.n = false;
            }
        }
    }

    public void f() {
        if (this.x != null) {
            this.x.e();
        }
        if (this.d != null) {
            this.d.setVisibility(8);
        }
        if (this.x != null) {
            this.x.f();
        }
        if (com.kwad.sdk.core.response.a.d.v(this.b)) {
            this.n = true;
        }
    }

    public void g() {
        if (this.e != null) {
            this.e.c();
            this.e = null;
        }
        if (com.kwad.sdk.core.response.a.d.v(this.b)) {
            this.m.removeCallbacksAndMessages(null);
        }
    }
}
