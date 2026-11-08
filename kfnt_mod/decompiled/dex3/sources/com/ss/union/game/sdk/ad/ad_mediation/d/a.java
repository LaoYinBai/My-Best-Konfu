package com.ss.union.game.sdk.ad.ad_mediation.d;

import android.text.TextUtils;
import com.bytedance.msdk.api.v2.slot.GMAdOptionUtil;
import com.bytedance.msdk.api.v2.slot.GMAdSlotBanner;
import com.bytedance.msdk.api.v2.slot.GMAdSlotFullVideo;
import com.bytedance.msdk.api.v2.slot.GMAdSlotRewardVideo;
import com.bytedance.msdk.api.v2.slot.GMAdSlotSplash;
import com.bytedance.msdk.api.v2.slot.paltform.GMAdSlotBaiduOption;
import com.ss.union.game.sdk.ad.ad_mediation.dto.LGMediationAdFullScreenVideoAdDTO;
import com.ss.union.game.sdk.ad.ad_mediation.dto.LGMediationAdNativeBannerAdDTO;
import com.ss.union.game.sdk.ad.ad_mediation.dto.LGMediationAdRewardVideoAdDTO;
import com.ss.union.game.sdk.ad.ad_mediation.dto.LGMediationAdSplashAdDTO;
import com.ss.union.game.sdk.common.util.UIUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    public static GMAdSlotFullVideo a(LGMediationAdFullScreenVideoAdDTO lGMediationAdFullScreenVideoAdDTO) {
        if (lGMediationAdFullScreenVideoAdDTO == null) {
            b.a("UnifyBaseConfigAdDTO can't be null, please init it");
            return null;
        }
        if (lGMediationAdFullScreenVideoAdDTO.context == null) {
            b.a("UnifyBaseConfigAdDTO.context can't be null, please init it");
            return null;
        }
        if (TextUtils.isEmpty(lGMediationAdFullScreenVideoAdDTO.codeID)) {
            b.a("UnifyBaseConfigAdDTO.codeID can't be empty");
            return null;
        }
        GMAdSlotFullVideo gMAdSlotFullVideoBuild = new GMAdSlotFullVideo.Builder().setUserID(lGMediationAdFullScreenVideoAdDTO.userID).setDownloadType(lGMediationAdFullScreenVideoAdDTO.isPopDownLoadWindow ? 1 : 0).setOrientation(lGMediationAdFullScreenVideoAdDTO.videoPlayOrientation != 2 ? 1 : 2).setGMAdSlotGDTOption(GMAdOptionUtil.getGMAdSlotGDTOption().build()).setGMAdSlotBaiduOption(a()).build();
        b.a("codeID:" + lGMediationAdFullScreenVideoAdDTO.codeID + ",height:" + lGMediationAdFullScreenVideoAdDTO.expectedImageSize.height + ",width:" + lGMediationAdFullScreenVideoAdDTO.expectedImageSize.width);
        return gMAdSlotFullVideoBuild;
    }

    public static GMAdSlotRewardVideo a(LGMediationAdRewardVideoAdDTO lGMediationAdRewardVideoAdDTO) {
        if (lGMediationAdRewardVideoAdDTO == null) {
            b.a("UnifyBaseConfigAdDTO can't be null, please init it");
            return null;
        }
        if (lGMediationAdRewardVideoAdDTO.context == null) {
            b.a("UnifyBaseConfigAdDTO.context can't be null, please init it");
            return null;
        }
        if (TextUtils.isEmpty(lGMediationAdRewardVideoAdDTO.codeID)) {
            b.a("UnifyBaseConfigAdDTO.codeID can't be empty");
            return null;
        }
        GMAdSlotRewardVideo gMAdSlotRewardVideoBuild = new GMAdSlotRewardVideo.Builder().setGMAdSlotGDTOption(GMAdOptionUtil.getGMAdSlotGDTOption().build()).setGMAdSlotBaiduOption(a()).setOrientation(lGMediationAdRewardVideoAdDTO.videoPlayOrientation != 2 ? 1 : 2).setUserID(lGMediationAdRewardVideoAdDTO.userID).setRewardName(lGMediationAdRewardVideoAdDTO.rewardName).setRewardAmount(lGMediationAdRewardVideoAdDTO.rewardAmount).setDownloadType(lGMediationAdRewardVideoAdDTO.isPopDownLoadWindow ? 1 : 0).setExtraObject(lGMediationAdRewardVideoAdDTO.mediaExtraKey, lGMediationAdRewardVideoAdDTO.mediaExtra).build();
        b.a("codeID:" + lGMediationAdRewardVideoAdDTO.codeID + ",height:" + lGMediationAdRewardVideoAdDTO.expectedImageSize.height + ",width:" + lGMediationAdRewardVideoAdDTO.expectedImageSize.width);
        return gMAdSlotRewardVideoBuild;
    }

    public static GMAdSlotBanner a(LGMediationAdNativeBannerAdDTO lGMediationAdNativeBannerAdDTO) {
        if (lGMediationAdNativeBannerAdDTO == null) {
            b.a("UnifyBaseConfigAdDTO can't be null, please init it");
            return null;
        }
        if (lGMediationAdNativeBannerAdDTO.context == null) {
            b.a("UnifyBaseConfigAdDTO.context can't be null, please init it");
            return null;
        }
        if (TextUtils.isEmpty(lGMediationAdNativeBannerAdDTO.codeID)) {
            b.a("UnifyBaseConfigAdDTO.codeID can't be empty");
            return null;
        }
        return new GMAdSlotBanner.Builder().setBannerSize(6).setGMAdSlotBaiduOption(a()).setImageAdSize(UIUtils.px2dip(lGMediationAdNativeBannerAdDTO.expectedImageSize.width), UIUtils.px2dip(lGMediationAdNativeBannerAdDTO.expectedImageSize.height)).setAllowShowCloseBtn(lGMediationAdNativeBannerAdDTO.allowShowCloseBtn).setDownloadType(lGMediationAdNativeBannerAdDTO.isPopDownLoadWindow ? 1 : 0).build();
    }

    public static GMAdSlotSplash a(LGMediationAdSplashAdDTO lGMediationAdSplashAdDTO) {
        if (lGMediationAdSplashAdDTO == null) {
            b.a("UnifyBaseConfigAdDTO can't be null, please init it");
            return null;
        }
        if (lGMediationAdSplashAdDTO.context == null) {
            b.a("UnifyBaseConfigAdDTO.context can't be null, please init it");
            return null;
        }
        if (TextUtils.isEmpty(lGMediationAdSplashAdDTO.codeID)) {
            b.a("UnifyBaseConfigAdDTO.codeID can't be empty");
            return null;
        }
        return new GMAdSlotSplash.Builder().setGMAdSlotBaiduOption(a()).setImageAdSize(lGMediationAdSplashAdDTO.expectedImageSize.width, lGMediationAdSplashAdDTO.expectedImageSize.height).setSplashPreLoad(lGMediationAdSplashAdDTO.preLoad).setTimeOut(lGMediationAdSplashAdDTO.timeOut).setSplashButtonType(lGMediationAdSplashAdDTO.splashButtonType).setDownloadType(lGMediationAdSplashAdDTO.isPopDownLoadWindow ? 1 : 0).build();
    }

    private static GMAdSlotBaiduOption a() {
        return new GMAdSlotBaiduOption.Builder().setDownloadAppConfirmPolicy(3).setShowDialogOnSkip(true).setUseRewardCountdown(true).setCacheVideoOnlyWifi(true).build();
    }
}
