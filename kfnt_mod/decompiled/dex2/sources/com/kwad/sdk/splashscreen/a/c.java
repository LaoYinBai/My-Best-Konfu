package com.kwad.sdk.splashscreen.a;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.kwad.sdk.R;
import com.kwad.sdk.core.page.AdLandPageActivityProxy;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.mvp.Presenter;
import com.kwad.sdk.utils.at;
import com.kwad.sdk.widget.SkipView;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c extends Presenter implements com.kwad.sdk.core.j.d {
    protected com.kwad.sdk.splashscreen.g a;
    private Handler b;
    private TextView c;
    private SkipView d;
    private int e;
    private boolean f;
    private AdInfo.AdPreloadInfo g;
    private long h;
    private AdInfo i;
    private boolean j = false;
    private View k;
    private ViewTreeObserver.OnGlobalLayoutListener l;

    private void a(SkipView skipView, AdInfo adInfo) {
        skipView.setTimerBtnVisible(i() ? false : com.kwad.sdk.core.response.a.a.aw(adInfo));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        if (a(this.i)) {
            this.k.setVisibility(z ? 0 : 8);
        }
    }

    public static boolean a(@NonNull AdInfo adInfo) {
        return adInfo.adSplashInfo != null && adInfo.adSplashInfo.splashCloseButtonNewStyleSwitch == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() throws JSONException {
        String strD;
        if (this.a.a == null || this.a.b) {
            strD = null;
        } else {
            strD = this.a.d();
            if (strD != null) {
                this.a.a.onSkippedAd();
                this.a.b = true;
                this.a.d.mMiniWindowId = strD;
            }
            com.kwad.sdk.core.report.a.a(this.a.d, 114, com.kwad.sdk.core.config.b.ao(), this.a.f != null ? (int) (this.a.f.c() / 1000) : 0);
        }
        if (strD == null && this.f && this.a.a != null) {
            this.a.a.onSkippedAd();
            JSONObject jSONObject = new JSONObject();
            if (this.a.f != null) {
                try {
                    jSONObject.put("duration", this.a.f.c());
                } catch (JSONException e) {
                    com.kwad.sdk.core.d.a.a(e);
                }
            }
            com.kwad.sdk.core.report.a.a(this.a.d, 1, jSONObject);
        }
    }

    private boolean i() {
        return this.a != null && this.a.b() && com.kwad.sdk.core.config.b.ao() > 0 && !at.a(com.kwad.sdk.core.config.b.am());
    }

    private void r() {
        if (a(this.i)) {
            this.d.getViewTreeObserver().addOnGlobalLayoutListener(s());
        }
    }

    private ViewTreeObserver.OnGlobalLayoutListener s() {
        this.l = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.kwad.sdk.splashscreen.a.c.4
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                ViewGroup.LayoutParams layoutParams = c.this.d.getLayoutParams();
                layoutParams.height = com.kwad.sdk.a.kwai.a.a(c.this.d.getContext(), 35.0f);
                int width = c.this.d.getWidth();
                c.this.d.setLayoutParams(layoutParams);
                ViewGroup.LayoutParams layoutParams2 = c.this.k.getLayoutParams();
                layoutParams2.width = width + com.kwad.sdk.a.kwai.a.a(c.this.d.getContext(), 66.0f);
                c.this.k.setLayoutParams(layoutParams2);
                if (c.this.l != null) {
                    c.this.d.getViewTreeObserver().removeOnGlobalLayoutListener(c.this.l);
                }
            }
        };
        return this.l;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void t() {
        if (!this.j && this.d != null) {
            if (this.d.f()) {
                com.kwad.sdk.core.report.a.c(this.a.d, 124, null);
                this.j = true;
            }
        }
    }

    @Override // com.kwad.sdk.mvp.Presenter
    @SuppressLint({"SetTextI18n"})
    protected void a() {
        super.a();
        com.kwad.sdk.core.d.a.a("SkipAdPresenter", "onBind");
        this.a = (com.kwad.sdk.splashscreen.g) p();
        this.c = (TextView) this.a.e.findViewById(R.id.ksad_splash_preload_tips);
        this.d = (SkipView) this.a.e.findViewById(R.id.ksad_splash_skip_view);
        this.k = this.a.e.findViewById(R.id.ksad_skip_view_area);
        this.b = new Handler(Looper.getMainLooper());
        this.i = com.kwad.sdk.core.response.a.d.j(this.a.d);
        int i = this.i.adSplashInfo.imageDisplaySecond;
        this.d.setTimerPrefixText(com.kwad.sdk.core.config.b.at());
        this.d.setTimerSecond(i);
        if (!com.kwad.sdk.core.response.a.a.P(this.i)) {
            this.d.a();
        }
        if (!this.a.d.adInfoList.isEmpty()) {
            this.g = this.i.adPreloadInfo;
            AdInfo.AdSplashInfo adSplashInfo = this.i.adSplashInfo;
            this.e = adSplashInfo.skipSecond;
            if (this.e == 5) {
                this.e = 0;
            }
            if (this.a.b()) {
                adSplashInfo.skipSecond = 0;
                this.e = 0;
            }
        }
        this.c.setVisibility(8);
        if (this.g == null || at.a(this.g.preloadTips)) {
            this.c.setVisibility(8);
        } else {
            this.c.setVisibility(0);
            this.c.setText(this.g.preloadTips);
        }
        this.h = SystemClock.elapsedRealtime();
        this.a.i.a(this);
        if (!this.a.b() || at.a(com.kwad.sdk.core.config.b.am())) {
            this.d.setSkipText(com.kwad.sdk.core.response.a.a.av(this.i));
        } else {
            this.d.setSkipText(com.kwad.sdk.core.config.b.am() + " " + com.kwad.sdk.core.config.b.ao());
        }
        this.d.setVisibility(4);
        if (a(this.i)) {
            this.k.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.splashscreen.a.c.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) throws JSONException {
                    c.this.h();
                }
            });
        }
        this.d.setOnViewListener(new SkipView.a() { // from class: com.kwad.sdk.splashscreen.a.c.2
            @Override // com.kwad.sdk.widget.SkipView.a
            public void a() throws JSONException {
                c.this.h();
            }

            @Override // com.kwad.sdk.widget.SkipView.a
            public void b() {
                c.this.g();
            }
        });
        a(this.d, this.i);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        this.a.i.b(this);
        if (this.b != null) {
            this.b.removeCallbacksAndMessages(null);
        }
    }

    @Override // com.kwad.sdk.core.j.d
    public void e() {
        com.kwad.sdk.core.d.a.a("SkipAdPresenter", "onPageVisible");
        this.b.removeCallbacksAndMessages(null);
        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(this.a.d);
        if (adInfoJ.adSplashInfo.skipSecond == -1) {
            this.d.e();
            a(false);
        } else if (this.e >= 0 && !this.f) {
            this.b.postDelayed(new Runnable() { // from class: com.kwad.sdk.splashscreen.a.c.3
                @Override // java.lang.Runnable
                @SuppressLint({"SetTextI18n"})
                public void run() {
                    c.this.d.b();
                    c.this.a(true);
                    c.this.t();
                    c.this.e = 0;
                    c.this.f = true;
                }
            }, this.e * 1000);
        }
        if (!com.kwad.sdk.core.response.a.a.P(adInfoJ)) {
            this.d.d();
        }
        r();
    }

    @Override // com.kwad.sdk.core.j.d
    public void f() {
        com.kwad.sdk.core.d.a.a("SkipAdPresenter", "onPageInvisible");
        if (com.kwad.sdk.core.response.a.a.P(this.i)) {
            return;
        }
        this.d.c();
    }

    public void g() {
        this.b.post(new Runnable() { // from class: com.kwad.sdk.splashscreen.a.c.5
            @Override // java.lang.Runnable
            public void run() {
                if (com.kwad.sdk.core.download.kwai.b.a() || AdLandPageActivityProxy.isDownloadDialogShowing()) {
                    c.this.b.postDelayed(this, 1000L);
                } else if (c.this.a.a != null) {
                    c.this.a.a.onAdShowEnd();
                }
            }
        });
    }
}
