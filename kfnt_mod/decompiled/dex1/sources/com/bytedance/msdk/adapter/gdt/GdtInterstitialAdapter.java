package com.bytedance.msdk.adapter.gdt;

import android.app.Activity;
import android.content.Context;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.listener.ITTAdapterInterstitialListener;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.api.AdErrorUtil;
import com.bytedance.msdk.api.GDTExtraOption;
import com.bytedance.msdk.api.TTAdConstant;
import com.bytedance.msdk.api.TTGlobalConfig;
import com.bytedance.msdk.api.TTVideoOption;
import com.bytedance.msdk.api.v2.GMAdConstant;
import com.bytedance.msdk.base.TTBaseAd;
import com.qq.e.ads.cfg.VideoOption;
import com.qq.e.ads.interstitial2.UnifiedInterstitialAD;
import com.qq.e.ads.interstitial2.UnifiedInterstitialADListener;
import com.qq.e.comm.managers.status.SDKStatus;
import com.qq.e.comm.util.AdError;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GdtInterstitialAdapter extends TTAbsAdLoaderAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f3983a;

    /* renamed from: b, reason: collision with root package name */
    private TTVideoOption f3984b;

    /* renamed from: c, reason: collision with root package name */
    private GDTExtraOption f3985c;
    private int d;
    private int e;

    class GdtIntersitialAd extends TTBaseAd {

        /* renamed from: a, reason: collision with root package name */
        private UnifiedInterstitialAD f3986a;

        /* renamed from: b, reason: collision with root package name */
        private boolean f3987b;

        /* renamed from: c, reason: collision with root package name */
        UnifiedInterstitialADListener f3988c = new UnifiedInterstitialADListener() { // from class: com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter.GdtIntersitialAd.1
            public void onADClicked() {
                if (((TTBaseAd) GdtIntersitialAd.this).mTTAdatperCallback instanceof ITTAdapterInterstitialListener) {
                    GdtIntersitialAd.this.b().onInterstitialAdClick();
                }
            }

            public void onADClosed() {
                if (((TTBaseAd) GdtIntersitialAd.this).mTTAdatperCallback instanceof ITTAdapterInterstitialListener) {
                    GdtIntersitialAd.this.b().onInterstitialClosed();
                }
            }

            public void onADExposure() {
                if (((TTBaseAd) GdtIntersitialAd.this).mTTAdatperCallback instanceof ITTAdapterInterstitialListener) {
                    GdtIntersitialAd.this.b().onInterstitialShow();
                }
            }

            public void onADLeftApplication() {
                if (((TTBaseAd) GdtIntersitialAd.this).mTTAdatperCallback instanceof ITTAdapterInterstitialListener) {
                    GdtIntersitialAd.this.b().onAdLeftApplication();
                }
            }

            public void onADOpened() {
                if (((TTBaseAd) GdtIntersitialAd.this).mTTAdatperCallback instanceof ITTAdapterInterstitialListener) {
                    GdtIntersitialAd.this.b().onAdOpened();
                }
            }

            /* JADX WARN: Removed duplicated region for block: B:15:0x00b8  */
            /* JADX WARN: Removed duplicated region for block: B:17:0x00bf  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onADReceive() {
                /*
                    r3 = this;
                    com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter$GdtIntersitialAd r0 = com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter.GdtIntersitialAd.this
                    r1 = 1
                    com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter.GdtIntersitialAd.a(r0, r1)
                    com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter$GdtIntersitialAd r0 = com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter.GdtIntersitialAd.this
                    com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter r0 = com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter.this
                    boolean r0 = r0.isClientBidding()
                    if (r0 == 0) goto L5d
                    com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter$GdtIntersitialAd r0 = com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter.GdtIntersitialAd.this
                    com.qq.e.ads.interstitial2.UnifiedInterstitialAD r1 = com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter.GdtIntersitialAd.d(r0)
                    int r1 = r1.getECPM()
                    r2 = -1
                    if (r1 == r2) goto L29
                    com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter$GdtIntersitialAd r1 = com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter.GdtIntersitialAd.this
                    com.qq.e.ads.interstitial2.UnifiedInterstitialAD r1 = com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter.GdtIntersitialAd.d(r1)
                    int r1 = r1.getECPM()
                    double r1 = (double) r1
                    goto L2b
                L29:
                    r1 = 0
                L2b:
                    r0.setCpm(r1)
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder
                    r0.<init>()
                    com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter$GdtIntersitialAd r1 = com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter.GdtIntersitialAd.this
                    com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter r1 = com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter.this
                    java.lang.String r1 = r1.getAdapterRit()
                    com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter$GdtIntersitialAd r2 = com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter.GdtIntersitialAd.this
                    com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter r2 = com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter.this
                    java.lang.String r2 = r2.getAdSlotId()
                    java.lang.String r1 = com.bytedance.msdk.adapter.util.TTLogUtil.getTagThirdLevelById(r1, r2)
                    r0.append(r1)
                    java.lang.String r1 = "GDT_clientBidding 插屏 返回的 cpm价格："
                    r0.append(r1)
                    com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter$GdtIntersitialAd r1 = com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter.GdtIntersitialAd.this
                    com.qq.e.ads.interstitial2.UnifiedInterstitialAD r1 = com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter.GdtIntersitialAd.d(r1)
                    int r1 = r1.getECPM()
                    r0.append(r1)
                    goto La2
                L5d:
                    com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter$GdtIntersitialAd r0 = com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter.GdtIntersitialAd.this
                    com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter r0 = com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter.this
                    boolean r0 = r0.isMultiBidding()
                    if (r0 == 0) goto Lab
                    com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter$GdtIntersitialAd r0 = com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter.GdtIntersitialAd.this
                    com.qq.e.ads.interstitial2.UnifiedInterstitialAD r1 = com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter.GdtIntersitialAd.d(r0)
                    java.lang.String r1 = r1.getECPMLevel()
                    r0.setLevelTag(r1)
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder
                    r0.<init>()
                    com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter$GdtIntersitialAd r1 = com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter.GdtIntersitialAd.this
                    com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter r1 = com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter.this
                    java.lang.String r1 = r1.getAdapterRit()
                    com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter$GdtIntersitialAd r2 = com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter.GdtIntersitialAd.this
                    com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter r2 = com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter.this
                    java.lang.String r2 = r2.getAdSlotId()
                    java.lang.String r1 = com.bytedance.msdk.adapter.util.TTLogUtil.getTagThirdLevelById(r1, r2)
                    r0.append(r1)
                    java.lang.String r1 = "GDT_多阶底价 插屏 返回的价格标签："
                    r0.append(r1)
                    com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter$GdtIntersitialAd r1 = com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter.GdtIntersitialAd.this
                    com.qq.e.ads.interstitial2.UnifiedInterstitialAD r1 = com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter.GdtIntersitialAd.d(r1)
                    java.lang.String r1 = r1.getECPMLevel()
                    r0.append(r1)
                La2:
                    java.lang.String r0 = r0.toString()
                    java.lang.String r1 = "TTMediationSDK_ECMP"
                    com.bytedance.msdk.adapter.util.Logger.d(r1, r0)
                Lab:
                    com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter$GdtIntersitialAd r0 = com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter.GdtIntersitialAd.this
                    com.qq.e.ads.interstitial2.UnifiedInterstitialAD r0 = com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter.GdtIntersitialAd.d(r0)
                    int r0 = r0.getAdPatternType()
                    r1 = 2
                    if (r0 != r1) goto Lbf
                    com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter$GdtIntersitialAd r0 = com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter.GdtIntersitialAd.this
                    r1 = 5
                Lbb:
                    r0.setImageMode(r1)
                    goto Lc3
                Lbf:
                    com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter$GdtIntersitialAd r0 = com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter.GdtIntersitialAd.this
                    r1 = 3
                    goto Lbb
                Lc3:
                    com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter$GdtIntersitialAd r0 = com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter.GdtIntersitialAd.this
                    com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter r1 = com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter.this
                    r1.notifyAdLoaded(r0)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.bytedance.msdk.adapter.gdt.GdtInterstitialAdapter.GdtIntersitialAd.AnonymousClass1.onADReceive():void");
            }

            public void onNoAD(AdError adError) {
                GdtIntersitialAd.this.f3987b = false;
                if (adError != null) {
                    GdtInterstitialAdapter.this.notifyAdFailed(AdErrorUtil.getGdtError(adError.getErrorCode(), adError.getErrorMsg()));
                } else {
                    GdtInterstitialAdapter.this.notifyAdFailed(AdErrorUtil.obtainAdError(0, com.bytedance.msdk.api.AdError.getMessage(0)));
                }
            }

            public void onRenderFail() {
            }

            public void onRenderSuccess() {
            }

            public void onVideoCached() {
            }
        };

        GdtIntersitialAd(ITTAdapterInterstitialListener iTTAdapterInterstitialListener) {
            this.mTTAdatperCallback = iTTAdapterInterstitialListener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ITTAdapterInterstitialListener b() {
            return (ITTAdapterInterstitialListener) this.mTTAdatperCallback;
        }

        void a() {
            Logger.d("TTMediationSDK", "GdtIntersitialAd load");
            if (!(GdtInterstitialAdapter.this.f3983a instanceof Activity)) {
                Logger.d("TTMediationSDK_interisitial", TTLogUtil.getTagThirdLevelById(GdtInterstitialAdapter.this.getAdapterRit(), GdtInterstitialAdapter.this.getAdSlotId()) + "GDT--插屏-传入mContext 不是 Activity 请求中断");
                return;
            }
            this.f3986a = new UnifiedInterstitialAD((Activity) GdtInterstitialAdapter.this.f3983a, GdtInterstitialAdapter.this.getAdSlotId(), this.f3988c);
            VideoOption videoOption = GDTAdapterUtils.getVideoOption(GdtInterstitialAdapter.this.f3985c);
            this.f3986a.setVideoOption(videoOption);
            if (GdtInterstitialAdapter.this.e > 0) {
                this.f3986a.setMaxVideoDuration(GdtInterstitialAdapter.this.e);
            }
            if (GdtInterstitialAdapter.this.d > 0) {
                this.f3986a.setMinVideoDuration(GdtInterstitialAdapter.this.d);
            }
            this.f3986a.setVideoPlayPolicy(GDTAdapterUtils.getVideoPlayPolicy(videoOption.getAutoPlayPolicy(), GdtInterstitialAdapter.this.f3983a));
            this.f3986a.loadAD();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void bidLoseNotify(Map<String, Object> map) {
            if (!isClientBiddingAd() || this.f3986a == null || map == null) {
                return;
            }
            try {
                Object obj = map.get("bidding_lose_reason");
                if (obj instanceof GMAdConstant.BiddingLossReason) {
                    this.f3986a.sendLossNotification(0, GDTAdapterUtils.getBiddingLossReason((GMAdConstant.BiddingLossReason) obj), (String) null);
                }
            } catch (Exception unused) {
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void bidWinNotify(Map<String, Object> map) {
            UnifiedInterstitialAD unifiedInterstitialAD;
            if (isClientBiddingAd() && (unifiedInterstitialAD = this.f3986a) != null) {
                try {
                    unifiedInterstitialAD.sendWinNotification((int) getCpm());
                } catch (Exception unused) {
                }
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return ((TTAbsAdLoaderAdapter) GdtInterstitialAdapter.this).mAdSlot.getAdType();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return this.f3986a == null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isAdCacheReady() {
            UnifiedInterstitialAD unifiedInterstitialAD = this.f3986a;
            return unifiedInterstitialAD != null && unifiedInterstitialAD.isValid();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isReady() {
            return this.f3987b;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            UnifiedInterstitialAD unifiedInterstitialAD = this.f3986a;
            if (unifiedInterstitialAD != null) {
                unifiedInterstitialAD.destroy();
                this.f3986a = null;
            }
            this.mTTAdatperCallback = null;
            super.onDestroy();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void showAd(Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map) {
            if (this.f3986a == null || activity == null || activity.isFinishing()) {
                return;
            }
            this.f3986a.show(activity);
        }
    }

    private void a(ITTAdapterInterstitialListener iTTAdapterInterstitialListener) {
        new GdtIntersitialAd(iTTAdapterInterstitialListener).a();
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void destroy() {
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getAdNetWorkName() {
        return "gdt";
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getSdkVersion() {
        return SDKStatus.getSDKVersion();
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void loadAd(Context context, Map<String, Object> map) {
        this.f3983a = context;
        if (map != null) {
            Object obj = map.get("tt_ad_network_callback");
            this.d = map.get(TTGlobalConfig.MIN_VIDEO_DURATION) != null ? ((Integer) map.get(TTGlobalConfig.MIN_VIDEO_DURATION)).intValue() : 0;
            this.e = map.get(TTGlobalConfig.MAX_VIDEO_DURATION) != null ? ((Integer) map.get(TTGlobalConfig.MAX_VIDEO_DURATION)).intValue() : 0;
            ITTAdapterInterstitialListener iTTAdapterInterstitialListener = obj instanceof ITTAdapterInterstitialListener ? (ITTAdapterInterstitialListener) obj : null;
            this.f3984b = this.mAdSlot.getTTVideoOption();
            TTVideoOption tTVideoOption = this.f3984b;
            if (tTVideoOption != null) {
                this.f3985c = tTVideoOption.getGDTExtraOption();
            }
            a(iTTAdapterInterstitialListener);
        }
    }
}
