package com.kwad.sdk.core.webview.jshandler;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.kwad.sdk.core.download.a.a;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b implements com.kwad.sdk.core.webview.kwai.a {
    private final com.kwad.sdk.core.webview.a a;
    private Handler b;
    private boolean c;

    @Nullable
    private final com.kwad.sdk.core.download.a.b d;

    @Nullable
    private c e;

    @Nullable
    private d f;

    public static final class a extends com.kwad.sdk.core.response.kwai.a implements com.kwad.sdk.core.b {
        public boolean a;

        @Deprecated
        public boolean b;
        public int c;
        public C0052b d;
        public long e = -1;

        public boolean a() {
            return 1 == this.c;
        }
    }

    /* renamed from: com.kwad.sdk.core.webview.jshandler.b$b, reason: collision with other inner class name */
    public static final class C0052b extends com.kwad.sdk.core.response.kwai.a implements com.kwad.sdk.core.b {
        public String a;
    }

    public interface c {
        @WorkerThread
        void a(@Nullable a aVar);
    }

    public interface d {
        @MainThread
        void a(int i);
    }

    public b(@NonNull com.kwad.sdk.core.webview.a aVar, @Nullable com.kwad.sdk.core.download.a.b bVar, @Nullable c cVar) {
        this(aVar, bVar, cVar, false);
    }

    public b(@NonNull com.kwad.sdk.core.webview.a aVar, @Nullable com.kwad.sdk.core.download.a.b bVar, @Nullable c cVar, boolean z) {
        this.c = false;
        this.c = z;
        this.b = new Handler(Looper.getMainLooper());
        this.a = aVar;
        this.d = bVar;
        if (bVar != null) {
            this.d.a(1);
        }
        this.e = cVar;
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    @NonNull
    public String a() {
        return "convert";
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    public void a(String str, @NonNull com.kwad.sdk.core.webview.kwai.c cVar) {
        Handler handler;
        Runnable runnable;
        if (this.a.c()) {
            cVar.a(-1, "native adTemplate is null");
            return;
        }
        final a aVar = new a();
        try {
            aVar.parseJson(new JSONObject(str));
            aVar.a = true;
        } catch (JSONException e) {
            com.kwad.sdk.core.d.a.a(e);
        }
        if (!this.a.h) {
            if (this.e != null) {
                handler = this.b;
                runnable = new Runnable() { // from class: com.kwad.sdk.core.webview.jshandler.b.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (b.this.e != null) {
                            b.this.e.a(aVar);
                        }
                    }
                };
            }
            cVar.a(null);
        }
        handler = this.b;
        runnable = new Runnable() { // from class: com.kwad.sdk.core.webview.jshandler.b.1
            @Override // java.lang.Runnable
            public void run() {
                if (b.this.a.i || aVar.b) {
                    int iA = com.kwad.sdk.core.download.a.a.a(b.this.a.d.getContext(), b.this.a.a(), new a.b() { // from class: com.kwad.sdk.core.webview.jshandler.b.1.1
                        @Override // com.kwad.sdk.core.download.a.a.b
                        public void a() {
                            if (b.this.e != null) {
                                b.this.e.a(aVar);
                            }
                        }
                    }, b.this.d, aVar.b, b.this.c);
                    if (b.this.f != null) {
                        b.this.f.a(iA);
                    }
                }
            }
        };
        handler.post(runnable);
        cVar.a(null);
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    public void b() {
        this.b.removeCallbacksAndMessages(null);
        this.e = null;
    }
}
