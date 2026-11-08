package com.bytedance.msdk.adapter.sigmob;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdErrorUtil;
import com.bytedance.msdk.api.TTAdConstant;
import com.bytedance.msdk.api.reward.RewardItem;
import com.bytedance.msdk.api.v2.ad.reward.GMRewardedAdListener;
import com.bytedance.msdk.base.TTBaseAd;
import com.sigmob.windad.WindAdError;
import com.sigmob.windad.WindAds;
import com.sigmob.windad.bidding.WindBiddingRewardedAd;
import com.sigmob.windad.rewardedVideo.WindRewardAdRequest;
import com.sigmob.windad.rewardedVideo.WindRewardInfo;
import com.sigmob.windad.rewardedVideo.WindRewardedVideoAd;
import com.sigmob.windad.rewardedVideo.WindRewardedVideoAdListener;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class SigmobRewardVideoAdapter extends TTAbsAdLoaderAdapter {
    public static final String TAG = "SigmobRewardVideoAdapter";

    /* renamed from: a, reason: collision with root package name */
    private SigmobRewardVideo f4203a;

    /* renamed from: b, reason: collision with root package name */
    private Context f4204b;

    class SigmobRewardVideo extends TTBaseAd {

        /* renamed from: a, reason: collision with root package name */
        private WindRewardedVideoAd f4205a;

        /* renamed from: b, reason: collision with root package name */
        private WindBiddingRewardedAd f4206b;

        /* renamed from: c, reason: collision with root package name */
        private boolean f4207c;
        WindRewardedVideoAdListener d = new WindRewardedVideoAdListener() { // from class: com.bytedance.msdk.adapter.sigmob.SigmobRewardVideoAdapter.SigmobRewardVideo.1
            public void onVideoAdClicked(String str) {
                if (((TTBaseAd) SigmobRewardVideo.this).mTTAdatperCallback instanceof GMRewardedAdListener) {
                    SigmobRewardVideo.this.a().onRewardClick();
                }
            }

            public void onVideoAdClosed(WindRewardInfo windRewardInfo, String str) {
                if (((TTBaseAd) SigmobRewardVideo.this).mTTAdatperCallback instanceof GMRewardedAdListener) {
                    SigmobRewardVideo.this.a().onRewardedAdClosed();
                }
            }

            public void onVideoAdLoadError(WindAdError windAdError, String str) {
                SigmobRewardVideoAdapter sigmobRewardVideoAdapter;
                AdError adErrorObtainAdError;
                SigmobRewardVideo.this.f4207c = false;
                if (windAdError != null) {
                    adErrorObtainAdError = AdErrorUtil.getSigmobError(windAdError.getErrorCode(), windAdError.getMessage());
                    if (adErrorObtainAdError != null) {
                        adErrorObtainAdError.message = windAdError.getMessage();
                    }
                    sigmobRewardVideoAdapter = SigmobRewardVideoAdapter.this;
                } else {
                    sigmobRewardVideoAdapter = SigmobRewardVideoAdapter.this;
                    adErrorObtainAdError = AdErrorUtil.obtainAdError(AdError.ERROR_CODE_NO_AD, AdError.AD_NO_FILL);
                }
                sigmobRewardVideoAdapter.notifyAdFailed(adErrorObtainAdError);
            }

            public void onVideoAdLoadSuccess(String str) {
                SigmobRewardVideo.this.setExpressAd(true);
                SigmobRewardVideo.this.f4207c = true;
                SigmobRewardVideo sigmobRewardVideo = SigmobRewardVideo.this;
                SigmobRewardVideoAdapter.this.notifyAdVideoCache(sigmobRewardVideo, null);
            }

            public void onVideoAdPlayEnd(String str) {
                if (((TTBaseAd) SigmobRewardVideo.this).mTTAdatperCallback instanceof GMRewardedAdListener) {
                    SigmobRewardVideo.this.a().onVideoComplete();
                    SigmobRewardVideo.this.a().onRewardVerify(new RewardItem() { // from class: com.bytedance.msdk.adapter.sigmob.SigmobRewardVideoAdapter.SigmobRewardVideo.1.1
                        @Override // com.bytedance.msdk.api.reward.RewardItem
                        public float getAmount() {
                            return ((TTAbsAdLoaderAdapter) SigmobRewardVideoAdapter.this).mAdSlot.getRewardAmount();
                        }

                        @Override // com.bytedance.msdk.api.reward.RewardItem
                        public Map<String, Object> getCustomData() {
                            return null;
                        }

                        @Override // com.bytedance.msdk.api.reward.RewardItem
                        public String getRewardName() {
                            return ((TTAbsAdLoaderAdapter) SigmobRewardVideoAdapter.this).mAdSlot.getRewardName();
                        }

                        @Override // com.bytedance.msdk.api.reward.RewardItem
                        public boolean rewardVerify() {
                            return true;
                        }
                    });
                }
            }

            public void onVideoAdPlayError(WindAdError windAdError, String str) {
                if (((TTBaseAd) SigmobRewardVideo.this).mTTAdatperCallback instanceof GMRewardedAdListener) {
                    SigmobRewardVideo.this.a().onVideoError();
                }
            }

            public void onVideoAdPlayStart(String str) {
                if (((TTBaseAd) SigmobRewardVideo.this).mTTAdatperCallback instanceof GMRewardedAdListener) {
                    SigmobRewardVideo.this.a().onRewardedAdShow();
                }
            }

            public void onVideoAdPreLoadFail(String str) {
                SigmobRewardVideo.this.f4207c = false;
                SigmobRewardVideoAdapter.this.notifyAdFailed(new AdError(AdError.ERROR_CODE_AD_LOAD_FAIL, AdError.AD_LOAD_FAIL_MSG, 0, str));
            }

            public void onVideoAdPreLoadSuccess(String str) {
                SigmobRewardVideo.this.setExpressAd(true);
                SigmobRewardVideo.this.f4207c = true;
                SigmobRewardVideo sigmobRewardVideo = SigmobRewardVideo.this;
                SigmobRewardVideoAdapter.this.notifyAdLoaded(sigmobRewardVideo);
            }
        };

        public SigmobRewardVideo(GMRewardedAdListener gMRewardedAdListener) {
            this.mTTAdatperCallback = gMRewardedAdListener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public GMRewardedAdListener a() {
            return (GMRewardedAdListener) this.mTTAdatperCallback;
        }

        private void a(Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map) {
            try {
                if (!isReady() || this.f4206b == null) {
                    return;
                }
                this.f4206b.show(activity, (HashMap) null);
                Logger.e("TTMediationSDK", "SigmobRewardVideoAdapter --- showWithBid");
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

        private void a(String str) {
            if (SigmobRewardVideoAdapter.this.f4204b instanceof Activity) {
                this.f4206b = new WindBiddingRewardedAd((Activity) SigmobRewardVideoAdapter.this.f4204b, new WindRewardAdRequest(SigmobRewardVideoAdapter.this.getAdSlotId(), ((TTAbsAdLoaderAdapter) SigmobRewardVideoAdapter.this).mAdSlot.getUserID(), (Map) null));
                this.f4206b.setWindRewardedVideoAdListener(this.d);
                this.f4206b.loadAd(str);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b() {
            if (TextUtils.isEmpty(SigmobRewardVideoAdapter.this.getAdm())) {
                Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(SigmobRewardVideoAdapter.this.getAdapterRit(), SigmobRewardVideoAdapter.this.getAdSlotId()) + "loadAdWithoutBid......SigmobRewardVideoAdapter...loadAd start....");
                c();
                return;
            }
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(SigmobRewardVideoAdapter.this.getAdapterRit(), SigmobRewardVideoAdapter.this.getAdSlotId()) + "loadAdWithBid......SigmobRewardVideoAdapter...loadAd start....adm:" + SigmobRewardVideoAdapter.this.getAdm());
            a(SigmobRewardVideoAdapter.this.getAdm());
        }

        private void b(Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map) {
            try {
                if (!isReady() || this.f4205a == null) {
                    return;
                }
                this.f4205a.show(activity, (HashMap) null);
                Logger.e("TTMediationSDK", "SigmobRewardVideoAdapter --- showWithoutBid");
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

        private void c() {
            if (SigmobRewardVideoAdapter.this.f4204b instanceof Activity) {
                this.f4205a = new WindRewardedVideoAd((Activity) SigmobRewardVideoAdapter.this.f4204b, new WindRewardAdRequest(SigmobRewardVideoAdapter.this.getAdSlotId(), ((TTAbsAdLoaderAdapter) SigmobRewardVideoAdapter.this).mAdSlot.getUserID(), (Map) null));
                this.f4205a.setWindRewardedVideoAdListener(this.d);
                this.f4205a.loadAd();
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean adnHasAdVideoCachedApi() {
            return true;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return ((TTAbsAdLoaderAdapter) SigmobRewardVideoAdapter.this).mAdSlot.getAdType();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return !TextUtils.isEmpty(SigmobRewardVideoAdapter.this.getAdm()) ? this.f4206b == null : this.f4205a == null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isAdCacheReady() {
            return isReady();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isReady() {
            WindRewardedVideoAd windRewardedVideoAd;
            WindBiddingRewardedAd windBiddingRewardedAd;
            return !TextUtils.isEmpty(SigmobRewardVideoAdapter.this.getAdm()) ? this.f4207c && (windBiddingRewardedAd = this.f4206b) != null && windBiddingRewardedAd.isReady() : this.f4207c && (windRewardedVideoAd = this.f4205a) != null && windRewardedVideoAd.isReady();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            WindRewardedVideoAd windRewardedVideoAd = this.f4205a;
            if (windRewardedVideoAd != null) {
                windRewardedVideoAd.setWindRewardedVideoAdListener((WindRewardedVideoAdListener) null);
                this.f4205a = null;
            }
            WindBiddingRewardedAd windBiddingRewardedAd = this.f4206b;
            if (windBiddingRewardedAd != null) {
                windBiddingRewardedAd.setWindRewardedVideoAdListener((WindRewardedVideoAdListener) null);
                this.f4206b = null;
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void showAd(Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map) {
            if (TextUtils.isEmpty(SigmobRewardVideoAdapter.this.getAdm())) {
                b(activity, map);
            } else {
                a(activity, map);
            }
        }
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void destroy() {
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getAdNetWorkName() {
        return "sigmob";
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getSdkVersion() {
        return WindAds.getVersion();
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void loadAd(Context context, Map<String, Object> map) {
        this.f4204b = context;
        if (map != null) {
            Object obj = map.get("tt_ad_network_callback");
            this.f4203a = new SigmobRewardVideo(obj instanceof GMRewardedAdListener ? (GMRewardedAdListener) obj : null);
            this.f4203a.b();
        }
    }
}
