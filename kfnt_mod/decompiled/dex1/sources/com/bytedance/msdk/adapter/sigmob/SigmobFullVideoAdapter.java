package com.bytedance.msdk.adapter.sigmob;

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
import com.bytedance.msdk.base.TTBaseAd;
import com.sigmob.windad.WindAdError;
import com.sigmob.windad.WindAds;
import com.sigmob.windad.bidding.WindBiddingInterstitialAd;
import com.sigmob.windad.interstitial.WindInterstitialAd;
import com.sigmob.windad.interstitial.WindInterstitialAdListener;
import com.sigmob.windad.interstitial.WindInterstitialAdRequest;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class SigmobFullVideoAdapter extends TTAbsAdLoaderAdapter {
    public static final String TAG = "SigmobFullVideoAdapter";

    /* renamed from: a, reason: collision with root package name */
    private Context f4190a;

    class SigMobFullVideoAd extends TTBaseAd {

        /* renamed from: a, reason: collision with root package name */
        private WindInterstitialAd f4191a;

        /* renamed from: b, reason: collision with root package name */
        private WindBiddingInterstitialAd f4192b;

        /* renamed from: c, reason: collision with root package name */
        private boolean f4193c;
        WindInterstitialAdListener d = new WindInterstitialAdListener() { // from class: com.bytedance.msdk.adapter.sigmob.SigmobFullVideoAdapter.SigMobFullVideoAd.1
            public void onInterstitialAdClicked(String str) {
                if (((TTBaseAd) SigMobFullVideoAd.this).mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener) {
                    SigMobFullVideoAd.this.a().onFullVideoAdClick();
                }
            }

            public void onInterstitialAdClosed(String str) {
                if (((TTBaseAd) SigMobFullVideoAd.this).mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener) {
                    SigMobFullVideoAd.this.a().onFullVideoAdClosed();
                }
            }

            public void onInterstitialAdLoadError(WindAdError windAdError, String str) {
                SigmobFullVideoAdapter sigmobFullVideoAdapter;
                AdError adErrorObtainAdError;
                SigMobFullVideoAd.this.f4193c = false;
                if (windAdError != null) {
                    adErrorObtainAdError = AdErrorUtil.getSigmobError(windAdError.getErrorCode(), windAdError.getMessage());
                    if (adErrorObtainAdError != null) {
                        adErrorObtainAdError.message = windAdError.getMessage();
                    }
                    sigmobFullVideoAdapter = SigmobFullVideoAdapter.this;
                } else {
                    sigmobFullVideoAdapter = SigmobFullVideoAdapter.this;
                    adErrorObtainAdError = AdErrorUtil.obtainAdError(AdError.ERROR_CODE_NO_AD, AdError.AD_NO_FILL);
                }
                sigmobFullVideoAdapter.notifyAdFailed(adErrorObtainAdError);
            }

            public void onInterstitialAdLoadSuccess(String str) {
                SigMobFullVideoAd.this.setExpressAd(true);
                SigMobFullVideoAd.this.f4193c = true;
                SigMobFullVideoAd sigMobFullVideoAd = SigMobFullVideoAd.this;
                SigmobFullVideoAdapter.this.notifyAdVideoCache(sigMobFullVideoAd, null);
            }

            public void onInterstitialAdPlayEnd(String str) {
                if (((TTBaseAd) SigMobFullVideoAd.this).mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener) {
                    SigMobFullVideoAd.this.a().onVideoComplete();
                }
            }

            public void onInterstitialAdPlayError(WindAdError windAdError, String str) {
                if (((TTBaseAd) SigMobFullVideoAd.this).mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener) {
                    SigMobFullVideoAd.this.a().onVideoError();
                }
            }

            public void onInterstitialAdPlayStart(String str) {
                if (((TTBaseAd) SigMobFullVideoAd.this).mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener) {
                    SigMobFullVideoAd.this.a().onFullVideoAdShow();
                }
            }

            public void onInterstitialAdPreLoadFail(String str) {
                SigmobFullVideoAdapter.this.notifyAdFailed(new AdError(AdError.ERROR_CODE_AD_LOAD_FAIL, AdError.AD_LOAD_FAIL_MSG, 0, str));
            }

            public void onInterstitialAdPreLoadSuccess(String str) {
                SigMobFullVideoAd.this.setExpressAd(true);
                SigMobFullVideoAd.this.f4193c = true;
                SigMobFullVideoAd sigMobFullVideoAd = SigMobFullVideoAd.this;
                SigmobFullVideoAdapter.this.notifyAdLoaded(sigMobFullVideoAd);
            }
        };

        SigMobFullVideoAd(ITTAdapterFullVideoAdListener iTTAdapterFullVideoAdListener) {
            this.mTTAdatperCallback = iTTAdapterFullVideoAdListener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ITTAdapterFullVideoAdListener a() {
            return (ITTAdapterFullVideoAdListener) this.mTTAdatperCallback;
        }

        private void a(Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map) {
            try {
                if (!isReady() || this.f4192b == null) {
                    return;
                }
                this.f4192b.show(activity, (HashMap) null);
                Logger.e("TTMediationSDK", "SigmobFullVideoAdapter --- showWithBid");
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

        private void a(String str) {
            if (SigmobFullVideoAdapter.this.f4190a instanceof Activity) {
                this.f4192b = new WindBiddingInterstitialAd((Activity) SigmobFullVideoAdapter.this.f4190a, new WindInterstitialAdRequest(SigmobFullVideoAdapter.this.getAdSlotId(), ((TTAbsAdLoaderAdapter) SigmobFullVideoAdapter.this).mAdSlot.getUserID(), (Map) null));
                this.f4192b.setWindInterstitialAdListener(this.d);
                this.f4192b.loadAd(str);
            }
        }

        private void b() {
            if (SigmobFullVideoAdapter.this.f4190a instanceof Activity) {
                this.f4191a = new WindInterstitialAd((Activity) SigmobFullVideoAdapter.this.f4190a, new WindInterstitialAdRequest(SigmobFullVideoAdapter.this.getAdSlotId(), ((TTAbsAdLoaderAdapter) SigmobFullVideoAdapter.this).mAdSlot.getUserID(), (Map) null));
                this.f4191a.setWindInterstitialAdListener(this.d);
                this.f4191a.loadAd();
            }
        }

        private void b(Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map) {
            try {
                if (!isReady() || this.f4191a == null) {
                    return;
                }
                this.f4191a.show(activity, (HashMap) null);
                Logger.e("TTMediationSDK", "SigmobFullVideoAdapter --- showWithoutBid");
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean adnHasAdVideoCachedApi() {
            return true;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return ((TTAbsAdLoaderAdapter) SigmobFullVideoAdapter.this).mAdSlot.getAdType();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return !TextUtils.isEmpty(SigmobFullVideoAdapter.this.getAdm()) ? this.f4192b == null : this.f4191a == null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isAdCacheReady() {
            return isReady();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isReady() {
            WindInterstitialAd windInterstitialAd;
            WindBiddingInterstitialAd windBiddingInterstitialAd;
            return !TextUtils.isEmpty(SigmobFullVideoAdapter.this.getAdm()) ? this.f4193c && (windBiddingInterstitialAd = this.f4192b) != null && windBiddingInterstitialAd.isReady() : this.f4193c && (windInterstitialAd = this.f4191a) != null && windInterstitialAd.isReady();
        }

        public void loadAd() {
            if (TextUtils.isEmpty(SigmobFullVideoAdapter.this.getAdm())) {
                Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(SigmobFullVideoAdapter.this.getAdapterRit(), SigmobFullVideoAdapter.this.getAdSlotId()) + "loadAdWithoutBid......SigMobFullVideoAdapter...loadAd start....");
                b();
                return;
            }
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(SigmobFullVideoAdapter.this.getAdapterRit(), SigmobFullVideoAdapter.this.getAdSlotId()) + "loadAdWithBid......SigMobFullVideoAdapter...loadAd start....");
            a(SigmobFullVideoAdapter.this.getAdm());
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            WindInterstitialAd windInterstitialAd = this.f4191a;
            if (windInterstitialAd != null) {
                windInterstitialAd.setWindInterstitialAdListener((WindInterstitialAdListener) null);
                this.f4191a = null;
            }
            WindBiddingInterstitialAd windBiddingInterstitialAd = this.f4192b;
            if (windBiddingInterstitialAd != null) {
                windBiddingInterstitialAd.setWindInterstitialAdListener((WindInterstitialAdListener) null);
                this.f4192b = null;
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void showAd(Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map) {
            if (TextUtils.isEmpty(SigmobFullVideoAdapter.this.getAdm())) {
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
        this.f4190a = context;
        if (map != null) {
            Object obj = map.get("tt_ad_network_callback");
            new SigMobFullVideoAd(obj instanceof ITTAdapterFullVideoAdListener ? (ITTAdapterFullVideoAdListener) obj : null).loadAd();
        }
    }
}
