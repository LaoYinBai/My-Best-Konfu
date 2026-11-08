package com.bytedance.msdk.adapter.pangle;

import android.content.Context;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdErrorUtil;
import com.bytedance.msdk.api.TTRequestExtraParams;
import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class PangleNativeAdapter extends TTAbsAdLoaderAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f4150a;

    private void a(TTAdNative tTAdNative, AdSlot.Builder builder) {
        new PangleNativeLoader().loadAd(this.f4150a, isClientBidding(), tTAdNative, builder.build(), this);
    }

    private void a(TTAdNative tTAdNative, AdSlot.Builder builder, int i, int i2) {
        float f;
        float f2;
        if (i2 > 0) {
            f = i;
            f2 = i2;
        } else {
            f = i;
            f2 = 0.0f;
        }
        builder.setExpressViewAcceptedSize(f, f2);
        new PangleNativeExpressLoader().loadAd(this.f4150a, isClientBidding(), tTAdNative, builder.build(), this);
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void destroy() {
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getAdNetWorkName() {
        return "pangle";
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getSdkVersion() {
        return TTAdSdk.getAdManager().getSDKVersion();
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void loadAd(Context context, Map<String, Object> map) {
        this.f4150a = context;
        if (map != null) {
            TTAdNative tTAdNativeCreateAdNative = TTAdSdk.getAdManager().createAdNative(this.f4150a);
            AdSlot.Builder builderBuildPangleAdSlot = PangleAdapterUtils.buildPangleAdSlot(this.mAdSlot, getAdSlotId(), this.mWaterfallAbTestParam, getClientReqId(), getAdm(), false);
            int iIntValue = ((Integer) map.get(TTRequestExtraParams.PARAM_AD_WIDTH)).intValue();
            int iIntValue2 = ((Integer) map.get(TTRequestExtraParams.PARAM_AD_HEIGHT)).intValue();
            if (iIntValue > 0 && iIntValue2 > 0) {
                builderBuildPangleAdSlot.setImageAcceptedSize(PangleAdapterUtils.dp2px(this.f4150a, iIntValue), PangleAdapterUtils.dp2px(this.f4150a, iIntValue2));
            }
            Object obj = map.get("tt_ad_origin_type");
            if (obj != null) {
                int iIntValue3 = ((Integer) obj).intValue();
                if (iIntValue3 == 1) {
                    a(tTAdNativeCreateAdNative, builderBuildPangleAdSlot, iIntValue, iIntValue2);
                    return;
                } else if (iIntValue3 == 2) {
                    a(tTAdNativeCreateAdNative, builderBuildPangleAdSlot);
                    return;
                } else if (iIntValue3 == 3) {
                    notifyAdFailed(AdErrorUtil.obtainAdError(AdError.ERROR_CODE_RENDER_TYPE_MISMATCH, AdError.getMessage(AdError.ERROR_CODE_RENDER_TYPE_MISMATCH)));
                    return;
                }
            }
            if (this.mAdSlot.getAdStyleType() == 1) {
                a(tTAdNativeCreateAdNative, builderBuildPangleAdSlot, iIntValue, iIntValue2);
            } else if (this.mAdSlot.getAdStyleType() == 2) {
                a(tTAdNativeCreateAdNative, builderBuildPangleAdSlot);
            }
        }
    }
}
