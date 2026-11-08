package com.kwad.sdk.core.download.kwai;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.kwad.sdk.R;
import com.kwad.sdk.core.page.widget.webview.KsAdWebView;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.webview.jshandler.b;
import com.kwad.sdk.core.webview.jshandler.i;
import com.kwad.sdk.core.webview.jshandler.j;
import com.kwad.sdk.core.webview.jshandler.k;
import com.kwad.sdk.core.webview.jshandler.p;
import com.kwad.sdk.core.webview.jshandler.s;
import com.kwad.sdk.core.webview.jshandler.t;
import com.kwad.sdk.utils.be;
import com.kwad.sdk.utils.u;
import com.kwad.sdk.utils.v;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f extends c {
    private KsAdWebView b;
    private j.a c;
    private AdInfo.DownloadSafeInfo d;
    private com.kwad.sdk.core.download.a.b e;
    private com.kwad.sdk.core.webview.kwai.g f;
    private com.kwad.sdk.core.webview.a g;
    private s h;
    private String i;
    private Runnable j;
    private boolean k = false;
    private b.c l = new b.c() { // from class: com.kwad.sdk.core.download.kwai.f.1
        @Override // com.kwad.sdk.core.webview.jshandler.b.c
        public void a(b.a aVar) {
            com.kwad.sdk.core.d.a.a("DownloadTipsDialogWebCardPresenter", "onAdClicked convertBridgeClicked: " + f.this.k);
            f.this.k = true;
        }
    };
    private j.b m = new j.b() { // from class: com.kwad.sdk.core.download.kwai.f.4
        @Override // com.kwad.sdk.core.webview.jshandler.j.b
        public void a(j.a aVar) {
            com.kwad.sdk.core.d.a.a("DownloadTipsDialogWebCardPresenter", "onAdFrameValid=" + aVar);
            f.this.c = aVar;
            f.this.b.setTranslationY((float) (aVar.a + aVar.d));
        }
    };
    private i.b n = new i.b() { // from class: com.kwad.sdk.core.download.kwai.f.5
        @Override // com.kwad.sdk.core.webview.jshandler.i.b
        public void a(int i) {
            com.kwad.sdk.core.d.a.a("DownloadTipsDialogWebCardPresenter", "handleWebCardHide");
            f.this.v();
        }
    };
    private p.b o = new p.b() { // from class: com.kwad.sdk.core.download.kwai.f.6
        @Override // com.kwad.sdk.core.webview.jshandler.p.b
        public void a(p.a aVar) {
            com.kwad.sdk.core.d.a.c("DownloadTipsDialogWebCardPresenter", "updatePageStatus mPageState: " + aVar);
            if (aVar.a == 1) {
                f.this.u();
                return;
            }
            f.this.v();
            if (f.this.q() != null) {
                u.a(f.this.q(), v.a(f.this.q()));
            }
        }
    };

    public f(String str) {
        this.i = str;
    }

    private void a(com.kwad.sdk.core.webview.kwai.g gVar) {
        com.kwad.sdk.core.d.a.a("DownloadTipsDialogWebCardPresenter", "registerWebCardHandler");
        gVar.a(new com.kwad.sdk.core.webview.jshandler.b(this.g, this.e, this.l));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.a(this.g, this.e, this.l));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.f(this.g));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.g(this.g));
        gVar.a(new com.kwad.sdk.core.webview.jshandler.d(this.g));
        gVar.a(new j(this.g, this.m));
        gVar.a(new p(this.o));
        this.h = new s();
        gVar.a(this.h);
        gVar.a(new t(this.g, this.e));
        gVar.a(new i(this.n));
        gVar.a(new k(this.g));
    }

    private void e() {
        this.b.setVisibility(8);
        this.b.a();
        t();
    }

    private void f() {
        this.g = new com.kwad.sdk.core.webview.a();
        this.g.a(this.a.d);
        this.g.a = this.a.a;
        this.g.b = this.a.c;
        this.g.d = this.a.c;
        this.g.e = this.b;
    }

    private void g() {
        i();
        this.b.postDelayed(h(), 1500L);
        this.b.setBackgroundColor(0);
        this.b.getBackground().setAlpha(0);
        this.b.setVisibility(0);
        com.kwad.sdk.core.d.a.a("DownloadTipsDialogWebCardPresenter", "downloadPanelUrl=" + this.d.windowPopUrl);
    }

    private Runnable h() {
        this.j = new Runnable() { // from class: com.kwad.sdk.core.download.kwai.f.2
            @Override // java.lang.Runnable
            public void run() {
                f.this.v();
                if (f.this.q() != null) {
                    u.a(f.this.q(), v.a(f.this.q()));
                }
            }
        };
        return this.j;
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface", "JavascriptInterface"})
    private void i() {
        com.kwad.sdk.core.d.a.a("DownloadTipsDialogWebCardPresenter", "setupJsBridge");
        t();
        be.b(this.b);
        this.b.setHttpErrorListener(new KsAdWebView.d() { // from class: com.kwad.sdk.core.download.kwai.f.3
            @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
            public void a() {
            }

            @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
            public void a(int i, String str, String str2) {
            }

            @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
            public void b() {
            }
        });
        this.f = new com.kwad.sdk.core.webview.kwai.g(this.b);
        a(this.f);
        this.b.addJavascriptInterface(this.f, "KwaiAd");
        String strR = r();
        com.kwad.sdk.core.d.a.a("DownloadTipsDialogWebCardPresenter", "getUrl: " + strR);
        this.b.loadUrl(strR);
    }

    private String r() {
        if (!TextUtils.isEmpty(this.i)) {
            return this.i;
        }
        String strS = s();
        return TextUtils.isEmpty(strS) ? this.d.windowPopUrl : strS;
    }

    @NonNull
    private String s() {
        String string;
        String str;
        StringBuilder sb;
        String str2;
        if (q() != null) {
            File fileC = com.kwad.sdk.core.config.b.c(q());
            if (fileC.exists()) {
                string = Uri.fromFile(fileC).toString();
                str = "DownloadTipsDialogWebCardPresenter";
                sb = new StringBuilder();
                str2 = "getPreloadUrl preloadUrl ";
            } else {
                if (com.kwad.sdk.core.config.b.c() != null) {
                    string = com.kwad.sdk.core.config.b.c().h5Url;
                    str = "DownloadTipsDialogWebCardPresenter";
                    sb = new StringBuilder();
                    str2 = "getPreloadUrl getDownloadPopWindowConfig ";
                }
                string = null;
            }
            sb.append(str2);
            sb.append(string);
            com.kwad.sdk.core.d.a.a(str, sb.toString());
        } else {
            string = null;
        }
        return string == null ? "" : string;
    }

    private void t() {
        if (this.f != null) {
            this.f.a();
            this.f = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        if (this.j != null) {
            this.b.removeCallbacks(this.j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        com.kwad.sdk.core.d.a.a("DownloadTipsDialogWebCardPresenter", "hideWithOutAnimation  convertBridgeClicked: " + this.k);
        if (this.b.getVisibility() != 0) {
            return;
        }
        if (this.h != null) {
            this.h.e();
        }
        this.b.setVisibility(4);
        if (this.h != null) {
            this.h.f();
        }
        if (this.a.b != null) {
            this.a.b.a(this.k);
        }
    }

    @Override // com.kwad.sdk.core.download.kwai.c, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.d = com.kwad.sdk.core.response.a.a.ac(com.kwad.sdk.core.response.a.d.j(this.a.d));
        this.e = this.a.e;
        f();
        g();
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        e();
        u();
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.b = (KsAdWebView) a(R.id.ksad_download_tips_web_card_webView);
    }
}
