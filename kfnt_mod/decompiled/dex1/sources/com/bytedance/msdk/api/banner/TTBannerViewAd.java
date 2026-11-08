package com.bytedance.msdk.api.banner;

import android.app.Activity;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do.g;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdLoadInfo;
import com.bytedance.msdk.api.AdSlot;
import com.bytedance.msdk.api.GMAdEcpmInfo;
import com.bytedance.msdk.api.base.TTLoadBase;
import java.util.ArrayList;
import java.util.List;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class TTBannerViewAd extends TTLoadBase {

    /* renamed from: a, reason: collision with root package name */
    private g f4314a;

    public TTBannerViewAd(Activity activity, String str) {
        this.f4314a = new g(activity, str);
    }

    public void destroy() {
        g gVar = this.f4314a;
        if (gVar != null) {
            gVar.a();
        }
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public List<AdLoadInfo> getAdLoadInfoList() {
        g gVar = this.f4314a;
        return gVar != null ? gVar.b() : new ArrayList();
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public int getAdNetworkPlatformId() {
        if (!hasPlatFormPermission()) {
            return -3;
        }
        g gVar = this.f4314a;
        if (gVar != null) {
            return gVar.c();
        }
        return -2;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    @NonNull
    public String getAdNetworkRitId() {
        if (!hasPlatFormPermission()) {
            return "-3";
        }
        g gVar = this.f4314a;
        return gVar != null ? gVar.d() : "-2";
    }

    @Nullable
    public View getBannerView() {
        g gVar = this.f4314a;
        if (gVar != null) {
            return gVar.e();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public GMAdEcpmInfo getBestEcpm() {
        g gVar = this.f4314a;
        if (gVar != null) {
            return gVar.f();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public List<GMAdEcpmInfo> getCacheList() {
        g gVar = this.f4314a;
        if (gVar != null) {
            return gVar.g();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public List<GMAdEcpmInfo> getMultiBiddingEcpm() {
        g gVar = this.f4314a;
        if (gVar != null) {
            return gVar.h();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    @NonNull
    public String getPreEcpm() {
        if (!hasPlatFormPermission()) {
            return "-3";
        }
        g gVar = this.f4314a;
        return gVar != null ? gVar.i() : "-2";
    }

    public void loadAd(AdSlot adSlot, TTAdBannerLoadCallBack tTAdBannerLoadCallBack) {
        if (this.f4314a != null) {
            if (!a.f().a(this.f4314a.k(), 1) && tTAdBannerLoadCallBack != null) {
                tTAdBannerLoadCallBack.onAdFailedToLoad(new AdError(AdError.ERROR_CODE_RIT_ADTYPE_NO_SAME, AdError.getMessage(AdError.ERROR_CODE_RIT_ADTYPE_NO_SAME)));
            } else if (a.f().s()) {
                this.f4314a.a(adSlot, null, tTAdBannerLoadCallBack);
            } else if (tTAdBannerLoadCallBack != null) {
                tTAdBannerLoadCallBack.onAdFailedToLoad(new AdError(AdError.ERROR_CODE_BANNER_MODULE_UNABLE, AdError.getMessage(AdError.ERROR_CODE_BANNER_MODULE_UNABLE)));
            }
        }
    }

    public void setAllowShowCloseBtn(boolean z) {
        g gVar = this.f4314a;
        if (gVar != null) {
            gVar.a(z);
        }
    }

    @Deprecated
    public void setRefreshTime(int i) {
    }

    public void setTTAdBannerListener(TTAdBannerListener tTAdBannerListener) {
        g gVar = this.f4314a;
        if (gVar != null) {
            gVar.a(tTAdBannerListener);
        }
    }
}
