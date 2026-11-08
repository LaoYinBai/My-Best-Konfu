package com.bytedance.msdk.api.v2.ad.custom.fullvideo;

import android.content.Context;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.v2.ad.custom.GMCustomAdError;
import com.bytedance.msdk.api.v2.ad.custom.GMCustomTTBaseAd;
import com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter;
import com.bytedance.msdk.api.v2.ad.custom.base.GMCustomVideoAdapter;
import com.bytedance.msdk.api.v2.ad.custom.bean.GMCustomServiceConfig;
import com.bytedance.msdk.api.v2.ad.fullvideo.GMFullVideoAdListener;
import com.bytedance.msdk.api.v2.slot.GMAdSlotBase;
import com.bytedance.msdk.api.v2.slot.GMAdSlotFullVideo;
import com.bytedance.msdk.api.v2.slot.GMAdSlotInterstitialFull;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class GMCustomFullVideoAdapter extends GMCustomVideoAdapter implements IGMCustomFullVideoEvent {
    public final void callAdVideoCache() {
        GMCustomTTBaseAd gMCustomTTBaseAd;
        TTAbsAdLoaderAdapter tTAbsAdLoaderAdapter = this.f4373c;
        if (tTAbsAdLoaderAdapter == null || (gMCustomTTBaseAd = this.d) == null) {
            return;
        }
        tTAbsAdLoaderAdapter.notifyAdVideoCache(gMCustomTTBaseAd, null);
    }

    @Override // com.bytedance.msdk.api.v2.ad.custom.fullvideo.IGMCustomFullVideoEvent
    public final void callFullVideoAdClick() {
        if (this.d != null) {
            checkLoadSuccess(new GMCustomBaseAdapter.CheckCallback() { // from class: com.bytedance.msdk.api.v2.ad.custom.fullvideo.GMCustomFullVideoAdapter.2
                @Override // com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter.CheckCallback
                public void callback() {
                    GMCustomFullVideoAdapter.this.checkClick(new GMCustomBaseAdapter.CheckCallback() { // from class: com.bytedance.msdk.api.v2.ad.custom.fullvideo.GMCustomFullVideoAdapter.2.1
                        @Override // com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter.CheckCallback
                        public void callback() {
                            GMFullVideoAdListener gMFullVideoAdListener = (GMFullVideoAdListener) ((GMCustomBaseAdapter) GMCustomFullVideoAdapter.this).d.getTTAdapterCallback();
                            if (gMFullVideoAdListener != null) {
                                gMFullVideoAdListener.onFullVideoAdClick();
                            }
                        }
                    });
                }
            });
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.custom.fullvideo.IGMCustomFullVideoEvent
    public final void callFullVideoAdClosed() {
        if (this.d != null) {
            checkLoadSuccess(new GMCustomBaseAdapter.CheckCallback() { // from class: com.bytedance.msdk.api.v2.ad.custom.fullvideo.GMCustomFullVideoAdapter.3
                @Override // com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter.CheckCallback
                public void callback() {
                    GMFullVideoAdListener gMFullVideoAdListener = (GMFullVideoAdListener) ((GMCustomBaseAdapter) GMCustomFullVideoAdapter.this).d.getTTAdapterCallback();
                    if (gMFullVideoAdListener != null) {
                        gMFullVideoAdListener.onFullVideoAdClosed();
                    }
                }
            });
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.custom.fullvideo.IGMCustomFullVideoEvent
    public final void callFullVideoAdShow() {
        if (this.d != null) {
            checkLoadSuccess(new GMCustomBaseAdapter.CheckCallback() { // from class: com.bytedance.msdk.api.v2.ad.custom.fullvideo.GMCustomFullVideoAdapter.1
                @Override // com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter.CheckCallback
                public void callback() {
                    GMCustomFullVideoAdapter.this.checkShow(new GMCustomBaseAdapter.CheckCallback() { // from class: com.bytedance.msdk.api.v2.ad.custom.fullvideo.GMCustomFullVideoAdapter.1.1
                        @Override // com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter.CheckCallback
                        public void callback() {
                            GMFullVideoAdListener gMFullVideoAdListener = (GMFullVideoAdListener) ((GMCustomBaseAdapter) GMCustomFullVideoAdapter.this).d.getTTAdapterCallback();
                            if (gMFullVideoAdListener != null) {
                                gMFullVideoAdListener.onFullVideoAdShow();
                            }
                        }
                    });
                }
            });
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.custom.fullvideo.IGMCustomFullVideoEvent
    public final void callFullVideoComplete() {
        if (this.d != null) {
            checkLoadSuccess(new GMCustomBaseAdapter.CheckCallback() { // from class: com.bytedance.msdk.api.v2.ad.custom.fullvideo.GMCustomFullVideoAdapter.4
                @Override // com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter.CheckCallback
                public void callback() {
                    GMFullVideoAdListener gMFullVideoAdListener = (GMFullVideoAdListener) ((GMCustomBaseAdapter) GMCustomFullVideoAdapter.this).d.getTTAdapterCallback();
                    if (gMFullVideoAdListener != null) {
                        gMFullVideoAdListener.onVideoComplete();
                    }
                }
            });
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.custom.fullvideo.IGMCustomFullVideoEvent
    public final void callFullVideoError() {
        if (this.d != null) {
            checkLoadSuccess(new GMCustomBaseAdapter.CheckCallback() { // from class: com.bytedance.msdk.api.v2.ad.custom.fullvideo.GMCustomFullVideoAdapter.5
                @Override // com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter.CheckCallback
                public void callback() {
                    GMFullVideoAdListener gMFullVideoAdListener = (GMFullVideoAdListener) ((GMCustomBaseAdapter) GMCustomFullVideoAdapter.this).d.getTTAdapterCallback();
                    if (gMFullVideoAdListener != null) {
                        gMFullVideoAdListener.onVideoError();
                    }
                }
            });
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.custom.fullvideo.IGMCustomFullVideoEvent
    public final void callFullVideoSkippedVideo() {
        if (this.d != null) {
            checkLoadSuccess(new GMCustomBaseAdapter.CheckCallback() { // from class: com.bytedance.msdk.api.v2.ad.custom.fullvideo.GMCustomFullVideoAdapter.6
                @Override // com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter.CheckCallback
                public void callback() {
                    GMFullVideoAdListener gMFullVideoAdListener = (GMFullVideoAdListener) ((GMCustomBaseAdapter) GMCustomFullVideoAdapter.this).d.getTTAdapterCallback();
                    if (gMFullVideoAdListener != null) {
                        gMFullVideoAdListener.onSkippedVideo();
                    }
                }
            });
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.custom.base.IGMCustomLoadAdCall
    public final void callLoadFail(GMCustomAdError gMCustomAdError) {
        if (isCallLoadFailCall()) {
            Logger.e("TTMediationSDK", "自定义Adapter 加载成功或者失败回调只能调用一次");
            return;
        }
        TTAbsAdLoaderAdapter tTAbsAdLoaderAdapter = this.f4373c;
        if (tTAbsAdLoaderAdapter != null) {
            tTAbsAdLoaderAdapter.notifyAdFailed(new AdError(AdError.ERROR_CODE_CUSTOM_FULL_VIDEO_LOAD_ERROR, AdError.getMessage(AdError.ERROR_CODE_CUSTOM_FULL_VIDEO_SHOW_ERROR), gMCustomAdError == null ? -1 : gMCustomAdError.getCode(), gMCustomAdError == null ? "" : gMCustomAdError.getMessage()));
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.custom.base.IGMCustomLoadAdCall
    public final void callLoadSuccess() {
        if (isCallLoadSuccessCall()) {
            Logger.e("TTMediationSDK", "自定义Adapter 加载成功或者失败回调只能调用一次");
            return;
        }
        Logger.e("TTMediationSDK", "自定义Adapter callLoadSuccess");
        GMCustomFullVideoAd gMCustomFullVideoAd = new GMCustomFullVideoAd();
        gMCustomFullVideoAd.setAdType(this.f4372b.r());
        this.d = gMCustomFullVideoAd.getTTBaseAd();
        this.d.setGMCustomBaseAdapter(this);
        TTAbsAdLoaderAdapter tTAbsAdLoaderAdapter = this.f4373c;
        if (tTAbsAdLoaderAdapter != null) {
            tTAbsAdLoaderAdapter.notifyAdLoaded(this.d);
        }
    }

    public abstract void load(Context context, GMAdSlotFullVideo gMAdSlotFullVideo, GMCustomServiceConfig gMCustomServiceConfig);

    @Override // com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter
    public void loadCustom(Context context, GMAdSlotBase gMAdSlotBase, GMCustomServiceConfig gMCustomServiceConfig) {
        load(context, gMAdSlotBase instanceof GMAdSlotInterstitialFull ? ((GMAdSlotInterstitialFull) gMAdSlotBase).getGMAdSlotFullVideo() : (GMAdSlotFullVideo) gMAdSlotBase, gMCustomServiceConfig);
    }
}
