package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class w extends u {
    private String i;
    private String j;
    private String k;
    private String l;
    private long m;
    private long n;

    w() {
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.u
    protected String[] a() {
        return new String[]{"local_time_ms", "integer", "tea_event_index", "integer", com.bytedance.b.a.a.d.p, "varchar", "user_unique_id", "varchar", "category", "varchar", "tag", "varchar", com.bytedance.b.a.a.a.n, "integer", "ext_value", "integer", "params", "varchar", TTDownloadField.TT_LABEL, "varchar", "ab_version", "varchar", "ab_sdk_version", "varchar"};
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.u
    protected JSONObject b() throws JSONException {
        JSONObject jSONObject = !TextUtils.isEmpty(this.l) ? new JSONObject(this.l) : null;
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        jSONObject.put("local_time_ms", this.f1385a);
        jSONObject.put("tea_event_index", this.f1386b);
        jSONObject.put(com.bytedance.b.a.a.d.p, this.f1387c);
        if (!TextUtils.isEmpty(this.d)) {
            jSONObject.put("user_unique_id", this.d);
        }
        jSONObject.put("category", this.i);
        jSONObject.put("tag", this.j);
        jSONObject.put(com.bytedance.b.a.a.a.n, this.m);
        jSONObject.put("ext_value", this.n);
        jSONObject.put(TTDownloadField.TT_LABEL, this.k);
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
        return "event";
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.u
    protected String h() {
        return "" + this.j + ", " + this.k;
    }

    public String j() {
        return this.j;
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
        this.i = cursor.getString(4);
        this.j = cursor.getString(5);
        this.m = cursor.getInt(6);
        this.n = cursor.getInt(7);
        this.l = cursor.getString(8);
        this.k = cursor.getString(9);
        this.e = cursor.getString(10);
        this.f = cursor.getString(11);
        return this;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.u
    protected void a(ContentValues contentValues) {
        contentValues.put("local_time_ms", Long.valueOf(this.f1385a));
        contentValues.put("tea_event_index", Long.valueOf(this.f1386b));
        contentValues.put(com.bytedance.b.a.a.d.p, this.f1387c);
        contentValues.put("user_unique_id", this.d);
        contentValues.put("category", this.i);
        contentValues.put("tag", this.j);
        contentValues.put(com.bytedance.b.a.a.a.n, Long.valueOf(this.m));
        contentValues.put("ext_value", Long.valueOf(this.n));
        contentValues.put("params", this.l);
        contentValues.put(TTDownloadField.TT_LABEL, this.k);
        contentValues.put("ab_version", this.e);
        contentValues.put("ab_sdk_version", this.f);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.u
    protected u b(JSONObject jSONObject) {
        this.f1385a = jSONObject.optLong("local_time_ms", 0L);
        this.f1386b = jSONObject.optLong("tea_event_index", 0L);
        this.f1387c = jSONObject.optString(com.bytedance.b.a.a.d.p, null);
        this.d = jSONObject.optString("user_unique_id", null);
        this.i = jSONObject.optString("category", null);
        this.j = jSONObject.optString("tag", null);
        this.m = jSONObject.optLong(com.bytedance.b.a.a.a.n, 0L);
        this.n = jSONObject.optLong("ext_value", 0L);
        this.l = jSONObject.optString("params", null);
        this.k = jSONObject.optString(TTDownloadField.TT_LABEL, null);
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
        jSONObject.put("category", this.i);
        jSONObject.put("tag", this.j);
        jSONObject.put(com.bytedance.b.a.a.a.n, this.m);
        jSONObject.put("ext_value", this.n);
        jSONObject.put("params", this.l);
        jSONObject.put(TTDownloadField.TT_LABEL, this.k);
        jSONObject.put("ab_version", this.e);
        jSONObject.put("ab_sdk_version", this.f);
    }
}
