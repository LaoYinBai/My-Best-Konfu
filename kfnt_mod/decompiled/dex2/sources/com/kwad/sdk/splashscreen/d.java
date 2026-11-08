package com.kwad.sdk.splashscreen;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import androidx.annotation.NonNull;
import com.kwad.sdk.R;
import com.kwad.sdk.api.KsScene;
import com.kwad.sdk.api.KsSplashScreenAd;
import com.kwad.sdk.api.KsVideoPlayConfig;
import com.kwad.sdk.contentalliance.detail.video.DetailVideoView;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdResultData;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.view.AdBaseFrameLayout;
import com.kwad.sdk.mvp.Presenter;
import com.kwad.sdk.utils.bb;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d extends com.kwad.sdk.contentalliance.b<g> implements DialogInterface.OnDismissListener, DialogInterface.OnShowListener {
    private KsSplashScreenAd.SplashScreenAdInteractionListener d;
    private KsVideoPlayConfig e;
    private AdBaseFrameLayout f;
    private DetailVideoView g;
    private com.kwad.sdk.core.download.a.b h;
    private com.kwad.sdk.core.j.f i;
    private AdTemplate j;
    private KsScene k;
    private AdInfo l;
    private boolean m;

    public d(Context context) {
        super(context);
        f();
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.kwad.sdk.mvp.Presenter a(android.content.Context r2, com.kwad.sdk.core.response.model.AdInfo r3) {
        /*
            com.kwad.sdk.mvp.Presenter r0 = new com.kwad.sdk.mvp.Presenter
            r0.<init>()
            com.kwad.sdk.splashscreen.a.b r1 = new com.kwad.sdk.splashscreen.a.b
            r1.<init>()
            r0.a(r1)
            com.kwad.sdk.splashscreen.a.c r1 = new com.kwad.sdk.splashscreen.a.c
            r1.<init>()
            r0.a(r1)
            com.kwad.sdk.splashscreen.a.h r1 = new com.kwad.sdk.splashscreen.a.h
            r1.<init>()
            r0.a(r1)
            boolean r1 = com.kwad.sdk.core.response.a.a.P(r3)
            if (r1 == 0) goto L34
            com.kwad.sdk.splashscreen.a.d r1 = new com.kwad.sdk.splashscreen.a.d
            r1.<init>()
            r0.a(r1)
            com.kwad.sdk.splashscreen.a.j r1 = new com.kwad.sdk.splashscreen.a.j
            r1.<init>()
        L30:
            r0.a(r1)
            goto L3a
        L34:
            com.kwad.sdk.splashscreen.a.f r1 = new com.kwad.sdk.splashscreen.a.f
            r1.<init>()
            goto L30
        L3a:
            boolean r2 = com.kwad.sdk.splashscreen.g.a(r2, r3)
            if (r2 == 0) goto L49
            com.kwad.sdk.splashscreen.a.g r2 = new com.kwad.sdk.splashscreen.a.g
            r2.<init>()
        L45:
            r0.a(r2)
            goto L55
        L49:
            boolean r2 = b(r3)
            if (r2 == 0) goto L55
            com.kwad.sdk.splashscreen.a.i r2 = new com.kwad.sdk.splashscreen.a.i
            r2.<init>()
            goto L45
        L55:
            boolean r2 = com.kwad.sdk.core.response.a.a.D(r3)
            if (r2 == 0) goto L63
            com.kwad.sdk.splashscreen.a.e r2 = new com.kwad.sdk.splashscreen.a.e
            r2.<init>()
            r0.a(r2)
        L63:
            com.kwad.sdk.splashscreen.a.a r2 = new com.kwad.sdk.splashscreen.a.a
            r2.<init>()
            r0.a(r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.splashscreen.d.a(android.content.Context, com.kwad.sdk.core.response.model.AdInfo):com.kwad.sdk.mvp.Presenter");
    }

    public static d a(Context context, @NonNull KsScene ksScene, @NonNull AdResultData adResultData) {
        AdTemplate adTemplate = new AdTemplate();
        if (!adResultData.adTemplateList.isEmpty()) {
            adTemplate = adResultData.adTemplateList.get(0);
        }
        d dVar = new d(context);
        dVar.a(ksScene, adTemplate);
        return dVar;
    }

    private void a(KsScene ksScene, AdTemplate adTemplate) {
        this.k = ksScene;
        this.j = adTemplate;
        e();
    }

    public static boolean a(@NonNull AdInfo adInfo) {
        return adInfo.adSplashInfo != null && adInfo.adSplashInfo.fullScreenClickSwitch == 1;
    }

    public static boolean b(@NonNull AdInfo adInfo) {
        return adInfo.adSplashInfo != null && adInfo.adSplashInfo.splashShowClickButtonSwitch == 1;
    }

    private void e() {
        this.l = com.kwad.sdk.core.response.a.d.j(this.j);
        this.e = new KsVideoPlayConfig.Builder().videoSoundEnable(this.l.adSplashInfo.mute != 1).skipThirtySecond(true).build();
        this.j.mInitVoiceStatus = this.e.isVideoSoundEnable() ? 2 : 1;
        this.h = new com.kwad.sdk.core.download.a.b(this.j);
        this.h.a((DialogInterface.OnDismissListener) this);
        this.h.a((DialogInterface.OnShowListener) this);
    }

    private void f() {
        this.f = (AdBaseFrameLayout) this.c.findViewById(R.id.ksad_splash_root_container);
        this.g = (DetailVideoView) this.c.findViewById(R.id.ksad_splash_video_player);
        this.g.setAd(true);
        this.g.setVisibility(8);
        this.f.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.splashscreen.d.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (bb.a() || !d.a(d.this.l) || d.this.b == null) {
                    return;
                }
                ((g) d.this.b).a(view.getContext(), 2, 2);
            }
        });
    }

    private void g() {
        if (this.m) {
            return;
        }
        this.m = true;
    }

    @Override // com.kwad.sdk.contentalliance.b
    @NonNull
    public Presenter c() {
        return a(getContext(), this.l);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.kwad.sdk.contentalliance.b
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public g b() {
        this.i = new com.kwad.sdk.core.j.f(this.c, 70);
        this.i.a();
        if (this.e == null) {
            this.e = new KsVideoPlayConfig.Builder().videoSoundEnable(this.l.adSplashInfo.mute != 1).skipThirtySecond(true).build();
        }
        g gVar = new g();
        gVar.a = this.d;
        gVar.e = this.f;
        gVar.d = this.j;
        gVar.h = this.k;
        gVar.c = this.e;
        gVar.d.mMiniWindowId = gVar.c();
        gVar.i = this.i;
        gVar.g = this.h;
        if (com.kwad.sdk.core.response.a.a.P(this.l)) {
            com.kwad.sdk.splashscreen.b.a aVar = new com.kwad.sdk.splashscreen.b.a(this.j, this.g, this.e);
            gVar.f = aVar;
            gVar.i.a(aVar);
        }
        return gVar;
    }

    @Override // com.kwad.sdk.contentalliance.b
    protected int getLayoutId() {
        return R.layout.ksad_splash_screen;
    }

    @Override // com.kwad.sdk.contentalliance.b, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (((g) this.b).f != null) {
            ((g) this.b).f.j();
        }
    }

    @Override // com.kwad.sdk.contentalliance.b, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.i.b();
        com.kwad.sdk.splashscreen.b.a aVar = ((g) this.b).f;
        ((g) this.b).a();
        g();
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        boolean zB = dialogInterface instanceof com.kwad.sdk.core.download.kwai.b ? ((com.kwad.sdk.core.download.kwai.b) dialogInterface).b() : false;
        try {
            if (this.d != null) {
                if (zB) {
                    this.d.onDownloadTipsDialogDismiss();
                } else {
                    this.d.onDownloadTipsDialogCancel();
                }
            }
        } catch (Throwable th) {
            com.kwad.sdk.core.d.a.b(th);
        }
    }

    @Override // android.content.DialogInterface.OnShowListener
    public void onShow(DialogInterface dialogInterface) {
        try {
            if (this.d != null) {
                this.d.onDownloadTipsDialogShow();
            }
        } catch (Throwable th) {
            com.kwad.sdk.core.d.a.b(th);
        }
    }

    @Override // android.view.View
    protected void onVisibilityChanged(@NonNull View view, int i) {
        super.onVisibilityChanged(view, i);
    }

    public void setSplashScreenAdListener(KsSplashScreenAd.SplashScreenAdInteractionListener splashScreenAdInteractionListener) {
        this.d = splashScreenAdInteractionListener;
        if (this.b != 0) {
            ((g) this.b).a = splashScreenAdInteractionListener;
        }
    }
}
