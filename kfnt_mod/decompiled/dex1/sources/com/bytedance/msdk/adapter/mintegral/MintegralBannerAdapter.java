package com.bytedance.msdk.adapter.mintegral;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.listener.ITTAdapterBannerAdListener;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.adapter.util.UIUtils;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdErrorUtil;
import com.bytedance.msdk.api.TTRequestExtraParams;
import com.bytedance.msdk.base.TTBaseAd;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.mbridge.msdk.out.BannerAdListener;
import com.mbridge.msdk.out.BannerSize;
import com.mbridge.msdk.out.MBBannerView;
import com.mbridge.msdk.out.MBridgeIds;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class MintegralBannerAdapter extends TTAbsAdLoaderAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f4072a;

    /* renamed from: b, reason: collision with root package name */
    private MTBannerView f4073b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f4074c;

    class MTBannerView extends TTBaseAd {

        /* renamed from: b, reason: collision with root package name */
        private MBBannerView f4076b;

        /* renamed from: a, reason: collision with root package name */
        private AtomicBoolean f4075a = new AtomicBoolean(false);

        /* renamed from: c, reason: collision with root package name */
        BannerAdListener f4077c = new BannerAdListener() { // from class: com.bytedance.msdk.adapter.mintegral.MintegralBannerAdapter.MTBannerView.2
            public void closeFullScreen(MBridgeIds mBridgeIds) {
            }

            public void onClick(MBridgeIds mBridgeIds) {
                if (((TTBaseAd) MTBannerView.this).mTTAdatperCallback instanceof ITTAdapterBannerAdListener) {
                    MTBannerView.this.a().onAdClicked();
                }
            }

            public void onCloseBanner(MBridgeIds mBridgeIds) {
                if (((TTBaseAd) MTBannerView.this).mTTAdatperCallback instanceof ITTAdapterBannerAdListener) {
                    MTBannerView.this.a().onAdClosed();
                }
            }

            public void onLeaveApp(MBridgeIds mBridgeIds) {
                if (((TTBaseAd) MTBannerView.this).mTTAdatperCallback instanceof ITTAdapterBannerAdListener) {
                    MTBannerView.this.a().onAdLeftApplication();
                }
            }

            public void onLoadFailed(MBridgeIds mBridgeIds, String str) {
                if (MTBannerView.this.f4075a.get()) {
                    return;
                }
                MTBannerView.this.f4075a.set(true);
                MintegralBannerAdapter.this.notifyAdFailed(AdErrorUtil.obtainAdError(AdError.ERROR_CODE_THIRD_SDK_LOAD_FAIL, str));
            }

            public void onLoadSuccessed(MBridgeIds mBridgeIds) {
                if (MTBannerView.this.f4075a.get()) {
                    return;
                }
                MTBannerView mTBannerView = MTBannerView.this;
                MintegralBannerAdapter.this.notifyAdLoaded(mTBannerView);
                MTBannerView.this.f4075a.set(true);
            }

            public void onLogImpression(MBridgeIds mBridgeIds) {
                if (((TTBaseAd) MTBannerView.this).mTTAdatperCallback instanceof ITTAdapterBannerAdListener) {
                    MTBannerView.this.a().onAdShow();
                }
            }

            public void showFullScreen(MBridgeIds mBridgeIds) {
            }
        };

        MTBannerView(ITTAdapterBannerAdListener iTTAdapterBannerAdListener) {
            this.mTTAdatperCallback = iTTAdapterBannerAdListener;
            this.f4075a.set(false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ITTAdapterBannerAdListener a() {
            return (ITTAdapterBannerAdListener) this.mTTAdatperCallback;
        }

        private BannerSize a(int i) {
            switch (i) {
                case 1:
                    return new BannerSize(4, 0, 0);
                case 2:
                    return new BannerSize(1, 0, 0);
                case 3:
                    return new BannerSize(2, 0, 0);
                case 4:
                    return new BannerSize(5, 468, 60);
                case 5:
                    return new BannerSize(3, 0, 0);
                case 6:
                    return (((TTAbsAdLoaderAdapter) MintegralBannerAdapter.this).mAdSlot.getImgAcceptedWidth() <= 0 || ((TTAbsAdLoaderAdapter) MintegralBannerAdapter.this).mAdSlot.getImgAcceptedHeight() <= 0) ? new BannerSize(4, 0, 0) : new BannerSize(5, ((TTAbsAdLoaderAdapter) MintegralBannerAdapter.this).mAdSlot.getImgAcceptedWidth(), ((TTAbsAdLoaderAdapter) MintegralBannerAdapter.this).mAdSlot.getImgAcceptedHeight());
                default:
                    return new BannerSize(4, 0, 0);
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        private int[] b(int i) {
            int i2;
            int imgAcceptedHeight;
            int screenWidth = UIUtils.getScreenWidth(MintegralBannerAdapter.this.f4072a);
            switch (i) {
                case 1:
                    i2 = screenWidth * 50;
                    imgAcceptedHeight = i2 / 320;
                    break;
                case 2:
                    i2 = screenWidth * 100;
                    imgAcceptedHeight = i2 / 320;
                    break;
                case 3:
                    imgAcceptedHeight = (screenWidth * ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION) / TTAdConstant.DEFAULT_LIVE_SHOW_TIME_MAX;
                    break;
                case 4:
                    imgAcceptedHeight = (screenWidth * 60) / 468;
                    break;
                case 5:
                    imgAcceptedHeight = (screenWidth * 90) / 728;
                    break;
                case 6:
                    if (((TTAbsAdLoaderAdapter) MintegralBannerAdapter.this).mAdSlot.getImgAcceptedWidth() > 0 && ((TTAbsAdLoaderAdapter) MintegralBannerAdapter.this).mAdSlot.getImgAcceptedHeight() > 0) {
                        imgAcceptedHeight = (((TTAbsAdLoaderAdapter) MintegralBannerAdapter.this).mAdSlot.getImgAcceptedHeight() * screenWidth) / ((TTAbsAdLoaderAdapter) MintegralBannerAdapter.this).mAdSlot.getImgAcceptedWidth();
                        break;
                    }
                    i2 = screenWidth * 50;
                    imgAcceptedHeight = i2 / 320;
                    break;
                default:
                    imgAcceptedHeight = 0;
                    break;
            }
            return new int[]{screenWidth, imgAcceptedHeight};
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return 1;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public View getAdView() {
            MBBannerView mBBannerView = this.f4076b;
            return mBBannerView != null ? mBBannerView : super.getAdView();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return this.f4076b == null;
        }

        public void loadAd() {
            this.f4076b = new MBBannerView(MintegralBannerAdapter.this.f4072a);
            this.f4076b.init(a(((TTAbsAdLoaderAdapter) MintegralBannerAdapter.this).mAdSlot.getBannerSize()), "", MintegralBannerAdapter.this.getAdSlotId());
            int[] iArrB = b(((TTAbsAdLoaderAdapter) MintegralBannerAdapter.this).mAdSlot.getBannerSize());
            this.f4076b.setLayoutParams(new ViewGroup.LayoutParams(iArrB[0], iArrB[1]));
            this.f4076b.setAllowShowCloseBtn(MintegralBannerAdapter.this.f4074c);
            this.f4076b.setRefreshTime(0);
            this.f4076b.setBannerAdListener(this.f4077c);
            if (TextUtils.isEmpty(MintegralBannerAdapter.this.getAdm())) {
                Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(MintegralBannerAdapter.this.getAdapterRit(), MintegralBannerAdapter.this.getAdSlotId()) + "loadAdWithoutBid......MintegralBannerAdapter...loadAd start....");
                this.f4076b.load();
                return;
            }
            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(MintegralBannerAdapter.this.getAdapterRit(), MintegralBannerAdapter.this.getAdSlotId()) + "loadAdWithBid......MintegralBannerAdapter...loadAd start....");
            this.f4076b.loadFromBid(MintegralBannerAdapter.this.getAdm());
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.bytedance.msdk.adapter.mintegral.MintegralBannerAdapter.MTBannerView.1
                @Override // java.lang.Runnable
                public void run() {
                    if (MTBannerView.this.f4076b != null) {
                        MTBannerView.this.f4076b.setBannerAdListener((BannerAdListener) null);
                        MTBannerView.this.f4076b.release();
                        MTBannerView.this.f4076b = null;
                    }
                }
            });
            this.mTTAdatperCallback = null;
            MintegralBannerAdapter.this.f4072a = null;
        }
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void destroy() {
        MTBannerView mTBannerView = this.f4073b;
        if (mTBannerView != null) {
            mTBannerView.onDestroy();
        }
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getAdNetWorkName() {
        return "mintegral";
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getSdkVersion() {
        return "MAL_15.7.57";
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void loadAd(Context context, Map<String, Object> map) {
        this.f4072a = context;
        if (map != null) {
            Object obj = map.get("tt_ad_network_callback");
            ITTAdapterBannerAdListener iTTAdapterBannerAdListener = obj instanceof ITTAdapterBannerAdListener ? (ITTAdapterBannerAdListener) obj : null;
            this.f4074c = map.containsKey(TTRequestExtraParams.PARAM_BANNER_ALLOW_SHOW_CLOSE_BTN) && ((Boolean) map.get(TTRequestExtraParams.PARAM_BANNER_ALLOW_SHOW_CLOSE_BTN)).booleanValue();
            this.f4073b = new MTBannerView(iTTAdapterBannerAdListener);
            this.f4073b.loadAd();
        }
    }
}
