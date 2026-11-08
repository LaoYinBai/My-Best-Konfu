package com.bytedance.services.apm.api;

import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    protected static IApmAgent f5315a = null;

    /* renamed from: b, reason: collision with root package name */
    protected static volatile boolean f5316b = false;

    protected a() {
    }

    public static void a(IApmAgent iApmAgent) {
        f5315a = iApmAgent;
    }

    public static void a(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        if (f5315a == null) {
            if (f5316b) {
                throw new RuntimeException("implement IApmAgent and set sInstance first");
            }
        } else {
            f5315a.monitorEvent(str, jSONObject, jSONObject2, jSONObject3);
        }
    }

    public static void a(String str, JSONObject jSONObject) {
        if (f5315a == null) {
            if (f5316b) {
                throw new RuntimeException("implement IApmAgent and set sInstance first");
            }
        } else {
            f5315a.monitorLog(str, jSONObject);
        }
    }

    @Deprecated
    public static void a(String str, int i, JSONObject jSONObject) {
        if (f5315a == null) {
            if (f5316b) {
                throw new RuntimeException("implement IApmAgent and set sInstance first");
            }
        } else {
            f5315a.monitorStatusRate(str, i, jSONObject);
        }
    }

    @Deprecated
    public static void a(String str, JSONObject jSONObject, JSONObject jSONObject2) {
        if (f5315a == null) {
            if (f5316b) {
                throw new RuntimeException("implement IApmAgent and set sInstance first");
            }
        } else {
            f5315a.monitorDuration(str, jSONObject, jSONObject2);
        }
    }

    @Deprecated
    public static void a(String str, int i, JSONObject jSONObject, JSONObject jSONObject2) {
        if (f5315a == null) {
            if (f5316b) {
                throw new RuntimeException("implement IApmAgent and set sInstance first");
            }
        } else {
            f5315a.monitorStatusAndDuration(str, i, jSONObject, jSONObject2);
        }
    }

    public static boolean a() {
        return f5315a != null;
    }

    public static boolean b() {
        return f5316b;
    }

    public static void a(boolean z) {
        f5316b = z;
    }
}
