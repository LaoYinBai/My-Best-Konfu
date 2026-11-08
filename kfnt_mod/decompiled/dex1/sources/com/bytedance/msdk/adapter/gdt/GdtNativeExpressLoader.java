package com.bytedance.msdk.adapter.gdt;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import com.alipay.sdk.util.f;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.listener.ITTAdapterNativeExpressAdListener;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdErrorUtil;
import com.bytedance.msdk.api.GDTExtraOption;
import com.bytedance.msdk.api.TTVideoOption;
import com.bytedance.msdk.api.v2.GMAdConstant;
import com.bytedance.msdk.api.v2.GMDislikeCallback;
import com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAdListener;
import com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeExpressAdListener;
import com.bytedance.msdk.base.TTBaseAd;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import com.qq.e.ads.cfg.DownAPPConfirmPolicy;
import com.qq.e.ads.cfg.VideoOption;
import com.qq.e.ads.nativ.ADSize;
import com.qq.e.ads.nativ.NativeExpressAD;
import com.qq.e.ads.nativ.NativeExpressADView;
import com.qq.e.ads.nativ.NativeExpressMediaListener;
import com.qq.e.comm.pi.AdData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GdtNativeExpressLoader {
    private static Map<NativeExpressADView, ITTAdapterNativeExpressAdListener> j = new HashMap();

    /* renamed from: a, reason: collision with root package name */
    private String f3992a;

    /* renamed from: b, reason: collision with root package name */
    private int f3993b;

    /* renamed from: c, reason: collision with root package name */
    private Context f3994c;
    private TTVideoOption d;
    private GDTExtraOption e;
    private NativeExpressAD f;
    private TTAbsAdLoaderAdapter g;
    private int h;
    private final NativeExpressAD.NativeExpressADListener i = new NativeExpressAD.NativeExpressADListener() { // from class: com.bytedance.msdk.adapter.gdt.GdtNativeExpressLoader.1
        public void onADClicked(NativeExpressADView nativeExpressADView) {
            ITTAdapterNativeExpressAdListener iTTAdapterNativeExpressAdListener;
            if (GdtNativeExpressLoader.j == null || (iTTAdapterNativeExpressAdListener = (ITTAdapterNativeExpressAdListener) GdtNativeExpressLoader.j.get(nativeExpressADView)) == null) {
                return;
            }
            iTTAdapterNativeExpressAdListener.onAdClick();
        }

        public void onADCloseOverlay(NativeExpressADView nativeExpressADView) {
        }

        public void onADClosed(NativeExpressADView nativeExpressADView) {
            if (GdtNativeExpressLoader.j != null) {
                ITTAdapterNativeExpressAdListener iTTAdapterNativeExpressAdListener = (ITTAdapterNativeExpressAdListener) GdtNativeExpressLoader.j.get(nativeExpressADView);
                if (iTTAdapterNativeExpressAdListener instanceof TTExpressAd) {
                    ((TTExpressAd) iTTAdapterNativeExpressAdListener).closeAd();
                }
                GdtNativeExpressLoader.j.remove(nativeExpressADView);
            }
            if (nativeExpressADView != null) {
                nativeExpressADView.destroy();
            }
        }

        public void onADExposure(NativeExpressADView nativeExpressADView) {
            ITTAdapterNativeExpressAdListener iTTAdapterNativeExpressAdListener;
            if (GdtNativeExpressLoader.j == null || (iTTAdapterNativeExpressAdListener = (ITTAdapterNativeExpressAdListener) GdtNativeExpressLoader.j.get(nativeExpressADView)) == null) {
                return;
            }
            iTTAdapterNativeExpressAdListener.onAdShow();
        }

        public void onADLeftApplication(NativeExpressADView nativeExpressADView) {
        }

        public void onADLoaded(List<NativeExpressADView> list) {
            if (list == null || list.size() == 0) {
                GdtNativeExpressLoader.this.g.notifyAdFailed(new AdError(AdError.ERROR_CODE_NO_AD, AdError.AD_NO_FILL, AdError.ERROR_CODE_NO_AD, AdError.AD_NO_FILL));
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (NativeExpressADView nativeExpressADView : list) {
                if (nativeExpressADView != null) {
                    TTExpressAd tTExpressAd = GdtNativeExpressLoader.this.new TTExpressAd(nativeExpressADView);
                    GdtNativeExpressLoader.j.put(nativeExpressADView, tTExpressAd);
                    arrayList.add(tTExpressAd);
                }
            }
            GdtNativeExpressLoader.this.g.notifyAdLoaded(arrayList);
        }

        public void onADOpenOverlay(NativeExpressADView nativeExpressADView) {
        }

        public void onNoAD(com.qq.e.comm.util.AdError adError) {
            if (adError != null) {
                GdtNativeExpressLoader.this.g.notifyAdFailed(AdErrorUtil.getGdtError(adError.getErrorCode(), adError.getErrorMsg()));
            }
        }

        public void onRenderFail(NativeExpressADView nativeExpressADView) {
            ITTAdapterNativeExpressAdListener iTTAdapterNativeExpressAdListener;
            if (GdtNativeExpressLoader.j == null || (iTTAdapterNativeExpressAdListener = (ITTAdapterNativeExpressAdListener) GdtNativeExpressLoader.j.get(nativeExpressADView)) == null) {
                return;
            }
            iTTAdapterNativeExpressAdListener.onRenderFail(nativeExpressADView, AdError.getMessage(106), 106);
        }

        public void onRenderSuccess(NativeExpressADView nativeExpressADView) {
            ITTAdapterNativeExpressAdListener iTTAdapterNativeExpressAdListener;
            if (GdtNativeExpressLoader.j == null || (iTTAdapterNativeExpressAdListener = (ITTAdapterNativeExpressAdListener) GdtNativeExpressLoader.j.get(nativeExpressADView)) == null) {
                return;
            }
            iTTAdapterNativeExpressAdListener.onRenderSuccess(-1.0f, -2.0f);
        }
    };

    class TTExpressAd extends TTBaseAd implements ITTAdapterNativeExpressAdListener {

        /* renamed from: a, reason: collision with root package name */
        NativeExpressADView f3996a;

        /* renamed from: b, reason: collision with root package name */
        GMDislikeCallback f3997b;

        /* renamed from: c, reason: collision with root package name */
        private final NativeExpressMediaListener f3998c = new NativeExpressMediaListener() { // from class: com.bytedance.msdk.adapter.gdt.GdtNativeExpressLoader.TTExpressAd.1
            public void onVideoCached(NativeExpressADView nativeExpressADView) {
                Log.i("GdtNativeExpressLoader", "onVideoCached");
            }

            public void onVideoComplete(NativeExpressADView nativeExpressADView) {
                if (((TTBaseAd) TTExpressAd.this).mTTVideoListener != null) {
                    ((TTBaseAd) TTExpressAd.this).mTTVideoListener.onVideoCompleted();
                }
            }

            public void onVideoError(NativeExpressADView nativeExpressADView, com.qq.e.comm.util.AdError adError) {
                if (((TTBaseAd) TTExpressAd.this).mTTVideoListener != null) {
                    ((TTBaseAd) TTExpressAd.this).mTTVideoListener.onVideoError(AdErrorUtil.getGdtError(adError.getErrorCode(), adError.getErrorMsg()));
                }
            }

            public void onVideoInit(NativeExpressADView nativeExpressADView) {
                Log.i("GdtNativeExpressLoader", "onVideoInit: " + GdtNativeExpressLoader.this.a((AdData.VideoPlayer) nativeExpressADView.getBoundData().getProperty(AdData.VideoPlayer.class)));
            }

            public void onVideoLoading(NativeExpressADView nativeExpressADView) {
                Log.i("GdtNativeExpressLoader", "onVideoLoading");
            }

            public void onVideoPageClose(NativeExpressADView nativeExpressADView) {
            }

            public void onVideoPageOpen(NativeExpressADView nativeExpressADView) {
            }

            public void onVideoPause(NativeExpressADView nativeExpressADView) {
                if (((TTBaseAd) TTExpressAd.this).mTTVideoListener != null) {
                    ((TTBaseAd) TTExpressAd.this).mTTVideoListener.onVideoPause();
                }
            }

            public void onVideoReady(NativeExpressADView nativeExpressADView, long j) {
            }

            public void onVideoStart(NativeExpressADView nativeExpressADView) {
                if (((TTBaseAd) TTExpressAd.this).mTTVideoListener != null) {
                    ((TTBaseAd) TTExpressAd.this).mTTVideoListener.onVideoStart();
                }
            }
        };

        TTExpressAd(NativeExpressADView nativeExpressADView) {
            StringBuilder sb;
            this.f3996a = nativeExpressADView;
            AdData boundData = nativeExpressADView.getBoundData();
            if (boundData.getAdPatternType() == 2) {
                nativeExpressADView.preloadVideo();
                nativeExpressADView.setMediaListener(this.f3998c);
                setImageMode(5);
            } else if (boundData.getAdPatternType() == 4 || boundData.getAdPatternType() == 1 || boundData.getAdPatternType() != 3) {
                setImageMode(3);
            } else {
                setImageMode(4);
            }
            setExpressAd(true);
            setTitle(boundData.getTitle());
            setAdDescription(boundData.getDesc());
            setInteractionType(3);
            if (GdtNativeExpressLoader.this.g != null) {
                if (GdtNativeExpressLoader.this.g.isClientBidding()) {
                    setCpm(boundData.getECPM() != -1 ? boundData.getECPM() : 0.0d);
                    Logger.d("");
                    sb = new StringBuilder();
                    sb.append("GDT_clientBidding 模板Native 返回的 cpm价格：");
                    sb.append(boundData.getECPM());
                } else {
                    if (!GdtNativeExpressLoader.this.g.isMultiBidding()) {
                        return;
                    }
                    setLevelTag(boundData.getECPMLevel());
                    Logger.d("");
                    sb = new StringBuilder();
                    sb.append("GDT_多阶底价 模板Native 返回的 价格标签：");
                    sb.append(boundData.getECPMLevel());
                }
                Logger.d("TTMediationSDK_ECMP", sb.toString());
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void bidLoseNotify(Map<String, Object> map) {
            if (!isClientBiddingAd() || this.f3996a == null || map == null) {
                return;
            }
            try {
                Object obj = map.get("bidding_lose_reason");
                if (obj instanceof GMAdConstant.BiddingLossReason) {
                    this.f3996a.sendLossNotification(0, GDTAdapterUtils.getBiddingLossReason((GMAdConstant.BiddingLossReason) obj), (String) null);
                }
            } catch (Exception unused) {
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void bidWinNotify(Map<String, Object> map) {
            NativeExpressADView nativeExpressADView;
            if (isClientBiddingAd() && (nativeExpressADView = this.f3996a) != null) {
                try {
                    nativeExpressADView.sendWinNotification((int) getCpm());
                } catch (Exception unused) {
                }
            }
        }

        public void closeAd() {
            GMDislikeCallback gMDislikeCallback = this.f3997b;
            if (gMDislikeCallback != null) {
                gMDislikeCallback.onSelected(-1, "ad close !");
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return 5;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public View getAdView() {
            return this.f3996a;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return this.f3996a == null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDislike() {
            return true;
        }

        @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAdListener
        public void onAdClick() {
            GMNativeAdListener gMNativeAdListener = this.mTTNativeAdListener;
            if (gMNativeAdListener != null) {
                gMNativeAdListener.onAdClick();
            }
        }

        @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAdListener
        public void onAdShow() {
            GMNativeAdListener gMNativeAdListener = this.mTTNativeAdListener;
            if (gMNativeAdListener != null) {
                gMNativeAdListener.onAdShow();
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            NativeExpressADView nativeExpressADView = this.f3996a;
            if (nativeExpressADView != null) {
                nativeExpressADView.destroy();
            }
            if (GdtNativeExpressLoader.j != null) {
                GdtNativeExpressLoader.j.remove(this.f3996a);
                this.f3996a = null;
            }
            this.f3997b = null;
            super.onDestroy();
        }

        @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeExpressAdListener
        public void onRenderFail(View view, String str, int i) {
            GMNativeAdListener gMNativeAdListener = this.mTTNativeAdListener;
            if (gMNativeAdListener instanceof GMNativeExpressAdListener) {
                ((GMNativeExpressAdListener) gMNativeAdListener).onRenderFail(view, str, i);
            }
        }

        @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeExpressAdListener
        public void onRenderSuccess(float f, float f2) {
            GMNativeAdListener gMNativeAdListener = this.mTTNativeAdListener;
            if (gMNativeAdListener instanceof GMNativeExpressAdListener) {
                ((GMNativeExpressAdListener) gMNativeAdListener).onRenderSuccess(f, f2);
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void render() {
            NativeExpressADView nativeExpressADView = this.f3996a;
            if (nativeExpressADView != null) {
                nativeExpressADView.render();
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void setDislikeCallback(Activity activity, GMDislikeCallback gMDislikeCallback) {
            this.f3997b = gMDislikeCallback;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(AdData.VideoPlayer videoPlayer) {
        if (videoPlayer == null) {
            return null;
        }
        return "{state:" + videoPlayer.getVideoState() + IWeiboService.Scope.EMPTY_SCOPE + "duration:" + videoPlayer.getDuration() + IWeiboService.Scope.EMPTY_SCOPE + "position:" + videoPlayer.getCurrentPosition() + f.d;
    }

    private ADSize b() {
        ADSize aDSize = new ADSize(-1, -2);
        int i = this.h;
        return i > 0 ? new ADSize(i, -2) : aDSize;
    }

    GdtNativeExpressLoader a(int i) {
        this.f3993b = i;
        return this;
    }

    GdtNativeExpressLoader a(TTVideoOption tTVideoOption) {
        this.d = tTVideoOption;
        return this;
    }

    GdtNativeExpressLoader a(String str) {
        this.f3992a = str;
        return this;
    }

    GdtNativeExpressLoader b(int i) {
        return this;
    }

    GdtNativeExpressLoader c(int i) {
        this.h = i;
        return this;
    }

    public void loadAd(Context context, boolean z, TTAbsAdLoaderAdapter tTAbsAdLoaderAdapter) {
        NativeExpressAD nativeExpressAD;
        DownAPPConfirmPolicy downAPPConfirmPolicy;
        if (context == null || tTAbsAdLoaderAdapter == null) {
            return;
        }
        this.f3994c = context;
        this.g = tTAbsAdLoaderAdapter;
        this.f = new NativeExpressAD(this.f3994c, b(), this.f3992a, this.i);
        TTVideoOption tTVideoOption = this.d;
        if (tTVideoOption != null) {
            this.e = tTVideoOption.getGDTExtraOption();
        }
        GDTExtraOption gDTExtraOption = this.e;
        if (gDTExtraOption != null) {
            this.f.setMinVideoDuration(gDTExtraOption.getGDTMinVideoDuration());
            this.f.setMaxVideoDuration(this.e.getGDTMaxVideoDuration());
        }
        VideoOption videoOption = GDTAdapterUtils.getVideoOption(this.e);
        this.f.setVideoOption(videoOption);
        this.f.setVideoPlayPolicy(GDTAdapterUtils.getVideoPlayPolicy(videoOption.getAutoPlayPolicy(), this.f3994c));
        if (this.e.getDownAPPConfirmPolicy() != 0) {
            if (this.e.getDownAPPConfirmPolicy() == 1) {
                nativeExpressAD = this.f;
                downAPPConfirmPolicy = DownAPPConfirmPolicy.NOConfirm;
            }
            this.f.loadAD(this.f3993b);
        }
        nativeExpressAD = this.f;
        downAPPConfirmPolicy = DownAPPConfirmPolicy.Default;
        nativeExpressAD.setDownAPPConfirmPolicy(downAPPConfirmPolicy);
        this.f.loadAD(this.f3993b);
    }
}
