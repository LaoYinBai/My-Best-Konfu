package com.bytedance.msdk.adapter.pangle;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.listener.ITTAdapterInterstitialListener;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.TTAdConstant;
import com.bytedance.msdk.base.TTBaseAd;
import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTNativeExpressAd;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class PangleInterstitialAdapter extends TTAbsAdLoaderAdapter {
    public static final String TAG = "PangleInterstitialAdapter";

    /* renamed from: a, reason: collision with root package name */
    private PangleExpressInterstitialAd f4143a;

    /* renamed from: b, reason: collision with root package name */
    private Context f4144b;

    class PangleExpressInterstitialAd extends TTBaseAd {

        /* renamed from: a, reason: collision with root package name */
        TTNativeExpressAd.AdInteractionListener f4145a = new TTNativeExpressAd.AdInteractionListener() { // from class: com.bytedance.msdk.adapter.pangle.PangleInterstitialAdapter.PangleExpressInterstitialAd.2
            @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressAdInteractionListener
            public void onAdClicked(View view, int i) {
                if (((TTBaseAd) PangleExpressInterstitialAd.this).mTTAdatperCallback instanceof ITTAdapterInterstitialListener) {
                    PangleExpressInterstitialAd.this.a().onInterstitialAdClick();
                }
            }

            @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.AdInteractionListener
            public void onAdDismiss() {
                if (((TTBaseAd) PangleExpressInterstitialAd.this).mTTAdatperCallback instanceof ITTAdapterInterstitialListener) {
                    PangleExpressInterstitialAd.this.a().onInterstitialClosed();
                }
            }

            @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressAdInteractionListener
            public void onAdShow(View view, int i) {
                if (((TTBaseAd) PangleExpressInterstitialAd.this).mTTAdatperCallback instanceof ITTAdapterInterstitialListener) {
                    PangleExpressInterstitialAd.this.a().onInterstitialShow();
                }
            }

            @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressAdInteractionListener
            public void onRenderFail(View view, String str, int i) {
                Logger.e("TTMediationSDK_interisitial", TTLogUtil.getTagThirdLevelById(PangleInterstitialAdapter.this.getAdapterRit(), PangleInterstitialAdapter.this.getAdSlotId()) + "PangleExpressInterstitialAd onRenderFail -> code=" + i + ",msg=" + str);
                PangleInterstitialAdapter.this.notifyAdFailed(PangleAdapterUtils.buildAdError(i, str));
            }

            @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressAdInteractionListener
            public void onRenderSuccess(View view, float f, float f2) {
                PangleExpressInterstitialAd.this.d = true;
                Logger.d("TTMediationSDK_interisitial", TTLogUtil.getTagThirdLevelById(PangleInterstitialAdapter.this.getAdapterRit(), PangleInterstitialAdapter.this.getAdSlotId()) + "穿山甲SDK--插屏-onRenderSucces");
                PangleExpressInterstitialAd pangleExpressInterstitialAd = PangleExpressInterstitialAd.this;
                PangleInterstitialAdapter.this.notifyAdLoaded(pangleExpressInterstitialAd);
            }
        };

        /* renamed from: c, reason: collision with root package name */
        private TTNativeExpressAd f4147c;
        private boolean d;

        PangleExpressInterstitialAd(ITTAdapterInterstitialListener iTTAdapterInterstitialListener) {
            this.mTTAdatperCallback = iTTAdapterInterstitialListener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ITTAdapterInterstitialListener a() {
            return (ITTAdapterInterstitialListener) this.mTTAdatperCallback;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public long getAdId() {
            TTNativeExpressAd tTNativeExpressAd = this.f4147c;
            return tTNativeExpressAd != null ? PangleAdapterUtils.getAdId(tTNativeExpressAd.getMediaExtraInfo()) : super.getAdId();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return ((TTAbsAdLoaderAdapter) PangleInterstitialAdapter.this).mAdSlot.getAdType();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public long getCreativeId() {
            TTNativeExpressAd tTNativeExpressAd = this.f4147c;
            return tTNativeExpressAd != null ? PangleAdapterUtils.getCreativeId(tTNativeExpressAd.getMediaExtraInfo()) : super.getCreativeId();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public String getReqId() {
            TTNativeExpressAd tTNativeExpressAd = this.f4147c;
            return tTNativeExpressAd != null ? PangleAdapterUtils.getReqId(tTNativeExpressAd.getMediaExtraInfo()) : super.getReqId();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return this.f4147c == null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isReady() {
            return this.d;
        }

        public void loadAd() {
            TTAdNative tTAdNativeCreateAdNative = TTAdSdk.getAdManager().createAdNative(PangleInterstitialAdapter.this.f4144b);
            AdSlot.Builder builderBuildPangleAdSlot = PangleAdapterUtils.buildPangleAdSlot(((TTAbsAdLoaderAdapter) PangleInterstitialAdapter.this).mAdSlot, PangleInterstitialAdapter.this.getAdSlotId(), ((TTAbsAdLoaderAdapter) PangleInterstitialAdapter.this).mWaterfallAbTestParam, PangleInterstitialAdapter.this.getClientReqId(), PangleInterstitialAdapter.this.getAdm(), false);
            builderBuildPangleAdSlot.setExpressViewAcceptedSize(((TTAbsAdLoaderAdapter) PangleInterstitialAdapter.this).mAdSlot.getImgAcceptedWidth(), ((TTAbsAdLoaderAdapter) PangleInterstitialAdapter.this).mAdSlot.getImgAcceptedHeight());
            tTAdNativeCreateAdNative.loadInteractionExpressAd(builderBuildPangleAdSlot.build(), new TTAdNative.NativeExpressAdListener() { // from class: com.bytedance.msdk.adapter.pangle.PangleInterstitialAdapter.PangleExpressInterstitialAd.1
                @Override // com.bytedance.sdk.openadsdk.TTAdNative.NativeExpressAdListener, com.bytedance.sdk.openadsdk.common.CommonListener
                public void onError(int i, String str) {
                    PangleInterstitialAdapter.this.notifyAdFailed(PangleAdapterUtils.buildAdError(i, str));
                }

                @Override // com.bytedance.sdk.openadsdk.TTAdNative.NativeExpressAdListener
                public void onNativeExpressAdLoad(List<TTNativeExpressAd> list) {
                    Map<String, Object> mediaExtraInfo;
                    if (list == null || list.size() == 0) {
                        PangleInterstitialAdapter.this.notifyAdFailed(PangleAdapterUtils.buildAdError(AdError.ERROR_CODE_NO_AD, AdError.getMessage(AdError.ERROR_CODE_NO_AD)));
                        return;
                    }
                    Logger.d("TTMediationSDK_interisitial", TTLogUtil.getTagThirdLevelById(PangleInterstitialAdapter.this.getAdapterRit(), PangleInterstitialAdapter.this.getAdSlotId()) + "穿山甲SDK--插屏-onNativeExpressAdLoad");
                    PangleExpressInterstitialAd.this.f4147c = list.get(0);
                    PangleExpressInterstitialAd.this.setExpressAd(true);
                    PangleExpressInterstitialAd pangleExpressInterstitialAd = PangleExpressInterstitialAd.this;
                    pangleExpressInterstitialAd.setInteractionType(pangleExpressInterstitialAd.f4147c.getInteractionType());
                    if (PangleInterstitialAdapter.this.isClientBidding() && (mediaExtraInfo = PangleExpressInterstitialAd.this.f4147c.getMediaExtraInfo()) != null) {
                        double value = PangleAdapterUtils.getValue(mediaExtraInfo.get("price"));
                        Logger.d("TTMediationSDK_ECMP", TTLogUtil.getTagThirdLevelById(PangleInterstitialAdapter.this.getAdapterRit(), PangleInterstitialAdapter.this.getAdSlotId()) + "pangle 插屏 返回的 cpm价格：" + value);
                        PangleExpressInterstitialAd pangleExpressInterstitialAd2 = PangleExpressInterstitialAd.this;
                        if (value <= 0.0d) {
                            value = 0.0d;
                        }
                        pangleExpressInterstitialAd2.setCpm(value);
                    }
                    PangleExpressInterstitialAd.this.f4147c.setExpressInteractionListener(PangleExpressInterstitialAd.this.f4145a);
                    PangleExpressInterstitialAd.this.f4147c.render();
                }
            });
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            TTNativeExpressAd tTNativeExpressAd = this.f4147c;
            if (tTNativeExpressAd != null) {
                tTNativeExpressAd.setExpressInteractionListener((TTNativeExpressAd.AdInteractionListener) null);
                this.f4147c.setDownloadListener(null);
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void showAd(Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map) {
            TTNativeExpressAd tTNativeExpressAd = this.f4147c;
            if (tTNativeExpressAd != null) {
                tTNativeExpressAd.showInteractionExpressAd(activity);
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
        this.f4144b = context;
        if (map != null) {
            Object obj = map.get("tt_ad_network_callback");
            this.f4143a = new PangleExpressInterstitialAd(obj instanceof ITTAdapterInterstitialListener ? (ITTAdapterInterstitialListener) obj : null);
            this.f4143a.loadAd();
        }
    }
}
