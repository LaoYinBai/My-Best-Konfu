package com.kwad.sdk.core.webview.jshandler;

import android.content.Context;
import androidx.annotation.NonNull;
import com.kwad.sdk.core.response.model.AdTemplate;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class aa implements com.kwad.sdk.core.webview.kwai.a {
    private Context a;
    private AdTemplate b;
    private a c;

    public interface a {
        boolean a();
    }

    public aa(Context context, AdTemplate adTemplate) {
        this.a = context;
        this.b = adTemplate;
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    @NonNull
    public String a() {
        return "showPlayable";
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    public void a(String str, @NonNull com.kwad.sdk.core.webview.kwai.c cVar) {
        boolean zA = this.c != null ? this.c.a() : true;
        com.kwad.sdk.core.d.a.a("WebShowPlayableHandler", "handleJsCall launch AdPlayableActivityProxy : " + zA);
        if (zA) {
            com.kwad.sdk.core.page.a.a(this.a, this.b);
        }
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    public void b() {
    }
}
