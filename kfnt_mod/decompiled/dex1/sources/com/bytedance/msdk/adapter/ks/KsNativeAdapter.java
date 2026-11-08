package com.bytedance.msdk.adapter.ks;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.util.DownloadImageTask;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdErrorUtil;
import com.bytedance.msdk.api.TTVideoOption;
import com.bytedance.msdk.api.format.TTMediaView;
import com.bytedance.msdk.api.format.TTNativeAdView;
import com.bytedance.msdk.api.v2.GMDislikeCallback;
import com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAdListener;
import com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeExpressAdListener;
import com.bytedance.msdk.api.v2.ad.nativeAd.GMViewBinder;
import com.bytedance.msdk.base.TTBaseAd;
import com.kwad.sdk.api.KsAdSDK;
import com.kwad.sdk.api.KsAdVideoPlayConfig;
import com.kwad.sdk.api.KsFeedAd;
import com.kwad.sdk.api.KsImage;
import com.kwad.sdk.api.KsLoadManager;
import com.kwad.sdk.api.KsNativeAd;
import com.kwad.sdk.api.KsScene;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class KsNativeAdapter extends TTAbsAdLoaderAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f4038a;

    /* renamed from: b, reason: collision with root package name */
    private TTVideoOption f4039b;

    class TTKsExpressAd extends TTBaseAd {

        /* renamed from: a, reason: collision with root package name */
        KsFeedAd f4042a;

        /* renamed from: b, reason: collision with root package name */
        GMDislikeCallback f4043b;

        public TTKsExpressAd(KsFeedAd ksFeedAd) {
            this.f4042a = ksFeedAd;
            if (KsNativeAdapter.this.isClientBidding()) {
                int ecpm = ksFeedAd.getECPM();
                Logger.d("TTMediationSDK_ECMP", TTLogUtil.getTagThirdLevelById(KsNativeAdapter.this.getAdapterRit(), KsNativeAdapter.this.getAdSlotId()) + "KS_clientBidding native模板广告返回的 cpm价格：" + ecpm);
                setCpm(ecpm > 0 ? ecpm : 0.0d);
            }
            setExpressAd(true);
            this.f4042a.setAdInteractionListener(new KsFeedAd.AdInteractionListener(KsNativeAdapter.this) { // from class: com.bytedance.msdk.adapter.ks.KsNativeAdapter.TTKsExpressAd.1
                public void onAdClicked() {
                    if (((TTBaseAd) TTKsExpressAd.this).mTTNativeAdListener != null) {
                        ((TTBaseAd) TTKsExpressAd.this).mTTNativeAdListener.onAdClick();
                    }
                }

                public void onAdShow() {
                    if (((TTBaseAd) TTKsExpressAd.this).mTTNativeAdListener != null) {
                        ((TTBaseAd) TTKsExpressAd.this).mTTNativeAdListener.onAdShow();
                    }
                }

                public void onDislikeClicked() {
                    GMDislikeCallback gMDislikeCallback = TTKsExpressAd.this.f4043b;
                    if (gMDislikeCallback != null) {
                        gMDislikeCallback.onSelected(-1, "ks type ,no value");
                    }
                }

                public void onDownloadTipsDialogDismiss() {
                }

                public void onDownloadTipsDialogShow() {
                }
            });
            if (KsNativeAdapter.this.f4039b == null || KsNativeAdapter.this.f4039b.isMuted()) {
                return;
            }
            ksFeedAd.setVideoSoundEnable(true);
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return 5;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public View getAdView() {
            KsFeedAd ksFeedAd = this.f4042a;
            if (ksFeedAd != null) {
                return ksFeedAd.getFeedView(KsNativeAdapter.this.f4038a);
            }
            return null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return false;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDislike() {
            return true;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void render() {
            GMNativeAdListener gMNativeAdListener;
            super.render();
            KsFeedAd ksFeedAd = this.f4042a;
            if (ksFeedAd == null) {
                gMNativeAdListener = this.mTTNativeAdListener;
                if (!(gMNativeAdListener instanceof GMNativeExpressAdListener)) {
                    return;
                }
            } else {
                if (ksFeedAd.getFeedView(KsNativeAdapter.this.f4038a) != null) {
                    GMNativeAdListener gMNativeAdListener2 = this.mTTNativeAdListener;
                    if (gMNativeAdListener2 instanceof GMNativeExpressAdListener) {
                        ((GMNativeExpressAdListener) gMNativeAdListener2).onRenderSuccess(-1.0f, -2.0f);
                        return;
                    }
                    return;
                }
                gMNativeAdListener = this.mTTNativeAdListener;
                if (!(gMNativeAdListener instanceof GMNativeExpressAdListener)) {
                    return;
                }
            }
            ((GMNativeExpressAdListener) gMNativeAdListener).onRenderFail(null, AdError.getMessage(106), 106);
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void setDislikeCallback(Activity activity, GMDislikeCallback gMDislikeCallback) {
            this.f4043b = gMDislikeCallback;
        }
    }

    class TTKsNativeAd extends TTBaseAd {

        /* renamed from: a, reason: collision with root package name */
        KsNativeAd f4046a;

        /* renamed from: b, reason: collision with root package name */
        KsNativeAd.AdInteractionListener f4047b = new KsNativeAd.AdInteractionListener() { // from class: com.bytedance.msdk.adapter.ks.KsNativeAdapter.TTKsNativeAd.1
            public boolean handleDownloadDialog(DialogInterface.OnClickListener onClickListener) {
                return false;
            }

            public void onAdClicked(View view, KsNativeAd ksNativeAd) {
                if (((TTBaseAd) TTKsNativeAd.this).mTTNativeAdListener != null) {
                    ((TTBaseAd) TTKsNativeAd.this).mTTNativeAdListener.onAdClick();
                }
            }

            public void onAdShow(KsNativeAd ksNativeAd) {
                if (((TTBaseAd) TTKsNativeAd.this).mTTNativeAdListener != null) {
                    ((TTBaseAd) TTKsNativeAd.this).mTTNativeAdListener.onAdShow();
                }
            }

            public void onDownloadTipsDialogDismiss() {
            }

            public void onDownloadTipsDialogShow() {
            }
        };

        /* renamed from: c, reason: collision with root package name */
        KsNativeAd.VideoPlayListener f4048c = new KsNativeAd.VideoPlayListener() { // from class: com.bytedance.msdk.adapter.ks.KsNativeAdapter.TTKsNativeAd.2
            public void onVideoPlayComplete() {
                if (((TTBaseAd) TTKsNativeAd.this).mTTVideoListener != null) {
                    ((TTBaseAd) TTKsNativeAd.this).mTTVideoListener.onVideoCompleted();
                }
            }

            public void onVideoPlayError(int i, int i2) {
                if (((TTBaseAd) TTKsNativeAd.this).mTTVideoListener != null) {
                    ((TTBaseAd) TTKsNativeAd.this).mTTVideoListener.onVideoError(new AdError(i, "Android MediaPlay Error Code :" + i2));
                }
            }

            public void onVideoPlayStart() {
                if (((TTBaseAd) TTKsNativeAd.this).mTTVideoListener != null) {
                    ((TTBaseAd) TTKsNativeAd.this).mTTVideoListener.onVideoStart();
                }
            }
        };

        public TTKsNativeAd(KsNativeAd ksNativeAd) {
            this.f4046a = ksNativeAd;
            setActionText(ksNativeAd.getActionDescription());
            setAdDescription(ksNativeAd.getAdDescription());
            setIconUrl(ksNativeAd.getAppIconUrl());
            setAdType(5);
            setSource(!TextUtils.isEmpty(ksNativeAd.getAppName()) ? ksNativeAd.getAppName() : !TextUtils.isEmpty(ksNativeAd.getProductName()) ? ksNativeAd.getProductName() : "");
            setTitle(ksNativeAd.getAdSource());
            setIsAppDownload(ksNativeAd.getInteractionType() == 1);
            setExpressAd(false);
            setStore(String.valueOf(ksNativeAd.getAppScore()));
            if (ksNativeAd.getInteractionType() == 1) {
                setInteractionType(4);
            } else if (ksNativeAd.getInteractionType() == 2) {
                setInteractionType(3);
            } else {
                setInteractionType(-1);
            }
            if (KsNativeAdapter.this.isClientBidding()) {
                double ecpm = ksNativeAd.getECPM();
                Logger.d("TTMediationSDK_ECMP", TTLogUtil.getTagThirdLevelById(KsNativeAdapter.this.getAdapterRit(), KsNativeAdapter.this.getAdSlotId()) + "KS_clientBidding native自渲染 返回的 cpm价格：" + ecpm);
                setCpm(ecpm <= 0.0d ? 0.0d : ecpm);
            }
            if (ksNativeAd.getMaterialType() == 2) {
                if (ksNativeAd.getImageList() != null && !ksNativeAd.getImageList().isEmpty() && ksNativeAd.getImageList().get(0) != null) {
                    setImageMode(3);
                    KsImage ksImage = (KsImage) ksNativeAd.getImageList().get(0);
                    setImageUrl(ksImage.getImageUrl());
                    setImageHeight(ksImage.getHeight());
                    setImageWidth(ksImage.getWidth());
                }
            } else if (ksNativeAd.getMaterialType() == 3) {
                if (ksNativeAd.getImageList() != null && ksNativeAd.getImageList().size() > 0) {
                    setImageMode(4);
                    ArrayList arrayList = new ArrayList();
                    Iterator it = ksNativeAd.getImageList().iterator();
                    while (it.hasNext()) {
                        arrayList.add(((KsImage) it.next()).getImageUrl());
                    }
                    setImages(arrayList);
                }
            } else if (ksNativeAd.getMaterialType() == 1) {
                setImageMode(5);
            } else {
                setImageMode(-1);
            }
            this.f4046a.setVideoPlayListener(this.f4048c);
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return 5;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getVideoHeight() {
            KsNativeAd ksNativeAd = this.f4046a;
            if (ksNativeAd != null) {
                return ksNativeAd.getVideoHeight();
            }
            return 0;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getVideoWidth() {
            KsNativeAd ksNativeAd = this.f4046a;
            if (ksNativeAd != null) {
                return ksNativeAd.getVideoWidth();
            }
            return 0;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return this.f4046a == null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            KsNativeAd ksNativeAd = this.f4046a;
            if (ksNativeAd != null) {
                ksNativeAd.setVideoPlayListener((KsNativeAd.VideoPlayListener) null);
                this.f4046a = null;
            }
            super.onDestroy();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void registerViewForInteraction(@NonNull ViewGroup viewGroup, List<View> list, GMViewBinder gMViewBinder) {
            registerViewForInteraction(viewGroup, list, null, gMViewBinder);
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void registerViewForInteraction(@NonNull ViewGroup viewGroup, List<View> list, List<View> list2, GMViewBinder gMViewBinder) {
            View videoView;
            View viewFindViewById;
            super.registerViewForInteraction(viewGroup, list, list2, gMViewBinder);
            if (list != null && list2 != null) {
                list.addAll(list2);
            }
            if (viewGroup instanceof TTNativeAdView) {
                KsNativeAd ksNativeAd = this.f4046a;
                if (ksNativeAd != null) {
                    ksNativeAd.registerViewForInteraction(viewGroup, list, this.f4047b);
                }
                KsNativeAd ksNativeAd2 = this.f4046a;
                if (ksNativeAd2 != null && ksNativeAd2.getAdSourceLogoUrl(1) != null && (viewFindViewById = viewGroup.findViewById(gMViewBinder.logoLayoutId)) != null) {
                    viewFindViewById.setVisibility(0);
                    if (viewFindViewById instanceof ViewGroup) {
                        ViewGroup viewGroup2 = (ViewGroup) viewFindViewById;
                        viewGroup2.removeAllViews();
                        ImageView imageView = new ImageView(KsNativeAdapter.this.f4038a);
                        new DownloadImageTask(imageView).execute(this.f4046a.getAdSourceLogoUrl(1));
                        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                        viewGroup2.addView(imageView, -1, -1);
                    } else if (viewFindViewById instanceof ImageView) {
                        ImageView imageView2 = (ImageView) viewFindViewById;
                        new DownloadImageTask(imageView2).execute(this.f4046a.getAdSourceLogoUrl(1));
                        imageView2.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    }
                }
                TTMediaView tTMediaView = (TTMediaView) viewGroup.findViewById(gMViewBinder.mediaViewId);
                KsNativeAd ksNativeAd3 = this.f4046a;
                if (ksNativeAd3 == null || tTMediaView == null || (videoView = ksNativeAd3.getVideoView(KsNativeAdapter.this.f4038a, (KsAdVideoPlayConfig) null)) == null) {
                    return;
                }
                removeSelfFromParent(videoView);
                tTMediaView.removeAllViews();
                tTMediaView.addView(videoView, -1, -1);
            }
        }
    }

    private void a(KsScene ksScene) {
        KsAdSDK.getLoadManager().loadConfigFeedAd(ksScene, new KsLoadManager.FeedAdListener() { // from class: com.bytedance.msdk.adapter.ks.KsNativeAdapter.1
            public void onError(int i, String str) {
                Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelById(KsNativeAdapter.this.getAdapterRit(), KsNativeAdapter.this.getAdSlotId()) + "加载ks feed模板广告失败：code:" + i + "   msg:" + str);
                KsNativeAdapter.this.notifyAdFailed(new AdError(AdError.ERROR_CODE_AD_LOAD_FAIL, AdError.AD_LOAD_FAIL_MSG, i, str));
            }

            public void onFeedAdLoad(@Nullable List<KsFeedAd> list) {
                StringBuilder sb;
                if (list == null) {
                    sb = new StringBuilder();
                    sb.append(TTLogUtil.getTagThirdLevelById(KsNativeAdapter.this.getAdapterRit(), KsNativeAdapter.this.getAdSlotId()));
                    sb.append("加载ks feed模板广告广告成功，但没有返回数据");
                } else {
                    sb = new StringBuilder();
                    sb.append(TTLogUtil.getTagThirdLevelById(KsNativeAdapter.this.getAdapterRit(), KsNativeAdapter.this.getAdSlotId()));
                    sb.append("加载ks feed模板广告广告成功，数量:");
                    sb.append(list.size());
                }
                Logger.e("TTMediationSDK", sb.toString());
                if (list == null || list.isEmpty()) {
                    KsNativeAdapter.this.notifyAdFailed(new AdError(AdError.ERROR_CODE_NO_AD, AdError.getMessage(AdError.ERROR_CODE_NO_AD), AdError.ERROR_CODE_NO_AD, AdError.getMessage(AdError.ERROR_CODE_NO_AD)));
                    return;
                }
                ArrayList arrayList = new ArrayList();
                Iterator<KsFeedAd> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(KsNativeAdapter.this.new TTKsExpressAd(it.next()));
                }
                KsNativeAdapter.this.notifyAdLoaded(arrayList);
            }
        });
    }

    private void b(KsScene ksScene) {
        KsAdSDK.getLoadManager().loadNativeAd(ksScene, new KsLoadManager.NativeAdListener() { // from class: com.bytedance.msdk.adapter.ks.KsNativeAdapter.2
            public void onError(int i, String str) {
                Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelById(KsNativeAdapter.this.getAdapterRit(), KsNativeAdapter.this.getAdSlotId()) + "加载ks feed自渲染广告失败：code:" + i + "   msg:" + str);
                KsNativeAdapter.this.notifyAdFailed(new AdError(AdError.ERROR_CODE_AD_LOAD_FAIL, AdError.AD_LOAD_FAIL_MSG, i, str));
            }

            public void onNativeAdLoad(@Nullable List<KsNativeAd> list) {
                StringBuilder sb;
                if (list == null) {
                    sb = new StringBuilder();
                    sb.append(TTLogUtil.getTagThirdLevelById(KsNativeAdapter.this.getAdapterRit(), KsNativeAdapter.this.getAdSlotId()));
                    sb.append("加载ks feed自渲染广告广告成功，但没有返回数据");
                } else {
                    sb = new StringBuilder();
                    sb.append(TTLogUtil.getTagThirdLevelById(KsNativeAdapter.this.getAdapterRit(), KsNativeAdapter.this.getAdSlotId()));
                    sb.append("加载ks feed自渲染广告广告成功，数量:");
                    sb.append(list.size());
                }
                Logger.e("TTMediationSDK", sb.toString());
                if (list == null || list.isEmpty()) {
                    KsNativeAdapter.this.notifyAdFailed(new AdError(AdError.ERROR_CODE_NO_AD, AdError.getMessage(AdError.ERROR_CODE_NO_AD), AdError.ERROR_CODE_NO_AD, AdError.getMessage(AdError.ERROR_CODE_NO_AD)));
                    return;
                }
                ArrayList arrayList = new ArrayList();
                Iterator<KsNativeAd> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(KsNativeAdapter.this.new TTKsNativeAd(it.next()));
                }
                KsNativeAdapter.this.notifyAdLoaded(arrayList);
            }
        });
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void destroy() {
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getAdNetWorkName() {
        return "ks";
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getSdkVersion() {
        return KsAdSDK.getSDKVersion();
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void loadAd(Context context, Map<String, Object> map) {
        AdError adError;
        KsScene ksSceneBuild;
        Object obj;
        this.f4038a = context;
        try {
            ksSceneBuild = new KsScene.Builder(Long.valueOf(getAdSlotId()).longValue()).adNum(getAdLoadCount()).build();
            this.f4039b = this.mAdSlot.getTTVideoOption();
            obj = map.get("tt_ad_origin_type");
        } catch (Exception unused) {
            adError = new AdError(AdError.ERROR_CODE_ADSLOT_ERROR, AdError.AD_SLOTID_ERROR_MSG, AdError.ERROR_CODE_ADSLOT_ERROR, AdError.AD_SLOTID_ERROR_MSG);
        }
        if (obj != null) {
            int iIntValue = ((Integer) obj).intValue();
            if (iIntValue == 1) {
                a(ksSceneBuild);
                return;
            } else if (iIntValue == 2) {
                b(ksSceneBuild);
                return;
            } else if (iIntValue == 3) {
                adError = AdErrorUtil.obtainAdError(AdError.ERROR_CODE_RENDER_TYPE_MISMATCH, AdError.getMessage(AdError.ERROR_CODE_RENDER_TYPE_MISMATCH));
                notifyAdFailed(adError);
                return;
            }
        }
        if (this.mAdSlot.getAdStyleType() == 1) {
            a(ksSceneBuild);
        } else if (this.mAdSlot.getAdStyleType() == 2) {
            b(ksSceneBuild);
        }
    }
}
