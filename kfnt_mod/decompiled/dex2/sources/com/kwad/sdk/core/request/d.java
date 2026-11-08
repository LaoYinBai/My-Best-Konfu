package com.kwad.sdk.core.request;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.api.KsFeedAd;
import com.kwad.sdk.api.KsLoadManager;
import com.kwad.sdk.api.KsScene;
import com.kwad.sdk.core.network.f;
import com.kwad.sdk.core.network.i;
import com.kwad.sdk.core.preload.SplashPreloadManager;
import com.kwad.sdk.core.request.model.j;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdResultData;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.feed.FeedType;
import com.kwad.sdk.feed.b;
import com.kwad.sdk.internal.api.SceneImpl;
import com.kwad.sdk.reward.n;
import com.kwad.sdk.utils.ap;
import com.kwad.sdk.utils.as;
import com.mbridge.msdk.interstitial.view.MBInterstitialActivity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d implements KsLoadManager {
    private static final Handler a = new Handler(Looper.getMainLooper());

    /* renamed from: com.kwad.sdk.core.request.d$5, reason: invalid class name */
    class AnonymousClass5 implements a {
        final /* synthetic */ KsLoadManager.FeedAdListener a;
        final /* synthetic */ KsScene b;

        AnonymousClass5(KsLoadManager.FeedAdListener feedAdListener, KsScene ksScene) {
            this.a = feedAdListener;
            this.b = ksScene;
        }

        @Override // com.kwad.sdk.core.request.d.a
        public void a(final int i, final String str) {
            d.a.post(new Runnable() { // from class: com.kwad.sdk.core.request.d.5.1
                @Override // java.lang.Runnable
                public void run() {
                    com.kwad.sdk.core.d.a.e("AdRequestManager", "loadFeedAd onError:" + String.format("code:%s__msg:%s", Integer.valueOf(i), str));
                    AnonymousClass5.this.a.onError(i, str);
                }
            });
        }

        @Override // com.kwad.sdk.core.request.d.a
        public void a(@NonNull AdResultData adResultData) throws Throwable {
            d.this.c(adResultData, this.b.getPosId());
            final ArrayList arrayList = new ArrayList();
            String str = null;
            for (AdTemplate adTemplate : adResultData.adTemplateList) {
                if (adTemplate != null) {
                    AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(adTemplate);
                    if (FeedType.checkTypeValid(adTemplate)) {
                        if (this.b instanceof SceneImpl) {
                            adTemplate.mAdScene = (SceneImpl) this.b;
                        }
                        arrayList.add(new com.kwad.sdk.feed.b(adTemplate, this.b.getWidth(), true));
                    } else {
                        str = String.format("(模板不匹配materialType:%s_feedType:%s)", Integer.valueOf(com.kwad.sdk.core.response.a.a.R(adInfoJ)), FeedType.fromInt(adTemplate.type));
                    }
                }
            }
            if (!arrayList.isEmpty()) {
                d.a.post(new Runnable() { // from class: com.kwad.sdk.core.request.d.5.2
                    @Override // java.lang.Runnable
                    public void run() {
                        int size = arrayList.size();
                        if (size == 0) {
                            AnonymousClass5.this.a.onFeedAdLoad(arrayList);
                            return;
                        }
                        final b.a aVar = new b.a(size);
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            ((com.kwad.sdk.feed.b) ((KsFeedAd) it.next())).a(new b.InterfaceC0061b() { // from class: com.kwad.sdk.core.request.d.5.2.1
                                @Override // com.kwad.sdk.feed.b.InterfaceC0061b
                                public void a() {
                                    if (aVar.a()) {
                                        AnonymousClass5.this.a.onFeedAdLoad(arrayList);
                                    }
                                }
                            });
                        }
                    }
                });
                return;
            }
            a(f.e.n, f.e.o + str);
        }
    }

    public interface a {
        @WorkerThread
        void a(int i, String str);

        @WorkerThread
        void a(@NonNull AdResultData adResultData);
    }

    private static class b {
        private volatile boolean a;

        private b() {
            this.a = false;
        }
    }

    private static j a(KsScene ksScene, boolean z) {
        int iF;
        boolean zO = com.kwad.sdk.core.config.b.o();
        if (KsAdSDKImpl.get().getContext() != null && (iF = as.f(KsAdSDKImpl.get().getContext(), String.valueOf(ksScene.getPosId()))) != -1) {
            zO = iF == 1;
        }
        j jVar = new j();
        if (z) {
            jVar.a = zO ? 1 : 0;
        }
        return jVar;
    }

    private AdTemplate a(long j) {
        Object objC = com.kwad.sdk.core.diskcache.a.a.a().c("feed_ad_cache_" + j);
        if (objC == null || objC == "") {
            return null;
        }
        AdTemplate adTemplate = new AdTemplate();
        try {
            adTemplate.parseJson(new JSONObject(com.kwad.sdk.core.a.d.b(objC.toString())));
            return adTemplate;
        } catch (JSONException e) {
            com.kwad.sdk.core.d.a.b(e);
            return null;
        }
    }

    private static void a(com.kwad.sdk.core.request.model.f fVar, @NonNull a aVar, boolean z) {
        a(fVar, null, false, z, a((KsScene) fVar.a, false), aVar);
    }

    private static void a(com.kwad.sdk.core.request.model.f fVar, List<String> list, boolean z, boolean z2, @Nullable j jVar, @NonNull a aVar) {
        a(fVar, list, z, z2, jVar, aVar, false);
    }

    private static void a(final com.kwad.sdk.core.request.model.f fVar, final List<String> list, final boolean z, final boolean z2, @Nullable final j jVar, @NonNull final a aVar, final boolean z3) {
        new i<com.kwad.sdk.core.request.a, AdResultData>() { // from class: com.kwad.sdk.core.request.d.8
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
        }.a(new com.kwad.sdk.core.network.j<com.kwad.sdk.core.request.a, AdResultData>() { // from class: com.kwad.sdk.core.request.d.9
            @Override // com.kwad.sdk.core.network.j, com.kwad.sdk.core.network.h
            public void a(@NonNull com.kwad.sdk.core.request.a aVar2, int i, String str) {
                aVar.a(i, str);
            }

            @Override // com.kwad.sdk.core.network.j, com.kwad.sdk.core.network.h
            public void a(@NonNull com.kwad.sdk.core.request.a aVar2, @NonNull AdResultData adResultData) {
                if (!adResultData.isAdResultDataEmpty() || z3) {
                    aVar.a(adResultData);
                } else {
                    aVar.a(f.e.n, f.e.o);
                }
            }
        });
    }

    private void a(Runnable runnable) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            runnable.run();
        } else {
            a.post(runnable);
        }
    }

    private boolean a(AdTemplate adTemplate) {
        if (adTemplate == null) {
            return false;
        }
        return com.kwad.sdk.core.response.a.d.j(adTemplate).adBaseInfo.adCacheSecond * 1000 > System.currentTimeMillis();
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

    private void b(AdTemplate adTemplate) throws Throwable {
        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(adTemplate);
        adInfoJ.adBaseInfo.adCacheSecond = (System.currentTimeMillis() / 1000) + adInfoJ.adBaseInfo.adCacheSecond;
        com.kwad.sdk.core.diskcache.a.a.a().a("feed_ad_cache_" + adTemplate.posId, com.kwad.sdk.core.a.d.a(adTemplate.toJson().toString()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(AdResultData adResultData, long j) throws Throwable {
        AdTemplate adTemplateA = a(j);
        if (adResultData.adTemplateList.isEmpty() && a(adTemplateA)) {
            adResultData.adTemplateList.add(adTemplateA);
            return;
        }
        if (adResultData.adTemplateList.isEmpty()) {
            return;
        }
        AdTemplate adTemplate = adResultData.adTemplateList.get(0);
        if (com.kwad.sdk.core.response.a.d.j(adTemplate).adBaseInfo.adCacheSwitch == 0) {
            return;
        }
        if (adTemplateA == null) {
            b(adTemplate);
            return;
        }
        int iF = com.kwad.sdk.core.response.a.a.F(com.kwad.sdk.core.response.a.d.j(adTemplate));
        int iF2 = com.kwad.sdk.core.response.a.a.F(com.kwad.sdk.core.response.a.d.j(adTemplateA));
        if (!a(adTemplateA) || iF2 <= iF) {
            b(adTemplate);
        } else {
            adResultData.adTemplateList.set(0, adTemplateA);
        }
    }

    public void a(KsScene ksScene) {
        com.kwad.sdk.core.report.d.b();
        ksScene.setAdStyle(4);
        ksScene.setAdNum(5);
        com.kwad.sdk.core.d.a.a("AdRequestManager", "loadSplashScreenCache ");
        a(new com.kwad.sdk.core.request.model.f(ksScene), new ArrayList(), false, false, a(ksScene, false), new a() { // from class: com.kwad.sdk.core.request.d.12
            @Override // com.kwad.sdk.core.request.d.a
            public void a(int i, String str) {
                com.kwad.sdk.core.d.a.a("AdRequestManager", "loadSplashScreenCache onError:" + String.format("code:%s__msg:%s", Integer.valueOf(i), str));
            }

            @Override // com.kwad.sdk.core.request.d.a
            public void a(@NonNull AdResultData adResultData) {
                if (adResultData.adTemplateList.size() > 0) {
                    com.kwad.sdk.core.d.a.a("AdRequestManager", "loadSplashScreenCache onSuccess:" + adResultData.adTemplateList.size() + " saved " + SplashPreloadManager.b().a(adResultData, false));
                    com.kwad.sdk.core.report.d.b(adResultData.adTemplateList.size());
                }
            }
        });
    }

    @Override // com.kwad.sdk.api.KsLoadManager
    public void loadConfigFeedAd(KsScene ksScene, @NonNull KsLoadManager.FeedAdListener feedAdListener) {
        boolean zA = ap.a().a(ksScene, "loadConfigFeedAd");
        ksScene.setAdStyle(1);
        a(new com.kwad.sdk.core.request.model.f(ksScene), null, false, zA, a(ksScene, true), new AnonymousClass5(feedAdListener, ksScene), true);
    }

    @Override // com.kwad.sdk.api.KsLoadManager
    public void loadDrawAd(KsScene ksScene, @NonNull final KsLoadManager.DrawAdListener drawAdListener) {
        boolean zA = ap.a().a(ksScene, "loadDrawAd");
        ksScene.setAdStyle(6);
        a(new com.kwad.sdk.core.request.model.f(ksScene), new a() { // from class: com.kwad.sdk.core.request.d.6
            @Override // com.kwad.sdk.core.request.d.a
            public void a(final int i, final String str) {
                d.a.post(new Runnable() { // from class: com.kwad.sdk.core.request.d.6.1
                    @Override // java.lang.Runnable
                    public void run() {
                        com.kwad.sdk.core.d.a.e("AdRequestManager", "loadDrawAd onError:" + String.format("code:%s__msg:%s", Integer.valueOf(i), str));
                        drawAdListener.onError(i, str);
                    }
                });
            }

            @Override // com.kwad.sdk.core.request.d.a
            public void a(@NonNull AdResultData adResultData) {
                final ArrayList arrayList = new ArrayList();
                for (AdTemplate adTemplate : adResultData.adTemplateList) {
                    if (adTemplate != null && !TextUtils.isEmpty(com.kwad.sdk.core.response.a.a.a(com.kwad.sdk.core.response.a.d.j(adTemplate)))) {
                        arrayList.add(new com.kwad.sdk.draw.b(adTemplate));
                    }
                }
                if (!arrayList.isEmpty()) {
                    d.a.post(new Runnable() { // from class: com.kwad.sdk.core.request.d.6.2
                        @Override // java.lang.Runnable
                        public void run() {
                            drawAdListener.onDrawAdLoad(arrayList);
                        }
                    });
                    return;
                }
                a(f.e.n, f.e.o + "(无视频资源)");
            }
        }, zA);
    }

    @Override // com.kwad.sdk.api.KsLoadManager
    public void loadFeedAd(final KsScene ksScene, @NonNull final KsLoadManager.FeedAdListener feedAdListener) {
        boolean zA = ap.a().a(ksScene, "loadFeedAd");
        ksScene.setAdStyle(1);
        a(new com.kwad.sdk.core.request.model.f(ksScene), null, false, zA, a(ksScene, true), new a() { // from class: com.kwad.sdk.core.request.d.4
            @Override // com.kwad.sdk.core.request.d.a
            public void a(final int i, final String str) {
                d.a.post(new Runnable() { // from class: com.kwad.sdk.core.request.d.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        com.kwad.sdk.core.d.a.e("AdRequestManager", "loadFeedAd onError:" + String.format("code:%s__msg:%s", Integer.valueOf(i), str));
                        feedAdListener.onError(i, str);
                    }
                });
            }

            @Override // com.kwad.sdk.core.request.d.a
            public void a(@NonNull AdResultData adResultData) throws Throwable {
                d.this.c(adResultData, ksScene.getPosId());
                final ArrayList arrayList = new ArrayList();
                String str = null;
                for (AdTemplate adTemplate : adResultData.adTemplateList) {
                    if (adTemplate != null) {
                        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(adTemplate);
                        if (FeedType.checkTypeValid(adTemplate)) {
                            if (ksScene instanceof SceneImpl) {
                                adTemplate.mAdScene = (SceneImpl) ksScene;
                            }
                            arrayList.add(new com.kwad.sdk.feed.b(adTemplate));
                        } else {
                            str = String.format("(模板不匹配materialType:%s_feedType:%s)", Integer.valueOf(com.kwad.sdk.core.response.a.a.R(adInfoJ)), FeedType.fromInt(adTemplate.type));
                        }
                    }
                }
                if (!arrayList.isEmpty()) {
                    d.a.post(new Runnable() { // from class: com.kwad.sdk.core.request.d.4.2
                        @Override // java.lang.Runnable
                        public void run() {
                            feedAdListener.onFeedAdLoad(arrayList);
                        }
                    });
                    return;
                }
                a(f.e.n, f.e.o + str);
            }
        }, true);
    }

    @Override // com.kwad.sdk.api.KsLoadManager
    public void loadFullScreenVideoAd(final KsScene ksScene, @NonNull final KsLoadManager.FullScreenVideoAdListener fullScreenVideoAdListener) {
        boolean zA = ap.a().a(ksScene, "loadFullScreenVideoAd");
        final long jElapsedRealtime = SystemClock.elapsedRealtime();
        ksScene.setAdStyle(3);
        a(new com.kwad.sdk.core.request.model.f(ksScene), new a() { // from class: com.kwad.sdk.core.request.d.13
            @Override // com.kwad.sdk.core.request.d.a
            public void a(final int i, final String str) {
                d.a.post(new Runnable() { // from class: com.kwad.sdk.core.request.d.13.1
                    @Override // java.lang.Runnable
                    public void run() {
                        com.kwad.sdk.core.d.a.e("AdRequestManager", "loadFullScreenVideoAd onError:" + String.format("code:%s__msg:%s", Integer.valueOf(i), str));
                        fullScreenVideoAdListener.onError(i, str);
                    }
                });
            }

            @Override // com.kwad.sdk.core.request.d.a
            public void a(@NonNull final AdResultData adResultData) {
                d.a.post(new Runnable() { // from class: com.kwad.sdk.core.request.d.13.2
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            fullScreenVideoAdListener.onRequestResult(adResultData.adTemplateList.size());
                        } catch (Throwable th) {
                            com.kwad.sdk.core.d.a.b(th);
                        }
                    }
                });
                final ArrayList arrayList = new ArrayList();
                int iC = com.kwad.sdk.core.config.b.C();
                boolean z = false;
                boolean z2 = false;
                for (AdTemplate adTemplate : adResultData.adTemplateList) {
                    if (adTemplate != null) {
                        if (adTemplate.mAdScene == null && (ksScene instanceof SceneImpl)) {
                            adTemplate.mAdScene = (SceneImpl) ksScene;
                        }
                        String strA = com.kwad.sdk.core.response.a.a.a(com.kwad.sdk.core.response.a.d.j(adTemplate));
                        com.kwad.sdk.core.d.a.a("loadFullScreenVideoAd", " videoUrl:" + strA);
                        if (!TextUtils.isEmpty(strA)) {
                            com.kwad.sdk.core.d.a.a("loadFullScreenVideoAd", " preSize:" + iC);
                            if (iC < 0) {
                                if (n.a(adTemplate)) {
                                    arrayList.add(new com.kwad.sdk.fullscreen.b(adTemplate));
                                    z = true;
                                    z2 = true;
                                } else {
                                    z = true;
                                }
                            } else if (iC <= 0 || com.kwad.sdk.core.videocache.b.a.a(KsAdSDKImpl.get().getContext()).a(strA, iC * 1024)) {
                                arrayList.add(new com.kwad.sdk.fullscreen.b(adTemplate));
                                z = true;
                                z2 = true;
                            } else {
                                z = true;
                            }
                        }
                    }
                }
                com.kwad.sdk.core.d.a.a("loadFullScreenVideoAd", " downloadVideo finish");
                if (z) {
                    if (z2) {
                        d.a.post(new Runnable() { // from class: com.kwad.sdk.core.request.d.13.3
                            @Override // java.lang.Runnable
                            public void run() {
                                fullScreenVideoAdListener.onFullScreenVideoAdLoad(arrayList);
                                d.b(adResultData, jElapsedRealtime);
                            }
                        });
                        return;
                    } else {
                        a(f.f.n, f.f.o);
                        return;
                    }
                }
                a(f.e.n, f.e.o + "(无视频资源)");
            }
        }, zA);
    }

    @Override // com.kwad.sdk.api.KsLoadManager
    public void loadInterstitialAd(@NonNull final KsScene ksScene, @NonNull final KsLoadManager.InterstitialAdListener interstitialAdListener) {
        boolean zA = ap.a().a(ksScene, "loadInterstitialAd");
        final long jElapsedRealtime = SystemClock.elapsedRealtime();
        ksScene.setAdStyle(13);
        a(new com.kwad.sdk.core.request.model.f(ksScene), new a() { // from class: com.kwad.sdk.core.request.d.7
            @Override // com.kwad.sdk.core.request.d.a
            public void a(final int i, final String str) {
                d.a.post(new Runnable() { // from class: com.kwad.sdk.core.request.d.7.1
                    @Override // java.lang.Runnable
                    public void run() {
                        com.kwad.sdk.core.d.a.e("AdRequestManager", "loadInterstitialAd onError:" + String.format("code:%s__msg:%s", Integer.valueOf(i), str));
                        interstitialAdListener.onError(i, str);
                    }
                });
            }

            @Override // com.kwad.sdk.core.request.d.a
            public void a(@NonNull final AdResultData adResultData) {
                com.kwad.sdk.d.a aVar;
                d.a.post(new Runnable() { // from class: com.kwad.sdk.core.request.d.7.2
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            interstitialAdListener.onRequestResult(adResultData.adTemplateList.size());
                        } catch (Throwable th) {
                            com.kwad.sdk.core.d.a.b(th);
                        }
                    }
                });
                final ArrayList arrayList = new ArrayList();
                int iC = com.kwad.sdk.core.config.b.C();
                for (AdTemplate adTemplate : adResultData.adTemplateList) {
                    if (adTemplate != null) {
                        if (adTemplate.mAdScene == null && (ksScene instanceof SceneImpl)) {
                            adTemplate.mAdScene = (SceneImpl) ksScene;
                        }
                        String strA = com.kwad.sdk.core.response.a.a.a(com.kwad.sdk.core.response.a.d.j(adTemplate));
                        if (TextUtils.isEmpty(strA)) {
                            aVar = new com.kwad.sdk.d.a(ksScene, adTemplate);
                        } else if (iC < 0) {
                            if (n.a(adTemplate)) {
                                aVar = new com.kwad.sdk.d.a(ksScene, adTemplate);
                            }
                        } else if (iC <= 0 || com.kwad.sdk.core.videocache.b.a.a(KsAdSDKImpl.get().getContext()).a(strA, iC * 1024)) {
                            aVar = new com.kwad.sdk.d.a(ksScene, adTemplate);
                        }
                        arrayList.add(aVar);
                    }
                }
                if (arrayList.size() == 0) {
                    a(f.e.n, f.e.o);
                } else {
                    d.a.post(new Runnable() { // from class: com.kwad.sdk.core.request.d.7.3
                        @Override // java.lang.Runnable
                        public void run() {
                            interstitialAdListener.onInterstitialAdLoad(arrayList);
                            d.b(adResultData, jElapsedRealtime);
                        }
                    });
                }
            }
        }, zA);
    }

    @Override // com.kwad.sdk.api.KsLoadManager
    public void loadNativeAd(KsScene ksScene, @NonNull final KsLoadManager.NativeAdListener nativeAdListener) {
        boolean zA = ap.a().a(ksScene, "loadNativeAd");
        ksScene.setAdStyle(10000);
        a(new com.kwad.sdk.core.request.model.f(ksScene), new a() { // from class: com.kwad.sdk.core.request.d.15
            @Override // com.kwad.sdk.core.request.d.a
            public void a(final int i, final String str) {
                d.a.post(new Runnable() { // from class: com.kwad.sdk.core.request.d.15.1
                    @Override // java.lang.Runnable
                    public void run() {
                        com.kwad.sdk.core.d.a.e("AdRequestManager", "loadNativeAd onError:" + String.format("code:%s__msg:%s", Integer.valueOf(i), str));
                        nativeAdListener.onError(i, str);
                    }
                });
            }

            @Override // com.kwad.sdk.core.request.d.a
            public void a(@NonNull AdResultData adResultData) {
                final ArrayList arrayList = new ArrayList();
                for (AdTemplate adTemplate : adResultData.adTemplateList) {
                    if (adTemplate != null) {
                        arrayList.add(new com.kwad.sdk.f.a(adTemplate));
                    }
                }
                d.a.post(new Runnable() { // from class: com.kwad.sdk.core.request.d.15.2
                    @Override // java.lang.Runnable
                    public void run() {
                        nativeAdListener.onNativeAdLoad(arrayList);
                    }
                });
            }
        }, zA);
    }

    @Override // com.kwad.sdk.api.KsLoadManager
    public void loadNativeAd(String str, @NonNull final KsLoadManager.NativeAdListener nativeAdListener) throws Throwable {
        Runnable runnable;
        if (ap.a().a(null, "loadNativeAdByJson") && ap.a().b() == 1) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            a(new Runnable() { // from class: com.kwad.sdk.core.request.d.16
                @Override // java.lang.Runnable
                public void run() {
                    com.kwad.sdk.core.d.a.e("AdRequestManager", "method parseJson params jsonResult is empty");
                    nativeAdListener.onError(f.e.n, f.e.o);
                }
            });
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            final AdResultData adResultData = new AdResultData();
            adResultData.parseJson(jSONObject);
            if (adResultData.result != 1) {
                a(new Runnable() { // from class: com.kwad.sdk.core.request.d.17
                    @Override // java.lang.Runnable
                    public void run() {
                        com.kwad.sdk.core.d.a.e("AdRequestManager", "loadNativeAd onError:" + String.format("%s__%s", Integer.valueOf(adResultData.result), adResultData.errorMsg));
                        nativeAdListener.onError(adResultData.result, adResultData.errorMsg);
                    }
                });
                return;
            }
            if (adResultData.isAdResultDataEmpty()) {
                runnable = new Runnable() { // from class: com.kwad.sdk.core.request.d.2
                    @Override // java.lang.Runnable
                    public void run() {
                        nativeAdListener.onError(f.e.n, f.e.o);
                    }
                };
            } else {
                final ArrayList arrayList = new ArrayList();
                for (AdTemplate adTemplate : adResultData.adTemplateList) {
                    if (adTemplate != null) {
                        arrayList.add(new com.kwad.sdk.f.a(adTemplate));
                    }
                }
                runnable = new Runnable() { // from class: com.kwad.sdk.core.request.d.3
                    @Override // java.lang.Runnable
                    public void run() {
                        nativeAdListener.onNativeAdLoad(arrayList);
                    }
                };
            }
            a(runnable);
        } catch (JSONException e) {
            com.kwad.sdk.core.d.a.a(e);
            nativeAdListener.onError(f.d.n, f.d.o);
        }
    }

    @Override // com.kwad.sdk.api.KsLoadManager
    public void loadRewardVideoAd(final KsScene ksScene, @NonNull final KsLoadManager.RewardVideoAdListener rewardVideoAdListener) {
        boolean zA = ap.a().a(ksScene, "loadRewardVideoAd");
        final long jElapsedRealtime = SystemClock.elapsedRealtime();
        ksScene.setAdStyle(2);
        a(new com.kwad.sdk.core.request.model.f(ksScene), new a() { // from class: com.kwad.sdk.core.request.d.14
            @Override // com.kwad.sdk.core.request.d.a
            public void a(final int i, final String str) {
                d.a.post(new Runnable() { // from class: com.kwad.sdk.core.request.d.14.1
                    @Override // java.lang.Runnable
                    public void run() {
                        com.kwad.sdk.core.d.a.e("AdRequestManager", "loadRewardVideoAd onError:" + String.format("code:%s__msg:%s", Integer.valueOf(i), str));
                        rewardVideoAdListener.onError(i, str);
                    }
                });
            }

            @Override // com.kwad.sdk.core.request.d.a
            public void a(@NonNull final AdResultData adResultData) {
                d.a.post(new Runnable() { // from class: com.kwad.sdk.core.request.d.14.2
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            rewardVideoAdListener.onRequestResult(adResultData.adTemplateList.size());
                        } catch (Throwable th) {
                            com.kwad.sdk.core.d.a.b(th);
                        }
                    }
                });
                final ArrayList arrayList = new ArrayList();
                int iC = com.kwad.sdk.core.config.b.C();
                boolean z = false;
                boolean z2 = false;
                boolean z3 = false;
                for (AdTemplate adTemplate : adResultData.adTemplateList) {
                    if (adTemplate != null) {
                        if (adTemplate.mAdScene == null && (ksScene instanceof SceneImpl)) {
                            adTemplate.mAdScene = (SceneImpl) ksScene;
                        }
                        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(adTemplate);
                        String strA = com.kwad.sdk.core.response.a.a.a(adInfoJ);
                        if (com.kwad.sdk.core.response.a.a.as(adInfoJ)) {
                            arrayList.add(new com.kwad.sdk.reward.f(adTemplate));
                            z = true;
                        } else if (!TextUtils.isEmpty(strA)) {
                            if (iC < 0) {
                                if (n.a(adTemplate)) {
                                    arrayList.add(new com.kwad.sdk.reward.f(adTemplate));
                                    z2 = true;
                                    z3 = true;
                                } else {
                                    z2 = true;
                                }
                            } else if (iC <= 0 || com.kwad.sdk.core.videocache.b.a.a(KsAdSDKImpl.get().getContext()).a(strA, iC * 1024)) {
                                arrayList.add(new com.kwad.sdk.reward.f(adTemplate));
                                z2 = true;
                                z3 = true;
                            } else {
                                z2 = true;
                            }
                        }
                    }
                }
                if (!z) {
                    if (!z2) {
                        a(f.e.n, f.e.o + "(无视频资源)");
                        return;
                    }
                    if (!z3) {
                        a(f.f.n, f.f.o);
                        return;
                    }
                }
                d.a.post(new Runnable() { // from class: com.kwad.sdk.core.request.d.14.3
                    @Override // java.lang.Runnable
                    public void run() {
                        rewardVideoAdListener.onRewardVideoAdLoad(arrayList);
                        d.b(adResultData, jElapsedRealtime);
                    }
                });
            }
        }, zA);
    }

    @Override // com.kwad.sdk.api.KsLoadManager
    public void loadSplashScreenAd(@NonNull final KsScene ksScene, @NonNull final KsLoadManager.SplashScreenAdListener splashScreenAdListener) {
        boolean zA = ap.a().a(ksScene, "loadSplashScreenAd");
        ksScene.setAdStyle(4);
        List<String> listC = SplashPreloadManager.b().c();
        ksScene.setAdNum(1);
        com.kwad.sdk.core.d.a.a("AdRequestManager", "loadSplashScreenAd ");
        final b bVar = new b();
        com.kwad.sdk.core.report.d.a();
        a.postDelayed(new Runnable() { // from class: com.kwad.sdk.core.request.d.1
            @Override // java.lang.Runnable
            public void run() {
                d.this.a(ksScene);
            }
        }, MBInterstitialActivity.WEB_LOAD_TIME);
        final Runnable runnable = new Runnable() { // from class: com.kwad.sdk.core.request.d.10
            @Override // java.lang.Runnable
            public void run() {
                bVar.a = true;
                com.kwad.sdk.core.d.a.a("AdRequestManager", "loadSplashAd mTimeOutRunnable timeOut");
                splashScreenAdListener.onError(f.g.n, f.g.o);
                com.kwad.sdk.core.report.d.a(4);
            }
        };
        a.postDelayed(runnable, com.kwad.sdk.core.config.b.i());
        a(new com.kwad.sdk.core.request.model.f(ksScene), listC, true, zA, a(ksScene, false), new a() { // from class: com.kwad.sdk.core.request.d.11
            @Override // com.kwad.sdk.core.request.d.a
            public void a(final int i, final String str) {
                if (!bVar.a) {
                    d.a.removeCallbacks(runnable);
                    d.a.post(new Runnable() { // from class: com.kwad.sdk.core.request.d.11.1
                        @Override // java.lang.Runnable
                        public void run() {
                            com.kwad.sdk.core.d.a.a("AdRequestManager", "loadSplashAd onError:" + String.format("code:%s__msg:%s", Integer.valueOf(i), str));
                            splashScreenAdListener.onError(i, str);
                            if (i == f.h.n) {
                                com.kwad.sdk.core.report.d.a(0);
                            } else {
                                com.kwad.sdk.core.report.d.a(3);
                            }
                        }
                    });
                } else {
                    com.kwad.sdk.core.d.a.a("AdRequestManager", "loadSplashAd onError isTimeOut return " + String.format("code:%s__msg:%s", Integer.valueOf(i), str));
                }
            }

            @Override // com.kwad.sdk.core.request.d.a
            public void a(@NonNull final AdResultData adResultData) {
                if (adResultData.adTemplateList.size() <= 0) {
                    if (!bVar.a) {
                        d.a.removeCallbacks(runnable);
                        a(f.e.n, f.e.o);
                        com.kwad.sdk.core.report.d.a(3);
                        return;
                    }
                    com.kwad.sdk.core.d.a.a("AdRequestManager", "loadSplashAd isTimeOut return ");
                }
                final com.kwad.sdk.splashscreen.c cVar = new com.kwad.sdk.splashscreen.c(ksScene, adResultData);
                d.a.post(new Runnable() { // from class: com.kwad.sdk.core.request.d.11.2
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            splashScreenAdListener.onRequestResult(adResultData.adTemplateList.size());
                        } catch (Throwable th) {
                            com.kwad.sdk.core.d.a.b(th);
                        }
                    }
                });
                boolean zA2 = SplashPreloadManager.b().a(adResultData);
                com.kwad.sdk.core.d.a.a("AdRequestManager", "loadSplashAd onSuccess " + zA2);
                if (zA2) {
                    if (!bVar.a) {
                        d.a.removeCallbacks(runnable);
                        d.a.post(new Runnable() { // from class: com.kwad.sdk.core.request.d.11.3
                            @Override // java.lang.Runnable
                            public void run() {
                                splashScreenAdListener.onSplashScreenAdLoad(cVar);
                            }
                        });
                        com.kwad.sdk.core.d.a.a("AdRequestManager", "loadSplashAd cache returned");
                        com.kwad.sdk.core.report.d.a(1);
                        return;
                    }
                } else if (SplashPreloadManager.b().b(adResultData)) {
                    com.kwad.sdk.core.d.a.a("AdRequestManager", "loadSplashAd image returned");
                    int iA = SplashPreloadManager.b().a(adResultData, true);
                    if (!bVar.a) {
                        d.a.removeCallbacks(runnable);
                        if (iA <= 0) {
                            a(f.h.n, f.h.o);
                            return;
                        } else {
                            d.a.post(new Runnable() { // from class: com.kwad.sdk.core.request.d.11.4
                                @Override // java.lang.Runnable
                                public void run() {
                                    splashScreenAdListener.onSplashScreenAdLoad(cVar);
                                }
                            });
                            com.kwad.sdk.core.report.d.a(2);
                            return;
                        }
                    }
                } else if (!bVar.a) {
                    d.a.removeCallbacks(runnable);
                    a(f.f.n, "请求成功，但缓存未命中");
                    com.kwad.sdk.core.d.a.a("AdRequestManager", "loadSplashAd no cache returned");
                    com.kwad.sdk.core.report.d.a(3);
                    return;
                }
                com.kwad.sdk.core.d.a.a("AdRequestManager", "loadSplashAd isTimeOut return ");
            }
        });
    }
}
