package b.b.a.j;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import b.b.a.k.v;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class m extends b {
    public long l;
    public long m;
    public String n;

    @Override // b.b.a.j.b
    public int a(@NonNull Cursor cursor) {
        v.a((Throwable) null);
        return 0;
    }

    @Override // b.b.a.j.b
    public List<String> a() {
        return null;
    }

    @Override // b.b.a.j.b
    public void a(@NonNull ContentValues contentValues) {
        v.a((Throwable) null);
    }

    @Override // b.b.a.j.b
    public JSONObject b() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("local_time_ms", this.f1110c);
        jSONObject.put("tea_event_index", this.d);
        jSONObject.put(com.bytedance.b.a.a.d.p, this.e);
        jSONObject.put("stop_timestamp", this.m / 1000);
        jSONObject.put("duration", this.l / 1000);
        jSONObject.put("datetime", this.k);
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
        if (!TextUtils.isEmpty(this.n)) {
            jSONObject.put("uuid_changed", true);
            if (!TextUtils.equals(this.n, this.e)) {
                jSONObject.put("original_session_id", this.n);
            }
        }
        return jSONObject;
    }

    @Override // b.b.a.j.b
    @NonNull
    public String d() {
        return "terminate";
    }

    @Override // b.b.a.j.b
    public String g() {
        return String.valueOf(this.l);
    }

    @Override // b.b.a.j.b
    public b a(@NonNull JSONObject jSONObject) {
        v.a((Throwable) null);
        return this;
    }
}
