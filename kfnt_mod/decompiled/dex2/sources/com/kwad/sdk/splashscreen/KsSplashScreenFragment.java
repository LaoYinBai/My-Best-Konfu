package com.kwad.sdk.splashscreen;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import java.io.Serializable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class KsSplashScreenFragment extends com.kwad.sdk.contentalliance.a<g> implements DialogInterface.OnDismissListener, DialogInterface.OnShowListener {
    private KsSplashScreenAd.SplashScreenAdInteractionListener d;
    private KsVideoPlayConfig e;
    private AdBaseFrameLayout f;
    private DetailVideoView g;
    private com.kwad.sdk.core.download.a.b h;
    private com.kwad.sdk.core.j.b i;
    private AdTemplate j;
    private KsScene k;
    private AdInfo l;
    private boolean m;

    public static KsSplashScreenFragment a(@NonNull KsScene ksScene, @NonNull AdResultData adResultData) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("adScene", ksScene);
        if (!adResultData.adTemplateList.isEmpty()) {
            bundle.putSerializable("KsSplashScreenFragment", adResultData.adTemplateList.get(0));
        }
        KsSplashScreenFragment ksSplashScreenFragment = new KsSplashScreenFragment();
        ksSplashScreenFragment.setArguments(bundle);
        return ksSplashScreenFragment;
    }

    public static boolean a(@NonNull AdInfo adInfo) {
        return adInfo.adSplashInfo != null && adInfo.adSplashInfo.fullScreenClickSwitch == 1;
    }

    private void f() {
        if (getArguments() == null) {
            return;
        }
        Serializable serializable = getArguments().getSerializable("KsSplashScreenFragment");
        if (!(serializable instanceof AdTemplate)) {
            com.kwad.sdk.core.d.a.e("FullScreenVideo", "data is not instanceof AdTemplate:" + serializable);
            this.d.onAdShowError(0, "返回数据格式错误");
            return;
        }
        Serializable serializable2 = getArguments().getSerializable("adScene");
        if (!(serializable2 instanceof KsScene)) {
            com.kwad.sdk.core.d.a.e("FullScreenVideo", "data is not instanceof AdScene" + serializable);
            this.d.onAdShowError(0, "AdScene 数据错误");
            return;
        }
        this.j = (AdTemplate) serializable;
        this.k = (KsScene) serializable2;
        this.l = com.kwad.sdk.core.response.a.d.j(this.j);
        this.e = new KsVideoPlayConfig.Builder().videoSoundEnable(this.l.adSplashInfo.mute != 1).skipThirtySecond(true).build();
        this.j.mInitVoiceStatus = this.e.isVideoSoundEnable() ? 2 : 1;
        this.h = new com.kwad.sdk.core.download.a.b(this.j);
        this.h.a((DialogInterface.OnDismissListener) this);
        this.h.a((DialogInterface.OnShowListener) this);
    }

    private void g() {
        this.f = (AdBaseFrameLayout) this.c.findViewById(R.id.ksad_splash_root_container);
        this.g = (DetailVideoView) this.c.findViewById(R.id.ksad_splash_video_player);
        this.g.setAd(true);
        this.g.setVisibility(8);
        this.f.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.splashscreen.KsSplashScreenFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (bb.a() || !KsSplashScreenFragment.a(KsSplashScreenFragment.this.l) || KsSplashScreenFragment.this.b == null) {
                    return;
                }
                ((g) KsSplashScreenFragment.this.b).a(view.getContext(), 2, 2);
            }
        });
    }

    private void h() {
        if (this.m) {
            return;
        }
        this.m = true;
    }

    @Override // com.kwad.sdk.contentalliance.a
    protected void a() {
        super.a();
        ((g) this.b).a();
    }

    public void a(KsSplashScreenAd.SplashScreenAdInteractionListener splashScreenAdInteractionListener) {
        this.d = splashScreenAdInteractionListener;
        if (this.b != 0) {
            ((g) this.b).a = splashScreenAdInteractionListener;
        }
    }

    @Override // com.kwad.sdk.contentalliance.a
    protected int b() {
        return R.layout.ksad_splash_screen;
    }

    @Override // com.kwad.sdk.contentalliance.a
    @NonNull
    public Presenter d() {
        return d.a(getContext(), this.l);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.kwad.sdk.contentalliance.a
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public g c() {
        this.i = new com.kwad.sdk.core.j.b(this, this.c, 70);
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
        gVar.i = this.i;
        gVar.g = this.h;
        if (com.kwad.sdk.core.response.a.a.P(this.l)) {
            com.kwad.sdk.splashscreen.b.a aVar = new com.kwad.sdk.splashscreen.b.a(this.j, this.g, this.e);
            gVar.f = aVar;
            gVar.i.a(aVar);
        }
        return gVar;
    }

    @Override // com.kwad.sdk.contentalliance.a, com.kwad.sdk.api.core.fragment.KsFragment, com.kwad.sdk.api.core.fragment.AbstractIFragmentLifecycle, com.kwad.sdk.api.core.fragment.IFragmentLifecycle
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        if (((g) this.b).f != null) {
            ((g) this.b).f.j();
        }
    }

    @Override // com.kwad.sdk.h.d, com.kwad.sdk.api.core.fragment.KsFragment, com.kwad.sdk.api.core.fragment.AbstractIFragmentLifecycle, com.kwad.sdk.api.core.fragment.IFragmentLifecycle
    public void onDestroy() {
        super.onDestroy();
        h();
    }

    @Override // com.kwad.sdk.contentalliance.a, com.kwad.sdk.api.core.fragment.KsFragment, com.kwad.sdk.api.core.fragment.AbstractIFragmentLifecycle, com.kwad.sdk.api.core.fragment.IFragmentLifecycle
    public void onDestroyView() {
        super.onDestroyView();
        this.i.b();
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

    @Override // com.kwad.sdk.api.core.fragment.KsFragment, com.kwad.sdk.api.core.fragment.AbstractIFragmentLifecycle, com.kwad.sdk.api.core.fragment.IFragmentLifecycle
    public void onPause() {
        super.onPause();
        this.i.i();
    }

    @Override // com.kwad.sdk.api.core.fragment.KsFragment, com.kwad.sdk.api.core.fragment.AbstractIFragmentLifecycle, com.kwad.sdk.api.core.fragment.IFragmentLifecycle
    public void onResume() {
        super.onResume();
        this.i.h();
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

    @Override // com.kwad.sdk.api.core.fragment.KsFragment, com.kwad.sdk.api.core.fragment.AbstractIFragmentLifecycle, com.kwad.sdk.api.core.fragment.IFragmentLifecycle
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        f();
        g();
    }

    @Override // com.kwad.sdk.api.core.fragment.KsFragment, com.kwad.sdk.api.core.fragment.IFragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.i.a(z);
    }
}
