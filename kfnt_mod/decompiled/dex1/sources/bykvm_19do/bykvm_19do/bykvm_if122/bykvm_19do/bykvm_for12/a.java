package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12;

import android.os.SystemClock;
import android.text.TextUtils;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.f;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.api.AdSlot;
import com.bytedance.msdk.api.TTVideoOption;
import com.bytedance.msdk.base.TTBaseAd;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private final Map<String, Integer> f1848a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    private final Map<String, Integer> f1849b = new HashMap();

    public void a(String str, String str2, int i) {
        this.f1848a.put(str + "_" + str2, Integer.valueOf(i));
    }

    public void b(String str, String str2, int i) {
        this.f1849b.put(str + "_" + str2, Integer.valueOf(i));
    }

    public int a(String str, String str2) {
        Integer num = this.f1848a.get(str + "_" + str2);
        if (num != null) {
            return num.intValue();
        }
        return 1800000;
    }

    public int b(String str, String str2) {
        Integer num = this.f1849b.get(str + "_" + str2);
        if (num != null) {
            return num.intValue();
        }
        return 1;
    }

    public int a(f fVar, AdSlot adSlot, AdSlot adSlot2, String str) {
        if (fVar == null || fVar.f1866a == null) {
            return 4;
        }
        String adUnitId = adSlot2 != null ? adSlot2.getAdUnitId() : "";
        String adNetWorkName = fVar.f1866a.getAdNetWorkName();
        String adNetworkSlotId = fVar.f1866a.getAdNetworkSlotId();
        if (fVar.f1866a.adnHasAdExpiredApi()) {
            if (a(adUnitId, fVar)) {
                Logger.d("TTMediationSDK", "--==-- " + str + ":广告过期了(adn api) ---: " + adNetWorkName + ", adSlotId: " + adNetworkSlotId);
                return 1;
            }
            if (fVar.f1866a.isAdCacheReady() && !fVar.f1866a.isHasShown()) {
                if (!a(adSlot, adSlot2, fVar.f1866a)) {
                    Logger.d("TTMediationSDK", "--==-- " + str + ":AdSlot不符合 ---: " + adNetWorkName + ", adSlotId: " + adNetworkSlotId);
                    return 6;
                }
                Logger.d("TTMediationSDK", "--==-- " + str + ":复用成功：---" + adNetWorkName + ", adSlotId: " + adNetworkSlotId);
                return -1;
            }
            Logger.d("TTMediationSDK", "--==-- " + str + ":isReady是false或已show过 ---: " + adNetWorkName + ", adSlotId: " + adNetworkSlotId);
            return 5;
        }
        if (fVar.f1866a.isAdCacheReady() && !fVar.f1866a.isHasShown()) {
            if (a(adUnitId, fVar)) {
                Logger.d("TTMediationSDK", "--==-- " + str + ":广告过期了(M配置) ---: " + adNetWorkName + ", adSlotId: " + adNetworkSlotId);
                return 2;
            }
            if (!a(adSlot, adSlot2, fVar.f1866a)) {
                Logger.d("TTMediationSDK", "--==-- " + str + ":AdSlot不符合 ---: " + adNetWorkName + ", adSlotId: " + adNetworkSlotId);
                return 6;
            }
            Logger.d("TTMediationSDK", "--==-- " + str + ":复用成功：---" + adNetWorkName + ", adSlotId: " + adNetworkSlotId);
            return -1;
        }
        Logger.d("TTMediationSDK", "--==-- " + str + ":isReady是false或已show过 ---: " + adNetWorkName + ", adSlotId: " + adNetworkSlotId);
        return 5;
    }

    public boolean a(String str, f fVar) {
        TTBaseAd tTBaseAd;
        if (fVar != null && (tTBaseAd = fVar.f1866a) != null && !tTBaseAd.hasDestroyed()) {
            if (fVar.f1866a.adnHasAdExpiredApi()) {
                boolean zIsAdExpired = fVar.f1866a.isAdExpired();
                Logger.d("TTMediationSDK", "--==-- 使用了adn的过期接口 ------");
                return zIsAdExpired;
            }
            boolean z = SystemClock.elapsedRealtime() > fVar.b() + ((long) a(str, fVar.f1866a.getAdNetworkSlotId()));
            Logger.d("TTMediationSDK", "--==-- 使用了配置的过期时间进行判断 ------");
            return z;
        }
        Logger.d("TTMediationSDK", "--==-- 广告已经destroy了，视为过期 ------");
        return true;
    }

    public static boolean a(AdSlot adSlot, AdSlot adSlot2, TTBaseAd tTBaseAd) {
        if (adSlot != null && adSlot2 != null) {
            if (adSlot.getOrientation() != adSlot2.getOrientation()) {
                Logger.d("TMe", "--==-- orientation, a1: " + adSlot.getOrientation() + ", a2: " + adSlot2.getOrientation());
                return false;
            }
            TTVideoOption tTVideoOption = adSlot.getTTVideoOption();
            TTVideoOption tTVideoOption2 = adSlot2.getTTVideoOption();
            if (tTVideoOption != null || tTVideoOption2 != null) {
                if (tTVideoOption != null && tTVideoOption2 != null) {
                    if (tTVideoOption.isMuted() != tTVideoOption2.isMuted()) {
                        Logger.d("TMe", "--==-- isMuted, a1: " + tTVideoOption.isMuted() + ", a2: " + tTVideoOption2.isMuted());
                        return false;
                    }
                } else {
                    Logger.d("TMe", "--==-- options=null, a1: " + tTVideoOption + ", a2: " + tTVideoOption2);
                    return false;
                }
            }
            if (adSlot.getBannerSize() != adSlot2.getBannerSize()) {
                Logger.d("TMe", "--==-- bannerSize, a1: " + adSlot.getBannerSize() + ", a2: " + adSlot2.getBannerSize());
                return false;
            }
            if (adSlot.getAdCount() != adSlot2.getAdCount()) {
                Logger.d("TMe", "--==-- adCount, a1: " + adSlot.getAdCount() + ", a2: " + adSlot2.getAdCount());
                return false;
            }
            if (adSlot.getImgAcceptedWidth() != adSlot2.getImgAcceptedWidth()) {
                Logger.d("TMe", "--==-- ImgWidth, a1: " + adSlot.getImgAcceptedWidth() + ", a2: " + adSlot2.getImgAcceptedWidth());
                return false;
            }
            if (adSlot.getImgAcceptedHeight() != adSlot2.getImgAcceptedHeight()) {
                Logger.d("TMe", "--==-- ImgHeight, a1: " + adSlot.getImgAcceptedHeight() + ", a2: " + adSlot2.getImgAcceptedHeight());
                return false;
            }
            if (tTBaseAd != null && "pangle".equals(tTBaseAd.getAdNetWorkName())) {
                Logger.d("TMe", "--==-- pangle合规判断进入");
                if (tTBaseAd.getAdType() == 3 && adSlot.getSplashButtonType() != adSlot2.getSplashButtonType()) {
                    Logger.d("TMe", "--==-- pangle splashButtonType, a1: " + adSlot.getSplashButtonType() + ", a2: " + adSlot2.getSplashButtonType());
                    return false;
                }
                if (adSlot.getDownloadType() != adSlot2.getDownloadType()) {
                    Logger.d("TMe", "--==-- pangle downloadDialogType, a1: " + adSlot.getDownloadType() + ", a2: " + adSlot2.getDownloadType());
                    return false;
                }
                Logger.d("TMe", "--==-- pangle合规判断ok，adslot兼容");
            }
            if (adSlot.getRewardName() != null && adSlot2.getRewardName() != null) {
                if (!adSlot.getRewardName().equals(adSlot2.getRewardName())) {
                    Logger.d("TMe", "--==-- rewardName, a1: " + adSlot.getRewardName() + ", a2: " + adSlot2.getRewardName());
                    return false;
                }
            } else if (adSlot.getRewardName() != null || adSlot2.getRewardName() != null) {
                Logger.d("TMe", "--==-- rewardName=null, a1: " + adSlot.getRewardName() + ", a2: " + adSlot2.getRewardName());
                return false;
            }
            if (adSlot.getRewardAmount() != adSlot2.getRewardAmount()) {
                Logger.d("TMe", "--==-- rewardAmount, a1: " + adSlot.getRewardAmount() + ", a2: " + adSlot2.getRewardAmount());
                return false;
            }
            if (adSlot.getUserID() != null && adSlot2.getUserID() != null) {
                if (!adSlot.getUserID().equals(adSlot2.getUserID())) {
                    Logger.d("TMe", "--==-- reward, userId, a1: " + adSlot.getUserID() + ", a2: " + adSlot2.getUserID());
                    return false;
                }
            } else if (adSlot.getUserID() != null || adSlot2.getUserID() != null) {
                Logger.d("TMe", "--==-- reward, userId=null, a1: " + adSlot.getUserID() + ", a2: " + adSlot2.getUserID());
                return false;
            }
            if (adSlot.getCustomData() != null && adSlot2.getCustomData() != null) {
                if (a(adSlot.getCustomData(), adSlot2.getCustomData())) {
                    return true;
                }
                Logger.d("TMe", "--==-- customData, a1: " + adSlot.getCustomData().toString() + ", a2: " + adSlot2.getCustomData().toString());
                return false;
            }
            if (adSlot.getCustomData() == null && adSlot2.getCustomData() == null) {
                return true;
            }
            Logger.d("TMe", "--==-- customData=null, a1: " + adSlot.getCustomData() + ", a2: " + adSlot2.getCustomData());
            return false;
        }
        Logger.d("TMe", "--==-- adSlot = null, a1: " + adSlot + ", a2: " + adSlot2);
        return false;
    }

    public static boolean a(Map<String, String> map, Map<String, String> map2) {
        if (map == null && map2 == null) {
            return true;
        }
        if (map == null || map2 == null || map.size() != map2.size()) {
            return false;
        }
        Set<String> setKeySet = map.keySet();
        if (setKeySet != null) {
            for (String str : setKeySet) {
                if (str != null) {
                    String str2 = map.get(str);
                    String str3 = map2.get(str);
                    if (str2 != null || str3 != null) {
                        if (!TextUtils.equals(str2, str3)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
