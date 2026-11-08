package com.bytedance.msdk.adapter.pangle;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.listener.ITTAdapterBannerAdListener;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.v2.GMDislikeCallback;
import com.bytedance.msdk.base.TTBaseAd;
import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdDislike;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTNativeExpressAd;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class PangleBannerAdapter extends TTAbsAdLoaderAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f4128a;

    class TTBannerView extends TTBaseAd {

        /* renamed from: a, reason: collision with root package name */
        TTNativeExpressAd f4129a;

        /* renamed from: b, reason: collision with root package name */
        View f4130b;

        /* renamed from: c, reason: collision with root package name */
        final Object f4131c = new Object();
        TTNativeExpressAd.ExpressAdInteractionListener d = new TTNativeExpressAd.ExpressAdInteractionListener() { // from class: com.bytedance.msdk.adapter.pangle.PangleBannerAdapter.TTBannerView.4
            @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressAdInteractionListener
            public void onAdClicked(View view, int i) {
                if (((TTBaseAd) TTBannerView.this).mTTAdatperCallback instanceof ITTAdapterBannerAdListener) {
                    TTBannerView.this.a().onAdClicked();
                }
            }

            @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressAdInteractionListener
            public void onAdShow(View view, int i) {
                if (((TTBaseAd) TTBannerView.this).mTTAdatperCallback instanceof ITTAdapterBannerAdListener) {
                    TTBannerView.this.a().onAdShow();
                }
            }

            @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressAdInteractionListener
            public void onRenderFail(View view, String str, int i) {
                TTBannerView.this.f = false;
                Logger.e("TTMediationSDK_banner", TTLogUtil.getTagThirdLevelById(PangleBannerAdapter.this.getAdapterRit(), PangleBannerAdapter.this.getAdSlotId()) + "TTBannerView onRenderFail -> code=" + i + ",msg=" + str);
            }

            @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressAdInteractionListener
            public void onRenderSuccess(View view, float f, float f2) {
                Logger.e("TTMediationSDK_banner", TTLogUtil.getTagThirdLevelById(PangleBannerAdapter.this.getAdapterRit(), PangleBannerAdapter.this.getAdSlotId()) + "TTBannerView onRenderSuccess 渲染成功！！");
                TTBannerView tTBannerView = TTBannerView.this;
                if (tTBannerView.f4130b instanceof FrameLayout) {
                    PangleBannerAdapter.this.removeFromParent(view);
                    ((FrameLayout) TTBannerView.this.f4130b).addView(view, new ViewGroup.LayoutParams(-1, -1));
                }
                TTBannerView.this.f = true;
            }
        };
        private boolean f;

        TTBannerView(ITTAdapterBannerAdListener iTTAdapterBannerAdListener) {
            this.mTTAdatperCallback = iTTAdapterBannerAdListener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ITTAdapterBannerAdListener a() {
            return (ITTAdapterBannerAdListener) this.mTTAdatperCallback;
        }

        private int[] a(int i) {
            switch (i) {
                case 1:
                    return new int[]{320, 50};
                case 2:
                    return new int[]{320, 100};
                case 3:
                    return new int[]{TTAdConstant.DEFAULT_LIVE_SHOW_TIME_MAX, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION};
                case 4:
                    return new int[]{468, 60};
                case 5:
                    return new int[]{728, 90};
                case 6:
                    if (((TTAbsAdLoaderAdapter) PangleBannerAdapter.this).mAdSlot.getImgAcceptedWidth() > 0 && ((TTAbsAdLoaderAdapter) PangleBannerAdapter.this).mAdSlot.getImgAcceptedHeight() > 0) {
                        return new int[]{((TTAbsAdLoaderAdapter) PangleBannerAdapter.this).mAdSlot.getImgAcceptedWidth(), ((TTAbsAdLoaderAdapter) PangleBannerAdapter.this).mAdSlot.getImgAcceptedHeight()};
                    }
                    break;
            }
            return new int[]{320, 50};
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public long getAdId() {
            TTNativeExpressAd tTNativeExpressAd = this.f4129a;
            return tTNativeExpressAd != null ? PangleAdapterUtils.getAdId(tTNativeExpressAd.getMediaExtraInfo()) : super.getAdId();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return ((TTAbsAdLoaderAdapter) PangleBannerAdapter.this).mAdSlot.getAdType();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public synchronized View getAdView() {
            synchronized (this.f4131c) {
                if (this.f4130b == null) {
                    try {
                        long jUptimeMillis = SystemClock.uptimeMillis();
                        long j = 2000 + jUptimeMillis;
                        while (!this.f && jUptimeMillis < j) {
                            this.f4131c.wait(j - jUptimeMillis);
                            jUptimeMillis = SystemClock.uptimeMillis();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            return this.f4130b;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public long getCreativeId() {
            TTNativeExpressAd tTNativeExpressAd = this.f4129a;
            return tTNativeExpressAd != null ? PangleAdapterUtils.getCreativeId(tTNativeExpressAd.getMediaExtraInfo()) : super.getCreativeId();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public String getReqId() {
            TTNativeExpressAd tTNativeExpressAd = this.f4129a;
            return tTNativeExpressAd != null ? PangleAdapterUtils.getReqId(tTNativeExpressAd.getMediaExtraInfo()) : super.getReqId();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return this.f4129a == null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDislike() {
            return true;
        }

        public void loadAd() {
            TTAdNative tTAdNativeCreateAdNative = TTAdSdk.getAdManager().createAdNative(PangleBannerAdapter.this.f4128a);
            AdSlot.Builder builderBuildPangleAdSlot = PangleAdapterUtils.buildPangleAdSlot(((TTAbsAdLoaderAdapter) PangleBannerAdapter.this).mAdSlot, PangleBannerAdapter.this.getAdSlotId(), ((TTAbsAdLoaderAdapter) PangleBannerAdapter.this).mWaterfallAbTestParam, PangleBannerAdapter.this.getClientReqId(), PangleBannerAdapter.this.getAdm(), false);
            int[] iArrA = a(((TTAbsAdLoaderAdapter) PangleBannerAdapter.this).mAdSlot.getBannerSize());
            builderBuildPangleAdSlot.setExpressViewAcceptedSize(iArrA[0], iArrA[1]);
            tTAdNativeCreateAdNative.loadBannerExpressAd(builderBuildPangleAdSlot.build(), new TTAdNative.NativeExpressAdListener() { // from class: com.bytedance.msdk.adapter.pangle.PangleBannerAdapter.TTBannerView.1
                @Override // com.bytedance.sdk.openadsdk.TTAdNative.NativeExpressAdListener, com.bytedance.sdk.openadsdk.common.CommonListener
                public void onError(int i, String str) {
                    PangleBannerAdapter.this.notifyAdFailed(PangleAdapterUtils.buildAdError(i, str));
                }

                @Override // com.bytedance.sdk.openadsdk.TTAdNative.NativeExpressAdListener
                public void onNativeExpressAdLoad(List<TTNativeExpressAd> list) {
                    Map<String, Object> mediaExtraInfo;
                    if (list == null || list.size() == 0 || PangleBannerAdapter.this.f4128a == null) {
                        PangleBannerAdapter.this.notifyAdFailed(PangleAdapterUtils.buildAdError(AdError.ERROR_CODE_NO_AD, AdError.getMessage(AdError.ERROR_CODE_NO_AD)));
                        return;
                    }
                    TTBannerView.this.f4129a = list.get(0);
                    TTBannerView.this.setExpressAd(true);
                    TTBannerView tTBannerView = TTBannerView.this;
                    tTBannerView.setInteractionType(tTBannerView.f4129a.getInteractionType());
                    TTBannerView tTBannerView2 = TTBannerView.this;
                    tTBannerView2.setImageMode(tTBannerView2.f4129a.getImageMode());
                    if (PangleBannerAdapter.this.isClientBidding() && (mediaExtraInfo = TTBannerView.this.f4129a.getMediaExtraInfo()) != null) {
                        double value = PangleAdapterUtils.getValue(mediaExtraInfo.get("price"));
                        Logger.d("TTMediationSDK_ECMP", TTLogUtil.getTagThirdLevelById(PangleBannerAdapter.this.getAdapterRit(), PangleBannerAdapter.this.getAdSlotId()) + "pangle banner 返回的 cpm价格：" + value);
                        TTBannerView tTBannerView3 = TTBannerView.this;
                        if (value <= 0.0d) {
                            value = 0.0d;
                        }
                        tTBannerView3.setCpm(value);
                    }
                    TTBannerView tTBannerView4 = TTBannerView.this;
                    tTBannerView4.f4129a.setExpressInteractionListener(tTBannerView4.d);
                    TTBannerView.this.f4129a.render();
                    TTBannerView tTBannerView5 = TTBannerView.this;
                    tTBannerView5.f4130b = new FrameLayout(PangleBannerAdapter.this.f4128a);
                    TTBannerView tTBannerView6 = TTBannerView.this;
                    PangleBannerAdapter.this.notifyAdLoaded(tTBannerView6);
                }
            });
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            super.onDestroy();
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.bytedance.msdk.adapter.pangle.PangleBannerAdapter.TTBannerView.3
                @Override // java.lang.Runnable
                public void run() {
                    TTNativeExpressAd tTNativeExpressAd = TTBannerView.this.f4129a;
                    if (tTNativeExpressAd != null) {
                        tTNativeExpressAd.setExpressInteractionListener((TTNativeExpressAd.AdInteractionListener) null);
                        TTBannerView.this.f4129a.destroy();
                        TTBannerView.this.f4129a = null;
                    }
                }
            });
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void setDislikeCallback(Activity activity, final GMDislikeCallback gMDislikeCallback) {
            TTNativeExpressAd tTNativeExpressAd = this.f4129a;
            if (tTNativeExpressAd != null) {
                tTNativeExpressAd.setDislikeCallback(activity, new TTAdDislike.DislikeInteractionCallback() { // from class: com.bytedance.msdk.adapter.pangle.PangleBannerAdapter.TTBannerView.2
                    @Override // com.bytedance.sdk.openadsdk.TTAdDislike.DislikeInteractionCallback
                    public void onCancel() {
                        GMDislikeCallback gMDislikeCallback2 = gMDislikeCallback;
                        if (gMDislikeCallback2 != null) {
                            gMDislikeCallback2.onCancel();
                        }
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTAdDislike.DislikeInteractionCallback
                    public void onSelected(int i, String str, boolean z) {
                        GMDislikeCallback gMDislikeCallback2 = gMDislikeCallback;
                        if (gMDislikeCallback2 != null) {
                            gMDislikeCallback2.onSelected(i, str);
                            if (((TTBaseAd) TTBannerView.this).mTTAdatperCallback instanceof ITTAdapterBannerAdListener) {
                                TTBannerView.this.a().onAdClosed();
                            }
                        }
                    }

                    @Override // com.bytedance.sdk.openadsdk.TTAdDislike.DislikeInteractionCallback
                    public void onShow() {
                        GMDislikeCallback gMDislikeCallback2 = gMDislikeCallback;
                        if (gMDislikeCallback2 != null) {
                            gMDislikeCallback2.onShow();
                        }
                    }
                });
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
        this.f4128a = context;
        if (map != null) {
            Object obj = map.get("tt_ad_network_callback");
            new TTBannerView(obj instanceof ITTAdapterBannerAdListener ? (ITTAdapterBannerAdListener) obj : null).loadAd();
        }
    }
}
