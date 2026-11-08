package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.Context;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class b2 extends d2 {
    private final i2 e;

    b2(Context context, i2 i2Var) {
        super(true, false);
        this.e = i2Var;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.d2
    protected boolean a(JSONObject jSONObject) throws JSONException {
        if (!TextUtils.isEmpty(this.e.i())) {
            jSONObject.put("ab_client", this.e.i());
        }
        if (!TextUtils.isEmpty(this.e.I())) {
            if (l0.f1356a) {
                l0.a("init config has abversion:" + this.e.I(), null);
            }
            jSONObject.put("ab_version", this.e.I());
        }
        if (!TextUtils.isEmpty(this.e.j())) {
            jSONObject.put("ab_group", this.e.j());
        }
        if (TextUtils.isEmpty(this.e.k())) {
            return true;
        }
        jSONObject.put("ab_feature", this.e.k());
        return true;
    }
}
