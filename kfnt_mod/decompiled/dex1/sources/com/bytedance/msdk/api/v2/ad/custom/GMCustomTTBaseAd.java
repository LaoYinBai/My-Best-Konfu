package com.bytedance.msdk.api.v2.ad.custom;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.bytedance.msdk.adapter.listener.ITTAdatperCallback;
import com.bytedance.msdk.api.TTAdConstant;
import com.bytedance.msdk.api.v2.ad.custom.banner.GMCustomBannerAdapter;
import com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter;
import com.bytedance.msdk.api.v2.ad.custom.base.GMCustomVideoAdapter;
import com.bytedance.msdk.api.v2.ad.custom.nativeAd.IGMCustomNativeConvert;
import com.bytedance.msdk.api.v2.ad.custom.nativeAd.IGMCustomNativeExpressConvert;
import com.bytedance.msdk.api.v2.ad.custom.nativeAd.IGMCustomNativeLifecycleConvert;
import com.bytedance.msdk.api.v2.ad.custom.splash.GMCustomSplashAdapter;
import com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAdListener;
import com.bytedance.msdk.api.v2.ad.nativeAd.GMVideoListener;
import com.bytedance.msdk.api.v2.ad.nativeAd.GMViewBinder;
import com.bytedance.msdk.base.TTBaseAd;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GMCustomTTBaseAd extends TTBaseAd {

    /* renamed from: a, reason: collision with root package name */
    private int f4361a;

    /* renamed from: b, reason: collision with root package name */
    private GMCustomBaseAdapter f4362b;

    /* renamed from: c, reason: collision with root package name */
    private Map<String, Object> f4363c = new HashMap();
    private IGMCustomNativeConvert d;
    private IGMCustomNativeExpressConvert e;
    private IGMCustomNativeLifecycleConvert f;

    @Override // com.bytedance.msdk.base.TTBaseAd
    public int getAdType() {
        return this.f4361a;
    }

    @Override // com.bytedance.msdk.base.TTBaseAd
    public View getAdView() {
        GMCustomBaseAdapter gMCustomBaseAdapter = this.f4362b;
        if (gMCustomBaseAdapter != null && (gMCustomBaseAdapter instanceof GMCustomBannerAdapter)) {
            return ((GMCustomBannerAdapter) gMCustomBaseAdapter).getAdViewInner();
        }
        IGMCustomNativeExpressConvert iGMCustomNativeExpressConvert = this.e;
        if (iGMCustomNativeExpressConvert != null) {
            return iGMCustomNativeExpressConvert.getExpressViewInner();
        }
        return null;
    }

    public GMNativeAdListener getGMNativeAdListener() {
        return this.mTTNativeAdListener;
    }

    public GMVideoListener getGMVideoListener() {
        return this.mTTVideoListener;
    }

    public Map<String, Object> getMediaExtraInfo() {
        return this.f4363c;
    }

    public ITTAdatperCallback getTTAdapterCallback() {
        return this.mTTAdatperCallback;
    }

    @Override // com.bytedance.msdk.base.TTBaseAd
    public boolean hasDestroyed() {
        return false;
    }

    @Override // com.bytedance.msdk.base.TTBaseAd
    public boolean isReady() {
        GMCustomBaseAdapter gMCustomBaseAdapter = this.f4362b;
        if (gMCustomBaseAdapter != null) {
            return gMCustomBaseAdapter.isReadyInner();
        }
        return false;
    }

    @Override // com.bytedance.msdk.base.TTBaseAd
    public void onDestroy() {
        super.onDestroy();
        IGMCustomNativeLifecycleConvert iGMCustomNativeLifecycleConvert = this.f;
        if (iGMCustomNativeLifecycleConvert != null) {
            iGMCustomNativeLifecycleConvert.onDestroyInner();
        }
    }

    @Override // com.bytedance.msdk.base.TTBaseAd
    public void onPause() {
        super.onPause();
        IGMCustomNativeLifecycleConvert iGMCustomNativeLifecycleConvert = this.f;
        if (iGMCustomNativeLifecycleConvert != null) {
            iGMCustomNativeLifecycleConvert.onPauseInner();
        }
    }

    @Override // com.bytedance.msdk.base.TTBaseAd
    public void onResume() {
        super.onResume();
        IGMCustomNativeLifecycleConvert iGMCustomNativeLifecycleConvert = this.f;
        if (iGMCustomNativeLifecycleConvert != null) {
            iGMCustomNativeLifecycleConvert.onResumeInner();
        }
    }

    @Override // com.bytedance.msdk.base.TTBaseAd
    public void registerViewForInteraction(@NonNull ViewGroup viewGroup, List<View> list, List<View> list2, GMViewBinder gMViewBinder) {
        super.registerViewForInteraction(viewGroup, list, list2, gMViewBinder);
        IGMCustomNativeConvert iGMCustomNativeConvert = this.d;
        if (iGMCustomNativeConvert != null) {
            iGMCustomNativeConvert.registerViewForInteractionInner(viewGroup, list, list2, gMViewBinder);
        }
    }

    @Override // com.bytedance.msdk.base.TTBaseAd
    public void render() {
        IGMCustomNativeExpressConvert iGMCustomNativeExpressConvert = this.e;
        if (iGMCustomNativeExpressConvert != null) {
            iGMCustomNativeExpressConvert.renderInner();
        }
    }

    @Override // com.bytedance.msdk.base.TTBaseAd
    public void setAdType(int i) {
        this.f4361a = i;
    }

    public void setCustomNativeConvert(IGMCustomNativeConvert iGMCustomNativeConvert) {
        this.d = iGMCustomNativeConvert;
    }

    public void setCustomNativeExpressConvert(IGMCustomNativeExpressConvert iGMCustomNativeExpressConvert) {
        this.e = iGMCustomNativeExpressConvert;
    }

    public void setCustomNativeLifecycleConvert(IGMCustomNativeLifecycleConvert iGMCustomNativeLifecycleConvert) {
        this.f = iGMCustomNativeLifecycleConvert;
    }

    public void setGMCustomBaseAdapter(GMCustomBaseAdapter gMCustomBaseAdapter) {
        this.f4362b = gMCustomBaseAdapter;
    }

    public void setMediaExtraInfo(Map<String, Object> map) {
        if (map != null) {
            this.f4363c.putAll(map);
        }
    }

    @Override // com.bytedance.msdk.base.TTBaseAd
    public void showAd(Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map) {
        GMCustomBaseAdapter gMCustomBaseAdapter = this.f4362b;
        if (gMCustomBaseAdapter == null || !(gMCustomBaseAdapter instanceof GMCustomVideoAdapter)) {
            return;
        }
        ((GMCustomVideoAdapter) gMCustomBaseAdapter).showAdInner(activity);
    }

    @Override // com.bytedance.msdk.base.TTBaseAd
    public void showSplashAd(ViewGroup viewGroup) {
        GMCustomBaseAdapter gMCustomBaseAdapter = this.f4362b;
        if (gMCustomBaseAdapter == null || !(gMCustomBaseAdapter instanceof GMCustomSplashAdapter)) {
            return;
        }
        ((GMCustomSplashAdapter) gMCustomBaseAdapter).showAdInner(viewGroup);
    }

    @Override // com.bytedance.msdk.base.TTBaseAd
    public void unregisterView() {
        IGMCustomNativeConvert iGMCustomNativeConvert = this.d;
        if (iGMCustomNativeConvert != null) {
            iGMCustomNativeConvert.unregisterViewInner();
        }
    }
}
