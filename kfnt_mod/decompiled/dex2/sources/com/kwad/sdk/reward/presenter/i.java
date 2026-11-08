package com.kwad.sdk.reward.presenter;

import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.mvp.Presenter;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class i extends com.kwad.sdk.reward.g {
    public i(AdTemplate adTemplate) {
        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(adTemplate);
        if (com.kwad.sdk.core.response.a.a.an(adInfoJ)) {
            a((Presenter) new g());
            a((Presenter) new h());
        } else {
            if (com.kwad.sdk.core.response.a.a.ao(adInfoJ)) {
                return;
            }
            com.kwad.sdk.core.report.d.b(adTemplate);
        }
    }
}
