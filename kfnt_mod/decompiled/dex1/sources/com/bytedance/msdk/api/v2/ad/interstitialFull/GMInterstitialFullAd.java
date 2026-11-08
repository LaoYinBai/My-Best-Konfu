package com.bytedance.msdk.api.v2.ad.interstitialFull;

import android.app.Activity;
import androidx.annotation.NonNull;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do.a;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdLoadInfo;
import com.bytedance.msdk.api.GMAdEcpmInfo;
import com.bytedance.msdk.api.v2.ad.GMBaseAd;
import com.bytedance.msdk.api.v2.slot.GMAdSlotInterstitialFull;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GMInterstitialFullAd extends GMBaseAd {

    /* renamed from: b, reason: collision with root package name */
    private a f4431b;

    public GMInterstitialFullAd(Activity activity, String str) {
        this.f4431b = new a(activity, str);
    }

    public void destroy() {
        a aVar = this.f4431b;
        if (aVar != null) {
            aVar.x();
        }
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public List<AdLoadInfo> getAdLoadInfoList() {
        a aVar = this.f4431b;
        return aVar != null ? aVar.getAdLoadInfoList() : new ArrayList();
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public int getAdNetworkPlatformId() {
        a aVar = this.f4431b;
        if (aVar != null) {
            return aVar.z();
        }
        return -2;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    @NonNull
    public String getAdNetworkRitId() {
        a aVar = this.f4431b;
        return aVar != null ? aVar.A() : "-2";
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public GMAdEcpmInfo getBestEcpm() {
        a aVar = this.f4431b;
        if (aVar != null) {
            return aVar.b();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public List<GMAdEcpmInfo> getCacheList() {
        a aVar = this.f4431b;
        if (aVar != null) {
            return aVar.c();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public List<GMAdEcpmInfo> getMultiBiddingEcpm() {
        a aVar = this.f4431b;
        if (aVar != null) {
            return aVar.f();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    @NonNull
    public String getPreEcpm() {
        a aVar = this.f4431b;
        return aVar != null ? aVar.B() : "-2";
    }

    public boolean isReady() {
        a aVar = this.f4431b;
        if (aVar != null) {
            return aVar.K();
        }
        return false;
    }

    @Override // com.bytedance.msdk.api.v2.ad.GMBaseAd
    public void loadAd(GMAdSlotInterstitialFull gMAdSlotInterstitialFull, GMInterstitialFullAdLoadCallback gMInterstitialFullAdLoadCallback) {
        super.loadAd(gMAdSlotInterstitialFull, gMInterstitialFullAdLoadCallback);
        if (this.f4431b != null) {
            if (!bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().a(this.f4431b.j(), 10) && gMInterstitialFullAdLoadCallback != null) {
                gMInterstitialFullAdLoadCallback.onInterstitialFullLoadFail(new AdError(AdError.ERROR_CODE_RIT_ADTYPE_NO_SAME, AdError.getMessage(AdError.ERROR_CODE_RIT_ADTYPE_NO_SAME)));
            } else if (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().y()) {
                this.f4431b.a(getAdSlot(), gMAdSlotInterstitialFull, gMInterstitialFullAdLoadCallback);
            } else if (gMInterstitialFullAdLoadCallback != null) {
                gMInterstitialFullAdLoadCallback.onInterstitialFullLoadFail(new AdError(AdError.ERROR_CODE_INTERACTION_MODULE_UNABLE, AdError.getMessage(AdError.ERROR_CODE_INTERACTION_MODULE_UNABLE)));
            }
        }
    }

    public void setAdInterstitialFullListener(GMInterstitialFullAdListener gMInterstitialFullAdListener) {
        a aVar = this.f4431b;
        if (aVar != null) {
            aVar.a(gMInterstitialFullAdListener);
        }
    }

    public void showAd(Activity activity) throws JSONException {
        a aVar = this.f4431b;
        if (aVar != null) {
            if (activity == null) {
                Logger.e("TTMediationSDK", "activity can not be null !");
            } else {
                aVar.a(activity);
            }
        }
    }
}
