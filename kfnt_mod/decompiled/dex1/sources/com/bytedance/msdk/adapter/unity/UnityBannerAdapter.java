package com.bytedance.msdk.adapter.unity;

import android.app.Activity;
import android.content.Context;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.listener.ITTAdapterBannerAdListener;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.adapter.util.WeakHandler;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdErrorUtil;
import com.bytedance.msdk.base.TTBaseAd;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.BannerErrorCode;
import com.unity3d.services.banners.BannerErrorInfo;
import com.unity3d.services.banners.BannerView;
import com.unity3d.services.banners.UnityBannerSize;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class UnityBannerAdapter extends TTAbsAdLoaderAdapter implements WeakHandler.IHandler {
    public static final String TAG = "UnityBannerAdapter";

    /* renamed from: a, reason: collision with root package name */
    private Context f4218a;

    /* renamed from: b, reason: collision with root package name */
    private String f4219b;

    /* renamed from: c, reason: collision with root package name */
    private WeakHandler f4220c = null;
    private HandlerThread d = null;
    private UnityBannerAd e = null;
    private final AtomicInteger f = new AtomicInteger(255);

    /* renamed from: com.bytedance.msdk.adapter.unity.UnityBannerAdapter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f4221a = new int[BannerErrorCode.values().length];

        static {
            try {
                f4221a[BannerErrorCode.UNKNOWN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4221a[BannerErrorCode.NO_FILL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f4221a[BannerErrorCode.NATIVE_ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f4221a[BannerErrorCode.WEBVIEW_ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    class UnityBannerAd extends TTBaseAd implements BannerView.IListener {

        /* renamed from: a, reason: collision with root package name */
        private BannerView f4222a;

        /* renamed from: b, reason: collision with root package name */
        private boolean f4223b;

        UnityBannerAd(ITTAdapterBannerAdListener iTTAdapterBannerAdListener) {
            this.mTTAdatperCallback = iTTAdapterBannerAdListener;
        }

        private int a(BannerErrorCode bannerErrorCode) {
            switch (AnonymousClass1.f4221a[bannerErrorCode.ordinal()]) {
            }
            return 1;
        }

        private ITTAdapterBannerAdListener a() {
            return (ITTAdapterBannerAdListener) this.mTTAdatperCallback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b() {
            if (UnityBannerAdapter.this.d != null) {
                try {
                    UnityBannerAdapter.this.d.getLooper().quit();
                    UnityBannerAdapter.this.d.quit();
                    UnityBannerAdapter.this.d = null;
                    UnityBannerAdapter.this.f4220c = null;
                } catch (Throwable unused) {
                }
            }
        }

        void a(Activity activity) {
            Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelById(UnityBannerAdapter.this.getAdapterRit(), UnityBannerAdapter.this.getAdSlotId()) + "loadBannerAd=" + UnityAds.isInitialized());
            if (UnityAds.isInitialized()) {
                this.f4222a = new BannerView(activity, UnityBannerAdapter.this.getAdSlotId(), UnityBannerSize.getDynamicSize(activity.getApplicationContext()));
                this.f4222a.setListener(this);
                this.f4222a.load();
                return;
            }
            Logger.e("TTMediationSDK", "Unity Banner Ad is initing..., retry once");
            if (UnityBannerAdapter.this.d == null) {
                UnityBannerAdapter.this.d = new HandlerThread("unity-banner");
                UnityBannerAdapter.this.d.start();
            }
            if (UnityBannerAdapter.this.f4220c == null) {
                UnityBannerAdapter unityBannerAdapter = UnityBannerAdapter.this;
                unityBannerAdapter.f4220c = new WeakHandler(unityBannerAdapter.d.getLooper(), UnityBannerAdapter.this);
            }
            UnityBannerAdapter.this.f4220c.sendEmptyMessageDelayed(1, 50L);
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return ((TTAbsAdLoaderAdapter) UnityBannerAdapter.this).mAdSlot.getAdType();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public View getAdView() {
            return this.f4222a;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return this.f4222a == null;
        }

        public void onBannerClick(BannerView bannerView) {
            if (this.mTTAdatperCallback instanceof ITTAdapterBannerAdListener) {
                a().onAdClicked();
            }
            Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelById(UnityBannerAdapter.this.getAdapterRit(), UnityBannerAdapter.this.getAdSlotId()) + "Unity Ad onUnityBannerClick");
        }

        public void onBannerFailedToLoad(BannerView bannerView, BannerErrorInfo bannerErrorInfo) {
            if (this.f4223b) {
                return;
            }
            this.f4223b = true;
            UnityBannerAdapter.this.notifyAdFailed(AdErrorUtil.obtainAdError(a(bannerErrorInfo.errorCode), bannerErrorInfo.errorMessage));
        }

        public void onBannerLeftApplication(BannerView bannerView) {
            if (this.mTTAdatperCallback instanceof ITTAdapterBannerAdListener) {
                a().onAdLeftApplication();
            }
            Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelById(UnityBannerAdapter.this.getAdapterRit(), UnityBannerAdapter.this.getAdSlotId()) + "Unity Ad onBannerLeftApplication");
        }

        public void onBannerLoaded(BannerView bannerView) {
            if (this.f4223b) {
                return;
            }
            this.f4223b = true;
            UnityBannerAdapter.this.notifyAdLoaded(this);
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            BannerView bannerView = this.f4222a;
            if (bannerView != null) {
                bannerView.setListener((BannerView.IListener) null);
                this.f4222a.destroy();
                this.f4222a = null;
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

    @Override // com.bytedance.msdk.adapter.util.WeakHandler.IHandler
    public void handleMsg(Message message) {
        UnityBannerAd unityBannerAd;
        if (message.what == 1 && this.e != null && this.f.get() > 0) {
            this.e.a((Activity) this.f4218a);
            this.f.decrementAndGet();
        }
        if (this.f.get() > 0 || (unityBannerAd = this.e) == null) {
            return;
        }
        unityBannerAd.b();
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void loadAd(Context context, Map<String, Object> map) {
        this.f4218a = context;
        if (map != null) {
            if (map.containsKey("tt_ad_network_config_appid")) {
                this.f4219b = (String) map.get("tt_ad_network_config_appid");
            }
            Object obj = map.get("tt_ad_network_callback");
            ITTAdapterBannerAdListener iTTAdapterBannerAdListener = obj instanceof ITTAdapterBannerAdListener ? (ITTAdapterBannerAdListener) obj : null;
            if (TextUtils.isEmpty(this.f4219b)) {
                if (iTTAdapterBannerAdListener != null) {
                    notifyAdFailed(new AdError(AdError.ERROR_CODE_MEDIA_ID, "Unity AppId can not be null ", AdError.ERROR_CODE_MEDIA_ID, "Unity AppId can not be null "));
                }
                Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelById(getAdapterRit(), getAdSlotId()) + "Unity Banner Ad Error ,Unity AppId can not be null  ");
            }
            UnityAds.setDebugMode(Logger.isDebug());
            UnityAds.initialize(context, this.f4219b, Logger.isDebug());
            this.e = new UnityBannerAd(iTTAdapterBannerAdListener);
            this.e.a((Activity) context);
        }
    }
}
