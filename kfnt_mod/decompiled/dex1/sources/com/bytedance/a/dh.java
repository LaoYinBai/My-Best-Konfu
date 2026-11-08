package com.bytedance.a;

import android.content.Context;
import android.content.SharedPreferences;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class dh extends af {

    /* renamed from: a, reason: collision with root package name */
    public final az f3508a;

    public dh(Context context, az azVar, be beVar) {
        super(true, false, false);
        this.f3508a = azVar;
    }

    @Override // com.bytedance.a.af
    public boolean a(JSONObject jSONObject) throws JSONException {
        SharedPreferences sharedPreferences = this.f3508a.e;
        String string = sharedPreferences.getString("bd_did", null);
        be.a(jSONObject, "bd_did", string);
        String string2 = sharedPreferences.getString("install_id", null);
        String string3 = sharedPreferences.getString(this.f3508a.i(), null);
        be.a(jSONObject, "install_id", string2);
        be.a(jSONObject, "ssid", string3);
        long j = 0;
        long j2 = sharedPreferences.getLong("register_time", 0L);
        if ((dj.b(string2) && ((dj.b(null) || dj.b(string)) && dj.b(string3))) || j2 == 0) {
            j = j2;
        } else {
            this.f3508a.e.edit().putLong("register_time", 0L).apply();
        }
        jSONObject.put("register_time", j);
        return true;
    }
}
