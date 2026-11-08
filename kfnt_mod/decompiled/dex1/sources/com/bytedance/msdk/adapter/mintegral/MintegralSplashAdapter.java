package com.bytedance.msdk.adapter.mintegral;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.listener.ITTAdapterSplashAdListener;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdErrorUtil;
import com.bytedance.msdk.api.TTRequestExtraParams;
import com.bytedance.msdk.base.TTBaseAd;
import com.mbridge.msdk.out.MBSplashHandler;
import com.mbridge.msdk.out.MBSplashLoadListener;
import com.mbridge.msdk.out.MBSplashShowListener;
import com.mbridge.msdk.out.MBridgeIds;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class MintegralSplashAdapter extends TTAbsAdLoaderAdapter {

    /* renamed from: a, reason: collision with root package name */
    private MintegralSplashAd f4113a;

    class MintegralSplashAd extends TTBaseAd {

        /* renamed from: a, reason: collision with root package name */
        private MBSplashHandler f4114a;

        /* renamed from: b, reason: collision with root package name */
        private TTAbsAdLoaderAdapter f4115b;

        public MintegralSplashAd(Activity activity, TTAbsAdLoaderAdapter tTAbsAdLoaderAdapter, ITTAdapterSplashAdListener iTTAdapterSplashAdListener, int i) {
            this.mTTAdatperCallback = iTTAdapterSplashAdListener;
            this.f4115b = tTAbsAdLoaderAdapter;
            this.f4114a = new MBSplashHandler(activity, "", tTAbsAdLoaderAdapter.getAdSlotId());
            this.f4114a.setLoadTimeOut(i);
            this.f4114a.setSplashLoadListener(new MBSplashLoadListener(MintegralSplashAdapter.this) { // from class: com.bytedance.msdk.adapter.mintegral.MintegralSplashAdapter.MintegralSplashAd.1
                public void isSupportZoomOut(MBridgeIds mBridgeIds, boolean z) {
                }

                public void onLoadFailed(MBridgeIds mBridgeIds, String str, int i2) {
                    if (MintegralSplashAd.this.f4115b != null) {
                        MintegralSplashAd.this.f4115b.notifyAdFailed(AdErrorUtil.obtainAdError(AdError.ERROR_CODE_THIRD_SDK_LOAD_FAIL, str));
                    }
                }

                public void onLoadSuccessed(MBridgeIds mBridgeIds, int i2) {
                    if (MintegralSplashAd.this.f4115b != null) {
                        MintegralSplashAd.this.f4115b.notifyAdLoaded(MintegralSplashAd.this);
                    }
                }
            });
            this.f4114a.setSplashShowListener(new MBSplashShowListener(MintegralSplashAdapter.this) { // from class: com.bytedance.msdk.adapter.mintegral.MintegralSplashAdapter.MintegralSplashAd.2
                public void onAdClicked(MBridgeIds mBridgeIds) {
                    if (((TTBaseAd) MintegralSplashAd.this).mTTAdatperCallback instanceof ITTAdapterSplashAdListener) {
                        MintegralSplashAd.this.a().onAdClicked();
                    }
                }

                public void onAdTick(MBridgeIds mBridgeIds, long j) {
                }

                public void onDismiss(MBridgeIds mBridgeIds, int i2) {
                    if (((TTBaseAd) MintegralSplashAd.this).mTTAdatperCallback instanceof ITTAdapterSplashAdListener) {
                        MintegralSplashAd.this.a().onAdDismiss();
                    }
                }

                public void onShowFailed(MBridgeIds mBridgeIds, String str) {
                    if (((TTBaseAd) MintegralSplashAd.this).mTTAdatperCallback instanceof ITTAdapterSplashAdListener) {
                        MintegralSplashAd.this.a().onAdShowFail(new AdError(AdError.ERROR_CODE_ADN_SHOW_FAIL, str, AdError.ERROR_CODE_ADN_SHOW_FAIL, str));
                    }
                }

                public void onShowSuccessed(MBridgeIds mBridgeIds) {
                    if (((TTBaseAd) MintegralSplashAd.this).mTTAdatperCallback instanceof ITTAdapterSplashAdListener) {
                        MintegralSplashAd.this.a().onAdShow();
                    }
                }

                public void onZoomOutPlayFinish(MBridgeIds mBridgeIds) {
                }

                public void onZoomOutPlayStart(MBridgeIds mBridgeIds) {
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ITTAdapterSplashAdListener a() {
            return (ITTAdapterSplashAdListener) this.mTTAdatperCallback;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            if (this.f4115b.getAdSlot() != null) {
                return this.f4115b.getAdSlot().getAdType();
            }
            return 0;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return this.f4114a == null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isAdCacheReady() {
            return isReady();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isReady() {
            if (this.f4114a != null) {
                return !TextUtils.isEmpty(MintegralSplashAdapter.this.getAdm()) ? this.f4114a.isReady(MintegralSplashAdapter.this.getAdm()) : this.f4114a.isReady();
            }
            return false;
        }

        public void load() {
            if (this.f4114a != null) {
                if (TextUtils.isEmpty(MintegralSplashAdapter.this.getAdm())) {
                    Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(MintegralSplashAdapter.this.getAdapterRit(), MintegralSplashAdapter.this.getAdSlotId()) + "loadAdWithoutBid......MintegralSplashAdapter...loadAd start....");
                    this.f4114a.preLoad();
                    return;
                }
                Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelById(MintegralSplashAdapter.this.getAdapterRit(), MintegralSplashAdapter.this.getAdSlotId()) + "loadAdWithBid......MintegralSplashAdapter...loadAd start....");
                this.f4114a.preLoadByToken(MintegralSplashAdapter.this.getAdm());
            }
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            MBSplashHandler mBSplashHandler = this.f4114a;
            if (mBSplashHandler != null) {
                mBSplashHandler.onDestroy();
                this.f4114a = null;
            }
            super.onDestroy();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void showSplashAd(ViewGroup viewGroup) {
            if (viewGroup == null || !isReady()) {
                return;
            }
            viewGroup.removeAllViews();
            if (TextUtils.isEmpty(MintegralSplashAdapter.this.getAdm())) {
                this.f4114a.show(viewGroup);
            } else {
                this.f4114a.show(viewGroup, MintegralSplashAdapter.this.getAdm());
            }
        }
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void destroy() {
        MintegralSplashAd mintegralSplashAd = this.f4113a;
        if (mintegralSplashAd != null) {
            mintegralSplashAd.onDestroy();
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
        Object obj;
        int iIntValue = (map == null || !map.containsKey(TTRequestExtraParams.PARAM_AD_LOAD_TIMEOUT) || (obj = map.get(TTRequestExtraParams.PARAM_AD_LOAD_TIMEOUT)) == null) ? 3000 : ((Integer) obj).intValue();
        Object obj2 = map.get("tt_ad_network_callback");
        this.f4113a = new MintegralSplashAd((Activity) context, this, obj2 instanceof ITTAdapterSplashAdListener ? (ITTAdapterSplashAdListener) obj2 : null, iIntValue);
        this.f4113a.load();
    }
}
