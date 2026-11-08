package com.bytedance.msdk.api.v2.ad.custom.reward;

import android.app.Activity;
import android.content.Context;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.reward.RewardItem;
import com.bytedance.msdk.api.v2.ad.custom.GMCustomAdError;
import com.bytedance.msdk.api.v2.ad.custom.GMCustomTTBaseAd;
import com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter;
import com.bytedance.msdk.api.v2.ad.custom.base.GMCustomVideoAdapter;
import com.bytedance.msdk.api.v2.ad.custom.bean.GMCustomServiceConfig;
import com.bytedance.msdk.api.v2.ad.reward.GMRewardedAdListener;
import com.bytedance.msdk.api.v2.slot.GMAdSlotBase;
import com.bytedance.msdk.api.v2.slot.GMAdSlotRewardVideo;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class GMCustomRewardAdapter extends GMCustomVideoAdapter implements IGMCustomRewardEvent {
    public final void callAdVideoCache() {
        GMCustomTTBaseAd gMCustomTTBaseAd;
        TTAbsAdLoaderAdapter tTAbsAdLoaderAdapter = this.f4373c;
        if (tTAbsAdLoaderAdapter == null || (gMCustomTTBaseAd = this.d) == null) {
            return;
        }
        tTAbsAdLoaderAdapter.notifyAdVideoCache(gMCustomTTBaseAd, null);
    }

    @Override // com.bytedance.msdk.api.v2.ad.custom.base.IGMCustomLoadAdCall
    public final void callLoadFail(GMCustomAdError gMCustomAdError) {
        if (isCallLoadFailCall()) {
            Logger.e("TTMediationSDK", "自定义Adapter 加载成功或者失败回调只能调用一次");
            return;
        }
        TTAbsAdLoaderAdapter tTAbsAdLoaderAdapter = this.f4373c;
        if (tTAbsAdLoaderAdapter != null) {
            tTAbsAdLoaderAdapter.notifyAdFailed(new AdError(AdError.ERROR_CODE_CUSTOM_REWARD_LOAD_ERROR, AdError.getMessage(AdError.ERROR_CODE_CUSTOM_REWARD_LOAD_ERROR), gMCustomAdError == null ? -1 : gMCustomAdError.getCode(), gMCustomAdError == null ? "" : gMCustomAdError.getMessage()));
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.custom.base.IGMCustomLoadAdCall
    public final void callLoadSuccess() {
        if (isCallLoadSuccessCall()) {
            Logger.e("TTMediationSDK", "自定义Adapter 加载成功或者失败回调只能调用一次");
            return;
        }
        Logger.e("TTMediationSDK", "自定义Adapter callLoadSuccess");
        GMCustomRewardAd gMCustomRewardAd = new GMCustomRewardAd();
        gMCustomRewardAd.setAdType(this.f4372b.r());
        this.d = gMCustomRewardAd.getTTBaseAd();
        this.d.setGMCustomBaseAdapter(this);
        TTAbsAdLoaderAdapter tTAbsAdLoaderAdapter = this.f4373c;
        if (tTAbsAdLoaderAdapter != null) {
            tTAbsAdLoaderAdapter.notifyAdLoaded(this.d);
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.custom.reward.IGMCustomRewardEvent
    public final void callRewardClick() {
        if (this.d != null) {
            checkLoadSuccess(new GMCustomBaseAdapter.CheckCallback() { // from class: com.bytedance.msdk.api.v2.ad.custom.reward.GMCustomRewardAdapter.2
                @Override // com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter.CheckCallback
                public void callback() {
                    GMCustomRewardAdapter.this.checkClick(new GMCustomBaseAdapter.CheckCallback() { // from class: com.bytedance.msdk.api.v2.ad.custom.reward.GMCustomRewardAdapter.2.1
                        @Override // com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter.CheckCallback
                        public void callback() {
                            GMRewardedAdListener gMRewardedAdListener = (GMRewardedAdListener) ((GMCustomBaseAdapter) GMCustomRewardAdapter.this).d.getTTAdapterCallback();
                            if (gMRewardedAdListener != null) {
                                gMRewardedAdListener.onRewardClick();
                            }
                        }
                    });
                }
            });
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.custom.reward.IGMCustomRewardEvent
    public final void callRewardSkippedVideo() {
        if (this.d != null) {
            checkLoadSuccess(new GMCustomBaseAdapter.CheckCallback() { // from class: com.bytedance.msdk.api.v2.ad.custom.reward.GMCustomRewardAdapter.7
                @Override // com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter.CheckCallback
                public void callback() {
                    GMRewardedAdListener gMRewardedAdListener = (GMRewardedAdListener) ((GMCustomBaseAdapter) GMCustomRewardAdapter.this).d.getTTAdapterCallback();
                    if (gMRewardedAdListener != null) {
                        gMRewardedAdListener.onSkippedVideo();
                    }
                }
            });
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.custom.reward.IGMCustomRewardEvent
    public final void callRewardVerify(final RewardItem rewardItem) {
        if (this.d != null) {
            checkLoadSuccess(new GMCustomBaseAdapter.CheckCallback() { // from class: com.bytedance.msdk.api.v2.ad.custom.reward.GMCustomRewardAdapter.6
                @Override // com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter.CheckCallback
                public void callback() {
                    GMRewardedAdListener gMRewardedAdListener = (GMRewardedAdListener) ((GMCustomBaseAdapter) GMCustomRewardAdapter.this).d.getTTAdapterCallback();
                    if (gMRewardedAdListener != null) {
                        gMRewardedAdListener.onRewardVerify(rewardItem);
                    }
                }
            });
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.custom.reward.IGMCustomRewardEvent
    public final void callRewardVideoComplete() {
        if (this.d != null) {
            checkLoadSuccess(new GMCustomBaseAdapter.CheckCallback() { // from class: com.bytedance.msdk.api.v2.ad.custom.reward.GMCustomRewardAdapter.4
                @Override // com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter.CheckCallback
                public void callback() {
                    GMRewardedAdListener gMRewardedAdListener = (GMRewardedAdListener) ((GMCustomBaseAdapter) GMCustomRewardAdapter.this).d.getTTAdapterCallback();
                    if (gMRewardedAdListener != null) {
                        gMRewardedAdListener.onVideoComplete();
                    }
                }
            });
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.custom.reward.IGMCustomRewardEvent
    public final void callRewardVideoError() {
        if (this.d != null) {
            checkLoadSuccess(new GMCustomBaseAdapter.CheckCallback() { // from class: com.bytedance.msdk.api.v2.ad.custom.reward.GMCustomRewardAdapter.5
                @Override // com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter.CheckCallback
                public void callback() {
                    GMRewardedAdListener gMRewardedAdListener = (GMRewardedAdListener) ((GMCustomBaseAdapter) GMCustomRewardAdapter.this).d.getTTAdapterCallback();
                    if (gMRewardedAdListener != null) {
                        gMRewardedAdListener.onVideoError();
                    }
                }
            });
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.custom.reward.IGMCustomRewardEvent
    public final void callRewardedAdClosed() {
        if (this.d != null) {
            checkLoadSuccess(new GMCustomBaseAdapter.CheckCallback() { // from class: com.bytedance.msdk.api.v2.ad.custom.reward.GMCustomRewardAdapter.3
                @Override // com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter.CheckCallback
                public void callback() {
                    GMRewardedAdListener gMRewardedAdListener = (GMRewardedAdListener) ((GMCustomBaseAdapter) GMCustomRewardAdapter.this).d.getTTAdapterCallback();
                    if (gMRewardedAdListener != null) {
                        gMRewardedAdListener.onRewardedAdClosed();
                    }
                }
            });
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.custom.reward.IGMCustomRewardEvent
    public final void callRewardedAdShow() {
        if (this.d != null) {
            checkLoadSuccess(new GMCustomBaseAdapter.CheckCallback() { // from class: com.bytedance.msdk.api.v2.ad.custom.reward.GMCustomRewardAdapter.1
                @Override // com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter.CheckCallback
                public void callback() {
                    GMCustomRewardAdapter.this.checkShow(new GMCustomBaseAdapter.CheckCallback() { // from class: com.bytedance.msdk.api.v2.ad.custom.reward.GMCustomRewardAdapter.1.1
                        @Override // com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter.CheckCallback
                        public void callback() {
                            GMRewardedAdListener gMRewardedAdListener = (GMRewardedAdListener) ((GMCustomBaseAdapter) GMCustomRewardAdapter.this).d.getTTAdapterCallback();
                            if (gMRewardedAdListener != null) {
                                gMRewardedAdListener.onRewardedAdShow();
                            }
                        }
                    });
                }
            });
        }
    }

    public abstract void load(Context context, GMAdSlotRewardVideo gMAdSlotRewardVideo, GMCustomServiceConfig gMCustomServiceConfig);

    @Override // com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter
    public void loadCustom(Context context, GMAdSlotBase gMAdSlotBase, GMCustomServiceConfig gMCustomServiceConfig) {
        load(context, (GMAdSlotRewardVideo) gMAdSlotBase, gMCustomServiceConfig);
    }

    @Override // com.bytedance.msdk.api.v2.ad.custom.base.GMCustomVideoAdapter
    public abstract void showAd(Activity activity);
}
