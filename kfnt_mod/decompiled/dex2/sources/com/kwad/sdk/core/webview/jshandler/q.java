package com.kwad.sdk.core.webview.jshandler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.api.KsAppDownloadListener;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class q implements com.kwad.sdk.core.webview.kwai.a {
    private final com.kwad.sdk.core.webview.a a;
    private com.kwad.sdk.core.download.a.b b;

    @Nullable
    private com.kwad.sdk.core.webview.kwai.c c;

    @Nullable
    private KsAppDownloadListener d;
    private AdTemplate e;

    public static final class a extends com.kwad.sdk.core.response.kwai.a {
        public double a;
        public int b;
    }

    public static final class b extends com.kwad.sdk.core.response.kwai.a {
        public String a;
        public String b;
        public String c;
        public int d;
        public long e;
        public String f;
        public String g;
        public String h;
        public String i;
    }

    public q(com.kwad.sdk.core.webview.a aVar) {
        this.a = aVar;
        try {
            this.e = new AdTemplate();
            AdTemplate adTemplateA = this.a.a();
            if (adTemplateA != null) {
                if (adTemplateA.mOriginJString != null) {
                    this.e.parseJson(new JSONObject(adTemplateA.mOriginJString));
                } else {
                    this.e.parseJson(adTemplateA.toJson());
                }
            }
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.a(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, float f) {
        if (this.c != null) {
            a aVar = new a();
            aVar.a = f;
            aVar.b = i;
            this.c.a(aVar);
        }
    }

    private static void a(@NonNull AdInfo adInfo, @NonNull b bVar) {
        adInfo.adBaseInfo.adOperationType = 1;
        adInfo.adBaseInfo.appPackageName = bVar.b;
        adInfo.adBaseInfo.appName = bVar.a;
        adInfo.adBaseInfo.appVersion = bVar.c;
        adInfo.adBaseInfo.packageSize = bVar.e;
        adInfo.adBaseInfo.appIconUrl = bVar.h;
        adInfo.adBaseInfo.appDescription = bVar.i;
        adInfo.adConversionInfo.appDownloadUrl = bVar.g;
        adInfo.downloadId = com.kwad.sdk.utils.aa.a(adInfo.adConversionInfo.appDownloadUrl);
    }

    private KsAppDownloadListener c() {
        return new com.kwad.sdk.core.download.a.c() { // from class: com.kwad.sdk.core.webview.jshandler.q.1
            @Override // com.kwad.sdk.core.download.a.c
            public void a(int i) {
                q.this.a(3, (i * 1.0f) / 100.0f);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onDownloadFailed() {
                q.this.a(1, 0.0f);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onDownloadFinished() {
                q.this.a(5, 1.0f);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onIdle() {
                q.this.a(1, 0.0f);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onInstalled() {
                q.this.a(6, 1.0f);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onProgressUpdate(int i) {
                q.this.a(2, (i * 1.0f) / 100.0f);
            }
        };
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    @NonNull
    public String a() {
        return "registerApkStatusListener";
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    public void a(String str, @NonNull com.kwad.sdk.core.webview.kwai.c cVar) {
        com.kwad.sdk.core.download.a.b bVar;
        int i;
        if (this.e == null) {
            cVar.a(-1, "native photo is null");
            return;
        }
        if (com.kwad.sdk.core.response.a.a.B(com.kwad.sdk.core.response.a.d.j(this.e))) {
            if (this.b == null) {
                this.b = new com.kwad.sdk.core.download.a.b(this.e);
            }
            bVar = this.b;
            i = 2;
        } else {
            AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(this.e);
            b bVar2 = new b();
            try {
                bVar2.parseJson(new JSONObject(str));
            } catch (Exception e) {
                com.kwad.sdk.core.d.a.a(e);
            }
            a(adInfoJ, bVar2);
            if (this.b == null) {
                this.b = new com.kwad.sdk.core.download.a.b(this.e);
            }
            bVar = this.b;
            i = 1;
        }
        bVar.a(i);
        this.c = cVar;
        if (this.d != null) {
            this.b.c(this.d);
        } else {
            this.d = c();
            this.b.a(this.d);
        }
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    public void b() {
        this.c = null;
        if (this.b == null || this.d == null) {
            return;
        }
        this.b.b(this.d);
        this.d = null;
    }
}
