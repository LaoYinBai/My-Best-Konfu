package com.huawei.a.a.b.c.b;

import android.text.TextUtils;
import com.heytap.mcssdk.mode.Message;
import com.huawei.a.a.b.a.b;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d implements f {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;

    public String a() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        this.b = jSONObject.optString("event");
        this.c = jSONObject.optString("properties");
        this.c = com.huawei.a.a.b.a.b.a().a(b.a.AES).a(com.huawei.a.a.b.g.b.a().c(), this.c);
        this.a = jSONObject.optString(Message.TYPE);
        this.d = jSONObject.optString("eventtime");
        this.e = jSONObject.optString("event_session_name");
        this.f = jSONObject.optString("first_session_event");
    }

    public String b() {
        return this.d;
    }

    public void b(String str) {
        this.b = str;
    }

    @Override // com.huawei.a.a.b.c.b.f
    public JSONObject c() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(Message.TYPE, this.a);
        jSONObject.put("eventtime", this.d);
        jSONObject.put("event", this.b);
        jSONObject.put("event_session_name", this.e);
        jSONObject.put("first_session_event", this.f);
        if (TextUtils.isEmpty(this.c)) {
            return null;
        }
        jSONObject.put("properties", new JSONObject(this.c));
        return jSONObject;
    }

    public void c(String str) {
        this.c = str;
    }

    public JSONObject d() throws JSONException {
        JSONObject jSONObjectC = c();
        jSONObjectC.put("properties", com.huawei.a.a.b.a.b.a().a(b.a.AES).b(com.huawei.a.a.b.g.b.a().c(), this.c));
        return jSONObjectC;
    }

    public void d(String str) {
        this.d = str;
    }

    public void e(String str) {
        this.e = str;
    }

    public void f(String str) {
        this.f = str;
    }
}
