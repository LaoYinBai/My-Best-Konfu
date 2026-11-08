package com.bytedance.msdk.api.fullVideo;

import android.app.Activity;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do.e;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdLoadInfo;
import com.bytedance.msdk.api.AdSlot;
import com.bytedance.msdk.api.GMAdEcpmInfo;
import com.bytedance.msdk.api.TTAdConstant;
import com.bytedance.msdk.api.base.TTLoadBase;
import com.bytedance.msdk.api.v2.ad.fullvideo.GMFullVideoAdListener;
import com.bytedance.msdk.api.v2.slot.GMAdSlotFullVideo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class TTFullVideoAd extends TTLoadBase {

    /* renamed from: a, reason: collision with root package name */
    private e f4319a;

    public TTFullVideoAd(Activity activity, String str) {
        this.f4319a = new e(activity, str);
    }

    public void destroy() {
        e eVar = this.f4319a;
        if (eVar != null) {
            eVar.x();
        }
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public List<AdLoadInfo> getAdLoadInfoList() {
        e eVar = this.f4319a;
        return eVar != null ? eVar.getAdLoadInfoList() : new ArrayList();
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public int getAdNetworkPlatformId() {
        if (!hasPlatFormPermission()) {
            return -3;
        }
        e eVar = this.f4319a;
        if (eVar != null) {
            return eVar.z();
        }
        return -2;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    @NonNull
    public String getAdNetworkRitId() {
        if (!hasPlatFormPermission()) {
            return "-3";
        }
        e eVar = this.f4319a;
        return eVar != null ? eVar.A() : "-2";
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public GMAdEcpmInfo getBestEcpm() {
        e eVar = this.f4319a;
        if (eVar != null) {
            return eVar.b();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public List<GMAdEcpmInfo> getCacheList() {
        e eVar = this.f4319a;
        if (eVar != null) {
            return eVar.c();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public List<GMAdEcpmInfo> getMultiBiddingEcpm() {
        e eVar = this.f4319a;
        if (eVar != null) {
            return eVar.f();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    @NonNull
    public String getPreEcpm() {
        if (!hasPlatFormPermission()) {
            return "-3";
        }
        e eVar = this.f4319a;
        return eVar != null ? eVar.B() : "-2";
    }

    public boolean isReady() {
        e eVar = this.f4319a;
        if (eVar != null) {
            return eVar.K();
        }
        return false;
    }

    public void loadFullAd(AdSlot adSlot, @NonNull TTFullVideoAdLoadCallback tTFullVideoAdLoadCallback) {
        if (this.f4319a != null) {
            if (!a.f().a(this.f4319a.j(), 8) && tTFullVideoAdLoadCallback != null) {
                tTFullVideoAdLoadCallback.onFullVideoLoadFail(new AdError(AdError.ERROR_CODE_RIT_ADTYPE_NO_SAME, AdError.getMessage(AdError.ERROR_CODE_RIT_ADTYPE_NO_SAME)));
            } else if (a.f().w()) {
                this.f4319a.a(adSlot, (GMAdSlotFullVideo) null, tTFullVideoAdLoadCallback);
            } else if (tTFullVideoAdLoadCallback != null) {
                tTFullVideoAdLoadCallback.onFullVideoLoadFail(new AdError(AdError.ERROR_CODE_FULL_MODULE_UNABLE, AdError.getMessage(AdError.ERROR_CODE_FULL_MODULE_UNABLE)));
            }
        }
    }

    public void setFullVideoAdListener(GMFullVideoAdListener gMFullVideoAdListener) {
        e eVar = this.f4319a;
        if (eVar != null) {
            eVar.a(gMFullVideoAdListener);
        }
    }

    @MainThread
    @Deprecated
    public void showFullAd(Activity activity, TTFullVideoAdListener tTFullVideoAdListener) throws JSONException {
        showFullAd(activity, null, tTFullVideoAdListener);
    }

    @MainThread
    public void showFullAd(Activity activity) throws JSONException {
        e eVar = this.f4319a;
        if (eVar != null) {
            eVar.a(activity, (Map<TTAdConstant.GroMoreExtraKey, Object>) null);
        }
    }

    @MainThread
    @Deprecated
    public void showFullAd(Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map, TTFullVideoAdListener tTFullVideoAdListener) throws JSONException {
        e eVar = this.f4319a;
        if (eVar != null) {
            eVar.a(tTFullVideoAdListener);
            this.f4319a.a(activity, map);
        }
    }

    @MainThread
    public void showFullAd(Activity activity, Map<TTAdConstant.GroMoreExtraKey, Object> map) throws JSONException {
        e eVar = this.f4319a;
        if (eVar != null) {
            eVar.a(activity, map);
        }
    }
}
