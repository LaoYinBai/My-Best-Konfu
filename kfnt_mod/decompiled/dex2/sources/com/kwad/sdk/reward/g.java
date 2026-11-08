package com.kwad.sdk.reward;

import android.app.Activity;
import com.kwad.sdk.mvp.Presenter;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class g extends Presenter {
    protected a a;

    @Override // com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.a = (a) p();
    }

    protected void a(boolean z) {
        com.kwad.sdk.core.report.a.a(this.a.g, z ? 1 : 153, this.a.j.getTouchCoords(), this.a.e);
        this.a.b.a();
    }

    public boolean f() {
        return com.kwad.sdk.core.response.a.d.u(this.a.g);
    }

    public boolean g() {
        return com.kwad.sdk.core.response.a.d.v(this.a.g);
    }

    protected void h() {
        Activity activity = this.a.h;
        if (activity != null) {
            activity.finish();
        }
    }
}
