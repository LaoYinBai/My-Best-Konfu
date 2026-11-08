package com.bytedance.msdk.adapter.pangle;

import android.app.Activity;
import android.content.Context;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.listener.ITTAdapterFullVideoAdListener;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.TTAdConstant;
import com.bytedance.msdk.base.TTBaseAd;
import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTFullScreenVideoAd;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class PangleFullVideoAdapter extends TTAbsAdLoaderAdapter {
    public static final String TAG = "PangleFullVideoAdapter";

    /* renamed from: a, reason: collision with root package name */
    private Context f4137a;

    class PangleFullVideoAd extends TTBaseAd {

        /* renamed from: a, reason: collision with root package name */
        TTAdNative.FullScreenVideoAdListener f4138a = new TTAdNative.FullScreenVideoAdListener() { // from class: com.bytedance.msdk.adapter.pangle.PangleFullVideoAdapter.PangleFullVideoAd.1
            @Override // com.bytedance.sdk.openadsdk.TTAdNative.FullScreenVideoAdListener, com.bytedance.sdk.openadsdk.common.CommonListener
            public void onError(int i, String str) {
                PangleFullVideoAd.this.d = false;
                PangleFullVideoAdapter.this.notifyAdFailed(PangleAdapterUtils.buildAdError(i, str));
            }

            @Override // com.bytedance.sdk.openadsdk.TTAdNative.FullScreenVideoAdListener
            public void onFullScreenVideoAdLoad(TTFullScreenVideoAd tTFullScreenVideoAd) {
                if (tTFullScreenVideoAd == null) {
                    PangleFullVideoAdapter.this.notifyAdFailed(PangleAdapterUtils.buildAdError(AdError.ERROR_CODE_NO_AD, AdError.AD_NO_FILL));
                    return;
                }
                PangleFullVideoAd.this.f4140c = tTFullScreenVideoAd;
                PangleFullVideoAd.this.setExpressAd(true);
                PangleFullVideoAd pangleFullVideoAd = PangleFullVideoAd.this;
                pangleFullVideoAd.setInteractionType(pangleFullVideoAd.f4140c.getInteractionType());
                Map<String, Object> mediaExtraInfo = PangleFullVideoAd.this.f4140c.getMediaExtraInfo();
                if (PangleFullVideoAdapter.this.isClientBidding() && mediaExtraInfo != null) {
                    double value = PangleAdapterUtils.getValue(mediaExtraInfo.get("price"));
                    Logger.d("TTMediationSDK_ECMP", TTLogUtil.getTagThirdLevelById(PangleFullVideoAdapter.this.getAdapterRit(), PangleFullVideoAdapter.this.getAdSlotId()) + "pangle 全屏 返回的 cpm价格：" + value);
                    PangleFullVideoAd pangleFullVideoAd2 = PangleFullVideoAd.this;
                    if (value <= 0.0d) {
                        value = 0.0d;
                    }
                    pangleFullVideoAd2.setCpm(value);
                }
                if (mediaExtraInfo != null) {
                    Object obj = mediaExtraInfo.get("materialMetaIsFromPreload");
                    if (obj instanceof Boolean) {
                        PangleFullVideoAd.this.e = ((Boolean) obj).booleanValue();
                        Logger.d("TTMediationSDK", "pangle 全屏 返回的adnPreload：" + PangleFullVideoAd.this.e);
                    }
                }
                PangleFullVideoAd.this.f4140c.setFullScreenVideoAdInteractionListener(new TTFullScreenVideoAd.FullScreenVideoAdInteractionListener() { // from class: com.bytedance.msdk.adapter.pangle.PangleFullVideoAdapter.PangleFullVideoAd.1.1
                    @Override // com.bytedance.sdk.openadsdk.TTFullScreenVideoAd.FullScreenVideoAdInteractionListener
                    public void onAdClose() {
                        if (((TTBaseAd) PangleFullVideoAd.this).mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener) {
                            PangleFullVideoAd.this.a().onFullVideoAdClosed();
                        }
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTFullScreenVideoAd.FullScreenVideoAdInteractionListener
                    public void onAdShow() {
                        if (((TTBaseAd) PangleFullVideoAd.this).mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener) {
                            PangleFullVideoAd.this.a().onFullVideoAdShow();
                        }
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTFullScreenVideoAd.FullScreenVideoAdInteractionListener
                    public void onAdVideoBarClick() {
                        if (((TTBaseAd) PangleFullVideoAd.this).mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener) {
                            PangleFullVideoAd.this.a().onFullVideoAdClick();
                        }
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTFullScreenVideoAd.FullScreenVideoAdInteractionListener
                    public void onSkippedVideo() {
                        if (((TTBaseAd) PangleFullVideoAd.this).mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener) {
                            PangleFullVideoAd.this.a().onSkippedVideo();
                        }
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTFullScreenVideoAd.FullScreenVideoAdInteractionListener
                    public void onVideoComplete() {
                        if (((TTBaseAd) PangleFullVideoAd.this).mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener) {
                            PangleFullVideoAd.this.a().onVideoComplete();
                        }
                    }
                });
                PangleFullVideoAd.this.d = true;
                PangleFullVideoAd pangleFullVideoAd3 = PangleFullVideoAd.this;
                PangleFullVideoAdapter.this.notifyAdLoaded(pangleFullVideoAd3);
            }

            @Override // com.bytedance.sdk.openadsdk.TTAdNative.FullScreenVideoAdListener
            public void onFullScreenVideoCached() {
            }

            @Override // com.bytedance.sdk.openadsdk.TTAdNative.FullScreenVideoAdListener
            public void onFullScreenVideoCached(TTFullScreenVideoAd tTFullScreenVideoAd) {
                PangleFullVideoAd.this.d = true;
                PangleFullVideoAd pangleFullVideoAd = PangleFullVideoAd.this;
                PangleFullVideoAdapter.this.notifyAdVideoCache(pangleFullVideoAd, null);
            }
        };

        /* renamed from: c, reason: collision with root package name */
        private TTFullScreenVideoAd f4140c;
        private boolean d;
        private boolean e;

        PangleFullVideoAd(ITTAdapterFullVideoAdListener iTTAdapterFullVideoAdListener) {
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
        public boolean adnHasAdVideoCachedApi() {
            return true;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public long getAdId() {
            TTFullScreenVideoAd tTFullScreenVideoAd = this.f4140c;
            return tTFullScreenVideoAd != null ? PangleAdapterUtils.getAdId(tTFullScreenVideoAd.getMediaExtraInfo()) : super.getAdId();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return ((TTAbsAdLoaderAdapter) PangleFullVideoAdapter.this).mAdSlot.getAdType();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public long getCreativeId() {
            TTFullScreenVideoAd tTFullScreenVideoAd = this.f4140c;
            return tTFullScreenVideoAd != null ? PangleAdapterUtils.getCreativeId(tTFullScreenVideoAd.getMediaExtraInfo()) : super.getCreativeId();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public String getReqId() {
            TTFullScreenVideoAd tTFullScreenVideoAd = this.f4140c;
            return tTFullScreenVideoAd != null ? PangleAdapterUtils.getReqId(tTFullScreenVideoAd.getMediaExtraInfo()) : super.getReqId();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return this.f4140c == null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isAdExpired() {
            long jCurrentTimeMillis = System.currentTimeMillis();
            TTFullScreenVideoAd tTFullScreenVideoAd = this.f4140c;
            return tTFullScreenVideoAd == null || jCurrentTimeMillis > tTFullScreenVideoAd.getExpirationTimestamp();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isAdnPreload() {
            return this.e;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isReady() {
            return this.d;
        }

        public void loadAd() {
            TTAdNative tTAdNativeCreateAdNative = TTAdSdk.getAdManager().createAdNative(PangleFullVideoAdapter.this.f4137a);
            AdSlot.Builder builderBuildPangleAdSlot = PangleAdapterUtils.buildPangleAdSlot(((TTAbsAdLoaderAdapter) PangleFullVideoAdapter.this).mAdSlot, PangleFullVideoAdapter.this.getAdSlotId(), ((TTAbsAdLoaderAdapter) PangleFullVideoAdapter.this).mWaterfallAbTestParam, PangleFullVideoAdapter.this.getClientReqId(), PangleFullVideoAdapter.this.getAdm(), false);
            builderBuildPangleAdSlot.setImageAcceptedSize(1080, 1920).setOrientation(((TTAbsAdLoaderAdapter) PangleFullVideoAdapter.this).mAdSlot.getOrientation());
            tTAdNativeCreateAdNative.loadFullScreenVideoAd(builderBuildPangleAdSlot.build(), this.f4138a);
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            TTFullScreenVideoAd tTFullScreenVideoAd = this.f4140c;
            if (tTFullScreenVideoAd != null) {
                tTFullScreenVideoAd.setFullScreenVideoAdInteractionListener(null);
                this.f4140c.setDownloadListener(null);
                this.f4140c = null;
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void showAd(Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map) {
            if (!isReady() || this.f4140c == null) {
                return;
            }
            TTAdConstant.RitScenes ritScenes = PangleAdapterUtils.getRitScenes(map);
            String customRitScenes = PangleAdapterUtils.getCustomRitScenes(map);
            if (ritScenes != null) {
                this.f4140c.showFullScreenVideoAd(activity, ritScenes, customRitScenes);
            } else {
                this.f4140c.showFullScreenVideoAd(activity);
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
        this.f4137a = context;
        if (map != null) {
            Object obj = map.get("tt_ad_network_callback");
            new PangleFullVideoAd(obj instanceof ITTAdapterFullVideoAdListener ? (ITTAdapterFullVideoAdListener) obj : null).loadAd();
        }
    }
}
