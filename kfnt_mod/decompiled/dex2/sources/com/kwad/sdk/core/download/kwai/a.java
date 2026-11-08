package com.kwad.sdk.core.download.kwai;

import android.content.Context;
import androidx.annotation.NonNull;
import com.kwad.sdk.R;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.view.AdBaseFrameLayout;
import com.kwad.sdk.mvp.Presenter;
import com.kwad.sdk.widget.KSFrameLayout;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends KSFrameLayout {
    private final b a;
    private AdBaseFrameLayout b;
    private d c;
    private Presenter d;

    @NonNull
    private AdTemplate e;
    private AdInfo f;
    private final int g;
    private String h;

    public a(Context context, @NonNull b bVar, @NonNull AdTemplate adTemplate, int i, String str) {
        super(context);
        this.h = str;
        this.g = i;
        this.a = bVar;
        a(context);
        a(adTemplate);
    }

    private void a(Context context) {
        inflate(context, R.layout.ksad_download_dialog_layout, this);
        this.b = (AdBaseFrameLayout) findViewById(R.id.ksad_root_container);
    }

    private d d() {
        d dVar = new d();
        dVar.c = this.b;
        dVar.d = this.e;
        dVar.a = this.g;
        if (com.kwad.sdk.core.response.a.a.B(this.f)) {
            dVar.e = new com.kwad.sdk.core.download.a.b(this.e);
        }
        dVar.b = new e() { // from class: com.kwad.sdk.core.download.kwai.a.1
            @Override // com.kwad.sdk.core.download.kwai.e
            public void a(boolean z) {
                com.kwad.sdk.core.d.a.a("DownloadDialogView", "onClose()");
                if (z) {
                    com.kwad.sdk.core.report.a.n(a.this.e);
                }
                if (a.this.a == null || !a.this.a.isShowing()) {
                    return;
                }
                a.this.a.a(z);
            }
        };
        return dVar;
    }

    private Presenter e() {
        Presenter presenter = new Presenter();
        presenter.a((Presenter) new f(this.h));
        return presenter;
    }

    @Override // com.kwad.sdk.widget.KSFrameLayout
    protected void a() {
        super.a();
        this.c = d();
        this.d = e();
        this.d.c(this.b);
        this.d.a(this.c);
    }

    public void a(@NonNull AdTemplate adTemplate) {
        this.e = adTemplate;
        this.f = com.kwad.sdk.core.response.a.d.j(this.e);
    }

    @Override // com.kwad.sdk.widget.KSFrameLayout
    protected void b() {
        super.b();
        if (this.c != null) {
            this.c.a();
        }
        if (this.d != null) {
            this.d.k();
        }
    }

    public void c() {
    }
}
