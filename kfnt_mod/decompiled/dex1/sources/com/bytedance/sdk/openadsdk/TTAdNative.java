package com.bytedance.sdk.openadsdk;

import com.bytedance.sdk.openadsdk.common.CommonListener;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface TTAdNative {

    public interface BannerAdListener extends CommonListener {
        void onBannerAdLoad(TTBannerAd tTBannerAd);

        @Override // com.bytedance.sdk.openadsdk.common.CommonListener
        void onError(int i, String str);
    }

    public interface DrawFeedAdListener extends CommonListener {
        void onDrawFeedAdLoad(List<TTDrawFeedAd> list);

        @Override // com.bytedance.sdk.openadsdk.common.CommonListener
        void onError(int i, String str);
    }

    public interface FeedAdListener extends CommonListener {
        @Override // com.bytedance.sdk.openadsdk.common.CommonListener
        void onError(int i, String str);

        void onFeedAdLoad(List<TTFeedAd> list);
    }

    public interface FullScreenVideoAdListener extends CommonListener {
        @Override // com.bytedance.sdk.openadsdk.common.CommonListener
        void onError(int i, String str);

        void onFullScreenVideoAdLoad(TTFullScreenVideoAd tTFullScreenVideoAd);

        @Deprecated
        void onFullScreenVideoCached();

        void onFullScreenVideoCached(TTFullScreenVideoAd tTFullScreenVideoAd);
    }

    public interface InteractionAdListener extends CommonListener {
        @Override // com.bytedance.sdk.openadsdk.common.CommonListener
        void onError(int i, String str);

        void onInteractionAdLoad(TTInteractionAd tTInteractionAd);
    }

    public interface NativeAdListener extends CommonListener {
        @Override // com.bytedance.sdk.openadsdk.common.CommonListener
        void onError(int i, String str);

        void onNativeAdLoad(List<TTNativeAd> list);
    }

    public interface NativeExpressAdListener extends CommonListener {
        @Override // com.bytedance.sdk.openadsdk.common.CommonListener
        void onError(int i, String str);

        void onNativeExpressAdLoad(List<TTNativeExpressAd> list);
    }

    public interface RewardVideoAdListener extends CommonListener {
        @Override // com.bytedance.sdk.openadsdk.common.CommonListener
        void onError(int i, String str);

        void onRewardVideoAdLoad(TTRewardVideoAd tTRewardVideoAd);

        @Deprecated
        void onRewardVideoCached();

        void onRewardVideoCached(TTRewardVideoAd tTRewardVideoAd);
    }

    public interface SplashAdListener extends CommonListener {
        @Override // com.bytedance.sdk.openadsdk.common.CommonListener
        void onError(int i, String str);

        void onSplashAdLoad(TTSplashAd tTSplashAd);

        void onTimeout();
    }

    void loadBannerAd(AdSlot adSlot, BannerAdListener bannerAdListener);

    void loadBannerExpressAd(AdSlot adSlot, NativeExpressAdListener nativeExpressAdListener);

    void loadDrawFeedAd(AdSlot adSlot, DrawFeedAdListener drawFeedAdListener);

    void loadExpressDrawFeedAd(AdSlot adSlot, NativeExpressAdListener nativeExpressAdListener);

    void loadFeedAd(AdSlot adSlot, FeedAdListener feedAdListener);

    void loadFullScreenVideoAd(AdSlot adSlot, FullScreenVideoAdListener fullScreenVideoAdListener);

    void loadInteractionAd(AdSlot adSlot, InteractionAdListener interactionAdListener);

    void loadInteractionExpressAd(AdSlot adSlot, NativeExpressAdListener nativeExpressAdListener);

    void loadNativeAd(AdSlot adSlot, NativeAdListener nativeAdListener);

    void loadNativeExpressAd(AdSlot adSlot, NativeExpressAdListener nativeExpressAdListener);

    void loadRewardVideoAd(AdSlot adSlot, RewardVideoAdListener rewardVideoAdListener);

    void loadSplashAd(AdSlot adSlot, SplashAdListener splashAdListener);

    void loadSplashAd(AdSlot adSlot, SplashAdListener splashAdListener, int i);

    void loadStream(AdSlot adSlot, FeedAdListener feedAdListener);
}
