package com.bytedance.msdk.adapter.sigmob;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.listener.ITTAdapterSplashAdListener;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdErrorUtil;
import com.bytedance.msdk.api.TTRequestExtraParams;
import com.bytedance.msdk.base.TTBaseAd;
import com.sigmob.windad.Splash.WindSplashAD;
import com.sigmob.windad.Splash.WindSplashADListener;
import com.sigmob.windad.Splash.WindSplashAdRequest;
import com.sigmob.windad.WindAdError;
import com.sigmob.windad.WindAds;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class SigmobSplashAdapter extends TTAbsAdLoaderAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f4210a;

    /* renamed from: b, reason: collision with root package name */
    private int f4211b = 3000;

    /* renamed from: c, reason: collision with root package name */
    private SigmobSplashAd f4212c;

    class SigmobSplashAd extends TTBaseAd {

        /* renamed from: a, reason: collision with root package name */
        WindSplashAD f4213a;

        /* renamed from: b, reason: collision with root package name */
        WindSplashADListener f4214b = new WindSplashADListener() { // from class: com.bytedance.msdk.adapter.sigmob.SigmobSplashAdapter.SigmobSplashAd.1
            public void onSplashAdClicked() {
                Log.e("TTMediationSDK", "sigmob_splash_onSplashAdClicked");
                if (((TTBaseAd) SigmobSplashAd.this).mTTAdatperCallback instanceof ITTAdapterSplashAdListener) {
                    SigmobSplashAd.this.b().onAdClicked();
                }
            }

            public void onSplashAdFailToLoad(WindAdError windAdError, String str) {
                SigmobSplashAdapter sigmobSplashAdapter;
                AdError adErrorObtainAdError;
                if (windAdError != null) {
                    Log.e("TTMediationSDK", "sigmob_splash_onSplashAdFailToLoad  err_code:" + windAdError.getErrorCode() + "  err_msg:" + windAdError.getMessage());
                    adErrorObtainAdError = AdErrorUtil.getSigmobError(windAdError.getErrorCode(), windAdError.getMessage());
                    if (adErrorObtainAdError != null) {
                        adErrorObtainAdError.message = windAdError.getMessage();
                    }
                    sigmobSplashAdapter = SigmobSplashAdapter.this;
                } else {
                    sigmobSplashAdapter = SigmobSplashAdapter.this;
                    adErrorObtainAdError = AdErrorUtil.obtainAdError(AdError.ERROR_CODE_NO_AD, AdError.AD_NO_FILL);
                }
                sigmobSplashAdapter.notifyAdFailed(adErrorObtainAdError);
            }

            public void onSplashAdSuccessLoad() {
                Log.e("TTMediationSDK", "sigmob_splash_onSplashAdSuccessLoad");
                SigmobSplashAd sigmobSplashAd = SigmobSplashAd.this;
                SigmobSplashAdapter.this.notifyAdLoaded(sigmobSplashAd);
            }

            public void onSplashAdSuccessPresent() {
                Log.e("TTMediationSDK", "sigmob_splash_onSplashAdSuccessPresent");
                if (((TTBaseAd) SigmobSplashAd.this).mTTAdatperCallback instanceof ITTAdapterSplashAdListener) {
                    SigmobSplashAd.this.b().onAdShow();
                }
            }

            public void onSplashClosed() {
                Log.e("TTMediationSDK", "sigmob_splash_onSplashClosed");
                if (((TTBaseAd) SigmobSplashAd.this).mTTAdatperCallback instanceof ITTAdapterSplashAdListener) {
                    SigmobSplashAd.this.b().onAdDismiss();
                }
            }
        };

        SigmobSplashAd(ITTAdapterSplashAdListener iTTAdapterSplashAdListener) {
            this.mTTAdatperCallback = iTTAdapterSplashAdListener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ITTAdapterSplashAdListener b() {
            return (ITTAdapterSplashAdListener) this.mTTAdatperCallback;
        }

        void a() {
            if (SigmobSplashAdapter.this.f4210a instanceof Activity) {
                WindSplashAdRequest windSplashAdRequest = new WindSplashAdRequest(SigmobSplashAdapter.this.getAdSlotId(), ((TTAbsAdLoaderAdapter) SigmobSplashAdapter.this).mAdSlot.getUserID(), (Map) null);
                windSplashAdRequest.setFetchDelay(SigmobSplashAdapter.this.a());
                this.f4213a = new WindSplashAD((Activity) SigmobSplashAdapter.this.f4210a, windSplashAdRequest, this.f4214b);
                this.f4213a.loadAdOnly();
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return ((TTAbsAdLoaderAdapter) SigmobSplashAdapter.this).mAdSlot.getAdType();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return this.f4213a == null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isAdCacheReady() {
            return isReady();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isReady() {
            WindSplashAD windSplashAD = this.f4213a;
            if (windSplashAD != null) {
                return windSplashAD.isReady();
            }
            return false;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            this.f4213a = null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void showSplashAd(ViewGroup viewGroup) {
            WindSplashAD windSplashAD = this.f4213a;
            if (windSplashAD == null || !windSplashAD.isReady() || viewGroup == null) {
                return;
            }
            viewGroup.removeAllViews();
            this.f4213a.showAd(viewGroup);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a() {
        int i = this.f4211b;
        if (i < 1000) {
            return 1;
        }
        return i / 1000;
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
        this.f4210a = context;
        if (map != null) {
            this.f4211b = map.containsKey(TTRequestExtraParams.PARAM_AD_LOAD_TIMEOUT) ? ((Integer) map.get(TTRequestExtraParams.PARAM_AD_LOAD_TIMEOUT)).intValue() : this.f4211b;
            Object obj = map.get("tt_ad_network_callback");
            this.f4212c = new SigmobSplashAd(obj instanceof ITTAdapterSplashAdListener ? (ITTAdapterSplashAdListener) obj : null);
            this.f4212c.a();
        }
    }
}
