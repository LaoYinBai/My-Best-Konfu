package com.bytedance.msdk.api.v2.ad.splash;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do.i;
import com.bytedance.msdk.adapter.ThirdSdkInit;
import com.bytedance.msdk.adapter.util.ThreadHelper;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdLoadInfo;
import com.bytedance.msdk.api.GMAdEcpmInfo;
import com.bytedance.msdk.api.v2.GMMediationAdSdk;
import com.bytedance.msdk.api.v2.GMNetworkRequestInfo;
import com.bytedance.msdk.api.v2.GMSettingConfigCallback;
import com.bytedance.msdk.api.v2.ad.GMBaseAd;
import com.bytedance.msdk.api.v2.slot.GMAdSlotSplash;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GMSplashAd extends GMBaseAd {

    /* renamed from: b, reason: collision with root package name */
    private i f4438b;

    public GMSplashAd(Activity activity, String str) {
        this.f4438b = new i(activity, str);
    }

    public void destroy() {
        i iVar = this.f4438b;
        if (iVar != null) {
            iVar.x();
        }
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public List<AdLoadInfo> getAdLoadInfoList() {
        i iVar = this.f4438b;
        return iVar != null ? iVar.getAdLoadInfoList() : new ArrayList();
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public int getAdNetworkPlatformId() {
        i iVar = this.f4438b;
        if (iVar != null) {
            return iVar.z();
        }
        return -2;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    @NonNull
    public String getAdNetworkRitId() {
        i iVar = this.f4438b;
        return iVar != null ? iVar.A() : "-2";
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public GMAdEcpmInfo getBestEcpm() {
        i iVar = this.f4438b;
        if (iVar != null) {
            return iVar.b();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public List<GMAdEcpmInfo> getCacheList() {
        i iVar = this.f4438b;
        if (iVar != null) {
            return iVar.c();
        }
        return null;
    }

    public int[] getMinWindowSize() {
        i iVar = this.f4438b;
        if (iVar == null) {
            return null;
        }
        iVar.K();
        return null;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public List<GMAdEcpmInfo> getMultiBiddingEcpm() {
        i iVar = this.f4438b;
        if (iVar != null) {
            return iVar.f();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    @NonNull
    public String getPreEcpm() {
        i iVar = this.f4438b;
        return iVar != null ? iVar.B() : "-2";
    }

    @Nullable
    public Bitmap getSplashBitMap() {
        i iVar = this.f4438b;
        if (iVar == null) {
            return null;
        }
        iVar.L();
        return null;
    }

    @VisibleForTesting
    public int getTimeOut() {
        i iVar = this.f4438b;
        if (iVar != null) {
            return iVar.M();
        }
        return -1;
    }

    public void loadAd(GMAdSlotSplash gMAdSlotSplash, GMSplashAdLoadCallback gMSplashAdLoadCallback) {
        loadAd(gMAdSlotSplash, null, gMSplashAdLoadCallback);
    }

    public void setAdSplashListener(GMSplashAdListener gMSplashAdListener) {
        i iVar = this.f4438b;
        if (iVar != null) {
            iVar.a(gMSplashAdListener);
        }
    }

    public void setMinWindowListener(GMSplashMinWindowListener gMSplashMinWindowListener) {
        i iVar = this.f4438b;
        if (iVar != null) {
            iVar.a(gMSplashMinWindowListener);
        }
    }

    public void showAd(final ViewGroup viewGroup) {
        if (this.f4438b != null) {
            ThreadHelper.runOnUiThread(new Runnable() { // from class: com.bytedance.msdk.api.v2.ad.splash.GMSplashAd.2
                @Override // java.lang.Runnable
                public void run() throws JSONException {
                    if (GMSplashAd.this.f4438b != null) {
                        GMSplashAd.this.f4438b.a(viewGroup);
                    }
                }
            });
        }
    }

    public boolean showWindowDirect(Rect rect, GMSplashAdListener gMSplashAdListener) {
        i iVar = this.f4438b;
        if (iVar != null) {
            return iVar.a(rect, gMSplashAdListener);
        }
        return false;
    }

    public void splashMinWindowAnimationFinish() {
        i iVar = this.f4438b;
        if (iVar != null) {
            iVar.N();
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.GMBaseAd
    public void loadAd(final GMAdSlotSplash gMAdSlotSplash, final GMNetworkRequestInfo gMNetworkRequestInfo, final GMSplashAdLoadCallback gMSplashAdLoadCallback) {
        super.loadAd(gMAdSlotSplash, gMNetworkRequestInfo, gMSplashAdLoadCallback);
        if (gMNetworkRequestInfo != null && gMNetworkRequestInfo.getAdNetworkFlatFromId() == 1 && !TextUtils.isEmpty(gMNetworkRequestInfo.getAppId()) && !TextUtils.isEmpty(ThirdSdkInit.getTTPangleAppId()) && !gMNetworkRequestInfo.getAppId().equals(ThirdSdkInit.getTTPangleAppId())) {
            if (gMSplashAdLoadCallback != null) {
                gMSplashAdLoadCallback.onSplashAdLoadFail(new AdError(AdError.ERROR_CODE_PANGLE_APPID_NO_SAME, AdError.getMessage(AdError.ERROR_CODE_PANGLE_APPID_NO_SAME)));
                return;
            }
            return;
        }
        if (this.f4438b != null) {
            if (!a.f().a(this.f4438b.j(), 3) && gMSplashAdLoadCallback != null) {
                gMSplashAdLoadCallback.onSplashAdLoadFail(new AdError(AdError.ERROR_CODE_RIT_ADTYPE_NO_SAME, AdError.getMessage(AdError.ERROR_CODE_RIT_ADTYPE_NO_SAME)));
                return;
            }
            if (!a.f().C()) {
                if (gMSplashAdLoadCallback != null) {
                    gMSplashAdLoadCallback.onSplashAdLoadFail(new AdError(AdError.ERROR_CODE_SPLASH_MODULE_UNABLE, AdError.getMessage(AdError.ERROR_CODE_SPLASH_MODULE_UNABLE)));
                }
            } else {
                this.f4438b.b(gMAdSlotSplash.getTimeOut());
                if (a.f().t()) {
                    GMMediationAdSdk.registerConfigCallback(new GMSettingConfigCallback() { // from class: com.bytedance.msdk.api.v2.ad.splash.GMSplashAd.1
                        @Override // com.bytedance.msdk.api.v2.GMSettingConfigCallback
                        public void configLoad() {
                            GMSplashAd.this.f4438b.a(GMSplashAd.this.getAdSlot(), gMAdSlotSplash, gMNetworkRequestInfo, gMSplashAdLoadCallback);
                        }
                    });
                } else {
                    this.f4438b.a(getAdSlot(), gMAdSlotSplash, gMNetworkRequestInfo, gMSplashAdLoadCallback);
                }
            }
        }
    }
}
