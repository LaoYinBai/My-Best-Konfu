package com.bytedance.a;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.bytedance.common.utility.NetworkUtils;
import com.bytedance.sdk.account.platform.api.IAppAuthService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class dn extends bu {
    public String m;
    public String n;

    public dn(String str, String str2) {
        this.n = str;
        this.m = str2;
    }

    @Override // com.bytedance.a.bu
    public int a(@NonNull Cursor cursor) {
        super.a(cursor);
        this.n = cursor.getString(10);
        this.m = cursor.getString(11);
        return 12;
    }

    @Override // com.bytedance.a.bu
    public List<String> a() {
        List<String> listA = super.a();
        ArrayList arrayList = new ArrayList(listA.size());
        arrayList.addAll(listA);
        arrayList.addAll(Arrays.asList("event", "varchar", "params", "varchar"));
        return arrayList;
    }

    @Override // com.bytedance.a.bu
    public void a(@NonNull ContentValues contentValues) {
        super.a(contentValues);
        contentValues.put("event", this.n);
        contentValues.put("params", this.m);
    }

    @Override // com.bytedance.a.bu
    public void a(@NonNull JSONObject jSONObject) throws JSONException {
        jSONObject.put("local_time_ms", this.f3434c);
        jSONObject.put("event", this.n);
        jSONObject.put("params", this.m);
    }

    @Override // com.bytedance.a.bu
    public bu b(@NonNull JSONObject jSONObject) {
        super.b(jSONObject);
        this.n = jSONObject.optString("event", null);
        this.m = jSONObject.optString("params", null);
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
        jSONObject.put("event", this.n);
        if (!TextUtils.isEmpty(this.m)) {
            jSONObject.put("params", new JSONObject(this.m));
        }
        if (this.j != NetworkUtils.NetworkType.UNKNOWN.getValue()) {
            jSONObject.put("nt", this.j);
        }
        jSONObject.put("datetime", this.l);
        if (!TextUtils.isEmpty(this.i)) {
            jSONObject.put("ab_sdk_version", this.i);
        }
        return jSONObject;
    }

    @Override // com.bytedance.a.bu
    @NonNull
    public String d() {
        return IAppAuthService.Scope.PROFILE;
    }

    @Override // com.bytedance.a.bu
    public String h() {
        return this.n;
    }

    @Override // com.bytedance.a.bu
    public String i() {
        return this.m;
    }
}
