package b.b.b;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class r {

    /* renamed from: a, reason: collision with root package name */
    public final SharedPreferences f1181a;

    @WorkerThread
    public r(Context context) {
        this.f1181a = context.getSharedPreferences("device_register_oaid_refine", 0);
    }

    @WorkerThread
    public void a(@Nullable q qVar) {
        if (qVar == null) {
            return;
        }
        this.f1181a.edit().putString("oaid", qVar.b().toString()).apply();
    }

    @Nullable
    @WorkerThread
    public q a() {
        String string = this.f1181a.getString("oaid", "");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(string);
            return new q(jSONObject.optString("id", null), jSONObject.optString("req_id", null), jSONObject.has("is_track_limited") ? Boolean.valueOf(jSONObject.optBoolean("is_track_limited")) : null, jSONObject.has("take_ms") ? Long.valueOf(jSONObject.optLong("take_ms", -1L)) : null, jSONObject.has(com.bytedance.b.a.a.a.h) ? Long.valueOf(jSONObject.optLong(com.bytedance.b.a.a.a.h, -1L)) : null, jSONObject.has("query_times") ? Integer.valueOf(jSONObject.optInt("query_times", -1)) : null, jSONObject.has("hw_id_version_code") ? Long.valueOf(jSONObject.optLong("hw_id_version_code", -1L)) : null);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
