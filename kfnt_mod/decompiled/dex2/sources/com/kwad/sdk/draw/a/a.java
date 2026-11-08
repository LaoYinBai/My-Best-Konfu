package com.kwad.sdk.draw.a;

import com.kwad.sdk.R;
import com.kwad.sdk.compliance.widget.ComplianceTextView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends com.kwad.sdk.draw.kwai.a {
    private ComplianceTextView b;

    @Override // com.kwad.sdk.draw.kwai.a, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.b.setVisibility(0);
        this.b.setAdTemplate(this.a.c);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.b = (ComplianceTextView) a(R.id.ksad_compliance_view);
    }
}
