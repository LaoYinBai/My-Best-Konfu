package com.bytedance.push.f;

import android.os.Looper;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class e {

    /* renamed from: a, reason: collision with root package name */
    private static a f4803a;

    static void a(a aVar) {
        f4803a = aVar;
    }

    static void a(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        com.bytedance.push.utils.e.a("Monitor", "serviceName=" + str + ", category=" + jSONObject + ", metric=" + jSONObject2 + ", extraLog=" + jSONObject3);
        if (f4803a != null) {
            f4803a.a(str, jSONObject, jSONObject2, jSONObject3);
            return;
        }
        com.bytedance.push.utils.e.b("Monitor", "monitor impl is null when send event = " + str);
    }

    static Looper a() {
        return Looper.getMainLooper();
    }
}
