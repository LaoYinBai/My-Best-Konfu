package com.bytedance.a;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class di extends bu {
    public long m;
    public String n;
    public String o;
    public int p;
    public String q;

    @Override // com.bytedance.a.bu
    public int a(@NonNull Cursor cursor) {
        super.a(cursor);
        this.o = cursor.getString(10);
        this.n = cursor.getString(11);
        this.m = cursor.getLong(12);
        this.p = cursor.getInt(13);
        this.q = cursor.getString(14);
        return 15;
    }

    @Override // com.bytedance.a.bu
    public List<String> a() {
        List<String> listA = super.a();
        ArrayList arrayList = new ArrayList(listA.size());
        arrayList.addAll(listA);
        arrayList.addAll(Arrays.asList("page_key", "varchar", "refer_page_key", "varchar", "duration", "integer", "is_back", "integer", "last_session", "varchar"));
        return arrayList;
    }

    @Override // com.bytedance.a.bu
    public void a(@NonNull ContentValues contentValues) {
        super.a(contentValues);
        contentValues.put("page_key", this.o);
        contentValues.put("refer_page_key", this.n);
        contentValues.put("duration", Long.valueOf(this.m));
        contentValues.put("is_back", Integer.valueOf(this.p));
        contentValues.put("last_session", this.q);
    }

    @Override // com.bytedance.a.bu
    public void a(@NonNull JSONObject jSONObject) throws JSONException {
        jSONObject.put("local_time_ms", this.f3434c);
        jSONObject.put("page_key", this.o);
        jSONObject.put("refer_page_key", this.n);
        jSONObject.put("duration", this.m);
        jSONObject.put("is_back", this.p);
    }

    @Override // com.bytedance.a.bu
    public bu b(@NonNull JSONObject jSONObject) {
        super.b(jSONObject);
        this.o = jSONObject.optString("page_key", null);
        this.n = jSONObject.optString("refer_page_key", null);
        this.m = jSONObject.optLong("duration", 0L);
        this.p = jSONObject.optInt("is_back", 0);
        return this;
    }

    @Override // com.bytedance.a.bu
    public JSONObject b() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("local_time_ms", this.f3434c);
        jSONObject.put("tea_event_index", this.d);
        jSONObject.put(com.bytedance.b.a.a.d.p, this.e);
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
        jSONObject.put("event", "bav2b_page");
        jSONObject.put("is_bav", 1);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("page_key", this.o);
        jSONObject2.put("refer_page_key", this.n);
        jSONObject2.put("is_back", this.p);
        jSONObject2.put("duration", this.m);
        jSONObject.put("params", jSONObject2);
        jSONObject.put("datetime", this.l);
        return jSONObject;
    }

    @Override // com.bytedance.a.bu
    @NonNull
    public String d() {
        return "page";
    }

    @Override // com.bytedance.a.bu
    public String h() {
        return this.o + ", " + this.m;
    }

    public boolean j() {
        return this.m == -1;
    }
}
