package com.kwad.sdk.core.webview.jshandler;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import com.kwad.sdk.core.download.a.a;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class h implements com.kwad.sdk.core.webview.kwai.a {
    private final com.kwad.sdk.core.webview.a a;
    private com.kwad.sdk.core.download.a.b c;
    private final Handler d = new Handler(Looper.getMainLooper());
    private final AdTemplate b = new AdTemplate();

    public static final class a extends com.kwad.sdk.core.response.kwai.a {
        public int a;
        public String b;
        public String c;
        public String d;
        public int e;
        public int f;
        public String g;
        public String h;
        public String i;
        public String j;
        public String k;
        public String l;
        public String m;

        @Deprecated
        public boolean n;
        public boolean o;
        public boolean p;
    }

    public h(com.kwad.sdk.core.webview.a aVar) {
        this.a = aVar;
        try {
            AdTemplate adTemplateA = this.a.a();
            if (adTemplateA != null) {
                if (adTemplateA.mOriginJString != null) {
                    this.b.parseJson(new JSONObject(adTemplateA.mOriginJString));
                } else {
                    this.b.parseJson(adTemplateA.toJson());
                }
            }
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.a(e);
        }
    }

    private static void a(@NonNull AdInfo adInfo, @NonNull a aVar) {
        AdInfo.AdConversionInfo adConversionInfo = adInfo.adConversionInfo;
        adConversionInfo.deeplinkUrl = aVar.i;
        adConversionInfo.marketUrl = aVar.m;
        adInfo.adBaseInfo.adOperationType = aVar.a;
        adInfo.adBaseInfo.appPackageName = aVar.c;
        adInfo.adBaseInfo.appName = aVar.b;
        adInfo.adBaseInfo.appVersion = aVar.d;
        adInfo.adBaseInfo.packageSize = aVar.f;
        adInfo.adBaseInfo.appIconUrl = aVar.j;
        adInfo.adBaseInfo.appDescription = aVar.k;
        if (!com.kwad.sdk.core.response.a.a.B(adInfo)) {
            adInfo.adConversionInfo.h5Url = aVar.h;
        } else {
            adInfo.adConversionInfo.appDownloadUrl = aVar.h;
            adInfo.downloadId = com.kwad.sdk.utils.aa.a(adInfo.adConversionInfo.appDownloadUrl);
        }
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    @NonNull
    public String a() {
        return "handleAdUrl";
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    public void a(String str, @NonNull com.kwad.sdk.core.webview.kwai.c cVar) {
        com.kwad.sdk.core.download.a.b bVar;
        int i;
        if (com.kwad.sdk.core.response.a.a.B(com.kwad.sdk.core.response.a.d.j(this.b))) {
            if (this.c == null) {
                this.c = new com.kwad.sdk.core.download.a.b(this.b);
            }
            bVar = this.c;
            i = 2;
        } else {
            AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(this.b);
            a aVar = new a();
            try {
                aVar.parseJson(new JSONObject(str));
            } catch (Exception e) {
                com.kwad.sdk.core.d.a.a(e);
            }
            a(adInfoJ, aVar);
            if (this.c == null) {
                this.c = new com.kwad.sdk.core.download.a.b(this.b);
            }
            bVar = this.c;
            i = 1;
        }
        bVar.a(i);
        this.d.post(new Runnable() { // from class: com.kwad.sdk.core.webview.jshandler.h.1
            @Override // java.lang.Runnable
            public void run() {
                com.kwad.sdk.core.download.a.a.a(h.this.a.d.getContext(), h.this.b, new a.b() { // from class: com.kwad.sdk.core.webview.jshandler.h.1.1
                    @Override // com.kwad.sdk.core.download.a.a.b
                    public void a() {
                    }
                }, h.this.c, false);
            }
        });
        cVar.a(null);
    }

    @Override // com.kwad.sdk.core.webview.kwai.a
    public void b() {
        this.d.removeCallbacksAndMessages(null);
    }
}
