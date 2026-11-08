package com.bytedance.msdk.adapter.ks;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.Nullable;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.listener.ITTAdapterInterstitialListener;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.TTAdConstant;
import com.bytedance.msdk.api.TTVideoOption;
import com.bytedance.msdk.base.TTBaseAd;
import com.kwad.sdk.api.KsAdSDK;
import com.kwad.sdk.api.KsInterstitialAd;
import com.kwad.sdk.api.KsLoadManager;
import com.kwad.sdk.api.KsScene;
import com.kwad.sdk.api.KsVideoPlayConfig;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class KsInterstitialAdapter extends TTAbsAdLoaderAdapter {
    public static final String TAG = "KsInterstitialAdapter";

    /* renamed from: a, reason: collision with root package name */
    private TTVideoOption f4032a;

    class KsInterstitialVideoAd extends TTBaseAd {

        /* renamed from: a, reason: collision with root package name */
        private KsInterstitialAd f4033a;

        /* renamed from: b, reason: collision with root package name */
        private boolean f4034b;

        /* renamed from: c, reason: collision with root package name */
        private boolean f4035c;
        KsLoadManager.InterstitialAdListener d = new KsLoadManager.InterstitialAdListener() { // from class: com.bytedance.msdk.adapter.ks.KsInterstitialAdapter.KsInterstitialVideoAd.1
            public void onError(int i, String str) {
                KsInterstitialAdapter.this.notifyAdFailed(new AdError(AdError.ERROR_CODE_AD_LOAD_FAIL, AdError.AD_LOAD_FAIL_MSG, i, str));
            }

            public void onInterstitialAdLoad(@Nullable List<KsInterstitialAd> list) {
                if (list == null || list.size() == 0) {
                    KsInterstitialAdapter.this.notifyAdFailed(new AdError(AdError.ERROR_CODE_NO_AD, AdError.getMessage(AdError.ERROR_CODE_NO_AD), AdError.ERROR_CODE_NO_AD, AdError.getMessage(AdError.ERROR_CODE_NO_AD)));
                    return;
                }
                KsInterstitialVideoAd.this.f4034b = true;
                KsInterstitialVideoAd.this.f4033a = list.get(0);
                KsInterstitialVideoAd.this.setExpressAd(true);
                if (KsInterstitialAdapter.this.isClientBidding()) {
                    double ecpm = KsInterstitialVideoAd.this.f4033a.getECPM();
                    KsInterstitialVideoAd.this.setCpm(ecpm > 0.0d ? ecpm : 0.0d);
                    Logger.d("TTMediationSDK_ECMP", TTLogUtil.getTagThirdLevelById(KsInterstitialAdapter.this.getAdapterRit(), KsInterstitialAdapter.this.getAdSlotId()) + "KS_clientBidding   插屏 返回的 cpm价格：" + ecpm);
                }
                KsInterstitialVideoAd ksInterstitialVideoAd = KsInterstitialVideoAd.this;
                KsInterstitialAdapter.this.notifyAdLoaded(ksInterstitialVideoAd);
                KsInterstitialVideoAd.this.f4033a.setAdInteractionListener(new KsInterstitialAd.AdInteractionListener() { // from class: com.bytedance.msdk.adapter.ks.KsInterstitialAdapter.KsInterstitialVideoAd.1.1
                    public void onAdClicked() {
                        Logger.d("TTMediationSDK", "ks_interstitialAd_onAdClicked");
                        if (((TTBaseAd) KsInterstitialVideoAd.this).mTTAdatperCallback instanceof ITTAdapterInterstitialListener) {
                            KsInterstitialVideoAd.this.b().onInterstitialAdClick();
                        }
                    }

                    public void onAdClosed() {
                        KsInterstitialVideoAd.this.a();
                    }

                    public void onAdShow() {
                        Logger.d("TTMediationSDK", "ks_interstitialAd_onAdShow");
                        if (((TTBaseAd) KsInterstitialVideoAd.this).mTTAdatperCallback instanceof ITTAdapterInterstitialListener) {
                            KsInterstitialVideoAd.this.b().onInterstitialShow();
                        }
                    }

                    public void onPageDismiss() {
                        Logger.d("TTMediationSDK", "ks_interstitialAd_onPageDismiss");
                        KsInterstitialVideoAd.this.a();
                    }

                    public void onSkippedAd() {
                        Logger.d("TTMediationSDK", "ks_interstitialAd_onSkippedAd");
                        KsInterstitialVideoAd.this.a();
                    }

                    public void onVideoPlayEnd() {
                        Logger.d("TTMediationSDK", "ks_interstitialAd_onVideoPlayEnd");
                    }

                    public void onVideoPlayError(int i, int i2) {
                        Logger.d("TTMediationSDK", "ks_interstitialAd_onVideoPlayError");
                    }

                    public void onVideoPlayStart() {
                        Logger.d("TTMediationSDK", "ks_interstitialAd_onVideoPlayStart");
                    }
                });
            }

            public void onRequestResult(int i) {
            }
        };

        KsInterstitialVideoAd(ITTAdapterInterstitialListener iTTAdapterInterstitialListener) {
            this.mTTAdatperCallback = iTTAdapterInterstitialListener;
            this.f4035c = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            if (this.f4035c) {
                return;
            }
            Logger.d("TTMediationSDK", "ks_interstitialAd_onAdClosed");
            this.f4035c = true;
            if (this.mTTAdatperCallback instanceof ITTAdapterInterstitialListener) {
                b().onInterstitialClosed();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ITTAdapterInterstitialListener b() {
            return (ITTAdapterInterstitialListener) this.mTTAdatperCallback;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return ((TTAbsAdLoaderAdapter) KsInterstitialAdapter.this).mAdSlot.getAdType();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return this.f4033a == null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isReady() {
            return this.f4034b;
        }

        public void loadAd(KsScene ksScene) {
            KsAdSDK.getLoadManager().loadInterstitialAd(ksScene, this.d);
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            KsInterstitialAd ksInterstitialAd = this.f4033a;
            if (ksInterstitialAd != null) {
                ksInterstitialAd.setAdInteractionListener((KsInterstitialAd.AdInteractionListener) null);
                this.f4033a = null;
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void showAd(Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map) {
            if (this.f4033a != null) {
                boolean z = false;
                if (KsInterstitialAdapter.this.f4032a != null && KsInterstitialAdapter.this.f4032a.isMuted()) {
                    z = true;
                }
                this.f4033a.showInterstitialAd(activity, new KsVideoPlayConfig.Builder().videoSoundEnable(!z).build());
            }
        }
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void destroy() {
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getAdNetWorkName() {
        return "ks";
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getSdkVersion() {
        return KsAdSDK.getSDKVersion();
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void loadAd(Context context, Map<String, Object> map) {
        try {
            long jLongValue = Long.valueOf(getAdSlotId()).longValue();
            this.f4032a = this.mAdSlot.getTTVideoOption();
            KsScene ksSceneBuild = new KsScene.Builder(jLongValue).build();
            if (map != null) {
                Object obj = map.get("tt_ad_network_callback");
                new KsInterstitialVideoAd(obj instanceof ITTAdapterInterstitialListener ? (ITTAdapterInterstitialListener) obj : null).loadAd(ksSceneBuild);
            }
        } catch (Exception unused) {
            notifyAdFailed(new AdError(AdError.ERROR_CODE_ADSLOT_ERROR, AdError.AD_SLOTID_ERROR_MSG, AdError.ERROR_CODE_ADSLOT_ERROR, AdError.AD_SLOTID_ERROR_MSG));
        }
    }
}
