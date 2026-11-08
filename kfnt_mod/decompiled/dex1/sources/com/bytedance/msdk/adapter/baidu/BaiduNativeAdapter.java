package com.bytedance.msdk.adapter.baidu;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import com.baidu.mobads.sdk.api.AdSettings;
import com.baidu.mobads.sdk.api.BaiduNativeManager;
import com.baidu.mobads.sdk.api.FeedNativeView;
import com.baidu.mobads.sdk.api.INativeVideoListener;
import com.baidu.mobads.sdk.api.NativeResponse;
import com.baidu.mobads.sdk.api.RequestParameters;
import com.baidu.mobads.sdk.api.XAdNativeResponse;
import com.baidu.mobads.sdk.api.XNativeView;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.util.DownloadImageTask;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.BaiduExtraOptions;
import com.bytedance.msdk.api.BaiduNativeSmartOptStyleParams;
import com.bytedance.msdk.api.BaiduRequestParameters;
import com.bytedance.msdk.api.TTVideoOption;
import com.bytedance.msdk.api.format.TTMediaView;
import com.bytedance.msdk.api.format.TTNativeAdView;
import com.bytedance.msdk.api.v2.GMDislikeCallback;
import com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAdListener;
import com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeExpressAdListener;
import com.bytedance.msdk.api.v2.ad.nativeAd.GMViewBinder;
import com.bytedance.msdk.base.TTBaseAd;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class BaiduNativeAdapter extends TTAbsAdLoaderAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f3940a;

    /* renamed from: b, reason: collision with root package name */
    private TTVideoOption f3941b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f3942c;
    private BaiduExtraOptions d;
    private BaiduNativeSmartOptStyleParams e;
    private Map<NativeResponse, BaiduNativeAd> f = new HashMap();
    private final BaiduNativeManager.FeedAdListener g = new BaiduNativeManager.FeedAdListener() { // from class: com.bytedance.msdk.adapter.baidu.BaiduNativeAdapter.1
        @Override // com.baidu.mobads.sdk.api.BaiduNativeManager.FeedAdListener
        public void onLpClosed() {
            Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduNativeAdapter.this.getAdapterRit(), BaiduNativeAdapter.this.getAdSlotId()) + "Baidu native ad  ....... onLpClosed:落地页关闭");
        }

        @Override // com.baidu.mobads.sdk.api.BaiduNativeManager.FeedAdListener
        public void onNativeFail(int i, String str) {
            Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduNativeAdapter.this.getAdapterRit(), BaiduNativeAdapter.this.getAdSlotId()) + "Baidu onLoadFail native message=" + str + "，errorCode=" + i);
            BaiduNativeAdapter.this.notifyAdFailed(new AdError(AdError.ERROR_CODE_AD_LOAD_FAIL, AdError.getMessage(AdError.ERROR_CODE_AD_LOAD_FAIL), i, str));
        }

        @Override // com.baidu.mobads.sdk.api.BaiduNativeManager.FeedAdListener
        public void onNativeLoad(List<NativeResponse> list) {
            if (list == null || list.size() <= 0) {
                BaiduNativeAdapter.this.notifyAdFailed(new AdError(AdError.ERROR_CODE_NO_AD, AdError.AD_NO_FILL, AdError.ERROR_CODE_NO_AD, AdError.AD_NO_FILL));
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (NativeResponse nativeResponse : list) {
                if (nativeResponse != null) {
                    BaiduNativeAd baiduNativeAd = BaiduNativeAdapter.this.new BaiduNativeAd((XAdNativeResponse) nativeResponse);
                    BaiduNativeAdapter.this.f.put(nativeResponse, baiduNativeAd);
                    arrayList.add(baiduNativeAd);
                }
            }
            BaiduNativeAdapter.this.notifyAdLoaded(arrayList);
        }

        @Override // com.baidu.mobads.sdk.api.BaiduNativeManager.FeedAdListener
        public void onNoAd(int i, String str) {
            Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduNativeAdapter.this.getAdapterRit(), BaiduNativeAdapter.this.getAdSlotId()) + "Baidu onNoAd native message=" + str + "，errorCode=" + i);
            BaiduNativeAdapter.this.notifyAdFailed(new AdError(AdError.ERROR_CODE_NO_AD, AdError.getMessage(AdError.ERROR_CODE_NO_AD), i, str));
        }

        @Override // com.baidu.mobads.sdk.api.BaiduNativeManager.FeedAdListener
        public void onVideoDownloadFailed() {
            Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduNativeAdapter.this.getAdapterRit(), BaiduNativeAdapter.this.getAdSlotId()) + "Baidu native ad  ....... onVideoDownloadFailed:");
        }

        @Override // com.baidu.mobads.sdk.api.BaiduNativeManager.FeedAdListener
        public void onVideoDownloadSuccess() {
            Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduNativeAdapter.this.getAdapterRit(), BaiduNativeAdapter.this.getAdSlotId()) + "Baidu native ad  ....... onVideoDownloadSuccess:");
        }
    };

    class BaiduNativeAd extends TTBaseAd {

        /* renamed from: a, reason: collision with root package name */
        private XAdNativeResponse f3944a;

        /* renamed from: b, reason: collision with root package name */
        private FeedNativeView f3945b;

        /* renamed from: c, reason: collision with root package name */
        private boolean f3946c = false;

        /* JADX WARN: Removed duplicated region for block: B:14:0x00c9  */
        /* JADX WARN: Removed duplicated region for block: B:15:0x00cd  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x00da  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x01b5  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        BaiduNativeAd(com.baidu.mobads.sdk.api.XAdNativeResponse r8) {
            /*
                Method dump skipped, instructions count: 487
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bytedance.msdk.adapter.baidu.BaiduNativeAdapter.BaiduNativeAd.<init>(com.bytedance.msdk.adapter.baidu.BaiduNativeAdapter, com.baidu.mobads.sdk.api.XAdNativeResponse):void");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            GMNativeAdListener gMNativeAdListener = this.mTTNativeAdListener;
            if (gMNativeAdListener != null) {
                gMNativeAdListener.onAdShow();
            }
        }

        private boolean a(NativeResponse nativeResponse) {
            return (TextUtils.isEmpty(nativeResponse.getAppVersion()) || TextUtils.isEmpty(nativeResponse.getPublisher()) || TextUtils.isEmpty(nativeResponse.getAppPrivacyLink()) || TextUtils.isEmpty(nativeResponse.getAppPermissionLink())) ? false : true;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return ((TTAbsAdLoaderAdapter) BaiduNativeAdapter.this).mAdSlot.getAdType();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public View getAdView() {
            return this.f3945b;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return this.f3946c;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDislike() {
            return true;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            super.onDestroy();
            this.f3946c = true;
            if (BaiduNativeAdapter.this.f == null || this.f3944a == null) {
                return;
            }
            BaiduNativeAdapter.this.f.remove(this.f3944a);
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onPause() throws JSONException {
            super.onPause();
            XAdNativeResponse xAdNativeResponse = this.f3944a;
            if (xAdNativeResponse == null || xAdNativeResponse.getDownloadStatus() < 0 || this.f3944a.getDownloadStatus() > 100 || !a(this.f3944a)) {
                return;
            }
            this.f3944a.pauseAppDownload();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onResume() throws JSONException {
            super.onResume();
            XAdNativeResponse xAdNativeResponse = this.f3944a;
            if (xAdNativeResponse != null && xAdNativeResponse.getDownloadStatus() == 102 && a(this.f3944a)) {
                this.f3944a.resumeAppDownload();
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void registerViewForInteraction(@NonNull ViewGroup viewGroup, List<View> list, GMViewBinder gMViewBinder) {
            registerViewForInteraction(viewGroup, list, null, gMViewBinder);
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void registerViewForInteraction(@NonNull ViewGroup viewGroup, List<View> list, List<View> list2, GMViewBinder gMViewBinder) {
            super.registerViewForInteraction(viewGroup, list, list2, gMViewBinder);
            if (this.f3944a == null || !(viewGroup instanceof TTNativeAdView)) {
                return;
            }
            TTNativeAdView tTNativeAdView = (TTNativeAdView) viewGroup;
            ViewGroup viewGroup2 = (ViewGroup) tTNativeAdView.findViewById(gMViewBinder.logoLayoutId);
            if (viewGroup2 != null && !BaiduAdapterUtil.isNativeSmartOpt(this.f3944a.getStyleType())) {
                viewGroup2.setVisibility(0);
                LinearLayout linearLayout = new LinearLayout(BaiduNativeAdapter.this.f3940a);
                linearLayout.setGravity(17);
                linearLayout.setOrientation(0);
                linearLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
                ImageView imageView = new ImageView(BaiduNativeAdapter.this.f3940a);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(BaiduAdapterUtil.dp2px(BaiduNativeAdapter.this.f3940a, 15.0f), BaiduAdapterUtil.dp2px(BaiduNativeAdapter.this.f3940a, 15.0f)));
                linearLayout.addView(imageView);
                ImageView imageView2 = new ImageView(BaiduNativeAdapter.this.f3940a);
                imageView2.setLayoutParams(new LinearLayout.LayoutParams(BaiduAdapterUtil.dp2px(BaiduNativeAdapter.this.f3940a, 25.0f), BaiduAdapterUtil.dp2px(BaiduNativeAdapter.this.f3940a, 13.0f)));
                linearLayout.addView(imageView2);
                new DownloadImageTask(imageView).execute(this.f3944a.getBaiduLogoUrl());
                new DownloadImageTask(imageView2).execute(this.f3944a.getAdLogoUrl());
                viewGroup2.removeAllViews();
                ViewGroup.LayoutParams layoutParams = viewGroup2.getLayoutParams();
                layoutParams.width = BaiduAdapterUtil.dp2px(BaiduNativeAdapter.this.f3940a, 38.0f);
                layoutParams.height = BaiduAdapterUtil.dp2px(BaiduNativeAdapter.this.f3940a, 13.0f);
                viewGroup2.setLayoutParams(layoutParams);
                viewGroup2.addView(linearLayout, -1, -1);
            }
            TTMediaView tTMediaView = (TTMediaView) tTNativeAdView.findViewById(gMViewBinder.mediaViewId);
            if (tTMediaView != null && getImageMode() == 5) {
                XNativeView xNativeView = new XNativeView(BaiduNativeAdapter.this.f3940a);
                xNativeView.setNativeItem(this.f3944a);
                xNativeView.setVideoMute(BaiduNativeAdapter.this.f3942c);
                xNativeView.setNativeVideoListener(new INativeVideoListener() { // from class: com.bytedance.msdk.adapter.baidu.BaiduNativeAdapter.BaiduNativeAd.1
                    @Override // com.baidu.mobads.sdk.api.INativeVideoListener
                    public void onCompletion() {
                        if (((TTBaseAd) BaiduNativeAd.this).mTTVideoListener != null) {
                            ((TTBaseAd) BaiduNativeAd.this).mTTVideoListener.onVideoCompleted();
                        }
                    }

                    @Override // com.baidu.mobads.sdk.api.INativeVideoListener
                    public void onError() {
                        if (((TTBaseAd) BaiduNativeAd.this).mTTVideoListener != null) {
                            ((TTBaseAd) BaiduNativeAd.this).mTTVideoListener.onVideoError(new AdError(0, AdError.getMessage(0)));
                        }
                    }

                    @Override // com.baidu.mobads.sdk.api.INativeVideoListener
                    public void onPause() {
                        if (((TTBaseAd) BaiduNativeAd.this).mTTVideoListener != null) {
                            ((TTBaseAd) BaiduNativeAd.this).mTTVideoListener.onVideoPause();
                        }
                    }

                    @Override // com.baidu.mobads.sdk.api.INativeVideoListener
                    public void onRenderingStart() {
                        if (((TTBaseAd) BaiduNativeAd.this).mTTVideoListener != null) {
                            ((TTBaseAd) BaiduNativeAd.this).mTTVideoListener.onVideoStart();
                        }
                    }

                    @Override // com.baidu.mobads.sdk.api.INativeVideoListener
                    public void onResume() {
                        if (((TTBaseAd) BaiduNativeAd.this).mTTVideoListener != null) {
                            ((TTBaseAd) BaiduNativeAd.this).mTTVideoListener.onVideoResume();
                        }
                    }
                });
                tTMediaView.removeAllViews();
                tTMediaView.addView(xNativeView, -1, -1);
                xNativeView.setNativeViewClickListener(new XNativeView.INativeViewClickListener(this) { // from class: com.bytedance.msdk.adapter.baidu.BaiduNativeAdapter.BaiduNativeAd.2
                    @Override // com.baidu.mobads.sdk.api.XNativeView.INativeViewClickListener
                    public void onNativeViewClick(XNativeView xNativeView2) {
                    }
                });
                xNativeView.render();
            }
            this.f3944a.registerViewForInteraction(tTNativeAdView, list, list2, new NativeResponse.AdInteractionListener() { // from class: com.bytedance.msdk.adapter.baidu.BaiduNativeAdapter.BaiduNativeAd.3
                @Override // com.baidu.mobads.sdk.api.NativeResponse.AdInteractionListener
                public void onADExposed() {
                    BaiduNativeAd.this.a();
                    Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduNativeAdapter.this.getAdapterRit(), BaiduNativeAdapter.this.getAdSlotId()) + "Baidu native ad  ....... onAdExposed");
                }

                @Override // com.baidu.mobads.sdk.api.NativeResponse.AdInteractionListener
                public void onADExposureFailed(int i) {
                    Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduNativeAdapter.this.getAdapterRit(), BaiduNativeAdapter.this.getAdSlotId()) + "Baidu native ad  ....... onAdExposeFailed: " + i);
                }

                @Override // com.baidu.mobads.sdk.api.NativeResponse.AdInteractionListener
                public void onADStatusChanged() {
                    Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduNativeAdapter.this.getAdapterRit(), BaiduNativeAdapter.this.getAdSlotId()) + "Baidu native ad  ....... onAdStatusChanged");
                }

                @Override // com.baidu.mobads.sdk.api.NativeResponse.AdInteractionListener
                public void onAdClick() {
                    Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduNativeAdapter.this.getAdapterRit(), BaiduNativeAdapter.this.getAdSlotId()) + "Baidu native ad  ....... onAdClick");
                    if (((TTBaseAd) BaiduNativeAd.this).mTTNativeAdListener != null) {
                        ((TTBaseAd) BaiduNativeAd.this).mTTNativeAdListener.onAdClick();
                    }
                }

                @Override // com.baidu.mobads.sdk.api.NativeResponse.AdInteractionListener
                public void onAdUnionClick() {
                    Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduNativeAdapter.this.getAdapterRit(), BaiduNativeAdapter.this.getAdSlotId()) + "Baidu native ad  ....... onAdUnionClick");
                }
            });
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void render() {
            super.render();
            XAdNativeResponse xAdNativeResponse = this.f3944a;
            if (xAdNativeResponse == null || !BaiduAdapterUtil.isNativeSmartOpt(xAdNativeResponse.getStyleType())) {
                GMNativeAdListener gMNativeAdListener = this.mTTNativeAdListener;
                if (gMNativeAdListener instanceof GMNativeExpressAdListener) {
                    ((GMNativeExpressAdListener) gMNativeAdListener).onRenderFail(this.f3945b, AdError.getMessage(104), 104);
                    return;
                }
                return;
            }
            this.f3944a.registerViewForInteraction(this.f3945b, new NativeResponse.AdInteractionListener() { // from class: com.bytedance.msdk.adapter.baidu.BaiduNativeAdapter.BaiduNativeAd.4
                @Override // com.baidu.mobads.sdk.api.NativeResponse.AdInteractionListener
                public void onADExposed() {
                    BaiduNativeAd.this.a();
                    Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduNativeAdapter.this.getAdapterRit(), BaiduNativeAdapter.this.getAdSlotId()) + "Baidu native ad express ....... onAdExposed");
                }

                @Override // com.baidu.mobads.sdk.api.NativeResponse.AdInteractionListener
                public void onADExposureFailed(int i) {
                    Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduNativeAdapter.this.getAdapterRit(), BaiduNativeAdapter.this.getAdSlotId()) + "Baidu native ad express ....... onAdExposeFailed: " + i);
                }

                @Override // com.baidu.mobads.sdk.api.NativeResponse.AdInteractionListener
                public void onADStatusChanged() {
                    Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduNativeAdapter.this.getAdapterRit(), BaiduNativeAdapter.this.getAdSlotId()) + "Baidu native ad express ....... onAdStatusChanged");
                }

                @Override // com.baidu.mobads.sdk.api.NativeResponse.AdInteractionListener
                public void onAdClick() {
                    if (((TTBaseAd) BaiduNativeAd.this).mTTNativeAdListener != null) {
                        ((TTBaseAd) BaiduNativeAd.this).mTTNativeAdListener.onAdClick();
                    }
                    Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduNativeAdapter.this.getAdapterRit(), BaiduNativeAdapter.this.getAdSlotId()) + "Baidu native express ad ....... onAdClick");
                }

                @Override // com.baidu.mobads.sdk.api.NativeResponse.AdInteractionListener
                public void onAdUnionClick() {
                    Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduNativeAdapter.this.getAdapterRit(), BaiduNativeAdapter.this.getAdSlotId()) + "Baidu native ad express ....... onAdUnionClick");
                }
            });
            GMNativeAdListener gMNativeAdListener2 = this.mTTNativeAdListener;
            if (gMNativeAdListener2 instanceof GMNativeExpressAdListener) {
                ((GMNativeExpressAdListener) gMNativeAdListener2).onRenderSuccess(-1.0f, -2.0f);
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void setDislikeCallback(Activity activity, final GMDislikeCallback gMDislikeCallback) {
            this.f3944a.setAdDislikeListener(new NativeResponse.AdDislikeListener() { // from class: com.bytedance.msdk.adapter.baidu.BaiduNativeAdapter.BaiduNativeAd.5
                @Override // com.baidu.mobads.sdk.api.NativeResponse.AdDislikeListener
                public void onDislikeClick() {
                    Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduNativeAdapter.this.getAdapterRit(), BaiduNativeAdapter.this.getAdSlotId()) + "Baidu native ad express ....... onDislikeClick");
                    GMDislikeCallback gMDislikeCallback2 = gMDislikeCallback;
                    if (gMDislikeCallback2 != null) {
                        gMDislikeCallback2.onSelected(0, "百度智能优选dislike接口无关闭原因");
                    }
                }
            });
        }
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void destroy() {
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getAdNetWorkName() {
        return "baidu";
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getSdkVersion() {
        return AdSettings.getSDKVersion();
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void loadAd(Context context, Map<String, Object> map) {
        this.f3940a = context;
        BaiduNativeManager baiduNativeManager = new BaiduNativeManager(this.f3940a, getAdSlotId());
        this.f3941b = this.mAdSlot.getTTVideoOption();
        TTVideoOption tTVideoOption = this.f3941b;
        if (tTVideoOption != null) {
            this.f3942c = tTVideoOption.isMuted();
            this.d = this.f3941b.getBaiduExtraOption();
            BaiduExtraOptions baiduExtraOptions = this.d;
            if (baiduExtraOptions != null) {
                this.e = baiduExtraOptions.getBaiduNativeSmartOptStyleParams();
                String appSid = this.d.getAppSid();
                if (!TextUtils.isEmpty(appSid)) {
                    baiduNativeManager.setAppSid(appSid);
                }
            }
        }
        int imgAcceptedWidth = this.mAdSlot.getImgAcceptedWidth() > 0 ? this.mAdSlot.getImgAcceptedWidth() : 0;
        int imgAcceptedHeight = this.mAdSlot.getImgAcceptedHeight() > 0 ? this.mAdSlot.getImgAcceptedHeight() : 0;
        RequestParameters.Builder builder = new RequestParameters.Builder();
        BaiduExtraOptions baiduExtraOptions2 = this.d;
        if (baiduExtraOptions2 != null) {
            BaiduRequestParameters baiduRequestParameters = baiduExtraOptions2.getBaiduRequestParameters();
            if (baiduRequestParameters != null) {
                builder.downloadAppConfirmPolicy(baiduRequestParameters.getAPPConfirmPolicy() != 0 ? baiduRequestParameters.getAPPConfirmPolicy() : 3);
                Map<String, String> extras = baiduRequestParameters.getExtras();
                if (extras != null) {
                    try {
                        for (Map.Entry<String, String> entry : extras.entrySet()) {
                            if (entry != null) {
                                builder.addExtra(entry.getKey(), entry.getValue());
                            }
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            }
        } else {
            builder.downloadAppConfirmPolicy(1);
        }
        if (imgAcceptedWidth > 0 && imgAcceptedHeight > 0) {
            builder.setWidth(imgAcceptedWidth).setHeight(imgAcceptedHeight);
        }
        BaiduExtraOptions baiduExtraOptions3 = this.d;
        baiduNativeManager.setCacheVideoOnlyWifi(baiduExtraOptions3 == null || baiduExtraOptions3.isCacheVideoOnlyWifi());
        BaiduExtraOptions baiduExtraOptions4 = this.d;
        if (baiduExtraOptions4 == null || baiduExtraOptions4.isCacheVideoOnlyWifi()) {
            baiduNativeManager.setCacheVideoOnlyWifi(true);
        }
        baiduNativeManager.loadFeedAd(builder.build(), this.g);
    }
}
