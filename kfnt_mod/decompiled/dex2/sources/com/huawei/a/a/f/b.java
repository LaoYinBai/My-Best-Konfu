package com.huawei.a.a.f;

import android.content.Context;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class b {
    private static b a;
    private Context b;
    private final Object c = new Object();

    private b() {
    }

    public static b a() {
        if (a == null) {
            b();
        }
        return a;
    }

    private JSONObject a(Map<String, String> map) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (map != null) {
            try {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    jSONObject.put(entry.getKey(), entry.getValue());
                }
            } catch (JSONException unused) {
                com.huawei.a.a.b.e.a.d("HiAnalyticsEventManager", "Exception occured when transferring bundle to json");
            }
        }
        return jSONObject;
    }

    private static synchronized void b() {
        if (a == null) {
            a = new b();
        }
    }

    public void a(Context context) {
        synchronized (this.c) {
            if (this.b != null) {
                return;
            }
            this.b = context;
            com.huawei.a.a.b.j.a.a.a().a(context);
        }
    }

    public void a(String str, int i) {
        com.huawei.a.a.b.j.a.a.a().a(str, i);
    }

    public void a(String str, int i, String str2, LinkedHashMap<String, String> linkedHashMap) {
        com.huawei.a.a.b.j.a.a.a().a(str, i, str2, a(linkedHashMap));
    }

    public void a(String str, Context context, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("_constants", str3);
            com.huawei.a.a.b.j.a.a.a().a(str, 0, str2, jSONObject);
        } catch (JSONException unused) {
            com.huawei.a.a.b.e.a.c("HiAnalyticsEventManager", "onEvent():JSON structure Exception!");
        }
    }
}
