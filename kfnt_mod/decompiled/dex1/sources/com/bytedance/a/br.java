package com.bytedance.a;

import androidx.annotation.NonNull;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class br {

    /* renamed from: a, reason: collision with root package name */
    public final String f3425a;

    /* renamed from: b, reason: collision with root package name */
    public final String f3426b;

    /* renamed from: c, reason: collision with root package name */
    public final Boolean f3427c;
    public final Long d;
    public final Long e;
    public final Integer f;
    public final Long g;

    public br(String str, String str2, Boolean bool, Long l, Long l2, Integer num, Long l3) {
        this.f3425a = str;
        this.f3426b = str2;
        this.f3427c = bool;
        this.d = l;
        this.e = l2;
        this.f = num;
        this.g = l3;
    }

    @NonNull
    public Map<String, String> a() {
        HashMap map = new HashMap();
        bh.a(map, "id", this.f3425a);
        bh.a(map, "req_id", this.f3426b);
        bh.a(map, "is_track_limited", String.valueOf(this.f3427c));
        bh.a(map, "take_ms", String.valueOf(this.d));
        bh.a(map, com.bytedance.b.a.a.a.h, String.valueOf(this.e));
        bh.a(map, "query_times", String.valueOf(this.f));
        bh.a(map, "hw_id_version_code", String.valueOf(this.g));
        return map;
    }

    @NonNull
    public JSONObject b() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        bh.a(jSONObject, "id", this.f3425a);
        bh.a(jSONObject, "req_id", this.f3426b);
        bh.a(jSONObject, "is_track_limited", this.f3427c);
        bh.a(jSONObject, "take_ms", this.d);
        bh.a(jSONObject, com.bytedance.b.a.a.a.h, this.e);
        bh.a(jSONObject, "query_times", this.f);
        bh.a(jSONObject, "hw_id_version_code", this.g);
        return jSONObject;
    }

    public String toString() {
        return b().toString();
    }
}
