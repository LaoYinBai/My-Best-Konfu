package com.kwad.sdk.core.webview.jshandler;

import androidx.annotation.NonNull;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class r implements com.kwad.sdk.core.webview.kwai.a {
    private com.kwad.sdk.core.webview.kwai.c a;

    @Override // com.kwad.sdk.core.webview.kwai.a
    @NonNull
    public String a() {
        return "registerDeeplinkListener";
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    public void a(String str, @NonNull com.kwad.sdk.core.webview.kwai.c cVar) {
        this.a = cVar;
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    public void b() {
        this.a = null;
    }

    public void c() {
        if (this.a != null) {
            this.a.a(null);
        }
    }

    public void d() {
        if (this.a != null) {
            this.a.a(-1, "deep link error");
        }
    }
}
