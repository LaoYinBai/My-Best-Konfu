package com.bytedance.a;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class dx extends bu {
    public long m;
    public long n;
    public String o;

    @Override // com.bytedance.a.bu
    public int a(@NonNull Cursor cursor) {
        de.a("U SHALL NOT PASS!", null);
        return 0;
    }

    @Override // com.bytedance.a.bu
    public List<String> a() {
        return null;
    }

    @Override // com.bytedance.a.bu
    public void a(@NonNull ContentValues contentValues) {
        de.a("U SHALL NOT PASS!", null);
    }

    @Override // com.bytedance.a.bu
    public JSONObject b() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("local_time_ms", this.f3434c);
        jSONObject.put("tea_event_index", this.d);
        jSONObject.put(com.bytedance.b.a.a.d.p, this.e);
        jSONObject.put("stop_timestamp", this.n / 1000);
        jSONObject.put("duration", this.m / 1000);
        jSONObject.put("datetime", this.l);
        long j = this.f;
        if (j > 0) {
            jSONObject.put("user_id", j);
        }
        if (!TextUtils.isEmpty(this.g)) {
            jSONObject.put("user_unique_id", this.g);
        }
        if (!TextUtils.isEmpty(this.h)) {
            jSONObject.put("ssid", this.h);
        }
        if (!TextUtils.isEmpty(this.i)) {
            jSONObject.put("ab_sdk_version", this.i);
        }
        if (!TextUtils.isEmpty(this.o)) {
            jSONObject.put("uuid_changed", true);
            if (!TextUtils.equals(this.o, this.e)) {
                jSONObject.put("original_session_id", this.o);
            }
        }
        return jSONObject;
    }

    @Override // com.bytedance.a.bu
    @NonNull
    public String d() {
        return "terminate";
    }

    @Override // com.bytedance.a.bu
    public String h() {
        return String.valueOf(this.m);
    }

    @Override // com.bytedance.a.bu
    public void a(@NonNull JSONObject jSONObject) {
        de.a("U SHALL NOT PASS!", null);
    }

    @Override // com.bytedance.a.bu
    public bu b(@NonNull JSONObject jSONObject) {
        de.a("U SHALL NOT PASS!", null);
        return this;
    }
}
