package com.apm.insight.k;

import androidx.annotation.Nullable;
import com.apm.insight.CrashType;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class c {

    /* renamed from: a, reason: collision with root package name */
    private static ConcurrentLinkedQueue<c> f2910a = new ConcurrentLinkedQueue<>();

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private JSONObject f2912a;

        /* renamed from: b, reason: collision with root package name */
        private JSONObject f2913b;

        /* renamed from: c, reason: collision with root package name */
        private CrashType f2914c;

        a(JSONObject jSONObject, CrashType crashType) {
            this.f2914c = crashType;
            if (crashType == CrashType.LAUNCH) {
                this.f2912a = ((JSONArray) jSONObject.opt("data")).optJSONObject(0);
            } else {
                this.f2912a = jSONObject;
            }
            this.f2913b = jSONObject.optJSONObject("header");
        }

        @Nullable
        public String a() {
            return this.f2912a.optString("crash_thread_name", null);
        }

        public long b() {
            return this.f2912a.optInt("app_start_time", -1);
        }

        @Nullable
        public String c() {
            JSONObject jSONObject;
            String str;
            switch (this.f2914c) {
                case JAVA:
                case NATIVE:
                    jSONObject = this.f2912a;
                    str = "data";
                    break;
                case LAUNCH:
                    jSONObject = this.f2912a;
                    str = "stack";
                    break;
                default:
                    return null;
            }
            return jSONObject.optString(str, null);
        }
    }

    static void a(CrashType crashType, JSONObject jSONObject) {
        if (f2910a == null || f2910a.isEmpty()) {
            return;
        }
        a aVar = new a(jSONObject, crashType);
        while (!f2910a.isEmpty()) {
            c cVarPoll = f2910a.poll();
            if (cVarPoll != null) {
                cVarPoll.a(crashType, aVar);
            }
        }
        f2910a = null;
    }

    public abstract void a(CrashType crashType, a aVar);
}
