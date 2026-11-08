package com.bytedance.msdk.adapter.baidu;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.baidu.mobads.sdk.api.AdSettings;
import com.baidu.mobads.sdk.api.RewardVideoAd;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.listener.ITTAdapterRewardedAdListener;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdErrorUtil;
import com.bytedance.msdk.api.BaiduExtraOptions;
import com.bytedance.msdk.api.TTAdConstant;
import com.bytedance.msdk.api.TTVideoOption;
import com.bytedance.msdk.api.reward.RewardItem;
import com.bytedance.msdk.base.TTBaseAd;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class BaiduRewardVideoAdapter extends TTAbsAdLoaderAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f3952a;

    /* renamed from: b, reason: collision with root package name */
    private TTVideoOption f3953b;

    class BaiduRewardVideoAd extends TTBaseAd implements RewardVideoAd.RewardVideoAdListener {

        /* renamed from: a, reason: collision with root package name */
        private RewardVideoAd f3954a;

        BaiduRewardVideoAd(ITTAdapterRewardedAdListener iTTAdapterRewardedAdListener) {
            this.mTTAdatperCallback = iTTAdapterRewardedAdListener;
        }

        private ITTAdapterRewardedAdListener b() {
            return (ITTAdapterRewardedAdListener) this.mTTAdatperCallback;
        }

        void a() {
            this.f3954a = new RewardVideoAd(BaiduRewardVideoAdapter.this.f3952a, BaiduRewardVideoAdapter.this.getAdSlotId(), this, BaiduRewardVideoAdapter.this.f3953b != null && BaiduRewardVideoAdapter.this.f3953b.useSurfaceView());
            if (BaiduRewardVideoAdapter.this.f3953b != null) {
                BaiduExtraOptions baiduExtraOption = BaiduRewardVideoAdapter.this.f3953b.getBaiduExtraOption();
                this.f3954a.setDownloadAppConfirmPolicy((baiduExtraOption == null || baiduExtraOption.getDownloadAppConfirmPolicy() == 0) ? 3 : baiduExtraOption.getDownloadAppConfirmPolicy());
                this.f3954a.setShowDialogOnSkip(baiduExtraOption != null && baiduExtraOption.getShowDialogOnSkip());
                this.f3954a.setUseRewardCountdown(baiduExtraOption != null && baiduExtraOption.getUseRewardCountdown());
                String appSid = baiduExtraOption != null ? baiduExtraOption.getAppSid() : null;
                if (!TextUtils.isEmpty(appSid)) {
                    this.f3954a.setAppSid(appSid);
                }
            }
            this.f3954a.load();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean adnHasAdExpiredApi() {
            return true;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return ((TTAbsAdLoaderAdapter) BaiduRewardVideoAdapter.this).mAdSlot.getAdType();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return this.f3954a == null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isAdExpired() {
            return !isReady();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isReady() {
            RewardVideoAd rewardVideoAd = this.f3954a;
            return rewardVideoAd != null && rewardVideoAd.isReady();
        }

        @Override // com.baidu.mobads.sdk.api.RewardVideoAd.RewardVideoAdListener, com.baidu.mobads.sdk.api.ScreenVideoAdListener
        public void onAdClick() {
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduRewardVideoAdapter.this.getAdapterRit(), BaiduRewardVideoAdapter.this.getAdSlotId()) + "Baidu rewardVideo ad onAdClick ...");
            if (this.mTTAdatperCallback instanceof ITTAdapterRewardedAdListener) {
                b().onRewardClick();
            }
        }

        @Override // com.baidu.mobads.sdk.api.RewardVideoAd.RewardVideoAdListener, com.baidu.mobads.sdk.api.ScreenVideoAdListener
        public void onAdClose(float f) {
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduRewardVideoAdapter.this.getAdapterRit(), BaiduRewardVideoAdapter.this.getAdSlotId()) + "Baidu rewardVideo ad onAdClose ...");
            if (this.mTTAdatperCallback instanceof ITTAdapterRewardedAdListener) {
                b().onRewardedAdClosed();
            }
        }

        @Override // com.baidu.mobads.sdk.api.RewardVideoAd.RewardVideoAdListener, com.baidu.mobads.sdk.api.ScreenVideoAdListener
        public void onAdFailed(String str) {
            BaiduRewardVideoAdapter.this.notifyAdFailed(AdErrorUtil.obtainAdError(AdError.ERROR_CODE_THIRD_SDK_LOAD_FAIL, str));
        }

        @Override // com.baidu.mobads.sdk.api.RewardVideoAd.RewardVideoAdListener, com.baidu.mobads.sdk.api.ScreenVideoAdListener
        public void onAdLoaded() {
            StringBuilder sb;
            String str;
            Logger.i("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduRewardVideoAdapter.this.getAdapterRit(), BaiduRewardVideoAdapter.this.getAdSlotId()) + "Baidu rewardVideo ad video load success ...");
            if (this.f3954a != null) {
                if (BaiduRewardVideoAdapter.this.isClientBidding()) {
                    double dDoubleValue = 0.0d;
                    try {
                        dDoubleValue = Double.valueOf(this.f3954a.getECPMLevel()).doubleValue();
                    } catch (Exception unused) {
                    }
                    setCpm(dDoubleValue);
                    sb = new StringBuilder();
                    sb.append(TTLogUtil.getTagThirdLevelById(BaiduRewardVideoAdapter.this.getAdapterRit(), BaiduRewardVideoAdapter.this.getAdSlotId()));
                    str = "Baidu_cientBidding reward 返回的 cpm价格：";
                } else if (BaiduRewardVideoAdapter.this.isMultiBidding()) {
                    setLevelTag(this.f3954a.getECPMLevel());
                    sb = new StringBuilder();
                    sb.append(TTLogUtil.getTagThirdLevelById(BaiduRewardVideoAdapter.this.getAdapterRit(), BaiduRewardVideoAdapter.this.getAdSlotId()));
                    str = "Baidu_多阶底价 reward 返回的 价格标签：";
                }
                sb.append(str);
                sb.append(this.f3954a.getECPMLevel());
                Logger.d("TTMediationSDK_ECMP", sb.toString());
            }
            BaiduRewardVideoAdapter.this.notifyAdLoaded(this);
        }

        @Override // com.baidu.mobads.sdk.api.RewardVideoAd.RewardVideoAdListener, com.baidu.mobads.sdk.api.ScreenVideoAdListener
        public void onAdShow() {
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduRewardVideoAdapter.this.getAdapterRit(), BaiduRewardVideoAdapter.this.getAdSlotId()) + "Baidu rewardVideo ad onAdShow ...");
            if (this.mTTAdatperCallback instanceof ITTAdapterRewardedAdListener) {
                b().onRewardedAdShow();
            }
        }

        @Override // com.baidu.mobads.sdk.api.RewardVideoAd.RewardVideoAdListener, com.baidu.mobads.sdk.api.ScreenVideoAdListener
        public void onAdSkip(float f) {
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduRewardVideoAdapter.this.getAdapterRit(), BaiduRewardVideoAdapter.this.getAdSlotId()) + "Baidu rewardVideo ad onAdSkip ...");
            if (this.mTTAdatperCallback instanceof ITTAdapterRewardedAdListener) {
                b().onSkippedVideo();
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            this.f3954a = null;
            BaiduRewardVideoAdapter.this.f3952a = null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onPause() {
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onResume() {
        }

        @Override // com.baidu.mobads.sdk.api.RewardVideoAd.RewardVideoAdListener
        public void onRewardVerify(final boolean z) {
            b().onRewardVerify(new RewardItem() { // from class: com.bytedance.msdk.adapter.baidu.BaiduRewardVideoAdapter.BaiduRewardVideoAd.1
                @Override // com.bytedance.msdk.api.reward.RewardItem
                public float getAmount() {
                    if (((TTAbsAdLoaderAdapter) BaiduRewardVideoAdapter.this).mAdSlot != null) {
                        return ((TTAbsAdLoaderAdapter) BaiduRewardVideoAdapter.this).mAdSlot.getRewardAmount();
                    }
                    return 1.0f;
                }

                @Override // com.bytedance.msdk.api.reward.RewardItem
                public Map<String, Object> getCustomData() {
                    return null;
                }

                @Override // com.bytedance.msdk.api.reward.RewardItem
                public String getRewardName() {
                    return ((TTAbsAdLoaderAdapter) BaiduRewardVideoAdapter.this).mAdSlot != null ? ((TTAbsAdLoaderAdapter) BaiduRewardVideoAdapter.this).mAdSlot.getRewardName() : "";
                }

                @Override // com.bytedance.msdk.api.reward.RewardItem
                public boolean rewardVerify() {
                    return z;
                }
            });
        }

        @Override // com.baidu.mobads.sdk.api.RewardVideoAd.RewardVideoAdListener, com.baidu.mobads.sdk.api.ScreenVideoAdListener
        public void onVideoDownloadFailed() {
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduRewardVideoAdapter.this.getAdapterRit(), BaiduRewardVideoAdapter.this.getAdSlotId()) + "Baidu rewardVideo ad video  cache failed ...");
        }

        @Override // com.baidu.mobads.sdk.api.RewardVideoAd.RewardVideoAdListener, com.baidu.mobads.sdk.api.ScreenVideoAdListener
        public void onVideoDownloadSuccess() {
            Logger.i("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduRewardVideoAdapter.this.getAdapterRit(), BaiduRewardVideoAdapter.this.getAdSlotId()) + "Baidu rewardVideo ad video cache success ...");
            BaiduRewardVideoAdapter.this.notifyAdVideoCache(this, null);
        }

        @Override // com.baidu.mobads.sdk.api.RewardVideoAd.RewardVideoAdListener, com.baidu.mobads.sdk.api.ScreenVideoAdListener
        public void playCompletion() {
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduRewardVideoAdapter.this.getAdapterRit(), BaiduRewardVideoAdapter.this.getAdSlotId()) + "Baidu rewardVideo video complete and reward verify ...");
            if (this.mTTAdatperCallback instanceof ITTAdapterRewardedAdListener) {
                b().onVideoComplete();
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void showAd(Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map) {
            if (this.f3954a == null || !isReady()) {
                return;
            }
            this.f3954a.show();
        }
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void destroy() {
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getAdNetWorkName() {
        return "baidu";
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getSdkVersion() {
        return AdSettings.getSDKVersion();
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void loadAd(Context context, Map<String, Object> map) {
        this.f3952a = context;
        this.f3953b = this.mAdSlot.getTTVideoOption();
        if (map != null) {
            Object obj = map.get("tt_ad_network_callback");
            new BaiduRewardVideoAd(obj instanceof ITTAdapterRewardedAdListener ? (ITTAdapterRewardedAdListener) obj : null).a();
        }
    }
}
