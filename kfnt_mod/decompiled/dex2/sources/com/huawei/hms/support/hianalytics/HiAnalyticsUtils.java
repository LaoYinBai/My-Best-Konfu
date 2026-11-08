package com.huawei.hms.support.hianalytics;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.support.log.HMSLog;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class HiAnalyticsUtils {
    private static final Object a = new Object();
    private static final Object b = new Object();
    private static HiAnalyticsUtils c;
    private int d = 0;

    public static HiAnalyticsUtils getInstance() {
        HiAnalyticsUtils hiAnalyticsUtils;
        synchronized (a) {
            if (c == null) {
                c = new HiAnalyticsUtils();
            }
            hiAnalyticsUtils = c;
        }
        return hiAnalyticsUtils;
    }

    public void onEvent(Context context, String str, Map<String, String> map) {
        String strA = a(map);
        if (TextUtils.isEmpty(strA)) {
            return;
        }
        onEvent2(context, str, strA);
        com.huawei.a.b.a.a(1, str, b(map));
        a();
    }

    public void onBuoyEvent(Context context, String str, String str2) {
        if (context != null) {
            onEvent2(context, str, str2);
        }
    }

    public void onEvent2(Context context, String str, String str2) {
        if (context == null || !com.huawei.a.b.a.b()) {
            return;
        }
        com.huawei.a.b.a.a(context, str, str2);
    }

    private String a(Map<String, String> map) throws JSONException {
        if (map == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                jSONObject.put(entry.getKey(), entry.getValue());
            }
        } catch (JSONException e) {
            HMSLog.e("HiAnalyticsUtils", "AnalyticsHelper create json exception" + e.getMessage());
        }
        return jSONObject.toString();
    }

    public void onNewEvent(Context context, String str, Map map) throws JSONException {
        String strA = a(map);
        if (context == null || !com.huawei.a.b.a.b()) {
            return;
        }
        com.huawei.a.b.a.a(context, str, strA);
        com.huawei.a.b.a.a(1, str, b(map));
        a();
    }

    private static LinkedHashMap<String, String> b(Map<String, String> map) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        for (String str : map.keySet()) {
            linkedHashMap.put(str, map.get(str));
        }
        return linkedHashMap;
    }

    public static String versionCodeToName(String str) throws NumberFormatException {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (str.length() != 8 && str.length() != 9) {
            return "";
        }
        try {
            Integer.parseInt(str);
            return Integer.parseInt(str.substring(0, str.length() - 7)) + "." + Integer.parseInt(str.substring(str.length() - 7, str.length() - 5)) + "." + Integer.parseInt(str.substring(str.length() - 5, str.length() - 3)) + "." + Integer.parseInt(str.substring(str.length() - 3));
        } catch (NumberFormatException unused) {
            return "";
        }
    }

    private void a() {
        synchronized (b) {
            if (this.d < 60) {
                this.d++;
            } else {
                this.d = 0;
                com.huawei.a.b.a.a();
            }
        }
    }
}
