package com.bytedance.msdk.adapter.gdt;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.api.GDTExtraOption;
import com.bytedance.msdk.api.TTVideoOption;
import com.bytedance.msdk.api.format.TTMediaView;
import com.bytedance.msdk.api.format.TTNativeAdView;
import com.bytedance.msdk.api.v2.GMAdConstant;
import com.bytedance.msdk.api.v2.ad.nativeAd.GMViewBinder;
import com.bytedance.msdk.base.TTBaseAd;
import com.qq.e.ads.cfg.VideoOption;
import com.qq.e.ads.nativ.MediaView;
import com.qq.e.ads.nativ.NativeADEventListener;
import com.qq.e.ads.nativ.NativeADMediaListener;
import com.qq.e.ads.nativ.NativeUnifiedAD;
import com.qq.e.ads.nativ.NativeUnifiedADAppMiitInfo;
import com.qq.e.ads.nativ.NativeUnifiedADData;
import com.qq.e.ads.nativ.VideoPreloadListener;
import com.qq.e.ads.nativ.widget.NativeAdContainer;
import com.qq.e.comm.util.AdError;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GdtNativeLoader {

    /* renamed from: a, reason: collision with root package name */
    private NativeUnifiedADData f4000a;

    /* renamed from: b, reason: collision with root package name */
    private NativeUnifiedAD f4001b;

    /* renamed from: c, reason: collision with root package name */
    private String f4002c;
    private int d;
    private int e;
    private int f;
    private Context g;
    private TTVideoOption h;
    private GDTExtraOption i;
    private VideoOption j;
    private FrameLayout.LayoutParams k;
    private TTAbsAdLoaderAdapter l;

    class GdtNativeAd extends TTBaseAd {

        /* renamed from: a, reason: collision with root package name */
        NativeUnifiedADData f4005a;

        /* renamed from: b, reason: collision with root package name */
        NativeADMediaListener f4006b = new NativeADMediaListener() { // from class: com.bytedance.msdk.adapter.gdt.GdtNativeLoader.GdtNativeAd.3
            public void onVideoClicked() {
                if (((TTBaseAd) GdtNativeAd.this).mTTNativeAdListener != null) {
                    ((TTBaseAd) GdtNativeAd.this).mTTNativeAdListener.onAdClick();
                }
                Log.d("GdtNativeLoader", "onVideoClicked");
            }

            public void onVideoCompleted() {
                if (((TTBaseAd) GdtNativeAd.this).mTTVideoListener != null) {
                    ((TTBaseAd) GdtNativeAd.this).mTTVideoListener.onVideoCompleted();
                }
            }

            public void onVideoError(AdError adError) {
                if (((TTBaseAd) GdtNativeAd.this).mTTVideoListener != null) {
                    ((TTBaseAd) GdtNativeAd.this).mTTVideoListener.onVideoError(new com.bytedance.msdk.api.AdError(adError.getErrorCode(), adError.getErrorMsg(), adError.getErrorCode(), adError.getErrorMsg()));
                }
            }

            public void onVideoInit() {
                Log.d("GdtNativeLoader", "onVideoInit: ");
            }

            public void onVideoLoaded(int i) {
                Log.d("GdtNativeLoader", "onVideoLoaded: ");
            }

            public void onVideoLoading() {
                Log.d("GdtNativeLoader", "onVideoLoading: ");
            }

            public void onVideoPause() {
                if (((TTBaseAd) GdtNativeAd.this).mTTVideoListener != null) {
                    ((TTBaseAd) GdtNativeAd.this).mTTVideoListener.onVideoPause();
                }
            }

            public void onVideoReady() {
                Log.d("GdtNativeLoader", "onVideoReady");
            }

            public void onVideoResume() {
                if (((TTBaseAd) GdtNativeAd.this).mTTVideoListener != null) {
                    ((TTBaseAd) GdtNativeAd.this).mTTVideoListener.onVideoResume();
                }
            }

            public void onVideoStart() {
                Log.d("GdtNativeLoader", "onVideoStart");
                if (((TTBaseAd) GdtNativeAd.this).mTTVideoListener != null) {
                    ((TTBaseAd) GdtNativeAd.this).mTTVideoListener.onVideoStart();
                }
            }

            public void onVideoStop() {
                Log.d("GdtNativeLoader", "onVideoStop");
            }
        };

        GdtNativeAd(NativeUnifiedADData nativeUnifiedADData) {
            StringBuilder sb;
            this.f4005a = nativeUnifiedADData;
            NativeUnifiedADAppMiitInfo appMiitInfo = this.f4005a.getAppMiitInfo();
            if (appMiitInfo != null) {
                setAppName(appMiitInfo.getAppName());
                setAuthorName(appMiitInfo.getAuthorName());
                setPackageSizeBytes(appMiitInfo.getPackageSizeBytes());
                setPermissionsUrl(appMiitInfo.getPermissionsUrl());
                setPrivacyAgreement(appMiitInfo.getPrivacyAgreement());
                setVersionName(appMiitInfo.getVersionName());
            }
            setTitle(nativeUnifiedADData.getTitle());
            setAdDescription(nativeUnifiedADData.getDesc());
            setActionText(nativeUnifiedADData.getCTAText());
            setIconUrl(nativeUnifiedADData.getIconUrl());
            setImageUrl(nativeUnifiedADData.getImgUrl());
            setImageWidth(nativeUnifiedADData.getPictureWidth());
            setImageHeight(nativeUnifiedADData.getPictureHeight());
            setImages(nativeUnifiedADData.getImgList());
            setRating(nativeUnifiedADData.getAppScore());
            setAdType(5);
            setSource(nativeUnifiedADData.getTitle());
            setIsAppDownload(nativeUnifiedADData.isAppAd());
            if (GdtNativeLoader.this.l != null) {
                if (GdtNativeLoader.this.l.isClientBidding()) {
                    setCpm(nativeUnifiedADData.getECPM() != -1 ? nativeUnifiedADData.getECPM() : 0.0d);
                    sb = new StringBuilder();
                    sb.append("GDT_clientBidding 原生Native 返回的 cpm价格：");
                    sb.append(nativeUnifiedADData.getECPM());
                } else if (GdtNativeLoader.this.l.isMultiBidding()) {
                    setLevelTag(nativeUnifiedADData.getECPMLevel());
                    sb = new StringBuilder();
                    sb.append("GDT_多阶底价 原生Native 返回的 价格标签：");
                    sb.append(nativeUnifiedADData.getECPMLevel());
                }
                Logger.d("TTMediationSDK_ECMP", sb.toString());
            }
            if (nativeUnifiedADData.getAdPatternType() == 2) {
                setImageMode(5);
                nativeUnifiedADData.preloadVideo(new VideoPreloadListener(this, GdtNativeLoader.this) { // from class: com.bytedance.msdk.adapter.gdt.GdtNativeLoader.GdtNativeAd.1
                    public void onVideoCacheFailed(int i, String str) {
                    }

                    public void onVideoCached() {
                    }
                });
            } else if (nativeUnifiedADData.getAdPatternType() == 4 || nativeUnifiedADData.getAdPatternType() == 1) {
                setImageMode(3);
            } else if (nativeUnifiedADData.getAdPatternType() == 3) {
                setImageMode(4);
            }
            if (nativeUnifiedADData.isAppAd()) {
                setInteractionType(4);
            } else {
                setInteractionType(3);
            }
        }

        private void a(Context context, @NonNull ViewGroup viewGroup, List<View> list, List<View> list2, GMViewBinder gMViewBinder) {
            NativeAdContainer nativeAdContainer;
            Object tag;
            if (this.f4005a == null || !(viewGroup instanceof TTNativeAdView)) {
                return;
            }
            TTNativeAdView tTNativeAdView = (TTNativeAdView) viewGroup;
            int i = 0;
            if (tTNativeAdView.getChildAt(0) instanceof NativeAdContainer) {
                nativeAdContainer = (NativeAdContainer) tTNativeAdView.getChildAt(0);
                while (i < nativeAdContainer.getChildCount()) {
                    View childAt = nativeAdContainer.getChildAt(i);
                    if (childAt == null || ((tag = childAt.getTag(R.id.tt_mediation_gdt_developer_view_tag_key)) != null && (tag instanceof String) && ((String) tag).equals("tt_gdt_developer_view"))) {
                        i++;
                    } else {
                        nativeAdContainer.removeView(childAt);
                    }
                }
            } else {
                nativeAdContainer = new NativeAdContainer(GdtNativeLoader.this.g);
                nativeAdContainer.setTag(R.id.tt_mediation_gdt_developer_view_root_tag_key, "tt_gdt_developer_view_root");
                while (tTNativeAdView.getChildCount() > 0) {
                    View childAt2 = tTNativeAdView.getChildAt(0);
                    childAt2.setTag(R.id.tt_mediation_gdt_developer_view_tag_key, "tt_gdt_developer_view");
                    int iIndexOfChild = tTNativeAdView.indexOfChild(childAt2);
                    tTNativeAdView.removeViewInLayout(childAt2);
                    if (childAt2 != null) {
                        nativeAdContainer.addView(childAt2, iIndexOfChild, childAt2.getLayoutParams());
                    }
                }
                tTNativeAdView.removeAllViews();
                tTNativeAdView.addView((View) nativeAdContainer, -1, -1);
            }
            NativeAdContainer nativeAdContainer2 = nativeAdContainer;
            TTMediaView tTMediaView = (TTMediaView) tTNativeAdView.findViewById(gMViewBinder.mediaViewId);
            this.f4005a.bindAdToView(context, nativeAdContainer2, GdtNativeLoader.this.k, list, list2);
            if (tTMediaView != null && getImageMode() == 5) {
                MediaView mediaView = new MediaView(GdtNativeLoader.this.g);
                tTMediaView.removeAllViews();
                tTMediaView.addView((View) mediaView, -1, -1);
                this.f4005a.bindMediaView(mediaView, GdtNativeLoader.this.j, this.f4006b);
            }
            if (!TextUtils.isEmpty(this.f4005a.getCTAText())) {
                View viewFindViewById = tTNativeAdView.findViewById(gMViewBinder.callToActionId);
                ArrayList arrayList = new ArrayList();
                arrayList.add(viewFindViewById);
                this.f4005a.bindCTAViews(arrayList);
            }
            this.f4005a.setNativeAdEventListener(new NativeADEventListener() { // from class: com.bytedance.msdk.adapter.gdt.GdtNativeLoader.GdtNativeAd.2
                public void onADClicked() {
                    if (((TTBaseAd) GdtNativeAd.this).mTTNativeAdListener != null) {
                        ((TTBaseAd) GdtNativeAd.this).mTTNativeAdListener.onAdClick();
                    }
                    Log.d("GdtNativeLoader", "Native GDT --- onADClicked。。。。");
                }

                public void onADError(AdError adError) {
                    Log.d("TTMediationSDK", "GDT --- onADError error code :" + adError.getErrorCode() + "  error msg: " + adError.getErrorMsg());
                }

                public void onADExposed() {
                    if (((TTBaseAd) GdtNativeAd.this).mTTNativeAdListener != null) {
                        ((TTBaseAd) GdtNativeAd.this).mTTNativeAdListener.onAdShow();
                    }
                    Log.d("GdtNativeLoader", "Native GDT --- onADExposed。。。。");
                }

                public void onADStatusChanged() {
                }
            });
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void bidLoseNotify(Map<String, Object> map) {
            if (!isClientBiddingAd() || this.f4005a == null || map == null) {
                return;
            }
            try {
                Object obj = map.get("bidding_lose_reason");
                if (obj instanceof GMAdConstant.BiddingLossReason) {
                    this.f4005a.sendLossNotification(0, GDTAdapterUtils.getBiddingLossReason((GMAdConstant.BiddingLossReason) obj), (String) null);
                }
            } catch (Exception unused) {
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void bidWinNotify(Map<String, Object> map) {
            NativeUnifiedADData nativeUnifiedADData;
            if (isClientBiddingAd() && (nativeUnifiedADData = this.f4005a) != null) {
                try {
                    nativeUnifiedADData.sendWinNotification((int) getCpm());
                } catch (Exception unused) {
                }
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return 5;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return this.f4005a == null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            NativeUnifiedADData nativeUnifiedADData = this.f4005a;
            if (nativeUnifiedADData != null) {
                nativeUnifiedADData.destroy();
                this.f4005a = null;
            }
            super.onDestroy();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onPause() {
            super.onPause();
            NativeUnifiedADData nativeUnifiedADData = this.f4005a;
            if (nativeUnifiedADData != null) {
                nativeUnifiedADData.pauseVideo();
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onResume() {
            super.onResume();
            NativeUnifiedADData nativeUnifiedADData = this.f4005a;
            if (nativeUnifiedADData != null) {
                nativeUnifiedADData.resume();
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void registerViewForInteraction(@NonNull Activity activity, @NonNull ViewGroup viewGroup, List<View> list, List<View> list2, GMViewBinder gMViewBinder) {
            super.registerViewForInteraction(viewGroup, list, list2, gMViewBinder);
            a(activity, viewGroup, list, list2, gMViewBinder);
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void registerViewForInteraction(@NonNull ViewGroup viewGroup, List<View> list, GMViewBinder gMViewBinder) {
            registerViewForInteraction(viewGroup, list, null, gMViewBinder);
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void registerViewForInteraction(@NonNull ViewGroup viewGroup, List<View> list, List<View> list2, GMViewBinder gMViewBinder) {
            super.registerViewForInteraction(viewGroup, list, list2, gMViewBinder);
            a(GdtNativeLoader.this.g, viewGroup, list, list2, gMViewBinder);
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void unregisterView() {
        }
    }

    GdtNativeLoader a(int i) {
        this.d = i;
        return this;
    }

    GdtNativeLoader a(FrameLayout.LayoutParams layoutParams) {
        this.k = layoutParams;
        return this;
    }

    GdtNativeLoader a(TTVideoOption tTVideoOption) {
        this.h = tTVideoOption;
        return this;
    }

    GdtNativeLoader a(String str) {
        this.f4002c = str;
        return this;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x009c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void loadAd(android.content.Context r3, boolean r4, final com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter r5) {
        /*
            r2 = this;
            r2.g = r3
            r2.l = r5
            com.qq.e.ads.nativ.NativeUnifiedAD r4 = new com.qq.e.ads.nativ.NativeUnifiedAD
            java.lang.String r0 = r2.f4002c
            com.bytedance.msdk.adapter.gdt.GdtNativeLoader$1 r1 = new com.bytedance.msdk.adapter.gdt.GdtNativeLoader$1
            r1.<init>()
            r4.<init>(r3, r0, r1)
            r2.f4001b = r4
            com.bytedance.msdk.api.TTVideoOption r3 = r2.h
            if (r3 == 0) goto L1c
            com.bytedance.msdk.api.GDTExtraOption r3 = r3.getGDTExtraOption()
            r2.i = r3
        L1c:
            com.bytedance.msdk.api.GDTExtraOption r3 = r2.i
            if (r3 == 0) goto L2e
            int r3 = r3.getGDTMaxVideoDuration()
            r2.f = r3
            com.bytedance.msdk.api.GDTExtraOption r3 = r2.i
            int r3 = r3.getGDTMinVideoDuration()
            r2.e = r3
        L2e:
            int r3 = r2.e
            if (r3 <= 0) goto L37
            com.qq.e.ads.nativ.NativeUnifiedAD r4 = r2.f4001b
            r4.setMinVideoDuration(r3)
        L37:
            int r3 = r2.f
            if (r3 <= 0) goto L40
            com.qq.e.ads.nativ.NativeUnifiedAD r4 = r2.f4001b
            r4.setMaxVideoDuration(r3)
        L40:
            com.bytedance.msdk.api.GDTExtraOption r3 = r2.i
            com.qq.e.ads.cfg.VideoOption r3 = com.bytedance.msdk.adapter.gdt.GDTAdapterUtils.getVideoOption(r3)
            r2.j = r3
            com.qq.e.ads.nativ.NativeUnifiedAD r3 = r2.f4001b
            com.qq.e.ads.cfg.VideoOption r4 = r2.j
            int r4 = r4.getAutoPlayPolicy()
            android.content.Context r5 = r2.g
            int r4 = com.bytedance.msdk.adapter.gdt.GDTAdapterUtils.getVideoPlayPolicy(r4, r5)
            r3.setVideoPlayPolicy(r4)
            com.qq.e.ads.nativ.NativeUnifiedAD r3 = r2.f4001b
            r4 = 1
            r3.setVideoADContainerRender(r4)
            com.bytedance.msdk.api.GDTExtraOption r3 = r2.i
            if (r3 == 0) goto La9
            int r3 = r3.getBrowserType()
            if (r3 != 0) goto L71
            com.qq.e.ads.nativ.NativeUnifiedAD r3 = r2.f4001b
            com.qq.e.ads.cfg.BrowserType r5 = com.qq.e.ads.cfg.BrowserType.Default
        L6d:
            r3.setBrowserType(r5)
            goto L8c
        L71:
            com.bytedance.msdk.api.GDTExtraOption r3 = r2.i
            int r3 = r3.getBrowserType()
            if (r3 != r4) goto L7e
            com.qq.e.ads.nativ.NativeUnifiedAD r3 = r2.f4001b
            com.qq.e.ads.cfg.BrowserType r5 = com.qq.e.ads.cfg.BrowserType.Inner
            goto L6d
        L7e:
            com.bytedance.msdk.api.GDTExtraOption r3 = r2.i
            int r3 = r3.getBrowserType()
            r5 = 2
            if (r3 != r5) goto L8c
            com.qq.e.ads.nativ.NativeUnifiedAD r3 = r2.f4001b
            com.qq.e.ads.cfg.BrowserType r5 = com.qq.e.ads.cfg.BrowserType.Sys
            goto L6d
        L8c:
            com.bytedance.msdk.api.GDTExtraOption r3 = r2.i
            int r3 = r3.getDownAPPConfirmPolicy()
            if (r3 != 0) goto L9c
            com.qq.e.ads.nativ.NativeUnifiedAD r3 = r2.f4001b
            com.qq.e.ads.cfg.DownAPPConfirmPolicy r4 = com.qq.e.ads.cfg.DownAPPConfirmPolicy.Default
        L98:
            r3.setDownAPPConfirmPolicy(r4)
            goto La9
        L9c:
            com.bytedance.msdk.api.GDTExtraOption r3 = r2.i
            int r3 = r3.getDownAPPConfirmPolicy()
            if (r3 != r4) goto La9
            com.qq.e.ads.nativ.NativeUnifiedAD r3 = r2.f4001b
            com.qq.e.ads.cfg.DownAPPConfirmPolicy r4 = com.qq.e.ads.cfg.DownAPPConfirmPolicy.NOConfirm
            goto L98
        La9:
            com.qq.e.ads.nativ.NativeUnifiedADData r3 = r2.f4000a
            if (r3 == 0) goto Lb3
            r3.destroy()
            r3 = 0
            r2.f4000a = r3
        Lb3:
            com.qq.e.ads.nativ.NativeUnifiedAD r3 = r2.f4001b
            if (r3 == 0) goto Lbc
            int r4 = r2.d
            r3.loadData(r4)
        Lbc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.msdk.adapter.gdt.GdtNativeLoader.loadAd(android.content.Context, boolean, com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter):void");
    }
}
