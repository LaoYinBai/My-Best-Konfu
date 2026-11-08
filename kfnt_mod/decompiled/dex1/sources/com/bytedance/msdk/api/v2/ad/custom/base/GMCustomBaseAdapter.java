package com.bytedance.msdk.api.v2.ad.custom.base;

import android.content.Context;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.h;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108.c;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.ThreadHelper;
import com.bytedance.msdk.api.AdSlot;
import com.bytedance.msdk.api.v2.ad.custom.GMCustomTTAbsAdLoaderAdapter;
import com.bytedance.msdk.api.v2.ad.custom.GMCustomTTBaseAd;
import com.bytedance.msdk.api.v2.ad.custom.bean.GMCustomServiceConfig;
import com.bytedance.msdk.api.v2.ad.custom.init.GMCustomAdapterConfiguration;
import com.bytedance.msdk.api.v2.slot.GMAdSlotBase;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class GMCustomBaseAdapter {
    public static final int CLICK_TIMES = 60;
    public static final int SHOW_TIMES = 2;

    /* renamed from: b, reason: collision with root package name */
    protected i f4372b;

    /* renamed from: c, reason: collision with root package name */
    protected TTAbsAdLoaderAdapter f4373c;
    protected GMCustomTTBaseAd d;

    /* renamed from: a, reason: collision with root package name */
    protected boolean f4371a = false;
    protected int e = 0;
    protected int f = 0;

    public interface CheckCallback {
        void callback();
    }

    public final void checkClick(CheckCallback checkCallback) {
        int i = this.f;
        if (i >= 60) {
            Logger.e("TTMediationSDK", "自定义Adapter click方法回调次数需要小于60次");
            return;
        }
        this.f = i + 1;
        if (checkCallback != null) {
            checkCallback.callback();
        }
    }

    public final void checkLoadSuccess(CheckCallback checkCallback) {
        if (!isCallLoadSuccessCall()) {
            Logger.e("TTMediationSDK", "自定义Adapter 调用错误需要在load成功之后才可以调用");
        } else if (checkCallback != null) {
            checkCallback.callback();
        }
    }

    public final void checkShow(CheckCallback checkCallback) {
        if (!this.f4371a) {
            Logger.e("TTMediationSDK", "自定义Adapter show方法回调必须由GroMore触发show时才会生效");
            return;
        }
        int i = this.e;
        if (i >= 2) {
            Logger.e("TTMediationSDK", "自定义Adapter show方法回调次数需要小于2次");
            return;
        }
        this.e = i + 1;
        if (checkCallback != null) {
            checkCallback.callback();
        }
    }

    public final String getCustomADNName() {
        i iVar = this.f4372b;
        return iVar == null ? "" : iVar.e();
    }

    public final boolean isCallLoadFailCall() {
        TTAbsAdLoaderAdapter tTAbsAdLoaderAdapter = this.f4373c;
        if (tTAbsAdLoaderAdapter != null) {
            return tTAbsAdLoaderAdapter.hasNotifyFail();
        }
        return false;
    }

    public final boolean isCallLoadSuccessCall() {
        TTAbsAdLoaderAdapter tTAbsAdLoaderAdapter = this.f4373c;
        if (tTAbsAdLoaderAdapter != null) {
            return tTAbsAdLoaderAdapter.hasNotifySuccess();
        }
        return false;
    }

    public abstract boolean isReadyInner();

    public abstract void loadCustom(Context context, GMAdSlotBase gMAdSlotBase, GMCustomServiceConfig gMCustomServiceConfig);

    public final void loadInner(Context context, AdSlot adSlot, final GMAdSlotBase gMAdSlotBase, final i iVar, Map<String, Object> map, h hVar, int i, TTAbsAdLoaderAdapter.AdapterLoaderListener adapterLoaderListener) {
        Logger.e("TTMediationSDK_SDK_Init", "自定义ADN 开始加载广告对象 ----------   adSlot = " + iVar.d());
        this.f4372b = iVar;
        map.put("const_is_custom", true);
        this.f4373c = new GMCustomTTAbsAdLoaderAdapter() { // from class: com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter.1
            @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter
            public void loadAd(final Context context2, Map<String, Object> map2) {
                final GMCustomServiceConfig gMCustomServiceConfig = new GMCustomServiceConfig(GMCustomBaseAdapter.this.getCustomADNName(), iVar.d(), iVar.p(), iVar.g());
                GMCustomAdapterConfiguration gMCustomAdapterConfigurationA = c.a(iVar.e());
                ((GMCustomTTAbsAdLoaderAdapter) this).f4360a = gMCustomAdapterConfigurationA != null ? gMCustomAdapterConfigurationA.getNetworkSdkVersion() : "";
                ThreadHelper.runOnThreadPool(new Runnable() { // from class: com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                        GMCustomBaseAdapter.this.loadCustom(context2, gMAdSlotBase, gMCustomServiceConfig);
                    }
                });
            }
        };
        this.f4373c.setAdapterListener(adapterLoaderListener);
        this.f4373c.loadAdInter(context, iVar, map, adSlot, hVar, i);
    }

    public void onDestroy() {
    }

    public void onPause() {
    }

    public void onResume() {
    }
}
