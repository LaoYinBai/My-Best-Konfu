package com.kwad.sdk.splashscreen.a;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.kwad.sdk.R;
import com.kwad.sdk.contentalliance.detail.video.DetailVideoView;
import com.kwad.sdk.core.imageloader.KSImageLoader;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.mvp.Presenter;
import com.kwad.sdk.utils.at;
import com.kwad.sdk.utils.av;
import com.kwad.sdk.utils.j;
import com.kwad.sdk.widget.SkipView;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class j extends Presenter implements com.kwad.sdk.core.j.d {
    protected com.kwad.sdk.splashscreen.g a;
    private ImageView f;
    private SkipView g;
    private AdInfo h;
    private volatile boolean b = false;
    private boolean c = false;
    private boolean d = false;
    private boolean e = false;
    private j.a i = new j.a() { // from class: com.kwad.sdk.splashscreen.a.j.1
        @Override // com.kwad.sdk.utils.j.a
        public void a() {
            j.this.b = false;
            if (j.this.f != null) {
                j.this.f.post(new Runnable() { // from class: com.kwad.sdk.splashscreen.a.j.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (j.this.a.f != null) {
                            j.this.a.f.a(j.this.b, false);
                        }
                        if (j.this.a != null) {
                            AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(j.this.a.d);
                            String str = j.this.b ? adInfoJ.adSplashInfo.speakerIconUrl : adInfoJ.adSplashInfo.speakerMuteIconUrl;
                            if (TextUtils.isEmpty(str)) {
                                j.this.f.setImageDrawable(j.this.q().getResources().getDrawable(R.drawable.ksad_splash_sound_selector));
                            } else {
                                KSImageLoader.loadImage(j.this.f, str, j.this.a.d);
                            }
                            j.this.f.setSelected(false);
                        }
                    }
                });
            }
        }

        @Override // com.kwad.sdk.utils.j.a
        public void b() {
        }
    };
    private com.kwad.sdk.contentalliance.detail.video.d j = new com.kwad.sdk.contentalliance.detail.video.d() { // from class: com.kwad.sdk.splashscreen.a.j.2
        private boolean b = false;
        private int c = com.kwad.sdk.core.config.b.ao();
        private String d = com.kwad.sdk.core.config.b.am();
        private String e = com.kwad.sdk.core.config.b.at();
        private int f = com.kwad.sdk.core.config.b.as();

        @Override // com.kwad.sdk.contentalliance.detail.video.d
        public void a() {
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.d
        public void a(int i, int i2) {
            if (j.this.a.a == null || j.this.e) {
                return;
            }
            j.this.a.a.onAdShowError(0, "onVideoPlayError");
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.d
        public void a(long j, final long j2) {
            String str;
            int iMin = this.c;
            final boolean zH = j.this.h();
            if (zH) {
                str = this.d;
            } else {
                iMin = Math.min(this.f, ((int) j) / 1000);
                str = this.e;
            }
            final String str2 = str;
            final int i = iMin;
            j.this.g.post(new Runnable() { // from class: com.kwad.sdk.splashscreen.a.j.2.1
                @Override // java.lang.Runnable
                @SuppressLint({"SetTextI18n"})
                public void run() {
                    int i2 = (int) (((i * 1000) - j2) / 1000);
                    if (i2 < 1) {
                        i2 = 1;
                    }
                    String str3 = str2 + i2;
                    if (zH) {
                        j.this.g.setSkipText(str3);
                    } else {
                        j.this.g.a(str3);
                    }
                }
            });
            float f = j2 / 1000.0f;
            if (iMin <= 0 || f + 0.5d <= iMin - 1 || j.this.a.a == null) {
                return;
            }
            if (!j.this.a.b) {
                j.this.a.d();
            }
            j.this.a.b = true;
            if (this.b || j.this.a.a == null) {
                return;
            }
            j.this.a.a.onAdShowEnd();
            this.b = true;
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.d
        public void b() {
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.d
        public void c() {
            if (j.this.a.f != null) {
                j.this.a.f.a(j.this.b, false);
            }
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.d
        public void d() {
            if (j.this.c) {
                return;
            }
            j.this.g();
            if (j.this.a.a != null) {
                j.this.a.a.onAdShowStart();
                if (j.this.a.f != null) {
                    j.this.a.f.a(true);
                    j.this.a.f.a(j.this.b, true);
                }
            }
            j.this.c = true;
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.d
        public void e() {
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.d
        public void f() {
            if (this.b || j.this.a.a == null) {
                return;
            }
            j.this.a.a.onAdShowEnd();
            this.b = true;
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.d
        public void g() {
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.d
        public void h() {
        }
    };

    private void a(SkipView skipView, AdInfo adInfo) {
        skipView.setTimerBtnVisible(h() ? false : com.kwad.sdk.core.response.a.a.aw(adInfo));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        com.kwad.sdk.core.report.a.i(this.a.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean h() {
        return this.a != null && this.a.b() && com.kwad.sdk.core.config.b.ao() > 0 && !at.a(com.kwad.sdk.core.config.b.am());
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.a = (com.kwad.sdk.splashscreen.g) p();
        this.h = com.kwad.sdk.core.response.a.d.j(this.a.d);
        ((DetailVideoView) this.a.e.findViewById(R.id.ksad_splash_video_player)).setVisibility(0);
        if (this.a.f != null) {
            this.a.f.a(this.j);
        }
        this.a.i.a(this);
        this.g = (SkipView) this.a.e.findViewById(R.id.ksad_splash_skip_view);
        a(this.g, this.h);
        final AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(this.a.d);
        boolean z = true;
        switch (adInfoJ.adSplashInfo.mute) {
            case 3:
                if (av.u(this.a.e.getContext()) <= 0) {
                    z = false;
                }
            case 2:
                this.b = z;
                break;
            default:
                this.b = false;
                break;
        }
        if (com.kwad.sdk.utils.b.a(q()).a()) {
            this.b = false;
        }
        if (this.a.f != null) {
            this.a.f.a(this.b, false);
            this.a.f.a(this.i);
        }
        this.f = (ImageView) this.a.e.findViewById(R.id.ksad_splash_sound);
        this.f.setVisibility(0);
        String str = this.b ? adInfoJ.adSplashInfo.speakerIconUrl : adInfoJ.adSplashInfo.speakerMuteIconUrl;
        if (TextUtils.isEmpty(str)) {
            this.f.setImageDrawable(q().getResources().getDrawable(R.drawable.ksad_splash_sound_selector));
        } else {
            KSImageLoader.loadImage(this.f, str, this.a.d);
        }
        this.f.setSelected(this.b);
        this.f.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.splashscreen.a.j.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                j.this.b = !j.this.b;
                String str2 = j.this.b ? adInfoJ.adSplashInfo.speakerIconUrl : adInfoJ.adSplashInfo.speakerMuteIconUrl;
                if (TextUtils.isEmpty(str2)) {
                    j.this.f.setImageDrawable(j.this.q().getResources().getDrawable(R.drawable.ksad_splash_sound_selector));
                } else {
                    KSImageLoader.loadImage(j.this.f, str2, j.this.a.d);
                }
                j.this.f.setSelected(j.this.b);
                j.this.a.f.a(j.this.b, true);
            }
        });
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        if (this.a.f != null) {
            this.a.f.b(this.j);
            this.a.f.b(this.i);
        }
        if (this.g.getHandler() != null) {
            this.g.getHandler().removeCallbacksAndMessages(null);
        }
        this.a.i.b(this);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void d_() {
        super.d_();
        this.e = true;
    }

    @Override // com.kwad.sdk.core.j.d
    public void e() {
        if (this.d) {
            return;
        }
        this.d = true;
        com.kwad.sdk.core.report.a.a(this.a.d, (JSONObject) null);
    }

    @Override // com.kwad.sdk.core.j.d
    public void f() {
        if (this.a.f != null) {
            this.a.f.g();
        }
    }
}
