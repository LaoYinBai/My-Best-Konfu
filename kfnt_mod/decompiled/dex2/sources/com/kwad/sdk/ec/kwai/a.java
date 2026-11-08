package com.kwad.sdk.ec.kwai;

import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.core.network.d;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class a extends d {
    @Override // com.kwad.sdk.core.network.b, com.kwad.sdk.core.network.g
    public String a() {
        return com.kwad.sdk.d.b() + k();
    }

    @Override // com.kwad.sdk.core.network.b
    protected String f() {
        return com.kwad.sdk.d.b();
    }

    @Override // com.kwad.sdk.core.network.d, com.kwad.sdk.core.network.b
    protected void h() {
        super.h();
        b("kpn", "kseulivesdk");
        b("subBiz", KsAdSDKImpl.get().getAppId());
    }

    protected abstract String k();
}
