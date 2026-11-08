package com.kwad.sdk.f;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.api.KsAdVideoPlayConfig;
import com.kwad.sdk.api.KsNativeAd;
import com.kwad.sdk.contentalliance.detail.video.DetailVideoView;
import com.kwad.sdk.core.j.f;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.view.AdBasePvFrameLayout;
import com.kwad.sdk.f.a;
import com.kwad.sdk.f.a.c;
import com.kwad.sdk.f.a.d;
import com.kwad.sdk.f.a.e;
import com.kwad.sdk.f.a.g;
import com.kwad.sdk.f.a.h;
import com.kwad.sdk.f.a.i;
import com.kwad.sdk.f.a.j;
import com.kwad.sdk.mvp.Presenter;
import com.kwad.sdk.utils.m;
import com.kwad.sdk.widget.KSFrameLayout;
import com.kwad.sdk.widget.k;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends KSFrameLayout {
    private a.InterfaceC0060a a;
    private KsNativeAd.VideoPlayListener b;
    private AdBasePvFrameLayout c;
    private DetailVideoView d;
    private com.kwad.sdk.f.kwai.b e;
    private Presenter f;
    private AdTemplate g;
    private AdInfo h;
    private com.kwad.sdk.core.download.a.b i;
    private com.kwad.sdk.f.b.a j;
    private f k;
    private KsAdVideoPlayConfig l;
    private k m;

    public b(@NonNull Context context) {
        super(context);
        this.m = new k() { // from class: com.kwad.sdk.f.b.1
            @Override // com.kwad.sdk.widget.k
            public void a() {
                m.c(b.this.g);
            }
        };
        c();
    }

    private void c() {
        inflate(getContext(), R.layout.ksad_native_video_layout, this);
        this.c = (AdBasePvFrameLayout) findViewById(R.id.ksad_root_container);
        this.d = (DetailVideoView) this.c.findViewById(R.id.ksad_video_player);
        this.d.setAd(true);
    }

    private com.kwad.sdk.f.kwai.b d() {
        com.kwad.sdk.f.kwai.b bVar = new com.kwad.sdk.f.kwai.b();
        bVar.c = this.c;
        bVar.d = this.g;
        bVar.a = this.a;
        bVar.b = this.b;
        bVar.e = (this.i == null && com.kwad.sdk.core.response.a.a.B(this.h)) ? new com.kwad.sdk.core.download.a.b(this.g) : this.i;
        bVar.f = this.j;
        return bVar;
    }

    private Presenter e() {
        Presenter presenter = new Presenter();
        presenter.a((Presenter) new e());
        presenter.a((Presenter) new c());
        presenter.a((Presenter) new i());
        presenter.a((Presenter) new j());
        presenter.a((Presenter) new d());
        presenter.a((Presenter) new com.kwad.sdk.f.a.b());
        presenter.a((Presenter) new com.kwad.sdk.f.a.k());
        if (this.l == null || !this.l.isDataFlowAutoStart()) {
            presenter.a((Presenter) new com.kwad.sdk.f.a.a());
        }
        presenter.a((Presenter) new g());
        presenter.a(com.kwad.sdk.core.response.a.a.B(this.h) ? new com.kwad.sdk.f.a.f() : new h());
        return presenter;
    }

    @Override // com.kwad.sdk.widget.KSFrameLayout
    protected void a() {
        super.a();
        if (this.j == null) {
            this.j = new com.kwad.sdk.f.b.a(this.g, this.k, this.d, this.l);
        }
        this.e = d();
        this.f = e();
        this.f.c(this.c);
        this.f.a(this.e);
        this.k.a();
        this.j.a();
    }

    public void a(@NonNull AdTemplate adTemplate, @Nullable com.kwad.sdk.core.download.a.b bVar, @Nullable KsAdVideoPlayConfig ksAdVideoPlayConfig) {
        this.g = adTemplate;
        this.h = com.kwad.sdk.core.response.a.d.j(this.g);
        this.i = bVar;
        this.l = ksAdVideoPlayConfig;
        this.c.setVisibleListener(this.m);
        this.k = new f(getParent() == null ? this : (View) getParent(), 30);
    }

    @Override // com.kwad.sdk.widget.KSFrameLayout
    protected void b() {
        super.b();
        if (this.k != null) {
            this.k.g();
        }
        if (this.j != null) {
            this.j.b();
        }
        if (this.e != null) {
            this.e.a();
        }
        if (this.f != null) {
            this.f.k();
        }
    }

    public void setInnerAdInteractionListener(a.InterfaceC0060a interfaceC0060a) {
        this.a = interfaceC0060a;
    }

    public void setVideoPlayListener(KsNativeAd.VideoPlayListener videoPlayListener) {
        this.b = videoPlayListener;
    }
}
