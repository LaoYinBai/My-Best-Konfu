package com.kwad.sdk.reflux;

import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.core.network.i;
import com.kwad.sdk.core.request.d;
import com.kwad.sdk.core.request.model.f;
import com.kwad.sdk.core.request.model.j;
import com.kwad.sdk.core.response.model.AdResultData;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.internal.api.SceneImpl;
import com.kwad.sdk.utils.ap;
import com.kwad.sdk.utils.ax;
import java.util.List;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static int a = 12;

    /* renamed from: com.kwad.sdk.reflux.a$a, reason: collision with other inner class name */
    public interface InterfaceC0066a {
        void a(int i, String str);

        void a(@Nullable List<AdTemplate> list);

        void a_(int i);
    }

    private static void a(final f fVar, final List<String> list, final boolean z, final boolean z2, @Nullable final j jVar, @NonNull final d.a aVar, final boolean z3) {
        new i<com.kwad.sdk.core.request.a, AdResultData>() { // from class: com.kwad.sdk.reflux.a.2
            @Override // com.kwad.sdk.core.network.a
            @NonNull
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public com.kwad.sdk.core.request.a b() {
                com.kwad.sdk.core.request.a aVar2 = new com.kwad.sdk.core.request.a(fVar, list, z, null, jVar);
                aVar2.a(z2 ? 1 : 0);
                return aVar2;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.kwad.sdk.core.network.i
            @NonNull
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public AdResultData b(String str) throws Throwable {
                JSONObject jSONObject = new JSONObject(str);
                AdResultData adResultData = new AdResultData(fVar.a);
                adResultData.parseJson(jSONObject);
                return adResultData;
            }
        }.a(new com.kwad.sdk.core.network.j<com.kwad.sdk.core.request.a, AdResultData>() { // from class: com.kwad.sdk.reflux.a.3
            @Override // com.kwad.sdk.core.network.j, com.kwad.sdk.core.network.h
            public void a(@NonNull com.kwad.sdk.core.request.a aVar2, int i, String str) {
                aVar.a(i, str);
            }

            @Override // com.kwad.sdk.core.network.j, com.kwad.sdk.core.network.h
            public void a(@NonNull com.kwad.sdk.core.request.a aVar2, @NonNull AdResultData adResultData) {
                if (!adResultData.isAdResultDataEmpty() || z3) {
                    aVar.a(adResultData);
                } else {
                    aVar.a(com.kwad.sdk.core.network.f.e.n, com.kwad.sdk.core.network.f.e.o);
                }
            }
        });
    }

    public static void a(@NonNull SceneImpl sceneImpl, final InterfaceC0066a interfaceC0066a) {
        boolean zA = ap.a().a(sceneImpl, "loadInterstitialAd");
        final long jElapsedRealtime = SystemClock.elapsedRealtime();
        sceneImpl.setAdStyle(11);
        sceneImpl.setAdNum(a);
        a(new f(sceneImpl), null, false, zA, null, new d.a() { // from class: com.kwad.sdk.reflux.a.1
            @Override // com.kwad.sdk.core.request.d.a
            public void a(final int i, final String str) {
                ax.a(new Runnable() { // from class: com.kwad.sdk.reflux.a.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        com.kwad.sdk.core.d.a.e("RefluxAdLoadManager", "loadRefluxAd onError:" + String.format("code:%s__msg:%s", Integer.valueOf(i), str));
                        interfaceC0066a.a(i, str);
                    }
                });
            }

            @Override // com.kwad.sdk.core.request.d.a
            public void a(@NonNull final AdResultData adResultData) {
                ax.a(new Runnable() { // from class: com.kwad.sdk.reflux.a.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            interfaceC0066a.a_(adResultData.adTemplateList.size());
                        } catch (Throwable th) {
                            com.kwad.sdk.core.d.a.b(th);
                        }
                    }
                });
                ax.a(new Runnable() { // from class: com.kwad.sdk.reflux.a.1.3
                    @Override // java.lang.Runnable
                    public void run() {
                        interfaceC0066a.a(adResultData.adTemplateList);
                        a.b(adResultData, jElapsedRealtime);
                    }
                });
            }
        }, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(AdResultData adResultData, long j) {
        AdTemplate adTemplate;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (adResultData.adTemplateList.size() <= 0 || (adTemplate = adResultData.adTemplateList.get(0)) == null) {
            return;
        }
        com.kwad.sdk.core.report.d.a(adTemplate, jElapsedRealtime - j);
    }
}
