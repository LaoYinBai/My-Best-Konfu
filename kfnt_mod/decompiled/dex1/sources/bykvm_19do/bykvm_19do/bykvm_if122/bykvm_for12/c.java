package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12;

import android.content.Context;
import java.util.Map;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {
    public static void a(Context context, d dVar, Map<String, Object> map) {
        if (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().r()) {
            if (context == null) {
                context = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d();
            }
            JSONObject jSONObject = new JSONObject();
            if (map != null) {
                try {
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        jSONObject.put(entry.getKey(), entry.getValue());
                    }
                } catch (JSONException unused) {
                }
            }
            int iK = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().k();
            if (dVar != null) {
                dVar.a("event_id", UUID.randomUUID().toString());
            }
            if (iK == 0 || iK == 2) {
                bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.c().a(h.b(context, dVar, jSONObject));
            }
            if (iK == 0 || iK == 1) {
                bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.a().a(a.b(context, dVar, jSONObject));
            }
        }
    }
}
