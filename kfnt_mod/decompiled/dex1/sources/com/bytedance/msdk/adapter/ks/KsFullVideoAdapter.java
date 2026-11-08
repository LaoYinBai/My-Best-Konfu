package com.bytedance.msdk.adapter.ks;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.Nullable;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.listener.ITTAdapterFullVideoAdListener;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.TTAdConstant;
import com.bytedance.msdk.base.TTBaseAd;
import com.kwad.sdk.api.KsAdSDK;
import com.kwad.sdk.api.KsFullScreenVideoAd;
import com.kwad.sdk.api.KsLoadManager;
import com.kwad.sdk.api.KsScene;
import com.kwad.sdk.api.KsVideoPlayConfig;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class KsFullVideoAdapter extends TTAbsAdLoaderAdapter {
    public static final String TAG = "KsFullVideoAdapter";

    class KsFullVideoAd extends TTBaseAd {

        /* renamed from: a, reason: collision with root package name */
        private KsFullScreenVideoAd f4027a;

        /* renamed from: b, reason: collision with root package name */
        private boolean f4028b;

        /* renamed from: c, reason: collision with root package name */
        KsLoadManager.FullScreenVideoAdListener f4029c = new KsLoadManager.FullScreenVideoAdListener() { // from class: com.bytedance.msdk.adapter.ks.KsFullVideoAdapter.KsFullVideoAd.1
            public void onError(int i, String str) {
                KsFullVideoAd.this.f4028b = false;
                Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelById(KsFullVideoAdapter.this.getAdapterRit(), KsFullVideoAdapter.this.getAdSlotId()) + "加载ks全屏视频广告失败：code:" + i + "   msg:" + str);
                KsFullVideoAdapter.this.notifyAdFailed(new AdError(AdError.ERROR_CODE_AD_LOAD_FAIL, AdError.AD_LOAD_FAIL_MSG, i, str));
            }

            public void onFullScreenVideoAdLoad(@Nullable List<KsFullScreenVideoAd> list) {
                StringBuilder sb;
                if (list == null) {
                    sb = new StringBuilder();
                    sb.append(TTLogUtil.getTagThirdLevelById(KsFullVideoAdapter.this.getAdapterRit(), KsFullVideoAdapter.this.getAdSlotId()));
                    sb.append("加载ks全屏视频广告成功，但没有返回数据");
                } else {
                    sb = new StringBuilder();
                    sb.append(TTLogUtil.getTagThirdLevelById(KsFullVideoAdapter.this.getAdapterRit(), KsFullVideoAdapter.this.getAdSlotId()));
                    sb.append("加载ks全屏视频广告成功，数量:");
                    sb.append(list.size());
                }
                Logger.e("TTMediationSDK", sb.toString());
                if (list == null || list.size() == 0 || list.get(0) == null) {
                    KsFullVideoAdapter.this.notifyAdFailed(new AdError(AdError.ERROR_CODE_NO_AD, AdError.AD_NO_FILL, AdError.ERROR_CODE_NO_AD, AdError.AD_NO_FILL));
                    return;
                }
                KsFullVideoAd.this.f4027a = list.get(0);
                if (KsFullVideoAdapter.this.isClientBidding()) {
                    double ecpm = KsFullVideoAd.this.f4027a.getECPM();
                    Logger.d("TTMediationSDK_ECMP", TTLogUtil.getTagThirdLevelById(KsFullVideoAdapter.this.getAdapterRit(), KsFullVideoAdapter.this.getAdSlotId()) + "KS_clientBidding   全屏 返回的 cpm价格：" + ecpm);
                    KsFullVideoAd ksFullVideoAd = KsFullVideoAd.this;
                    if (ecpm <= 0.0d) {
                        ecpm = 0.0d;
                    }
                    ksFullVideoAd.setCpm(ecpm);
                }
                KsFullVideoAd.this.setExpressAd(true);
                KsFullVideoAd.this.f4027a.setFullScreenVideoAdInteractionListener(new KsFullScreenVideoAd.FullScreenVideoAdInteractionListener() { // from class: com.bytedance.msdk.adapter.ks.KsFullVideoAdapter.KsFullVideoAd.1.1
                    public void onAdClicked() {
                        if (((TTBaseAd) KsFullVideoAd.this).mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener) {
                            KsFullVideoAd.this.a().onFullVideoAdClick();
                        }
                    }

                    public void onPageDismiss() {
                        if (((TTBaseAd) KsFullVideoAd.this).mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener) {
                            KsFullVideoAd.this.a().onFullVideoAdClosed();
                        }
                    }

                    public void onSkippedVideo() {
                        if (((TTBaseAd) KsFullVideoAd.this).mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener) {
                            KsFullVideoAd.this.a().onSkippedVideo();
                        }
                    }

                    public void onVideoPlayEnd() {
                        if (((TTBaseAd) KsFullVideoAd.this).mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener) {
                            KsFullVideoAd.this.a().onVideoComplete();
                        }
                    }

                    public void onVideoPlayError(int i, int i2) {
                        if (((TTBaseAd) KsFullVideoAd.this).mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener) {
                            KsFullVideoAd.this.a().onVideoError();
                        }
                    }

                    public void onVideoPlayStart() {
                        if (((TTBaseAd) KsFullVideoAd.this).mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener) {
                            KsFullVideoAd.this.a().onFullVideoAdShow();
                        }
                    }
                });
                KsFullVideoAd.this.f4028b = true;
                KsFullVideoAd ksFullVideoAd2 = KsFullVideoAd.this;
                KsFullVideoAdapter.this.notifyAdLoaded(ksFullVideoAd2);
                KsFullVideoAd ksFullVideoAd3 = KsFullVideoAd.this;
                KsFullVideoAdapter.this.notifyAdVideoCache(ksFullVideoAd3, new AdError(AdError.ERROR_CODE_THIRD_SDK_NO_CACHE_CALLBACK, AdError.AD_THIRD_SDK_NO_CACHE_CALLBACK_MSG));
            }

            public void onRequestResult(int i) {
            }
        };

        KsFullVideoAd(ITTAdapterFullVideoAdListener iTTAdapterFullVideoAdListener) {
            this.mTTAdatperCallback = iTTAdapterFullVideoAdListener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ITTAdapterFullVideoAdListener a() {
            return (ITTAdapterFullVideoAdListener) this.mTTAdatperCallback;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean adnHasAdExpiredApi() {
            return true;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return ((TTAbsAdLoaderAdapter) KsFullVideoAdapter.this).mAdSlot.getAdType();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return this.f4027a == null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isAdExpired() {
            if (this.f4027a != null) {
                return !r0.isAdEnable();
            }
            return true;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isReady() {
            KsFullScreenVideoAd ksFullScreenVideoAd;
            return this.f4028b && (ksFullScreenVideoAd = this.f4027a) != null && ksFullScreenVideoAd.isAdEnable();
        }

        public void loadAd(KsScene ksScene) {
            KsAdSDK.getLoadManager().loadFullScreenVideoAd(ksScene, this.f4029c);
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            KsFullScreenVideoAd ksFullScreenVideoAd = this.f4027a;
            if (ksFullScreenVideoAd != null) {
                ksFullScreenVideoAd.setFullScreenVideoAdInteractionListener((KsFullScreenVideoAd.FullScreenVideoAdInteractionListener) null);
                this.f4027a = null;
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void showAd(Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map) {
            KsFullScreenVideoAd ksFullScreenVideoAd;
            if (activity == null || !isReady() || (ksFullScreenVideoAd = this.f4027a) == null || !ksFullScreenVideoAd.isAdEnable()) {
                return;
            }
            KsVideoPlayConfig ksVideoPlayConfigBuild = null;
            if (((TTAbsAdLoaderAdapter) KsFullVideoAdapter.this).mAdSlot != null && ((TTAbsAdLoaderAdapter) KsFullVideoAdapter.this).mAdSlot.getOrientation() == 2) {
                ksVideoPlayConfigBuild = new KsVideoPlayConfig.Builder().showLandscape(true).build();
            }
            this.f4027a.showFullScreenVideoAd(activity, ksVideoPlayConfigBuild);
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
            KsScene ksSceneBuild = new KsScene.Builder(Long.valueOf(getAdSlotId()).longValue()).build();
            if (map != null) {
                Object obj = map.get("tt_ad_network_callback");
                new KsFullVideoAd(obj instanceof ITTAdapterFullVideoAdListener ? (ITTAdapterFullVideoAdListener) obj : null).loadAd(ksSceneBuild);
            }
        } catch (Exception unused) {
            notifyAdFailed(new AdError(AdError.ERROR_CODE_ADSLOT_ERROR, AdError.AD_SLOTID_ERROR_MSG, AdError.ERROR_CODE_ADSLOT_ERROR, AdError.AD_SLOTID_ERROR_MSG));
        }
    }
}
