package com.bytedance.msdk.api.v2.ad.nativeAd;

import android.content.Context;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do.f;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdLoadInfo;
import com.bytedance.msdk.api.GMAdEcpmInfo;
import com.bytedance.msdk.api.v2.ad.GMBaseAd;
import com.bytedance.msdk.api.v2.slot.GMAdSlotNative;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GMUnifiedNativeAd extends GMBaseAd {

    /* renamed from: b, reason: collision with root package name */
    private f f4433b;

    public GMUnifiedNativeAd(Context context, String str) {
        this.f4433b = new f(context, str);
    }

    public void destroy() {
        f fVar = this.f4433b;
        if (fVar != null) {
            fVar.x();
        }
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public List<AdLoadInfo> getAdLoadInfoList() {
        f fVar = this.f4433b;
        return fVar != null ? fVar.getAdLoadInfoList() : new ArrayList();
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public int getAdNetworkPlatformId() {
        return 0;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public String getAdNetworkRitId() {
        return "";
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public GMAdEcpmInfo getBestEcpm() {
        return null;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public List<GMAdEcpmInfo> getCacheList() {
        f fVar = this.f4433b;
        if (fVar != null) {
            return fVar.c();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public List<GMAdEcpmInfo> getMultiBiddingEcpm() {
        f fVar = this.f4433b;
        if (fVar != null) {
            return fVar.f();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public String getPreEcpm() {
        return "";
    }

    @Override // com.bytedance.msdk.api.v2.ad.GMBaseAd
    public void loadAd(GMAdSlotNative gMAdSlotNative, GMNativeAdLoadCallback gMNativeAdLoadCallback) {
        super.loadAd(gMAdSlotNative, gMNativeAdLoadCallback);
        if (this.f4433b != null) {
            if (!a.f().a(this.f4433b.j(), 5) && gMNativeAdLoadCallback != null) {
                gMNativeAdLoadCallback.onAdLoadedFail(new AdError(AdError.ERROR_CODE_RIT_ADTYPE_NO_SAME, AdError.getMessage(AdError.ERROR_CODE_RIT_ADTYPE_NO_SAME)));
                return;
            }
            if (gMAdSlotNative != null) {
                if (gMAdSlotNative.getAdStyleType() == 1) {
                    if (!a.f().v()) {
                        if (gMNativeAdLoadCallback != null) {
                            gMNativeAdLoadCallback.onAdLoadedFail(new AdError(AdError.ERROR_CODE_FEED_MODULE_UNABLE, AdError.getMessage(AdError.ERROR_CODE_FEED_MODULE_UNABLE)));
                            return;
                        }
                        return;
                    }
                } else if (gMAdSlotNative.getAdStyleType() == 2 && !a.f().z()) {
                    if (gMNativeAdLoadCallback != null) {
                        gMNativeAdLoadCallback.onAdLoadedFail(new AdError(AdError.ERROR_CODE_NATIVE_MODULE_UNABLE, AdError.getMessage(AdError.ERROR_CODE_NATIVE_MODULE_UNABLE)));
                        return;
                    }
                    return;
                }
            }
            this.f4433b.a(getAdSlot(), gMAdSlotNative, gMNativeAdLoadCallback);
        }
    }
}
