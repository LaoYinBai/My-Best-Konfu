package com.kwad.sdk.splashscreen.a;

import android.content.Context;
import android.view.View;
import com.kwad.sdk.R;
import com.kwad.sdk.mvp.Presenter;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends Presenter implements com.kwad.sdk.widget.d {
    private View a;
    private com.kwad.sdk.widget.f b;
    private boolean c;
    private com.kwad.sdk.splashscreen.g d = null;

    @Override // com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        Object objP = p();
        if (objP instanceof com.kwad.sdk.splashscreen.g) {
            this.d = (com.kwad.sdk.splashscreen.g) objP;
        }
        if (this.d == null) {
            return;
        }
        this.c = com.kwad.sdk.core.response.a.c.g(this.d.d);
        boolean zA = i.a(com.kwad.sdk.core.response.a.d.j(this.d.d));
        this.a.setVisibility(zA ? 0 : 8);
        if (zA) {
            this.b = new com.kwad.sdk.widget.f(this.a.getContext(), this.a, this);
        }
    }

    @Override // com.kwad.sdk.widget.d
    public void a_(View view) {
        com.kwad.sdk.splashscreen.g gVar;
        Context context;
        int i;
        if (com.kwad.sdk.splashscreen.g.a(view.getContext(), com.kwad.sdk.core.response.a.d.j(this.d.d))) {
            gVar = this.d;
            context = view.getContext();
            i = 53;
        } else {
            gVar = this.d;
            context = view.getContext();
            i = 0;
        }
        gVar.a(context, i, 2);
    }

    @Override // com.kwad.sdk.widget.d
    public void b(View view) {
        com.kwad.sdk.core.d.a.a("FullScreenTouchConvertPresenter", "onSlide: enableSlickClick: " + this.c);
        if (this.c) {
            this.d.a(view.getContext(), 153, 2);
        }
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.a = a(R.id.ksad_splash_actionbar_full_screen);
    }
}
