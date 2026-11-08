package com.kwad.sdk.splashscreen.a;

import com.kwad.sdk.R;
import com.kwad.sdk.compliance.widget.ComplianceTextView;
import com.kwad.sdk.mvp.Presenter;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e extends Presenter {
    private ComplianceTextView a;

    @Override // com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        com.kwad.sdk.splashscreen.g gVar = (com.kwad.sdk.splashscreen.g) p();
        this.a.setVisibility(0);
        this.a.setAdTemplate(gVar.d);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.a = (ComplianceTextView) a(R.id.ksad_compliance_view);
    }
}
