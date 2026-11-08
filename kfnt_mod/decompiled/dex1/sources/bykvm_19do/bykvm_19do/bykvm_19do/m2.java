package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.Context;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class m2 extends d2 {
    m2(Context context, i2 i2Var) {
        super(true, false);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.d2
    protected boolean a(JSONObject jSONObject) throws JSONException {
        String strA = a.k() != null ? a.k().q().a() : "";
        if (TextUtils.isEmpty(strA)) {
            return true;
        }
        jSONObject.put("mc", strA);
        return true;
    }
}
