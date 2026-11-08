package com.bytedance.msdk.adapter.ks;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.Nullable;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.listener.ITTAdapterSplashAdListener;
import com.bytedance.msdk.adapter.listener.ITTAdatperCallback;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.TTRequestExtraParams;
import com.bytedance.msdk.api.v2.ad.splash.GMSplashAdListener;
import com.bytedance.msdk.base.TTBaseAd;
import com.kwad.sdk.api.KsAdSDK;
import com.kwad.sdk.api.KsLoadManager;
import com.kwad.sdk.api.KsScene;
import com.kwad.sdk.api.KsSplashScreenAd;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class KsSplashAdapter extends TTAbsAdLoaderAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f4058a;

    /* renamed from: b, reason: collision with root package name */
    private int f4059b = 3000;

    /* renamed from: c, reason: collision with root package name */
    private KsSplashAd f4060c;

    class KsSplashAd extends TTBaseAd {

        /* renamed from: a, reason: collision with root package name */
        private KsSplashScreenAd f4061a;

        /* renamed from: b, reason: collision with root package name */
        KsLoadManager.SplashScreenAdListener f4062b = new KsLoadManager.SplashScreenAdListener() { // from class: com.bytedance.msdk.adapter.ks.KsSplashAdapter.KsSplashAd.3
            public void onError(int i, String str) {
                KsSplashAdapter.this.notifyAdFailed(new AdError(AdError.ERROR_CODE_AD_LOAD_FAIL, AdError.AD_LOAD_FAIL_MSG, i, str));
            }

            public void onRequestResult(int i) {
            }

            public void onSplashScreenAdLoad(@Nullable KsSplashScreenAd ksSplashScreenAd) {
                if (ksSplashScreenAd == null) {
                    KsSplashAdapter.this.notifyAdFailed(new AdError(AdError.ERROR_CODE_NO_AD, AdError.AD_NO_FILL, AdError.ERROR_CODE_NO_AD, AdError.AD_NO_FILL));
                    return;
                }
                KsSplashAd.this.f4061a = ksSplashScreenAd;
                if (KsSplashAdapter.this.isClientBidding()) {
                    double ecpm = ksSplashScreenAd.getECPM();
                    KsSplashAd.this.setCpm(ecpm > 0.0d ? ecpm : 0.0d);
                    Logger.d("TTMediationSDK_ECMP", TTLogUtil.getTagThirdLevelById(KsSplashAdapter.this.getAdapterRit(), KsSplashAdapter.this.getAdSlotId()) + "KS_clientBidding 开屏 返回的 cpm价格：" + ecpm);
                }
                KsSplashAd.this.setExpressAd(true);
                KsSplashAd ksSplashAd = KsSplashAd.this;
                KsSplashAdapter.this.notifyAdLoaded(ksSplashAd);
            }
        };

        KsSplashAd(ITTAdapterSplashAdListener iTTAdapterSplashAdListener) {
            this.mTTAdatperCallback = iTTAdapterSplashAdListener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ITTAdapterSplashAdListener a() {
            return (ITTAdapterSplashAdListener) this.mTTAdatperCallback;
        }

        void a(KsScene ksScene) {
            KsAdSDK.getLoadManager().loadSplashScreenAd(ksScene, this.f4062b);
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return ((TTAbsAdLoaderAdapter) KsSplashAdapter.this).mAdSlot.getAdType();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return this.f4061a == null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isReady() {
            return true;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            if (this.f4061a != null) {
                this.f4061a = null;
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void showMinWindow(Rect rect, final ITTAdatperCallback iTTAdatperCallback) {
            if (this.f4061a == null || KsSplashAdapter.this.f4058a == null || rect == null) {
                return;
            }
            this.f4061a.showSplashMiniWindowIfNeeded(KsSplashAdapter.this.f4058a, new KsSplashScreenAd.SplashScreenAdInteractionListener() { // from class: com.bytedance.msdk.adapter.ks.KsSplashAdapter.KsSplashAd.2
                public void onAdClicked() {
                    ITTAdatperCallback iTTAdatperCallback2 = iTTAdatperCallback;
                    if (iTTAdatperCallback2 instanceof GMSplashAdListener) {
                        ((GMSplashAdListener) iTTAdatperCallback2).onAdClicked();
                    }
                }

                public void onAdShowEnd() {
                    ITTAdatperCallback iTTAdatperCallback2 = iTTAdatperCallback;
                    if (iTTAdatperCallback2 instanceof GMSplashAdListener) {
                        ((GMSplashAdListener) iTTAdatperCallback2).onAdDismiss();
                    }
                }

                public void onAdShowError(int i, String str) {
                    if (((TTBaseAd) KsSplashAd.this).mTTAdatperCallback instanceof ITTAdapterSplashAdListener) {
                        KsSplashAd.this.a().onAdShowFail(new AdError(AdError.ERROR_CODE_ADN_SHOW_FAIL, str, i, str));
                    }
                }

                public void onAdShowStart() {
                    ITTAdatperCallback iTTAdatperCallback2 = iTTAdatperCallback;
                    if (iTTAdatperCallback2 instanceof GMSplashAdListener) {
                        ((GMSplashAdListener) iTTAdatperCallback2).onAdShow();
                    }
                }

                public void onDownloadTipsDialogCancel() {
                }

                public void onDownloadTipsDialogDismiss() {
                }

                public void onDownloadTipsDialogShow() {
                }

                public void onSkippedAd() {
                    ITTAdatperCallback iTTAdatperCallback2 = iTTAdatperCallback;
                    if (iTTAdatperCallback2 instanceof GMSplashAdListener) {
                        ((GMSplashAdListener) iTTAdatperCallback2).onAdSkip();
                    }
                }
            }, rect);
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void showSplashAd(ViewGroup viewGroup) {
            View view;
            KsSplashScreenAd ksSplashScreenAd = this.f4061a;
            if (ksSplashScreenAd == null || viewGroup == null || (view = ksSplashScreenAd.getView(KsSplashAdapter.this.f4058a, new KsSplashScreenAd.SplashScreenAdInteractionListener() { // from class: com.bytedance.msdk.adapter.ks.KsSplashAdapter.KsSplashAd.1
                public void onAdClicked() {
                    if (((TTBaseAd) KsSplashAd.this).mTTAdatperCallback instanceof ITTAdapterSplashAdListener) {
                        KsSplashAd.this.a().onAdClicked();
                    }
                }

                public void onAdShowEnd() {
                    if (((TTBaseAd) KsSplashAd.this).mTTAdatperCallback instanceof ITTAdapterSplashAdListener) {
                        KsSplashAd.this.a().onAdDismiss();
                    }
                }

                public void onAdShowError(int i, String str) {
                    if (((TTBaseAd) KsSplashAd.this).mTTAdatperCallback instanceof ITTAdapterSplashAdListener) {
                        KsSplashAd.this.a().onAdShowFail(new AdError(AdError.ERROR_CODE_ADN_SHOW_FAIL, str, i, str));
                    }
                }

                public void onAdShowStart() {
                    if (((TTBaseAd) KsSplashAd.this).mTTAdatperCallback instanceof ITTAdapterSplashAdListener) {
                        KsSplashAd.this.a().onAdShow();
                    }
                }

                public void onDownloadTipsDialogCancel() {
                }

                public void onDownloadTipsDialogDismiss() {
                }

                public void onDownloadTipsDialogShow() {
                }

                public void onSkippedAd() {
                    if (((TTBaseAd) KsSplashAd.this).mTTAdatperCallback instanceof ITTAdapterSplashAdListener) {
                        KsSplashAd.this.a().onAdSkip();
                    }
                }
            })) == null) {
                return;
            }
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view);
            }
            view.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            viewGroup.removeAllViews();
            viewGroup.addView(view);
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
        this.f4058a = context;
        try {
            KsScene ksSceneBuild = new KsScene.Builder(Long.valueOf(getAdSlotId()).longValue()).needShowMiniWindow(true).build();
            this.f4058a = context;
            if (map != null) {
                this.f4059b = map.containsKey(TTRequestExtraParams.PARAM_AD_LOAD_TIMEOUT) ? ((Integer) map.get(TTRequestExtraParams.PARAM_AD_LOAD_TIMEOUT)).intValue() : this.f4059b;
                Object obj = map.get("tt_ad_network_callback");
                this.f4060c = new KsSplashAd(obj instanceof ITTAdapterSplashAdListener ? (ITTAdapterSplashAdListener) obj : null);
                this.f4060c.a(ksSceneBuild);
            }
        } catch (Exception unused) {
            notifyAdFailed(new AdError(AdError.ERROR_CODE_ADSLOT_ERROR, AdError.AD_SLOTID_ERROR_MSG, AdError.ERROR_CODE_ADSLOT_ERROR, AdError.AD_SLOTID_ERROR_MSG));
        }
    }
}
