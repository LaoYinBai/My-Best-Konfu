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
public class cy extends bu {
    public int m;
    public String n;
    public boolean o;
    public String p;
    public int q;

    @Override // com.bytedance.a.bu
    public int a(@NonNull Cursor cursor) {
        super.a(cursor);
        this.n = cursor.getString(10);
        this.m = cursor.getInt(11);
        this.p = cursor.getString(12);
        this.q = cursor.getInt(13);
        return 14;
    }

    @Override // com.bytedance.a.bu
    public List<String> a() {
        List<String> listA = super.a();
        ArrayList arrayList = new ArrayList(listA.size());
        arrayList.addAll(listA);
        arrayList.addAll(Arrays.asList("ver_name", "varchar", "ver_code", "integer", "last_session", "varchar", "is_first_time", "integer"));
        return arrayList;
    }

    @Override // com.bytedance.a.bu
    public void a(@NonNull ContentValues contentValues) {
        super.a(contentValues);
        contentValues.put("ver_name", this.n);
        contentValues.put("ver_code", Integer.valueOf(this.m));
        contentValues.put("last_session", this.p);
        contentValues.put("is_first_time", Integer.valueOf(this.q));
    }

    @Override // com.bytedance.a.bu
    public void a(@NonNull JSONObject jSONObject) {
        de.a("U SHALL NOT PASS!", null);
    }

    @Override // com.bytedance.a.bu
    public bu b(@NonNull JSONObject jSONObject) {
        de.a("U SHALL NOT PASS!", null);
        return null;
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
        boolean z = this.o;
        if (z) {
            jSONObject.put("is_background", z);
        }
        jSONObject.put("datetime", this.l);
        if (!TextUtils.isEmpty(this.i)) {
            jSONObject.put("ab_sdk_version", this.i);
        }
        if (!TextUtils.isEmpty(this.p)) {
            jSONObject.put("uuid_changed", true);
            jSONObject.put("original_session_id", this.p);
        }
        if (this.q == 1) {
            jSONObject.put("$is_first_time", "true");
        }
        return jSONObject;
    }

    @Override // com.bytedance.a.bu
    @NonNull
    public String d() {
        return "launch";
    }

    @Override // com.bytedance.a.bu
    public String h() {
        return this.o ? "bg" : "fg";
    }
}
