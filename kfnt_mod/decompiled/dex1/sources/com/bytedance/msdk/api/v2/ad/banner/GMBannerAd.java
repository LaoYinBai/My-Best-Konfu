package com.bytedance.msdk.api.v2.ad.banner;

import android.app.Activity;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do.g;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdLoadInfo;
import com.bytedance.msdk.api.GMAdEcpmInfo;
import com.bytedance.msdk.api.v2.ad.GMBaseAd;
import com.bytedance.msdk.api.v2.slot.GMAdSlotBanner;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GMBannerAd extends GMBaseAd {

    /* renamed from: b, reason: collision with root package name */
    private g f4357b;

    public GMBannerAd(Activity activity, String str) {
        this.f4357b = new g(activity, str);
    }

    public void destroy() {
        g gVar = this.f4357b;
        if (gVar != null) {
            gVar.a();
        }
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public List<AdLoadInfo> getAdLoadInfoList() {
        g gVar = this.f4357b;
        return gVar != null ? gVar.b() : new ArrayList();
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public int getAdNetworkPlatformId() {
        g gVar = this.f4357b;
        if (gVar != null) {
            return gVar.c();
        }
        return -2;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    @NonNull
    public String getAdNetworkRitId() {
        g gVar = this.f4357b;
        return gVar != null ? gVar.d() : "-2";
    }

    @Nullable
    public View getBannerView() {
        g gVar = this.f4357b;
        if (gVar != null) {
            return gVar.e();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public GMAdEcpmInfo getBestEcpm() {
        g gVar = this.f4357b;
        if (gVar != null) {
            return gVar.f();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public List<GMAdEcpmInfo> getCacheList() {
        g gVar = this.f4357b;
        if (gVar != null) {
            return gVar.g();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public List<GMAdEcpmInfo> getMultiBiddingEcpm() {
        g gVar = this.f4357b;
        if (gVar != null) {
            return gVar.h();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    @NonNull
    public String getPreEcpm() {
        g gVar = this.f4357b;
        return gVar != null ? gVar.i() : "-2";
    }

    @VisibleForTesting
    public int getRefreshTime() {
        g gVar = this.f4357b;
        if (gVar != null) {
            return gVar.j();
        }
        return 0;
    }

    @VisibleForTesting
    public boolean isAllSHowCloseBtn() {
        g gVar = this.f4357b;
        if (gVar != null) {
            return gVar.l();
        }
        return false;
    }

    @Override // com.bytedance.msdk.api.v2.ad.GMBaseAd
    public void loadAd(GMAdSlotBanner gMAdSlotBanner, GMBannerAdLoadCallback gMBannerAdLoadCallback) {
        super.loadAd(gMAdSlotBanner, gMBannerAdLoadCallback);
        if (this.f4357b != null) {
            if (!a.f().a(this.f4357b.k(), 1) && gMBannerAdLoadCallback != null) {
                gMBannerAdLoadCallback.onAdFailedToLoad(new AdError(AdError.ERROR_CODE_RIT_ADTYPE_NO_SAME, AdError.getMessage(AdError.ERROR_CODE_RIT_ADTYPE_NO_SAME)));
                return;
            }
            if (a.f().s()) {
                this.f4357b.a(gMAdSlotBanner.isAllowShowCloseBtn());
                this.f4357b.a(getAdSlot(), gMAdSlotBanner, gMBannerAdLoadCallback);
            } else if (gMBannerAdLoadCallback != null) {
                gMBannerAdLoadCallback.onAdFailedToLoad(new AdError(AdError.ERROR_CODE_BANNER_MODULE_UNABLE, AdError.getMessage(AdError.ERROR_CODE_BANNER_MODULE_UNABLE)));
            }
        }
    }

    public void setAdBannerListener(GMBannerAdListener gMBannerAdListener) {
        g gVar = this.f4357b;
        if (gVar != null) {
            gVar.a(gMBannerAdListener);
        }
    }
}
