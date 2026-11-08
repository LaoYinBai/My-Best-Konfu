package com.kwad.sdk.core.webview.jshandler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d implements com.kwad.sdk.core.webview.kwai.a {
    private final com.kwad.sdk.core.webview.a a;
    private b b;

    public static final class a implements com.kwad.sdk.core.b {
        public int a;
        public int b;

        @Override // com.kwad.sdk.core.b
        public void parseJson(@Nullable JSONObject jSONObject) {
        }

        @Override // com.kwad.sdk.core.b
        public JSONObject toJson() {
            JSONObject jSONObject = new JSONObject();
            com.kwad.sdk.utils.t.a(jSONObject, "width", this.a);
            com.kwad.sdk.utils.t.a(jSONObject, "height", this.b);
            return jSONObject;
        }
    }

    public interface b {
        void a(a aVar);
    }

    public d(com.kwad.sdk.core.webview.a aVar) {
        this.a = aVar;
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    @NonNull
    public String a() {
        return "getContainerLimit";
    }

    public void a(b bVar) {
        this.b = bVar;
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    public void a(String str, @NonNull com.kwad.sdk.core.webview.kwai.c cVar) {
        a aVar = new a();
        if (this.b != null) {
            this.b.a(aVar);
        } else {
            aVar.a = this.a.d.getWidth();
            aVar.b = this.a.d.getHeight();
        }
        cVar.a(aVar);
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    public void b() {
    }
}
