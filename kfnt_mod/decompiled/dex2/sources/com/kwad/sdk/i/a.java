package com.kwad.sdk.i;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.core.page.widget.webview.KsAdWebView;
import com.kwad.sdk.core.report.p;
import com.kwad.sdk.core.response.a.d;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.view.AdBaseFrameLayout;
import com.kwad.sdk.core.webview.jshandler.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    protected View a;
    private AdBaseFrameLayout c;
    private long d;
    private Activity e;
    private ImageView f;
    private KsAdWebView g;
    private boolean h;
    private boolean i;
    private AdTemplate j;
    private InterfaceC0064a k;
    private FrameLayout m;

    @Nullable
    private b.c n;
    private b o;
    private boolean b = true;
    private Handler l = new Handler(Looper.getMainLooper());
    private boolean p = false;
    private boolean q = false;
    private boolean r = false;

    /* renamed from: com.kwad.sdk.i.a$a, reason: collision with other inner class name */
    public interface InterfaceC0064a {
        void a();
    }

    public interface b {
        void a(boolean z);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void d() {
        this.m.removeAllViews();
        this.m.setVisibility(4);
        this.a = com.kwad.sdk.a.kwai.a.a((ViewGroup) this.m, b(), true);
        this.g = (KsAdWebView) this.m.findViewById(R.id.ksad_web_card_webView);
        this.g.setBackgroundColor(-1);
        p.a aVar = new p.a();
        aVar.n = 1;
        this.g.setClientParams(aVar);
        this.g.setTemplateData(this.j);
        this.g.setHttpErrorListener(new KsAdWebView.d() { // from class: com.kwad.sdk.i.a.1
            @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
            public void a() {
            }

            @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
            public void a(int i, String str, String str2) {
                a.this.b = true;
                if (a.this.o != null) {
                    a.this.o.a(a.this.f());
                }
            }

            @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
            public void b() {
                if (a.this.o != null) {
                    a.this.o.a(a.this.f());
                }
            }
        });
        this.g.setDeepLinkEnabled(false);
        this.g.setInsideDownloadEnable(false);
        this.g.setDownloadListener(new DownloadListener() { // from class: com.kwad.sdk.i.a.2
            @Override // android.webkit.DownloadListener
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                a.this.q = true;
                if (a.this.o != null) {
                    a.this.o.a(a.this.f());
                }
            }
        });
        this.g.setDeepLinkListener(new KsAdWebView.b() { // from class: com.kwad.sdk.i.a.3
            @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.b
            public void a() {
                a.this.p = true;
                if (a.this.o != null) {
                    a.this.o.a(a.this.f());
                }
            }

            @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.b
            public void b() {
                a.this.p = true;
                if (a.this.o != null) {
                    a.this.o.a(a.this.f());
                }
            }
        });
        this.f = (ImageView) this.a.findViewById(R.id.ksad_end_close_btn);
        this.f.setVisibility(8);
        this.f.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.i.a.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                a.this.e();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.g != null) {
            this.g.c();
        }
        if (this.k != null) {
            this.k.a();
        }
        if (this.e != null) {
            this.e.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean f() {
        return this.r ? !this.b : (this.b || this.p || this.q) ? false : true;
    }

    private boolean g() {
        if (!f()) {
            if (this.m != null) {
                this.m.setVisibility(8);
            }
            return false;
        }
        if (this.m == null) {
            return true;
        }
        this.m.setVisibility(0);
        return true;
    }

    public a a(boolean z) {
        this.i = z;
        return this;
    }

    protected String a(AdTemplate adTemplate) {
        return com.kwad.sdk.core.response.a.a.G(d.j(adTemplate));
    }

    public void a() {
        this.m.setVisibility(4);
        String strA = a(this.j);
        if (TextUtils.isEmpty(strA)) {
            return;
        }
        this.g.loadUrl(strA);
    }

    public void a(Activity activity) {
        this.e = activity;
    }

    public void a(FrameLayout frameLayout, AdBaseFrameLayout adBaseFrameLayout, AdTemplate adTemplate) {
        this.m = frameLayout;
        this.c = adBaseFrameLayout;
        this.j = adTemplate;
        d();
        this.b = false;
    }

    public void a(b.c cVar) {
        this.n = cVar;
    }

    public void a(InterfaceC0064a interfaceC0064a) {
        this.k = interfaceC0064a;
    }

    public void a(b bVar) {
        this.o = bVar;
    }

    protected int b() {
        return R.layout.ksad_ad_landingpage_layout;
    }

    public boolean c() {
        boolean zG = g();
        this.r = true;
        if (zG && this.e != null) {
            this.g.setDeepLinkEnabled(true);
            this.g.setInsideDownloadEnable(true);
            this.c.setDispatchTouchListener(new View.OnTouchListener() { // from class: com.kwad.sdk.i.a.5
                @Override // android.view.View.OnTouchListener
                @SuppressLint({"ClickableViewAccessibility"})
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getX() > a.this.f.getX() && motionEvent.getX() - a.this.f.getX() < a.this.f.getWidth() && motionEvent.getY() > a.this.f.getY() && motionEvent.getY() - a.this.f.getY() < a.this.f.getHeight()) {
                        com.kwad.sdk.core.d.a.a("LandingPageWebCard", "onClick backIcon");
                        return false;
                    }
                    if (motionEvent.getAction() == 0) {
                        a.this.d = SystemClock.elapsedRealtime();
                    } else if (motionEvent.getAction() == 1) {
                        long jElapsedRealtime = SystemClock.elapsedRealtime() - a.this.d;
                        if (a.this.d > 0 && jElapsedRealtime > 60 && jElapsedRealtime < 500) {
                            com.kwad.sdk.core.report.a.a(a.this.j, 155, a.this.c.getTouchCoords());
                            if (!a.this.h) {
                                a.this.h = true;
                                if (a.this.n != null) {
                                    b.a aVar = new b.a();
                                    aVar.c = 3;
                                    a.this.n.a(aVar);
                                }
                            }
                        }
                        a.this.d = 0L;
                    }
                    return false;
                }
            });
            long jT = com.kwad.sdk.core.config.b.t() * 1000;
            if (jT == 0 || !this.i) {
                this.f.setVisibility(0);
            } else {
                this.l.postDelayed(new Runnable() { // from class: com.kwad.sdk.i.a.6
                    @Override // java.lang.Runnable
                    public void run() {
                        if (a.this.e == null || a.this.e.isFinishing()) {
                            return;
                        }
                        a.this.f.setVisibility(0);
                        a.this.f.setAlpha(0.0f);
                        a.this.f.animate().alpha(1.0f).setDuration(500L).start();
                    }
                }, jT);
            }
            if (this.g != null) {
                this.g.b();
            }
        }
        return zG;
    }
}
