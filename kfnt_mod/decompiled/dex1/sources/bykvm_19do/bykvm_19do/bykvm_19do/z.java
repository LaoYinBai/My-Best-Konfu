package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class z extends u {
    public int i;
    public String j;
    boolean k;
    volatile boolean l;

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.u
    protected String[] a() {
        return new String[]{"local_time_ms", "integer", "tea_event_index", "integer", com.bytedance.b.a.a.d.p, "varchar", "ver_name", "varchar", "ver_code", "integer", "ab_version", "varchar", "ab_sdk_version", "varchar"};
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.u
    protected JSONObject b() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("local_time_ms", this.f1385a);
        jSONObject.put("tea_event_index", this.f1386b);
        jSONObject.put(com.bytedance.b.a.a.d.p, this.f1387c);
        boolean z = this.k;
        if (z) {
            jSONObject.put("is_background", z);
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
        return "launch";
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.u
    public u a(Cursor cursor) {
        this.f1385a = cursor.getLong(0);
        this.f1386b = cursor.getLong(1);
        this.f1387c = cursor.getString(2);
        this.j = cursor.getString(3);
        this.i = cursor.getInt(4);
        this.e = cursor.getString(5);
        this.f = cursor.getString(6);
        return this;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.u
    protected void a(ContentValues contentValues) {
        contentValues.put("local_time_ms", Long.valueOf(this.f1385a));
        contentValues.put("tea_event_index", Long.valueOf(this.f1386b));
        contentValues.put(com.bytedance.b.a.a.d.p, this.f1387c);
        contentValues.put("ver_name", this.j);
        contentValues.put("ver_code", Integer.valueOf(this.i));
        contentValues.put("ab_version", this.e);
        contentValues.put("ab_sdk_version", this.f);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.u
    protected u b(JSONObject jSONObject) {
        this.f1385a = jSONObject.optLong("local_time_ms", 0L);
        this.f1386b = jSONObject.optLong("tea_event_index", 0L);
        this.f1387c = jSONObject.optString(com.bytedance.b.a.a.d.p, null);
        this.e = jSONObject.optString("ab_version", null);
        this.f = jSONObject.optString("ab_sdk_version", null);
        return this;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.u
    protected void a(JSONObject jSONObject) throws JSONException {
        jSONObject.put("local_time_ms", this.f1385a);
        jSONObject.put("tea_event_index", this.f1386b);
        jSONObject.put(com.bytedance.b.a.a.d.p, this.f1387c);
        jSONObject.put("ab_version", this.e);
        jSONObject.put("ab_sdk_version", this.f);
    }
}
