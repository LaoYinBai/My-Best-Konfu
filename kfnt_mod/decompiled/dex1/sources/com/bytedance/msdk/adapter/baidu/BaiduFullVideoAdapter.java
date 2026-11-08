package com.bytedance.msdk.adapter.baidu;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.baidu.mobads.sdk.api.AdSettings;
import com.baidu.mobads.sdk.api.FullScreenVideoAd;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.listener.ITTAdapterFullVideoAdListener;
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
public class BaiduFullVideoAdapter extends TTAbsAdLoaderAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f3933a;

    /* renamed from: b, reason: collision with root package name */
    private TTVideoOption f3934b;

    class BaiduFullVideoAd extends TTBaseAd implements FullScreenVideoAd.FullScreenVideoAdListener {

        /* renamed from: a, reason: collision with root package name */
        private FullScreenVideoAd f3935a;

        BaiduFullVideoAd(ITTAdapterFullVideoAdListener iTTAdapterFullVideoAdListener) {
            this.mTTAdatperCallback = iTTAdapterFullVideoAdListener;
        }

        private ITTAdapterFullVideoAdListener b() {
            return (ITTAdapterFullVideoAdListener) this.mTTAdatperCallback;
        }

        void a() {
            this.f3935a = new FullScreenVideoAd(BaiduFullVideoAdapter.this.f3933a, BaiduFullVideoAdapter.this.getAdSlotId(), this);
            if (BaiduFullVideoAdapter.this.f3934b != null) {
                BaiduExtraOptions baiduExtraOption = BaiduFullVideoAdapter.this.f3934b.getBaiduExtraOption();
                String appSid = baiduExtraOption != null ? baiduExtraOption.getAppSid() : null;
                if (!TextUtils.isEmpty(appSid)) {
                    this.f3935a.setAppSid(appSid);
                }
            }
            this.f3935a.load();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean adnHasAdExpiredApi() {
            return true;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return ((TTAbsAdLoaderAdapter) BaiduFullVideoAdapter.this).mAdSlot.getAdType();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return this.f3935a == null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isAdExpired() {
            return !isReady();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isReady() {
            FullScreenVideoAd fullScreenVideoAd = this.f3935a;
            return fullScreenVideoAd != null && fullScreenVideoAd.isReady();
        }

        @Override // com.baidu.mobads.sdk.api.FullScreenVideoAd.FullScreenVideoAdListener, com.baidu.mobads.sdk.api.ScreenVideoAdListener
        public void onAdClick() {
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduFullVideoAdapter.this.getAdapterRit(), BaiduFullVideoAdapter.this.getAdSlotId()) + "Baidu fullVideo ad onAdClick ...");
            if (this.mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener) {
                b().onFullVideoAdClick();
            }
        }

        @Override // com.baidu.mobads.sdk.api.FullScreenVideoAd.FullScreenVideoAdListener, com.baidu.mobads.sdk.api.ScreenVideoAdListener
        public void onAdClose(float f) {
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduFullVideoAdapter.this.getAdapterRit(), BaiduFullVideoAdapter.this.getAdSlotId()) + "Baidu fullVideo ad onAdClose ...");
            if (this.mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener) {
                b().onFullVideoAdClosed();
            }
        }

        @Override // com.baidu.mobads.sdk.api.FullScreenVideoAd.FullScreenVideoAdListener, com.baidu.mobads.sdk.api.ScreenVideoAdListener
        public void onAdFailed(String str) {
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduFullVideoAdapter.this.getAdapterRit(), BaiduFullVideoAdapter.this.getAdSlotId()) + "Baidu fullVideo load fail: " + str);
            BaiduFullVideoAdapter.this.notifyAdFailed(AdErrorUtil.obtainAdError(AdError.ERROR_CODE_THIRD_SDK_LOAD_FAIL, str));
        }

        @Override // com.baidu.mobads.sdk.api.ScreenVideoAdListener
        public void onAdLoaded() {
            StringBuilder sb;
            String str;
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduFullVideoAdapter.this.getAdapterRit(), BaiduFullVideoAdapter.this.getAdSlotId()) + "Baidu fullVideo load success");
            if (this.f3935a != null) {
                if (BaiduFullVideoAdapter.this.isClientBidding()) {
                    double dDoubleValue = 0.0d;
                    try {
                        dDoubleValue = Double.valueOf(this.f3935a.getECPMLevel()).doubleValue();
                    } catch (Exception unused) {
                    }
                    setCpm(dDoubleValue);
                    sb = new StringBuilder();
                    sb.append(TTLogUtil.getTagThirdLevelById(BaiduFullVideoAdapter.this.getAdapterRit(), BaiduFullVideoAdapter.this.getAdSlotId()));
                    str = "Baidu_cientBidding full 返回的 cpm价格：";
                } else if (BaiduFullVideoAdapter.this.isMultiBidding()) {
                    setLevelTag(this.f3935a.getECPMLevel());
                    sb = new StringBuilder();
                    sb.append(TTLogUtil.getTagThirdLevelById(BaiduFullVideoAdapter.this.getAdapterRit(), BaiduFullVideoAdapter.this.getAdSlotId()));
                    str = "Baidu_多阶底价 full 返回的 价格标签：";
                }
                sb.append(str);
                sb.append(this.f3935a.getECPMLevel());
                Logger.d("TTMediationSDK_ECMP", sb.toString());
            }
            BaiduFullVideoAdapter.this.notifyAdLoaded(this);
        }

        @Override // com.baidu.mobads.sdk.api.FullScreenVideoAd.FullScreenVideoAdListener, com.baidu.mobads.sdk.api.ScreenVideoAdListener
        public void onAdShow() {
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduFullVideoAdapter.this.getAdapterRit(), BaiduFullVideoAdapter.this.getAdSlotId()) + "Baidu fullVideo ad onAdShow ...");
            if (this.mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener) {
                b().onFullVideoAdShow();
            }
        }

        @Override // com.baidu.mobads.sdk.api.FullScreenVideoAd.FullScreenVideoAdListener, com.baidu.mobads.sdk.api.ScreenVideoAdListener
        public void onAdSkip(float f) {
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduFullVideoAdapter.this.getAdapterRit(), BaiduFullVideoAdapter.this.getAdSlotId()) + "Baidu fullVideo ad onAdSkip ...");
            if (this.mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener) {
                b().onSkippedVideo();
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
        }

        @Override // com.baidu.mobads.sdk.api.FullScreenVideoAd.FullScreenVideoAdListener, com.baidu.mobads.sdk.api.ScreenVideoAdListener
        public void onVideoDownloadFailed() {
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduFullVideoAdapter.this.getAdapterRit(), BaiduFullVideoAdapter.this.getAdSlotId()) + "Baidu fullVideo cache fail");
        }

        @Override // com.baidu.mobads.sdk.api.FullScreenVideoAd.FullScreenVideoAdListener, com.baidu.mobads.sdk.api.ScreenVideoAdListener
        public void onVideoDownloadSuccess() {
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduFullVideoAdapter.this.getAdapterRit(), BaiduFullVideoAdapter.this.getAdSlotId()) + "Baidu fullVideo cache success");
            BaiduFullVideoAdapter.this.notifyAdVideoCache(this, null);
        }

        @Override // com.baidu.mobads.sdk.api.FullScreenVideoAd.FullScreenVideoAdListener, com.baidu.mobads.sdk.api.ScreenVideoAdListener
        public void playCompletion() {
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduFullVideoAdapter.this.getAdapterRit(), BaiduFullVideoAdapter.this.getAdSlotId()) + "Baidu fullVideo play complete ...");
            if (this.mTTAdatperCallback instanceof ITTAdapterFullVideoAdListener) {
                b().onVideoComplete();
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void showAd(Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map) {
            if (isReady()) {
                this.f3935a.show();
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
        this.f3933a = context;
        this.f3934b = this.mAdSlot.getTTVideoOption();
        if (map != null) {
            Object obj = map.get("tt_ad_network_callback");
            new BaiduFullVideoAd(obj instanceof ITTAdapterFullVideoAdListener ? (ITTAdapterFullVideoAdListener) obj : null).a();
        }
    }
}
