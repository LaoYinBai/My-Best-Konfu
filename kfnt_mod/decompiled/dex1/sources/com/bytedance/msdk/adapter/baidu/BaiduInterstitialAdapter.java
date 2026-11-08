package com.bytedance.msdk.adapter.baidu;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.baidu.mobads.sdk.api.AdSettings;
import com.baidu.mobads.sdk.api.InterstitialAd;
import com.baidu.mobads.sdk.api.InterstitialAdListener;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.listener.ITTAdapterInterstitialListener;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdErrorUtil;
import com.bytedance.msdk.api.BaiduExtraOptions;
import com.bytedance.msdk.api.TTAdConstant;
import com.bytedance.msdk.api.TTVideoOption;
import com.bytedance.msdk.base.TTBaseAd;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class BaiduInterstitialAdapter extends TTAbsAdLoaderAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f3937a;

    class BaiduInterstitialAd extends TTBaseAd implements InterstitialAdListener {

        /* renamed from: a, reason: collision with root package name */
        private InterstitialAd f3938a;

        BaiduInterstitialAd(ITTAdapterInterstitialListener iTTAdapterInterstitialListener) {
            this.mTTAdatperCallback = iTTAdapterInterstitialListener;
        }

        private ITTAdapterInterstitialListener b() {
            return (ITTAdapterInterstitialListener) this.mTTAdatperCallback;
        }

        void a() {
            this.f3938a = new InterstitialAd(BaiduInterstitialAdapter.this.f3937a, BaiduInterstitialAdapter.this.getAdSlotId());
            TTVideoOption tTVideoOption = ((TTAbsAdLoaderAdapter) BaiduInterstitialAdapter.this).mAdSlot.getTTVideoOption();
            if (tTVideoOption != null) {
                BaiduExtraOptions baiduExtraOption = tTVideoOption.getBaiduExtraOption();
                String appSid = baiduExtraOption != null ? baiduExtraOption.getAppSid() : null;
                if (!TextUtils.isEmpty(appSid)) {
                    this.f3938a.setAppSid(appSid);
                }
            }
            Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduInterstitialAdapter.this.getAdapterRit(), BaiduInterstitialAdapter.this.getAdSlotId()) + "Baidu interstitial ad load 1 ...");
            this.f3938a.setListener(this);
            Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduInterstitialAdapter.this.getAdapterRit(), BaiduInterstitialAdapter.this.getAdSlotId()) + "Baidu interstitial ad load 2 ...");
            this.f3938a.loadAd();
            Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduInterstitialAdapter.this.getAdapterRit(), BaiduInterstitialAdapter.this.getAdSlotId()) + "Baidu interstitial ad load 3 ...");
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return ((TTAbsAdLoaderAdapter) BaiduInterstitialAdapter.this).mAdSlot.getAdType();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return this.f3938a == null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isAdCacheReady() {
            return isReady();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isReady() {
            InterstitialAd interstitialAd = this.f3938a;
            return interstitialAd != null && interstitialAd.isAdReady();
        }

        @Override // com.baidu.mobads.sdk.api.InterstitialAdListener
        public void onAdClick(InterstitialAd interstitialAd) {
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduInterstitialAdapter.this.getAdapterRit(), BaiduInterstitialAdapter.this.getAdSlotId()) + "Baidu interstitial ad  onAdClick ...");
            if (this.mTTAdatperCallback instanceof ITTAdapterInterstitialListener) {
                b().onInterstitialAdClick();
            }
        }

        @Override // com.baidu.mobads.sdk.api.InterstitialAdListener
        public void onAdDismissed() {
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduInterstitialAdapter.this.getAdapterRit(), BaiduInterstitialAdapter.this.getAdSlotId()) + "Baidu interstitial ad  close ...");
            if (this.mTTAdatperCallback instanceof ITTAdapterInterstitialListener) {
                b().onInterstitialClosed();
            }
        }

        @Override // com.baidu.mobads.sdk.api.InterstitialAdListener
        public void onAdFailed(String str) {
            Logger.d("TTMediationSDK", "Baidu Interstitial onAdFailed " + str);
            BaiduInterstitialAdapter.this.notifyAdFailed(AdErrorUtil.obtainAdError(AdError.ERROR_CODE_THIRD_SDK_LOAD_FAIL, str));
        }

        @Override // com.baidu.mobads.sdk.api.InterstitialAdListener
        public void onAdPresent() {
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduInterstitialAdapter.this.getAdapterRit(), BaiduInterstitialAdapter.this.getAdSlotId()) + "Baidu interstitial ad onAdPresent --> show ...");
            if (this.mTTAdatperCallback instanceof ITTAdapterInterstitialListener) {
                b().onInterstitialShow();
            }
        }

        @Override // com.baidu.mobads.sdk.api.InterstitialAdListener
        public void onAdReady() {
            Logger.d("TTMediationSDK", "Baidu Interstitial onAdReady --> load success");
            BaiduInterstitialAdapter.this.notifyAdLoaded(this);
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            InterstitialAd interstitialAd = this.f3938a;
            if (interstitialAd != null) {
                interstitialAd.destroy();
                this.f3938a = null;
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void showAd(Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map) {
            if (isReady()) {
                this.f3938a.showAd();
            }
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
        this.f3937a = context;
        if (map != null) {
            Object obj = map.get("tt_ad_network_callback");
            new BaiduInterstitialAd(obj instanceof ITTAdapterInterstitialListener ? (ITTAdapterInterstitialListener) obj : null).a();
        }
    }
}
