package com.kwad.sdk.core.webview.jshandler;

import androidx.annotation.NonNull;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class v implements com.kwad.sdk.core.webview.kwai.a {
    private com.kwad.sdk.core.webview.kwai.c a;
    private a b = new a();

    public static final class a extends com.kwad.sdk.core.response.kwai.a implements com.kwad.sdk.core.b {
        public int a;
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    @NonNull
    public String a() {
        return "registerVideoListener";
    }

    public void a(int i) {
        if (this.a != null) {
            this.b.a = i;
            this.a.a(this.b);
        }
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    public void a(String str, @NonNull com.kwad.sdk.core.webview.kwai.c cVar) {
        this.a = cVar;
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    public void b() {
        this.a = null;
    }
}
