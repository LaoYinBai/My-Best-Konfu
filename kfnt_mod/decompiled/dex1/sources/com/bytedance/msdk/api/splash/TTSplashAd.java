package com.bytedance.msdk.api.splash;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do.i;
import com.bytedance.msdk.adapter.ThirdSdkInit;
import com.bytedance.msdk.adapter.util.ThreadHelper;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdLoadInfo;
import com.bytedance.msdk.api.AdSlot;
import com.bytedance.msdk.api.GMAdEcpmInfo;
import com.bytedance.msdk.api.base.TTLoadBase;
import com.bytedance.msdk.api.v2.GMMediationAdSdk;
import com.bytedance.msdk.api.v2.GMNetworkRequestInfo;
import com.bytedance.msdk.api.v2.GMSettingConfigCallback;
import com.bytedance.msdk.api.v2.slot.GMAdSlotSplash;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class TTSplashAd extends TTLoadBase {

    /* renamed from: a, reason: collision with root package name */
    private i f4327a;

    public TTSplashAd(Activity activity, String str) {
        this.f4327a = new i(activity, str);
    }

    public void destroy() {
        i iVar = this.f4327a;
        if (iVar != null) {
            iVar.x();
        }
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public List<AdLoadInfo> getAdLoadInfoList() {
        i iVar = this.f4327a;
        return iVar != null ? iVar.getAdLoadInfoList() : new ArrayList();
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public int getAdNetworkPlatformId() {
        i iVar = this.f4327a;
        if (iVar != null) {
            return iVar.z();
        }
        return -2;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    @NonNull
    public String getAdNetworkRitId() {
        i iVar = this.f4327a;
        return iVar != null ? iVar.A() : "-2";
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public GMAdEcpmInfo getBestEcpm() {
        i iVar = this.f4327a;
        if (iVar != null) {
            return iVar.b();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public List<GMAdEcpmInfo> getCacheList() {
        i iVar = this.f4327a;
        if (iVar != null) {
            return iVar.c();
        }
        return null;
    }

    public int[] getMinWindowSize() {
        i iVar = this.f4327a;
        if (iVar == null) {
            return null;
        }
        iVar.K();
        return null;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    public List<GMAdEcpmInfo> getMultiBiddingEcpm() {
        i iVar = this.f4327a;
        if (iVar != null) {
            return iVar.f();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.base.TTLoadBase
    @NonNull
    public String getPreEcpm() {
        i iVar = this.f4327a;
        return iVar != null ? iVar.B() : "-2";
    }

    @Nullable
    public Bitmap getSplashBitMap() {
        i iVar = this.f4327a;
        if (iVar == null) {
            return null;
        }
        iVar.L();
        return null;
    }

    public void loadAd(AdSlot adSlot, TTSplashAdLoadCallback tTSplashAdLoadCallback, int i) {
        loadAd(adSlot, null, tTSplashAdLoadCallback, i);
    }

    public void setMinWindowListener(TTSplashMinWindowListener tTSplashMinWindowListener) {
        i iVar = this.f4327a;
        if (iVar != null) {
            iVar.a(tTSplashMinWindowListener);
        }
    }

    public void setTTAdSplashListener(TTSplashAdListener tTSplashAdListener) {
        i iVar = this.f4327a;
        if (iVar != null) {
            iVar.a(tTSplashAdListener);
        }
    }

    public void showAd(final ViewGroup viewGroup) {
        if (this.f4327a != null) {
            ThreadHelper.runOnUiThread(new Runnable() { // from class: com.bytedance.msdk.api.splash.TTSplashAd.2
                @Override // java.lang.Runnable
                public void run() throws JSONException {
                    if (TTSplashAd.this.f4327a != null) {
                        TTSplashAd.this.f4327a.a(viewGroup);
                    }
                }
            });
        }
    }

    public boolean showWindowDirect(Rect rect, TTSplashAdListener tTSplashAdListener) {
        i iVar = this.f4327a;
        if (iVar != null) {
            return iVar.a(rect, tTSplashAdListener);
        }
        return false;
    }

    public void splashMinWindowAnimationFinish() {
        i iVar = this.f4327a;
        if (iVar != null) {
            iVar.N();
        }
    }

    public void loadAd(final AdSlot adSlot, final GMNetworkRequestInfo gMNetworkRequestInfo, final TTSplashAdLoadCallback tTSplashAdLoadCallback, final int i) {
        if (gMNetworkRequestInfo != null && gMNetworkRequestInfo.getAdNetworkFlatFromId() == 1 && !TextUtils.isEmpty(gMNetworkRequestInfo.getAppId()) && !TextUtils.isEmpty(ThirdSdkInit.getTTPangleAppId()) && !gMNetworkRequestInfo.getAppId().equals(ThirdSdkInit.getTTPangleAppId())) {
            if (tTSplashAdLoadCallback != null) {
                tTSplashAdLoadCallback.onSplashAdLoadFail(new AdError(AdError.ERROR_CODE_PANGLE_APPID_NO_SAME, AdError.getMessage(AdError.ERROR_CODE_PANGLE_APPID_NO_SAME)));
                return;
            }
            return;
        }
        if (this.f4327a != null) {
            if (!a.f().a(this.f4327a.j(), 3) && tTSplashAdLoadCallback != null) {
                tTSplashAdLoadCallback.onSplashAdLoadFail(new AdError(AdError.ERROR_CODE_RIT_ADTYPE_NO_SAME, AdError.getMessage(AdError.ERROR_CODE_RIT_ADTYPE_NO_SAME)));
                return;
            }
            if (!a.f().C()) {
                if (tTSplashAdLoadCallback != null) {
                    tTSplashAdLoadCallback.onSplashAdLoadFail(new AdError(AdError.ERROR_CODE_SPLASH_MODULE_UNABLE, AdError.getMessage(AdError.ERROR_CODE_SPLASH_MODULE_UNABLE)));
                }
            } else if (a.f().t()) {
                GMMediationAdSdk.registerConfigCallback(new GMSettingConfigCallback() { // from class: com.bytedance.msdk.api.splash.TTSplashAd.1
                    @Override // com.bytedance.msdk.api.v2.GMSettingConfigCallback
                    public void configLoad() {
                        TTSplashAd.this.f4327a.a(adSlot, (GMAdSlotSplash) null, gMNetworkRequestInfo, tTSplashAdLoadCallback, i);
                    }
                });
            } else {
                this.f4327a.a(adSlot, (GMAdSlotSplash) null, gMNetworkRequestInfo, tTSplashAdLoadCallback, i);
            }
        }
    }

    @Deprecated
    public TTSplashAd(Activity activity, View view, String str) {
        this.f4327a = new i(activity, view, str);
    }
}
