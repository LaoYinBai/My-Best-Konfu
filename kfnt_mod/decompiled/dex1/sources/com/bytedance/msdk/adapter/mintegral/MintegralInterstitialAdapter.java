package com.bytedance.msdk.adapter.mintegral;

import android.app.Activity;
import android.content.Context;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.listener.ITTAdapterInterstitialListener;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdErrorUtil;
import com.bytedance.msdk.api.TTAdConstant;
import com.bytedance.msdk.base.TTBaseAd;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.out.InterstitialListener;
import com.mbridge.msdk.out.MBInterstitialHandler;
import com.mbridge.msdk.out.MBridgeIds;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class MintegralInterstitialAdapter extends TTAbsAdLoaderAdapter {

    /* renamed from: a, reason: collision with root package name */
    private MtInterstitialAd f4087a;

    /* renamed from: b, reason: collision with root package name */
    private Context f4088b;

    class MtInterstitialAd extends TTBaseAd {

        /* renamed from: a, reason: collision with root package name */
        private MBInterstitialHandler f4089a;

        /* renamed from: b, reason: collision with root package name */
        private boolean f4090b;

        /* renamed from: c, reason: collision with root package name */
        InterstitialListener f4091c = new InterstitialListener() { // from class: com.bytedance.msdk.adapter.mintegral.MintegralInterstitialAdapter.MtInterstitialAd.1
            public void onInterstitialAdClick(MBridgeIds mBridgeIds) {
                if (((TTBaseAd) MtInterstitialAd.this).mTTAdatperCallback instanceof ITTAdapterInterstitialListener) {
                    MtInterstitialAd.this.a().onInterstitialAdClick();
                }
            }

            public void onInterstitialClosed(MBridgeIds mBridgeIds) {
                if (((TTBaseAd) MtInterstitialAd.this).mTTAdatperCallback instanceof ITTAdapterInterstitialListener) {
                    MtInterstitialAd.this.a().onInterstitialClosed();
                }
            }

            public void onInterstitialLoadFail(MBridgeIds mBridgeIds, String str) {
                MintegralInterstitialAdapter.this.notifyAdFailed(AdErrorUtil.obtainAdError(AdError.ERROR_CODE_THIRD_SDK_LOAD_FAIL, str));
                MtInterstitialAd.this.f4090b = false;
            }

            public void onInterstitialLoadSuccess(MBridgeIds mBridgeIds) {
                MtInterstitialAd mtInterstitialAd = MtInterstitialAd.this;
                MintegralInterstitialAdapter.this.notifyAdLoaded(mtInterstitialAd);
                MtInterstitialAd.this.f4090b = true;
            }

            public void onInterstitialShowFail(MBridgeIds mBridgeIds, String str) {
                if (((TTBaseAd) MtInterstitialAd.this).mTTAdatperCallback instanceof ITTAdapterInterstitialListener) {
                    MtInterstitialAd.this.a().onInterstitialShowFail(new AdError(AdError.ERROR_CODE_ADN_SHOW_FAIL, str, AdError.ERROR_CODE_ADN_SHOW_FAIL, str));
                }
            }

            public void onInterstitialShowSuccess(MBridgeIds mBridgeIds) {
                if (((TTBaseAd) MtInterstitialAd.this).mTTAdatperCallback instanceof ITTAdapterInterstitialListener) {
                    MtInterstitialAd.this.a().onInterstitialShow();
                }
            }
        };

        MtInterstitialAd(ITTAdapterInterstitialListener iTTAdapterInterstitialListener) {
            this.mTTAdatperCallback = iTTAdapterInterstitialListener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ITTAdapterInterstitialListener a() {
            return (ITTAdapterInterstitialListener) this.mTTAdatperCallback;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public int getAdType() {
            return ((TTAbsAdLoaderAdapter) MintegralInterstitialAdapter.this).mAdSlot.getAdType();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean hasDestroyed() {
            return this.f4089a == null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public boolean isReady() {
            return this.f4090b;
        }

        public void loadAd() {
            HashMap map = new HashMap();
            map.put(MBridgeConstans.PLACEMENT_ID, "");
            map.put("unit_id", MintegralInterstitialAdapter.this.getAdSlotId());
            this.f4089a = new MBInterstitialHandler(MintegralInterstitialAdapter.this.f4088b, map);
            this.f4089a.setInterstitialListener(this.f4091c);
            this.f4089a.preload();
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void onDestroy() {
            MBInterstitialHandler mBInterstitialHandler = this.f4089a;
            if (mBInterstitialHandler != null) {
                mBInterstitialHandler.setInterstitialListener((InterstitialListener) null);
            }
            this.f4089a = null;
            this.mTTAdatperCallback = null;
        }

        @Override // com.bytedance.msdk.base.TTBaseAd
        public void showAd(Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map) {
            MBInterstitialHandler mBInterstitialHandler = this.f4089a;
            if (mBInterstitialHandler != null) {
                mBInterstitialHandler.show();
            }
        }
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void destroy() {
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
        this.f4088b = context;
        if (map != null) {
            Object obj = map.get("tt_ad_network_callback");
            this.f4087a = new MtInterstitialAd(obj instanceof ITTAdapterInterstitialListener ? (ITTAdapterInterstitialListener) obj : null);
            this.f4087a.loadAd();
        }
    }
}
