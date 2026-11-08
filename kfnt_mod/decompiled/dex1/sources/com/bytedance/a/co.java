package com.bytedance.a;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class co extends bu {

    @NonNull
    public String m;

    @NonNull
    public String n;

    public co(@NonNull String str, @NonNull JSONObject jSONObject) {
        this.n = str;
        this.m = jSONObject.toString();
        this.k = 0;
    }

    @Override // com.bytedance.a.bu
    public int a(@NonNull Cursor cursor) {
        super.a(cursor);
        this.m = cursor.getString(10);
        this.n = cursor.getString(11);
        return 12;
    }

    @Override // com.bytedance.a.bu
    public List<String> a() {
        List<String> listA = super.a();
        ArrayList arrayList = new ArrayList(listA.size());
        arrayList.addAll(listA);
        arrayList.addAll(Arrays.asList("params", "varchar", com.bytedance.b.a.a.d.o, "varchar"));
        return arrayList;
    }

    @Override // com.bytedance.a.bu
    public void a(@NonNull ContentValues contentValues) {
        super.a(contentValues);
        contentValues.put("params", this.m);
        contentValues.put(com.bytedance.b.a.a.d.o, this.n);
    }

    @Override // com.bytedance.a.bu
    public void a(@NonNull JSONObject jSONObject) throws JSONException {
        jSONObject.put("local_time_ms", this.f3434c);
        jSONObject.put("params", this.m);
        jSONObject.put(com.bytedance.b.a.a.d.o, this.n);
    }

    @Override // com.bytedance.a.bu
    public bu b(@NonNull JSONObject jSONObject) {
        super.b(jSONObject);
        this.m = jSONObject.optString("params", null);
        this.n = jSONObject.optString(com.bytedance.b.a.a.d.o, null);
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
        jSONObject.put(com.bytedance.b.a.a.d.o, this.n);
        try {
            JSONObject jSONObject2 = new JSONObject(this.m);
            Iterator<String> itKeys = jSONObject2.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                Object obj = jSONObject2.get(next);
                if (jSONObject.opt(next) != null) {
                    de.a("misc事件存在重复的key", null);
                }
                jSONObject.put(next, obj);
            }
        } catch (Exception e) {
            de.a("解析 event misc 失败", e);
        }
        return jSONObject;
    }

    @Override // com.bytedance.a.bu
    @NonNull
    public String d() {
        return "event_misc";
    }

    @Override // com.bytedance.a.bu
    public String h() {
        StringBuilder sbA = a.a("param:");
        sbA.append(this.m);
        sbA.append(" logType:");
        sbA.append(this.n);
        return sbA.toString();
    }

    @Override // com.bytedance.a.bu
    public String i() {
        return this.m;
    }
}
