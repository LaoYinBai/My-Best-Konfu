package b.b.b;

import androidx.annotation.NonNull;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class q {

    /* renamed from: a, reason: collision with root package name */
    public final String f1178a;

    /* renamed from: b, reason: collision with root package name */
    public final String f1179b;

    /* renamed from: c, reason: collision with root package name */
    public final Boolean f1180c;
    public final Long d;
    public final Long e;
    public final Integer f;
    public final Long g;

    public q(String str, String str2, Boolean bool, Long l, Long l2, Integer num, Long l3) {
        this.f1178a = str;
        this.f1179b = str2;
        this.f1180c = bool;
        this.d = l;
        this.e = l2;
        this.f = num;
        this.g = l3;
    }

    @NonNull
    public Map<String, String> a() {
        HashMap map = new HashMap();
        o.a(map, "id", this.f1178a);
        o.a(map, "req_id", this.f1179b);
        o.a(map, "is_track_limited", String.valueOf(this.f1180c));
        o.a(map, "take_ms", String.valueOf(this.d));
        o.a(map, com.bytedance.b.a.a.a.h, String.valueOf(this.e));
        o.a(map, "query_times", String.valueOf(this.f));
        o.a(map, "hw_id_version_code", String.valueOf(this.g));
        return map;
    }

    @NonNull
    public JSONObject b() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        o.a(jSONObject, "id", this.f1178a);
        o.a(jSONObject, "req_id", this.f1179b);
        o.a(jSONObject, "is_track_limited", this.f1180c);
        o.a(jSONObject, "take_ms", this.d);
        o.a(jSONObject, com.bytedance.b.a.a.a.h, this.e);
        o.a(jSONObject, "query_times", this.f);
        o.a(jSONObject, "hw_id_version_code", this.g);
        return jSONObject;
    }

    public String toString() {
        return b().toString();
    }
}
