package com.bytedance.msdk.adapter.gdt;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.listener.ITTAdapterRewardedAdListener;
import com.bytedance.msdk.api.AdErrorUtil;
import com.bytedance.msdk.api.TTAdConstant;
import com.bytedance.msdk.api.TTVideoOption;
import com.bytedance.msdk.api.reward.RewardItem;
import com.bytedance.msdk.api.v2.GMAdConstant;
import com.bytedance.msdk.base.TTBaseAd;
import com.qq.e.ads.rewardvideo.RewardVideoAD;
import com.qq.e.ads.rewardvideo.RewardVideoADListener;
import com.qq.e.ads.rewardvideo.ServerSideVerificationOptions;
import com.qq.e.comm.managers.status.SDKStatus;
import com.qq.e.comm.util.AdError;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GdtRewardVideoAdapter extends TTAbsAdLoaderAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f4010a;

    /* renamed from: b, reason: collision with root package name */
    private TTVideoOption f4011b;

    class GdtRewardVideo extends TTBaseAd {

        /* renamed from: a, reason: collision with root package name */
        private RewardVideoAD f4012a;

        /* renamed from: b, reason: collision with root package name */
        private boolean f4013b;

        /* renamed from: c, reason: collision with root package name */
        RewardVideoADListener f4014c = new RewardVideoADListener() { // from class: com.bytedance.msdk.adapter.gdt.GdtRewardVideoAdapter.GdtRewardVideo.1
            public void onADClick() {
                if (((TTBaseAd) GdtRewardVideo.this).mTTAdatperCallback instanceof ITTAdapterRewardedAdListener) {
                    GdtRewardVideo.this.b().onRewardClick();
                }
            }

            public void onADClose() {
                if (((TTBaseAd) GdtRewardVideo.this).mTTAdatperCallback instanceof ITTAdapterRewardedAdListener) {
                    GdtRewardVideo.this.b().onRewardedAdClosed();
                }
            }

            public void onADExpose() {
            }

            /* JADX WARN: Removed duplicated region for block: B:17:0x00c8  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onADLoad() {
                /*
                    Method dump skipped, instructions count: 298
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.bytedance.msdk.adapter.gdt.GdtRewardVideoAdapter.GdtRewardVideo.AnonymousClass1.onADLoad():void");
            }

            public void onADShow() {
                if (((TTBaseAd) GdtRewardVideo.this).mTTAdatperCallback instanceof ITTAdapterRewardedAdListener) {
                    GdtRewardVideo.this.b().onRewardedAdShow();
                }
            }

            public void onError(AdError adError) {
                GdtRewardVideo.this.f4013b = false;
                if (adError != null) {
                    GdtRewardVideoAdapter.this.notifyAdFailed(AdErrorUtil.getGdtError(adError.getErrorCode(), adError.getErrorMsg()));
                } else {
                    GdtRewardVideoAdapter.this.notifyAdFailed(AdErrorUtil.obtainAdError(0, com.bytedance.msdk.api.AdError.getMessage(0)));
                }
            }

            public void onReward(final Map<String, Object> map) {
                if (((TTBaseAd) GdtRewardVideo.this).mTTAdatperCallback instanceof ITTAdapterRewardedAdListener) {
                    GdtRewardVideo.this.b().onRewardVerify(new RewardItem() { // from class: com.bytedance.msdk.adapter.gdt.GdtRewardVideoAdapter.GdtRewardVideo.1.1
                        @Override // com.bytedance.msdk.api.reward.RewardItem
                        public float getAmount() {
                            if (((TTAbsAdLoaderAdapter) GdtRewardVideoAdapter.this).mAdSlot != null) {
                                return ((TTAbsAdLoaderAdapter) GdtRewardVideoAdapter.this).mAdSlot.getRewardAmount();
                            }
                            return 0.0f;
                        }

                        @Override // com.bytedance.msdk.api.reward.RewardItem
                        public Map<String, Object> getCustomData() {
                            Map map2 = map;
                            if (map2 == null || map2.isEmpty()) {
                                return null;
                            }
                            map.put(RewardItem.KEY_ADN_NAME, "gdt");
                            return map;
                        }

                        @Override // com.bytedance.msdk.api.reward.RewardItem
                        public String getRewardName() {
                            return ((TTAbsAdLoaderAdapter) GdtRewardVideoAdapter.this).mAdSlot != null ? ((TTAbsAdLoaderAdapter) GdtRewardVideoAdapter.this).mAdSlot.getRewardName() : "";
                        }

                        @Override // com.bytedance.msdk.api.reward.RewardItem
                        public boolean rewardVerify() {
                            return true;
                        }
                    });
                }
            }

            public void onVideoCached() {
                GdtRewardVideo gdtRewardVideo = GdtRewardVideo.this;
                GdtRewardVideoAdapter.this.notifyAdVideoCache(gdtRewardVideo, null);
            }

            public void onVideoComplete() {
                if (((TTBaseAd) GdtRewardVideo.this).mTTAdatperCallback instanceof ITTAdapterRewardedAdListener) {
                    GdtRewardVideo.this.b().onVideoComplete();
                }
            }
        };

        GdtRewardVideo(ITTAdapterRewardedAdListener iTTAdapterRewardedAdListener) {
            this.mTTAdatperCallback = iTTAdapterRewardedAdListener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ITTAdapterRewardedAdListener b() {
            return (ITTAdapterRewardedAdListener) this.mTTAdatperCallback;
        }

        void a() {
            String str;
            this.f4012a = new RewardVideoAD(GdtRewardVideoAdapter.this.f4010a, GdtRewardVideoAdapter.this.getAdSlotId(), this.f4014c, (GdtRewardVideoAdapter.this.f4011b == null || GdtRewardVideoAdapter.this.f4011b.isMuted()) ? false : true);
            if (((TTAbsAdLoaderAdapter) GdtRewardVideoAdapter.this).mAdSlot != null) {
                ServerSideVerificationOptions.Builder builder = new ServerSideVerificationOptions.Builder();
                String userID = ((TTAbsAdLoaderAdapter) GdtRewardVideoAdapter.this).mAdSlot.getUserID();
                if (userID != null) {
                    builder.setUserId(userID);
                }
                Map<String, String> customData = ((TTAbsAdLoaderAdapter) GdtRewardVideoAdapter.this).mAdSlot.getCustomData();
                if (customData != null && (str = customData.get("gdt")) != null) {
                    builder.setCustomData(str);
                }
                if (userID != null || customData != null) {
                    this.f4012a.setServerSideVerificationOptions(builder.build());
                }
            }
            this.f4012a.loadAD();
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
        public void bidLoseNotify(Map<String, Object> map) {
            if (!isClientBiddingAd() || this.f4012a == null || map == null) {
                return;
            }
            try {
                Object obj = map.get("bidding_lose_reason");
                if (obj instanceof GMAdConstant.BiddingLossReason) {
                    this.f4012a.sendLossNotification(0, GDTAdapterUtils.getBiddingLossReason((GMAdConstant.BiddingLossReason) obj), (String) null);
                }
            } catch (Exception unused) {
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void bidWinNotify(Map<String, Object> map) {
            RewardVideoAD rewardVideoAD;
            if (isClientBiddingAd() && (rewardVideoAD = this.f4012a) != null) {
                try {
                    rewardVideoAD.sendWinNotification((int) getCpm());
                } catch (Exception unused) {
                }
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return 7;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return this.f4012a == null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isAdCacheReady() {
            return isReady();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isAdExpired() {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            RewardVideoAD rewardVideoAD = this.f4012a;
            return rewardVideoAD == null || jElapsedRealtime > rewardVideoAD.getExpireTimestamp();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isReady() {
            RewardVideoAD rewardVideoAD;
            if (!this.f4013b || (rewardVideoAD = this.f4012a) == null || rewardVideoAD.hasShown()) {
                return false;
            }
            return this.f4013b;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            if (this.f4012a != null) {
                this.f4012a = null;
                this.mTTAdatperCallback = null;
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void showAd(Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map) {
            RewardVideoAD rewardVideoAD = this.f4012a;
            if (rewardVideoAD != null) {
                rewardVideoAD.showAD(activity);
            }
        }
    }

    private void a(ITTAdapterRewardedAdListener iTTAdapterRewardedAdListener) {
        new GdtRewardVideo(iTTAdapterRewardedAdListener).a();
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void destroy() {
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getAdNetWorkName() {
        return "gdt";
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getSdkVersion() {
        return SDKStatus.getSDKVersion();
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void loadAd(Context context, Map<String, Object> map) {
        this.f4010a = context;
        this.f4011b = this.mAdSlot.getTTVideoOption();
        if (map != null) {
            Object obj = map.get("tt_ad_network_callback");
            a(obj instanceof ITTAdapterRewardedAdListener ? (ITTAdapterRewardedAdListener) obj : null);
        }
    }
}
