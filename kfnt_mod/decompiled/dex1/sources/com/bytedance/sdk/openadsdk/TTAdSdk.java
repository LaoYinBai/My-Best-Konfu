package com.bytedance.sdk.openadsdk;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import com.bytedance.sdk.openadsdk.api.b.d;
import com.bytedance.sdk.openadsdk.api.plugin.g;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class TTAdSdk {
    public static final String INITIALIZER_CLASS_NAME = "com.bytedance.sdk.openadsdk.core.AdSdkInitializerHolder";

    /* renamed from: a, reason: collision with root package name */
    private static final TTInitializer f5176a = new g();

    public interface InitCallback {
        void fail(int i, String str);

        void success();
    }

    public static boolean isInitSuccess() {
        if (f5176a != null) {
            return f5176a.isInitSuccess();
        }
        return false;
    }

    @Deprecated
    public static TTAdManager init(Context context, TTAdConfig tTAdConfig) {
        d.a("Please call init(final Context context, final TTAdConfig config, final InitCallback callback), this method will be deprecated");
        a(context, tTAdConfig);
        if (f5176a != null) {
            return f5176a.init(context, tTAdConfig);
        }
        return null;
    }

    public static void init(Context context, TTAdConfig tTAdConfig, InitCallback initCallback) {
        a(context, tTAdConfig);
        if (f5176a == null) {
            initCallback.fail(4100, "Load initializer failed");
        } else {
            f5176a.init(context, tTAdConfig, initCallback);
        }
    }

    private static void a(Context context, TTAdConfig tTAdConfig) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            d.a("Wrong Thread ! Please exec TTAdSdk.init in main thread.");
        }
        a(context, "Context is null, please check.");
        a(tTAdConfig, "TTAdConfig is null, please check.");
        TTAppContextHolder.setContext(context);
        if (tTAdConfig.isDebug()) {
            d.a();
        }
        tTAdConfig.setExtra(TTAdConstant.PANGLE_INIT_START_TIME, Long.valueOf(SystemClock.elapsedRealtime()));
        tTAdConfig.setExtra(TTAdConstant.KEY_S_C, "internal");
    }

    public static TTAdManager getAdManager() {
        if (f5176a != null) {
            return f5176a.getAdManager();
        }
        return null;
    }

    public static void updateAdConfig(TTAdConfig tTAdConfig) {
        TTAdManager adManager;
        if (tTAdConfig == null || (adManager = f5176a.getAdManager()) == null) {
            return;
        }
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(tTAdConfig.getData())) {
            bundle.putString("extra_data", tTAdConfig.getData());
        }
        if (!TextUtils.isEmpty(tTAdConfig.getKeywords())) {
            bundle.putString("keywords", tTAdConfig.getKeywords());
        }
        if (bundle.keySet().isEmpty()) {
            return;
        }
        adManager.getExtra(AdConfig.class, bundle);
    }

    public static void updatePaid(boolean z) {
        TTAdManager adManager = f5176a.getAdManager();
        if (adManager == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean("is_paid", z);
        if (bundle.keySet().isEmpty()) {
            return;
        }
        adManager.getExtra(AdConfig.class, bundle);
    }

    private static void a(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException(str);
        }
    }
}
