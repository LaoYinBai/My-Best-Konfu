package com.sigmob.windad.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.a;
import com.sigmob.sdk.base.models.LoadAdRequest;
import com.sigmob.sdk.common.Constants;
import com.sigmob.sdk.common.models.ADStrategy;
import com.sigmob.sdk.common.models.SigmobError;
import com.sigmob.sdk.common.mta.PointCategory;
import com.sigmob.sdk.common.mta.PointEntitySigmobError;
import com.sigmob.sdk.splash.b;
import com.sigmob.sdk.splash.c;
import com.sigmob.windad.WindAdAdapterError;
import com.sigmob.windad.WindAdRequest;
import com.sigmob.windad.WindAds;
import com.sigmob.windad.base.WindAdAdapter;
import com.sigmob.windad.base.WindAdBaseConnector;
import com.sigmob.windad.base.WindAdConnector;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SigmobSplashAdAdapter extends WindAdAdapter implements b {
    private WindAdConnector a;
    private c b;
    private int c = 3100;
    private ADStrategy d;

    private WindAdAdapterError a(int i, String str) {
        return new WindAdAdapterError(i, str);
    }

    @Override // com.sigmob.windad.base.WindAdAdapter
    public void destroy() {
    }

    @Override // com.sigmob.windad.base.WindAdAdapter
    public int getAdapterVersion() {
        return this.c;
    }

    @Override // com.sigmob.windad.base.WindAdAdapter
    public void initWithAdConnector(WindAdBaseConnector windAdBaseConnector) {
        this.a = (WindAdConnector) windAdBaseConnector;
    }

    @Override // com.sigmob.windad.base.WindAdAdapter
    public void initializeSdk(Context context, ADStrategy aDStrategy) {
        this.d = aDStrategy;
        if (!a.a()) {
            a.a(WindAds.sharedAds().getContext());
        }
        SigmobLog.i(getClass().getName() + "initializeSdk Success");
    }

    @Override // com.sigmob.windad.base.WindAdAdapter
    public boolean isInit() {
        return a.a();
    }

    @Override // com.sigmob.windad.base.WindAdAdapter
    public boolean isReady(ADStrategy aDStrategy) {
        if (this.b != null) {
            return this.b.b();
        }
        return false;
    }

    @Override // com.sigmob.windad.base.WindAdAdapter
    public void loadAd(Activity activity, ViewGroup viewGroup, WindAdRequest windAdRequest, ADStrategy aDStrategy) {
        SigmobError sigmobErrorC;
        try {
            this.d = aDStrategy;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (activity != null && (sigmobErrorC = a.b().c()) != null) {
            SigmobLog.e("check loadAd error " + sigmobErrorC.toString());
            PointEntitySigmobError.SigmobError(PointCategory.LOAD, sigmobErrorC.getErrorCode(), sigmobErrorC.getMessage()).commit();
            if (this.a != null) {
                this.a.adapterDidFailToLoadAd(this, aDStrategy, a(sigmobErrorC.getErrorCode(), sigmobErrorC.getMessage()));
                return;
            }
            return;
        }
        Object obj = aDStrategy.getOptions().get(Constants.SHOW_DOWNLOAD_DIALOG);
        if (obj == null || !obj.equals("1")) {
            com.sigmob.sdk.common.a.n().f(false);
        } else {
            com.sigmob.sdk.common.a.n().f(true);
        }
        SigmobLog.i(getClass().getName() + "loadAd: " + aDStrategy.getPlacement_id());
        this.b = new c(activity, new LoadAdRequest(aDStrategy.isUseMediation(), aDStrategy.getBidToken(), windAdRequest.getUserId(), aDStrategy.getPlacement_id(), aDStrategy.getAdType(), windAdRequest.getLoadId(), windAdRequest.getOptions()), this);
        if (this.b.a() || this.a == null) {
            return;
        }
        this.a.adapterDidFailToLoadAd(this, aDStrategy, a(0, "sigmob load adManager is null"));
    }

    @Override // com.sigmob.windad.LifecycleListener
    public void onCreate(Activity activity) {
    }

    @Override // com.sigmob.windad.LifecycleListener
    public void onDestroy(Activity activity) {
        if (this.b != null) {
            this.b.c(activity);
        }
    }

    @Override // com.sigmob.windad.LifecycleListener
    public void onPause(Activity activity) {
        if (this.b != null) {
            this.b.a(activity);
        }
    }

    @Override // com.sigmob.windad.LifecycleListener
    public void onResume(Activity activity) {
        if (this.b != null) {
            this.b.b(activity);
        }
    }

    @Override // com.sigmob.sdk.splash.b
    public void onSplashAdClicked() {
        if (this.a != null) {
            this.a.adapterDidAdClick(this, this.d);
        }
    }

    @Override // com.sigmob.sdk.splash.b
    public void onSplashAdFailToPresent(int i, String str) {
        if (this.a != null) {
            this.a.adapterDidFailToPlayingAd(this, this.d, a(i, str));
        }
    }

    @Override // com.sigmob.sdk.splash.b
    public void onSplashAdLoadFail(int i, String str) {
        if (this.a != null) {
            this.a.adapterDidFailToLoadAd(this, this.d, a(i, str));
        }
    }

    @Override // com.sigmob.sdk.splash.b
    public void onSplashAdLoadSuccess() {
        if (this.a != null) {
            this.a.adapterDidLoadAdSuccessAd(this, this.d);
        }
    }

    @Override // com.sigmob.sdk.splash.b
    public void onSplashAdSkip() {
        if (this.a != null) {
            this.a.adapterDidSkipAd(this, this.d);
        }
    }

    @Override // com.sigmob.sdk.splash.b
    public void onSplashAdSuccessPresentScreen() {
        if (this.a != null) {
            this.a.adapterDidStartPlayingAd(this, this.d);
        }
    }

    @Override // com.sigmob.sdk.splash.b
    public void onSplashClosed() {
        if (this.a != null) {
            this.a.adapterDidCloseAd(this, this.d, true);
        }
    }

    @Override // com.sigmob.windad.LifecycleListener
    public void onStart(Activity activity) {
    }

    @Override // com.sigmob.windad.LifecycleListener
    public void onStop(Activity activity) {
    }

    @Override // com.sigmob.windad.base.WindAdAdapter
    public void presentVideoAd(Activity activity, ViewGroup viewGroup, ADStrategy aDStrategy) {
        WindAdConnector windAdConnector;
        String str;
        SigmobLog.i(getClass().getName() + " show");
        if (this.b != null) {
            if (this.b.a(viewGroup) || this.a == null) {
                return;
            }
            windAdConnector = this.a;
            str = "sigmob show adManager is null";
        } else {
            if (this.a == null) {
                return;
            }
            windAdConnector = this.a;
            str = "mSplashAd is null";
        }
        windAdConnector.adapterDidFailToPlayingAd(this, aDStrategy, a(0, str));
    }
}
