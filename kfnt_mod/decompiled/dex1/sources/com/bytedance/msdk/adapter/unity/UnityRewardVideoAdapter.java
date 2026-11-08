package com.bytedance.msdk.adapter.unity;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.listener.ITTAdapterRewardedAdListener;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.TTAdConstant;
import com.bytedance.msdk.api.reward.RewardItem;
import com.bytedance.msdk.base.TTBaseAd;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class UnityRewardVideoAdapter extends TTAbsAdLoaderAdapter {

    /* renamed from: a, reason: collision with root package name */
    private String f4229a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f4230b;

    class UnityRewardAd extends TTBaseAd implements IUnityAdsListener {

        /* renamed from: a, reason: collision with root package name */
        private boolean f4231a = false;

        UnityRewardAd(ITTAdapterRewardedAdListener iTTAdapterRewardedAdListener) {
            this.mTTAdatperCallback = iTTAdapterRewardedAdListener;
        }

        private ITTAdapterRewardedAdListener a() {
            return (ITTAdapterRewardedAdListener) this.mTTAdatperCallback;
        }

        void a(Activity activity, String str) {
            UnityAds.setDebugMode(Logger.isDebug());
            UnityAds.setListener(this);
            UnityAds.initialize(activity, str, false);
            if (isReady()) {
                onUnityAdsReady(UnityRewardVideoAdapter.this.getAdSlotId());
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return ((TTAbsAdLoaderAdapter) UnityRewardVideoAdapter.this).mAdSlot.getAdType();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return this.f4231a;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isAdCacheReady() {
            return isReady();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isReady() {
            return UnityAds.isReady(UnityRewardVideoAdapter.this.getAdSlotId());
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            this.f4231a = true;
            UnityAds.removeListener(this);
        }

        public void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String str) {
            if (UnityRewardVideoAdapter.this.f4230b) {
                return;
            }
            UnityRewardVideoAdapter.this.f4230b = true;
            UnityRewardVideoAdapter.this.notifyAdFailed(UnityAdapterUtil.errorMsg(unityAdsError, str));
        }

        public void onUnityAdsFinish(String str, UnityAds.FinishState finishState) {
            Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelById(UnityRewardVideoAdapter.this.getAdapterRit(), UnityRewardVideoAdapter.this.getAdSlotId()) + "Unity RewardVideo Ad onUnityAdsFinish, placementId=" + str + IWeiboService.Scope.EMPTY_SCOPE + finishState.name());
            if (UnityRewardVideoAdapter.this.a(str)) {
                if (finishState == UnityAds.FinishState.COMPLETED) {
                    if (!(this.mTTAdatperCallback instanceof ITTAdapterRewardedAdListener)) {
                        return;
                    }
                    a().onRewardVerify(new RewardItem() { // from class: com.bytedance.msdk.adapter.unity.UnityRewardVideoAdapter.UnityRewardAd.1
                        @Override // com.bytedance.msdk.api.reward.RewardItem
                        public float getAmount() {
                            return ((TTAbsAdLoaderAdapter) UnityRewardVideoAdapter.this).mAdSlot.getRewardAmount();
                        }

                        @Override // com.bytedance.msdk.api.reward.RewardItem
                        public Map<String, Object> getCustomData() {
                            return null;
                        }

                        @Override // com.bytedance.msdk.api.reward.RewardItem
                        public String getRewardName() {
                            return ((TTAbsAdLoaderAdapter) UnityRewardVideoAdapter.this).mAdSlot.getRewardName();
                        }

                        @Override // com.bytedance.msdk.api.reward.RewardItem
                        public boolean rewardVerify() {
                            return true;
                        }
                    });
                    a().onVideoComplete();
                } else {
                    if (finishState != UnityAds.FinishState.SKIPPED) {
                        if (finishState == UnityAds.FinishState.ERROR && (this.mTTAdatperCallback instanceof ITTAdapterRewardedAdListener)) {
                            a().onVideoError();
                            return;
                        }
                        return;
                    }
                    if (!(this.mTTAdatperCallback instanceof ITTAdapterRewardedAdListener)) {
                        return;
                    } else {
                        a().onSkippedVideo();
                    }
                }
                a().onRewardedAdClosed();
            }
        }

        public void onUnityAdsReady(String str) {
            if (!UnityRewardVideoAdapter.this.a(str) || UnityRewardVideoAdapter.this.f4230b) {
                return;
            }
            UnityRewardVideoAdapter.this.f4230b = true;
            UnityRewardVideoAdapter.this.notifyAdLoaded(this);
            UnityRewardVideoAdapter.this.notifyAdVideoCache(this, new AdError(AdError.ERROR_CODE_THIRD_SDK_NO_CACHE_CALLBACK, AdError.AD_THIRD_SDK_NO_CACHE_CALLBACK_MSG));
        }

        public void onUnityAdsStart(String str) {
            Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelById(UnityRewardVideoAdapter.this.getAdapterRit(), UnityRewardVideoAdapter.this.getAdSlotId()) + "Unity RewardVideo Ad onUnityAdsStart, placementId=" + str);
            if (this.mTTAdatperCallback instanceof ITTAdapterRewardedAdListener) {
                a().onRewardedAdShow();
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void showAd(Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map) {
            if (isReady()) {
                UnityAds.show(activity, UnityRewardVideoAdapter.this.getAdSlotId());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(String str) {
        return !TextUtils.isEmpty(str) && str.equalsIgnoreCase(getAdSlotId());
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void destroy() {
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getAdNetWorkName() {
        return "unity";
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getSdkVersion() {
        return UnityAds.getVersion();
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void loadAd(Context context, Map<String, Object> map) {
        if (map != null) {
            if (map.containsKey("tt_ad_network_config_appid")) {
                this.f4229a = (String) map.get("tt_ad_network_config_appid");
            }
            Object obj = map.get("tt_ad_network_callback");
            ITTAdapterRewardedAdListener iTTAdapterRewardedAdListener = obj instanceof ITTAdapterRewardedAdListener ? (ITTAdapterRewardedAdListener) obj : null;
            if (TextUtils.isEmpty(this.f4229a)) {
                if (iTTAdapterRewardedAdListener != null) {
                    notifyAdFailed(new AdError(AdError.ERROR_CODE_MEDIA_ID, "Unity AppId can not be null ", AdError.ERROR_CODE_MEDIA_ID, "Unity AppId can not be null "));
                }
                Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelById(getAdapterRit(), getAdSlotId()) + "Unity RewardVideo Ad Error ,Unity AppId can not be null  ");
            }
            new UnityRewardAd(iTTAdapterRewardedAdListener).a((Activity) context, this.f4229a);
        }
    }
}
