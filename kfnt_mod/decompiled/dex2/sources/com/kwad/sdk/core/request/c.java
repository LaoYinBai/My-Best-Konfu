package com.kwad.sdk.core.request;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.kwad.sdk.core.network.i;
import com.kwad.sdk.core.network.j;
import com.kwad.sdk.core.response.model.SdkConfigData;
import com.kwai.video.ksvodplayerkit.KSVodPlayerCoreInitConfig;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    private static boolean a = false;
    private static volatile boolean b = false;
    private static Context c;
    private static a d;

    public interface a {
        void a();

        void a(@NonNull SdkConfigData sdkConfigData);
    }

    private static class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            com.kwad.sdk.core.config.b.b(c.c);
            if (c.d != null) {
                c.d.a();
            }
            if (!c.a) {
                com.kwad.sdk.core.report.d.b(c.c);
                boolean unused = c.a = true;
            }
            c.a();
        }
    }

    public static void a() {
        com.kwad.sdk.core.d.a.a("ConfigRequestManager", "load()");
        new i<com.kwad.sdk.core.request.b, SdkConfigData>() { // from class: com.kwad.sdk.core.request.c.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.kwad.sdk.core.network.a
            @NonNull
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public com.kwad.sdk.core.request.b b() {
                return new com.kwad.sdk.core.request.b();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.kwad.sdk.core.network.i
            @NonNull
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SdkConfigData b(String str) throws Throwable {
                JSONObject jSONObject = new JSONObject(str);
                SdkConfigData sdkConfigData = new SdkConfigData();
                sdkConfigData.parseJson(jSONObject);
                try {
                    boolean zH = com.kwad.sdk.core.config.b.h();
                    String strG = com.kwad.sdk.core.config.b.g();
                    if (zH && !TextUtils.isEmpty(strG)) {
                        KSVodPlayerCoreInitConfig.updatePlayerConfig(strG);
                    }
                } catch (Throwable th) {
                    com.kwad.sdk.core.d.a.a(th);
                }
                return sdkConfigData;
            }
        }.a(new j<com.kwad.sdk.core.request.b, SdkConfigData>() { // from class: com.kwad.sdk.core.request.c.2
            @Override // com.kwad.sdk.core.network.j, com.kwad.sdk.core.network.h
            public void a(@NonNull com.kwad.sdk.core.request.b bVar) {
                com.kwad.sdk.core.d.a.a("ConfigRequestManager", "onStartRequest request url = " + bVar.a());
                super.a((AnonymousClass2) bVar);
            }

            @Override // com.kwad.sdk.core.network.j, com.kwad.sdk.core.network.h
            public void a(@NonNull com.kwad.sdk.core.request.b bVar, int i, String str) {
                super.a((AnonymousClass2) bVar, i, str);
                com.kwad.sdk.core.d.a.a("ConfigRequestManager", "onError errorCode=" + i + " errorMsg=" + str);
            }

            @Override // com.kwad.sdk.core.network.j, com.kwad.sdk.core.network.h
            public void a(@NonNull com.kwad.sdk.core.request.b bVar, @NonNull SdkConfigData sdkConfigData) {
                com.kwad.sdk.core.config.a.a(c.c);
                com.kwad.sdk.core.config.b.a(sdkConfigData);
                com.kwad.sdk.core.config.b.a(c.c, sdkConfigData);
                if (c.d != null) {
                    c.d.a(sdkConfigData);
                }
            }
        });
    }

    public static synchronized void a(Context context, a aVar) {
        if (b) {
            com.kwad.sdk.core.d.a.a("ConfigRequestManager", "config request manager has init-ed");
            return;
        }
        b = true;
        c = context;
        d = aVar;
        com.kwad.sdk.utils.i.a(new b());
    }
}
