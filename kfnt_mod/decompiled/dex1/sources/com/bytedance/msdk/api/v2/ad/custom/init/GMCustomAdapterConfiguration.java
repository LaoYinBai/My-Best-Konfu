package com.bytedance.msdk.api.v2.ad.custom.init;

import android.content.Context;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.api.v2.ad.custom.bean.GMCustomInitConfig;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class GMCustomAdapterConfiguration {

    /* renamed from: a, reason: collision with root package name */
    private static final AtomicBoolean f4395a;

    /* renamed from: b, reason: collision with root package name */
    private GMCustomInitConfig f4396b;

    static {
        String str = "TTMediationSDK_" + GMCustomAdapterConfiguration.class.getSimpleName();
        f4395a = new AtomicBoolean(false);
    }

    public final void callInitSuccess() {
        f4395a.set(true);
    }

    public abstract String getAdapterSdkVersion();

    public final String getCustomADNName() {
        return this.f4396b.getADNName();
    }

    public abstract String getNetworkSdkVersion();

    public abstract void initializeADN(Context context, GMCustomInitConfig gMCustomInitConfig, Map<String, Object> map);

    public final void initializeInnerADN(Context context, GMCustomInitConfig gMCustomInitConfig, Map<String, Object> map) {
        Logger.e("TTMediationSDK_SDK_Init", "自定义ADN 初始化成功 ----------     adnName = " + gMCustomInitConfig.getADNName() + " ADN version = " + getNetworkSdkVersion() + " Adapter version = " + getAdapterSdkVersion());
        f4395a.set(false);
        this.f4396b = gMCustomInitConfig;
        initializeADN(context, this.f4396b, map);
    }

    public final boolean isInit() {
        return f4395a.get();
    }
}
