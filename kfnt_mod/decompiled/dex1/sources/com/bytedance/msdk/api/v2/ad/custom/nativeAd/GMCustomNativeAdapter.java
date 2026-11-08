package com.bytedance.msdk.api.v2.ad.custom.nativeAd;

import android.content.Context;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.v2.ad.custom.GMCustomAdError;
import com.bytedance.msdk.api.v2.ad.custom.GMCustomTTBaseAd;
import com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter;
import com.bytedance.msdk.api.v2.ad.custom.base.IGMCustomLoadNativeCall;
import com.bytedance.msdk.api.v2.ad.custom.bean.GMCustomServiceConfig;
import com.bytedance.msdk.api.v2.slot.GMAdSlotBase;
import com.bytedance.msdk.api.v2.slot.GMAdSlotNative;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class GMCustomNativeAdapter extends GMCustomBaseAdapter implements IGMCustomLoadNativeCall {
    private GMCustomServiceConfig g;

    @Override // com.bytedance.msdk.api.v2.ad.custom.base.IGMCustomLoadNativeCall
    public final void callLoadFail(GMCustomAdError gMCustomAdError) {
        if (isCallLoadFailCall()) {
            Logger.e("TTMediationSDK", "自定义Adapter 加载成功或者失败回调只能调用一次");
            return;
        }
        TTAbsAdLoaderAdapter tTAbsAdLoaderAdapter = this.f4373c;
        if (tTAbsAdLoaderAdapter != null) {
            tTAbsAdLoaderAdapter.notifyAdFailed(new AdError(AdError.ERROR_CODE_CUSTOM_NATIVE_LOAD_ERROR, AdError.getMessage(AdError.ERROR_CODE_CUSTOM_NATIVE_LOAD_ERROR), gMCustomAdError == null ? -1 : gMCustomAdError.getCode(), gMCustomAdError == null ? "" : gMCustomAdError.getMessage()));
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.custom.base.IGMCustomLoadNativeCall
    public final void callLoadSuccess(List<? extends GMCustomNativeAd> list) {
        if (list != null) {
            if (isCallLoadSuccessCall()) {
                Logger.e("TTMediationSDK", "自定义Adapter 加载成功或者失败回调只能调用一次");
                return;
            }
            Logger.e("TTMediationSDK", "自定义Adapter callLoadSuccess");
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                GMCustomTTBaseAd tTBaseAd = list.get(i).getTTBaseAd();
                tTBaseAd.setAdType(this.f4372b.r());
                tTBaseAd.setExpressAd(this.f4372b.p() != 2);
                arrayList.add(tTBaseAd);
            }
            TTAbsAdLoaderAdapter tTAbsAdLoaderAdapter = this.f4373c;
            if (tTAbsAdLoaderAdapter != null) {
                tTAbsAdLoaderAdapter.notifyAdLoaded(arrayList);
            }
        }
    }

    public final boolean isExpressRender() {
        GMCustomServiceConfig gMCustomServiceConfig = this.g;
        return gMCustomServiceConfig != null && gMCustomServiceConfig.getAdStyleType() == 1;
    }

    public final boolean isNativeAd() {
        GMCustomServiceConfig gMCustomServiceConfig = this.g;
        return gMCustomServiceConfig != null && gMCustomServiceConfig.getAdStyleType() == 2;
    }

    @Override // com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter
    public final boolean isReadyInner() {
        return true;
    }

    public abstract void load(Context context, GMAdSlotNative gMAdSlotNative, GMCustomServiceConfig gMCustomServiceConfig);

    @Override // com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter
    public final void loadCustom(Context context, GMAdSlotBase gMAdSlotBase, GMCustomServiceConfig gMCustomServiceConfig) {
        if (this.f4372b.p() == 0) {
            GMAdSlotNative gMAdSlotNative = (GMAdSlotNative) gMAdSlotBase;
            if (gMAdSlotNative.getAdStyleType() == 1 || gMAdSlotNative.getAdStyleType() == 2) {
                GMCustomServiceConfig gMCustomServiceConfig2 = new GMCustomServiceConfig(gMCustomServiceConfig.getADNNetworkName(), gMCustomServiceConfig.getADNNetworkSlotId(), gMAdSlotNative.getAdStyleType(), gMCustomServiceConfig.getCustomAdapterJson());
                load(context, gMAdSlotNative, gMCustomServiceConfig2);
                gMCustomServiceConfig = gMCustomServiceConfig2;
            } else {
                callLoadFail(new GMCustomAdError(9100, "自定义信息流客户端渲染类型设置错误"));
            }
        } else if (this.f4372b.p() == 1 || this.f4372b.p() == 2 || this.f4372b.p() == 3) {
            load(context, (GMAdSlotNative) gMAdSlotBase, gMCustomServiceConfig);
        } else {
            callLoadFail(new GMCustomAdError(9101, "自定义信息流服务端渲染类型设置错误"));
        }
        this.g = gMCustomServiceConfig;
    }

    @Override // com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter
    public final void onDestroy() {
    }

    @Override // com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter
    public final void onPause() {
    }

    @Override // com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter
    public final void onResume() {
    }
}
