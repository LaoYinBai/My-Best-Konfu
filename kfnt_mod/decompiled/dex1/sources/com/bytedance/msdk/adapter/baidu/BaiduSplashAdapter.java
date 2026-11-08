package com.bytedance.msdk.adapter.baidu;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.baidu.mobads.sdk.api.AdSettings;
import com.baidu.mobads.sdk.api.RequestParameters;
import com.baidu.mobads.sdk.api.SplashAd;
import com.baidu.mobads.sdk.api.SplashInteractionListener;
import com.bytedance.msdk.adapter.SplashDismissController;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.listener.ITTAdapterSplashAdListener;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdErrorUtil;
import com.bytedance.msdk.api.BaiduExtraOptions;
import com.bytedance.msdk.api.BaiduRequestParameters;
import com.bytedance.msdk.api.BaiduSplashParams;
import com.bytedance.msdk.api.TTRequestExtraParams;
import com.bytedance.msdk.api.TTVideoOption;
import com.bytedance.msdk.base.TTBaseAd;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class BaiduSplashAdapter extends TTAbsAdLoaderAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f3958a;

    /* renamed from: b, reason: collision with root package name */
    private TTVideoOption f3959b;

    /* renamed from: c, reason: collision with root package name */
    private BaiduRequestParameters f3960c;
    private BaiduSplashParams d;
    private SplashDismissController f;
    private int e = 3000;
    private boolean g = true;

    class BaiduSplashAd extends TTBaseAd implements SplashInteractionListener {

        /* renamed from: a, reason: collision with root package name */
        private SplashAd f3961a;

        /* renamed from: b, reason: collision with root package name */
        FrameLayout f3962b;

        BaiduSplashAd(ITTAdapterSplashAdListener iTTAdapterSplashAdListener) {
            this.mTTAdatperCallback = iTTAdapterSplashAdListener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ITTAdapterSplashAdListener a() {
            return (ITTAdapterSplashAdListener) this.mTTAdatperCallback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b() {
            this.f3962b = new FrameLayout(BaiduSplashAdapter.this.f3958a);
            RequestParameters requestParametersBuild = new RequestParameters.Builder().addExtra("timeout", String.valueOf(BaiduSplashAdapter.this.e)).build();
            if (BaiduSplashAdapter.this.f3960c != null) {
                RequestParameters.Builder builderDownloadAppConfirmPolicy = new RequestParameters.Builder().setHeight(BaiduSplashAdapter.this.f3960c.getHeight()).setWidth(BaiduSplashAdapter.this.f3960c.getWidth()).downloadAppConfirmPolicy(BaiduSplashAdapter.this.f3960c.getAPPConfirmPolicy());
                HashMap<String, Object> hashMap = BaiduSplashAdapter.this.f3960c.toHashMap();
                if (!hashMap.containsKey("timeout")) {
                    hashMap.put("timeout", String.valueOf(BaiduSplashAdapter.this.e));
                }
                if (hashMap != null) {
                    for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
                        try {
                            builderDownloadAppConfirmPolicy.addExtra(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
                        } catch (Throwable unused) {
                        }
                    }
                }
                requestParametersBuild = builderDownloadAppConfirmPolicy.build();
            }
            BaiduSplashParams unused2 = BaiduSplashAdapter.this.d;
            this.f3961a = new SplashAd(BaiduSplashAdapter.this.f3958a, BaiduSplashAdapter.this.getAdSlotId(), requestParametersBuild, this);
            if (BaiduSplashAdapter.this.f3959b != null) {
                BaiduExtraOptions baiduExtraOption = BaiduSplashAdapter.this.f3959b.getBaiduExtraOption();
                String appSid = baiduExtraOption != null ? baiduExtraOption.getAppSid() : null;
                if (!TextUtils.isEmpty(appSid)) {
                    this.f3961a.setAppSid(appSid);
                }
            }
            this.f3961a.load();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return ((TTAbsAdLoaderAdapter) BaiduSplashAdapter.this).mAdSlot.getAdType();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return this.f3961a == null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isReady() {
            return BaiduSplashAdapter.this.g;
        }

        @Override // com.baidu.mobads.sdk.api.SplashAdListener
        public void onADLoaded() {
            StringBuilder sb;
            String str;
            if (this.f3961a != null) {
                if (BaiduSplashAdapter.this.isClientBidding()) {
                    double dDoubleValue = 0.0d;
                    try {
                        dDoubleValue = Double.valueOf(this.f3961a.getECPMLevel()).doubleValue();
                    } catch (Exception unused) {
                    }
                    setCpm(dDoubleValue);
                    sb = new StringBuilder();
                    sb.append(TTLogUtil.getTagThirdLevelById(BaiduSplashAdapter.this.getAdapterRit(), BaiduSplashAdapter.this.getAdSlotId()));
                    str = "Baidu_cientBidding splash 返回的 cpm价格：";
                } else if (BaiduSplashAdapter.this.isMultiBidding()) {
                    setLevelTag(this.f3961a.getECPMLevel());
                    sb = new StringBuilder();
                    sb.append(TTLogUtil.getTagThirdLevelById(BaiduSplashAdapter.this.getAdapterRit(), BaiduSplashAdapter.this.getAdSlotId()));
                    str = "Baidu_多阶底价 splash 返回的 价格标签：";
                }
                sb.append(str);
                sb.append(this.f3961a.getECPMLevel());
                Logger.d("TTMediationSDK_ECMP", sb.toString());
            }
            BaiduSplashAdapter.this.notifyAdLoaded(this);
        }

        @Override // com.baidu.mobads.sdk.api.SplashInteractionListener
        public void onAdCacheFailed() {
            BaiduSplashAdapter.this.g = false;
            Logger.e("TTMediationSDK", "baidu_splash_onAdCacheFailed");
        }

        @Override // com.baidu.mobads.sdk.api.SplashInteractionListener
        public void onAdCacheSuccess() {
            BaiduSplashAdapter.this.g = true;
            Logger.e("TTMediationSDK", "baidu_splash_onAdCacheSuccess");
        }

        @Override // com.baidu.mobads.sdk.api.SplashInteractionListener
        public void onAdClick() {
            if (this.mTTAdatperCallback instanceof ITTAdapterSplashAdListener) {
                if (BaiduSplashAdapter.this.f != null) {
                    BaiduSplashAdapter.this.f.setClick(true);
                    BaiduSplashAdapter.this.f.setCallBack(new SplashDismissController.CallBack() { // from class: com.bytedance.msdk.adapter.baidu.BaiduSplashAdapter.BaiduSplashAd.1
                        @Override // com.bytedance.msdk.adapter.SplashDismissController.CallBack
                        public void onResume() {
                            if (BaiduSplashAdapter.this.f == null || !BaiduSplashAdapter.this.f.jumpToAdPage() || BaiduSplashAdapter.this.f.isCallDismiss() || !(((TTBaseAd) BaiduSplashAd.this).mTTAdatperCallback instanceof ITTAdapterSplashAdListener)) {
                                return;
                            }
                            BaiduSplashAd.this.a().onAdDismiss();
                        }
                    });
                }
                a().onAdClicked();
            }
        }

        @Override // com.baidu.mobads.sdk.api.SplashInteractionListener
        public void onAdDismissed() {
            if (this.mTTAdatperCallback instanceof ITTAdapterSplashAdListener) {
                if (BaiduSplashAdapter.this.f == null || !BaiduSplashAdapter.this.f.jumpToAdPage()) {
                    if (BaiduSplashAdapter.this.f != null) {
                        BaiduSplashAdapter.this.f.setCallDismiss(true);
                    }
                    a().onAdDismiss();
                }
            }
        }

        @Override // com.baidu.mobads.sdk.api.SplashAdListener
        public void onAdFailed(String str) {
            BaiduSplashAdapter.this.notifyAdFailed(AdErrorUtil.obtainAdError(AdError.ERROR_CODE_THIRD_SDK_LOAD_FAIL, str));
        }

        @Override // com.baidu.mobads.sdk.api.SplashInteractionListener
        public void onAdPresent() {
            if (this.mTTAdatperCallback instanceof ITTAdapterSplashAdListener) {
                a().onAdShow();
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            SplashAd splashAd = this.f3961a;
            if (splashAd != null) {
                splashAd.destroy();
                this.f3961a = null;
            }
            FrameLayout frameLayout = this.f3962b;
            if (frameLayout != null) {
                frameLayout.removeAllViews();
            }
            BaiduSplashAdapter.this.f = null;
        }

        @Override // com.baidu.mobads.sdk.api.SplashInteractionListener
        public void onLpClosed() {
            if (this.mTTAdatperCallback instanceof ITTAdapterSplashAdListener) {
                a().onAdDismiss();
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void showSplashAd(ViewGroup viewGroup) {
            if (this.f3961a == null || viewGroup == null) {
                return;
            }
            viewGroup.removeAllViews();
            this.f3962b.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            this.f3962b.setVisibility(0);
            viewGroup.addView(this.f3962b);
            this.f3961a.show(this.f3962b);
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
        this.f3958a = context;
        if (map != null) {
            this.f3959b = this.mAdSlot.getTTVideoOption();
            Context context2 = this.f3958a;
            if (context2 instanceof Activity) {
                this.f = new SplashDismissController((Activity) context2);
            }
            TTVideoOption tTVideoOption = this.f3959b;
            if (tTVideoOption != null && tTVideoOption.getBaiduExtraOption() != null) {
                BaiduExtraOptions baiduExtraOption = this.f3959b.getBaiduExtraOption();
                this.f3960c = baiduExtraOption.getBaiduRequestParameters();
                this.d = baiduExtraOption.getBaiduSplashParams();
            }
            this.e = (!map.containsKey(TTRequestExtraParams.PARAM_AD_LOAD_TIMEOUT) || map.get(TTRequestExtraParams.PARAM_AD_LOAD_TIMEOUT) == null) ? this.e : ((Integer) map.get(TTRequestExtraParams.PARAM_AD_LOAD_TIMEOUT)).intValue();
            Object obj = map.get("tt_ad_network_callback");
            new BaiduSplashAd(obj instanceof ITTAdapterSplashAdListener ? (ITTAdapterSplashAdListener) obj : null).b();
        }
    }
}
