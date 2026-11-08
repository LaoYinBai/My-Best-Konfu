package com.bytedance.msdk.adapter.mintegral;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdErrorUtil;
import com.bytedance.msdk.api.TTAdConstant;
import com.bytedance.msdk.api.TTVideoOption;
import com.bytedance.msdk.api.reward.RewardItem;
import com.bytedance.msdk.api.v2.ad.reward.GMRewardedAdListener;
import com.bytedance.msdk.base.TTBaseAd;
import com.mbridge.msdk.out.MBBidRewardVideoHandler;
import com.mbridge.msdk.out.MBRewardVideoHandler;
import com.mbridge.msdk.out.MBridgeIds;
import com.mbridge.msdk.out.RewardInfo;
import com.mbridge.msdk.out.RewardVideoListener;
import com.mbridge.msdk.video.bt.module.b.g;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class MintegralRewardVideoAdapter extends TTAbsAdLoaderAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f4105a;

    /* renamed from: b, reason: collision with root package name */
    private TTVideoOption f4106b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f4107c = false;
    private boolean d = false;

    class TTMTGRewardVideo extends TTBaseAd {

        /* renamed from: a, reason: collision with root package name */
        private MBRewardVideoHandler f4108a;

        /* renamed from: b, reason: collision with root package name */
        private MBBidRewardVideoHandler f4109b;

        /* renamed from: c, reason: collision with root package name */
        RewardVideoListener f4110c = new RewardVideoListener() { // from class: com.bytedance.msdk.adapter.mintegral.MintegralRewardVideoAdapter.TTMTGRewardVideo.1
            public void onAdClose(MBridgeIds mBridgeIds, final RewardInfo rewardInfo) {
                if (rewardInfo != null) {
                    Logger.i("TTMediationSDK", TTLogUtil.getTagThirdLevelById(MintegralRewardVideoAdapter.this.getAdapterRit(), MintegralRewardVideoAdapter.this.getAdSlotId()) + "onAdClose rewardinfo :RewardName:" + rewardInfo.getRewardName() + "RewardAmout:" + rewardInfo.getRewardAmount() + " isCompleteView：" + rewardInfo.isCompleteView());
                }
                if (((TTBaseAd) TTMTGRewardVideo.this).mTTAdatperCallback instanceof GMRewardedAdListener) {
                    TTMTGRewardVideo.this.a().onRewardVerify(new RewardItem(this) { // from class: com.bytedance.msdk.adapter.mintegral.MintegralRewardVideoAdapter.TTMTGRewardVideo.1.1
                        @Override // com.bytedance.msdk.api.reward.RewardItem
                        public float getAmount() {
                            RewardInfo rewardInfo2 = rewardInfo;
                            if (rewardInfo2 == null) {
                                return 0.0f;
                            }
                            try {
                                return Float.parseFloat(rewardInfo2.getRewardAmount());
                            } catch (Exception unused) {
                                return 0.0f;
                            }
                        }

                        @Override // com.bytedance.msdk.api.reward.RewardItem
                        public Map<String, Object> getCustomData() {
                            return null;
                        }

                        @Override // com.bytedance.msdk.api.reward.RewardItem
                        public String getRewardName() {
                            RewardInfo rewardInfo2 = rewardInfo;
                            return rewardInfo2 == null ? "" : rewardInfo2.getRewardName();
                        }

                        @Override // com.bytedance.msdk.api.reward.RewardItem
                        public boolean rewardVerify() {
                            RewardInfo rewardInfo2 = rewardInfo;
                            if (rewardInfo2 == null) {
                                return false;
                            }
                            return rewardInfo2.isCompleteView();
                        }
                    });
                }
                if (((TTBaseAd) TTMTGRewardVideo.this).mTTAdatperCallback instanceof GMRewardedAdListener) {
                    TTMTGRewardVideo.this.a().onRewardedAdClosed();
                }
            }

            public void onAdShow(MBridgeIds mBridgeIds) {
                if (((TTBaseAd) TTMTGRewardVideo.this).mTTAdatperCallback instanceof GMRewardedAdListener) {
                    TTMTGRewardVideo.this.a().onRewardedAdShow();
                }
            }

            public void onEndcardShow(MBridgeIds mBridgeIds) {
            }

            public void onLoadSuccess(MBridgeIds mBridgeIds) {
                if (MintegralRewardVideoAdapter.this.f4107c) {
                    return;
                }
                MintegralRewardVideoAdapter.this.f4107c = true;
                TTMTGRewardVideo tTMTGRewardVideo = TTMTGRewardVideo.this;
                MintegralRewardVideoAdapter.this.notifyAdLoaded(tTMTGRewardVideo);
            }

            public void onShowFail(MBridgeIds mBridgeIds, String str) {
                if (((TTBaseAd) TTMTGRewardVideo.this).mTTAdatperCallback instanceof GMRewardedAdListener) {
                    TTMTGRewardVideo.this.a().onRewardedAdShowFail(new AdError(AdError.ERROR_CODE_ADN_SHOW_FAIL, str, AdError.ERROR_CODE_ADN_SHOW_FAIL, str));
                }
            }

            public void onVideoAdClicked(MBridgeIds mBridgeIds) {
                if (((TTBaseAd) TTMTGRewardVideo.this).mTTAdatperCallback instanceof GMRewardedAdListener) {
                    TTMTGRewardVideo.this.a().onRewardClick();
                }
            }

            public void onVideoComplete(MBridgeIds mBridgeIds) {
                if (((TTBaseAd) TTMTGRewardVideo.this).mTTAdatperCallback instanceof GMRewardedAdListener) {
                    TTMTGRewardVideo.this.a().onVideoComplete();
                }
            }

            public void onVideoLoadFail(MBridgeIds mBridgeIds, String str) {
                MintegralRewardVideoAdapter.this.d = true;
                if (MintegralRewardVideoAdapter.this.f4107c) {
                    return;
                }
                MintegralRewardVideoAdapter.this.f4107c = true;
                MintegralRewardVideoAdapter.this.notifyAdFailed(AdErrorUtil.obtainAdError(AdError.ERROR_CODE_THIRD_SDK_LOAD_FAIL, str));
            }

            public void onVideoLoadSuccess(MBridgeIds mBridgeIds) {
                TTMTGRewardVideo tTMTGRewardVideo = TTMTGRewardVideo.this;
                MintegralRewardVideoAdapter.this.notifyAdVideoCache(tTMTGRewardVideo, null);
            }
        };

        TTMTGRewardVideo(GMRewardedAdListener gMRewardedAdListener, Map<String, Object> map) {
            this.mTTAdatperCallback = gMRewardedAdListener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public GMRewardedAdListener a() {
            return (GMRewardedAdListener) this.mTTAdatperCallback;
        }

        private void a(String str) {
            MBBidRewardVideoHandler mBBidRewardVideoHandler;
            int i;
            this.f4109b = new MBBidRewardVideoHandler(MintegralRewardVideoAdapter.this.f4105a, "", MintegralRewardVideoAdapter.this.getAdSlotId());
            if (MintegralRewardVideoAdapter.this.f4106b == null || !MintegralRewardVideoAdapter.this.f4106b.isMuted()) {
                mBBidRewardVideoHandler = this.f4109b;
                i = 2;
            } else {
                mBBidRewardVideoHandler = this.f4109b;
                i = 1;
            }
            mBBidRewardVideoHandler.playVideoMute(i);
            this.f4109b.setRewardVideoListener(this.f4110c);
            this.f4109b.loadFromBid(str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b() {
            if (TextUtils.isEmpty(MintegralRewardVideoAdapter.this.getAdm())) {
                Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(MintegralRewardVideoAdapter.this.getAdapterRit(), MintegralRewardVideoAdapter.this.getAdSlotId()) + "loadAdWithoutBid......MintegralRewardVideoAdapter...loadAd start....");
                c();
                return;
            }
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(MintegralRewardVideoAdapter.this.getAdapterRit(), MintegralRewardVideoAdapter.this.getAdSlotId()) + "loadAdWithBid......MintegralRewardVideoAdapter...loadAd start....adm:" + MintegralRewardVideoAdapter.this.getAdm());
            a(MintegralRewardVideoAdapter.this.getAdm());
        }

        private void c() {
            MBRewardVideoHandler mBRewardVideoHandler;
            int i;
            this.f4108a = new MBRewardVideoHandler(MintegralRewardVideoAdapter.this.f4105a, "", MintegralRewardVideoAdapter.this.getAdSlotId());
            if (MintegralRewardVideoAdapter.this.f4106b == null || !MintegralRewardVideoAdapter.this.f4106b.isMuted()) {
                mBRewardVideoHandler = this.f4108a;
                i = 2;
            } else {
                mBRewardVideoHandler = this.f4108a;
                i = 1;
            }
            mBRewardVideoHandler.playVideoMute(i);
            this.f4108a.setRewardVideoListener(this.f4110c);
            this.f4108a.load();
        }

        private void d() {
            if (this.f4109b != null) {
                Logger.e("TTMediationSDK", "MintegralRewardVideoAdapter____showWithBid");
                this.f4109b.showFromBid("1");
            }
        }

        private void e() {
            if (this.f4108a != null) {
                Logger.e("TTMediationSDK", "MintegralRewardVideoAdapter____showWithoutBid");
                this.f4108a.show("1");
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return 7;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return !TextUtils.isEmpty(MintegralRewardVideoAdapter.this.getAdm()) ? this.f4109b == null : this.f4108a == null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isAdCacheReady() {
            return isReady();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isReady() {
            if (MintegralRewardVideoAdapter.this.d) {
                return false;
            }
            if (TextUtils.isEmpty(MintegralRewardVideoAdapter.this.getAdm())) {
                MBRewardVideoHandler mBRewardVideoHandler = this.f4108a;
                if (mBRewardVideoHandler != null) {
                    return mBRewardVideoHandler.isReady();
                }
            } else {
                MBBidRewardVideoHandler mBBidRewardVideoHandler = this.f4109b;
                if (mBBidRewardVideoHandler != null) {
                    return mBBidRewardVideoHandler.isBidReady();
                }
            }
            return super.isReady();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            MBRewardVideoHandler mBRewardVideoHandler = this.f4108a;
            if (mBRewardVideoHandler != null) {
                mBRewardVideoHandler.setRewardVideoListener((g) null);
                this.f4108a.clearVideoCache();
            }
            this.f4108a = null;
            MBBidRewardVideoHandler mBBidRewardVideoHandler = this.f4109b;
            if (mBBidRewardVideoHandler != null) {
                mBBidRewardVideoHandler.setRewardVideoListener((g) null);
                this.f4109b.clearVideoCache();
            }
            this.f4109b = null;
            this.mTTAdatperCallback = null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void showAd(Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map) {
            if (TextUtils.isEmpty(MintegralRewardVideoAdapter.this.getAdm())) {
                e();
            } else {
                d();
            }
        }
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void destroy() {
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getAdNetWorkName() {
        return "mintegral";
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getSdkVersion() {
        return "MAL_15.7.57";
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void loadAd(Context context, Map<String, Object> map) {
        this.f4105a = context;
        this.f4107c = false;
        this.d = false;
        if (map != null) {
            Object obj = map.get("tt_ad_network_callback");
            GMRewardedAdListener gMRewardedAdListener = obj instanceof GMRewardedAdListener ? (GMRewardedAdListener) obj : null;
            this.f4106b = this.mAdSlot.getTTVideoOption();
            new TTMTGRewardVideo(gMRewardedAdListener, map).b();
        }
    }
}
