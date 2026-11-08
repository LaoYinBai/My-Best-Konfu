package com.bytedance.msdk.adapter.mintegral;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.listener.ITTAdapterFullVideoAdListener;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdErrorUtil;
import com.bytedance.msdk.api.TTAdConstant;
import com.bytedance.msdk.api.TTVideoOption;
import com.bytedance.msdk.base.TTBaseAd;
import com.mbridge.msdk.interstitialvideo.out.InterstitialVideoListener;
import com.mbridge.msdk.interstitialvideo.out.MBBidInterstitialVideoHandler;
import com.mbridge.msdk.interstitialvideo.out.MBInterstitialVideoHandler;
import com.mbridge.msdk.out.MBridgeIds;
import com.mbridge.msdk.out.RewardInfo;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class MintegralFullVideoAdapter extends TTAbsAdLoaderAdapter {

    /* renamed from: a, reason: collision with root package name */
    private MtFullVideoAd f4080a;

    /* renamed from: b, reason: collision with root package name */
    private Context f4081b;

    /* renamed from: c, reason: collision with root package name */
    private TTVideoOption f4082c;
    private boolean d = false;
    private boolean e = false;

    class MtFullVideoAd extends TTBaseAd {

        /* renamed from: a, reason: collision with root package name */
        private MBInterstitialVideoHandler f4083a;

        /* renamed from: b, reason: collision with root package name */
        private MBBidInterstitialVideoHandler f4084b;

        /* renamed from: c, reason: collision with root package name */
        InterstitialVideoListener f4085c = new InterstitialVideoListener() { // from class: com.bytedance.msdk.adapter.mintegral.MintegralFullVideoAdapter.MtFullVideoAd.1
            public void onAdClose(MBridgeIds mBridgeIds, RewardInfo rewardInfo) {
                if (((TTBaseAd) MtFullVideoAd.this).mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener) {
                    MtFullVideoAd.this.a().onFullVideoAdClosed();
                }
            }

            public void onAdCloseWithIVReward(MBridgeIds mBridgeIds, RewardInfo rewardInfo) {
            }

            public void onAdShow(MBridgeIds mBridgeIds) {
                if (((TTBaseAd) MtFullVideoAd.this).mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener) {
                    MtFullVideoAd.this.a().onFullVideoAdShow();
                }
            }

            public void onEndcardShow(MBridgeIds mBridgeIds) {
            }

            public void onLoadSuccess(MBridgeIds mBridgeIds) {
                if (MintegralFullVideoAdapter.this.d) {
                    return;
                }
                MintegralFullVideoAdapter.this.d = true;
                MtFullVideoAd mtFullVideoAd = MtFullVideoAd.this;
                MintegralFullVideoAdapter.this.notifyAdLoaded(mtFullVideoAd);
            }

            public void onShowFail(MBridgeIds mBridgeIds, String str) {
                if (((TTBaseAd) MtFullVideoAd.this).mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener) {
                    MtFullVideoAd.this.a().onFullVideoAdShowFail(new AdError(AdError.ERROR_CODE_ADN_SHOW_FAIL, str, AdError.ERROR_CODE_ADN_SHOW_FAIL, str));
                }
            }

            public void onVideoAdClicked(MBridgeIds mBridgeIds) {
                if (((TTBaseAd) MtFullVideoAd.this).mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener) {
                    MtFullVideoAd.this.a().onFullVideoAdClick();
                }
            }

            public void onVideoComplete(MBridgeIds mBridgeIds) {
                if (((TTBaseAd) MtFullVideoAd.this).mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener) {
                    MtFullVideoAd.this.a().onVideoComplete();
                }
            }

            public void onVideoLoadFail(MBridgeIds mBridgeIds, String str) {
                MintegralFullVideoAdapter.this.e = true;
                if (MintegralFullVideoAdapter.this.d) {
                    return;
                }
                MintegralFullVideoAdapter.this.d = true;
                MintegralFullVideoAdapter.this.notifyAdFailed(AdErrorUtil.obtainAdError(AdError.ERROR_CODE_THIRD_SDK_LOAD_FAIL, str));
            }

            public void onVideoLoadSuccess(MBridgeIds mBridgeIds) {
                MtFullVideoAd mtFullVideoAd = MtFullVideoAd.this;
                MintegralFullVideoAdapter.this.notifyAdVideoCache(mtFullVideoAd, null);
            }
        };

        MtFullVideoAd(ITTAdapterFullVideoAdListener iTTAdapterFullVideoAdListener) {
            this.mTTAdatperCallback = iTTAdapterFullVideoAdListener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ITTAdapterFullVideoAdListener a() {
            return (ITTAdapterFullVideoAdListener) this.mTTAdatperCallback;
        }

        private void a(String str) {
            MBBidInterstitialVideoHandler mBBidInterstitialVideoHandler;
            int i;
            this.f4084b = new MBBidInterstitialVideoHandler(MintegralFullVideoAdapter.this.f4081b, "", MintegralFullVideoAdapter.this.getAdSlotId());
            if (MintegralFullVideoAdapter.this.f4082c == null || !MintegralFullVideoAdapter.this.f4082c.isMuted()) {
                mBBidInterstitialVideoHandler = this.f4084b;
                i = 2;
            } else {
                mBBidInterstitialVideoHandler = this.f4084b;
                i = 1;
            }
            mBBidInterstitialVideoHandler.playVideoMute(i);
            this.f4084b.setInterstitialVideoListener(this.f4085c);
            this.f4084b.loadFromBid(str);
        }

        private void b() {
            MBInterstitialVideoHandler mBInterstitialVideoHandler;
            int i;
            this.f4083a = new MBInterstitialVideoHandler(MintegralFullVideoAdapter.this.f4081b, "", MintegralFullVideoAdapter.this.getAdSlotId());
            if (MintegralFullVideoAdapter.this.f4082c == null || !MintegralFullVideoAdapter.this.f4082c.isMuted()) {
                mBInterstitialVideoHandler = this.f4083a;
                i = 2;
            } else {
                mBInterstitialVideoHandler = this.f4083a;
                i = 1;
            }
            mBInterstitialVideoHandler.playVideoMute(i);
            this.f4083a.setInterstitialVideoListener(this.f4085c);
            this.f4083a.load();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return ((TTAbsAdLoaderAdapter) MintegralFullVideoAdapter.this).mAdSlot.getAdType();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return !TextUtils.isEmpty(MintegralFullVideoAdapter.this.getAdm()) ? this.f4084b == null : this.f4083a == null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isAdCacheReady() {
            return isReady();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isReady() {
            if (MintegralFullVideoAdapter.this.e) {
                return false;
            }
            if (TextUtils.isEmpty(MintegralFullVideoAdapter.this.getAdm())) {
                MBInterstitialVideoHandler mBInterstitialVideoHandler = this.f4083a;
                if (mBInterstitialVideoHandler != null) {
                    return mBInterstitialVideoHandler.isReady();
                }
            } else {
                MBBidInterstitialVideoHandler mBBidInterstitialVideoHandler = this.f4084b;
                if (mBBidInterstitialVideoHandler != null) {
                    return mBBidInterstitialVideoHandler.isBidReady();
                }
            }
            return false;
        }

        public void loadAd() {
            if (TextUtils.isEmpty(MintegralFullVideoAdapter.this.getAdm())) {
                Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(MintegralFullVideoAdapter.this.getAdapterRit(), MintegralFullVideoAdapter.this.getAdSlotId()) + "loadAdWithoutBid......MintegralFullVideoAdapter...loadAd start....");
                b();
                return;
            }
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(MintegralFullVideoAdapter.this.getAdapterRit(), MintegralFullVideoAdapter.this.getAdSlotId()) + "loadAdWithBid......MintegralFullVideoAdapter...loadAd start....");
            a(MintegralFullVideoAdapter.this.getAdm());
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            MBInterstitialVideoHandler mBInterstitialVideoHandler = this.f4083a;
            if (mBInterstitialVideoHandler != null) {
                mBInterstitialVideoHandler.setInterstitialVideoListener((InterstitialVideoListener) null);
                this.f4083a.clearVideoCache();
            }
            this.f4083a = null;
            MBBidInterstitialVideoHandler mBBidInterstitialVideoHandler = this.f4084b;
            if (mBBidInterstitialVideoHandler != null) {
                mBBidInterstitialVideoHandler.setInterstitialVideoListener((InterstitialVideoListener) null);
                this.f4084b.clearVideoCache();
            }
            this.f4084b = null;
            this.mTTAdatperCallback = null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void showAd(Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map) {
            if (TextUtils.isEmpty(MintegralFullVideoAdapter.this.getAdm())) {
                MBInterstitialVideoHandler mBInterstitialVideoHandler = this.f4083a;
                if (mBInterstitialVideoHandler != null) {
                    mBInterstitialVideoHandler.show();
                    return;
                }
                return;
            }
            MBBidInterstitialVideoHandler mBBidInterstitialVideoHandler = this.f4084b;
            if (mBBidInterstitialVideoHandler != null) {
                mBBidInterstitialVideoHandler.showFromBid();
            }
        }
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void destroy() {
        MtFullVideoAd mtFullVideoAd = this.f4080a;
        if (mtFullVideoAd != null) {
            mtFullVideoAd.onDestroy();
        }
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
        this.f4081b = context;
        this.d = false;
        this.e = false;
        if (map != null) {
            Object obj = map.get("tt_ad_network_callback");
            ITTAdapterFullVideoAdListener iTTAdapterFullVideoAdListener = obj instanceof ITTAdapterFullVideoAdListener ? (ITTAdapterFullVideoAdListener) obj : null;
            this.f4082c = this.mAdSlot.getTTVideoOption();
            this.f4080a = new MtFullVideoAd(iTTAdapterFullVideoAdListener);
            this.f4080a.loadAd();
        }
    }
}
