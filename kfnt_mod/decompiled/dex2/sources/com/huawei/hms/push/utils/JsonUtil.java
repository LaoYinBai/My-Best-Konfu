package com.huawei.hms.push.utils;

import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.support.log.HMSLog;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class JsonUtil {
    private JsonUtil() {
    }

    public static String getString(JSONObject jSONObject, String str, String str2) {
        if (jSONObject == null) {
            return str2;
        }
        try {
            return (!jSONObject.has(str) || jSONObject.get(str) == null) ? str2 : String.valueOf(jSONObject.get(str));
        } catch (JSONException unused) {
            HMSLog.w("JsonUtil", "JSONException: get " + str + " error.");
            return str2;
        }
    }

    public static int getInt(JSONObject jSONObject, String str, int i) {
        if (jSONObject == null) {
            return i;
        }
        try {
            return jSONObject.has(str) ? jSONObject.getInt(str) : i;
        } catch (JSONException unused) {
            HMSLog.w("JsonUtil", "JSONException: get " + str + " error.");
            return i;
        }
    }

    public static String[] getStringArray(JSONObject jSONObject, String str, String[] strArr) throws JSONException {
        String[] strArr2 = null;
        if (jSONObject == null) {
            return null;
        }
        try {
            if (!jSONObject.has(str)) {
                return null;
            }
            JSONArray jSONArray = jSONObject.getJSONArray(str);
            String[] strArr3 = new String[jSONArray.length()];
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    strArr3[i] = (String) jSONArray.get(i);
                } catch (JSONException unused) {
                    strArr2 = strArr3;
                    HMSLog.w("JsonUtil", "JSONException: get " + str + " error.");
                    return strArr2;
                }
            }
            return strArr3;
        } catch (JSONException unused2) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int[] getIntArray(org.json.JSONObject r3, java.lang.String r4, int[] r5) throws org.json.JSONException {
        /*
            r0 = 0
            if (r3 == 0) goto L46
            boolean r1 = r3.has(r4)     // Catch: org.json.JSONException -> L29
            if (r1 == 0) goto L46
            org.json.JSONArray r3 = r3.getJSONArray(r4)     // Catch: org.json.JSONException -> L29
            int r1 = r3.length()     // Catch: org.json.JSONException -> L29
            int[] r1 = new int[r1]     // Catch: org.json.JSONException -> L29
            r0 = 0
        L14:
            int r2 = r3.length()     // Catch: org.json.JSONException -> L2a
            if (r0 >= r2) goto L45
            java.lang.Object r2 = r3.get(r0)     // Catch: org.json.JSONException -> L2a
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch: org.json.JSONException -> L2a
            int r2 = r2.intValue()     // Catch: org.json.JSONException -> L2a
            r1[r0] = r2     // Catch: org.json.JSONException -> L2a
            int r0 = r0 + 1
            goto L14
        L29:
            r1 = r0
        L2a:
            java.lang.String r3 = "JsonUtil"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "JSONException: get "
            r0.append(r2)
            r0.append(r4)
            java.lang.String r4 = " error."
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            com.huawei.hms.support.log.HMSLog.w(r3, r4)
        L45:
            r0 = r1
        L46:
            if (r0 != 0) goto L49
            goto L4a
        L49:
            r5 = r0
        L4a:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.push.utils.JsonUtil.getIntArray(org.json.JSONObject, java.lang.String, int[]):int[]");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static long[] getLongArray(org.json.JSONObject r4, java.lang.String r5, long[] r6) throws org.json.JSONException {
        /*
            r0 = 0
            if (r4 == 0) goto L41
            boolean r1 = r4.has(r5)     // Catch: org.json.JSONException -> L26
            if (r1 == 0) goto L41
            org.json.JSONArray r4 = r4.getJSONArray(r5)     // Catch: org.json.JSONException -> L26
            int r1 = r4.length()     // Catch: org.json.JSONException -> L26
            long[] r1 = new long[r1]     // Catch: org.json.JSONException -> L26
            r0 = 0
        L14:
            int r2 = r4.length()     // Catch: org.json.JSONException -> L25
            if (r0 >= r2) goto L23
            long r2 = r4.getLong(r0)     // Catch: org.json.JSONException -> L25
            r1[r0] = r2     // Catch: org.json.JSONException -> L25
            int r0 = r0 + 1
            goto L14
        L23:
            r0 = r1
            goto L41
        L25:
            r0 = r1
        L26:
            java.lang.String r4 = "JsonUtil"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "JSONException: get "
            r1.append(r2)
            r1.append(r5)
            java.lang.String r5 = " error."
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            com.huawei.hms.support.log.HMSLog.w(r4, r5)
        L41:
            if (r0 != 0) goto L44
            goto L45
        L44:
            r6 = r0
        L45:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.push.utils.JsonUtil.getLongArray(org.json.JSONObject, java.lang.String, long[]):long[]");
    }

    public static void transferJsonObjectToBundle(JSONObject jSONObject, Bundle bundle, HashMap<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            a(jSONObject, entry.getKey(), entry.getValue(), bundle);
        }
    }

    private static void a(JSONObject jSONObject, String str, Object obj, Bundle bundle) {
        if (obj == null) {
            HMSLog.w("JsonUtil", "transfer jsonObject to bundle failed, defaultValue is null.");
            return;
        }
        if (obj instanceof String) {
            String str2 = (String) obj;
            if (TextUtils.isEmpty(str2)) {
                str2 = null;
            }
            bundle.putString(str, getString(jSONObject, str, str2));
            return;
        }
        if (obj instanceof Integer) {
            bundle.putInt(str, getInt(jSONObject, str, ((Integer) obj).intValue()));
            return;
        }
        if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            if (iArr.length == 0) {
                iArr = null;
            }
            bundle.putIntArray(str, getIntArray(jSONObject, str, iArr));
            return;
        }
        if (obj instanceof long[]) {
            long[] jArr = (long[]) obj;
            if (jArr.length == 0) {
                jArr = null;
            }
            bundle.putLongArray(str, getLongArray(jSONObject, str, jArr));
            return;
        }
        if (obj instanceof String[]) {
            String[] strArr = (String[]) obj;
            if (strArr.length == 0) {
                strArr = null;
            }
            bundle.putStringArray(str, getStringArray(jSONObject, str, strArr));
            return;
        }
        HMSLog.w("JsonUtil", "transfer jsonObject to bundle failed, invalid data type.");
    }

    public static JSONArray getIntJsonArray(int[] iArr) {
        JSONArray jSONArray = new JSONArray();
        if (iArr == null || iArr.length == 0) {
            return jSONArray;
        }
        for (int i : iArr) {
            jSONArray.put(i);
        }
        return jSONArray;
    }

    public static JSONArray getLongJsonArray(long[] jArr) {
        JSONArray jSONArray = new JSONArray();
        if (jArr == null || jArr.length == 0) {
            return jSONArray;
        }
        for (long j : jArr) {
            jSONArray.put(j);
        }
        return jSONArray;
    }

    public static JSONArray getStringJsonArray(String[] strArr) {
        JSONArray jSONArray = new JSONArray();
        if (strArr == null || strArr.length == 0) {
            return jSONArray;
        }
        for (String str : strArr) {
            jSONArray.put(str);
        }
        return jSONArray;
    }
}
