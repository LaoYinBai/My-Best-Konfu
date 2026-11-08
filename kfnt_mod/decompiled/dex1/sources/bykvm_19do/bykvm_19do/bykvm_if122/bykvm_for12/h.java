package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12;

import android.content.Context;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.q;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class h extends a {
    private static final SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

    public h(String str, JSONObject jSONObject) {
        super(str, jSONObject);
    }

    public static h b(Context context, d dVar, JSONObject jSONObject) {
        return new h(UUID.randomUUID().toString(), d(context, dVar, jSONObject));
    }

    private static JSONObject d(Context context, d dVar, JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.putOpt("event", dVar.f1952a);
            jSONObject2.putOpt("nt", Integer.valueOf(q.b(context)));
            jSONObject2.putOpt("datetime", d.format(new Date()));
            jSONObject2.putOpt("params", a.a(context, dVar, jSONObject));
        } catch (Exception unused) {
        }
        return jSONObject2;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.a
    public String toString() {
        return "AdEventV3{localId='" + this.f1937a + "', event=" + this.f1938b + '}';
    }
}
