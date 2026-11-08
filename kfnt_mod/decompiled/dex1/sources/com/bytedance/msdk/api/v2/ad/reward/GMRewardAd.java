package com.bytedance.msdk.api.v2.ad.reward;

import android.app.Activity;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do.h;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdLoadInfo;
import com.bytedance.msdk.api.GMAdEcpmInfo;
import com.bytedance.msdk.api.TTAdConstant;
import com.bytedance.msdk.api.v2.ad.GMBaseAd;
import com.bytedance.msdk.api.v2.slot.GMAdSlotRewardVideo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GMRewardAd extends GMBaseAd {

    /* renamed from: b, reason: collision with root package name */
    private h f4437b;

    public GMRewardAd(Activity activity, String str) {
        this.f4437b = new h(activity, str);
    }

    public void destroy() {
        h hVar = this.f4437b;
        if (hVar != null) {
            hVar.x();
        }
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public List<AdLoadInfo> getAdLoadInfoList() {
        h hVar = this.f4437b;
        return hVar != null ? hVar.getAdLoadInfoList() : new ArrayList();
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public int getAdNetworkPlatformId() {
        h hVar = this.f4437b;
        if (hVar != null) {
            return hVar.z();
        }
        return -2;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    @NonNull
    public String getAdNetworkRitId() {
        h hVar = this.f4437b;
        return hVar != null ? hVar.A() : "-2";
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public GMAdEcpmInfo getBestEcpm() {
        h hVar = this.f4437b;
        if (hVar != null) {
            return hVar.b();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public List<GMAdEcpmInfo> getCacheList() {
        h hVar = this.f4437b;
        if (hVar != null) {
            return hVar.c();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public List<GMAdEcpmInfo> getMultiBiddingEcpm() {
        h hVar = this.f4437b;
        if (hVar != null) {
            return hVar.f();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    @NonNull
    public String getPreEcpm() {
        h hVar = this.f4437b;
        return hVar != null ? hVar.B() : "-2";
    }

    public boolean isReady() {
        h hVar = this.f4437b;
        if (hVar != null) {
            return hVar.K();
        }
        return false;
    }

    @Override // com.bytedance.msdk.api.v2.ad.GMBaseAd
    public void loadAd(GMAdSlotRewardVideo gMAdSlotRewardVideo, @NonNull GMRewardedAdLoadCallback gMRewardedAdLoadCallback) {
        super.loadAd(gMAdSlotRewardVideo, gMRewardedAdLoadCallback);
        if (this.f4437b != null) {
            if (!a.f().a(this.f4437b.j(), 7) && gMRewardedAdLoadCallback != null) {
                gMRewardedAdLoadCallback.onRewardVideoLoadFail(new AdError(AdError.ERROR_CODE_RIT_ADTYPE_NO_SAME, AdError.getMessage(AdError.ERROR_CODE_RIT_ADTYPE_NO_SAME)));
            } else if (a.f().B()) {
                this.f4437b.a(getAdSlot(), gMAdSlotRewardVideo, gMRewardedAdLoadCallback);
            } else if (gMRewardedAdLoadCallback != null) {
                gMRewardedAdLoadCallback.onRewardVideoLoadFail(new AdError(AdError.ERROR_CODE_REWARD_MODULE_UNABLE, AdError.getMessage(AdError.ERROR_CODE_REWARD_MODULE_UNABLE)));
            }
        }
    }

    public void setRewardAdListener(GMRewardedAdListener gMRewardedAdListener) {
        h hVar = this.f4437b;
        if (hVar != null) {
            hVar.a(gMRewardedAdListener);
        }
    }

    @MainThread
    public void showRewardAd(Activity activity) throws JSONException {
        h hVar = this.f4437b;
        if (hVar != null) {
            hVar.a(activity, (Map<TTAdConstant.GroMoreExtraKey, Object>) null);
        }
    }

    @MainThread
    public void showRewardAd(Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map) throws JSONException {
        h hVar = this.f4437b;
        if (hVar != null) {
            hVar.a(activity, map);
        }
    }
}
