package b.b.a.f;

import android.content.Context;
import android.content.SharedPreferences;
import b.b.a.k.x;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class s extends c {

    /* renamed from: a, reason: collision with root package name */
    public final g f1085a;

    public s(Context context, g gVar, h hVar) {
        super(true, false, false);
        this.f1085a = gVar;
    }

    @Override // b.b.a.f.c
    public boolean a(JSONObject jSONObject) throws JSONException {
        SharedPreferences sharedPreferences = this.f1085a.e;
        String string = sharedPreferences.getString("bd_did", null);
        h.a(jSONObject, "bd_did", string);
        String string2 = sharedPreferences.getString("install_id", null);
        String string3 = sharedPreferences.getString(this.f1085a.j(), null);
        h.a(jSONObject, "install_id", string2);
        h.a(jSONObject, "ssid", string3);
        long j = 0;
        long j2 = sharedPreferences.getLong("register_time", 0L);
        if ((x.b(string2) && ((x.b(null) || x.b(string)) && x.b(string3))) || j2 == 0) {
            j = j2;
        } else {
            this.f1085a.e.edit().putLong("register_time", 0L).apply();
        }
        jSONObject.put("register_time", j);
        return true;
    }
}
