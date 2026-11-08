package com.huawei.hms.support.hianalytics;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import com.huawei.hms.android.SystemUtils;
import com.huawei.hms.common.internal.RequestHeader;
import com.huawei.hms.common.internal.ResponseHeader;
import com.huawei.hms.common.internal.TransactionIdCreater;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.Util;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class HiAnalyticsUtil {
    private static final Object a = new Object();
    private static HiAnalyticsUtil b;
    private static HiAnalyticsUtils c;
    private final int d = -1;
    private int e = -1;

    public static HiAnalyticsUtil getInstance() {
        HiAnalyticsUtil hiAnalyticsUtil;
        synchronized (a) {
            if (b == null) {
                b = new HiAnalyticsUtil();
                c = HiAnalyticsUtils.getInstance();
            }
            hiAnalyticsUtil = b;
        }
        return hiAnalyticsUtil;
    }

    public void onEvent(Context context, String str, Map<String, String> map) {
        c.onEvent(context, str, map);
    }

    public void onEvent(Context context, String str, String str2) {
        if (getInstance().hasError(context) || context == null) {
            return;
        }
        onEvent2(context, str, a(context, str2));
    }

    public void onBuoyEvent(Context context, String str, String str2) {
        if (getInstance().hasError(context)) {
            return;
        }
        c.onBuoyEvent(context, str, str2);
    }

    public void onEvent2(Context context, String str, String str2) {
        c.onEvent2(context, str, str2);
    }

    private String a(Context context, String str) {
        return "01|" + HiAnalyticsConstant.REPORT_VAL_SEPARATOR + context.getPackageName() + HiAnalyticsConstant.REPORT_VAL_SEPARATOR + Util.getAppId(context) + HiAnalyticsConstant.REPORT_VAL_SEPARATOR + 40004300 + HiAnalyticsConstant.REPORT_VAL_SEPARATOR + str;
    }

    @Deprecated
    public boolean hasError() {
        if (SystemUtils.isChinaROM()) {
            return false;
        }
        HMSLog.i("HiAnalyticsUtil", "not ChinaROM ");
        return true;
    }

    public boolean hasError(Context context) {
        if (SystemUtils.isChinaROM() || this.e != -1) {
            return this.e == 0;
        }
        HMSLog.i("HiAnalyticsUtil", "not ChinaROM");
        if (context == null) {
            return true;
        }
        try {
            this.e = Settings.Secure.getInt(context.getContentResolver(), "hw_app_analytics_state");
            HMSLog.i("HiAnalyticsUtil", "hw_app_analytics_state value is " + this.e);
            return this.e != 1;
        } catch (Settings.SettingNotFoundException unused) {
            HMSLog.i("HiAnalyticsUtil", "Get OOBE failed");
            return true;
        }
    }

    public void onNewEvent(Context context, String str, Map map) {
        if (hasError(context)) {
            return;
        }
        c.onNewEvent(context, str, map);
    }

    @Deprecated
    public Map<String, String> getMapForBi(Context context, String str) {
        HashMap map = new HashMap();
        String[] strArrSplit = str.split("\\.");
        if (strArrSplit.length < 2) {
            return map;
        }
        String str2 = strArrSplit[0];
        String str3 = strArrSplit[1];
        String appId = Util.getAppId(context);
        map.put(HiAnalyticsConstant.HaKey.BI_KEY_TRANSID, TransactionIdCreater.getId(appId, str));
        map.put(HiAnalyticsConstant.HaKey.BI_KEY_APPID, appId);
        map.put("service", str2);
        map.put(HiAnalyticsConstant.HaKey.BI_KEY_APINAME, str3);
        map.put("package", context.getPackageName());
        map.put(HiAnalyticsConstant.HaKey.BI_KEY_VERSION, "4.0.4.300");
        map.put(HiAnalyticsConstant.HaKey.BI_KEY_CALLTMEI, String.valueOf(System.currentTimeMillis()));
        return map;
    }

    public static Map<String, String> getMapFromRequestHeader(ResponseHeader responseHeader) {
        HashMap map = new HashMap();
        if (responseHeader == null) {
            return map;
        }
        map.put(HiAnalyticsConstant.HaKey.BI_KEY_TRANSID, responseHeader.getTransactionId());
        map.put(HiAnalyticsConstant.HaKey.BI_KEY_APPID, responseHeader.getActualAppID());
        map.put("service", responseHeader.getSrvName());
        String apiName = responseHeader.getApiName();
        if (!TextUtils.isEmpty(apiName)) {
            String[] strArrSplit = apiName.split("\\.");
            if (strArrSplit.length >= 2) {
                map.put(HiAnalyticsConstant.HaKey.BI_KEY_APINAME, strArrSplit[1]);
            }
        }
        map.put("package", responseHeader.getPkgName());
        map.put(HiAnalyticsConstant.HaKey.BI_KEY_RESULT, String.valueOf(responseHeader.getStatusCode()));
        map.put("result", String.valueOf(responseHeader.getErrorCode()));
        map.put(HiAnalyticsConstant.HaKey.BI_KEY_ERRORREASON, responseHeader.getErrorReason());
        map.put(HiAnalyticsConstant.HaKey.BI_KEY_CALLTMEI, String.valueOf(System.currentTimeMillis()));
        map.put(HiAnalyticsConstant.HaKey.BI_KEY_BASE_VERSION, "4.0.4.300");
        return map;
    }

    public static Map<String, String> getMapFromForegroundResponseHeader(ResponseHeader responseHeader) {
        HashMap map = new HashMap();
        if (responseHeader == null) {
            return map;
        }
        map.put(HiAnalyticsConstant.HaKey.BI_KEY_TRANSID, responseHeader.getTransactionId());
        map.put(HiAnalyticsConstant.HaKey.BI_KEY_APPID, responseHeader.getActualAppID());
        map.put("service", responseHeader.getSrvName());
        map.put(HiAnalyticsConstant.HaKey.BI_KEY_APINAME, responseHeader.getApiName());
        map.put("package", responseHeader.getPkgName());
        map.put(HiAnalyticsConstant.HaKey.BI_KEY_RESULT, String.valueOf(responseHeader.getStatusCode()));
        map.put("result", String.valueOf(responseHeader.getErrorCode()));
        map.put(HiAnalyticsConstant.HaKey.BI_KEY_ERRORREASON, responseHeader.getErrorReason());
        map.put(HiAnalyticsConstant.HaKey.BI_KEY_CALLTMEI, String.valueOf(System.currentTimeMillis()));
        map.put(HiAnalyticsConstant.HaKey.BI_KEY_BASE_VERSION, "4.0.4.300");
        return map;
    }

    public Map<String, String> getMapFromRequestHeader(RequestHeader requestHeader) {
        HashMap map = new HashMap();
        if (requestHeader == null) {
            return map;
        }
        map.put(HiAnalyticsConstant.HaKey.BI_KEY_TRANSID, requestHeader.getTransactionId());
        map.put(HiAnalyticsConstant.HaKey.BI_KEY_APPID, requestHeader.getActualAppID());
        map.put("service", requestHeader.getSrvName());
        String apiName = requestHeader.getApiName();
        if (!TextUtils.isEmpty(apiName)) {
            String[] strArrSplit = apiName.split("\\.");
            if (strArrSplit.length >= 2) {
                map.put(HiAnalyticsConstant.HaKey.BI_KEY_APINAME, strArrSplit[1]);
            }
        }
        map.put("package", requestHeader.getPkgName());
        map.put(HiAnalyticsConstant.HaKey.BI_KEY_CALLTMEI, String.valueOf(System.currentTimeMillis()));
        map.put(HiAnalyticsConstant.HaKey.BI_KEY_BASE_VERSION, "4.0.4.300");
        return map;
    }

    public Map<String, String> getMapFromForegroundRequestHeader(RequestHeader requestHeader) {
        HashMap map = new HashMap();
        if (requestHeader == null) {
            return map;
        }
        map.put(HiAnalyticsConstant.HaKey.BI_KEY_TRANSID, requestHeader.getTransactionId());
        map.put(HiAnalyticsConstant.HaKey.BI_KEY_APPID, requestHeader.getActualAppID());
        map.put("service", requestHeader.getSrvName());
        map.put(HiAnalyticsConstant.HaKey.BI_KEY_APINAME, requestHeader.getApiName());
        map.put("package", requestHeader.getPkgName());
        map.put(HiAnalyticsConstant.HaKey.BI_KEY_CALLTMEI, String.valueOf(System.currentTimeMillis()));
        map.put(HiAnalyticsConstant.HaKey.BI_KEY_BASE_VERSION, "4.0.4.300");
        return map;
    }

    public static String versionCodeToName(String str) {
        return HiAnalyticsUtils.versionCodeToName(str);
    }
}
