package com.kwad.sdk.splashscreen.a;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.view.ViewStub;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.core.download.a.a;
import com.kwad.sdk.core.page.widget.webview.KsAdWebView;
import com.kwad.sdk.core.report.p;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.webview.jshandler.b;
import com.kwad.sdk.core.webview.jshandler.p;
import com.kwad.sdk.core.webview.jshandler.t;
import com.kwad.sdk.mvp.Presenter;
import com.kwad.sdk.utils.at;
import com.kwad.sdk.utils.ax;
import com.kwad.sdk.utils.bb;
import com.kwad.sdk.utils.be;
import com.kwad.sdk.utils.z;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class i extends Presenter implements com.kwad.sdk.splashscreen.f {
    private static String a = "SplashWebViewPresenter";
    private com.kwad.sdk.splashscreen.g b;
    private KsAdWebView c;
    private com.kwad.sdk.core.webview.a d;
    private com.kwad.sdk.core.webview.kwai.g e;
    private com.kwad.sdk.splashscreen.c.a f;
    private ViewStub g;
    private Runnable h = new Runnable() { // from class: com.kwad.sdk.splashscreen.a.i.1
        @Override // java.lang.Runnable
        public void run() {
            if (i.this.f != null) {
                i.this.f.a();
            }
        }
    };
    private AdInfo i;

    private void a(com.kwad.sdk.core.webview.kwai.g gVar) {
        gVar.a(new t(this.d, this.b.g));
        gVar.a(t());
        gVar.a(s());
        gVar.a(new com.kwad.sdk.core.webview.jshandler.f(this.d));
        gVar.a(new com.kwad.sdk.core.webview.a.g());
        gVar.a(new p(new p.b() { // from class: com.kwad.sdk.splashscreen.a.i.3
            @Override // com.kwad.sdk.core.webview.jshandler.p.b
            public void a(p.a aVar) {
                com.kwad.sdk.core.d.a.a(i.a, "updatePageStatus: " + aVar);
                if (aVar.a != 1) {
                    i.this.f.a();
                } else {
                    ax.b(i.this.h);
                    com.kwad.sdk.core.report.a.c(i.this.b.d, 123, null);
                }
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, int i, @Nullable p.a aVar) throws JSONException {
        if (this.b.a != null) {
            this.b.a.onAdClicked();
        }
        boolean z2 = aVar != null;
        boolean z3 = i == 1;
        com.kwad.sdk.core.download.a.a.a(new a.C0035a(this.b.e.getContext()).a(this.b.d).a(this.b.g).a(z3).a(i).a(aVar).c(z2).a(new a.b() { // from class: com.kwad.sdk.splashscreen.a.i.6
            @Override // com.kwad.sdk.core.download.a.a.b
            public void a() {
                String strD;
                if (com.kwad.sdk.core.response.a.a.B(com.kwad.sdk.core.response.a.d.j(i.this.b.d)) || (strD = i.this.b.d()) == null) {
                    return;
                }
                i.this.b.b = true;
                i.this.b.d.mMiniWindowId = strD;
            }
        }));
        if (z2) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.b != null) {
                if (this.b.f != null) {
                    jSONObject.put("duration", this.b.f.c());
                }
                com.kwad.sdk.core.report.a.a(this.b.d, z ? 153 : z3 ? 132 : 0, (z.a) null, jSONObject);
            }
        } catch (JSONException e) {
            com.kwad.sdk.core.d.a.a(e);
        }
    }

    public static boolean a(@NonNull AdInfo adInfo) {
        return adInfo.adSplashInfo.fullScreenClickSwitch == 1;
    }

    private void h() {
        this.d = new com.kwad.sdk.core.webview.a();
        this.d.a(this.b.d);
        this.d.a = 0;
        this.d.b = this.b.e;
        this.d.d = this.b.e;
        this.d.e = this.c;
        this.d.c = null;
        this.d.h = false;
        this.d.i = a(this.i);
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface", "JavascriptInterface"})
    private void i() {
        r();
        be.a(this.c).setAllowFileAccess(true);
        this.e = new com.kwad.sdk.core.webview.kwai.g(this.c);
        a(this.e);
        this.c.addJavascriptInterface(this.e, "KwaiAd");
    }

    private void r() {
        if (this.e != null) {
            this.e.a();
            this.e = null;
        }
    }

    private com.kwad.sdk.core.webview.jshandler.a s() {
        return new com.kwad.sdk.core.webview.jshandler.a(this.d, this.b.g, new b.c() { // from class: com.kwad.sdk.splashscreen.a.i.4
            @Override // com.kwad.sdk.core.webview.jshandler.b.c
            public void a(b.a aVar) throws JSONException {
                if (bb.a()) {
                    return;
                }
                if (aVar.a() || i.a(i.this.i)) {
                    p.a aVar2 = new p.a();
                    aVar2.h = aVar.d.a;
                    i.this.a(false, aVar.c, aVar2);
                }
            }
        });
    }

    private com.kwad.sdk.core.webview.jshandler.b t() {
        return new com.kwad.sdk.core.webview.jshandler.b(this.d, this.b.g, new b.c() { // from class: com.kwad.sdk.splashscreen.a.i.5
            @Override // com.kwad.sdk.core.webview.jshandler.b.c
            public void a(b.a aVar) throws JSONException {
                if (aVar.b || !i.a(i.this.i)) {
                    i.this.a(false, aVar.b ? 1 : 3, null);
                }
            }
        });
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.b = (com.kwad.sdk.splashscreen.g) p();
        this.c = (KsAdWebView) this.b.e.findViewById(R.id.ksad_splash_web_card_webView);
        this.g = (ViewStub) this.b.e.findViewById(R.id.ksad_splash_actionbar_native_stub);
        this.i = com.kwad.sdk.core.response.a.d.j(this.b.d);
        this.f = new com.kwad.sdk.splashscreen.c.a(this.g, this.c, com.kwad.sdk.core.response.a.c.g(this.b.d), this.b.g);
        this.f.a(this.b.d);
        this.f.a(this);
        this.c.setBackgroundColor(0);
        this.c.getBackground().setAlpha(0);
        this.c.setVisibility(0);
        f();
        ax.a(this.h, null, 1000L);
    }

    @Override // com.kwad.sdk.splashscreen.f
    public void a(boolean z, boolean z2) throws JSONException {
        com.kwad.sdk.core.d.a.a(a, "isClick: " + z + ", isActionBar: " + z2);
        a(!z, z2 ? 1 : 2, null);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        r();
        if (this.f != null) {
            this.f.b();
        }
    }

    protected String e() {
        if (q() == null) {
            return null;
        }
        File fileE = com.kwad.sdk.core.config.b.e(q());
        if (fileE.exists()) {
            return Uri.fromFile(fileE).toString();
        }
        if (com.kwad.sdk.core.config.b.a() != null) {
            return com.kwad.sdk.core.config.b.a().h5Url;
        }
        return null;
    }

    public void f() {
        String strE = e();
        com.kwad.sdk.core.d.a.a(a, "startPreloadWebView url: " + strE);
        if (at.a(strE)) {
            this.f.a();
            return;
        }
        this.c.setVisibility(0);
        h();
        i();
        this.c.loadUrl(strE);
        this.c.setHttpErrorListener(new KsAdWebView.d() { // from class: com.kwad.sdk.splashscreen.a.i.2
            @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
            public void a() {
            }

            @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
            public void a(int i, String str, String str2) {
                i.this.f.a();
            }

            @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
            public void b() {
            }
        });
    }
}
