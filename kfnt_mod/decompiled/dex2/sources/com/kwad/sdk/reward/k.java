package com.kwad.sdk.reward;

import android.content.Context;
import com.kwad.sdk.core.page.AdWebViewLandPageActivityProxy;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class k {
    public static void a(a aVar) {
        AdTemplate adTemplate = aVar.g;
        Context context = aVar.i;
        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(adTemplate);
        if ((com.kwad.sdk.core.response.a.a.an(adInfoJ) && aVar.m != null && aVar.m.e()) || com.kwad.sdk.core.response.a.b.j(adTemplate) || com.kwad.sdk.core.response.a.a.aG(adInfoJ) || adTemplate.mXiaomiAppStoreDetailViewOpen || !com.kwad.sdk.core.response.a.a.B(adInfoJ) || !com.kwad.sdk.core.response.a.a.r(adInfoJ)) {
            return;
        }
        AdWebViewLandPageActivityProxy.launch(context, adTemplate, true);
    }
}
