package com.sigmob.windad.rewardedVideo;

import android.app.Activity;
import android.os.Handler;
import android.text.TextUtils;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.common.models.ADStrategy;
import com.sigmob.sdk.common.models.AdStatus;
import com.sigmob.sdk.common.mta.PointEntityWindError;
import com.sigmob.windad.WindAdAdapterError;
import com.sigmob.windad.WindAdError;
import com.sigmob.windad.WindAdLifecycleManager;
import com.sigmob.windad.WindAds;
import com.sigmob.windad.base.WindAdRequestController;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class WindRewardedVideoAd implements WindAdRequestController.WindAdInitListener, WindAdRequestController.WindAdRequestLoadLister, WindAdRequestController.WindAdRequestShowLister {
    private WindAdRequestController a;
    public AdStatus adOutStatus = AdStatus.AdStatusNone;
    private WindAdRequestController b;
    private WindRewardedVideoAdListener c;
    private String d;
    private Handler e;
    private WindRewardAdRequest f;

    public WindRewardedVideoAd(Activity activity, WindRewardAdRequest windRewardAdRequest) {
        this.d = "";
        if (activity != null) {
            WindAds.sharedAds().setActivity(activity);
        }
        if (windRewardAdRequest != null && !TextUtils.isEmpty(windRewardAdRequest.getPlacementId())) {
            this.d = windRewardAdRequest.getPlacementId();
        }
        this.f = windRewardAdRequest;
        this.e = WindAds.sharedAds().getHandler();
    }

    private boolean a(WindRewardAdRequest windRewardAdRequest) {
        WindAdRequestController windAdRequestController;
        try {
            if (this.a == null) {
                this.a = new WindAdRequestController(windRewardAdRequest, this, this, this);
                windAdRequestController = this.a;
            } else if (this.adOutStatus == AdStatus.AdStatusPlaying) {
                this.b = new WindAdRequestController(windRewardAdRequest, this, this, this);
                windAdRequestController = this.b;
            } else {
                windAdRequestController = this.a;
            }
            windAdRequestController.loadAd(windRewardAdRequest);
            return true;
        } catch (Throwable th) {
            PointEntityWindError pointEntityWindErrorWindError = PointEntityWindError.WindError("error", WindAdError.ERROR_SIGMOB_REQUEST.getErrorCode(), th.getMessage());
            pointEntityWindErrorWindError.setAdtype(String.valueOf(1));
            pointEntityWindErrorWindError.setPlacement_id(this.d);
            pointEntityWindErrorWindError.commit();
            onVideoAdLoadFail(WindAdError.ERROR_SIGMOB_REQUEST, windRewardAdRequest.getPlacementId());
            return false;
        }
    }

    @Override // com.sigmob.windad.base.WindAdRequestController.WindAdInitListener
    public void adapterDidInitFailWithStrategy(ADStrategy aDStrategy, WindAdAdapterError windAdAdapterError) {
        SigmobLog.i("adapterDidInitFailVideoAdWithStrategy() called with: strategy = [" + aDStrategy.getName() + "], error = [" + windAdAdapterError + "]");
    }

    @Override // com.sigmob.windad.base.WindAdRequestController.WindAdInitListener
    public void adapterDidInitSuccessWithStrategy(ADStrategy aDStrategy) {
        SigmobLog.i("adapterDidInitSuccessVideoAdWithStrategy() called with: strategy = [" + aDStrategy.getName() + "]");
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

    public boolean loadAd() {
        try {
            return a(this.f);
        } catch (Throwable th) {
            PointEntityWindError pointEntityWindErrorWindError = PointEntityWindError.WindError("error", WindAdError.ERROR_SIGMOB_REQUEST.getErrorCode(), th.getMessage());
            pointEntityWindErrorWindError.setAdtype(String.valueOf(1));
            pointEntityWindErrorWindError.setPlacement_id(this.d);
            pointEntityWindErrorWindError.commit();
            onVideoAdLoadFail(WindAdError.ERROR_SIGMOB_REQUEST, this.d);
            return false;
        }
    }

    @Override // com.sigmob.windad.base.WindAdRequestController.WindAdRequestShowLister
    public void onVideoAdClicked(final String str) {
        this.e.post(new Runnable() { // from class: com.sigmob.windad.rewardedVideo.WindRewardedVideoAd.6
            @Override // java.lang.Runnable
            public void run() {
                if (WindRewardedVideoAd.this.c != null) {
                    SigmobLog.i("onVideoAdClicked |" + str);
                    WindRewardedVideoAd.this.c.onVideoAdClicked(str);
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
        this.e.post(new Runnable() { // from class: com.sigmob.windad.rewardedVideo.WindRewardedVideoAd.7
            @Override // java.lang.Runnable
            public void run() {
                if (WindRewardedVideoAd.this.c != null) {
                    SigmobLog.i("onVideoAdClosed " + windRewardInfo.toString() + "|" + str);
                    WindRewardedVideoAd.this.c.onVideoAdClosed(windRewardInfo, str);
                }
            }
        });
    }

    @Override // com.sigmob.windad.base.WindAdRequestController.WindAdRequestLoadLister
    public void onVideoAdLoadFail(final WindAdError windAdError, final String str) {
        this.e.post(new Runnable() { // from class: com.sigmob.windad.rewardedVideo.WindRewardedVideoAd.8
            @Override // java.lang.Runnable
            public void run() {
                if (WindRewardedVideoAd.this.c != null) {
                    SigmobLog.i("onVideoAdLoadFail " + windAdError.toString() + "|" + str);
                    WindRewardedVideoAd.this.c.onVideoAdLoadError(windAdError, str);
                }
            }
        });
    }

    @Override // com.sigmob.windad.base.WindAdRequestController.WindAdRequestLoadLister
    public void onVideoAdLoadStart(String str) {
    }

    @Override // com.sigmob.windad.base.WindAdRequestController.WindAdRequestLoadLister
    public void onVideoAdLoadSuccess(final String str) {
        this.e.post(new Runnable() { // from class: com.sigmob.windad.rewardedVideo.WindRewardedVideoAd.1
            @Override // java.lang.Runnable
            public void run() {
                if (WindRewardedVideoAd.this.c != null) {
                    SigmobLog.i("onVideoAdLoadSuccess |" + str);
                    WindRewardedVideoAd.this.c.onVideoAdLoadSuccess(str);
                }
            }
        });
    }

    @Override // com.sigmob.windad.base.WindAdRequestController.WindAdRequestShowLister
    public void onVideoAdPlayComplete(String str) {
    }

    @Override // com.sigmob.windad.base.WindAdRequestController.WindAdRequestShowLister
    public void onVideoAdPlayEnd(final String str) {
        this.e.post(new Runnable() { // from class: com.sigmob.windad.rewardedVideo.WindRewardedVideoAd.5
            @Override // java.lang.Runnable
            public void run() {
                if (WindRewardedVideoAd.this.c != null) {
                    SigmobLog.i("onVideoAdPlayEnd |" + str);
                    WindRewardedVideoAd.this.c.onVideoAdPlayEnd(str);
                }
            }
        });
    }

    @Override // com.sigmob.windad.base.WindAdRequestController.WindAdRequestShowLister
    public void onVideoAdPlayError(final WindAdError windAdError, final String str) {
        this.e.post(new Runnable() { // from class: com.sigmob.windad.rewardedVideo.WindRewardedVideoAd.9
            @Override // java.lang.Runnable
            public void run() {
                if (WindRewardedVideoAd.this.c != null) {
                    SigmobLog.i("onVideoAdPlayError " + windAdError + "|" + str);
                    WindRewardedVideoAd.this.c.onVideoAdPlayError(windAdError, str);
                }
            }
        });
    }

    @Override // com.sigmob.windad.base.WindAdRequestController.WindAdRequestShowLister
    public void onVideoAdPlayStart(final String str) {
        this.adOutStatus = AdStatus.AdStatusPlaying;
        this.e.post(new Runnable() { // from class: com.sigmob.windad.rewardedVideo.WindRewardedVideoAd.4
            @Override // java.lang.Runnable
            public void run() {
                if (WindRewardedVideoAd.this.c != null) {
                    SigmobLog.i("onVideoAdPlayStart |" + str);
                    WindRewardedVideoAd.this.c.onVideoAdPlayStart(str);
                }
            }
        });
    }

    @Override // com.sigmob.windad.base.WindAdRequestController.WindAdRequestLoadLister
    public void onVideoAdPreLoadFail(final String str) {
        this.e.post(new Runnable() { // from class: com.sigmob.windad.rewardedVideo.WindRewardedVideoAd.3
            @Override // java.lang.Runnable
            public void run() {
                if (WindRewardedVideoAd.this.c != null) {
                    SigmobLog.i("onVideoAdPreLoadFail  " + str);
                    WindRewardedVideoAd.this.c.onVideoAdPreLoadFail(str);
                }
            }
        });
    }

    @Override // com.sigmob.windad.base.WindAdRequestController.WindAdRequestLoadLister
    public void onVideoAdPreLoadSuccess(final String str) {
        this.e.post(new Runnable() { // from class: com.sigmob.windad.rewardedVideo.WindRewardedVideoAd.2
            @Override // java.lang.Runnable
            public void run() {
                if (WindRewardedVideoAd.this.c != null) {
                    SigmobLog.i("onVideoAdPreLoadSuccess |" + str);
                    WindRewardedVideoAd.this.c.onVideoAdPreLoadSuccess(str);
                }
            }
        });
    }

    public void setWindRewardedVideoAdListener(WindRewardedVideoAdListener windRewardedVideoAdListener) {
        this.c = windRewardedVideoAdListener;
    }

    public boolean show(Activity activity, HashMap<String, String> map) {
        try {
        } catch (Throwable th) {
            PointEntityWindError pointEntityWindErrorWindError = PointEntityWindError.WindError("error", WindAdError.ERROR_SIGMOB_REQUEST.getErrorCode(), th.getMessage());
            pointEntityWindErrorWindError.setAdtype(String.valueOf(1));
            pointEntityWindErrorWindError.setPlacement_id(this.d);
            pointEntityWindErrorWindError.commit();
            SigmobLog.e("show Ad ", th);
        }
        if (activity == null) {
            SigmobLog.e("activity can't is null");
            PointEntityWindError pointEntityWindErrorWindError2 = PointEntityWindError.WindError("error", WindAdError.ERROR_SIGMOB_AD_PLAY.getErrorCode(), "activity can't is null");
            pointEntityWindErrorWindError2.setAdtype(String.valueOf(1));
            pointEntityWindErrorWindError2.setPlacement_id(this.d);
            pointEntityWindErrorWindError2.commit();
            onVideoAdPlayError(WindAdError.ERROR_SIGMOB_AD_PLAY, this.d);
            return false;
        }
        WindAds.sharedAds().setActivity(activity);
        if (WindAdLifecycleManager.getInstance() == null) {
            WindAdLifecycleManager.initialize(activity.getApplication());
        }
        if (this.adOutStatus == AdStatus.AdStatusPlaying) {
            onVideoAdPlayError(WindAdError.ERROR_AD_LOAD_FAIL_PLAYING, this.d);
            return false;
        }
        if (this.a != null) {
            this.a.showAd(activity, map);
            return true;
        }
        SigmobLog.e("can't find  controller object");
        PointEntityWindError pointEntityWindErrorWindError3 = PointEntityWindError.WindError("error", WindAdError.ERROR_SIGMOB_NOT_READY.getErrorCode(), "can't find  controller object");
        pointEntityWindErrorWindError3.setAdtype(String.valueOf(1));
        pointEntityWindErrorWindError3.setPlacement_id(this.d);
        pointEntityWindErrorWindError3.commit();
        onVideoAdPlayError(WindAdError.ERROR_SIGMOB_NOT_READY, this.d);
        return false;
    }
}
