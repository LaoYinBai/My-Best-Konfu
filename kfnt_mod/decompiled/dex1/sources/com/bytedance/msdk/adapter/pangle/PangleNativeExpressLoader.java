package com.bytedance.msdk.adapter.pangle;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.v2.GMDislikeCallback;
import com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeExpressAdListener;
import com.bytedance.msdk.base.TTBaseAd;
import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdDislike;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTNativeExpressAd;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class PangleNativeExpressLoader {

    /* renamed from: a, reason: collision with root package name */
    private boolean f4151a;

    class PangleNativeExpressAd extends TTBaseAd {

        /* renamed from: a, reason: collision with root package name */
        TTNativeExpressAd f4154a;

        /* renamed from: b, reason: collision with root package name */
        TTNativeExpressAd.ExpressAdInteractionListener f4155b = new TTNativeExpressAd.ExpressAdInteractionListener() { // from class: com.bytedance.msdk.adapter.pangle.PangleNativeExpressLoader.PangleNativeExpressAd.2
            @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressAdInteractionListener
            public void onAdClicked(View view, int i) {
                if (((TTBaseAd) PangleNativeExpressAd.this).mTTNativeAdListener != null) {
                    ((TTBaseAd) PangleNativeExpressAd.this).mTTNativeAdListener.onAdClick();
                }
            }

            @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressAdInteractionListener
            public void onAdShow(View view, int i) {
                if (((TTBaseAd) PangleNativeExpressAd.this).mTTNativeAdListener != null) {
                    ((TTBaseAd) PangleNativeExpressAd.this).mTTNativeAdListener.onAdShow();
                }
            }

            @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressAdInteractionListener
            public void onRenderFail(View view, String str, int i) {
                if (((TTBaseAd) PangleNativeExpressAd.this).mTTNativeAdListener instanceof GMNativeExpressAdListener) {
                    ((GMNativeExpressAdListener) ((TTBaseAd) PangleNativeExpressAd.this).mTTNativeAdListener).onRenderFail(view, str, i);
                }
            }

            @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressAdInteractionListener
            public void onRenderSuccess(View view, float f, float f2) {
                if (((TTBaseAd) PangleNativeExpressAd.this).mTTNativeAdListener instanceof GMNativeExpressAdListener) {
                    ((GMNativeExpressAdListener) ((TTBaseAd) PangleNativeExpressAd.this).mTTNativeAdListener).onRenderSuccess(f, f2);
                }
            }
        };

        /* renamed from: c, reason: collision with root package name */
        TTNativeExpressAd.ExpressVideoAdListener f4156c = new TTNativeExpressAd.ExpressVideoAdListener() { // from class: com.bytedance.msdk.adapter.pangle.PangleNativeExpressLoader.PangleNativeExpressAd.3
            @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressVideoAdListener
            public void onClickRetry() {
            }

            @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressVideoAdListener
            public void onProgressUpdate(long j, long j2) {
            }

            @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressVideoAdListener
            public void onVideoAdComplete() {
                if (((TTBaseAd) PangleNativeExpressAd.this).mTTVideoListener != null) {
                    ((TTBaseAd) PangleNativeExpressAd.this).mTTVideoListener.onVideoCompleted();
                }
            }

            @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressVideoAdListener
            public void onVideoAdContinuePlay() {
                if (((TTBaseAd) PangleNativeExpressAd.this).mTTVideoListener != null) {
                    ((TTBaseAd) PangleNativeExpressAd.this).mTTVideoListener.onVideoResume();
                }
            }

            @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressVideoAdListener
            public void onVideoAdPaused() {
                if (((TTBaseAd) PangleNativeExpressAd.this).mTTVideoListener != null) {
                    ((TTBaseAd) PangleNativeExpressAd.this).mTTVideoListener.onVideoPause();
                }
            }

            @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressVideoAdListener
            public void onVideoAdStartPlay() {
                if (((TTBaseAd) PangleNativeExpressAd.this).mTTVideoListener != null) {
                    ((TTBaseAd) PangleNativeExpressAd.this).mTTVideoListener.onVideoStart();
                }
            }

            @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressVideoAdListener
            public void onVideoError(int i, int i2) {
                if (((TTBaseAd) PangleNativeExpressAd.this).mTTVideoListener != null) {
                    ((TTBaseAd) PangleNativeExpressAd.this).mTTVideoListener.onVideoError(new AdError(i, "MediaPlayer inter error code:" + i2));
                }
            }

            @Override // com.bytedance.sdk.openadsdk.TTNativeExpressAd.ExpressVideoAdListener
            public void onVideoLoad() {
            }
        };

        PangleNativeExpressAd(PangleNativeExpressLoader pangleNativeExpressLoader, TTNativeExpressAd tTNativeExpressAd) {
            Map<String, Object> mediaExtraInfo;
            this.f4154a = tTNativeExpressAd;
            setImageMode(this.f4154a.getImageMode());
            setInteractionType(this.f4154a.getInteractionType());
            setExpressAd(true);
            this.f4154a.setExpressInteractionListener(this.f4155b);
            if (tTNativeExpressAd.getImageMode() == 5) {
                this.f4154a.setVideoAdListener(this.f4156c);
            }
            if (!pangleNativeExpressLoader.f4151a || (mediaExtraInfo = tTNativeExpressAd.getMediaExtraInfo()) == null) {
                return;
            }
            double value = PangleAdapterUtils.getValue(mediaExtraInfo.get("price"));
            Logger.d("TTMediationSDK_ECMP", "pangle 模板native 返回的 cpm价格：" + value);
            setCpm(value <= 0.0d ? 0.0d : value);
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public long getAdId() {
            TTNativeExpressAd tTNativeExpressAd = this.f4154a;
            return tTNativeExpressAd != null ? PangleAdapterUtils.getAdId(tTNativeExpressAd.getMediaExtraInfo()) : super.getAdId();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return 5;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public View getAdView() {
            TTNativeExpressAd tTNativeExpressAd = this.f4154a;
            if (tTNativeExpressAd != null) {
                return tTNativeExpressAd.getExpressAdView();
            }
            return null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public long getCreativeId() {
            TTNativeExpressAd tTNativeExpressAd = this.f4154a;
            return tTNativeExpressAd != null ? PangleAdapterUtils.getCreativeId(tTNativeExpressAd.getMediaExtraInfo()) : super.getCreativeId();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public String getReqId() {
            TTNativeExpressAd tTNativeExpressAd = this.f4154a;
            return tTNativeExpressAd != null ? PangleAdapterUtils.getReqId(tTNativeExpressAd.getMediaExtraInfo()) : super.getReqId();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return this.f4154a == null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDislike() {
            return true;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            TTNativeExpressAd tTNativeExpressAd = this.f4154a;
            if (tTNativeExpressAd != null) {
                tTNativeExpressAd.destroy();
                this.f4154a = null;
            }
            super.onDestroy();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void render() {
            TTNativeExpressAd tTNativeExpressAd = this.f4154a;
            if (tTNativeExpressAd != null) {
                tTNativeExpressAd.render();
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void setDislikeCallback(Activity activity, final GMDislikeCallback gMDislikeCallback) {
            TTNativeExpressAd tTNativeExpressAd = this.f4154a;
            if (tTNativeExpressAd != null) {
                tTNativeExpressAd.setDislikeCallback(activity, new TTAdDislike.DislikeInteractionCallback(this) { // from class: com.bytedance.msdk.adapter.pangle.PangleNativeExpressLoader.PangleNativeExpressAd.1
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

    public void loadAd(Context context, boolean z, TTAdNative tTAdNative, AdSlot adSlot, final TTAbsAdLoaderAdapter tTAbsAdLoaderAdapter) {
        if (tTAdNative == null || tTAbsAdLoaderAdapter == null) {
            return;
        }
        this.f4151a = z;
        tTAdNative.loadNativeExpressAd(adSlot, new TTAdNative.NativeExpressAdListener() { // from class: com.bytedance.msdk.adapter.pangle.PangleNativeExpressLoader.1
            @Override // com.bytedance.sdk.openadsdk.TTAdNative.NativeExpressAdListener, com.bytedance.sdk.openadsdk.common.CommonListener
            public void onError(int i, String str) {
                tTAbsAdLoaderAdapter.notifyAdFailed(PangleAdapterUtils.buildAdError(i, str));
            }

            @Override // com.bytedance.sdk.openadsdk.TTAdNative.NativeExpressAdListener
            public void onNativeExpressAdLoad(List<TTNativeExpressAd> list) {
                if (list == null || list.size() == 0) {
                    tTAbsAdLoaderAdapter.notifyAdFailed(PangleAdapterUtils.buildAdError(AdError.ERROR_CODE_NO_AD, AdError.getMessage(AdError.ERROR_CODE_NO_AD)));
                    return;
                }
                ArrayList arrayList = new ArrayList();
                for (TTNativeExpressAd tTNativeExpressAd : list) {
                    if (tTNativeExpressAd != null) {
                        arrayList.add(new PangleNativeExpressAd(PangleNativeExpressLoader.this, tTNativeExpressAd));
                    }
                }
                tTAbsAdLoaderAdapter.notifyAdLoaded(arrayList);
            }
        });
    }
}
