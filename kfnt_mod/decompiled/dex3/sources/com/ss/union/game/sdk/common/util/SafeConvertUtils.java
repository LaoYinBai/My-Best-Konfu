package com.ss.union.game.sdk.common.util;

import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SafeConvertUtils {
    public static final boolean convertToBoolean(Object obj, boolean z) {
        if (obj == null) {
            return z;
        }
        try {
            return Boolean.valueOf(obj.toString().trim()).booleanValue();
        } catch (Throwable unused) {
            return z;
        }
    }

    public static final int convertToInt(Object obj, int i) {
        if (obj == null) {
            return i;
        }
        try {
            return Integer.valueOf(obj.toString().trim()).intValue();
        } catch (Throwable unused) {
            return i;
        }
    }

    public static final long convertToLong(Object obj, long j) {
        if (obj == null) {
            return j;
        }
        try {
            return Long.valueOf(obj.toString().trim()).longValue();
        } catch (Throwable unused) {
            return j;
        }
    }

    public static final float convertToFloat(Object obj, float f) {
        if (obj == null) {
            return f;
        }
        try {
            return Float.valueOf(obj.toString().trim()).floatValue();
        } catch (Throwable unused) {
            return f;
        }
    }

    public static final double convertToDouble(Object obj, double d) {
        if (obj == null) {
            return d;
        }
        try {
            return Double.valueOf(obj.toString().trim()).doubleValue();
        } catch (Throwable unused) {
            return d;
        }
    }

    public static final String convertToString(Object obj, String str) {
        return obj == null ? str : obj.toString();
    }

    public static final JSONObject convertToJsonObject(Object obj, JSONObject jSONObject) {
        if (obj == null) {
            return jSONObject;
        }
        if (obj instanceof JSONObject) {
            return (JSONObject) obj;
        }
        try {
            return new JSONObject(obj.toString().trim());
        } catch (Throwable unused) {
            return jSONObject;
        }
    }

    public static final JSONArray convertToJsonArray(Object obj, JSONArray jSONArray) {
        if (obj == null) {
            return jSONArray;
        }
        if (obj instanceof JSONArray) {
            return (JSONArray) obj;
        }
        try {
            return new JSONArray(obj.toString().trim());
        } catch (Throwable unused) {
            return jSONArray;
        }
    }
}
