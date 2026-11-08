package com.bytedance.msdk.api.reward;

import android.app.Activity;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do.h;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdLoadInfo;
import com.bytedance.msdk.api.AdSlot;
import com.bytedance.msdk.api.GMAdEcpmInfo;
import com.bytedance.msdk.api.TTAdConstant;
import com.bytedance.msdk.api.base.TTLoadBase;
import com.bytedance.msdk.api.v2.slot.GMAdSlotRewardVideo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class TTRewardAd extends TTLoadBase {

    /* renamed from: a, reason: collision with root package name */
    private h f4326a;

    public TTRewardAd(Activity activity, String str) {
        this.f4326a = new h(activity, str);
    }

    public void destroy() {
        h hVar = this.f4326a;
        if (hVar != null) {
            hVar.x();
        }
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public List<AdLoadInfo> getAdLoadInfoList() {
        h hVar = this.f4326a;
        return hVar != null ? hVar.getAdLoadInfoList() : new ArrayList();
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public int getAdNetworkPlatformId() {
        if (!hasPlatFormPermission()) {
            return -3;
        }
        h hVar = this.f4326a;
        if (hVar != null) {
            return hVar.z();
        }
        return -2;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    @NonNull
    public String getAdNetworkRitId() {
        if (!hasPlatFormPermission()) {
            return "-3";
        }
        h hVar = this.f4326a;
        return hVar != null ? hVar.A() : "-2";
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public GMAdEcpmInfo getBestEcpm() {
        h hVar = this.f4326a;
        if (hVar != null) {
            return hVar.b();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public List<GMAdEcpmInfo> getCacheList() {
        h hVar = this.f4326a;
        if (hVar != null) {
            return hVar.c();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public List<GMAdEcpmInfo> getMultiBiddingEcpm() {
        h hVar = this.f4326a;
        if (hVar != null) {
            return hVar.f();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    @NonNull
    public String getPreEcpm() {
        if (!hasPlatFormPermission()) {
            return "-3";
        }
        h hVar = this.f4326a;
        return hVar != null ? hVar.B() : "-2";
    }

    public boolean isReady() {
        h hVar = this.f4326a;
        if (hVar != null) {
            return hVar.K();
        }
        return false;
    }

    public void loadRewardAd(AdSlot adSlot, @NonNull TTRewardedAdLoadCallback tTRewardedAdLoadCallback) {
        if (this.f4326a != null) {
            if (!a.f().a(this.f4326a.j(), 7) && tTRewardedAdLoadCallback != null) {
                tTRewardedAdLoadCallback.onRewardVideoLoadFail(new AdError(AdError.ERROR_CODE_RIT_ADTYPE_NO_SAME, AdError.getMessage(AdError.ERROR_CODE_RIT_ADTYPE_NO_SAME)));
            } else if (a.f().B()) {
                this.f4326a.a(adSlot, (GMAdSlotRewardVideo) null, tTRewardedAdLoadCallback);
            } else if (tTRewardedAdLoadCallback != null) {
                tTRewardedAdLoadCallback.onRewardVideoLoadFail(new AdError(AdError.ERROR_CODE_REWARD_MODULE_UNABLE, AdError.getMessage(AdError.ERROR_CODE_REWARD_MODULE_UNABLE)));
            }
        }
    }

    public void setRewardAdListener(TTRewardedAdListener tTRewardedAdListener) {
        h hVar = this.f4326a;
        if (hVar != null) {
            hVar.a(tTRewardedAdListener);
        }
    }

    @MainThread
    @Deprecated
    public void showRewardAd(Activity activity, TTRewardedAdListener tTRewardedAdListener) throws JSONException {
        showRewardAd(activity, null, tTRewardedAdListener);
    }

    @MainThread
    public void showRewardAd(Activity activity) throws JSONException {
        h hVar = this.f4326a;
        if (hVar != null) {
            hVar.a(activity, (Map<TTAdConstant.GroMoreExtraKey, Object>) null);
        }
    }

    @MainThread
    @Deprecated
    public void showRewardAd(Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map, TTRewardedAdListener tTRewardedAdListener) throws JSONException {
        h hVar = this.f4326a;
        if (hVar != null) {
            hVar.a(tTRewardedAdListener);
            this.f4326a.a(activity, map);
        }
    }

    @MainThread
    public void showRewardAd(Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map) throws JSONException {
        h hVar = this.f4326a;
        if (hVar != null) {
            hVar.a(activity, map);
        }
    }
}
