package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b0 extends u {
    public long i;
    public String j;
    public String k;
    public int l;

    private JSONObject l() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("page_key", this.k);
        jSONObject.put("refer_page_key", this.j);
        jSONObject.put("is_back", this.l);
        return jSONObject;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.u
    protected String[] a() {
        return new String[]{"local_time_ms", "integer", "tea_event_index", "integer", com.bytedance.b.a.a.d.p, "varchar", "user_unique_id", "varchar", "page_key", "varchar", "refer_page_key", "varchar", "duration", "integer", "is_back", "integer"};
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.u
    protected u b(JSONObject jSONObject) {
        this.f1385a = jSONObject.optLong("local_time_ms", 0L);
        this.f1386b = jSONObject.optLong("tea_event_index", 0L);
        this.f1387c = jSONObject.optString(com.bytedance.b.a.a.d.p, null);
        this.k = jSONObject.optString("page_key", null);
        this.j = jSONObject.optString("refer_page_key", null);
        this.i = jSONObject.optLong("duration", 0L);
        this.l = jSONObject.optInt("is_back", 0);
        return this;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.u
    String d() {
        return "page";
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.u
    protected String h() {
        return super.h() + " name:" + this.k + " duration:" + this.i;
    }

    public boolean j() {
        return this.i == -1;
    }

    public boolean k() {
        return this.k.contains(":");
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.u
    public u a(Cursor cursor) {
        this.f1385a = cursor.getLong(0);
        this.f1386b = cursor.getLong(1);
        this.f1387c = cursor.getString(2);
        this.d = cursor.getString(3);
        this.k = cursor.getString(4);
        this.j = cursor.getString(5);
        this.i = cursor.getLong(6);
        this.l = cursor.getInt(7);
        return this;
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
        jSONObject.put("event", "bav2b_page");
        jSONObject.put("is_bav", 1);
        jSONObject.put("params", l());
        jSONObject.put("datetime", this.g);
        return jSONObject;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.u
    protected void a(ContentValues contentValues) {
        contentValues.put("local_time_ms", Long.valueOf(this.f1385a));
        contentValues.put("tea_event_index", Long.valueOf(this.f1386b));
        contentValues.put(com.bytedance.b.a.a.d.p, this.f1387c);
        contentValues.put("user_unique_id", this.d);
        contentValues.put("page_key", this.k);
        contentValues.put("refer_page_key", this.j);
        contentValues.put("duration", Long.valueOf(this.i));
        contentValues.put("is_back", Integer.valueOf(this.l));
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.u
    protected void a(JSONObject jSONObject) throws JSONException {
        jSONObject.put("page_key", this.k);
        jSONObject.put("refer_page_key", this.j);
        jSONObject.put("duration", this.i);
        jSONObject.put("local_time_ms", this.f1385a);
        jSONObject.put(com.bytedance.b.a.a.d.p, this.f1387c);
        jSONObject.put("tea_event_index", this.f1386b);
        jSONObject.put("is_back", this.l);
    }
}
