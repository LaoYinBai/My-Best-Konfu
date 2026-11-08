package com.bytedance.msdk.adapter.pangle;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.UIUtils;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.format.TTMediaView;
import com.bytedance.msdk.api.format.TTNativeAdView;
import com.bytedance.msdk.api.v2.GMAdDislike;
import com.bytedance.msdk.api.v2.GMDislikeCallback;
import com.bytedance.msdk.api.v2.ad.nativeAd.GMViewBinder;
import com.bytedance.msdk.base.TTBaseAd;
import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.ComplianceInfo;
import com.bytedance.sdk.openadsdk.TTAdDislike;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTFeedAd;
import com.bytedance.sdk.openadsdk.TTImage;
import com.bytedance.sdk.openadsdk.TTNativeAd;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class PangleNativeLoader {

    /* renamed from: a, reason: collision with root package name */
    private boolean f4160a;

    /* renamed from: b, reason: collision with root package name */
    private Context f4161b;

    class PangleNativeAd extends TTBaseAd {

        /* renamed from: a, reason: collision with root package name */
        TTNativeAd.AdInteractionListener f4164a = new TTNativeAd.AdInteractionListener() { // from class: com.bytedance.msdk.adapter.pangle.PangleNativeLoader.PangleNativeAd.2
            @Override // com.bytedance.sdk.openadsdk.TTNativeAd.AdInteractionListener
            public void onAdClicked(View view, TTNativeAd tTNativeAd) {
                if (((TTBaseAd) PangleNativeAd.this).mTTNativeAdListener != null) {
                    ((TTBaseAd) PangleNativeAd.this).mTTNativeAdListener.onAdClick();
                }
            }

            @Override // com.bytedance.sdk.openadsdk.TTNativeAd.AdInteractionListener
            public void onAdCreativeClick(View view, TTNativeAd tTNativeAd) {
                if (((TTBaseAd) PangleNativeAd.this).mTTNativeAdListener != null) {
                    ((TTBaseAd) PangleNativeAd.this).mTTNativeAdListener.onAdClick();
                }
            }

            @Override // com.bytedance.sdk.openadsdk.TTNativeAd.AdInteractionListener
            public void onAdShow(TTNativeAd tTNativeAd) {
                if (((TTBaseAd) PangleNativeAd.this).mTTNativeAdListener != null) {
                    ((TTBaseAd) PangleNativeAd.this).mTTNativeAdListener.onAdShow();
                }
            }
        };

        /* renamed from: b, reason: collision with root package name */
        TTFeedAd.VideoAdListener f4165b = new TTFeedAd.VideoAdListener() { // from class: com.bytedance.msdk.adapter.pangle.PangleNativeLoader.PangleNativeAd.3
            @Override // com.bytedance.sdk.openadsdk.TTFeedAd.VideoAdListener
            public void onProgressUpdate(long j, long j2) {
            }

            @Override // com.bytedance.sdk.openadsdk.TTFeedAd.VideoAdListener
            public void onVideoAdComplete(TTFeedAd tTFeedAd) {
                if (((TTBaseAd) PangleNativeAd.this).mTTVideoListener != null) {
                    ((TTBaseAd) PangleNativeAd.this).mTTVideoListener.onVideoCompleted();
                }
            }

            @Override // com.bytedance.sdk.openadsdk.TTFeedAd.VideoAdListener
            public void onVideoAdContinuePlay(TTFeedAd tTFeedAd) {
                if (((TTBaseAd) PangleNativeAd.this).mTTVideoListener != null) {
                    ((TTBaseAd) PangleNativeAd.this).mTTVideoListener.onVideoResume();
                }
            }

            @Override // com.bytedance.sdk.openadsdk.TTFeedAd.VideoAdListener
            public void onVideoAdPaused(TTFeedAd tTFeedAd) {
                if (((TTBaseAd) PangleNativeAd.this).mTTVideoListener != null) {
                    ((TTBaseAd) PangleNativeAd.this).mTTVideoListener.onVideoPause();
                }
            }

            @Override // com.bytedance.sdk.openadsdk.TTFeedAd.VideoAdListener
            public void onVideoAdStartPlay(TTFeedAd tTFeedAd) {
                if (((TTBaseAd) PangleNativeAd.this).mTTVideoListener != null) {
                    ((TTBaseAd) PangleNativeAd.this).mTTVideoListener.onVideoStart();
                }
            }

            @Override // com.bytedance.sdk.openadsdk.TTFeedAd.VideoAdListener
            public void onVideoError(int i, int i2) {
                if (((TTBaseAd) PangleNativeAd.this).mTTVideoListener != null) {
                    ((TTBaseAd) PangleNativeAd.this).mTTVideoListener.onVideoError(new AdError(i, "Android MediaPlay Error Code :" + i2));
                }
            }

            @Override // com.bytedance.sdk.openadsdk.TTFeedAd.VideoAdListener
            public void onVideoLoad(TTFeedAd tTFeedAd) {
            }
        };
        private TTFeedAd d;

        PangleNativeAd(TTFeedAd tTFeedAd) {
            Map<String, Object> mediaExtraInfo;
            this.d = tTFeedAd;
            ComplianceInfo complianceInfo = tTFeedAd.getComplianceInfo();
            if (complianceInfo != null) {
                setAppName(complianceInfo.getAppName());
                setAuthorName(complianceInfo.getDeveloperName());
                setPrivacyAgreement(complianceInfo.getPrivacyUrl());
                setVersionName(complianceInfo.getAppVersion());
                HashMap map = new HashMap();
                Map<String, String> permissionsMap = complianceInfo.getPermissionsMap();
                if (permissionsMap != null && permissionsMap.size() > 0) {
                    map.putAll(permissionsMap);
                }
                setPermissionsMap(map);
            }
            setTitle(tTFeedAd.getTitle());
            setAdDescription(tTFeedAd.getDescription());
            setActionText(tTFeedAd.getButtonText());
            setIconUrl(tTFeedAd.getIcon() != null ? tTFeedAd.getIcon().getImageUrl() : null);
            setImageMode(tTFeedAd.getImageMode());
            setInteractionType(tTFeedAd.getInteractionType());
            setSource(tTFeedAd.getSource());
            setRating(tTFeedAd.getAppScore());
            setAdType(5);
            setIsAppDownload(tTFeedAd.getInteractionType() == 4);
            setExpressAd(false);
            this.d.getMediaExtraInfo();
            if (tTFeedAd.getImageMode() == 16 || tTFeedAd.getImageMode() == 3 || tTFeedAd.getImageMode() == 2) {
                if (tTFeedAd.getImageList() != null && !tTFeedAd.getImageList().isEmpty() && tTFeedAd.getImageList().get(0) != null) {
                    TTImage tTImage = tTFeedAd.getImageList().get(0);
                    setImageUrl(tTImage.getImageUrl());
                    setImageHeight(tTImage.getHeight());
                    setImageWidth(tTImage.getWidth());
                }
            } else if (tTFeedAd.getImageMode() == 4 && tTFeedAd.getImageList() != null && tTFeedAd.getImageList().size() > 0) {
                ArrayList arrayList = new ArrayList();
                Iterator<TTImage> it = tTFeedAd.getImageList().iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().getImageUrl());
                }
                setImages(arrayList);
            }
            if (PangleNativeLoader.this.f4160a && (mediaExtraInfo = tTFeedAd.getMediaExtraInfo()) != null) {
                double value = PangleAdapterUtils.getValue(mediaExtraInfo.get("price"));
                Logger.d("TTMediationSDK_ECMP", "pangle native 返回的 cpm价格：" + value);
                setCpm(value <= 0.0d ? 0.0d : value);
            }
            this.d.setVideoAdListener(this.f4165b);
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public long getAdId() {
            TTFeedAd tTFeedAd = this.d;
            return tTFeedAd != null ? PangleAdapterUtils.getAdId(tTFeedAd.getMediaExtraInfo()) : super.getAdId();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return 5;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public long getCreativeId() {
            TTFeedAd tTFeedAd = this.d;
            return tTFeedAd != null ? PangleAdapterUtils.getCreativeId(tTFeedAd.getMediaExtraInfo()) : super.getCreativeId();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public GMAdDislike getDislikeDialog(Activity activity) {
            TTFeedAd tTFeedAd = this.d;
            if (tTFeedAd == null) {
                return super.getDislikeDialog(activity);
            }
            final TTAdDislike dislikeDialog = tTFeedAd.getDislikeDialog(activity);
            return new GMAdDislike(this) { // from class: com.bytedance.msdk.adapter.pangle.PangleNativeLoader.PangleNativeAd.1
                @Override // com.bytedance.msdk.api.v2.GMAdDislike
                public void setDislikeCallback(final GMDislikeCallback gMDislikeCallback) {
                    if (gMDislikeCallback != null) {
                        dislikeDialog.setDislikeInteractionCallback(new TTAdDislike.DislikeInteractionCallback(this) { // from class: com.bytedance.msdk.adapter.pangle.PangleNativeLoader.PangleNativeAd.1.1
                            @Override // com.bytedance.sdk.openadsdk.TTAdDislike.DislikeInteractionCallback
                            public void onCancel() {
                                gMDislikeCallback.onCancel();
                            }

                            @Override // com.bytedance.sdk.openadsdk.TTAdDislike.DislikeInteractionCallback
                            public void onSelected(int i, String str, boolean z) {
                                gMDislikeCallback.onSelected(i, str);
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

                @Override // com.bytedance.msdk.api.v2.GMAdDislike
                public void showDislikeDialog() {
                    TTAdDislike tTAdDislike = dislikeDialog;
                    if (tTAdDislike != null) {
                        tTAdDislike.showDislikeDialog();
                    }
                }
            };
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public String getReqId() {
            TTFeedAd tTFeedAd = this.d;
            return tTFeedAd != null ? PangleAdapterUtils.getReqId(tTFeedAd.getMediaExtraInfo()) : super.getReqId();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getVideoHeight() {
            TTFeedAd tTFeedAd = this.d;
            if (tTFeedAd != null) {
                return tTFeedAd.getAdViewHeight();
            }
            return 0;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getVideoWidth() {
            TTFeedAd tTFeedAd = this.d;
            if (tTFeedAd != null) {
                return tTFeedAd.getAdViewWidth();
            }
            return 0;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return this.d == null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDislike() {
            return true;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            TTFeedAd tTFeedAd = this.d;
            if (tTFeedAd != null) {
                tTFeedAd.setVideoAdListener(null);
                this.d = null;
            }
            super.onDestroy();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void registerViewForInteraction(@NonNull ViewGroup viewGroup, List<View> list, GMViewBinder gMViewBinder) {
            registerViewForInteraction(viewGroup, list, null, gMViewBinder);
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void registerViewForInteraction(@NonNull ViewGroup viewGroup, List<View> list, List<View> list2, GMViewBinder gMViewBinder) {
            TTFeedAd tTFeedAd;
            View adView;
            View viewFindViewById;
            super.registerViewForInteraction(viewGroup, list, list2, gMViewBinder);
            if (viewGroup instanceof TTNativeAdView) {
                TTFeedAd tTFeedAd2 = this.d;
                if (tTFeedAd2 != null) {
                    tTFeedAd2.registerViewForInteraction(viewGroup, list, list2, this.f4164a);
                }
                TTFeedAd tTFeedAd3 = this.d;
                if (tTFeedAd3 != null && tTFeedAd3.getAdLogo() != null && (viewFindViewById = viewGroup.findViewById(gMViewBinder.logoLayoutId)) != null) {
                    viewFindViewById.setVisibility(0);
                    if (viewFindViewById instanceof ViewGroup) {
                        ViewGroup viewGroup2 = (ViewGroup) viewFindViewById;
                        viewGroup2.removeAllViews();
                        ImageView imageView = new ImageView(PangleNativeLoader.this.f4161b);
                        imageView.setImageBitmap(this.d.getAdLogo());
                        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                        ViewGroup.LayoutParams layoutParams = viewFindViewById.getLayoutParams();
                        layoutParams.width = UIUtils.dp2px(PangleNativeLoader.this.f4161b, 38.0f);
                        layoutParams.height = UIUtils.dp2px(PangleNativeLoader.this.f4161b, 38.0f);
                        viewFindViewById.setLayoutParams(layoutParams);
                        viewGroup2.addView(imageView, -1, -1);
                    } else if (viewFindViewById instanceof ImageView) {
                        ((ImageView) viewFindViewById).setImageBitmap(this.d.getAdLogo());
                    }
                }
                TTMediaView tTMediaView = (TTMediaView) viewGroup.findViewById(gMViewBinder.mediaViewId);
                if (tTMediaView == null || (tTFeedAd = this.d) == null || (adView = tTFeedAd.getAdView()) == null) {
                    return;
                }
                removeSelfFromParent(adView);
                tTMediaView.removeAllViews();
                tTMediaView.addView(adView, -1, -1);
            }
        }
    }

    public void loadAd(Context context, boolean z, TTAdNative tTAdNative, AdSlot adSlot, final TTAbsAdLoaderAdapter tTAbsAdLoaderAdapter) {
        if (tTAdNative == null || tTAbsAdLoaderAdapter == null) {
            return;
        }
        this.f4161b = context;
        this.f4160a = z;
        tTAdNative.loadFeedAd(adSlot, new TTAdNative.FeedAdListener() { // from class: com.bytedance.msdk.adapter.pangle.PangleNativeLoader.1
            @Override // com.bytedance.sdk.openadsdk.TTAdNative.FeedAdListener, com.bytedance.sdk.openadsdk.common.CommonListener
            public void onError(int i, String str) {
                tTAbsAdLoaderAdapter.notifyAdFailed(PangleAdapterUtils.buildAdError(i, str));
            }

            @Override // com.bytedance.sdk.openadsdk.TTAdNative.FeedAdListener
            public void onFeedAdLoad(List<TTFeedAd> list) {
                if (list == null || list.isEmpty()) {
                    tTAbsAdLoaderAdapter.notifyAdFailed(PangleAdapterUtils.buildAdError(AdError.ERROR_CODE_NO_AD, AdError.getMessage(AdError.ERROR_CODE_NO_AD)));
                    return;
                }
                ArrayList arrayList = new ArrayList();
                Iterator<TTFeedAd> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(PangleNativeLoader.this.new PangleNativeAd(it.next()));
                }
                tTAbsAdLoaderAdapter.notifyAdLoaded(arrayList);
            }
        });
    }
}
