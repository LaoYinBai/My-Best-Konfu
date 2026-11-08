package com.kwad.sdk;

import com.kwad.sdk.core.network.m;
import com.kwad.sdk.design.provider.ServiceProvider;
import java.io.InputStream;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f {
    static void a() {
        ServiceProvider.a(com.kwad.sdk.design.kwai.b.class, new com.kwad.sdk.design.kwai.b() { // from class: com.kwad.sdk.f.1
            @Override // com.kwad.sdk.design.kwai.b
            public Class<?> a(Class cls) {
                return KsAdSDKImpl.getProxyRealClass(cls);
            }
        });
    }

    static void b() {
        ServiceProvider.a(com.kwad.sdk.design.kwai.a.class, new com.kwad.sdk.design.kwai.a() { // from class: com.kwad.sdk.f.2
            @Override // com.kwad.sdk.design.kwai.a
            public void a(URLConnection uRLConnection) throws NoSuchAlgorithmException, KeyManagementException {
                m.a(uRLConnection);
            }
        });
        ServiceProvider.a(com.kwad.sdk.design.kwai.c.class, new com.kwad.sdk.design.kwai.c() { // from class: com.kwad.sdk.f.3
            @Override // com.kwad.sdk.design.kwai.c
            public InputStream a(InputStream inputStream) {
                return com.kwad.sdk.core.h.b.a().b(inputStream);
            }
        });
    }
}
