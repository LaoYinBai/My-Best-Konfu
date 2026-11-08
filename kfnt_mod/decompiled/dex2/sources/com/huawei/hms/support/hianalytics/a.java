package com.huawei.hms.support.hianalytics;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    protected static Map<String, String> getMapForBi(Context context, String str) {
        HashMap map = new HashMap();
        if (context == null || TextUtils.isEmpty(str)) {
            return map;
        }
        String[] strArrSplit = str.split("\\.");
        if (strArrSplit.length < 2) {
            return map;
        }
        String str2 = strArrSplit[0];
        String str3 = strArrSplit[1];
        map.put("service", str2);
        map.put(HiAnalyticsConstant.HaKey.BI_KEY_APINAME, str3);
        map.put("package", context.getPackageName());
        map.put(HiAnalyticsConstant.HaKey.BI_KEY_BASE_VERSION, "4.0.4.300");
        map.put(HiAnalyticsConstant.HaKey.BI_KEY_CALLTMEI, String.valueOf(System.currentTimeMillis()));
        return map;
    }
}
