package com.kwad.sdk.reward.presenter;

import com.kwad.sdk.R;
import com.kwad.sdk.compliance.widget.ComplianceTextView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends com.kwad.sdk.reward.g {
    private ComplianceTextView b;

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.b.setVisibility(0);
        this.b.setAdTemplate(this.a.g);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.b = (ComplianceTextView) a(R.id.ksad_compliance_view);
    }
}
