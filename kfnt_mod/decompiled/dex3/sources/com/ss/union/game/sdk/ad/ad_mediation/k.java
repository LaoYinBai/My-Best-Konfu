package com.ss.union.game.sdk.ad.ad_mediation;

import android.app.Activity;
import android.os.Looper;
import com.bytedance.msdk.api.v2.GMMediationAdSdk;
import com.bytedance.msdk.api.v2.GMSettingConfigCallback;
import com.ss.union.game.sdk.ad.ad_mediation.LGMediationAdService;
import com.ss.union.game.sdk.ad.ad_mediation.dto.LGMediationAdFullScreenVideoAdDTO;
import com.ss.union.game.sdk.ad.ad_mediation.dto.LGMediationAdNativeBannerAdDTO;
import com.ss.union.game.sdk.ad.ad_mediation.dto.LGMediationAdRewardVideoAdDTO;
import com.ss.union.game.sdk.ad.ad_mediation.dto.LGMediationAdSplashAdDTO;
import com.ss.union.game.sdk.common.util.MainThreadExecutor;
import com.ss.union.game.sdk.common.util.logger.LogConstant;
import com.ss.union.game.sdk.common.util.logger.LogUtils;
import com.ss.union.game.sdk.core.LGSDKCore;
import com.ss.union.game.sdk.core.base.debug.automatic_detection.LGDetectionManager;
import com.ss.union.game.sdk.core.base.debug.automatic_detection.constants.LGDetectionConstant;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class k implements LGMediationAdService {
    private static volatile k a;
    private final LGMediationAdService b = com.ss.union.game.sdk.ad.ad_mediation.c.d.a();

    private k() {
    }

    public static k a() {
        if (a == null) {
            synchronized (k.class) {
                if (a == null) {
                    a = new k();
                }
            }
        }
        return a;
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.LGMediationAdService
    public void configPersonalAdsSwitchIsOn(boolean z) {
        this.b.configPersonalAdsSwitchIsOn(z);
        e.b(z);
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.LGMediationAdService
    public void blockPersonalizedAds(boolean z) {
        this.b.blockPersonalizedAds(z);
        e.a(z);
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.LGMediationAdService
    public String personalizedAdsStatus() {
        return this.b.personalizedAdsStatus();
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.LGMediationAdService
    public void loadFullScreenVideoAd(final Activity activity, LGMediationAdFullScreenVideoAdDTO lGMediationAdFullScreenVideoAdDTO, final LGMediationAdService.MediationFullScreenVideoAdListener mediationFullScreenVideoAdListener) throws JSONException {
        if (!LGSDKCore.isSdkInitSuccess()) {
            if (mediationFullScreenVideoAdListener != null) {
                mediationFullScreenVideoAdListener.onError(-204, "SDK还未初始化完成");
            } else {
                LogUtils.log(LogConstant.TAG_AD, "loadFullScreenVideoAd NOT_INITIALIZED and listener is null...");
            }
            LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.AD, LGDetectionConstant.AdItem.DETECTION_ID_FULL_SCREEN_LOAD, LGDetectionConstant.DetectionState.FAIL);
            return;
        }
        if (lGMediationAdFullScreenVideoAdDTO == null) {
            if (mediationFullScreenVideoAdListener != null) {
                mediationFullScreenVideoAdListener.onError(-203, "参数错误");
                return;
            } else {
                LogUtils.log(LogConstant.TAG_AD, "loadFullScreenVideoAd configDTO and listener is null...");
                return;
            }
        }
        final LGMediationAdFullScreenVideoAdDTO lGMediationAdFullScreenVideoAdDTO2 = (LGMediationAdFullScreenVideoAdDTO) com.ss.union.game.sdk.ad.c.b.a().a((com.ss.union.game.sdk.ad.c.b) lGMediationAdFullScreenVideoAdDTO);
        LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.AD, LGDetectionConstant.AdItem.DETECTION_ID_FULL_SCREEN_LOAD, LGDetectionConstant.DetectionState.PASS);
        LGDetectionManager.getDetectionParamsApi().insertParams(LGDetectionConstant.Params.MEDIATION_FULL_SCREEN_ID, lGMediationAdFullScreenVideoAdDTO2.codeID);
        e.a("init_code_id", lGMediationAdFullScreenVideoAdDTO2.codeID, "full", -1);
        a(new GMSettingConfigCallback() { // from class: com.ss.union.game.sdk.ad.ad_mediation.k.1
            public void configLoad() {
                k.this.b.loadFullScreenVideoAd(activity, lGMediationAdFullScreenVideoAdDTO2, new d(lGMediationAdFullScreenVideoAdDTO2, mediationFullScreenVideoAdListener));
            }
        });
        e.a("ad_load", lGMediationAdFullScreenVideoAdDTO2.codeID, "full", -1);
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.LGMediationAdService
    public void loadRewardVideoAd(final Activity activity, LGMediationAdRewardVideoAdDTO lGMediationAdRewardVideoAdDTO, final LGMediationAdService.MediationRewardVideoAdListener mediationRewardVideoAdListener) throws JSONException {
        if (!LGSDKCore.isSdkInitSuccess()) {
            if (mediationRewardVideoAdListener != null) {
                mediationRewardVideoAdListener.onError(-204, "SDK还未初始化完成");
            } else {
                LogUtils.log(LogConstant.TAG_AD, "loadRewardVideoAd NOT_INITIALIZED and listener is null...");
            }
            LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.AD, LGDetectionConstant.AdItem.DETECTION_ID_REWARD_LOAD, LGDetectionConstant.DetectionState.FAIL);
            return;
        }
        if (lGMediationAdRewardVideoAdDTO == null) {
            if (mediationRewardVideoAdListener != null) {
                mediationRewardVideoAdListener.onError(-203, "参数错误");
                return;
            } else {
                LogUtils.log(LogConstant.TAG_AD, "loadRewardVideoAd configDTO and listener is null...");
                return;
            }
        }
        final LGMediationAdRewardVideoAdDTO lGMediationAdRewardVideoAdDTO2 = (LGMediationAdRewardVideoAdDTO) com.ss.union.game.sdk.ad.c.b.a().a((com.ss.union.game.sdk.ad.c.b) lGMediationAdRewardVideoAdDTO);
        LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.AD, LGDetectionConstant.AdItem.DETECTION_ID_REWARD_LOAD, LGDetectionConstant.DetectionState.PASS);
        LGDetectionManager.getDetectionParamsApi().insertParams(LGDetectionConstant.Params.MEDIATION_REWARD_ID, lGMediationAdRewardVideoAdDTO2.codeID);
        e.a("init_code_id", lGMediationAdRewardVideoAdDTO2.codeID, "reward", -1);
        a(new GMSettingConfigCallback() { // from class: com.ss.union.game.sdk.ad.ad_mediation.k.2
            public void configLoad() {
                k.this.b.loadRewardVideoAd(activity, lGMediationAdRewardVideoAdDTO2, new f(lGMediationAdRewardVideoAdDTO2, mediationRewardVideoAdListener));
            }
        });
        e.a("ad_load", lGMediationAdRewardVideoAdDTO2.codeID, "reward", -1);
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.LGMediationAdService
    public void loadBannerAd(final Activity activity, final LGMediationAdNativeBannerAdDTO lGMediationAdNativeBannerAdDTO, final LGMediationAdService.MediationBannerAdListener mediationBannerAdListener) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.k.3
                @Override // java.lang.Runnable
                public void run() {
                    k.this.loadBannerAd(activity, lGMediationAdNativeBannerAdDTO, mediationBannerAdListener);
                }
            });
            return;
        }
        if (!LGSDKCore.isSdkInitSuccess()) {
            if (mediationBannerAdListener != null) {
                mediationBannerAdListener.onError(-204, "SDK还未初始化完成");
                return;
            } else {
                LogUtils.log(LogConstant.TAG_AD, "loadBannerAd NOT_INITIALIZED and listener is null...");
                return;
            }
        }
        if (lGMediationAdNativeBannerAdDTO != null) {
            final LGMediationAdNativeBannerAdDTO lGMediationAdNativeBannerAdDTO2 = (LGMediationAdNativeBannerAdDTO) com.ss.union.game.sdk.ad.c.b.a().a((com.ss.union.game.sdk.ad.c.b) lGMediationAdNativeBannerAdDTO);
            LGDetectionManager.getDetectionParamsApi().insertParams(LGDetectionConstant.Params.PANGOLIN_BANNER, lGMediationAdNativeBannerAdDTO2.codeID);
            a(new GMSettingConfigCallback() { // from class: com.ss.union.game.sdk.ad.ad_mediation.k.4
                public void configLoad() {
                    k.this.b.loadBannerAd(activity, lGMediationAdNativeBannerAdDTO2, new c(lGMediationAdNativeBannerAdDTO2, mediationBannerAdListener));
                }
            });
        } else if (mediationBannerAdListener != null) {
            mediationBannerAdListener.onError(-203, "参数错误");
        } else {
            LogUtils.log(LogConstant.TAG_AD, "loadBannerAd configDTO and listener is null...");
        }
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.LGMediationAdService
    public void loadSplashAd(final Activity activity, final LGMediationAdSplashAdDTO lGMediationAdSplashAdDTO, final LGMediationAdService.MediationSplashAdListener mediationSplashAdListener) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.ad.ad_mediation.k.5
                @Override // java.lang.Runnable
                public void run() {
                    k.this.loadSplashAd(activity, lGMediationAdSplashAdDTO, mediationSplashAdListener);
                }
            });
            return;
        }
        if (!LGSDKCore.isSdkInitSuccess()) {
            if (mediationSplashAdListener != null) {
                mediationSplashAdListener.onError(-204, "SDK还未初始化完成");
            } else {
                LogUtils.log(LogConstant.TAG_AD, "loadSplashAd NOT_INITIALIZED and listener is null...");
            }
            LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.AD, LGDetectionConstant.AdItem.DETECTION_ID_SPLASH_LOAD, LGDetectionConstant.DetectionState.FAIL);
            return;
        }
        if (lGMediationAdSplashAdDTO == null) {
            if (mediationSplashAdListener != null) {
                mediationSplashAdListener.onError(-203, "参数错误");
                return;
            } else {
                LogUtils.log(LogConstant.TAG_AD, "loadSplashAd configDTO and listener is null...");
                return;
            }
        }
        final LGMediationAdSplashAdDTO lGMediationAdSplashAdDTO2 = (LGMediationAdSplashAdDTO) com.ss.union.game.sdk.ad.c.b.a().a((com.ss.union.game.sdk.ad.c.b) lGMediationAdSplashAdDTO);
        LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.AD, LGDetectionConstant.AdItem.DETECTION_ID_SPLASH_LOAD, LGDetectionConstant.DetectionState.PASS);
        LGDetectionManager.getDetectionParamsApi().insertParams(LGDetectionConstant.Params.PANGOLIN_SPLASH, lGMediationAdSplashAdDTO2.codeID);
        a(new GMSettingConfigCallback() { // from class: com.ss.union.game.sdk.ad.ad_mediation.k.6
            public void configLoad() {
                k.this.b.loadSplashAd(activity, lGMediationAdSplashAdDTO2, new g(lGMediationAdSplashAdDTO2, mediationSplashAdListener));
            }
        });
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.LGMediationAdService
    public String getSDKVersion() {
        return this.b.getSDKVersion();
    }

    private void a(final GMSettingConfigCallback gMSettingConfigCallback) {
        if (GMMediationAdSdk.configLoadSuccess()) {
            gMSettingConfigCallback.configLoad();
            LogUtils.log(LogConstant.TAG_AD, "configLoadSuccess...");
        } else {
            LogUtils.log(LogConstant.TAG_AD, "config Loading, please wait for...");
            GMMediationAdSdk.registerConfigCallback(new GMSettingConfigCallback() { // from class: com.ss.union.game.sdk.ad.ad_mediation.k.7
                public void configLoad() {
                    LogUtils.log(LogConstant.TAG_AD, "waiting for the end, configLoadSuccess...");
                    gMSettingConfigCallback.configLoad();
                    GMMediationAdSdk.unregisterConfigCallback(this);
                }
            });
        }
    }
}
