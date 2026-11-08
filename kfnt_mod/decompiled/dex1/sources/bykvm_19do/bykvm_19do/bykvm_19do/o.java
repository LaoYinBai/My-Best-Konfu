package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.SharedPreferences;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class o extends d2 {
    private final i2 e;

    o(i2 i2Var) {
        super(true, false, false);
        this.e = i2Var;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.d2
    protected boolean a(JSONObject jSONObject) throws JSONException {
        SharedPreferences sharedPreferencesT = this.e.t();
        String string = sharedPreferencesT.getString("install_id", null);
        String string2 = sharedPreferencesT.getString(com.bytedance.b.a.c.h.x, null);
        String string3 = sharedPreferencesT.getString("ssid", null);
        j2.a(jSONObject, "install_id", string);
        j2.a(jSONObject, com.bytedance.b.a.c.h.x, string2);
        j2.a(jSONObject, "ssid", string3);
        long j = 0;
        long j2 = sharedPreferencesT.getLong("register_time", 0L);
        if ((j2.b(string) && j2.b(string2)) || j2 == 0) {
            j = j2;
        } else {
            sharedPreferencesT.edit().putLong("register_time", 0L).apply();
        }
        jSONObject.put("register_time", j);
        return true;
    }
}
