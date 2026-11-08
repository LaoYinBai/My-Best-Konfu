package com.ss.union.game.sdk.ad.ad_mediation;

import android.app.Activity;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import com.ss.union.game.sdk.ad.ad_mediation.dto.LGMediationAdFullScreenVideoAdDTO;
import com.ss.union.game.sdk.ad.ad_mediation.dto.LGMediationAdNativeBannerAdDTO;
import com.ss.union.game.sdk.ad.ad_mediation.dto.LGMediationAdRewardVideoAdDTO;
import com.ss.union.game.sdk.ad.ad_mediation.dto.LGMediationAdSplashAdDTO;
import com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdBannerAd;
import com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdFullScreenVideoAd;
import com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdRewardVideoAd;
import com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdSplashAd;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface LGMediationAdService {

    public interface MediationBannerAdListener {
        @MainThread
        void onBannerAdLoad(LGMediationAdBannerAd lGMediationAdBannerAd);

        @MainThread
        void onError(int i, String str);
    }

    public interface MediationFullScreenVideoAdListener {
        @MainThread
        void onError(int i, String str);

        @MainThread
        void onFullVideoAdLoad(LGMediationAdFullScreenVideoAd lGMediationAdFullScreenVideoAd);

        @MainThread
        void onFullVideoCached(LGMediationAdFullScreenVideoAd lGMediationAdFullScreenVideoAd);
    }

    public interface MediationRewardVideoAdListener {
        @MainThread
        void onError(int i, String str);

        @MainThread
        void onRewardVideoAdLoad(LGMediationAdRewardVideoAd lGMediationAdRewardVideoAd);

        @MainThread
        void onRewardVideoCached(LGMediationAdRewardVideoAd lGMediationAdRewardVideoAd);
    }

    public interface MediationSplashAdListener {
        @MainThread
        void onError(int i, String str);

        @MainThread
        void onSplashAdLoad(LGMediationAdSplashAd lGMediationAdSplashAd);

        @MainThread
        void onTimeout();
    }

    @Deprecated
    void blockPersonalizedAds(boolean z);

    void configPersonalAdsSwitchIsOn(boolean z);

    String getSDKVersion();

    void loadBannerAd(Activity activity, LGMediationAdNativeBannerAdDTO lGMediationAdNativeBannerAdDTO, MediationBannerAdListener mediationBannerAdListener);

    void loadFullScreenVideoAd(Activity activity, LGMediationAdFullScreenVideoAdDTO lGMediationAdFullScreenVideoAdDTO, MediationFullScreenVideoAdListener mediationFullScreenVideoAdListener);

    void loadRewardVideoAd(Activity activity, LGMediationAdRewardVideoAdDTO lGMediationAdRewardVideoAdDTO, @NonNull MediationRewardVideoAdListener mediationRewardVideoAdListener);

    void loadSplashAd(Activity activity, LGMediationAdSplashAdDTO lGMediationAdSplashAdDTO, MediationSplashAdListener mediationSplashAdListener);

    String personalizedAdsStatus();
}
