package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.Context;
import android.telephony.TelephonyManager;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class q extends d2 {
    private final Context e;

    q(Context context) {
        super(true, false);
        this.e = context;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.d2
    protected boolean a(JSONObject jSONObject) throws JSONException {
        j2.a(jSONObject, "sim_region", ((TelephonyManager) this.e.getSystemService("phone")).getSimCountryIso());
        return true;
    }
}
