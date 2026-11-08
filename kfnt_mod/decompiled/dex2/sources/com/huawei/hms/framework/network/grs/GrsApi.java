package com.huawei.hms.framework.network.grs;

import android.content.Context;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.grs.local.model.CountryCodeBean;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class GrsApi {
    private static final String TAG = "GrsApi";
    private static GrsClient grsClient;

    public static void ayncGetGrsUrl(String str, String str2, IQueryUrlCallBack iQueryUrlCallBack) {
        if (iQueryUrlCallBack == null) {
            Logger.w(TAG, "IQueryUrlCallBack is must not null for process continue.");
        } else if (grsClient == null || str == null || str2 == null) {
            iQueryUrlCallBack.onCallBackFail(-6);
        } else {
            grsClient.ayncGetGrsUrl(str, str2, iQueryUrlCallBack);
        }
    }

    public static void ayncGetGrsUrls(String str, IQueryUrlsCallBack iQueryUrlsCallBack) {
        if (iQueryUrlsCallBack == null) {
            Logger.w(TAG, "IQueryUrlsCallBack is must not null for process continue.");
        } else if (grsClient == null || str == null) {
            iQueryUrlsCallBack.onCallBackFail(-6);
        } else {
            grsClient.ayncGetGrsUrls(str, iQueryUrlsCallBack);
        }
    }

    public static void clearSp() {
        if (grsClient == null) {
            Logger.w(TAG, "GrsApi.clearSp return because grsClient is null.");
        } else {
            grsClient.clearSp();
        }
    }

    public static boolean forceExpire() {
        if (grsClient != null) {
            return grsClient.forceExpire();
        }
        Logger.w(TAG, "GrsApi.forceExpire return false because grsClient is null.");
        return false;
    }

    public static CountryCodeBean getCountryCode(Context context, boolean z) {
        return a.a(context, z);
    }

    public static int grsSdkInit(Context context, GrsBaseInfo grsBaseInfo) {
        grsClient = new GrsClient(context, grsBaseInfo);
        return 0;
    }

    public static String synGetGrsUrl(String str, String str2) {
        if (grsClient != null && str != null && str2 != null) {
            return grsClient.synGetGrsUrl(str, str2);
        }
        Logger.w(TAG, "GrsApi.synGetGrsUrl method maybe grsSdkInit has not completed and grsClient is null.");
        return null;
    }

    public static Map<String, String> synGetGrsUrls(String str) {
        if (grsClient != null && str != null) {
            return grsClient.synGetGrsUrls(str);
        }
        Logger.w(TAG, "GrsApi.synGetGrsUrls method maybe grsSdkInit has not completed and grsClient is null.");
        return new HashMap();
    }
}
