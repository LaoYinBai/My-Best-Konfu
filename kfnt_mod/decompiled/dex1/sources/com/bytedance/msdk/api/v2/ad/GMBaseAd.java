package com.bytedance.msdk.api.v2.ad;

import androidx.annotation.NonNull;
import com.bytedance.msdk.api.AdSlot;
import com.bytedance.msdk.api.base.TTLoadBase;
import com.bytedance.msdk.api.v2.GMNetworkRequestInfo;
import com.bytedance.msdk.api.v2.ad.banner.GMBannerAdLoadCallback;
import com.bytedance.msdk.api.v2.ad.fullvideo.GMFullVideoAdLoadCallback;
import com.bytedance.msdk.api.v2.ad.interstitial.GMInterstitialAdLoadCallback;
import com.bytedance.msdk.api.v2.ad.interstitialFull.GMInterstitialFullAdLoadCallback;
import com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAdLoadCallback;
import com.bytedance.msdk.api.v2.ad.reward.GMRewardedAdLoadCallback;
import com.bytedance.msdk.api.v2.ad.splash.GMSplashAdLoadCallback;
import com.bytedance.msdk.api.v2.slot.GMAdSlotBanner;
import com.bytedance.msdk.api.v2.slot.GMAdSlotBase;
import com.bytedance.msdk.api.v2.slot.GMAdSlotFullVideo;
import com.bytedance.msdk.api.v2.slot.GMAdSlotInterstitial;
import com.bytedance.msdk.api.v2.slot.GMAdSlotInterstitialFull;
import com.bytedance.msdk.api.v2.slot.GMAdSlotNative;
import com.bytedance.msdk.api.v2.slot.GMAdSlotRewardVideo;
import com.bytedance.msdk.api.v2.slot.GMAdSlotSplash;
import com.bytedance.msdk.api.v2.slot.paltform.GMAdSlotGDTOption;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class GMBaseAd extends TTLoadBase {

    /* renamed from: a, reason: collision with root package name */
    final AdSlot.Builder f4356a = new AdSlot.Builder();

    private void a(GMAdSlotBase gMAdSlotBase) {
        if (gMAdSlotBase != null) {
            this.f4356a.setBidNotify(gMAdSlotBase.isBidNotify());
            this.f4356a.setTestSlotId(gMAdSlotBase.getTestSlotId());
            if (gMAdSlotBase instanceof GMAdSlotSplash) {
                this.f4356a.setTTVideoOption(gMAdSlotBase.createTTVideoOption(((GMAdSlotSplash) gMAdSlotBase).isSplashPreLoad()));
            } else {
                this.f4356a.setTTVideoOption(gMAdSlotBase.createTTVideoOption(false));
            }
            this.f4356a.setTTRequestExtraParams(gMAdSlotBase.getTTRequestExtraParams());
            this.f4356a.setDownloadType(gMAdSlotBase.getDownloadType());
            this.f4356a.setV2Request(true);
        }
    }

    public AdSlot getAdSlot() {
        return this.f4356a.build();
    }

    protected void loadAd(GMAdSlotBanner gMAdSlotBanner, GMBannerAdLoadCallback gMBannerAdLoadCallback) {
        if (gMAdSlotBanner != null) {
            a(gMAdSlotBanner);
            this.f4356a.setImageAdSize(gMAdSlotBanner.getWidth(), gMAdSlotBanner.getHeight());
            this.f4356a.setBannerSize(gMAdSlotBanner.getBannerSize());
        }
    }

    protected void loadAd(GMAdSlotFullVideo gMAdSlotFullVideo, GMFullVideoAdLoadCallback gMFullVideoAdLoadCallback) {
        if (gMAdSlotFullVideo != null) {
            a(gMAdSlotFullVideo);
            this.f4356a.setUserID(gMAdSlotFullVideo.getUserID());
            this.f4356a.setOrientation(gMAdSlotFullVideo.getOrientation());
        }
    }

    protected void loadAd(GMAdSlotInterstitial gMAdSlotInterstitial, GMInterstitialAdLoadCallback gMInterstitialAdLoadCallback) {
        if (gMAdSlotInterstitial != null) {
            a(gMAdSlotInterstitial);
            this.f4356a.setImageAdSize(gMAdSlotInterstitial.getWidth(), gMAdSlotInterstitial.getHeight());
        }
    }

    protected void loadAd(GMAdSlotInterstitialFull gMAdSlotInterstitialFull, GMInterstitialFullAdLoadCallback gMInterstitialFullAdLoadCallback) {
        if (gMAdSlotInterstitialFull != null) {
            a(gMAdSlotInterstitialFull);
            this.f4356a.setImageAdSize(gMAdSlotInterstitialFull.getWidth(), gMAdSlotInterstitialFull.getHeight());
            this.f4356a.setUserID(gMAdSlotInterstitialFull.getUserID());
            this.f4356a.setOrientation(gMAdSlotInterstitialFull.getOrientation());
        }
    }

    protected void loadAd(GMAdSlotNative gMAdSlotNative, GMNativeAdLoadCallback gMNativeAdLoadCallback) {
        if (gMAdSlotNative != null) {
            a(gMAdSlotNative);
            this.f4356a.setImageAdSize(gMAdSlotNative.getWidth(), gMAdSlotNative.getHeight());
            this.f4356a.setAdCount(gMAdSlotNative.getAdCount());
            this.f4356a.setAdStyleType(gMAdSlotNative.getAdStyleType());
            this.f4356a.setAdmobNativeAdOptions(gMAdSlotNative.getAdmobNativeAdOptions());
            GMAdSlotGDTOption gMAdSlotGDTOption = gMAdSlotNative.getGMAdSlotGDTOption();
            if (gMAdSlotGDTOption != null) {
                this.f4356a.setGdtNativeAdLogoParams(gMAdSlotGDTOption.getNativeAdLogoParams());
            }
        }
    }

    protected void loadAd(GMAdSlotRewardVideo gMAdSlotRewardVideo, @NonNull GMRewardedAdLoadCallback gMRewardedAdLoadCallback) {
        if (gMAdSlotRewardVideo != null) {
            a(gMAdSlotRewardVideo);
            this.f4356a.setRewardName(gMAdSlotRewardVideo.getRewardName());
            this.f4356a.setRewardAmount(gMAdSlotRewardVideo.getRewardAmount());
            this.f4356a.setCustomData(gMAdSlotRewardVideo.getCustomData());
            this.f4356a.setUserID(gMAdSlotRewardVideo.getUserID());
            this.f4356a.setOrientation(gMAdSlotRewardVideo.getOrientation());
        }
    }

    protected void loadAd(GMAdSlotSplash gMAdSlotSplash, GMNetworkRequestInfo gMNetworkRequestInfo, GMSplashAdLoadCallback gMSplashAdLoadCallback) {
        if (gMAdSlotSplash != null) {
            a(gMAdSlotSplash);
            this.f4356a.setImageAdSize(gMAdSlotSplash.getWidth(), gMAdSlotSplash.getHeight());
            this.f4356a.setUserID(gMAdSlotSplash.getUserID());
            this.f4356a.setSplashButtonType(gMAdSlotSplash.getSplashButtonType());
        }
    }
}
