package bykvm_19do.bykvm_19do.bykvm_19do;

import android.text.TextUtils;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class c1 {

    /* renamed from: a, reason: collision with root package name */
    final String f1317a;

    /* renamed from: b, reason: collision with root package name */
    final String f1318b;

    /* renamed from: c, reason: collision with root package name */
    final Boolean f1319c;
    final Long d;
    final Long e;
    final Integer f;
    final Long g;

    c1(String str, String str2, Boolean bool, Long l, Long l2, Integer num, Long l3) {
        this.f1317a = str;
        this.f1318b = str2;
        this.f1319c = bool;
        this.d = l;
        this.e = l2;
        this.f = num;
        this.g = l3;
    }

    @NonNull
    Map<String, String> a() {
        HashMap map = new HashMap();
        z0.a(map, "id", this.f1317a);
        z0.a(map, "req_id", this.f1318b);
        z0.a(map, "is_track_limited", String.valueOf(this.f1319c));
        z0.a(map, "take_ms", String.valueOf(this.d));
        z0.a(map, com.bytedance.b.a.a.a.h, String.valueOf(this.e));
        z0.a(map, "query_times", String.valueOf(this.f));
        z0.a(map, "hw_id_version_code", String.valueOf(this.g));
        return map;
    }

    @NonNull
    JSONObject b() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        z0.a(jSONObject, "id", this.f1317a);
        z0.a(jSONObject, "req_id", this.f1318b);
        z0.a(jSONObject, "is_track_limited", this.f1319c);
        z0.a(jSONObject, "take_ms", this.d);
        z0.a(jSONObject, com.bytedance.b.a.a.a.h, this.e);
        z0.a(jSONObject, "query_times", this.f);
        z0.a(jSONObject, "hw_id_version_code", this.g);
        return jSONObject;
    }

    public String toString() {
        return b().toString();
    }

    @Nullable
    @AnyThread
    static c1 a(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            return new c1(jSONObject.optString("id", null), jSONObject.optString("req_id", null), jSONObject.has("is_track_limited") ? Boolean.valueOf(jSONObject.optBoolean("is_track_limited")) : null, jSONObject.has("take_ms") ? Long.valueOf(jSONObject.optLong("take_ms", -1L)) : null, jSONObject.has(com.bytedance.b.a.a.a.h) ? Long.valueOf(jSONObject.optLong(com.bytedance.b.a.a.a.h, -1L)) : null, jSONObject.has("query_times") ? Integer.valueOf(jSONObject.optInt("query_times", -1)) : null, jSONObject.has("hw_id_version_code") ? Long.valueOf(jSONObject.optLong("hw_id_version_code", -1L)) : null);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
