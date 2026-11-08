package com.umeng.commonsdk.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.text.TextUtils;
import com.umeng.commonsdk.statistics.common.ULog;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    public static final String a = "lng";
    public static final String b = "lat";
    public static final String c = "ts";
    public static final long d = 30000;
    public static final int e = 200;
    private static final String f = "UMSysLocationCache";

    public static void a(final Context context) throws Throwable {
        ULog.i(f, "begin location");
        if (context == null) {
            return;
        }
        try {
            new Thread(new Runnable() { // from class: com.umeng.commonsdk.proguard.b.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        JSONArray jSONArrayB = b.b(context);
                        if (jSONArrayB != null) {
                            if (jSONArrayB.length() >= 200) {
                                return;
                            }
                        }
                        try {
                            ULog.i(b.f, "location status is ok, time is " + System.currentTimeMillis());
                            final a aVar = new a(context);
                            aVar.a(new c() { // from class: com.umeng.commonsdk.proguard.b.1.1
                                @Override // com.umeng.commonsdk.proguard.c
                                public void a(Location location) throws Throwable {
                                    if (location != null) {
                                        try {
                                            double longitude = location.getLongitude();
                                            double latitude = location.getLatitude();
                                            float accuracy = location.getAccuracy();
                                            double altitude = location.getAltitude();
                                            ULog.i(b.f, "lon is " + longitude + ", lat is " + latitude + ", acc is " + accuracy + ", alt is " + altitude);
                                            if (longitude != 0.0d && latitude != 0.0d) {
                                                long time = location.getTime();
                                                JSONObject jSONObject = new JSONObject();
                                                try {
                                                    jSONObject.put(b.a, longitude);
                                                    jSONObject.put(b.b, latitude);
                                                    jSONObject.put("ts", time);
                                                    jSONObject.put("acc", accuracy);
                                                    jSONObject.put("alt", altitude);
                                                } catch (JSONException e2) {
                                                    ULog.i(b.f, "e is " + e2);
                                                }
                                                ULog.i(b.f, "locationJSONObject is " + jSONObject.toString());
                                                SharedPreferences sharedPreferences = context.getSharedPreferences(com.umeng.commonsdk.internal.a.q, 0);
                                                if (sharedPreferences != null) {
                                                    String string = sharedPreferences.getString(com.umeng.commonsdk.internal.a.s, "");
                                                    String string2 = sharedPreferences.getString(com.umeng.commonsdk.internal.a.t, "");
                                                    ULog.i(b.f, "--->>> get lon is " + string + ", lat is " + string2);
                                                    if (!TextUtils.isEmpty(string) && Double.parseDouble(string) == longitude && !TextUtils.isEmpty(string2) && Double.parseDouble(string2) == latitude) {
                                                        ULog.i(b.f, "location same");
                                                    } else {
                                                        JSONArray jSONArrayB2 = b.b(context);
                                                        if (jSONArrayB2 == null) {
                                                            jSONArrayB2 = new JSONArray();
                                                        }
                                                        jSONArrayB2.put(jSONObject);
                                                        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                                                        editorEdit.putString(com.umeng.commonsdk.internal.a.s, String.valueOf(longitude));
                                                        editorEdit.putString(com.umeng.commonsdk.internal.a.t, String.valueOf(latitude));
                                                        editorEdit.putString(com.umeng.commonsdk.internal.a.r, jSONArrayB2.toString());
                                                        editorEdit.commit();
                                                        ULog.i(b.f, "location put is ok~~");
                                                    }
                                                }
                                            }
                                        } catch (Throwable th) {
                                            ULog.i(b.f, "" + th.getMessage());
                                        }
                                    }
                                    aVar.a();
                                }
                            });
                        } catch (Throwable unused) {
                        }
                    } catch (Throwable unused2) {
                    }
                }
            }).start();
        } catch (Exception unused) {
        }
    }

    public static JSONArray b(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(com.umeng.commonsdk.internal.a.q, 0);
        JSONArray jSONArray = null;
        if (sharedPreferences == null) {
            return null;
        }
        try {
            String string = sharedPreferences.getString(com.umeng.commonsdk.internal.a.r, "");
            if (!TextUtils.isEmpty(string)) {
                jSONArray = new JSONArray(string);
            }
        } catch (JSONException e2) {
            ULog.i(f, "e is " + e2);
        } catch (Throwable th) {
            ULog.i(f, "e is " + th);
        }
        if (jSONArray != null) {
            ULog.i(f, "get json str is " + jSONArray.toString());
        }
        return jSONArray;
    }

    public static void c(Context context) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(com.umeng.commonsdk.internal.a.q, 0);
            if (sharedPreferences != null) {
                SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                editorEdit.putString(com.umeng.commonsdk.internal.a.r, "");
                editorEdit.commit();
                ULog.i(f, "delete is ok~~");
            }
        } catch (Throwable unused) {
        }
    }
}
