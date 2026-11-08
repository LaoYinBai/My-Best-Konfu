package com.kwad.sdk.d.kwai;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.kwad.sdk.R;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.video.videoview.a;
import com.kwad.sdk.d.kwai.c;
import com.kwad.sdk.widget.KsAutoCloseView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e extends b {
    private c a;
    private AdInfo b;
    private AdTemplate c;
    private boolean d = false;
    private com.kwad.sdk.core.video.videoview.b e;
    private KsAutoCloseView f;
    private boolean g;

    /* JADX INFO: Access modifiers changed from: private */
    public void a(View view, boolean z, int i, boolean z2) {
        this.a.a(view, z, i, z2);
        if (!com.kwad.sdk.core.config.b.aC() || this.a.c == null) {
            return;
        }
        this.a.c.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        if (this.e != null) {
            this.e.k();
        }
        this.a.a(z, this.e);
        this.a.c.dismiss();
        if (this.a.b != null) {
            this.a.b.onAdClosed();
        }
    }

    private static boolean a(Context context, AdInfo adInfo) {
        return adInfo.adInsertScreenInfo.autoCloseTime > 0 && !com.kwad.sdk.core.response.a.a.an(adInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        final com.kwad.sdk.d.a.b bVar = this.a.e;
        bVar.setAdTemplate(this.c);
        if (com.kwad.sdk.core.response.a.a.Q(this.b)) {
            bVar.a(com.kwad.sdk.core.response.a.a.I(this.b).materialUrl, this.c);
            bVar.a(true, true);
            bVar.a(false);
        } else {
            bVar.a(true);
            String strA = com.kwad.sdk.core.response.a.a.aa(this.b).a();
            if (TextUtils.isEmpty(strA)) {
                bVar.a(false, false);
            } else {
                bVar.a(strA, this.c);
                bVar.a(true, false);
            }
            this.e = this.a.i;
            if (this.e.getParent() != null) {
                ((ViewGroup) this.e.getParent()).removeView(this.e);
            }
            if (com.kwad.sdk.core.response.a.a.P(this.b)) {
                bVar.a(com.kwad.sdk.core.response.a.a.J(this.b).height / com.kwad.sdk.core.response.a.a.J(this.b).width, this.e);
            }
            bVar.b(this.a.g.isVideoSoundEnable());
            final int iB = com.kwad.sdk.core.response.a.a.b(this.b);
            this.a.a(new a.b() { // from class: com.kwad.sdk.d.kwai.e.4
                @Override // com.kwad.sdk.core.video.videoview.a.b
                @SuppressLint({"SetTextI18n"})
                public void a(long j) {
                    int i = iB - ((int) (j / 1000));
                    if (i >= 0) {
                        bVar.a(String.valueOf(i));
                    } else {
                        bVar.c();
                    }
                    if (j < com.kwad.sdk.core.config.b.aj() * 1000) {
                        return;
                    }
                    e.this.d = true;
                }

                @Override // com.kwad.sdk.core.video.videoview.a.b
                public void b_() {
                    bVar.a(true);
                }

                @Override // com.kwad.sdk.core.video.videoview.a.b
                public void e() {
                    bVar.a(false, false);
                }

                @Override // com.kwad.sdk.core.video.videoview.a.b
                public void f() {
                    e.this.e.setVisibility(8);
                    if (e.this.a.a(e.this.q())) {
                        return;
                    }
                    bVar.d();
                }
            });
            bVar.a(this.c, this.b);
            bVar.a(true);
        }
        this.a.a(q(), this.b, this.c, bVar.getBlurBgView());
        if (!a(q(), this.b)) {
            this.f.a(false);
        } else {
            this.f.a(true);
            f();
        }
    }

    private void f() {
        int iMin = this.b.adInsertScreenInfo.autoCloseTime;
        if (com.kwad.sdk.core.response.a.a.P(this.b)) {
            iMin = Math.min(iMin, com.kwad.sdk.core.response.a.a.b(this.b));
            this.a.e.c();
        }
        this.f.a(iMin);
        com.kwad.sdk.core.report.a.c(this.c, 165, null);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.a = (c) p();
        this.c = this.a.a;
        this.b = com.kwad.sdk.core.response.a.d.j(this.a.a);
        this.a.e.a(this.c);
        this.a.e.setViewListener(new com.kwad.sdk.d.a.d() { // from class: com.kwad.sdk.d.kwai.e.2
            @Override // com.kwad.sdk.d.a.d
            public void a() {
                e.this.a(false);
            }

            @Override // com.kwad.sdk.d.a.d
            public void a(View view) {
                e.this.a(view, false, 3, e.this.g);
            }

            @Override // com.kwad.sdk.d.a.d
            public void a(boolean z) {
                e.this.e.setVideoSoundEnable(z);
            }

            @Override // com.kwad.sdk.d.a.d
            public void b() {
            }

            @Override // com.kwad.sdk.d.a.d
            public void b(View view) {
                e.this.a(view, true, 1, e.this.g);
            }

            @Override // com.kwad.sdk.d.a.d
            public void b(boolean z) {
                e.this.g = z;
            }

            @Override // com.kwad.sdk.d.a.d
            public void c(View view) {
                e.this.a(view, true, 1, e.this.g);
            }

            @Override // com.kwad.sdk.d.a.d
            public void d(View view) {
                e.this.a(view, false, 3, e.this.g);
            }

            @Override // com.kwad.sdk.d.a.d
            public void e(View view) {
                e.this.a(view, false, 3, e.this.g);
            }

            @Override // com.kwad.sdk.d.a.d
            public void f(View view) {
                e.this.a(view, false, 2, e.this.g);
            }

            @Override // com.kwad.sdk.d.a.d
            public void g(View view) {
                e.this.a(view, false, 2, e.this.g);
            }

            @Override // com.kwad.sdk.d.a.d
            public void h(View view) {
                e.this.a(view, false, 2, e.this.g);
            }
        });
        this.a.a(new c.a() { // from class: com.kwad.sdk.d.kwai.e.3
            @Override // com.kwad.sdk.d.kwai.c.a
            public void a() {
                e.this.e();
            }
        });
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.f = (KsAutoCloseView) a(R.id.ksad_interstitial_auto_close);
        this.f.setViewListener(new KsAutoCloseView.a() { // from class: com.kwad.sdk.d.kwai.e.1
            @Override // com.kwad.sdk.widget.KsAutoCloseView.a
            public void a() {
                e.this.a(true);
            }

            @Override // com.kwad.sdk.widget.KsAutoCloseView.a
            public void b() {
                e.this.a(false);
            }
        });
    }

    @Override // com.kwad.sdk.d.kwai.b
    public void g() {
        super.g();
        this.f.setCountDownPaused(false);
    }

    @Override // com.kwad.sdk.d.kwai.b
    public void h() {
        super.h();
        this.f.setCountDownPaused(true);
    }
}
