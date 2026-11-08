package com.meizu.cloud.pushsdk.c.f;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e {
    private static final String a = "e";

    public static long a(String str) {
        long j;
        long j2 = 0;
        int i = 0;
        while (i < str.length()) {
            char cCharAt = str.charAt(i);
            if (cCharAt <= 127) {
                j = 1;
            } else if (cCharAt <= 2047) {
                j = 2;
            } else {
                if (cCharAt >= 55296 && cCharAt <= 57343) {
                    j2 += 4;
                    i++;
                } else if (cCharAt < 65535) {
                    j = 3;
                } else {
                    j2 += 4;
                }
                i++;
            }
            j2 += j;
            i++;
        }
        return j2;
    }

    private static Object a(Object obj) {
        if (Build.VERSION.SDK_INT >= 19) {
            return obj;
        }
        if (obj == null) {
            return JSONObject.NULL;
        }
        if ((obj instanceof JSONObject) || (obj instanceof JSONArray)) {
            return obj;
        }
        if (obj instanceof Collection) {
            JSONArray jSONArray = new JSONArray();
            Iterator it = ((Collection) obj).iterator();
            while (it.hasNext()) {
                jSONArray.put(a(it.next()));
            }
            return jSONArray;
        }
        if (obj.getClass().isArray()) {
            JSONArray jSONArray2 = new JSONArray();
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                jSONArray2.put(a(Array.get(obj, i)));
            }
            return jSONArray2;
        }
        if (obj instanceof Map) {
            return a((Map) obj);
        }
        if ((obj instanceof Boolean) || (obj instanceof Byte) || (obj instanceof Character) || (obj instanceof Double) || (obj instanceof Float) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Short) || (obj instanceof String)) {
            return obj;
        }
        if (obj.getClass().getPackage().getName().startsWith("java.")) {
            return obj.toString();
        }
        return null;
    }

    public static String a() {
        return Long.toString(System.currentTimeMillis());
    }

    public static JSONObject a(Map map) throws JSONException {
        if (Build.VERSION.SDK_INT >= 19) {
            return new JSONObject(map);
        }
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            Object objA = a(entry.getValue());
            try {
                jSONObject.put(str, objA);
            } catch (JSONException e) {
                c.a(a, "Could not put key '%s' and value '%s' into new JSONObject: %s", str, objA, e);
                e.printStackTrace();
            }
        }
        return jSONObject;
    }

    public static boolean a(long j, long j2, long j3) {
        return j > j2 - j3;
    }

    public static boolean a(Context context) {
        try {
            c.c(a, "Checking tracker internet connectivity.", new Object[0]);
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            boolean z = activeNetworkInfo != null && activeNetworkInfo.isConnected();
            c.b(a, "Tracker connection online: %s", Boolean.valueOf(z));
            return z;
        } catch (Exception e) {
            c.a(a, "Security exception checking connection: %s", e.toString());
            return true;
        }
    }

    public static String b() {
        return UUID.randomUUID().toString();
    }

    public static String b(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                return telephonyManager.getNetworkOperatorName();
            }
            return null;
        } catch (Exception e) {
            c.a(a, "getCarrier: %s", e.toString());
            return null;
        }
    }

    @SuppressLint({"MissingPermission"})
    public static Location c(Context context) {
        String str;
        String str2;
        Object[] objArr;
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService(FirebaseAnalytics.Param.LOCATION);
            if (locationManager != null) {
                Criteria criteria = new Criteria();
                criteria.setPowerRequirement(1);
                criteria.setAccuracy(2);
                String bestProvider = locationManager.getBestProvider(criteria, true);
                if (bestProvider != null) {
                    Location lastKnownLocation = locationManager.getLastKnownLocation(bestProvider);
                    c.b(a, "Location found: %s", lastKnownLocation);
                    return lastKnownLocation;
                }
                str = a;
                str2 = "Location Manager provider is null.";
                objArr = new Object[0];
            } else {
                str = a;
                str2 = "Location Manager is null.";
                objArr = new Object[0];
            }
            c.a(str, str2, objArr);
            return null;
        } catch (Exception e) {
            c.a(a, "Failed to retrieve location: %s", e.toString());
            return null;
        }
    }
}
