package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.Context;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class k extends d2 {
    private final Context e;
    private final i2 f;

    k(Context context, i2 i2Var) {
        super(true, false);
        this.e = context;
        this.f = i2Var;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.d2
    protected boolean a(JSONObject jSONObject) throws Throwable {
        Map<String, String> mapA = s0.a(this.e, this.f.t());
        if (mapA == null) {
            return false;
        }
        jSONObject.put("oaid", new JSONObject(mapA));
        return true;
    }
}
