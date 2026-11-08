package com.kwad.sdk.draw.a.a;

import android.view.ViewGroup;
import com.kwad.sdk.R;
import com.kwad.sdk.core.response.a.d;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.draw.a.a.a;
import com.kwad.sdk.draw.view.playcard.DrawCardApp;
import com.kwad.sdk.draw.view.playcard.DrawCardH5;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends com.kwad.sdk.draw.kwai.a {
    private ViewGroup b;
    private DrawCardApp c;
    private DrawCardH5 d;
    private AdTemplate e;
    private AdInfo f;
    private a.InterfaceC0059a g = new a.InterfaceC0059a() { // from class: com.kwad.sdk.draw.a.a.b.1
        @Override // com.kwad.sdk.draw.a.a.a.InterfaceC0059a
        public void a() {
            b.this.e();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (com.kwad.sdk.core.response.a.a.B(this.f)) {
            f();
        } else {
            g();
        }
    }

    private void f() {
        this.b.setVisibility(8);
        this.c.a(this.e, new DrawCardApp.a() { // from class: com.kwad.sdk.draw.a.a.b.2
            @Override // com.kwad.sdk.draw.view.playcard.DrawCardApp.a
            public void a() {
                b.this.b.setVisibility(0);
            }

            @Override // com.kwad.sdk.draw.view.playcard.DrawCardApp.a
            public void b() {
                b.this.h();
            }
        });
        this.c.setVisibility(0);
        this.c.b();
    }

    private void g() {
        this.b.setVisibility(8);
        this.d.a(this.e, new DrawCardH5.a() { // from class: com.kwad.sdk.draw.a.a.b.3
            @Override // com.kwad.sdk.draw.view.playcard.DrawCardH5.a
            public void a() {
                b.this.b.setVisibility(0);
            }

            @Override // com.kwad.sdk.draw.view.playcard.DrawCardH5.a
            public void b() {
                b.this.h();
            }
        });
        this.d.setVisibility(0);
        this.d.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        com.kwad.sdk.core.report.a.a(this.e, 29, this.a.b.getTouchCoords());
        if (this.a.a != null) {
            this.a.a.onAdClicked();
        }
    }

    @Override // com.kwad.sdk.draw.kwai.a, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.e = this.a.c;
        this.f = d.j(this.e);
        this.a.f.a(this.g);
        this.c.setVisibility(8);
        this.d.setVisibility(8);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        this.c.a();
        this.d.a();
        this.a.f.a((a.InterfaceC0059a) null);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.b = (ViewGroup) a(R.id.ksad_ad_normal_container);
        this.c = (DrawCardApp) a(R.id.ksad_card_app_container);
        this.d = (DrawCardH5) a(R.id.ksad_card_h5_container);
    }
}
