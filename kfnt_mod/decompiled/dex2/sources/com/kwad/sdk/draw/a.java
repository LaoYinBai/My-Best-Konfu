package com.kwad.sdk.draw;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.api.KsDrawAd;
import com.kwad.sdk.contentalliance.detail.video.DetailVideoView;
import com.kwad.sdk.core.download.a.a;
import com.kwad.sdk.core.j.f;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.view.AdBaseFrameLayout;
import com.kwad.sdk.draw.a.c;
import com.kwad.sdk.draw.a.d;
import com.kwad.sdk.mvp.Presenter;
import com.kwad.sdk.widget.KSFrameLayout;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends KSFrameLayout {

    @Nullable
    private KsDrawAd.AdInteractionListener a;
    private AdBaseFrameLayout b;
    private DetailVideoView c;
    private com.kwad.sdk.draw.kwai.b d;
    private Presenter e;

    @NonNull
    private AdTemplate f;
    private AdInfo g;
    private com.kwad.sdk.draw.b.a h;
    private f i;
    private Context j;

    public a(@NonNull Context context) {
        super(context);
        this.j = context;
        c();
    }

    private void c() {
        inflate(this.j, R.layout.ksad_draw_layout, this);
        this.b = (AdBaseFrameLayout) findViewById(R.id.ksad_root_container);
        this.c = (DetailVideoView) this.b.findViewById(R.id.ksad_video_player);
        this.c.setAd(true);
        this.c.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.draw.a.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                com.kwad.sdk.core.download.a.a.a(new a.C0035a(a.this.d.b.getContext()).a(a.this.d.c).a(a.this.d.d).a(false).a(3).a(new a.b() { // from class: com.kwad.sdk.draw.a.1.1
                    @Override // com.kwad.sdk.core.download.a.a.b
                    public void a() {
                    }
                }));
            }
        });
    }

    private com.kwad.sdk.draw.kwai.b d() {
        com.kwad.sdk.draw.kwai.b bVar = new com.kwad.sdk.draw.kwai.b();
        bVar.a = this.a;
        bVar.b = this.b;
        bVar.c = this.f;
        if (com.kwad.sdk.core.response.a.a.B(this.g)) {
            bVar.d = new com.kwad.sdk.core.download.a.b(this.f);
        }
        bVar.e = this.h;
        bVar.f = new com.kwad.sdk.draw.a.a.a(this.f);
        if (com.kwad.sdk.core.response.a.b.h(this.f)) {
            bVar.g = new com.kwad.sdk.i.b();
        }
        return bVar;
    }

    private Presenter e() {
        Presenter presenter = new Presenter();
        presenter.a((Presenter) new com.kwad.sdk.draw.a.kwai.a());
        presenter.a((Presenter) new com.kwad.sdk.draw.a.b());
        presenter.a((Presenter) new d());
        presenter.a((Presenter) new c());
        presenter.a((Presenter) new com.kwad.sdk.draw.a.a.b());
        if (com.kwad.sdk.core.response.a.b.d(this.f)) {
            presenter.a((Presenter) new com.kwad.sdk.draw.a.a.c());
        }
        presenter.a((Presenter) new com.kwad.sdk.draw.a.b.a());
        if (com.kwad.sdk.core.response.a.b.h(this.f)) {
            presenter.a((Presenter) new com.kwad.sdk.draw.a.b.b());
        }
        if (com.kwad.sdk.core.response.a.a.D(this.g)) {
            presenter.a((Presenter) new com.kwad.sdk.draw.a.a());
        }
        return presenter;
    }

    @Override // com.kwad.sdk.widget.KSFrameLayout
    protected void a() {
        super.a();
        this.d = d();
        this.e = e();
        this.e.c(this.b);
        this.e.a(this.d);
        this.i.a();
        this.h.a();
    }

    public void a(@NonNull AdTemplate adTemplate) {
        this.f = adTemplate;
        this.g = com.kwad.sdk.core.response.a.d.j(this.f);
        this.i = new f(this, 70);
        this.h = new com.kwad.sdk.draw.b.a(this.f, this.i, this.c);
    }

    @Override // com.kwad.sdk.widget.KSFrameLayout
    protected void b() {
        super.b();
        if (this.i != null) {
            this.i.g();
        }
        if (this.h != null) {
            this.h.b();
        }
        if (this.d != null) {
            this.d.a();
        }
        if (this.e != null) {
            this.e.k();
        }
    }

    public void setAdInteractionListener(KsDrawAd.AdInteractionListener adInteractionListener) {
        this.a = adInteractionListener;
    }
}
