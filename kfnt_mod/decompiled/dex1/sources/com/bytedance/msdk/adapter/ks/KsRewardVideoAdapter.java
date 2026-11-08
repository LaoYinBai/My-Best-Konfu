package com.bytedance.msdk.adapter.ks;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.Nullable;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.TTAdConstant;
import com.bytedance.msdk.api.reward.RewardItem;
import com.bytedance.msdk.api.v2.ad.reward.GMRewardedAdListener;
import com.bytedance.msdk.base.TTBaseAd;
import com.kwad.sdk.api.KsAdSDK;
import com.kwad.sdk.api.KsLoadManager;
import com.kwad.sdk.api.KsRewardVideoAd;
import com.kwad.sdk.api.KsScene;
import com.kwad.sdk.api.KsVideoPlayConfig;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class KsRewardVideoAdapter extends TTAbsAdLoaderAdapter {
    public static final String TAG = "PgRewardVideoAdapter";

    /* renamed from: a, reason: collision with root package name */
    private KsRewardVideo f4051a;

    class KsRewardVideo extends TTBaseAd {

        /* renamed from: a, reason: collision with root package name */
        private KsRewardVideoAd f4052a;

        /* renamed from: b, reason: collision with root package name */
        private boolean f4053b;

        /* renamed from: c, reason: collision with root package name */
        KsLoadManager.RewardVideoAdListener f4054c = new AnonymousClass1();

        /* renamed from: com.bytedance.msdk.adapter.ks.KsRewardVideoAdapter$KsRewardVideo$1, reason: invalid class name */
        class AnonymousClass1 implements KsLoadManager.RewardVideoAdListener {
            AnonymousClass1() {
            }

            public void onError(int i, String str) {
                KsRewardVideo.this.f4053b = false;
                Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelById(KsRewardVideoAdapter.this.getAdapterRit(), KsRewardVideoAdapter.this.getAdSlotId()) + "加载ks激励视频广告失败：code:" + i + "   msg:" + str);
                KsRewardVideoAdapter.this.notifyAdFailed(new AdError(AdError.ERROR_CODE_AD_LOAD_FAIL, AdError.AD_LOAD_FAIL_MSG, i, str));
            }

            public void onRequestResult(int i) {
            }

            public void onRewardVideoAdLoad(@Nullable List<KsRewardVideoAd> list) {
                StringBuilder sb;
                if (list == null) {
                    sb = new StringBuilder();
                    sb.append(TTLogUtil.getTagThirdLevelById(KsRewardVideoAdapter.this.getAdapterRit(), KsRewardVideoAdapter.this.getAdSlotId()));
                    sb.append("加载ks激励视频广告成功，但没有返回数据");
                } else {
                    sb = new StringBuilder();
                    sb.append(TTLogUtil.getTagThirdLevelById(KsRewardVideoAdapter.this.getAdapterRit(), KsRewardVideoAdapter.this.getAdSlotId()));
                    sb.append("加载ks激励视频广告成功，数量:");
                    sb.append(list.size());
                }
                Logger.e("TTMediationSDK", sb.toString());
                if (list == null || list.size() == 0 || list.get(0) == null) {
                    KsRewardVideoAdapter.this.notifyAdFailed(new AdError(AdError.ERROR_CODE_NO_AD, AdError.AD_NO_FILL, AdError.ERROR_CODE_NO_AD, AdError.AD_NO_FILL));
                    return;
                }
                KsRewardVideo.this.f4052a = list.get(0);
                if (KsRewardVideoAdapter.this.isClientBidding()) {
                    double ecpm = KsRewardVideo.this.f4052a.getECPM();
                    KsRewardVideo.this.setCpm(ecpm > 0.0d ? ecpm : 0.0d);
                    Logger.d("TTMediationSDK_ECMP", TTLogUtil.getTagThirdLevelById(KsRewardVideoAdapter.this.getAdapterRit(), KsRewardVideoAdapter.this.getAdSlotId()) + "KS_clientBidding 激励视频 返回的 cpm价格：" + ecpm);
                }
                KsRewardVideo.this.setExpressAd(true);
                KsRewardVideo.this.f4052a.setRewardAdInteractionListener(new KsRewardVideoAd.RewardAdInteractionListener() { // from class: com.bytedance.msdk.adapter.ks.KsRewardVideoAdapter.KsRewardVideo.1.1
                    public void onAdClicked() {
                        if (((TTBaseAd) KsRewardVideo.this).mTTAdatperCallback instanceof GMRewardedAdListener) {
                            KsRewardVideo.this.a().onRewardClick();
                        }
                    }

                    public void onPageDismiss() {
                        if (((TTBaseAd) KsRewardVideo.this).mTTAdatperCallback instanceof GMRewardedAdListener) {
                            KsRewardVideo.this.a().onRewardedAdClosed();
                        }
                    }

                    public void onRewardStepVerify(int i, int i2) {
                    }

                    public void onRewardVerify() {
                        if (((TTBaseAd) KsRewardVideo.this).mTTAdatperCallback instanceof GMRewardedAdListener) {
                            KsRewardVideo.this.a().onRewardVerify(new RewardItem() { // from class: com.bytedance.msdk.adapter.ks.KsRewardVideoAdapter.KsRewardVideo.1.1.1
                                @Override // com.bytedance.msdk.api.reward.RewardItem
                                public float getAmount() {
                                    if (((TTAbsAdLoaderAdapter) KsRewardVideoAdapter.this).mAdSlot != null) {
                                        return ((TTAbsAdLoaderAdapter) KsRewardVideoAdapter.this).mAdSlot.getRewardAmount();
                                    }
                                    return 0.0f;
                                }

                                @Override // com.bytedance.msdk.api.reward.RewardItem
                                public Map<String, Object> getCustomData() {
                                    return null;
                                }

                                @Override // com.bytedance.msdk.api.reward.RewardItem
                                public String getRewardName() {
                                    return ((TTAbsAdLoaderAdapter) KsRewardVideoAdapter.this).mAdSlot != null ? ((TTAbsAdLoaderAdapter) KsRewardVideoAdapter.this).mAdSlot.getRewardName() : "";
                                }

                                @Override // com.bytedance.msdk.api.reward.RewardItem
                                public boolean rewardVerify() {
                                    return true;
                                }
                            });
                        }
                    }

                    public void onVideoPlayEnd() {
                        if (((TTBaseAd) KsRewardVideo.this).mTTAdatperCallback instanceof GMRewardedAdListener) {
                            KsRewardVideo.this.a().onVideoComplete();
                        }
                    }

                    public void onVideoPlayError(int i, int i2) {
                        if (((TTBaseAd) KsRewardVideo.this).mTTAdatperCallback instanceof GMRewardedAdListener) {
                            KsRewardVideo.this.a().onVideoError();
                        }
                    }

                    public void onVideoPlayStart() {
                        if (((TTBaseAd) KsRewardVideo.this).mTTAdatperCallback instanceof GMRewardedAdListener) {
                            KsRewardVideo.this.a().onRewardedAdShow();
                        }
                    }

                    public void onVideoSkipToEnd(long j) {
                        if (((TTBaseAd) KsRewardVideo.this).mTTAdatperCallback instanceof GMRewardedAdListener) {
                            KsRewardVideo.this.a().onSkippedVideo();
                        }
                    }
                });
                KsRewardVideo.this.f4053b = true;
                KsRewardVideo ksRewardVideo = KsRewardVideo.this;
                KsRewardVideoAdapter.this.notifyAdLoaded(ksRewardVideo);
                KsRewardVideo ksRewardVideo2 = KsRewardVideo.this;
                KsRewardVideoAdapter.this.notifyAdVideoCache(ksRewardVideo2, new AdError(AdError.ERROR_CODE_THIRD_SDK_NO_CACHE_CALLBACK, AdError.AD_THIRD_SDK_NO_CACHE_CALLBACK_MSG));
            }
        }

        public KsRewardVideo(GMRewardedAdListener gMRewardedAdListener) {
            this.mTTAdatperCallback = gMRewardedAdListener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public GMRewardedAdListener a() {
            return (GMRewardedAdListener) this.mTTAdatperCallback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(KsScene ksScene) {
            KsAdSDK.getLoadManager().loadRewardVideoAd(ksScene, this.f4054c);
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean adnHasAdExpiredApi() {
            return true;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return ((TTAbsAdLoaderAdapter) KsRewardVideoAdapter.this).mAdSlot.getAdType();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return this.f4052a == null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isAdExpired() {
            if (this.f4052a != null) {
                return !r0.isAdEnable();
            }
            return true;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isReady() {
            KsRewardVideoAd ksRewardVideoAd;
            return this.f4053b && (ksRewardVideoAd = this.f4052a) != null && ksRewardVideoAd.isAdEnable();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            KsRewardVideoAd ksRewardVideoAd = this.f4052a;
            if (ksRewardVideoAd != null) {
                ksRewardVideoAd.setRewardAdInteractionListener((KsRewardVideoAd.RewardAdInteractionListener) null);
                this.f4052a = null;
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void showAd(Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map) {
            KsRewardVideoAd ksRewardVideoAd;
            if (isReady() && (ksRewardVideoAd = this.f4052a) != null && ksRewardVideoAd.isAdEnable()) {
                KsVideoPlayConfig ksVideoPlayConfigBuild = null;
                if (((TTAbsAdLoaderAdapter) KsRewardVideoAdapter.this).mAdSlot != null && ((TTAbsAdLoaderAdapter) KsRewardVideoAdapter.this).mAdSlot.getOrientation() == 2) {
                    ksVideoPlayConfigBuild = new KsVideoPlayConfig.Builder().showLandscape(true).build();
                }
                this.f4052a.showRewardVideoAd(activity, ksVideoPlayConfigBuild);
            }
        }
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void destroy() {
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getAdNetWorkName() {
        return "ks";
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getSdkVersion() {
        return KsAdSDK.getSDKVersion();
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void loadAd(Context context, Map<String, Object> map) {
        String str;
        try {
            KsScene.Builder builder = new KsScene.Builder(Long.valueOf(getAdSlotId()).longValue());
            HashMap map2 = new HashMap();
            String userID = this.mAdSlot.getUserID();
            if (userID != null) {
                map2.put("thirdUserId", userID);
            }
            Map<String, String> customData = this.mAdSlot.getCustomData();
            if (customData != null && (str = customData.get("ks")) != null) {
                map2.put("extraData", str);
            }
            if (map2.size() > 0) {
                builder.rewardCallbackExtraData(map2);
            }
            KsScene ksSceneBuild = builder.build();
            if (map != null) {
                Object obj = map.get("tt_ad_network_callback");
                this.f4051a = new KsRewardVideo(obj instanceof GMRewardedAdListener ? (GMRewardedAdListener) obj : null);
                this.f4051a.a(ksSceneBuild);
            }
        } catch (Exception unused) {
            notifyAdFailed(new AdError(AdError.ERROR_CODE_ADSLOT_ERROR, AdError.AD_SLOTID_ERROR_MSG, AdError.ERROR_CODE_ADSLOT_ERROR, AdError.AD_SLOTID_ERROR_MSG));
        }
    }
}
