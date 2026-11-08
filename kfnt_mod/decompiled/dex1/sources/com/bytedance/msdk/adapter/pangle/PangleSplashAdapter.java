package com.bytedance.msdk.adapter.pangle;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.MainThread;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.listener.ITTAdapterSplashAdListener;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.TTRequestExtraParams;
import com.bytedance.msdk.base.TTBaseAd;
import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.ISplashClickEyeListener;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTSplashAd;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class PangleSplashAdapter extends TTAbsAdLoaderAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f4181a;

    /* renamed from: b, reason: collision with root package name */
    private int f4182b = 3000;

    /* renamed from: c, reason: collision with root package name */
    private PangleSplashAd f4183c;

    class PangleSplashAd extends TTBaseAd {

        /* renamed from: a, reason: collision with root package name */
        boolean f4184a;

        /* renamed from: b, reason: collision with root package name */
        TTAdNative.SplashAdListener f4185b = new TTAdNative.SplashAdListener() { // from class: com.bytedance.msdk.adapter.pangle.PangleSplashAdapter.PangleSplashAd.1
            @Override // com.bytedance.sdk.openadsdk.TTAdNative.SplashAdListener, com.bytedance.sdk.openadsdk.common.CommonListener
            @MainThread
            public void onError(int i, String str) {
                PangleSplashAdapter.this.notifyAdFailed(PangleAdapterUtils.buildAdError(i, str));
            }

            @Override // com.bytedance.sdk.openadsdk.TTAdNative.SplashAdListener
            @MainThread
            public void onSplashAdLoad(TTSplashAd tTSplashAd) {
                Map<String, Object> mediaExtraInfo;
                if (tTSplashAd == null) {
                    PangleSplashAdapter.this.notifyAdFailed(PangleAdapterUtils.buildAdError(AdError.ERROR_CODE_NO_AD, AdError.AD_NO_FILL));
                    return;
                }
                PangleSplashAd.this.d = tTSplashAd;
                PangleSplashAd.this.setExpressAd(true);
                PangleSplashAd pangleSplashAd = PangleSplashAd.this;
                pangleSplashAd.setInteractionType(pangleSplashAd.d.getInteractionType());
                if (PangleSplashAdapter.this.isClientBidding() && (mediaExtraInfo = PangleSplashAd.this.d.getMediaExtraInfo()) != null) {
                    double value = PangleAdapterUtils.getValue(mediaExtraInfo.get("price"));
                    Logger.d("TTMediationSDK_ECMP", TTLogUtil.getTagThirdLevelById(PangleSplashAdapter.this.getAdapterRit(), PangleSplashAdapter.this.getAdSlotId()) + "pangle Splash 返回的 cpm价格：" + value);
                    PangleSplashAd pangleSplashAd2 = PangleSplashAd.this;
                    if (value <= 0.0d) {
                        value = 0.0d;
                    }
                    pangleSplashAd2.setCpm(value);
                }
                PangleSplashAd.this.d.setSplashInteractionListener(new TTSplashAd.AdInteractionListener() { // from class: com.bytedance.msdk.adapter.pangle.PangleSplashAdapter.PangleSplashAd.1.1
                    @Override // com.bytedance.sdk.openadsdk.TTSplashAd.AdInteractionListener
                    public void onAdClicked(View view, int i) {
                        if (((TTBaseAd) PangleSplashAd.this).mTTAdatperCallback instanceof ITTAdapterSplashAdListener) {
                            PangleSplashAd.this.b().onAdClicked();
                        }
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTSplashAd.AdInteractionListener
                    public void onAdShow(View view, int i) {
                        if (((TTBaseAd) PangleSplashAd.this).mTTAdatperCallback instanceof ITTAdapterSplashAdListener) {
                            PangleSplashAd.this.b().onAdShow();
                        }
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTSplashAd.AdInteractionListener
                    public void onAdSkip() {
                        Logger.e("TTMediationSDK", "穿山甲_splash_onAdSkip");
                        if (((TTBaseAd) PangleSplashAd.this).mTTAdatperCallback instanceof ITTAdapterSplashAdListener) {
                            PangleSplashAd.this.b().onAdSkip();
                        }
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTSplashAd.AdInteractionListener
                    public void onAdTimeOver() {
                        Logger.e("TTMediationSDK", "穿山甲_splash_onAdTimeOver");
                        if (((TTBaseAd) PangleSplashAd.this).mTTAdatperCallback instanceof ITTAdapterSplashAdListener) {
                            PangleSplashAd.this.b().onAdDismiss();
                        }
                    }
                });
                PangleSplashAd.this.d.setSplashClickEyeListener(new ISplashClickEyeListener() { // from class: com.bytedance.msdk.adapter.pangle.PangleSplashAdapter.PangleSplashAd.1.2
                    @Override // com.bytedance.sdk.openadsdk.ISplashClickEyeListener
                    public boolean isSupportSplashClickEye(boolean z) {
                        Logger.e("TTMediationSDK", "穿山甲_splash_isSupportSplashClickEye:" + z);
                        PangleSplashAd.this.f4184a = z;
                        return false;
                    }

                    @Override // com.bytedance.sdk.openadsdk.ISplashClickEyeListener
                    public void onSplashClickEyeAnimationFinish() {
                        Logger.e("TTMediationSDK", "穿山甲_splash_onSplashClickEyeAnimationFinish");
                        if (((TTBaseAd) PangleSplashAd.this).mTTAdatperCallback instanceof ITTAdapterSplashAdListener) {
                            PangleSplashAd.this.b().onMinWindowPlayFinish();
                        }
                    }

                    @Override // com.bytedance.sdk.openadsdk.ISplashClickEyeListener
                    public void onSplashClickEyeAnimationStart() {
                        Logger.e("TTMediationSDK", "穿山甲_splash_onSplashClickEyeAnimationStart");
                        PangleSplashAd pangleSplashAd3 = PangleSplashAd.this;
                        if (pangleSplashAd3.f4184a && (((TTBaseAd) pangleSplashAd3).mTTAdatperCallback instanceof ITTAdapterSplashAdListener)) {
                            PangleSplashAd.this.b().onMinWindowStart();
                        }
                    }
                });
                PangleSplashAd pangleSplashAd3 = PangleSplashAd.this;
                PangleSplashAdapter.this.notifyAdLoaded(pangleSplashAd3);
            }

            @Override // com.bytedance.sdk.openadsdk.TTAdNative.SplashAdListener
            @MainThread
            public void onTimeout() {
                PangleSplashAdapter.this.notifyAdFailed(PangleAdapterUtils.buildAdError(AdError.ERROR_CODE_LOAD_AD_TIMEOUT, "splash ad load timeout !"));
            }
        };
        private TTSplashAd d;

        PangleSplashAd(ITTAdapterSplashAdListener iTTAdapterSplashAdListener) {
            this.mTTAdatperCallback = iTTAdapterSplashAdListener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ITTAdapterSplashAdListener b() {
            return (ITTAdapterSplashAdListener) this.mTTAdatperCallback;
        }

        void a() {
            TTAdNative tTAdNativeCreateAdNative = TTAdSdk.getAdManager().createAdNative(PangleSplashAdapter.this.f4181a);
            AdSlot.Builder builderBuildPangleAdSlot = PangleAdapterUtils.buildPangleAdSlot(((TTAbsAdLoaderAdapter) PangleSplashAdapter.this).mAdSlot, PangleSplashAdapter.this.getAdSlotId(), ((TTAbsAdLoaderAdapter) PangleSplashAdapter.this).mWaterfallAbTestParam, PangleSplashAdapter.this.getClientReqId(), PangleSplashAdapter.this.getAdm(), false);
            builderBuildPangleAdSlot.setImageAcceptedSize(((TTAbsAdLoaderAdapter) PangleSplashAdapter.this).mAdSlot.getImgAcceptedWidth(), ((TTAbsAdLoaderAdapter) PangleSplashAdapter.this).mAdSlot.getImgAcceptedHeight()).setExpressViewAcceptedSize(((TTAbsAdLoaderAdapter) PangleSplashAdapter.this).mAdSlot.getImgAcceptedWidth(), ((TTAbsAdLoaderAdapter) PangleSplashAdapter.this).mAdSlot.getImgAcceptedHeight()).setSplashButtonType(((TTAbsAdLoaderAdapter) PangleSplashAdapter.this).mAdSlot.getSplashButtonType());
            tTAdNativeCreateAdNative.loadSplashAd(builderBuildPangleAdSlot.build(), this.f4185b, PangleSplashAdapter.this.f4182b);
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public long getAdId() {
            TTSplashAd tTSplashAd = this.d;
            return tTSplashAd != null ? PangleAdapterUtils.getAdId(tTSplashAd.getMediaExtraInfo()) : super.getAdId();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return ((TTAbsAdLoaderAdapter) PangleSplashAdapter.this).mAdSlot.getAdType();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public long getCreativeId() {
            TTSplashAd tTSplashAd = this.d;
            return tTSplashAd != null ? PangleAdapterUtils.getCreativeId(tTSplashAd.getMediaExtraInfo()) : super.getCreativeId();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int[] getMinWindowSize() {
            TTSplashAd tTSplashAd = this.d;
            if (tTSplashAd == null) {
                return null;
            }
            tTSplashAd.getSplashClickEyeSizeToDp();
            return null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public String getReqId() {
            TTSplashAd tTSplashAd = this.d;
            return tTSplashAd != null ? PangleAdapterUtils.getReqId(tTSplashAd.getMediaExtraInfo()) : super.getReqId();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return this.d == null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isReady() {
            return true;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            TTSplashAd tTSplashAd = this.d;
            if (tTSplashAd != null) {
                tTSplashAd.setSplashInteractionListener(null);
                this.d.setDownloadListener(null);
                this.d.renderExpressAd(null);
                this.d = null;
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void showSplashAd(ViewGroup viewGroup) {
            View splashView;
            TTSplashAd tTSplashAd = this.d;
            if (tTSplashAd == null || viewGroup == null || (splashView = tTSplashAd.getSplashView()) == null) {
                return;
            }
            ViewParent parent = splashView.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(splashView);
            }
            viewGroup.removeAllViews();
            viewGroup.addView(splashView);
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void splashMinWindowAnimationFinish() {
            TTSplashAd tTSplashAd = this.d;
            if (tTSplashAd != null) {
                tTSplashAd.splashClickEyeAnimationFinish();
            }
        }
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void destroy() {
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getAdNetWorkName() {
        return "pangle";
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getSdkVersion() {
        return TTAdSdk.getAdManager().getSDKVersion();
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void loadAd(Context context, Map<String, Object> map) {
        this.f4181a = context;
        if (map != null) {
            this.f4182b = map.containsKey(TTRequestExtraParams.PARAM_AD_LOAD_TIMEOUT) ? ((Integer) map.get(TTRequestExtraParams.PARAM_AD_LOAD_TIMEOUT)).intValue() : this.f4182b;
            Object obj = map.get("tt_ad_network_callback");
            this.f4183c = new PangleSplashAd(obj instanceof ITTAdapterSplashAdListener ? (ITTAdapterSplashAdListener) obj : null);
            this.f4183c.a();
        }
    }
}
