package com.ss.union.game.sdk.ad.ad_mediation.c;

import android.app.Activity;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.v2.GMMediationAdSdk;
import com.bytedance.msdk.api.v2.GMNetworkRequestInfo;
import com.bytedance.msdk.api.v2.ad.banner.GMBannerAd;
import com.bytedance.msdk.api.v2.ad.banner.GMBannerAdLoadCallback;
import com.bytedance.msdk.api.v2.ad.fullvideo.GMFullVideoAd;
import com.bytedance.msdk.api.v2.ad.fullvideo.GMFullVideoAdLoadCallback;
import com.bytedance.msdk.api.v2.ad.reward.GMRewardAd;
import com.bytedance.msdk.api.v2.ad.reward.GMRewardedAdLoadCallback;
import com.bytedance.msdk.api.v2.ad.splash.GMSplashAd;
import com.bytedance.msdk.api.v2.ad.splash.GMSplashAdLoadCallback;
import com.bytedance.msdk.api.v2.slot.GMAdSlotBanner;
import com.bytedance.msdk.api.v2.slot.GMAdSlotFullVideo;
import com.bytedance.msdk.api.v2.slot.GMAdSlotRewardVideo;
import com.bytedance.msdk.api.v2.slot.GMAdSlotSplash;
import com.sigmob.sdk.common.Constants;
import com.ss.union.game.sdk.ad.ad_mediation.LGMediationAdService;
import com.ss.union.game.sdk.ad.ad_mediation.dto.LGMediationAdFullScreenVideoAdDTO;
import com.ss.union.game.sdk.ad.ad_mediation.dto.LGMediationAdNativeBannerAdDTO;
import com.ss.union.game.sdk.ad.ad_mediation.dto.LGMediationAdRewardVideoAdDTO;
import com.ss.union.game.sdk.ad.ad_mediation.dto.LGMediationAdSplashAdDTO;
import com.ss.union.game.sdk.common.util.MainThreadExecutor;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d implements LGMediationAdService {
    static final String a = "union_full";
    static final String b = "union_reward";
    private static final String c = "LGUnifyAdManagerImpl";
    private static volatile d d;

    private d() {
    }

    public static d a() {
        if (d == null) {
            synchronized (d.class) {
                if (d == null) {
                    d = new d();
                }
            }
        }
        return d;
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.LGMediationAdService
    public void configPersonalAdsSwitchIsOn(boolean z) {
        com.ss.union.game.sdk.ad.ad_mediation.b.a.b(z ? "1" : Constants.FAIL);
        com.ss.union.game.sdk.ad.ad_mediation.b.a.c(com.ss.union.game.sdk.ad.ad_mediation.b.a.f());
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.LGMediationAdService
    public void blockPersonalizedAds(boolean z) {
        com.ss.union.game.sdk.ad.ad_mediation.b.a.b(z ? Constants.FAIL : "1");
        com.ss.union.game.sdk.ad.ad_mediation.b.a.c(com.ss.union.game.sdk.ad.ad_mediation.b.a.f());
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.LGMediationAdService
    public String personalizedAdsStatus() {
        return com.ss.union.game.sdk.ad.ad_mediation.b.a.i();
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.LGMediationAdService
    public void loadFullScreenVideoAd(Activity activity, LGMediationAdFullScreenVideoAdDTO lGMediationAdFullScreenVideoAdDTO, final LGMediationAdService.MediationFullScreenVideoAdListener mediationFullScreenVideoAdListener) {
        com.ss.union.game.sdk.ad.ad_mediation.d.b.a("loadFullScreenVideoAd() Start");
        GMAdSlotFullVideo gMAdSlotFullVideoA = com.ss.union.game.sdk.ad.ad_mediation.d.a.a(lGMediationAdFullScreenVideoAdDTO);
        if (gMAdSlotFullVideoA == null) {
            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("adSlot is null...");
            mediationFullScreenVideoAdListener.onError(-203, "参数错误");
        } else {
            final GMFullVideoAd gMFullVideoAd = new GMFullVideoAd(activity, lGMediationAdFullScreenVideoAdDTO.codeID);
            gMFullVideoAd.loadAd(gMAdSlotFullVideoA, new GMFullVideoAdLoadCallback() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.d.1
                public void onFullVideoLoadFail(AdError adError) {
                    final int i = adError.code;
                    final String str = TextUtils.isEmpty(adError.message) ? "" : adError.message;
                    com.ss.union.game.sdk.ad.ad_mediation.d.b.a("loadFullScreenVideoAd() fail " + adError.toString());
                    MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.d.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            mediationFullScreenVideoAdListener.onError(i, str);
                        }
                    });
                }

                public void onFullVideoAdLoad() {
                    com.ss.union.game.sdk.ad.ad_mediation.d.b.a("loadFullScreenVideoAd() AdLoaded");
                    MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.d.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            mediationFullScreenVideoAdListener.onFullVideoAdLoad(new b(gMFullVideoAd));
                        }
                    });
                }

                public void onFullVideoCached() {
                    com.ss.union.game.sdk.ad.ad_mediation.d.b.a("loadFullScreenVideoAd() Cached");
                    MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.d.1.3
                        @Override // java.lang.Runnable
                        public void run() {
                            mediationFullScreenVideoAdListener.onFullVideoCached(new b(gMFullVideoAd));
                        }
                    });
                }
            });
        }
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.LGMediationAdService
    public void loadRewardVideoAd(Activity activity, LGMediationAdRewardVideoAdDTO lGMediationAdRewardVideoAdDTO, @NonNull final LGMediationAdService.MediationRewardVideoAdListener mediationRewardVideoAdListener) {
        com.ss.union.game.sdk.ad.ad_mediation.d.b.a("loadRewardVideoAd() Start");
        GMAdSlotRewardVideo gMAdSlotRewardVideoA = com.ss.union.game.sdk.ad.ad_mediation.d.a.a(lGMediationAdRewardVideoAdDTO);
        if (gMAdSlotRewardVideoA == null) {
            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("adSlot is null...");
            mediationRewardVideoAdListener.onError(-203, "参数错误");
        } else {
            GMRewardAd gMRewardAd = new GMRewardAd(activity, lGMediationAdRewardVideoAdDTO.codeID);
            final c cVar = new c(gMRewardAd);
            gMRewardAd.loadAd(gMAdSlotRewardVideoA, new GMRewardedAdLoadCallback() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.d.2
                public void onRewardVideoLoadFail(@NonNull AdError adError) {
                    final int i = adError.code;
                    final String str = TextUtils.isEmpty(adError.message) ? "" : adError.message;
                    com.ss.union.game.sdk.ad.ad_mediation.d.b.a("loadRewardVideoAd() fail " + adError.toString());
                    MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.d.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            mediationRewardVideoAdListener.onError(i, str);
                        }
                    });
                }

                public void onRewardVideoAdLoad() {
                    com.ss.union.game.sdk.ad.ad_mediation.d.b.a("loadRewardVideoAd() AdLoaded");
                    MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.d.2.2
                        @Override // java.lang.Runnable
                        public void run() {
                            mediationRewardVideoAdListener.onRewardVideoAdLoad(cVar);
                        }
                    });
                }

                public void onRewardVideoCached() {
                    com.ss.union.game.sdk.ad.ad_mediation.d.b.a("loadRewardVideoAd() Cached");
                    MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.d.2.3
                        @Override // java.lang.Runnable
                        public void run() {
                            mediationRewardVideoAdListener.onRewardVideoCached(cVar);
                        }
                    });
                }
            });
        }
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.LGMediationAdService
    public void loadBannerAd(Activity activity, final LGMediationAdNativeBannerAdDTO lGMediationAdNativeBannerAdDTO, final LGMediationAdService.MediationBannerAdListener mediationBannerAdListener) {
        com.ss.union.game.sdk.ad.ad_mediation.d.b.a("loadBannerAd() Start");
        GMAdSlotBanner gMAdSlotBannerA = com.ss.union.game.sdk.ad.ad_mediation.d.a.a(lGMediationAdNativeBannerAdDTO);
        if (gMAdSlotBannerA == null) {
            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("adSlot is null...");
            mediationBannerAdListener.onError(-203, "参数错误");
        } else {
            final GMBannerAd gMBannerAd = new GMBannerAd(activity, lGMediationAdNativeBannerAdDTO.codeID);
            gMBannerAd.loadAd(gMAdSlotBannerA, new GMBannerAdLoadCallback() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.d.3
                public void onAdFailedToLoad(@NonNull AdError adError) {
                    final int i = adError.code;
                    final String str = TextUtils.isEmpty(adError.message) ? "" : adError.message;
                    com.ss.union.game.sdk.ad.ad_mediation.d.b.a("loadBannerAd() fail " + adError.toString());
                    MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.d.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            mediationBannerAdListener.onError(i, str);
                        }
                    });
                }

                public void onAdLoaded() {
                    com.ss.union.game.sdk.ad.ad_mediation.d.b.a("loadBannerAd() AdLoaded");
                    MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.d.3.2
                        @Override // java.lang.Runnable
                        public void run() {
                            mediationBannerAdListener.onBannerAdLoad(new a(gMBannerAd, lGMediationAdNativeBannerAdDTO));
                        }
                    });
                }
            });
        }
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.LGMediationAdService
    public void loadSplashAd(Activity activity, final LGMediationAdSplashAdDTO lGMediationAdSplashAdDTO, final LGMediationAdService.MediationSplashAdListener mediationSplashAdListener) {
        com.ss.union.game.sdk.ad.ad_mediation.d.b.a("loadSplashAd() Start");
        GMAdSlotSplash gMAdSlotSplashA = com.ss.union.game.sdk.ad.ad_mediation.d.a.a(lGMediationAdSplashAdDTO);
        if (gMAdSlotSplashA == null) {
            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("adSlot is null...");
            mediationSplashAdListener.onError(-203, "参数错误");
        } else {
            final GMSplashAd gMSplashAd = new GMSplashAd(activity, lGMediationAdSplashAdDTO.codeID);
            gMSplashAd.loadAd(gMAdSlotSplashA, new GMNetworkRequestInfo() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.d.4
                public String getAdNetworkSlotId() {
                    return lGMediationAdSplashAdDTO.defaultAdRitId;
                }

                public String getAppId() {
                    return com.ss.union.game.sdk.ad.ad_mediation.a.a;
                }
            }, new GMSplashAdLoadCallback() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.d.5
                public void onSplashAdLoadFail(@NonNull AdError adError) {
                    final int i = adError.code;
                    final String str = TextUtils.isEmpty(adError.message) ? "" : adError.message;
                    com.ss.union.game.sdk.ad.ad_mediation.d.b.a("loadSplashAd() fail " + adError.toString());
                    MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.d.5.1
                        @Override // java.lang.Runnable
                        public void run() {
                            mediationSplashAdListener.onError(i, str);
                        }
                    });
                }

                public void onSplashAdLoadSuccess() {
                    com.ss.union.game.sdk.ad.ad_mediation.d.b.a("loadSplashAd() load success");
                    MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.d.5.2
                        @Override // java.lang.Runnable
                        public void run() {
                            mediationSplashAdListener.onSplashAdLoad(new e(gMSplashAd));
                        }
                    });
                }

                public void onAdLoadTimeout() {
                    com.ss.union.game.sdk.ad.ad_mediation.d.b.a("loadSplashAd() timeout");
                    MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.c.d.5.3
                        @Override // java.lang.Runnable
                        public void run() {
                            mediationSplashAdListener.onTimeout();
                        }
                    });
                }
            });
        }
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.LGMediationAdService
    public String getSDKVersion() {
        return GMMediationAdSdk.getSdkVersion();
    }
}
