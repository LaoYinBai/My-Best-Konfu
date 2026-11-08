package com.kwad.sdk.core.webview.request;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import com.kwad.sdk.core.network.i;
import com.kwad.sdk.core.network.j;
import com.kwad.sdk.core.webview.jshandler.w;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private static final Handler a = new Handler(Looper.getMainLooper());

    public interface a {
        void a();

        @MainThread
        void a(int i, String str);

        @MainThread
        void a(@NonNull WebCardGetDataResponse webCardGetDataResponse);
    }

    public void a(final w.a aVar, @NonNull final a aVar2) {
        new i<com.kwad.sdk.core.webview.request.a, WebCardGetDataResponse>() { // from class: com.kwad.sdk.core.webview.request.b.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.kwad.sdk.core.network.i
            @NonNull
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public WebCardGetDataResponse b(String str) throws Throwable {
                JSONObject jSONObject = new JSONObject(str);
                WebCardGetDataResponse webCardGetDataResponse = new WebCardGetDataResponse();
                webCardGetDataResponse.parseJson(jSONObject);
                return webCardGetDataResponse;
            }

            @Override // com.kwad.sdk.core.network.a
            @NonNull
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public com.kwad.sdk.core.webview.request.a b() {
                return new com.kwad.sdk.core.webview.request.a(aVar.a, aVar.b, aVar.c);
            }
        }.a(new j<com.kwad.sdk.core.webview.request.a, WebCardGetDataResponse>() { // from class: com.kwad.sdk.core.webview.request.b.2
            @Override // com.kwad.sdk.core.network.j, com.kwad.sdk.core.network.h
            public void a(@NonNull com.kwad.sdk.core.webview.request.a aVar3) {
                super.a((AnonymousClass2) aVar3);
                com.kwad.sdk.core.d.a.a("WebCardGetDataRequestManager", "onStartRequest");
                b.a.post(new Runnable() { // from class: com.kwad.sdk.core.webview.request.b.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        aVar2.a();
                    }
                });
            }

            @Override // com.kwad.sdk.core.network.j, com.kwad.sdk.core.network.h
            public void a(@NonNull com.kwad.sdk.core.webview.request.a aVar3, final int i, final String str) {
                com.kwad.sdk.core.d.a.a("WebCardGetDataRequestManager", "onError errorCode=" + i + " errorMsg=" + str);
                b.a.post(new Runnable() { // from class: com.kwad.sdk.core.webview.request.b.2.3
                    @Override // java.lang.Runnable
                    public void run() {
                        com.kwad.sdk.core.d.a.a("WebCardGetDataRequestManager", "load onError:" + String.format("code:%s__msg:%s", Integer.valueOf(i), str));
                        aVar2.a(i, str);
                    }
                });
            }

            @Override // com.kwad.sdk.core.network.j, com.kwad.sdk.core.network.h
            public void a(@NonNull com.kwad.sdk.core.webview.request.a aVar3, @NonNull final WebCardGetDataResponse webCardGetDataResponse) {
                com.kwad.sdk.core.d.a.a("WebCardGetDataRequestManager", "onSuccess");
                b.a.post(new Runnable() { // from class: com.kwad.sdk.core.webview.request.b.2.2
                    @Override // java.lang.Runnable
                    public void run() {
                        aVar2.a(webCardGetDataResponse);
                    }
                });
            }
        });
    }
}
