package com.sigmob.windad.bidding;

import android.app.Activity;
import android.os.Handler;
import android.text.TextUtils;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.common.models.ADStrategy;
import com.sigmob.sdk.common.models.AdStatus;
import com.sigmob.sdk.common.mta.PointEntityWindError;
import com.sigmob.windad.WindAdAdapterError;
import com.sigmob.windad.WindAdError;
import com.sigmob.windad.WindAds;
import com.sigmob.windad.base.WindAdRequestController;
import com.sigmob.windad.interstitial.WindInterstitialAdListener;
import com.sigmob.windad.interstitial.WindInterstitialAdRequest;
import com.sigmob.windad.rewardedVideo.WindRewardInfo;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class WindBiddingInterstitialAd implements WindAdRequestController.WindAdInitListener, WindAdRequestController.WindAdRequestLoadLister, WindAdRequestController.WindAdRequestShowLister {
    private WindAdRequestController a;
    public AdStatus adOutStatus = AdStatus.AdStatusNone;
    private WindAdRequestController b;
    private WindInterstitialAdListener c;
    private String d;
    private Handler e;
    private WindInterstitialAdRequest f;

    public WindBiddingInterstitialAd(Activity activity, WindInterstitialAdRequest windInterstitialAdRequest) {
        this.d = "";
        if (activity != null) {
            WindAds.sharedAds().setActivity(activity);
        }
        if (windInterstitialAdRequest != null && !TextUtils.isEmpty(windInterstitialAdRequest.getPlacementId())) {
            this.d = windInterstitialAdRequest.getPlacementId();
        }
        this.f = windInterstitialAdRequest;
        this.e = WindAds.sharedAds().getHandler();
    }

    private boolean a(WindInterstitialAdRequest windInterstitialAdRequest) {
        WindAdRequestController windAdRequestController;
        try {
            if (this.a == null) {
                this.a = new WindAdRequestController(windInterstitialAdRequest, this, this, this);
                windAdRequestController = this.a;
            } else if (this.adOutStatus == AdStatus.AdStatusPlaying) {
                this.b = new WindAdRequestController(windInterstitialAdRequest, this, this, this);
                windAdRequestController = this.b;
            } else {
                windAdRequestController = this.a;
            }
            windAdRequestController.loadAd(windInterstitialAdRequest);
            return true;
        } catch (Throwable th) {
            PointEntityWindError pointEntityWindErrorWindError = PointEntityWindError.WindError("error", WindAdError.ERROR_SIGMOB_REQUEST.getErrorCode(), th.getMessage());
            pointEntityWindErrorWindError.setAdtype(String.valueOf(4));
            pointEntityWindErrorWindError.setPlacement_id(this.d);
            pointEntityWindErrorWindError.commit();
            SigmobLog.e("load Ad error", th);
            onVideoAdLoadFail(WindAdError.ERROR_SIGMOB_REQUEST, windInterstitialAdRequest.getPlacementId());
            return false;
        }
    }

    @Override // com.sigmob.windad.base.WindAdRequestController.WindAdInitListener
    public void adapterDidInitFailWithStrategy(ADStrategy aDStrategy, WindAdAdapterError windAdAdapterError) {
        SigmobLog.i("adapterDidInitFailWithStrategy() called with: strategy = [" + aDStrategy.getName() + "], error = [" + windAdAdapterError + "]");
    }

    @Override // com.sigmob.windad.base.WindAdRequestController.WindAdInitListener
    public void adapterDidInitSuccessWithStrategy(ADStrategy aDStrategy) {
        SigmobLog.i("adapterDidInitSuccessWithStrategy() called with: strategy = [" + aDStrategy.getName() + "]");
    }

    public void destroy() {
        if (this.a != null) {
            this.a.destroy();
        }
        if (this.c != null) {
            this.c = null;
        }
    }

    public boolean isReady() {
        if (this.a != null) {
            return this.a.isReady();
        }
        return false;
    }

    public boolean loadAd(String str) {
        try {
            if (!WindAds.sharedAds().isInit()) {
                SigmobLog.e("WindAds not initialize");
                PointEntityWindError pointEntityWindErrorWindError = PointEntityWindError.WindError("error", WindAdError.ERROR_SIGMOB_NOT_INIT.getErrorCode(), "WindAds not initialize");
                pointEntityWindErrorWindError.setAdtype(String.valueOf(4));
                pointEntityWindErrorWindError.setPlacement_id(this.d);
                pointEntityWindErrorWindError.commit();
                onVideoAdLoadFail(WindAdError.ERROR_SIGMOB_NOT_INIT, this.d);
                return false;
            }
            if (this.f != null && !TextUtils.isEmpty(this.f.getPlacementId())) {
                if (!TextUtils.isEmpty(str)) {
                    this.f.setBidToken(str);
                    return a(this.f);
                }
                PointEntityWindError pointEntityWindErrorWindError2 = PointEntityWindError.WindError("error", WindAdError.ERROR_SIGMOB_BID_TOKEN_IS_EMPTY.getErrorCode(), "bidToken can't is null");
                pointEntityWindErrorWindError2.setAdtype(String.valueOf(4));
                pointEntityWindErrorWindError2.commit();
                SigmobLog.e("BidToken with WindAdRequest can't is null");
                onVideoAdLoadFail(WindAdError.ERROR_SIGMOB_BID_TOKEN_IS_EMPTY, this.d);
                return false;
            }
            PointEntityWindError pointEntityWindErrorWindError3 = PointEntityWindError.WindError("error", WindAdError.ERROR_SIGMOB_PLACEMNT_ID_IS_EMPTY.getErrorCode(), "WindVideoAdRequest can't is null");
            pointEntityWindErrorWindError3.setAdtype(String.valueOf(4));
            pointEntityWindErrorWindError3.commit();
            SigmobLog.e("PlacementId with WindAdRequest can't is null");
            onVideoAdLoadFail(WindAdError.ERROR_SIGMOB_PLACEMNT_ID_IS_EMPTY, this.d);
            return false;
        } catch (Throwable th) {
            SigmobLog.e("load Ad error", th);
            PointEntityWindError pointEntityWindErrorWindError4 = PointEntityWindError.WindError("error", WindAdError.ERROR_SIGMOB_REQUEST.getErrorCode(), th.getMessage());
            pointEntityWindErrorWindError4.setAdtype(String.valueOf(4));
            pointEntityWindErrorWindError4.setPlacement_id(this.d);
            pointEntityWindErrorWindError4.commit();
            onVideoAdLoadFail(WindAdError.ERROR_SIGMOB_REQUEST, this.d);
            return false;
        }
    }

    public void onInterstitialAdPlayError(final WindAdError windAdError, final String str) {
        this.e.post(new Runnable() { // from class: com.sigmob.windad.bidding.WindBiddingInterstitialAd.10
            @Override // java.lang.Runnable
            public void run() {
                if (WindBiddingInterstitialAd.this.c != null) {
                    SigmobLog.i("onInterstitialAdPlayError " + windAdError + "|" + str);
                    WindBiddingInterstitialAd.this.c.onInterstitialAdPlayError(windAdError, str);
                }
            }
        });
    }

    @Override // com.sigmob.windad.base.WindAdRequestController.WindAdRequestShowLister
    public void onVideoAdClicked(final String str) {
        this.e.post(new Runnable() { // from class: com.sigmob.windad.bidding.WindBiddingInterstitialAd.6
            @Override // java.lang.Runnable
            public void run() {
                if (WindBiddingInterstitialAd.this.c != null) {
                    SigmobLog.i("onVideoAdClicked |" + str);
                    WindBiddingInterstitialAd.this.c.onInterstitialAdClicked(str);
                }
            }
        });
    }

    @Override // com.sigmob.windad.base.WindAdRequestController.WindAdRequestShowLister
    public void onVideoAdClosed(final WindRewardInfo windRewardInfo, final String str) {
        this.adOutStatus = AdStatus.AdStatusClose;
        if (this.b != null) {
            if (this.a != null) {
                this.a.destroy();
            }
            this.a = this.b;
            this.b = null;
        }
        this.e.post(new Runnable() { // from class: com.sigmob.windad.bidding.WindBiddingInterstitialAd.7
            @Override // java.lang.Runnable
            public void run() {
                if (WindBiddingInterstitialAd.this.c != null) {
                    SigmobLog.i("onVideoAdClosed " + windRewardInfo.toString() + "|" + str);
                    WindBiddingInterstitialAd.this.c.onInterstitialAdClosed(str);
                }
            }
        });
    }

    @Override // com.sigmob.windad.base.WindAdRequestController.WindAdRequestLoadLister
    public void onVideoAdLoadFail(final WindAdError windAdError, final String str) {
        this.e.post(new Runnable() { // from class: com.sigmob.windad.bidding.WindBiddingInterstitialAd.9
            @Override // java.lang.Runnable
            public void run() {
                if (WindBiddingInterstitialAd.this.c != null) {
                    SigmobLog.i("onVideoAdLoadFail " + windAdError + "|" + str);
                    WindBiddingInterstitialAd.this.c.onInterstitialAdLoadError(windAdError, str);
                }
            }
        });
    }

    @Override // com.sigmob.windad.base.WindAdRequestController.WindAdRequestLoadLister
    public void onVideoAdLoadStart(String str) {
    }

    @Override // com.sigmob.windad.base.WindAdRequestController.WindAdRequestLoadLister
    public void onVideoAdLoadSuccess(final String str) {
        this.e.post(new Runnable() { // from class: com.sigmob.windad.bidding.WindBiddingInterstitialAd.1
            @Override // java.lang.Runnable
            public void run() {
                if (WindBiddingInterstitialAd.this.c != null) {
                    SigmobLog.i("onVideoAdLoadSuccess |" + str);
                    WindBiddingInterstitialAd.this.c.onInterstitialAdLoadSuccess(str);
                }
            }
        });
    }

    @Override // com.sigmob.windad.base.WindAdRequestController.WindAdRequestShowLister
    public void onVideoAdPlayComplete(String str) {
    }

    @Override // com.sigmob.windad.base.WindAdRequestController.WindAdRequestShowLister
    public void onVideoAdPlayEnd(final String str) {
        this.e.post(new Runnable() { // from class: com.sigmob.windad.bidding.WindBiddingInterstitialAd.5
            @Override // java.lang.Runnable
            public void run() {
                if (WindBiddingInterstitialAd.this.c != null) {
                    SigmobLog.i("onVideoAdPlayEnd |" + str);
                    WindBiddingInterstitialAd.this.c.onInterstitialAdPlayEnd(str);
                }
            }
        });
    }

    @Override // com.sigmob.windad.base.WindAdRequestController.WindAdRequestShowLister
    public void onVideoAdPlayError(final WindAdError windAdError, final String str) {
        this.e.post(new Runnable() { // from class: com.sigmob.windad.bidding.WindBiddingInterstitialAd.8
            @Override // java.lang.Runnable
            public void run() {
                if (WindBiddingInterstitialAd.this.c != null) {
                    SigmobLog.i("onVideoAdPlayError " + windAdError + "|" + str);
                    WindBiddingInterstitialAd.this.c.onInterstitialAdPlayError(windAdError, str);
                }
            }
        });
    }

    @Override // com.sigmob.windad.base.WindAdRequestController.WindAdRequestShowLister
    public void onVideoAdPlayStart(final String str) {
        this.adOutStatus = AdStatus.AdStatusPlaying;
        this.e.post(new Runnable() { // from class: com.sigmob.windad.bidding.WindBiddingInterstitialAd.4
            @Override // java.lang.Runnable
            public void run() {
                if (WindBiddingInterstitialAd.this.c != null) {
                    SigmobLog.i("onVideoAdPlayStart |" + str);
                    WindBiddingInterstitialAd.this.c.onInterstitialAdPlayStart(str);
                }
            }
        });
    }

    @Override // com.sigmob.windad.base.WindAdRequestController.WindAdRequestLoadLister
    public void onVideoAdPreLoadFail(final String str) {
        this.e.post(new Runnable() { // from class: com.sigmob.windad.bidding.WindBiddingInterstitialAd.3
            @Override // java.lang.Runnable
            public void run() {
                if (WindBiddingInterstitialAd.this.c != null) {
                    SigmobLog.i("onVideoAdPreLoadFail  " + str);
                    WindBiddingInterstitialAd.this.c.onInterstitialAdPreLoadFail(str);
                }
            }
        });
    }

    @Override // com.sigmob.windad.base.WindAdRequestController.WindAdRequestLoadLister
    public void onVideoAdPreLoadSuccess(final String str) {
        this.e.post(new Runnable() { // from class: com.sigmob.windad.bidding.WindBiddingInterstitialAd.2
            @Override // java.lang.Runnable
            public void run() {
                if (WindBiddingInterstitialAd.this.c != null) {
                    SigmobLog.i("onVideoAdPreLoadSuccess |" + str);
                    WindBiddingInterstitialAd.this.c.onInterstitialAdPreLoadSuccess(str);
                }
            }
        });
    }

    public void setWindInterstitialAdListener(WindInterstitialAdListener windInterstitialAdListener) {
        this.c = windInterstitialAdListener;
    }

    public boolean show(Activity activity, HashMap<String, String> map) {
        try {
            if (activity == null) {
                SigmobLog.e("activity can't is null");
                PointEntityWindError pointEntityWindErrorWindError = PointEntityWindError.WindError("error", WindAdError.ERROR_SIGMOB_AD_PLAY.getErrorCode(), "activity can't is null");
                pointEntityWindErrorWindError.setAdtype(String.valueOf(4));
                pointEntityWindErrorWindError.setPlacement_id(this.d);
                pointEntityWindErrorWindError.commit();
                onVideoAdPlayError(WindAdError.ERROR_SIGMOB_AD_PLAY, this.d);
                return false;
            }
            WindAds.sharedAds().setActivity(activity);
            if (this.adOutStatus == AdStatus.AdStatusPlaying) {
                onVideoAdPlayError(WindAdError.ERROR_AD_LOAD_FAIL_PLAYING, this.d);
                return false;
            }
            if (this.a != null) {
                this.a.showAd(activity, map);
                return true;
            }
            SigmobLog.e("can't find  controller object");
            PointEntityWindError pointEntityWindErrorWindError2 = PointEntityWindError.WindError("error", WindAdError.ERROR_SIGMOB_NOT_READY.getErrorCode(), "can't find  controller object");
            pointEntityWindErrorWindError2.setAdtype(String.valueOf(4));
            pointEntityWindErrorWindError2.setPlacement_id(this.d);
            pointEntityWindErrorWindError2.commit();
            onInterstitialAdPlayError(WindAdError.ERROR_SIGMOB_NOT_READY, this.d);
            return false;
        } catch (Throwable th) {
            PointEntityWindError pointEntityWindErrorWindError3 = PointEntityWindError.WindError("error", WindAdError.ERROR_SIGMOB_REQUEST.getErrorCode(), th.getMessage());
            pointEntityWindErrorWindError3.setAdtype(String.valueOf(4));
            pointEntityWindErrorWindError3.setPlacement_id(this.d);
            pointEntityWindErrorWindError3.commit();
            SigmobLog.e("show Ad error", th);
            return true;
        }
    }
}
