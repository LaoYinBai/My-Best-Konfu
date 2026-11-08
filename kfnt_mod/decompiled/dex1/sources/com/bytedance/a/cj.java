package com.bytedance.a;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.bytedance.common.utility.NetworkUtils;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class cj extends bu {
    public String m;
    public String n;
    public String o;
    public String p;
    public long q;
    public long r;

    public cj() {
    }

    public cj(String str, String str2, String str3, long j, long j2, String str4) {
        a(0L);
        this.m = str;
        this.n = str2;
        this.o = str3;
        this.q = j;
        this.r = j2;
        this.p = str4;
        this.k = 0;
    }

    @Override // com.bytedance.a.bu
    public int a(@NonNull Cursor cursor) {
        super.a(cursor);
        this.m = cursor.getString(10);
        this.n = cursor.getString(11);
        this.q = cursor.getLong(12);
        this.r = cursor.getLong(13);
        this.p = cursor.getString(14);
        this.o = cursor.getString(15);
        return 16;
    }

    @Override // com.bytedance.a.bu
    public List<String> a() {
        List<String> listA = super.a();
        ArrayList arrayList = new ArrayList(listA.size());
        arrayList.addAll(listA);
        arrayList.addAll(Arrays.asList("category", "varchar", "tag", "varchar", com.bytedance.b.a.a.a.n, "integer", "ext_value", "integer", "params", "varchar", TTDownloadField.TT_LABEL, "varchar"));
        return arrayList;
    }

    @Override // com.bytedance.a.bu
    public void a(@NonNull ContentValues contentValues) {
        super.a(contentValues);
        contentValues.put("category", this.m);
        contentValues.put("tag", this.n);
        contentValues.put(com.bytedance.b.a.a.a.n, Long.valueOf(this.q));
        contentValues.put("ext_value", Long.valueOf(this.r));
        contentValues.put("params", this.p);
        contentValues.put(TTDownloadField.TT_LABEL, this.o);
    }

    @Override // com.bytedance.a.bu
    public void a(@NonNull JSONObject jSONObject) throws JSONException {
        jSONObject.put("local_time_ms", this.f3434c);
        jSONObject.put("tea_event_index", this.d);
        jSONObject.put("category", this.m);
        jSONObject.put("tag", this.n);
        jSONObject.put(com.bytedance.b.a.a.a.n, this.q);
        jSONObject.put("ext_value", this.r);
        jSONObject.put("params", this.p);
        jSONObject.put(TTDownloadField.TT_LABEL, this.o);
    }

    @Override // com.bytedance.a.bu
    public bu b(@NonNull JSONObject jSONObject) {
        super.b(jSONObject);
        this.d = jSONObject.optLong("tea_event_index", 0L);
        this.m = jSONObject.optString("category", null);
        this.n = jSONObject.optString("tag", null);
        this.q = jSONObject.optLong(com.bytedance.b.a.a.a.n, 0L);
        this.r = jSONObject.optLong("ext_value", 0L);
        this.p = jSONObject.optString("params", null);
        this.o = jSONObject.optString(TTDownloadField.TT_LABEL, null);
        return this;
    }

    @Override // com.bytedance.a.bu
    public JSONObject b() throws JSONException {
        JSONObject jSONObject = !TextUtils.isEmpty(this.p) ? new JSONObject(this.p) : null;
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        jSONObject.put("local_time_ms", this.f3434c);
        jSONObject.put("tea_event_index", this.d);
        jSONObject.put(com.bytedance.b.a.a.d.p, this.e);
        long j = this.f;
        if (j > 0) {
            jSONObject.put("user_id", j);
        }
        if (this.j != NetworkUtils.NetworkType.UNKNOWN.getValue()) {
            jSONObject.put("nt", this.j);
        }
        if (!TextUtils.isEmpty(this.g)) {
            jSONObject.put("user_unique_id", this.g);
        }
        if (!TextUtils.isEmpty(this.h)) {
            jSONObject.put("ssid", this.h);
        }
        jSONObject.put("category", this.m);
        jSONObject.put("tag", this.n);
        jSONObject.put(com.bytedance.b.a.a.a.n, this.q);
        jSONObject.put("ext_value", this.r);
        jSONObject.put(TTDownloadField.TT_LABEL, this.o);
        jSONObject.put("datetime", this.l);
        if (!TextUtils.isEmpty(this.i)) {
            jSONObject.put("ab_sdk_version", this.i);
        }
        return jSONObject;
    }

    @Override // com.bytedance.a.bu
    @NonNull
    public String d() {
        return "event";
    }

    @Override // com.bytedance.a.bu
    public String h() {
        StringBuilder sbA = a.a("");
        sbA.append(this.n);
        sbA.append(", ");
        sbA.append(this.o);
        return sbA.toString();
    }

    @Override // com.bytedance.a.bu
    public String i() {
        return this.p;
    }
}
