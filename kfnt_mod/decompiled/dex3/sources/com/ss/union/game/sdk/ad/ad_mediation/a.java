package com.ss.union.game.sdk.ad.ad_mediation;

import android.util.Log;
import com.bun.miitmdid.core.JLibrary;
import com.bun.miitmdid.core.MdidSdkHelper;
import com.bun.supplier.IIdentifierListener;
import com.bun.supplier.IdSupplier;
import com.bytedance.msdk.api.v2.GMAdConfig;
import com.bytedance.msdk.api.v2.GMMediationAdSdk;
import com.bytedance.msdk.api.v2.GMPangleOption;
import com.bytedance.msdk.api.v2.GMPrivacyConfig;
import com.sigmob.sdk.common.Constants;
import com.ss.android.download.api.config.IDownloadCustomChecker;
import com.ss.android.downloadlib.TTDownloader;
import com.ss.union.game.sdk.common.util.GlobalApplicationUtils;
import com.ss.union.game.sdk.common.util.SPUtils;
import com.ss.union.game.sdk.common.util.StringUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    public static String a = null;
    private static final String b = "KEY_SP_OAID";
    private static boolean c = false;
    private static String d = "";

    public static void a(String str, String str2, String str3, boolean z) {
        a = str;
        com.ss.union.game.sdk.ad.ad_mediation.b.a.a();
        try {
            c();
        } catch (Throwable th) {
            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("initTTAdSDK  initOaid," + th.getMessage());
            th.printStackTrace();
        }
        try {
            TTDownloader.inst(GlobalApplicationUtils.getContext()).getDownloadConfigure().setDownloadCustomChecker(new IDownloadCustomChecker() { // from class: com.ss.union.game.sdk.ad.ad_mediation.a.1
                @Override // com.ss.android.download.api.config.IDownloadCustomChecker
                public boolean isOpenUpgradeChecker() {
                    return true;
                }
            });
            GMPangleOption.Builder isUseTextureView = new GMPangleOption.Builder().setIsPaid(com.ss.union.game.sdk.ad.ad_mediation.b.a.b()).setAllowShowNotify(com.ss.union.game.sdk.ad.ad_mediation.b.a.e()).setAllowShowPageWhenScreenLock(com.ss.union.game.sdk.ad.ad_mediation.b.a.d()).setIsUseTextureView(com.ss.union.game.sdk.ad.ad_mediation.b.a.c());
            if (!StringUtils.isEmpty(com.ss.union.game.sdk.ad.ad_mediation.b.a.f())) {
                isUseTextureView.setData(com.ss.union.game.sdk.ad.ad_mediation.b.a.f());
            }
            if (!StringUtils.isEmpty(com.ss.union.game.sdk.ad.ad_mediation.b.a.g())) {
                isUseTextureView.setKeywords(com.ss.union.game.sdk.ad.ad_mediation.b.a.g());
            }
            if (com.ss.union.game.sdk.ad.ad_mediation.b.a.h() != null && com.ss.union.game.sdk.ad.ad_mediation.b.a.h().length > 0) {
                isUseTextureView.setDirectDownloadNetworkType(com.ss.union.game.sdk.ad.ad_mediation.b.a.h());
            }
            GMMediationAdSdk.initialize(GlobalApplicationUtils.getContext(), new GMAdConfig.Builder().setAppId(str).setAppName(str2).setPublisherDid(str3).setDebug(z).setOpenAdnTest(z).setPangleOption(isUseTextureView.build()).setPrivacyConfig(new GMPrivacyConfig() { // from class: com.ss.union.game.sdk.ad.ad_mediation.a.2
                public String getDevOaid() {
                    return a.d;
                }

                public boolean isLimitPersonalAds() {
                    return Constants.FAIL.equals(com.ss.union.game.sdk.ad.ad_mediation.b.a.i());
                }
            }).build());
            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("initUnionAdSdkDone() -- done");
            c = true;
        } catch (Exception e) {
            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("initUnifyADSDKDone() exception:" + Log.getStackTraceString(e));
        }
    }

    public static boolean a() {
        return c;
    }

    public static void a(String str) {
        com.ss.union.game.sdk.ad.ad_mediation.d.b.a("syncDidToAd to M-SDK");
        com.ss.union.game.sdk.ad.ad_mediation.b.a.a(str);
        com.ss.union.game.sdk.ad.ad_mediation.b.a.c(com.ss.union.game.sdk.ad.ad_mediation.b.a.f());
        com.ss.union.game.sdk.ad.ad_mediation.b.a.d(str);
    }

    private static void c() {
        d = SPUtils.getInstance().getString(b);
        final long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            JLibrary.InitEntry(GlobalApplicationUtils.getContext());
        } catch (Throwable th) {
            th.printStackTrace();
        }
        int iInitSdk = MdidSdkHelper.InitSdk(GlobalApplicationUtils.getContext(), true, new IIdentifierListener() { // from class: com.ss.union.game.sdk.ad.ad_mediation.a.3
            public void OnSupport(boolean z, IdSupplier idSupplier) {
                if (idSupplier == null) {
                    return;
                }
                try {
                    String unused = a.d = idSupplier.getOAID();
                    com.ss.union.game.sdk.ad.ad_mediation.d.b.a("MdidSdkHelper::getOAIDSuc: " + a.d + ",cost:" + (System.currentTimeMillis() - jCurrentTimeMillis));
                    SPUtils.getInstance().put(a.b, a.d);
                } catch (Throwable th2) {
                    com.ss.union.game.sdk.ad.ad_mediation.d.b.a("MdidSdkHelper::getOAID::fail," + th2.getMessage());
                }
            }
        });
        if (iInitSdk == 1008612) {
            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("MdidSdkHelper::InitSdk，不支持的设备");
            return;
        }
        if (iInitSdk == 1008613) {
            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("MdidSdkHelper::InitSdk，加载配置文件出错");
            return;
        }
        if (iInitSdk == 1008611) {
            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("MdidSdkHelper::InitSdk，不支持的设备厂商");
        } else if (iInitSdk == 1008614) {
            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("MdidSdkHelper::InitSdk，获取接口是异步的，结果会在回调中返回，回调执行的回调可能在工作线程");
        } else if (iInitSdk == 1008615) {
            com.ss.union.game.sdk.ad.ad_mediation.d.b.a("MdidSdkHelper::InitSdk，反射调用出错");
        }
    }
}
