package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.Context;
import android.telephony.TelephonyManager;
import anet.channel.strategy.dispatch.DispatchConstants;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class r extends d2 {
    private final Context e;
    private final i2 f;

    r(Context context, i2 i2Var) {
        super(true, false);
        this.e = context;
        this.f = i2Var;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.d2
    protected boolean a(JSONObject jSONObject) {
        TelephonyManager telephonyManager = (TelephonyManager) this.e.getSystemService("phone");
        if (telephonyManager != null) {
            try {
                j2.a(jSONObject, DispatchConstants.CARRIER, telephonyManager.getNetworkOperatorName());
                j2.a(jSONObject, "mcc_mnc", telephonyManager.getNetworkOperator());
                j2.a(jSONObject, "udid", this.f.n() ? i0.a(telephonyManager) : this.f.m());
                return true;
            } catch (Exception e) {
                l0.a(e);
            }
        }
        return false;
    }
}
