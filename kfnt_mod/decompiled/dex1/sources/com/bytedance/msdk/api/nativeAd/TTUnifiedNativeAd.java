package com.bytedance.msdk.api.nativeAd;

import android.content.Context;
import androidx.annotation.NonNull;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do.f;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdLoadInfo;
import com.bytedance.msdk.api.AdSlot;
import com.bytedance.msdk.api.GMAdEcpmInfo;
import com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd;
import com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAdLoadCallback;
import com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeUtil;
import com.bytedance.msdk.api.v2.slot.GMAdSlotNative;
import java.util.ArrayList;
import java.util.List;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class TTUnifiedNativeAd implements GMNativeAdLoadCallback {

    /* renamed from: a, reason: collision with root package name */
    private f f4324a;

    /* renamed from: b, reason: collision with root package name */
    private TTNativeAdLoadCallback f4325b;

    public TTUnifiedNativeAd(Context context, String str) {
        this.f4324a = new f(context, str);
    }

    public void destroy() {
        f fVar = this.f4324a;
        if (fVar != null) {
            fVar.x();
        }
    }

    public List<AdLoadInfo> getAdLoadInfoList() {
        f fVar = this.f4324a;
        return fVar != null ? fVar.getAdLoadInfoList() : new ArrayList();
    }

    public List<GMAdEcpmInfo> getCacheList() {
        f fVar = this.f4324a;
        if (fVar != null) {
            return fVar.c();
        }
        return null;
    }

    public List<GMAdEcpmInfo> getMultiBiddingEcpm() {
        f fVar = this.f4324a;
        if (fVar != null) {
            return fVar.f();
        }
        return null;
    }

    public void loadAd(AdSlot adSlot, TTNativeAdLoadCallback tTNativeAdLoadCallback) {
        this.f4325b = tTNativeAdLoadCallback;
        if (this.f4324a != null) {
            if (!a.f().a(this.f4324a.j(), 5) && tTNativeAdLoadCallback != null) {
                tTNativeAdLoadCallback.onAdLoadedFial(new AdError(AdError.ERROR_CODE_RIT_ADTYPE_NO_SAME, AdError.getMessage(AdError.ERROR_CODE_RIT_ADTYPE_NO_SAME)));
                return;
            }
            if (adSlot != null) {
                if (adSlot.getAdStyleType() == 1) {
                    if (!a.f().v()) {
                        if (tTNativeAdLoadCallback != null) {
                            tTNativeAdLoadCallback.onAdLoadedFial(new AdError(AdError.ERROR_CODE_FEED_MODULE_UNABLE, AdError.getMessage(AdError.ERROR_CODE_FEED_MODULE_UNABLE)));
                            return;
                        }
                        return;
                    }
                } else if (adSlot.getAdStyleType() == 2 && !a.f().z()) {
                    if (tTNativeAdLoadCallback != null) {
                        tTNativeAdLoadCallback.onAdLoadedFial(new AdError(AdError.ERROR_CODE_NATIVE_MODULE_UNABLE, AdError.getMessage(AdError.ERROR_CODE_NATIVE_MODULE_UNABLE)));
                        return;
                    }
                    return;
                }
            }
            this.f4324a.a(adSlot, (GMAdSlotNative) null, this);
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAdLoadCallback
    public void onAdLoaded(@NonNull List<GMNativeAd> list) {
        TTNativeAdLoadCallback tTNativeAdLoadCallback = this.f4325b;
        if (tTNativeAdLoadCallback != null) {
            tTNativeAdLoadCallback.onAdLoaded(GMNativeUtil.GMsToTTs(list));
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAdLoadCallback
    public void onAdLoadedFail(@NonNull AdError adError) {
        TTNativeAdLoadCallback tTNativeAdLoadCallback = this.f4325b;
        if (tTNativeAdLoadCallback != null) {
            tTNativeAdLoadCallback.onAdLoadedFial(adError);
        }
    }
}
