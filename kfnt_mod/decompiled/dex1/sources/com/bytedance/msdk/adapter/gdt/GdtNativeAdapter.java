package com.bytedance.msdk.adapter.gdt;

import android.content.Context;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.api.TTVideoOption;
import com.qq.e.comm.managers.status.SDKStatus;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GdtNativeAdapter extends TTAbsAdLoaderAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f3990a;

    /* renamed from: b, reason: collision with root package name */
    private TTVideoOption f3991b;

    private void a() {
        GdtNativeExpressLoader gdtNativeExpressLoader = new GdtNativeExpressLoader();
        gdtNativeExpressLoader.a(getAdLoadCount()).a(getAdSlotId()).c(this.mAdSlot.getImgAcceptedWidth()).b(this.mAdSlot.getImgAcceptedHeight()).a(this.f3991b);
        gdtNativeExpressLoader.loadAd(this.f3990a, isClientBidding(), this);
    }

    private void b() {
        GdtNativeLoader gdtNativeLoader = new GdtNativeLoader();
        gdtNativeLoader.a(getAdLoadCount()).a(this.mAdSlot.getGdtNativeAdLogoParams()).a(getAdSlotId()).a(this.f3991b);
        gdtNativeLoader.loadAd(this.f3990a, isClientBidding(), this);
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void destroy() {
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getAdNetWorkName() {
        return "gdt";
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public String getSdkVersion() {
        return SDKStatus.getSDKVersion();
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
    public void loadAd(Context context, Map<String, Object> map) {
        this.f3990a = context;
        if (map != null) {
            this.f3991b = this.mAdSlot.getTTVideoOption();
            Object obj = map.get("tt_ad_origin_type");
            if (obj != null) {
                int iIntValue = ((Integer) obj).intValue();
                if (iIntValue == 1 || iIntValue == 3) {
                    a();
                    return;
                } else if (iIntValue == 2) {
                    b();
                    return;
                }
            }
            int adStyleType = this.mAdSlot.getAdStyleType();
            if (adStyleType == 1) {
                a();
            } else if (adStyleType == 2) {
                b();
            }
        }
    }
}
