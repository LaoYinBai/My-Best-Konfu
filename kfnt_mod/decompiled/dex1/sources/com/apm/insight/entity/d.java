package com.apm.insight.entity;

import android.os.Environment;
import com.apm.insight.h;
import com.apm.insight.l.i;
import com.apm.insight.l.l;
import java.io.IOException;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private static String f2838a;

    /* renamed from: b, reason: collision with root package name */
    private static JSONObject f2839b;

    private static void a() {
        JSONObject jSONObject;
        if (f2838a == null) {
            f2838a = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + h.g().getPackageName() + "/AutomationTestInfo.json";
        }
        if (f2839b == null) {
            try {
                f2839b = new JSONObject(i.c(f2838a));
            } catch (IOException unused) {
                jSONObject = new JSONObject();
                f2839b = jSONObject;
            } catch (JSONException unused2) {
                jSONObject = new JSONObject();
                f2839b = jSONObject;
            }
        }
    }

    public static void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            c(jSONObject);
        } catch (Throwable unused) {
        }
    }

    public static void b(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            d(jSONObject);
        } catch (Throwable unused) {
        }
    }

    private static void c(JSONObject jSONObject) throws JSONException {
        Object objOpt;
        a();
        if (f2839b == null) {
            return;
        }
        Iterator<String> itKeys = f2839b.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            if (!"slardar_filter".equals(next) && (objOpt = f2839b.opt(next)) != null) {
                try {
                    jSONObject.put(next, objOpt);
                } catch (JSONException unused) {
                }
            }
        }
    }

    private static void d(JSONObject jSONObject) throws JSONException {
        a();
        if (f2839b == null) {
            return;
        }
        JSONObject jSONObjectOptJSONObject = f2839b.optJSONObject("slardar_filter");
        if (l.a(jSONObjectOptJSONObject)) {
            return;
        }
        JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("filters");
        if (jSONObjectOptJSONObject2 == null) {
            jSONObjectOptJSONObject2 = new JSONObject();
            try {
                jSONObject.put("filters", jSONObjectOptJSONObject2);
            } catch (JSONException unused) {
            }
        }
        a.b(jSONObjectOptJSONObject2, jSONObjectOptJSONObject);
    }
}
