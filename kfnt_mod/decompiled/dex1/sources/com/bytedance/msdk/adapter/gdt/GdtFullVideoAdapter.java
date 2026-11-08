package com.bytedance.msdk.adapter.gdt;

import android.app.Activity;
import android.content.Context;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.listener.ITTAdapterFullVideoAdListener;
import com.bytedance.msdk.api.AdErrorUtil;
import com.bytedance.msdk.api.GDTExtraOption;
import com.bytedance.msdk.api.TTAdConstant;
import com.bytedance.msdk.api.TTVideoOption;
import com.bytedance.msdk.api.v2.GMAdConstant;
import com.bytedance.msdk.base.TTBaseAd;
import com.qq.e.ads.cfg.VideoOption;
import com.qq.e.ads.interstitial2.UnifiedInterstitialAD;
import com.qq.e.ads.interstitial2.UnifiedInterstitialADListener;
import com.qq.e.ads.interstitial2.UnifiedInterstitialMediaListener;
import com.qq.e.comm.managers.status.SDKStatus;
import com.qq.e.comm.util.AdError;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GdtFullVideoAdapter extends TTAbsAdLoaderAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f3975a;

    /* renamed from: b, reason: collision with root package name */
    private TTVideoOption f3976b;

    /* renamed from: c, reason: collision with root package name */
    private GDTExtraOption f3977c;
    private int d;
    private int e;

    class GdtFullVideoAd extends TTBaseAd {

        /* renamed from: a, reason: collision with root package name */
        private UnifiedInterstitialAD f3978a;

        /* renamed from: b, reason: collision with root package name */
        private boolean f3979b;

        /* renamed from: c, reason: collision with root package name */
        UnifiedInterstitialADListener f3980c = new UnifiedInterstitialADListener() { // from class: com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.GdtFullVideoAd.1
            public void onADClicked() {
                if (((TTBaseAd) GdtFullVideoAd.this).mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener) {
                    GdtFullVideoAd.this.b().onFullVideoAdClick();
                }
            }

            public void onADClosed() {
                if (((TTBaseAd) GdtFullVideoAd.this).mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener) {
                    GdtFullVideoAd.this.b().onFullVideoAdClosed();
                }
            }

            public void onADExposure() {
                if (((TTBaseAd) GdtFullVideoAd.this).mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener) {
                    GdtFullVideoAd.this.b().onFullVideoAdShow();
                }
            }

            public void onADLeftApplication() {
            }

            public void onADOpened() {
            }

            /* JADX WARN: Removed duplicated region for block: B:15:0x00b8  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onADReceive() {
                /*
                    r3 = this;
                    com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter$GdtFullVideoAd r0 = com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.GdtFullVideoAd.this
                    r1 = 1
                    com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.GdtFullVideoAd.a(r0, r1)
                    com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter$GdtFullVideoAd r0 = com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.GdtFullVideoAd.this
                    com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter r0 = com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.this
                    boolean r0 = r0.isClientBidding()
                    if (r0 == 0) goto L5d
                    com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter$GdtFullVideoAd r0 = com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.GdtFullVideoAd.this
                    com.qq.e.ads.interstitial2.UnifiedInterstitialAD r1 = com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.GdtFullVideoAd.d(r0)
                    int r1 = r1.getECPM()
                    r2 = -1
                    if (r1 == r2) goto L29
                    com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter$GdtFullVideoAd r1 = com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.GdtFullVideoAd.this
                    com.qq.e.ads.interstitial2.UnifiedInterstitialAD r1 = com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.GdtFullVideoAd.d(r1)
                    int r1 = r1.getECPM()
                    double r1 = (double) r1
                    goto L2b
                L29:
                    r1 = 0
                L2b:
                    r0.setCpm(r1)
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder
                    r0.<init>()
                    com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter$GdtFullVideoAd r1 = com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.GdtFullVideoAd.this
                    com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter r1 = com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.this
                    java.lang.String r1 = r1.getAdapterRit()
                    com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter$GdtFullVideoAd r2 = com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.GdtFullVideoAd.this
                    com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter r2 = com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.this
                    java.lang.String r2 = r2.getAdSlotId()
                    java.lang.String r1 = com.bytedance.msdk.adapter.util.TTLogUtil.getTagThirdLevelById(r1, r2)
                    r0.append(r1)
                    java.lang.String r1 = "GDT_clientBidding FullVideo 返回的 cpm价格："
                    r0.append(r1)
                    com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter$GdtFullVideoAd r1 = com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.GdtFullVideoAd.this
                    com.qq.e.ads.interstitial2.UnifiedInterstitialAD r1 = com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.GdtFullVideoAd.d(r1)
                    int r1 = r1.getECPM()
                    r0.append(r1)
                    goto La2
                L5d:
                    com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter$GdtFullVideoAd r0 = com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.GdtFullVideoAd.this
                    com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter r0 = com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.this
                    boolean r0 = r0.isMultiBidding()
                    if (r0 == 0) goto Lab
                    com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter$GdtFullVideoAd r0 = com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.GdtFullVideoAd.this
                    com.qq.e.ads.interstitial2.UnifiedInterstitialAD r1 = com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.GdtFullVideoAd.d(r0)
                    java.lang.String r1 = r1.getECPMLevel()
                    r0.setLevelTag(r1)
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder
                    r0.<init>()
                    com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter$GdtFullVideoAd r1 = com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.GdtFullVideoAd.this
                    com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter r1 = com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.this
                    java.lang.String r1 = r1.getAdapterRit()
                    com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter$GdtFullVideoAd r2 = com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.GdtFullVideoAd.this
                    com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter r2 = com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.this
                    java.lang.String r2 = r2.getAdSlotId()
                    java.lang.String r1 = com.bytedance.msdk.adapter.util.TTLogUtil.getTagThirdLevelById(r1, r2)
                    r0.append(r1)
                    java.lang.String r1 = "GDT_多阶底价 FullVideo 返回的价格标签："
                    r0.append(r1)
                    com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter$GdtFullVideoAd r1 = com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.GdtFullVideoAd.this
                    com.qq.e.ads.interstitial2.UnifiedInterstitialAD r1 = com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.GdtFullVideoAd.d(r1)
                    java.lang.String r1 = r1.getECPMLevel()
                    r0.append(r1)
                La2:
                    java.lang.String r0 = r0.toString()
                    java.lang.String r1 = "TTMediationSDK_ECMP"
                    com.bytedance.msdk.adapter.util.Logger.d(r1, r0)
                Lab:
                    com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter$GdtFullVideoAd r0 = com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.GdtFullVideoAd.this
                    com.qq.e.ads.interstitial2.UnifiedInterstitialAD r0 = com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.GdtFullVideoAd.d(r0)
                    int r0 = r0.getAdPatternType()
                    r1 = 2
                    if (r0 != r1) goto Lcb
                    com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter$GdtFullVideoAd r0 = com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.GdtFullVideoAd.this
                    r1 = 5
                    r0.setImageMode(r1)
                    com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter$GdtFullVideoAd r0 = com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.GdtFullVideoAd.this
                    com.qq.e.ads.interstitial2.UnifiedInterstitialAD r0 = com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.GdtFullVideoAd.d(r0)
                    com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter$GdtFullVideoAd r1 = com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.GdtFullVideoAd.this
                    com.qq.e.ads.interstitial2.UnifiedInterstitialMediaListener r1 = r1.d
                    r0.setMediaListener(r1)
                Lcb:
                    com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter$GdtFullVideoAd r0 = com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.GdtFullVideoAd.this
                    com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter r1 = com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.this
                    r1.notifyAdLoaded(r0)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.GdtFullVideoAd.AnonymousClass1.onADReceive():void");
            }

            public void onNoAD(AdError adError) {
                GdtFullVideoAd.this.f3979b = false;
                if (adError != null) {
                    GdtFullVideoAdapter.this.notifyAdFailed(AdErrorUtil.getGdtError(adError.getErrorCode(), adError.getErrorMsg()));
                } else {
                    GdtFullVideoAdapter.this.notifyAdFailed(AdErrorUtil.obtainAdError(0, com.bytedance.msdk.api.AdError.getMessage(0)));
                }
            }

            public void onRenderFail() {
            }

            public void onRenderSuccess() {
            }

            public void onVideoCached() {
                GdtFullVideoAd gdtFullVideoAd = GdtFullVideoAd.this;
                GdtFullVideoAdapter.this.notifyAdVideoCache(gdtFullVideoAd, null);
            }
        };
        UnifiedInterstitialMediaListener d = new UnifiedInterstitialMediaListener() { // from class: com.bytedance.msdk.adapter.gdt.GdtFullVideoAdapter.GdtFullVideoAd.2
            public void onVideoComplete() {
                if (((TTBaseAd) GdtFullVideoAd.this).mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener) {
                    GdtFullVideoAd.this.b().onVideoComplete();
                }
            }

            public void onVideoError(AdError adError) {
                if (((TTBaseAd) GdtFullVideoAd.this).mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener) {
                    GdtFullVideoAd.this.b().onVideoError();
                }
            }

            public void onVideoInit() {
            }

            public void onVideoLoading() {
            }

            public void onVideoPageClose() {
            }

            public void onVideoPageOpen() {
            }

            public void onVideoPause() {
            }

            public void onVideoReady(long j) {
            }

            public void onVideoStart() {
            }
        };

        GdtFullVideoAd(ITTAdapterFullVideoAdListener iTTAdapterFullVideoAdListener) {
            this.mTTAdatperCallback = iTTAdapterFullVideoAdListener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ITTAdapterFullVideoAdListener b() {
            return (ITTAdapterFullVideoAdListener) this.mTTAdatperCallback;
        }

        void a() {
            if (GdtFullVideoAdapter.this.f3975a instanceof Activity) {
                this.f3978a = new UnifiedInterstitialAD((Activity) GdtFullVideoAdapter.this.f3975a, GdtFullVideoAdapter.this.getAdSlotId(), this.f3980c);
                VideoOption videoOption = GDTAdapterUtils.getVideoOption(GdtFullVideoAdapter.this.f3977c);
                this.f3978a.setVideoOption(videoOption);
                if (GdtFullVideoAdapter.this.e > 0) {
                    this.f3978a.setMaxVideoDuration(GdtFullVideoAdapter.this.e);
                }
                if (GdtFullVideoAdapter.this.d > 0) {
                    this.f3978a.setMinVideoDuration(GdtFullVideoAdapter.this.d);
                }
                this.f3978a.setVideoPlayPolicy(GDTAdapterUtils.getVideoPlayPolicy(videoOption.getAutoPlayPolicy(), GdtFullVideoAdapter.this.f3975a));
                this.f3978a.loadFullScreenAD();
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean adnHasAdVideoCachedApi() {
            return true;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void bidLoseNotify(Map<String, Object> map) {
            if (!isClientBiddingAd() || this.f3978a == null || map == null) {
                return;
            }
            try {
                Object obj = map.get("bidding_lose_reason");
                if (obj instanceof GMAdConstant.BiddingLossReason) {
                    this.f3978a.sendLossNotification(0, GDTAdapterUtils.getBiddingLossReason((GMAdConstant.BiddingLossReason) obj), (String) null);
                }
            } catch (Exception unused) {
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void bidWinNotify(Map<String, Object> map) {
            UnifiedInterstitialAD unifiedInterstitialAD;
            if (isClientBiddingAd() && (unifiedInterstitialAD = this.f3978a) != null) {
                try {
                    unifiedInterstitialAD.sendWinNotification((int) getCpm());
                } catch (Exception unused) {
                }
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return ((TTAbsAdLoaderAdapter) GdtFullVideoAdapter.this).mAdSlot.getAdType();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return this.f3978a == null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isAdCacheReady() {
            UnifiedInterstitialAD unifiedInterstitialAD = this.f3978a;
            return unifiedInterstitialAD != null && unifiedInterstitialAD.isValid();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isReady() {
            return this.f3979b;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            UnifiedInterstitialAD unifiedInterstitialAD = this.f3978a;
            if (unifiedInterstitialAD != null) {
                unifiedInterstitialAD.destroy();
                this.f3978a = null;
            }
            this.mTTAdatperCallback = null;
            super.onDestroy();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void showAd(Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map) {
            UnifiedInterstitialAD unifiedInterstitialAD = this.f3978a;
            if (unifiedInterstitialAD != null) {
                unifiedInterstitialAD.showFullScreenAD(activity);
            }
        }
    }

    private void a(ITTAdapterFullVideoAdListener iTTAdapterFullVideoAdListener) {
        new GdtFullVideoAd(iTTAdapterFullVideoAdListener).a();
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
        this.f3975a = context;
        if (map != null) {
            Object obj = map.get("tt_ad_network_callback");
            this.f3976b = this.mAdSlot.getTTVideoOption();
            TTVideoOption tTVideoOption = this.f3976b;
            if (tTVideoOption != null) {
                this.f3977c = tTVideoOption.getGDTExtraOption();
                GDTExtraOption gDTExtraOption = this.f3977c;
                if (gDTExtraOption != null) {
                    this.d = gDTExtraOption.getGDTMinVideoDuration();
                    this.e = this.f3977c.getGDTMaxVideoDuration();
                }
            }
            a(obj instanceof ITTAdapterFullVideoAdListener ? (ITTAdapterFullVideoAdListener) obj : null);
        }
    }
}
