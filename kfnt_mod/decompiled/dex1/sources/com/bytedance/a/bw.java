package com.bytedance.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class bw {

    /* renamed from: a, reason: collision with root package name */
    public final SharedPreferences f3435a;

    @WorkerThread
    public bw(Context context) {
        this.f3435a = context.getSharedPreferences("device_register_oaid_refine", 0);
    }

    @Nullable
    @WorkerThread
    public br a() {
        String string = this.f3435a.getString("oaid", "");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(string);
            return new br(jSONObject.optString("id", null), jSONObject.optString("req_id", null), jSONObject.has("is_track_limited") ? Boolean.valueOf(jSONObject.optBoolean("is_track_limited")) : null, jSONObject.has("take_ms") ? Long.valueOf(jSONObject.optLong("take_ms", -1L)) : null, jSONObject.has(com.bytedance.b.a.a.a.h) ? Long.valueOf(jSONObject.optLong(com.bytedance.b.a.a.a.h, -1L)) : null, jSONObject.has("query_times") ? Integer.valueOf(jSONObject.optInt("query_times", -1)) : null, jSONObject.has("hw_id_version_code") ? Long.valueOf(jSONObject.optLong("hw_id_version_code", -1L)) : null);
        } catch (JSONException e) {
            de.a("", e);
            return null;
        }
    }

    @WorkerThread
    public void a(@Nullable br brVar) {
        if (brVar == null) {
            return;
        }
        this.f3435a.edit().putString("oaid", brVar.b().toString()).apply();
    }
}
