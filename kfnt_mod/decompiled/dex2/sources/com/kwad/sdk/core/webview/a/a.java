package com.kwad.sdk.core.webview.a;

import androidx.annotation.NonNull;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a implements com.kwad.sdk.core.webview.kwai.a {
    private InterfaceC0049a a;

    /* renamed from: com.kwad.sdk.core.webview.a.a$a, reason: collision with other inner class name */
    public interface InterfaceC0049a {
        void a();
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    @NonNull
    public String a() {
        return "callButtonImpressionWhenFinish";
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    public void a(String str, @NonNull com.kwad.sdk.core.webview.kwai.c cVar) {
        if (this.a != null) {
            this.a.a();
        }
        c();
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    public void b() {
        this.a = null;
    }

    public void c() {
    }
}
