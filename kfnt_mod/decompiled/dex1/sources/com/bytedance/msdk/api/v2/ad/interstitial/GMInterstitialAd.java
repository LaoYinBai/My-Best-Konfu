package com.bytedance.msdk.api.v2.ad.interstitial;

import android.app.Activity;
import androidx.annotation.NonNull;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do.b;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdLoadInfo;
import com.bytedance.msdk.api.GMAdEcpmInfo;
import com.bytedance.msdk.api.v2.ad.GMBaseAd;
import com.bytedance.msdk.api.v2.slot.GMAdSlotInterstitial;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GMInterstitialAd extends GMBaseAd {

    /* renamed from: b, reason: collision with root package name */
    private b f4430b;

    public GMInterstitialAd(Activity activity, String str) {
        this.f4430b = new b(activity, str);
    }

    public void destroy() {
        b bVar = this.f4430b;
        if (bVar != null) {
            bVar.x();
        }
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public List<AdLoadInfo> getAdLoadInfoList() {
        b bVar = this.f4430b;
        return bVar != null ? bVar.getAdLoadInfoList() : new ArrayList();
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public int getAdNetworkPlatformId() {
        b bVar = this.f4430b;
        if (bVar != null) {
            return bVar.z();
        }
        return -2;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    @NonNull
    public String getAdNetworkRitId() {
        b bVar = this.f4430b;
        return bVar != null ? bVar.A() : "-2";
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public GMAdEcpmInfo getBestEcpm() {
        b bVar = this.f4430b;
        if (bVar != null) {
            return bVar.b();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public List<GMAdEcpmInfo> getCacheList() {
        b bVar = this.f4430b;
        if (bVar != null) {
            return bVar.c();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public List<GMAdEcpmInfo> getMultiBiddingEcpm() {
        b bVar = this.f4430b;
        if (bVar != null) {
            return bVar.f();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    @NonNull
    public String getPreEcpm() {
        b bVar = this.f4430b;
        return bVar != null ? bVar.B() : "-2";
    }

    public boolean isReady() {
        b bVar = this.f4430b;
        if (bVar != null) {
            return bVar.K();
        }
        return false;
    }

    @Override // com.bytedance.msdk.api.v2.ad.GMBaseAd
    public void loadAd(GMAdSlotInterstitial gMAdSlotInterstitial, GMInterstitialAdLoadCallback gMInterstitialAdLoadCallback) {
        super.loadAd(gMAdSlotInterstitial, gMInterstitialAdLoadCallback);
        if (this.f4430b != null) {
            if (!a.f().a(this.f4430b.j(), 2) && gMInterstitialAdLoadCallback != null) {
                gMInterstitialAdLoadCallback.onInterstitialLoadFail(new AdError(AdError.ERROR_CODE_RIT_ADTYPE_NO_SAME, AdError.getMessage(AdError.ERROR_CODE_RIT_ADTYPE_NO_SAME)));
            } else if (a.f().x()) {
                this.f4430b.a(getAdSlot(), gMAdSlotInterstitial, gMInterstitialAdLoadCallback);
            } else if (gMInterstitialAdLoadCallback != null) {
                gMInterstitialAdLoadCallback.onInterstitialLoadFail(new AdError(AdError.ERROR_CODE_INTERACTION_MODULE_UNABLE, AdError.getMessage(AdError.ERROR_CODE_INTERACTION_MODULE_UNABLE)));
            }
        }
    }

    public void setAdInterstitialListener(GMInterstitialAdListener gMInterstitialAdListener) {
        b bVar = this.f4430b;
        if (bVar != null) {
            bVar.a(gMInterstitialAdListener);
        }
    }

    public void showAd(Activity activity) throws JSONException {
        b bVar = this.f4430b;
        if (bVar != null) {
            if (activity == null) {
                Logger.e("TTMediationSDK", "activity can not be null !");
            } else {
                bVar.a(activity);
            }
        }
    }
}
