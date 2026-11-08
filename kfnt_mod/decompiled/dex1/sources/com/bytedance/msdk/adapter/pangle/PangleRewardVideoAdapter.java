package com.bytedance.msdk.adapter.pangle;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.TTAdConstant;
import com.bytedance.msdk.api.reward.RewardItem;
import com.bytedance.msdk.api.v2.ad.reward.GMRewardedAdListener;
import com.bytedance.msdk.base.TTBaseAd;
import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTRewardVideoAd;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class PangleRewardVideoAdapter extends TTAbsAdLoaderAdapter {
    public static final String TAG = "PgRewardVideoAdapter";

    /* renamed from: a, reason: collision with root package name */
    private PangleRewardVideo f4171a;

    /* renamed from: b, reason: collision with root package name */
    private Context f4172b;

    class PangleRewardVideo extends TTBaseAd {

        /* renamed from: a, reason: collision with root package name */
        TTAdNative.RewardVideoAdListener f4173a = new AnonymousClass1();

        /* renamed from: c, reason: collision with root package name */
        private TTRewardVideoAd f4175c;
        private boolean d;
        private boolean e;

        /* renamed from: com.bytedance.msdk.adapter.pangle.PangleRewardVideoAdapter$PangleRewardVideo$1, reason: invalid class name */
        class AnonymousClass1 implements TTAdNative.RewardVideoAdListener {
            AnonymousClass1() {
            }

            @Override // com.bytedance.sdk.openadsdk.TTAdNative.RewardVideoAdListener, com.bytedance.sdk.openadsdk.common.CommonListener
            public void onError(int i, String str) {
                Logger.e("TTMediationSDK", "pangle_reward_onError code:" + i + "  message:" + str);
                PangleRewardVideo.this.d = false;
                PangleRewardVideoAdapter.this.notifyAdFailed(PangleAdapterUtils.buildAdError(i, str));
            }

            @Override // com.bytedance.sdk.openadsdk.TTAdNative.RewardVideoAdListener
            public void onRewardVideoAdLoad(TTRewardVideoAd tTRewardVideoAd) {
                Logger.e("TTMediationSDK", "pangle_reward_onRewardVideoAdLoad");
                if (tTRewardVideoAd == null) {
                    PangleRewardVideoAdapter.this.notifyAdFailed(PangleAdapterUtils.buildAdError(AdError.ERROR_CODE_NO_AD, AdError.AD_NO_FILL));
                    return;
                }
                PangleRewardVideo.this.f4175c = tTRewardVideoAd;
                PangleRewardVideo.this.setExpressAd(true);
                PangleRewardVideo pangleRewardVideo = PangleRewardVideo.this;
                pangleRewardVideo.setInteractionType(pangleRewardVideo.f4175c.getInteractionType());
                Map<String, Object> mediaExtraInfo = PangleRewardVideo.this.f4175c.getMediaExtraInfo();
                if (PangleRewardVideoAdapter.this.isClientBidding() && mediaExtraInfo != null) {
                    double value = PangleAdapterUtils.getValue(mediaExtraInfo.get("price"));
                    Logger.d("TTMediationSDK_ECMP", TTLogUtil.getTagThirdLevelById(PangleRewardVideoAdapter.this.getAdapterRit(), PangleRewardVideoAdapter.this.getAdSlotId()) + "pangle reward 返回的 cpm价格：" + value);
                    PangleRewardVideo pangleRewardVideo2 = PangleRewardVideo.this;
                    if (value <= 0.0d) {
                        value = 0.0d;
                    }
                    pangleRewardVideo2.setCpm(value);
                }
                if (mediaExtraInfo != null) {
                    Object obj = mediaExtraInfo.get("materialMetaIsFromPreload");
                    if (obj instanceof Boolean) {
                        PangleRewardVideo.this.e = ((Boolean) obj).booleanValue();
                        Logger.d("TTMediationSDK", "pangle 激励 返回的adnPreload：" + PangleRewardVideo.this.e);
                    }
                }
                PangleRewardVideo.this.d = true;
                PangleRewardVideo.this.f4175c.setRewardAdInteractionListener(new TTRewardVideoAd.RewardAdInteractionListener() { // from class: com.bytedance.msdk.adapter.pangle.PangleRewardVideoAdapter.PangleRewardVideo.1.1
                    @Override // com.bytedance.sdk.openadsdk.TTRewardVideoAd.RewardAdInteractionListener
                    public void onAdClose() {
                        Logger.e("TTMediationSDK", "pangle_reward:onAdClose");
                        if (((TTBaseAd) PangleRewardVideo.this).mTTAdatperCallback instanceof GMRewardedAdListener) {
                            PangleRewardVideo.this.a().onRewardedAdClosed();
                        }
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTRewardVideoAd.RewardAdInteractionListener
                    public void onAdShow() {
                        Logger.e("TTMediationSDK", "pangle_reward:onAdShow");
                        if (((TTBaseAd) PangleRewardVideo.this).mTTAdatperCallback instanceof GMRewardedAdListener) {
                            PangleRewardVideo.this.a().onRewardedAdShow();
                        }
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTRewardVideoAd.RewardAdInteractionListener
                    public void onAdVideoBarClick() {
                        Logger.e("TTMediationSDK", "pangle_reward:onAdVideoBarClick");
                        if (((TTBaseAd) PangleRewardVideo.this).mTTAdatperCallback instanceof GMRewardedAdListener) {
                            PangleRewardVideo.this.a().onRewardClick();
                        }
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTRewardVideoAd.RewardAdInteractionListener
                    public void onRewardVerify(final boolean z, final int i, final String str, int i2, String str2) {
                        Logger.e("TTMediationSDK", "pangle_reward:onRewardVerify");
                        if (((TTBaseAd) PangleRewardVideo.this).mTTAdatperCallback instanceof GMRewardedAdListener) {
                            PangleRewardVideo.this.a().onRewardVerify(new RewardItem() { // from class: com.bytedance.msdk.adapter.pangle.PangleRewardVideoAdapter.PangleRewardVideo.1.1.1
                                @Override // com.bytedance.msdk.api.reward.RewardItem
                                public float getAmount() {
                                    int i3 = i;
                                    if (i3 >= 0) {
                                        return i3;
                                    }
                                    if (((TTAbsAdLoaderAdapter) PangleRewardVideoAdapter.this).mAdSlot != null) {
                                        return ((TTAbsAdLoaderAdapter) PangleRewardVideoAdapter.this).mAdSlot.getRewardAmount();
                                    }
                                    return 0.0f;
                                }

                                @Override // com.bytedance.msdk.api.reward.RewardItem
                                public Map<String, Object> getCustomData() {
                                    return null;
                                }

                                @Override // com.bytedance.msdk.api.reward.RewardItem
                                public String getRewardName() {
                                    return !TextUtils.isEmpty(str) ? str : ((TTAbsAdLoaderAdapter) PangleRewardVideoAdapter.this).mAdSlot != null ? ((TTAbsAdLoaderAdapter) PangleRewardVideoAdapter.this).mAdSlot.getRewardName() : "";
                                }

                                @Override // com.bytedance.msdk.api.reward.RewardItem
                                public boolean rewardVerify() {
                                    return z;
                                }
                            });
                        }
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTRewardVideoAd.RewardAdInteractionListener
                    public void onSkippedVideo() {
                        Logger.e("TTMediationSDK", "pangle_reward:onSkippedVideo");
                        if (((TTBaseAd) PangleRewardVideo.this).mTTAdatperCallback instanceof GMRewardedAdListener) {
                            PangleRewardVideo.this.a().onSkippedVideo();
                        }
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTRewardVideoAd.RewardAdInteractionListener
                    public void onVideoComplete() {
                        Logger.e("TTMediationSDK", "pangle_reward:onVideoComplete");
                        if (((TTBaseAd) PangleRewardVideo.this).mTTAdatperCallback instanceof GMRewardedAdListener) {
                            PangleRewardVideo.this.a().onVideoComplete();
                        }
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTRewardVideoAd.RewardAdInteractionListener
                    public void onVideoError() {
                        Logger.e("TTMediationSDK", "pangle_reward:onVideoError");
                        if (((TTBaseAd) PangleRewardVideo.this).mTTAdatperCallback instanceof GMRewardedAdListener) {
                            PangleRewardVideo.this.a().onVideoError();
                        }
                    }
                });
                PangleRewardVideo pangleRewardVideo3 = PangleRewardVideo.this;
                PangleRewardVideoAdapter.this.notifyAdLoaded(pangleRewardVideo3);
            }

            @Override // com.bytedance.sdk.openadsdk.TTAdNative.RewardVideoAdListener
            public void onRewardVideoCached() {
                Logger.e("TTMediationSDK", "pangle_reward_onRewardVideoCached");
            }

            @Override // com.bytedance.sdk.openadsdk.TTAdNative.RewardVideoAdListener
            public void onRewardVideoCached(TTRewardVideoAd tTRewardVideoAd) {
                Logger.e("TTMediationSDK", "pangle_reward_onRewardVideoCached");
                PangleRewardVideo pangleRewardVideo = PangleRewardVideo.this;
                PangleRewardVideoAdapter.this.notifyAdVideoCache(pangleRewardVideo, null);
            }
        }

        public PangleRewardVideo(GMRewardedAdListener gMRewardedAdListener, Map<String, Object> map) {
            this.mTTAdatperCallback = gMRewardedAdListener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public GMRewardedAdListener a() {
            return (GMRewardedAdListener) this.mTTAdatperCallback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b() {
            TTAdNative tTAdNativeCreateAdNative = TTAdSdk.getAdManager().createAdNative(PangleRewardVideoAdapter.this.f4172b);
            String mediaExtra = ((TTAbsAdLoaderAdapter) PangleRewardVideoAdapter.this).mAdSlot.getMediaExtra();
            Map<String, String> customData = ((TTAbsAdLoaderAdapter) PangleRewardVideoAdapter.this).mAdSlot.getCustomData();
            if (customData != null) {
                String str = customData.get("pangle");
                if (!TextUtils.isEmpty(str)) {
                    mediaExtra = str;
                }
            }
            AdSlot.Builder builderBuildPangleAdSlot = PangleAdapterUtils.buildPangleAdSlot(((TTAbsAdLoaderAdapter) PangleRewardVideoAdapter.this).mAdSlot, PangleRewardVideoAdapter.this.getAdSlotId(), ((TTAbsAdLoaderAdapter) PangleRewardVideoAdapter.this).mWaterfallAbTestParam, PangleRewardVideoAdapter.this.getClientReqId(), PangleRewardVideoAdapter.this.getAdm(), false);
            builderBuildPangleAdSlot.setRewardName(((TTAbsAdLoaderAdapter) PangleRewardVideoAdapter.this).mAdSlot.getRewardName()).setUserID(((TTAbsAdLoaderAdapter) PangleRewardVideoAdapter.this).mAdSlot.getUserID()).setRewardAmount(((TTAbsAdLoaderAdapter) PangleRewardVideoAdapter.this).mAdSlot.getRewardAmount()).setMediaExtra(mediaExtra).setImageAcceptedSize(1080, 1920).setOrientation(((TTAbsAdLoaderAdapter) PangleRewardVideoAdapter.this).mAdSlot.getOrientation());
            tTAdNativeCreateAdNative.loadRewardVideoAd(builderBuildPangleAdSlot.build(), this.f4173a);
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean adnHasAdExpiredApi() {
            return true;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean adnHasAdVideoCachedApi() {
            return true;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public long getAdId() {
            TTRewardVideoAd tTRewardVideoAd = this.f4175c;
            return tTRewardVideoAd != null ? PangleAdapterUtils.getAdId(tTRewardVideoAd.getMediaExtraInfo()) : super.getAdId();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return ((TTAbsAdLoaderAdapter) PangleRewardVideoAdapter.this).mAdSlot.getAdType();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public long getCreativeId() {
            TTRewardVideoAd tTRewardVideoAd = this.f4175c;
            return tTRewardVideoAd != null ? PangleAdapterUtils.getCreativeId(tTRewardVideoAd.getMediaExtraInfo()) : super.getCreativeId();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public String getReqId() {
            TTRewardVideoAd tTRewardVideoAd = this.f4175c;
            return tTRewardVideoAd != null ? PangleAdapterUtils.getReqId(tTRewardVideoAd.getMediaExtraInfo()) : super.getReqId();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return this.f4175c == null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isAdExpired() {
            long jCurrentTimeMillis = System.currentTimeMillis();
            TTRewardVideoAd tTRewardVideoAd = this.f4175c;
            return tTRewardVideoAd == null || jCurrentTimeMillis > tTRewardVideoAd.getExpirationTimestamp();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isAdnPreload() {
            return this.e;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isReady() {
            return this.d;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            TTRewardVideoAd tTRewardVideoAd = this.f4175c;
            if (tTRewardVideoAd != null) {
                tTRewardVideoAd.setDownloadListener(null);
                this.f4175c.setRewardAdInteractionListener(null);
                this.f4175c = null;
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void showAd(Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map) {
            if (this.f4175c != null) {
                TTAdConstant.RitScenes ritScenes = PangleAdapterUtils.getRitScenes(map);
                String customRitScenes = PangleAdapterUtils.getCustomRitScenes(map);
                if (ritScenes != null) {
                    this.f4175c.showRewardVideoAd(activity, ritScenes, customRitScenes);
                } else {
                    this.f4175c.showRewardVideoAd(activity);
                }
            }
        }
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void destroy() {
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getAdNetWorkName() {
        return "pangle";
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getSdkVersion() {
        return TTAdSdk.getAdManager().getSDKVersion();
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void loadAd(Context context, Map<String, Object> map) {
        this.f4172b = context;
        if (map != null) {
            Object obj = map.get("tt_ad_network_callback");
            this.f4171a = new PangleRewardVideo(obj instanceof GMRewardedAdListener ? (GMRewardedAdListener) obj : null, map);
            this.f4171a.b();
        }
    }
}
