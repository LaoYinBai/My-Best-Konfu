package com.bytedance.push;

import com.bytedance.applog.AppLog;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class h {

    /* renamed from: a, reason: collision with root package name */
    private ITracker f4824a;

    h() {
    }

    void a(String str, JSONObject jSONObject) {
        if (this.f4824a != null) {
            this.f4824a.onEvent(str, jSONObject);
            return;
        }
        try {
            AppLog.onEventV3(str, jSONObject);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a(ITracker iTracker) {
        this.f4824a = iTracker;
    }
}
