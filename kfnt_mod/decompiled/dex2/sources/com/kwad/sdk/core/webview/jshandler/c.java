package com.kwad.sdk.core.webview.jshandler;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c implements com.kwad.sdk.core.webview.kwai.a {
    private final com.kwad.sdk.core.webview.a a;
    private a b;
    private Handler c = new Handler(Looper.getMainLooper());

    public interface a {
        void a();
    }

    public c(com.kwad.sdk.core.webview.a aVar, a aVar2) {
        this.a = aVar;
        this.b = aVar2;
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    @NonNull
    public String a() {
        return "dislike";
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    public void a(String str, @NonNull com.kwad.sdk.core.webview.kwai.c cVar) {
        this.c.post(new Runnable() { // from class: com.kwad.sdk.core.webview.jshandler.c.1
            @Override // java.lang.Runnable
            public void run() {
                c.this.b.a();
            }
        });
        cVar.a(null);
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    public void b() {
        this.c.removeCallbacksAndMessages(null);
    }
}
