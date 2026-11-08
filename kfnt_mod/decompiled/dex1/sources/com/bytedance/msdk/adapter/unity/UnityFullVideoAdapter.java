package com.bytedance.msdk.adapter.unity;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.listener.ITTAdapterFullVideoAdListener;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.TTAdConstant;
import com.bytedance.msdk.base.TTBaseAd;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class UnityFullVideoAdapter extends TTAbsAdLoaderAdapter {

    /* renamed from: a, reason: collision with root package name */
    private String f4225a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f4226b;

    class UnityFullVideo extends TTBaseAd implements IUnityAdsListener {

        /* renamed from: a, reason: collision with root package name */
        private boolean f4227a = false;

        UnityFullVideo(ITTAdapterFullVideoAdListener iTTAdapterFullVideoAdListener) {
            this.mTTAdatperCallback = iTTAdapterFullVideoAdListener;
        }

        private ITTAdapterFullVideoAdListener a() {
            return (ITTAdapterFullVideoAdListener) this.mTTAdatperCallback;
        }

        private boolean a(String str) {
            return !TextUtils.isEmpty(str) && str.equalsIgnoreCase(UnityFullVideoAdapter.this.getAdSlotId());
        }

        void a(Activity activity, String str) {
            UnityAds.setDebugMode(Logger.isDebug());
            UnityAds.setListener(this);
            UnityAds.initialize(activity, str, false);
            if (isReady()) {
                onUnityAdsReady(UnityFullVideoAdapter.this.getAdSlotId());
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return ((TTAbsAdLoaderAdapter) UnityFullVideoAdapter.this).mAdSlot.getAdType();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return this.f4227a;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isAdCacheReady() {
            return isReady();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isReady() {
            return UnityAds.isReady(UnityFullVideoAdapter.this.getAdSlotId());
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            super.onDestroy();
            this.f4227a = true;
            UnityAds.removeListener(this);
        }

        public void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String str) {
            if (UnityFullVideoAdapter.this.f4226b) {
                return;
            }
            UnityFullVideoAdapter.this.f4226b = true;
            UnityFullVideoAdapter.this.notifyAdFailed(UnityAdapterUtil.errorMsg(unityAdsError, str));
        }

        public void onUnityAdsFinish(String str, UnityAds.FinishState finishState) {
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(UnityFullVideoAdapter.this.getAdapterRit(), UnityFullVideoAdapter.this.getAdSlotId()) + "Unity FullVideo Ad onUnityAdsFinish, placementId=" + str + IWeiboService.Scope.EMPTY_SCOPE + finishState.name());
            if (a(str)) {
                if (finishState == UnityAds.FinishState.COMPLETED) {
                    if (!(this.mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener)) {
                        return;
                    } else {
                        a().onVideoComplete();
                    }
                } else {
                    if (finishState != UnityAds.FinishState.SKIPPED) {
                        if (finishState == UnityAds.FinishState.ERROR) {
                            if (this.mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener) {
                                a().onVideoError();
                            }
                            Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelById(UnityFullVideoAdapter.this.getAdapterRit(), UnityFullVideoAdapter.this.getAdSlotId()) + "Unity FullVideo Ad FinishState.ERRO, placementId=" + str + IWeiboService.Scope.EMPTY_SCOPE + finishState.name());
                            return;
                        }
                        return;
                    }
                    if (!(this.mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener)) {
                        return;
                    } else {
                        a().onSkippedVideo();
                    }
                }
                a().onFullVideoAdClosed();
            }
        }

        public void onUnityAdsReady(String str) {
            if (!a(str) || UnityFullVideoAdapter.this.f4226b) {
                return;
            }
            UnityFullVideoAdapter.this.f4226b = true;
            UnityFullVideoAdapter.this.notifyAdLoaded(this);
            UnityFullVideoAdapter.this.notifyAdVideoCache(this, new AdError(AdError.ERROR_CODE_THIRD_SDK_NO_CACHE_CALLBACK, AdError.AD_THIRD_SDK_NO_CACHE_CALLBACK_MSG));
        }

        public void onUnityAdsStart(String str) {
            Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelById(UnityFullVideoAdapter.this.getAdapterRit(), UnityFullVideoAdapter.this.getAdSlotId()) + "Unity FullVideo Ad onUnityAdsStart, placementId=" + str);
            if (this.mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener) {
                a().onFullVideoAdShow();
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void showAd(Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map) {
            if (isReady()) {
                UnityAds.show(activity, UnityFullVideoAdapter.this.getAdSlotId());
            }
        }
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void destroy() {
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getAdNetWorkName() {
        return "unity";
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getSdkVersion() {
        return UnityAds.getVersion();
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void loadAd(Context context, Map<String, Object> map) {
        if (map != null) {
            if (map.containsKey("tt_ad_network_config_appid")) {
                this.f4225a = (String) map.get("tt_ad_network_config_appid");
            }
            Object obj = map.get("tt_ad_network_callback");
            ITTAdapterFullVideoAdListener iTTAdapterFullVideoAdListener = obj instanceof ITTAdapterFullVideoAdListener ? (ITTAdapterFullVideoAdListener) obj : null;
            if (TextUtils.isEmpty(this.f4225a)) {
                if (iTTAdapterFullVideoAdListener != null) {
                    notifyAdFailed(new AdError(AdError.ERROR_CODE_MEDIA_ID, "Unity AppId can not be null ", AdError.ERROR_CODE_MEDIA_ID, "Unity AppId can not be null "));
                }
                Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelById(getAdapterRit(), getAdSlotId()) + "Unity FullVideo Ad Error ,Unity AppId can not be null  ");
            }
            new UnityFullVideo(iTTAdapterFullVideoAdListener).a((Activity) context, this.f4225a);
        }
    }
}
