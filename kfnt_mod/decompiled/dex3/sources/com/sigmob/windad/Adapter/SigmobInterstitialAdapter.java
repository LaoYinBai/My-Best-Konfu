package com.sigmob.windad.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.a;
import com.sigmob.sdk.base.common.AdActivity;
import com.sigmob.sdk.base.models.LoadAdRequest;
import com.sigmob.sdk.common.Constants;
import com.sigmob.sdk.common.models.ADStrategy;
import com.sigmob.sdk.common.models.SigmobError;
import com.sigmob.sdk.rewardVideoAd.b;
import com.sigmob.sdk.rewardVideoAd.c;
import com.sigmob.sdk.rewardVideoAd.d;
import com.sigmob.windad.WindAdAdapterError;
import com.sigmob.windad.WindAdRequest;
import com.sigmob.windad.WindAds;
import com.sigmob.windad.base.WindAdAdapter;
import com.sigmob.windad.base.WindAdBaseConnector;
import com.sigmob.windad.base.WindAdConnector;
import com.sigmob.windad.interstitial.WindInterstitialAdRequest;
import com.sigmob.windad.rewardedVideo.WindRewardAdRequest;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SigmobInterstitialAdapter extends WindAdAdapter implements d {
    private c a;
    private WindAdConnector b;
    private Activity c;
    private HashMap<String, ADStrategy> d = new HashMap<>();
    private int e = 3100;
    private LoadAdRequest f;

    private WindAdAdapterError a(int i, String str) {
        return new WindAdAdapterError(i, str);
    }

    @Override // com.sigmob.windad.base.WindAdAdapter
    public void destroy() {
        if (this.a != null) {
            this.a.a();
        }
    }

    @Override // com.sigmob.windad.base.WindAdAdapter
    public int getAdapterVersion() {
        return this.e;
    }

    @Override // com.sigmob.windad.base.WindAdAdapter
    public void initWithAdConnector(WindAdBaseConnector windAdBaseConnector) {
        this.b = (WindAdConnector) windAdBaseConnector;
        this.a = new c();
    }

    @Override // com.sigmob.windad.base.WindAdAdapter
    public void initializeSdk(Context context, ADStrategy aDStrategy) {
        a.a(WindAds.sharedAds().getContext());
        SigmobLog.i(getClass().getName() + "initializeSdk Success");
    }

    @Override // com.sigmob.windad.base.WindAdAdapter
    public boolean isInit() {
        return a.a();
    }

    @Override // com.sigmob.windad.base.WindAdAdapter
    public boolean isReady(ADStrategy aDStrategy) {
        if (this.a != null) {
            return this.a.a(aDStrategy.getPlacement_id());
        }
        return false;
    }

    @Override // com.sigmob.windad.base.WindAdAdapter
    public void loadAd(Activity activity, ViewGroup viewGroup, WindAdRequest windAdRequest, ADStrategy aDStrategy) {
        boolean zIsEnableScreenLockDisPlayAd;
        boolean zIsEnableKeepOn;
        if (windAdRequest instanceof WindInterstitialAdRequest) {
            WindInterstitialAdRequest windInterstitialAdRequest = (WindInterstitialAdRequest) windAdRequest;
            zIsEnableKeepOn = windInterstitialAdRequest.isEnableKeepOn();
            zIsEnableScreenLockDisPlayAd = windInterstitialAdRequest.isEnableScreenLockDisPlayAd();
        } else if (windAdRequest instanceof WindRewardAdRequest) {
            WindRewardAdRequest windRewardAdRequest = (WindRewardAdRequest) windAdRequest;
            zIsEnableKeepOn = windRewardAdRequest.isEnableKeepOn();
            zIsEnableScreenLockDisPlayAd = windRewardAdRequest.isEnableScreenLockDisPlayAd();
        } else {
            zIsEnableScreenLockDisPlayAd = false;
            zIsEnableKeepOn = false;
        }
        this.d.put(aDStrategy.getPlacement_id(), aDStrategy);
        this.f = new LoadAdRequest(aDStrategy.isUseMediation(), aDStrategy.getBidToken(), windAdRequest.getUserId(), aDStrategy.getPlacement_id(), aDStrategy.getAdType(), aDStrategy.getSig_load_id(), windAdRequest.getOptions());
        this.f.setEnable_keep_on(zIsEnableKeepOn);
        this.f.setEnable_screen_lock_displayad(zIsEnableScreenLockDisPlayAd);
        try {
            Object obj = aDStrategy.getOptions().get(Constants.SHOW_DOWNLOAD_DIALOG);
            if (obj == null || !obj.equals("1")) {
                com.sigmob.sdk.common.a.n().f(false);
            } else {
                com.sigmob.sdk.common.a.n().f(true);
            }
            this.a.a(this);
            this.a.a(this.f);
        } catch (Throwable th) {
            if (this.b != null) {
                this.b.adapterDidFailToLoadAd(this, aDStrategy, a(SigmobError.ERROR_SIGMOB_REQUEST.getErrorCode(), th.getMessage()));
            }
        }
    }

    @Override // com.sigmob.windad.LifecycleListener
    public void onCreate(Activity activity) {
        if (activity instanceof AdActivity) {
            this.c = activity;
        }
    }

    @Override // com.sigmob.windad.LifecycleListener
    public void onDestroy(Activity activity) {
        if (activity instanceof AdActivity) {
            this.c = null;
        }
    }

    @Override // com.sigmob.windad.LifecycleListener
    public void onPause(Activity activity) {
    }

    @Override // com.sigmob.windad.LifecycleListener
    public void onResume(Activity activity) {
    }

    @Override // com.sigmob.windad.LifecycleListener
    public void onStart(Activity activity) {
    }

    @Override // com.sigmob.windad.LifecycleListener
    public void onStop(Activity activity) {
    }

    @Override // com.sigmob.sdk.rewardVideoAd.d
    public void onVideoAdClicked(String str) {
        ADStrategy aDStrategy = this.d.get(str);
        SigmobLog.i("onVideoAdClicked() called with: placementId = [" + str + "]");
        if (this.b != null) {
            this.b.adapterDidAdClick(this, aDStrategy);
        }
    }

    @Override // com.sigmob.sdk.rewardVideoAd.d
    public void onVideoAdClosed(b bVar, String str) {
        SigmobLog.i("onVideoAdClosedWithInfo() called with: info = [" + bVar + "], placementId = [" + str + "]");
        if (this.b != null) {
            this.b.adapterDidCloseAd(this, this.d.get(str), bVar.a());
        }
    }

    @Override // com.sigmob.sdk.rewardVideoAd.d
    public void onVideoAdLoadError(int i, String str, String str2) {
        SigmobLog.e("onVideoAdLoadError() called with: error = [" + i + "], placementId = [" + str2 + "]");
        if (this.b != null) {
            this.b.adapterDidFailToLoadAd(this, this.d.get(str2), a(i, str));
        }
    }

    @Override // com.sigmob.sdk.rewardVideoAd.d
    public void onVideoAdLoadSuccess(String str) {
        SigmobLog.i("onVideoAdLoadSuccess() called with: placementId = [" + str + "]");
        ADStrategy aDStrategy = this.d.get(str);
        if (this.b != null) {
            this.b.adapterDidLoadAdSuccessAd(this, aDStrategy);
        }
    }

    @Override // com.sigmob.sdk.rewardVideoAd.d
    public void onVideoAdPlayComplete(String str) {
        SigmobLog.i("onVideoAdPlayComplete() called with: placementId = [" + str + "]");
        ADStrategy aDStrategy = this.d.get(str);
        if (this.b != null) {
            this.b.adapterDidRewardAd(this, aDStrategy);
        }
    }

    @Override // com.sigmob.sdk.rewardVideoAd.d
    public void onVideoAdPlayEnd(String str) {
        SigmobLog.i("onVideoAdPlayEnd() called with: placementId = [" + str + "]");
        ADStrategy aDStrategy = this.d.get(str);
        if (this.b != null) {
            this.b.adapterDidPlayEndAd(this, aDStrategy);
        }
    }

    @Override // com.sigmob.sdk.rewardVideoAd.d
    public void onVideoAdPlayError(int i, String str, String str2) {
        SigmobLog.e("onVideoAdPlayError() called with: error = [" + i + "], placementId = [" + str2 + "]");
        if (this.b != null) {
            this.b.adapterDidFailToPlayingAd(this, this.d.get(str2), a(i, str));
        }
    }

    @Override // com.sigmob.sdk.rewardVideoAd.d
    public void onVideoAdPlayStart(String str) {
        SigmobLog.i("onVideoAdPlayStart() called with: placementId = [" + str + "]");
        ADStrategy aDStrategy = this.d.get(str);
        if (this.b != null) {
            this.b.adapterDidStartPlayingAd(this, aDStrategy);
        }
    }

    @Override // com.sigmob.sdk.rewardVideoAd.d
    public void onVideoAdPreLoadFail(int i, String str, String str2) {
        SigmobLog.i("onVideoAdPreLoadFail() called with: placementId = [" + str2 + "]");
        ADStrategy aDStrategy = this.d.get(str2);
        if (this.b != null) {
            this.b.adapterDidPreLoadFailAd(this, aDStrategy);
        }
    }

    @Override // com.sigmob.sdk.rewardVideoAd.d
    public void onVideoAdPreLoadSuccess(String str) {
        SigmobLog.i("onVideoAdPreLoadSuccess() called with: placementId = [" + str + "]");
        ADStrategy aDStrategy = this.d.get(str);
        if (this.b != null) {
            this.b.adapterDidPreLoadSuccessAd(this, aDStrategy);
        }
    }

    @Override // com.sigmob.windad.base.WindAdAdapter
    public void presentVideoAd(Activity activity, ViewGroup viewGroup, ADStrategy aDStrategy) {
        try {
            this.f.setLoadId(aDStrategy.getSig_load_id());
            this.f.setAdScene(aDStrategy.getAdScene());
            this.a.a(activity, this.f);
        } catch (Throwable th) {
            if (this.b != null) {
                this.b.adapterDidFailToPlayingAd(this, aDStrategy, a(0, th.getMessage()));
            }
        }
    }
}
