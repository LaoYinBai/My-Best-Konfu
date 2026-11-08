package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class y extends u {
    protected String i;
    private boolean j;
    private String k;

    public y(String str, boolean z, String str2) {
        this.k = str;
        this.j = z;
        this.i = str2;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.u
    protected String[] a() {
        return new String[]{"local_time_ms", "integer", "tea_event_index", "integer", com.bytedance.b.a.a.d.p, "varchar", "user_unique_id", "varchar", "event", "varchar", "params", "varchar", "is_bav", "integer", "ab_version", "varchar", "ab_sdk_version", "varchar"};
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.u
    protected JSONObject b() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("local_time_ms", this.f1385a);
        jSONObject.put("tea_event_index", this.f1386b);
        jSONObject.put(com.bytedance.b.a.a.d.p, this.f1387c);
        if (!TextUtils.isEmpty(this.d)) {
            jSONObject.put("user_unique_id", this.d);
        }
        jSONObject.put("event", this.k);
        if (this.j) {
            jSONObject.put("is_bav", 1);
        }
        if (!TextUtils.isEmpty(this.i)) {
            jSONObject.put("params", new JSONObject(this.i));
        }
        jSONObject.put("datetime", this.g);
        if (!TextUtils.isEmpty(this.e)) {
            jSONObject.put("ab_version", this.e);
        }
        if (!TextUtils.isEmpty(this.f)) {
            jSONObject.put("ab_sdk_version", this.f);
        }
        return jSONObject;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.u
    String d() {
        return "eventv3";
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.u
    protected String h() {
        return this.k;
    }

    protected void j() {
    }

    public String k() {
        return this.k;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.u
    public u a(Cursor cursor) {
        this.f1385a = cursor.getLong(0);
        this.f1386b = cursor.getLong(1);
        this.f1387c = cursor.getString(2);
        this.d = cursor.getString(3);
        this.k = cursor.getString(4);
        this.i = cursor.getString(5);
        this.j = cursor.getInt(6) == 1;
        this.e = cursor.getString(7);
        this.f = cursor.getString(8);
        return this;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.u
    protected void a(ContentValues contentValues) {
        contentValues.put("local_time_ms", Long.valueOf(this.f1385a));
        contentValues.put("tea_event_index", Long.valueOf(this.f1386b));
        contentValues.put(com.bytedance.b.a.a.d.p, this.f1387c);
        contentValues.put("user_unique_id", this.d);
        contentValues.put("event", this.k);
        if (this.j && this.i == null) {
            try {
                j();
            } catch (JSONException e) {
                l0.a(e);
            }
        }
        contentValues.put("params", this.i);
        contentValues.put("is_bav", Integer.valueOf(this.j ? 1 : 0));
        contentValues.put("ab_version", this.e);
        contentValues.put("ab_sdk_version", this.f);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.u
    protected u b(JSONObject jSONObject) {
        this.f1385a = jSONObject.optLong("local_time_ms", 0L);
        this.f1386b = jSONObject.optLong("tea_event_index", 0L);
        this.f1387c = jSONObject.optString(com.bytedance.b.a.a.d.p, null);
        this.d = jSONObject.optString("user_unique_id", null);
        this.k = jSONObject.optString("event", null);
        this.i = jSONObject.optString("params", null);
        this.j = jSONObject.optBoolean("is_bav", false);
        this.e = jSONObject.optString("ab_version", null);
        this.f = jSONObject.optString("ab_sdk_version", null);
        return this;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.u
    protected void a(JSONObject jSONObject) throws JSONException {
        jSONObject.put("local_time_ms", this.f1385a);
        jSONObject.put("tea_event_index", this.f1386b);
        jSONObject.put(com.bytedance.b.a.a.d.p, this.f1387c);
        jSONObject.put("user_unique_id", this.d);
        jSONObject.put("event", this.k);
        if (this.j && this.i == null) {
            j();
        }
        jSONObject.put("params", this.i);
        jSONObject.put("is_bav", this.j);
        jSONObject.put("ab_version", this.e);
        jSONObject.put("ab_sdk_version", this.f);
    }
}
