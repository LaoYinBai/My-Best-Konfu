package com.kwad.sdk.core.webview.jshandler;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class p implements com.kwad.sdk.core.webview.kwai.a {
    private b a;
    private Handler b = new Handler(Looper.getMainLooper());
    private com.kwad.sdk.core.webview.kwai.c c;

    public static final class a extends com.kwad.sdk.core.response.kwai.a {
        public int a;
        public String b;
    }

    public interface b {
        @MainThread
        void a(a aVar);
    }

    public p(b bVar) {
        this.a = bVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(a aVar) {
        if (this.a != null) {
            this.a.a(aVar);
        }
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    @NonNull
    public String a() {
        return "pageStatus";
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    public void a(String str, @NonNull com.kwad.sdk.core.webview.kwai.c cVar) {
        this.c = cVar;
        try {
            final a aVar = new a();
            aVar.parseJson(new JSONObject(str));
            this.b.post(new Runnable() { // from class: com.kwad.sdk.core.webview.jshandler.p.1
                @Override // java.lang.Runnable
                public void run() {
                    p.this.a(aVar);
                    if (p.this.c != null) {
                        p.this.c.a(null);
                    }
                }
            });
        } catch (JSONException e) {
            com.kwad.sdk.core.d.a.e("WebCardPageStatusHandler", "handleJsCall error: " + e);
            cVar.a(-1, e.getMessage());
        }
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    public void b() {
        this.a = null;
        this.c = null;
        this.b.removeCallbacksAndMessages(null);
    }
}
