package com.bytedance.msdk.adapter.sigmob;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.UIUtils;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdErrorUtil;
import com.bytedance.msdk.api.format.TTMediaView;
import com.bytedance.msdk.api.format.TTNativeAdView;
import com.bytedance.msdk.api.v2.GMDislikeCallback;
import com.bytedance.msdk.api.v2.ad.nativeAd.GMViewBinder;
import com.bytedance.msdk.base.TTBaseAd;
import com.sigmob.windad.WindAdError;
import com.sigmob.windad.WindAds;
import com.sigmob.windad.natives.NativeADData;
import com.sigmob.windad.natives.NativeADEventListener;
import com.sigmob.windad.natives.WindNativeAdRequest;
import com.sigmob.windad.natives.WindNativeUnifiedAd;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class SigmobNativeAdapter extends TTAbsAdLoaderAdapter {
    public static final String TAG = "SigmobNativeAdapter";

    /* renamed from: a, reason: collision with root package name */
    private Context f4195a;

    /* renamed from: b, reason: collision with root package name */
    private WindNativeUnifiedAd f4196b;

    class SigmobNativeAd extends TTBaseAd {

        /* renamed from: a, reason: collision with root package name */
        NativeADData f4198a;

        public SigmobNativeAd(NativeADData nativeADData) {
            int i;
            this.f4198a = nativeADData;
            setAdDescription(nativeADData.getDesc());
            setIconUrl(nativeADData.getIconUrl());
            setTitle(nativeADData.getTitle());
            setActionText(nativeADData.getCTAText());
            setSource(nativeADData.getTitle());
            switch (nativeADData.getAdPatternType()) {
                case 0:
                    i = -1;
                    setImageMode(i);
                    break;
                case 1:
                    i = 2;
                    setImageMode(i);
                    break;
                case 2:
                    setImageMode(3);
                    break;
                case 3:
                    setImageMode(4);
                    break;
                case 4:
                    setImageMode(5);
                    break;
            }
            String cTAText = nativeADData.getCTAText();
            if (cTAText.contains("下载")) {
                setInteractionType(4);
            } else if (!cTAText.contains("详情") && cTAText.contains("拨打")) {
                setInteractionType(5);
            } else {
                setInteractionType(3);
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return 5;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return this.f4198a == null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            NativeADData nativeADData = this.f4198a;
            if (nativeADData != null) {
                nativeADData.destroy();
                this.f4198a = null;
            }
            super.onDestroy();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onPause() {
            super.onPause();
            NativeADData nativeADData = this.f4198a;
            if (nativeADData != null) {
                nativeADData.pauseVideo();
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onResume() {
            super.onResume();
            NativeADData nativeADData = this.f4198a;
            if (nativeADData != null) {
                nativeADData.resumeVideo();
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void registerViewForInteraction(@NonNull ViewGroup viewGroup, List<View> list, List<View> list2, GMViewBinder gMViewBinder) {
            NativeADData nativeADData;
            View viewFindViewById;
            super.registerViewForInteraction(viewGroup, list, list2, gMViewBinder);
            if (viewGroup instanceof TTNativeAdView) {
                NativeADData nativeADData2 = this.f4198a;
                if (nativeADData2 != null) {
                    nativeADData2.bindViewForInteraction(SigmobNativeAdapter.this.f4195a, viewGroup, list, list2, (View) null, new NativeADEventListener() { // from class: com.bytedance.msdk.adapter.sigmob.SigmobNativeAdapter.SigmobNativeAd.1
                        public void onADClicked() {
                            if (((TTBaseAd) SigmobNativeAd.this).mTTNativeAdListener != null) {
                                ((TTBaseAd) SigmobNativeAd.this).mTTNativeAdListener.onAdClick();
                            }
                            Logger.d(SigmobNativeAdapter.TAG, "onADClicked: ");
                        }

                        public void onADError(WindAdError windAdError) {
                            Logger.d(SigmobNativeAdapter.TAG, "onADError error code :" + windAdError.toString());
                        }

                        public void onADExposed() {
                            if (((TTBaseAd) SigmobNativeAd.this).mTTNativeAdListener != null) {
                                ((TTBaseAd) SigmobNativeAd.this).mTTNativeAdListener.onAdShow();
                            }
                            Logger.d(SigmobNativeAdapter.TAG, "onADExposed: ");
                        }

                        public void onADStatusChanged(String str) {
                            Logger.d(SigmobNativeAdapter.TAG, "onADStatusChanged: " + str);
                        }
                    });
                }
                NativeADData nativeADData3 = this.f4198a;
                if (nativeADData3 != null && nativeADData3.getAdLogo() != null && (viewFindViewById = viewGroup.findViewById(gMViewBinder.logoLayoutId)) != null) {
                    viewFindViewById.setVisibility(0);
                    if (viewFindViewById instanceof ViewGroup) {
                        ViewGroup viewGroup2 = (ViewGroup) viewFindViewById;
                        viewGroup2.removeAllViews();
                        ImageView imageView = new ImageView(SigmobNativeAdapter.this.f4195a);
                        imageView.setImageBitmap(this.f4198a.getAdLogo());
                        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                        ViewGroup.LayoutParams layoutParams = viewFindViewById.getLayoutParams();
                        layoutParams.width = UIUtils.dp2px(SigmobNativeAdapter.this.f4195a, 38.0f);
                        layoutParams.height = UIUtils.dp2px(SigmobNativeAdapter.this.f4195a, 38.0f);
                        viewFindViewById.setLayoutParams(layoutParams);
                        viewGroup2.addView(imageView, -1, -1);
                    } else if (viewFindViewById instanceof ImageView) {
                        ((ImageView) viewFindViewById).setImageBitmap(this.f4198a.getAdLogo());
                    }
                }
                if (getImageMode() == 5) {
                    TTMediaView tTMediaView = (TTMediaView) viewGroup.findViewById(gMViewBinder.mediaViewId);
                    if (tTMediaView == null || (nativeADData = this.f4198a) == null) {
                        return;
                    }
                    nativeADData.bindMediaView(SigmobNativeAdapter.this.f4195a, tTMediaView, new NativeADData.NativeADMediaListener() { // from class: com.bytedance.msdk.adapter.sigmob.SigmobNativeAdapter.SigmobNativeAd.2
                        public void onVideoCompleted() {
                            if (((TTBaseAd) SigmobNativeAd.this).mTTVideoListener != null) {
                                ((TTBaseAd) SigmobNativeAd.this).mTTVideoListener.onVideoCompleted();
                            }
                            Logger.d(SigmobNativeAdapter.TAG, "onVideoCompleted: ");
                        }

                        public void onVideoError(WindAdError windAdError) {
                            if (((TTBaseAd) SigmobNativeAd.this).mTTVideoListener != null) {
                                ((TTBaseAd) SigmobNativeAd.this).mTTVideoListener.onVideoError(new AdError(windAdError.getErrorCode(), windAdError.toString()));
                            }
                            Logger.d(SigmobNativeAdapter.TAG, "onVideoError: " + windAdError.toString());
                        }

                        public void onVideoLoad() {
                            Logger.d(SigmobNativeAdapter.TAG, "onVideoLoad: ");
                        }

                        public void onVideoPause() {
                            if (((TTBaseAd) SigmobNativeAd.this).mTTVideoListener != null) {
                                ((TTBaseAd) SigmobNativeAd.this).mTTVideoListener.onVideoPause();
                            }
                            Logger.d(SigmobNativeAdapter.TAG, "onVideoPause: ");
                        }

                        public void onVideoResume() {
                            if (((TTBaseAd) SigmobNativeAd.this).mTTVideoListener != null) {
                                ((TTBaseAd) SigmobNativeAd.this).mTTVideoListener.onVideoResume();
                            }
                            Logger.d(SigmobNativeAdapter.TAG, "onVideoResume: ");
                        }

                        public void onVideoStart() {
                            if (((TTBaseAd) SigmobNativeAd.this).mTTVideoListener != null) {
                                ((TTBaseAd) SigmobNativeAd.this).mTTVideoListener.onVideoStart();
                            }
                            Logger.d(SigmobNativeAdapter.TAG, "onVideoStart: ");
                        }
                    });
                    return;
                }
                if (getImageMode() == 2 || getImageMode() == 3) {
                    if (this.f4198a != null) {
                        View viewFindViewById2 = viewGroup.findViewById(gMViewBinder.mainImageId);
                        if (viewFindViewById2 instanceof ImageView) {
                            ArrayList arrayList = new ArrayList();
                            arrayList.add((ImageView) viewFindViewById2);
                            this.f4198a.bindImageViews(SigmobNativeAdapter.this.f4195a, arrayList, 0);
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (getImageMode() != 4 || this.f4198a == null) {
                    return;
                }
                View viewFindViewById3 = viewGroup.findViewById(gMViewBinder.groupImage1Id);
                View viewFindViewById4 = viewGroup.findViewById(gMViewBinder.groupImage2Id);
                View viewFindViewById5 = viewGroup.findViewById(gMViewBinder.groupImage3Id);
                ArrayList arrayList2 = new ArrayList();
                if (viewFindViewById3 instanceof ImageView) {
                    arrayList2.add((ImageView) viewFindViewById3);
                }
                if (viewFindViewById4 instanceof ImageView) {
                    arrayList2.add((ImageView) viewFindViewById4);
                }
                if (viewFindViewById5 instanceof ImageView) {
                    arrayList2.add((ImageView) viewFindViewById5);
                }
                this.f4198a.bindImageViews(SigmobNativeAdapter.this.f4195a, arrayList2, 0);
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void setDislikeCallback(Activity activity, final GMDislikeCallback gMDislikeCallback) {
            super.setDislikeCallback(activity, gMDislikeCallback);
            NativeADData nativeADData = this.f4198a;
            if (nativeADData != null) {
                nativeADData.setDislikeInteractionCallback(activity, new NativeADData.DislikeInteractionCallback(this) { // from class: com.bytedance.msdk.adapter.sigmob.SigmobNativeAdapter.SigmobNativeAd.3
                    public void onCancel() {
                        GMDislikeCallback gMDislikeCallback2 = gMDislikeCallback;
                        if (gMDislikeCallback2 != null) {
                            gMDislikeCallback2.onShow();
                        }
                    }

                    public void onSelected(int i, String str, boolean z) {
                        gMDislikeCallback.onSelected(i, str);
                    }

                    public void onShow() {
                        gMDislikeCallback.onCancel();
                    }
                });
            }
        }
    }

    private void a() {
        this.f4196b = new WindNativeUnifiedAd(this.f4195a, new WindNativeAdRequest(getAdSlotId(), this.mAdSlot.getUserID(), getAdLoadCount(), (Map) null));
        this.f4196b.loadAd(new WindNativeUnifiedAd.NativeAdLoadListener() { // from class: com.bytedance.msdk.adapter.sigmob.SigmobNativeAdapter.1
            public void onError(WindAdError windAdError, String str) {
                SigmobNativeAdapter sigmobNativeAdapter;
                AdError adErrorObtainAdError;
                if (windAdError != null) {
                    Log.e("TTMediationSDK", "sigmob_native_onError  err_code:" + windAdError.getErrorCode() + "  err_msg:" + windAdError.getMessage());
                    adErrorObtainAdError = AdErrorUtil.getSigmobError(windAdError.getErrorCode(), windAdError.getMessage());
                    if (adErrorObtainAdError != null) {
                        adErrorObtainAdError.message = windAdError.getMessage();
                    }
                    sigmobNativeAdapter = SigmobNativeAdapter.this;
                } else {
                    sigmobNativeAdapter = SigmobNativeAdapter.this;
                    adErrorObtainAdError = AdErrorUtil.obtainAdError(AdError.ERROR_CODE_NO_AD, AdError.AD_NO_FILL);
                }
                sigmobNativeAdapter.notifyAdFailed(adErrorObtainAdError);
            }

            public void onFeedAdLoad(String str) {
                List<NativeADData> nativeADDataList = SigmobNativeAdapter.this.f4196b.getNativeADDataList();
                if (nativeADDataList == null || nativeADDataList.isEmpty()) {
                    SigmobNativeAdapter.this.notifyAdFailed(new AdError(AdError.ERROR_CODE_NO_AD, AdError.getMessage(AdError.ERROR_CODE_NO_AD)));
                    return;
                }
                ArrayList arrayList = new ArrayList();
                for (NativeADData nativeADData : nativeADDataList) {
                    if (nativeADData != null) {
                        arrayList.add(SigmobNativeAdapter.this.new SigmobNativeAd(nativeADData));
                    }
                }
                SigmobNativeAdapter.this.notifyAdLoaded(arrayList);
            }
        });
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void destroy() {
        WindNativeUnifiedAd windNativeUnifiedAd = this.f4196b;
        if (windNativeUnifiedAd != null) {
            windNativeUnifiedAd.destroy();
        }
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getAdNetWorkName() {
        return "sigmob";
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getSdkVersion() {
        return WindAds.getVersion();
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void loadAd(Context context, Map<String, Object> map) {
        int iIntValue;
        this.f4195a = context;
        Object obj = map.get("tt_ad_origin_type");
        if ((obj instanceof Integer) && ((iIntValue = ((Integer) obj).intValue()) == 2 || (iIntValue == 0 && this.mAdSlot.getAdStyleType() == 2))) {
            a();
        } else {
            notifyAdFailed(AdErrorUtil.obtainAdError(AdError.ERROR_CODE_RENDER_TYPE_MISMATCH, AdError.getMessage(AdError.ERROR_CODE_RENDER_TYPE_MISMATCH)));
        }
    }
}
