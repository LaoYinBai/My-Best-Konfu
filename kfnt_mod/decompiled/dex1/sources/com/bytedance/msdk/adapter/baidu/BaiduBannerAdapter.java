package com.bytedance.msdk.adapter.baidu;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.baidu.mobads.sdk.api.AdSettings;
import com.baidu.mobads.sdk.api.AdSize;
import com.baidu.mobads.sdk.api.AdView;
import com.baidu.mobads.sdk.api.AdViewListener;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.baidu.VisibleStateFrameLayout;
import com.bytedance.msdk.adapter.listener.ITTAdapterBannerAdListener;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdErrorUtil;
import com.bytedance.msdk.api.BaiduExtraOptions;
import com.bytedance.msdk.api.TTVideoOption;
import com.bytedance.msdk.base.TTBaseAd;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class BaiduBannerAdapter extends TTAbsAdLoaderAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f3926a;

    class BaiduBannerAd extends TTBaseAd implements AdViewListener {

        /* renamed from: a, reason: collision with root package name */
        private AdView f3927a;

        /* renamed from: b, reason: collision with root package name */
        private boolean f3928b;

        /* renamed from: c, reason: collision with root package name */
        private boolean f3929c;
        private boolean d;
        private VisibleStateFrameLayout e;

        BaiduBannerAd() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ITTAdapterBannerAdListener b() {
            return (ITTAdapterBannerAdListener) this.mTTAdatperCallback;
        }

        void a() {
            BaiduBannerAdapter baiduBannerAdapter;
            String str;
            if (BaiduBannerAdapter.this.f3926a instanceof Activity) {
                this.f3927a = new AdView(BaiduBannerAdapter.this.f3926a, null, false, AdSize.Banner, BaiduBannerAdapter.this.getAdSlotId());
                TTVideoOption tTVideoOption = ((TTAbsAdLoaderAdapter) BaiduBannerAdapter.this).mAdSlot.getTTVideoOption();
                if (tTVideoOption != null) {
                    BaiduExtraOptions baiduExtraOption = tTVideoOption.getBaiduExtraOption();
                    String appSid = baiduExtraOption != null ? baiduExtraOption.getAppSid() : null;
                    if (!TextUtils.isEmpty(appSid)) {
                        this.f3927a.setAppSid(appSid);
                    }
                }
                this.f3927a.setListener(this);
                Activity activity = (Activity) BaiduBannerAdapter.this.f3926a;
                if (activity != null && activity.getWindow() != null && (activity.getWindow().getDecorView() instanceof ViewGroup)) {
                    this.e = new VisibleStateFrameLayout(BaiduBannerAdapter.this.f3926a);
                    this.e.addView(this.f3927a, new FrameLayout.LayoutParams(-1, -1));
                    this.e.setVisibility(4);
                    final ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
                    viewGroup.addView(this.e);
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.bytedance.msdk.adapter.baidu.BaiduBannerAdapter.BaiduBannerAd.1
                        @Override // java.lang.Runnable
                        public void run() {
                            viewGroup.removeView(BaiduBannerAd.this.e);
                        }
                    }, 100L);
                    return;
                }
                baiduBannerAdapter = BaiduBannerAdapter.this;
                str = "上下文为null，无法加载百度Banner";
            } else {
                baiduBannerAdapter = BaiduBannerAdapter.this;
                str = "Context必须为Activity，其他类型不支持百度Banner广告";
            }
            baiduBannerAdapter.notifyAdFailed(AdErrorUtil.obtainAdError(AdError.ERROR_CODE_THIRD_SDK_LOAD_FAIL, str));
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return ((TTAbsAdLoaderAdapter) BaiduBannerAdapter.this).mAdSlot.getAdType();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public View getAdView() {
            if (this.d) {
                return null;
            }
            if (this.f3928b) {
                this.e.setVisibility(0);
                BaiduBannerAdapter.this.removeFromParent(this.e);
                this.e.setVisibilityChanged(new VisibleStateFrameLayout.VisibilityChanged() { // from class: com.bytedance.msdk.adapter.baidu.BaiduBannerAdapter.BaiduBannerAd.2
                    @Override // com.bytedance.msdk.adapter.baidu.VisibleStateFrameLayout.VisibilityChanged
                    public void callback(boolean z) {
                        if (z) {
                            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduBannerAdapter.this.getAdapterRit(), BaiduBannerAdapter.this.getAdSlotId()) + "Baidu banner ad real show .......");
                            if (BaiduBannerAd.this.b() != null) {
                                BaiduBannerAd.this.b().onAdShow();
                            }
                        }
                    }
                });
                return this.e;
            }
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduBannerAdapter.this.getAdapterRit(), BaiduBannerAdapter.this.getAdSlotId()) + "Baidu banner ad getView is null!! ...");
            return null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return this.f3927a == null;
        }

        @Override // com.baidu.mobads.sdk.api.AdViewListener
        public void onAdClick(JSONObject jSONObject) {
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduBannerAdapter.this.getAdapterRit(), BaiduBannerAdapter.this.getAdSlotId()) + "Baidu banner ad click .......");
            if (b() != null) {
                b().onAdClicked();
            }
        }

        @Override // com.baidu.mobads.sdk.api.AdViewListener
        public void onAdClose(JSONObject jSONObject) {
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduBannerAdapter.this.getAdapterRit(), BaiduBannerAdapter.this.getAdSlotId()) + "Baidu banner ad close .......");
            if (b() != null) {
                b().onAdClosed();
            }
        }

        @Override // com.baidu.mobads.sdk.api.AdViewListener
        public void onAdFailed(String str) {
            Logger.d("TTMediationSDK", "Baidu banner onAdFailed " + str);
            this.d = true;
            if (this.f3929c) {
                return;
            }
            this.f3929c = true;
            BaiduBannerAdapter.this.notifyAdFailed(AdErrorUtil.obtainAdError(AdError.ERROR_CODE_THIRD_SDK_LOAD_FAIL, str));
        }

        @Override // com.baidu.mobads.sdk.api.AdViewListener
        public void onAdReady(AdView adView) {
            Logger.d("TTMediationSDK", "Baidu banner onAdReady ......");
            this.f3928b = true;
            if (this.f3929c) {
                return;
            }
            this.f3929c = true;
            BaiduBannerAdapter.this.notifyAdLoaded(this);
        }

        @Override // com.baidu.mobads.sdk.api.AdViewListener
        public void onAdShow(JSONObject jSONObject) {
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduBannerAdapter.this.getAdapterRit(), BaiduBannerAdapter.this.getAdSlotId()) + "Baidu banner ad show .......");
        }

        @Override // com.baidu.mobads.sdk.api.AdViewListener
        public void onAdSwitch() {
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(BaiduBannerAdapter.this.getAdapterRit(), BaiduBannerAdapter.this.getAdSlotId()) + "Baidu banner ad switch .......");
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            AdView adView = this.f3927a;
            if (adView != null) {
                adView.destroy();
                this.f3927a = null;
            }
            BaiduBannerAdapter.this.f3926a = null;
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
        this.f3926a = context;
        if (map != null) {
            new BaiduBannerAd().a();
        }
    }
}
