package com.kwad.tachikoma.config;

import com.heytap.mcssdk.mode.CommandMessage;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.core.network.d;
import com.kwad.sdk.plugin.DevelopMangerPlugin;
import com.kwad.sdk.plugin.f;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends d {
    public a(String str, String str2) {
        b("tkVersion", str);
        b("jsVersion", str2);
        b(CommandMessage.SDK_VERSION, "3.3.17");
        b("sdkApiVersion", KsAdSDKImpl.get().getApiVersion());
    }

    @Override // com.kwad.sdk.core.network.b, com.kwad.sdk.core.network.g
    public String a() {
        DevelopMangerPlugin.DevelopValue developValueA = ((DevelopMangerPlugin) f.a(DevelopMangerPlugin.class)).a("KEY_HOST_ENCRYPT_DISABLE");
        return (developValueA == null || !((Boolean) developValueA.getValue()).booleanValue()) ? com.kwad.sdk.d.k() : com.kwad.sdk.d.j();
    }

    @Override // com.kwad.sdk.core.network.d
    protected boolean j() {
        return false;
    }
}
