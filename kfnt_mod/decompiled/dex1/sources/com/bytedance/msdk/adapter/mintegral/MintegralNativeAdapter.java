package com.bytedance.msdk.adapter.mintegral;

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
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.adapter.util.UIUtils;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdErrorUtil;
import com.bytedance.msdk.api.TTVideoOption;
import com.bytedance.msdk.api.format.TTMediaView;
import com.bytedance.msdk.api.format.TTNativeAdView;
import com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAdListener;
import com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeExpressAdListener;
import com.bytedance.msdk.api.v2.ad.nativeAd.GMViewBinder;
import com.bytedance.msdk.base.TTBaseAd;
import com.mbridge.msdk.nativex.view.MBMediaView;
import com.mbridge.msdk.out.Campaign;
import com.mbridge.msdk.out.Frame;
import com.mbridge.msdk.out.MBBidNativeHandler;
import com.mbridge.msdk.out.MBNativeAdvancedHandler;
import com.mbridge.msdk.out.MBNativeHandler;
import com.mbridge.msdk.out.MBridgeIds;
import com.mbridge.msdk.out.NativeAdvancedAdListener;
import com.mbridge.msdk.out.NativeListener;
import com.mbridge.msdk.out.OnMBMediaViewListener;
import com.mbridge.msdk.widget.MBAdChoice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class MintegralNativeAdapter extends TTAbsAdLoaderAdapter {
    private static AtomicInteger f = new AtomicInteger(0);

    /* renamed from: a, reason: collision with root package name */
    private Context f4093a;

    /* renamed from: b, reason: collision with root package name */
    private List<TTBaseAd> f4094b = new ArrayList();

    /* renamed from: c, reason: collision with root package name */
    private TTVideoOption f4095c;
    private MTExpressNativeAd d;
    private MTNativeAd e;

    class MTExpressNativeAd extends TTBaseAd {

        /* renamed from: a, reason: collision with root package name */
        MBNativeAdvancedHandler f4096a;

        /* renamed from: b, reason: collision with root package name */
        private int f4097b;

        /* renamed from: c, reason: collision with root package name */
        private int f4098c;

        MTExpressNativeAd(Activity activity) {
            setExpressAd(true);
            this.f4096a = new MBNativeAdvancedHandler(activity, "", MintegralNativeAdapter.this.getAdSlotId());
            this.f4097b = ((TTAbsAdLoaderAdapter) MintegralNativeAdapter.this).mAdSlot.getImgAcceptedWidth();
            if (this.f4097b <= 0) {
                this.f4097b = UIUtils.px2dip(activity, UIUtils.getScreenWidth(activity));
            }
            int i = this.f4097b;
            this.f4098c = (int) ((i / 32.0f) * 25.0f);
            this.f4096a.setNativeViewSize(i, this.f4098c);
            if (MintegralNativeAdapter.this.f4095c != null) {
                this.f4096a.setPlayMuteState(MintegralNativeAdapter.this.f4095c.isMuted() ? 1 : 2);
            }
            this.f4096a.setAdListener(new NativeAdvancedAdListener(MintegralNativeAdapter.this) { // from class: com.bytedance.msdk.adapter.mintegral.MintegralNativeAdapter.MTExpressNativeAd.1
                public void closeFullScreen(MBridgeIds mBridgeIds) {
                }

                public void onClick(MBridgeIds mBridgeIds) {
                    if (((TTBaseAd) MTExpressNativeAd.this).mTTNativeAdListener != null) {
                        Logger.w("TTMediationSDK", TTLogUtil.getTagThirdLevelById(MintegralNativeAdapter.this.getAdapterRit(), MintegralNativeAdapter.this.getAdSlotId()) + "MT native expression onAdClick...onAdClick....");
                        ((TTBaseAd) MTExpressNativeAd.this).mTTNativeAdListener.onAdClick();
                    }
                }

                public void onClose(MBridgeIds mBridgeIds) {
                }

                public void onLeaveApp(MBridgeIds mBridgeIds) {
                }

                public void onLoadFailed(MBridgeIds mBridgeIds, String str) {
                    MintegralNativeAdapter.f.incrementAndGet();
                    if (MintegralNativeAdapter.f.get() == ((TTAbsAdLoaderAdapter) MintegralNativeAdapter.this).mAdSlot.getAdCount()) {
                        MintegralNativeAdapter.this.notifyAdFailed(AdErrorUtil.obtainAdError(AdError.ERROR_CODE_THIRD_SDK_LOAD_FAIL, str));
                    }
                }

                public void onLoadSuccessed(MBridgeIds mBridgeIds) {
                    MintegralNativeAdapter.f.incrementAndGet();
                    MintegralNativeAdapter.this.f4094b.add(MTExpressNativeAd.this);
                    if (MintegralNativeAdapter.f.get() == ((TTAbsAdLoaderAdapter) MintegralNativeAdapter.this).mAdSlot.getAdCount()) {
                        MintegralNativeAdapter mintegralNativeAdapter = MintegralNativeAdapter.this;
                        mintegralNativeAdapter.notifyAdLoaded(mintegralNativeAdapter.f4094b);
                    }
                }

                public void onLogImpression(MBridgeIds mBridgeIds) {
                    if (((TTBaseAd) MTExpressNativeAd.this).mTTNativeAdListener != null) {
                        Logger.w("TTMediationSDK", TTLogUtil.getTagThirdLevelById(MintegralNativeAdapter.this.getAdapterRit(), MintegralNativeAdapter.this.getAdSlotId()) + "MT native expression onLogImpression=" + toString());
                        ((TTBaseAd) MTExpressNativeAd.this).mTTNativeAdListener.onAdShow();
                    }
                }

                public void showFullScreen(MBridgeIds mBridgeIds) {
                }
            });
        }

        void a() {
            MBNativeAdvancedHandler mBNativeAdvancedHandler = this.f4096a;
            if (mBNativeAdvancedHandler != null) {
                mBNativeAdvancedHandler.load();
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return 5;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public View getAdView() {
            MBNativeAdvancedHandler mBNativeAdvancedHandler = this.f4096a;
            return mBNativeAdvancedHandler != null ? mBNativeAdvancedHandler.getAdViewGroup() : super.getAdView();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return this.f4096a == null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isAdCacheReady() {
            return isReady();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isReady() {
            MBNativeAdvancedHandler mBNativeAdvancedHandler = this.f4096a;
            if (mBNativeAdvancedHandler != null) {
                return mBNativeAdvancedHandler.isReady();
            }
            return false;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            MBNativeAdvancedHandler mBNativeAdvancedHandler = this.f4096a;
            if (mBNativeAdvancedHandler != null) {
                mBNativeAdvancedHandler.release();
                this.f4096a = null;
            }
            super.onDestroy();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onPause() {
            super.onPause();
            MBNativeAdvancedHandler mBNativeAdvancedHandler = this.f4096a;
            if (mBNativeAdvancedHandler != null) {
                mBNativeAdvancedHandler.onPause();
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onResume() {
            super.onResume();
            MBNativeAdvancedHandler mBNativeAdvancedHandler = this.f4096a;
            if (mBNativeAdvancedHandler != null) {
                mBNativeAdvancedHandler.onResume();
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void render() {
            GMNativeAdListener gMNativeAdListener;
            MBNativeAdvancedHandler mBNativeAdvancedHandler;
            super.render();
            MBNativeAdvancedHandler mBNativeAdvancedHandler2 = this.f4096a;
            if (mBNativeAdvancedHandler2 == null || mBNativeAdvancedHandler2.getAdViewGroup() == null || (gMNativeAdListener = this.mTTNativeAdListener) == null) {
                GMNativeAdListener gMNativeAdListener2 = this.mTTNativeAdListener;
                if (gMNativeAdListener2 instanceof GMNativeExpressAdListener) {
                    ((GMNativeExpressAdListener) gMNativeAdListener2).onRenderFail(null, AdError.getMessage(106), 106);
                    return;
                }
                return;
            }
            if ((gMNativeAdListener instanceof GMNativeExpressAdListener) && (mBNativeAdvancedHandler = this.f4096a) != null && mBNativeAdvancedHandler.isReady()) {
                this.f4096a.onResume();
                ((GMNativeExpressAdListener) this.mTTNativeAdListener).onRenderSuccess(this.f4097b, this.f4098c);
            }
        }
    }

    class MTNativeAd extends TTBaseAd {

        /* renamed from: a, reason: collision with root package name */
        Campaign f4100a;

        /* renamed from: b, reason: collision with root package name */
        MBNativeHandler f4101b;

        /* renamed from: c, reason: collision with root package name */
        MBBidNativeHandler f4102c;
        ViewGroup d;
        List<View> e;
        MBMediaView f;
        Context g;
        int h;
        NativeListener.NativeAdListener i = new NativeListener.NativeAdListener() { // from class: com.bytedance.msdk.adapter.mintegral.MintegralNativeAdapter.MTNativeAd.1
            public void onAdClick(Campaign campaign) {
                if (campaign == null || ((TTBaseAd) MTNativeAd.this).mTTNativeAdListener == null) {
                    return;
                }
                Logger.w("TTMediationSDK", TTLogUtil.getTagThirdLevelById(MintegralNativeAdapter.this.getAdapterRit(), MintegralNativeAdapter.this.getAdSlotId()) + "MT onAdClick...onAdClick....");
                ((TTBaseAd) MTNativeAd.this).mTTNativeAdListener.onAdClick();
            }

            public void onAdFramesLoaded(List<Frame> list) {
            }

            public void onAdLoadError(String str) {
                MintegralNativeAdapter.f.incrementAndGet();
                int i = MintegralNativeAdapter.f.get();
                MTNativeAd mTNativeAd = MTNativeAd.this;
                if (i == mTNativeAd.h) {
                    MintegralNativeAdapter.this.notifyAdFailed(AdErrorUtil.obtainAdError(AdError.ERROR_CODE_THIRD_SDK_LOAD_FAIL, str));
                }
            }

            public void onAdLoaded(List<Campaign> list, int i) {
                MintegralNativeAdapter.f.incrementAndGet();
                if (list == null || list.size() <= 0 || list.get(0) == null) {
                    MintegralNativeAdapter.this.notifyAdFailed(AdErrorUtil.obtainAdError(AdError.ERROR_CODE_NO_AD, AdError.getMessage(AdError.ERROR_CODE_NO_AD)));
                    return;
                }
                MTNativeAd.this.f4100a = list.get(0);
                MTNativeAd mTNativeAd = MTNativeAd.this;
                mTNativeAd.a(mTNativeAd.f4100a);
                MintegralNativeAdapter.this.f4094b.add(MTNativeAd.this);
                int i2 = MintegralNativeAdapter.f.get();
                MTNativeAd mTNativeAd2 = MTNativeAd.this;
                if (i2 == mTNativeAd2.h) {
                    MintegralNativeAdapter mintegralNativeAdapter = MintegralNativeAdapter.this;
                    mintegralNativeAdapter.notifyAdLoaded(mintegralNativeAdapter.f4094b);
                }
            }

            public void onLoggingImpression(int i) {
                if (((TTBaseAd) MTNativeAd.this).mTTNativeAdListener != null) {
                    Logger.w("TTMediationSDK", TTLogUtil.getTagThirdLevelById(MintegralNativeAdapter.this.getAdapterRit(), MintegralNativeAdapter.this.getAdSlotId()) + "onLoggingImpression...onLoggingImpression....=" + toString());
                    ((TTBaseAd) MTNativeAd.this).mTTNativeAdListener.onAdShow();
                }
            }
        };
        OnMBMediaViewListener j = new OnMBMediaViewListener() { // from class: com.bytedance.msdk.adapter.mintegral.MintegralNativeAdapter.MTNativeAd.2
            public void onEnterFullscreen() {
            }

            public void onExitFullscreen() {
            }

            public void onFinishRedirection(Campaign campaign, String str) {
            }

            public void onRedirectionFailed(Campaign campaign, String str) {
            }

            public void onStartRedirection(Campaign campaign, String str) {
            }

            public void onVideoAdClicked(Campaign campaign) {
                if (((TTBaseAd) MTNativeAd.this).mTTNativeAdListener != null) {
                    Logger.w("TTMediationSDK", TTLogUtil.getTagThirdLevelById(MintegralNativeAdapter.this.getAdapterRit(), MintegralNativeAdapter.this.getAdSlotId()) + "MT onVideoAdClicked...onVideoAdClicked....");
                    ((TTBaseAd) MTNativeAd.this).mTTNativeAdListener.onAdClick();
                }
            }

            public void onVideoStart() {
                if (((TTBaseAd) MTNativeAd.this).mTTVideoListener != null) {
                    ((TTBaseAd) MTNativeAd.this).mTTVideoListener.onVideoStart();
                }
            }
        };
        NativeListener.NativeTrackingListener k = new NativeListener.NativeTrackingListener(this) { // from class: com.bytedance.msdk.adapter.mintegral.MintegralNativeAdapter.MTNativeAd.3
            public void onDismissLoading(Campaign campaign) {
            }

            public void onDownloadFinish(Campaign campaign) {
                Log.e("pro", "finish---");
            }

            public void onDownloadProgress(int i) {
                Log.e("pro", "progress----" + i);
            }

            public void onDownloadStart(Campaign campaign) {
                Log.e("pro", "start---");
            }

            public void onFinishRedirection(Campaign campaign, String str) {
                Log.e("pro", "onFinishRedirection---" + str);
            }

            public boolean onInterceptDefaultLoadingDialog() {
                return false;
            }

            public void onRedirectionFailed(Campaign campaign, String str) {
                Log.e("pro", "onRedirectionFailed---");
            }

            public void onShowLoading(Campaign campaign) {
            }

            public void onStartRedirection(Campaign campaign, String str) {
                Log.e("pro", "onStartRedirection---");
            }
        };

        MTNativeAd(Context context) {
            this.g = context;
            setExpressAd(false);
        }

        private void a() {
            Map nativeProperties = MBNativeHandler.getNativeProperties("", MintegralNativeAdapter.this.getAdSlotId());
            nativeProperties.put("ad_num", 1);
            nativeProperties.put("videoSupport", true);
            this.f4101b = new MBNativeHandler(nativeProperties, this.g);
            this.f4101b.setAdListener(this.i);
            this.f4101b.load();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(Campaign campaign) {
            if (TextUtils.isEmpty(MintegralNativeAdapter.this.getAdm())) {
                this.f4101b.setTrackingListener(this.k);
            } else {
                this.f4102c.setTrackingListener(this.k);
            }
            setAdDescription(campaign.getAppDesc());
            setTitle(campaign.getAppName());
            setIconUrl(campaign.getIconUrl());
            setImageUrl(campaign.getImageUrl());
            setActionText(campaign.getAdCall());
            setPackageName(campaign.getPackageName());
            setRating(campaign.getRating());
            setImageMode(5);
            setSource(campaign.getAppName());
            ArrayList arrayList = new ArrayList();
            arrayList.add("安装");
            arrayList.add("立即下载");
            arrayList.add("立即安装");
            arrayList.add("下载");
            arrayList.add("install");
            setInteractionType(arrayList.contains(campaign.getAdCall()) ? 4 : 3);
        }

        private void a(String str) {
            Map nativeProperties = MBNativeHandler.getNativeProperties("", MintegralNativeAdapter.this.getAdSlotId());
            nativeProperties.put("ad_num", 1);
            nativeProperties.put("videoSupport", true);
            this.f4102c = new MBBidNativeHandler(nativeProperties, this.g);
            this.f4102c.setAdListener(this.i);
            this.f4102c.bidLoad(str);
        }

        void a(int i) {
            this.h = i;
            if (TextUtils.isEmpty(MintegralNativeAdapter.this.getAdm())) {
                Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(MintegralNativeAdapter.this.getAdapterRit(), MintegralNativeAdapter.this.getAdSlotId()) + "loadAdWithoutBid......MintegralNativeAdapter...loadAd start....");
                a();
                return;
            }
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(MintegralNativeAdapter.this.getAdapterRit(), MintegralNativeAdapter.this.getAdSlotId()) + "loadAdWithBid......MintegralNativeAdapter...loadAd start....");
            a(MintegralNativeAdapter.this.getAdm());
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public View getAdLogoView() {
            MBAdChoice mBAdChoice = new MBAdChoice(this.g);
            mBAdChoice.setCampaign(this.f4100a);
            return mBAdChoice;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return 5;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return !TextUtils.isEmpty(MintegralNativeAdapter.this.getAdm()) ? this.f4102c == null : this.f4101b == null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            MBNativeHandler mBNativeHandler = this.f4101b;
            if (mBNativeHandler != null) {
                mBNativeHandler.setTrackingListener((NativeListener.NativeTrackingListener) null);
                this.f4101b.setAdListener((NativeListener.NativeAdListener) null);
                this.f4101b.release();
                this.f4101b = null;
                this.g = null;
            }
            MBBidNativeHandler mBBidNativeHandler = this.f4102c;
            if (mBBidNativeHandler != null) {
                mBBidNativeHandler.setTrackingListener((NativeListener.NativeTrackingListener) null);
                this.f4102c.setAdListener((NativeListener.NativeAdListener) null);
                this.f4102c.bidRelease();
                this.f4102c = null;
                this.g = null;
            }
            MBMediaView mBMediaView = this.f;
            if (mBMediaView != null) {
                mBMediaView.destory();
            }
            super.onDestroy();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void registerViewForInteraction(@NonNull ViewGroup viewGroup, List<View> list, List<View> list2, GMViewBinder gMViewBinder) {
            super.registerViewForInteraction(viewGroup, list, list2, gMViewBinder);
            this.d = viewGroup;
            this.e = new ArrayList();
            if ((viewGroup instanceof TTNativeAdView) && this.f4100a != null) {
                TTNativeAdView tTNativeAdView = (TTNativeAdView) viewGroup;
                this.f = new MBMediaView(this.g);
                this.f.setProgressVisibility(true);
                this.f.setSoundIndicatorVisibility(true);
                int i = 0;
                this.f.setVideoSoundOnOff(!(MintegralNativeAdapter.this.f4095c == null || MintegralNativeAdapter.this.f4095c.isMuted()));
                this.f.setIsAllowFullScreen(false);
                this.f.setAllowVideoRefresh(false);
                this.f.setAllowLoopPlay(false);
                this.f.setOnMediaViewListener(this.j);
                View mBAdChoice = new MBAdChoice(this.g);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
                if (this.f4100a.getAdchoiceSizeHeight() != 0) {
                    layoutParams.height = this.f4100a.getAdchoiceSizeHeight();
                }
                if (this.f4100a.getAdchoiceSizeWidth() != 0) {
                    layoutParams.width = this.f4100a.getAdchoiceSizeWidth();
                }
                layoutParams.rightMargin = 8;
                layoutParams.topMargin = 8;
                layoutParams.gravity = 53;
                mBAdChoice.setCampaign(this.f4100a);
                mBAdChoice.setId(R.id.tt_mediation_mtg_ad_choice);
                while (true) {
                    if (i >= tTNativeAdView.getChildCount()) {
                        break;
                    }
                    View childAt = tTNativeAdView.getChildAt(i);
                    if (childAt instanceof MBAdChoice) {
                        tTNativeAdView.removeViewInLayout(childAt);
                        break;
                    }
                    i++;
                }
                tTNativeAdView.addView(mBAdChoice, layoutParams);
                TTMediaView tTMediaView = (TTMediaView) tTNativeAdView.findViewById(gMViewBinder.mediaViewId);
                if (tTMediaView != null) {
                    tTMediaView.removeAllViews();
                    tTMediaView.addView((View) this.f, -1, -1);
                }
                this.e.add(tTMediaView);
                this.f.setNativeAd(this.f4100a);
            }
            if (list != null) {
                this.e.addAll(list);
            }
            if (list2 != null) {
                this.e.addAll(list2);
            }
            View view = this.f;
            if (view != null) {
                this.e.add(view);
            }
            if (TextUtils.isEmpty(MintegralNativeAdapter.this.getAdm())) {
                MBNativeHandler mBNativeHandler = this.f4101b;
                if (mBNativeHandler != null) {
                    mBNativeHandler.registerView(viewGroup, this.e, this.f4100a);
                    return;
                }
                return;
            }
            MBBidNativeHandler mBBidNativeHandler = this.f4102c;
            if (mBBidNativeHandler != null) {
                mBBidNativeHandler.registerView(viewGroup, this.e, this.f4100a);
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void unregisterView() {
            if (TextUtils.isEmpty(MintegralNativeAdapter.this.getAdm())) {
                MBNativeHandler mBNativeHandler = this.f4101b;
                if (mBNativeHandler != null) {
                    mBNativeHandler.unregisterView(this.d, this.e, this.f4100a);
                    return;
                }
                return;
            }
            MBBidNativeHandler mBBidNativeHandler = this.f4102c;
            if (mBBidNativeHandler != null) {
                mBBidNativeHandler.unregisterView(this.d, this.e, this.f4100a);
            }
        }
    }

    public MintegralNativeAdapter() {
        new HashMap();
    }

    private void b() {
        if (!(this.f4093a instanceof Activity)) {
            notifyAdFailed(new AdError(AdError.ERROR_CODE_CONTEXT_ERROR, "需要Activity ,但传的是context", AdError.ERROR_CODE_CONTEXT_ERROR, "需要Activity ,但传的是context"));
            return;
        }
        for (int i = 0; i < getAdLoadCount(); i++) {
            this.d = new MTExpressNativeAd((Activity) this.f4093a);
            this.d.a();
        }
    }

    private void c() {
        int adLoadCount = !TextUtils.isEmpty(getAdm()) ? 1 : getAdLoadCount();
        for (int i = 0; i < adLoadCount; i++) {
            this.e = new MTNativeAd(this.f4093a);
            this.e.a(adLoadCount);
        }
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void destroy() {
        MTExpressNativeAd mTExpressNativeAd = this.d;
        if (mTExpressNativeAd != null) {
            mTExpressNativeAd.onDestroy();
        }
        MTNativeAd mTNativeAd = this.e;
        if (mTNativeAd != null) {
            mTNativeAd.onDestroy();
        }
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getAdNetWorkName() {
        return "mintegral";
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getSdkVersion() {
        return "MAL_15.7.57";
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void loadAd(Context context, Map<String, Object> map) {
        this.f4093a = context;
        this.f4095c = this.mAdSlot.getTTVideoOption();
        f.set(0);
        this.f4094b.clear();
        Object obj = map.get("tt_ad_origin_type");
        if (obj != null) {
            int iIntValue = ((Integer) obj).intValue();
            if (iIntValue == 1) {
                b();
                return;
            } else if (iIntValue == 2) {
                c();
                return;
            } else if (iIntValue == 3) {
                notifyAdFailed(AdErrorUtil.obtainAdError(AdError.ERROR_CODE_RENDER_TYPE_MISMATCH, AdError.getMessage(AdError.ERROR_CODE_RENDER_TYPE_MISMATCH)));
                return;
            }
        }
        if (this.mAdSlot.getAdStyleType() == 1) {
            b();
        } else if (this.mAdSlot.getAdStyleType() == 2) {
            c();
        }
    }
}
