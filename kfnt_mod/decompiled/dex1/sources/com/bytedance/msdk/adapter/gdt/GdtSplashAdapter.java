package com.bytedance.msdk.adapter.gdt;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.annotation.RequiresApi;
import com.bytedance.msdk.adapter.SplashDismissController;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.listener.ITTAdapterSplashAdListener;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdErrorUtil;
import com.bytedance.msdk.api.GDTExtraOption;
import com.bytedance.msdk.api.TTRequestExtraParams;
import com.bytedance.msdk.api.TTVideoOption;
import com.bytedance.msdk.api.v2.GMAdConstant;
import com.bytedance.msdk.base.TTBaseAd;
import com.qq.e.ads.splash.SplashAD;
import com.qq.e.ads.splash.SplashADZoomOutListener;
import com.qq.e.comm.managers.status.SDKStatus;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GdtSplashAdapter extends TTAbsAdLoaderAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f4018a;

    /* renamed from: c, reason: collision with root package name */
    private GDTSplashAd f4020c;
    private SplashDismissController e;

    /* renamed from: b, reason: collision with root package name */
    private int f4019b = 3000;
    boolean d = false;

    class GDTSplashAd extends TTBaseAd {

        /* renamed from: a, reason: collision with root package name */
        private SplashAD f4021a;

        /* renamed from: b, reason: collision with root package name */
        private RelativeLayout f4022b;

        /* renamed from: c, reason: collision with root package name */
        private FrameLayout f4023c;
        SplashADZoomOutListener d = new SplashADZoomOutListener() { // from class: com.bytedance.msdk.adapter.gdt.GdtSplashAdapter.GDTSplashAd.1
            public long millisUntilFinished;

            public boolean isSupportZoomOut() {
                Log.d("TTMediationSDK", "GDT-开屏广告--isSupportZoomOut");
                return true;
            }

            public void onADClicked() {
                Log.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(GdtSplashAdapter.this.getAdapterRit(), GdtSplashAdapter.this.getAdSlotId()) + "GDT-开屏广告--onADClicked....");
                if (((TTBaseAd) GDTSplashAd.this).mTTAdatperCallback instanceof ITTAdapterSplashAdListener) {
                    if (GdtSplashAdapter.this.e != null) {
                        GdtSplashAdapter.this.e.setClick(true);
                        GdtSplashAdapter.this.e.setCallBack(new SplashDismissController.CallBack() { // from class: com.bytedance.msdk.adapter.gdt.GdtSplashAdapter.GDTSplashAd.1.1
                            @Override // com.bytedance.msdk.adapter.SplashDismissController.CallBack
                            public void onResume() {
                                if (GdtSplashAdapter.this.e == null || !GdtSplashAdapter.this.e.jumpToAdPage() || GdtSplashAdapter.this.e.isCallDismiss() || !(((TTBaseAd) GDTSplashAd.this).mTTAdatperCallback instanceof ITTAdapterSplashAdListener)) {
                                    return;
                                }
                                GDTSplashAd.this.a().onAdDismiss();
                            }
                        });
                    }
                    GDTSplashAd.this.a().onAdClicked();
                }
            }

            public void onADDismissed() {
                if (((TTBaseAd) GDTSplashAd.this).mTTAdatperCallback instanceof ITTAdapterSplashAdListener) {
                    if (GdtSplashAdapter.this.e == null || !GdtSplashAdapter.this.e.jumpToAdPage()) {
                        if (GdtSplashAdapter.this.e != null) {
                            GdtSplashAdapter.this.e.setCallDismiss(true);
                        }
                        GDTSplashAd.this.a().onAdDismiss();
                    }
                }
            }

            public void onADExposure() {
                if (((TTBaseAd) GDTSplashAd.this).mTTAdatperCallback instanceof ITTAdapterSplashAdListener) {
                    GDTSplashAd.this.a().onAdShow();
                }
                Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelById(GdtSplashAdapter.this.getAdapterRit(), GdtSplashAdapter.this.getAdSlotId()) + "GDT-开屏广告_onADExposure......onADExposure.....");
            }

            public void onADLoaded(long j) {
                StringBuilder sb;
                long jElapsedRealtime = j - SystemClock.elapsedRealtime();
                if (GDTSplashAd.this.f4021a == null || jElapsedRealtime <= 1000) {
                    GdtSplashAdapter.this.notifyAdFailed(AdErrorUtil.obtainAdError(AdError.ERROR_CODE_NO_AD, "Ad has expired !"));
                    return;
                }
                if (!GdtSplashAdapter.this.isClientBidding()) {
                    if (GdtSplashAdapter.this.isMultiBidding()) {
                        GDTSplashAd gDTSplashAd = GDTSplashAd.this;
                        gDTSplashAd.setLevelTag(gDTSplashAd.f4021a.getECPMLevel());
                        sb = new StringBuilder();
                        sb.append(TTLogUtil.getTagThirdLevelById(GdtSplashAdapter.this.getAdapterRit(), GdtSplashAdapter.this.getAdSlotId()));
                        sb.append("GDT_多阶底价 splash 返回的 价格标签：");
                        sb.append(GDTSplashAd.this.f4021a.getECPMLevel());
                    }
                    GDTSplashAd gDTSplashAd2 = GDTSplashAd.this;
                    GdtSplashAdapter.this.notifyAdLoaded(gDTSplashAd2);
                }
                GDTSplashAd gDTSplashAd3 = GDTSplashAd.this;
                gDTSplashAd3.setCpm(gDTSplashAd3.f4021a.getECPM() != -1 ? GDTSplashAd.this.f4021a.getECPM() : 0.0d);
                sb = new StringBuilder();
                sb.append(TTLogUtil.getTagThirdLevelById(GdtSplashAdapter.this.getAdapterRit(), GdtSplashAdapter.this.getAdSlotId()));
                sb.append("GDT_clientBidding splash 返回的 cpm价格：");
                sb.append(GDTSplashAd.this.f4021a.getECPM());
                Logger.d("TTMediationSDK_ECMP", sb.toString());
                GDTSplashAd gDTSplashAd22 = GDTSplashAd.this;
                GdtSplashAdapter.this.notifyAdLoaded(gDTSplashAd22);
            }

            public void onADPresent() {
            }

            public void onADTick(long j) {
                this.millisUntilFinished = j;
            }

            public void onNoAD(com.qq.e.comm.util.AdError adError) {
                GdtSplashAdapter.this.notifyAdFailed(AdErrorUtil.getGdtError(adError.getErrorCode(), adError.getErrorMsg()));
            }

            public void onZoomOut() {
                Log.d("TTMediationSDK", "GDT-开屏广告--onZoomOut");
                if (((TTBaseAd) GDTSplashAd.this).mTTAdatperCallback instanceof ITTAdapterSplashAdListener) {
                    GDTSplashAd.this.a().onMinWindowStart();
                }
            }

            public void onZoomOutPlayFinish() {
                Log.d("TTMediationSDK", "GDT-开屏广告--onZoomOutPlayFinish");
                if (((TTBaseAd) GDTSplashAd.this).mTTAdatperCallback instanceof ITTAdapterSplashAdListener) {
                    GDTSplashAd.this.a().onMinWindowPlayFinish();
                }
            }
        };

        @RequiresApi(api = 16)
        GDTSplashAd(ITTAdapterSplashAdListener iTTAdapterSplashAdListener) {
            this.mTTAdatperCallback = iTTAdapterSplashAdListener;
            RelativeLayout relativeLayout = new RelativeLayout(GdtSplashAdapter.this.f4018a);
            relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            FrameLayout frameLayout = new FrameLayout(GdtSplashAdapter.this.f4018a);
            frameLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            relativeLayout.addView(frameLayout);
            this.f4023c = frameLayout;
            this.f4022b = relativeLayout;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ITTAdapterSplashAdListener a() {
            return (ITTAdapterSplashAdListener) this.mTTAdatperCallback;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void bidLoseNotify(Map<String, Object> map) {
            if (!isClientBiddingAd() || this.f4021a == null || map == null) {
                return;
            }
            try {
                Object obj = map.get("bidding_lose_reason");
                if (obj instanceof GMAdConstant.BiddingLossReason) {
                    this.f4021a.sendLossNotification(0, GDTAdapterUtils.getBiddingLossReason((GMAdConstant.BiddingLossReason) obj), (String) null);
                }
            } catch (Exception unused) {
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void bidWinNotify(Map<String, Object> map) {
            SplashAD splashAD;
            if (isClientBiddingAd() && (splashAD = this.f4021a) != null) {
                try {
                    splashAD.sendWinNotification((int) getCpm());
                } catch (Exception unused) {
                }
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return 3;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public Bitmap getSplashBitMap() {
            SplashAD splashAD = this.f4021a;
            if (splashAD == null) {
                return null;
            }
            splashAD.getZoomOutBitmap();
            return null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return this.f4021a == null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isReady() {
            return !GdtSplashAdapter.this.hasNotifyFail();
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0048  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void loadAd() {
            /*
                r5 = this;
                com.bytedance.msdk.adapter.gdt.GdtSplashAdapter r0 = com.bytedance.msdk.adapter.gdt.GdtSplashAdapter.this
                android.content.Context r0 = com.bytedance.msdk.adapter.gdt.GdtSplashAdapter.a(r0)
                boolean r0 = r0 instanceof android.app.Activity
                if (r0 == 0) goto L52
                com.bytedance.msdk.adapter.gdt.GdtSplashAdapter r0 = com.bytedance.msdk.adapter.gdt.GdtSplashAdapter.this
                int r0 = com.bytedance.msdk.adapter.gdt.GdtSplashAdapter.b(r0)
                if (r0 >= 0) goto L1a
                com.bytedance.msdk.adapter.gdt.GdtSplashAdapter r0 = com.bytedance.msdk.adapter.gdt.GdtSplashAdapter.this
                r1 = 3000(0xbb8, float:4.204E-42)
            L16:
                com.bytedance.msdk.adapter.gdt.GdtSplashAdapter.a(r0, r1)
                goto L27
            L1a:
                com.bytedance.msdk.adapter.gdt.GdtSplashAdapter r0 = com.bytedance.msdk.adapter.gdt.GdtSplashAdapter.this
                int r0 = com.bytedance.msdk.adapter.gdt.GdtSplashAdapter.b(r0)
                r1 = 5000(0x1388, float:7.006E-42)
                if (r0 <= r1) goto L27
                com.bytedance.msdk.adapter.gdt.GdtSplashAdapter r0 = com.bytedance.msdk.adapter.gdt.GdtSplashAdapter.this
                goto L16
            L27:
                com.qq.e.ads.splash.SplashAD r0 = new com.qq.e.ads.splash.SplashAD
                com.bytedance.msdk.adapter.gdt.GdtSplashAdapter r1 = com.bytedance.msdk.adapter.gdt.GdtSplashAdapter.this
                android.content.Context r1 = com.bytedance.msdk.adapter.gdt.GdtSplashAdapter.a(r1)
                com.bytedance.msdk.adapter.gdt.GdtSplashAdapter r2 = com.bytedance.msdk.adapter.gdt.GdtSplashAdapter.this
                java.lang.String r2 = r2.getAdSlotId()
                com.qq.e.ads.splash.SplashADZoomOutListener r3 = r5.d
                com.bytedance.msdk.adapter.gdt.GdtSplashAdapter r4 = com.bytedance.msdk.adapter.gdt.GdtSplashAdapter.this
                int r4 = com.bytedance.msdk.adapter.gdt.GdtSplashAdapter.b(r4)
                r0.<init>(r1, r2, r3, r4)
                r5.f4021a = r0
                com.bytedance.msdk.adapter.gdt.GdtSplashAdapter r0 = com.bytedance.msdk.adapter.gdt.GdtSplashAdapter.this
                boolean r0 = r0.d
                if (r0 == 0) goto L4d
                com.qq.e.ads.splash.SplashAD r0 = r5.f4021a
                r0.preLoad()
            L4d:
                com.qq.e.ads.splash.SplashAD r0 = r5.f4021a
                r0.fetchAdOnly()
            L52:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bytedance.msdk.adapter.gdt.GdtSplashAdapter.GDTSplashAd.loadAd():void");
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            this.f4021a = null;
            this.d = null;
            this.mTTAdatperCallback = null;
            GdtSplashAdapter.this.f4018a = null;
            GdtSplashAdapter.this.e = null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void showSplashAd(ViewGroup viewGroup) {
            if (this.f4021a == null || viewGroup == null) {
                return;
            }
            viewGroup.removeAllViews();
            RelativeLayout relativeLayout = this.f4022b;
            if (relativeLayout == null || this.f4023c == null) {
                return;
            }
            ViewParent parent = relativeLayout.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.f4022b);
            }
            viewGroup.addView(this.f4022b, -1, -1);
            this.f4021a.showAd(this.f4023c);
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void splashMinWindowAnimationFinish() {
            SplashAD splashAD = this.f4021a;
            if (splashAD != null) {
                splashAD.zoomOutAnimationFinish();
            }
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
        GDTExtraOption gDTExtraOption;
        this.f4018a = context;
        if (map != null) {
            this.f4019b = map.get(TTRequestExtraParams.PARAM_AD_LOAD_TIMEOUT) != null ? ((Integer) map.get(TTRequestExtraParams.PARAM_AD_LOAD_TIMEOUT)).intValue() : this.f4019b;
            Context context2 = this.f4018a;
            if (context2 instanceof Activity) {
                this.e = new SplashDismissController((Activity) context2);
            }
            Object obj = map.get("tt_ad_network_callback");
            ITTAdapterSplashAdListener iTTAdapterSplashAdListener = obj instanceof ITTAdapterSplashAdListener ? (ITTAdapterSplashAdListener) obj : null;
            TTVideoOption tTVideoOption = this.mAdSlot.getTTVideoOption();
            if (tTVideoOption != null && (gDTExtraOption = tTVideoOption.getGDTExtraOption()) != null) {
                this.d = gDTExtraOption.isSplashPreLoad();
            }
            this.f4020c = new GDTSplashAd(iTTAdapterSplashAdListener);
            this.f4020c.loadAd();
        }
    }
}
