package com.bytedance.msdk.adapter.gdt;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.listener.ITTAdapterBannerAdListener;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.api.AdErrorUtil;
import com.bytedance.msdk.api.v2.GMAdConstant;
import com.bytedance.msdk.base.TTBaseAd;
import com.qq.e.ads.banner2.UnifiedBannerADListener;
import com.qq.e.ads.banner2.UnifiedBannerView;
import com.qq.e.comm.managers.status.SDKStatus;
import com.qq.e.comm.util.AdError;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GdtBannerAdapter extends TTAbsAdLoaderAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f3970a;

    class GdtBanner extends TTBaseAd {

        /* renamed from: a, reason: collision with root package name */
        private UnifiedBannerView f3971a;

        /* renamed from: b, reason: collision with root package name */
        private boolean f3972b = false;

        /* renamed from: c, reason: collision with root package name */
        private UnifiedBannerADListener f3973c = new UnifiedBannerADListener() { // from class: com.bytedance.msdk.adapter.gdt.GdtBannerAdapter.GdtBanner.1
            public void onADClicked() {
                if (((TTBaseAd) GdtBanner.this).mTTAdatperCallback instanceof ITTAdapterBannerAdListener) {
                    GdtBanner.this.b().onAdClicked();
                }
            }

            public void onADCloseOverlay() {
            }

            public void onADClosed() {
                if (((TTBaseAd) GdtBanner.this).mTTAdatperCallback instanceof ITTAdapterBannerAdListener) {
                    GdtBanner.this.b().onAdClosed();
                }
            }

            public void onADExposure() {
                if (((TTBaseAd) GdtBanner.this).mTTAdatperCallback instanceof ITTAdapterBannerAdListener) {
                    GdtBanner.this.b().onAdShow();
                }
            }

            public void onADLeftApplication() {
                if (((TTBaseAd) GdtBanner.this).mTTAdatperCallback instanceof ITTAdapterBannerAdListener) {
                    GdtBanner.this.b().onAdLeftApplication();
                }
            }

            public void onADOpenOverlay() {
                if (((TTBaseAd) GdtBanner.this).mTTAdatperCallback instanceof ITTAdapterBannerAdListener) {
                    GdtBanner.this.b().onAdOpened();
                }
            }

            public void onADReceive() {
                StringBuilder sb;
                if (GdtBanner.this.f3972b) {
                    return;
                }
                if (GdtBanner.this.f3971a != null) {
                    if (GdtBannerAdapter.this.isClientBidding()) {
                        int ecpm = GdtBanner.this.f3971a.getECPM();
                        GdtBanner.this.setCpm(ecpm > 0 ? ecpm : 0.0d);
                        sb = new StringBuilder();
                        sb.append(TTLogUtil.getTagThirdLevelById(GdtBannerAdapter.this.getAdapterRit(), GdtBannerAdapter.this.getAdSlotId()));
                        sb.append("GDT_clientBidding 插屏 返回的 cpm价格：");
                        sb.append(ecpm);
                    } else if (GdtBannerAdapter.this.isMultiBidding()) {
                        String eCPMLevel = GdtBanner.this.f3971a.getECPMLevel();
                        GdtBanner.this.setLevelTag(eCPMLevel);
                        sb = new StringBuilder();
                        sb.append(TTLogUtil.getTagThirdLevelById(GdtBannerAdapter.this.getAdapterRit(), GdtBannerAdapter.this.getAdSlotId()));
                        sb.append("GDT_多阶底价 插屏 返回的 价格标签：");
                        sb.append(eCPMLevel);
                    }
                    Logger.d("TTMediationSDK_ECMP", sb.toString());
                }
                GdtBanner gdtBanner = GdtBanner.this;
                GdtBannerAdapter.this.notifyAdLoaded(gdtBanner);
                GdtBanner.this.f3972b = true;
            }

            public void onNoAD(AdError adError) {
                if (GdtBanner.this.f3972b) {
                    return;
                }
                GdtBanner.this.f3972b = true;
                if (adError != null) {
                    GdtBannerAdapter.this.notifyAdFailed(AdErrorUtil.getGdtError(adError.getErrorCode(), adError.getErrorMsg()));
                } else {
                    GdtBannerAdapter.this.notifyAdFailed(AdErrorUtil.obtainAdError(0, com.bytedance.msdk.api.AdError.getMessage(0)));
                }
            }
        };

        GdtBanner(ITTAdapterBannerAdListener iTTAdapterBannerAdListener) {
            this.mTTAdatperCallback = iTTAdapterBannerAdListener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ITTAdapterBannerAdListener b() {
            return (ITTAdapterBannerAdListener) this.mTTAdatperCallback;
        }

        void a() {
            if (GdtBannerAdapter.this.f3970a instanceof Activity) {
                this.f3971a = new UnifiedBannerView((Activity) GdtBannerAdapter.this.f3970a, GdtBannerAdapter.this.getAdSlotId(), this.f3973c);
                this.f3971a.setRefresh(0);
                this.f3971a.loadAD();
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void bidLoseNotify(Map<String, Object> map) {
            if (!isClientBiddingAd() || this.f3971a == null || map == null) {
                return;
            }
            try {
                Object obj = map.get("bidding_lose_reason");
                if (obj instanceof GMAdConstant.BiddingLossReason) {
                    this.f3971a.sendLossNotification(0, GDTAdapterUtils.getBiddingLossReason((GMAdConstant.BiddingLossReason) obj), (String) null);
                }
            } catch (Exception unused) {
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void bidWinNotify(Map<String, Object> map) {
            UnifiedBannerView unifiedBannerView;
            if (isClientBiddingAd() && (unifiedBannerView = this.f3971a) != null) {
                try {
                    unifiedBannerView.sendWinNotification((int) getCpm());
                } catch (Exception unused) {
                }
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return 1;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public View getAdView() {
            return this.f3971a;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return this.f3971a == null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            UnifiedBannerView unifiedBannerView = this.f3971a;
            if (unifiedBannerView != null) {
                unifiedBannerView.destroy();
            }
            this.mTTAdatperCallback = null;
            this.f3971a = null;
            super.onDestroy();
        }
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
        this.f3970a = context;
        if (map != null) {
            Object obj = map.get("tt_ad_network_callback");
            new GdtBanner(obj instanceof ITTAdapterBannerAdListener ? (ITTAdapterBannerAdListener) obj : null).a();
        }
    }
}
