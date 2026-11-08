package com.sigmob.windad.natives;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.common.models.ADStrategy;
import com.sigmob.sdk.common.mta.PointEntityWindError;
import com.sigmob.windad.WindAdAdapterError;
import com.sigmob.windad.WindAdError;
import com.sigmob.windad.WindAds;
import com.sigmob.windad.natives.WindNativeAdRequestController;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class WindNativeUnifiedAd implements WindNativeAdRequestController.WindAdInitListener, WindNativeAdRequestController.WindAdRequestLoadLister {
    private WindNativeAdRequestController a;
    private NativeAdLoadListener b;
    private String c;
    private Handler d;
    private WindNativeAdRequest e;
    private Context f;

    public interface NativeAdLoadListener {
        void onError(WindAdError windAdError, String str);

        void onFeedAdLoad(String str);
    }

    public WindNativeUnifiedAd(Context context, WindNativeAdRequest windNativeAdRequest) {
        this.c = "";
        this.f = context;
        if (windNativeAdRequest != null && !TextUtils.isEmpty(windNativeAdRequest.getPlacementId())) {
            this.c = windNativeAdRequest.getPlacementId();
        }
        this.e = windNativeAdRequest;
        this.d = WindAds.sharedAds().getHandler();
    }

    private boolean a(WindNativeAdRequest windNativeAdRequest) {
        try {
            if (this.a == null) {
                this.a = new WindNativeAdRequestController(this.f, windNativeAdRequest, this, this);
            }
            this.a.loadAd(windNativeAdRequest);
            return true;
        } catch (Throwable th) {
            PointEntityWindError pointEntityWindErrorWindError = PointEntityWindError.WindError("error", WindAdError.ERROR_SIGMOB_REQUEST.getErrorCode(), th.getMessage());
            pointEntityWindErrorWindError.setAdtype(String.valueOf(5));
            pointEntityWindErrorWindError.setPlacement_id(this.c);
            pointEntityWindErrorWindError.commit();
            onVideoAdLoadFail(WindAdError.ERROR_SIGMOB_REQUEST, windNativeAdRequest.getPlacementId());
            return false;
        }
    }

    @Override // com.sigmob.windad.natives.WindNativeAdRequestController.WindAdInitListener
    public void adapterDidInitFailWithStrategy(ADStrategy aDStrategy, WindAdAdapterError windAdAdapterError) {
        SigmobLog.i("adapterDidInitFailVideoAdWithStrategy() called with: strategy = [" + aDStrategy.getName() + "], error = [" + windAdAdapterError + "]");
    }

    @Override // com.sigmob.windad.natives.WindNativeAdRequestController.WindAdInitListener
    public void adapterDidInitSuccessWithStrategy(ADStrategy aDStrategy) {
        SigmobLog.i("adapterDidInitSuccessVideoAdWithStrategy() called with: strategy = [" + aDStrategy.getName() + "]");
    }

    public void destroy() {
        if (this.a != null) {
            this.a.destroy();
            this.a = null;
        }
    }

    public List<NativeADData> getNativeADDataList() {
        if (this.a != null) {
            return this.a.getNativeADDataList();
        }
        return null;
    }

    public boolean loadAd(NativeAdLoadListener nativeAdLoadListener) {
        if (nativeAdLoadListener != null) {
            try {
                this.b = nativeAdLoadListener;
            } catch (Throwable th) {
                PointEntityWindError pointEntityWindErrorWindError = PointEntityWindError.WindError("error", WindAdError.ERROR_SIGMOB_REQUEST.getErrorCode(), th.getMessage());
                pointEntityWindErrorWindError.setAdtype(String.valueOf(5));
                pointEntityWindErrorWindError.setPlacement_id(this.c);
                pointEntityWindErrorWindError.commit();
                onVideoAdLoadFail(WindAdError.ERROR_SIGMOB_REQUEST, this.c);
                return false;
            }
        }
        return a(this.e);
    }

    @Override // com.sigmob.windad.natives.WindNativeAdRequestController.WindAdRequestLoadLister
    public void onVideoAdLoadFail(final WindAdError windAdError, final String str) {
        this.d.post(new Runnable() { // from class: com.sigmob.windad.natives.WindNativeUnifiedAd.2
            @Override // java.lang.Runnable
            public void run() {
                if (WindNativeUnifiedAd.this.b != null) {
                    SigmobLog.i("onVideoAdLoadFail " + windAdError.toString() + "|" + str);
                    WindNativeUnifiedAd.this.b.onError(windAdError, str);
                }
            }
        });
    }

    @Override // com.sigmob.windad.natives.WindNativeAdRequestController.WindAdRequestLoadLister
    public void onVideoAdLoadSuccess(List<NativeADData> list, final String str) {
        this.d.post(new Runnable() { // from class: com.sigmob.windad.natives.WindNativeUnifiedAd.1
            @Override // java.lang.Runnable
            public void run() {
                if (WindNativeUnifiedAd.this.b != null) {
                    SigmobLog.i("onVideoAdLoadSuccess |" + str);
                    WindNativeUnifiedAd.this.b.onFeedAdLoad(str);
                }
            }
        });
    }
}
